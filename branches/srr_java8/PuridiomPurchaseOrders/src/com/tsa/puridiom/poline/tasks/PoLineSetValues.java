package com.tsa.puridiom.poline.tasks;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.vendor.VendorManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;

public class PoLineSetValues extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        Object result = null;
    	String organizationId = (String) incomingRequest.get("organizationId");
        String userDateFormat = (String) incomingRequest.get("userDateFormat");

        if (Utility.isEmpty(userDateFormat)) {
            userDateFormat = PropertiesManager.getInstance(organizationId).getProperty("MISC", "DATEFORMAT", "MM-dd-yyyy");
        }

        try
        {
            PoLine poLine = (PoLine) incomingRequest.get("poLine");
            if (poLine == null)
            {
                poLine = new PoLine();
                Log.debug(this, "creating poline: " + incomingRequest.get("PoLine_icPoLine"));
            }

            if (incomingRequest.containsKey("PoLine_icPoHeader"))
            {
                String icPoHeaderString = (String) incomingRequest.get("PoLine_icPoHeader");
                if (Utility.isEmpty(icPoHeaderString))
                {
                    icPoHeaderString = "0";
                }
                BigDecimal icPoHeader = new BigDecimal ( icPoHeaderString );
                poLine.setIcPoHeader(icPoHeader);
            }
            if (incomingRequest.containsKey("PoLine_icPoLine"))
            {
                String icPoLineString = (String) incomingRequest.get("PoLine_icPoLine");
                if (Utility.isEmpty(icPoLineString))
                {
                    icPoLineString = "0";
                }
                BigDecimal icPoLine = new BigDecimal ( icPoLineString );
                poLine.setIcPoLine(icPoLine);
                //poLine.setIcLineKey(icPoLine);
            }
            if (incomingRequest.containsKey("PoLine_lineNumber"))
            {
                String lineNumberString = (String) incomingRequest.get("PoLine_lineNumber");
                if (Utility.isEmpty(lineNumberString))
                {
                    lineNumberString = "0";
                }
                BigDecimal lineNumber = new BigDecimal ( lineNumberString );
                poLine.setLineNumber(lineNumber);
            }
            if (incomingRequest.containsKey("PoLine_poNumber"))
            {
                String poNumber = (String ) incomingRequest.get("PoLine_poNumber");
                poLine.setPoNumber(poNumber);
            }
            if (incomingRequest.containsKey("PoLine_releaseNumber"))
            {
                String releaseNumberString = (String) incomingRequest.get("PoLine_releaseNumber");
                if (Utility.isEmpty(releaseNumberString))
                {
                    releaseNumberString = "0";
                }
                BigDecimal releaseNumber = new BigDecimal ( releaseNumberString );
                poLine.setReleaseNumber(releaseNumber);
            }
            if (incomingRequest.containsKey("PoLine_itemNumber"))
            {
                String itemNumber = (String ) incomingRequest.get("PoLine_itemNumber");
                poLine.setItemNumber(itemNumber);
            }
            if (incomingRequest.containsKey("PoLine_itemSource"))
            {
                String itemSource = (String ) incomingRequest.get("PoLine_itemSource");
                poLine.setItemSource(itemSource);
            }
            if (incomingRequest.containsKey("PoLine_umCode"))
            {
                String umCode = (String ) incomingRequest.get("PoLine_umCode");
                poLine.setUmCode(umCode);
            }
            if (incomingRequest.containsKey("PoLine_quantity"))
            {
                String quantityString = (String) incomingRequest.get("PoLine_quantity");
                if (Utility.isEmpty(quantityString))
                {
                    quantityString = "0";
                }
                BigDecimal quantity = new BigDecimal ( quantityString );
                poLine.setQuantity(quantity);
            }
            if (incomingRequest.containsKey("PoLine_unitPrice"))
            {
                String unitPriceString = (String) incomingRequest.get("PoLine_unitPrice");
                if (Utility.isEmpty(unitPriceString))
                {
                    unitPriceString = "0";
                }
                BigDecimal unitPrice = new BigDecimal ( unitPriceString );
                poLine.setUnitPrice(unitPrice);
            }
            if (incomingRequest.containsKey("PoLine_commodity"))
            {
                String commodity = (String ) incomingRequest.get("PoLine_commodity");
                poLine.setCommodity(commodity);
            }
            if (incomingRequest.containsKey("PoLine_taxable"))
            {
                String taxable = (String ) incomingRequest.get("PoLine_taxable");
                poLine.setTaxable(taxable);
            }
            if (incomingRequest.containsKey("PoLine_taxPercent"))
            {
                String taxPercentString = (String) incomingRequest.get("PoLine_taxPercent");
                if (Utility.isEmpty(taxPercentString))
                {
                    taxPercentString = "0";
                }
                BigDecimal taxPercent = new BigDecimal ( taxPercentString );
                poLine.setTaxPercent(taxPercent);
            }
            if (incomingRequest.containsKey("PoLine_taxAmount"))
            {
                String taxAmountString = (String) incomingRequest.get("PoLine_taxAmount");
                if (Utility.isEmpty(taxAmountString))
                {
                    taxAmountString = "0";
                }
                BigDecimal taxAmount = new BigDecimal ( taxAmountString );
                poLine.setTaxAmount(taxAmount);
            }
            if (incomingRequest.containsKey("PoLine_discountSource"))
            {
                String discountSource = (String ) incomingRequest.get("PoLine_discountSource");
                poLine.setDiscountSource(discountSource);
            }
            if (incomingRequest.containsKey("PoLine_discountPercent"))
            {
                String discountPercentString = (String) incomingRequest.get("PoLine_discountPercent");
                if (Utility.isEmpty(discountPercentString))
                {
                    discountPercentString = "0";
                }
                BigDecimal discountPercent = new BigDecimal ( discountPercentString );
                poLine.setDiscountPercent(discountPercent);
            }
            if (incomingRequest.containsKey("PoLine_discountAmount"))
            {
                String discountAmountString = (String) incomingRequest.get("PoLine_discountAmount");
                if (Utility.isEmpty(discountAmountString))
                {
                    discountAmountString = "0";
                }
                BigDecimal discountAmount = new BigDecimal ( discountAmountString );
                poLine.setDiscountAmount(discountAmount);
            }
            if (incomingRequest.containsKey("PoLine_shippingCharges"))
            {
                String shippingChargesString = (String) incomingRequest.get("PoLine_shippingCharges");
                if (Utility.isEmpty(shippingChargesString))
                {
                    shippingChargesString = "0";
                }
                BigDecimal shippingCharges = new BigDecimal ( shippingChargesString );
                poLine.setShippingCharges(shippingCharges);
            }
            if (incomingRequest.containsKey("PoLine_shippingTaxable"))
            {
                String shippingTaxable = (String ) incomingRequest.get("PoLine_shippingTaxable");
                poLine.setShippingTaxable(shippingTaxable);
            }
            if (incomingRequest.containsKey("PoLine_shippingTax"))
            {
                String shippingTaxString = (String) incomingRequest.get("PoLine_shippingTax");
                if (Utility.isEmpty(shippingTaxString))
                {
                    shippingTaxString = "0";
                }
                BigDecimal shippingTax = new BigDecimal ( shippingTaxString );
                poLine.setShippingTax(shippingTax);
            }
            if (incomingRequest.containsKey("PoLine_otherCharges"))
            {
                String otherChargesString = (String) incomingRequest.get("PoLine_otherCharges");
                if (Utility.isEmpty(otherChargesString))
                {
                    otherChargesString = "0";
                }
                BigDecimal otherCharges = new BigDecimal ( otherChargesString );
                poLine.setOtherCharges(otherCharges);
            }
            if (incomingRequest.containsKey("PoLine_otherDescription"))
            {
                String otherDescription = (String ) incomingRequest.get("PoLine_otherDescription");
                poLine.setOtherDescription(otherDescription);
            }
            if (incomingRequest.containsKey("PoLine_otherTaxable"))
            {
                String otherTaxable = (String ) incomingRequest.get("PoLine_otherTaxable");
                poLine.setOtherTaxable(otherTaxable);
            }
            if (incomingRequest.containsKey("PoLine_otherTax"))
            {
                String otherTaxString = (String) incomingRequest.get("PoLine_otherTax");
                if (Utility.isEmpty(otherTaxString))
                {
                    otherTaxString = "0";
                }
                BigDecimal otherTax = new BigDecimal ( otherTaxString );
                poLine.setOtherTax(otherTax);
            }
            if (incomingRequest.containsKey("PoLine_icReqLine"))
            {
                String icReqLineString = (String) incomingRequest.get("PoLine_icReqLine");
                if (Utility.isEmpty(icReqLineString))
                {
                    icReqLineString = "0";
                }
                BigDecimal icReqLine = new BigDecimal ( icReqLineString );
                poLine.setIcReqLine(icReqLine);
            }
            if (incomingRequest.containsKey("PoLine_asset"))
            {
                String asset = (String ) incomingRequest.get("PoLine_asset");
                poLine.setAsset(asset);
            }
            if (incomingRequest.containsKey("PoLine_splits"))
            {
                String splits = (String ) incomingRequest.get("PoLine_splits");
                poLine.setSplits(splits);
            }
            if (incomingRequest.containsKey("PoLine_commentFlag"))
            {
                String commentFlag = (String ) incomingRequest.get("PoLine_commentFlag");
                poLine.setCommentFlag(commentFlag);
            }
            if (incomingRequest.containsKey("PoLine_status"))
            {
                String status = (String ) incomingRequest.get("PoLine_status");
                poLine.setStatus(status);
            }
            if (incomingRequest.containsKey("PoLine_icRfqLine"))
            {
                String icRfqLineString = (String) incomingRequest.get("PoLine_icRfqLine");
                if (Utility.isEmpty(icRfqLineString))
                {
                    icRfqLineString = "0";
                }
                BigDecimal icRfqLine = new BigDecimal ( icRfqLineString );
                poLine.setIcRfqLine(icRfqLine);
            }
            if (incomingRequest.containsKey("PoLine_umConv"))
            {
                String umConv = (String) incomingRequest.get("PoLine_umConv");
                poLine.setUmConv(umConv);
            }
            if (incomingRequest.containsKey("PoLine_umFactor"))
            {
                String umFactorString = (String) incomingRequest.get("PoLine_umFactor");
                if (Utility.isEmpty(umFactorString))
                {
                    umFactorString = "1";
                }
                BigDecimal umFactor = new BigDecimal ( umFactorString );
                poLine.setUmFactor(umFactor);
            }
            if (incomingRequest.containsKey("PoLine_catalogId"))
            {
                String catalogId = (String ) incomingRequest.get("PoLine_catalogId");
                poLine.setCatalogId(catalogId);
            }
            if (incomingRequest.containsKey("PoLine_lineTotal"))
            {
                String lineTotalString = (String) incomingRequest.get("PoLine_lineTotal");
                if (Utility.isEmpty(lineTotalString))
                {
                    lineTotalString = "0";
                }
                BigDecimal lineTotal = new BigDecimal ( lineTotalString );
                poLine.setLineTotal(lineTotal);
            }
            if (incomingRequest.containsKey("PoLine_taxOvr"))
            {
                String taxOvr = (String ) incomingRequest.get("PoLine_taxOvr");
                poLine.setTaxOvr(taxOvr);
            }
            if (incomingRequest.containsKey("PoLine_discOvr"))
            {
                String discOvr = (String ) incomingRequest.get("PoLine_discOvr");
                poLine.setDiscOvr(discOvr);
            }
            if (incomingRequest.containsKey("PoLine_shipOvr"))
            {
                String shipOvr = (String ) incomingRequest.get("PoLine_shipOvr");
                poLine.setShipOvr(shipOvr);
            }
            if (incomingRequest.containsKey("PoLine_otherOvr"))
            {
                String otherOvr = (String ) incomingRequest.get("PoLine_otherOvr");
                poLine.setOtherOvr(otherOvr);
            }
            if (incomingRequest.containsKey("PoLine_shiptoFlag"))
            {
                String shiptoFlag = (String ) incomingRequest.get("PoLine_shiptoFlag");
                poLine.setShiptoFlag(shiptoFlag);
            }
            if (incomingRequest.containsKey("PoLine_receiptRequired"))
            {
                String receiptRequired = (String ) incomingRequest.get("PoLine_receiptRequired");
                poLine.setReceiptRequired(receiptRequired);
            }
            if (incomingRequest.containsKey("PoLine_modelNumber"))
            {
                String modelNumber = (String ) incomingRequest.get("PoLine_modelNumber");
                poLine.setModelNumber(modelNumber);
            }
            if (incomingRequest.containsKey("PoLine_udf1Code"))
            {
                String udf1Code = (String ) incomingRequest.get("PoLine_udf1Code");
                poLine.setUdf1Code(udf1Code);
            }
            if (incomingRequest.containsKey("PoLine_udf2Code"))
            {
                String udf2Code = (String ) incomingRequest.get("PoLine_udf2Code");
                poLine.setUdf2Code(udf2Code);
            }
            if (incomingRequest.containsKey("PoLine_udf3Code"))
            {
                String udf3Code = (String ) incomingRequest.get("PoLine_udf3Code");
                poLine.setUdf3Code(udf3Code);
            }
            if (incomingRequest.containsKey("PoLine_udf4Code"))
            {
                String udf4Code = (String ) incomingRequest.get("PoLine_udf4Code");
                poLine.setUdf4Code(udf4Code);
            }
            if (incomingRequest.containsKey("PoLine_udf5Code"))
            {
                String udf5Code = (String ) incomingRequest.get("PoLine_udf5Code");
                poLine.setUdf5Code(udf5Code);
            }
            if (incomingRequest.containsKey("PoLine_udf6Code"))
            {
                String udf6Code = (String ) incomingRequest.get("PoLine_udf6Code");
                poLine.setUdf6Code(udf6Code);
            }
            if (incomingRequest.containsKey("PoLine_udf7Code"))
            {
                String udf7Code = (String ) incomingRequest.get("PoLine_udf7Code");
                poLine.setUdf7Code(udf7Code);
            }
            if (incomingRequest.containsKey("PoLine_udf8Code"))
            {
                String udf8Code = (String ) incomingRequest.get("PoLine_udf8Code");
                poLine.setUdf8Code(udf8Code);
            }
            if (incomingRequest.containsKey("PoLine_udf9Code"))
            {
                String udf9Code = (String ) incomingRequest.get("PoLine_udf9Code");
                poLine.setUdf9Code(udf9Code);
            }
            if (incomingRequest.containsKey("PoLine_udf10Code"))
            {
                String udf10Code = (String ) incomingRequest.get("PoLine_udf10Code");
                poLine.setUdf10Code(udf10Code);
            }
            if (incomingRequest.containsKey("PoLine_memoLine"))
            {
                String memoLine = (String ) incomingRequest.get("PoLine_memoLine");
                poLine.setMemoLine(memoLine);
            }

            if (incomingRequest.containsKey("PoLine_mfgName"))
            {
                String mfgName = (String ) incomingRequest.get("PoLine_mfgName");
                poLine.setMfgName(mfgName);
            }
            if (incomingRequest.containsKey("PoLine_lineRevNo"))
            {
                String lineRevNo = (String ) incomingRequest.get("PoLine_lineRevNo");
                poLine.setLineRevNo(lineRevNo);
            }
            if (incomingRequest.containsKey("PoLine_actionInd"))
            {
                String actionInd = (String ) incomingRequest.get("PoLine_actionInd");
                poLine.setActionInd(actionInd);
            }
            if (incomingRequest.containsKey("PoLine_icRelKey"))
            {
                String icRelKeyString = (String) incomingRequest.get("PoLine_icRelKey");
                if (Utility.isEmpty(icRelKeyString))
                {
                    icRelKeyString = "0";
                }
                BigDecimal icRelKey = new BigDecimal ( icRelKeyString );
                poLine.setIcRelKey(icRelKey);
            }
            if (incomingRequest.containsKey("PoLine_chgPromCnt"))
            {
                String chgPromCntString = (String) incomingRequest.get("PoLine_chgPromCnt");
                if (Utility.isEmpty(chgPromCntString))
                {
                    chgPromCntString = "0";
                }
                BigDecimal chgPromCnt = new BigDecimal ( chgPromCntString );
                poLine.setChgPromCnt(chgPromCnt);
            }
            if (incomingRequest.containsKey("PoLine_chgPromDate"))
            {
                String chgPromDateString = (String) incomingRequest.get("PoLine_chgPromDate");
                Date chgPromDate = Dates.getSqlDate(chgPromDateString, userDateFormat);
                poLine.setChgPromDate(chgPromDate);
            }
            if (incomingRequest.containsKey("PoLine_poPromised"))
            {
                String poPromisedString = (String) incomingRequest.get("PoLine_poPromised");
                Date poPromised = Dates.getSqlDate(poPromisedString, userDateFormat);
                poLine.setPoPromised(poPromised);
            }
            if (incomingRequest.containsKey("PoLine_roFlag"))
            {
                String roFlag = (String ) incomingRequest.get("PoLine_roFlag");
                poLine.setRoFlag(roFlag);
            }
            if (incomingRequest.containsKey("PoLine_icLineHistory"))
            {
                String icLineHistoryString = (String) incomingRequest.get("PoLine_icLineHistory");
                if (Utility.isEmpty(icLineHistoryString))
                {
                    icLineHistoryString = "0";
                }
                BigDecimal icLineHistory = new BigDecimal ( icLineHistoryString );
                poLine.setIcLineHistory(icLineHistory);
            }
            if (incomingRequest.containsKey("PoLine_itemLocation"))
            {
                String itemLocation = (String ) incomingRequest.get("PoLine_itemLocation");
                poLine.setItemLocation(itemLocation);
            }
            if (incomingRequest.containsKey("PoLine_description"))
            {
                String description = (String ) incomingRequest.get("PoLine_description");
                poLine.setDescription(description);
            }
            if (incomingRequest.containsKey("PoLine_routing"))
            {
                String routing = (String ) incomingRequest.get("PoLine_routing");
                poLine.setRouting(routing);
            }
            if (incomingRequest.containsKey("PoLine_qtyReceived"))
            {
                String qtyReceivedString = (String) incomingRequest.get("PoLine_qtyReceived");
                if (Utility.isEmpty(qtyReceivedString))
                {
                    qtyReceivedString = "0";
                }
                BigDecimal qtyReceived = new BigDecimal ( qtyReceivedString );
                poLine.setQtyReceived(qtyReceived);
            }
            if (incomingRequest.containsKey("PoLine_duomQtyReceived"))
            {
                String qtyReceivedString = (String) incomingRequest.get("PoLine_duomQtyReceived");
                if (Utility.isEmpty(qtyReceivedString))
                {
                    qtyReceivedString = "0";
                }
                BigDecimal qtyReceived = new BigDecimal ( qtyReceivedString );
                poLine.setDuomQtyReceived(qtyReceived);
            }

            if (incomingRequest.containsKey("PoLine_requisitionerCode"))
            {
                String requisitionerCode = (String ) incomingRequest.get("PoLine_requisitionerCode");
                poLine.setRequisitionerCode(requisitionerCode);
            }
            if (incomingRequest.containsKey("PoLine_taxCode"))
            {
                String taxCode = (String ) incomingRequest.get("PoLine_taxCode");
                poLine.setTaxCode(taxCode);
            }

            if (incomingRequest.containsKey("PoLine_icAccount"))
            {
                String icAccountString = (String) incomingRequest.get("PoLine_icAccount");
                if (Utility.isEmpty(icAccountString))
                {
                    icAccountString = "0";
                }
                BigDecimal icAccount = new BigDecimal ( icAccountString );
                poLine.setIcAccount(icAccount);
            }
            if (incomingRequest.containsKey("PoLine_departmentCode"))
            {
                String departmentCode = (String ) incomingRequest.get("PoLine_departmentCode");
                poLine.setDepartmentCode(departmentCode);
            }
            if (incomingRequest.containsKey("PoLine_requisitionNumber"))
            {
                String requisitionNumber = (String ) incomingRequest.get("PoLine_requisitionNumber");
                poLine.setRequisitionNumber(requisitionNumber);
            }
            if (incomingRequest.containsKey("PoLine_dateEntered"))
            {
                String dateEnteredString = (String) incomingRequest.get("PoLine_dateEntered");
                Date dateEntered = Dates.getSqlDate(dateEnteredString, userDateFormat);
                poLine.setDateEntered(dateEntered);
            }

            if (incomingRequest.containsKey("PoLine_lastChgDate"))
            {
                String lastChgDateString = (String) incomingRequest.get("PoLine_lastChgDate");
                Date lastChgDate = Dates.getSqlDate(lastChgDateString, userDateFormat);
                poLine.setLastChgDate(lastChgDate);
            }

            if (incomingRequest.containsKey("PoLine_icXls"))
			{
				String icXlsString = (String) incomingRequest.get("PoLine_icXls");
				if (Utility.isEmpty(icXlsString))
				{
					icXlsString = "0";
				}
				try
				{
					BigDecimal icXls = new BigDecimal ( icXlsString );
					poLine.setIcXls(icXls);
				}
				catch (Exception e)
				{
					poLine.setIcXls(new BigDecimal(0));
				}
			}
            if (incomingRequest.containsKey("PoLine_inspectionReqd"))
            {
                String inspectionReqd = (String ) incomingRequest.get("PoLine_inspectionReqd");
                poLine.setInspectionReqd(inspectionReqd);
            }
            if (incomingRequest.containsKey("PoLine_useTaxCode"))
            {
                String taxCode = (String ) incomingRequest.get("PoLine_useTaxCode");
                poLine.setUseTaxCode(taxCode);
            }
            if (incomingRequest.containsKey("PoLine_useTaxable"))
            {
                String taxable = (String ) incomingRequest.get("PoLine_useTaxable");
                poLine.setUseTaxable(taxable);
            }
            if (incomingRequest.containsKey("PoLine_useTaxPercent"))
            {
                String taxPercentString = (String) incomingRequest.get("PoLine_useTaxPercent");
                if (Utility.isEmpty(taxPercentString))
                {
                    taxPercentString = "0";
                }
                BigDecimal taxPercent = new BigDecimal ( taxPercentString );
                poLine.setUseTaxPercent(taxPercent);
            }
            if (incomingRequest.containsKey("PoLine_useTaxAmount"))
            {
                String taxAmountString = (String) incomingRequest.get("PoLine_useTaxAmount");
                if (Utility.isEmpty(taxAmountString))
                {
                    taxAmountString = "0";
                }
                BigDecimal taxAmount = new BigDecimal ( taxAmountString );
                poLine.setUseTaxAmount(taxAmount);
            }
            if (incomingRequest.containsKey("PoLine_icLineKey"))
            {
                String icLineKeyString = (String) incomingRequest.get("PoLine_icLineKey");
                if (Utility.isEmpty(icLineKeyString))
                {
                	icLineKeyString = "0";
                }
                BigDecimal icLineKey = new BigDecimal ( icLineKeyString );
                poLine.setIcLineKey(icLineKey);
            }
            if (incomingRequest.containsKey("PoLine_altItemNumber"))
            {
                String altItemNumber = (String ) incomingRequest.get("PoLine_altItemNumber");
                poLine.setAltItemNumber(altItemNumber);
            }
            if (incomingRequest.containsKey("PoLine_vendorId"))
			{
				String vendorId = (String) incomingRequest.get("PoLine_vendorId");
				poLine.setVendorId(vendorId);

				String vendorName = VendorManager.getInstance().getVendorName(organizationId, vendorId);
				poLine.setVendorName(vendorName);
			}
            if (incomingRequest.containsKey("PoLine_requiredDate"))
			{
				String requiredDateString = (String) incomingRequest.get("PoLine_requiredDate");
				Date requiredDate = null;
				if (!HiltonUtility.isEmpty(requiredDateString))
				{
					requiredDate = Dates.getSqlDate(requiredDateString, userDateFormat);
				}
				poLine.setRequiredDate(requiredDate);
			}
			if (incomingRequest.containsKey("PoLine_vendContactCode"))
			{
				String vendContactCode = (String) incomingRequest.get("PoLine_vendContactCode");
				poLine.setVendContactCode(vendContactCode);
			}
			if (incomingRequest.containsKey("PoLine_expensed"))
            {
                String expensedString = (String) incomingRequest.get("PoLine_expensed");
                if (HiltonUtility.isEmpty(expensedString))
                {
                    expensedString = "0";
                }
                BigDecimal expensed = new BigDecimal (expensedString);
                poLine.setExpensed(expensed);
            }
			if (incomingRequest.containsKey("PoLine_assetNumber"))
			{
				String assetNumber = (String) incomingRequest.get("PoLine_assetNumber");
				poLine.setAssetNumber(assetNumber);
			}
			if (incomingRequest.containsKey("PoLine_shelfLifeRqd"))
			{
				String shelfLife = (String) incomingRequest.get("PoLine_shelfLifeRqd");
				poLine.setShelfLifeRqd(shelfLife);
			}
			if (incomingRequest.containsKey("PoLine_chemicalItemNumber"))
			{
				String chemicalItemNumber = (String) incomingRequest.get("PoLine_chemicalItemNumber");
				poLine.setChemicalItemNumber(chemicalItemNumber);
			}
			if (incomingRequest.containsKey("PoLine_pyStatus"))
			{
				String pyStatus = (String) incomingRequest.get("PoLine_pyStatus");
				poLine.setPyStatus(pyStatus);
			}
			if (incomingRequest.containsKey("PoLine_qtyInvoiced"))
            {
                String qtyInvoicedString = (String) incomingRequest.get("PoLine_qtyInvoiced");
                if (Utility.isEmpty(qtyInvoicedString))
                {
                	qtyInvoicedString = "0";
                }
                BigDecimal qtyInvoiced = new BigDecimal ( qtyInvoicedString );
                poLine.setQtyReceived(qtyInvoiced);
            }
            result = poLine;
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