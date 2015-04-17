/**
 *
 */
package com.tsa.puridiom.cxml.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Attribute;
import org.jdom.Element;

/**
 * @author Johnny Zapana
 *
 */
public class Credential implements Serializable {

	private String domain;

	private String type;

	private Identity identity;

	private Object sharedSecret;

	private DigitalSignature digitalSignature;

	private CredentialMac credentialMac;

	public Credential() {
	}

	public Credential(String domain, Identity identity, String sharedSecret) {
		this.domain = domain;
		this.identity = identity;
		this.sharedSecret = sharedSecret;
	}

	/**
	 * @return the credentialMac
	 */
	public CredentialMac getCredentialMac() {
		return credentialMac;
	}

	/**
	 * @param credentialMac
	 *            the credentialMac to set
	 */
	public void setCredentialMac(CredentialMac credentialMac) {
		this.credentialMac = credentialMac;
	}

	/**
	 * @return the digitalSignature
	 */
	public DigitalSignature getDigitalSignature() {
		return digitalSignature;
	}

	/**
	 * @param digitalSignature
	 *            the digitalSignature to set
	 */
	public void setDigitalSignature(DigitalSignature digitalSignature) {
		this.digitalSignature = digitalSignature;
	}

	/**
	 * @return the domain
	 */
	public String getDomain() {
		return domain;
	}

	/**
	 * @param domain
	 *            the domain to set
	 */
	public void setDomain(String domain) {
		this.domain = domain;
	}

	/**
	 * @return the identity
	 */
	public Identity getIdentity() {
		return identity;
	}

	/**
	 * @param identity
	 *            the identity to set
	 */
	public void setIdentity(Identity identity) {
		this.identity = identity;
	}

	/**
	 * @return the sharedSecret
	 */
	public Object getSharedSecret() {
		return sharedSecret;
	}

	/**
	 * @param sharedSecret
	 *            the sharedSecret to set
	 */
	public void setSharedSecret(Object sharedSecret) {
		this.sharedSecret = sharedSecret;
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
	 * <!ENTITY % cxml.authentication "SharedSecret | DigitalSignature |
	 * CredentialMac" > <!ELEMENT Credential (Identity,
	 * (%cxml.authentication;)?)> <!ATTLIST Credential domain %string; #REQUIRED
	 * type (marketplace) #IMPLIED >
	 *
	 * @param credentialObject
	 * @return
	 */
	public static Element buildCredentialElement(Credential credentialObject) {
		Element credentialElement = new Element("Credential");

		credentialObject.setAttributes(credentialElement, credentialObject);

		credentialObject.setContent(credentialElement, credentialObject);

		return credentialElement;
	}

	private void setAttributes(Element credentialElement,
			Credential credentialObject) {
		/*
		 * domain %string; #REQUIRED type (marketplace) #IMPLIED
		 */
		// List<Attribute> attributes = new ArrayList<Attribute>();
		List attributes = new ArrayList();

		attributes.add(new Attribute("domain", credentialObject.getDomain()));

		if (credentialObject.getType() != null) {
			attributes.add(new Attribute("type", credentialObject.getType()));
		}

		credentialElement.setAttributes(attributes);
	}

	private void setContent(Element credentialElement,
			Credential credentialObject) {
		/*
		 * (Identity, (%cxml.authentication;)?)
		 */
		credentialElement.addContent(Identity
				.buildIdentityElement(credentialObject.getIdentity()));

		if (credentialObject.getSharedSecret() != null) {
			credentialElement.addContent(new Element(
					Params.SHARED_SECRET_ELEMENT).setText(credentialObject
					.getSharedSecret().toString()));
		}
		// TODO
		// else { ...

	}
}
