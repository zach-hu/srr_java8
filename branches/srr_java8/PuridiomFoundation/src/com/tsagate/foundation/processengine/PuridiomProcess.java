/*
 * Created on Jul 31, 2003
 */
package com.tsagate.foundation.processengine;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import netscape.ldap.LDAPConnection;

import org.jdom.Element;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.exceptions.DatabaseConnectionException;
import com.tsagate.foundation.security.tasks.DisconnectLDAPConnection;
import com.tsagate.foundation.security.tasks.GetAuthenticatedLDAPConnection;
import com.tsagate.foundation.security.tasks.GetLDAPConnection;
import com.tsagate.foundation.utility.Log;
import com.tsagate.properties.DictionaryManager;

/**
 * @author Administrator
 */
public class PuridiomProcess {
	private String name = "";
	private String connectionType = "None" ;
	private String connectionName = "" ;
	private String ldapConnectionType = "None" ;
	public List activities = null;
	private int status = Status.READY;
	private	List  dbsStack = null ;
	private RuleHelper rule = null;

	private boolean executed = true;
	private boolean onceExecution = false;
	private String applicationName = null;

	public PuridiomProcess() {
		if (activities == null) {
			activities = new ArrayList();
		}
		if (dbsStack == null) {
			dbsStack = new ArrayList() ;
		}
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int theStatus) {
		status = theStatus;
	}

	public void executeProcess(Map request) throws Exception {
		try {
			boolean bProcess = true;
			boolean executeProcess = true;
			Log.debug(this, "Process: " + getName());

			if (this.isOnceExecution())
			{
				executeProcess = (request.get("validToken") != null) ? ((Boolean) request.get("validToken")).booleanValue() : true;
				Log.debug(this, "Process: " + this.getName() + " is once execution");
			}

			if (executeProcess)
			{
				if (!this.rule.executeRule(request, this.applicationName))
				{
					bProcess = false;
					this.setStatus(Status.SUCCEEDED);
					this.setExecuted(false);
				}

				if (bProcess)
				{
					if (activities != null)
					{
						this.executeActivities(request);
					}
				}
			} else {
				this.setStatus(Status.DUPLICATED_REQUEST);
			}
		}
		catch (Exception e)
		{
			Log.debug(this, "Process: " + getName() + "Error - " + e.getMessage());
			this.status = Status.FAILED;
			throw e;
		}
		Log.debug(this, "Process: " + getName() + " done");
	}

	public void addActivity(Activity activity) throws Exception{
		try {
			if (activities == null) {
				activities = new ArrayList();
			}
			activities.add(activity);
		}
		catch (Exception e) {
			throw e;
		}
	}

	private void executeActivities(Map request) throws Exception {
		try {
			boolean validToken = (request.get("validToken") != null) ? ((Boolean) request.get("validToken")).booleanValue() : true;

			if (startDbSession(request) && startLdapSession(request)) {
				Iterator activityIterator = activities.iterator();
				while (activityIterator.hasNext()) {
					Activity activity = (Activity) activityIterator.next();
					boolean executeActivity = true;

					if (activity.isOnceExecution())
					{
						executeActivity = validToken;
						Log.debug(this, "Activity: " + activity.getName() + " is once execution");
					}

					if (executeActivity)
					{
						if (activity.getRule().executeRule(request, this.applicationName)) {
							if (activity.isCommitBefore()) {
								Log.debug(this, "Activity: " + activity.getName() + "** Commit Before **");
								endDbSession(request) ;
								startDbSession(request) ;
							}
							if (activity.isSynchronous()) {
								activity.executeActivity(request);
								this.setStatus(activity.getStatus());

								if (this.status == Status.FAILED && !activity.continueOnFailure()) {
									break;
								}
								else if (activity.getPostAction().equalsIgnoreCase("exitProcess")) {
									//this.setStatus(Status.COMPLETED);
									break;
								}
								else if (this.status == Status.COMPLETED) {
									break;
								}
							}
							else {
								runAsynchronousActivity(activity, request);
							}
							if (activity.isCommitAfter()) {
								Log.debug(this, "Activity: " + activity.getName() + "** Commit After **");
								endDbSession(request) ;
								startDbSession(request) ;
							}
						}
					}
				}
			}
		}
		catch (Exception e) {
			throw e;
		}
		finally {
				endDbSession(request) ;
				endLdapSession(request) ;
		}
	}


	private void runAsynchronousActivity(Activity activity, Object object) {
		final Activity theActivity = activity;
		final Object incomingObject = object;
		class MyRunner implements Runnable {
			public void run() {
				try {
					theActivity.executeActivity(incomingObject);
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

	/**
	 * Method startDbSession.
	 * @param incomingRequest
	 * @return DBSession
	 * @throws HibernateException
	 */
	public boolean startDbSession(Map request) throws HibernateException, DatabaseConnectionException
	{
	    Log.debug(this, "startDbSession - "  + this.connectionType);
		DBSession dbs = (DBSession) request.get("dbsession") ;
		String cfgId = (String) request.get("organizationId");
		boolean success = true;
		try
		{
			if (this.getConnectionName().length() > 0) {
				cfgId = this.getConnectionName();
			}
			if (cfgId == null || cfgId.trim().length() == 0) {
				cfgId = getDefaultDBConfigurationName();
			}
			if (this.connectionType.equalsIgnoreCase("None")) {
				// Do Nothing
			}
			else if (this.connectionType.equalsIgnoreCase("Connection-Required") || this.connectionType.equalsIgnoreCase("Transaction-Required"))
			{
			    if (dbs == null || !dbs.getSessionOrganizationId().equals(cfgId))
			    {
		    		dbs = new DBSession(cfgId, request) ;
				}
			    else
			    {
				    dbs.setProcessCount(dbs.getProcessCount() + 1) ;
				}
			    if(this.connectionType.equalsIgnoreCase("Connection-Required") )
			    {
			        dbs.noUpdate();
			    }
			} else if (this.connectionType.equalsIgnoreCase("New-Connection") || this.connectionType.equalsIgnoreCase("New-Transaction")) {
				if (dbs != null) {
					dbsStack.add(0,dbs) ;
				}
				dbs = new DBSession(cfgId, request) ;
				if (this.connectionType.equalsIgnoreCase("New-Connection")) {
					dbs.noUpdate() ;
				}
			}

			if (this.connectionType.equalsIgnoreCase("Transaction-Required") || this.connectionType.equalsIgnoreCase("New-Transaction")) {
				if (! dbs.isTransactionStarted()) {
					dbs.startTransaction()  ;
				}
			}

			if (!this.connectionType.equalsIgnoreCase("None") && (dbs.getStatus() == Status.FAILED)) {
				success = false;
			}
			//System.out.println(this.getName() + ", transaction: " + this.connectionType);

			request.put("dbsession",dbs) ;
		}
		catch(DatabaseConnectionException e)
		{
		    request.put("errorMsg", e.getMessage());
		    throw e;
		}
		catch(Exception e)
		{
		    e.printStackTrace();
			Log.error(this, "Error occured starting database!");
		}
		return success;
	}

	/**
	 * Method startLdapSession.
	 * @param incomingRequest
	 * @return boolean success
	 * @throws Exception
	 */
	public boolean startLdapSession(Map request) throws Exception {
		boolean success = true;

		try {
			LDAPConnection ldapConnection = (LDAPConnection) request.get("LDAPConnection") ;

			if (this.ldapConnectionType.equalsIgnoreCase("None")) {
				// Do Nothing
			}
			else if (this.ldapConnectionType.equalsIgnoreCase("Connection-Required")) {
				if (ldapConnection == null) {
					GetLDAPConnection getConnectionTask = new GetLDAPConnection();
					ldapConnection = (LDAPConnection) getConnectionTask.executeTask(request);
				}
			}
			else if (this.ldapConnectionType.equalsIgnoreCase("Authenticated-Connection-Required")) {
				if (ldapConnection == null) {
					GetAuthenticatedLDAPConnection getConnectionTask = new GetAuthenticatedLDAPConnection();
					ldapConnection = (LDAPConnection) getConnectionTask.executeTask(request);
				}
			}

			if (!this.ldapConnectionType.equalsIgnoreCase("None") && (ldapConnection == null || !ldapConnection.isConnected())) {
				success = false;
			}
			else if (this.ldapConnectionType.equalsIgnoreCase("Authenticated-Connection-Required") && (!ldapConnection.isAuthenticated())) {
				success = false;
			}
			request.put("LDAPConnection",ldapConnection) ;
		}
		catch (Exception e) {
			throw e;
		}
		return success;
	}
	/**
	 * Method endDbSession.
	 * @param request
	 * @return DBSession
	 * @throws HibernateException
	 */
	public DBSession endDbSession(Map request) throws HibernateException {

			DBSession dbs = (DBSession) request.get("dbsession") ;
			boolean	closeSession = false ;

		    Log.debug(this, "End DbSession - " + this.connectionType);
			if (dbs != null && (this.connectionType.equalsIgnoreCase("Connection-Required")  || this.connectionType.equalsIgnoreCase("Transaction-Required") || this.connectionType.equalsIgnoreCase("New-Connection") || this.connectionType.equalsIgnoreCase("New-Transaction"))) {
				if (dbs.getProcessCount() > 0) {
					// Shared Process
					dbs.setProcessCount(dbs.getProcessCount() - 1) ;
				} else {
					closeSession = true ;
				}
			}

			if (closeSession) {
				if (dbs != null)
				{
					dbs.setStatus(this.getStatus());
					dbs.close() ;
				}

				if (! dbsStack.isEmpty()) {
					// get session off the stack
					dbs = (DBSession) dbsStack.get(0) ;
					dbsStack.remove(0) ;
				} else {
					dbs = null ;
				}

				request.put("dbsession",dbs) ;
			}

			return dbs ;
	}
	/**
	 * Method endLdapSession.
	 * @param request
	 * @return LdapSession
	 * @throws Exception
	 */
	public void endLdapSession(Map request) throws Exception {
		LDAPConnection ldapConnection = (LDAPConnection) request.get("LDAPConnection") ;

		if (ldapConnection != null) {
			DisconnectLDAPConnection disconnectConnection = new DisconnectLDAPConnection();
			disconnectConnection.executeTask(request);
		}
	}
	private String getDefaultDBConfigurationName()
	{
		String property = "";

		if (this.getApplicationName() == null || this.getApplicationName().trim().length() == 0)
		{
			property = "default-database-configuration";
		}
		else
		{
			property += this.getApplicationName();
		}

	    String name = DictionaryManager.getInstance("host", "").getProperty(property, "puridiom");

		//system.out.println("DB config: " + name);

		return name;
	}
	/**
	 * @return
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param string
	 */
	public void setName(String string) {
		name = string;
	}
	/**
	 * Returns the connectionType.
	 * @return String
	 */
	public String getConnectionType() {
		return connectionType;
	}
	/**
	 * Sets the connectionType.
	 * @param connectionType The connectionType to set
	 */
	public void setConnectionType(String connectionType) {
		if (connectionType == null) {
			connectionType = "None" ;
		}
		this.connectionType = connectionType;
	}
	/**
	 * Returns the connectionName.
	 * @return String
	 */
	public String getConnectionName() {
		if (connectionName == null) {
			connectionName = "" ;
		}
		return connectionName;
	}
	/**
	 * Sets the connectionName.
	 * @param connectionName The connectionName to set
	 */
	public void setConnectionName(String connectionName) {
		if (connectionName == null) {
			connectionName = "" ;
		}
		this.connectionName = connectionName;
	}
	/**
	 * Returns the ldapConnectionType.
	 * @return String
	 */
	public String getLdapConnectionType() {
		return ldapConnectionType;
	}
	/**
	 * Sets the ldapConnectionType.
	 * @param ldapConnectionType the ldapConnectionType to set
	 */
	public void setLdapConnectionType(String type) {
		if (type == null) {
			type = "None" ;
		}
		this.ldapConnectionType = type;
	}

	/**
	 * @return Returns the ruleFileName.
	 */
	public RuleHelper getRule()
	{
		return this.rule;
	}

	/**
	 * @param ruleFileName The ruleFileName to set.
	 */
	public void setRule(String ruleFileName)
	{
		this.rule = new RuleHelper(ruleFileName, this.applicationName);
	}

	public void setRule(Element ruleElement)
	{
		this.rule = new RuleHelper(ruleElement, this.applicationName);
	}
	public String getApplicationName()
	{
		return this.applicationName;
	}
	public void setApplicationName(String name)
	{
		this.applicationName = name;
	}

	/**
	 * @return Returns the executed.
	 */
	public boolean isExecuted()
	{
		return executed;
	}

	/**
	 * @param executed The executed to set.
	 */
	public void setExecuted(boolean executed)
	{
		this.executed = executed;
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

	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("[ClassName=Process, ");
		sb.append("name=");
		sb.append(this.getName());
		sb.append("connectionType=");
		sb.append(this.getConnectionType());
		sb.append("ldapConnectionType=");
		sb.append(this.getLdapConnectionType());
		sb.append(" rule: ");
		sb.append(rule.toString());
		sb.append("] - contains the following activities: ");
		Iterator activityIterator = activities.iterator();
		while (activityIterator.hasNext()) {
			Activity activity = (Activity)activityIterator.next();
			sb.append("\n\t");
			sb.append(activity.toString());
		}
		sb.append("\n [end Process class]");
		return sb.toString();
	}

}
