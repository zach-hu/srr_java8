/*
 * Created on Nov 13, 2003
 */
package com.tsa.puridiom.rfqline.tasks;

import com.tsa.puridiom.entity.RfqLine;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;
import java.util.List;
/**
 * @author Kelli
 */
public class RfqLineDeleteByHeader extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {
	    Map incomingRequest = (Map) object;
		
		try {
		    List	rfqLineList = (List)incomingRequest.get("rfqLineList");
		
			if (rfqLineList != null) {
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
				PuridiomProcess process = processLoader.loadProcess("rfqline-delete.xml");
					
				for (int i=0; i < rfqLineList.size(); i++) {
					RfqLine rfqLine = (RfqLine) rfqLineList.get(i);
		
					incomingRequest.put("rfqLine", rfqLine);
					incomingRequest.put("RfqLine_icRfqHeader", String.valueOf(rfqLine.getIcRfqHeader()));
					incomingRequest.put("RfqLine_icRfqLine", String.valueOf(rfqLine.getIcRfqLine()));
					incomingRequest.put("deleteAction", "BY-HEADER");
					
					process.executeProcess(incomingRequest);
					
					if (process.getStatus() < Status.SUCCEEDED) {
						throw new Exception("RfqLine delete process failed.");
					}
				}
			}
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		return null;
	}
}
