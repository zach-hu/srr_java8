/*
 * Created on Aug 25, 2004
 *
 * @author  * renzo
 * project: HiltonPurchaseOrders
 * package com.tsa.puridiom.po.tasks.PoReleaseCreateSetup.java
 *
 */
package com.tsa.puridiom.po.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.documents.OrderType;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.ShipTo;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PoAutoReleaseFromReqSetup extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        try
        {
        	Map incomingRequest = (Map)object;

        	String organizationId = (String)incomingRequest.get("organizationId");
        	PropertiesManager propertiesManager = PropertiesManager.getInstance(organizationId) ;

            BigDecimal blanketIcDecimal = (BigDecimal)incomingRequest.get("blanketIc");
            String blanketIc = blanketIcDecimal.toString();
            String	userId = (String) incomingRequest.get("userId") ;
            if(Utility.isEmpty(blanketIc))
            {
                this.setStatus(Status.FAILED);
                throw new TsaException("Blanket Ic can not be Empty");
            }
            incomingRequest.put("old_PoHeader_icPoHeader", blanketIc);
            PoHeader newPoHeader = (PoHeader)incomingRequest.get("poHeader");
            PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
            if(newPoHeader == null)
            {
                this.setStatus(Status.FAILED);
                throw new TsaException("Release Order was not Found.");
            }
            PoHeader blanket = (PoHeader)incomingRequest.get("blanket");
            if(blanket == null)
            {
                this.setStatus(Status.FAILED);
                throw new TsaException("Blanket Order was not Found.");
            }

            RequisitionHeader requisitionHeader = (RequisitionHeader)incomingRequest.get("requisitionHeader");
            if(requisitionHeader == null)
            {
                this.setStatus(Status.FAILED);
                throw new TsaException("Requisition was not Found!.");
            }

            Dates dates = new Dates();
            int offsetDays = dates.getOffsetDays(10, false);
            String promised = Dates.add(Dates.today("", newPoHeader.getTimeZone()), offsetDays);

            newPoHeader.setPromisedDate(Dates.getDate(promised));
            String poType = (String)incomingRequest.get("releaseType");
            if(poType == null)
            {
                newPoHeader.setPoType(OrderType.PURCHASE_ORDER);
            }
            else
            {
                newPoHeader.setPoType(poType);
            }
            newPoHeader.setPoNumber(blanket.getPoNumber());
            newPoHeader.setVendorId(blanket.getVendorId());
            newPoHeader.setVendorName(blanket.getVendorName());
            newPoHeader.setVendContactCode(blanket.getVendContactCode());
            newPoHeader.setBlanketLimit(blanket.getBlanketLimit());
            newPoHeader.setReleaseLimit(blanket.getReleaseLimit());
            newPoHeader.setReleaseNumber(new BigDecimal((String)incomingRequest.get("PoHeader_releaseNumber")));
            newPoHeader.setContractNo(blanket.getContractNo());
            newPoHeader.setShipViaCode(blanket.getShipViaCode());
            newPoHeader.setEffectiveDate(blanket.getEffectiveDate());
            newPoHeader.setExpirationDate(blanket.getExpirationDate());
            newPoHeader.setConfirming(blanket.getConfirming());
            if(Utility.isEmpty(newPoHeader.getConfirming()))
            {
                newPoHeader.setConfirming("N");
            }
            newPoHeader.setConfirmDate(blanket.getConfirmDate());
            newPoHeader.setTaxCode(blanket.getTaxCode());
            newPoHeader.setShippingTaxable(blanket.getShippingTaxable());
            newPoHeader.setOtherTaxable(blanket.getOtherTaxable());
            newPoHeader.setPrePaid(blanket.getPrePaid());
            newPoHeader.setReceiptRequired(blanket.getReceiptRequired());
            newPoHeader.setShippingTaxable(blanket.getShippingTaxable());
            newPoHeader.setOtherTaxable(blanket.getOtherTaxable());
            newPoHeader.setEdiOrder(blanket.getEdiOrder());
            //newPoHeader.setOwner(blanket.getOwner());
            newPoHeader.setOwner("AUTORELEASE");
            newPoHeader.setLastChgBy("AUTORELEASE");

            newPoHeader.setIcReqHeader(requisitionHeader.getIcReqHeader());
            newPoHeader.setRequisitionNumber(requisitionHeader.getRequisitionNumber());
            newPoHeader.setFiscalYear(requisitionHeader.getFiscalYear());
            newPoHeader.setTotal(requisitionHeader.getTotal());
            newPoHeader.setSubtotal(requisitionHeader.getSubtotal());
            newPoHeader.setOtherCharges(requisitionHeader.getOtherCharges());
            newPoHeader.setOtherDescription(requisitionHeader.getOtherChargDesc());
            //newPoHeader.setOtherTaxable(requisitionHeader.getTaxOther());
            newPoHeader.setOtherTax(requisitionHeader.getOtherTaxAmount());
            newPoHeader.setDiscountAmount(requisitionHeader.getDiscountAmount());
            newPoHeader.setDiscountPercent(requisitionHeader.getDiscountPercent());
            newPoHeader.setTaxCode(requisitionHeader.getTaxCode());
            newPoHeader.setTaxPercent(requisitionHeader.getTaxPercent());
            newPoHeader.setTaxAmount(requisitionHeader.getTaxAmount());
            newPoHeader.setShippingCharges(requisitionHeader.getShippingCharges());
            //newPoHeader.setShippingTaxable(requisitionHeader.getTaxShipping());
            //newPoHeader.setOtherTaxable(requisitionHeader.getTaxOther());
            newPoHeader.setShippingTax(requisitionHeader.getShippingTaxAmt());
            newPoHeader.setIcHeaderHistory(requisitionHeader.getIcHeaderHistory());
            newPoHeader.setShipToCode(requisitionHeader.getShipToCode());
            newPoHeader.setShipToContact(requisitionHeader.getShipToContact());
            newPoHeader.setRequisitionerCode(requisitionHeader.getRequisitionerCode());
            newPoHeader.setRequiredDate(requisitionHeader.getRequiredDate());
            newPoHeader.setShipViaCode(requisitionHeader.getShippingCode());
            newPoHeader.setPriorityCode(requisitionHeader.getPriorityCode());
            newPoHeader.setRouting(requisitionHeader.getRouting());
            newPoHeader.setShipToContact(requisitionHeader.getShipAttn());
            newPoHeader.setCurrencyCode(requisitionHeader.getCurrencyCode());
            newPoHeader.setKit(requisitionHeader.getKit());
            newPoHeader.setItemLocation(requisitionHeader.getItemLocation());
            newPoHeader.setRequestCat(requisitionHeader.getRequestCat());
            newPoHeader.setPoCopies(new BigDecimal(1));
            newPoHeader.setDatePrinted(null);
            newPoHeader.setDateFaxed(null);
            newPoHeader.setDateEdiXmit(null);
            newPoHeader.setCurrencyFactor(new BigDecimal(1));
            newPoHeader.setObligDate(null);
            newPoHeader.setIcMsrHeader(requisitionHeader.getIcMsrHeader());
            newPoHeader.setMsrNumber(requisitionHeader.getMsrNumber());

            newPoHeader.setDepartmentCode(requisitionHeader.getDepartmentCode());

            if(Utility.isEmpty(newPoHeader.getPriorityCode()))
            {
                newPoHeader.setPriorityCode(blanket.getPriorityCode());
            }

            String internalCmts = requisitionHeader.getInternalComments();
            if(Utility.isEmpty(internalCmts))
            {
                internalCmts = blanket.getInternalComments();
            }
            newPoHeader.setInternalComments(internalCmts);
            if(Utility.isEmpty(newPoHeader.getShipViaCode()))
            {
                newPoHeader.setShipViaCode(blanket.getShipViaCode());
            }

            newPoHeader.setLastRevision("C");

            String autoAwardReleaseRequest = propertiesManager.getProperty("AUTOAWARD", "AUTOAWARDRELEASEREQUEST", "N");
            if (autoAwardReleaseRequest.equals("N"))
            {
            	incomingRequest.put("PoHeader_status", DocumentStatus.PO_INPROGRESS);
            }
            else
            {
            	incomingRequest.put("PoHeader_status", DocumentStatus.PO_AWARDED);
            	poHeader.setStatus( DocumentStatus.PO_AWARDED);
            }

            if (incomingRequest.containsKey("autoShipToHeader"))
			{
				newPoHeader.setShipToCode(((ShipTo) incomingRequest.get("autoShipToHeader")).getShipToCode());
			}

            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException(this.getName(), e);
        }
        return super.executeTask(object);
    }
}
