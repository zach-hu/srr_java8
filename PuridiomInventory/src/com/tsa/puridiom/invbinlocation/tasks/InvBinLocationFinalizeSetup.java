/*
 * Created on August 30, 2005
 */
package com.tsa.puridiom.invbinlocation.tasks;

import com.tsa.puridiom.entity.InvItem;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsa.puridiom.entity.ReceiptLine;
import com.tsa.puridiom.entity.UnitOfMeasure;
import com.tsa.puridiom.unitofmeasure.tasks.UnitOfMeasureRetrieveById;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Kelli
 */
public class InvBinLocationFinalizeSetup extends Task
{
	/* (non-Javadoc)
	 * @see com.tsagate.common.processengine.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;

		try {
			InvItem invItem = (InvItem) incomingRequest.get("invItem");
			ReceiptLine receiptLine = (ReceiptLine) incomingRequest.get("receiptLine");
			PoLine poLine = (PoLine) incomingRequest.get("poLine");
			RequisitionLine requisitionLine = (RequisitionLine) incomingRequest.get("requisitionLine") ;

			incomingRequest.put("InvBinLocation_status", "02");
			incomingRequest.put("InvBinLocation_tempIc", "0");
	        incomingRequest.put("InvBinLocation_icRecLine" , receiptLine.getIcRecLine().toString());

	        if (poLine == null) {
		        if (requisitionLine.getUmCode().equals(invItem.getUnitOfIssue())) {
		            // quantity stays the same
		        } else if (requisitionLine.getUmCode().equals(invItem.getUnitOfOrder())) {
		            // use invItem conversion factor to determine issue quantity
		            BigDecimal qty = receiptLine.getQtyAccepted().multiply(invItem.getFactor());
		            incomingRequest.put("InvBinLocation_qtyOnHand", qty.toString());
		        } else {
		            // calculate conversion from poLine uom to invItem unitOfIssue
		            String	userId = (String) incomingRequest.get("userId");
		            String	organizationId = (String) incomingRequest.get("organizationId");
		            DBSession	dbsession = (DBSession) incomingRequest.get("dbsession");

		            Map requestParams = new HashMap();
		            requestParams.put("userId", userId);
						      requestParams.put("userTimeZone", incomingRequest.get("userTimeZone"));
		            requestParams.put("organizationId", organizationId);
		            requestParams.put("dbsession", dbsession);
		            requestParams.put("UnitOfMeasure_umCode", invItem.getUnitOfOrder());

		            UnitOfMeasureRetrieveById uomRetrieve = new UnitOfMeasureRetrieveById();
		            UnitOfMeasure unitOfMeasure = (UnitOfMeasure) uomRetrieve.executeTask(requestParams);

		            BigDecimal qty = receiptLine.getQtyAccepted().multiply(poLine.getUmFactor());
		            qty = qty.divide(unitOfMeasure.getFactor(), BigDecimal.ROUND_HALF_UP);

		            incomingRequest.put("InvBinLocation_qtyOnHand", qty.toString());
		        }
	        } else {
		        if (poLine.getUmCode().equals(invItem.getUnitOfIssue())) {
		            // quantity stays the same
		        } else if (poLine.getUmCode().equals(invItem.getUnitOfOrder())) {
		            // use invItem conversion factor to determine issue quantity
		            BigDecimal qty = receiptLine.getQtyAccepted().multiply(invItem.getFactor());
		            incomingRequest.put("InvBinLocation_qtyOnHand", qty.toString());
		        } else {
		            // calculate conversion from poLine uom to invItem unitOfIssue
		            String	userId = (String) incomingRequest.get("userId");
		            String	organizationId = (String) incomingRequest.get("organizationId");
		            DBSession	dbsession = (DBSession) incomingRequest.get("dbsession");

		            Map requestParams = new HashMap();
		            requestParams.put("userId", userId);
						      requestParams.put("userTimeZone", incomingRequest.get("userTimeZone"));
		            requestParams.put("organizationId", organizationId);
		            requestParams.put("dbsession", dbsession);
		            requestParams.put("UnitOfMeasure_umCode", invItem.getUnitOfOrder());

		            UnitOfMeasureRetrieveById uomRetrieve = new UnitOfMeasureRetrieveById();
		            UnitOfMeasure unitOfMeasure = (UnitOfMeasure) uomRetrieve.executeTask(requestParams);

		            BigDecimal qty = receiptLine.getQtyAccepted().multiply(poLine.getUmFactor());
		            qty = qty.divide(unitOfMeasure.getFactor(), BigDecimal.ROUND_HALF_UP);

		            incomingRequest.put("InvBinLocation_qtyOnHand", qty.toString());
		        }
	        }

	        this.status = Status.SUCCEEDED;
		} catch (Exception e) {
		    this.status = Status.FAILED;
		    throw e;
		}

		return null;
	}

}
