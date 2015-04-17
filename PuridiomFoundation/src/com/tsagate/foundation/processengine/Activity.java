/*
 * Created on Aug 1, 2003
 */
package com.tsagate.foundation.processengine;

import com.tsagate.foundation.rule.*;
import com.tsagate.foundation.utility.*;
import org.jdom.Element;
import java.util.*;

/**
 * @author Administrator
 */
public class Activity {
	private String applicationName = "";
	private String name = "";
	private String postAction = "";
	private RuleHelper rule;
	private boolean synchronous = true;
	private boolean continueOnFailure = false;
	private boolean onceExecution = false;
	private int status = Status.READY;
	private boolean commitBefore = false ;
	private boolean commitAfter = false ;
	
	public List tasks = null;

	public Activity() {
		if (tasks == null) {
			tasks = new ArrayList();
		}
	}
	public String getName() {
		return name;
	}
	public String getPostAction() {
		return postAction;
	}
	public String getRuleFilename() {
		return rule.getName();
	}
	public RuleHelper getRule(){
		return this.rule;
	}
	public boolean isSynchronous() {
		return synchronous;
	}
	public boolean isCommitBefore() {
		return commitBefore;
	}
	public void setCommitBefore(boolean commitBefore) {
		this.commitBefore = commitBefore;
	}
	public boolean isCommitAfter() {
		return commitAfter;
	}
	public void setCommitAfter(boolean commitAfter) {
		this.commitAfter = commitAfter;
	}
	public boolean continueOnFailure() {
		return continueOnFailure;
	}
	public int getStatus() {
		return status;
	}
	public void setName(String string) {
		if (string != null) {
			name = string;
		}
	}
	public void setPostAction(String string) {
		if (string != null) {
			postAction = string;
		}
	}
	public void setRuleFilename(String string) {
		if (string != null) {
			rule = new RuleHelper(string, this.applicationName);
		}
	}

	public void setRule(Element ruleElement) {
		rule = new RuleHelper(ruleElement, this.applicationName);
	}

	public void setSynchronous(boolean synchronousValue) {
		synchronous = synchronousValue;
	}
	public void setContinueOnFailure(boolean shouldContinueOnFailure) {
		continueOnFailure = shouldContinueOnFailure;
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

	public void setStatus(int theStatus) {
		status = theStatus;
	}
	public void executeActivity(Object object) throws Exception {
		try {
			Map incomingRequest = (Map) object;
			Iterator taskIterator = tasks.iterator();
			boolean validToken = (incomingRequest.get("validToken") != null) ? ((Boolean) incomingRequest.get("validToken")).booleanValue() : true;

			Log.debug(this, "Executing: " + this.name);

			while (taskIterator.hasNext()) {
				ITask task = (ITask) taskIterator.next();
				String taskName = task.getName();
				boolean executeTask = true;

				if (task.isOnceExecution())
				{
					executeTask = validToken;
					Log.debug(this, "Task: " + taskName + " is once execution");
				}

				if (executeTask)
				{
					if (task.getRule().executeRule(incomingRequest, this.applicationName))
					{
						Log.debug(this, "Task: " + task.getTaskObjectName());
						if (task.isSynchronous()) {
							Object resultObject = task.executeTask(incomingRequest);
							//Log.debug(this, tatskName + " returns: " + Utility.tsaToString(resultObject));
							Log.debug(this, task.getTaskObjectName() + " returned with status: " + task.getStatus());
							this.setStatus(task.getStatus());
							if (this.status == Status.FAILED && !task.continueOnFailure()) {
								break;
							}
							else if (task.getPostAction().trim().equalsIgnoreCase("exitActivity")) {
								if (! (task instanceof InvokePuridiomProcessTask))	{
									incomingRequest.put(taskName, resultObject);
								}
								break;
							}
							else if (task.getPostAction().trim().equalsIgnoreCase("exitProcess")) {
								if (! (task instanceof InvokePuridiomProcessTask))	{
									incomingRequest.put(taskName, resultObject);
								}
								//this.setStatus(Status.COMPLETED);
								this.setPostAction("exitProcess");
								break;
							}
							if (! (task instanceof InvokePuridiomProcessTask))	{
								incomingRequest.put(taskName, resultObject);
							}
						}
						else {
							runAsynchronousTask(task, incomingRequest);
						}
					}
					else
					{
						Log.debug(this, "Continue.. task: " + taskName + "will not be executed (" + task.getRule().getName() + " returned false!)");
						this.setStatus(Status.SUCCEEDED);
					}
				}
			}
		}
		catch (Exception e) {
			//give to exception handling service
			e.printStackTrace();
			throw e;
		}
	}
	private void runAsynchronousTask(ITask task, Map incomingRequest) {
		final ITask theTask = task;
		final Map theIncomingRequest = incomingRequest;
		class MyRunner implements Runnable {
			public void run() {
				try {
					theTask.executeTask(theIncomingRequest);
				}
				catch (Exception exception) {
					//handle exception by sending it to the
					//exception handling service
					//no need to throw this since the process
					//has exited at this point
				}
			}
		}
		new Thread(new MyRunner()).start();
	}
	public void addTask(ITask task) throws Exception {
		try {
			if (tasks == null) {
				tasks = new ArrayList();
			}
			tasks.add(task);
		}
		catch (Exception e) {
			throw e;
		}
	}
	private boolean executeRule(String rules, Map request) throws Exception {
		boolean evaluation = false;
		try {
			if (rules == null || rules.trim().length() == 0) {
				evaluation = true;
			}
			else {
				rules = rules.trim();

				boolean continueEvaluation = true;
				int	ind = rules.lastIndexOf(";");

				if (ind < (rules.length() - 1)) {
					rules = rules + ";";
				}
				ind = rules.indexOf(";");

				while (ind > 0 && continueEvaluation) {
					String	ruleFilename = rules.substring(0, rules.indexOf(";"));
					rules = rules.substring(ind + 1);

					if (ruleFilename.indexOf(".xml") < 0) {
						ruleFilename = ruleFilename + ".xml";
					}
					RuleManager ruleManager = RuleManager.getInstance();
					Rule rule = ruleManager.getRule(ruleFilename, request, this.applicationName);
					continueEvaluation = rule.evaluate(request);
					ind = rules.indexOf(";");
				}
				evaluation = continueEvaluation;
			}
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			//system.out.println("rule name(s): " + rules);
			throw e;
		}
		return evaluation;
	}
	public String getApplicationName() {
		return this.applicationName;
	}
	public void setApplicationName(String appName) {
		this.applicationName = appName;
	}
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[ClassName = Activity, ");
		sb.append("name=" + this.getName());
		sb.append("applicationName=" + this.getApplicationName());
		sb.append("] - contains the following tasks: ");
		Iterator taskIterator = tasks.iterator();
		while (taskIterator.hasNext()) {
			ITask task = (ITask) taskIterator.next();
			sb.append("\n\t\t");
			sb.append(task.toString());
		}

		return sb.toString();
	}
}
