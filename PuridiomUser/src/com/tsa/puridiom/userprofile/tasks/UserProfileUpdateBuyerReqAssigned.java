package com.tsa.puridiom.userprofile.tasks;

import java.sql.Types;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class UserProfileUpdateBuyerReqAssigned extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");

			String buyer = "Y";
			String status = "03";
			String requisitionNumber = "N/A";
			String property = "REQASSIGNEDCNT";

			String sqlString = "UPDATE User_Preference SET VALUE = (select count(RequisitionHeader.ic_Req_Header) " +
				"from User_Profile as UserProfile, Requisition_Header as RequisitionHeader " +
				"where UserProfile.buyer = ? and UserProfile.status <> ? and RequisitionHeader.requisition_Number <> ? " +
				"and (RequisitionHeader.requisition_Type = 'H' or RequisitionHeader.requisition_Type = 'P' or RequisitionHeader.requisition_Type = 'R' or RequisitionHeader.requisition_Type = 'C' or RequisitionHeader.requisition_Type = 'A' or RequisitionHeader.requisition_Type = 'E')" +
				"and RequisitionHeader.status >= ? and RequisitionHeader.status <= ? " +
				"and (RequisitionHeader.assigned_Buyer = UserProfile.user_Id or RequisitionHeader.ic_Req_Header in (select RequisitionLine.ic_Req_Header from Requisition_Line as RequisitionLine " +
				"where RequisitionLine.assigned_Buyer = UserProfile.user_Id)) " +
				"and User_Preference.user_Id = UserProfile.user_Id " +
				"group by UserProfile.user_Id) " +
				"WHERE User_Preference.property = ?";
			
			
			Object [] args = {buyer, status, requisitionNumber, DocumentStatus.REQ_APPROVED, DocumentStatus.PO_INPROGRESS, property};
			Integer [] types = {Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR};
			
			dbs.sqlUpdate(sqlString, args, types);

			this.setStatus(dbs.getStatus());
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}
}
