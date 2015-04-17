package com.tsa.puridiom.poline.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.documents.OrderType;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import com.tsagate.foundation.utility.Utility;
import java.math.BigDecimal;
import java.util.*;

public class PoLineSaveas extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain requisitionLine */
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
            String  createFromContract = (String) incomingRequest.get("createFromContract");
            String  userTimeZone = (String) incomingRequest.get("userTimeZone");
			PoLine	originalPoLine = (PoLine) incomingRequest.get("poLine");
			PoLine	poLine = new PoLine();

			poLine.setAsset(originalPoLine.getAsset());
			poLine.setCatalogId(originalPoLine.getCatalogId());
			poLine.setCommentFlag(originalPoLine.getCommentFlag());
			poLine.setCommodity(originalPoLine.getCommodity());
			poLine.setDescription(originalPoLine.getDescription());
			poLine.setDiscountAmount(originalPoLine.getDiscountAmount());
			poLine.setDiscountPercent(originalPoLine.getDiscountPercent());
			poLine.setDiscountSource(originalPoLine.getDiscountSource());
			poLine.setDiscOvr(originalPoLine.getDiscOvr());
			poLine.setIcLineHistory(new BigDecimal(ukg.getUniqueKey().toString()));
			//poLine.setIcLineKey(originalPoLine.getIcLineKey());
			poLine.setIcPoHeader(new BigDecimal((String) incomingRequest.get("newPoLine_icPoHeader")));
			//poLine.setIcPoLine(new BigDecimal(ukg.getUniqueKey().toString()));
			BigDecimal icPoLine = new BigDecimal(ukg.getUniqueKey().toString());
			poLine.setIcPoLine(icPoLine);
			poLine.setIcLineKey(icPoLine);	//set icLineKey to the original icPoLine IF creating a revision (see below)
			poLine.setIcRelKey(icPoLine);	//set icRelKey to the original icPoLine IF creating a release (see below)

			BigDecimal orgIcAccount = originalPoLine.getIcAccount() ;
			if (orgIcAccount == null) orgIcAccount = new BigDecimal("0") ;
			if (orgIcAccount.compareTo(new BigDecimal("0")) > 0) {
				poLine.setIcAccount(poLine.getIcPoLine()) ;
			} else {
				poLine.setIcAccount(new BigDecimal("0")) ;
			}

			poLine.setItemLocation(originalPoLine.getItemLocation());
			poLine.setItemNumber(originalPoLine.getItemNumber());
			poLine.setItemSource(originalPoLine.getItemSource());
			poLine.setInspectionReqd(originalPoLine.getInspectionReqd());
			poLine.setLineNumber(originalPoLine.getLineNumber());
			poLine.setLineTotal(originalPoLine.getLineTotal());
			poLine.setMfgName(originalPoLine.getMfgName());
			poLine.setModelNumber(originalPoLine.getModelNumber());
			poLine.setOtherCharges(originalPoLine.getOtherCharges());
			poLine.setOtherDescription(originalPoLine.getOtherDescription());
			poLine.setOtherOvr(originalPoLine.getOtherOvr());
			poLine.setOtherTax(originalPoLine.getOtherTax());
			poLine.setOtherTaxable(originalPoLine.getOtherTaxable());
			poLine.setPoNumber((String) incomingRequest.get("newPoLine_poNumber"));
			poLine.setQtyReceived(new BigDecimal(0));
			poLine.setDuomQtyReceived(new BigDecimal(0));
			poLine.setQuantity(originalPoLine.getQuantity());
			poLine.setReceiptRequired(originalPoLine.getReceiptRequired());
			poLine.setRequisitionerCode(originalPoLine.getRequisitionerCode());
			poLine.setRoFlag(originalPoLine.getRoFlag());
			poLine.setRouting(originalPoLine.getRouting());
			poLine.setShipOvr(originalPoLine.getShipOvr());
			poLine.setShippingCharges(originalPoLine.getShippingCharges());
			poLine.setShippingTax(originalPoLine.getShippingTax());
			poLine.setShippingTaxable(originalPoLine.getShippingTaxable());
			poLine.setShiptoFlag(originalPoLine.getShiptoFlag());
			poLine.setSplits(originalPoLine.getSplits());
            if (!Utility.ckNull(createFromContract).equals("Y") && originalPoLine.getStatus().compareTo(DocumentStatus.CT_INPROGRESS) >= 0 && originalPoLine.getStatus().compareTo(DocumentStatus.CT_AWARDED) <= 0) {
                poLine.setStatus(DocumentStatus.CT_INPROGRESS);
            } else {
                poLine.setStatus(DocumentStatus.PO_INPROGRESS);
            }
			poLine.setTaxable(originalPoLine.getTaxable());
			poLine.setTaxAmount(originalPoLine.getTaxAmount());
			poLine.setTaxCode(originalPoLine.getTaxCode());
			poLine.setUseTaxable(originalPoLine.getUseTaxable());
			poLine.setUseTaxAmount(originalPoLine.getUseTaxAmount());
			poLine.setUseTaxCode(originalPoLine.getUseTaxCode());
			poLine.setTaxOvr(originalPoLine.getTaxOvr());
			poLine.setTaxPercent(originalPoLine.getTaxPercent());
			poLine.setUdf1Code(originalPoLine.getUdf1Code());
			poLine.setUdf2Code(originalPoLine.getUdf2Code());
			poLine.setUdf3Code(originalPoLine.getUdf3Code());
			poLine.setUdf4Code(originalPoLine.getUdf4Code());
			poLine.setUdf5Code(originalPoLine.getUdf5Code());
			poLine.setUdf6Code(originalPoLine.getUdf6Code());
			poLine.setUdf7Code(originalPoLine.getUdf7Code());
			poLine.setUdf8Code(originalPoLine.getUdf8Code());
			poLine.setUdf9Code(originalPoLine.getUdf9Code());
			poLine.setUdf10Code(originalPoLine.getUdf10Code());
			poLine.setMemoLine(originalPoLine.getMemoLine());


			poLine.setUmCode(originalPoLine.getUmCode());
            poLine.setUmConv(originalPoLine.getUmConv());
			poLine.setUmFactor(originalPoLine.getUmFactor());
			poLine.setUnitPrice(originalPoLine.getUnitPrice());
			poLine.setDepartmentCode(originalPoLine.getDepartmentCode());


			poLine.setRequisitionNumber("");
			poLine.setDateEntered(Dates.getCurrentDate(userTimeZone)) ;
			String releaseType = (String)incomingRequest.get("releaseType");
			if(!Utility.isEmpty(releaseType) && (releaseType.equals(OrderType.DELIVERY_RELEASE) || releaseType.equals(OrderType.SERVICE_RELEASE) || releaseType.equals(OrderType.RELEASE_ORDER)))
			{
			    poLine.setIcRelKey(originalPoLine.getIcRelKey());
			    poLine.setVendorId(originalPoLine.getVendorId());
			    poLine.setVendorName(originalPoLine.getVendorName());
			    poLine.setVendContactCode(originalPoLine.getVendContactCode());
			    poLine.setUmConv(originalPoLine.getUmConv());
			    //poLine.setIcLineKey(originalPoLine.getIcLineKey());
			}

			String createRevision = (String)incomingRequest.get("createRevision");
			if(!Utility.isEmpty(createRevision) && createRevision.equals("Y")) {
				poLine.setIcLineKey(originalPoLine.getIcLineKey());

			    poLine.setActionInd(originalPoLine.getActionInd());
			    poLine.setChgPromCnt(originalPoLine.getChgPromCnt());
			    poLine.setChgPromDate(originalPoLine.getChgPromDate());
			    poLine.setIcLineHistory(originalPoLine.getIcLineHistory());
			    poLine.setIcReqLine(originalPoLine.getIcReqLine());
			    poLine.setIcRfqLine(originalPoLine.getIcRfqLine());
			    poLine.setLineRevNo(originalPoLine.getLineRevNo());
			    poLine.setPoPromised(originalPoLine.getPoPromised());
			    poLine.setQtyReceived(originalPoLine.getQtyReceived());
			    poLine.setDuomQtyReceived(originalPoLine.getDuomQtyReceived());
			    poLine.setReleaseNumber(originalPoLine.getReleaseNumber());
			    poLine.setRequisitionNumber(originalPoLine.getRequisitionNumber());
			    poLine.setExpensed(originalPoLine.getExpensed());

			    if (originalPoLine.getStatus().equals(DocumentStatus.CANCELLED)) {
			        poLine.setStatus(DocumentStatus.CANCELLED);
			    }
			}

            if(Utility.ckNull(createFromContract).equals("Y")) {
                poLine.setIcLineKey(originalPoLine.getIcLineKey());

                poLine.setActionInd(originalPoLine.getActionInd());
                poLine.setChgPromCnt(originalPoLine.getChgPromCnt());
                poLine.setChgPromDate(originalPoLine.getChgPromDate());
                poLine.setIcLineHistory(originalPoLine.getIcLineHistory());
                poLine.setIcReqLine(originalPoLine.getIcReqLine());
                poLine.setIcRfqLine(originalPoLine.getIcRfqLine());
                poLine.setLineRevNo(originalPoLine.getLineRevNo());
                poLine.setPoPromised(originalPoLine.getPoPromised());
                poLine.setQtyReceived(originalPoLine.getQtyReceived());
                poLine.setDuomQtyReceived(originalPoLine.getDuomQtyReceived());
                poLine.setReleaseNumber(originalPoLine.getReleaseNumber());
                poLine.setRequisitionNumber(originalPoLine.getRequisitionNumber());
                poLine.setAltItemNumber(originalPoLine.getAltItemNumber());

                if (originalPoLine.getStatus().equals(DocumentStatus.CANCELLED)) {
                    poLine.setStatus(DocumentStatus.CANCELLED);
                }
            }

			incomingRequest.put("poLine", poLine);

			PoLineAdd addTask = new PoLineAdd();
			poLine = (PoLine) addTask.executeTask(incomingRequest);
			this.setStatus(addTask.getStatus()) ;

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