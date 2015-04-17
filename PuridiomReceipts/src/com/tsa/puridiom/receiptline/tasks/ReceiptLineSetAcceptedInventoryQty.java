/**
 * Created on Mar 5, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.poline.tasks.PoLineUpdateInventorySetup.java
 *
 */
package com.tsa.puridiom.receiptline.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.entity.InvItem;
import com.tsa.puridiom.entity.InvLocation;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.ReceiptLine;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class ReceiptLineSetAcceptedInventoryQty extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		try
		{

            ReceiptLine receiptLine = (ReceiptLine) incomingRequest.get("receiptLine");
            BigDecimal qtyOrdered = new BigDecimal(0);
            BigDecimal qtyReceived = new BigDecimal(0);
            String uom = "";
            if(receiptLine.getReceiptType().equalsIgnoreCase("T")){
            	RequisitionLine requisitionLine = (RequisitionLine)incomingRequest.get("requisitionLine");
            	qtyOrdered = requisitionLine.getQuantity();
	            qtyReceived = requisitionLine.getAllocated();
	            uom = requisitionLine.getUmCode();
            } else {
	            PoLine poLine = (PoLine) incomingRequest.get("poLine");
				qtyOrdered = poLine.getQuantity();
	            qtyReceived = poLine.getQtyReceived();
	            uom = poLine.getUmCode();
            }
			InvLocation invLocation = (InvLocation) incomingRequest.get("invLocation");
            InvItem invItem = (InvItem) incomingRequest.get("invItem");
            if (receiptLine != null && invItem != null && invLocation != null) {
            	BigDecimal qtyRemainingOnOrder = new BigDecimal(0);
                BigDecimal qtyOnOrder = invLocation.getQtyOnOrder();
                BigDecimal qtyAccepted = receiptLine.getQtyAccepted();
                if (qtyAccepted.compareTo(new BigDecimal(0)) >= 0)
                {
                	qtyRemainingOnOrder = qtyOrdered.subtract(qtyReceived);
                }

                // poLine.getQtyReceived() was already updated. Then for receive all, the qtyRemainingOnOrder will be zero
                if (qtyRemainingOnOrder.compareTo(new BigDecimal(0)) >= 0) {
                    String invUom = invItem.getUnitOfIssue();
                    BigDecimal factor = invItem.getFactor();

                    if (!invUom.equals(uom)) {
                        String userSpecifiedFactor = (String) incomingRequest.get("receiptLineFactor");
                        if (!Utility.isEmpty(userSpecifiedFactor) && !userSpecifiedFactor.equals("0")) {
                            try {
                                factor = new BigDecimal(userSpecifiedFactor);
                            } catch (Exception e) {

                            }
                        }
                    }

                    if (factor == null) {
                        factor = new BigDecimal(1);
                    }
                    else if (factor.compareTo(new BigDecimal(0)) == 0)
                    {
                        factor = new BigDecimal(1);
                    }

                    qtyOrdered = qtyOrdered.multiply(factor);

                    BigDecimal newQtyOnOrder = qtyOnOrder.subtract(qtyAccepted);

                    if(newQtyOnOrder.compareTo(new BigDecimal(0)) < 0 || newQtyOnOrder.compareTo(invLocation.getQtyOnHand()) <= 0) {
                        newQtyOnOrder = new BigDecimal(0);
                    }

                    invLocation.setQtyOnOrder(newQtyOnOrder);
                    incomingRequest.put("invLocation", invLocation);
                }
            }

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
