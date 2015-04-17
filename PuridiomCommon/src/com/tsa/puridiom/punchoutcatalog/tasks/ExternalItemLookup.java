package com.tsa.puridiom.punchoutcatalog.tasks;

import com.tsa.puridiom.punchoutcatalog.*;
import com.tsa.puridiom.commodity.CommodityManager;
import com.tsa.puridiom.common.documents.ItemLookup;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.Catalog;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;

import java.math.BigDecimal;
import java.util.*;

public class ExternalItemLookup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String	requestId = (String) incomingRequest.get("puridiomRequestId");

			Map requestParameters = PunchOutRequestManager.getRequestParameters(requestId);
			
			Iterator iterator = requestParameters.keySet().iterator();
			while (iterator.hasNext()) {
			    String	key = (String) iterator.next();
			    if (!incomingRequest.containsKey(key)) {
			        incomingRequest.put(key, requestParameters.get(key));
			    }
			}
			
			String endcodedXml = (String) requestParameters.get("cxml-urlencoded");
			
			Catalog catalog = (Catalog) incomingRequest.get("catalog");
			List itemList = null;
			if (catalog != null) {
				itemList = PunchOutRequestManager.getInstance().processPunchoutOrderMessage(endcodedXml, (String) requestParameters.get("organizationId"), catalog.getCatalogId());
			} else {
				itemList = PunchOutRequestManager.getInstance().processPunchoutOrderMessage(endcodedXml, (String) requestParameters.get("organizationId"));
			}

			incomingRequest.put("lookupStatus","FOUND") ;

			PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
			PuridiomProcess process = processLoader.loadProcess("external-item-lookup-by-id.xml");

			String	icHeader = (String) requestParameters.get("icHeader");
			String	formType = (String) requestParameters.get("formtype");

			incomingRequest.put("formtype", formType);

			if (Utility.ckNull(formType).equals("REQ")) {
				incomingRequest.put("RequisitionHeader_icReqHeader", icHeader);
			} else if (Utility.ckNull(formType).equals("RFQ")) {
				incomingRequest.put("RfqHeader_icRfqHeader", icHeader);
			} else if (Utility.ckNull(formType).equals("PO")) {
				incomingRequest.put("PoHeader_icPoHeader", icHeader);
			}

			if (Utility.isEmpty(icHeader)) {
				throw new Exception("The ic header was not found.");
			}

			if (itemList != null) {
				for (int i=0; i < itemList.size(); i++) {
					ItemLookup itemLookup = (ItemLookup) itemList.get(i);

					if (HiltonUtility.isEmpty(itemLookup.getCatalogId()))
					{
						itemLookup.setCatalogId(catalog.getCatalogId());
					}
					if (HiltonUtility.isEmpty(itemLookup.getVendorId()))
					{
						itemLookup.setVendorId(catalog.getVendorId());
					}

					if (Utility.isEmpty(itemLookup.getReceiptRequired())) {
					    String	receiptRequired = catalog.getReceiptRequired();
					    if (Utility.isEmpty(receiptRequired)) {
					        receiptRequired = "R";
					    }
					    itemLookup.setReceiptRequired(receiptRequired);
					}

					Map updateParameters = new HashMap();
					updateParameters.put("userId", incomingRequest.get("userId"));
					updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
					updateParameters.put("organizationId", incomingRequest.get("organizationId"));
					updateParameters.put("dbsession", incomingRequest.get("dbsession"));
					updateParameters.put("icHeader", icHeader);
					updateParameters.put("formtype", formType);
					updateParameters.put("itemLookup", itemLookup);
					updateParameters.put("createAction", "SAVE");
					updateParameters.put("lookupStatus","FOUND") ;
					updateParameters.put("catalog", catalog);
					updateParameters.put("vendorId",catalog.getVendorId()) ;
					updateParameters.put("punchOutAddAccount",incomingRequest.get("punchOutAddAccount")) ;
					updateParameters.put("userNameUdf1",incomingRequest.get("userNameUdf1")) ;
					updateParameters.put("userNameUdf2",incomingRequest.get("userNameUdf2")) ;
					updateParameters.put("userNameUdf3",incomingRequest.get("userNameUdf3")) ;

					BigDecimal icAccount = CommodityManager.getInstance().getCommodityIcAccount((String) incomingRequest.get("organizationId"), itemLookup.getCommodity());
					if (icAccount.compareTo(new BigDecimal(0)) <= 0)
					{
						icAccount = catalog.getIcAccount();
					}

					updateParameters.put("Account_icHeader", String.valueOf(icAccount));

					if (Utility.ckNull(formType).equals("REQ")) {
						updateParameters.put("RequisitionHeader_icReqHeader", icHeader);
					} else if (Utility.ckNull(formType).equals("RFQ")) {
						updateParameters.put("RfqHeader_icRfqHeader", icHeader);
					} else if (Utility.ckNull(formType).equals("PO")) {
						updateParameters.put("PoHeader_icPoHeader", icHeader);
					}

					process.executeProcess(updateParameters);

					if (process.getStatus() < Status.COMPLETED) {
						throw new Exception("ExternalItemLookup failed.  (ItemLookup = " + itemLookup.toString());
					}

					if (i == 0 && Utility.ckNull(formType).equals("REQ")) {
					    PuridiomProcess hdrUpdateProcess = processLoader.loadProcess("requisitionheader-set-xml-request-info.xml");
					    hdrUpdateProcess.executeProcess(incomingRequest);
					}
				}
			}
			else {
				if (!incomingRequest.containsKey("Catalog_catalogId")) {
					incomingRequest.put("Catalog_catalogId", incomingRequest.get("CatalogItem_catalogId"));
				}
				incomingRequest.put("createAction", "SAVE");
				process.executeProcess(incomingRequest);

				if (process.getStatus() < Status.COMPLETED) {
					throw new Exception("CatalogItemLookup failed.");
				}
			}

			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}
