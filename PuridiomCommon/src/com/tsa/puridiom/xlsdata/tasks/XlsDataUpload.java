/**
 * 
 */
package com.tsa.puridiom.xlsdata.tasks;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.XlsData;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * @author Johnny
 */
public class XlsDataUpload extends Task
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
			String userId = (String) incomingRequest.get("userId");
			XlsData xlsData = (XlsData) incomingRequest.get("xlsData");
			String section = (String) incomingRequest.get("xlsPropertySection");
			String uploadProcess = (String) incomingRequest.get("xlsDataUploadProcess");
			Map xlsReferences = (Map) incomingRequest.get("xlsReferences");
			String entityReference = PropertiesManager.getInstance(organizationId).getProperty(section, "ENTITY", "");
			String entity = (String) incomingRequest.get("xlsRefEntity");
			String field = (String) incomingRequest.get("xlsRefField");
			String fieldKey = (String) incomingRequest.get("xlsRefFieldKey");

			Map entityReferences = new HashMap();
			Map newIncomingRequest;
			boolean idReference = false;
			boolean reference = false;

			if (!HiltonUtility.isEmpty(entityReference))
			{
				idReference = true;
			}

			if (!HiltonUtility.isEmpty(entity))
			{
				reference = true;
			}

			for (int i = 0; i < xlsData.getContent().size(); i++)
			{
				try
				{
					List xlsRowData = (List) xlsData.getContent().get(i);
					newIncomingRequest = new HashMap();
					Object idEntity = null;

					for (int j = 0; j < xlsData.getColumns().size(); j++)
					{
						String data = (String) xlsRowData.get(j);

						if (idReference && (j == 0))
						{
							idEntity = data;

						} else if (reference && (j == 0))
						{
							this.setReferenceValue(newIncomingRequest, xlsReferences, data, entity, field, fieldKey);

						} else
						{
							newIncomingRequest.put(xlsData.getColumns().get(j), data);
						}
					}

					this.executeProcess(uploadProcess, newIncomingRequest, organizationId, userId);

					if (idReference && (newIncomingRequest.get(entityReference) != null))
					{
						entityReferences.put(idEntity.toString(), newIncomingRequest.get(entityReference));
					}

					Log.debug(this, "XlsDataUpload executeProcess for row = " + i + " , executed successfully");

				} catch (Exception exception)
				{
					Log.error(this, "XlsDataUpload error for row = " + i + " , " + exception.getMessage());
					exception.printStackTrace();
				}
			}

			incomingRequest.put("entityReferences", entityReferences);

			this.setStatus(Status.SUCCEEDED);

		} catch (Exception exception)
		{
			this.setStatus(Status.FAILED);

			Log.error(this, "XlsDataUpload error " + exception.getMessage());

			throw exception;
		}

		return result;
	}

	private void setReferenceValue(Map incomingRequest, Map xlsReferences, String id, String entity, String field, String fieldKey) throws Exception
	{
		Object referenceId = "";
		Object xlsReference = xlsReferences.get(id);

		if (xlsReference.getClass().getName().indexOf(entity) > 0)
		{
			Method method = xlsReference.getClass().getMethod("get" + field, new Class[] {});
			referenceId = method.invoke(xlsReference, new Object[0]);
		}

		incomingRequest.put(fieldKey, referenceId.toString());
	}

	private void executeProcess(String processFileName, Map incomingRequest, String organizationId, String userId) throws Exception
	{
		PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
		PuridiomProcess process = processLoader.loadProcess(processFileName);

		incomingRequest.put("organizationId", organizationId);
		incomingRequest.put("userId", userId);

		process.executeProcess(incomingRequest);
	}
}