package com.tsa.puridiom.userprofile.tasks;

import com.tsa.puridiom.entity.DepartmentBuyer;
import com.tsagate.foundation.processengine.*;

import java.util.Map;

public class UserProfileRetrieveByDeptBuyerSetup extends Task{
    public Object  executeTask (Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        Object result = null;

        try
        {
        	String oid = (String) incomingRequest.get("organizationId");
        	DepartmentBuyer deptbuyer = (DepartmentBuyer) incomingRequest.get("departmentBuyer");

			if (deptbuyer != null)
			{
				incomingRequest.put("UserProfile_userId", deptbuyer.getComp_id().getUserId());
				incomingRequest.put("UserProfile_organizationId", oid);
			}

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