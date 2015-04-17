package com.puridiom.service.budget.exceptions;

import com.puridiom.service.budget.BudgetWrapper;

public class BudgetLockedException extends Exception
{
	/**
	 *
	 */
	private static final long serialVersionUID = -3589517755757019612L;

	public BudgetLockedException(BudgetWrapper budgetWrapper)
	{
		super("Budget Locked by: " + budgetWrapper.getLockingId());
	}

}
