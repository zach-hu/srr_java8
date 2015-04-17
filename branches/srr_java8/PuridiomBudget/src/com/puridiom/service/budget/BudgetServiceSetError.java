/**
 * 
 */
package com.puridiom.service.budget;

import java.math.BigDecimal;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.properties.DictionaryManager;

/**
 * @author Johnny
 */
public class BudgetServiceSetError
{
	public static void budgetError(List errorList, Set distinctErrors, int exception, String[] budgetKey, String authority, String organizationId)
	{
		String budgetKeyString = BudgetServiceSetError.getBudgetKeyAsString(budgetKey);
		String errorKey = String.valueOf(exception) + "/" + budgetKeyString + "/" + authority;
		Hashtable ht = new Hashtable();

		if (!distinctErrors.contains(errorKey))
		{
			ht.put("budgetError", BudgetServiceSetError.getErrorMessage(exception, organizationId));
			ht.put("budgetId", budgetKeyString);
			ht.put("budgetAuth", authority);
			ht.put("budgetException", String.valueOf(exception));
			// ht.put("budgetBalance", new BigDecimal(asBal));
			// ht.put("budgetInitial", new BigDecimal(asInitial));
			ht.put("errorSeverity", BudgetServiceSetError.getSeverity(exception));

			errorList.add(ht);
			distinctErrors.add(errorKey);
		}
	}

	public static void budgetError(List errorList, Set distinctErrors, int exception, String[] budgetKey, String authority, BigDecimal asBal, String organizationId)
	{
		String budgetKeyString = BudgetServiceSetError.getBudgetKeyAsString(budgetKey);
		String errorKey = String.valueOf(exception) + "/" + budgetKeyString + "/" + authority;
		Hashtable ht = new Hashtable();

		if (!distinctErrors.contains(errorKey))
		{
			ht.put("budgetError", BudgetServiceSetError.getErrorMessage(exception, organizationId));
			ht.put("budgetId", budgetKeyString);
			ht.put("budgetAuth", authority);
			ht.put("budgetException", String.valueOf(exception));
		    ht.put("budgetBalance", asBal);
			// ht.put("budgetInitial", new BigDecimal(asInitial));
			ht.put("errorSeverity", BudgetServiceSetError.getSeverity(exception));

			errorList.add(ht);
			distinctErrors.add(errorKey);
		}
	}
	
	private static String getBudgetKeyAsString(String[] budgetKey)
	{
		String s_budgetKey = "";

		for (int i = 0; i < budgetKey.length; i++)
		{
			String key = budgetKey[i];

			if (!HiltonUtility.isEmpty(key))
			{
				s_budgetKey += key + ";";
			}
		}

		return s_budgetKey;
	}

	private static String getErrorMessage(int exception, String organizationId)
	{
		String errorMessage = "";

		exception = Math.abs(exception) * -1;

		if (exception == BudgetReturn.BUDGETLOCKED)
		{
			errorMessage = DictionaryManager.getLabel(organizationId, "budget-check-locked", "Budget is in use");
		} else if (exception == BudgetReturn.BUDGETNOTACTIVE)
		{
			errorMessage = DictionaryManager.getLabel(organizationId, "budget-check-inactive", "Budget is Inactive");
		} else if (exception == BudgetReturn.BUDGETNOEXISTS)
		{
			errorMessage = DictionaryManager.getLabel(organizationId, "budget-check-not-exist", "Budget does not exist");
		} else if (exception == BudgetReturn.NOAUTHORITY)
		{
			errorMessage = DictionaryManager.getLabel(organizationId, "budget-check-authority-not-exist", "Authority does not exist");
		} else if (exception == BudgetReturn.AUTHORITYNOTACTIVE)
		{
			errorMessage = DictionaryManager.getLabel(organizationId, "budget-check-authority-inactive", "Authority is Inactive");
		} else if (exception == BudgetReturn.NOTENOUGHBALANCE)
		{
			errorMessage = DictionaryManager.getLabel(organizationId, "budget-check-over-budget", "Over Budget but Within Tolerance");
		} else if (exception == BudgetReturn.OVERTOLERANCE)
		{
			errorMessage = DictionaryManager.getLabel(organizationId, "budget-check-over-tolerance", "Amount Over Budget and Not Within Tolerance");
		}

		return errorMessage;
	}

	private static String getSeverity(int exception)
	{
		String severity = "Error";

		if (exception > 0)
		{
			severity = "Warning";
		}

		return severity;
	}
}
