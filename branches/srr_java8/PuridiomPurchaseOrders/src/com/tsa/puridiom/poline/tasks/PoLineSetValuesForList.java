package com.tsa.puridiom.poline.tasks;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Utility;

public class PoLineSetValuesForList extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
            String userTimeZone = (String) incomingRequest.get("userTimeZone");
			Object icPoLineObj = incomingRequest.get("PoLine_icPoLine");
			List poLines = (List) incomingRequest.get("poLineList");

			if (icPoLineObj instanceof String[])
			{
				String icPoLineString[] = (String[]) icPoLineObj;

				if (poLines == null || poLines.size() != icPoLineString.length)
				{
					throw new Exception("The number of po line list records does not match the number of records in the request.");
				}

				String icPoHeaderString[] = null, lineNumberString[] = null, poNumber[] = null, releaseNumberString[] = null, itemNumberString[] = null, itemSource[] = null;
				String umCode[] = null, quantityString[] = null, unitPriceString[] = null, commodity[] = null, taxable[] = null, taxPercentString[] = null;
				String taxAmountString[] = null, discountSource[] = null, discountPercentString[] = null, discountAmountString[] = null, shippingChargesString[] = null;
				String shippingTaxable[] = null, shippingTaxString[] = null, otherChargesString[] = null, otherDescription[] = null, otherTaxable[] = null;
				String otherTaxString[] = null, icReqLineString[] = null, asset[] = null, splits[] = null, commentFlag[] = null, status[] = null;
				String icRfqLineString[] = null, umFactorString[] = null, catalogId[] = null, lineTotalString[] = null, taxOvr[] = null, discOvr[] = null, 	shipOvr[] = null;
				String otherOvr[] = null, shiptoFlag[] = null, receiptRequired[] = null, icLineKeyString[] = null, modelNumber[] = null, udf1Code[] = null;
				String udf2Code[] = null, udf3Code[] = null, udf4Code[] = null, udf5Code[] = null, mfgName[] = null, lineRevNo[] = null;
				String actionInd[] = null, icRelKeyString[] = null, chgPromCntString[] = null, chgPromDateString[] = null, poPromisedString[] = null, roFlag[] = null;
				String icLineHistoryString[] = null, itemLocation[] = null, description[] = null, routing[] = null, taxCode[] = null, qtyReceivedString[] = null, requisitionerCode[] = null;
				String icAccountString[] = null, departmentCode[] = null, requisitionNumber[] = null, dateEntered[] = null, inspectionReqd[] = null;
				String udf6Code[] = null, udf7Code[] = null, udf8Code[] = null, udf9Code[] = null, udf10Code[] = null,memoLine[] = null ;

				if (incomingRequest.containsKey("PoLine_icPoHeader")) {
					Object icPoHeaderObj = incomingRequest.get("PoLine_icPoHeader");
					if (icPoHeaderObj instanceof String[]) {
					    icPoHeaderString = (String[]) incomingRequest.get("PoLine_icPoHeader");
					} else {
					    icPoHeaderString = new String[icPoLineString.length];
						for (int ii = 0; ii < icPoLineString.length; ii++) {
						    icPoHeaderString[ii] = (String) icPoHeaderObj;
						}
					}
				}
				if (incomingRequest.containsKey("PoLine_icPoLine"))
				{
					icPoLineString = (String[])incomingRequest.get("PoLine_icPoLine");
				}
				if (incomingRequest.containsKey("PoLine_lineNumber"))
				{
					lineNumberString = (String[])incomingRequest.get("PoLine_lineNumber");
				}
				if (incomingRequest.containsKey("PoLine_poNumber"))
				{
					poNumber = (String[]) incomingRequest.get("PoLine_poNumber");
				}
				if (incomingRequest.containsKey("PoLine_releaseNumber"))
				{
					releaseNumberString = (String[]) incomingRequest.get("PoLine_releaseNumber");
				}
				if (incomingRequest.containsKey("PoLine_itemNumber"))
				{
					itemNumberString = (String[]) incomingRequest.get("PoLine_itemNumber");
				}
				if (incomingRequest.containsKey("PoLine_itemSource"))
				{
					itemSource = (String[]) incomingRequest.get("PoLine_itemSource");
				}
				if (incomingRequest.containsKey("PoLine_umCode"))
				{
					umCode = (String[]) incomingRequest.get("PoLine_umCode");
				}
				if (incomingRequest.containsKey("PoLine_quantity"))
				{
					quantityString = (String[])incomingRequest.get("PoLine_quantity");
				}
				if (incomingRequest.containsKey("PoLine_unitPrice"))
				{
					unitPriceString = (String[])incomingRequest.get("PoLine_unitPrice");
				}
				if (incomingRequest.containsKey("PoLine_commodity"))
				{
					commodity = (String[]) incomingRequest.get("PoLine_commodity");
				}
				if (incomingRequest.containsKey("PoLine_taxable"))
				{
					taxable = (String[]) incomingRequest.get("PoLine_taxable");
				}
				if (incomingRequest.containsKey("PoLine_taxPercent"))
				{
					taxPercentString = (String[])incomingRequest.get("PoLine_taxPercent");
				}
				if (incomingRequest.containsKey("PoLine_taxAmount"))
				{
					taxAmountString = (String[])incomingRequest.get("PoLine_taxAmount");
				}
				if (incomingRequest.containsKey("PoLine_discountSource"))
				{
					discountSource = (String[]) incomingRequest.get("PoLine_discountSource");
				}
				if (incomingRequest.containsKey("PoLine_discountPercent"))
				{
					discountPercentString = (String[])incomingRequest.get("PoLine_discountPercent");
				}
				if (incomingRequest.containsKey("PoLine_discountAmount"))
				{
					discountAmountString = (String[])incomingRequest.get("PoLine_discountAmount");
				}
				if (incomingRequest.containsKey("PoLine_shippingCharges"))
				{
					shippingChargesString = (String[])incomingRequest.get("PoLine_shippingCharges");
				}
				if (incomingRequest.containsKey("PoLine_shippingTaxable"))
				{
					shippingTaxable = (String[]) incomingRequest.get("PoLine_shippingTaxable");
				}
				if (incomingRequest.containsKey("PoLine_shippingTax"))
				{
					shippingTaxString = (String[])incomingRequest.get("PoLine_shippingTax");
				}
				if (incomingRequest.containsKey("PoLine_otherCharges"))
				{
					otherChargesString = (String[])incomingRequest.get("PoLine_otherCharges");
				}
				if (incomingRequest.containsKey("PoLine_otherDescription"))
				{
					otherDescription = (String[]) incomingRequest.get("PoLine_otherDescription");
				}
				if (incomingRequest.containsKey("PoLine_otherTaxable"))
				{
					otherTaxable = (String[]) incomingRequest.get("PoLine_otherTaxable");
				}
				if (incomingRequest.containsKey("PoLine_otherTax"))
				{
					otherTaxString = (String[])incomingRequest.get("PoLine_otherTax");
				}
				if (incomingRequest.containsKey("PoLine_icReqLine"))
				{
					icReqLineString = (String[])incomingRequest.get("PoLine_icReqLine");
				}
				if (incomingRequest.containsKey("PoLine_asset"))
				{
					asset = (String[]) incomingRequest.get("PoLine_asset");
				}
				if (incomingRequest.containsKey("PoLine_splits"))
				{
					splits = (String[]) incomingRequest.get("PoLine_splits");
				}
				if (incomingRequest.containsKey("PoLine_commentFlag"))
				{
					commentFlag = (String[]) incomingRequest.get("PoLine_commentFlag");
				}
				if (incomingRequest.containsKey("PoLine_status"))
				{
					status = (String[]) incomingRequest.get("PoLine_status");
				}
				if (incomingRequest.containsKey("PoLine_icRfqLine"))
				{
					icRfqLineString = (String[])incomingRequest.get("PoLine_icRfqLine");
				}
				if (incomingRequest.containsKey("PoLine_umFactor"))
				{
					umFactorString = (String[])incomingRequest.get("PoLine_umFactor");
				}
				if (incomingRequest.containsKey("PoLine_catalogId"))
				{
					catalogId = (String[]) incomingRequest.get("PoLine_catalogId");
				}
				if (incomingRequest.containsKey("PoLine_lineTotal"))
				{
					lineTotalString = (String[])incomingRequest.get("PoLine_lineTotal");
				}
				if (incomingRequest.containsKey("PoLine_taxOvr"))
				{
					taxOvr = (String[]) incomingRequest.get("PoLine_taxOvr");
				}
				if (incomingRequest.containsKey("PoLine_discOvr"))
				{
					discOvr = (String[]) incomingRequest.get("PoLine_discOvr");
				}
				if (incomingRequest.containsKey("PoLine_shipOvr"))
				{
					shipOvr = (String[]) incomingRequest.get("PoLine_shipOvr");
				}
				if (incomingRequest.containsKey("PoLine_otherOvr"))
				{
					otherOvr = (String[]) incomingRequest.get("PoLine_otherOvr");
				}
				if (incomingRequest.containsKey("PoLine_shiptoFlag"))
				{
					shiptoFlag = (String[]) incomingRequest.get("PoLine_shiptoFlag");
				}
				if (incomingRequest.containsKey("PoLine_receiptRequired"))
				{
					receiptRequired = (String[]) incomingRequest.get("PoLine_receiptRequired");
				}
				if (incomingRequest.containsKey("PoLine_icLineKey"))
				{
					icLineKeyString = (String[])incomingRequest.get("PoLine_icLineKey");
				}
				if (incomingRequest.containsKey("PoLine_modelNumber"))
				{
					modelNumber = (String[]) incomingRequest.get("PoLine_modelNumber");
				}
				if (incomingRequest.containsKey("PoLine_udf1Code"))
				{
					udf1Code = (String[]) incomingRequest.get("PoLine_udf1Code");
				}
				if (incomingRequest.containsKey("PoLine_udf2Code"))
				{
					udf2Code = (String[]) incomingRequest.get("PoLine_udf2Code");
				}
				if (incomingRequest.containsKey("PoLine_udf3Code"))
				{
					udf3Code = (String[]) incomingRequest.get("PoLine_udf3Code");
				}
				if (incomingRequest.containsKey("PoLine_udf4Code"))
				{
					udf4Code = (String[]) incomingRequest.get("PoLine_udf4Code");
				}
				if (incomingRequest.containsKey("PoLine_udf5Code"))
				{
					udf5Code = (String[]) incomingRequest.get("PoLine_udf5Code");
				}
				if (incomingRequest.containsKey("PoLine_udf6Code"))
				{
					udf6Code = (String[]) incomingRequest.get("PoLine_udf6Code");
				}
				if (incomingRequest.containsKey("PoLine_udf7Code"))
				{
					udf7Code = (String[]) incomingRequest.get("PoLine_udf7Code");
				}
				if (incomingRequest.containsKey("PoLine_udf8Code"))
				{
					udf8Code = (String[]) incomingRequest.get("PoLine_udf8Code");
				}
				if (incomingRequest.containsKey("PoLine_udf9Code"))
				{
					udf9Code = (String[]) incomingRequest.get("PoLine_udf9Code");
				}
				if (incomingRequest.containsKey("PoLine_udf10Code"))
				{
					udf10Code = (String[]) incomingRequest.get("PoLine_udf10Code");
				}
				if (incomingRequest.containsKey("PoLine_memoLine"))
				{
					memoLine = (String[]) incomingRequest.get("PoLine_memoLine");
				}
				if (incomingRequest.containsKey("PoLine_mfgName"))
				{
					mfgName = (String[]) incomingRequest.get("PoLine_mfgName");
				}
				if (incomingRequest.containsKey("PoLine_lineRevNo"))
				{
					lineRevNo = (String[]) incomingRequest.get("PoLine_lineRevNo");
				}
				if (incomingRequest.containsKey("PoLine_actionInd"))
				{
					actionInd = (String[]) incomingRequest.get("PoLine_actionInd");
				}
				if (incomingRequest.containsKey("PoLine_icRelKey"))
				{
					icRelKeyString = (String[])incomingRequest.get("PoLine_icRelKey");
				}
				if (incomingRequest.containsKey("PoLine_chgPromCnt"))
				{
					chgPromCntString = (String[])incomingRequest.get("PoLine_chgPromCnt");
				}
				if (incomingRequest.containsKey("PoLine_chgPromDate"))
				{
					chgPromDateString = (String[])incomingRequest.get("PoLine_chgPromDate");
				}
				if (incomingRequest.containsKey("PoLine_poPromised"))
				{
					poPromisedString = (String[])incomingRequest.get("PoLine_poPromised");
				}
				if (incomingRequest.containsKey("PoLine_roFlag"))
				{
					roFlag = (String[]) incomingRequest.get("PoLine_roFlag");
				}
				if (incomingRequest.containsKey("PoLine_icLineHistory"))
				{
					icLineHistoryString = (String[])incomingRequest.get("PoLine_icLineHistory");
				}
				if (incomingRequest.containsKey("PoLine_itemLocation"))
				{
					itemLocation = (String[]) incomingRequest.get("PoLine_itemLocation");
				}
				if (incomingRequest.containsKey("PoLine_description"))
				{
					description = (String[]) incomingRequest.get("PoLine_description");
				}
				if (incomingRequest.containsKey("PoLine_routing"))
				{
					routing = (String[]) incomingRequest.get("PoLine_routing");
				}
				if (incomingRequest.containsKey("PoLine_taxCode"))
				{
					taxCode = (String[]) incomingRequest.get("PoLine_taxCode");
				}
				if (incomingRequest.containsKey("PoLine_qtyReceived"))
				{
					qtyReceivedString = (String[]) incomingRequest.get("PoLine_qtyReceived");
				}
				if (incomingRequest.containsKey("PoLine_requisitionerCode"))
				{
					requisitionerCode = (String[]) incomingRequest.get("PoLine_requisitionerCode");
				}
				if (incomingRequest.containsKey("PoLine_icAccount"))
				{
					icAccountString = (String[])incomingRequest.get("PoLine_icAccount");
				}
	            if (incomingRequest.containsKey("PoLine_departmentCode"))
	            {
	                 departmentCode = (String[] ) incomingRequest.get("PoLine_departmentCode");
	            }
	            if (incomingRequest.containsKey("PoLine_requisitionNumber"))
	            {
	                requisitionNumber = (String[] ) incomingRequest.get("PoLine_requisitionNumber");
	            }
	            if (incomingRequest.containsKey("PoLine_dateEntered"))
	            {
	                dateEntered = (String[]) incomingRequest.get("PoLine_dateEntered");
	            }
	            if (incomingRequest.containsKey("PoLine_inspectionReqd"))
	            {
	                inspectionReqd = (String[]) incomingRequest.get("PoLine_inspectionReqd");
	            }


				for (int i = 0; i < icPoLineString.length; i++)
				{
					PoLine poLine = (PoLine)poLines.get(i);
					if (Utility.isEmpty(icPoHeaderString[i]))
					{
						icPoHeaderString[i] = "0";
					}
					BigDecimal icPoHeader = new BigDecimal(icPoHeaderString[i]);
					poLine.setIcPoHeader(icPoHeader);

					if (icPoLineString != null) {
						if (Utility.isEmpty(icPoLineString[i]))
						{
							icPoLineString[i] = "0";
						}
						BigDecimal icPoLine = new BigDecimal ( icPoLineString[i] );
						poLine.setIcPoLine(icPoLine);
					}
					if (lineNumberString != null) {
						if (Utility.isEmpty(lineNumberString[i]))
						{
							lineNumberString[i] = "0";
						}
						BigDecimal lineNumber = new BigDecimal ( lineNumberString[i] );
						poLine.setLineNumber(lineNumber);
					}
					if (poNumber != null) {
					    poLine.setPoNumber(poNumber[i]);
					}
					if (releaseNumberString != null) {
						if (Utility.isEmpty(releaseNumberString[i]))
						{
							releaseNumberString[i] = "0";
						}
						BigDecimal releaseNumber = new BigDecimal ( releaseNumberString[i] );
						poLine.setReleaseNumber(releaseNumber);
					}
					if (itemNumberString != null) {
					    poLine.setItemNumber(itemNumberString[i]);
					}
					if (itemSource != null) {
					    poLine.setItemSource(itemSource[i]);
					}
					if (umCode != null) {
					    poLine.setUmCode(umCode[i]);
					}
					if (quantityString != null) {
					    if (Utility.isEmpty(quantityString[i]))
						{
							quantityString[i] = "0";
						}
						BigDecimal quantity = new BigDecimal ( quantityString[i] );
						poLine.setQuantity(quantity);
					}
					if (unitPriceString != null) {
						if (Utility.isEmpty(unitPriceString[i]))
						{
							unitPriceString[i] = "0";
						}
						BigDecimal unitPrice = new BigDecimal ( unitPriceString[i] );
						poLine.setUnitPrice(unitPrice);
					}
					if (commodity != null) {
						poLine.setCommodity(commodity[i]);
					}
					if (taxable != null) {
						poLine.setTaxable(taxable[i]);
					}
					if (taxPercentString != null) {
						if (Utility.isEmpty(taxPercentString[i]))
						{
							taxPercentString[i] = "0";
						}
						BigDecimal taxPercent = new BigDecimal ( taxPercentString[i] );
						poLine.setTaxPercent(taxPercent);
					}
					if (taxAmountString != null) {
						if (Utility.isEmpty(taxAmountString[i]))
						{
							taxAmountString[i] = "0";
						}
						BigDecimal taxAmount = new BigDecimal ( taxAmountString[i] );
						poLine.setTaxAmount(taxAmount);
					}
					if (discountSource != null) {
						poLine.setDiscountSource(discountSource[i]);
					}
					if (discountPercentString != null) {
						if (Utility.isEmpty(discountPercentString[i]))
						{
							discountPercentString[i] = "0";
						}
						BigDecimal discountPercent = new BigDecimal ( discountPercentString[i] );
						poLine.setDiscountPercent(discountPercent);
					}
					if (discountAmountString != null) {
						if (Utility.isEmpty(discountAmountString[i]))
						{
							discountAmountString[i] = "0";
						}
						BigDecimal discountAmount = new BigDecimal ( discountAmountString[i] );
						poLine.setDiscountAmount(discountAmount);
					}
					if (shippingChargesString != null) {
						if (Utility.isEmpty(shippingChargesString[i]))
						{
							shippingChargesString[i] = "0";
						}
						BigDecimal shippingCharges = new BigDecimal ( shippingChargesString[i] );
						poLine.setShippingCharges(shippingCharges);
					}
					if (shippingTaxString != null) {
						if (Utility.isEmpty(shippingTaxString[i]))
						{
							shippingTaxString[i] = "0";
						}
						BigDecimal shippingTax = new BigDecimal ( shippingTaxString[i] );
						poLine.setShippingTax(shippingTax);
					}
					if (otherChargesString != null) {
						if (Utility.isEmpty(otherChargesString[i]))
						{
							otherChargesString[i] = "0";
						}
						BigDecimal otherCharges = new BigDecimal ( otherChargesString[i] );
						poLine.setOtherCharges(otherCharges);
					}
					if (otherDescription != null) {
						poLine.setOtherDescription(otherDescription[i]);
					}
					if (otherTaxable != null) {
						poLine.setOtherTaxable(otherTaxable[i]);
					}
					if (otherTaxString != null) {
						if (Utility.isEmpty(otherTaxString[i]))
						{
							otherTaxString[i] = "0";
						}
						BigDecimal otherTax = new BigDecimal ( otherTaxString[i] );
						poLine.setOtherTax(otherTax);
					}
					if (icReqLineString != null) {
						if (Utility.isEmpty(icReqLineString[i]))
						{
							icReqLineString[i] = "0";
						}
						BigDecimal icReqLine = new BigDecimal ( icReqLineString[i] );
						poLine.setIcReqLine(icReqLine);
					}
					if (asset != null) {
						poLine.setAsset(asset[i]);
					}
					if (splits != null) {
						poLine.setSplits(splits[i]);
					}
					if (commentFlag != null) {
						poLine.setCommentFlag(commentFlag[i]);
					}
					if (status != null) {
						poLine.setStatus(status[i]);
					}
					if (icRfqLineString != null) {
						if (Utility.isEmpty(icRfqLineString[i]))
						{
							icRfqLineString[i] = "0";
						}
						BigDecimal icRfqLine = new BigDecimal ( icRfqLineString[i] );
						poLine.setIcRfqLine(icRfqLine);
					}
					if (umFactorString != null) {
						if (Utility.isEmpty(umFactorString[i]))
						{
							umFactorString[i] = "1";
						}
						BigDecimal umFactor = new BigDecimal ( umFactorString[i] );
						poLine.setUmFactor(umFactor);
					}
					if (catalogId != null) {
						poLine.setCatalogId(catalogId[i]);
					}
					if (lineTotalString != null) {
						if (Utility.isEmpty(lineTotalString[i]))
						{
							lineTotalString[i] = "0";
						}
						BigDecimal lineTotal = new BigDecimal ( lineTotalString[i] );
						poLine.setLineTotal(lineTotal);
					}
					if (taxOvr != null) {
						poLine.setTaxOvr(taxOvr[i]);
					}
					if (discOvr != null) {
						poLine.setDiscOvr(discOvr[i]);
					}
					if (shipOvr != null) {
						poLine.setShipOvr(shipOvr[i]);
					}
					if (otherOvr != null) {
						poLine.setOtherOvr(otherOvr[i]);
					}
					if (shiptoFlag != null) {
						poLine.setShiptoFlag(shiptoFlag[i]);
					}
					if (shippingTaxable != null) {
						poLine.setShippingTaxable(shippingTaxable[i]);
					}
					if (receiptRequired != null) {
						poLine.setReceiptRequired(receiptRequired[i]);
					}
					if (icLineKeyString != null) {
						if (Utility.isEmpty(icLineKeyString[i]))
						{
							icLineKeyString[i] = "0";
						}
						BigDecimal icLineKey = new BigDecimal ( icLineKeyString[i] );
						poLine.setIcLineKey(icLineKey);
					}
					if (icAccountString != null) {
						if (Utility.isEmpty(icAccountString[i]))
						{
							icAccountString[i] = "0";
						}
						BigDecimal icAccount = new BigDecimal ( icAccountString[i] );
						poLine.setIcAccount(icAccount);
					}

					if (modelNumber != null) {
						poLine.setModelNumber(modelNumber[i]);
					}
					if (udf1Code != null) {
						poLine.setUdf1Code(udf1Code[i]);
					}
					if (udf2Code != null) {
						poLine.setUdf2Code(udf2Code[i]);
					}
					if (udf3Code != null) {
						poLine.setUdf3Code(udf3Code[i]);
					}
					if (udf4Code != null) {
						poLine.setUdf4Code(udf4Code[i]);
					}
					if (udf5Code != null) {
						poLine.setUdf5Code(udf5Code[i]);
					}
					if (udf6Code != null) {
						poLine.setUdf6Code(udf6Code[i]);
					}
					if (udf7Code != null) {
						poLine.setUdf7Code(udf7Code[i]);
					}
					if (udf8Code != null) {
						poLine.setUdf8Code(udf8Code[i]);
					}
					if (udf9Code != null) {
						poLine.setUdf9Code(udf9Code[i]);
					}
					if (udf10Code != null) {
						poLine.setUdf10Code(udf10Code[i]);
					}
					if (memoLine != null) {
						poLine.setMemoLine(memoLine[i]);
					}
					if (mfgName != null) {
						poLine.setMfgName(mfgName[i]);
					}
					if (lineRevNo != null) {
						poLine.setLineRevNo(lineRevNo[i]);
					}
					if (actionInd != null) {
						poLine.setActionInd(actionInd[i]);
					}
					if (icRelKeyString != null) {
						if (Utility.isEmpty(icRelKeyString[i]))
						{
							icRelKeyString[i] = "0";
						}
						BigDecimal icRelKey = new BigDecimal ( icRelKeyString[i] );
						poLine.setIcRelKey(icRelKey);
					}
					if (chgPromCntString != null) {
						if (Utility.isEmpty(chgPromCntString[i]))
						{
							chgPromCntString[i] = "0";
						}
						BigDecimal chgPromCnt = new BigDecimal ( chgPromCntString[i] );
						poLine.setChgPromCnt(chgPromCnt);
					}
					if (chgPromDateString != null) {
						Date chgPromDate = Dates.getDate(chgPromDateString[i]);
						poLine.setChgPromDate(chgPromDate);
					}
					if (poPromisedString != null) {
						Date poPromised = Dates.getDate(poPromisedString[i]);
						poLine.setPoPromised(poPromised);
					}
					if (roFlag != null) {
						poLine.setRoFlag(roFlag[i]);
					}
					if (icLineHistoryString != null) {
						if (Utility.isEmpty(icLineHistoryString[i]))
						{
							icLineHistoryString[i] = "0";
						}
						BigDecimal icLineHistory = new BigDecimal ( icLineHistoryString[i] );
						poLine.setIcLineHistory(icLineHistory);
					}
					if (itemLocation != null) {
						poLine.setItemLocation(itemLocation[i]);
					}
					if (description != null) {
						poLine.setDescription(description[i]);
					}
					if (routing != null) {
						poLine.setRouting(routing[i]);
					}
					if (taxCode != null) {
						poLine.setTaxCode(taxCode[i]);
					}
					if (qtyReceivedString != null) {
						if (Utility.isEmpty(qtyReceivedString[i]))
						{
							qtyReceivedString[i] = "0";
						}
						BigDecimal qtyReceived = new BigDecimal ( quantityString[i] );
						poLine.setQtyReceived(qtyReceived);
					}
					if (requisitionerCode != null) {
						poLine.setRequisitionerCode(requisitionerCode[i]);
					}
					if (departmentCode != null) {
						poLine.setDepartmentCode(departmentCode[i]);
					}
					if (requisitionNumber != null) {
						poLine.setRequisitionNumber(requisitionNumber[i]);
					}
					if (dateEntered != null) {
						if (Utility.isEmpty(dateEntered[i]))
						{
							dateEntered[i] = Dates.today("", userTimeZone) ;
						}
		                Date dateEnt = Dates.getDate(dateEntered[i]);
		                poLine.setDateEntered(dateEnt);
					}
					if (inspectionReqd != null) {
						poLine.setInspectionReqd(inspectionReqd[i]);
					}
				}
			}
			else if (poLines != null && poLines.size() == 1)
			{
				PoLine poLine = (PoLine) poLines.get(0);

				incomingRequest.put("poLine", poLine);

				PoLineSetValues setValuesTask = new PoLineSetValues();
				poLine = (PoLine) setValuesTask.executeTask(incomingRequest);

				poLines.set(0, poLine);
			}

			result = poLines;
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