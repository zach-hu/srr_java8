package com.tsa.puridiom.accountqxref.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class AccountQxrefSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			AccountQxrefPK comp_id = new AccountQxrefPK();
			AccountQxref accountQxref = (AccountQxref) incomingRequest.get("accountQxref");
			if (accountQxref == null)
			{
				accountQxref = new AccountQxref();
			}

			if (incomingRequest.containsKey("AccountQxref_location"))
			{
				String location = (String) incomingRequest.get("AccountQxref_location");
				comp_id.setLocation(location);
			}
			if (incomingRequest.containsKey("AccountQxref_afe"))
			{
				String afe = (String) incomingRequest.get("AccountQxref_afe");
				comp_id.setAfe(afe);
			}
			if (incomingRequest.containsKey("AccountQxref_wellFacility"))
			{
				String wellFacility = (String) incomingRequest.get("AccountQxref_wellFacility");
				accountQxref.setWellFacility(wellFacility);
			}
			if (incomingRequest.containsKey("AccountQxref_costCenter"))
			{
				String costCenter = (String) incomingRequest.get("AccountQxref_costCenter");
				accountQxref.setCostCenter(costCenter);
			}
			accountQxref.setComp_id(comp_id);

			result = accountQxref;
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