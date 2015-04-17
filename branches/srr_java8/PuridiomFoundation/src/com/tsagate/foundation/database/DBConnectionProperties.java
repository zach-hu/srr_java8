/*
 * Created on Oct. 16, 2003
 */
package com.tsagate.foundation.database;

import java.io.*;
import java.util.Iterator;
import org.jdom.*;
import org.jdom.input.DOMBuilder;
import org.jdom.output.XMLOutputter;

/**
 * @author Kelli
 */
public class DBConnectionProperties {
	private String name = "";
	private String dialect = "";
	private String driverClass = "";
	private String userName = "";
	private String password = "";
	private String connectionUrl = "";
	private boolean showSql = false;
	private Document document = null;
	private File xmlFile = null;
	
	public DBConnectionProperties () {
	}
	
	public DBConnectionProperties (String fileName) throws Exception {
		this.loadConnectionFile(fileName);
		this.populateConnectionProperties();
	}
	
	private Document loadConnectionFile(String fileName) throws Exception {
		try {
			this.setName(fileName);
			
			// temporary path for database configuation files
			String pathToFiles = "C:\\Temp\\development\\software\\properties\\";
			xmlFile = new File(pathToFiles + fileName + "cfg.xml");
			
			DOMBuilder docBuilder = new DOMBuilder();	
			document = docBuilder.build(xmlFile);
		}
		catch(Exception exception) {
			//system.out.println("EXCEPTION ERROR:  (fileName = " + fileName + ")");
			exception.printStackTrace();
			throw exception;
		}
		finally {
			return document;
		}
	}
	
	private void populateConnectionProperties() throws Exception {
		try {
			Element rootElement = document.getRootElement();
			Element sessionElement = rootElement.getChild("session-factory");
			Iterator properties = sessionElement.getChildren("property").iterator();
			
			while(properties.hasNext()){
				Element propertyElement = (Element)properties.next();
				String propertyName = propertyElement.getAttributeValue("name");
				String propertyValue = propertyElement.getText();

				if (propertyName.equals("dialect")) {
					this.setDialect(propertyValue);
				}
				else if (propertyName.equals("connection.driver_class")) {
					this.setDriverClass(propertyValue);
				}
				else if (propertyName.equals("connection.username")) {
					this.setUserName(propertyValue);
				}
				else if (propertyName.equals("connection.password")) {
					this.setPassword(propertyValue);
				}
				else if (propertyName.equals("connection.url")) {
					this.setConnectionUrl(propertyValue);
				}
				else if (propertyName.equals("show_sql")) {
					this.setShowSql(new Boolean(propertyValue).booleanValue());
				}
			}
		}
		catch(Exception exception){	
			throw exception;	
		}
	}
	
	public void updateConnectionProperties() throws Exception {
		try {
			Element rootElement = document.getRootElement();
			Element sessionElement = rootElement.getChild("session-factory");
			Iterator properties = sessionElement.getChildren("property").iterator();
			
			while(properties.hasNext()){
				Element propertyElement = (Element)properties.next();
				String propertyName = propertyElement.getAttributeValue("name");
				String propertyValue = propertyElement.getText();

				if (propertyName.equals("dialect")) {
					propertyElement.setText(this.dialect);
				}
				else if (propertyName.equals("connection.driver_class")) {
					propertyElement.setText(this.driverClass);
				}
				else if (propertyName.equals("connection.username")) {
					propertyElement.setText(this.userName);
				}
				else if (propertyName.equals("connection.password")) {
					propertyElement.setText(this.password);
				}
				else if (propertyName.equals("connection.url")) {
					propertyElement.setText(this.connectionUrl);
				}
				else if (propertyName.equals("show_sql")) {
					propertyElement.setText(String.valueOf(this.showSql));
				}
			}
			
			if(this.xmlFile != null && this.xmlFile.isFile())
			{
				FileOutputStream fos = new FileOutputStream(this.xmlFile);
				XMLOutputter outputter = new XMLOutputter(" ", false);
				outputter.output(document, fos);
			}
		}
		catch (IOException ie)
		{
			ie.printStackTrace();
			throw ie;
		}
		catch (Exception exception){	
			throw exception;	
		}
	}
					
	/**
	 * @return String
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return String
	 */
	public String getDialect() {
		return dialect;
	}
	/**
	 * @return String
	 */
	public String getDriverClass() {
		return driverClass;
	}
	/**
	 * @return String
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @return String
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @return String
	 */
	public String getConnectionUrl() {
		return connectionUrl;
	}
	/**
	 * @return boolean
	 */
	public boolean getShowSql() {
		return showSql;
	}
	/**
	 * @param string
	 */
	public void setName(String string) {
		name = string;
	}
	/**
	 * @param string
	 */
	public void setDialect(String string) {
		dialect = string;
	}
	/**
	 * @param string
	 */
	public void setDriverClass(String string) {
		driverClass = string;
	}
	/**
	 * @param string
	 */
	public void setUserName(String string) {
		userName = string;
	}
	/**
	 * @param string
	 */
	public void setPassword(String string) {
		password = string;
	}
	/**
	 * @param string
	 */
	public void setConnectionUrl(String string) {
		connectionUrl = string;
	}
	/**
	 * @param boolean
	 */
	public void setShowSql(boolean b) {
		showSql = b;
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("[ClassName = DBConnectionProperties, ");
		sb.append("name = " + this.getName() + ", ");
		sb.append("dialect = " + this.getDialect() + ", ");
		sb.append("driverClass = " + this.getDriverClass() + ", ");
		sb.append("userName = " + this.getUserName() + ", ");
		sb.append("password = " + this.getPassword() + ", ");
		sb.append("connectionUrl = " + this.getConnectionUrl() + ", ");
		sb.append("showSql = " + String.valueOf(this.getShowSql()) + "]");
		
		return sb.toString();
	}
}
