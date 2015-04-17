/*
 * Created on Aug 19, 2003
 */
package com.tsa.puridiom.common.tasks;

import com.tsagate.foundation.processengine.*;

import java.util.*;

/**
 * @author Administrator
 */
public class AutoGenDocNumber extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {
        Map incomingRequest = (Map) object;
        Object result = null;

        try
        {
        	String oid = (String) incomingRequest.get("organizationId");
        	String userId = (String) incomingRequest.get("userId");
        	incomingRequest.put("UserProfile_userId", userId) ;
        	incomingRequest.put("UserProfile_organizationId", oid) ;

            PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
            PuridiomProcess process = processLoader.loadProcess("autogen-next-doc-number.xml");
            process.executeProcess(incomingRequest);

            if (process.getStatus() < Status.SUCCEEDED)
            {
                 throw new Exception("Auto Number process failed.");
            }

            result = (String) incomingRequest.get("nextDocNumber");
            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw e;
        }

		return result;
	}
}
