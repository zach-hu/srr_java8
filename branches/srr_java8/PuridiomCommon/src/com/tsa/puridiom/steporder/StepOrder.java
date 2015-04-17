/*
 * Created on Jun 2, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package com.tsa.puridiom.steporder;
/**
 * @author renzo
 * 
 * To change this generated comment go to Window>Preferences>Java>Code
 * Generation>Code and Comments
 */
public class StepOrder {
	private String process = "";
	private String method = "";
	private String visible = "";
	private String label = "";
	private String link = "";
	private int order = -1;
	public String sClassName = "StepOrder";
	/**
	 * @return int
	 */
	public int getOrder() {
		return order;
	}
	/**
	 * Sets the label.
	 * 
	 * @param label
	 *                The label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}
	/**
	 * Sets the link.
	 * 
	 * @param link
	 *                The link to set
	 */
	public void setLink(String link) {
		this.link = link;
	}
	/**
	 * Sets the order.
	 * 
	 * @param order
	 *                The order to set
	 */
	public void setOrder(int order) {
		this.order = order;
	}
	/**
	 * Sets the process.
	 * 
	 * @param process
	 *                The process to set
	 */
	public void setProcess(String process) {
		this.process = process;
	}
	/**
	 * Sets the visible.
	 * 
	 * @param visible
	 *                The visible to set
	 */
	public void setVisible(String visible) {
		this.visible = visible;
	}
	/**
	 * Sets the retrieve method
	 * 
	 * @param
	 */
	public void setMethod(String method) {
		this.method = method;
	}
	/**
	 * @return String
	 */
	public String getLabel() {
		return label;
	}
	/**
	 * @return String
	 */
	public String getLink() {
		return link;
	}
	/**
	 * @return String
	 */
	public String getProcess() {
		return process;
	}
	/**
	 * @return String
	 */
	public String getVisible() {
		return visible;
	}
	/**
	 * @return String
	 */
	public String getMethod() {
		return method;
	}
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(this.sClassName + "\t");
		sb.append("process: " + this.process + "\t");
		sb.append("visible: " + this.visible + "\t");
		sb.append("label: " + this.label + "\t");
		sb.append("link: " + this.link + "\t");
		return sb.toString();
	}
}
