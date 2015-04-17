/*
 * Created on Aug 1, 2003
 */
package com.tsagate.foundation.processengine;

import java.util.*;

import org.jdom.Element;

import com.tsagate.foundation.utility.Utility;

/**
 * @author Administrator
 */
public class Task implements ITask {
	protected String applicationName = "";
	protected String name = "";
	protected boolean synchronous = true;
	protected boolean continueOnFailure = false;
	protected boolean onceExecution = false;
	protected String ruleFilename;
	protected String taskObjectName = "";
	protected String processFilename = "";
	protected String postAction = "";
	protected List parameters = null;
	protected int status = Status.READY;
	protected RuleHelper rule;
	protected String entityList;

	public Task() {
		//
	}
	public String getApplicationName() {
		return applicationName;
	}
	public String getName() {
		return name;
	}
	public boolean isSynchronous() {
		return this.synchronous;
	}
	public boolean continueOnFailure() {
		return this.continueOnFailure;
	}
	public String getRuleFilename() {
		return this.ruleFilename;
	}
	public String getTaskObjectName() {
		return taskObjectName;
	}
	public String getProcessFilename() {
		return this.processFilename;
	}
	public String getPostAction() {
		return this.postAction;
	}
	public int getStatus() {
		return this.status;
	}
	public void setApplicationName(String string) {
		if (string != null) {
			this.applicationName = string;
		}
	}
	public void setName(String string) {
		if (string != null) {
			this.name = string;
		}
	}
	public void setSynchronous(boolean value) {
		this.synchronous = value;
	}
	public void setContinueOnFailure(boolean shouldContinueOnFailure) {
		this.continueOnFailure = shouldContinueOnFailure;
	}
	
	/**
	 * @return the onceExecution
	 */
	public boolean isOnceExecution()
	{
		return onceExecution;
	}
	
	/**
	 * @param onceExecution the onceExecution to set
	 */
	public void setOnceExecution(boolean onceExecution)
	{
		this.onceExecution = onceExecution;
	}
	
	public void setRuleFilename(String rule) {
		if (rule != null) {
			this.ruleFilename= rule;
		}
	}
	public void setTaskObjectName(String string) {
		if (string != null) {
			taskObjectName = string;
		}
	}
	public void setProcessFilename(String theProcessFilename) {
		if (theProcessFilename != null) {
			this.processFilename = theProcessFilename;
		}
	}
	public void setPostAction(String action) {
		if (action != null) {
			this.postAction = action;
		}
	}
	public void setStatus(int theStatus) {
		this.status = theStatus;
	}
	public Object executeTask(Object object) throws Exception {
		Object returnObject = null;
		try {
			//
		}
		catch (Exception e) {
			throw e;
		}
		return returnObject;
	}
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[ClassName = ");
		sb.append(this.getTaskObjectName());
		sb.append(", name=");
		sb.append(this.getName());
		sb.append(", synchronous=");
		sb.append(this.isSynchronous());
		sb.append(", continueOnFailure=");
		sb.append(this.continueOnFailure());
		sb.append(", ruleFileName=");
		sb.append(this.getRuleFilename());
		sb.append(", taskObjectName=");
		sb.append(this.getTaskObjectName());
		sb.append(", processFilename=");
		sb.append(this.getProcessFilename());
		sb.append(", postAction=");
		sb.append(", status=");
		sb.append(this.getStatus());
		sb.append("]");
		return sb.toString();
	}
	/*
	 * @see com.tsagate.foundation.processengine.ITask#getRule()
	 */
	public RuleHelper getRule()
	{
		return this.rule;
	}
	/*
	 * @see com.tsagate.foundation.processengine.ITask#setRule(org.jdom.Element)
	 */
	public void setRule(Element ruleElement)
	{
		rule = new RuleHelper(ruleElement, this.applicationName);
	}
	public String getAuditEntityList() {
		return Utility.ckNull(this.entityList);
	}
	public void setEntityList(String entityList) {
		if (entityList != null) {		this.entityList = entityList	;		}
	}

	protected Map getDefaultUpdateParameters(Map incomingRequest) {
		Map updateParameters = new HashMap();
		
		updateParameters.put("userId", incomingRequest.get("userId"));
		updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
		updateParameters.put("organizationId", incomingRequest.get("organizationId"));
		updateParameters.put("dbsession", incomingRequest.get("dbsession"));
	    
		return updateParameters;
	}
}
