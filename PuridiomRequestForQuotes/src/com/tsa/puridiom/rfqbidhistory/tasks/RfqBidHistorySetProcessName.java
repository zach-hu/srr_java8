package com.tsa.puridiom.rfqbidhistory.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;

public class RfqBidHistorySetProcessName extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Object ret = null;
		try
		{
			ret = "rfqbidhistory-add.xml";
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
		}
		return ret;
	}

}