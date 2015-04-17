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
public class ItemID implements Serializable {

	private String supplierPartID;

	private Object supplierPartAuxiliaryID;

	public ItemID() {
	}

	public ItemID(String supplierPartID) {
		this.supplierPartID = supplierPartID;
	}

	public ItemID(String supplierPartID, Object supplierPartAuxiliaryID) {
		this.supplierPartID = supplierPartID;
		this.supplierPartAuxiliaryID = supplierPartAuxiliaryID;
	}

	/**
	 * @return the supplierPartAuxiliaryID
	 */
	public Object getSupplierPartAuxiliaryID() {
		return supplierPartAuxiliaryID;
	}

	/**
	 * @param supplierPartAuxiliaryID
	 *            the supplierPartAuxiliaryID to set
	 */
	public void setSupplierPartAuxiliaryID(Object supplierPartAuxiliaryID) {
		this.supplierPartAuxiliaryID = supplierPartAuxiliaryID;
	}

	/**
	 * @return the supplierPartID
	 */
	public String getSupplierPartID() {
		return supplierPartID;
	}

	/**
	 * @param supplierPartID
	 *            the supplierPartID to set
	 */
	public void setSupplierPartID(String supplierPartID) {
		this.supplierPartID = supplierPartID;
	}

	/**
	 * <!ELEMENT ItemID (SupplierPartID, SupplierPartAuxiliaryID?)>
	 */
	public static Element buildItemIDElement(ItemID itemIDObject) {
		Element itemIDElement = new Element("ItemId");

		itemIDObject.setContent(itemIDElement, itemIDObject);

		return itemIDElement;
	}

	private void setContent(Element itemIDElement, ItemID itemIDObject) {
		/*
		 * (SupplierPartID, SupplierPartAuxiliaryID?)
		 */

		itemIDElement.addContent(new Element(Params.SUPPLIER_PART_ID_ELEMENT)
				.setText(itemIDObject.getSupplierPartID()));

		if (itemIDObject.getSupplierPartAuxiliaryID() != null) {
			itemIDElement.addContent(new Element(
					Params.SUPPLIER_PART_AUXILIARY_ID_ELEMENT)
					.setText(itemIDObject.getSupplierPartAuxiliaryID()
							.toString()));
		}
	}

}
