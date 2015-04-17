package com.tsa.puridiom.vendorquestion.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class VendorQuestionSetValuesPK
{
	public void setValues(Map incomingRequest, VendorQuestion vendorquestion ) throws Exception
	{
		try
		{
			String icVendorQuestionString = (String) incomingRequest.get("VendorQuestion_icVendorQuestion");
			BigDecimal icVendorQuestion = new BigDecimal ( icVendorQuestionString );
			vendorquestion.setIcVendorQuestion(icVendorQuestion);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}