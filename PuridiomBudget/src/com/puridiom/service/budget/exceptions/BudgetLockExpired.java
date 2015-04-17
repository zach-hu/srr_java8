package com.puridiom.service.budget.exceptions;

public class BudgetLockExpired extends Exception
{
	/**
	 *
	 */
	private static final long serialVersionUID = -271515831922432170L;

	public BudgetLockExpired()
	{
		super("Locking Token has expired");
	}

}
