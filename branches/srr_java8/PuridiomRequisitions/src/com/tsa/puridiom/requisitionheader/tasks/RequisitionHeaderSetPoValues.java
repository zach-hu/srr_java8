package com.tsa.puridiom.requisitionheader.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.Vendor;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.vendor.VendorManager;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility;
import java.math.BigDecimal;
import java.util.Map;

public class RequisitionHeaderSetPoValues extends Task {

	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;

		try {
			String organizationId = (String) incomingRequest.get("organizationId");
			PoHeader poHeader = (PoHeader) incomingRequest.get("poHeader");
		    RequisitionHeader requisitionHeader = (RequisitionHeader) incomingRequest.get("requisitionHeader");
		    PropertiesManager propertiesManager = PropertiesManager.getInstance(organizationId);
		    String userId = (String)incomingRequest.get("userId");

		    //	Check first for empty values to prevent overwriting requisition defaults with empty values from the po

		    if (!Utility.isEmpty(poHeader.getBuyerCode())) {
		        incomingRequest.put("RequisitionHeader_buyerCode", poHeader.getBuyerCode()) ;
		    }
		    if (!Utility.isEmpty(poHeader.getBillToCode())) {
		        incomingRequest.put("RequisitionHeader_billToCode", poHeader.getBillToCode()) ;
		    }
		    if (!Utility.isEmpty(poHeader.getBillToContact())) {
		        incomingRequest.put("RequisitionHeader_billToContact", poHeader.getBillToContact()) ;
		    }
		    if (!Utility.isEmpty(poHeader.getCurrencyCode())) {
		        incomingRequest.put("RequisitionHeader_currencyCode", poHeader.getCurrencyCode());
		    }
		    if (!Utility.isEmpty(poHeader.getDepartmentCode())) {
		        incomingRequest.put("RequisitionHeader_departmentCode", poHeader.getDepartmentCode()) ;
		    }
		    if (!Utility.isEmpty(poHeader.getRequestCat())) {
		    	incomingRequest.put("RequisitionHeader_requestCat", poHeader.getRequestCat());
		    }
		    if (!Utility.isEmpty(poHeader.getKit())) {
		    	incomingRequest.put("RequisitionHeader_kit", poHeader.getKit());
		    }
		    if (!Utility.isEmpty(poHeader.getBidWaiver())) {
		    	incomingRequest.put("RequisitionHeader_bidWaiver", poHeader.getBidWaiver());
		    }
		    if (!Utility.isEmpty(poHeader.getGfpGfm())) {
		    	incomingRequest.put("RequisitionHeader_gfpGfm", poHeader.getGfpGfm());
		    }
		    incomingRequest.put("RequisitionHeader_fiscalYear", poHeader.getFiscalYear());
			incomingRequest.put("RequisitionHeader_icHeaderHistory", String.valueOf(poHeader.getIcHeaderHistory()));
			incomingRequest.put("RequisitionHeader_icRevisedOrder", String.valueOf(poHeader.getIcPoHeader()));
			incomingRequest.put("RequisitionHeader_internalComments", poHeader.getInternalComments());
			incomingRequest.put("RequisitionHeader_icMsrHeader", poHeader.getIcMsrHeader().toString());
			if (!Utility.isEmpty(poHeader.getItemLocation())) {
			    incomingRequest.put("RequisitionHeader_itemLocation", poHeader.getItemLocation()) ;
			}
			if (!Utility.isEmpty(poHeader.getLanguage())) {
			    incomingRequest.put("RequisitionHeader_language", poHeader.getLanguage());
			}
			if (!Utility.isEmpty(poHeader.getPriorityCode())) {
			    incomingRequest.put("RequisitionHeader_priorityCode", poHeader.getPriorityCode()) ;
			}
			if (!Utility.isEmpty(poHeader.getReceiptRequired())) {
			    incomingRequest.put("RequisitionHeader_receiptRequired", poHeader.getReceiptRequired());
			}
			if (!Utility.isEmpty(poHeader.getRouting())) {
			    incomingRequest.put("RequisitionHeader_routing", poHeader.getRouting()) ;
			}
			if (!Utility.isEmpty(poHeader.getShipViaCode())) {
			    incomingRequest.put("RequisitionHeader_shippingCode", poHeader.getShipViaCode()) ;
			}
			if (!Utility.isEmpty(poHeader.getShipToCode())) {
			    incomingRequest.put("RequisitionHeader_shipToCode", poHeader.getShipToCode()) ;
			}
			if (!Utility.isEmpty(poHeader.getShipToContact())) {
			    incomingRequest.put("RequisitionHeader_shipToContact", poHeader.getShipToContact()) ;
			}
			if (!Utility.isEmpty(poHeader.getTaxCode())) {
			    incomingRequest.put("RequisitionHeader_taxCode", poHeader.getTaxCode()) ;
			}
			if (poHeader.getTaxPercent().compareTo(new BigDecimal(0)) > 0) {
			    incomingRequest.put("RequisitionHeader_taxPercent", String.valueOf(poHeader.getTaxPercent()));
			}
			incomingRequest.put("RequisitionHeader_taxShipping", String.valueOf(poHeader.getShippingTaxable()));

			if (!Utility.isEmpty(poHeader.getVendContactCode())) {
			    incomingRequest.put("RequisitionHeader_vendContactCode", poHeader.getVendContactCode());
			}
			if (!Utility.isEmpty(poHeader.getCorrosionEval())) {
			    incomingRequest.put("RequisitionHeader_corrosionEval", poHeader.getCorrosionEval());
			}
			if (!Utility.isEmpty(poHeader.getEstimatedCost().toString())) {
			    incomingRequest.put("RequisitionHeader_estimatedCost", poHeader.getEstimatedCost().toString());
			}
			if(!(requisitionHeader.getRequisitionType().equalsIgnoreCase("M")))
			{
				if (!Utility.isEmpty(poHeader.getVendorId())) {
					requisitionHeader.setVendorId(poHeader.getVendorId());
				    incomingRequest.put("RequisitionHeader_vendorId", poHeader.getVendorId());
				}
				if (!Utility.isEmpty(poHeader.getVendorName())) {
				    incomingRequest.put("RequisitionHeader_vendorName", poHeader.getVendorName());
				}
			}
			if (!Utility.isEmpty(poHeader.getContactName())) {
			    incomingRequest.put("RequisitionHeader_vendorAttn", poHeader.getContactName());
			}
			if (!Utility.isEmpty(poHeader.getContactPhone())) {
			    incomingRequest.put("RequisitionHeader_contactPhone", poHeader.getContactPhone());
			}
			if (!Utility.isEmpty(poHeader.getContactMobilePhone())) {
			    incomingRequest.put("RequisitionHeader_contactMobilePhone", poHeader.getContactMobilePhone());
			}
			if (!Utility.isEmpty(poHeader.getMsrNumber()))
			{
				incomingRequest.put("RequisitionHeader_msrNumber", poHeader.getMsrNumber());
			}
			if (requisitionHeader.getRequisitionType().equalsIgnoreCase("E"))
			{
				if (propertiesManager.getProperty("PO OPTIONS", "RELEASEASSIGNEDBUYER", "N").equalsIgnoreCase("Y"))
				{
					incomingRequest.put("RequisitionHeader_assignedBuyer", userId);
				}
			}

			if ( !requisitionHeader.getRequisitionType().equalsIgnoreCase("V") )	// if revision request, do not copy totals!
			{
				incomingRequest.put("RequisitionHeader_discountAmount", String.valueOf(poHeader.getDiscountAmount()));
			    incomingRequest.put("RequisitionHeader_discountPercent", String.valueOf(poHeader.getDiscountPercent()));
				incomingRequest.put("RequisitionHeader_taxAmount", String.valueOf(poHeader.getTaxAmount()));
				incomingRequest.put("RequisitionHeader_otherChargDesc", poHeader.getOtherDescription());
				incomingRequest.put("RequisitionHeader_otherCharges", String.valueOf(poHeader.getOtherCharges()));
				incomingRequest.put("RequisitionHeader_otherTaxAmount", String.valueOf(poHeader.getOtherTax()));
				incomingRequest.put("RequisitionHeader_subtotal", String.valueOf(poHeader.getSubtotal()));
				incomingRequest.put("RequisitionHeader_total", String.valueOf(poHeader.getTotal()));
			}
			if (requisitionHeader.getRequisitionType().equalsIgnoreCase("C")) {
			    incomingRequest.put("RequisitionHeader_internalComments", poHeader.getInternalComments());
			    incomingRequest.put("RequisitionHeader_pcardExpires", poHeader.getPcardExpires());
			    incomingRequest.put("RequisitionHeader_pcardHolder", poHeader.getPcardHolder());
			    incomingRequest.put("RequisitionHeader_pcardName", poHeader.getPcardName());
			    incomingRequest.put("RequisitionHeader_pcardNumber", poHeader.getPcardNumber());
			    incomingRequest.put("RequisitionHeader_pcardOrder", poHeader.getPcardOrder());
			    incomingRequest.put("RequisitionHeader_buyer", poHeader.getBuyerCode());

			    if (propertiesManager.getProperty("PO OPTIONS", "CHANGEASSIGNBUYER", "N").equalsIgnoreCase("Y"))
			    {
			    	incomingRequest.put("RequisitionHeader_assignedBuyer", poHeader.getBuyerCode());
			    }


			    incomingRequest.put("RequisitionHeader_fobCode", poHeader.getFobCode());
			    String vendorId = (String) poHeader.getVendorId();
	        	if (!HiltonUtility.isEmpty(vendorId) && organizationId.equalsIgnoreCase("BLY07P")) {
	        		Object vendor = VendorManager.getInstance().getVendor(organizationId, vendorId);
	        		if( vendor != null && vendor instanceof Vendor)
	        		{
	        			incomingRequest.put("RequisitionHeader_fobCode", (String) ( ((Vendor)vendor).getFobId()));
	        		}
	        	}
	        	if (organizationId.equalsIgnoreCase("BLY07P")){
	        		incomingRequest.put("RequisitionHeader_udf7Code", poHeader.getUdf7Code());
	        	}
			}

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}

		return null ;
	}

}