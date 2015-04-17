package com.tsa.puridiom.validationrules.receipt.tasks;

import java.math.BigDecimal;
import java.math.MathContext;
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
import com.tsagate.foundation.utility.TsaException;

@SuppressWarnings("unchecked")
public class QtyOrderedByOrderSubType extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		String qtyOrderedByOrderSubType = "succeeded";
		String receiptAmtWithinTolerance = "succeeded";

		Object result = null;
		Object[] qtyAcceptedList = null;
		boolean qty = true;

		try
		{
			// line accounts
			PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
			List poLineList = (List)incomingRequest.get("poLineList");
			List receiptLineList = (List)incomingRequest.get("receiptLineList");
			ReceiptHeader receiptHeader = (ReceiptHeader)incomingRequest.get("receiptHeader");
			if(receiptHeader.getReceiptStatus().equals(DocumentStatus.RCV_INPROGRESS)){
				for (int i = 0; i < receiptLineList.size(); i++) {

					ReceiptLine receiptLine = (ReceiptLine)receiptLineList.get(i);
					PoLine poLine = (PoLine)poLineList.get(i);
					if (receiptAmtWithinTolerance.equalsIgnoreCase("succeeded")) {
						receiptAmtWithinTolerance = this.getReceiptsInProgress(poLine, receiptLine, poHeader);
					}

					if(qty){
						qtyAcceptedList = new Object[poLineList.size()];
						qty = false;
					}

					BigDecimal qtyAccepted = new BigDecimal(0);
					if(poLine.getQtyReceived() != null){
						qtyAccepted = poLine.getQtyReceived();//Here get qty Received to date
					}

					String receiptLine_status = receiptLine.getStatus();

					if(receiptLine_status.equals(DocumentStatus.RCV_INPROGRESS)){
						qtyAccepted = qtyAccepted.add(HiltonUtility.ckNull(receiptLine.getQtyStep0Accepted()));
					}
					qtyAcceptedList[i] = qtyAccepted;
				}

				String poSubType = "";
				if (poHeader != null) {
					poSubType = poHeader.getSubType();
				}
				if(qtyAcceptedList != null){
					for (int i = 0; i < poLineList.size(); i++) {
						PoLine poLine = (PoLine)poLineList.get(i);

						MathContext mc = new MathContext(2);
						if((poSubType.equals("00") || poSubType.equals("20")) && poLine.getQuantity().compareTo(((BigDecimal)qtyAcceptedList[i])) < 0){
							qtyOrderedByOrderSubType = "failed";
							break;
						}
						if((poSubType.equals("01") || poSubType.equals("21")) && poLine.getQuantity().multiply(new BigDecimal(1.1, mc)).compareTo(((BigDecimal)qtyAcceptedList[i])) <= 0){
							qtyOrderedByOrderSubType = "failed";
							break;
						}
						if(poSubType.equals("03")){
							qtyOrderedByOrderSubType = "succeeded";
							break;
						}
					}
				}
			}
            incomingRequest.put("qtyOrderedByOrderSubType", qtyOrderedByOrderSubType);
            incomingRequest.put("receiptAmtWithinTolerance", receiptAmtWithinTolerance);

            result = null;

            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException("An Error occurred at AccountExistRules", e);
        }

        return result;
    }

	public String getReceiptsInProgress(PoLine poLine, ReceiptLine receiptLine, PoHeader poHeader) throws Exception
	{
		String receiptAmtWithinTolerance = "succeeded";
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("retrieve-receipt-lines-by-icpoline.xml");
			BigDecimal receivedAmount = new BigDecimal(0);
			Map newIncomingRequest = new HashMap();
			String poSubType = poHeader.getSubType();

			newIncomingRequest.put("PoLine_icPoLine", poLine.getIcPoLine().toString());

			process.executeProcess(newIncomingRequest);
			List recList = (List)newIncomingRequest.get("receiptsList");
			for (int ix = 0; ix < recList.size(); ix++)
			{
				ReceiptLine recLine = (ReceiptLine) recList.get(ix);
				if (recLine.getInspectionRequired().equalsIgnoreCase("Y"))
				{
					if (recLine.getQtyStep1Accepted().compareTo(new BigDecimal(0)) == 0 && recLine.getQtyStep1Rejected().compareTo(new BigDecimal(0)) == 0 )
					{
						receivedAmount = receivedAmount.add(recLine.getQtyStep0Accepted());
					}
					else
					{
						receivedAmount = receivedAmount.add(recLine.getQtyStep1Accepted());
					}
				}
				else
				{
					receivedAmount = receivedAmount.add(recLine.getQtyStep0Accepted());
				}
			}

			BigDecimal totalReceived = receivedAmount.add(receiptLine.getQtyStep0Accepted());
			MathContext mc = new MathContext(2);
			if ((poSubType.equals("00") || poSubType.equals("20") || poSubType.equals("03") || poSubType.equals("04") || poSubType.equals("99")) && totalReceived.compareTo(poLine.getQuantity()) > 0)
			{
				receiptAmtWithinTolerance = "failed";
			}
			if ((poSubType.equals("01") || poSubType.equals("21")) && totalReceived.compareTo(poLine.getQuantity().multiply(new BigDecimal(1.1, mc))) > 0)
			{
				receiptAmtWithinTolerance = "failed";
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}

		return receiptAmtWithinTolerance;
	}
}