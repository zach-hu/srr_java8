package com.tsa.puridiom.invworkcenter.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;
public class InvWorkCenterRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from InvWorkCenter as invworkcenter where 1=1 ");
		if(incomingRequest.containsKey("InvWorkCenter_workCenterId"))
		{
			String workCenterId = (String) incomingRequest.get("InvWorkCenter_workCenterId");
			queryString.append(" AND invworkcenter.workCenterId = '" + workCenterId + "'");
		}
		if(incomingRequest.containsKey("InvWorkCenter_description"))
		{
			String description = (String) incomingRequest.get("InvWorkCenter_description");
			queryString.append(" AND invworkcenter.description = '" + description + "'");
		}
		if(incomingRequest.containsKey("InvWorkCenter_departmentCode"))
		{
			String departmentCode = (String) incomingRequest.get("InvWorkCenter_departmentCode");
			queryString.append(" AND invworkcenter.departmentCode = '" + departmentCode + "'");
		}
		if(incomingRequest.containsKey("InvWorkCenter_subcontract"))
		{
			String subcontract = (String) incomingRequest.get("InvWorkCenter_subcontract");
			queryString.append(" AND invworkcenter.subcontract = '" + subcontract + "'");
		}
		if(incomingRequest.containsKey("InvWorkCenter_laborRate"))
		{
			String laborRate = (String) incomingRequest.get("InvWorkCenter_laborRate");
			queryString.append(" AND invworkcenter.laborRate = '" + laborRate + "'");
		}
		if(incomingRequest.containsKey("InvWorkCenter_setupRate"))
		{
			String setupRate = (String) incomingRequest.get("InvWorkCenter_setupRate");
			queryString.append(" AND invworkcenter.setupRate = '" + setupRate + "'");
		}
		if(incomingRequest.containsKey("InvWorkCenter_fixOverHead"))
		{
			String fixOverHead = (String) incomingRequest.get("InvWorkCenter_fixOverHead");
			queryString.append(" AND invworkcenter.fixOverHead = '" + fixOverHead + "'");
		}
		if(incomingRequest.containsKey("InvWorkCenter_varOverHead"))
		{
			String varOverHead = (String) incomingRequest.get("InvWorkCenter_varOverHead");
			queryString.append(" AND invworkcenter.varOverHead = '" + varOverHead + "'");
		}
		if(incomingRequest.containsKey("InvWorkCenter_setuphours"))
		{
			String setuphours = (String) incomingRequest.get("InvWorkCenter_setuphours");
			queryString.append(" AND invworkcenter.setuphours = '" + setuphours + "'");
		}
		if(incomingRequest.containsKey("InvWorkCenter_perDayHours"))
		{
			String perDayHours = (String) incomingRequest.get("InvWorkCenter_perDayHours");
			queryString.append(" AND invworkcenter.perDayHours = '" + perDayHours + "'");
		}
		if(incomingRequest.containsKey("InvWorkCenter_perJobHours"))
		{
			String perJobHours = (String) incomingRequest.get("InvWorkCenter_perJobHours");
			queryString.append(" AND invworkcenter.perJobHours = '" + perJobHours + "'");
		}
		if(incomingRequest.containsKey("InvWorkCenter_processTime"))
		{
			String processTime = (String) incomingRequest.get("InvWorkCenter_processTime");
			queryString.append(" AND invworkcenter.processTime = '" + processTime + "'");
		}
		if(incomingRequest.containsKey("InvWorkCenter_processItems"))
		{
			String processItems = (String) incomingRequest.get("InvWorkCenter_processItems");
			queryString.append(" AND invworkcenter.processItems = '" + processItems + "'");
		}
		if(incomingRequest.containsKey("InvWorkCenter_bufferDays"))
		{
			String bufferDays = (String) incomingRequest.get("InvWorkCenter_bufferDays");
			queryString.append(" AND invworkcenter.bufferDays = '" + bufferDays + "'");
		}
		if(incomingRequest.containsKey("InvWorkCenter_vendorName"))
		{
			String vendorName = (String) incomingRequest.get("InvWorkCenter_vendorName");
			queryString.append(" AND invworkcenter.vendorName = '" + vendorName + "'");
		}
		if(incomingRequest.containsKey("InvWorkCenter_vendorId"))
		{
			String vendorId = (String) incomingRequest.get("InvWorkCenter_vendorId");
			queryString.append(" AND invworkcenter.vendorId = '" + vendorId + "'");
		}
		if(incomingRequest.containsKey("InvWorkCenter_cost"))
		{
			String cost = (String) incomingRequest.get("InvWorkCenter_cost");
			queryString.append(" AND invworkcenter.cost = '" + cost + "'");
		}
		if(incomingRequest.containsKey("InvWorkCenter_unitOfMeasure"))
		{
			String unitOfMeasure = (String) incomingRequest.get("InvWorkCenter_unitOfMeasure");
			queryString.append(" AND invworkcenter.unitOfMeasure = '" + unitOfMeasure + "'");
		}
		if(incomingRequest.containsKey("InvWorkCenter_leadTime"))
		{
			String leadTime = (String) incomingRequest.get("InvWorkCenter_leadTime");
			queryString.append(" AND invworkcenter.leadTime = '" + leadTime + "'");
		}
		if(incomingRequest.containsKey("InvWorkCenter_dateEntered"))
		{
			String dateEntered = (String) incomingRequest.get("InvWorkCenter_dateEntered");
			queryString.append(" AND invworkcenter.dateEntered = '" + dateEntered + "'");
		}
		if(incomingRequest.containsKey("InvWorkCenter_dateExpires"))
		{
			String dateExpires = (String) incomingRequest.get("InvWorkCenter_dateExpires");
			queryString.append(" AND invworkcenter.dateExpires = '" + dateExpires + "'");
		}
		if(incomingRequest.containsKey("InvWorkCenter_owner"))
		{
			String owner = (String) incomingRequest.get("InvWorkCenter_owner");
			queryString.append(" AND invworkcenter.owner = '" + owner + "'");
		}
		if(incomingRequest.containsKey("InvWorkCenter_status"))
		{
			String status = (String) incomingRequest.get("InvWorkCenter_status");
			queryString.append(" AND invworkcenter.status = '" + status + "'");
		}
		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}