/*
 * Created on Jul 21, 2004
 *
 * @author  * renzo
 * package com.tsa.puridiom.historylog.tasks;.HistoryLogAddFromList.java
 *
 */
package com.tsa.puridiom.inspectionhistory.tasks;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.HistoryLog;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

public class DiscrepHistoryAddFromHistoryLogList extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
	    {
		    Map incomingRequest = (Map)object;

		    List historyList = (List)incomingRequest.get("historyLogList");
		    if (historyList == null) historyList = new ArrayList() ;

		     for (Iterator iter = historyList.iterator(); iter.hasNext();)
	        {
		    	HistoryLog history = (HistoryLog) iter.next() ;
		        String userTimeZone = (String) incomingRequest.get("userTimeZone");
		        String userId = (String) incomingRequest.get("userId");
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));

				PuridiomProcess process = processLoader.loadProcess("discrep-inspection-history-add.xml");

				incomingRequest.put("inspectionComment", history.getDescription()) ;

				process.executeProcess(incomingRequest) ;
	        }

		    ret = historyList;

		    this.setStatus(Status.SUCCEEDED);
	    }
	    catch (Exception e)
	    {
	    	Log.error(this, e);
	        this.setStatus(Status.FAILED);
	        throw e;
	    }
	    return ret;
    }
}
