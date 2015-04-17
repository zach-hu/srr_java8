package com.tsa.puridiom.receipt.tasks;

import com.tsagate.foundation.processengine.*;
import java.util.*;

public class ReceiptTempPropertyListUpdate extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		Object itemNumberObj = incomingRequest.get("InvProperty_itemNumber");
		Object itemLocationObj = incomingRequest.get("InvProperty_itemLocation");
		Object receiptNumberObj = incomingRequest.get("InvProperty_receiptNumber");
		Object icRcObj = incomingRequest.get("InvProperty_icRc");
		Object icRecLineObj = incomingRequest.get("InvProperty_icRecLine");
		Object icPoLineObj = incomingRequest.get("InvProperty_icPoLine");
		Object statusObj = incomingRequest.get("InvProperty_status");
		Object tagNumberObj = incomingRequest.get("InvProperty_tagNumber");
		Object serialNumberObj = incomingRequest.get("InvProperty_serialNumber");
		Object armyNumberObj = incomingRequest.get("InvProperty_armyNumber");
		Object shipNumberObj = incomingRequest.get("InvProperty_shipNumber");
		Object dateInObj = incomingRequest.get("InvProperty_dateIn");
		Object dateOutObj = incomingRequest.get("InvProperty_dateOut");
		Object cblOutNumberObj = incomingRequest.get("InvProperty_cblOutNumber");
		Object remarksObj = incomingRequest.get("InvProperty_remarks");
		Object contractObj = incomingRequest.get("InvProperty_contract");


		if (itemNumberObj instanceof String[]) {
			String	itemNumbers[] = (String[]) itemNumberObj;
			String	itemLocations[] = (String[]) itemLocationObj;
			String	receiptNumbers[] = (String[]) receiptNumberObj;
			String	armyNumbers[] = (String[]) armyNumberObj;
			String	icRcs[] = (String[]) icRcObj;
			String	tagNumbers[] = (String[]) tagNumberObj;
			String	shipNumbers[] = (String[]) shipNumberObj;
			String	cblOutNumbers[] = (String[]) cblOutNumberObj;
			String	serialNumbers[] = (String[]) serialNumberObj;
			String	dateIns[] = (String[]) dateInObj;
			String	dateOuts[] = (String[]) dateOutObj;
			String	contracts[] = (String[]) contractObj;
			String	remarks[] = (String[]) remarksObj;
			String	icRecLines[] = (String[]) icRecLineObj;
			String	icPoLines[] = (String[]) icPoLineObj;
			String	status[] = (String[]) statusObj;

			for (int i=0; i < itemNumbers.length; i++)
			{
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
				PuridiomProcess process = processLoader.loadProcess("invproperty-add-by-id.xml");

					Map updateParameters = new HashMap();
					updateParameters.put("userId", incomingRequest.get("userId"));
					updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
					updateParameters.put("organizationId", incomingRequest.get("organizationId"));
					updateParameters.put("dbsession", incomingRequest.get("dbsession"));

					updateParameters.put("formtype", incomingRequest.get("formtype"));
					updateParameters.put("InvProperty_source", "REC");
					updateParameters.put("InvProperty_status", status[i]);
					updateParameters.put("InvProperty owner", incomingRequest.get("userId"));
					updateParameters.put("InvProperty_itemNumber", itemNumbers[i]);
					updateParameters.put("InvProperty_itemLocation", itemLocations[i]);
					updateParameters.put("InvProperty_serialNumber", serialNumbers[i]);
					updateParameters.put("InvProperty_tagNumber", tagNumbers[i]);
					updateParameters.put("InvProperty_armyNumber", armyNumbers[i]);
					updateParameters.put("InvProperty_cblOutNumber", cblOutNumbers[i]);
					updateParameters.put("InvProperty_shipNumber", shipNumbers[i]);
					updateParameters.put("InvProperty_receiptNumber", receiptNumbers[i]);
					updateParameters.put("InvProperty_dateIn", dateIns[i]);
					updateParameters.put("InvProperty_dateOut", dateOuts[i]);
					updateParameters.put("InvProperty_icRc", icRcs[i]);
					updateParameters.put("InvProperty_icRecLine", icRecLines[i]);
					updateParameters.put("InvProperty_icPoLine", icPoLines[i]);
					updateParameters.put("InvProperty_remarks", remarks[i]);
					updateParameters.put("InvProperty_contract", contracts[i]);

					process.executeProcess(updateParameters);

					if (process.getStatus() < Status.SUCCEEDED) {
						throw new Exception("Serial Number Add Failed - " + serialNumbers[i]);
					}
			}
		}
		else
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
			PuridiomProcess process = processLoader.loadProcess("invproperty-add-by-id.xml");
			process.executeProcess(incomingRequest);

			if (process.getStatus() < Status.SUCCEEDED) {
				throw new Exception("SerialNumber Add failed.");
			}
		}

		this.status = Status.SUCCEEDED;
		return result;
	}
}