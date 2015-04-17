package com.tsa.puridiom.poheader.tasks;
import java.text.SimpleDateFormat;
import java.util.Map;

import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.poheader.history.tasks.PoHeaderHistory;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.TsaException;

public class PoHeaderUpdate extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
			if (poHeader == null)
			{
				throw new Exception ("PoHeader was not found.");
			}

			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;

			incomingRequest.put("Vendor_vendorId", poHeader.getVendorId());
			//incomingRequest.put("Vendor_lastActive", new SimpleDateFormat("MM-dd-yyyy").format(poHeader.getAppDate()));
			incomingRequest.put("Vendor_lastActive", Dates.today("MM-dd-yyyy", ""));



			/*PoHeaderHistory history = new PoHeaderHistory();
			incomingRequest.put("newHistoryPoHeader", poHeader);

			history.executeTask(incomingRequest);
			if(history.getStatus() == Status.SUCCEEDED)
			{*/
			    dbs.update(poHeader);
			/*}
			else
			{
			    this.setStatus(history.getStatus());
			    throw new TsaException(this.getName() + ", An error ocurred updating history! ");
			}*/

			result = poHeader;
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}