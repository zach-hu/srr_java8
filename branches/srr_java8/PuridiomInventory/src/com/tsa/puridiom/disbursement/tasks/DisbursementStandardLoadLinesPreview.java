/*
 * Created on Nov 11, 2003 
 */
package com.tsa.puridiom.disbursement.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * @author renzo
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class DisbursementStandardLoadLinesPreview extends Task
{
	/* (non-Javadoc)
	 * @see com.tsagate.common.processengine.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		try
		{
			Map incomingRequest = (Map)object;
			List reqLines = (List)incomingRequest.get("RequisitionLines");
			int rows = reqLines.size();
		
			for(int line = 0; line < rows; line++)
			{
				RequisitionLine reqLine = (RequisitionLine)reqLines.get(line);
				incomingRequest.put("requisitionLine", reqLine);		
				
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
				PuridiomProcess process = processLoader.loadProcess("disbursement-standard-load-lines.xml");
				process.executeProcess(incomingRequest);
				if(process.getStatus() != Status.SUCCEEDED)
				{
					line = rows;
					throw new Exception("an error occurred creating disb. line for req line " + reqLine.toString());
				}
				reqLine = (RequisitionLine)incomingRequest.get("requisitionLine");
				reqLines.set(line, reqLine);
				this.setStatus(process.getStatus());
			}
			incomingRequest.put("RequisitionLines", reqLines);
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			e.printStackTrace();
		}
		finally
		{	
			return null;
		}
	}

}
