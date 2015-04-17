package com.tsa.puridiom.department.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import java.util.*;

public class DepartmentSaveas extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain department */
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			Department	originalDepartment = (Department) incomingRequest.get("department");
			Department	department = new Department();

			department.setDepartmentCode(originalDepartment.getDepartmentCode());
			department.setDepartmentName(originalDepartment.getDepartmentName());
			department.setDeptManager(originalDepartment.getDeptManager());
			department.setDivision(originalDepartment.getDivision());
			department.setDateEntered(originalDepartment.getDateEntered());
			department.setDateExpires(originalDepartment.getDateExpires());
			department.setOwner(originalDepartment.getOwner());
			department.setStatus(originalDepartment.getStatus());
			department.setDeptApprv1(originalDepartment.getDeptApprv1());
			department.setDeptApprv2(originalDepartment.getDeptApprv2());
			department.setDeptApprv3(originalDepartment.getDeptApprv3());
			department.setDeptApprv4(originalDepartment.getDeptApprv4());
			department.setDeptApprv5(originalDepartment.getDeptApprv5());
			department.setManagerAmount(originalDepartment.getManagerAmount());
			department.setApprv1Amount(originalDepartment.getApprv1Amount());
			department.setApprv2Amount(originalDepartment.getApprv2Amount());
			department.setApprv3Amount(originalDepartment.getApprv3Amount());
			department.setApprv4Amount(originalDepartment.getApprv4Amount());
			department.setApprv5Amount(originalDepartment.getApprv5Amount());

			incomingRequest.put("department", department);

			DepartmentAdd addTask = new DepartmentAdd();
			department = (Department) addTask.executeTask(incomingRequest);
			this.setStatus(addTask.getStatus()) ;

			result = department;
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