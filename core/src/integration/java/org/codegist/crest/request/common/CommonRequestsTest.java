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

package org.codegist.crest.request.common;

import org.codegist.crest.BaseCRestTest;
import org.codegist.crest.CRest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Laurent Gilles (laurent.gilles@codegist.org)
 */
public class CommonRequestsTest<T extends Requests>  extends BaseCRestTest<T> {

    public CommonRequestsTest(CRest crest, Class<T> service) {
        super(crest, service);
    }


    public static CRest[] byRestServicesAndCustomContentTypes(){
        return  new CRest[]{
            /* HttpURLConnection based CRest */
            baseBuilder()
            .bindPlainTextDeserializerWith("text/html", "application/custom","application/custom1","application/custom2")
            .build(),
            /* Apache HttpClient based CRest */
            baseBuilder()
            .bindPlainTextDeserializerWith("text/html", "application/custom","application/custom1","application/custom2")
                    .useHttpClientRestService()
            .build(),
        };
    }
}
