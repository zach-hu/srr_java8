package com.tsa.puridiom.requisitionline.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.*;

public class RequisitionLineSetValuesForList extends Task {

	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map) object;
		Object result = null;

		try {
			Object icReqLineObj = incomingRequest.get("RequisitionLine_icReqLine");
			List requisitionLineList = (List) incomingRequest.get("requisitionLineList");

			if (icReqLineObj instanceof String[]) {
				String icReqLineString[] = (String[]) icReqLineObj;

				if (requisitionLineList == null || requisitionLineList.size() != icReqLineString.length) {
					throw new Exception("The number of requisition line list records does not match the number of records in the request.");
				}

				String lineNumberString[] = null, icReqHeaderString[] = null, requisitionNumber[] = null, itemNumber[] = null;
				String itemSource[] = null, umCode[] = null, quantityString[] = null, unitPriceString[] = null;
				String commodityCode[] = null, taxable[] = null, asset[] = null, discountSource[] = null, discountPercentString[] = null;
				String discountAmountString[] = null, shippingChargesString[] = null, taxShipping[] = null, otherChargesString[] = null;
				String otherDescription[] = null, taxOther[] = null, icPoLineString[] = null, splits[] = null, status[] = null, commentFlag[] = null;
				String taxPercentString[] = null, taxAmountString[] = null, shippingTaxAmtString[] = null, otherTaxAmountString[] = null;
				String useTaxPercentString[] = null, useTaxAmountString[] = null, useTaxCode[] = null, useTaxable[] = null;
				String reqType[] = null, catalogId[] = null, umFactorString[] = null, lineTotalString[] = null;
				String taxOvr[] = null, discOvr[] = null, shipOvr[] = null, otherOvr[] = null;
				String shiptoFlag[] = null, autoReleaseString[] = null, lastQtyEnteredString[] = null, assignedBuyer[] = null, assignedDateString[] = null;
				String allocatedString[] = null, backorderedString[] = null, mfgName[] = null, modelNumber[] = null;
				String udf1Code[] = null, udf2Code[] = null, udf3Code[] = null, udf4Code[] = null, udf5Code[] = null;
				String rqLineKey[] = null, receiptRequired[] = null, roFlag[] = null, routing[] = null, itemLocation[] = null;
				String description[] = null, icLineHistoryString[] = null, taxCode[] = null;
				String icAccountString[] = null ;
				String udf6Code[] = null, udf7Code[] = null, udf8Code[] = null, udf9Code[] = null, udf10Code[] = null,memoLine[] = null ;

				if (incomingRequest.containsKey("RequisitionLine_lineNumber")) {
					 lineNumberString = (String[]) incomingRequest.get("RequisitionLine_lineNumber");
				}
				if (incomingRequest.containsKey("RequisitionLine_icReqHeader")) {
					Object icReqHeaderObj = incomingRequest.get("RequisitionLine_icReqHeader");
					if (icReqHeaderObj instanceof String[]) {
						icReqHeaderString = (String[]) incomingRequest.get("RequisitionLine_icReqHeader");
					} else {
						icReqHeaderString = new String[icReqLineString.length];
						for (int ii = 0; ii < icReqLineString.length; ii++) {
							icReqHeaderString[ii] = (String) icReqHeaderObj;
						}
					}
				}
				if (incomingRequest.containsKey("RequisitionLine_requisitionNumber")) {
					 requisitionNumber = (String[]) incomingRequest.get("RequisitionLine_requisitionNumber");
				}
				if (incomingRequest.containsKey("RequisitionLine_itemNumber")) {
					 itemNumber = (String[]) incomingRequest.get("RequisitionLine_itemNumber");
				}
				if (incomingRequest.containsKey("RequisitionLine_itemSource")) {
					 itemSource = (String[]) incomingRequest.get("RequisitionLine_itemSource");
				}
				if (incomingRequest.containsKey("RequisitionLine_umCode")) {
					 umCode = (String[]) incomingRequest.get("RequisitionLine_umCode");
				}
				if (incomingRequest.containsKey("RequisitionLine_quantity")) {
					 quantityString = (String[]) incomingRequest.get("RequisitionLine_quantity");
				}
				if (incomingRequest.containsKey("RequisitionLine_unitPrice")) {
					 unitPriceString = (String[]) incomingRequest.get("RequisitionLine_unitPrice");
				}
				if (incomingRequest.containsKey("RequisitionLine_commodityCode")) {
					 commodityCode = (String[]) incomingRequest.get("RequisitionLine_commodityCode");
				}
				if (incomingRequest.containsKey("RequisitionLine_taxable")) {
					 taxable = (String[]) incomingRequest.get("RequisitionLine_taxable");
				}
				if (incomingRequest.containsKey("RequisitionLine_asset")) {
					 asset = (String[]) incomingRequest.get("RequisitionLine_asset");
				}
				if (incomingRequest.containsKey("RequisitionLine_discountSource")) {
					 discountSource = (String[]) incomingRequest.get("RequisitionLine_discountSource");
				}
				if (incomingRequest.containsKey("RequisitionLine_discountPercent")) {
					 discountPercentString = (String[]) incomingRequest.get("RequisitionLine_discountPercent");
				}
				if (incomingRequest.containsKey("RequisitionLine_discountAmount")) {
					 discountAmountString = (String[]) incomingRequest.get("RequisitionLine_discountAmount");
				}
				if (incomingRequest.containsKey("RequisitionLine_shippingCharges")) {
					 shippingChargesString = (String[]) incomingRequest.get("RequisitionLine_shippingCharges");
				}
				if (incomingRequest.containsKey("RequisitionLine_taxShipping")) {
					 taxShipping = (String[]) incomingRequest.get("RequisitionLine_taxShipping");
				}
				if (incomingRequest.containsKey("RequisitionLine_otherCharges")) {
					 otherChargesString = (String[]) incomingRequest.get("RequisitionLine_otherCharges");
				}
				if (incomingRequest.containsKey("RequisitionLine_otherDescription")) {
					 otherDescription = (String[]) incomingRequest.get("RequisitionLine_otherDescription");
				}
				if (incomingRequest.containsKey("RequisitionLine_taxOther")) {
					 taxOther = (String[]) incomingRequest.get("RequisitionLine_taxOther");
				}
				if (incomingRequest.containsKey("RequisitionLine_icPoLine")) {
					 icPoLineString = (String[]) incomingRequest.get("RequisitionLine_icPoLine");
				}
				if (incomingRequest.containsKey("RequisitionLine_splits")) {
					 splits = (String[]) incomingRequest.get("RequisitionLine_splits");
				}
				if (incomingRequest.containsKey("RequisitionLine_status")) {
					 status = (String[]) incomingRequest.get("RequisitionLine_status");
				}
				if (incomingRequest.containsKey("RequisitionLine_commentFlag")) {
					 commentFlag = (String[]) incomingRequest.get("RequisitionLine_commentFlag");
				}
				if (incomingRequest.containsKey("RequisitionLine_taxPercent")) {
					 taxPercentString = (String[]) incomingRequest.get("RequisitionLine_taxPercent");
				}
				if (incomingRequest.containsKey("RequisitionLine_taxAmount")) {
					 taxAmountString = (String[]) incomingRequest.get("RequisitionLine_taxAmount");
				}
				if (incomingRequest.containsKey("RequisitionLine_shippingTaxAmt")) {
					 shippingTaxAmtString = (String[]) incomingRequest.get("RequisitionLine_shippingTaxAmt");
				}
				if (incomingRequest.containsKey("RequisitionLine_otherTaxAmount")) {
					 otherTaxAmountString = (String[]) incomingRequest.get("RequisitionLine_otherTaxAmount");
				}
				if (incomingRequest.containsKey("RequisitionLine_reqType")) {
					 reqType = (String[]) incomingRequest.get("RequisitionLine_reqType");
				}
				if (incomingRequest.containsKey("RequisitionLine_catalogId")) {
					 catalogId = (String[]) incomingRequest.get("RequisitionLine_catalogId");
				}
				if (incomingRequest.containsKey("RequisitionLine_umFactor")) {
					 umFactorString = (String[]) incomingRequest.get("RequisitionLine_umFactor");
				}
				if (incomingRequest.containsKey("RequisitionLine_lineTotal")) {
					 lineTotalString = (String[]) incomingRequest.get("RequisitionLine_lineTotal");
				}
				if (incomingRequest.containsKey("RequisitionLine_taxOvr")) {
					 taxOvr = (String[]) incomingRequest.get("RequisitionLine_taxOvr");
				}
				if (incomingRequest.containsKey("RequisitionLine_discOvr")) {
					 discOvr = (String[]) incomingRequest.get("RequisitionLine_discOvr");
				}
				if (incomingRequest.containsKey("RequisitionLine_shipOvr")) {
					 shipOvr = (String[]) incomingRequest.get("RequisitionLine_shipOvr");
				}
				if (incomingRequest.containsKey("RequisitionLine_otherOvr")) {
					 otherOvr = (String[]) incomingRequest.get("RequisitionLine_otherOvr");
				}
				if (incomingRequest.containsKey("RequisitionLine_shiptoFlag")) {
					 shiptoFlag = (String[]) incomingRequest.get("RequisitionLine_shiptoFlag");
				}
				if (incomingRequest.containsKey("RequisitionLine_autoRelease")) {
					 autoReleaseString = (String[]) incomingRequest.get("RequisitionLine_autoRelease");
				}
				if (incomingRequest.containsKey("RequisitionLine_lastQtyEntered")) {
					 lastQtyEnteredString = (String[]) incomingRequest.get("RequisitionLine_lastQtyEntered");
				}
				if (incomingRequest.containsKey("RequisitionLine_assignedBuyer")) {
					 assignedBuyer = (String[]) incomingRequest.get("RequisitionLine_assignedBuyer");
				}
				if (incomingRequest.containsKey("RequisitionLine_assignedDate")) {
					 assignedDateString = (String[]) incomingRequest.get("RequisitionLine_assignedDate");
				}
				if (incomingRequest.containsKey("RequisitionLine_allocated")) {
					 allocatedString = (String[]) incomingRequest.get("RequisitionLine_allocated");
				}
				if (incomingRequest.containsKey("RequisitionLine_backordered")) {
					 backorderedString = (String[]) incomingRequest.get("RequisitionLine_backordered");
				}
				if (incomingRequest.containsKey("RequisitionLine_mfgName")) {
					 mfgName = (String[]) incomingRequest.get("RequisitionLine_mfgName");
				}
				if (incomingRequest.containsKey("RequisitionLine_modelNumber")) {
					 modelNumber = (String[]) incomingRequest.get("RequisitionLine_modelNumber");
				}
				if (incomingRequest.containsKey("RequisitionLine_udf1Code")) {
					 udf1Code = (String[]) incomingRequest.get("RequisitionLine_udf1Code");
				}
				if (incomingRequest.containsKey("RequisitionLine_udf2Code")) {
					 udf2Code = (String[]) incomingRequest.get("RequisitionLine_udf2Code");
				}
				if (incomingRequest.containsKey("RequisitionLine_udf3Code")) {
					 udf3Code = (String[]) incomingRequest.get("RequisitionLine_udf3Code");
				}
				if (incomingRequest.containsKey("RequisitionLine_udf4Code")) {
					 udf4Code = (String[]) incomingRequest.get("RequisitionLine_udf4Code");
				}
				if (incomingRequest.containsKey("RequisitionLine_udf5Code")) {
					 udf5Code = (String[]) incomingRequest.get("RequisitionLine_udf5Code");
				}
				if (incomingRequest.containsKey("RequisitionLine_udf6Code"))
				{
					udf6Code = (String[]) incomingRequest.get("RequisitionLine_udf6Code");
				}
				if (incomingRequest.containsKey("RequisitionLine_udf7Code"))
				{
					udf7Code = (String[]) incomingRequest.get("RequisitionLine_udf7Code");
				}
				if (incomingRequest.containsKey("RequisitionLine_udf8Code"))
				{
					udf8Code = (String[]) incomingRequest.get("RequisitionLine_udf8Code");
				}
				if (incomingRequest.containsKey("RequisitionLine_udf9Code"))
				{
					udf9Code = (String[]) incomingRequest.get("RequisitionLine_udf9Code");
				}
				if (incomingRequest.containsKey("RequisitionLine_udf10Code"))
				{
					udf10Code = (String[]) incomingRequest.get("RequisitionLine_udf10Code");
				}
				if (incomingRequest.containsKey("RequisitionLine_memoLine"))
				{
					memoLine = (String[]) incomingRequest.get("RequisitionLine_memoLine");
				}
				if (incomingRequest.containsKey("RequisitionLine_rqLineKey")) {
					 rqLineKey = (String[]) incomingRequest.get("RequisitionLine_rqLineKey");
				}
				if (incomingRequest.containsKey("RequisitionLine_receiptRequired")) {
					 receiptRequired = (String[]) incomingRequest.get("RequisitionLine_receiptRequired");
				}
				if (incomingRequest.containsKey("RequisitionLine_roFlag")) {
					 roFlag = (String[]) incomingRequest.get("RequisitionLine_roFlag");
				}
				if (incomingRequest.containsKey("RequisitionLine_routing")) {
					 routing = (String[]) incomingRequest.get("RequisitionLine_routing");
				}
				if (incomingRequest.containsKey("RequisitionLine_itemLocation")) {
					 itemLocation = (String[]) incomingRequest.get("RequisitionLine_itemLocation");
				}
				if (incomingRequest.containsKey("RequisitionLine_description")) {
					 description = (String[]) incomingRequest.get("RequisitionLine_description");
				}
				if (incomingRequest.containsKey("RequisitionLine_icLineHistory")) {
					 icLineHistoryString = (String[]) incomingRequest.get("RequisitionLine_icLineHistory");
				}
				if (incomingRequest.containsKey("RequisitionLine_taxCode")) {
					 taxCode = (String[]) incomingRequest.get("RequisitionLine_taxCode");
				}
				if (incomingRequest.containsKey("RequisitionLine_icAccount")) {
					 icAccountString = (String[]) incomingRequest.get("RequisitionLine_icAccount");
				}
				if (incomingRequest.containsKey("RequisitionLine_useTaxPercent")) {
					useTaxPercentString = (String[]) incomingRequest.get("RequisitionLine_useTaxPercent");
				}
				if (incomingRequest.containsKey("RequisitionLine_useTaxAmount")) {
					useTaxAmountString = (String[]) incomingRequest.get("RequisitionLine_useTaxAmount");
				}
				if (incomingRequest.containsKey("RequisitionLine_useTaxCode")) {
					 useTaxCode = (String[]) incomingRequest.get("RequisitionLine_useTaxCode");
				}
				if (incomingRequest.containsKey("RequisitionLine_useTaxable")) {
					 useTaxable = (String[]) incomingRequest.get("RequisitionLine_useTaxable");
				}

				for (int i = 0; i < icReqLineString.length; i++) {
					RequisitionLine requisitionLine = (RequisitionLine) requisitionLineList.get(i);

					if (requisitionLine == null) {
						requisitionLine = new RequisitionLine();
					}

					if (icReqLineString != null) {
						if (Utility.isEmpty(icReqLineString[i])) {
							icReqLineString[i] = "0";
						}
						BigDecimal icReqLine = new BigDecimal(icReqLineString[i]);
						requisitionLine.setIcReqLine(icReqLine);
					}
					if (lineNumberString != null) {
						if (Utility.isEmpty(lineNumberString[i])) {
							lineNumberString[i] = "0";
						}
						BigDecimal lineNumber = new BigDecimal(lineNumberString[i]);
						requisitionLine.setLineNumber(lineNumber);
					}
					if (icReqHeaderString != null) {
						if (Utility.isEmpty(icReqHeaderString[i])) {
							icReqHeaderString[i] = "0";
						}
						BigDecimal icReqHeader = new BigDecimal(icReqHeaderString[i]);
						requisitionLine.setIcReqHeader(icReqHeader);
					}
					if (requisitionNumber != null) {
						requisitionLine.setRequisitionNumber(requisitionNumber[i]);
					}
					if (itemNumber != null) {
						requisitionLine.setItemNumber(itemNumber[i]);
					}
					if (itemSource != null) {
						requisitionLine.setItemSource(itemSource[i]);
					}
					if (umCode != null) {
						requisitionLine.setUmCode(umCode[i]);
					}
					if (quantityString != null) {
						if (Utility.isEmpty(quantityString[i])) {
							quantityString[i] = "0";
						}
						BigDecimal quantity = new BigDecimal(quantityString[i]);
						requisitionLine.setQuantity(quantity);
					}
					if (unitPriceString != null) {
						if (Utility.isEmpty(unitPriceString[i])) {
							unitPriceString[i] = "0";
						}
						BigDecimal unitPrice = new BigDecimal(unitPriceString[i]);
						requisitionLine.setUnitPrice(unitPrice);
					}
					if (commodityCode != null) {
						requisitionLine.setCommodityCode(commodityCode[i]);
					}
					if (taxable != null) {
						requisitionLine.setTaxable(taxable[i]);
					}
					if (asset != null) {
						requisitionLine.setAsset(asset[i]);
					}
					if (discountSource != null) {
						requisitionLine.setDiscountSource(discountSource[i]);
					}
					if (discountPercentString != null) {
						if (Utility.isEmpty(discountPercentString[i])) {
							discountPercentString[i] = "0";
						}
						BigDecimal discountPercent = new BigDecimal(discountPercentString[i]);
						requisitionLine.setDiscountPercent(discountPercent);
					}
					if (discountAmountString != null) {
						if (Utility.isEmpty(discountAmountString[i])) {
							discountAmountString[i] = "0";
						}
						BigDecimal discountAmount = new BigDecimal(discountAmountString[i]);
						requisitionLine.setDiscountAmount(discountAmount);
					}
					if (shippingChargesString != null) {
						if (Utility.isEmpty(shippingChargesString[i])) {
							shippingChargesString[i] = "0";
						}
						BigDecimal shippingCharges = new BigDecimal(shippingChargesString[i]);
						requisitionLine.setShippingCharges(shippingCharges);
					}
					if (taxShipping != null) {
						requisitionLine.setTaxShipping(taxShipping[i]);
					}
					if (otherChargesString != null) {
						if (Utility.isEmpty(otherChargesString[i])) {
							otherChargesString[i] = "0";
						}
						BigDecimal otherCharges = new BigDecimal(otherChargesString[i]);
						requisitionLine.setOtherCharges(otherCharges);
					}
					if (otherDescription != null) {
						requisitionLine.setOtherDescription(otherDescription[i]);
					}
					if (taxOther != null) {
						requisitionLine.setTaxOther(taxOther[i]);
					}
					if (icPoLineString != null) {
						if (Utility.isEmpty(icPoLineString[i])) {
							icPoLineString[i] = "0";
						}
						BigDecimal icPoLine = new BigDecimal(icPoLineString[i]);
						requisitionLine.setIcPoLine(icPoLine);
					}
					if (splits != null) {
						requisitionLine.setSplits(splits[i]);
					}
					if (status != null) {
						requisitionLine.setStatus(status[i]);
					}
					if (commentFlag != null) {
						requisitionLine.setCommentFlag(commentFlag[i]);
					}
					if (taxPercentString != null) {
						if (Utility.isEmpty(taxPercentString[i])) {
							taxPercentString[i] = "0";
						}
						BigDecimal taxPercent = new BigDecimal(taxPercentString[i]);
						requisitionLine.setTaxPercent(taxPercent);
					}
					if (taxAmountString != null) {
						if (Utility.isEmpty(taxAmountString[i])) {
							taxAmountString[i] = "0";
						}
						BigDecimal taxAmount = new BigDecimal(taxAmountString[i]);
						requisitionLine.setTaxAmount(taxAmount);
					}
					if (shippingTaxAmtString != null) {
						if (Utility.isEmpty(shippingTaxAmtString[i])) {
							shippingTaxAmtString[i] = "0";
						}
						BigDecimal shippingTaxAmt = new BigDecimal(shippingTaxAmtString[i]);
						requisitionLine.setShippingTaxAmt(shippingTaxAmt);
					}
					if (otherTaxAmountString != null) {
						if (Utility.isEmpty(otherTaxAmountString[i])) {
							otherTaxAmountString[i] = "0";
						}
						BigDecimal otherTaxAmount = new BigDecimal(otherTaxAmountString[i]);
						requisitionLine.setOtherTaxAmount(otherTaxAmount);
					}
					if (reqType != null) {
						requisitionLine.setReqType(reqType[i]);
					}
					if (catalogId != null) {
						requisitionLine.setCatalogId(catalogId[i]);
					}
					if (umFactorString != null) {
						if (Utility.isEmpty(umFactorString[i])) {
							umFactorString[i] = "1";
						}
						try {
							BigDecimal umFactor = new BigDecimal(umFactorString[i]);
							requisitionLine.setUmFactor(umFactor);
						} catch (Exception e) {
							//do not set umFactor if there is an exception
						}
					}
					if (lineTotalString != null) {
						if (Utility.isEmpty(lineTotalString[i])) {
							lineTotalString[i] = "0";
						}
						BigDecimal lineTotal = new BigDecimal(lineTotalString[i]);
						requisitionLine.setLineTotal(lineTotal);
					}
					if (taxOvr != null) {
						requisitionLine.setTaxOvr(taxOvr[i]);
					}
					if (discOvr != null) {
						requisitionLine.setDiscOvr(discOvr[i]);
					}
					if (shipOvr != null) {
						requisitionLine.setShipOvr(shipOvr[i]);
					}
					if (otherOvr != null) {
						requisitionLine.setOtherOvr(otherOvr[i]);
					}
					if (shiptoFlag != null) {
						requisitionLine.setShiptoFlag(shiptoFlag[i]);
					}
					if (autoReleaseString != null) {
						if (Utility.isEmpty(autoReleaseString[i])) {
							autoReleaseString[i] = "0";
						}
						BigDecimal autoRelease = new BigDecimal(autoReleaseString[i]);
						requisitionLine.setAutoRelease(autoRelease);
					}
					if (lastQtyEnteredString != null) {
						if (Utility.isEmpty(lastQtyEnteredString[i])) {
							lastQtyEnteredString[i] = "0";
						}
						BigDecimal lastQtyEntered = new BigDecimal(lastQtyEnteredString[i]);
						requisitionLine.setLastQtyEntered(lastQtyEntered);
					}
					if (assignedBuyer != null) {
						requisitionLine.setAssignedBuyer(assignedBuyer[i]);
					}
					if (assignedDateString != null) {
						Date assignedDate = Dates.getDate(assignedDateString[i]);
						requisitionLine.setAssignedDate(assignedDate);
					}
					if (allocatedString != null) {
						if (Utility.isEmpty(allocatedString[i])) {
							allocatedString[i] = "0";
						}
						BigDecimal allocated = new BigDecimal(allocatedString[i]);
						requisitionLine.setAllocated(allocated);
					}
					if (backorderedString != null) {
						if (Utility.isEmpty(backorderedString[i])) {
							backorderedString[i] = "0";
						}
						BigDecimal backordered = new BigDecimal(backorderedString[i]);
						requisitionLine.setBackordered(backordered);
					}
					if (mfgName != null) {
						requisitionLine.setMfgName(mfgName[i]);
					}
					if (modelNumber != null) {
						requisitionLine.setModelNumber(modelNumber[i]);
					}
					if (udf1Code != null) {
						requisitionLine.setUdf1Code(udf1Code[i]);
					}
					if (udf2Code != null) {
						requisitionLine.setUdf2Code(udf2Code[i]);
					}
					if (udf3Code != null) {
						requisitionLine.setUdf3Code(udf3Code[i]);
					}
					if (udf4Code != null) {
						requisitionLine.setUdf4Code(udf4Code[i]);
					}
					if (udf5Code != null) {
						requisitionLine.setUdf5Code(udf5Code[i]);
					}
					if (udf6Code != null) {
						requisitionLine.setUdf6Code(udf6Code[i]);
					}
					if (udf7Code != null) {
						requisitionLine.setUdf7Code(udf7Code[i]);
					}
					if (udf8Code != null) {
						requisitionLine.setUdf8Code(udf8Code[i]);
					}
					if (udf9Code != null) {
						requisitionLine.setUdf9Code(udf9Code[i]);
					}
					if (udf10Code != null) {
						requisitionLine.setUdf10Code(udf10Code[i]);
					}
					if (memoLine != null) {
						requisitionLine.setMemoLine(memoLine[i]);
					}

					if (rqLineKey != null) {
						requisitionLine.setRqLineKey(rqLineKey[i]);
					}
					if (receiptRequired != null) {
						requisitionLine.setReceiptRequired(receiptRequired[i]);
					}
					if (roFlag != null) {
						requisitionLine.setRoFlag(roFlag[i]);
					}
					if (routing != null) {
						requisitionLine.setRouting(routing[i]);
					}
					if (itemLocation != null) {
						requisitionLine.setItemLocation(itemLocation[i]);
					}
					if (description != null) {
						requisitionLine.setDescription(description[i]);
					}
					if (icLineHistoryString != null) {
						if (Utility.isEmpty(icLineHistoryString[i])) {
							icLineHistoryString[i] = "0";
						}
						BigDecimal icLineHistory = new BigDecimal(icLineHistoryString[i]);
						requisitionLine.setIcLineHistory(icLineHistory);
					}
					if (taxCode != null) {
						requisitionLine.setTaxCode(taxCode[i]);
					}
					if (useTaxCode != null) {
						requisitionLine.setUseTaxCode(useTaxCode[i]);
					}
					if (useTaxPercentString != null) {
						if (Utility.isEmpty(useTaxPercentString[i])) {
							useTaxPercentString[i] = "0";
						}
						BigDecimal taxPercent = new BigDecimal(useTaxPercentString[i]);
						requisitionLine.setUseTaxPercent(taxPercent);
					}
					if (useTaxAmountString != null) {
						if (Utility.isEmpty(useTaxAmountString[i])) {
							useTaxAmountString[i] = "0";
						}
						BigDecimal useTaxAmount = new BigDecimal(useTaxAmountString[i]);
						requisitionLine.setUseTaxAmount(useTaxAmount);
					}

					if (icAccountString != null) {
						if (Utility.isEmpty(icAccountString[i])) {
							icAccountString[i] = "0";
						}
						BigDecimal icAccount = new BigDecimal(icAccountString[i]);
						requisitionLine.setIcLineHistory(icAccount);
					}
					if (useTaxable != null) {
						requisitionLine.setUseTaxable(useTaxable[i]);
					}

					requisitionLineList.set(i, requisitionLine);
				}
			}
			else if (requisitionLineList != null && requisitionLineList.size() == 1){
				RequisitionLine requisitionLine = (RequisitionLine) requisitionLineList.get(0);

				incomingRequest.put("requisitionLine", requisitionLine);

				RequisitionLineSetValues setValuesTask = new RequisitionLineSetValues();
				requisitionLine = (RequisitionLine) setValuesTask.executeTask(incomingRequest);

				requisitionLineList.set(0, requisitionLine);
			}

			result = requisitionLineList;
			this.status = Status.SUCCEEDED;
		} catch (Exception e) {
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}