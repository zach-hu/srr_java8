package com.tsa.puridiom.department.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import java.util.Map;

public class DepartmentSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			incomingRequest.put("Department_departmentCode", "");
			incomingRequest.put("Department_departmentName", "");
			incomingRequest.put("Department_deptManager", "");
			incomingRequest.put("Department_division", "");
			incomingRequest.put("Department_dateEntered", Dates.today("", ""));
			incomingRequest.put("Department_dateExpires", Dates.today("", ""));
			incomingRequest.put("Department_owner", "");
			incomingRequest.put("Department_status", "");
			incomingRequest.put("Department_deptApprv1", "");
			incomingRequest.put("Department_deptApprv2", "");
			incomingRequest.put("Department_deptApprv3", "");
			incomingRequest.put("Department_deptApprv4", "");
			incomingRequest.put("Department_deptApprv5", "");
			incomingRequest.put("Department_managerAmount", "0");
			incomingRequest.put("Department_apprv1Amount", "0");
			incomingRequest.put("Department_apprv2Amount", "0");
			incomingRequest.put("Department_apprv3Amount", "0");
			incomingRequest.put("Department_apprv4Amount", "0");
			incomingRequest.put("Department_apprv5Amount", "0");

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