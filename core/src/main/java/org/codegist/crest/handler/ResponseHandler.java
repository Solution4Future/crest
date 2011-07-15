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

package org.codegist.crest.handler;

import org.codegist.crest.io.Response;

/**
 * Response handler is invoked for each io's as long as the interface doesn't specifically requested for the raw response (Reader or InputStream method return types.)
 * <p>Response handler role is to check for thridparties specific error formatted content in the response, and returns the expected return type for method calls.
 * <p>NB: if the response code if different from HTTP 200, this handler won't be called, error handler will be directly invoked
 * <p>If implementor declares a constructor with a Map argument, it will be called with the user custom properties.
 * @see org.codegist.crest.InterfaceContext#getProperties()
 * @author Laurent Gilles (laurent.gilles@codegist.org)
 */
public interface ResponseHandler {

    Object handle(Response response) throws Exception;

}
