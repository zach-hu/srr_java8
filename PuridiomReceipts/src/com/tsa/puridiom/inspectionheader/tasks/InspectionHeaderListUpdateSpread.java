package com.tsa.puridiom.inspectionheader.tasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.InspectionHeader;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;

@SuppressWarnings( "unchecked" )
public class InspectionHeaderListUpdateSpread extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		try
		{
			Map incomingRequest = (Map) object;
			String oid = (String)incomingRequest.get("organizationId");
			String userId = (String)incomingRequest.get("userId");
			DBSession dbsession = (DBSession)incomingRequest.get("dbsession");

			List inspectionHeaderList = (ArrayList) incomingRequest.get("inspectionHeaderList") ;
			
			String cgdNo = (String)incomingRequest.get("InspectionHeader_cgdNo");
			String cgdRevString = (String) incomingRequest.get("InspectionHeader_cgdRev");
			String inspectionType = (String) incomingRequest.get("InspectionHeader_inspectType");
			if (Utility.isEmpty(cgdRevString)){	cgdRevString = "0";	}
			BigDecimal cgdRev = new BigDecimal (cgdRevString);
			
			if(inspectionHeaderList != null && inspectionHeaderList.size() > 0){
	
				for (int i = 0; i < inspectionHeaderList.size(); i++) {
					InspectionHeader inspectionHeader = (InspectionHeader)inspectionHeaderList.get(i);
					
					if(inspectionHeader.getInspectType().equalsIgnoreCase(inspectionType)){
						inspectionHeader.setCgdNo(cgdNo);
						//inspectionHeader.setCgdRev(cgdRev);
						
						Map updateIncomingRequest = new HashMap();
						updateIncomingRequest.put("organizationId", oid);
						updateIncomingRequest.put("userId", userId);
						updateIncomingRequest.put("dbsession", dbsession);
						updateIncomingRequest.put("inspectionHeader", inspectionHeader);
						updateIncomingRequest.put("InspectionHeader_cgdNo", cgdNo);
						//updateIncomingRequest.put("InspectionHeader_cgdRev", cgdRev);
						
						InspectionHeaderUpdate task = new InspectionHeaderUpdate() ;
		            	task.executeTask(updateIncomingRequest) ;
					}
				}
			}
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
		}
		return ret;
	}
}
