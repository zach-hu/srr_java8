package com.tsa.puridiom.po.tasks;

import java.util.Map;

import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class QueueAddOrderReleaseRequest extends Task {

	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map) object;
		Object result = null;

		try {
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
            PuridiomProcess process = processLoader.loadProcess("email-awarded-po.xml");

            process.executeProcess(incomingRequest);
            this.status = process.getStatus();
		} catch (Exception e) {
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}
