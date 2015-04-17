/*
 * Created on October 05, 2005
 */
package com.tsa.puridiom.invoiceline.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.InvoiceLine;
import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * @author kathleen
 */
public class InvoiceLineDataSet extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
		
		try
		{
			InvoiceLine ivl = (InvoiceLine) incomingRequest.get("invoiceLine");		
			ivl.setBudgetInfoList((List) incomingRequest.get("budgetInfoList"));
			ivl.setAccountList((List) incomingRequest.get("accountList"));
		
			PoLine poLine = (PoLine) incomingRequest.get("poLine") ; 
			if( poLine!= null )
			{
				ivl.setPoLine(poLine);
			}
			
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			Log.error(this, "An Error occurred at InvoiceLineDataSet" + e);            
            e.printStackTrace();
            throw e;
		}		

		return null;
	}
}
