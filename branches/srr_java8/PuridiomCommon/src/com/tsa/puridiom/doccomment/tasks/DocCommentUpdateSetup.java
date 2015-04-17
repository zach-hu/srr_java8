/*
 * Created on Aug 19, 2003 
 */
package com.tsa.puridiom.doccomment.tasks;

import java.math.BigDecimal;
import java.util.*;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import com.tsagate.foundation.utility.Utility;

/**
 * @author Administrator 
 */
public class DocCommentUpdateSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

		// Create new ic codes
		String		icText = (String) incomingRequest.get("DocComment_icText") ;
		if (Utility.isEmpty(icText)) {
			icText = "0" ;
		}
		BigDecimal bdText = new BigDecimal(icText) ;
		if (bdText.equals(new BigDecimal(0))) {
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			icText = ukg.getUniqueKey().toString() ;
		}
		incomingRequest.put("DocComment_icText", icText);
		incomingRequest.put("DocText_icText", icText) ;
        
        this.setStatus(Status.SUCCEEDED) ;

				
		return null  ;
	}
	
}
