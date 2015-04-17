/**
 *
 */
package com.tsa.puridiom.common.tasks;

import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * @author Johnny Zapana
 */
public class StatusListRetrieve extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;
		String organizationId = (String) incomingRequest.get("organizationId");
		String columnValue = (String) incomingRequest.get("columnValue");
		String startCode = "0";
		String lastCode = "100000";
		String type = (String) incomingRequest.get("type");

		try
		{
			if (columnValue.indexOf("Disb") == 0)
			{
				startCode = "5000";
				lastCode = "8030";
			} else if (columnValue.indexOf("PoHeader_pyStatus") == 0)
			{
				startCode = "6000";
				lastCode = "6060";
			} else if (columnValue.indexOf("Po") == 0)
			{
				startCode = "3000";
				lastCode = "4010";
				if(type.equals("CT-STATUS")){
					startCode = "2900";
					lastCode= "2990";
				}
			} else if (columnValue.indexOf("Rfq") == 0)
			{
				startCode = "2000";
				lastCode = "3045";
			} else if (columnValue.indexOf("Req") == 0)
			{
				startCode = "0500";
				lastCode = "4010";
			} else if (columnValue.indexOf("Inv") == 0)
			{
				startCode = "6000";
				lastCode = "6085";
			} else if (columnValue.indexOf("Vendor_status") == 0){
				startCode = "01";
				lastCode = "09";
			} else if (columnValue.indexOf("UserLog_status") == 0){
				startCode = "11";
				lastCode = "16";
			} else if (columnValue.indexOf("ReceiptHeader_receiptStatus") == 0){
				startCode = "4000";
				lastCode = "4190";
			}

			Map specificStatus = DocumentStatus.getPropertyMap(organizationId, startCode, lastCode);
			Map extraStatus = DocumentStatus.getPropertyMap(organizationId, "9000", "9020");

			if(!(columnValue.indexOf("UserLog_status") == 0 || columnValue.indexOf("Vendor_status") == 0))
				specificStatus.putAll(extraStatus);

			result = specificStatus;

			this.setStatus(Status.SUCCEEDED);
		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}

		return result;
	}

}