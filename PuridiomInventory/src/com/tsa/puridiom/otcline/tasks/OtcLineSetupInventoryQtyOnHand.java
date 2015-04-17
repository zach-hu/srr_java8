/*
 * Created on Jun 30, 2004
 *
 * @author renzo
 * project: HiltonInventory
 * com.tsa.puridiom.otcline.tasks.OtcLineSetupInventoryQtyOnHand.java
 *
 */
package com.tsa.puridiom.otcline.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.DisbHeader;
import com.tsa.puridiom.entity.DisbLine;
import com.tsa.puridiom.entity.InvBinLocation;
import com.tsa.puridiom.entity.InvLocation;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class OtcLineSetupInventoryQtyOnHand extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		try
		{
			Map incomingRequest = (Map)object;
			InvBinLocation bin = (InvBinLocation)incomingRequest.get("invBinLocation");
			InvLocation location = (InvLocation)incomingRequest.get("invLocation");
			DisbLine line = (DisbLine)incomingRequest.get("disbLine");
			DisbHeader disbHeader = (DisbHeader)incomingRequest.get("disbHeader");
			if(line == null)
			{
				this.setStatus(Status.FAILED);
				throw new TsaException("Disbursement Line Entity was not found!");
			}
			if(disbHeader.getStatus().compareTo(DocumentStatus.INV_INPROGRESS) != 0)
			{
				BigDecimal locQtyOnHand = location.getQtyOnHand().subtract(line.getQuantity());
				location.setQtyOnHand(locQtyOnHand);
				BigDecimal binQtyOnHand = bin.getQtyOnHand().subtract(line.getQuantity());
				bin.setQtyOnHand(binQtyOnHand);

				BigDecimal duomQtyOnHand = location.getDuomQtyOnHand().subtract(line.getDuomQuantity());
				location.setDuomQtyOnHand(duomQtyOnHand);
				BigDecimal binDuomQtyOnHand = bin.getDuomQtyOnHand().subtract(line.getDuomQuantity());
				bin.setDuomQtyOnHand(binDuomQtyOnHand);

				line.setStatus(DocumentStatus.INV_DISBURSED);
			}
			else
			{
				BigDecimal locQtyAlloc = location.getQtyAlloc().add(line.getQuantity());
				location.setQtyAlloc(locQtyAlloc);
				BigDecimal binQtyAlloc = bin.getQtyAlloc().add(line.getQuantity());
				bin.setQtyAlloc(binQtyAlloc);

				BigDecimal duomQtyAlloc = location.getDuomQtyAlloc().add(line.getDuomQuantity());
				location.setDuomQtyAlloc(duomQtyAlloc);
				BigDecimal binDuomQtyAlloc = bin.getDuomQtyAlloc().add(line.getDuomQuantity());
				bin.setDuomQtyAlloc(binDuomQtyAlloc);

				line.setStatus(DocumentStatus.INV_INPROGRESS);
			}
			incomingRequest.put("invBinLocation", bin);
			incomingRequest.put("invLocation", location);


			incomingRequest.put("disbLine", line);
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
