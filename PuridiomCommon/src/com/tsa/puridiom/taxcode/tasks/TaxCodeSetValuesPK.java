package com.tsa.puridiom.taxcode.tasks;

import com.tsa.puridiom.entity.TaxCode;
import java.util.Map;

public class TaxCodeSetValuesPK
{
	public void setValues(Map incomingRequest, TaxCode taxcode) throws Exception
	{
		try
		{
			String code = (String) incomingRequest.get("TaxCode_taxCode");
			taxcode.setTaxCode(code);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}