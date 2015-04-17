/*
 * Created on Dec 9, 2003
 */
package com.tsa.puridiom.account.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.Account;
import com.tsa.puridiom.entity.InvoiceLine;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.ReceiptLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.UniqueKeyGenerator;

/**
 * @author Jeff / Kelli
 */
public class AccountHeaderListToLine extends Task
{

	/**
	 * executeTask
	 * <p>This method takes a list of accounts coming from incoming request(accountList)</p>
	 * <p> and calls AccountSaveas for each one of them.</p>
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		List accounts = (List)incomingRequest.get("accountList");
		String saveAccountTotals = HiltonUtility.ckNull((String) incomingRequest.get("saveAccountTotals"));
		List poLines = (List)incomingRequest.get("poLines");
		List receiptLines = (List)incomingRequest.get("receiptLines");
		List invoiceLines = (List)incomingRequest.get("invoiceLineList");

		for (int i = 0; i < poLines.size(); i++)
		{
			PoLine poLine = (PoLine)poLines.get(i);
			Log.error(this, " poLine ic = "+HiltonUtility.ckNull(poLine.getIcPoHeader()).toString());
			if(!poLine.getStatus().equals(DocumentStatus.CANCELLED))
			{
				ReceiptLine receiptLine = (ReceiptLine)receiptLines.get(i);
				InvoiceLine invoiceLine	= (InvoiceLine)invoiceLines.get(i);

				for (int accountRow = 0; accountRow < accounts.size(); accountRow++)
				{
					Account account = (Account)accounts.get(accountRow);
					if (account.getAllocMethod().equalsIgnoreCase("AH") || account.getAllocMethod().equalsIgnoreCase("AL"))
					{
						BigDecimal  totalAlloc = new BigDecimal(0);
						for (int ix = 0; ix < accounts.size(); ix++)
						{
							Account tempAccount = (Account)accounts.get(ix);
							BigDecimal tempAlloc = tempAccount.getAllocAmount();
							totalAlloc = totalAlloc.add(tempAlloc);
						}
						if (accounts.size() == 1)
						{
							incomingRequest.put("newAllocationPercentage", new BigDecimal(100));
						}
						else
						{
							BigDecimal tempPercent = new BigDecimal(0);
							if(HiltonUtility.ckNull(totalAlloc).compareTo(new BigDecimal(0)) == 0){
								totalAlloc = new BigDecimal(1);
							}
							tempPercent = account.getAllocAmount().multiply(new BigDecimal(100)).divide(totalAlloc, 2);
							incomingRequest.put("newAllocationPercent", tempPercent);
						}


						incomingRequest.put("amountAllocation", "Y");
					}
					if (poLine.getIcAccount().compareTo(account.getComp_id().getIcLine())  == 0)
					{
						incomingRequest.put("account", account);
						incomingRequest.put("saveAccountTotals", saveAccountTotals);
						incomingRequest.put("currentPoLine", poLine);
						incomingRequest.put("currentReceiptLine", receiptLine);
						incomingRequest.put("currentInvoiceLine", invoiceLine);
						incomingRequest.put("newSequence", accountRow + 1);
						incomingRequest.put("newIcLine", invoiceLine.getIcIvcLine());

						AccountSaveas saveas = new AccountSaveas();
						saveas.executeTask(incomingRequest);

						if (saveas.getStatus() != Status.SUCCEEDED)
						{
							throw new Exception("Account save as failed.");
						}

						accounts.set(accountRow, account);
						this.setStatus(Status.SUCCEEDED);
					}
				}
			} else {
				this.setStatus(Status.SUCCEEDED);
			}
		}
		if(accounts.size() == 0)
		{
			this.setStatus(Status.SUCCEEDED);
		}
		return accounts;
	}

}
