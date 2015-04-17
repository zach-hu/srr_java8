package com.tsa.puridiom.account.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.account.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.utility.Dates;
import java.math.BigDecimal;
import java.util.*;

public class AccountAddTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			AccountAdd task = new AccountAdd();
			Map incomingRequest = new HashMap();

			Account account = new Account();
			AccountPK pk = new AccountPK();
			DBSession dbs = new DBSession("puridiom");

			dbs.startTransaction();

			//pk.setIcHeader(new BigDecimal("478808700000")) ;
			pk.setIcHeader(new BigDecimal("1")) ;
			pk.setIcLine(new BigDecimal("0"));
			pk.setSequence(new BigDecimal("1"));
			account.setComp_id(pk);
			account.setAccountTitle("ACCOUNT 1") ;
			account.setAccountType("RQH") ;
			account.setAllocAmount(new BigDecimal("100.00"));
			account.setAllocMethod("PH");
			account.setAllocPercent(new BigDecimal("100.00"));
			account.setDateEntered(Dates.getDate(Dates.today("", "")));
			account.setFld1("acc udf 1");

			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "puridiom");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("account", account);

			Object result = task.executeTask(incomingRequest);

			if (task.getStatus() == Status.SUCCEEDED)
			{
				dbs = (DBSession) incomingRequest.get("dbsession");

				dbs.commit();
				dbs.close();
			}

			System.out.println("RESULT: " + result.toString());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}