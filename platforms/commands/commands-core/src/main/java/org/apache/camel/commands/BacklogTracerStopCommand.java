/**
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
package org.apache.camel.commands;

import java.io.PrintStream;

import org.apache.camel.CamelContext;
import org.apache.camel.processor.interceptor.BacklogTracer;

/**
 * Command to use the <a href="camel.apache.org/backlogtracer">Backlog Tracer</a>.
 */
public class BacklogTracerStopCommand extends AbstractContextCommand {

    public BacklogTracerStopCommand(String context) {
        super(context);
    }

    @Override
    protected Object performContextCommand(CamelController camelController, CamelContext camelContext, PrintStream out, PrintStream err) throws Exception {
        BacklogTracer backlogTracer = BacklogTracer.getBacklogTracer(camelContext);
        if (backlogTracer == null) {
            backlogTracer = (BacklogTracer) camelContext.getDefaultBacklogTracer();
        }

        // disable tracer and clear counter and the backlog queue
        backlogTracer.setEnabled(false);
        backlogTracer.resetTraceCounter();
        backlogTracer.clear();
        out.println("BacklogTracer stopped on " + camelContext.getName());
        return null;
    }

}
