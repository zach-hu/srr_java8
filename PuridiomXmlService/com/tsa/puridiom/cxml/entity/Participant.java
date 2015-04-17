/**
 *
 */
package com.tsa.puridiom.cxml.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Johnny Zapana
 *
 */
public abstract class Participant {

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

}
