package com.tsa.puridiom.requisitionline.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

@SuppressWarnings(value = { "unchecked" })
public class MsrLineUpdateList extends Task {
	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try {
			List	msrLineList = (List) incomingRequest.get("msrLineList");
			
				
			for (int i=0; i < msrLineList.size(); i++) {
				RequisitionLine msrLine = (RequisitionLine) msrLineList.get(i);
				
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
				PuridiomProcess process = processLoader.loadProcess("requisitionline-update-norecalc.xml");

				incomingRequest.put("requisitionLine", msrLine);
				
				process.executeProcess(incomingRequest);
				
				if (process.getStatus() < Status.SUCCEEDED) {
					throw new Exception("Requisition Line save as process failed.");
				}
			}

			result = msrLineList;			
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}