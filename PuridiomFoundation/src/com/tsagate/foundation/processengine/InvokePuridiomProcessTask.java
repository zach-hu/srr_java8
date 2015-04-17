/*
 * Created on Nov 5, 2003
 */
package com.tsagate.foundation.processengine;

import java.util.*;

import com.tsagate.foundation.utility.Log;

/**
 * @author Kelli
 */
public class InvokePuridiomProcessTask extends Task {
	
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {
		try {
			if (object instanceof Map) {
				Map incomingRequest = (Map) object;
				String	organizationId = (String) incomingRequest.get("organizationId");
				String	filename = this.getProcessFilename();
				if (filename.indexOf(".xml") < 0) {
					filename = filename + ".xml";
				}
				PuridiomProcess process = this.getProcess(filename, organizationId);
				if (this.isSynchronous()) {			
					process.executeProcess((Map)object);
					this.setStatus(process.getStatus());
					Log.debug(this, "Process " + filename + "done with status: " + this.getStatus());
				}
				else {
					process.executeProcess((Map)object);
				}
			}
			else {
				throw new Exception("Incorrect Argument - requires Map.");
			}
		}
		catch (Exception e) {
			throw e;
		}
		return object;
	}
	
	protected PuridiomProcess getProcess(String filename, String organizationId) throws Exception{
		PuridiomProcess process = null;
		try {
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
			process = processLoader.loadProcess(filename);
		}
		catch (Exception e) {
			throw e;
		}
		
		return process;
		}
}

