package com.tsa.puridiom.historylog.tasks;
import java.util.Map;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsa.puridiom.entity.*;

public class HistoryLogDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		HistoryLog historyLog = (HistoryLog)incomingRequest.get("historyLog");
		if(historyLog == null)
		{
			historyLog = new HistoryLog();
		}
		HistoryLogSetValuesPK setValues = new HistoryLogSetValuesPK();
		setValues.setValues(incomingRequest, historyLog);
		dbs.delete(historyLog);
		this.setStatus(dbs.getStatus()) ;
		return historyLog ;
	}

}