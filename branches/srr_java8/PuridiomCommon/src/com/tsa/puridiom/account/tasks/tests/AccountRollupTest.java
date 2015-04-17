package com.tsa.puridiom.account.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.account.tasks.*;
import com.tsagate.foundation.utility.Dates;
import java.math.BigDecimal;
import java.util.*;

public class AccountRollupTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			AccountRollup task = new AccountRollup();
			Map incomingRequest = new HashMap();

			/* HDR ACCOUNTS */
			Account account1 = new Account();
			AccountPK pk1 = new AccountPK();
			pk1.setIcHeader(new BigDecimal("1")) ;
			pk1.setIcLine(new BigDecimal("0"));
			pk1.setSequence(new BigDecimal("1"));
			account1.setComp_id(pk1);
			account1.setAccountTitle("HDR ACCOUNT 1") ;
			account1.setAccountType("RQH") ;
			account1.setAllocAmount(new BigDecimal("100.00"));
			account1.setAllocMethod("PH");
			account1.setAllocPercent(new BigDecimal("50.00"));
			account1.setDateEntered(Dates.getDate(Dates.today("", "")));
			account1.setFld1("0011");
			account1.setFld2("0022");
			account1.setFld3("0033");

			Account account2 = new Account();
			AccountPK pk2 = new AccountPK();
			pk2.setIcHeader(new BigDecimal("1")) ;
			pk2.setIcLine(new BigDecimal("0"));
			pk2.setSequence(new BigDecimal("2"));
			account2.setComp_id(pk2);
			account2.setAccountTitle("HDR ACCOUNT 2") ;
			account2.setAccountType("RQH") ;
			account2.setAllocAmount(new BigDecimal("100.00"));
			account2.setAllocMethod("PH");
			account2.setAllocPercent(new BigDecimal("50.00"));
			account2.setDateEntered(Dates.getDate(Dates.today("", "")));
			account2.setFld1("001122");
			account2.setFld2("002222");
			account2.setFld3("003322");

			/* LINE 1 ACCOUNTS */
			Account account3 = new Account();
			AccountPK pk3 = new AccountPK();
			pk3.setIcHeader(new BigDecimal("1")) ;
			pk3.setIcLine(new BigDecimal("1"));
			pk3.setSequence(new BigDecimal("1"));
			account3.setComp_id(pk3);
			account3.setAccountTitle("Line 1 ACCOUNT 1") ;
			account3.setAccountType("RQL") ;
			account3.setAllocAmount(new BigDecimal("25.00"));
			account3.setAllocMethod("PL");
			account3.setAllocPercent(new BigDecimal("50.00"));
			account3.setDateEntered(Dates.getDate(Dates.today("", "")));
			account3.setFld1("0011");
			account3.setFld2("0022");
			account3.setFld3("0033");

			Account account4 = new Account();
			AccountPK pk4 = new AccountPK();
			pk4.setIcHeader(new BigDecimal("1")) ;
			pk4.setIcLine(new BigDecimal("0"));
			pk4.setSequence(new BigDecimal("1"));
			account4.setComp_id(pk4);
			account4.setAccountTitle("Line 1 ACCOUNT 2") ;
			account4.setAccountType("RQL") ;
			account4.setAllocAmount(new BigDecimal("25.00"));
			account4.setAllocMethod("PL");
			account4.setAllocPercent(new BigDecimal("50.00"));
			account4.setDateEntered(Dates.getDate(Dates.today("", "")));
			account4.setFld1("001122");
			account4.setFld2("002222");
			account4.setFld3("003322");

			/* LINE 2 ACCOUNTS */
			Account account5 = new Account();
			AccountPK pk5 = new AccountPK();
			pk5.setIcHeader(new BigDecimal("1")) ;
			pk5.setIcLine(new BigDecimal("2"));
			pk5.setSequence(new BigDecimal("1"));
			account5.setComp_id(pk5);
			account5.setAccountTitle("LINE 2 ACCOUNT 1") ;
			account5.setAccountType("RQL") ;
			account5.setAllocAmount(new BigDecimal("30.00"));
			account5.setAllocMethod("PL");
			account5.setAllocPercent(new BigDecimal("100.00"));
			account5.setDateEntered(Dates.getDate(Dates.today("", "")));
			account5.setFld1("0011");
			account5.setFld2("0022");
			account5.setFld3("0033");

			/* LINE 3 ACCOUNTS */
			Account account6 = new Account();
			AccountPK pk6 = new AccountPK();
			pk6.setIcHeader(new BigDecimal("1")) ;
			pk6.setIcLine(new BigDecimal("3"));
			pk6.setSequence(new BigDecimal("1"));
			account6.setComp_id(pk6);
			account6.setAccountTitle("LINE 3 ACCOUNT 1") ;
			account6.setAccountType("RQL") ;
			account6.setAllocAmount(new BigDecimal("33.00"));
			account6.setAllocMethod("PL");
			account6.setAllocPercent(new BigDecimal("33.00"));
			account6.setDateEntered(Dates.getDate(Dates.today("", "")));
			account6.setFld1("XX11");
			account6.setFld2("YY11");
			account6.setFld3("ZZ11");

			Account account7 = new Account();
			AccountPK pk7 = new AccountPK();
			pk7.setIcHeader(new BigDecimal("1")) ;
			pk7.setIcLine(new BigDecimal("3"));
			pk7.setSequence(new BigDecimal("2"));
			account7.setComp_id(pk7);
			account7.setAccountTitle("LINE 3 ACCOUNT 2") ;
			account7.setAccountType("RQL") ;
			account7.setAllocAmount(new BigDecimal("34.00"));
			account7.setAllocMethod("PL");
			account7.setAllocPercent(new BigDecimal("34.00"));
			account7.setDateEntered(Dates.getDate(Dates.today("", "")));
			account7.setFld1("XX22");
			account7.setFld2("YY22");
			account7.setFld3("ZZ22");

			Account account8 = new Account();
			AccountPK pk8 = new AccountPK();
			pk8.setIcHeader(new BigDecimal("1")) ;
			pk8.setIcLine(new BigDecimal("3"));
			pk8.setSequence(new BigDecimal("3"));
			account8.setComp_id(pk8);
			account8.setAccountTitle("LINE 3 ACCOUNT 3") ;
			account8.setAccountType("RQL") ;
			account8.setAllocAmount(new BigDecimal("33.00"));
			account8.setAllocMethod("PL");
			account8.setAllocPercent(new BigDecimal("33.00"));
			account8.setDateEntered(Dates.getDate(Dates.today("", "")));
			account8.setFld1("XX33");
			account8.setFld2("YY33");
			account8.setFld3("ZZ33");


			List accountList = new ArrayList();
			accountList.add(account1);
			accountList.add(account2);
			accountList.add(account3);
			accountList.add(account4);
			accountList.add(account5);
			accountList.add(account6);
			accountList.add(account7);
			accountList.add(account8);

			incomingRequest.put("organizationId", "puridiom");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("accountList", accountList);


			List resultList = (List) task.executeTask(incomingRequest);

			System.out.println("RESULT STATUS: " + task.getStatus());

			if (resultList == null) {
			    System.out.println("Result List was NULL");
			} else {
			    for (int i=0; i < resultList.size(); i++) {
			        Account account = (Account) resultList.get(i);
			        System.out.println((i+1) + ") " + account.getAcctString("/") + "  $" + account.getAllocAmount());
			    }
			}
			System.out.println("TEST COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}