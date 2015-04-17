package com.tsa.puridiom.requisitionline.tasks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.Account;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

@SuppressWarnings(value = { "unchecked" })
public class RequisitionLineDefaultAccount extends Task {

	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
		Object result = null;
		Map accountFlds = null;
		try
		{
			List requisitionHeaderAccountList = (List) incomingRequest.get("requisitionHeaderAccountList");
			if(requisitionHeaderAccountList != null && !requisitionHeaderAccountList.isEmpty()){
				Account account = (Account)requisitionHeaderAccountList.get(0);
				accountFlds = new HashMap();
				accountFlds.put("Account_fld1", account.getFld1());
				accountFlds.put("Account_fld2", account.getFld2());
				accountFlds.put("Account_fld3", account.getFld3());
				
				result = accountFlds;
			}

	        this.setStatus(Status.SUCCEEDED) ;
		}
		catch(Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}

		return result ;
	}

}
