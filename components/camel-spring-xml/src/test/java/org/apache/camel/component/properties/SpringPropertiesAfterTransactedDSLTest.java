/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.component.properties;

import org.apache.camel.spring.SpringTestSupport;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringPropertiesAfterTransactedDSLTest extends SpringTestSupport {

    @Override
    protected AbstractXmlApplicationContext createApplicationContext() {
        return new ClassPathXmlApplicationContext(
                "org/apache/camel/component/properties/SpringPropertiesAfterTransactedDSLTest.xml");
    }

    @Test
    public void testResolveHeaderAfterTransactedDSL() throws Exception {
        getMockEndpoint("mock:transactedHeader").expectedMessageCount(1);
        getMockEndpoint("mock:transactedHeader").message(0).header("myHeader").isEqualTo("transacted");

        template.requestBody("direct:transactedHeader", "Hello");

        assertMockEndpointsSatisfied();
    }

    @Test
    public void testResolveBodyAfterTransactedDSL() throws Exception {
        getMockEndpoint("mock:transactedBody").expectedMessageCount(1);
        getMockEndpoint("mock:transactedBody").message(0).body().isEqualTo("transacted");

        template.requestBody("direct:transactedBody", "Hello");

        assertMockEndpointsSatisfied();
    }

}
