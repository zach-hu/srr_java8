/**
 *
 */
package com.tsa.puridiom.cxml.entity;

import java.io.Serializable;
import java.util.Iterator;

import org.jdom.Element;

/**
 * @author Johnny Zapana
 *
 */
public class Sender extends Participant implements Serializable {

	private String userAgent;

	/**
	 * @return the userAgent
	 */
	public String getUserAgent() {
		return userAgent;
	}

	/**
	 * @param userAgent
	 *            the userAgent to set
	 */
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	/**
	 * <!ELEMENT Sender (Credential+, UserAgent)>
	 *
	 * @param senderObject
	 * @return
	 */
	public static Element buildSenderElement(Sender senderObject) {
		Element senderElement = new Element("Sender");

		senderObject.setContent(senderElement, senderObject);

		return senderElement;
	}

	private void setContent(Element senderElement, Sender senderObject) {
		/*
		 * (Credential+, UserAgent)
		 */
		Iterator iterator = senderObject.getCredential().iterator();

		do {
			senderElement.addContent(Credential
					.buildCredentialElement((Credential) iterator.next()));
		} while (iterator.hasNext());

		senderElement.addContent(new Element(Params.USER_AGENT_ELEMENT)
				.setText(senderObject.getUserAgent()));
	}
}
