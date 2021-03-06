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

package org.codegist.crest.param.forms.crest;

import org.codegist.crest.annotate.EndPoint;
import org.codegist.crest.annotate.FormParam;
import org.codegist.crest.annotate.POST;
import org.codegist.crest.annotate.Path;
import org.codegist.crest.param.forms.common.ISpecialParamsTests;

import java.io.InputStream;
import java.io.Reader;

/**
 * @author laurent.gilles@codegist.org
 */
public class SpecialParamsTest extends ISpecialParamsTests<SpecialParamsTest.SpecialParams> {

    public SpecialParamsTest(CRestHolder crest) {
        super(crest, SpecialParams.class);
    }

    @EndPoint("{crest.server.end-point}")
    @Path("params/form/special")
    @POST
    public static interface SpecialParams extends ISpecialParamsTests.ISpecialParams {


        @Path("inputStream")
        String inputStream(
                @FormParam("p1") InputStream p1,
                @FormParam("p2") InputStream[] p2);

        @Path("reader")
        String reader(
                @FormParam("p1") Reader p1,
                @FormParam("p2") Reader[] p2);
    }

}
