/**
 * 
 */
package com.puridiom.service.budget;

import java.math.BigDecimal;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.InvoiceLine;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.RequisitionLine;

/**
 * @author Johnny
 */
public class BudgetEntity
{
	private BudgetAction budgetAction;

	private BigDecimal icHeader;

	private BigDecimal icLine;

	private BigDecimal icReferenceLine;

	private BigDecimal lineTotal;

	private BigDecimal alternateTotal;

	private String commodity;

	private String userCode;

	private boolean distributable;

	public BudgetEntity()
	{
		this.distributable = false;
		this.alternateTotal = new BigDecimal(0);
	}

	/**
	 * @return the budgetAction
	 */
	public BudgetAction getBudgetAction()
	{
		return budgetAction;
	}

	/**
	 * @param budgetAction
	 *            the budgetAction to set
	 */
	public void setBudgetAction(BudgetAction budgetAction)
	{
		this.budgetAction = budgetAction;
	}

	/**
	 * @return the icHeader
	 */
	public BigDecimal getIcHeader()
	{
		return icHeader;
	}

	/**
	 * @param icHeader
	 *            the icHeader to set
	 */
	public void setIcHeader(BigDecimal icHeader)
	{
		this.icHeader = icHeader;
	}

	/**
	 * @return the icLine
	 */
	public BigDecimal getIcLine()
	{
		return icLine;
	}

	/**
	 * @param icLine
	 *            the icLine to set
	 */
	public void setIcLine(BigDecimal icLine)
	{
		this.icLine = icLine;
	}

	/**
	 * @return the icReferenceLine
	 */
	public BigDecimal getIcReferenceLine()
	{
		return icReferenceLine;
	}

	/**
	 * @param icReferenceLine
	 *            the icReferenceLine to set
	 */
	public void setIcReferenceLine(BigDecimal icReferenceLine)
	{
		this.icReferenceLine = icReferenceLine;
	}

	/**
	 * @return the lineTotal
	 */
	public BigDecimal getLineTotal()
	{
		return (java.math.BigDecimal) HiltonUtility.ckNull(this.lineTotal);
	}

	/**
	 * @param lineTotal
	 *            the lineTotal to set
	 */
	public void setLineTotal(BigDecimal lineTotal)
	{
		this.lineTotal = lineTotal;
	}

	/**
	 * @return the commodity
	 */
	public String getCommodity()
	{
		return (java.lang.String) HiltonUtility.ckNull(this.commodity).trim();
	}

	/**
	 * @param commodity
	 *            the commodity to set
	 */
	public void setCommodity(String commodity)
	{
		this.commodity = commodity;
	}

	/**
	 * @return the userCode
	 */
	public String getUserCode()
	{
		return (java.lang.String) HiltonUtility.ckNull(this.userCode).trim();
	}

	/**
	 * @param userCode
	 *            the userCode to set
	 */
	public void setUserCode(String userCode)
	{
		this.userCode = userCode;
	}

	/**
	 * @return the alternateTotal
	 */
	public BigDecimal getAlternateTotal()
	{
		return alternateTotal;
	}

	/**
	 * @param alternateTotal
	 *            the alternateTotal to set
	 */
	public void setAlternateTotal(BigDecimal alternateTotal)
	{
		this.alternateTotal = alternateTotal;
	}

	/**
	 * @return the distributable
	 */
	public boolean isDistributable()
	{
		return distributable;
	}

	/**
	 * @param distributable
	 *            the distributable to set
	 */
	public void setDistributable(boolean distributable)
	{
		this.distributable = distributable;
	}

	public static BudgetEntity buildBudgetEntity(Object lineItem, BudgetAction budgetAction, String authority)
	{
		return buildBudgetEntity(lineItem, budgetAction, authority, new BigDecimal(-1));
	}

	public static BudgetEntity buildBudgetEntity(Object lineItem, BudgetAction budgetAction, String authority, BigDecimal alternateTotal)
	{
		BudgetEntity budgetEntity = new BudgetEntity();
		BigDecimal icHeader = new BigDecimal(0);
		BigDecimal icLine = new BigDecimal(0);
		BigDecimal lineTotal = new BigDecimal(0);
		String commodity = "";

		String formType = budgetAction.getType();

		budgetEntity.setBudgetAction(budgetAction);
		budgetEntity.setUserCode(authority);

		if (formType.startsWith("REQ"))
		{
			RequisitionLine reqLine = (RequisitionLine) lineItem;

			icHeader = reqLine.getIcReqHeader();
			icLine = reqLine.getIcReqLine();
			lineTotal = reqLine.getLineTotal();
			commodity = reqLine.getCommodityCode();

		} else if (formType.startsWith("PO"))
		{
			PoLine poLine = (PoLine) lineItem;

			icHeader = poLine.getIcPoHeader();
			icLine = poLine.getIcPoLine();
			lineTotal = poLine.getLineTotal();
			commodity = poLine.getCommodity();

		} else if (formType.equalsIgnoreCase("IVC"))
		{
			InvoiceLine invoiceLine = (InvoiceLine) lineItem;

			icHeader = invoiceLine.getIcIvcHeader();
			icLine = invoiceLine.getIcIvcLine();
			lineTotal = invoiceLine.getLineTotal();
			commodity = invoiceLine.getCommodity();

			if (invoiceLine.getIcPoLine().compareTo(new BigDecimal(0)) != 0)
			{
				budgetEntity.setIcReferenceLine(invoiceLine.getIcRelPoLine());
			}
		}

		budgetEntity.setIcHeader(icHeader);
		budgetEntity.setIcLine(icLine);
		budgetEntity.setCommodity(commodity);
		budgetEntity.setLineTotal(lineTotal);

		if (alternateTotal.compareTo(new BigDecimal(-1)) > 0)
		{
			budgetEntity.setDistributable(true);
			budgetEntity.setAlternateTotal(alternateTotal);
		}

		return budgetEntity;
	}

}
