package com.tsa.puridiom.requisitionline.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.vendor.VendorManager;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class RequisitionLineSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		String	organizationId = (String) incomingRequest.get("organizationId") ;
        String userDateFormat = (String) incomingRequest.get("userDateFormat");

        if (Utility.isEmpty(userDateFormat)) {
            userDateFormat = PropertiesManager.getInstance(organizationId).getProperty("MISC", "DATEFORMAT", "MM-dd-yyyy");
        }

		try
		{
			RequisitionLine requisitionLine = (RequisitionLine) incomingRequest.get("requisitionLine");
			if (requisitionLine == null)
			{
				requisitionLine = new RequisitionLine();
			}

			if (incomingRequest.containsKey("RequisitionLine_icReqLine"))
			{
				String icReqLineString = (String) incomingRequest.get("RequisitionLine_icReqLine");
				if (Utility.isEmpty(icReqLineString))
				{
					icReqLineString = "0";
				}
				BigDecimal icReqLine = new BigDecimal ( icReqLineString );
				requisitionLine.setIcReqLine(icReqLine);
			}
			if (incomingRequest.containsKey("RequisitionLine_lineNumber"))
			{
				String lineNumberString = (String) incomingRequest.get("RequisitionLine_lineNumber");
				if (Utility.isEmpty(lineNumberString))
				{
					lineNumberString = "0";
				}
				BigDecimal lineNumber = new BigDecimal ( lineNumberString );
				requisitionLine.setLineNumber(lineNumber);
			}
			if (incomingRequest.containsKey("RequisitionLine_icReqHeader"))
			{
				String icReqHeaderString = (String) incomingRequest.get("RequisitionLine_icReqHeader");
				if (Utility.isEmpty(icReqHeaderString))
				{
					icReqHeaderString = "0";
				}
				BigDecimal icReqHeader = new BigDecimal ( icReqHeaderString );
				requisitionLine.setIcReqHeader(icReqHeader);
			}
			if (incomingRequest.containsKey("RequisitionLine_requisitionNumber"))
			{
				String requisitionNumber = (String ) incomingRequest.get("RequisitionLine_requisitionNumber");
				requisitionLine.setRequisitionNumber(requisitionNumber);
			}
			if (incomingRequest.containsKey("RequisitionLine_itemNumber"))
			{
				String itemNumber = (String ) incomingRequest.get("RequisitionLine_itemNumber");
				requisitionLine.setItemNumber(itemNumber);
			}
			if (incomingRequest.containsKey("RequisitionLine_itemSource"))
			{
				String itemSource = (String ) incomingRequest.get("RequisitionLine_itemSource");
				requisitionLine.setItemSource(itemSource);
			}
			if (incomingRequest.containsKey("RequisitionLine_umCode"))
			{
				String umCode = (String ) incomingRequest.get("RequisitionLine_umCode");
				requisitionLine.setUmCode(umCode);
			}
			if (incomingRequest.containsKey("RequisitionLine_quantity"))
			{
				String quantityString = (String) incomingRequest.get("RequisitionLine_quantity");
				if (Utility.isEmpty(quantityString))
				{
					quantityString = "0";
				}
				BigDecimal quantity = new BigDecimal ( quantityString );
				requisitionLine.setQuantity(quantity);
			}
			if (incomingRequest.containsKey("RequisitionLine_unitPrice"))
			{
				String unitPriceString = (String) incomingRequest.get("RequisitionLine_unitPrice");
				if (Utility.isEmpty(unitPriceString))
				{
					unitPriceString = "0";
				}
				BigDecimal unitPrice = new BigDecimal ( unitPriceString );
				requisitionLine.setUnitPrice(unitPrice);
			}
			if (incomingRequest.containsKey("RequisitionLine_commodityCode"))
			{
				String commodityCode = (String ) incomingRequest.get("RequisitionLine_commodityCode");
				requisitionLine.setCommodityCode(commodityCode);
			}
			if (incomingRequest.containsKey("RequisitionLine_taxable"))
			{
				String taxable = (String ) incomingRequest.get("RequisitionLine_taxable");
				requisitionLine.setTaxable(taxable);
			}
			if (incomingRequest.containsKey("RequisitionLine_asset"))
			{
				String asset = (String ) incomingRequest.get("RequisitionLine_asset");
				requisitionLine.setAsset(asset);
			}
			if (incomingRequest.containsKey("RequisitionLine_discountSource"))
			{
				String discountSource = (String ) incomingRequest.get("RequisitionLine_discountSource");
				requisitionLine.setDiscountSource(discountSource);
			}
			if (incomingRequest.containsKey("RequisitionLine_discountPercent"))
			{
				String discountPercentString = (String) incomingRequest.get("RequisitionLine_discountPercent");
				if (Utility.isEmpty(discountPercentString))
				{
					discountPercentString = "0";
				}
				BigDecimal discountPercent = new BigDecimal ( discountPercentString );
				requisitionLine.setDiscountPercent(discountPercent);
			}
			if (incomingRequest.containsKey("RequisitionLine_discountAmount"))
			{
				String discountAmountString = (String) incomingRequest.get("RequisitionLine_discountAmount");
				if (Utility.isEmpty(discountAmountString))
				{
					discountAmountString = "0";
				}
				BigDecimal discountAmount = new BigDecimal ( discountAmountString );
				requisitionLine.setDiscountAmount(discountAmount);
			}
			if (incomingRequest.containsKey("RequisitionLine_shippingCharges"))
			{
				String shippingChargesString = (String) incomingRequest.get("RequisitionLine_shippingCharges");
				if (Utility.isEmpty(shippingChargesString))
				{
					shippingChargesString = "0";
				}
				BigDecimal shippingCharges = new BigDecimal ( shippingChargesString );
				requisitionLine.setShippingCharges(shippingCharges);
			}
			if (incomingRequest.containsKey("RequisitionLine_taxShipping"))
			{
				String taxShipping = (String ) incomingRequest.get("RequisitionLine_taxShipping");
				requisitionLine.setTaxShipping(taxShipping);
			}
			if (incomingRequest.containsKey("RequisitionLine_otherCharges"))
			{
				String otherChargesString = (String) incomingRequest.get("RequisitionLine_otherCharges");
				if (Utility.isEmpty(otherChargesString))
				{
					otherChargesString = "0";
				}
				BigDecimal otherCharges = new BigDecimal ( otherChargesString );
				requisitionLine.setOtherCharges(otherCharges);
			}
			if (incomingRequest.containsKey("RequisitionLine_otherDescription"))
			{
				String otherDescription = (String ) incomingRequest.get("RequisitionLine_otherDescription");
				requisitionLine.setOtherDescription(otherDescription);
			}
			if (incomingRequest.containsKey("RequisitionLine_taxOther"))
			{
				String taxOther = (String ) incomingRequest.get("RequisitionLine_taxOther");
				requisitionLine.setTaxOther(taxOther);
			}
			if (incomingRequest.containsKey("RequisitionLine_icPoLine"))
			{
				String icPoLineString = (String) incomingRequest.get("RequisitionLine_icPoLine");
				if (Utility.isEmpty(icPoLineString))
				{
					icPoLineString = "0";
				}
				BigDecimal icPoLine = new BigDecimal ( icPoLineString );
				requisitionLine.setIcPoLine(icPoLine);
			}

			if (incomingRequest.containsKey("RequisitionLine_icRevisedPoLine"))
			{
				String icRevisedPoLineString = (String) incomingRequest.get("RequisitionLine_icRevisedPoLine");
				if (HiltonUtility.isEmpty(icRevisedPoLineString))
				{
					icRevisedPoLineString = "0";
				}
				BigDecimal icRevisedPoLine = new BigDecimal (icRevisedPoLineString);
				requisitionLine.setIcRevisedPoLine(icRevisedPoLine);
			}

			if (incomingRequest.containsKey("RequisitionLine_splits"))
			{
				String splits = (String ) incomingRequest.get("RequisitionLine_splits");
				requisitionLine.setSplits(splits);
			}
			if (incomingRequest.containsKey("RequisitionLine_status"))
			{
				String status = (String ) incomingRequest.get("RequisitionLine_status");
				requisitionLine.setStatus(status);
			}
			if (incomingRequest.containsKey("RequisitionLine_commentFlag"))
			{
				String commentFlag = (String ) incomingRequest.get("RequisitionLine_commentFlag");
				requisitionLine.setCommentFlag(commentFlag);
			}
			if (incomingRequest.containsKey("RequisitionLine_taxPercent"))
			{
				String taxPercentString = (String) incomingRequest.get("RequisitionLine_taxPercent");
				if (Utility.isEmpty(taxPercentString))
				{
					taxPercentString = "0";
				}
				BigDecimal taxPercent = new BigDecimal ( taxPercentString );
				requisitionLine.setTaxPercent(taxPercent);
			}
			if (incomingRequest.containsKey("RequisitionLine_taxAmount"))
			{
				String taxAmountString = (String) incomingRequest.get("RequisitionLine_taxAmount");
				if (Utility.isEmpty(taxAmountString))
				{
					taxAmountString = "0";
				}
				BigDecimal taxAmount = new BigDecimal ( taxAmountString );
				requisitionLine.setTaxAmount(taxAmount);
			}
			if (incomingRequest.containsKey("RequisitionLine_shippingTaxAmt"))
			{
				String shippingTaxAmtString = (String) incomingRequest.get("RequisitionLine_shippingTaxAmt");
				if (Utility.isEmpty(shippingTaxAmtString))
				{
					shippingTaxAmtString = "0";
				}
				BigDecimal shippingTaxAmt = new BigDecimal ( shippingTaxAmtString );
				requisitionLine.setShippingTaxAmt(shippingTaxAmt);
			}
			if (incomingRequest.containsKey("RequisitionLine_otherTaxAmount"))
			{
				String otherTaxAmountString = (String) incomingRequest.get("RequisitionLine_otherTaxAmount");
				if (Utility.isEmpty(otherTaxAmountString))
				{
					otherTaxAmountString = "0";
				}
				BigDecimal otherTaxAmount = new BigDecimal ( otherTaxAmountString );
				requisitionLine.setOtherTaxAmount(otherTaxAmount);
			}
			if (incomingRequest.containsKey("RequisitionLine_reqType"))
			{
				String reqType = (String ) incomingRequest.get("RequisitionLine_reqType");
				requisitionLine.setReqType(reqType);
			}
			if (incomingRequest.containsKey("RequisitionLine_catalogId"))
			{
				String catalogId = (String ) incomingRequest.get("RequisitionLine_catalogId");
				requisitionLine.setCatalogId(catalogId);
			}
			if (incomingRequest.containsKey("RequisitionLine_umFactor"))
			{
				String umFactorString = (String) incomingRequest.get("RequisitionLine_umFactor");
				if (Utility.isEmpty(umFactorString))
				{
					umFactorString = "1";
				}
				try
				{
					BigDecimal umFactor = new BigDecimal ( umFactorString );
					requisitionLine.setUmFactor(umFactor);
				}
				catch (Exception e)
				{
					//default to 1
					requisitionLine.setUmFactor(new BigDecimal(1));
				}
			}
			if (incomingRequest.containsKey("RequisitionLine_lineTotal"))
			{
				String lineTotalString = (String) incomingRequest.get("RequisitionLine_lineTotal");
				if (Utility.isEmpty(lineTotalString))
				{
					lineTotalString = "0";
				}
				BigDecimal lineTotal = new BigDecimal ( lineTotalString );
				requisitionLine.setLineTotal(lineTotal);
			}
			if (incomingRequest.containsKey("RequisitionLine_taxOvr"))
			{
				String taxOvr = (String ) incomingRequest.get("RequisitionLine_taxOvr");
				requisitionLine.setTaxOvr(taxOvr);
			}
			if (incomingRequest.containsKey("RequisitionLine_discOvr"))
			{
				String discOvr = (String ) incomingRequest.get("RequisitionLine_discOvr");
				requisitionLine.setDiscOvr(discOvr);
			}
			if (incomingRequest.containsKey("RequisitionLine_shipOvr"))
			{
				String shipOvr = (String ) incomingRequest.get("RequisitionLine_shipOvr");
				requisitionLine.setShipOvr(shipOvr);
			}
			if (incomingRequest.containsKey("RequisitionLine_otherOvr"))
			{
				String otherOvr = (String ) incomingRequest.get("RequisitionLine_otherOvr");
				requisitionLine.setOtherOvr(otherOvr);
			}
			if (incomingRequest.containsKey("RequisitionLine_shiptoFlag"))
			{
				String shiptoFlag = (String ) incomingRequest.get("RequisitionLine_shiptoFlag");
				requisitionLine.setShiptoFlag(shiptoFlag);
			}
			if (incomingRequest.containsKey("RequisitionLine_autoRelease"))
			{
				String autoReleaseString = (String) incomingRequest.get("RequisitionLine_autoRelease");
				if (Utility.isEmpty(autoReleaseString))
				{
					autoReleaseString = "0";
				}
				BigDecimal autoRelease = new BigDecimal ( autoReleaseString );
				requisitionLine.setAutoRelease(autoRelease);
			}
			if (incomingRequest.containsKey("RequisitionLine_lastQtyEntered"))
			{
				String lastQtyEnteredString = (String) incomingRequest.get("RequisitionLine_lastQtyEntered");
				if (Utility.isEmpty(lastQtyEnteredString))
				{
					lastQtyEnteredString = "0";
				}
				BigDecimal lastQtyEntered = new BigDecimal ( lastQtyEnteredString );
				requisitionLine.setLastQtyEntered(lastQtyEntered);
			}
			if (incomingRequest.containsKey("RequisitionLine_assignedBuyer"))
			{
				String assignedBuyer = (String ) incomingRequest.get("RequisitionLine_assignedBuyer");
				requisitionLine.setAssignedBuyer(assignedBuyer);
			}
			if (incomingRequest.containsKey("RequisitionLine_assignedDate"))
			{
				String assignedDateString = (String) incomingRequest.get("RequisitionLine_assignedDate");
				Date assignedDate = Dates.getSqlDate(assignedDateString, userDateFormat);
				requisitionLine.setAssignedDate(assignedDate);
			}
			if (incomingRequest.containsKey("RequisitionLine_allocated"))
			{
				String allocatedString = (String) incomingRequest.get("RequisitionLine_allocated");
				if (Utility.isEmpty(allocatedString))
				{
					allocatedString = "0";
				}
				BigDecimal allocated = new BigDecimal ( allocatedString );
				requisitionLine.setAllocated(allocated);
			}
			if (incomingRequest.containsKey("RequisitionLine_backordered"))
			{
				String backorderedString = (String) incomingRequest.get("RequisitionLine_backordered");
				if (Utility.isEmpty(backorderedString))
				{
					backorderedString = "0";
				}
				BigDecimal backordered = new BigDecimal ( backorderedString );
				requisitionLine.setBackordered(backordered);
			}
			if (incomingRequest.containsKey("RequisitionLine_mfgName"))
			{
				String mfgName = (String ) incomingRequest.get("RequisitionLine_mfgName");
				requisitionLine.setMfgName(mfgName);
			}
			if (incomingRequest.containsKey("RequisitionLine_modelNumber"))
			{
				String modelNumber = (String ) incomingRequest.get("RequisitionLine_modelNumber");
				requisitionLine.setModelNumber(modelNumber);
			}
			if (incomingRequest.containsKey("RequisitionLine_udf1Code"))
			{
				String udf1Code = (String ) incomingRequest.get("RequisitionLine_udf1Code");
				requisitionLine.setUdf1Code(udf1Code);
			}
			if (incomingRequest.containsKey("RequisitionLine_udf2Code"))
			{
				String udf2Code = (String ) incomingRequest.get("RequisitionLine_udf2Code");
				requisitionLine.setUdf2Code(udf2Code);
			}
			if (incomingRequest.containsKey("RequisitionLine_udf3Code"))
			{
				String udf3Code = (String ) incomingRequest.get("RequisitionLine_udf3Code");
				requisitionLine.setUdf3Code(udf3Code);
			}
			if (incomingRequest.containsKey("RequisitionLine_udf4Code"))
			{
				String udf4Code = (String ) incomingRequest.get("RequisitionLine_udf4Code");
				requisitionLine.setUdf4Code(udf4Code);
			}
			if (incomingRequest.containsKey("RequisitionLine_udf5Code"))
			{
				String udf5Code = (String ) incomingRequest.get("RequisitionLine_udf5Code");
				requisitionLine.setUdf5Code(udf5Code);
			}
            if (incomingRequest.containsKey("RequisitionLine_udf6Code"))
            {
                String udf6Code = (String ) incomingRequest.get("RequisitionLine_udf6Code");
                requisitionLine.setUdf6Code(udf6Code);
            }
            if (incomingRequest.containsKey("RequisitionLine_udf7Code"))
            {
                String udf7Code = (String ) incomingRequest.get("RequisitionLine_udf7Code");
                requisitionLine.setUdf7Code(udf7Code);
            }
            if (incomingRequest.containsKey("RequisitionLine_udf8Code"))
            {
                String udf8Code = (String ) incomingRequest.get("RequisitionLine_udf8Code");
                requisitionLine.setUdf8Code(udf8Code);
            }
            if (incomingRequest.containsKey("RequisitionLine_udf9Code"))
            {
                String udf9Code = (String ) incomingRequest.get("RequisitionLine_udf9Code");
                requisitionLine.setUdf9Code(udf9Code);
            }
            if (incomingRequest.containsKey("RequisitionLine_udf10Code"))
            {
                String udf10Code = (String ) incomingRequest.get("RequisitionLine_udf10Code");
                requisitionLine.setUdf10Code(udf10Code);
            }
            if (incomingRequest.containsKey("RequisitionLine_memoLine"))
            {
                String memoLine = (String ) incomingRequest.get("RequisitionLine_memoLine");
                requisitionLine.setMemoLine(memoLine);
            }

			if (incomingRequest.containsKey("RequisitionLine_rqLineKey"))
			{
				String rqLineKey = (String ) incomingRequest.get("RequisitionLine_rqLineKey");
				requisitionLine.setRqLineKey(rqLineKey);
			}
			if (incomingRequest.containsKey("RequisitionLine_receiptRequired"))
			{
				String receiptRequired = (String ) incomingRequest.get("RequisitionLine_receiptRequired");
				requisitionLine.setReceiptRequired(receiptRequired);
			}
			if (incomingRequest.containsKey("RequisitionLine_roFlag"))
			{
				String roFlag = (String ) incomingRequest.get("RequisitionLine_roFlag");
				requisitionLine.setRoFlag(roFlag);
			}
			if (incomingRequest.containsKey("RequisitionLine_routing"))
			{
				String routing = (String ) incomingRequest.get("RequisitionLine_routing");
				requisitionLine.setRouting(routing);
			}
			if (incomingRequest.containsKey("RequisitionLine_itemLocation"))
			{
				String itemLocation = (String ) incomingRequest.get("RequisitionLine_itemLocation");
				requisitionLine.setItemLocation(itemLocation);
			}
			if (incomingRequest.containsKey("RequisitionLine_description"))
			{
				String description = (String ) incomingRequest.get("RequisitionLine_description");
				requisitionLine.setDescription(description);
			}
			if (incomingRequest.containsKey("RequisitionLine_icLineHistory"))
			{
				String icLineHistoryString = (String) incomingRequest.get("RequisitionLine_icLineHistory");
				if (Utility.isEmpty(icLineHistoryString))
				{
					icLineHistoryString = "0";
				}
				BigDecimal icLineHistory = new BigDecimal ( icLineHistoryString );
				requisitionLine.setIcLineHistory(icLineHistory);
			}
			if (incomingRequest.containsKey("RequisitionLine_taxCode"))
			{
				String taxCode = (String ) incomingRequest.get("RequisitionLine_taxCode");
				requisitionLine.setTaxCode(taxCode);
			}
			if (incomingRequest.containsKey("RequisitionLine_vendorId"))
			{
				String vendorId = (String ) incomingRequest.get("RequisitionLine_vendorId");
				requisitionLine.setVendorId(vendorId);

				String vendorName =  (String) incomingRequest.get("RequisitionLine_vendorName");
				if (HiltonUtility.isEmpty(vendorName))
				{
					vendorName = VendorManager.getInstance().getVendorName(organizationId, vendorId);
				}
				requisitionLine.setVendorName(vendorName);
			}
			if (incomingRequest.containsKey("RequisitionLine_requisitionerCode"))
			{
				String requisitionerCode = (String ) incomingRequest.get("RequisitionLine_requisitionerCode");
				requisitionLine.setRequisitionerCode(requisitionerCode);
			}
			if (incomingRequest.containsKey("RequisitionLine_departmentCode"))
			{
				String departmentCode = (String ) incomingRequest.get("RequisitionLine_departmentCode");
				requisitionLine.setDepartmentCode(departmentCode);
			}

			if (incomingRequest.containsKey("RequisitionLine_icAccount"))
			{
				String icAccountString = (String) incomingRequest.get("RequisitionLine_icAccount");
				if (Utility.isEmpty(icAccountString))
				{
					icAccountString = "0";
				}
				BigDecimal icAccount = new BigDecimal ( icAccountString );
				requisitionLine.setIcAccount(icAccount);
			}

			if (incomingRequest.containsKey("RequisitionLine_useTaxCode"))
			{
				String useTaxCode = (String ) incomingRequest.get("RequisitionLine_useTaxCode");
				requisitionLine.setUseTaxCode(useTaxCode);
			}

			if (incomingRequest.containsKey("RequisitionLine_useTaxPercent"))
			{
				String taxPercentString = (String) incomingRequest.get("RequisitionLine_useTaxPercent");
				if (Utility.isEmpty(taxPercentString)){		taxPercentString = "0";		}
				BigDecimal taxPercent = new BigDecimal ( taxPercentString );
				requisitionLine.setUseTaxPercent(taxPercent);
			}

			if (incomingRequest.containsKey("RequisitionLine_useTaxAmount"))
			{
				String taxAmountString = (String) incomingRequest.get("RequisitionLine_useTaxAmount");
				if (Utility.isEmpty(taxAmountString)){		taxAmountString = "0";		}
				BigDecimal taxAmount = new BigDecimal ( taxAmountString );
				requisitionLine.setUseTaxAmount(taxAmount);
			}

			if (incomingRequest.containsKey("RequisitionLine_useTaxable"))
			{
				String taxable = (String ) incomingRequest.get("RequisitionLine_useTaxable");
				requisitionLine.setUseTaxable(taxable);
			}

			if (incomingRequest.containsKey("RequisitionLine_icXls"))
			{
				String icXlsString = (String) incomingRequest.get("RequisitionLine_icXls");
				if (Utility.isEmpty(icXlsString))
				{
					icXlsString = "0";
				}
				try
				{
					BigDecimal icXls = new BigDecimal ( icXlsString );
					requisitionLine.setIcXls(icXls);
				}
				catch (Exception e)
				{
					requisitionLine.setIcXls(new BigDecimal(0));
				}
			}

			if (incomingRequest.containsKey("RequisitionLine_requiredDate"))
			{
				String requiredDateString = (String) incomingRequest.get("RequisitionLine_requiredDate");
				Date requiredDate = null;
				if (!HiltonUtility.isEmpty(requiredDateString))
				{
					requiredDate = Dates.getDate(requiredDateString);
				}
				requisitionLine.setRequiredDate(requiredDate);
			}
			if (incomingRequest.containsKey("RequisitionLine_dateEntered"))
			{
				String dateEnteredString = (String) incomingRequest.get("RequisitionLine_dateEntered");
				Date dateEntered = null;
				if (!HiltonUtility.isEmpty(dateEnteredString))
				{
					dateEntered = Dates.getDate(dateEnteredString);
				}
				requisitionLine.setDateEntered(dateEntered);
			}
			if (incomingRequest.containsKey("RequisitionLine_vendContactCode"))
			{
				String vendContactCode = (String) incomingRequest.get("RequisitionLine_vendContactCode");
				requisitionLine.setVendContactCode(vendContactCode);
			}
			if (incomingRequest.containsKey("RequisitionLine_duomUmCode"))
			{
				String duomUmCode = (String ) incomingRequest.get("RequisitionLine_duomUmCode");
				requisitionLine.setDuomUmCode(duomUmCode);
			}
			if (incomingRequest.containsKey("RequisitionLine_duomQuantity"))
			{
				String quantityString = (String) incomingRequest.get("RequisitionLine_duomQuantity");
				if (Utility.isEmpty(quantityString))
				{
					quantityString = "0";
				}
				BigDecimal quantity = new BigDecimal ( quantityString );
				requisitionLine.setDuomQuantity(quantity);
			}
			if (incomingRequest.containsKey("RequisitionLine_shelfLifeRqd"))
			{
				String shelfLife = (String) incomingRequest.get("RequisitionLine_shelfLifeRqd");
				requisitionLine.setShelfLifeRqd(shelfLife);
			}
			if (incomingRequest.containsKey("RequisitionLine_blanketOrder"))
			{
				Object bo =incomingRequest.get("RequisitionLine_blanketOrder");
				if (bo instanceof String) {
					String blanketOrder = (String) bo ;
					requisitionLine.setBlanketOrder(blanketOrder);
				}
			}
			if (incomingRequest.containsKey("RequisitionLine_msrNumber"))
			{
				String msrNumber = (String) incomingRequest.get("RequisitionLine_msrNumber");
				requisitionLine.setMsrNumber(msrNumber);
			}
			if (incomingRequest.containsKey("RequisitionLine_chemicalItemNumber"))
			{
				String chemicalItemNumber = (String) incomingRequest.get("RequisitionLine_chemicalItemNumber");
				requisitionLine.setChemicalItemNumber(chemicalItemNumber);
			}
			result = requisitionLine;
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
