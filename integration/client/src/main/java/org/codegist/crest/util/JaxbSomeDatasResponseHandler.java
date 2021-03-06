/*
 * Copyright 2011 CodeGist.org
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

import org.codegist.common.reflect.Types;
import org.codegist.crest.handler.ResponseHandler;
import org.codegist.crest.io.Response;
import org.codegist.crest.util.model.jaxb.JaxbSomeData;
import org.codegist.crest.util.model.jaxb.JaxbSomeDatas;

import java.util.Collection;

/**
 * @author laurent.gilles@codegist.org
 */
public class JaxbSomeDatasResponseHandler implements ResponseHandler {
    public Object handle(Response response) throws Exception {

        if(response.getExpectedType().isArray() && response.getExpectedType().getComponentType().equals(JaxbSomeData.class)){
            JaxbSomeDatas datas = response.to(JaxbSomeDatas.class);
            return datas.getSomeData().toArray(new JaxbSomeData[datas.getSomeData().size()]);
        }else if (Collection.class.isAssignableFrom(response.getExpectedType()) && Types.getComponentClass(response.getExpectedType(), response.getExpectedGenericType()).equals(JaxbSomeData.class)){
            JaxbSomeDatas datas = response.to(JaxbSomeDatas.class);
            return datas.getSomeData();
        }else{
            throw new IllegalStateException("Should not be here");
        }

    }
}
