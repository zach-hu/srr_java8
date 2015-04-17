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

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsa.puridiom.invoiceline.tasks.InvoiceLineRetrieveQtyInvoicedByRelatedPoLine;
import com.tsa.puridiom.poline.tasks.PoLineRetrieveFromPoRevision;
import com.tsa.puridiom.requisitionline.tasks.RequisitionLineRetrieveById;
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
public class BudgetSetupPoEntities extends Task
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
			PoHeader poHeader = (PoHeader) incomingRequest.get("header");
			List lineItems = (List) incomingRequest.get("lineItems");
			List budgetActions = (List) incomingRequest.get("budgetServiceActions");
			String organizationId = (String) incomingRequest.get("organizationId");
			String userId = (String) incomingRequest.get("userId");
			DBSession dbsession = (DBSession) incomingRequest.get("dbsession");
			List budgetEntities = new ArrayList();
			Map newIncomingRequest = new HashMap();
			List executeNextActions = new ArrayList();

			incomingRequest.put("budgetIcHeader", poHeader.getIcPoHeader());
			incomingRequest.put("budgetServiceYear", poHeader.getFiscalYear());
			incomingRequest.put("budgetCurrency", poHeader.getCurrencyCode());

			newIncomingRequest.put("organizationId", organizationId);
			newIncomingRequest.put("userId", userId);
			newIncomingRequest.put("dbsession", dbsession);

			for (Iterator iterator = budgetActions.iterator(); iterator.hasNext();)
			{
				BudgetAction budgetAction = (BudgetAction) iterator.next();

				for (int i = 0; i < lineItems.size(); i++)
				{
					Object lineItem = lineItems.get(i);

					if ((i + 1) > executeNextActions.size())
					{
						executeNextActions.add(new Boolean(true));
					}

					boolean executeAction = ((Boolean) executeNextActions.get(i)).booleanValue();

					PoLine poLine = (PoLine) lineItem;
					String authority = this.getAuthority(organizationId, poHeader, poLine);
					BudgetEntity budgetEntity = null;

					if (budgetAction.getType().startsWith("REQ"))
					{
						if (poLine.getIcReqLine().compareTo(new BigDecimal(0)) != 0)
						{
							RequisitionLine reqLine = this.getRequisitionLine(poLine, newIncomingRequest);

							if (budgetAction.getType().equalsIgnoreCase("REQ") || (budgetAction.getType().equalsIgnoreCase("REQREV") && (!poLine.getStatus().equals(DocumentStatus.CANCELLED))))
							{
								budgetEntity = BudgetEntity.buildBudgetEntity(reqLine, budgetAction, authority);
							}
						}
					} else if (budgetAction.getType().equalsIgnoreCase("POPRE"))
					{
						PoLine poLinePre = this.getPoLineFromRevision(poHeader, poLine, newIncomingRequest);

						if (poLinePre != null)
						{
							BigDecimal quantityInvoiced = new BigDecimal(0);

							InvoiceLineRetrieveQtyInvoicedByRelatedPoLine ivLineRetrieveQtyInvoicedByRelatedPoLine = new InvoiceLineRetrieveQtyInvoicedByRelatedPoLine();

							incomingRequest.put("InvoiceLine_icRelPoLine", poLinePre.getIcPoLine().toString());

							quantityInvoiced = HiltonUtility.ckNull((BigDecimal) ivLineRetrieveQtyInvoicedByRelatedPoLine.executeTask(incomingRequest));

							if (poLinePre.getStatus().equals(DocumentStatus.PY_PARTIALLYINVOICED) && (quantityInvoiced.compareTo(new BigDecimal(0)) > 0))
							{
								BudgetAction newBudgetAction = new BudgetAction("POPRE", BudgetAction.ENCUMBRANCE, true);
								BigDecimal otherTotal = poLinePre.getLineTotal().subtract(poLinePre.getExpensed());
								budgetEntity = BudgetEntity.buildBudgetEntity(poLinePre, newBudgetAction, authority, otherTotal);

								if (poLine.getQuantity().compareTo(quantityInvoiced) == 0)
								{
									executeNextActions.set(i, new Boolean(false));
								}
							} else
							{
								budgetEntity = BudgetEntity.buildBudgetEntity(poLinePre, budgetAction, authority);
							}
						}
					} else
					{
						if (executeAction)
						{
							budgetEntity = BudgetEntity.buildBudgetEntity(poLine, budgetAction, authority);
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

			Log.error(this, "BudgetSetupPoEntities error " + e.getMessage());

			throw e;
		}

		return result;
	}

	private RequisitionLine getRequisitionLine(PoLine poLine, Map incomingRequest) throws Exception
	{
		RequisitionLine reqLine;
		RequisitionLineRetrieveById requisitionLineRetrieveById = new RequisitionLineRetrieveById();

		incomingRequest.put("RequisitionLine_icReqLine", poLine.getIcReqLine().toString());

		reqLine = (RequisitionLine) requisitionLineRetrieveById.executeTask(incomingRequest);

		if (requisitionLineRetrieveById.getStatus() == Status.FAILED)
		{
			throw new TsaException("Error loading Requisiton Line from PoLine " + poLine.getIcReqLine());
		}

		return reqLine;
	}

	private PoLine getPoLineFromRevision(PoHeader poHeader, PoLine poLine, Map incomingRequest) throws Exception
	{
		PoLine poLinePre;
		PoLineRetrieveFromPoRevision poLineRetrieveFromPoRevision = new PoLineRetrieveFromPoRevision();

		incomingRequest.put("PoHeader_poNumber", poHeader.getPoNumber());
		incomingRequest.put("PoHeader_releaseNumber", poHeader.getReleaseNumber().toString());
		incomingRequest.put("PoHeader_revisionNumber", poHeader.getRevisionNumber().toString());
		incomingRequest.put("PoLine_icLineKey", poLine.getIcLineKey().toString());

		poLinePre = (PoLine) poLineRetrieveFromPoRevision.executeTask(incomingRequest);

		if (poLineRetrieveFromPoRevision.getStatus() == Status.FAILED)
		{
			throw new TsaException("Error loading previous Po Line from Po Revision " + poLine.getIcLineKey());
		}

		return poLinePre;
	}

	private String getAuthority(String organizationId, PoHeader poHeader, PoLine poLine)
	{
		String authority = "";
		boolean isAuthRequisitioner = BudgetUtils.isAuthorityRequisitioner(organizationId);

		authority = (isAuthRequisitioner) ? poLine.getRequisitionerCode() : poLine.getDepartmentCode();

		if (HiltonUtility.isEmpty(authority))
		{
			authority = (isAuthRequisitioner) ? poHeader.getRequisitionerCode() : poHeader.getDepartmentCode();
		}

		return authority;
	}

}