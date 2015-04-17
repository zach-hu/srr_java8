/*
 * Created on Nov 8, 2006 
 */
package com.tsa.puridiom.stdquestion.tasks;

import com.tsa.puridiom.entity.StdQuestion;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;

/**
 * @author Kelli 
 */
public class StdQuestionDataSet extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

		StdQuestion stdQuestion = (StdQuestion) incomingRequest.get("stdQuestion") ;

		List responseValueList = (List) incomingRequest.get("responseValueList");
		
		if (responseValueList != null) {
		    stdQuestion.setResponseValueList(responseValueList) ;
		}
					
		this.setStatus(Status.SUCCEEDED) ;
        
		return stdQuestion  ;
	}
}
