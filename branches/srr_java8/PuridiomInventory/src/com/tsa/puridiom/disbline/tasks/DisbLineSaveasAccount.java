/*
 * Created on Nov 18, 2003
 */
package com.tsa.puridiom.disbline.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.DisbLine;
import com.tsa.puridiom.account.tasks.AccountSaveasList;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * @author renzo
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class DisbLineSaveasAccount extends Task
{
	/*
	 * Loops through the disb lines that were just added and then adds the accounts from the requisitionLine
	 * <p>to the the disbursement line.
	 * @see com.tsagate.common.processengine.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		List disbLines = (List)incomingRequest.get("disbLines");
		for(int i =0; i < disbLines.size(); i++)
		{
			DisbLine disbLine = (DisbLine)disbLines.get(i);
			incomingRequest.put("newAccount_icHeader", disbLine.getIcDsbHeader().toString());
			incomingRequest.put("newAccount_icLine", disbLine.getIcDsbLine().toString());
			incomingRequest.put("newAccount_accountType", "DBL");

			AccountSaveasList saveas = new AccountSaveasList();
			saveas.executeTask(incomingRequest);
			this.setStatus(saveas.getStatus());
			if(saveas.getStatus() != Status.SUCCEEDED)
			{
				i = disbLines.size();
			}
		}
		if(disbLines.size() == 0)
		{
			this.setStatus(Status.SUCCEEDED);
		}

		return null;
	}

}
