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
public class FromTo extends Participant implements Serializable {
	private Correspondent correspondent;

	/**
	 * @return the correspondent
	 */
	public Correspondent getCorrespondent() {
		return correspondent;
	}

	/**
	 * @param correspondent
	 *            the correspondent to set
	 */
	public void setCorrespondent(Correspondent correspondent) {
		this.correspondent = correspondent;
	}

	/**
	 * <!ELEMENT From (Credential+, Correspondent?)> <!ELEMENT To (Credential+,
	 * Correspondent?)>
	 *
	 * @param elementName
	 * @param fromToObject
	 * @return
	 */
	public static Element buildFromToElement(String elementName,
			FromTo fromToObject) {
		Element fromToElement = new Element(elementName);

		fromToObject.setContent(fromToElement, fromToObject);

		return fromToElement;
	}

	private void setContent(Element fromToElement, FromTo fromToObject) {
		/*
		 * (Credential+, Correspondent?)
		 */
		Iterator iterator = fromToObject.getCredential().iterator();

		do {
			fromToElement.addContent(Credential
					.buildCredentialElement((Credential) iterator.next()));
		} while (iterator.hasNext());

		if (fromToObject.getCorrespondent() != null) {
			fromToElement
					.addContent(Correspondent
							.buildCorrespondentElement(fromToObject
									.getCorrespondent()));
		}
	}
}
