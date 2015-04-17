/*
 * Created on Dec 9, 2004 TODO To change the template for this generated file go
 * to Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.requisition.tasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.currcode.CurrencyManager;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author renzo TODO To change the template for this generated type comment go
 *         to Window - Preferences - Java - Code Style - Code Templates
 */
public class RequisitionValidationRulesData extends Task
{

	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		try
		{
			Map incomingRequest = (Map) object;
			String organizationId = (String) incomingRequest.get("organizationId");
			RequisitionHeader header = (RequisitionHeader) incomingRequest.get("requisitionHeader");
			List lineItems = (List) incomingRequest.get("lineitems");

			if (header == null)
			{
				this.setStatus(Status.FAILED);
				throw new TsaException("Requisition Header was not found!");
			}
			else
			{
				BigDecimal totalUSD = new BigDecimal(0);
		    	totalUSD = (BigDecimal) CurrencyManager.getInstance(organizationId).convertPrice(header.getTotal(), header.getCurrencyCode(), "USD");
				header.setTotalUSD(totalUSD);
			}

			if (organizationId.equalsIgnoreCase("bsc04p") && !HiltonUtility.isEmpty(header.getUdf6Code()) && header.getUdf6Code().substring(1).indexOf("X") < 0)
				incomingRequest.put("req-rp-udf6-valid", "true");

			incomingRequest.put("lineItemsNoCancelled", this.getLineItemsNoCancelled(lineItems));
			incomingRequest.put("lineItemsSupplier", this.getLineItemsWithSupplier(lineItems));
			incomingRequest.put("lineItemsSupplier", this.getLineItemsWithSupplier(lineItems));
			incomingRequest.put("lineNonStandardItem", this.getLineNonStandardItem(lineItems, organizationId));
			incomingRequest.put("accountLineList", this.setUpLineAccounts(incomingRequest));
			incomingRequest.put("lineNonBlankUdf2", this.getLineNonBlankUdf2(lineItems));
			incomingRequest.put("header", header);
			boolean validationRequisitionLine = false;
			if(incomingRequest.containsKey("valType") && ((String)incomingRequest.get("valType")).equals("REQUISITIONLINE"))
			{
				validationRequisitionLine = true;
			}
			if(!validationRequisitionLine)
			{
				incomingRequest.put("Labels_moduleAccess", "REQUISITIONS");
			}else{
				incomingRequest.put("Labels_moduleAccess", "REQUISITIONLINE");
			}

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
				RequisitionLine reqLine = (RequisitionLine) iterator.next();

				if (!reqLine.getStatus().equals(DocumentStatus.CANCELLED))
				{
					linesNoCancelled.add(reqLine);
				}
			}
		}

		return linesNoCancelled;
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
			RequisitionLine line = (RequisitionLine) lineList.get(i);
			List acctLineList = line.getAccountList();
			if (acctLineList == null)
			{
				acctLineList = new ArrayList();
			}
			lineAccounts.add(acctLineList);
		}
		return lineAccounts;

	}

	private List getLineNonStandardItem(List lineItems, String organizationId)
	{
		List lineNonStandardItem = new ArrayList();

		if ((lineItems != null) && (!lineItems.isEmpty()))
		{
			for (Iterator iterator = lineItems.iterator(); iterator.hasNext();)
			{
				RequisitionLine reqLine = (RequisitionLine) iterator.next();

				if (organizationId.equalsIgnoreCase("bly07p"))
				{
					if (reqLine.getItemNumber().equalsIgnoreCase("") || reqLine.getItemNumber().startsWith("*"))
					{
						lineNonStandardItem.add(reqLine);
					}
				} else
				{
					if (HiltonUtility.isEmpty(reqLine.getCatalogId()))
					{
						lineNonStandardItem.add(reqLine);
					}
				}
			}
		}
		return lineNonStandardItem;
	}

	/**
	 * @param lineItems
	 * @return
	 */
	private List getLineItemsWithSupplier(List lineItems)
	{
		List linesWithSupplier = new ArrayList();

		if ((lineItems != null) && (!lineItems.isEmpty()))
		{
			for (Iterator iterator = lineItems.iterator(); iterator.hasNext();)
			{
				RequisitionLine reqLine = (RequisitionLine) iterator.next();

				if (!HiltonUtility.isEmpty(reqLine.getVendorId()))
				{
					linesWithSupplier.add(reqLine);
				}
			}
		}

		return linesWithSupplier;
	}

	private List getLineNonBlankUdf2(List lineItems)
	{
		List lineNonBlankUdf2 = new ArrayList();

		if ((lineItems != null) && (!lineItems.isEmpty()))
		{
			for (Iterator iterator = lineItems.iterator(); iterator.hasNext();)
			{
				RequisitionLine reqLine = (RequisitionLine) iterator.next();

				if (!HiltonUtility.isEmpty(reqLine.getUdf2Code()))
				{
					lineNonBlankUdf2.add(reqLine);
				}
			}
		}
		return lineNonBlankUdf2;
	}
}
