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

package org.codegist.crest.param.cookies;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author laurent.gilles@codegist.org
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
        org.codegist.crest.param.cookies.crest.BasicsTest.class,
        org.codegist.crest.param.cookies.crest.CollectionsTest.class,
        org.codegist.crest.param.cookies.crest.DatesTest.class,
        org.codegist.crest.param.cookies.crest.DefaultValuesTest.class,
        org.codegist.crest.param.cookies.crest.EncodingsTest.class,
        org.codegist.crest.param.cookies.crest.NullsTest.class,
        org.codegist.crest.param.cookies.crest.SerializersTest.class
})
public class CookiesCRestSuite {
}