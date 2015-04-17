package com.tsa.puridiom.inspectionheader.tasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.InspectionHeader;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

@SuppressWarnings(value = { "unchecked" })
public class InspectionHeaderDataRetrieve extends Task {
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

        DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
        this.setStatus(Status.SUCCEEDED) ;
        
		PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
		PuridiomProcess process = processLoader.loadProcess("inspectionheaderdata-retrieve.xml");
		        
		List inspectionHeaderList = (List) incomingRequest.get("inspectionList");
		List inspectionHeaderDataList = new ArrayList();
        for (Iterator it = inspectionHeaderList.iterator(); it.hasNext(); ) {
        		Map newIncomingRequest = new HashMap();
        		newIncomingRequest.put("inspectionHeader", (InspectionHeader) it.next());
				process.executeProcess(newIncomingRequest);
				inspectionHeaderDataList.add(newIncomingRequest.get("inspectionHeader"));
				this.setStatus(process.getStatus()) ;
				if (process.getStatus() == Status.FAILED) {
					break ;
				}
				
        }
					
		return inspectionHeaderDataList  ;
	}
	
}
