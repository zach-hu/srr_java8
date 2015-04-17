package com.tsa.puridiom.invitem.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import java.util.Map;

public class InvItemDefaultSetup extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			incomingRequest.put("InvItem.itemNumber", "");
			incomingRequest.put("InvItem.itemNumberSuperceded", "");
			incomingRequest.put("InvItem.commodity", "");
			incomingRequest.put("InvItem.icText", "0");
			incomingRequest.put("InvItem.unitOfOrder", "");
			incomingRequest.put("InvItem.cost", "0");
			incomingRequest.put("InvItem.taxable", "");
			incomingRequest.put("InvItem.abcCode", "");
			incomingRequest.put("InvItem.dateEntered", "2003-10-29");
			incomingRequest.put("InvItem.dateExpires", "2003-10-29");
			incomingRequest.put("InvItem.status", "");
			incomingRequest.put("InvItem.owner", "");
			incomingRequest.put("InvItem.source", "");
			incomingRequest.put("InvItem.unitOfIssue", "");
			incomingRequest.put("InvItem.factor", "1");
			incomingRequest.put("InvItem.averageCost", "0");
			incomingRequest.put("InvItem.udf1Code", "");
			incomingRequest.put("InvItem.udf2Code", "");
			incomingRequest.put("InvItem.udf3Code", "");
			incomingRequest.put("InvItem.udf4Code", "");
			incomingRequest.put("InvItem.udf5Code", "");
			incomingRequest.put("InvItem.udf6Code", "");
			incomingRequest.put("InvItem.udf7Code", "");
			incomingRequest.put("InvItem.udf8Code", "");
			incomingRequest.put("InvItem.udf9Code", "");
			incomingRequest.put("InvItem.udf10Code", "");
			incomingRequest.put("InvItem.udf11Code", "");
			incomingRequest.put("InvItem.udf12Code", "");
			incomingRequest.put("InvItem.udf13Code", "");
			incomingRequest.put("InvItem.udf14Code", "");
			incomingRequest.put("InvItem.udf15Code", "");
			incomingRequest.put("InvItem.variance", "0");
			incomingRequest.put("InvItem.imageFile", "");
			incomingRequest.put("InvItem.issueCost", "0");
			incomingRequest.put("InvItem.lastCost", "0");
			incomingRequest.put("InvItem.icNotes", "0");
			incomingRequest.put("InvItem.poNumber", "");
			incomingRequest.put("InvItem.kit", "");
			incomingRequest.put("InvItem.mohMonths", "0");
			incomingRequest.put("InvItem.eoqMonths", "0");
			incomingRequest.put("InvItem.mohTot", "0");
			incomingRequest.put("InvItem.eoqTot", "0");
			incomingRequest.put("InvItem.chargable", "");
			incomingRequest.put("InvItem.maxReqQty", "0");
			incomingRequest.put("InvItem.actionCode", "");
			incomingRequest.put("InvItem.restrictedItem", "");
			incomingRequest.put("InvItem.sumQtyOh", "0");
			incomingRequest.put("InvItem.sumBackorder", "0");
			incomingRequest.put("InvItem.sumUsage", "0");
			incomingRequest.put("InvItem.sumEoq", "0");
			incomingRequest.put("InvItem.sumMinOh", "0");
			incomingRequest.put("InvItem.sumQtyOrder", "0");
			incomingRequest.put("InvItem.lastPo", "");
			incomingRequest.put("InvItem.lastPoDate", "2003-10-29");
			incomingRequest.put("InvItem.assetCode", "");
			incomingRequest.put("InvItem.minReqQty", "0");
			incomingRequest.put("InvItem.receiptRequired", "");
			incomingRequest.put("InvItem.pullIncrement", "0");
			incomingRequest.put("InvItem.usageRecalc", "");
			incomingRequest.put("InvItem.buyerCode", "");
			incomingRequest.put("InvItem.aribaExport", "");
			incomingRequest.put("InvItem.appointedFlag", "");
			incomingRequest.put("InvItem.ssInterface", "");
			incomingRequest.put("InvItem.managedBy", "");
			incomingRequest.put("InvItem.description", "");

			incomingRequest.put("InvItem.modelNumber", "");
			incomingRequest.put("InvItem.mfgName", "");
			incomingRequest.put("InvItem.nsnNumber", "");
			incomingRequest.put("InvItem.gfpProperty", "");
			incomingRequest.put("InvItem.fgpProperty", "");
			incomingRequest.put("InvItem.capProperty", "");

			incomingRequest.put("InvItem.itemType", "INV") ;
			incomingRequest.put("InvItem.barcodeData", "") ;
			incomingRequest.put("InvItem.unitOfUsage", "") ;
			incomingRequest.put("InvItem.dfltMethod", "") ;
			incomingRequest.put("InvItem.drawingNo", "") ;
			incomingRequest.put("InvItem.makeToOrder", "N") ;
			incomingRequest.put("InvItem.dualUmCode", "") ;

			incomingRequest.put("InvItem.reorderLevel", "0") ;
			incomingRequest.put("InvItem.leadDays", "0") ;
			incomingRequest.put("InvItem.icHeader", "0") ;
			incomingRequest.put("InvItem.estCostQty", "0") ;
			incomingRequest.put("InvItem.jobDays", "0") ;
			incomingRequest.put("InvItem.minOrder", "0") ;
			incomingRequest.put("InvItem.usageQty", "0") ;
			incomingRequest.put("InvItem.shelfLifeRqd", "");
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}