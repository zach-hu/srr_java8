package com.tsa.puridiom.requisitionline.tasks;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.UniqueKeyGenerator;

public class RequisitionLineCloneMsr extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain requisitionLine */
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			String organizationId = (String) incomingRequest.get("organizationId");
			String userDateFormat = (String) incomingRequest.get("userDateFormat");
	        if (HiltonUtility.isEmpty(userDateFormat)) {
	            userDateFormat = PropertiesManager.getInstance(organizationId).getProperty("MISC", "DateFormat", "MM-dd-yyyy") ;
	        }
			RequisitionHeader	originalRequisitionHeader = (RequisitionHeader) incomingRequest.get("requisitionHeader");
		    String splitLocation = (String)incomingRequest.get("splitLocation") ;
		    String splitType = (String)incomingRequest.get("splitType");
		    String createdFrom = (String) incomingRequest.get("createdFrom");
		    boolean saveItem = true;
		    boolean inventoryItem = false;
		    boolean catalogItem = false;
			RequisitionLine	originalRequisitionLine = (RequisitionLine) incomingRequest.get("requisitionLine");
			RequisitionLine	requisitionLine = new RequisitionLine();
			String	userId = (String) incomingRequest.get("userId");
			requisitionLine.setIcReqLine(new BigDecimal(ukg.getUniqueKey().toString()));
			BigDecimal orgIcAccount = originalRequisitionLine.getIcAccount() ;
			if (orgIcAccount == null) orgIcAccount = new BigDecimal("0") ;
			if (orgIcAccount.compareTo(new BigDecimal("0")) > 0) {
				requisitionLine.setIcAccount(requisitionLine.getIcReqLine()) ;
			} else {
				requisitionLine.setIcAccount(new BigDecimal("0")) ;
			}

			requisitionLine.setLineNumber(originalRequisitionLine.getLineNumber());
			String newLineNumber = (String) incomingRequest.get("newRequisitionLine_lineNumber");
			if (createdFrom != null && createdFrom.equalsIgnoreCase("SOURCING") && !HiltonUtility.isEmpty(newLineNumber)) {
				requisitionLine.setLineNumber(new BigDecimal(newLineNumber));
			}
			requisitionLine.setIcReqHeader(new BigDecimal((String) incomingRequest.get("newRequisitionLine_icReqHeader")));

			String requisitionNumber = (String) incomingRequest.get("newRequisitionLine_requisitionNumber");
			requisitionLine.setRequisitionNumber(requisitionNumber);
			requisitionLine.setItemNumber(originalRequisitionLine.getItemNumber());
			requisitionLine.setUmCode(originalRequisitionLine.getUmCode());
			requisitionLine.setQuantity(originalRequisitionLine.getQuantity());
			requisitionLine.setUnitPrice(originalRequisitionLine.getUnitPrice());
			requisitionLine.setCommodityCode(originalRequisitionLine.getCommodityCode());
			requisitionLine.setTaxable(originalRequisitionLine.getTaxable());
			requisitionLine.setAsset(originalRequisitionLine.getAsset());
			requisitionLine.setDiscountSource(originalRequisitionLine.getDiscountSource());
			requisitionLine.setDiscountPercent(originalRequisitionLine.getDiscountPercent());
			requisitionLine.setDiscountAmount(originalRequisitionLine.getDiscountAmount());
			requisitionLine.setShippingCharges(originalRequisitionLine.getShippingCharges());
			requisitionLine.setTaxShipping(originalRequisitionLine.getTaxShipping());
			requisitionLine.setOtherCharges(originalRequisitionLine.getOtherCharges());
			requisitionLine.setOtherDescription(originalRequisitionLine.getOtherDescription());
			requisitionLine.setTaxOther(originalRequisitionLine.getTaxOther());
			requisitionLine.setShelfLifeRqd(originalRequisitionLine.getShelfLifeRqd());
			requisitionLine.setIcPoLine(new BigDecimal("0"));

			if ( !HiltonUtility.isEmpty((String) incomingRequest.get("templateNumber")))
			{
				requisitionLine.setStatus(DocumentStatus.TEMPLATE);
			}
			else
			{
				requisitionLine.setStatus(DocumentStatus.REQ_INPROGRESS);
			}


			if (originalRequisitionHeader != null) {
				if (originalRequisitionHeader.getRequisitionType().equals("M")) {
					requisitionLine.setStatus(DocumentStatus.REQ_PLANNING) ;
					requisitionLine.setBlanketOrder("") ;
				}
				else
				{
					requisitionLine.setBlanketOrder("");
				}
			}

			if (splitType != null){
				requisitionLine.setReqType(splitType);
			}else{
				requisitionLine.setReqType(originalRequisitionLine.getReqType());
			}

			requisitionLine.setCommentFlag(originalRequisitionLine.getCommentFlag());
			requisitionLine.setTaxPercent(originalRequisitionLine.getTaxPercent());
			requisitionLine.setTaxAmount(originalRequisitionLine.getTaxAmount());
			requisitionLine.setShippingTaxAmt(originalRequisitionLine.getShippingTaxAmt());
			requisitionLine.setOtherTaxAmount(originalRequisitionLine.getOtherTaxAmount());
			//requisitionLine.setReqType(originalRequisitionHeader.getRequisitionType());
			String catalogId = HiltonUtility.ckNull(originalRequisitionLine.getCatalogId());
			String itemSource = HiltonUtility.ckNull(originalRequisitionLine.getItemSource());
			if(itemSource.equals("INV")){
				saveItem = false;
				inventoryItem = true;
				requisitionLine.setCatalogId(originalRequisitionLine.getCatalogId());
				requisitionLine.setItemSource(originalRequisitionLine.getItemSource());
			}else if(itemSource.equals("CAT")){
				saveItem = false;
				catalogItem = true;
				requisitionLine.setCatalogId(originalRequisitionLine.getCatalogId());
				requisitionLine.setItemSource(originalRequisitionLine.getItemSource());

			}else{
				if(!catalogId.isEmpty() && requisitionLine.getReqType().equals("M") && catalogId.equalsIgnoreCase("VINIMAYA")){
					requisitionLine.setCatalogId("VINIMAYA");
					requisitionLine.setItemSource("XNO");
				}else{
					requisitionLine.setCatalogId(originalRequisitionLine.getCatalogId());
					requisitionLine.setItemSource(originalRequisitionLine.getItemSource());
				}
			}

			requisitionLine.setLineTotal(originalRequisitionLine.getLineTotal());
			requisitionLine.setTaxOvr(originalRequisitionLine.getTaxOvr());
			requisitionLine.setDiscOvr(originalRequisitionLine.getDiscOvr());
			requisitionLine.setShipOvr(originalRequisitionLine.getShipOvr());
			requisitionLine.setOtherOvr(originalRequisitionLine.getOtherOvr());
			requisitionLine.setShiptoFlag(originalRequisitionLine.getShiptoFlag());
			requisitionLine.setMfgName(originalRequisitionLine.getMfgName());
			requisitionLine.setModelNumber(originalRequisitionLine.getModelNumber());
			if(organizationId.equalsIgnoreCase("HOY08P") && originalRequisitionLine.getUdf1Code().equalsIgnoreCase("Y"))
			{
				requisitionLine.setUdf1Code("N");
			}
			else
			{
				requisitionLine.setUdf1Code(originalRequisitionLine.getUdf1Code());
			}
			requisitionLine.setUdf2Code(originalRequisitionLine.getUdf2Code());
			requisitionLine.setUdf3Code(originalRequisitionLine.getUdf3Code());
			requisitionLine.setUdf4Code(originalRequisitionLine.getUdf4Code());
			requisitionLine.setUdf5Code(originalRequisitionLine.getUdf5Code());
			requisitionLine.setUdf6Code(originalRequisitionLine.getUdf6Code());
			requisitionLine.setUdf7Code(originalRequisitionLine.getUdf7Code());
			requisitionLine.setUdf8Code(originalRequisitionLine.getUdf8Code());
			requisitionLine.setUdf9Code(originalRequisitionLine.getUdf9Code());
			requisitionLine.setUdf10Code(originalRequisitionLine.getUdf10Code());
			requisitionLine.setMemoLine(originalRequisitionLine.getMemoLine());

			//requisitionLine.setRqLineKey(originalRequisitionLine.getRqLineKey());
			requisitionLine.setRqLineKey("0");
			requisitionLine.setReceiptRequired(originalRequisitionLine.getReceiptRequired());
			requisitionLine.setRoFlag(originalRequisitionLine.getRoFlag());
			requisitionLine.setRouting(originalRequisitionLine.getRouting());

			if(originalRequisitionHeader.getKit().equalsIgnoreCase("Y") && HiltonUtility.isEmpty(originalRequisitionLine.getItemLocation())){
				requisitionLine.setItemLocation(originalRequisitionHeader.getItemLocation());
			} else {
				requisitionLine.setItemLocation(originalRequisitionLine.getItemLocation());
			}
			if (splitLocation != null) {
				requisitionLine.setItemLocation(splitLocation);
			}
			requisitionLine.setDescription(originalRequisitionLine.getDescription());
			//String createdFrom = (String) incomingRequest.get("createdFrom") ;
			if (createdFrom != null && createdFrom.equalsIgnoreCase("SOURCING")) {
				requisitionLine.setIcLineHistory(originalRequisitionLine.getIcLineHistory()) ;
			} else {
				requisitionLine.setIcLineHistory(new BigDecimal(ukg.getUniqueKey().toString()));
			}
			requisitionLine.setTaxCode(originalRequisitionLine.getTaxCode());
			requisitionLine.setVendorId(originalRequisitionLine.getVendorId());
			requisitionLine.setVendorName(originalRequisitionLine.getVendorName());
			requisitionLine.setRequisitionerCode(userId);
			requisitionLine.setDepartmentCode(originalRequisitionLine.getDepartmentCode());
			requisitionLine.setUseTaxable(originalRequisitionLine.getUseTaxable());
			requisitionLine.setUseTaxAmount(originalRequisitionLine.getUseTaxAmount());
			requisitionLine.setUseTaxCode(originalRequisitionLine.getUseTaxCode());
			requisitionLine.setUseTaxPercent(originalRequisitionLine.getUseTaxPercent());
			requisitionLine.setShelfLifeRqd(originalRequisitionLine.getShelfLifeRqd());


			/**  the following values should be set back to their defaults, as requested in incident #7841  **/
			//requisitionLine.setSplits(originalRequisitionLine.getSplits());
			requisitionLine.setSplits("N");
			//requisitionLine.setUmFactor(originalRequisitionLine.getUmFactor());
			requisitionLine.setUmFactor(new BigDecimal(1));
			//requisitionLine.setAutoRelease(originalRequisitionLine.getAutoRelease());
			requisitionLine.setAutoRelease(new BigDecimal(0));
			//requisitionLine.setLastQtyEntered(new BigDecimal("0"));
			requisitionLine.setLastQtyEntered(originalRequisitionLine.getLastQtyEntered());
			requisitionLine.setDuomQuantity(originalRequisitionLine.getDuomQuantity()) ;
			requisitionLine.setDuomUmCode(originalRequisitionLine.getDuomUmCode()) ;
			requisitionLine.setAssignedBuyer("UNASSIGNED");
			//requisitionLine.setAllocated(originalRequisitionLine.getAllocated());
			requisitionLine.setAllocated(new BigDecimal(0));
			requisitionLine.setBackordered(new BigDecimal(0));
			requisitionLine.setIcXls(new BigDecimal(0));
			
			String fromPage =(String)incomingRequest.get("frompage") ;
			if (fromPage == null)  fromPage = "" ;
			if (fromPage.equals("sourcing"))
			{
				originalRequisitionHeader.setPriorityCode(HiltonUtility.ckNull(originalRequisitionLine.getUdf2Code()));
				originalRequisitionHeader.setUdf7Code(HiltonUtility.ckNull(originalRequisitionLine.getUdf3Code()));
				requisitionLine.setRequiredDate(originalRequisitionLine.getRequiredDate());
				requisitionLine.setChemicalItemNumber(originalRequisitionLine.getChemicalItemNumber());
				incomingRequest.put("requisitionHeader", originalRequisitionHeader);
			}
			requisitionLine.setMsrNumber("");
			
			Date dateToday = Dates.getSqlDate("");
			requisitionLine.setDateEntered(dateToday);
			requisitionLine.setLastChgDate(dateToday);
			requisitionLine.setAssignedDate(dateToday);
			requisitionLine.setRequiredDate(dateToday);
			
			if(!saveItem){
				if(inventoryItem){
					Map newIncomingRequest = new HashMap();
					newIncomingRequest.put("dbsession", incomingRequest.get("dbsession"));
					//newIncomingRequest.put("LDAPConnection", incomingRequest.get("LDAPConnection"));
					newIncomingRequest.put("InvItem_itemNumber", HiltonUtility.ckNull(requisitionLine.getItemNumber()));
					newIncomingRequest.put("InvLocation_itemLocation", HiltonUtility.ckNull(requisitionLine.getItemLocation()));
					newIncomingRequest.put("organizationId", incomingRequest.get("organizationId"));
					newIncomingRequest.put("RequisitionHeader_requisitionType", HiltonUtility.ckNull(requisitionLine.getReqType()));
					newIncomingRequest.put("userTimeZone", incomingRequest.get("userTimeZone"));
					newIncomingRequest.put("icHeader",  HiltonUtility.ckNull(requisitionLine.getIcReqHeader().toString()));
					newIncomingRequest.put("icLine",  HiltonUtility.ckNull(requisitionLine.getIcReqLine().toString()));
					newIncomingRequest.put("quantity",  HiltonUtility.ckNull(requisitionLine.getQuantity().toString()));
					newIncomingRequest.put("requisitionLine", requisitionLine);
					newIncomingRequest.put("fromCloneProcedure", "Y");
					PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)newIncomingRequest.get("organizationId"));
					PuridiomProcess process = processLoader.loadProcess("invitem-retrieve-from-rqlclone.xml");
					newIncomingRequest.put("createAction", "SAVE");
					if("M".equalsIgnoreCase((String)newIncomingRequest.get("RequisitionHeader_requisitionType")))
					{
						newIncomingRequest.put("InvVendor.primaryVendor", "Y");
						newIncomingRequest.put("RequisitionHeader_requisitionType", newIncomingRequest.get("RequisitionHeader_requisitionType"));
						newIncomingRequest.put("InvVendor.itemNumber", newIncomingRequest.get("InvItem_itemNumber"));
					}
					process.executeProcess(newIncomingRequest);
					
					if (process.getStatus() < Status.SUCCEEDED) {
						throw new Exception("InvItemLookup failed.");
					}
					RequisitionLine newReqLine = (RequisitionLine)newIncomingRequest.get("requisitionLine");
					saveItem =true;
					if(newIncomingRequest.containsKey("invItemStatus")){
						if(HiltonUtility.ckNull((String)newIncomingRequest.get("invItemStatus")).equals("inactive")){
							newReqLine.setItemSource("INO");
						} else {
							newReqLine.setItemSource("INV");
						}
					} else {
						newReqLine.setItemSource("INO");
					}
					requisitionLine = newReqLine;
				} else if(catalogItem){
					Map newIncomingRequest = new HashMap();
					newIncomingRequest.put("dbsession", incomingRequest.get("dbsession"));
					//newIncomingRequest.put("LDAPConnection", incomingRequest.get("LDAPConnection"));
					newIncomingRequest.put("CatalogItem_itemNumber", HiltonUtility.ckNull(requisitionLine.getItemNumber()));
					newIncomingRequest.put("CatalogItem_catalogId", HiltonUtility.ckNull(requisitionLine.getCatalogId()));
					newIncomingRequest.put("organizationId", incomingRequest.get("organizationId"));
					newIncomingRequest.put("RequisitionHeader_requisitionType", HiltonUtility.ckNull(requisitionLine.getReqType()));
					newIncomingRequest.put("userTimeZone", incomingRequest.get("userTimeZone"));
					newIncomingRequest.put("icHeader",  HiltonUtility.ckNull(requisitionLine.getIcReqHeader().toString()));
					newIncomingRequest.put("icLine",  HiltonUtility.ckNull(requisitionLine.getIcReqLine().toString()));
					newIncomingRequest.put("quantity",  HiltonUtility.ckNull(requisitionLine.getQuantity().toString()));
					newIncomingRequest.put("requisitionLine", requisitionLine);
					newIncomingRequest.put("fromCloneProcedure", "Y");
					PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)newIncomingRequest.get("organizationId"));
					PuridiomProcess process = processLoader.loadProcess("catalogitem-retrieve-from-rqlclone.xml");
					newIncomingRequest.put("createAction", "SAVE");
					if("M".equalsIgnoreCase((String)newIncomingRequest.get("RequisitionHeader_requisitionType")))
					{
						newIncomingRequest.put("InvVendor.primaryVendor", "Y");
						newIncomingRequest.put("RequisitionHeader_requisitionType", newIncomingRequest.get("RequisitionHeader_requisitionType"));
						newIncomingRequest.put("InvVendor.itemNumber", newIncomingRequest.get("InvItem_itemNumber"));
					}
					process.executeProcess(newIncomingRequest);
					
					if (process.getStatus() < Status.SUCCEEDED) {
						throw new Exception("InvItemLookup failed.");
					}
					RequisitionLine newReqLine = (RequisitionLine)newIncomingRequest.get("requisitionLine");
					saveItem =true;
					if(newIncomingRequest.containsKey("catItemStatus")){
						if(HiltonUtility.ckNull((String)newIncomingRequest.get("catItemStatus")).equals("inactive")){
							newReqLine.setItemSource("CNO");
						} else {
							newReqLine.setItemSource("CAT");
						}
					} else {
						newReqLine.setItemSource("CNO");
					}
					requisitionLine = newReqLine;
				}
				incomingRequest.put("ClonedItemForSave", "false");
			}
			if(saveItem){
				incomingRequest.put("requisitionLine", requisitionLine);
				RequisitionLineAdd addTask = new RequisitionLineAdd();
				requisitionLine = (RequisitionLine) addTask.executeTask(incomingRequest);
				this.setStatus(addTask.getStatus()) ;
				incomingRequest.put("ClonedItemForSave", "true");
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
