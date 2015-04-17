/*
 * Created on Nov 16, 2005 
 */
package com.tsa.puridiom.rfqquestion.tasks;

import com.tsa.puridiom.entity.RfqQuestion;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;

/**
 * @author Kelli 
 */
public class RfqQuestionDataSet extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

		RfqQuestion rfqQuestion = (RfqQuestion) incomingRequest.get("rfqQuestion") ;

		List responseValueList = (List) incomingRequest.get("responseValueList");
		List vendorResponseList = (List) incomingRequest.get("vendorResponseList");
		
		if (responseValueList != null) {
		    rfqQuestion.setResponseValueList(responseValueList) ;
		}
		if (vendorResponseList != null) {
		    rfqQuestion.setVendorResponseList(vendorResponseList) ;
		}
					
		this.setStatus(Status.SUCCEEDED) ;
        
		return rfqQuestion  ;
	}
}
