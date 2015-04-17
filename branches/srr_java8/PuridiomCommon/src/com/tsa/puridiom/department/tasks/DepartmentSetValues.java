package com.tsa.puridiom.department.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class DepartmentSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			Department department = (Department) incomingRequest.get("department");
			if (department == null)
			{
				department = new Department();
			}

			if (incomingRequest.containsKey("Department_departmentCode"))
			{
				String departmentCode = (String ) incomingRequest.get("Department_departmentCode");
				department.setDepartmentCode(departmentCode);
			}
			if (incomingRequest.containsKey("Department_departmentName"))
			{
				String departmentName = (String ) incomingRequest.get("Department_departmentName");
				department.setDepartmentName(departmentName);
			}
			if (incomingRequest.containsKey("Department_deptManager"))
			{
				String deptManager = (String ) incomingRequest.get("Department_deptManager");
				department.setDeptManager(deptManager);
			}
			if (incomingRequest.containsKey("Department_division"))
			{
				String division = (String ) incomingRequest.get("Department_division");
				department.setDivision(division);
			}
			if (incomingRequest.containsKey("Department_dateEntered"))
			{
				String dateEnteredString = (String) incomingRequest.get("Department_dateEntered");
				Date dateEntered = Dates.getDate(dateEnteredString);
				department.setDateEntered(dateEntered);
			}
			if (incomingRequest.containsKey("Department_dateExpires"))
			{
				String dateExpiresString = (String) incomingRequest.get("Department_dateExpires");
				Date dateExpires = Dates.getDate(dateExpiresString);
				department.setDateExpires(dateExpires);
			}
			if (incomingRequest.containsKey("Department_owner"))
			{
				String owner = (String ) incomingRequest.get("Department_owner");
				department.setOwner(owner);
			}
			if (incomingRequest.containsKey("Department_status"))
			{
				String status = (String ) incomingRequest.get("Department_status");
				department.setStatus(status);
			}
			if (incomingRequest.containsKey("Department_deptApprv1"))
			{
				String deptApprv1 = (String ) incomingRequest.get("Department_deptApprv1");
				department.setDeptApprv1(deptApprv1);
			}
			if (incomingRequest.containsKey("Department_deptApprv2"))
			{
				String deptApprv2 = (String ) incomingRequest.get("Department_deptApprv2");
				department.setDeptApprv2(deptApprv2);
			}
			if (incomingRequest.containsKey("Department_deptApprv3"))
			{
				String deptApprv3 = (String ) incomingRequest.get("Department_deptApprv3");
				department.setDeptApprv3(deptApprv3);
			}
			if (incomingRequest.containsKey("Department_deptApprv4"))
			{
				String deptApprv4 = (String ) incomingRequest.get("Department_deptApprv4");
				department.setDeptApprv4(deptApprv4);
			}
			if (incomingRequest.containsKey("Department_deptApprv5"))
			{
				String deptApprv5 = (String ) incomingRequest.get("Department_deptApprv5");
				department.setDeptApprv5(deptApprv5);
			}
			if (incomingRequest.containsKey("Department_managerAmount"))
			{
				String managerAmountString = (String) incomingRequest.get("Department_managerAmount");
				if (Utility.isEmpty(managerAmountString))
				{
					managerAmountString = "0";
				}
				BigDecimal managerAmount = new BigDecimal ( managerAmountString );
				department.setManagerAmount(managerAmount);
			}
			if (incomingRequest.containsKey("Department_apprv1Amount"))
			{
				String apprv1AmountString = (String) incomingRequest.get("Department_apprv1Amount");
				if (Utility.isEmpty(apprv1AmountString))
				{
					apprv1AmountString = "0";
				}
				BigDecimal apprv1Amount = new BigDecimal ( apprv1AmountString );
				department.setApprv1Amount(apprv1Amount);
			}
			if (incomingRequest.containsKey("Department_apprv2Amount"))
			{
				String apprv2AmountString = (String) incomingRequest.get("Department_apprv2Amount");
				if (Utility.isEmpty(apprv2AmountString))
				{
					apprv2AmountString = "0";
				}
				BigDecimal apprv2Amount = new BigDecimal ( apprv2AmountString );
				department.setApprv2Amount(apprv2Amount);
			}
			if (incomingRequest.containsKey("Department_apprv3Amount"))
			{
				String apprv3AmountString = (String) incomingRequest.get("Department_apprv3Amount");
				if (Utility.isEmpty(apprv3AmountString))
				{
					apprv3AmountString = "0";
				}
				BigDecimal apprv3Amount = new BigDecimal ( apprv3AmountString );
				department.setApprv3Amount(apprv3Amount);
			}
			if (incomingRequest.containsKey("Department_apprv4Amount"))
			{
				String apprv4AmountString = (String) incomingRequest.get("Department_apprv4Amount");
				if (Utility.isEmpty(apprv4AmountString))
				{
					apprv4AmountString = "0";
				}
				BigDecimal apprv4Amount = new BigDecimal ( apprv4AmountString );
				department.setApprv4Amount(apprv4Amount);
			}
			if (incomingRequest.containsKey("Department_apprv5Amount"))
			{
				String apprv5AmountString = (String) incomingRequest.get("Department_apprv5Amount");
				if (Utility.isEmpty(apprv5AmountString))
				{
					apprv5AmountString = "0";
				}
				BigDecimal apprv5Amount = new BigDecimal ( apprv5AmountString );
				department.setApprv5Amount(apprv5Amount);
			}

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