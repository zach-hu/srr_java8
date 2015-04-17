package com.tsa.puridiom.inspection.tasks;

import java.util.Calendar;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.InspectionHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * @author Jael
 */
@SuppressWarnings(value = { "unchecked" })
public class InspectionFormatDocNumber extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map) object;
		Object result = null;
		try {
			String	inspectionNumber = (String) incomingRequest.get("InspectionHeader_inspectionNumber");
			InspectionHeader inspectionHeader = (InspectionHeader) incomingRequest.get("inspectionHeader");
			
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(inspectionHeader.getDateEntered());
			int year = calendar.get(Calendar.YEAR);
			
			String inspectType = inspectionHeader.getInspectType();
			
			if(inspectType.equals("GI")){
				if(HiltonUtility.isEmpty(inspectionNumber)){
					result = inspectionHeader.getInspectionNumber();
				} else {
					result = year + "-" + inspectType + "-16-" + inspectionNumber;
				}
			} else if(inspectType.equals("RI")){
				result = year + "-RIR-16-" + inspectionHeader.getPoNumber();  
			} else {
				result = year + "-" + inspectType + "-16-" + inspectionHeader.getPoNumber();
			}
			
			
			this.setStatus(Status.SUCCEEDED);
		}
		catch(Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		
		return result;
	}
}
