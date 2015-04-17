/*
 * Created on Nov 11, 2003 
 */
package com.tsa.puridiom.disbursement.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author renzo
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class DisbursementBackorderExtendedLoadLines extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		try
		{
			List reqLines = (List)incomingRequest.get("RequisitionLines");
			int rows = reqLines.size();
		
			for(int line = 0; line < rows; line++)
			{
				RequisitionLine reqLine = (RequisitionLine)reqLines.get(line);
				if(reqLine.getStatus().equalsIgnoreCase(DocumentStatus.INV_PARTIAL) || reqLine.getStatus().equalsIgnoreCase(DocumentStatus.INV_BACKORDERED))
				{
					incomingRequest.put("requisitionLine", reqLine);		
					
					PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
					PuridiomProcess process = processLoader.loadProcess("disbursement-load-lines.xml");
					process.executeProcess(incomingRequest);
					if(process.getStatus() == Status.FAILED)
					{
						this.setStatus(process.getStatus());
						throw new TsaException("An error ocurred Processing inventory! (line: " + line + ")");
					}
					reqLine = (RequisitionLine)incomingRequest.get("requisitionLine");
					reqLines.set(line, reqLine);
				}
				
			}
			incomingRequest.put("RequisitionLines", reqLines);
			
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return null;
	}

}
