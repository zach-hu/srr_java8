/**
 * 
 */
package com.tsa.puridiom.xlsdata.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.XlsData;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * @author Johnny
 */
public class XlsDataSetValues extends Task
{
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{
			XlsData xlsData = (XlsData) incomingRequest.get("xlsData");

			if (xlsData == null)
			{
				xlsData = new XlsData();
			}

			if (incomingRequest.containsKey("XlsData_labels"))
			{
				List labels = (List) incomingRequest.get("XlsData_labels");
				xlsData.setLabels(labels);
			}

			if (incomingRequest.containsKey("XlsData_columns"))
			{
				List columns = (List) incomingRequest.get("XlsData_columns");
				xlsData.setColumns(columns);
			}

			if (incomingRequest.containsKey("XlsData_types"))
			{
				List types = (List) incomingRequest.get("XlsData_types");
				xlsData.setTypes(types);
			}

			if (incomingRequest.containsKey("XlsData_content"))
			{
				List content = (List) incomingRequest.get("XlsData_content");
				xlsData.setContent(content);
			}

			result = xlsData;

			this.setStatus(Status.SUCCEEDED);

		} catch (Exception exception)
		{
			this.setStatus(Status.FAILED);

			Log.error(this, "XlsDataSetValues error " + exception.getMessage());

			throw exception;
		}

		return result;
	}

}