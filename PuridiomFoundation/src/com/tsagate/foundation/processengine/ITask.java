/*
 * Created on Aug 1, 2003
 */
package com.tsagate.foundation.processengine;

import org.jdom.Element;

/**
 * @author Administrator
 */
public interface ITask {

	public String getApplicationName() ;
	public String getName() ;
	public boolean continueOnFailure();
	public boolean isSynchronous();
	public boolean isOnceExecution();
	public String getRuleFilename();
	public String getTaskObjectName();
	public String getProcessFilename();
	public String getPostAction();
	public String getAuditEntityList();
	public RuleHelper getRule();
	public int getStatus();
	public void setApplicationName(String string);
	public void setName(String string);
	public void setSynchronous(boolean value);
	public void setContinueOnFailure(boolean shouldContinueOnFailure);
	public void setOnceExecution(boolean onceExecution);
	public void setRuleFilename(String ruleFilename);
	public void setTaskObjectName(String taskObjectName);
	public void setProcessFilename(String processFilename);
	public void setPostAction(String postAction);
	public void setStatus(int status);
	public void setRule(Element ruleElement);
	public void setEntityList(String entityList);
	public Object executeTask(Object object) throws Exception;

}
