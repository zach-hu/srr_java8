/*
 * Created on Aug 31, 2005
 */
package com.tsa.puridiom.invitem.tasks;

import java.util.*;

import com.tsa.puridiom.entity.InvItem;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility ;
/**
 * @author Kelli 
 */
public class InvItemRetrieveSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map) object;
		
		try {
			InvItem invItem = (InvItem) incomingRequest.get("invItem") ;
			if (invItem == null) {
			    throw new Exception ("The InvItem entity was not found.");
			} else {
				String itemNumber = invItem.getItemNumber() ;
				
				if (Utility.isEmpty(itemNumber)) {
				    throw new Exception ("The itemNumber cannot be empty.");
				} else {
					incomingRequest.put("ItemFormData_itemNumber", itemNumber) ;
				}
			}
			
			this.status = Status.SUCCEEDED;
		} catch (Exception e) {
		    this.status = Status.FAILED;
		    throw e;
		}
		
		return null ;
	}
}
