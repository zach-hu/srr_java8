package com.tsa.puridiom.coda.test;

import com.tsa.puridiom.coda.tasks.CodaPostInvoice;
import com.tsa.puridiom.entity.Account;
import com.tsa.puridiom.entity.InvoiceHeader;
import com.tsa.puridiom.entity.InvoiceLine;
import com.tsagate.foundation.processengine.*;

import java.math.BigDecimal;
import java.util.*;

public class CodaPostInvoiceTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			CodaPostInvoice test = new CodaPostInvoice() ;
			Map incomingRequest = new HashMap() ;

			incomingRequest.put("organizationId", "TTR09P");
			incomingRequest.put("userId", "SYSADM");

			InvoiceHeader header = new InvoiceHeader() ;
			header.setInvoiceNumber("20000") ;
			header.setPoNumber("10-000011") ;
			header.setInvoiceDesc("Testing") ;

			List acList = new ArrayList() ;
			Account ac = new Account() ;
			ac.setAllocAmount(new BigDecimal("4.01"));
			ac.setFld1("210001") ;
			ac.setFld2("AB") ;
			ac.setFld3("100") ;
			acList.add(ac) ;

			List lineList = new ArrayList() ;
			InvoiceLine line = new InvoiceLine() ;
			line.setDescription("Testing") ;

			line.setAccountList(acList) ;

			lineList.add(line) ;

			incomingRequest.put("header", header) ;
			incomingRequest.put("lineitems", lineList) ;
			test.executeTask(incomingRequest) ;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}