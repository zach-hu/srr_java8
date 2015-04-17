package com.tsa.puridiom.receiptline.tasks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.ReceiptLine;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * 
 * @author Alexander
 *
 */
public class ReceiptLineNStepSetValues extends Task
{
	
	@SuppressWarnings("unchecked")
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;
		
		ReceiptLine receiptLine = (ReceiptLine) incomingRequest.get("receiptLine");
		if(receiptLine != null)
		{
			String receiptLineStatus = receiptLine.getStatus();
			
			if (receiptLineStatus.equals(DocumentStatus.RCV_INPROGRESS) && receiptLine.getInspectionRequired().equalsIgnoreCase("Y")) 
			{
				incomingRequest.put("ReceiptLine_icRecLine", receiptLine.getIcRecLine().toString());
				incomingRequest.put("ReceiptLine_status", DocumentStatus.RCV_STEP_1);
			} 
			else if (receiptLineStatus.equals(DocumentStatus.RCV_STEP_1)) 
			{
				incomingRequest.put("ReceiptLine_icRecLine", receiptLine.getIcRecLine().toString());
				if(receiptLine.getMarkTagRequired().equalsIgnoreCase("M") || receiptLine.getMarkTagRequired().equalsIgnoreCase("T") || receiptLine.getMarkTagRequired().equalsIgnoreCase("H"))
				{
					incomingRequest.put("ReceiptLine_status", DocumentStatus.RCV_STEP_2);
				}
				else
				{
					incomingRequest.put("ReceiptLine_status", DocumentStatus.RCV_STEP_3);
				}
			} 
			else if (receiptLineStatus.equals(DocumentStatus.RCV_STEP_2)) 
			{
				incomingRequest.put("ReceiptLine_icRecLine", receiptLine.getIcRecLine().toString());
				incomingRequest.put("ReceiptLine_status", DocumentStatus.RCV_STEP_3);
			} 
			else if (receiptLineStatus.equals(DocumentStatus.RCV_STEP_3)) 
			{
				incomingRequest.put("isEndStep", Boolean.TRUE);
			}
			else 
			{
				Log.error(this, "Step no valid: " + receiptLineStatus);
				this.setStatus(Status.FAILED);
				new Exception("Step no valid: " + receiptLineStatus);
			}
			
		}
		
		this.setStatus(Status.SUCCEEDED);
		
		return result;
	}

}