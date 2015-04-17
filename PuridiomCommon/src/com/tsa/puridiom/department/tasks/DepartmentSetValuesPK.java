package com.tsa.puridiom.department.tasks;

import com.tsa.puridiom.entity.Department;
import java.util.Map;

public class DepartmentSetValuesPK
{
	public void setValues(Map incomingRequest, Department department ) throws Exception
	{
		try
		{
			String departmentCode = (String ) incomingRequest.get("Department_departmentCode");
			department.setDepartmentCode(departmentCode);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}