package com.tsa.puridiom.invitem.tasks;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Iterator;

import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;
import com.tsa.puridiom.common.documents.AutoGenType;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.common.documents.GeneralStatus;
/**
 * @author Kelli
 */
public class InvItemCrossRefAdd extends Task
{
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
	       Object ret = null;
	        try
	        {
		        Map incomingRequest = (Map)object;
				Object result = null;
				List xrefList = (List) incomingRequest.get("ItemCrossRefList") ;

				Iterator it = xrefList.iterator() ;
				while (it.hasNext()) {
					String altNo = (String)it.next() ;

					PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
					PuridiomProcess process = processLoader.loadProcess("itemcrossref-add.xml");

					Map updateParameters = new HashMap();
					updateParameters.put("userId", incomingRequest.get("userId"));
					updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
					updateParameters.put("organizationId", incomingRequest.get("organizationId"));
					updateParameters.put("dbsession", incomingRequest.get("dbsession"));
					updateParameters.put("ItemCrossRef_altItemNumber", altNo);
					updateParameters.put("ItemCrossRef_description", (String) incomingRequest.get("ItemCrossRef_description"));
					updateParameters.put("ItemCrossRef_itemNumber", (String) incomingRequest.get("ItemCrossRef_itemNumber"));
					updateParameters.put("ItemCrossRef_source", "INV");
					updateParameters.put("ItemCrossRef_dateEntered", Dates.today("", ""));
					updateParameters.put("ItemCrossRef_dateExpires", Dates.today("", ""));
					if(incomingRequest.get("InvItem_status") == null) {
						updateParameters.put("ItemCrossRef_status", GeneralStatus.STATUS_PERMANENT);
					} else {
						updateParameters.put("ItemCrossRef_status", incomingRequest.get("InvItem_status")) ;
					}
					updateParameters.put("ItemCrossRef_owner", (String) incomingRequest.get("userId"));

					process.executeProcess(updateParameters);
					if (process.getStatus() < Status.SUCCEEDED)
					{
					    this.setStatus(Status.FAILED);
//						throw new Exception("Adding Inventory Cross Reference Item Failed!");
					}

				}


				this.status = Status.SUCCEEDED;
	        }
	        catch (Exception e)
	        {
	            this.setStatus(Status.FAILED);
	            throw new TsaException(this.getName(), e);
	        }
	        return ret;
    }
}