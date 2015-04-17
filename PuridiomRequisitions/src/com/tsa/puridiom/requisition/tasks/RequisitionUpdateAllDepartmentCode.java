package com.tsa.puridiom.requisition.tasks;

import com.tsa.puridiom.account.tasks.AccountRetrieveBy;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.Account;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequisitionUpdateAllDepartmentCode extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			RequisitionHeader requisitionHeader = (RequisitionHeader) incomingRequest.get("requisitionHeader");
			List reqLineList = (List) incomingRequest.get("requisitionLineList");

			DBSession dbs = (DBSession)incomingRequest.get("dbsession");

			if (requisitionHeader != null && !HiltonUtility.isEmpty(requisitionHeader.getDepartmentCode()))
			{
				String departmentCode = requisitionHeader.getDepartmentCode();

				if (reqLineList != null && reqLineList.size() > 0)
				{
					for (int i = 0; i < reqLineList.size(); i++)
					{
						RequisitionLine reqLine = (RequisitionLine)reqLineList.get(i);
						reqLine.setDepartmentCode(departmentCode);
						dbs.update(reqLine);
					}
				}

				AccountRetrieveBy accountRetrieveBy = new AccountRetrieveBy();
				Map newMap = new HashMap();
				newMap.put("dbsession", dbs);
				newMap.put("Account_icHeader", requisitionHeader.getIcReqHeader().toString());
				List accountList = (List)accountRetrieveBy.executeTask(newMap);

				if (accountList != null && accountList.size() > 0)
				{
					for (int i = 0; i < accountList.size(); i++)
					{
						Account account = (Account)accountList.get(i);
						account.setFld3(departmentCode);
						dbs.update(account);
					}
				}
			}
			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			Log.error(this, e.getMessage());
			e.printStackTrace();
			throw e;
		}
		return result;
	}
}
