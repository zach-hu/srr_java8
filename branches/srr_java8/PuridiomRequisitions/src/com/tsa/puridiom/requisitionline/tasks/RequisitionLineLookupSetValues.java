package com.tsa.puridiom.requisitionline.tasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.ItemLookup;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.CatalogItem;
import com.tsa.puridiom.entity.Contact;
import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.entity.Vendor;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Utility;
import com.tsagate.properties.DictionaryManager;

public class RequisitionLineLookupSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		String oid = (String) incomingRequest.get("organizationId");
		PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
        String userTimeZone = (String) incomingRequest.get("userTimeZone");
        String today  = Dates.today("", userTimeZone);
        Object result = null;

		try
		{
			List newContactList = new ArrayList();
			ItemLookup item = (ItemLookup) incomingRequest.get("itemLookup");
			Vendor vendor = (Vendor) incomingRequest.get("vendor");
			List catalogItemList = (List) incomingRequest.get("catalogItemList");
			CatalogItem catalogItem = null;
			if (catalogItemList!=null && catalogItemList.size()>=1)
			{
				catalogItem = (CatalogItem) catalogItemList.get(0);
			}
			Contact contact = null;
			if(!oid.equalsIgnoreCase("HOY08P"))
			{
				contact = (Contact)incomingRequest.get("contact");
			}
			String contactDefault = "";

			if(oid.equalsIgnoreCase("HOY08P"))
			{
				List contactList = (List) incomingRequest.get("contact");
				String	uid = (String) incomingRequest.get("userId");
				UserProfile	user = UserManager.getInstance().getUser(oid,uid);

				if (contactList != null && contactList.size() > 0)
				{
					for(int x=0; x < contactList.size() ;x++ )
					{
						contact = (Contact) contactList.get(x);
						if(contact.getComp_id().getContactCode().equalsIgnoreCase(user.getNameUdf5()))
						{
							contactDefault = (String) user.getNameUdf5();
						}
						else
						{
							contactDefault = "001";
						}
					}
				}
			}
			if (item == null) {
				result = null ;
				this.setStatus(Status.FAILED) ;
			} else {
				incomingRequest.put("RequisitionLine_catalogId",item.getCatalogId()) ;
				incomingRequest.put("RequisitionLine_itemNumber",item.getItemNumber()) ;
				if(item.getSource().equalsIgnoreCase("XML") && !item.getCatalogId().equalsIgnoreCase("HAGEMEYER"))
				{
					incomingRequest.put("RequisitionLine_commodityCode",item.getCommodity()) ;
				}
				else if(!item.getSource().equalsIgnoreCase("XML"))
				{
					incomingRequest.put("RequisitionLine_commodityCode",item.getCommodity()) ;
				}
				incomingRequest.put("RequisitionLine_description",item.getDescription()) ;
				incomingRequest.put("RequisitionLine_itemLocation",item.getLocation()) ;
				incomingRequest.put("RequisitionLine_itemSource",item.getSource()) ;
				incomingRequest.put("RequisitionLine_receiptRequired",item.getReceiptRequired()) ;

				String reqType = (String)incomingRequest.get("RequisitionHeader_requisitionType");
				BigDecimal unitCost = new BigDecimal(0);
				if(reqType != null && reqType.equals("S") && item.getSource().equals("INV") && !item.getActionCode().equals("B"))
				{
				    incomingRequest.put("RequisitionLine_umCode",item.getUnitOfIssue()) ;
					incomingRequest.put("RequisitionLine_umFactor", item.getUnitOfIssueFactor().toString());

					if (propertiesManager.getProperty("MISC","UseAverage","N").equals("Y")) {
						incomingRequest.put("RequisitionLine_unitPrice", item.getAvgCost().toString());
						unitCost = item.getAvgCost();
					} else {
						incomingRequest.put("RequisitionLine_unitPrice",item.getIssueCost().toString()) ;
						unitCost = item.getIssueCost();
					}
				}
				else
				{
				    incomingRequest.put("RequisitionLine_umCode",item.getUnitOfOrder()) ;
			        incomingRequest.put("RequisitionLine_umFactor", item.getUmFactor().toString());
			        incomingRequest.put("RequisitionLine_unitPrice",item.getOrderCost().toString()) ;
			        unitCost = item.getOrderCost();
				}
				incomingRequest.put("RequisitionLine_duomUmCode", item.getDuomUmCode()) ;
				incomingRequest.put("RequisitionLine_udf1Code",item.getUdf01()) ;
				incomingRequest.put("RequisitionLine_udf2Code",item.getUdf02()) ;
				incomingRequest.put("RequisitionLine_udf3Code",item.getUdf03()) ;
				incomingRequest.put("RequisitionLine_udf4Code",item.getUdf04()) ;
				incomingRequest.put("RequisitionLine_udf5Code",item.getUdf05()) ;
                incomingRequest.put("RequisitionLine_udf6Code",item.getUdf06()) ;
                incomingRequest.put("RequisitionLine_udf7Code",item.getUdf07()) ;
                incomingRequest.put("RequisitionLine_udf8Code",item.getUdf08()) ;
                incomingRequest.put("RequisitionLine_udf9Code",item.getUdf09()) ;
                incomingRequest.put("RequisitionLine_udf10Code",item.getUdf10()) ;
				incomingRequest.put("RequisitionLine_mfgName",item.getMfgName()) ;
				incomingRequest.put("RequisitionLine_taxable",item.getTaxable()) ;
				incomingRequest.put("RequisitionLine_modelNumber",item.getModel()) ;
				incomingRequest.put("RequisitionLine_dateEntered", today);
				incomingRequest.put("RequisitionLine_blanketOrder", item.getBlanketOrder()) ;
				incomingRequest.put("RequisitionLine_shelfLifeRqd", item.getShelfLifeRqd());
				incomingRequest.put("RequisitionLine_memoLine", item.getMemoLine());
				
				String invMSRItem = HiltonUtility.ckNull((String) incomingRequest.get("InvVendor.primaryVendor"));
				if (item != null && !Utility.isEmpty(item.getVendorId()) && (reqType == null || !reqType.equalsIgnoreCase("M") || invMSRItem.equalsIgnoreCase("Y")))
				{
					incomingRequest.put("RequisitionLine_vendorId",item.getVendorId());
					if (oid.equalsIgnoreCase("bly07p") && catalogItem!=null)
					{
						String updateDate = "";
						updateDate = Dates.add(today, catalogItem.getLeadtime().intValue());
						incomingRequest.put("RequisitionLine_requiredDate",updateDate);
					}
				}
				if (!Utility.isEmpty(item.getVendorName()))
				{
					incomingRequest.put("RequisitionLine_vendorName",item.getVendorName());
				}

				if (contact!= null)
				{
					if (oid.equalsIgnoreCase("HOY08P"))
					{
						incomingRequest.put("RequisitionLine_vendContactCode", contactDefault);
					}
					else
					{
						incomingRequest.put("RequisitionLine_vendContactCode", contact.getComp_id().getContactCode());
					}
				}
				if(incomingRequest.containsKey("fromCloneProcedure") && HiltonUtility.ckNull((String)incomingRequest.get("fromCloneProcedure")).equals("Y")){
					BigDecimal lineTotal = new BigDecimal(0);
					lineTotal = item.getQuantity().multiply(unitCost);
					incomingRequest.put("RequisitionLine_lineTotal", lineTotal.toString());
				}else{
					incomingRequest.put("RequisitionLine_lineTotal", "0");
				}
				if (!incomingRequest.containsKey("RequisitionLine_quantity")) {
					incomingRequest.put("RequisitionLine_quantity",item.getQuantity().toString()) ;
				}
				if(item.isAutoRelease())
				{
				    incomingRequest.put("RequisitionLine_autoRelease", "1");
				}

				incomingRequest.put("RequisitionLine_asset", item.getAsset());

				if (!HiltonUtility.isEmpty(item.getCatalogId()) && item.getCatalogId().indexOf("CHEMICAL") >= 0) {
					incomingRequest.put("RequisitionLine_chemicalItemNumber", item.getItemNumber());
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