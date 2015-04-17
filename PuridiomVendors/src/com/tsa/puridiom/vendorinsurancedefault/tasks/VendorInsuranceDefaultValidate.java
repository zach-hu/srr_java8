package com.tsa.puridiom.vendorinsurancedefault.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.InsCategoryLevel;
import com.tsa.puridiom.entity.VendorInsuranceDefault;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class VendorInsuranceDefaultValidate extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String userTimeZone = (String) incomingRequest.get("userTimeZone");
			String userDateFormat = (String) incomingRequest.get("userDateFormat");

			String isVendorInsuranceDefaultValid = "Y";
			String[] vendorInsuranceDefaultResults = null;

			InsCategoryLevel insCategoryLevel = (InsCategoryLevel)incomingRequest.get("insCategoryLevel");
			VendorInsuranceDefault vendorInsuranceDefault = (VendorInsuranceDefault)incomingRequest.get("vendorInsuranceDefault");

			if (vendorInsuranceDefault == null) {
				vendorInsuranceDefault = new VendorInsuranceDefault();
			}
			if (insCategoryLevel == null) {
				insCategoryLevel = new InsCategoryLevel();
			}

			if (insCategoryLevel != null && vendorInsuranceDefault != null)
			{
				if (!vendorInsuranceDefault.getInsuranceOverride().equalsIgnoreCase("Y"))
				{
					Method[] insCategoryLevelMethods = new InsCategoryLevel().getClass().getDeclaredMethods();
					Method[] vendorInsuranceDefaultMethods = new VendorInsuranceDefault().getClass().getDeclaredMethods();

					int insCategoryLevelCount = 0;
					for (int i = 0; i < insCategoryLevelMethods.length; i++)
					{
						Method method = insCategoryLevelMethods[i];
						if (method.getName().indexOf("getIclRequired") >= 0) {
							insCategoryLevelCount ++;
						}
					}

					int vendorInsuranceDefaultCount = 0;
					for (int i = 0; i < vendorInsuranceDefaultMethods.length; i++)
					{
						Method method = vendorInsuranceDefaultMethods[i];
						if (method.getName().indexOf("getCoverage") >= 0) {
							vendorInsuranceDefaultCount ++;
						}
					}

					int minMethods = 0;
					if (insCategoryLevelCount >= vendorInsuranceDefaultCount) {
						minMethods = vendorInsuranceDefaultCount;
					} else {
						minMethods = insCategoryLevelCount;
					}

					vendorInsuranceDefaultResults = new String[minMethods];

					for (int i = 1; i <= minMethods; i++)
					{
						Method iclRequiredMethod = insCategoryLevel.getClass().getMethod("getIclRequired" + i, null);
						Method iclMinimumMethod = insCategoryLevel.getClass().getMethod("getIclMinimum" + i, null);
						Method coverageMethod = vendorInsuranceDefault.getClass().getMethod("getCoverage" + i, null);
						Method limitMethod = vendorInsuranceDefault.getClass().getMethod("getLimit" + i, null);
						Method expiresMethod = vendorInsuranceDefault.getClass().getMethod("getExpires" + i, null);

						String iclRequired = (String)iclRequiredMethod.invoke(insCategoryLevel, null);
						BigDecimal iclMinimum = (BigDecimal)iclMinimumMethod.invoke(insCategoryLevel, null);
						String coverage = (String)coverageMethod.invoke(vendorInsuranceDefault, null);
						BigDecimal limit = (BigDecimal)limitMethod.invoke(vendorInsuranceDefault, null);
						java.util.Date expires = (java.util.Date)expiresMethod.invoke(vendorInsuranceDefault, null);

						vendorInsuranceDefaultResults[i - 1] = "Y";

						if (iclRequired.equalsIgnoreCase("Y"))
						{
							if (coverage.equalsIgnoreCase("A") || HiltonUtility.isEmpty(coverage)) {
								if (limit.compareTo(iclMinimum) < 0) {
									isVendorInsuranceDefaultValid = "N";
									vendorInsuranceDefaultResults[i - 1] = "N";
								}
								if (expires != null) {
									Date dateToday = Dates.getDate(Dates.today(userDateFormat, userTimeZone));
									Date dateExpires = Dates.getDate(String.valueOf(expires));
									if (dateExpires.compareTo(dateToday) < 0) {
										isVendorInsuranceDefaultValid = "N";
										vendorInsuranceDefaultResults[i - 1] = "N";
									}
								}
							}
							else if (coverage.equalsIgnoreCase("C") || coverage.equalsIgnoreCase("I") || coverage.equalsIgnoreCase("P") || coverage.equalsIgnoreCase("U")) {
								isVendorInsuranceDefaultValid = "N";
								vendorInsuranceDefaultResults[i - 1] = "N";
							}
						}
					}
				}
			}
			else
			{
				isVendorInsuranceDefaultValid = "N";
			}

			incomingRequest.put("isVendorInsuranceDefaultValid", isVendorInsuranceDefaultValid);
			incomingRequest.put("vendorInsuranceDefaultResults", vendorInsuranceDefaultResults);

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}
}
