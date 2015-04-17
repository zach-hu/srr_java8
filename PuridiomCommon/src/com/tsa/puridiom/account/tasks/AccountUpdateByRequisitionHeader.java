/**
 * 
 */
package com.tsa.puridiom.account.tasks;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.Account;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * @author Johnny
 */
public class AccountUpdateByRequisitionHeader extends Task
{
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{

		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{
			RequisitionHeader requisitionHeader = (RequisitionHeader) incomingRequest.get("requisitionHeader");
			List accountList = (List) incomingRequest.get("accountList");

			if (accountList != null)
			{

				for (Iterator iterator = accountList.iterator(); iterator.hasNext();)
				{
					Account account = (Account) iterator.next();

					account.setFld5(requisitionHeader.getUdf2Code());
					account.setFld6(requisitionHeader.getUdf10Code());
				}
			}

			result = accountList;
			
			this.setStatus(Status.SUCCEEDED);

		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);

			Log.error(this, "AccountUpdateByRequisitionHeader failed! ");

			throw e;
		}
		return result;
	}
}