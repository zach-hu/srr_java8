package com.tsa.puridiom.receiptheader.tasks;

import java.util.Map;

import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

public class InvokeReceiptUpdate extends Task {

	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	public Object executeTask(Object object) throws Exception {


		Map incomingRequest = (Map) object;
		String	organizationId = (String) incomingRequest.get("organizationId");
		String	filename = "receipt-update.xml";
		PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
		
		try {
			incomingRequest.put("dbsession", null);
			
			PuridiomProcess process = processLoader.loadProcess(filename);
			process.executeProcess(incomingRequest);
			
			this.setStatus(Status.SUCCEEDED);
			
		} catch (Exception e) {
			
			this.setStatus(Status.FAILED);
			Log.error(this, e);
			e.printStackTrace();
		}
		
		return null;
	}
	
}
