/**
 *
 */
package com.tsa.puridiom.cxml.tasks;

import java.util.Map;

import com.tsa.puridiom.cxml.entity.CXML;
import com.tsa.puridiom.entity.SendQueue;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * @author Johnny Zapana
 *
 */
public class CXMLDocumentBuilder extends Task {
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map) object;

		try {
			CXML.buildCXMLDocument((CXML) incomingRequest.get("cXMLObject"),
					(String) incomingRequest.get("organizationId"),
					String.valueOf(incomingRequest.get("PoHeader_icPoHeader")));

			this.setStatus(Status.SUCCEEDED);
		} catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}

		return null;
	}

}
