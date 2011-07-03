/*
 * Copyright 2010 CodeGist.org
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *
 * ===================================================================
 *
 * More information at http://www.codegist.org.
 */

package org.codegist.crest.security;

import org.codegist.crest.http.HttpEntityWriter;
import org.codegist.crest.http.HttpMethod;
import org.codegist.crest.http.Pair;

/**
 * AuthentificationManager interface
 * @author Laurent Gilles (laurent.gilles@codegist.org)
 */
public interface Authorization {

    /**
     * Sign the request
     */
    AuthorizationToken authorize(HttpMethod method, String url, Pair... parameters);

    /**
     * Refresh the authentification information
     */
    void refresh();

}