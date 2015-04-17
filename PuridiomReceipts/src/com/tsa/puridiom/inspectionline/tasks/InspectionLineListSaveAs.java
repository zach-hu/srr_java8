package com.tsa.puridiom.inspectionline.tasks;

import java.util.*;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.InspectionLine;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Utility;

public class InspectionLineListSaveAs extends Task{
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
		Object		result = null ;
		this.setStatus(Status.SUCCEEDED) ;

		List inspList = (List) incomingRequest.get("inspectionLineList") ;
        PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
        PuridiomProcess process = processLoader.loadProcess("inspectionline-saveas.xml");

		if (inspList != null) {
			for (int i = 0; i < inspList.size(); i++) {
				InspectionLine  inspLine = (InspectionLine) inspList.get(i) ;
				incomingRequest.put("inspectionLine", inspLine) ;

                process.executeProcess(incomingRequest);

                if (process.getStatus() < Status.SUCCEEDED)
                {
                    throw new Exception("Inspection Line save as process failed.");
                }

                inspList.set(i, incomingRequest.get("inspectionLine"));
            }

            result = inspList;

		}

        return result ;

	}
}
