/*
 * Created on Aug 31, 2005
 */
package com.tsa.puridiom.invitem.tasks;

import com.tsa.puridiom.entity.InvItem;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility ;
import java.util.*;

/**
 * @author Kelli
 */
public class InvItemSaveasSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map) object;
		Object result = null;
		
		try
		{
			InvItem invItem = (InvItem) incomingRequest.get("invItem") ;
			if (invItem == null) {
				throw new Exception("The InvItem entity was not found.");
			}
			String	itemNumber = (String) incomingRequest.get("InvItem_itemNumber") ;
			String	newItemNumber = invItem.getItemNumber() ;
			
			if (Utility.isEmpty(itemNumber) || Utility.isEmpty(newItemNumber)) {
				throw new Exception("The value for InvItem_itemNumber and itemNumber on the InvItem entity (the new item number) cannot be empty.");
			}

			incomingRequest.put("InvFormData_itemNumber", itemNumber) ;
			incomingRequest.put("InvFormPart_itemNumber", itemNumber) ;
			incomingRequest.put("newInvFormData_itemNumber", newItemNumber) ;
			incomingRequest.put("newInvFormPart_itemNumber", newItemNumber) ;
			
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		
		return null ;
	}
}
