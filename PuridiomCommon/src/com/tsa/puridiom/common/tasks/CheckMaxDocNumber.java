/*
 * Created on Nov 21, 2003
 */
package com.tsa.puridiom.common.tasks;

import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;
import java.util.*;

/**
 * @author Kelli
 */
public class CheckMaxDocNumber extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;

		try {
			String	documentType = (String)incomingRequest.get("AutoGen_documentType");
			String	docNum = (String)incomingRequest.get("documentNumber");
			String	format = PropertiesManager.getInstance((String) incomingRequest.get("organizationId")).getProperty("AUTONUMBER", documentType + "Format", "NNNNNN");
			String	max = "";
			char	formatCh[] = { 'N','N','N','N','N','N','N','N','N','N','N','N','N','N','N','N','N','N','N','N' };
			int		len = format.length();
			long	lmax = 0;
			long	lnumber = 0;

			format = format.replace('#', 'N');

			format.getChars(0, len, formatCh, 0);

			for (int cnt = 0; cnt < len; cnt++) {
    			if (formatCh[cnt] == 'N') {
					max = max + "9";
				}
			}

			if (!Utility.isEmpty(max)) {
				lmax = Long.valueOf(max).longValue();
			}

			lnumber = Long.valueOf(docNum).longValue();

			if (lnumber >= lmax)
			{
				throw new Exception("The maximum number for document type " + documentType + " has been reached!");
			}
			result = docNum;
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}

		return result ;
	}
}
