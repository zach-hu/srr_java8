package com.tsa.puridiom.userpayments.tasks;

import com.tsagate.foundation.processengine.*;

import java.util.Map;

public class CompanyInfoSetupFromANPayment extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object	resultObj = null ;

		try
		{
			String	x_company = (String) incomingRequest.get("x_company");	
			String	x_address = (String) incomingRequest.get("x_address");
			String	x_city = (String) incomingRequest.get("x_city");
			String	x_state = (String) incomingRequest.get("x_state");
			String	x_country = (String) incomingRequest.get("x_country");
			String	x_zip = (String) incomingRequest.get("x_zip");
			String	x_phone = (String) incomingRequest.get("x_phone");
			String	x_fax = (String) incomingRequest.get("x_fax");
			
			String sections[] = new String[8];
			String properties[] = new String[8];
			String values[] = new String[8];
			int i = 0;
			
			sections[i] = "COMPANY";
			properties[i] = "NAME";
			values[i] = x_company;
			i++;

			sections[i] = "COMPANY";
			properties[i] = "ADDR1";
			values[i] = x_address;
			i++;

			sections[i] = "COMPANY";
			properties[i] = "CITY";
			values[i] = x_city;
			i++;
			
			sections[i] = "COMPANY";
			properties[i] = "STATE";
			values[i] = x_state;
			i++;
			
			sections[i] = "COMPANY";
			properties[i] = "POSTALCODE";
			values[i] = x_zip;
			i++;
			
			sections[i] = "COMPANY";
			properties[i] = "COUNTRY";
			values[i] = x_country;
			i++;
			
			sections[i] = "COMPANY";
			properties[i] = "PHONE";
			values[i] = x_phone;
			i++;
			
			sections[i] = "COMPANY";
			properties[i] = "FAX";
			values[i] = x_fax;
			i++;			

			incomingRequest.put("Property_section", sections);
			incomingRequest.put("Property_property", properties);
			incomingRequest.put("Property_value", values);
			
			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return resultObj;
	}
}