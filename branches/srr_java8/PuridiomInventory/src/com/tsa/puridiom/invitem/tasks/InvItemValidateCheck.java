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
import com.tsagate.foundation.rule.*;
import com.tsa.puridiom.entity.InvItem ;
import com.tsa.puridiom.common.documents.GeneralStatus;

/**
 * @author Kelli
 */
public class InvItemValidateCheck extends Task
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

				InvItem invItem = (InvItem) incomingRequest.get("invItem") ;
				String status = invItem.getStatus() ;

				ValidationRules rules = (ValidationRules)incomingRequest.get("rules");
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));

				if (rules.getResult() == rules.FAILED) {
					if (! status.equals(GeneralStatus.STATUS_ON_HOLD)) {
						PuridiomProcess process = processLoader.loadProcess("invitem-disable-item.xml");
						process.executeProcess(incomingRequest);
						if (process.getStatus() < Status.SUCCEEDED) this.setStatus(Status.FAILED);
					}
				} else if (status.equals(GeneralStatus.STATUS_ON_HOLD)) {
					PuridiomProcess process = processLoader.loadProcess("invitem-enable-item.xml");
					process.executeProcess(incomingRequest);
					if (process.getStatus() < Status.SUCCEEDED) this.setStatus(Status.FAILED);
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