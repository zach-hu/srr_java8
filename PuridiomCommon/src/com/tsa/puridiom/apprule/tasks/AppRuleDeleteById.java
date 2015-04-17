package com.tsa.puridiom.apprule.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class AppRuleDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		AppRule appRule = (AppRule)incomingRequest.get("appRule");
		if(appRule == null)
		{
			appRule = new AppRule();
		}
		AppRuleSetValuesPK setValues = new AppRuleSetValuesPK();
		setValues.setValues(incomingRequest, appRule);
		dbs.delete(appRule);
		this.setStatus(dbs.getStatus()) ;
		return appRule ;
	}

}