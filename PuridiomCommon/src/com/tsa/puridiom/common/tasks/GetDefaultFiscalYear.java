/*
 * Created on Oct 14, 2004
 */
package com.tsa.puridiom.common.tasks;

import com.tsagate.foundation.processengine.*;

/**
 * @author Kelli
 */
public class GetDefaultFiscalYear extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {
		return "1994";
	}
}
