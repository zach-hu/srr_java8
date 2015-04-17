/**
 * 
 */
package com.tsa.puridiom.xlsdata.tasks;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.xlsdata.XlsUploadType;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.UniqueKeyGenerator;

/**
 * @author Johnny
 */
public class XlsDataUploadFile extends Task
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
			String organizationId = (String) incomingRequest.get("organizationId");
			String userId = (String) incomingRequest.get("userId");
			String uploadType = HiltonUtility.ckNull((String) incomingRequest.get("uploadType"));
			String[] uploadTypes = uploadType.split(";");
			Map xlsFiles = (Map) incomingRequest.get("xlsFiles");
			List xlsDataObjects = new ArrayList();
			String dirName = (String) incomingRequest.get("dirName");
			Map entityReferences = new HashMap();

			for (int i = 0; i < xlsFiles.size(); i++)
			{
				File originalFile = (File) xlsFiles.get(String.valueOf(i + 1));

				if (originalFile != null)
				{
					File xlsFile = this.saveFile(originalFile, dirName);

					if (i < uploadTypes.length)
					{
						uploadType = uploadTypes[i];
					}

					String processFileName = this.getProcessFileNameByType(uploadType);
					String propertySection = this.getPropertySectionByType(uploadType);

					if (!HiltonUtility.isEmpty(processFileName))
					{
						Map newIncomingRequest = new HashMap();
						PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
						PuridiomProcess process = processLoader.loadProcess("xls-data-upload.xml");

						Log.debug(this, "XlsDataUploadFile for \"xlsDataUploadProcess\": " + processFileName + "  and  \"xlsPropertySection\": " + propertySection);

						newIncomingRequest.put("organizationId", organizationId);
						newIncomingRequest.put("userId", userId);
						newIncomingRequest.put("xlsFile", xlsFile);
						newIncomingRequest.put("xlsDataUploadProcess", processFileName);
						newIncomingRequest.put("xlsPropertySection", propertySection);
						newIncomingRequest.put("xlsReferences", entityReferences);

						process.executeProcess(newIncomingRequest);

						entityReferences = (Map) newIncomingRequest.get("entityReferences");

						xlsDataObjects.add(newIncomingRequest.get("xlsData"));

						this.setStatus(process.getStatus());
					} else
					{
						Log.debug(this, "XlsDataUploadFile could not find a process for \"uploadType\": " + processFileName);
						this.setStatus(Status.SUCCEEDED);
					}
				}
			}

			incomingRequest.put("xlsDataObjects", xlsDataObjects);

		} catch (Exception exception)
		{
			this.setStatus(Status.FAILED);

			Log.error(this, "XlsDataUploadFile error " + exception.getMessage());

			throw exception;
		}

		return result;
	}

	private File saveFile(File file, String dirName) throws Exception
	{
		File dir = new File(dirName);
		String filename = file.getName();
		String fileExtension = filename.substring(filename.lastIndexOf("."));
		String fileToName = UniqueKeyGenerator.getInstance().getUniqueKey().toString() + fileExtension;
		File newFile;

		if (!dir.isDirectory())
		{
			Log.error(this, "The [" + dirName + "] is not a valid directory.");
			throw new IOException("The [" + dirName + "] is not a valid directory.");
		}
		if (!dir.canWrite())
		{
			Log.error(this, "The [" + dirName + "] does not have write access.");
			throw new IOException("The application does not have write access for the [" + dirName + "]");
		}

		if (dirName.charAt(dirName.length() - 1) != File.separatorChar)
		{
			dirName = dirName + File.separator;
		}

		if (file.length() == 0)
		{
			throw new TsaException("Files can not be empty");
		}

		newFile = new File(dirName + fileToName);

		file.renameTo(newFile);

		Log.debug(this, "XlsFileName for upload process: " + newFile.getAbsolutePath());

		return newFile;
	}

	private String getProcessFileNameByType(String uploadType)
	{
		String process = "";

		/*
		 * BG = Budget, BD = BudgetDrawer
		 */
		if (uploadType.equalsIgnoreCase(XlsUploadType.BUDGET_CENTER))
		{
			process = "budgetcenter-add-from-xls-data.xml";

		} else if (uploadType.equalsIgnoreCase(XlsUploadType.BUDGET_DRAWER))
		{
			process = "budgetdrawer-add-from-xls-data.xml";
		}

		return process;
	}

	private String getPropertySectionByType(String uploadType)
	{
		String section = "";

		/*
		 * BG = Budget, BD = BudgetDrawer
		 */
		if (uploadType.equalsIgnoreCase(XlsUploadType.BUDGET_CENTER))
		{
			section = "BUDGETCENTERXLSUPLOAD";

		} else if (uploadType.equalsIgnoreCase(XlsUploadType.BUDGET_DRAWER))
		{
			section = "BUDGETDRAWERXLSUPLOAD";
		}

		return section;
	}

}