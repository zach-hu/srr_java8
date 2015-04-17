package com.tsa.puridiom.receiptline.tasks;

import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.ReceiptHeader;
import com.tsa.puridiom.entity.ReceiptLine;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Log;

import java.math.BigDecimal;
import java.util.*;

public class ReceiptLineReceiveAll extends Task {
	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("poline-retrieve-by-id.xml");

			List receiptLineList = (List) incomingRequest.get("receiptLineList");
			String receiptMethod = (String) incomingRequest.get("receiptMethod");

			boolean isShipToInv = false;
			ReceiptHeader receiptHeader = (ReceiptHeader)incomingRequest.get("receiptHeader");
			if (receiptHeader != null) {
				isShipToInv = receiptHeader.getShipToInv().equalsIgnoreCase("Y");
			}

			for(int i=0; i<receiptLineList.size(); i++)
			{
				ReceiptLine receiptLine = (ReceiptLine)receiptLineList.get(i);
				PoLine poLine = receiptLine.getPoLine();
				RequisitionLine reqLine = receiptLine.getRequisitionLine() ;

				Map requestParameters = new HashMap();
				BigDecimal qtyReceived = new BigDecimal(0) ;
				BigDecimal quantity = new BigDecimal(0) ;
				BigDecimal received = new BigDecimal(0) ;
				BigDecimal balance = new BigDecimal (0) ;

				try {
					boolean isItemSourceINV = false;

					if (receiptMethod.equalsIgnoreCase("ReceiveByTransfer")) {
						// Receive from transfer
						quantity = reqLine.getQuantity() ;
						received = reqLine.getAllocated() ;
						isItemSourceINV = reqLine.getItemSource().equalsIgnoreCase("INV");
					} else {
						// Receive from po
						quantity = poLine.getQuantity() ;
						received = poLine.getQtyReceived().add(receiptLine.getQtyAccepted()) ;
						if (receiptMethod.equalsIgnoreCase("ReceiveByOrder")) {
							isItemSourceINV = poLine.getItemSource().equalsIgnoreCase("INV");
						}
					}

					boolean hasInvLocation = false;
					if (receiptHeader != null && receiptLine.getItemLocation().equalsIgnoreCase(receiptHeader.getShipToCode())) {
						hasInvLocation = true;
					}

					if (isShipToInv && isItemSourceINV && !hasInvLocation) {
						continue;
					}

					if (received != null) {
						balance = quantity.subtract(received);
					} else {
						balance = quantity ;
					}
					receiptLine.setQtyReceived(balance) ;
					receiptLine.setQtyAccepted(balance) ;

					// Update Receipt Line
					ReceiptLineUpdateById receiptLineTask = new ReceiptLineUpdateById() ;

					Map updateParameters = new HashMap();
					updateParameters.put("userId", incomingRequest.get("userId"));
					updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
					updateParameters.put("organizationId", incomingRequest.get("organizationId"));
					updateParameters.put("dbsession", incomingRequest.get("dbsession"));
					updateParameters.put("receiptLine", receiptLine);

					receiptLineTask.executeTask(updateParameters) ;

				}
				catch (Exception ex) {

				}
				receiptLineList.set(i, receiptLine);
			}
			result = receiptLineList;
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}

		return result;
	}

}