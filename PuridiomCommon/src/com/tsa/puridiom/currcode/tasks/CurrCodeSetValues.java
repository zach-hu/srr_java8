package com.tsa.puridiom.currcode.tasks;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.*;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.CurrCode;

public class CurrCodeSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			CurrCode currCode = (CurrCode) incomingRequest.get("currCode");
			if (currCode == null)
			{
				currCode = new CurrCode();
			}

			if (incomingRequest.containsKey("CurrCode_currencyCode"))
			{
				String currencyCode = (String ) incomingRequest.get("CurrCode_currencyCode");
				currCode.setCurrencyCode(currencyCode);
			}
			if (incomingRequest.containsKey("CurrCode_currencyName"))
			{
				String currencyName = (String ) incomingRequest.get("CurrCode_currencyName");
				currCode.setCurrencyName(currencyName);
			}
			if (incomingRequest.containsKey("CurrCode_symbol"))
			{
				String symbol = (String ) incomingRequest.get("CurrCode_symbol");
				currCode.setSymbol(symbol);
			}
			if (incomingRequest.containsKey("CurrCode_country"))
			{
				String country = (String ) incomingRequest.get("CurrCode_country");
				currCode.setCountry(country);
			}
			if (incomingRequest.containsKey("CurrCode_symbolPlacement"))
			{
				String symbolPlacement = (String ) incomingRequest.get("CurrCode_symbolPlacement");
				currCode.setSymbolPlacement(symbolPlacement);
			}
			if (incomingRequest.containsKey("CurrCode_negativePlacement"))
			{
				String negativePlacement = (String ) incomingRequest.get("CurrCode_negativePlacement");
				currCode.setNegativePlacement(negativePlacement);
			}
			if (incomingRequest.containsKey("CurrCode_thousandsSeprtr"))
			{
				String thousandsSeprtr = (String ) incomingRequest.get("CurrCode_thousandsSeprtr");
				currCode.setThousandsSeprtr(thousandsSeprtr);
			}
			if (incomingRequest.containsKey("CurrCode_decimalSeparator"))
			{
				String decimalSeparator = (String ) incomingRequest.get("CurrCode_decimalSeparator");
				currCode.setDecimalSeparator(decimalSeparator);
			}
			if (incomingRequest.containsKey("CurrCode_decimalDigits"))
			{
				String decimalDigitsString = (String) incomingRequest.get("CurrCode_decimalDigits");
				if (Utility.isEmpty(decimalDigitsString))
				{
					decimalDigitsString = "0";
				}
				BigDecimal decimalDigits = new BigDecimal ( decimalDigitsString );
				currCode.setDecimalDigits(decimalDigits);
			}
			if (incomingRequest.containsKey("CurrCode_leadingZero"))
			{
				String leadingZero = (String ) incomingRequest.get("CurrCode_leadingZero");
				currCode.setLeadingZero(leadingZero);
			}
			if (incomingRequest.containsKey("CurrCode_positiveFormat"))
			{
				String positiveFormat = (String ) incomingRequest.get("CurrCode_positiveFormat");
				currCode.setPositiveFormat(positiveFormat);
			}
			if (incomingRequest.containsKey("CurrCode_negativeFormat"))
			{
				String negativeFormat = (String ) incomingRequest.get("CurrCode_negativeFormat");
				currCode.setNegativeFormat(negativeFormat);
			}
			if (incomingRequest.containsKey("CurrCode_dateEntered"))
			{
				String dateEnteredString = (String) incomingRequest.get("CurrCode_dateEntered");
				Date dateEntered = Dates.getDate(dateEnteredString);
				currCode.setDateEntered(dateEntered);
			}
			if (incomingRequest.containsKey("CurrCode_dateExpires"))
			{
				String dateExpiresString = (String) incomingRequest.get("CurrCode_dateExpires");
				Date dateExpires = Dates.getDate(dateExpiresString);
				currCode.setDateExpires(dateExpires);
			}
			if (incomingRequest.containsKey("CurrCode_status"))
			{
				String status = (String ) incomingRequest.get("CurrCode_status");
				currCode.setStatus(status);
			}
			if (incomingRequest.containsKey("CurrCode_owner"))
			{
				String owner = (String ) incomingRequest.get("CurrCode_owner");
				currCode.setOwner(owner);
			}
			if (incomingRequest.containsKey("CurrCode_conversionToBase"))
			{
				String conversionToBaseString = (String) incomingRequest.get("CurrCode_conversionToBase");
				if (Utility.isEmpty(conversionToBaseString))
				{
					conversionToBaseString = "0";
				}
				BigDecimal conversionToBase = new BigDecimal ( conversionToBaseString );
				currCode.setConversionToBase(conversionToBase);
			}
			
			if (incomingRequest.containsKey("CurrCode_baseToCurrency"))
			{
				String baseToCurrencyString = (String) incomingRequest.get("CurrCode_baseToCurrency");
				
				if (HiltonUtility.isEmpty(baseToCurrencyString))
				{
					baseToCurrencyString = "0";
				}
				
				BigDecimal baseToCurrency = new BigDecimal ( baseToCurrencyString );
				currCode.setBaseToCurrency(baseToCurrency);
			}
			
			if (incomingRequest.containsKey("CurrCode_isoCurrency"))
			{
				String isoCurrency = (String) incomingRequest.get("CurrCode_isoCurrency");
				currCode.setIsoCurrency(isoCurrency);
			}

			result = currCode;
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