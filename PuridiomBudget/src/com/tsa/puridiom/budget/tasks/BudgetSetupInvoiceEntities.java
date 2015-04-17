/**
 *
 */
package com.tsa.puridiom.budget.tasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.InvoiceHeader;
import com.tsa.puridiom.entity.InvoiceLine;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.invoiceline.tasks.InvoiceLineRetrieveQtyInvoicedByRelatedPoLine;
import com.tsa.puridiom.poline.tasks.PoLineRetrieveById;
import com.puridiom.service.budget.BudgetAction;
import com.puridiom.service.budget.BudgetEntity;
import com.puridiom.service.budget.BudgetUtils;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author Johnny
 */
public class BudgetSetupInvoiceEntities extends Task
{
	/*
	 * (non-Javadoc)
	 *
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{
			InvoiceHeader invoiceHeader = (InvoiceHeader) incomingRequest.get("header");
			List lineItems = (List) incomingRequest.get("lineItems");
			List budgetActions = (List) incomingRequest.get("budgetServiceActions");
			String organizationId = (String) incomingRequest.get("organizationId");
			String userId = (String) incomingRequest.get("userId");
			DBSession dbsession = (DBSession) incomingRequest.get("dbsession");
			List budgetEntities = new ArrayList();
			Map newIncomingRequest = new HashMap();

			incomingRequest.put("budgetIcHeader", invoiceHeader.getIcIvcHeader());
			incomingRequest.put("budgetServiceYear", invoiceHeader.getFiscalYear());
			incomingRequest.put("budgetCurrency", invoiceHeader.getCurrencyCode());

			newIncomingRequest.put("organizationId", organizationId);
			newIncomingRequest.put("userId", userId);
			newIncomingRequest.put("dbsession", dbsession);

			for (Iterator iterator = budgetActions.iterator(); iterator.hasNext();)
			{
				BudgetAction budgetAction = (BudgetAction) iterator.next();

				for (int i = 0; i < lineItems.size(); i++)
				{
					Object lineItem = lineItems.get(i);

					InvoiceLine invoiceLine = (InvoiceLine) lineItem;
					BudgetEntity budgetEntity = null;
					PoLine poLine = null;
					String authority = "";

					if (invoiceLine.getLineTotal().compareTo(new BigDecimal(0)) > 0)
					{
						if (invoiceLine.getIcPoLine().compareTo(new BigDecimal(0)) != 0)
						{
							poLine = this.getPoLineFromInvoiceLine(invoiceLine, newIncomingRequest);
						}

						authority = this.getAuthority(organizationId, invoiceHeader, invoiceLine, poLine);

						if (budgetAction.getType().equalsIgnoreCase("PO"))
						{
							if (poLine != null)
							{
								BigDecimal alternateTotal = this.getAlternateTotal(invoiceLine, poLine, newIncomingRequest);

								budgetEntity = BudgetEntity.buildBudgetEntity(poLine, budgetAction, authority, alternateTotal);
							}
						} else
						{
							budgetEntity = BudgetEntity.buildBudgetEntity(invoiceLine, budgetAction, authority);
						}
					}

					if (budgetEntity != null)
					{
						budgetEntities.add(budgetEntity);
					}
				}
			}

			result = budgetEntities;

			this.setStatus(Status.SUCCEEDED);

		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);

			Log.error(this, "BudgetSetupInvoiceEntities error " + e.getMessage());

			throw e;
		}

		return result;
	}

	private PoLine getPoLineFromInvoiceLine(InvoiceLine invoiceLine, Map incomingRequest) throws Exception
	{
		PoLine poLine;
		PoLineRetrieveById poLineRetrieveById = new PoLineRetrieveById();

		incomingRequest.put("PoLine_icPoLine", invoiceLine.getIcRelPoLine().toString());

		poLine = (PoLine) poLineRetrieveById.executeTask(incomingRequest);

		if (poLineRetrieveById.getStatus() == Status.FAILED)
		{
			throw new TsaException("Error loading Po Line from Invoice Line " + invoiceLine.getIcPoLine());
		}

		return poLine;
	}

	private String getAuthority(String organizationId, InvoiceHeader invoiceHeader, InvoiceLine invoiceLine, PoLine poLine)
	{
		String authority = "";
		boolean isAuthRequisitioner = BudgetUtils.isAuthorityRequisitioner(organizationId);

		authority = (isAuthRequisitioner) ? invoiceHeader.getEnteredBy() : invoiceHeader.getDepartmentCode();

		if (poLine != null)
		{
			authority = (isAuthRequisitioner) ? poLine.getRequisitionerCode() : poLine.getDepartmentCode();
		}

		return authority;
	}

	private BigDecimal getAlternateTotal(InvoiceLine invoiceLine, PoLine poLine, Map incomingRequest) throws Exception
	{
		BigDecimal alternateTotal = invoiceLine.getLineTotal();
		BigDecimal poLineDifference = poLine.getLineTotal().subtract(poLine.getExpensed()).setScale(2, BigDecimal.ROUND_HALF_UP);
		boolean isFullyInvoiced = this.isFullyInvoiced(invoiceLine, poLine, incomingRequest);

		if (poLineDifference.compareTo(new BigDecimal(0)) <= 0)
		{
			alternateTotal = new BigDecimal(0);

		} else if ((alternateTotal.compareTo(poLineDifference) > 0) || isFullyInvoiced)
		{
			alternateTotal = poLineDifference;
		}

		return alternateTotal;
	}

	private boolean isFullyInvoiced(InvoiceLine invoiceLine, PoLine poLine, Map incomingRequest) throws Exception
	{
		boolean fullInvoiced = false;
		BigDecimal quantityInvoiced = new BigDecimal(0);

		InvoiceLineRetrieveQtyInvoicedByRelatedPoLine invoiceLineRetrieveQtyInvoicedByRelatedPoLine = new InvoiceLineRetrieveQtyInvoicedByRelatedPoLine();

		incomingRequest.put("InvoiceLine_icRelPoLine", poLine.getIcPoLine().toString());

		quantityInvoiced = HiltonUtility.ckNull((BigDecimal) invoiceLineRetrieveQtyInvoicedByRelatedPoLine.executeTask(incomingRequest));

		if (quantityInvoiced.compareTo(poLine.getQuantity()) >= 0)
		{
			fullInvoiced = true;
		}

		return fullInvoiced;
	}

}