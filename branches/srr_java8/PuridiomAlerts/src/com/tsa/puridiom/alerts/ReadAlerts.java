package com.tsa.puridiom.alerts;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom.DataConversionException;
import org.jdom.Document;
import org.jdom.Element;

import com.tsagate.foundation.utility.Utility;

public class ReadAlerts
{
	private List alertList = new ArrayList();
	private Document xmlDocument = null;

	public ReadAlerts(String fileName, String organizationId)
	{
		this.xmlDocument = Utility.loadXml(fileName, organizationId);
		loadAlertFromXml(this.xmlDocument, organizationId);
	}

	private void loadAlertFromXml(Document alertXml, String organizationId)
	{
		 List alerts =  alertXml.getRootElement().getChildren("alert");
		 boolean alertsEnabled = true;
		 try
		 {
			 alertsEnabled = alertXml.getRootElement().getAttribute("enabled").getBooleanValue();
		 }
		 catch (DataConversionException e1)
		 {
			 alertsEnabled = false;
		 }

		 if(alertsEnabled)
		 {
	         for(int i = 0; i < alerts.size(); i++)
	         {
	        	 Element alertElement = (Element)alerts.get(i);
	        	 Alert alert = new Alert();
	        	 boolean enabled = true;
	        	 try
	        	 {
					enabled = alertElement.getAttribute("enabled").getBooleanValue();
				 }
	        	 catch (DataConversionException e)
	        	 {
					enabled = false;
				 }
	        	 alert.setEnabled(enabled);
	        	 String name = alertElement.getChildText("name");
	        	 alert.setName(name);
	             String alertType = alertElement.getChildText("type");
	             alert.setType(alertType);
	             String everyDay = alertElement.getChildText("every-day");
	        	 alert.setEveryDay(everyDay);
	        	 String typeAlert = alertElement.getChildText("type-alert");
	        	 alert.setTypeAlert(typeAlert);
	        	 String description = alertElement.getChildText("description");
	        	 alert.setDescription(description);
	             Element retrieve = alertElement.getChild("retrieve");
	             if(retrieve != null)
	             {
	            	 String retrieveProcess = retrieve.getChildText("process");
		             alert.setRetrieveProcess(retrieveProcess);
		             String argumentName = retrieve.getChildText("argumentName");
		             alert.setRetrieveArgumentName(argumentName);
	             }
	             Element lastRunEl = alertElement.getChild("last-run");
	             if(lastRunEl != null)
	             {
	            	 String lastRun = alertElement.getChildText("last-run");
		             alert.setLastRun(lastRun);
	             }

	             String subject = alertElement.getChildText("subject");
	        	 alert.setSubject(subject);
	             String alertWhere = alertElement.getChildText("where");
	             alert.setWhere(alertWhere);
	             String alertProcess = alertElement.getChildText("process");
	             alert.setProcess(alertProcess);
	             List alertWhereArgEl = alertElement.getChild("where").getChildren();
	             List alertWhereArg = new ArrayList();
	             if(alertWhereArgEl == null){ alertWhereArgEl = new ArrayList();	}

	             for (Iterator iter = alertWhereArgEl.iterator(); iter.hasNext();)
	             {
					Element argEl = (Element) iter.next();
					AlertWhereArgument awa = new AlertWhereArgument();
					awa.setOrganizationId(organizationId);
					awa.setOffset(argEl.getChildText("offset"));
					awa.setType(argEl.getChildText("type"));
					awa.setValue(argEl.getChildText("value"));
					alertWhereArg.add(awa);
	             }
	             alert.setWhereArgList(alertWhereArg);

	             Element sendtoElement = alertElement.getChild("sendto");
	             if(sendtoElement != null)
	             {
	            	 List userList = new ArrayList();
	            	 for (Iterator iter = sendtoElement.getChildren().iterator(); iter.hasNext();)
	            	 {
						Element user = (Element) iter.next();
						AlertSendTo sendTo = new AlertSendTo();
						sendTo.setType(user.getAttributeValue("type"), user.getText(), user);
						userList.add(sendTo);
					}
	            	 alert.setSenTo(userList);
	             }
	             Element msgElemet = alertElement.getChild("message");
	             List msgLines = new ArrayList();

	             if (msgElemet != null)
	             {
	            	 msgLines = msgElemet.getChildren();
	             }

	             alert.setMessage(msgLines);

	             alertList.add(alert);
	         }
		 }
	}

	public List getAlertList()
	{
		return alertList;
	}

	public void setAlertList(List alertList)
	{
		this.alertList = alertList;
	}

}
