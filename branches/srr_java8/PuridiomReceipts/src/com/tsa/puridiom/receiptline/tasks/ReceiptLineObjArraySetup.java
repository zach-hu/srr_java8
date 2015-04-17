package com.tsa.puridiom.receiptline.tasks;

import com.tsa.puridiom.entity.ReceiptLine;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Log;

import java.util.List;
import java.util.Map;

public class ReceiptLineObjArraySetup extends Task
{
	@SuppressWarnings("unchecked")
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;


		String[] oarray ;
		try {
			List rcl = (List) incomingRequest.get("receiptLineList") ;


			if (rcl != null) {
				oarray= new String[rcl.size()] ;
				for (int i = 0; i < rcl.size(); i++) {
					ReceiptLine receiptLine = (ReceiptLine) rcl.get(i) ;
					oarray[i] = receiptLine.getIcRecLine().toString();
				}
				if (oarray.length > 1) {
					result = oarray ;
				} else {
					result = oarray[0] ;
				}
			}
			
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e) 
		{
			this.setStatus(Status.FAILED);
			Log.error(this, e);
			e.printStackTrace() ;
		}

		return result;
	}
}
