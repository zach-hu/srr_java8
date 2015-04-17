/*
 * Created on Aug 31, 2005
 */
package com.tsa.puridiom.invitem.tasks;

import com.tsagate.foundation.processengine.*;
import java.util.*;
import com.tsa.puridiom.common.documents.GeneralStatus;

/**
 * @author Kelli
 */
public class InvItemEnableSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{
//			InvItem invItem = (InvItem)incomingRequest.get("invItem") ;
//			List xrefList = (List) incomingRequest.get("itemCrossRefList") ;
//			for (int ix = 0; ix < xrefList.size(); ix++) {
//				ItemCrossRef xrefItem = (ItemCrossRef) xrefList.get(ix) ;
//				incomingRequest.put("InvItem_altPartNo" + Integer.toString(ix + 1), xrefItem.getAltItemNumber()) ;
//			}
//			incomingRequest.put("InvItem_description", invItem.getDescription()) ;
			incomingRequest.put("InvItem_status", GeneralStatus.STATUS_PERMANENT) ;
			incomingRequest.put("ItemCrossRef_status", GeneralStatus.STATUS_PERMANENT) ;

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
