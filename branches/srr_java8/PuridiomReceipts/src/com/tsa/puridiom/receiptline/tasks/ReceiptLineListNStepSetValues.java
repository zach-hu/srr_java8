package com.tsa.puridiom.receiptline.tasks;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.ReceiptHeader;
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
public class ReceiptLineListNStepSetValues extends Task
{

	@SuppressWarnings("unchecked")
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;
		try{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
			PuridiomProcess process = processLoader.loadProcess("receiptline-update.xml");

			List receiptLineList = (List) incomingRequest.get("receiptLineList");
			List poLineList = (List) incomingRequest.get("poLineList");
			ReceiptHeader receiptHeader = (ReceiptHeader) incomingRequest.get("receiptHeader");
			PoHeader poHeader = (PoHeader) incomingRequest.get("poHeader");

			int cont = 0;
			for(int i=0; i<receiptLineList.size(); i++)
			{
				ReceiptLine receiptLine = (ReceiptLine)receiptLineList.get(i);
				String receiptLineStatus = receiptLine.getStatus();

				PoLine poLine = (PoLine)poLineList.get(i);

				Map updateParameters = new HashMap();
				updateParameters.put("userId", incomingRequest.get("userId"));
				updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
				updateParameters.put("organizationId", incomingRequest.get("organizationId"));
				updateParameters.put("dbsession", incomingRequest.get("dbsession"));

				if(receiptLineStatus.equals(DocumentStatus.RCV_INPROGRESS) && !receiptHeader.getReceiptStatus().equals(DocumentStatus.RCV_INPROGRESS) &&
						HiltonUtility.ckNull(receiptLine.getQtyStep0Received()).compareTo(new BigDecimal("0")) == 0)
				{
					cont++;
				}
				if (receiptLineStatus.equals(DocumentStatus.RCV_INPROGRESS) &&
						HiltonUtility.ckNull(receiptLine.getQtyStep0Rejected()).compareTo(poLine.getQuantity().subtract(poLine.getQtyReceived())) == 0 &&
						poLine.getQuantity().subtract(poLine.getQtyReceived()).compareTo(new BigDecimal(0)) != 0)
				{
					updateParameters.put("ReceiptLine_icRecLine", receiptLine.getIcRecLine().toString());
					updateParameters.put("ReceiptLine_status", DocumentStatus.RCV_REJECTED);
					process.executeProcess(updateParameters);
				}
				else if (receiptLineStatus.equals(DocumentStatus.RCV_INPROGRESS) && HiltonUtility.ckNull(receiptLine.getQtyStep0Received()).compareTo(new BigDecimal("0")) > 0 &&
						HiltonUtility.ckNull(receiptLine.getQtyStep0Rejected()).compareTo(HiltonUtility.ckNull(receiptLine.getQtyStep0Received())) == 0)
				{
					updateParameters.put("ReceiptLine_icRecLine", receiptLine.getIcRecLine().toString());
					updateParameters.put("ReceiptLine_status", DocumentStatus.RCV_REJECTED);
					process.executeProcess(updateParameters);
				}
				else if (receiptLineStatus.equals(DocumentStatus.RCV_INPROGRESS) && HiltonUtility.ckNull(receiptLine.getInspectionRequired()).equalsIgnoreCase("Y"))
				{
					updateParameters.put("ReceiptLine_icRecLine", receiptLine.getIcRecLine().toString());
					//updateParameters.put("ReceiptLine_qtyStep2Received", receiptLine.getQtyStep0Received().toString());
					//updateParameters.put("ReceiptLine_qtyStep2Rejected", receiptLine.getQtyStep0Rejected().toString());
					//updateParameters.put("ReceiptLine_qtyStep2Accepted", receiptLine.getQtyStep0Accepted().toString());
					//updateParameters.put("ReceiptLine_qtyStep3Received", receiptLine.getQtyStep0Received().toString());
					//updateParameters.put("ReceiptLine_qtyStep3Rejected", receiptLine.getQtyStep0Rejected().toString());
					//updateParameters.put("ReceiptLine_qtyStep3Accepted", receiptLine.getQtyStep0Accepted().toString());
					updateParameters.put("ReceiptLine_qtyStep1Received", receiptLine.getQtyStep0Accepted().toString());
					updateParameters.put("ReceiptLine_status", DocumentStatus.RCV_STEP_1);
					process.executeProcess(updateParameters);
				}
				else if (receiptLineStatus.equals(DocumentStatus.RCV_INPROGRESS) && !HiltonUtility.ckNull(receiptLine.getMarkTagRequired()).equalsIgnoreCase("N") && !HiltonUtility.isEmpty(receiptLine.getMarkTagRequired()))
				{
					updateParameters.put("ReceiptLine_icRecLine", receiptLine.getIcRecLine().toString());
					/*
					if (HiltonUtility.ckNull(receiptLine.getQtyStep2Received()).equals(new BigDecimal("0"))) {
						updateParameters.put("ReceiptLine_qtyStep2Received", receiptLine.getQtyStep0Received().toString());
						updateParameters.put("ReceiptLine_qtyStep2Rejected", receiptLine.getQtyStep0Rejected().toString());
						updateParameters.put("ReceiptLine_qtyStep2Accepted", receiptLine.getQtyStep0Accepted().toString());
						updateParameters.put("ReceiptLine_qtyStep3Received", receiptLine.getQtyStep0Received().toString());
						updateParameters.put("ReceiptLine_qtyStep3Rejected", receiptLine.getQtyStep0Rejected().toString());
						updateParameters.put("ReceiptLine_qtyStep3Accepted", receiptLine.getQtyStep0Accepted().toString());
					}*/
					updateParameters.put("ReceiptLine_qtyStep2Received", receiptLine.getQtyStep0Accepted().toString());
					updateParameters.put("ReceiptLine_status", DocumentStatus.RCV_STEP_2);
					process.executeProcess(updateParameters);
				}
				else if (receiptLineStatus.equals(DocumentStatus.RCV_INPROGRESS))
				{
					updateParameters.put("ReceiptLine_icRecLine", receiptLine.getIcRecLine().toString());
					updateParameters.put("ReceiptLine_qtyStep3Received", HiltonUtility.ckNull(receiptLine.getQtyStep0Accepted()).toString());
					//updateParameters.put("ReceiptLine_qtyStep3Rejected", HiltonUtility.ckNull(receiptLine.getQtyStep0Rejected()).toString());
					updateParameters.put("ReceiptLine_qtyStep3Accepted", HiltonUtility.ckNull(receiptLine.getQtyStep0Accepted()).toString());
					updateParameters.put("ReceiptLine_status", DocumentStatus.RCV_STEP_3);
					process.executeProcess(updateParameters);
				}
				else if (receiptLineStatus.equals(DocumentStatus.RCV_STEP_1) && HiltonUtility.ckNull(receiptLine.getQtyStep1Accepted()).compareTo(new BigDecimal("0")) == 0){
					updateParameters.put("ReceiptLine_icRecLine", receiptLine.getIcRecLine().toString());
					updateParameters.put("ReceiptLine_status", DocumentStatus.RCV_REJECTED);
					process.executeProcess(updateParameters);
				}
				else if (receiptLineStatus.equals(DocumentStatus.RCV_STEP_1) && !HiltonUtility.ckNull(receiptLine.getMarkTagRequired()).equalsIgnoreCase("N") && !HiltonUtility.isEmpty(receiptLine.getMarkTagRequired()))
				{
					updateParameters.put("ReceiptLine_icRecLine", receiptLine.getIcRecLine().toString());
					updateParameters.put("ReceiptLine_qtyStep2Received", HiltonUtility.ckNull(receiptLine.getQtyStep1Accepted()).toString());
					updateParameters.put("ReceiptLine_status", DocumentStatus.RCV_STEP_2);
					process.executeProcess(updateParameters);
				}
				else if (receiptLineStatus.equals(DocumentStatus.RCV_STEP_1)){
					updateParameters.put("ReceiptLine_icRecLine", receiptLine.getIcRecLine().toString());
					updateParameters.put("ReceiptLine_qtyStep3Received", HiltonUtility.ckNull(receiptLine.getQtyStep1Accepted()).toString());
					//updateParameters.put("ReceiptLine_qtyStep3Rejected", HiltonUtility.ckNull(receiptLine.getQtyStep1Rejected()).toString());
					updateParameters.put("ReceiptLine_qtyStep3Accepted", HiltonUtility.ckNull(receiptLine.getQtyStep1Accepted()).toString());
					
					updateParameters.put("ReceiptLine_status", DocumentStatus.RCV_STEP_3);
					process.executeProcess(updateParameters);
				}
				else if (receiptLineStatus.equals(DocumentStatus.RCV_STEP_2) && (HiltonUtility.ckNull(receiptLine.getQtyStep2Accepted()).compareTo(new BigDecimal("0")) == 0 && HiltonUtility.ckNull(receiptLine.getQtyStep2Received()).compareTo(new BigDecimal("0")) != 0)){
					updateParameters.put("ReceiptLine_icRecLine", receiptLine.getIcRecLine().toString());
					updateParameters.put("ReceiptLine_status", DocumentStatus.RCV_REJECTED);
					process.executeProcess(updateParameters);
				}
				else if (receiptLineStatus.equals(DocumentStatus.RCV_STEP_2) && (HiltonUtility.ckNull(receiptLine.getQtyStep2Accepted()).compareTo(new BigDecimal("0")) != 0 || HiltonUtility.ckNull(receiptLine.getQtyStep2Received()).compareTo(new BigDecimal("0")) == 0))
				{
					updateParameters.put("ReceiptLine_icRecLine", receiptLine.getIcRecLine().toString());
					updateParameters.put("ReceiptLine_qtyStep3Received", HiltonUtility.ckNull(receiptLine.getQtyStep2Accepted()).toString());
					//updateParameters.put("ReceiptLine_qtyStep3Rejected", HiltonUtility.ckNull(receiptLine.getQtyStep2Rejected()).toString());
					updateParameters.put("ReceiptLine_qtyStep3Accepted", HiltonUtility.ckNull(receiptLine.getQtyStep2Accepted()).toString());
					
					updateParameters.put("ReceiptLine_status", DocumentStatus.RCV_STEP_3);
					process.executeProcess(updateParameters);
				}
				else if (receiptLineStatus.equals(DocumentStatus.RCV_STEP_3))
				{
					cont++;
					updateParameters.put("ReceiptLine_icRecLine", receiptLine.getIcRecLine().toString());
					if(poHeader.getKit().equalsIgnoreCase("Y")){
						updateParameters.put("ReceiptLine_inventoryFlag", "K");
					} else if(!HiltonUtility.isEmpty(poLine.getItemSource()) && poLine.getItemSource().equalsIgnoreCase("INV") && !HiltonUtility.isEmpty(receiptLine.getItemLocation())){
						updateParameters.put("ReceiptLine_inventoryFlag", "I");
					}
					//updateParameters.put("ReceiptLine_status", DocumentStatus.RCV_RECEIVED);
					process.executeProcess(updateParameters);
				}

				if(receiptLine.getStatus().equalsIgnoreCase(DocumentStatus.RCV_REJECTED) || receiptLine.getStatus().equalsIgnoreCase(DocumentStatus.CANCELLED)){
					cont++;
				}

				if(cont == receiptLineList.size()){
					incomingRequest.put("isEndStep", "true");
				}

//				incomingRequest.put("newHistoryReceiptLine", receiptLine);
				receiptLine.setPoLine(poLine);
			}

			incomingRequest.put("receiptLineList", receiptLineList);

			this.setStatus(Status.SUCCEEDED);
		} catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}