package com.tsa.puridiom.approvals.tasks;

import com.tsa.puridiom.apppooluser.tasks.AppPooluserRetrieveBy;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApprovalSetupApproverAppPooluser extends Task
{
	List appLogList = null;
	String[] userList;
	String[] poolList;

    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest	= (Map)object;

        String oid = (String)incomingRequest.get("organizationId");
        PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
        
        if(incomingRequest.containsKey("AppPooluser_userId"))
        {
        	if(incomingRequest.get("AppPooluser_userId") instanceof String[])
        	{
        		userList = (String[])incomingRequest.get("AppPooluser_userId");
        		poolList = (String[])incomingRequest.get("AppPooluser_poolid");
        	}
        	else if(incomingRequest.get("AppPooluser_userId") instanceof String)
        	{
        		userList = new String[1];
        		userList[0] = (String)incomingRequest.get("AppPooluser_userId");
        		Object poolObj = incomingRequest.get("AppPooluser_poolid");
        		if (poolObj instanceof String) {
        			poolList = new String[1];
        			poolList[0] = (String) poolObj;
        		} else {
        			poolList = (String []) poolObj;
        			
        		}
        	}
        }
        try
        {
        	DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
        	AppPooluserRetrieveBy appPooluserRetriveBy = new AppPooluserRetrieveBy();
        	List approvers = new ArrayList();
        	for(int cont = 0; cont < userList.length ; cont++)
        	{
        		AppPooluser appPooluser;
        		Map newIncomingMap = new HashMap();
        		newIncomingMap.put("dbsession", dbs);
        		newIncomingMap.put("AppPooluser_userId", userList[cont]);
        		newIncomingMap.put("AppPooluser_poolid", poolList[cont]);
        		appPooluser = (AppPooluser)(((List)appPooluserRetriveBy.executeTask(newIncomingMap)).get(0));
        		approvers.add(appPooluser);
        		
        	}
        	appLogList = approvers;
            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            Log.error(this, e.toString());
            this.setStatus(Status.FAILED);
        }
        return appLogList;
    }

   
}
