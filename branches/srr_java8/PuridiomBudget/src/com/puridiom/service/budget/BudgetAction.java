package com.puridiom.service.budget;

import java.util.ArrayList;
import java.util.List;

import com.tsa.puridiom.common.utility.HiltonUtility;

public class BudgetAction
{
	public static int PREENCUMBRANCE = 0;

	public static int ENCUMBRANCE = 10;

	public static int EXPENSED = 20;

	private String type;

	private int action;

	private boolean negative;

	/**
	 *
	 */
	public BudgetAction()
	{
		super();
	}

	/**
	 * @param type
	 * @param action
	 * @param negative
	 */
	public BudgetAction(String type, int action, boolean negative)
	{
		this.type = type;
		this.action = action;
		this.negative = negative;
	}

	/**
	 * @return the type
	 */
	public String getType()
	{
		return (java.lang.String) HiltonUtility.ckNull(this.type).trim();
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type)
	{
		this.type = type;
	}

	/**
	 * @return the action
	 */
	public int getAction()
	{
		return action;
	}

	/**
	 * @param action
	 *            the action to set
	 */
	public void setAction(int action)
	{
		this.action = action;
	}

	/**
	 * @return the negative
	 */
	public boolean isNegative()
	{
		return negative;
	}

	/**
	 * @param negative
	 *            the negative to set
	 */
	public void setNegative(boolean negative)
	{
		this.negative = negative;
	}

	public static List getForwardActions(String documentType)
	{
		List budgetActions = new ArrayList();

		if (documentType.equalsIgnoreCase("REQ"))
		{
			budgetActions.add(new BudgetAction("REQ", BudgetAction.PREENCUMBRANCE, false));

		}
		else if (documentType.equalsIgnoreCase("PO"))
		{
			budgetActions.add(new BudgetAction("REQ", BudgetAction.PREENCUMBRANCE, true));
			budgetActions.add(new BudgetAction("PO", BudgetAction.ENCUMBRANCE, false));

		}
		else if (documentType.equalsIgnoreCase("POBO"))
		{
			budgetActions.add(new BudgetAction("REQ", BudgetAction.PREENCUMBRANCE, true));

		}
		else if (documentType.equalsIgnoreCase("POREV"))
		{
			budgetActions.add(new BudgetAction("REQ", BudgetAction.PREENCUMBRANCE, false));
			budgetActions.add(new BudgetAction("POPRE", BudgetAction.ENCUMBRANCE, true));
			budgetActions.add(new BudgetAction("REQREV", BudgetAction.PREENCUMBRANCE, true));
			budgetActions.add(new BudgetAction("PO", BudgetAction.ENCUMBRANCE, false));

		}
		else if (documentType.equalsIgnoreCase("POCREV"))
		{
			budgetActions.add(new BudgetAction("POPRE", BudgetAction.ENCUMBRANCE, true));
			budgetActions.add(new BudgetAction("PO", BudgetAction.ENCUMBRANCE, false));
		}
		else if (documentType.equalsIgnoreCase("IVC"))
		{
			budgetActions.add(new BudgetAction("PO", BudgetAction.ENCUMBRANCE, true));
			budgetActions.add(new BudgetAction("IVC", BudgetAction.EXPENSED, false));
		}

		return budgetActions;
	}

	public static List getCancelActions(String documentType)
	{
		List budgetActions = new ArrayList();

		if (documentType.equalsIgnoreCase("REQ"))
		{
			budgetActions.add(new BudgetAction("REQ", BudgetAction.PREENCUMBRANCE, true));
		}
		else if (documentType.equalsIgnoreCase("PO"))
		{
			budgetActions.add(new BudgetAction("REQ", BudgetAction.PREENCUMBRANCE, false));
			budgetActions.add(new BudgetAction("PO", BudgetAction.ENCUMBRANCE, true));
		}
		else if (documentType.equalsIgnoreCase("POREV"))
		{
			budgetActions.add(new BudgetAction("REQ", BudgetAction.PREENCUMBRANCE, false));
			budgetActions.add(new BudgetAction("PO", BudgetAction.ENCUMBRANCE, true));
		}
		else if (documentType.equalsIgnoreCase("POCREV"))
		{
			budgetActions.add(new BudgetAction("REQ", BudgetAction.PREENCUMBRANCE, false));
			budgetActions.add(new BudgetAction("PO", BudgetAction.ENCUMBRANCE, true));
		}
		else if (documentType.equalsIgnoreCase("IVC"))
		{
			budgetActions.add(new BudgetAction("PO", BudgetAction.ENCUMBRANCE, false));
			budgetActions.add(new BudgetAction("IVC", BudgetAction.EXPENSED, true));
		}

		return budgetActions;
	}

	public static List getCloseActions(String documentType)
	{
		List budgetActions = new ArrayList();

		if (documentType.equalsIgnoreCase("REQ"))
		{
			budgetActions.add(new BudgetAction("REQ", BudgetAction.PREENCUMBRANCE, true));
			budgetActions.add(new BudgetAction("REQ", BudgetAction.EXPENSED, false));

		} else if (documentType.startsWith("PO"))
		{
			budgetActions.add(new BudgetAction("PO", BudgetAction.ENCUMBRANCE, true));
			budgetActions.add(new BudgetAction("PO", BudgetAction.EXPENSED, false));
		}

		return budgetActions;
	}

	public static List getDeleteActions(String documentType, String subAction)
	{
		List budgetActions = new ArrayList();

		if (documentType.equalsIgnoreCase("PO"))
		{
			if (subAction.equalsIgnoreCase("CANCEL"))
			{
				budgetActions.add(new BudgetAction("PO", BudgetAction.PREENCUMBRANCE, true));

			} else if (subAction.equalsIgnoreCase("CLOSE"))
			{
				budgetActions.add(new BudgetAction("PO", BudgetAction.PREENCUMBRANCE, true));
				budgetActions.add(new BudgetAction("PO", BudgetAction.EXPENSED, false));
			}

		}
		else if (documentType.equalsIgnoreCase("POREV"))
		{
			budgetActions.add(new BudgetAction("PO", BudgetAction.ENCUMBRANCE, true));
		}
		else if (documentType.equalsIgnoreCase("POCREV"))
		{
			budgetActions.add(new BudgetAction("PO", BudgetAction.ENCUMBRANCE, true));
		}

		return budgetActions;
	}

}
