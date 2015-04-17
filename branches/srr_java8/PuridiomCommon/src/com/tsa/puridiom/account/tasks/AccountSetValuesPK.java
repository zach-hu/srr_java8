package com.tsa.puridiom.account.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class AccountSetValuesPK
{
	public void setValues(Map incomingRequest, Account account ) throws Exception
	{
		try
		{
			String icHeaderString = (String) incomingRequest.get("Account_icHeader");
			BigDecimal icHeader = new BigDecimal ( icHeaderString );
			String icLineString = (String) incomingRequest.get("Account_icLine");
			BigDecimal icLine = new BigDecimal ( icLineString );
			String sequenceString = (String) incomingRequest.get("Account_sequence");
			BigDecimal sequence = new BigDecimal ( sequenceString );
			AccountPK comp_id = new AccountPK();
			comp_id.setIcHeader(icHeader);
			comp_id.setIcLine(icLine);
			comp_id.setSequence(sequence);
			account.setComp_id(comp_id);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}