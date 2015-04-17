package com.tsa.puridiom.bomrouting.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class BomRoutingSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			BomRouting bomRouting = (BomRouting) incomingRequest.get("bomRouting");
			if (bomRouting == null)
			{
				bomRouting = new BomRouting();
			}

			if (incomingRequest.containsKey("BomRouting_icRouting"))
			{
				String icRoutingString = (String) incomingRequest.get("BomRouting_icRouting");
				if (Utility.isEmpty(icRoutingString))
				{
					icRoutingString = "0";
				}
				BigDecimal icRouting = new BigDecimal ( icRoutingString );
				bomRouting.setIcRouting(icRouting);
			}

			if (incomingRequest.containsKey("BomRouting_icMethod"))
			{
				String icMethodString = (String) incomingRequest.get("BomRouting_icMethod");
				if (Utility.isEmpty(icMethodString))
				{
					icMethodString = "0";
				}
				BigDecimal icMethod = new BigDecimal ( icMethodString );
				bomRouting.setIcMethod(icMethod);
			}
			if (incomingRequest.containsKey("BomRouting_parentItem"))
			{
				String parentItem = (String) incomingRequest.get("BomRouting_parentItem");
				bomRouting.setParentItem(parentItem);
			}
			if (incomingRequest.containsKey("BomRouting_componentItem"))
			{
				String componentItem = (String) incomingRequest.get("BomRouting_componentItem");
				bomRouting.setComponentItem(componentItem);
			}
			if (incomingRequest.containsKey("BomRouting_stageId"))
			{
				String stageId = (String) incomingRequest.get("BomRouting_stageId");
				bomRouting.setStageId(stageId);
			}
			if (incomingRequest.containsKey("BomRouting_stageSeq"))
			{
				String stageSeqString = (String) incomingRequest.get("BomRouting_stageSeq");
				if (Utility.isEmpty(stageSeqString))
				{
					stageSeqString = "0";
				}
				BigDecimal stageSeq = new BigDecimal ( stageSeqString );
				bomRouting.setStageSeq(stageSeq);
			}
			if (incomingRequest.containsKey("BomRouting_description"))
			{
				String description = (String) incomingRequest.get("BomRouting_description");
				bomRouting.setDescription(description);
			}
			if (incomingRequest.containsKey("BomRouting_workCenterId"))
			{
				String workCenterId = (String) incomingRequest.get("BomRouting_workCenterId");
				bomRouting.setWorkCenterId(workCenterId);
			}
			if (incomingRequest.containsKey("BomRouting_machineId"))
			{
				String machineId = (String) incomingRequest.get("BomRouting_machineId");
				bomRouting.setMachineId(machineId);
			}
			if (incomingRequest.containsKey("BomRouting_respons"))
			{
				String respons = (String) incomingRequest.get("BomRouting_respons");
				bomRouting.setRespons(respons);
			}
			if (incomingRequest.containsKey("BomRouting_utilization"))
			{
				String utilizationString = (String) incomingRequest.get("BomRouting_utilization");
				if (Utility.isEmpty(utilizationString))
				{
					utilizationString = "0";
				}
				BigDecimal utilization = new BigDecimal ( utilizationString );
				bomRouting.setUtilization(utilization);
			}
			if (incomingRequest.containsKey("BomRouting_qtyDays"))
			{
				String qtyDaysString = (String) incomingRequest.get("BomRouting_qtyDays");
				if (Utility.isEmpty(qtyDaysString))
				{
					qtyDaysString = "0";
				}
				BigDecimal qtyDays = new BigDecimal ( qtyDaysString );
				bomRouting.setQtyDays(qtyDays);
			}
			if (incomingRequest.containsKey("BomRouting_setupHours"))
			{
				String setupHoursString = (String) incomingRequest.get("BomRouting_setupHours");
				if (Utility.isEmpty(setupHoursString))
				{
					setupHoursString = "0";
				}
				BigDecimal setupHours = new BigDecimal ( setupHoursString );
				bomRouting.setSetupHours(setupHours);
			}
			if (incomingRequest.containsKey("BomRouting_partsHour"))
			{
				String partsHourString = (String) incomingRequest.get("BomRouting_partsHour");
				if (Utility.isEmpty(partsHourString))
				{
					partsHourString = "0";
				}
				BigDecimal partsHour = new BigDecimal ( partsHourString );
				bomRouting.setPartsHour(partsHour);
			}
			if (incomingRequest.containsKey("BomRouting_timePart"))
			{
				String timePartString = (String) incomingRequest.get("BomRouting_timePart");
				if (Utility.isEmpty(timePartString))
				{
					timePartString = "0";
				}
				BigDecimal timePart = new BigDecimal ( timePartString );
				bomRouting.setTimePart(timePart);
			}
			if (incomingRequest.containsKey("BomRouting_vendorName"))
			{
				String vendorName = (String) incomingRequest.get("BomRouting_vendorName");
				bomRouting.setVendorName(vendorName);
			}
			if (incomingRequest.containsKey("BomRouting_leadTime"))
			{
				String leadTimeString = (String) incomingRequest.get("BomRouting_leadTime");
				if (Utility.isEmpty(leadTimeString))
				{
					leadTimeString = "0";
				}
				BigDecimal leadTime = new BigDecimal ( leadTimeString );
				bomRouting.setLeadTime(leadTime);
			}
			if (incomingRequest.containsKey("BomRouting_outside"))
			{
				String outside = (String) incomingRequest.get("BomRouting_outside");
				bomRouting.setOutside(outside);
			}
			if (incomingRequest.containsKey("BomRouting_backflush"))
			{
				String backflush = (String) incomingRequest.get("BomRouting_backflush");
				bomRouting.setBackflush(backflush);
			}
			if (incomingRequest.containsKey("BomRouting_persons"))
			{
				String personsString = (String) incomingRequest.get("BomRouting_persons");
				if (Utility.isEmpty(personsString))
				{
					personsString = "0";
				}
				BigDecimal persons = new BigDecimal ( personsString );
				bomRouting.setPersons(persons);
			}
			if (incomingRequest.containsKey("BomRouting_ccost"))
			{
				String ccostString = (String) incomingRequest.get("BomRouting_ccost");
				if (Utility.isEmpty(ccostString))
				{
					ccostString = "0";
				}
				BigDecimal ccost = new BigDecimal ( ccostString );
				bomRouting.setCcost(ccost);
			}
			if (incomingRequest.containsKey("BomRouting_unitOfMeasure"))
			{
				String unitOfMeasure = (String) incomingRequest.get("BomRouting_unitOfMeasure");
				bomRouting.setUnitOfMeasure(unitOfMeasure);
			}
			if (incomingRequest.containsKey("BomRouting_poNotes"))
			{
				String poNotes = (String) incomingRequest.get("BomRouting_poNotes");
				bomRouting.setPoNotes(poNotes);
			}
			if (incomingRequest.containsKey("BomRouting_notes"))
			{
				String notes = (String) incomingRequest.get("BomRouting_notes");
				bomRouting.setNotes(notes);
			}
			if (incomingRequest.containsKey("BomRouting_dateEntered"))
			{
				String dateEnteredString = (String) incomingRequest.get("BomRouting_dateEntered");
				Date dateEntered = Dates.getDate(dateEnteredString);
				bomRouting.setDateEntered(dateEntered);
			}
			if (incomingRequest.containsKey("BomRouting_owner"))
			{
				String owner = (String) incomingRequest.get("BomRouting_owner");
				bomRouting.setOwner(owner);
			}

			result = bomRouting;
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