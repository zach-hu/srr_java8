package com.tsa.puridiom.invitem.tasks;

import java.util.Map;
import java.util.ArrayList;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;
import com.tsa.puridiom.common.documents.AutoGenType;

/**
 * @author Kelli
 */
public class InvItemCrossRefSaveSetup extends Task
{
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;

		try
		{
			String	oid = (String) incomingRequest.get("organizationId") ;
			String	itemNumber = (String) incomingRequest.get("InvItem_itemNumber") ;
			String	description = (String) incomingRequest.get("InvItem_description") ;
			String	nsnNumber = (String) incomingRequest.get("InvItem_nsnNumber") ;
			String	altPartNo1 = (String) incomingRequest.get("InvItem_altPartNo1") ;
			String	altPartNo2 = (String) incomingRequest.get("InvItem_altPartNo2") ;
			String	altPartNo3 = (String) incomingRequest.get("InvItem_altPartNo3") ;
			ArrayList itemXrefList = new ArrayList() ;

			if (! Utility.isEmpty(nsnNumber)) {
				itemXrefList.add(nsnNumber) ;
			}
			if (! Utility.isEmpty(altPartNo1)) {
				itemXrefList.add(altPartNo1) ;
			}
			if (! Utility.isEmpty(altPartNo2)) {
				itemXrefList.add(altPartNo2) ;
			}
			if (! Utility.isEmpty(altPartNo3)) {
				itemXrefList.add(altPartNo3) ;
			}

			incomingRequest.put("ItemCrossRef_itemNumber", itemNumber) ;
			incomingRequest.put("ItemCrossRef_description", description) ;
			incomingRequest.put("ItemCrossRefList", itemXrefList) ;
			this.setStatus(Status.SUCCEEDED);
		}
		catch(Exception e)
		{
			this.setStatus(Status.FAILED);
			Log.error(this, e.toString());
		}
		return null ;
	}
}
