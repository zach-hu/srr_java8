package com.tsa.puridiom.validationrules.receipt.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.ReceiptHeader;
import com.tsa.puridiom.entity.ReceiptLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class ReceiptAtLeastOneQuantityReceivedNStepRules extends Task
{
	@SuppressWarnings("unchecked")
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map) object;

		ReceiptHeader receiptHeader = (ReceiptHeader) incomingRequest.get("receiptHeader");
		List lineItems = (List) incomingRequest.get("receiptLineList");

		int atLeastOneQuantiyReceivedStep0 = 0;
		int atLeastOneQuantiyReceivedStep1 = 0;
		int atLeastOneQuantiyReceivedStep2 = 0;
		int atLeastOneQuantiyReceivedStep3 = 0;

		for (int i = 0; i < lineItems.size(); i++)
		{
			ReceiptLine recLine = (ReceiptLine) lineItems.get(i);
			if (recLine.getStatus().equals(DocumentStatus.RCV_INPROGRESS)) 
			{
				atLeastOneQuantiyReceivedStep0++;
			} 

			if (recLine.getStatus().equals(DocumentStatus.RCV_STEP_1)) 
			{
				atLeastOneQuantiyReceivedStep1++;
			}

			if (recLine.getStatus().equals(DocumentStatus.RCV_STEP_2)) 
			{
				atLeastOneQuantiyReceivedStep2++;
			}

			if (recLine.getStatus().equals(DocumentStatus.RCV_STEP_3)) 
			{
				atLeastOneQuantiyReceivedStep3++;
			}
		}


		String strAtLeastOneQuantiyReceivedStep0 = "Y";
		String strAtLeastOneQuantiyReceivedStep1 = "Y";
		String strAtLeastOneQuantiyReceivedStep2 = "Y";
		String strAtLeastOneQuantiyReceivedStep3 = "Y";


		String actionStep = (String)incomingRequest.get("actionStep");

		if ((actionStep == null || actionStep.equalsIgnoreCase("null")) && atLeastOneQuantiyReceivedStep0 > 0) 
		{
			strAtLeastOneQuantiyReceivedStep0 = "N";
			strAtLeastOneQuantiyReceivedStep1 = "Y";
			strAtLeastOneQuantiyReceivedStep2 = "Y";
			strAtLeastOneQuantiyReceivedStep3 = "Y";
		}
		if (actionStep.equalsIgnoreCase("step1") && atLeastOneQuantiyReceivedStep1 > 0) 
		{
			strAtLeastOneQuantiyReceivedStep0 = "Y";
			strAtLeastOneQuantiyReceivedStep1 = "N";
			strAtLeastOneQuantiyReceivedStep2 = "Y";
			strAtLeastOneQuantiyReceivedStep3 = "Y";
		}
		if (actionStep.equalsIgnoreCase("step2") && atLeastOneQuantiyReceivedStep2 > 0) 
		{
			strAtLeastOneQuantiyReceivedStep0 = "Y";
			strAtLeastOneQuantiyReceivedStep1 = "Y";
			strAtLeastOneQuantiyReceivedStep2 = "N";
			strAtLeastOneQuantiyReceivedStep3 = "Y";
		} 
		if (actionStep.equalsIgnoreCase("step3") && atLeastOneQuantiyReceivedStep3 > 0) 
		{
			strAtLeastOneQuantiyReceivedStep0 = "Y";
			strAtLeastOneQuantiyReceivedStep1 = "Y";
			strAtLeastOneQuantiyReceivedStep2 = "Y";
			strAtLeastOneQuantiyReceivedStep3 = "N";
		}

		if (lineItems != null && lineItems.size() > 0)
		{
			for (int i = 0; i < lineItems.size(); i++)
			{
				ReceiptLine recLine = (ReceiptLine) lineItems.get(i);
				if ((actionStep == null || actionStep.equalsIgnoreCase("null")) && recLine.getStatus().equals(DocumentStatus.RCV_INPROGRESS)
						&& recLine.getQtyStep0Received() != null && recLine.getQtyStep0Received().compareTo(new BigDecimal("0")) > 0)
				{
					strAtLeastOneQuantiyReceivedStep0 = "Y";
					break;
				}
				if (actionStep.equalsIgnoreCase("step1") && recLine.getStatus().equals(DocumentStatus.RCV_STEP_1)
						&& (recLine.getQtyStep1Received() != null || recLine.getQtyStep1Accepted() != null) && (recLine.getQtyStep1Received().compareTo(new BigDecimal("0")) > 0 || recLine.getQtyStep1Accepted().compareTo(new BigDecimal("0")) > 0))
				{
					strAtLeastOneQuantiyReceivedStep1 = "Y";
					break;
				}
				if (actionStep.equalsIgnoreCase("step2") && recLine.getStatus().equals(DocumentStatus.RCV_STEP_2)
						&& recLine.getQtyStep2Received() != null && recLine.getQtyStep2Received().compareTo(new BigDecimal("0")) > 0)
				{
					strAtLeastOneQuantiyReceivedStep2 = "Y";
					break;
				}
				if (actionStep.equalsIgnoreCase("step3") && recLine.getStatus().equals(DocumentStatus.RCV_STEP_3)
						&& recLine.getQtyStep3Received() != null && recLine.getQtyStep3Received().compareTo(new BigDecimal("0")) > 0)
				{
					strAtLeastOneQuantiyReceivedStep3 = "Y";
					break;
				}
			}
		}
		
		if(!receiptHeader.getReceiptStatus().equals(DocumentStatus.RCV_INPROGRESS)){
			strAtLeastOneQuantiyReceivedStep0 = "Y";
		}

		incomingRequest.put("atLeastOneQuantiyReceivedStep0", strAtLeastOneQuantiyReceivedStep0);
		incomingRequest.put("atLeastOneQuantiyReceivedStep1", strAtLeastOneQuantiyReceivedStep1);
		incomingRequest.put("atLeastOneQuantiyReceivedStep2", strAtLeastOneQuantiyReceivedStep2);
		incomingRequest.put("atLeastOneQuantiyReceivedStep3", strAtLeastOneQuantiyReceivedStep3);

		this.setStatus(Status.SUCCEEDED);

		return null;
	}
}
