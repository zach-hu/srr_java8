/**
 * 
 */
package com.tsa.puridiom.xlsdata.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * @author Johnny
 */
public class XlsDataSetup extends Task
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
			String propertyPrefix = "COL";
			PropertiesManager propertiesManager = PropertiesManager.getInstance(organizationId);
			List labels = new ArrayList();
			List columns = new ArrayList();
			List types = new ArrayList();
			boolean validColumn = true;
			int i = 1;

			while (validColumn)
			{
				String column = propertiesManager.getProperty(section, propertyPrefix + String.valueOf(i++), "");

				if (HiltonUtility.isEmpty(column))
				{
					validColumn = false;
					continue;
				} else
				{
					String[] parameters = column.split(",");

					for (int j = 0; j < parameters.length; j++)
					{
						String parameter = parameters[j].trim();
						switch (j)
						{
							case 0:
								labels.add(parameter);
								break;
							case 1:
								columns.add(parameter);
								break;
							case 2:
								types.add(parameter);
								break;
						}
					}
				}
			}
			
			incomingRequest.put("XlsData_labels", labels);
			incomingRequest.put("XlsData_columns", columns);
			incomingRequest.put("XlsData_types", types);

			this.setStatus(Status.SUCCEEDED);

		} catch (Exception exception)
		{
			this.setStatus(Status.FAILED);

			Log.error(this, "XlsDataSetup error " + exception.getMessage());

			throw exception;
		}

		return result;
	}

}