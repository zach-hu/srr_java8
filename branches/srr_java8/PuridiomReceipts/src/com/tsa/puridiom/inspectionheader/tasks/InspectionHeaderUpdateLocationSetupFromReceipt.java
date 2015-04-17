/**
 * Created on Jan 6, 2004
 * @author renzo
 * project: hilton
 * com.tsa.puridiom.tasks.poPOCreateSetup.java
 */

package com.tsa.puridiom.inspectionheader.tasks;

import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.ReceiptLine;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import com.tsagate.foundation.utility.Utility;

public class InspectionHeaderUpdateLocationSetupFromReceipt extends Task
{
	/**
	 * Method executeTask.
	 * @author EDSAC
	 * inserts values generated only by the system
	 * @param object <p>incomingRequest</p>
	 */
	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		try
		{
			Map incomingRequest = (Map) object;

			ReceiptLine recLine = (ReceiptLine) incomingRequest.get("receiptLine") ;

			incomingRequest.put("InspectionHeader_icRecLine", recLine.getIcRecLine().toString()) ;
			incomingRequest.put("InspectionHeader_location", recLine.getInspLocation()) ;
			incomingRequest.put("InspectionHeader_area", recLine.getInspArea()) ;
			incomingRequest.put("InspectionHeader_storage", recLine.getInspStorage()) ;

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
		}
		return ret;
	}
}
