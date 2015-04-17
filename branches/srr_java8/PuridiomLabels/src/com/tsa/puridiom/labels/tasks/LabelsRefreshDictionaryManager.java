/*
 * Created on May 29, 2009
 */
package com.tsa.puridiom.labels.tasks;

import com.tsagate.properties.*;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class LabelsRefreshDictionaryManager extends Task {

	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {
		try {
            DictionaryManager.getInstance(true);
            DictionaryManager.refresh();

            this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		return null;
	}
}
