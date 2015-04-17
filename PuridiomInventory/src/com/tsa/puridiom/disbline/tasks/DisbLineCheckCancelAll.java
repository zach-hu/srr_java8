/*
 * Created on Dec 27, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.disbline.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.DisbLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DisbLineCheckCancelAll extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        try
        {
            Map incomingRequest = (Map)object;
            String cancelAll = (String)incomingRequest.get("cancelall");
            boolean multiple = false;
            if(HiltonUtility.isEmpty(cancelAll))
            {
	            String ic = (String)incomingRequest.get("DisbLine_icReqLine");
	            List disbLines = (List)incomingRequest.get("disbLines");
	            BigDecimal bdQtyToDisburse = new BigDecimal(0);
	            BigDecimal icReqLine = new BigDecimal(ic);
	            int countLines = 0;
	            for(int i = 0; i < disbLines.size(); i++)
	            {
	                DisbLine disbLine = (DisbLine) disbLines.get(i);
					if(disbLine.getIcReqLine().compareTo(icReqLine) == 0)
					{
					    bdQtyToDisburse = bdQtyToDisburse.add(disbLine.getQuantity());
					    disbLine.setStatus(DocumentStatus.CANCELLED);
					    multiple = true;
					    countLines++;
					}
					disbLines.set(i, disbLine);
	            }
	            if(multiple && countLines > 1)
	            {
	                incomingRequest.put("cancelall", "?");
	            }
	            if(countLines == 1)
	            {
	                incomingRequest.put("cancelall", "1");
	            }
	            incomingRequest.put("disbQty", bdQtyToDisburse);
            }
            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException(this.getName(), e);
        }
        return super.executeTask(object);
    }
}
