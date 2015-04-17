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

import com.tsa.puridiom.account.tasks.AccountRetrieveByLine;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.Account;
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
public class BudgetSetupFromEntityList extends Task
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
			List budgetEntityList = (List) incomingRequest.get("budgetEntityList");
			String budgetServiceYear = (String) incomingRequest.get("budgetServiceYear");
			String organizationId = (String) incomingRequest.get("organizationId");
			String budgetCurrency = HiltonUtility.ckNull((String) incomingRequest.get("budgetCurrency"));
			String userId = (String) incomingRequest.get("userId");
			DBSession dbsession = (DBSession) incomingRequest.get("dbsession");
			List budgetAccounts = new ArrayList();
			List accountObjectList = new ArrayList();
			Map accountDistinctObjectList = new HashMap();
			List authoritiesList = new ArrayList();
			List amountsList = new ArrayList();
			List budgetActions = new ArrayList();
			List budgetIcLines = new ArrayList();
			Map newIncomingRequest = new HashMap();

			newIncomingRequest.put("organizationId", organizationId);
			newIncomingRequest.put("userId", userId);
			newIncomingRequest.put("dbsession", dbsession);

			for (Iterator it = budgetEntityList.iterator(); it.hasNext();)
			{
				BudgetEntity budgetEntity = (BudgetEntity) it.next();

				BudgetAction budgetAction = budgetEntity.getBudgetAction();
				List accountLineList = this.getAccountList(budgetEntity, incomingRequest);

				for (Iterator iterator = accountLineList.iterator(); iterator.hasNext();)
				{
					Account account = (Account) iterator.next();
					String[] budgetKey;
					BigDecimal allocAmount;

					if (!account.getSource().equalsIgnoreCase("M"))
					{
						account.setCommodity(budgetEntity.getCommodity());

						budgetKey = BudgetUtils.buildBudgetKey(account, budgetServiceYear, organizationId);
						allocAmount = this.getAllocAmount(account, budgetEntity);
						allocAmount = HiltonUtility.getConvertBaseTotal(allocAmount, budgetCurrency, organizationId);

						budgetAccounts.add(account);
						accountObjectList.add(budgetKey);
						accountDistinctObjectList.put(BudgetUtils.getBudgetId(budgetKey), budgetKey);
						authoritiesList.add(budgetEntity.getUserCode());
						amountsList.add(allocAmount);
						budgetActions.add(String.valueOf(budgetAction.getAction()));
						budgetIcLines.add(budgetEntity.getIcLine());
					}
				}
			}

			incomingRequest.put("accountList", budgetAccounts);
			incomingRequest.put("budgetDistinctKeys", (String[][]) accountDistinctObjectList.values().toArray(new String[accountDistinctObjectList.size()][0]));
			incomingRequest.put("budgetKeys", (String[][]) accountObjectList.toArray(new String[accountObjectList.size()][0]));
			incomingRequest.put("budgetIcLines", (BigDecimal[]) budgetIcLines.toArray(new BigDecimal[budgetIcLines.size()]));
			incomingRequest.put("authorities", (String[]) authoritiesList.toArray(new String[authoritiesList.size()]));
			incomingRequest.put("amounts", (BigDecimal[]) amountsList.toArray(new BigDecimal[amountsList.size()]));
			incomingRequest.put("actions", (String[]) budgetActions.toArray(new String[amountsList.size()]));

			this.setStatus(Status.SUCCEEDED);
		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);

			Log.error(this, "BudgetSetupFromEntityList error " + e.getMessage());

			throw e;
		}

		return result;
	}

	private List getAccountList(BudgetEntity budgetEntity, Map incomingRequest) throws Exception
	{
		AccountRetrieveByLine accountRetrieveByLine = new AccountRetrieveByLine();
		List accountList;

		incomingRequest.put("Account_icHeader", budgetEntity.getIcHeader().toString());
		incomingRequest.put("Account_icLine", budgetEntity.getIcLine().toString());

		accountList = (List) accountRetrieveByLine.executeTask(incomingRequest);

		if (accountRetrieveByLine.getStatus() == Status.FAILED)
		{
			throw new TsaException("Error loading Accounts from Header for Line Item " + budgetEntity.getIcLine());
		}

		return accountList;
	}

	private BigDecimal getAllocAmount(Account account, BudgetEntity budgetEntity)
	{
		BudgetAction budgetAction = budgetEntity.getBudgetAction();
		BigDecimal allocAmount;

		if (budgetEntity.isDistributable())
		{
			BigDecimal percent = account.getAllocPercent().divide(new BigDecimal(100), 5, BigDecimal.ROUND_HALF_UP);

			allocAmount = percent.multiply(budgetEntity.getAlternateTotal()).setScale(2, BigDecimal.ROUND_HALF_UP);
		} else
		{
			allocAmount = account.getAllocAmount();
		}

		if (budgetAction.isNegative())
		{
			allocAmount = allocAmount.multiply(new BigDecimal(-1)).setScale(2, BigDecimal.ROUND_HALF_UP);
		}

		return allocAmount;
	}
}
