package com.tsa.puridiom.browse.tasks;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class SetRetrieveAllRegisters  extends Task {
	/* (non-Javadoc)
	 * @see com.tsa.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {
		this.status = Status.SUCCEEDED;
		return "Y";
	}

}
