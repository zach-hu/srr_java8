package com.tsa.puridiom.alerts.tasks;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

import com.tsagate.properties.DictionaryManager;

import com.tsagate.foundation.utility.Utility;

public class AlertsRead extends Task
{
	//public static String xml_filename;
	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		Map alertsMap = new HashMap ();

		try
		{

			Map incomingRequest = (Map)object;
			String organizationId = (String)incomingRequest.get("organizationId");
			Document doc = null;


			String alertPathBase = DictionaryManager.getInstance("host", organizationId).getProperty("alerts-path", "");

			File alerPathFile = Utility.getOidFile(alertPathBase, organizationId);
			String alertPath = alerPathFile.getPath();

			SAXBuilder builder = new SAXBuilder();


			doc = builder.build(new File(alertPath));

			boolean enabled = false;
	        List rulesList = (List) doc.getRootElement().getChildren();

	   	 Attribute attEnabled = doc.getRootElement().getAttribute("enabled");
         if(attEnabled != null)
         {
             enabled= attEnabled.getBooleanValue();
         }

         if(enabled)
         {
            	List listAlerts = new ArrayList ();
            	List listAlertsName = new ArrayList ();
            	List listAlertDescription = new ArrayList();
            	List listAlertsOffset = new ArrayList ();

                for(int rulesIndex = 0; rulesIndex < rulesList.size(); rulesIndex++ )
                {
                	List dataAlerts = new ArrayList ();
                    	Element root =  (Element)rulesList.get(rulesIndex);
	                	String nameAlert = root.getChildText("name");
	                	String typeAlert = root.getChildText("type");
	        			Element whereAlert = root.getChild("where");
	        			Element argAlert = whereAlert.getChild("arg");
	        			String value = argAlert.getChildText("value");
	        			String offset = argAlert.getChildText("offset");
	        			String type = argAlert.getChildText("type");
	        			String description = root.getChildText("description");
	        			dataAlerts.add(value);
	        			dataAlerts.add(offset);
	        			dataAlerts.add(type);
	        			listAlertDescription.add(description);
	        			listAlertsName.add(nameAlert);
	        			listAlertsOffset.add(offset);
	        			alertsMap.put(nameAlert, dataAlerts);
	        			alertsMap.put("nameAlertDescrip", listAlertDescription);
	        			alertsMap.put("alertsOffset", listAlertsOffset);
                  }
            }
         		ret = alertsMap ;
				this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{

			e.printStackTrace();
		}
		return ret;
	}
}
