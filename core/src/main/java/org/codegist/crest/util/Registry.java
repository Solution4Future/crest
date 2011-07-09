/*
 * Copyright 2010 CodeGist.org
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 *
 *  ===================================================================
 *
 *  More information at http://www.codegist.org.
 */

package org.codegist.crest.util;

import org.codegist.crest.CRestException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.codegist.common.collect.Maps.merge;

/**
 * @author laurent.gilles@codegist.org
 */
public final class Registry<K,T> {

    private final Class<T> clazz;
    private final Map<K, Object> mapping;
    private final Map<K, T> cache = new HashMap<K, T>();
    private final Map<String,Object> crestProperties;
    private final T defaultIfNotFound;

    private Registry(Class<T> clazz, Map<K, Object> mapping, T defaultIfNotFound, Map<String, Object> crestProperties) {
        this.clazz = clazz;
        this.defaultIfNotFound = defaultIfNotFound;
        this.mapping = mapping;
        this.crestProperties = crestProperties;
    }

    public boolean contains(K key) {
        return mapping.containsKey(key);
    }

    public T get(K key) {
        T item = cache.get(key);
        if (item == null) {
            item = buildAndCache(key);
        }
        return item;
    }

    private synchronized T buildAndCache(K key) {
        T val = cache.get(key);
        if(val != null) {
            return val;
        }
        Object item = mapping.get(key);
        if (item == null) {
            if(defaultIfNotFound == null) {
                throw new CRestException("No item bound to key: " + key);
            }else{
                item = defaultIfNotFound;
            }
        }
        if (clazz.isAssignableFrom(item.getClass())) {
            val = (T) item;
        } else if (item instanceof ItemDescriptor) {
            val = ((ItemDescriptor<T>) item).instanciate(crestProperties);
        } else {
            throw new IllegalStateException("Shouldn't be here");
        }
        cache(key, val);
        return val;
    }

    private void cache(K key, T item) {
        cache.put(key, item);
        for(Map.Entry<K,Object> entry : mapping.entrySet()){
            if(entry.getValue() instanceof Class && entry.getValue().equals(entry.getClass())) {
                cache.put(entry.getKey(), item);
            }
        }
    }


    private static class ItemDescriptor<T> {
        private final Class<? extends T> clazz;
        private final Map<String, Object> config;

        ItemDescriptor(Class<? extends T> clazz, Map<String, Object> config) {
            this.clazz = clazz;
            this.config = config;
        }

        public T instanciate(Map<String,Object> crestProperties) {
            try {
                return accessible(clazz.getDeclaredConstructor(Map.class)).newInstance(merge(crestProperties,config));
            } catch (InvocationTargetException e) {
                throw CRestException.handle(e);
            } catch (NoSuchMethodException e) {
                try {
                    return accessible(clazz.getDeclaredConstructor()).newInstance();
                } catch (InvocationTargetException e1) {
                    throw CRestException.handle(e1);
                } catch (Exception e1) {
                    throw new CRestException("Class " + clazz + " doesn't have neither default contructor or a Map argument constructor!", e1);
                }
            } catch (Exception e) {
                throw CRestException.handle(e);
            }
        }

        private static <T> Constructor<? extends T> accessible(final Constructor<? extends T> constructor){
            if(!isPublic(constructor.getModifiers()) || !isPublic(constructor.getDeclaringClass().getModifiers())) {
                 AccessController.doPrivileged(new MakeAccessible(constructor));
            }
            return constructor;
        }

        public static boolean isPublic(int modifiers){
            return (modifiers & 0x00000001) != 0;
        }
    }

    private static final class MakeAccessible implements PrivilegedAction<Constructor> {
        private final Constructor constructor;

        private MakeAccessible(Constructor constructor) {
            this.constructor = constructor;
        }

        public Constructor run() {
            constructor.setAccessible(true);
            return constructor;
        }
    }

    public final static class Builder<K,T> {

        private final Map<K, Object> mapping = new HashMap<K, Object>();
        private final Class<T> clazz;
        private final Map<String,Object> crestProperties;
        private T defaultIfNotFound;

        public Builder(Map<String,Object> crestProperties, Class<T> clazz) {
            this.crestProperties = crestProperties;
            this.clazz = clazz;
        }

        public Registry<K,T> build() {
            return new Registry<K,T>(clazz, mapping, defaultIfNotFound, crestProperties);
        }

        public Builder<K,T> register(Class<? extends T> item, K... keys) {
            return register(item, keys, Collections.<String, Object>emptyMap());
        }

        public Builder<K,T> register(Class<? extends T> item, K[] keys, Map<String, Object> itemConfig) {
            for (K mt : keys) {
                mapping.put(mt, new ItemDescriptor<T>(item, itemConfig));
            }
            return this;
        }

        public Builder<K,T> register(T item, K... keys) {
            for (K mt : keys) {
                mapping.put(mt, item);
            }
            return this;
        }

        public Builder<K,T> register(Map<K, Class<? extends T>> mapping) {
            for (Map.Entry<K, Class<? extends T>> e : mapping.entrySet()) {
                register(e.getValue(), e.getKey());
            }
            return this;
        }

        public Builder<K,T> defaultAs(T item) {
            this.defaultIfNotFound = item;
            return this;
        }

    }
}

