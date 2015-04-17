package com.tsa.puridiom.inspectionheader.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.InspectionHeader;
import com.tsa.puridiom.entity.InspectionStd;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

@SuppressWarnings(value = { "unchecked" })
public class InspectionHeaderDataSet extends Task {

	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

		InspectionHeader inspectionHeader = (InspectionHeader) incomingRequest.get("inspectionHeader") ;

		inspectionHeader.setInspectionLineList((List) incomingRequest.get("inspectionLineList")) ;
		inspectionHeader.setInspectionMfrList((List) incomingRequest.get("inspectionMfrList")) ;
		inspectionHeader.setInspectionDiscrepList((List) incomingRequest.get("inspectionDiscrepList")) ;
		inspectionHeader.setInspectionDisposList((List) incomingRequest.get("inspectionDisposList")) ;
		List inspectionStdList = (List)incomingRequest.get("inspectionStdList");
		if(inspectionStdList != null && !inspectionStdList.isEmpty()){
			inspectionHeader.setInspectionStd((InspectionStd)inspectionStdList.get(0));
		}
		inspectionHeader.setInspectionHistoryList((List)incomingRequest.get("inspectionHistoryList"));

		this.setStatus(Status.SUCCEEDED) ;

		return null  ;
	}
}
