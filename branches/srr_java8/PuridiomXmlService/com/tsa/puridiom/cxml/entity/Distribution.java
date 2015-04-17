/**
 *
 */
package com.tsa.puridiom.cxml.entity;

import java.io.Serializable;

import org.jdom.Element;

/**
 * @author Johnny Zapana
 *
 */
public class Distribution implements Serializable {
	private Accounting accounting;

	private Money charge;

	public Distribution() {
	}

	public Distribution(Accounting accounting, Money charge) {
		this.accounting = accounting;
		this.charge = charge;
	}

	/**
	 * @return the accounting
	 */
	public Accounting getAccounting() {
		return accounting;
	}

	/**
	 * @param accounting
	 *            the accounting to set
	 */
	public void setAccounting(Accounting accounting) {
		this.accounting = accounting;
	}

	/**
	 * @return the charge
	 */
	public Money getCharge() {
		return charge;
	}

	/**
	 * @param charge
	 *            the charge to set
	 */
	public void setCharge(Money charge) {
		this.charge = charge;
	}

	/**
	 * <!ELEMENT Distribution (Accounting, Charge)>
	 *
	 * @param distributionObject
	 * @return
	 */
	public static Element buildDistributionElement(
			Distribution distributionObject) {
		Element distributionElement = new Element("Distribution");

		distributionObject.setContent(distributionElement, distributionObject);

		return distributionElement;
	}

	private void setContent(Element distributionElement,
			Distribution distributionObject) {
		/*
		 * (Accounting, Charge)
		 */
		distributionElement.addContent(Accounting
				.buildAccountingElement(distributionObject.getAccounting()));

		Element chargeElement = new Element(Params.CHARGE_ELEMENT);

		chargeElement.addContent(Money.buildMoneyElement(distributionObject
				.getCharge()));

		distributionElement.addContent(chargeElement);
	}
}
