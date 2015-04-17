package com.tsa.puridiom.apprulesext.tasks;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.properties.DictionaryManager;

public class AppRulesExtRetrieveXmlFileOptions extends Task {
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
            List availableRules = new ArrayList();
            try
            {
                String organizationId = (String) incomingRequest.get("organizationId");
                String rulesPath = DictionaryManager.getInstance("host", organizationId).getProperty("app-rules-ext-xml-path", "");
                File defaultDirectory = new File(rulesPath);
                File orgDirectory = null;
                File fileList[] = null;

                if (organizationId != null)
                {
                    orgDirectory = new File(rulesPath + File.separator + organizationId.toString().toLowerCase());
                }
                if(orgDirectory != null && orgDirectory.exists())
                {
                    fileList = orgDirectory.listFiles();
                    if (fileList != null) {
                        for (int i = 0; i < fileList.length; i++) {
                            File file = fileList[i];
                            String fileName = file.getName();
                            if (fileName.endsWith(".xml")) {
                                availableRules.add(fileName);
                            }
                        }
                    }
                }
                if(defaultDirectory != null && defaultDirectory.exists())
                {
                    fileList = defaultDirectory.listFiles();
                    if (fileList != null) {
                        for (int i = 0; i < fileList.length; i++) {
                            File file = fileList[i];
                            String fileName = file.getName();
                            if (!availableRules.contains(fileName) && fileName.endsWith(".xml")) {
                                availableRules.add(fileName);
                            }
                        }
                    }
                }
            }
            catch(Exception exception)
            {
                Log.error(this, exception.toString());
            }

            result = availableRules;

			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}