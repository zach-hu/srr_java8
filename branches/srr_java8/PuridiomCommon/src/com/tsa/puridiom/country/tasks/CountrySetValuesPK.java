package com.tsa.puridiom.country.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class CountrySetValuesPK
{
	public void setValues(Map incomingRequest, Country country ) throws Exception
	{
		try
		{
			String countryCode = (String ) incomingRequest.get("Country_countryCode");
			country.setCountryCode(countryCode);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}