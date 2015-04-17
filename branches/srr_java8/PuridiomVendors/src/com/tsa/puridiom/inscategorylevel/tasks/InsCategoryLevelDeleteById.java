package com.tsa.puridiom.inscategorylevel.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Task;
import com.tsa.puridiom.entity.*;

import java.util.Map;

public class InsCategoryLevelDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession");
		InsCategoryLevel insCategoryLevel = (InsCategoryLevel)incomingRequest.get("insCategoryLevel");
		if (insCategoryLevel == null)
		{
			insCategoryLevel = new InsCategoryLevel();
		}

		PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
		PuridiomProcess process = processLoader.loadProcess("inscategorylevel-retrieve-by-id.xml");
		process.executeProcess(incomingRequest);
		insCategoryLevel = (InsCategoryLevel) incomingRequest.get("insCategoryLevel");

		if (insCategoryLevel != null){
			dbs.delete(insCategoryLevel);
		}
		this.setStatus(dbs.getStatus());

		return insCategoryLevel;
	}
}
