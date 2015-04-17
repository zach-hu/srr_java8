/**
 *
 */
package com.tsa.puridiom.cxml.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom.Attribute;
import org.jdom.Element;

/**
 * @author Johnny Zapana
 *
 */
public class Node implements Serializable {
	private String type;

	private Boolean itemDetailsRequired;

	// private List<Credential> credential;
	private List credential = new ArrayList();

	/**
	 * @return the credential
	 */
	// public List<Credential> getCredential() {
	public List getCredential() {
		return credential;
	}

	/**
	 *
	 * @param credential
	 * @return
	 */
	public List addCredential(Credential credential) {
		this.credential.add(credential);

		return this.credential;
	}

	/**
	 * @param credential
	 *            the credential to set
	 */
	// public void setCredential(List<Credential> credential) {
	public void setCredential(List credential) {
		this.credential = credential;
	}

	/**
	 * @return the itemDetailsRequired
	 */
	public Boolean getItemDetailsRequired() {
		return itemDetailsRequired;
	}

	/**
	 * @param itemDetailsRequired
	 *            the itemDetailsRequired to set
	 */
	public void setItemDetailsRequired(Boolean itemDetailsRequired) {
		this.itemDetailsRequired = itemDetailsRequired;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * <!ELEMENT Node (Credential+)> <!ATTLIST Node type (copy | route)
	 * #REQUIRED itemDetailsRequired (yes) #IMPLIED >
	 */
	public static Element buildNodeElement(Node nodeObject) {
		Element nodeElement = new Element("Node");

		nodeObject.setAttributes(nodeElement, nodeObject);

		nodeObject.setContent(nodeElement, nodeObject);

		return nodeElement;
	}

	private void setAttributes(Element nodeElement, Node nodeObject) {
		/*
		 * type (copy | route) #REQUIRED itemDetailsRequired (yes) #IMPLIED
		 */
		// List<Attribute> attributes = new ArrayList<Attribute>();
		List attributes = new ArrayList();

		attributes.add(new Attribute("type", nodeObject.getType()));

		if (nodeObject.getItemDetailsRequired() != null) {
			attributes.add(new Attribute("itemDetailsRequired", String
					.valueOf(nodeObject.getItemDetailsRequired())));
		}

		nodeElement.setAttributes(attributes);
	}

	private void setContent(Element nodeElement, Node nodeObject) {
		/*
		 * (Credential+)
		 */
		Iterator iterator = nodeObject.getCredential().iterator();
		do {
			nodeElement.addContent(Credential
					.buildCredentialElement((Credential) iterator.next()));
		} while (iterator.hasNext());
	}
}
