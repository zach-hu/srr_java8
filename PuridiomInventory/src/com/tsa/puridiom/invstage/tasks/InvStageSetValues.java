package com.tsa.puridiom.invstage.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class InvStageSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			InvStage invStage = (InvStage) incomingRequest.get("invStage");
			if (invStage == null)
			{
				invStage = new InvStage();
			}

			if (incomingRequest.containsKey("InvStage_stageId"))
			{
				String stageId = (String) incomingRequest.get("InvStage_stageId");
				invStage.setStageId(stageId);
			}
			if (incomingRequest.containsKey("InvStage_description"))
			{
				String description = (String) incomingRequest.get("InvStage_description");
				invStage.setDescription(description);
			}
			if (incomingRequest.containsKey("InvStage_respons"))
			{
				String respons = (String) incomingRequest.get("InvStage_respons");
				invStage.setRespons(respons);
			}
			if (incomingRequest.containsKey("InvStage_notes"))
			{
				String notes = (String) incomingRequest.get("InvStage_notes");
				invStage.setNotes(notes);
			}
			if (incomingRequest.containsKey("InvStage_workCenterId"))
			{
				String workCenterId = (String) incomingRequest.get("InvStage_workCenterId");
				invStage.setWorkCenterId(workCenterId);
			}
			if (incomingRequest.containsKey("InvStage_utilization"))
			{
				String utilizationString = (String) incomingRequest.get("InvStage_utilization");
				if (Utility.isEmpty(utilizationString))
				{
					utilizationString = "0";
				}
				BigDecimal utilization = new BigDecimal ( utilizationString );
				invStage.setUtilization(utilization);
			}
			if (incomingRequest.containsKey("InvStage_qtyDays"))
			{
				String qtyDaysString = (String) incomingRequest.get("InvStage_qtyDays");
				if (Utility.isEmpty(qtyDaysString))
				{
					qtyDaysString = "0";
				}
				BigDecimal qtyDays = new BigDecimal ( qtyDaysString );
				invStage.setQtyDays(qtyDays);
			}
			if (incomingRequest.containsKey("InvStage_setupHours"))
			{
				String setupHoursString = (String) incomingRequest.get("InvStage_setupHours");
				if (Utility.isEmpty(setupHoursString))
				{
					setupHoursString = "0";
				}
				BigDecimal setupHours = new BigDecimal ( setupHoursString );
				invStage.setSetupHours(setupHours);
			}
			if (incomingRequest.containsKey("InvStage_partsHour"))
			{
				String partsHourString = (String) incomingRequest.get("InvStage_partsHour");
				if (Utility.isEmpty(partsHourString))
				{
					partsHourString = "0";
				}
				BigDecimal partsHour = new BigDecimal ( partsHourString );
				invStage.setPartsHour(partsHour);
			}
			if (incomingRequest.containsKey("InvStage_timePart"))
			{
				String timePartString = (String) incomingRequest.get("InvStage_timePart");
				if (Utility.isEmpty(timePartString))
				{
					timePartString = "0";
				}
				BigDecimal timePart = new BigDecimal ( timePartString );
				invStage.setTimePart(timePart);
			}
			if (incomingRequest.containsKey("InvStage_vendorName"))
			{
				String vendorName = (String) incomingRequest.get("InvStage_vendorName");
				invStage.setVendorName(vendorName);
			}
			if (incomingRequest.containsKey("InvStage_leadTime"))
			{
				String leadTimeString = (String) incomingRequest.get("InvStage_leadTime");
				if (Utility.isEmpty(leadTimeString))
				{
					leadTimeString = "0";
				}
				BigDecimal leadTime = new BigDecimal ( leadTimeString );
				invStage.setLeadTime(leadTime);
			}
			if (incomingRequest.containsKey("InvStage_outside"))
			{
				String outside = (String) incomingRequest.get("InvStage_outside");
				invStage.setOutside(outside);
			}
			if (incomingRequest.containsKey("InvStage_descriptor"))
			{
				String descriptor = (String) incomingRequest.get("InvStage_descriptor");
				invStage.setDescriptor(descriptor);
			}
			if (incomingRequest.containsKey("InvStage_machineId"))
			{
				String machineId = (String) incomingRequest.get("InvStage_machineId");
				invStage.setMachineId(machineId);
			}
			if (incomingRequest.containsKey("InvStage_backflush"))
			{
				String backflush = (String) incomingRequest.get("InvStage_backflush");
				invStage.setBackflush(backflush);
			}

			if (incomingRequest.containsKey("InvStage_persons"))
			{
				String personsString = (String) incomingRequest.get("InvStage_persons");
				if (Utility.isEmpty(personsString))
				{
					personsString = "0";
				}
				BigDecimal persons = new BigDecimal ( personsString );
				invStage.setPersons(persons);
			}
			if (incomingRequest.containsKey("InvStage_ccost"))
			{
				String ccostString = (String) incomingRequest.get("InvStage_ccost");
				if (Utility.isEmpty(ccostString))
				{
					ccostString = "0";
				}
				BigDecimal ccost = new BigDecimal ( ccostString );
				invStage.setCcost(ccost);
			}
			if (incomingRequest.containsKey("InvStage_unitOfMeasure"))
			{
				String unitOfMeasure = (String) incomingRequest.get("InvStage_unitOfMeasure");
				invStage.setUnitOfMeasure(unitOfMeasure);
			}

			if (incomingRequest.containsKey("InvStage_dateEntered"))
			{
				String dateEnteredString = (String) incomingRequest.get("InvStage_dateEntered");
				Date dateEntered = Dates.getDate(dateEnteredString);
				invStage.setDateEntered(dateEntered);
			}
			if (incomingRequest.containsKey("InvStage_dateExpires"))
			{
				String dateExpiresString = (String) incomingRequest.get("InvStage_dateExpires");
				Date dateExpires = Dates.getDate(dateExpiresString);
				invStage.setDateExpires(dateExpires);
			}
			if (incomingRequest.containsKey("InvStage_owner"))
			{
				String owner = (String) incomingRequest.get("InvStage_owner");
				invStage.setOwner(owner);
			}
			if (incomingRequest.containsKey("InvStage_status"))
			{
				String status = (String) incomingRequest.get("InvStage_status");
				invStage.setStatus(status);
			}

			result = invStage;
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