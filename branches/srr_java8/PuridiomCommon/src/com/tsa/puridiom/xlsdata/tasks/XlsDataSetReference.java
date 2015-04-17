/**
 * 
 */
package com.tsa.puridiom.xlsdata.tasks;

import java.util.Map;

import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * @author Johnny
 */
public class XlsDataSetReference extends Task
{

	/*
	 * (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{
			String organizationId = (String) incomingRequest.get("organizationId");
			String section = (String) incomingRequest.get("xlsPropertySection");
			String referenceField = PropertiesManager.getInstance(organizationId).getProperty(section, "REFERENCEFIELD", "");
			String[] fields = referenceField.split(",");
			String entity = "";
			String field = "";
			String fieldKey = "";

			for (int i = 0; i < fields.length; i++)
			{
				String refField = fields[i].trim();

				switch (i)
				{
					case 0:
						entity = refField;
						break;
					case 1:
						field = refField;
						break;
					case 2:
						fieldKey = refField;
						break;
				}
			}

			incomingRequest.put("xlsRefEntity", entity);
			incomingRequest.put("xlsRefField", field);
			incomingRequest.put("xlsRefFieldKey", fieldKey);

			this.setStatus(Status.SUCCEEDED);

		} catch (Exception exception)
		{
			this.setStatus(Status.FAILED);

			Log.error(this, "XlsDataSetReference error " + exception.getMessage());

			throw exception;
		}

		return result;
	}
}
