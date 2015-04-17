/*
 * Created on Dec 9, 2003
 */
package com.tsa.puridiom.billto.tasks;

import com.tsa.puridiom.entity.BillTo ;
import com.tsagate.foundation.processengine.*;
import java.util.List;
import java.util.Map;

/**
 * @author Jeff / Kelli
 */
public class BillToSaveasList extends Task
{
	
	/**
	 * executeTask
	 * <p>Thismethod takes a BillTo List coming from incoming request(billToList)</p>
	 * <p> and calls BillToSaveas for each one of them.</p>
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		List billToList = (List)incomingRequest.get("billToList");
		
		for (int row = 0; row < billToList.size(); row++)
		{
			BillTo billTo = (BillTo)billToList.get(row);

			incomingRequest.put("billTo", billTo);
			
			BillToSaveas saveas = new BillToSaveas();
			saveas.executeTask(incomingRequest);
			
			if (saveas.getStatus() != Status.SUCCEEDED)
			{
				throw new Exception("Ship To Saveas failed.");		
			}
			
			billToList.set(row, billTo);
			this.setStatus(Status.SUCCEEDED);
		}
		if(billToList.size() == 0)
		{
			this.setStatus(Status.SUCCEEDED);
		}
		return billToList;
	}

}
