package com.tsa.puridiom.poheader.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.documents.OrderType;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import com.tsagate.foundation.utility.Utility;
import java.math.BigDecimal;
import java.util.*;

public class PoHeaderSaveas extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        Object result = null;

        try
        {
            String organizationId = (String)incomingRequest.get("organizationId");
            PropertiesManager propertiesManager = PropertiesManager.getInstance(organizationId) ;

            /* Expects incoming request to contain poHeader */
            UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
            PoHeader	originalPoHeader = (PoHeader) incomingRequest.get("poHeader");
            PoHeader	poHeader = new PoHeader();
            String	userId = (String) incomingRequest.get("userId");
            String  userTimeZone = (String) incomingRequest.get("userTimeZone");
            String	createRevision = (String) incomingRequest.get("createRevision");
            String  createFromContract = (String) incomingRequest.get("createFromContract");
            String  newType = (String) incomingRequest.get("newType");
            Date d_today = Dates.getCurrentDate(userTimeZone);

            if (HiltonUtility.isEmpty(newType)) {
                newType = originalPoHeader.getPoType();
            }

            poHeader.setActionAlertFlag(originalPoHeader.getActionAlertFlag());
            poHeader.setApproved("N");
            poHeader.setAppBy("");
            poHeader.setAppDate(d_today);
            poHeader.setAppSigned("N");
            poHeader.setBillToCode(originalPoHeader.getBillToCode());
            poHeader.setBillToContact(originalPoHeader.getBillToContact());
            poHeader.setBlanketLimit(originalPoHeader.getBlanketLimit());
            poHeader.setBuyerCode(originalPoHeader.getBuyerCode());
            poHeader.setConfirmNameCode("");
            poHeader.setContactAddr(originalPoHeader.getContactAddr());
            poHeader.setContactName(originalPoHeader.getContactName());
            poHeader.setContactAddr(originalPoHeader.getContactAddr());
            poHeader.setContactPhone(originalPoHeader.getContactPhone());
            poHeader.setCorrosionEval(originalPoHeader.getCorrosionEval());
            poHeader.setCurrencyCode(originalPoHeader.getCurrencyCode());
            poHeader.setCurrencyFactor(originalPoHeader.getCurrencyFactor());
            poHeader.setDateEntered(d_today);
            poHeader.setDepartmentCode(originalPoHeader.getDepartmentCode());
            poHeader.setDiscountAmount(originalPoHeader.getDiscountAmount());
            poHeader.setDiscountCode(originalPoHeader.getDiscountCode());
            poHeader.setDiscountPercent(originalPoHeader.getDiscountPercent());
            poHeader.setEstimatedCost(originalPoHeader.getEstimatedCost());
            if (!HiltonUtility.isEmpty((String) incomingRequest.get("PoHeader_fiscalYear")))
			{
				poHeader.setFiscalYear((String) incomingRequest.get("PoHeader_fiscalYear"));
			}
			else
			{
				poHeader.setFiscalYear(originalPoHeader.getFiscalYear());
			}
            poHeader.setFobCode(originalPoHeader.getFobCode());
            poHeader.setIcHeaderHistory(new BigDecimal(ukg.getUniqueKey().toString()));
            poHeader.setIcPoHeader(new BigDecimal(ukg.getUniqueKey().toString()));
            poHeader.setInternalComments(originalPoHeader.getInternalComments());
            poHeader.setItemLocation(originalPoHeader.getItemLocation());
            poHeader.setLanguage(originalPoHeader.getLanguage());
            poHeader.setLastChgBy(userId);
            poHeader.setLastChgDate(d_today);
            poHeader.setLastRevision("C");
            poHeader.setObligAmt(originalPoHeader.getObligAmt());
            poHeader.setObligDate(originalPoHeader.getObligDate());
            poHeader.setOtherCharges(originalPoHeader.getOtherCharges());
            poHeader.setOtherDescription(originalPoHeader.getOtherDescription());
            poHeader.setOtherTax(originalPoHeader.getOtherTax());
            poHeader.setOtherTaxable(originalPoHeader.getOtherTaxable());
            poHeader.setOwner(userId);
            poHeader.setPoCopies(originalPoHeader.getPoCopies());
            poHeader.setPoDate(d_today);
            poHeader.setPoNumber((String) incomingRequest.get("newPoHeader_poNumber"));
            poHeader.setPriorityCode(originalPoHeader.getPriorityCode());
            poHeader.setReceiptRequired(originalPoHeader.getReceiptRequired());
            poHeader.setReleaseLimit(originalPoHeader.getReleaseLimit());
            poHeader.setReleaseNumber(originalPoHeader.getReleaseNumber());
            poHeader.setRequiredDate(d_today);
            poHeader.setRequisitionerCode(originalPoHeader.getRequisitionerCode());
            poHeader.setRevisionNumber(new BigDecimal(0));
            poHeader.setIcHeaderKey(originalPoHeader.getIcHeaderKey());
            poHeader.setRouting(originalPoHeader.getRouting());
            poHeader.setShippingCharges(originalPoHeader.getShippingCharges());
            poHeader.setShippingTax(originalPoHeader.getShippingTax());
            poHeader.setShippingTaxable(originalPoHeader.getShippingTaxable());
            poHeader.setShipToCode(originalPoHeader.getShipToCode());
            poHeader.setShipToContact(originalPoHeader.getShipToContact());
            poHeader.setShipToInv(originalPoHeader.getShipToInv());
            poHeader.setShipViaCode(originalPoHeader.getShipViaCode());
           	if (originalPoHeader.getPoType().equals(OrderType.CONTRACT) && propertiesManager.getProperty("PO OPTIONS", "CONTRACTSAVEASPO", "N").equals("Y") && newType.equals("CT") ) {
            	poHeader.setStatus(DocumentStatus.CT_INPROGRESS);
                poHeader.setPoType(originalPoHeader.getPoType());
            } else {
                poHeader.setStatus(DocumentStatus.PO_INPROGRESS);
                poHeader.setPoType(newType);
            }
            poHeader.setSubtotal(originalPoHeader.getSubtotal());
            poHeader.setTaxAmount(originalPoHeader.getTaxAmount());
            poHeader.setTaxCode(originalPoHeader.getTaxCode());
            poHeader.setTaxPercent(originalPoHeader.getTaxPercent());
            poHeader.setUseTaxAmount(originalPoHeader.getUseTaxAmount());
            poHeader.setUseTaxCode(originalPoHeader.getUseTaxCode());
            poHeader.setUseTaxPercent(originalPoHeader.getUseTaxPercent());
            poHeader.setTermsCode(originalPoHeader.getTermsCode());
            poHeader.setTotal(originalPoHeader.getTotal());
            poHeader.setUdf1Code(originalPoHeader.getUdf1Code());
            poHeader.setUdf2Code(originalPoHeader.getUdf2Code());
            poHeader.setUdf3Code(originalPoHeader.getUdf3Code());
            poHeader.setUdf4Code(originalPoHeader.getUdf4Code());
            poHeader.setUdf5Code(originalPoHeader.getUdf5Code());
            poHeader.setUdf6Code(originalPoHeader.getUdf6Code());
            poHeader.setUdf7Code(originalPoHeader.getUdf7Code());
            poHeader.setUdf8Code(originalPoHeader.getUdf8Code());
            poHeader.setUdf9Code(originalPoHeader.getUdf9Code());
            poHeader.setUdf10Code(originalPoHeader.getUdf10Code());
            poHeader.setUdf11Code(originalPoHeader.getUdf11Code());
            poHeader.setUdf12Code(originalPoHeader.getUdf12Code());
            poHeader.setUdf13Code(originalPoHeader.getUdf13Code());
            if (! propertiesManager.getProperty("FDCS", "Enabled", "N").equalsIgnoreCase("Y")) {
            	// Only set if FDC not enabled
            	poHeader.setUdf14Code(originalPoHeader.getUdf14Code());
            	poHeader.setUdf15Code(originalPoHeader.getUdf15Code());
            }
            poHeader.setVendorId(originalPoHeader.getVendorId());
            poHeader.setVendorName(originalPoHeader.getVendorName());
            poHeader.setVendContactCode(originalPoHeader.getVendContactCode());
            poHeader.setEdiOrder(originalPoHeader.getEdiOrder());
            poHeader.setVendorClass((String) incomingRequest.get("PoHeader_vendorClass"));
            poHeader.setPyStatus(DocumentStatus.PY_NOTINVOICED);
            poHeader.setKit(originalPoHeader.getKit());

            /* Copy Credit card information from the Blanket to the Release Order */
            String pCardExpires = HiltonUtility.ckNull((String) incomingRequest.get("PoHeader_pcardExpires"));
            String pCardHolder = HiltonUtility.ckNull((String) incomingRequest.get("PoHeader_pcardHolder"));
            String pCardNumber = HiltonUtility.ckNull((String) incomingRequest.get("PoHeader_pcardNumber"));
            String pCardOrder = HiltonUtility.ckNull((String) incomingRequest.get("PoHeader_pcardOrder"));
            String pCardName = HiltonUtility.ckNull((String) incomingRequest.get("PoHeader_pcardName"));

            poHeader.setPcardExpires( !HiltonUtility.isEmpty(pCardExpires) ? pCardExpires : originalPoHeader.getPcardExpires() );
            poHeader.setPcardHolder( !HiltonUtility.isEmpty(pCardHolder) ? pCardHolder : originalPoHeader.getPcardHolder() );
            poHeader.setPcardNumber( !HiltonUtility.isEmpty(pCardNumber) ? pCardNumber : originalPoHeader.getPcardNumber() );
            poHeader.setPcardOrder( !HiltonUtility.isEmpty(pCardOrder) ? pCardOrder : originalPoHeader.getPcardOrder() );
            poHeader.setPcardName( !HiltonUtility.isEmpty(pCardName) ? pCardName : originalPoHeader.getPcardName() );


            if (!Utility.isEmpty(createRevision) && createRevision.equals("Y")) {
                poHeader.setContractNo(originalPoHeader.getContractNo());

                if ((!originalPoHeader.getPoType().equalsIgnoreCase("CT")) || propertiesManager.getProperty("PO OPTIONS", "CTREVDATAFROMREQ", "Y").equalsIgnoreCase("Y")) {
	                poHeader.setIcHeaderHistory(originalPoHeader.getIcHeaderHistory());
	                poHeader.setIcReqHeader(originalPoHeader.getIcReqHeader());
	                poHeader.setRequisitionNumber(originalPoHeader.getRequisitionNumber());
                }

                poHeader.setIcRfqHeader(originalPoHeader.getIcRfqHeader());
                poHeader.setPcardOrder(originalPoHeader.getPcardOrder());
                poHeader.setPoDate(originalPoHeader.getPoDate());
                poHeader.setPrePaid(originalPoHeader.getPrePaid());
                if (organizationId.equalsIgnoreCase("DTN07P")) {
                	poHeader.setExpirationDate(originalPoHeader.getExpirationDate());
                }
                poHeader.setPromisedDate(originalPoHeader.getPromisedDate());
                poHeader.setPyStatus(originalPoHeader.getPyStatus());
                poHeader.setRequiredDate(originalPoHeader.getRequiredDate());
                poHeader.setReleaseNumber(originalPoHeader.getReleaseNumber());
                poHeader.setRevisionDate(d_today);
                poHeader.setRevisionNumber(new BigDecimal((String)incomingRequest.get("newPoHeader_revisionNumber")));
                poHeader.setRfqNumber(originalPoHeader.getRfqNumber());
                poHeader.setSavings(originalPoHeader.getSavings());
                poHeader.setSavingsReason(originalPoHeader.getSavingsReason());
                poHeader.setVendorEval(originalPoHeader.getVendorEval());
                poHeader.setVendorRating(originalPoHeader.getVendorRating());
                poHeader.setUdf7Code(originalPoHeader.getUdf7Code());
                poHeader.setUdf8Code(originalPoHeader.getUdf8Code());
                poHeader.setUdf9Code(originalPoHeader.getUdf9Code());
                poHeader.setUdf10Code(originalPoHeader.getUdf10Code());
                poHeader.setEstimatedCost(originalPoHeader.getEstimatedCost());
                poHeader.setRequestCat(originalPoHeader.getRequestCat());
                poHeader.setSubType(originalPoHeader.getSubType());
                poHeader.setMsrNumber(originalPoHeader.getMsrNumber());
                poHeader.setIcMsrHeader(originalPoHeader.getIcMsrHeader());
            }

            if (!Utility.isEmpty(createFromContract) && createFromContract.equals("Y")) {
                //poHeader.setContractNo(originalPoHeader.getPoNumber());
                poHeader.setContractNo(originalPoHeader.getContractNo());
                poHeader.setIcHeaderHistory(originalPoHeader.getIcHeaderHistory());
                poHeader.setIcReqHeader(originalPoHeader.getIcReqHeader());
                poHeader.setIcRfqHeader(originalPoHeader.getIcRfqHeader());
                poHeader.setPcardOrder(originalPoHeader.getPcardOrder());
                poHeader.setPoDate(originalPoHeader.getPoDate());
                poHeader.setPrePaid(originalPoHeader.getPrePaid());
                poHeader.setPromisedDate(originalPoHeader.getPromisedDate());
                poHeader.setPyStatus(originalPoHeader.getPyStatus());
                poHeader.setRequiredDate(originalPoHeader.getRequiredDate());
                poHeader.setReleaseNumber(originalPoHeader.getReleaseNumber());
                poHeader.setRequisitionNumber(originalPoHeader.getRequisitionNumber());
                poHeader.setRfqNumber(originalPoHeader.getRfqNumber());
                poHeader.setSavings(originalPoHeader.getSavings());
                poHeader.setSavingsReason(originalPoHeader.getSavingsReason());
                poHeader.setVendorEval(originalPoHeader.getVendorEval());
                poHeader.setVendorRating(originalPoHeader.getVendorRating());
            }

            if (originalPoHeader.getPoType().equalsIgnoreCase("RO") || originalPoHeader.getPoType().equalsIgnoreCase("DR") || originalPoHeader.getPoType().equalsIgnoreCase("SR")) {
            	PoHeader	blanketHeader = (PoHeader) incomingRequest.get("blanketHeader");
            	if ((Utility.isEmpty(createRevision) || ! createRevision.equals("Y"))) {
            		String releaseNumberString = (String) incomingRequest.get("PoHeader_releaseNumber");
            		if (Utility.isEmpty(releaseNumberString))
            		{
            			releaseNumberString = "0";
            		}
            		BigDecimal releaseNumber = new BigDecimal ( releaseNumberString );
            		poHeader.setReleaseNumber(releaseNumber);

            		poHeader.setBlanketLimit(blanketHeader.getBlanketLimit());
            		poHeader.setEffectiveDate(blanketHeader.getEffectiveDate());
            		poHeader.setExpirationDate(blanketHeader.getExpirationDate());
            		poHeader.setReleaseLimit(blanketHeader.getReleaseLimit());
            		poHeader.setVendorClass(blanketHeader.getVendorClass());
            	} else {
            		poHeader.setVendorClass((String) incomingRequest.get("PoHeader_vendorClass"));
            	}
            }

            if (originalPoHeader.getPoType().equalsIgnoreCase("BO") || originalPoHeader.getPoType().equalsIgnoreCase("DO") || originalPoHeader.getPoType().equalsIgnoreCase("SB")) {
            	poHeader.setEffectiveDate(originalPoHeader.getEffectiveDate());
            	poHeader.setPromisedDate(originalPoHeader.getPromisedDate());
        		poHeader.setExpirationDate(originalPoHeader.getExpirationDate());
            }

            if(Utility.isEmpty(poHeader.getPrePaid()))
            {
                poHeader.setPrePaid("N");
            }
            incomingRequest.put("poHeader", poHeader);
            if (Utility.isEmpty(createRevision) || !createRevision.equals("Y"))
            {
            	//need this in incomingRequest to get updated currency factor. not needed for a revision.
            	incomingRequest.put("CurrCode_currencyCode", originalPoHeader.getCurrencyCode());
            }

            PoHeaderAdd addTask = new PoHeaderAdd();
            poHeader = (PoHeader) addTask.executeTask(incomingRequest);
            result = poHeader;

            this.setStatus(addTask.getStatus());
        }
        catch (Exception e)
        {
            this.status = Status.FAILED;
            throw e;
        }
        return result;
    }
}