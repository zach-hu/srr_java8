/**
 * 
 * Created on Jan 29, 2004
 * @author renzo
 * com.tsa.puridiom.currcode.tasks.GetCurrencyFactor.java
 * 
 */
package com.tsa.puridiom.currcode.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsa.puridiom.entity.*;

public class GetCurrencyFactor extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		BigDecimal factor = new BigDecimal(0);
		try
		{
			Map incomingRequest = (Map)object;
			
			String organizationId = (String)incomingRequest.get("organizationId");
			PropertiesManager pm = PropertiesManager.getInstance(organizationId);
			CurrCode currency = (CurrCode)incomingRequest.get("currCode");
			
			if (currency != null) {
				if (pm.getProperty("DEFAULTS", "CURRENCY", " ").equals("EXTERNAL"))
				{
					//factor = currency.getExchangeRate();
				}
				else
				{
					factor = currency.getConversionToBase();
				}
			}
			if(factor.compareTo(new BigDecimal(0)) == 0)
			{
				factor = new BigDecimal(1);
			}
			incomingRequest.put("PoHeader_currencyFactor", factor.toString());
		}
		catch (Exception e)
		{
			throw new TsaException(e.toString());
		}
		return factor;
	}

}
