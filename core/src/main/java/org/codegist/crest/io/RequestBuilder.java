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

package org.codegist.crest.io;

import org.codegist.crest.config.MethodConfig;
import org.codegist.crest.config.ParamConfig;

/**
 * @author Laurent Gilles (laurent.gilles@codegist.org)
 */
public interface RequestBuilder {

    Request build(MethodConfig methodConfig);

    RequestBuilder params(ParamConfig[] paramConfigs);

    RequestBuilder addParam(ParamConfig paramConfig);

    RequestBuilder addParam(ParamConfig paramConfig, Object value);

}