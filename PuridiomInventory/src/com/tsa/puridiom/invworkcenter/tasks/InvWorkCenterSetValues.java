package com.tsa.puridiom.invworkcenter.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class InvWorkCenterSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			InvWorkCenter invWorkCenter = (InvWorkCenter) incomingRequest.get("invWorkCenter");
			if (invWorkCenter == null)
			{
				invWorkCenter = new InvWorkCenter();
			}

			if (incomingRequest.containsKey("InvWorkCenter_workCenterId"))
			{
				String workCenterId = (String) incomingRequest.get("InvWorkCenter_workCenterId");
				invWorkCenter.setWorkCenterId(workCenterId);
			}
			if (incomingRequest.containsKey("InvWorkCenter_description"))
			{
				String description = (String) incomingRequest.get("InvWorkCenter_description");
				invWorkCenter.setDescription(description);
			}
			if (incomingRequest.containsKey("InvWorkCenter_departmentCode"))
			{
				String departmentCode = (String) incomingRequest.get("InvWorkCenter_departmentCode");
				invWorkCenter.setDepartmentCode(departmentCode);
			}
			if (incomingRequest.containsKey("InvWorkCenter_subcontract"))
			{
				String subcontract = (String) incomingRequest.get("InvWorkCenter_subcontract");
				invWorkCenter.setSubcontract(subcontract);
			}
			if (incomingRequest.containsKey("InvWorkCenter_laborRate"))
			{
				String laborRateString = (String) incomingRequest.get("InvWorkCenter_laborRate");
				if (Utility.isEmpty(laborRateString))
				{
					laborRateString = "0";
				}
				BigDecimal laborRate = new BigDecimal ( laborRateString );
				invWorkCenter.setLaborRate(laborRate);
			}
			if (incomingRequest.containsKey("InvWorkCenter_setupRate"))
			{
				String setupRateString = (String) incomingRequest.get("InvWorkCenter_setupRate");
				if (Utility.isEmpty(setupRateString))
				{
					setupRateString = "0";
				}
				BigDecimal setupRate = new BigDecimal ( setupRateString );
				invWorkCenter.setSetupRate(setupRate);
			}
			if (incomingRequest.containsKey("InvWorkCenter_fixOverHead"))
			{
				String fixOverHeadString = (String) incomingRequest.get("InvWorkCenter_fixOverHead");
				if (Utility.isEmpty(fixOverHeadString))
				{
					fixOverHeadString = "0";
				}
				BigDecimal fixOverHead = new BigDecimal ( fixOverHeadString );
				invWorkCenter.setFixOverHead(fixOverHead);
			}
			if (incomingRequest.containsKey("InvWorkCenter_varOverHead"))
			{
				String varOverHeadString = (String) incomingRequest.get("InvWorkCenter_varOverHead");
				if (Utility.isEmpty(varOverHeadString))
				{
					varOverHeadString = "0";
				}
				BigDecimal varOverHead = new BigDecimal ( varOverHeadString );
				invWorkCenter.setVarOverHead(varOverHead);
			}
			if (incomingRequest.containsKey("InvWorkCenter_setuphours"))
			{
				String setuphoursString = (String) incomingRequest.get("InvWorkCenter_setuphours");
				if (Utility.isEmpty(setuphoursString))
				{
					setuphoursString = "0";
				}
				BigDecimal setuphours = new BigDecimal ( setuphoursString );
				invWorkCenter.setSetuphours(setuphours);
			}
			if (incomingRequest.containsKey("InvWorkCenter_perDayHours"))
			{
				String perDayHoursString = (String) incomingRequest.get("InvWorkCenter_perDayHours");
				if (Utility.isEmpty(perDayHoursString))
				{
					perDayHoursString = "0";
				}
				BigDecimal perDayHours = new BigDecimal ( perDayHoursString );
				invWorkCenter.setPerDayHours(perDayHours);
			}
			if (incomingRequest.containsKey("InvWorkCenter_perJobHours"))
			{
				String perJobHoursString = (String) incomingRequest.get("InvWorkCenter_perJobHours");
				if (Utility.isEmpty(perJobHoursString))
				{
					perJobHoursString = "0";
				}
				BigDecimal perJobHours = new BigDecimal ( perJobHoursString );
				invWorkCenter.setPerJobHours(perJobHours);
			}
			if (incomingRequest.containsKey("InvWorkCenter_processTime"))
			{
				String processTimeString = (String) incomingRequest.get("InvWorkCenter_processTime");
				if (Utility.isEmpty(processTimeString))
				{
					processTimeString = "0";
				}
				BigDecimal processTime = new BigDecimal ( processTimeString );
				invWorkCenter.setProcessTime(processTime);
			}
			if (incomingRequest.containsKey("InvWorkCenter_processItems"))
			{
				String processItemsString = (String) incomingRequest.get("InvWorkCenter_processItems");
				if (Utility.isEmpty(processItemsString))
				{
					processItemsString = "0";
				}
				BigDecimal processItems = new BigDecimal ( processItemsString );
				invWorkCenter.setProcessItems(processItems);
			}
			if (incomingRequest.containsKey("InvWorkCenter_bufferDays"))
			{
				String bufferDaysString = (String) incomingRequest.get("InvWorkCenter_bufferDays");
				if (Utility.isEmpty(bufferDaysString))
				{
					bufferDaysString = "0";
				}
				BigDecimal bufferDays = new BigDecimal ( bufferDaysString );
				invWorkCenter.setBufferDays(bufferDays);
			}
			if (incomingRequest.containsKey("InvWorkCenter_vendorName"))
			{
				String vendorName = (String) incomingRequest.get("InvWorkCenter_vendorName");
				invWorkCenter.setVendorName(vendorName);
			}
			if (incomingRequest.containsKey("InvWorkCenter_vendorId"))
			{
				String vendorId = (String) incomingRequest.get("InvWorkCenter_vendorId");
				invWorkCenter.setVendorId(vendorId);
			}
			if (incomingRequest.containsKey("InvWorkCenter_cost"))
			{
				String costString = (String) incomingRequest.get("InvWorkCenter_cost");
				if (Utility.isEmpty(costString))
				{
					costString = "0";
				}
				BigDecimal cost = new BigDecimal ( costString );
				invWorkCenter.setCost(cost);
			}
			if (incomingRequest.containsKey("InvWorkCenter_unitOfMeasure"))
			{
				String unitOfMeasure = (String) incomingRequest.get("InvWorkCenter_unitOfMeasure");
				invWorkCenter.setUnitOfMeasure(unitOfMeasure);
			}
			if (incomingRequest.containsKey("InvWorkCenter_leadTime"))
			{
				String leadTimeString = (String) incomingRequest.get("InvWorkCenter_leadTime");
				if (Utility.isEmpty(leadTimeString))
				{
					leadTimeString = "0";
				}
				BigDecimal leadTime = new BigDecimal ( leadTimeString );
				invWorkCenter.setLeadTime(leadTime);
			}
			if (incomingRequest.containsKey("InvWorkCenter_dateEntered"))
			{
				String dateEnteredString = (String) incomingRequest.get("InvWorkCenter_dateEntered");
				Date dateEntered = Dates.getDate(dateEnteredString);
				invWorkCenter.setDateEntered(dateEntered);
			}
			if (incomingRequest.containsKey("InvWorkCenter_dateExpires"))
			{
				String dateExpiresString = (String) incomingRequest.get("InvWorkCenter_dateExpires");
				Date dateExpires = Dates.getDate(dateExpiresString);
				invWorkCenter.setDateExpires(dateExpires);
			}
			if (incomingRequest.containsKey("InvWorkCenter_owner"))
			{
				String owner = (String) incomingRequest.get("InvWorkCenter_owner");
				invWorkCenter.setOwner(owner);
			}
			if (incomingRequest.containsKey("InvWorkCenter_status"))
			{
				String status = (String) incomingRequest.get("InvWorkCenter_status");
				invWorkCenter.setStatus(status);
			}

			result = invWorkCenter;
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