package com.tsa.puridiom.currcode.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.CurrCode;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.*;

public class CurrCodeSaveas extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain currCode */
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			CurrCode	originalCurrCode = (CurrCode) incomingRequest.get("currCode");
			CurrCode	currCode = new CurrCode();

			currCode.setCurrencyCode(originalCurrCode.getCurrencyCode());
			currCode.setCurrencyName(originalCurrCode.getCurrencyName());
			currCode.setSymbol(originalCurrCode.getSymbol());
			currCode.setCountry(originalCurrCode.getCountry());
			currCode.setSymbolPlacement(originalCurrCode.getSymbolPlacement());
			currCode.setNegativePlacement(originalCurrCode.getNegativePlacement());
			currCode.setThousandsSeprtr(originalCurrCode.getThousandsSeprtr());
			currCode.setDecimalSeparator(originalCurrCode.getDecimalSeparator());
			currCode.setDecimalDigits(originalCurrCode.getDecimalDigits());
			currCode.setLeadingZero(originalCurrCode.getLeadingZero());
			currCode.setPositiveFormat(originalCurrCode.getPositiveFormat());
			currCode.setNegativeFormat(originalCurrCode.getNegativeFormat());
			currCode.setDateEntered(originalCurrCode.getDateEntered());
			currCode.setDateExpires(originalCurrCode.getDateExpires());
			currCode.setStatus(originalCurrCode.getStatus());
			currCode.setOwner(originalCurrCode.getOwner());
			currCode.setConversionToBase(originalCurrCode.getConversionToBase());

			incomingRequest.put("currCode", currCode);

			CurrCodeAdd addTask = new CurrCodeAdd();
			currCode = (CurrCode) addTask.executeTask(incomingRequest);
			this.setStatus(addTask.getStatus()) ;

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