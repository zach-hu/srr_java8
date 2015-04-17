/*
 * Created on Jul 21, 2004
 *
 * @author  * renzo
 * package com.tsa.puridiom.historylog.tasks;.HistoryLogAddFromList.java
 *
 */
package com.tsa.puridiom.historylog.tasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.HistoryLog;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.UniqueKeyGenerator;

public class HistoryLogAddFromList extends Task
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
		 		UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
		        String userTimeZone = (String) incomingRequest.get("userTimeZone");
		        String userId = (String) incomingRequest.get("userId");

	            HistoryLog	history = (HistoryLog)iter.next();

	            history.setIcHistory(new  BigDecimal(ukg.getUniqueKey())) ;
	            history.setLogDate(Dates.today("", userTimeZone)) ;
	            history.setLogTime(Dates.getTimeString(Dates.today("MM/dd/yyyy HH:mm:ss", userTimeZone))) ;
	            history.setUserid(userId) ;
	            history.setTimeZone(userTimeZone) ;

	            incomingRequest.put("historyLog", history);
	            HistoryLogAdd add = new HistoryLogAdd();
	            add.executeTask(incomingRequest);
	            this.setStatus(add.getStatus());
	            if (this.getStatus() != Status.SUCCEEDED)
	            {
	                throw new TsaException("Error occurred updating History record");
	            }
	        }

		    ret = historyList;

		    this.setStatus(Status.SUCCEEDED);
	    }
	    catch (Exception e)
	    {
	        this.setStatus(Status.FAILED);
	        throw e;
	    }
	    return ret;
    }
}
