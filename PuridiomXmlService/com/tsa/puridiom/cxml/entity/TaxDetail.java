/**
 *
 */
package com.tsa.puridiom.cxml.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jdom.Attribute;
import org.jdom.Element;

/**
 * @author Johnny Zapana
 *
 */
public class TaxDetail implements Serializable {

	private String purpose;

	private String category;

	private Double percentageRate;

	private Boolean isVatRecoverable;

	private Date taxPointDate;

	private Date paymentDate;

	private Boolean isTriangularTransaction;

	private Money taxableAmount;

	private Money taxAmount;

	private TaxLocation taxLocation;

	private Description description;

	private TriangularTransactionLawReference triangularTransactionLawReference;

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category
	 *            the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
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
	 * @return the isTriangularTransaction
	 */
	public Boolean getIsTriangularTransaction() {
		return isTriangularTransaction;
	}

	/**
	 * @param isTriangularTransaction
	 *            the isTriangularTransaction to set
	 */
	public void setIsTriangularTransaction(Boolean isTriangularTransaction) {
		this.isTriangularTransaction = isTriangularTransaction;
	}

	/**
	 * @return the isVatRecoverable
	 */
	public Boolean getIsVatRecoverable() {
		return isVatRecoverable;
	}

	/**
	 * @param isVatRecoverable
	 *            the isVatRecoverable to set
	 */
	public void setIsVatRecoverable(Boolean isVatRecoverable) {
		this.isVatRecoverable = isVatRecoverable;
	}

	/**
	 * @return the paymentDate
	 */
	public Date getPaymentDate() {
		return paymentDate;
	}

	/**
	 * @param paymentDate
	 *            the paymentDate to set
	 */
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	/**
	 * @return the percentageRate
	 */
	public Double getPercentageRate() {
		return percentageRate;
	}

	/**
	 * @param percentageRate
	 *            the percentageRate to set
	 */
	public void setPercentageRate(Double percentageRate) {
		this.percentageRate = percentageRate;
	}

	/**
	 * @return the purpose
	 */
	public String getPurpose() {
		return purpose;
	}

	/**
	 * @param purpose
	 *            the purpose to set
	 */
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	/**
	 * @return the taxableAmount
	 */
	public Money getTaxableAmount() {
		return taxableAmount;
	}

	/**
	 * @param taxableAmount
	 *            the taxableAmount to set
	 */
	public void setTaxableAmount(Money taxableAmount) {
		this.taxableAmount = taxableAmount;
	}

	/**
	 * @return the taxAmount
	 */
	public Money getTaxAmount() {
		return taxAmount;
	}

	/**
	 * @param taxAmount
	 *            the taxAmount to set
	 */
	public void setTaxAmount(Money taxAmount) {
		this.taxAmount = taxAmount;
	}

	/**
	 * @return the taxLocation
	 */
	public TaxLocation getTaxLocation() {
		return taxLocation;
	}

	/**
	 * @param taxLocation
	 *            the taxLocation to set
	 */
	public void setTaxLocation(TaxLocation taxLocation) {
		this.taxLocation = taxLocation;
	}

	/**
	 * @return the taxPointDate
	 */
	public Date getTaxPointDate() {
		return taxPointDate;
	}

	/**
	 * @param taxPointDate
	 *            the taxPointDate to set
	 */
	public void setTaxPointDate(Date taxPointDate) {
		this.taxPointDate = taxPointDate;
	}

	/**
	 * @return the triangularTransactionLawReference
	 */
	public TriangularTransactionLawReference getTriangularTransactionLawReference() {
		return triangularTransactionLawReference;
	}

	/**
	 * @param triangularTransactionLawReference
	 *            the triangularTransactionLawReference to set
	 */
	public void setTriangularTransactionLawReference(
			TriangularTransactionLawReference triangularTransactionLawReference) {
		this.triangularTransactionLawReference = triangularTransactionLawReference;
	}

	/**
	 * <!ELEMENT TaxDetail (TaxableAmount?, TaxAmount, TaxLocation?,
	 * Description?, TriangularTransactionLawReference?)> <!ATTLIST TaxDetail
	 * purpose %string; #IMPLIED category %string; #REQUIRED percentageRate %r8;
	 * #IMPLIED isVatRecoverable (yes) #IMPLIED taxPointDate %datetime.tz;
	 * #IMPLIED paymentDate %datetime.tz; #IMPLIED isTriangularTransaction (yes)
	 * #IMPLIED >
	 *
	 * @param taxDetailElement
	 * @param taxDetailObject
	 */
	public static Element buildTaxDetailElement(TaxDetail taxDetailObject) {
		Element taxDetailElement = new Element("TaxDetail");

		taxDetailObject.setAttributes(taxDetailElement, taxDetailObject);

		taxDetailObject.setContent(taxDetailElement, taxDetailObject);

		return taxDetailElement;
	}

	private void setAttributes(Element taxDetailElement,
			TaxDetail taxDetailObject) {
		/*
		 * purpose %string; #IMPLIED category %string; #REQUIRED percentageRate
		 * %r8; #IMPLIED isVatRecoverable (yes) #IMPLIED taxPointDate
		 * %datetime.tz; #IMPLIED paymentDate %datetime.tz; #IMPLIED
		 * isTriangularTransaction (yes) #IMPLIED
		 */
//		List<Attribute> attributes = new ArrayList<Attribute>();
		List attributes = new ArrayList();

		if (taxDetailObject.getPurpose() != null) {
			attributes.add(new Attribute("purpose", taxDetailObject
					.getPurpose()));
		}

		attributes
				.add(new Attribute("category", taxDetailObject.getCategory()));

		if (taxDetailObject.getPercentageRate() != null) {
			attributes.add(new Attribute("purpose", String
					.valueOf(taxDetailObject.getPercentageRate())));
		}

		if (taxDetailObject.getIsVatRecoverable() != null) {
			attributes.add(new Attribute("isVatRecoverable", String
					.valueOf(taxDetailObject.getIsVatRecoverable())));
		}

		if (taxDetailObject.getTaxPointDate() != null) {
			attributes.add(new Attribute("taxPointDate", String
					.valueOf(taxDetailObject.getTaxPointDate())));
		}

		if (taxDetailObject.getPaymentDate() != null) {
			attributes.add(new Attribute("paymentDate", String
					.valueOf(taxDetailObject.getPaymentDate())));
		}

		if (taxDetailObject.getIsTriangularTransaction() != null) {
			attributes.add(new Attribute("isTriangularTransaction", String
					.valueOf(taxDetailObject.getIsTriangularTransaction())));
		}

		taxDetailElement.setAttributes(attributes);
	}

	private void setContent(Element taxDetailElement, TaxDetail taxDetailObject) {
		/*
		 * (TaxableAmount?, TaxAmount, TaxLocation?, Description?,
		 * TriangularTransactionLawReference?)
		 */
		if (taxDetailObject.getTaxableAmount() != null) {
			Element taxableAmountElement = new Element(Params.TAXABLE_AMOUNT_ELEMENT);

			taxableAmountElement.addContent(Money
					.buildMoneyElement(taxDetailObject.getTaxableAmount()));

			taxDetailElement.addContent(taxableAmountElement);
		}

		Element taxAmountElement = new Element(Params.TAX_AMOUNT_ELEMENT);

		taxAmountElement.addContent(Money.buildMoneyElement(taxDetailObject
				.getTaxableAmount()));

		taxDetailElement.addContent(taxAmountElement);

		if (taxDetailObject.getTaxLocation() != null) {
			taxDetailElement.addContent(TaxLocation
					.buildTaxLocationElement(taxDetailObject.getTaxLocation()));
		}

		if (taxDetailObject.getDescription() != null) {
			taxDetailElement.addContent(Description
					.buildDescriptionElement(taxDetailObject.getDescription()));
		}

		if (taxDetailObject.getTriangularTransactionLawReference() != null) {
			taxDetailElement
					.addContent(TriangularTransactionLawReference
							.buildTriangularTransactionLawReferenceElement(taxDetailObject
									.getTriangularTransactionLawReference()));
		}

	}
}
