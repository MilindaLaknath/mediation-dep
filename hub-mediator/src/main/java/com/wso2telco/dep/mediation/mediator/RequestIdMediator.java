/*******************************************************************************
 * Copyright  (c) 2015-2016, WSO2.Telco Inc. (http://www.wso2telco.com) All Rights Reserved.
 *
 * WSO2.Telco Inc. licences this file to you under  the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package com.wso2telco.dep.mediation.mediator;

import com.wso2telco.dep.mediation.mediator.internal.Type;
import org.apache.synapse.MessageContext;
import org.apache.synapse.mediators.AbstractMediator;

public class RequestIdMediator extends AbstractMediator {

    /**
     * The id.
     */
    private static long id;
    private static final String REQUEST_ID = "requestId";

    /**
     * Gets the unique id.
     *
     * @param axtype  the axtype
     * @param context the context
     * @param appid   the appid
     * @return the unique id
     */
    public static synchronized String getUniqueID(String axtype, MessageContext context, String appid) {
        String requestId = System.currentTimeMillis() + axtype + appid + "0" + id++;
        context.setProperty(REQUEST_ID, requestId);
        return requestId;
    }

    public boolean mediate(MessageContext messageContext) {
        String appId = (String) messageContext.getProperty("api.ut.application.id");
        getUniqueID(Type.PROVISION.getCode(), messageContext, appId);

        return true;
    }
}