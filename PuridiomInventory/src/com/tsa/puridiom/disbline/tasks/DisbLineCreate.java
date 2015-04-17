/*
 * Created on Nov 17, 2003
 */
package com.tsa.puridiom.disbline.tasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.DisbHeader;
import com.tsa.puridiom.entity.DisbLine;
import com.tsa.puridiom.entity.InvBinLocation;
import com.tsa.puridiom.entity.InvItem;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsa.puridiom.disbline.tasks.DisbLineAdd;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.UniqueKeyGenerator;

/**
 * @author renzo
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class DisbLineCreate extends Task
{
	/* (non-Javadoc)
	 * @see com.tsagate.common.processengine.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		List disbLines = new ArrayList();
		try
		{
			Map incomingRequest = (Map)object;
			List bins = (List)incomingRequest.get("invBinLocations");
			List invBinQties = (List)incomingRequest.get("invBinQties");

			RequisitionLine reqLine = (RequisitionLine)incomingRequest.get("requisitionLine");
			DisbHeader disbHeader = (DisbHeader)incomingRequest.get("disbHeader");
			InvItem invItem = (InvItem) incomingRequest.get("invItem") ;

			for(int i = 0; i < bins.size(); i++)
			{
				InvBinLocation bin = (InvBinLocation)bins.get(i);
				DisbLine disbLine = new DisbLine();

				if (invItem != null) disbLine.setDuomUmCode(invItem.getDuomUmCode()) ;

				BigDecimal icDsbHeader = disbHeader.getIcDsbHeader();
				disbLine.setIcDsbHeader(icDsbHeader);
				disbLine.setDisbNumber(disbHeader.getDisbNumber());
				UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
				BigDecimal icDsbLine = new BigDecimal(ukg.getUniqueKey().toString());
				disbLine.setIcDsbLine(icDsbLine);
				disbLine.setStatus(DocumentStatus.INV_INPROGRESS);

				incomingRequest.put("newAccount_icHeader", icDsbHeader.toString());
				incomingRequest.put("newAccount_icLine", icDsbLine.toString());
				incomingRequest.put("newDocComment_icHeader", icDsbHeader.toString());
				incomingRequest.put("newDocComment_icLine", icDsbLine.toString());

				//values from requisitionLine
				this.setReqLineValues(disbLine, reqLine, incomingRequest);
				this.setBinLinevalues(disbLine, bin, (BigDecimal)invBinQties.get(i));
				incomingRequest.put("disbLine", disbLine);

				DisbLineAdd add = new DisbLineAdd();
				add.executeTask(incomingRequest);
				this.setStatus(add.getStatus());
				if(this.getStatus() != Status.SUCCEEDED)
				{
					i = bins.size();
				}
				else
				{
					disbLines.add(disbLine);
				}
			}

		}
		catch(Exception e)
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
		}
		return disbLines;
	}

	/**
	 * setBinLinevalues
	 * @param disbLine
	 * @param bin
	 */
	private void setBinLinevalues(DisbLine disbLine, InvBinLocation bin, BigDecimal binQty)
	{
		disbLine.setQuantity(binQty);
		BigDecimal lineTotal = binQty.multiply(disbLine.getUnitPrice()).setScale(2, BigDecimal.ROUND_UP);
		disbLine.setLineTotal(lineTotal);
		disbLine.setAisle(bin.getAisle());
		disbLine.setLocrow(bin.getLocrow());
		disbLine.setTier(bin.getTier());
		disbLine.setLot(bin.getLot());
		disbLine.setBin(bin.getBin());
		disbLine.setUdf1Code(bin.getUdf1Code());
		disbLine.setVendorId(bin.getVendorId());
		disbLine.setManufactNo(bin.getManufactNo());
		disbLine.setIcRc(bin.getIcRc());
	}

	private void setReqLineValues(DisbLine disbLine, RequisitionLine reqLine, Map incomingRequest)
	{
		disbLine.setItemNumber(reqLine.getItemNumber());
		disbLine.setItemLocation(reqLine.getItemLocation());
		disbLine.setItemSource(reqLine.getItemSource());
		disbLine.setUmCode(reqLine.getUmCode());
		disbLine.setUmFactor(reqLine.getUmFactor());
		BigDecimal factor = (BigDecimal)incomingRequest.get("InvItem_factor");
		if (factor == null)
		{
			factor = new BigDecimal(1);
		}
		if(factor.compareTo(new BigDecimal(0)) == 0)
		{
			factor = new BigDecimal(1);
		}
		BigDecimal unitPrice = reqLine.getUnitPrice().divide(factor, 2, BigDecimal.ROUND_UP);
		disbLine.setUnitPrice(unitPrice);
		disbLine.setCommentFlag(reqLine.getCommentFlag());
		disbLine.setCommodityCode(reqLine.getCommodityCode());
		disbLine.setIcReqLine(reqLine.getIcReqLine());
		disbLine.setShiptoFlag(reqLine.getShiptoFlag());
		disbLine.setAssetCode(reqLine.getAsset());
		disbLine.setDescription(reqLine.getDescription());
		disbLine.setIcLineHistory(reqLine.getIcLineHistory());

		BigDecimal	icAccount = reqLine.getIcAccount() ;
		if (icAccount == null) icAccount = new BigDecimal("0") ;
		if (icAccount.compareTo(new BigDecimal("0")) != 0) {
			icAccount = disbLine.getIcDsbLine() ;
		}
		disbLine.setIcAccount(icAccount) ;
	}
}