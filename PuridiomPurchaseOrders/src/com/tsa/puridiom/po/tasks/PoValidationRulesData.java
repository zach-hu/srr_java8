/*
 * Created on Dec 9, 2004
 */
package com.tsa.puridiom.po.tasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.currcode.CurrencyManager;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author renzo
 */
public class PoValidationRulesData extends Task
{

	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		try
		{
			Map incomingRequest = (Map) object;
			String organizationId = (String) incomingRequest.get("organizationId");
			PoHeader header = (PoHeader) incomingRequest.get("poHeader");
			List lineItems = (List) incomingRequest.get("lineitems");

			if (header == null)
			{
				this.setStatus(Status.FAILED);
				throw new TsaException("Purchase Order was not found!");
			}
			else
			{
				BigDecimal totalUSD = new BigDecimal(0);
		    	totalUSD = (BigDecimal) CurrencyManager.getInstance(organizationId).convertPrice(header.getTotal(), header.getCurrencyCode(), "USD");
				header.setTotalUSD(totalUSD);
			}

			incomingRequest.put("lineItemsNoCancelled", this.getLineItemsNoCancelled(lineItems));
			incomingRequest.put("lineNonStandardItem", this.getLineNonStandardItem(lineItems, organizationId));
			incomingRequest.put("accountLineList", this.setUpLineAccounts(incomingRequest));
			incomingRequest.put("header", header);

			this.setStatus(Status.SUCCEEDED);
		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return ret;
	}

	/**
	 * @param lineItems
	 * @return
	 */
	private List getLineItemsNoCancelled(List lineItems)
	{
		List linesNoCancelled = new ArrayList();

		if ((lineItems != null) && (!lineItems.isEmpty()))
		{
			for (Iterator iterator = lineItems.iterator(); iterator.hasNext();)
			{
				PoLine poLine = (PoLine) iterator.next();

				if (!poLine.getStatus().equals(DocumentStatus.CANCELLED))
				{
					linesNoCancelled.add(poLine);
				}
			}
		}

		return linesNoCancelled;
	}

	private List getLineNonStandardItem(List lineItems, String organizationId)
	{
		List lineNonStandardItem = new ArrayList();

		if ((lineItems != null) && (!lineItems.isEmpty()))
		{
			for (Iterator iterator = lineItems.iterator(); iterator.hasNext();)
			{
				PoLine poLine = (PoLine) iterator.next();

				if (organizationId.equalsIgnoreCase("bly07p"))
				{
					if (poLine.getItemNumber().equalsIgnoreCase("") || poLine.getItemNumber().startsWith("*"))
					{
						lineNonStandardItem.add(poLine);
					}
				} else
				{
					if (HiltonUtility.isEmpty(poLine.getCatalogId()))
					{
						lineNonStandardItem.add(poLine);
					}
				}
			}
		}
		return lineNonStandardItem;
	}

	/**
	 * @param incomingRequest
	 */
	private List setUpLineAccounts(Map incomingRequest)
	{
		List lineAccounts = new ArrayList();
		List lineList = (List) incomingRequest.get("lineitems");
		for (int i = 0; i < lineList.size(); i++)
		{
			PoLine line = (PoLine) lineList.get(i);
			List acctLineList = line.getAccountList();
			if (acctLineList == null)
			{
				acctLineList = new ArrayList();
			}
			lineAccounts.add(acctLineList);
		}
		return lineAccounts;

	}
}
