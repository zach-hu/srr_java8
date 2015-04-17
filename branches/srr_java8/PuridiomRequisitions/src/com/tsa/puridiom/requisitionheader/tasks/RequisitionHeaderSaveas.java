package com.tsa.puridiom.requisitionheader.tasks;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.documents.RequisitionType;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.departmentbuyer.tasks.DepartmentBuyerManager;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.UniqueKeyGenerator;

public class RequisitionHeaderSaveas extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain requisitionHeader */
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			RequisitionHeader	originalRequisitionHeader = (RequisitionHeader) incomingRequest.get("requisitionHeader");
			RequisitionHeader	requisitionHeader = new RequisitionHeader();
			String	requisitionNumber = (String) incomingRequest.get("newRequisitionHeader_requisitionNumber");
			String	userId = (String) incomingRequest.get("userId");
			String  splitType = (String) incomingRequest.get("splitType") ;
		    String splitSubType = (String)incomingRequest.get("splitSubType") ;
		    String splitLocation = (String)incomingRequest.get("splitLocation") ;
		    String splitShipToCode = (String)incomingRequest.get("splitShipToCode") ;
		    String splitPcardExpires = (String)incomingRequest.get("splitPcardExpires") ;
		    String splitPcardNumber = (String)incomingRequest.get("splitPcardNumber") ;
		    String splitPcardHolder = (String)incomingRequest.get("splitPcardHolder") ;
		    String splitIcRevisedOrder = (String)incomingRequest.get("splitIcRevisedOrder") ;


            String userTimeZone = (String) incomingRequest.get("userTimeZone");
            Date d_today = Dates.getCurrentDate(userTimeZone);
            UserManager userManager = UserManager.getInstance();
            String	oid = (String) incomingRequest.get("organizationId");
			PropertiesManager propertiesManager = PropertiesManager.getInstance(oid) ;

			requisitionHeader.setIcReqHeader(new BigDecimal(ukg.getUniqueKey().toString()));
			requisitionHeader.setRequisitionNumber(requisitionNumber);
			if (splitType == null) {
				requisitionHeader.setRequisitionType(originalRequisitionHeader.getRequisitionType());
				requisitionHeader.setRequisitionerCode(userId);
				requisitionHeader.setOwner(userId);
			} else {
				requisitionHeader.setRequisitionType(splitType);
				requisitionHeader.setRequisitionerCode(originalRequisitionHeader.getRequisitionerCode());
				requisitionHeader.setOwner(originalRequisitionHeader.getOwner());
			}
			requisitionHeader.setRequisitionDate(d_today);
			if(propertiesManager.getProperty("REQ OPTIONS", "HISTORYSAVEAS", "N").equalsIgnoreCase("Y"))
			{
				String createFromSaveAs = "Created Requisition #" + requisitionNumber + " from Requisition #" + originalRequisitionHeader.getRequisitionNumber();
				incomingRequest.put("createFromSaveAs", createFromSaveAs);
			}
			if ( !HiltonUtility.isEmpty((String) incomingRequest.get("templateNumber")))
			{
				requisitionHeader.setStatus(DocumentStatus.TEMPLATE);
				String rqSubType = HiltonUtility.ckNull((String) incomingRequest.get("RequisitionHeader_rqSubType"));
				String internalComments = HiltonUtility.ckNull((String) incomingRequest.get("RequisitionHeader_internalComments"));
				requisitionHeader.setRqSubType(rqSubType);
				requisitionHeader.setInternalComments(internalComments);
			}
			else
			{
				requisitionHeader.setStatus(DocumentStatus.REQ_INPROGRESS);
				if (splitSubType != null) requisitionHeader.setRqSubType(splitSubType);

			//	requisitionHeader.setRqSubType(originalRequisitionHeader.getRqSubType());
				requisitionHeader.setInternalComments(originalRequisitionHeader.getInternalComments());
			}

			if (splitType == null && HiltonUtility.isEmpty((String) incomingRequest.get("templateNumber"))) {
				// Sourcing
				if (originalRequisitionHeader.getRequisitionType().equals("M")) {
					requisitionHeader.setStatus(DocumentStatus.REQ_PLANNING) ;
				}
			}

			requisitionHeader.setDiscountSource(originalRequisitionHeader.getDiscountSource());
			requisitionHeader.setDiscountPercent(originalRequisitionHeader.getDiscountPercent());
			requisitionHeader.setDiscountAmount(originalRequisitionHeader.getDiscountAmount());
			requisitionHeader.setShippingCharges(originalRequisitionHeader.getShippingCharges());
			requisitionHeader.setTaxShipping(originalRequisitionHeader.getTaxShipping());
			requisitionHeader.setOtherCharges(originalRequisitionHeader.getOtherCharges());
			requisitionHeader.setTaxOther(originalRequisitionHeader.getTaxOther());
			requisitionHeader.setOtherChargDesc(originalRequisitionHeader.getOtherChargDesc());
			if (!HiltonUtility.isEmpty((String) incomingRequest.get("RequisitionHeader_fiscalYear")))
			{
				requisitionHeader.setFiscalYear((String) incomingRequest.get("RequisitionHeader_fiscalYear"));
			}
			else
			{
				requisitionHeader.setFiscalYear(originalRequisitionHeader.getFiscalYear());
			}
			//requisitionHeader.setOwner(userId);
			requisitionHeader.setDateEntered(d_today);
			requisitionHeader.setTaxPercent(originalRequisitionHeader.getTaxPercent());
			requisitionHeader.setTaxAmount(originalRequisitionHeader.getTaxAmount());
			requisitionHeader.setOtherTaxAmount(originalRequisitionHeader.getOtherTaxAmount());
			requisitionHeader.setShippingTaxAmt(originalRequisitionHeader.getShippingTaxAmt());
			requisitionHeader.setLanguage(originalRequisitionHeader.getLanguage());
			requisitionHeader.setSubtotal(originalRequisitionHeader.getSubtotal());
			requisitionHeader.setTotal(originalRequisitionHeader.getTotal());
			//app date should be null when doing a saveas - as per JR 04.27.07
			//requisitionHeader.setAppDate(d_today);
			requisitionHeader.setAppSigned("N");
			requisitionHeader.setLastChgBy(userId);
			requisitionHeader.setLastChgDate(d_today);
			requisitionHeader.setApproved("N");
			requisitionHeader.setAppBy("");

			/**  the following values should be set back to their defaults, as requested in incident #7841  **/
			//requisitionHeader.setAssignedBuyer(originalRequisitionHeader.getAssignedBuyer());
			requisitionHeader.setAssignedBuyer("PURCHASING");
			//assigned date should be null
			//requisitionHeader.setAssignedDate(originalRequisitionHeader.getAssignedDate());

			requisitionHeader.setEstimatedCost(originalRequisitionHeader.getEstimatedCost());
			requisitionHeader.setBidWaiver(originalRequisitionHeader.getBidWaiver());
			requisitionHeader.setRqHeaderKey(originalRequisitionHeader.getRqHeaderKey());
			requisitionHeader.setPcardReq(originalRequisitionHeader.getPcardReq());
			requisitionHeader.setPcardName(originalRequisitionHeader.getPcardName());
			requisitionHeader.setPcardHolder(originalRequisitionHeader.getPcardHolder());
			requisitionHeader.setPcardNumber(originalRequisitionHeader.getPcardNumber());
			requisitionHeader.setPcardExpires(originalRequisitionHeader.getPcardExpires());
			if (HiltonUtility.isEmpty(splitIcRevisedOrder)) {
				requisitionHeader.setIcRevisedOrder(originalRequisitionHeader.getIcRevisedOrder());
			} else {
				BigDecimal icRevisedOrder = new BigDecimal(splitIcRevisedOrder) ;
				requisitionHeader.setIcRevisedOrder(icRevisedOrder);
			}
			requisitionHeader.setReqRecalc(originalRequisitionHeader.getReqRecalc());
			requisitionHeader.setActionAlertFlag(originalRequisitionHeader.getActionAlertFlag());
			requisitionHeader.setMaxStatus(originalRequisitionHeader.getMaxStatus());
			requisitionHeader.setBuyerRemarks(originalRequisitionHeader.getBuyerRemarks());
			requisitionHeader.setAmmendment(originalRequisitionHeader.getAmmendment());
			//requisitionHeader.setRequisitionerCode(userId);
			requisitionHeader.setDepartmentCode(originalRequisitionHeader.getDepartmentCode());
			requisitionHeader.setAuthorizationCode(originalRequisitionHeader.getAuthorizationCode());

			String oldReqNumber = originalRequisitionHeader.getRequisitionNumber();
			String vendorId = originalRequisitionHeader.getVendorId();
			
			/**  if the vendor id was equal to the req # it was a 1-time vendor address, do not save onto the new requisition  **/
			if ( !vendorId.equalsIgnoreCase(oldReqNumber) )
			{
				requisitionHeader.setVendorId(originalRequisitionHeader.getVendorId());
				requisitionHeader.setVendContactCode(originalRequisitionHeader.getVendContactCode());
				requisitionHeader.setVendorAttn(originalRequisitionHeader.getVendorAttn());
				requisitionHeader.setContactAddr(originalRequisitionHeader.getContactAddr());
				requisitionHeader.setContactPhone(originalRequisitionHeader.getContactPhone());
			}
			String shipToCode = originalRequisitionHeader.getShipToCode();
			/**  if the ship to code was equal to the req # it was a 1-time shipto address, do not save onto the new requisition  **/
			if ( !shipToCode.equalsIgnoreCase(oldReqNumber))
			{
				requisitionHeader.setShipToCode(originalRequisitionHeader.getShipToCode());
				requisitionHeader.setShipToContact(originalRequisitionHeader.getShipToContact());
				requisitionHeader.setShipAttn(originalRequisitionHeader.getShipAttn());
			}
			if (splitShipToCode != null && splitShipToCode != "") {
				requisitionHeader.setShipToCode(splitShipToCode);
			}

			requisitionHeader.setShippingCode(originalRequisitionHeader.getShippingCode());

			if (oid.equalsIgnoreCase("bsc04p"))
			{
				String departmentCode = originalRequisitionHeader.getDepartmentCode();
				String  s_Buyer_Department = DepartmentBuyerManager.getInstance().getBuyerId(oid, departmentCode);

				if ( !HiltonUtility.isEmpty(s_Buyer_Department))
				{
					requisitionHeader.setBuyer(s_Buyer_Department);
				}
				else
				{
					requisitionHeader.setBuyer(originalRequisitionHeader.getBuyer());
				}
			}
			else
			{
				requisitionHeader.setBuyer(originalRequisitionHeader.getBuyer());
			}

			requisitionHeader.setRouting(originalRequisitionHeader.getRouting());
			requisitionHeader.setPriorityCode(originalRequisitionHeader.getPriorityCode());
			if (originalRequisitionHeader.getRequiredDate() != null) {
				requisitionHeader.setRequiredDate(originalRequisitionHeader.getRequiredDate());
			}
			requisitionHeader.setTaxCode(originalRequisitionHeader.getTaxCode());
			requisitionHeader.setUdf1Code(originalRequisitionHeader.getUdf1Code());
			requisitionHeader.setUdf2Code(originalRequisitionHeader.getUdf2Code());


			System.out.println("udf 2 is " + originalRequisitionHeader.getUdf2Code());


			requisitionHeader.setUdf3Code(originalRequisitionHeader.getUdf3Code());
			requisitionHeader.setUdf4Code(originalRequisitionHeader.getUdf4Code());
			requisitionHeader.setUdf5Code(originalRequisitionHeader.getUdf5Code());
			requisitionHeader.setUdf6Code(originalRequisitionHeader.getUdf6Code());
			requisitionHeader.setUdf7Code(originalRequisitionHeader.getUdf7Code());
			requisitionHeader.setUdf8Code(originalRequisitionHeader.getUdf8Code());
			requisitionHeader.setUdf9Code(originalRequisitionHeader.getUdf9Code());
			requisitionHeader.setUdf10Code(originalRequisitionHeader.getUdf10Code());
			requisitionHeader.setUdf11Code(originalRequisitionHeader.getUdf11Code());
			requisitionHeader.setUdf12Code(originalRequisitionHeader.getUdf12Code());
			requisitionHeader.setUdf13Code(originalRequisitionHeader.getUdf13Code());
			requisitionHeader.setRequestCat(originalRequisitionHeader.getRequestCat());
            if (! propertiesManager.getProperty("FDCS", "Enabled", "N").equalsIgnoreCase("Y")) {
            	// Only set if FDC not enabled
            	requisitionHeader.setUdf14Code(originalRequisitionHeader.getUdf14Code());
            	requisitionHeader.setUdf15Code(originalRequisitionHeader.getUdf15Code());
            }

			requisitionHeader.setAccountCode(originalRequisitionHeader.getAccountCode());

			requisitionHeader.setBillToCode(originalRequisitionHeader.getBillToCode());
			requisitionHeader.setBillToContact(originalRequisitionHeader.getBillToContact());
			requisitionHeader.setIcHeaderHistory(new BigDecimal(ukg.getUniqueKey().toString()));
			requisitionHeader.setItemLocation(originalRequisitionHeader.getItemLocation());
			if (splitLocation != null && splitLocation != "") {
				requisitionHeader.setItemLocation(splitLocation);
			}
			requisitionHeader.setCurrencyCode(originalRequisitionHeader.getCurrencyCode());
			requisitionHeader.setReceiptRequired(originalRequisitionHeader.getReceiptRequired());
			requisitionHeader.setGfpGfm(originalRequisitionHeader.getGfpGfm());
			requisitionHeader.setKit(originalRequisitionHeader.getKit());
			requisitionHeader.setUseTaxAmount(originalRequisitionHeader.getUseTaxAmount());
			requisitionHeader.setUseTaxCode(originalRequisitionHeader.getUseTaxCode());
			requisitionHeader.setUseTaxPercent(originalRequisitionHeader.getUseTaxPercent());
			requisitionHeader.setOriginalReqType(originalRequisitionHeader.getRequisitionType());
			requisitionHeader.setCorrosionEval(originalRequisitionHeader.getCorrosionEval());
			requisitionHeader.setWorkOrder(originalRequisitionHeader.getWorkOrder());
			
			if(originalRequisitionHeader.getRequisitionType().equalsIgnoreCase(RequisitionType.MSR_REQUEST)){
				requisitionHeader.setIcMsrHeader(originalRequisitionHeader.getIcReqHeader());
				requisitionHeader.setMsrNumber(originalRequisitionHeader.getRequisitionNumber());
			} else {
				requisitionHeader.setIcMsrHeader(originalRequisitionHeader.getIcMsrHeader());
				requisitionHeader.setMsrNumber(originalRequisitionHeader.getMsrNumber());
			}
			
			if (!HiltonUtility.isEmpty(requisitionHeader.getOriginalReqType()) && requisitionHeader.getOriginalReqType().equalsIgnoreCase("M"))
			{
				requisitionHeader.setEstimatedCost(originalRequisitionHeader.getEstimatedCost());
			}

			if (originalRequisitionHeader.getServicesStartDate() != null)
				requisitionHeader.setServicesStartDate(originalRequisitionHeader.getServicesStartDate());
			if (originalRequisitionHeader.getServicesEndDate() != null)
				requisitionHeader.setServicesEndDate(originalRequisitionHeader.getServicesEndDate());

			if (splitPcardExpires != null ) 	requisitionHeader.setPcardExpires(splitPcardExpires) ;
			if (splitPcardNumber != null ) 	requisitionHeader.setPcardNumber(splitPcardNumber) ;
			if (splitPcardHolder != null ) 	requisitionHeader.setPcardHolder(splitPcardHolder) ;

			incomingRequest.put("requisitionHeader", requisitionHeader);
			incomingRequest.put("originalRequisitionHeader", originalRequisitionHeader);

			RequisitionHeaderAdd addTask = new RequisitionHeaderAdd();
			requisitionHeader = (RequisitionHeader) addTask.executeTask(incomingRequest);
			this.setStatus(addTask.getStatus()) ;

			result = requisitionHeader;
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