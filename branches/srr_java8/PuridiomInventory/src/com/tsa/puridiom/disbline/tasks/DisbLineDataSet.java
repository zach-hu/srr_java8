/*
 * Created on June 09, 2004 
 */
package com.tsa.puridiom.disbline.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.DisbLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsa.puridiom.entity.RequisitionLine;
/**
 * @author Administrator 
 */
public class DisbLineDataSet extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

		DisbLine dbl = (DisbLine) incomingRequest.get("disbLine") ;

		dbl.setAccountList((List) incomingRequest.get("accountList")) ;
		dbl.setDocCommentList((List) incomingRequest.get("docCommentList")) ;
		dbl.setRequisitionLine((RequisitionLine) incomingRequest.get("requisitionLine")) ;
		dbl.setMsrLine((RequisitionLine) incomingRequest.get("msrLine")) ;
		this.setStatus(Status.SUCCEEDED) ;
		return null  ;
	}
}
