/*
 * Created on Aug 19, 2003
 */
package com.tsa.puridiom.doctext.tasks;

import com.tsa.puridiom.entity.DocText;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import com.tsagate.foundation.processengine.Status;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author Administrator
*/
public class DocTextSaveasSetup extends Task {

	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map) object;

		this.setStatus(Status.SUCCEEDED) ;
		UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
		
		DocText docText = (DocText) incomingRequest.get("docText") ;
		if (docText != null) {
			docText.setIcText(new BigDecimal(ukg.getUniqueKey().toString())) ;
			incomingRequest.put("newDocText_icText", docText.getIcText().toString());
		} else {
			this.setStatus(Status.FAILED) ;
		}		

		return docText ;

	}

}
