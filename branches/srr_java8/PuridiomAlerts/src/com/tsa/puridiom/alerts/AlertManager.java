package com.tsa.puridiom.alerts;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsagate.properties.DictionaryManager;


public class AlertManager
{
	private static AlertManager INSTANCE = new AlertManager();
	private Map alertsMap = new HashMap();

	public static AlertManager getInstance()
    {
        if (INSTANCE == null)
        {
            INSTANCE = new AlertManager();
        }
        return INSTANCE;
    }

	public List getAlertList(String organizationId)
	{
		List alerts = null;
		if(alertsMap.containsKey(organizationId))
		{
			alerts = (List)alertsMap.get(organizationId);
		}
		else
		{
			String fileName = DictionaryManager.getInstance("host", organizationId).getProperty("alerts-path");

			ReadAlerts readAlerts = new ReadAlerts(fileName, organizationId);
			alertsMap.put(organizationId, readAlerts.getAlertList());
			alerts = readAlerts.getAlertList();
		}
		return alerts;
	}

	public Alert getAlert(String organizationId, String alertName)
	{
		List alertsList = this.getAlertList(organizationId);
		for (Iterator iter = alertsList.iterator(); iter.hasNext();)
		{
			Alert alert = (Alert) iter.next();
			if(alert.getName().equalsIgnoreCase(alertName))
			{
				return alert;
			}
		}
		return new Alert();
	}
}
