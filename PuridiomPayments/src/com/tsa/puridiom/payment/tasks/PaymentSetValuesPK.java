package com.tsa.puridiom.payment.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class PaymentSetValuesPK
{
	public void setValues(Map incomingRequest, Payment payment ) throws Exception
	{
		try
		{
			String icPoHeaderString = (String) incomingRequest.get("Payment_icPoHeader");
			BigDecimal icPoHeader = new BigDecimal ( icPoHeaderString );
			String sequenceString = (String) incomingRequest.get("Payment_sequence");
			BigDecimal sequence = new BigDecimal ( sequenceString );
			PaymentPK comp_id = new PaymentPK();
			comp_id.setIcPoHeader(icPoHeader);
			comp_id.setSequence(sequence);
			payment.setComp_id(comp_id);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}