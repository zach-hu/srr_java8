package com.tsa.puridiom.organizationpackage.tasks;

import com.tsa.puridiom.common.documents.GeneralStatus;

import com.tsa.puridiom.entity.OrganizationPackage;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;

import java.util.Map;

public class OrganizationPackagePaymentProcessed extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			OrganizationPackage op = (OrganizationPackage) incomingRequest.get("organizationPackage") ;
			String packageName = (String) incomingRequest.get("packageName");

			op.setDatePaid(Dates.getCurrentDate("")) ;
			if (packageName.toUpperCase().startsWith("FREE")) {
				op.setStatus(GeneralStatus.STATUS_TEMPORARY) ;
			} else {
				op.setStatus(GeneralStatus.STATUS_PERMANENT) ;
			}
			op.setTransactionId((String)incomingRequest.get("ccTranId")) ;
			result = op ;
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