/*
 * Created on Mar 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.requisition.tasks;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MiniItem
{
    private String itemNumber;
    private int quantity;
    private String Description;
    private String reason;
    private String catalog;

    public String getDescription()
    {
        return Description;
    }
    public void setDescription(String description)
    {
        Description = description;
    }
    public String getItemNumber()
    {
        return itemNumber;
    }
    public void setItemNumber(String itemNumber)
    {
        this.itemNumber = itemNumber;
    }
    public int getQuantity()
    {
        return quantity;
    }
    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getCatalog() {
		return catalog;
	}
	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}
	public String toString() {
			StringBuffer buffer = new StringBuffer();
			buffer.append("[MiniItem:");
			buffer.append(" itemNumber: ");
			buffer.append(itemNumber);
			buffer.append(" quantity: ");
			buffer.append(quantity);
			buffer.append(" Description: ");
			buffer.append(Description);
			buffer.append(" reason: ");
			buffer.append(reason);
			buffer.append(" catalog: ");
			buffer.append(catalog);
			buffer.append("]");
			return buffer.toString();
		}
}
