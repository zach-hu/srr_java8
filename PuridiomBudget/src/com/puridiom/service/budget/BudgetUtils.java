/**
 *
 */
package com.puridiom.service.budget;

import java.lang.reflect.Method;

import com.tsa.puridiom.entity.Account;
import com.tsa.puridiom.entity.BudgetCenter;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.utility.Log;

/**
 * @author Johnny
 */
public class BudgetUtils
{
	public static String getBudgetId(String[] budgetKey)
	{
		StringBuffer budgetId = new StringBuffer();

		for (int i = 0; i < budgetKey.length; i++)
		{
			budgetId.append(budgetKey[i] + ";");
		}

		return budgetId.toString();
	}

	public static String[] buildBudgetKey(Account account, String budgetYear, String organizationId)
	{
		int numBudgetFields = 16;
		String[] budgetKey = new String[numBudgetFields];

		budgetKey[0] = budgetYear;

		for (int i = 1; i < numBudgetFields; i++)
		{
			String field = PropertiesManager.getInstance(organizationId).getProperty("BUDGET", "Budget Udf" + String.valueOf(i), "BUDGET_" + String.valueOf(i));
			String data = "";
			try
			{
				Class[] types = new Class[] {};
				Method method = account.getClass().getMethod("get" + field, types);
				data = (String) method.invoke(account, new Object[0]);
			} catch (Throwable e)
			{
				Log.debug(new BudgetUtils(), "Account class reflection error get " + field + " " + e.getMessage());
			}

			budgetKey[i] = data.trim();
		}

		return budgetKey;
	}

	public static String[] buildBudgetKeyFromUDFs(BudgetCenter budgetcenter, String budgetYear, String organizationId)
	{
		int numBudgetFields = 16;
		String[] budgetKey = new String[numBudgetFields];

		budgetKey[0] = budgetYear;

		for (int i = 1; i < numBudgetFields; i++)
		{
			String field = "Budget" + String.valueOf(i);
			String data = "";
			try
			{
				Class[] types = new Class[] {};
				Method method = budgetcenter.getClass().getMethod("get" + field, types);
				data = (String) method.invoke(budgetcenter, new Object[0]);
			} catch (Throwable e)
			{
				Log.error(new BudgetUtils(), "BudgetCenter class reflection error get " + field + " " + e.getMessage());
			}

			budgetKey[i] = data.trim();
		}

		return budgetKey;
	}


	public static boolean isAuthorityRequisitioner(String organizationId)
	{
		String authorityField = PropertiesManager.getInstance(organizationId).getProperty("BUDGET", "Authority", "Department");
		boolean isAuthRequisitioner = false;

		if (authorityField.equalsIgnoreCase("Requisitioner"))
		{
			isAuthRequisitioner = true;
		}

		return isAuthRequisitioner;
	}
}
