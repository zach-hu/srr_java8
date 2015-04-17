package com.puridiom.service.budget;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BudgetReturn
{
	public static int BUDGETLOCKED = -1;
	public static int NOEXCEPTION = 0;
	public static int BUDGETNOTACTIVE = -10;
	public static int BUDGETNOEXISTS = -20;
	public static int NOAUTHORITY = -30;
	public static int AUTHORITYNOTACTIVE = -35;
	public static int NOTENOUGHBALANCE = -40;
	public static int OVERTOLERANCE = -60;
	private BigDecimal lockingToken = new BigDecimal(0);
	private Map budgets = new HashMap();

	/**
	 * @param _lockingToken
	 */
	public BudgetReturn(BigDecimal _lockingToken)
	{
		this.setLockingToken(_lockingToken);
	}

	public void addBudget(Object budgetKey, List budgetReturnData)
	{
		budgets.put(budgetKey, new ArrayList(budgetReturnData));
	}

	public void addBudget(Object budgetKey, BigDecimal _balance, int _budgetException)
	{
		List budget = new ArrayList();
		budget.add(_balance);
		budget.add(new Integer(_budgetException));
		budgets.put(budgetKey, budget);
	}

	private List getBudget(Object budgetKey)
	{
		if(this.budgets.containsKey(budgetKey))
		{
			return (List)this.budgets.get(budgetKey);
		}

		return new ArrayList();
	}

	public boolean isBudgetLocked(Object budgetKey)
	{
		List budget = this.getBudget(budgetKey);
		if(budget.size() > 0)
		{
			Integer exception = (Integer)budget.get(1);
			if(exception.compareTo(new Integer(BudgetReturn.BUDGETLOCKED)) != 0)
			{
				return true;
			}
		}

		return false;
	}

	public int getBudgetException(Object budgetKey)
	{
		List budget = this.getBudget(budgetKey);
		if(budget.size() > 0)
		{
			Integer exception = (Integer)budget.get(1);
			return exception.intValue();
		}

		return 0;
	}

	public BigDecimal getBudgetBalance(Object budgetKey)
	{
		List budget = this.getBudget(budgetKey);
		if(budget.size() > 0)
		{
			BigDecimal balance = (BigDecimal)budget.get(0);
			return balance;
		}

		return new BigDecimal(0);
	}

	public BigDecimal getLockingToken() {
		return lockingToken;
	}
	private void setLockingToken(BigDecimal lockingToken) {
		this.lockingToken = lockingToken;
	}
}
