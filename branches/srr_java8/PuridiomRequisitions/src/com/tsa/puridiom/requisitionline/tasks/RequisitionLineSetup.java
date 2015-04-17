package com.tsa.puridiom.requisitionline.tasks;

import java.util.Map;
import com.tsagate.foundation.utility.Dates ;
import com.tsagate.foundation.utility.Utility;

import com.tsa.puridiom.common.documents.*;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.UnitOfMeasure;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.unitofmeasure.tasks.UnitOfMeasureRetrieveById;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.UniqueKeyGenerator;

public class RequisitionLineSetup extends Task {

	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
		String	organizationId = (String) incomingRequest.get("organizationId");
		String	userId = (String) incomingRequest.get("userId") ;
        String  userTimeZone = (String) incomingRequest.get("userTimeZone") ;
        String userDateFormat = (String) incomingRequest.get("userDateFormat");
		PropertiesManager propertiesManager = PropertiesManager.getInstance(organizationId) ;

		String taxable = propertiesManager.getProperty("NON-STANDARD ITEM DEFAULTS","TAXABLE","Y");
		String commodityCode = propertiesManager.getProperty("NON-STANDARD ITEM DEFAULTS","COMMODITYCODE","");
		String uom = propertiesManager.getProperty("NON-STANDARD ITEM DEFAULTS","UMCODE","EA");
		String umFactor = "1";

		if (!Utility.isEmpty(uom)) {
			try {
				UnitOfMeasureRetrieveById uomRetrieve = new UnitOfMeasureRetrieveById();
				Map params = this.getDefaultUpdateParameters(incomingRequest);
				UnitOfMeasure unitOfMeasure = (UnitOfMeasure) uomRetrieve.executeTask(params);
				if (unitOfMeasure != null) {
					umFactor = String.valueOf(unitOfMeasure.getFactor());
				}
			} catch (Exception e1) {
				umFactor = "1";
			}
		}
        if (HiltonUtility.isEmpty(userDateFormat)) {
            userDateFormat = PropertiesManager.getInstance(organizationId).getProperty("MISC", "DATEFORMAT", "MM-dd-yyyy");
        }
        /* /*Contact Mark Danz if need to change.  IC_ACCOUNT should always be zero if no account is associated to this line!!!
		String flag = HiltonUtility.ckNull((String) incomingRequest.get("nonStandardItem"));*/

		this.setStatus(Status.COMPLETED);
		String today = Dates.today(userDateFormat, userTimeZone) ;

		UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
		String		icReqLine = ukg.getUniqueKey().toString() ;
		incomingRequest.put("RequisitionLine_icReqLine",	icReqLine);
		incomingRequest.put("RequisitionLine_icLineHistory",	ukg.getUniqueKey().toString());
		incomingRequest.put("RequisitionLine_itemNumber", "");
		incomingRequest.put("RequisitionLine_itemSource", "");
		incomingRequest.put("RequisitionLine_umCode", uom);
		incomingRequest.put("RequisitionLine_quantity", "1");
		incomingRequest.put("RequisitionLine_duomUmCode", "");
		incomingRequest.put("RequisitionLine_duomQuantity", "0");
		incomingRequest.put("RequisitionLine_unitPrice", "0");
		incomingRequest.put("RequisitionLine_commodityCode", commodityCode);
		incomingRequest.put("RequisitionLine_taxable", taxable);
		incomingRequest.put("RequisitionLine_asset", "N");
		incomingRequest.put("RequisitionLine_discountSource", "");
		incomingRequest.put("RequisitionLine_discountAmount", "0");
		incomingRequest.put("RequisitionLine_shippingCharges", "0");
		incomingRequest.put("RequisitionLine_taxShipping", "N");
		incomingRequest.put("RequisitionLine_otherCharges", "0");
		incomingRequest.put("RequisitionLine_otherDescription", "");
		incomingRequest.put("RequisitionLine_taxOther", "N");
		incomingRequest.put("RequisitionLine_icPoLine", "0");
		incomingRequest.put("RequisitionLine_icRevisedPoLine", "0");
		incomingRequest.put("RequisitionLine_splits", "N");
		incomingRequest.put("RequisitionLine_status", DocumentStatus.REQ_INPROGRESS);
		incomingRequest.put("RequisitionLine_commentFlag", "N");
		incomingRequest.put("RequisitionLine_taxAmount", "0");
		incomingRequest.put("RequisitionLine_shippingTaxAmt", "0");
		incomingRequest.put("RequisitionLine_otherTaxAmount", "0");
		incomingRequest.put("RequisitionLine_reqType", "P");
		incomingRequest.put("RequisitionLine_catalogId", "");
		incomingRequest.put("RequisitionLine_umFactor", umFactor);
		incomingRequest.put("RequisitionLine_lineTotal", "0");
		incomingRequest.put("RequisitionLine_taxOvr", "N");
		incomingRequest.put("RequisitionLine_discOvr", "N");
		incomingRequest.put("RequisitionLine_shipOvr", "N");
		incomingRequest.put("RequisitionLine_otherOvr", "N");
		incomingRequest.put("RequisitionLine_shiptoFlag", "N");
		incomingRequest.put("RequisitionLine_autoRelease", "0");
		incomingRequest.put("RequisitionLine_lastQtyEntered", "0");
		incomingRequest.put("RequisitionLine_assignedBuyer", "UNASSIGNED");
		incomingRequest.put("RequisitionLine_assignedDate", today);
		incomingRequest.put("RequisitionLine_allocated", "0");
		incomingRequest.put("RequisitionLine_backordered", "0");
		incomingRequest.put("RequisitionLine_mfgName", "");
		incomingRequest.put("RequisitionLine_modelNumber", "");
		incomingRequest.put("RequisitionLine_msrNumber", "");
		if(organizationId.equalsIgnoreCase("HOY08P"))
		{
			incomingRequest.put("RequisitionLine_udf1Code", "N");
		}
		else
		{
			incomingRequest.put("RequisitionLine_udf1Code", "");
		}
		incomingRequest.put("RequisitionLine_udf2Code", "");
		incomingRequest.put("RequisitionLine_udf3Code", "");
		incomingRequest.put("RequisitionLine_udf4Code", "");
		incomingRequest.put("RequisitionLine_udf5Code", "");
        incomingRequest.put("RequisitionLine_udf6Code", "");
        incomingRequest.put("RequisitionLine_udf7Code", "");
        incomingRequest.put("RequisitionLine_udf8Code", "");
        incomingRequest.put("RequisitionLine_udf9Code", "");
        incomingRequest.put("RequisitionLine_udf10Code", "");
        incomingRequest.put("RequisitionLine_memoLine", "");

		incomingRequest.put("RequisitionLine_rqLineKey", "");
		incomingRequest.put("RequisitionLine_receiptRequired", "");
		incomingRequest.put("RequisitionLine_roFlag", "N");
		incomingRequest.put("RequisitionLine_routing", "");
		incomingRequest.put("RequisitionLine_description", "");
		incomingRequest.put("RequisitionLine_taxCode", "");
		incomingRequest.put("RequisitionLine_vendorId", "");
		incomingRequest.put("RequisitionLine_requisitionerCode", "");
		incomingRequest.put("RequisitionLine_icAccount", "0");
		/*Contact Mark Danz if need to change.  IC_ACCOUNT should always be zero if no account is associated to this line!!!
		if(flag.equalsIgnoreCase("N"))
		{
			incomingRequest.put("RequisitionLine_icAccount", "0");
		}
		else
		{
			incomingRequest.put("RequisitionLine_icAccount", icReqLine);
		}*/
		incomingRequest.put("RequisitionLine_chemicalItemNumber", "");

		// The following should be passed
		//		incomingRequest.put("RequisitionLine_lineNumber", "0");

		RequisitionHeader  requisitionHeader = (RequisitionHeader) incomingRequest.get("requisitionHeader") ;

		if (requisitionHeader != null) {
			// Defaults from header
			incomingRequest.put("RequisitionLine_icReqHeader", requisitionHeader.getIcReqHeader().toString());
			incomingRequest.put("RequisitionLine_icRevisedOrder", requisitionHeader.getIcRevisedOrder().toString());
			incomingRequest.put("RequisitionLine_requisitionNumber", requisitionHeader.getRequisitionNumber());
			incomingRequest.put("RequisitionLine_itemLocation", requisitionHeader.getItemLocation());
			incomingRequest.put("RequisitionLine_reqType",requisitionHeader.getRequisitionType()) ;
			incomingRequest.put("RequisitionLine_routing",requisitionHeader.getRouting()) ;
			incomingRequest.put("RequisitionLine_taxCode", requisitionHeader.getTaxCode());
			incomingRequest.put("RequisitionLine_taxPercent", requisitionHeader.getTaxPercent().toString());
			incomingRequest.put("RequisitionLine_discountPercent", requisitionHeader.getDiscountPercent().toString());
			incomingRequest.put("RequisitionLine_taxShipping", requisitionHeader.getTaxShipping());
			incomingRequest.put("RequisitionLine_taxOther", requisitionHeader.getTaxOther());
			incomingRequest.put("RequisitionLine_requisitionerCode", requisitionHeader.getRequisitionerCode());
			incomingRequest.put("RequisitionLine_departmentCode", requisitionHeader.getDepartmentCode());
			incomingRequest.put("RequisitionLine_dateEntered",today);

			if (! (requisitionHeader.getRequiredDate() == null)) {
				incomingRequest.put("RequisitionLine_requiredDate", requisitionHeader.getRequiredDate().toString());
			}
			if(!requisitionHeader.getRequisitionType().equalsIgnoreCase("M"))
			{
				incomingRequest.put("RequisitionLine_vendorId", requisitionHeader.getVendorId());
				incomingRequest.put("RequisitionLine_vendContactCode", requisitionHeader.getVendContactCode());
			}

			if (HiltonUtility.ckNull(requisitionHeader.getRequisitionType()).equals("K"))
			{
				// Admin Check Request
				incomingRequest.put("RequisitionLine_assignedBuyer", "AP");
			} else if (HiltonUtility.ckNull(requisitionHeader.getRequisitionType()).equals("M"))
			{
				// MSR Request
				incomingRequest.put("RequisitionLine_status", DocumentStatus.REQ_PLANNING);
			}
		}

		return null ;
	}
}