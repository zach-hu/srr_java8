/**
 *
 */
package com.tsa.puridiom.cxml.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom.Element;

/**
 * @author Johnny Zapana
 *
 */
public class Tax implements Serializable {
	private Money money;

	private Description description;

	// private List<TaxDetail> taxDetail;
	private List taxDetail = new ArrayList();

	public Tax() {
	}

	public Tax(Money money, Description description) {
		this.money = money;
		this.description = description;
	}

	public Tax(Money money, Description description, List taxDetail) {
		this.money = money;
		this.description = description;
		this.taxDetail = taxDetail;
	}

	/**
	 * @return the description
	 */
	public Description getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(Description description) {
		this.description = description;
	}

	/**
	 * @return the money
	 */
	public Money getMoney() {
		return money;
	}

	/**
	 * @param money
	 *            the money to set
	 */
	public void setMoney(Money money) {
		this.money = money;
	}

	/**
	 * @return the taxDetail
	 */
	// public List<TaxDetail> getTaxDetail() {
	public List getTaxDetail() {
		return taxDetail;
	}

	/**
	 *
	 * @param taxDetail
	 * @return
	 */
	public List addTaxDetail(TaxDetail taxDetail) {
		this.taxDetail.add(taxDetail);

		return this.taxDetail;
	}

	/**
	 * @param taxDetail
	 *            the taxDetail to set
	 */
	// public void setTaxDetail(List<TaxDetail> taxDetail) {
	public void setTaxDetail(List taxDetail) {
		this.taxDetail = taxDetail;
	}

	/**
	 * <!ELEMENT Tax (Money, Description, TaxDetail*)>
	 *
	 * @param taxElement
	 * @param taxObject
	 */
	public static Element buildTaxElement(Tax taxObject) {
		Element taxElement = new Element("Tax");

		taxObject.setContent(taxElement, taxObject);

		return taxElement;
	}

	private void setContent(Element taxElement, Tax taxObject) {
		/*
		 * (Money, Description, TaxDetail*)
		 */
		taxElement.addContent(Money.buildMoneyElement(taxObject.getMoney()));

		taxElement.addContent(Description.buildDescriptionElement(taxObject
				.getDescription()));

		for (Iterator iter = taxObject.getTaxDetail().iterator(); iter
				.hasNext();) {
			taxElement.addContent(TaxDetail
					.buildTaxDetailElement((TaxDetail) iter.next()));
		}

	}
}
