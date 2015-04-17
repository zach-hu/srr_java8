/*
 * Created on Oct 31, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.invoiceline.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author kathleen
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class InvoiceLinesAccountUpateAll extends Task
{

    /* (non-Javadoc)
     * @see com.tsagate.foundation.processengine.ITask#executeTask(java.lang.Object)
     */
    public Object executeTask(Object object) throws Exception
    {
        try
        {
            Map incomingRequest = (Map)object;
            List lines = (List)incomingRequest.get("invoiceLineList");
            PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("requisitionlines-update-accounts.xml");

            for(int i = 0; i < lines.size(); i++)
            {
                RequisitionLine line = (RequisitionLine)lines.get(i);

				incomingRequest.put("requisitionLine", line);

				process.executeProcess(incomingRequest);

				if (process.getStatus() < Status.SUCCEEDED)
				{
				    this.setStatus(Status.FAILED);
					throw new Exception("Requisition Line Account Update process failed.");
				}
            }
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException(this.getName(), e);

        }
        return super.executeTask(object);
    }
}
