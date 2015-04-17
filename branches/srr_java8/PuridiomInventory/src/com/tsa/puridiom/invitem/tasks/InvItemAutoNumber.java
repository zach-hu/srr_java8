package com.tsa.puridiom.invitem.tasks;

import java.util.HashMap;
import java.util.Map;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsa.puridiom.entity.InvItem;

/**
 * @author Jeff
 */
public class InvItemAutoNumber extends Task
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
				DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;

				while (true) {
					PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
					PuridiomProcess process = processLoader.loadProcess("invitem-generate-item-number.xml");

					Map updateParameters = new HashMap();
					updateParameters.put("userId", incomingRequest.get("userId"));
					updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
					updateParameters.put("organizationId", incomingRequest.get("organizationId"));
					updateParameters.put("dbsession", incomingRequest.get("dbsession"));

					process.executeProcess(updateParameters);
					if (process.getStatus() < Status.SUCCEEDED)
					{
					    this.setStatus(Status.FAILED);
//						throw new Exception("Adding Inventory Cross Reference Item Failed!");
					}

					String itemNumber = (String)updateParameters.get("InvItem_itemNumber");
					incomingRequest.put("InvItem_itemNumber", itemNumber) ;

					InvItemRetrieveById item = new InvItemRetrieveById();
					InvItem invItem = (InvItem) item.executeTask(incomingRequest) ;
					if (invItem == null) {
						break ;
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