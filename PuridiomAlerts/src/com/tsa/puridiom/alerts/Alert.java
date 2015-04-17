/**
 *
 */
package com.tsa.puridiom.alerts;

import java.util.ArrayList;
import java.util.List;

import com.tsagate.foundation.utility.Utility;

/**
 * @author renzo
 *
 */
public class Alert
{
	private String name = "";
	private String type = "";
	private String everyDay = "";
	private String typeAlert = "";
	private String description = "";
	private List sendTo = new ArrayList();
	private List whereArgList = new ArrayList();
	private AlertMessage message;
	private String where = "";
	private String subject = "";
	private boolean enabled = true;
	private String process = "";
	private String retrieveProcess = "";
	//TODO might need to also store the type and do a cast from String?
	private String retrieveArgumentName = null;
	private String lastRun = "";


	public AlertMessage getMessage()
	{
		return message;
	}
	public void setMessage(List messageList)
	{
		message = new AlertMessage(messageList);
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public List getSenTo() {
		return sendTo;
	}
	public void setSenTo(List sendTo)
	{
		this.sendTo = sendTo;
	}
	public String getType() {
		return type;
	}
	public void setType(String type)
	{
		this.type = type;
	}
	public void setEveryDay(String everyDay)
	{
		this.everyDay = everyDay;
	}
	public String getEveryDay() {
		return everyDay;
	}
	public void setTypeAlert(String typeAlert)
	{
		this.typeAlert = typeAlert;
	}
	public String getTypeAlert() {
		return typeAlert;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	public String getDescription() {
		return description;
	}
	public String getWhere()
	{
		return Utility.ckNull(this.where);
	}
	public void setWhere(String where)
	{
		this.where = where;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String toString() {
			StringBuffer buffer = new StringBuffer();
			buffer.append("[Alert:");
			buffer.append(" name: ");
			buffer.append(name);
			buffer.append(" type: ");
			buffer.append(type);
			buffer.append(" sendTo: ");
			buffer.append(sendTo);
			buffer.append(" message: ");
			buffer.append(message);
			buffer.append(" where: ");
			buffer.append(where);
			buffer.append(" subject: ");
			buffer.append(subject);
			buffer.append("]");
			return buffer.toString();
		}
	public List getWhereArgList() {
		return whereArgList;
	}
	public void setWhereArgList(List whereArgList) {
		this.whereArgList = whereArgList;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public String getProcess() {
		return process;
	}
	public void setProcess(String process) {
		this.process = process;
	}
	public String getRetrieveProcess()
	{
		return retrieveProcess;
	}
	public void setRetrieveProcess(String retrieveProcess)
	{
		this.retrieveProcess = retrieveProcess;
	}
	public String getRetrieveArgumentName()
	{
		return retrieveArgumentName;
	}
	public void setRetrieveArgumentName(String retrieveArgumentName)
	{
		this.retrieveArgumentName = retrieveArgumentName;
	}
	public String getLastRun()
	{
		return lastRun;
	}
	public void setLastRun(String lastRun)
	{
		this.lastRun = lastRun;
	}
}
