package com.tsa.puridiom.requisitionline.tasks;
import java.util.Map;
import com.tsagate.foundation.utility.Dates ;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.Vendor;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.vendor.VendorManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.UniqueKeyGenerator;

public class RequisitionLineSetupForLookup extends Task {

	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
		String	organizationId = (String) incomingRequest.get("organizationId") ;
		String	userId = (String) incomingRequest.get("userId") ;
        String  userTimeZone = (String) incomingRequest.get("userTimeZone") ;
        String nonStandardItem = HiltonUtility.ckNull((String) incomingRequest.get("nonStandardItem"));
        String copyAccount = HiltonUtility.ckNull((String)incomingRequest.get("copyAccount"));

		this.setStatus(Status.COMPLETED);
		String today = Dates.today("", userTimeZone) ;

		PropertiesManager propertiesManager = PropertiesManager.getInstance(organizationId) ;
		UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
		String icReqLine = ukg.getUniqueKey().toString();
		incomingRequest.put("RequisitionLine_icReqLine", icReqLine);
		incomingRequest.put("RequisitionLine_icLineHistory",	ukg.getUniqueKey().toString());
		String DefaultUdfSupplier = propertiesManager.getProperty("REQ OPTIONS", "DEFAULT UDF1 SUPPLIER", "N") ;

		if (!incomingRequest.containsKey("RequisitionLine_itemNumber"))	{
			incomingRequest.put("RequisitionLine_itemNumber", "");
		}
		if (!incomingRequest.containsKey("RequisitionLine_itemSource"))	{
			incomingRequest.put("RequisitionLine_itemSource", "");
		}
		if (!incomingRequest.containsKey("RequisitionLine_umCode"))	{
			incomingRequest.put("RequisitionLine_umCode", "EA");
		}
		if (!incomingRequest.containsKey("RequisitionLine_umFactor"))	{
			incomingRequest.put("RequisitionLine_umFactor", "1");
		}
		if (!incomingRequest.containsKey("RequisitionLine_quantity"))	{
			incomingRequest.put("RequisitionLine_quantity", "1");
		}
		if (!incomingRequest.containsKey("RequisitionLine_unitPrice"))	{
			incomingRequest.put("RequisitionLine_unitPrice", "0");
		}
		if (!incomingRequest.containsKey("RequisitionLine_lineTotal"))	{
					incomingRequest.put("RequisitionLine_lineTotal", "0");
		}
		if (!incomingRequest.containsKey("RequisitionLine_commodityCode"))	{
			incomingRequest.put("RequisitionLine_commodityCode", "");

		}
		if (!incomingRequest.containsKey("RequisitionLine_taxable"))	{
			incomingRequest.put("RequisitionLine_taxable", "Y");
		}
		if (!incomingRequest.containsKey("RequisitionLine_asset"))	{
			incomingRequest.put("RequisitionLine_asset", "N");
		}
		if (!incomingRequest.containsKey("RequisitionLine_discountSource"))	{
			incomingRequest.put("RequisitionLine_discountSource", "");
		}
		if (!incomingRequest.containsKey("RequisitionLine_discountAmount"))	{
			incomingRequest.put("RequisitionLine_discountAmount", "0");
		}
		if (!incomingRequest.containsKey("RequisitionLine_shippingCharges"))	{
			incomingRequest.put("RequisitionLine_shippingCharges", "0");
		}
		if (!incomingRequest.containsKey("RequisitionLine_taxShipping"))	{
			incomingRequest.put("RequisitionLine_taxShipping", "N");
		}
		if (!incomingRequest.containsKey("RequisitionLine_otherCharges"))	{
			incomingRequest.put("RequisitionLine_otherCharges", "0");
		}
		if (!incomingRequest.containsKey("RequisitionLine_otherDescription"))	{
			incomingRequest.put("RequisitionLine_otherDescription", "");
		}
		if (!incomingRequest.containsKey("RequisitionLine_taxOther"))	{
			incomingRequest.put("RequisitionLine_taxOther", "N");
		}
		if (!incomingRequest.containsKey("RequisitionLine_icPoLine"))	{
			incomingRequest.put("RequisitionLine_icPoLine", "0");
		}
		if (!incomingRequest.containsKey("RequisitionLine_splits"))	{
			incomingRequest.put("RequisitionLine_splits", "N");
		}
		if (!incomingRequest.containsKey("RequisitionLine_status"))	{
			incomingRequest.put("RequisitionLine_status", "1000");
		}
		if (!incomingRequest.containsKey("RequisitionLine_commentFlag"))	{
			incomingRequest.put("RequisitionLine_commentFlag", "N");
		}
		if (!incomingRequest.containsKey("RequisitionLine_taxAmount"))	{
			incomingRequest.put("RequisitionLine_taxAmount", "0");
		}
		if (!incomingRequest.containsKey("RequisitionLine_shippingTaxAmt"))	{
			incomingRequest.put("RequisitionLine_shippingTaxAmt", "0");
		}
		if (!incomingRequest.containsKey("RequisitionLine_otherTaxAmount"))	{
			incomingRequest.put("RequisitionLine_otherTaxAmount", "0");
		}
		if (!incomingRequest.containsKey("RequisitionLine_assignedBuyer"))	{
			incomingRequest.put("RequisitionLine_assignedBuyer", "UNASSIGNED");
		}
		if (!incomingRequest.containsKey("RequisitionLine_assignedDate"))	{
			incomingRequest.put("RequisitionLine_assignedDate", today);
		}
		if (!incomingRequest.containsKey("RequisitionLine_allocated"))	{
			incomingRequest.put("RequisitionLine_allocated", "0");
		}
		if (!incomingRequest.containsKey("RequisitionLine_backordered"))	{
			incomingRequest.put("RequisitionLine_backordered", "0");
		}
		if (!incomingRequest.containsKey("RequisitionLine_mfgName"))	{
			incomingRequest.put("RequisitionLine_mfgName", "");
		}
		if (!incomingRequest.containsKey("RequisitionLine_modelNumber"))	{
			incomingRequest.put("RequisitionLine_modelNumber", "");
		}
		if (!incomingRequest.containsKey("RequisitionLine_udf1Code"))	{
			incomingRequest.put("RequisitionLine_udf1Code", propertiesManager.getProperty("RQL DEFAULTS","Udf1Code",""));
		}
		if (!incomingRequest.containsKey("RequisitionLine_udf2Code"))	{
			incomingRequest.put("RequisitionLine_udf2Code", propertiesManager.getProperty("RQL DEFAULTS","Udf2Code",""));
		}
		if (!incomingRequest.containsKey("RequisitionLine_udf3Code"))	{
			incomingRequest.put("RequisitionLine_udf3Code", propertiesManager.getProperty("RQL DEFAULTS","Udf3Code",""));
		}
		if (!incomingRequest.containsKey("RequisitionLine_udf4Code"))	{
			incomingRequest.put("RequisitionLine_udf4Code", propertiesManager.getProperty("RQL DEFAULTS","Udf4Code",""));
		}
		if (!incomingRequest.containsKey("RequisitionLine_udf5Code"))	{
			incomingRequest.put("RequisitionLine_udf5Code", propertiesManager.getProperty("RQL DEFAULTS","Udf5Code",""));
		}
        if (!incomingRequest.containsKey("RequisitionLine_udf6Code"))   {
            incomingRequest.put("RequisitionLine_udf6Code", propertiesManager.getProperty("RQL DEFAULTS","Udf6Code",""));
        }
        if (!incomingRequest.containsKey("RequisitionLine_udf7Code"))   {
            incomingRequest.put("RequisitionLine_udf7Code", propertiesManager.getProperty("RQL DEFAULTS","Udf7Code",""));
        }
        if (!incomingRequest.containsKey("RequisitionLine_udf8Code"))   {
            incomingRequest.put("RequisitionLine_udf8Code", propertiesManager.getProperty("RQL DEFAULTS","Udf8Code",""));
        }
        if (!incomingRequest.containsKey("RequisitionLine_udf9Code"))   {
            incomingRequest.put("RequisitionLine_udf9Code", propertiesManager.getProperty("RQL DEFAULTS","Udf9Code",""));
        }
        if (!incomingRequest.containsKey("RequisitionLine_udf10Code"))  {
            incomingRequest.put("RequisitionLine_udf10Code", propertiesManager.getProperty("RQL DEFAULTS","Udf10Code",""));
        }
		if (!incomingRequest.containsKey("RequisitionLine_rqLineKey"))	{
			incomingRequest.put("RequisitionLine_rqLineKey", "");
		}
		if (!incomingRequest.containsKey("RequisitionLine_receiptRequired"))	{
			incomingRequest.put("RequisitionLine_receiptRequired", "R");
		}
		if (!incomingRequest.containsKey("RequisitionLine_roFlag"))	{
			incomingRequest.put("RequisitionLine_roFlag", "N");
		}
		if (!incomingRequest.containsKey("RequisitionLine_routing"))	{
			incomingRequest.put("RequisitionLine_routing", "");
		}
		if (!incomingRequest.containsKey("RequisitionLine_description"))	{
			incomingRequest.put("RequisitionLine_description", "");
		}
		if (!incomingRequest.containsKey("RequisitionLine_taxOvr"))	{
			incomingRequest.put("RequisitionLine_taxOvr", "N");
		}
		if (!incomingRequest.containsKey("RequisitionLine_shipOvr"))	{
			incomingRequest.put("RequisitionLine_shipOvr", "N");
		}
		if (!incomingRequest.containsKey("RequisitionLine_discOvr"))	{
			incomingRequest.put("RequisitionLine_discOvr", "N");
		}
		if (!incomingRequest.containsKey("RequisitionLine_otherOvr"))	{
			incomingRequest.put("RequisitionLine_otherOvr", "N");
		}
		if (!incomingRequest.containsKey("RequisitionLine_icAccount")) {
			incomingRequest.put("RequisitionLine_icAccount", "0");
		}
		/*Contact Mark Danz if need to change.  IC_ACCOUNT should always be zero if no account is associated to this line!!!
		if (nonStandardItem.equalsIgnoreCase("N"))	{
			incomingRequest.put("RequisitionLine_icAccount", "0");
		} else {
			incomingRequest.put("RequisitionLine_icAccount", icReqLine);
		}*/
		// The following should be passed
		//		incomingRequest.put("RequisitionLine_lineNumber", "0");

		RequisitionHeader  requisitionHeader = (RequisitionHeader) incomingRequest.get("requisitionHeader") ;

		if (requisitionHeader != null) {
			// Defaults from header
			incomingRequest.put("RequisitionLine_icReqHeader", requisitionHeader.getIcReqHeader().toString());
			incomingRequest.put("RequisitionLine_requisitionNumber", requisitionHeader.getRequisitionNumber());
			if (!incomingRequest.containsKey("RequisitionLine_itemLocation"))	{
				incomingRequest.put("RequisitionLine_itemLocation", requisitionHeader.getItemLocation());
			}
			incomingRequest.put("RequisitionLine_reqType",requisitionHeader.getRequisitionType()) ;
			if (HiltonUtility.ckNull(requisitionHeader.getRequisitionType()).contains("M")) {
				incomingRequest.put("RequisitionLine_status", DocumentStatus.REQ_PLANNING);
			}
			incomingRequest.put("RequisitionLine_routing",requisitionHeader.getRouting()) ;
			if (!incomingRequest.containsKey("RequisitionLine_taxCode"))	{
				incomingRequest.put("RequisitionLine_taxCode", requisitionHeader.getTaxCode());
			}
			if (!incomingRequest.containsKey("RequisitionLine_taxPercent"))	{
				incomingRequest.put("RequisitionLine_taxPercent", requisitionHeader.getTaxPercent().toString());
			}
			if (!incomingRequest.containsKey("RequisitionLine_discountPercent"))	{
				incomingRequest.put("RequisitionLine_discountPercent", requisitionHeader.getDiscountPercent().toString());
			}
			if (!incomingRequest.containsKey("RequisitionLine_taxShipping"))	{
				incomingRequest.put("RequisitionLine_taxShipping", requisitionHeader.getTaxShipping());
			}
			if (!incomingRequest.containsKey("RequisitionLine_taxOther"))	{
				incomingRequest.put("RequisitionLine_taxOther", requisitionHeader.getTaxOther());
			}
			if (!incomingRequest.containsKey("RequisitionLine_requisitionerCode")) {
				incomingRequest.put("RequisitionLine_requisitionerCode", requisitionHeader.getRequisitionerCode());
			}
			if (!incomingRequest.containsKey("RequisitionLine_departmentCode")) {
				incomingRequest.put("RequisitionLine_departmentCode", requisitionHeader.getDepartmentCode());
			}
			if (!incomingRequest.containsKey("RequisitionLine_requiredDate")) {
				if (!(requisitionHeader.getRequiredDate() == null)) {
					incomingRequest.put("RequisitionLine_requiredDate", requisitionHeader.getRequiredDate().toString());
				}
			}
			if (!incomingRequest.containsKey("RequisitionLine_vendorId") && !requisitionHeader.getRequisitionType().equalsIgnoreCase("M")) {
				incomingRequest.put("RequisitionLine_vendorId", requisitionHeader.getVendorId());
			}
			if (!incomingRequest.containsKey("RequisitionLine_vendContactCode")) {
				incomingRequest.put("RequisitionLine_vendContactCode", requisitionHeader.getVendContactCode());
			}
			if (HiltonUtility.isEmpty((String) incomingRequest.get("RequisitionLine_commodityCode")))	{
				if(!HiltonUtility.isEmpty(requisitionHeader.getVendorId()) && DefaultUdfSupplier.equalsIgnoreCase("Y") && (VendorManager.getInstance().getVendor(organizationId, requisitionHeader.getVendorId()) instanceof Vendor))
				{
					String vendorUdf1 = HiltonUtility.ckNull(((Vendor)VendorManager.getInstance().getVendor(organizationId, requisitionHeader.getVendorId())).getVendUdf1());
					incomingRequest.put("RequisitionLine_commodityCode", vendorUdf1);
					incomingRequest.put("Commodity_commodity", vendorUdf1);
				}
			}
			if (nonStandardItem.equals("Y"))
			{
				incomingRequest.put("RequisitionLine_receiptRequired", requisitionHeader.getReceiptRequired());
			}
			else
			{
				if (propertiesManager.getProperty("REQ OPTIONS", "DEF REC OPTION FROM CAT ITEM", "N").equalsIgnoreCase("N"))
				{
					incomingRequest.put("RequisitionLine_receiptRequired", requisitionHeader.getReceiptRequired());
				}
			}
		}
		if (copyAccount.equals("Y"))
		{
			incomingRequest.put("RequisitionLine_icAccount", icReqLine);
		}

		return null ;
	}
}