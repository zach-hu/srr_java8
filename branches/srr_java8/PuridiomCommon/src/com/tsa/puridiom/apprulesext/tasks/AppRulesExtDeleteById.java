package com.tsa.puridiom.apprulesext.tasks;
import java.util.Map;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsa.puridiom.entity.*;

public class AppRulesExtDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		AppRulesExt appRulesExt = (AppRulesExt)incomingRequest.get("appRulesExt");
		if(appRulesExt == null)
		{
			appRulesExt = new AppRulesExt();
		}
		AppRulesExtSetValuesPK setValues = new AppRulesExtSetValuesPK();
		setValues.setValues(incomingRequest, appRulesExt);
		dbs.delete(appRulesExt);
		this.setStatus(dbs.getStatus()) ;
		return appRulesExt ;
	}

}