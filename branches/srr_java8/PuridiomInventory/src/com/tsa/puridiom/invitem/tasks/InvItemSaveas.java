package com.tsa.puridiom.invitem.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Utility;
import java.util.*;

public class InvItemSaveas extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
		    String	newItemNumber = (String) incomingRequest.get("newItemNumber");
            String  userTimeZone = (String) incomingRequest.get("userTimeZone");
            Date d_today = Dates.getCurrentDate(userTimeZone);

			if (Utility.isEmpty(newItemNumber)) {
			    throw new Exception("The value for newItemNumber cannot be empty!");
			}

			/* Expects incoming request to contain invItem */
			InvItem	originalInvItem = (InvItem) incomingRequest.get("invItem");
			InvItem	invItem = new InvItem();

			invItem.setItemNumber(newItemNumber);
			invItem.setCommodity(originalInvItem.getCommodity());
			invItem.setIcText(originalInvItem.getIcText());
			invItem.setUnitOfOrder(originalInvItem.getUnitOfOrder());
			invItem.setCost(originalInvItem.getCost());
			invItem.setTaxable(originalInvItem.getTaxable());
			invItem.setAbcCode(originalInvItem.getAbcCode());
			invItem.setDateEntered(d_today);
			invItem.setDateExpires(d_today);
			invItem.setStatus(originalInvItem.getStatus());
			invItem.setOwner(originalInvItem.getOwner());
			invItem.setSource(originalInvItem.getSource());
			invItem.setUnitOfIssue(originalInvItem.getUnitOfIssue());
			invItem.setFactor(originalInvItem.getFactor());
			invItem.setAverageCost(originalInvItem.getAverageCost());
			invItem.setUdf1Code(originalInvItem.getUdf1Code());
			invItem.setUdf2Code(originalInvItem.getUdf2Code());
			invItem.setUdf3Code(originalInvItem.getUdf3Code());
			invItem.setUdf4Code(originalInvItem.getUdf4Code());
			invItem.setUdf5Code(originalInvItem.getUdf5Code());
			invItem.setUdf6Code(originalInvItem.getUdf6Code());
			invItem.setUdf7Code(originalInvItem.getUdf7Code());
			invItem.setUdf8Code(originalInvItem.getUdf8Code());
			invItem.setUdf9Code(originalInvItem.getUdf9Code());
			invItem.setUdf10Code(originalInvItem.getUdf10Code());
			invItem.setUdf11Code(originalInvItem.getUdf11Code());
			invItem.setUdf12Code(originalInvItem.getUdf12Code());
			invItem.setUdf13Code(originalInvItem.getUdf13Code());
			invItem.setUdf14Code(originalInvItem.getUdf14Code());
			invItem.setUdf15Code(originalInvItem.getUdf15Code());
			invItem.setVariance(originalInvItem.getVariance());
			invItem.setImageFile(originalInvItem.getImageFile());
			invItem.setIssueCost(originalInvItem.getIssueCost());
			invItem.setLastCost(originalInvItem.getLastCost());
			invItem.setIcNotes(originalInvItem.getIcNotes());
			invItem.setPoNumber(originalInvItem.getPoNumber());
			invItem.setKit(originalInvItem.getKit());
			invItem.setMohMonths(originalInvItem.getMohMonths());
			invItem.setEoqMonths(originalInvItem.getEoqMonths());
			invItem.setMohTot(originalInvItem.getMohTot());
			invItem.setEoqTot(originalInvItem.getEoqTot());
			invItem.setChargable(originalInvItem.getChargable());
			invItem.setMaxReqQty(originalInvItem.getMaxReqQty());
			invItem.setActionCode(originalInvItem.getActionCode());
			invItem.setRestrictedItem(originalInvItem.getRestrictedItem());
			invItem.setSumQtyOh(originalInvItem.getSumQtyOh());
			invItem.setSumBackorder(originalInvItem.getSumBackorder());
			invItem.setSumUsage(originalInvItem.getSumUsage());
			invItem.setSumEoq(originalInvItem.getSumEoq());
			invItem.setSumMinOh(originalInvItem.getSumMinOh());
			invItem.setSumQtyOrder(originalInvItem.getSumQtyOrder());
			invItem.setLastPo(originalInvItem.getLastPo());
			invItem.setLastPoDate(originalInvItem.getLastPoDate());
			invItem.setAssetCode(originalInvItem.getAssetCode());
			invItem.setMinReqQty(originalInvItem.getMinReqQty());
			invItem.setReceiptRequired(originalInvItem.getReceiptRequired());
			invItem.setPullIncrement(originalInvItem.getPullIncrement());
			invItem.setUsageRecalc(originalInvItem.getUsageRecalc());
			invItem.setBuyerCode(originalInvItem.getBuyerCode());
			invItem.setAribaExport(originalInvItem.getAribaExport());
			invItem.setAppointedFlag(originalInvItem.getAppointedFlag());
			invItem.setSsInterface(originalInvItem.getSsInterface());
			invItem.setManagedBy(originalInvItem.getManagedBy());
			invItem.setDescription(originalInvItem.getDescription());
			invItem.setMfgName(originalInvItem.getMfgName()) ;
			invItem.setModelNumber(originalInvItem.getModelNumber()) ;
			invItem.setNsnNumber(originalInvItem.getNsnNumber()) ;
			invItem.setGfpProperty(originalInvItem.getGfpProperty()) ;
			invItem.setFgpProperty(originalInvItem.getFgpProperty()) ;
			invItem.setCapProperty(originalInvItem.getCapProperty()) ;
			invItem.setItemType(originalInvItem.getItemType()) ;
			invItem.setBarcodeData(originalInvItem.getBarcodeData()) ;
			invItem.setDrawingNo(originalInvItem.getDrawingNo()) ;
			invItem.setDuomUmCode(originalInvItem.getDuomUmCode()) ;
			invItem.setCritSparePart(originalInvItem.getCritSparePart());

			incomingRequest.put("invItem", invItem);

			InvItemAdd addTask = new InvItemAdd();
			invItem = (InvItem) addTask.executeTask(incomingRequest);

			if (addTask.getStatus() == Status.FAILED && !Utility.isEmpty((String) incomingRequest.get("errorMsg"))) {
			    //There must have been a unique constraint error.... set invItem back to original and continue
			    invItem = originalInvItem;
			    this.setStatus(Status.SUCCEEDED);
			} else {
			    this.setStatus(addTask.getStatus()) ;
			}

			result = invItem;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}