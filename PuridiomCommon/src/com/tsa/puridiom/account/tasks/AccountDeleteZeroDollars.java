package com.tsa.puridiom.account.tasks;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.Account;
import com.tsa.puridiom.entity.InvoiceHeader;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class AccountDeleteZeroDollars extends Task	{
		public Object  executeTask (Object object) throws Exception

	{
		Map incomingRequest = (Map)object;
		List accountList = null;
		String compareAmount = "successfully";
		BigDecimal sumAmount = new BigDecimal(0);
		BigDecimal invAmount = new BigDecimal(0);

		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("account-delete-zero-dollars.xml");

			accountList = (List) incomingRequest.get("accountListByHeader");
			InvoiceHeader header = (InvoiceHeader) incomingRequest.get("invoiceHeader");
			if (header != null)
			{
				invAmount = header.getInvoiceTotal().abs();
			}

			if (accountList != null && accountList.size() > 0)
			{
				for (int i = 0; i < accountList.size(); i++)
				{
					Account account = (Account) accountList.get(i);
					BigDecimal amount = account.getAllocAmount();
					if (amount.compareTo(new BigDecimal(0)) == 0)
					{
						BigDecimal icHeader = account.getComp_id().getIcHeader();
						BigDecimal icLine = account.getComp_id().getIcLine();
						BigDecimal sequence = account.getComp_id().getSequence();

						Map requestParameters = new HashMap();
						requestParameters.put("userId", incomingRequest.get("userId"));
                        requestParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
                        requestParameters.put("organizationId", incomingRequest.get("organizationId"));
			        	requestParameters.put("dbsession", incomingRequest.get("dbsession"));
			        	requestParameters.put("account", account);
			        	requestParameters.put("Account_icHeader", icHeader.toString());
			        	requestParameters.put("Account_icLine", icLine.toString());
			        	requestParameters.put("Account_sequence", sequence.toString());
			        	//requestParameters.put("RequisitionLine_icReqLine", icLine.toString());

						process.executeProcess(requestParameters);

						this.setStatus(process.getStatus());
						if (process.getStatus() == Status.FAILED)
						{
							break;
						}
					}
					else
					{
						sumAmount = sumAmount.add(amount.abs());
					}
				}
			}
			if(invAmount.compareTo(sumAmount) != 0)
			{
				compareAmount = "failed";
			}
			
			incomingRequest.put("compareAmount",compareAmount);
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}

		return accountList;
	}
}
