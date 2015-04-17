/*
 * Created on Nov 14, 2003 
 */
package com.tsa.puridiom.disbline.tasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.DisbHeader;
import com.tsa.puridiom.entity.DisbLine;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.UniqueKeyGenerator;

/**
 * @author renzo
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class DisbLineStandardCreate extends Task
{
	/* (non-Javadoc)
	 * @see com.tsagate.common.processengine.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		RequisitionLine reqLine = (RequisitionLine)incomingRequest.get("requisitionLine");
		DisbHeader disbHeader = (DisbHeader)incomingRequest.get("disbHeader");
		List disbLines = new ArrayList();
		DisbLine disbLine = new DisbLine();
		
		disbLine.setItemLocation(reqLine.getItemLocation());
		incomingRequest.put("InvLocation_itemLocation", disbLine.getItemLocation());
		disbLine.setItemNumber(reqLine.getItemNumber());
		incomingRequest.put("InvLocation_itemNumber", disbLine.getItemNumber());
		incomingRequest.put("InvItem_itemNumber", disbLine.getItemNumber());
		disbLine.setIcDsbHeader(disbHeader.getIcDsbHeader());
		disbLine.setDisbNumber(disbHeader.getDisbNumber());
		disbLine.setIcReqLine(reqLine.getIcReqLine());
		disbLine.setDescription(reqLine.getDescription());
		disbLine.setShiptoFlag(reqLine.getShiptoFlag());
		disbLine.setUmCode(reqLine.getUmCode());
		disbLine.setUmFactor(reqLine.getUmFactor());
		disbLine.setQuantity(reqLine.getQuantity());
		disbLine.setUnitPrice(reqLine.getUnitPrice());
		BigDecimal linetotal = reqLine.getUnitPrice().multiply(reqLine.getQuantity());
		disbLine.setLineTotal(linetotal);
		disbLine.setAssetCode(reqLine.getAsset());
		disbLine.setCommodityCode(reqLine.getCommodityCode());
		UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
		BigDecimal icDsbLine = new BigDecimal(ukg.getUniqueKey().toString());
		disbLine.setIcDsbLine(icDsbLine);
		disbLine.setStatus(DocumentStatus.INV_INPROGRESS);
		
		incomingRequest.put("Account_icHeader", reqLine.getIcReqHeader().toString());
		incomingRequest.put("Account_icLine", reqLine.getIcReqLine().toString());
		BigDecimal qtyRequested = reqLine.getQuantity();
		incomingRequest.put("qtyRequested", qtyRequested);
		BigDecimal qtyToDisburse = qtyRequested;
		incomingRequest.put("qtyToDisburse", qtyToDisburse);
		disbLines.add(disbLine);
		incomingRequest.put("disbLines", disbLines);
		return disbLine;
	}
}
