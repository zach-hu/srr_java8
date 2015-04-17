package com.puridiom.service.budget.exceptions;

import java.math.BigDecimal;

public class BudgetLockTokenBad extends Exception
{
	public BudgetLockTokenBad(BigDecimal badToken)
	{
		super("Wrong token provided.[" + badToken.toString() + "]");
	}
}
