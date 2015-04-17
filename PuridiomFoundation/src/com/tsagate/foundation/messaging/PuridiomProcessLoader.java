/*
 * Created on Jul 31, 2003
 */
package com.tsagate.foundation.messaging;

import com.tsagate.foundation.processengine.Activity;
import com.tsagate.foundation.processengine.ITask;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;
import com.tsagate.properties.DictionaryManager;
import java.io.File;
import java.util.Iterator;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.DOMBuilder;
/**
 * @author Administrator
 */

@SuppressWarnings("unchecked")
public class PuridiomProcessLoader {
	private String applicationName = "";
	private String organizationId = "";

    /**
     * @return Returns the organizationId.
     */
    public String getOrganizationId()
    {
        return this.organizationId;
    }
    /**
     * @param organizationId The organizationId to set.
     */
    public void setOrganizationId(String organizationId)
    {
        this.organizationId = organizationId;
    }

    public PuridiomProcessLoader(String asOrganizationId)
    {
        this.setOrganizationId(asOrganizationId);
	}
	public PuridiomProcessLoader() {
	}
	public PuridiomProcess loadProcess(String processName) throws Exception {
		PuridiomProcess process = null;
		Document document = null;
		try {
			document = buildProcessDocument( processName);
			process = populateProcessObject(document);
		}
		catch (Exception exception) {
			com.tsagate.foundation.utility.Log.error(this, exception.toString());
			throw exception;
		}
		return process;
	}
	protected Document buildProcessDocument(String processName) throws Exception {
		Document document = null;
		try {
			String filename = this.getProcessPath() + processName;
			Log.debug(this, "Process: " + processName) ;
			File f = new File(filename);
			DOMBuilder docBuilder = new DOMBuilder();
			document = docBuilder.build(f);
			return document;
		}
		catch (Exception exception) {
			//system.out.println("process name: " + processName);
			exception.printStackTrace();
			throw exception;
		}
//		finally {
//			return document;
//		}
	}
	protected PuridiomProcess populateProcessObject(Document document) throws Exception {
		PuridiomProcess process = null;
		try {
			process = new PuridiomProcess();
			Element rootElement = document.getRootElement();
			process.setApplicationName(this.getApplicationName());
			process.setName(rootElement.getAttributeValue("name"));
			process.setConnectionType(rootElement.getAttributeValue("connection-type"));
			process.setConnectionName(rootElement.getAttributeValue("connection-name"));
			process.setOnceExecution(new Boolean(rootElement.getAttributeValue("once-execution")).booleanValue());
			process.setLdapConnectionType(rootElement.getAttributeValue("ldap-connection-type"));
			process.setRule(rootElement.getChild("rule-filename"));

			loadActivities(rootElement, process);
		}
		catch (Exception exception) {
			com.tsagate.foundation.utility.Log.error(this, exception.toString());
			throw exception;
		}
		return process;
	}
	protected void loadActivities(Element rootElement, PuridiomProcess process) throws Exception {
		try {
			Element activitiesElement = rootElement.getChild("activities");
			Iterator activities = activitiesElement.getChildren().iterator();
			while (activities.hasNext()) {
				Element activityElement = (Element)activities.next();
				Activity activity = new Activity();
				activity.setApplicationName(this.getApplicationName());
				activity.setName(activityElement.getAttributeValue("name"));

				activity.setSynchronous(new Boolean(activityElement.getAttributeValue("synchronous")).booleanValue());
				activity.setContinueOnFailure(new Boolean(activityElement.getAttributeValue("continue-on-failure")).booleanValue());
				activity.setOnceExecution(new Boolean(activityElement.getAttributeValue("once-execution")).booleanValue());
				activity.setCommitBefore(new Boolean(activityElement.getAttributeValue("commit-before")).booleanValue());
				activity.setCommitAfter(new Boolean(activityElement.getAttributeValue("commit-after")).booleanValue());
				if (activityElement.hasChildren()) {
					getActivityTaskElements(activityElement, activity);
				}
				process.addActivity(activity);
			}
		}
		catch (Exception exception) {
			throw exception;
		}
	}
	protected void getActivityTaskElements(Element activityElement, Activity activity) throws Exception {
		try {
			Element ruleElement = activityElement.getChild("rule-filename");
			activity.setRule(ruleElement);

			ruleElement = activityElement.getChild("post-action");
			if (ruleElement != null) {
				activity.setPostAction(ruleElement.getText());
			}

			Iterator tasks = activityElement.getChildren("task").iterator();
			while (tasks.hasNext())
			{
				Element taskElement = (Element) tasks.next();
				String objectName = taskElement.getChildText("task-object-name");
				ITask task = (ITask)Class.forName(objectName).newInstance();
				task.setApplicationName(this.getApplicationName());
				task.setName(taskElement.getAttributeValue("name"));
				task.setTaskObjectName(taskElement.getChildText("task-object-name"));
				task.setSynchronous(new Boolean(taskElement.getAttributeValue("synchronous")).booleanValue());
				task.setContinueOnFailure(new Boolean(taskElement.getAttributeValue("continue-on-failure")).booleanValue());
				task.setOnceExecution(new Boolean(taskElement.getAttributeValue("once-execution")).booleanValue());
				task.setRule(taskElement.getChild("rule-filename"));
				task.setProcessFilename(taskElement.getChildText("process-filename"));
				task.setPostAction(taskElement.getChildText("post-action"));
				task.setEntityList(taskElement.getChildText("entities"));
				activity.addTask(task);
			}
		}
		catch (Exception exception)
		{
			com.tsagate.foundation.utility.Log.error(this, exception.toString());
			throw exception;
		}
	}
	protected String getProcessPath() throws Exception
	{
		try
		{
		    String path = "";
		    String property = "process-xml-path";

			if (!Utility.isEmpty(this.getApplicationName()))
			{
				property = this.getApplicationName() + "-" + property;
			}

		    path = DictionaryManager.getInstance("host", this.getOrganizationId()).getProperty(property, null);

		    if (path == null) {
		    	throw new Exception("The process xml path (" + property + ") was not found in the properties file for '" + this.getOrganizationId() + "'.");
		    }
			return path;
		}
		catch (Exception e) {
			throw e;
		}
	}

	public String getApplicationName() {
		return applicationName;
	}
	public void setApplicationName(String appName) {
		this.applicationName = appName;
	}
}
