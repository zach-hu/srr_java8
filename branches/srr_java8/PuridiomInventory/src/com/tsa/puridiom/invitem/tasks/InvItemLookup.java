package com.tsa.puridiom.invitem.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class InvItemLookup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		Object itemNumberObj = incomingRequest.get("InvItem_itemNumber");
		Object itemLocationObj = incomingRequest.get("InvLocation_itemLocation");
		if (itemLocationObj == null) itemLocationObj = incomingRequest.get("InvLocation_id_itemLocation");
		Object quantityObj = incomingRequest.get("quantity");
		Object binIcObj = incomingRequest.get("InvBinLocation_icRc");
		Object issueCostObj = incomingRequest.get("InvItem_issueCost");
		Object unitOfIssueObj = incomingRequest.get("InvItem_unitOfIssue");

		if (itemLocationObj == null) itemLocationObj = incomingRequest.get("InvLocation_id_itemLocation");
		if (quantityObj == null) quantityObj = incomingRequest.get("InvLocation_qtyEoq");

		String	icHeader = (String) incomingRequest.get("icHeader");

		if (Utility.isEmpty(icHeader)) {
			throw new Exception("The ic header was not found.");
		}

		if (itemNumberObj != null) {
			if (itemNumberObj instanceof String[]) {
				String	itemNumbers[] = (String[]) itemNumberObj;
				String	itemLocations[] = (String[]) itemLocationObj;
				String	quantities[] = (String[]) quantityObj;
				String	binIcs[] = (String[]) binIcObj;
				String	issueCosts[] = (String[]) issueCostObj;
				String	unitOfIssues[] = (String[]) unitOfIssueObj;

				for (int i=0; i < itemNumbers.length; i++)
				{
					PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
					PuridiomProcess process = processLoader.loadProcess("invitem-lookup-by-id.xml");

					String	itemNumber = itemNumbers[i];
					String	itemLocation = itemLocations[i];
					String	quantity = quantities[i];
					String	issueCost = "0.00";
					if (issueCostObj != null) {
						issueCost = issueCosts[i];
					}
					String unitOfIssue = "" ;
					if (unitOfIssueObj != null) {
						unitOfIssue = unitOfIssues[i];
					}
					String	binIc = "";
					if (binIcs != null)
					{
						binIc = binIcs[i];
					}
	                if (!HiltonUtility.isEmpty(itemLocation) && !HiltonUtility.isEmpty(itemNumber)) {
						Map updateParameters = new HashMap();
						updateParameters.put("userId", incomingRequest.get("userId"));
						updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
						updateParameters.put("organizationId", incomingRequest.get("organizationId"));
						updateParameters.put("dbsession", incomingRequest.get("dbsession"));
						updateParameters.put("icHeader", icHeader);
						updateParameters.put("formtype", incomingRequest.get("formtype"));
						updateParameters.put("RequisitionHeader_requisitionType", incomingRequest.get("RequisitionHeader_requisitionType"));
						updateParameters.put("InvItem_itemNumber", itemNumber);
						updateParameters.put("InvLocation_itemLocation", itemLocation);
						updateParameters.put("InvBinLocation_icRc", binIc);
						updateParameters.put("quantity", quantity);
						updateParameters.put("InvItem_issueCost", issueCost);
						updateParameters.put("InvItem_unitOfIssue", unitOfIssue);
						updateParameters.put("createAction", "SAVE");
						if("M".equalsIgnoreCase((String) incomingRequest.get("RequisitionHeader_requisitionType")))

						{
							updateParameters.put("InvVendor.itemNumber", itemNumber);
							updateParameters.put("InvVendor.primaryVendor", "Y");
						}

						process.executeProcess(updateParameters);

						if (process.getStatus() < Status.SUCCEEDED) {
							throw new Exception("InvItemLookup failed.  (ItemLocation = " + itemLocation + "; ItemNumber: " + itemNumber);
						}
	                }
				}
			}
			else
			{
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
				PuridiomProcess process = processLoader.loadProcess("invitem-lookup-by-id.xml");
				incomingRequest.put("createAction", "SAVE");
				if("M".equalsIgnoreCase((String) incomingRequest.get("RequisitionHeader_requisitionType")))
				{
					incomingRequest.put("InvVendor.primaryVendor", "Y");
					incomingRequest.put("RequisitionHeader_requisitionType", incomingRequest.get("RequisitionHeader_requisitionType"));
					incomingRequest.put("InvVendor.itemNumber", incomingRequest.get("InvItem_itemNumber"));
				}
				process.executeProcess(incomingRequest);

				if (process.getStatus() < Status.SUCCEEDED) {
					throw new Exception("InvItemLookup failed.");
				}
			}
		}

		this.status = Status.SUCCEEDED;
		return result;
	}
}