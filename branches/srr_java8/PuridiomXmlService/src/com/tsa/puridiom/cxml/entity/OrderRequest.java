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
 * @author JOHNNY
 *
 */
public class OrderRequest implements Serializable {

	private OrderRequestHeader orderRequestHeader;

	// private List<ItemOut> itemOut;
	private List itemOut = new ArrayList();

	/**
	 * @return the itemsOut
	 */
	public List getItemsOut() {
		return itemOut;
	}

	/**
	 *
	 * @param itemOut
	 * @return
	 */
	public List addItemOut(ItemOut itemOut) {
		this.itemOut.add(itemOut);

		return this.itemOut;
	}

	/**
	 * @param itemsOut
	 *            the itemsOut to set
	 */
	// public void setItemsOut(List<ItemOut> itemOut) {
	public void setItemsOut(List itemOut) {
		this.itemOut = itemOut;
	}

	/**
	 * @return the orderRequestHeader
	 */
	public OrderRequestHeader getOrderRequestHeader() {
		return orderRequestHeader;
	}

	/**
	 * @param orderRequestHeader
	 *            the orderRequestHeader to set
	 */
	public void setOrderRequestHeader(OrderRequestHeader orderRequestHeader) {
		this.orderRequestHeader = orderRequestHeader;
	}

	/**
	 * <!ELEMENT OrderRequest (OrderRequestHeader, ItemOut+)>
	 *
	 * @param orderRequestObject
	 * @return
	 */
	public static Element buildOrderRequestElement(
			OrderRequest orderRequestObject) {
		Element orderRequestElement = new Element("OrderRequest");

		orderRequestObject.setContent(orderRequestElement, orderRequestObject);

		return orderRequestElement;
	}

	private void setContent(Element orderRequestElement,
			OrderRequest orderRequestObject) {
		/*
		 * (OrderRequestHeader, ItemOut+)
		 */
		orderRequestElement.addContent(OrderRequestHeader
				.buildOrderRequestHeaderElement(orderRequestObject
						.getOrderRequestHeader()));

		Iterator iter = orderRequestObject.getItemsOut().iterator();

		do {
			orderRequestElement.addContent(ItemOut
					.buildItemOutElement((ItemOut) iter.next()));
		} while (iter.hasNext());

	}
}
