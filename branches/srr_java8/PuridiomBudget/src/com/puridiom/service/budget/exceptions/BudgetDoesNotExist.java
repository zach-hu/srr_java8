package com.puridiom.service.budget.exceptions;


public class BudgetDoesNotExist extends Exception
{
	/**
	 *
	 */
	private static final long serialVersionUID = 963199706741428056L;

	public BudgetDoesNotExist(Object key)
	{
		super("Budget[" + key.toString() + "] Does not Exist");
	}
}
