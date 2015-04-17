package com.tsa.puridiom.country.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.sql.Date;
import java.util.Map;

public class CountrySetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			Country country = (Country) incomingRequest.get("country");
			if (country == null)
			{
				country = new Country();
			}

			if (incomingRequest.containsKey("Country_countryCode"))
			{
				String countryCode = (String) incomingRequest.get("Country_countryCode");
				country.setCountryCode(countryCode);
			}
			if (incomingRequest.containsKey("Country_countryName"))
			{
				String countryName = (String) incomingRequest.get("Country_countryName");
				country.setCountryName(countryName);
			}
			if (incomingRequest.containsKey("Country_currencyCode"))
			{
				String currencyCode = (String) incomingRequest.get("Country_currencyCode");
				country.setCurrencyCode(currencyCode);
			}
			if (incomingRequest.containsKey("Country_timeZone"))
			{
				String timeZone = (String) incomingRequest.get("Country_timeZone");
				country.setTimeZone(timeZone);
			}
			if (incomingRequest.containsKey("Country_dateEntered"))
			{
				String dateEnteredString = (String) incomingRequest.get("Country_dateEntered");
				Date dateEntered = Dates.getDate(dateEnteredString);
				country.setDateEntered(dateEntered);
			}
			if (incomingRequest.containsKey("Country_dateExpires"))
			{
				String dateExpiresString = (String) incomingRequest.get("Country_dateExpires");
				Date dateExpires = Dates.getDate(dateExpiresString);
				country.setDateExpires(dateExpires);
			}
			if (incomingRequest.containsKey("Country_status"))
			{
				String status = (String) incomingRequest.get("Country_status");
				country.setStatus(status);
			}
			if (incomingRequest.containsKey("Country_owner"))
			{
				String owner = (String) incomingRequest.get("Country_owner");
				country.setOwner(owner);
			}
			if (incomingRequest.containsKey("Country_dateFormat"))
			{
				String dateFormat = (String) incomingRequest.get("Country_dateFormat");
				country.setDateFormat(dateFormat);
			}
			if (incomingRequest.containsKey("Country_language"))
			{
				String language = (String) incomingRequest.get("Country_language");
				country.setLanguage(language);
			}
			result = country;
			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}
