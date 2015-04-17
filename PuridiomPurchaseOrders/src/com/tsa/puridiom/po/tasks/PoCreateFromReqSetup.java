/**
 *
 * Created on Jan 22, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.po.tasks.PoCreateFromReqSetup.java
 *
 */
package com.tsa.puridiom.po.tasks;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.Vendor;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.vendor.VendorManager;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;

public class PoCreateFromReqSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{

		try
		{

		Map incomingRequest = (Map)object;
		String organizationId = (String)incomingRequest.get("organizationId");
        String userDateFormat = (String) incomingRequest.get("userDateFormat");

        if (HiltonUtility.isEmpty(userDateFormat)) {
            userDateFormat = PropertiesManager.getInstance(organizationId).getProperty("MISC", "DATEFORMAT", "MM-dd-yyyy");
        }
        String autoAwardedRequisition = HiltonUtility.ckNull((String) incomingRequest.get("autoAwardedRequisition"));
        String noApprovalNeed = HiltonUtility.ckNull((String) incomingRequest.get("NoApprovalNeed"));
        String userTimeZone = (String) incomingRequest.get("userTimeZone");
        PropertiesManager propertiesManager = PropertiesManager.getInstance(organizationId) ;
		String begin = propertiesManager.getProperty("Misc", "fybegin", "1");
		String assignFiscalYear = propertiesManager.getProperty("PO DEFAULTS", "POFYFROMREQ", "N");
		String statusAwardChangeReq = propertiesManager.getProperty("REQ DEFAULTS", "AWARDCHANGEREQ", "N");
		String newStatus = (String) incomingRequest.get("newStatus");
		String getVendorValue = propertiesManager.getProperty("PO DEFAULTS", "POGETVENDORVALUE", "N");
		boolean fdcsEnabled = propertiesManager.getProperty("FDCS", "Enabled", "N").equalsIgnoreCase("Y");
		boolean assignPoNumberInReqAward = propertiesManager.getProperty("REQ OPTIONS", "PO-NUMBER IN REQUISITION AWARD", "N").equalsIgnoreCase("Y");
		Boolean autoAwarded = (Boolean)incomingRequest.get("autoAwarded");
        if(autoAwarded == null){		autoAwarded = Boolean.FALSE;		}

		Integer iBegin = new Integer(begin);

        PoHeader previousPo = (PoHeader) incomingRequest.get("previousPo");

        if (previousPo == null)
        	previousPo = new PoHeader();

        String poNumberContract = HiltonUtility.ckNull((String) incomingRequest.get("poNumberContract"));

		RequisitionHeader reqHeader = (RequisitionHeader)incomingRequest.get("requisitionHeader");
		incomingRequest.put("PoHeader_icReqHeader", reqHeader.getIcReqHeader().toString());
		incomingRequest.put("PoHeader_icHeaderHistory", reqHeader.getIcHeaderHistory().toString());
		if(statusAwardChangeReq.equalsIgnoreCase("Y"))
		{
			if (reqHeader.getRequisitionType().equalsIgnoreCase("C") && newStatus.equalsIgnoreCase("1035"))
			{
				incomingRequest.put("PoHeader_status", DocumentStatus.PO_AWARDED);
			}
        }

		if ("C".equalsIgnoreCase(reqHeader.getRequisitionType()))
		{
			incomingRequest.put("PoHeader_poDate", HiltonUtility.getFormattedDate(previousPo.getPoDate(), organizationId, userDateFormat));
			incomingRequest.put("PoHeader_subType", previousPo.getSubType());
		}

		incomingRequest.put("PoHeader_authorizationCode", reqHeader.getAuthorizationCode());
		incomingRequest.put("PoHeader_billToContact", reqHeader.getBillToContact());
		incomingRequest.put("PoHeader_billToCode", reqHeader.getBillToCode());
		incomingRequest.put("PoHeader_currencyCode", reqHeader.getCurrencyCode());
		incomingRequest.put("PoHeader_departmentCode", reqHeader.getDepartmentCode());
		incomingRequest.put("PoHeader_discountPercent", reqHeader.getDiscountPercent().toString());
		incomingRequest.put("PoHeader_discountAmount", reqHeader.getDiscountAmount().toString());
		incomingRequest.put("PoHeader_discountCode", reqHeader.getDiscountSource());
		if(assignFiscalYear.equalsIgnoreCase("Y")){
			incomingRequest.put("PoHeader_fiscalYear", Dates.getFiscalYear(iBegin.intValue(), userTimeZone));
		}
		else{
			incomingRequest.put("PoHeader_fiscalYear", reqHeader.getFiscalYear());
		}
		incomingRequest.put("PoHeader_itemLocation", reqHeader.getItemLocation());
		incomingRequest.put("PoHeader_otherCharges", reqHeader.getOtherCharges().toString());
		incomingRequest.put("PoHeader_otherDescription", reqHeader.getOtherChargDesc());
		incomingRequest.put("PoHeader_otherTax", reqHeader.getOtherTaxAmount().toString());
		incomingRequest.put("PoHeader_otherTaxable", reqHeader.getTaxOther());
		incomingRequest.put("PoHeader_pcardOrder", reqHeader.getPcardReq());
		incomingRequest.put("PoHeader_pcardName", reqHeader.getPcardName());
		incomingRequest.put("PoHeader_pcardHolder", reqHeader.getPcardHolder());
		incomingRequest.put("PoHeader_pcardNumber", reqHeader.getPcardNumber());
		incomingRequest.put("PoHeader_pcardExpires", reqHeader.getPcardExpires());
		incomingRequest.put("PoHeader_priorityCode", reqHeader.getPriorityCode());
		incomingRequest.put("PoHeader_receiptRequired", reqHeader.getReceiptRequired());
		incomingRequest.put("PoHeader_requisitionerCode", reqHeader.getRequisitionerCode());
		incomingRequest.put("PoHeader_requisitionNumber", reqHeader.getRequisitionNumber());
		incomingRequest.put("PoHeader_estimatedCost", reqHeader.getEstimatedCost().toString());
		incomingRequest.put("PoHeader_contractNo", poNumberContract);
		if ( reqHeader.getRequiredDate()!= null)
		{
			incomingRequest.put("PoHeader_requiredDate", HiltonUtility.getFormattedDate(reqHeader.getRequiredDate(), organizationId, userDateFormat));
		}
		incomingRequest.put("PoHeader_routing", reqHeader.getRouting()) ;
		incomingRequest.put("PoHeader_shippingCharges", reqHeader.getShippingCharges().toString());
		incomingRequest.put("PoHeader_shippingTax", reqHeader.getShippingTaxAmt().toString());
		incomingRequest.put("PoHeader_shippingTaxable", reqHeader.getTaxShipping());
		incomingRequest.put("PoHeader_shipToCode", reqHeader.getShipToCode());
		incomingRequest.put("PoHeader_shipToContact", reqHeader.getShipAttn());
		if (!autoAwarded) {
			incomingRequest.put("PoHeader_shipViaCode", reqHeader.getShippingCode());
		}
		incomingRequest.put("PoHeader_subtotal", reqHeader.getSubtotal().toString());
		incomingRequest.put("PoHeader_taxAmount", reqHeader.getTaxAmount().toString());
		incomingRequest.put("PoHeader_taxCode", reqHeader.getTaxCode());
		incomingRequest.put("PoHeader_taxPercent", reqHeader.getTaxPercent().toString());
		incomingRequest.put("PoHeader_total", reqHeader.getTotal().toString());
		incomingRequest.put("PoHeader_internalComments", reqHeader.getInternalComments());
		incomingRequest.put("PoHeader_udf1Code", reqHeader.getUdf1Code());
		incomingRequest.put("PoHeader_gfpGfm", reqHeader.getGfpGfm());
		/* Commented out because the Requisition created from MSR should be populated when being sourced
		 * if(reqHeader.getIcMsrHeader() != null && reqHeader.getIcMsrHeader().compareTo(new BigDecimal(0)) != 0){
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
			PuridiomProcess process = processLoader.loadProcess("requisitionheader-retrieve-by-id.xml");

			Map newIncomingRequest = new HashMap();
			newIncomingRequest.put("RequisitionHeader_icReqHeader", reqHeader.getIcMsrHeader().toString());

			process.executeProcess(newIncomingRequest);

			RequisitionHeader msr = (RequisitionHeader) newIncomingRequest.get("requisitionHeader");

			incomingRequest.put("PoHeader_kit", msr.getKit());
		} else {
			incomingRequest.put("PoHeader_kit", reqHeader.getKit());
		}*/
		incomingRequest.put("PoHeader_kit", reqHeader.getKit());
		incomingRequest.put("PoHeader_workOrder", reqHeader.getWorkOrder());
		incomingRequest.put("PoHeader_requestCat", reqHeader.getRequestCat());
		incomingRequest.put("PoHeader_bidWaiver", reqHeader.getBidWaiver());
		incomingRequest.put("PoHeader_originalReqType", reqHeader.getOriginalReqType());

		// If there is a previous po, these fields are populated from the old po
		if (!HiltonUtility.isEmpty(previousPo.getRfqNumber())) {
            incomingRequest.put("PoHeader_rfqNumber", previousPo.getRfqNumber());
        }
		if (previousPo.getPromisedDate() != null) {
            incomingRequest.put("PoHeader_promisedDate", HiltonUtility.getFormattedDate(previousPo.getPromisedDate(), organizationId, userDateFormat));
        }
		if (!HiltonUtility.isEmpty(previousPo.getUdf13Code())) {
            incomingRequest.put("PoHeader_udf13Code", previousPo.getUdf13Code());
        }
		if (!HiltonUtility.isEmpty(previousPo.getNaics())) {
            incomingRequest.put("PoHeader_naics", previousPo.getNaics());
        }
		if (!HiltonUtility.isEmpty(previousPo.getAuthorizationCode())) {
            incomingRequest.put("PoHeader_authorizationCode", previousPo.getAuthorizationCode());
        }
		if (!HiltonUtility.isEmpty(previousPo.getUdf12Code())) {
            incomingRequest.put("PoHeader_udf12Code", previousPo.getUdf12Code());
        }
		if (!HiltonUtility.isEmpty(previousPo.getSubType())) {
            incomingRequest.put("PoHeader_subType", previousPo.getSubType());
        }
		if (!Utility.isEmpty(previousPo.getVendorClass())) {
			incomingRequest.put("PoHeader_vendorClass", previousPo.getVendorClass());
		}

		if (organizationId.equalsIgnoreCase("bly07p"))
		{
			incomingRequest.put("PoHeader_udf7Code", reqHeader.getUdf7Code());
			incomingRequest.put("PoHeader_udf8Code", reqHeader.getUdf8Code());
		}

		if (organizationId.equalsIgnoreCase("MSG07P"))
		{
			incomingRequest.put("PoHeader_udf2Code", reqHeader.getBidWaiver());
		}

		String vendor = HiltonUtility.ckNull((String)incomingRequest.get("PoHeader_vendorId"));
		//TODO CHECK HOW TO GET SUPPLIER
		if(HiltonUtility.isEmpty(vendor)){
			incomingRequest.put("PoHeader_vendorId", reqHeader.getVendorId());
			incomingRequest.put("PoHeader_vendorName", reqHeader.getVendorName());
			incomingRequest.put("PoHeader_vendContactCode", reqHeader.getVendContactCode());
			incomingRequest.put("PoHeader_contactName", reqHeader.getVendorAttn());
			incomingRequest.put("PoHeader_contactAddr", reqHeader.getContactAddr());
			incomingRequest.put("PoHeader_contactAddr", reqHeader.getContactAddr());
		}
		if(organizationId.equalsIgnoreCase("DTN07P"))
		{
			incomingRequest.put("PoHeader_contactMobilePhone", reqHeader.getContactMobilePhone());
		}

		String type = (String)incomingRequest.get("PoHeader_poType");
		if(organizationId.equalsIgnoreCase("DTN07P") && type.equals("BO"))
		{
			incomingRequest.put("PoHeader_blanketLimit", reqHeader.getTotal().toString());
		}

		if(noApprovalNeed.equalsIgnoreCase("Y"))
        {
			incomingRequest.put("PoHeader_buyerCode", reqHeader.getRequisitionerCode());
			incomingRequest.put("PoHeader_owner", reqHeader.getOwner());
        }

		incomingRequest.put("Schedule_icHeader", reqHeader.getIcReqHeader().toString());
		incomingRequest.put("Vendor_vendorId", reqHeader.getVendorId());
		incomingRequest.put("CurrCode_currencyCode", reqHeader.getCurrencyCode());


        if(getVendorValue.equalsIgnoreCase("Y") && !autoAwarded.booleanValue() && !HiltonUtility.isEmpty(reqHeader.getVendorId()))
		{
			String vendorId = reqHeader.getVendorId();
			Vendor v = (Vendor) VendorManager.getInstance().getVendor(organizationId, vendorId);
			incomingRequest.put("PoHeader_fobCode", HiltonUtility.ckNull(v.getFobId()));
			incomingRequest.put("PoHeader_termsCode", HiltonUtility.ckNull(v.getVendTerms()));
			String vendorPrintFaxCode = HiltonUtility.ckNull(((Vendor)VendorManager.getInstance().getVendor(organizationId, vendorId)).getPrintFaxCode());
			incomingRequest.put("PoHeader_ediOrder", vendorPrintFaxCode);
		}else{
			incomingRequest.put("PoHeader_fobCode", reqHeader.getFobCode());
		}

        if (assignPoNumberInReqAward) {
			if (!HiltonUtility.isEmpty(reqHeader.getUdf1Code())
					&& reqHeader.getRequisitionType().equals("P")
					&& reqHeader.getUdf1Code().indexOf("RESALE") > -1
					&& !HiltonUtility.isEmpty(reqHeader.getUdf15Code())) {
				incomingRequest.put("PoHeader_poNumber", reqHeader.getUdf15Code()) ;
			}
		}

        if ( autoAwarded.booleanValue())
		{
        	String shipToCode = (String) incomingRequest.get("AutoAwardByLine_shipToCode");

			if (organizationId.equalsIgnoreCase("HOY08P"))
			{
				incomingRequest.put("PoHeader_buyerCode", reqHeader.getOwner());
			}
			else if (organizationId.equalsIgnoreCase("BLY07P"))
			{
				String assignedBuyer = HiltonUtility.ckNull((String)incomingRequest.get("AutoAwardByLine_assignedBuyer"));

				if(HiltonUtility.isEmpty(assignedBuyer))
				{
					assignedBuyer = reqHeader.getBuyer();
				}
				if(HiltonUtility.isEmpty(assignedBuyer))
				{
					assignedBuyer = reqHeader.getRequisitionerCode();
				}
				incomingRequest.put("PoHeader_buyerCode", assignedBuyer );
			}
			else if (organizationId.equalsIgnoreCase("WPC08P"))
			{
				incomingRequest.put("PoHeader_buyerCode", reqHeader.getRequisitionerCode());
			}
			else
			{
				if (!HiltonUtility.isEmpty(reqHeader.getBuyer()))
				{
					incomingRequest.put("PoHeader_buyerCode", reqHeader.getBuyer());
				}
				else
				{
					incomingRequest.put("PoHeader_buyerCode", reqHeader.getRequisitionerCode());
				}
			}

			if ( autoAwardedRequisition.equalsIgnoreCase("Y"))
			{
				if (organizationId.equalsIgnoreCase("WPC08P"))
				{
					 incomingRequest.put("PoHeader_owner", reqHeader.getRequisitionerCode());
				}
				else
				{
					 incomingRequest.put("PoHeader_owner", reqHeader.getBuyer() );
				}

				incomingRequest.put("PoHeader_lastRelease", "0");
				incomingRequest.put("PoHeader_appBy", reqHeader.getBuyer());
				incomingRequest.put("PoHeader_lastChgBy", "AUTORELEASE");
				incomingRequest.put("PoHeader_lastChgDate", Dates.today(userDateFormat));
				if (HiltonUtility.isEmpty(reqHeader.getContactAddr()))
				{
					incomingRequest.put("PoHeader_contactAddr", "DEFAULT");
				}
				else
				{
					incomingRequest.put("PoHeader_contactAddr", reqHeader.getContactAddr());
				}

			}

			if (fdcsEnabled) {
				if (! HiltonUtility.isEmpty(reqHeader.getUdf15Code())) {
					// Assign the Po Number from fdcs
					incomingRequest.put("poAssignedByFdcs", "Y") ;
					incomingRequest.put("PoHeader_poNumber", reqHeader.getUdf15Code()) ;
				}
			}

			String vendorId = (String) incomingRequest.get("AutoAwardByLine_vendorId");
			if (HiltonUtility.isEmpty(vendorId)) {
				vendorId = reqHeader.getVendorId();
			}
			Vendor v = new Vendor();
			if (!HiltonUtility.isEmpty(vendorId)) {
				v = (Vendor) VendorManager.getInstance().getVendor(organizationId, vendorId);
			}
			String vendorName = (String) incomingRequest.get("AutoAwardByLine_vendorName");
			if (HiltonUtility.isEmpty(vendorName)) {
				vendorName = reqHeader.getVendorId();
			}
			String vendContactCode = (String) incomingRequest.get("AutoAwardByLine_vendContactCode");
			if (HiltonUtility.isEmpty(vendContactCode)) {
				vendContactCode = reqHeader.getVendContactCode();
			}
			String contactName = (String) incomingRequest.get("AutoAwardByLine_contactName");
			if (HiltonUtility.isEmpty(contactName)) {
				contactName = reqHeader.getVendorAttn();
			}
			String contactPhone = (String) incomingRequest.get("AutoAwardByLine_contactPhone");
			if (HiltonUtility.isEmpty(contactPhone)) {
				contactPhone = reqHeader.getContactPhone();
			}
			String contractNo = HiltonUtility.ckNull((String) incomingRequest.get("AutoAwardByLine_contractNo"));

			String fobCode = (String)incomingRequest.get("AutoAwardByLine_fobCode");
			if (HiltonUtility.isEmpty(fobCode)) {
				fobCode = reqHeader.getFobCode();
			}
			String shipViaCode = (String)incomingRequest.get("AutoAwardByLine_shipViaCode");
			if (HiltonUtility.isEmpty(shipViaCode)) {
				shipViaCode = reqHeader.getShippingCode();
			}
			String vendorClass = HiltonUtility.ckNull((String)incomingRequest.get("AutoAwardByLine_vendorClass"));

			String udf11Code = HiltonUtility.ckNull((String)incomingRequest.get("AutoAwardByLine_udf11Code"));
			String udf12Code = HiltonUtility.ckNull((String)incomingRequest.get("AutoAwardByLine_udf12Code"));
			String udf13Code = HiltonUtility.ckNull((String)incomingRequest.get("AutoAwardByLine_udf13Code"));
			String poSubType = HiltonUtility.ckNull((String)incomingRequest.get("AutoAwardByLine_poSubType"));



			String termsCode = (String) incomingRequest.get("AutoAwardByLine_vendTermsCode");
			if (HiltonUtility.isEmpty(termsCode) && (v != null)) {
				termsCode = v.getVendTerms();
			}
			if(getVendorValue.equalsIgnoreCase("Y") && (v != null))
			{
				incomingRequest.put("PoHeader_fobCode", HiltonUtility.ckNull(v.getFobId()));
				termsCode = v.getVendTerms();
			}

			incomingRequest.put("PoHeader_vendorId", vendorId);
			incomingRequest.put("PoHeader_vendorName", vendorName);
			incomingRequest.put("PoHeader_vendContactCode", vendContactCode);
			incomingRequest.put("PoHeader_contactName", contactName);
			incomingRequest.put("PoHeader_contactPhone", contactPhone);
			incomingRequest.put("PoHeader_termsCode", termsCode);
			incomingRequest.put("PoHeader_contractNo", contractNo);
			incomingRequest.put("PoHeader_shipViaCode", shipViaCode);
			incomingRequest.put("PoHeader_vendorClass", vendorClass);
			incomingRequest.put("PoHeader_udf11Code", udf11Code);
			incomingRequest.put("PoHeader_udf12Code", udf12Code);
			incomingRequest.put("PoHeader_udf13Code", udf13Code);
			incomingRequest.put("PoHeader_subType", poSubType);

			if (incomingRequest.containsKey("AutoAwardByLine_revisionValue")) {
				incomingRequest.put("PoHeader_revisionValue", incomingRequest.get("AutoAwardByLine_revisionValue"));
			}

//  Commented out b/c udf1Code should not be set to AutoAwardByLine_udf5Code for all customers
//  If this is needed, it should be based on a property setting before including again -KK 07/06/10
//			incomingRequest.put("PoHeader_udf1Code", incomingRequest.get("AutoAwardByLine_udf5Code"));

			if (!HiltonUtility.isEmpty(shipToCode))
			{
				incomingRequest.put("PoHeader_shipToCode", shipToCode);
			}

			if (incomingRequest.containsKey("AutoAwardByLine_vendorPrintFaxCode"))
			{
				incomingRequest.put("PoHeader_ediOrder", incomingRequest.get("AutoAwardByLine_vendorPrintFaxCode"));
			}
			else
			{
				incomingRequest.put("PoHeader_ediOrder", "P");
			}
		}

        if (autoAwardedRequisition.equalsIgnoreCase("Y"))
        {
	        if (!HiltonUtility.isEmpty(reqHeader.getBuyer()))
	        {
	        	incomingRequest.put("PoHeader_buyerCode", reqHeader.getBuyer());
	        }
	        if (!HiltonUtility.isEmpty(reqHeader.getRequisitionerCode()) &&
	        	PropertiesManager.getInstance(organizationId).getProperty("ASSIGNMENT", "ASSIGNMENTOPTIONS", "DEFAULT").equalsIgnoreCase("REQUISITIONERISBUYER"))
	        {
	        	incomingRequest.put("PoHeader_buyerCode", reqHeader.getRequisitionerCode());
	        }
	        if (!HiltonUtility.isEmpty(reqHeader.getAssignedBuyer()) &&
	            PropertiesManager.getInstance(organizationId).getProperty("ASSIGNMENT", "ASSIGNMENTOPTIONS", "DEFAULT").equalsIgnoreCase("ASSIGNEDISBUYER"))
	        {
	           	incomingRequest.put("PoHeader_buyerCode", reqHeader.getAssignedBuyer());
	        }
        }

        incomingRequest.put("PoHeader_icMsrHeader", reqHeader.getIcMsrHeader().toString());
        incomingRequest.put("PoHeader_msrNumber", reqHeader.getMsrNumber());
        incomingRequest.put("PoHeader_corrosionEval", reqHeader.getCorrosionEval());

        /*if (reqHeader.getRequiredDate() != null){
        	String requiredDate = reqHeader.getRequiredDate().toString();
        	incomingRequest.put("PoHeader_requiredDate", requiredDate);
        }*/

		this.setStatus(Status.SUCCEEDED);

		}
		catch (Exception e)
		{
        	Log.error(this, e);
            this.setStatus(Status.FAILED);
            throw e;
		}
		return null;
	}
}