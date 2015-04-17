package com.tsa.puridiom.receiptheader.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.ReceiptLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 *
 * @author Alexander
 *
 */
public class ReceiptHeaderNStepSetValues extends Task
{
	String[] statusHeader = {DocumentStatus.RCV_INPROGRESS, DocumentStatus.RCV_STEP_1, DocumentStatus.RCV_STEP_2, DocumentStatus.RCV_STEP_3, DocumentStatus.RCV_REJECTED};
	@SuppressWarnings("unchecked")
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		List receiptLineList = (List) incomingRequest.get("receiptLineList");

		boolean[] stepStatus = {false, false, false, false, false};
		int count = 0;

		if(receiptLineList != null && receiptLineList.size() != 0){
			for(int i=0; i<receiptLineList.size(); i++)
			{
				ReceiptLine receiptLine = (ReceiptLine)receiptLineList.get(i);
				String receiptLineStatus = receiptLine.getStatus();

				if (receiptLineStatus.equals(DocumentStatus.RCV_INPROGRESS) && HiltonUtility.ckNull(receiptLine.getQtyStep0Received()).equals(new BigDecimal("0"))) {
					count++;
				}
				else if (receiptLineStatus.equals(DocumentStatus.RCV_STEP_1))
				{
					stepStatus[1]=true;
				}
				else if (receiptLineStatus.equals(DocumentStatus.RCV_STEP_2))
				{
					stepStatus[2]=true;
				}
				else if (receiptLineStatus.equals(DocumentStatus.RCV_STEP_3))
				{
					stepStatus[3]=true;
				}
				else if (receiptLineStatus.equals(DocumentStatus.RCV_REJECTED) || receiptLineStatus.equals(DocumentStatus.CANCELLED))
				{
					count++;
				}

				if (count == receiptLineList.size())
				{
					stepStatus[4]=true;
				}

			}

			for (int i = 0; i < stepStatus.length; i++) {
				if(stepStatus[i])
				{
					incomingRequest.put("ReceiptHeader_receiptStatus", statusHeader[i]);
					break;
				}
			}
		}

		this.setStatus(Status.SUCCEEDED);

		return result;
	}

}