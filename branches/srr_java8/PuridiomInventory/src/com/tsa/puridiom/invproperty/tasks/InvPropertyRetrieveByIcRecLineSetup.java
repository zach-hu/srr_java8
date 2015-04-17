package com.tsa.puridiom.invproperty.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.ReceiptLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

import java.util.Map;

public class InvPropertyRetrieveByIcRecLineSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Object result = null;

		try
		{
			Map incomingRequest = (Map)object;

			String icRecLine = "";

			Object invPropertyIcRecLine = (Object) incomingRequest.get("InvProperty_icRecLine");
			if (invPropertyIcRecLine instanceof String[]) {
				icRecLine = ((String[])invPropertyIcRecLine)[0];
			} else if (invPropertyIcRecLine instanceof String) {
				icRecLine = (String)invPropertyIcRecLine;
			}

			if (HiltonUtility.isEmpty(icRecLine))
			{
				Object receiptLineIcRecLine = (Object) incomingRequest.get("ReceiptLine_icRecLine");
				if (receiptLineIcRecLine instanceof String[]) {
					icRecLine = ((String[])receiptLineIcRecLine)[0];
				} else if (receiptLineIcRecLine instanceof String) {
					icRecLine = (String)receiptLineIcRecLine;
				}
			}

			if (HiltonUtility.isEmpty(icRecLine))
			{
				ReceiptLine receiptLine = (ReceiptLine) incomingRequest.get("receiptLine");
				if (receiptLine != null) {
					icRecLine = receiptLine.getIcRecLine().toString();
				}
			}

			incomingRequest.put("InvProperty_icRecLine", icRecLine);

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return result;
	}
}
