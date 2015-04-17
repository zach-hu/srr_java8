package com.tsa.puridiom.requisitionline.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.util.*;

public class RequisitionLineDuplicate extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain requisitionLine */
			RequisitionLine	requisitionLine = (RequisitionLine) incomingRequest.get("requisitionLine");

			BigDecimal orgIcAccount = requisitionLine.getIcAccount() ;
			if (orgIcAccount == null) orgIcAccount = new BigDecimal("0") ;
			if (orgIcAccount.compareTo(new BigDecimal("0")) > 0) {
				requisitionLine.setIcAccount(requisitionLine.getIcReqLine()) ;
			} else {
				requisitionLine.setIcAccount(new BigDecimal("0")) ;
			}

			result = requisitionLine;
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
