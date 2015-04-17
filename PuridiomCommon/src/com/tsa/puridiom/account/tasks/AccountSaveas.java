package com.tsa.puridiom.account.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.Account;
import com.tsa.puridiom.entity.AccountPK;
import com.tsa.puridiom.entity.InvoiceLine;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.ReceiptLine;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;

public class AccountSaveas extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain account, newAccount_icHeader, and newAccount_icLine */
			String	icHeader = (String)incomingRequest.get("newAccount_icHeader");
			String	icLine = (String)incomingRequest.get("newAccount_icLine");
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader() ;
			String saveAccountTotals = HiltonUtility.ckNull((String) incomingRequest.get("saveAccountTotals"));
			String	accountType = "";
			String	originalAccountType = "";
			String	userId = (String)incomingRequest.get("userId");
			String userTimeZone = (String) incomingRequest.get("userTimeZone");
			AccountPK	comp_id = new AccountPK();
			Account	originalAccount = (Account) incomingRequest.get("account");  //account of PoLine
			Account	account = new Account(); // new Account InvoiceLine
			PoLine poLine = (PoLine)incomingRequest.get("poLine");
			ReceiptLine receiptLine = (ReceiptLine)incomingRequest.get("receiptLine");
			String amountAllocation = HiltonUtility.ckNull((String)incomingRequest.get("amountAllocation"));


			if (incomingRequest.containsKey("newAccount_accountType"))
			{
				accountType = (String)incomingRequest.get("newAccount_accountType");
				if (accountType.startsWith("IV"))
				{
					originalAccountType = "IV";
				}
			}
			else
			{
				accountType = originalAccount.getAccountType();
			}

			comp_id.setIcHeader(new BigDecimal(icHeader));
			comp_id.setIcLine(new BigDecimal(icLine));
			comp_id.setSequence(originalAccount.getComp_id().getSequence());
			account.setAccountType(accountType);
			account.setFld1(originalAccount.getFld1());
			account.setFld2(originalAccount.getFld2());
			account.setFld3(originalAccount.getFld3());
			account.setFld4(originalAccount.getFld4());
			account.setFld5(originalAccount.getFld5());
			account.setFld6(originalAccount.getFld6());
			account.setFld7(originalAccount.getFld7());
			account.setFld8(originalAccount.getFld8());
			account.setFld9(originalAccount.getFld9());
			account.setFld10(originalAccount.getFld10());
			account.setFld11(originalAccount.getFld11());
			account.setFld12(originalAccount.getFld12());
			account.setFld13(originalAccount.getFld13());
			account.setFld14(originalAccount.getFld14());
			account.setFld15(originalAccount.getFld15());
			account.setAllocPercent(originalAccount.getAllocPercent());
			if (saveAccountTotals.equalsIgnoreCase("N"))
			{
				account.setAllocAmount(new BigDecimal(0));
			}
			else
			{
				account.setAllocAmount(originalAccount.getAllocAmount());
			}
			account.setAccountTitle(originalAccount.getAccountTitle());
			account.setDateEntered(Dates.getCurrentDate(userTimeZone));
			account.setDateExpires(Dates.getCurrentDate(userTimeZone));
			account.setStatus(originalAccount.getStatus());
			account.setOwner(userId);
			account.setAllocMethod(originalAccount.getAllocMethod());
			account.setAllocQty(originalAccount.getAllocQty());
			account.setRecQty(originalAccount.getRecQty());
			account.setIcLastRec(originalAccount.getIcLastRec());
			account.setComp_id(comp_id);

			if(("PS-ACI").equalsIgnoreCase(userId) && account.getAccountType().equalsIgnoreCase("IVL")  && originalAccount.getAccountType().equalsIgnoreCase("POL"))
			{
				InvoiceLine invoiceLine = (InvoiceLine)incomingRequest.get("invoiceLine");
				if (amountAllocation.equalsIgnoreCase("Y"))
				{
					account.setAllocPercent((BigDecimal)incomingRequest.get("newAllocationPercent"));
				}
				BigDecimal qtyAccepted = invoiceLine.getQuantity();
				BigDecimal unitPrice = poLine.getUnitPrice();
				BigDecimal allocPercent = account.getAllocPercent();
				allocPercent = allocPercent.divide(new BigDecimal(100));
				BigDecimal total = unitPrice.multiply(qtyAccepted);
				BigDecimal accountTotal = total.multiply(allocPercent).setScale(2, BigDecimal.ROUND_HALF_DOWN);
				account.setAllocAmount(accountTotal);
				account.setAmountLine(accountTotal);
				saveAccountTotals = "Y";

			}

			if (("PS-ACI").equalsIgnoreCase(userId) && account.getAccountType().equalsIgnoreCase("IVH") && originalAccount.getAccountType().equalsIgnoreCase("POH"))
			{
				if (amountAllocation.equalsIgnoreCase("Y"))
				{
					account.setAllocPercent((BigDecimal)incomingRequest.get("newAllocationPercent"));
				}
				PoLine pLine = (PoLine)incomingRequest.get("currentPoLine");
				ReceiptLine rLine = (ReceiptLine)incomingRequest.get("currentReceiptLine");
				InvoiceLine invoiceLine = (InvoiceLine)incomingRequest.get("currentInvoiceLine");
				BigDecimal qtyAccepted = invoiceLine.getQuantity();
				BigDecimal unitPrice = pLine.getUnitPrice();
				BigDecimal allocPercent = account.getAllocPercent();
				allocPercent = allocPercent.divide(new BigDecimal(100));
				BigDecimal total = unitPrice.multiply(qtyAccepted);
				BigDecimal accountTotal = total.multiply(allocPercent).setScale(2, BigDecimal.ROUND_HALF_DOWN);
				account.setAllocAmount(accountTotal);
				account.setAccountType("IVL");
				account.getComp_id().setIcLine((BigDecimal)incomingRequest.get("newIcLine"));
				account.getComp_id().setSequence(new BigDecimal((Integer)incomingRequest.get("newSequence")));
				saveAccountTotals = "Y";

			}


			if (originalAccountType.equalsIgnoreCase("IV") &&  !saveAccountTotals.equalsIgnoreCase("Y"))	/* invoice */
			{
				account.setAllocAmount(new BigDecimal(0));
				account.setAmountLine(originalAccount.getAllocAmount());
			}

			incomingRequest.put("account", account);

			AccountAdd addTask = new AccountAdd();
			account = (Account) addTask.executeTask(incomingRequest);
			this.setStatus(addTask.getStatus()) ;


			result = account;
			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}