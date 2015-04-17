/**
 * Created on Apr 12, 2004
 *
 * @author renzo project: HiltonPurchaseOrders
 *         com.tsa.puridiom.poline.tasks.PoLineCancelCheck.java
 */
package com.tsa.puridiom.poline.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class PoLineCancelCheck extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		try
		{
			Map incomingRequest = (Map) object;
			PoHeader poHeader = (PoHeader) incomingRequest.get("poHeader");
			PoLine poLine = (PoLine) incomingRequest.get("poLine");
			String poaction = (String) incomingRequest.get("poaction");
			String lineStatus = poLine.getStatus();
			String lastRevision = poHeader.getLastRevision();

			if (!lastRevision.equals("C"))
			{
				// this.setStatus(Status.FAILED);
				// throw new TsaException("There are Revisions against this
				// Order!");
			}
			String poStatus = poHeader.getStatus();

			if (poaction.equals("close"))
			{
				if (poStatus.equals(DocumentStatus.CLOSED))
				{
					this.setStatus(Status.FAILED);
					throw new TsaException("Order has Already been Closed!");
				}
				if (lineStatus.equals(DocumentStatus.CLOSED))
				{
					this.setStatus(Status.FAILED);
					throw new TsaException("Line has Already been Closed!");
				}
			} else if (poaction.equals("cancel"))
			{
				if (poStatus.equals(DocumentStatus.CANCELLED))
				{
					this.setStatus(Status.FAILED);
					throw new TsaException("Order has Already been Cancelled!");
				}
				if (lineStatus.equals(DocumentStatus.CANCELLED))
				{
					this.setStatus(Status.FAILED);
					throw new TsaException("Line has Already been Cancelled!");
				}
			}

			/*
			 * List receipts = (List)incomingRequest.get("poLineReceipts");
			 * if(receipts.size() > 0) { throw new TsaException("There have been
			 * receipts against this Item!"); }
			 */
			if (lineStatus.equals(DocumentStatus.RCV_PARTIALLYRECEIVED) || lineStatus.equals(DocumentStatus.RCV_RECEIVED) || ((poLine.getIcPoLine().compareTo(poLine.getIcLineKey()) == 0) && (poLine.getQtyReceived().compareTo(new BigDecimal(0)) > 0)))
			{
				this.setStatus(Status.FAILED);
				throw new TsaException("There have been receipts against this Item!");
			}
			this.setStatus(Status.SUCCEEDED);
		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return super.executeTask(object);
	}

}