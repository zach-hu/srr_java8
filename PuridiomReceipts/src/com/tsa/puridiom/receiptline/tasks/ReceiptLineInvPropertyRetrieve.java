/*
 * Created on Aug 19, 2003
 */
package com.tsa.puridiom.receiptline.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.ReceiptLine;
import com.tsa.puridiom.entity.InvBinLocation;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * @author Administrator
 */
public class ReceiptLineInvPropertyRetrieve extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

		String		icRecLine = (String) incomingRequest.get("ReceiptLine_icRecLine") ;
		ReceiptLine receiptLine = (ReceiptLine) incomingRequest.get("receiptLine") ;
//		InvBinLocation invBinLocation =(InvBinLocation)incomingRequest.get("invBinLocation") ;
		List		invPropertyList = null ;

		if (icRecLine != null) {
//			 InvPropertyRetrieveBy retrieveTask = new InvPropertyRetrieveBy() ;
////			 String icRc = invBinLocation.getIcRc().toString() ;
////			 incomingRequest.put("InvProperty_icRc", icRc) ;
//			 incomingRequest.put("InvProperty_icRecLine", icRecLine) ;
//
//			 invPropertyList = (List) retrieveTask.executeTask(incomingRequest) ;

		}


		this.setStatus(Status.SUCCEEDED) ;

		return invPropertyList ;
	}
}
