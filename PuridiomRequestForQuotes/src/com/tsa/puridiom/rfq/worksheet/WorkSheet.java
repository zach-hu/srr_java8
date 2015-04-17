package com.tsa.puridiom.rfq.worksheet;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.RfqBid;
import com.tsa.puridiom.entity.RfqLine;

public class WorkSheet
{
	private BigDecimal line;
	private BigDecimal quantity;
	private String description;
	private String itemNumber;
	private String vendor;
	private String umCode;
	private BigDecimal umFactor;
	private BigDecimal unitPriceVend1;
	private BigDecimal unitPriceVend2;
	private BigDecimal unitPriceVend3;
	private BigDecimal unitPriceVend4;
	private BigDecimal unitPriceVend5;
	private RfqLine rfqLine;
	private String bidCodeDescription1;
	private String bidCodeDescription2;
	private String bidCodeDescription3;
	private String bidCodeDescription4;
	private String bidCodeDescription5;

	public WorkSheet(RfqLine rfqLine)
	{
		this.setDescription(rfqLine.getDescription());
		this.setLine(rfqLine.getRfqLine());
		this.setUmCode(rfqLine.getUmCode());
		this.setUmFactor(rfqLine.getUmFactor());
		this.setQuantity(rfqLine.getQuantity());
		this.setRfqLine(rfqLine);
	}

	private void setBids(int vendIndex, RfqBid bid)
	{
		BigDecimal bidPrice = bid.getUnitPrice();

		String bidCodeDescription = this.getBidDescription(bid.getBidCode());

		switch (vendIndex)
		{
			case 0:
				this.setUnitPriceVend1(bidPrice);
				this.setBidCodeDescription1(bidCodeDescription);
				break;
			case 1:
				this.setUnitPriceVend2(bidPrice);
				this.setBidCodeDescription2(bidCodeDescription);
				break;
			case 2:
				this.setUnitPriceVend3(bidPrice);
				this.setBidCodeDescription3(bidCodeDescription);
				break;
			case 3:
				this.setUnitPriceVend4(bidPrice);
				this.setBidCodeDescription4(bidCodeDescription);
				break;
			case 4:
				this.setUnitPriceVend5(bidPrice);
				this.setBidCodeDescription5(bidCodeDescription);
				break;
			default:
				break;
		}
	}

	public WorkSheet(RfqLine rfqLine, List bids)
	{
		this(rfqLine);
		if (bids != null)
		{
			for (int ib = 0; ib < bids.size(); ib++)
			{
				RfqBid bid = (RfqBid) bids.get(ib);
				this.setBids(ib, bid);
			}
		}
	}

	public WorkSheet(RfqLine rfqLine, RfqBid bid1, RfqBid bid2, RfqBid bid3, RfqBid bid4, RfqBid bid5)
	{
		this(rfqLine);
		this.setUnitPriceVend1(bid1.getUnitPrice());
		this.setUnitPriceVend2(bid2.getUnitPrice());
		this.setUnitPriceVend3(bid3.getUnitPrice());
		this.setUnitPriceVend4(bid4.getUnitPrice());
		this.setUnitPriceVend5(bid5.getUnitPrice());
	}

	private String getBidDescription(String bidCode)
	{
		String bidCodeDescription = "";

		if (!HiltonUtility.isEmpty(bidCode))
		{
			if (bidCode.equalsIgnoreCase("NC"))
			{
				bidCodeDescription = "No Charge";
			}
			else if (bidCode.equalsIgnoreCase("NSP"))
			{
				bidCodeDescription = "Not Separately Priced";
			}
			else if (bidCode.equalsIgnoreCase("SN"))
			{
				bidCodeDescription = "See Notes";
			}
			else if (bidCode.equalsIgnoreCase("NB"))
			{
				bidCodeDescription = "No Bid";
			}
			else if (bidCode.equalsIgnoreCase("NE"))
			{
				bidCodeDescription = "None Entered";
			}
		}

		return bidCodeDescription;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getLine() {
		return line;
	}
	public void setLine(BigDecimal line) {
		this.line = line;
	}
	public BigDecimal getQuantity() {
		return quantity;
	}
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	 public boolean equals(Object other) {
	        if ( !(other instanceof WorkSheet) ) return false;
	        WorkSheet castOther = (WorkSheet) other;
	        return new EqualsBuilder()
	            .append(this.getLine(), castOther.getLine())
	            .isEquals();
	    }

	    public int hashCode() {
	        return new HashCodeBuilder()
	            .append(getLine())
	            .toHashCode();
	    }
		public String getItemNumber() {
			return itemNumber;
		}
		public void setItemNumber(String itemNumber) {
			this.itemNumber = itemNumber;
		}
		public BigDecimal getUnitPriceVend1() {
			return unitPriceVend1;
		}
		public void setUnitPriceVend1(BigDecimal unitPriceVend1) {
			this.unitPriceVend1 = unitPriceVend1;
		}
		public BigDecimal getUnitPriceVend2() {
			return unitPriceVend2;
		}
		public void setUnitPriceVend2(BigDecimal unitPriceVend2) {
			this.unitPriceVend2 = unitPriceVend2;
		}
		public BigDecimal getUnitPriceVend3() {
			return unitPriceVend3;
		}
		public void setUnitPriceVend3(BigDecimal unitPriceVend3) {
			this.unitPriceVend3 = unitPriceVend3;
		}
		public BigDecimal getUnitPriceVend4() {
			return unitPriceVend4;
		}
		public void setUnitPriceVend4(BigDecimal unitPriceVend4) {
			this.unitPriceVend4 = unitPriceVend4;
		}
		public BigDecimal getUnitPriceVend5() {
			return unitPriceVend5;
		}
		public void setUnitPriceVend5(BigDecimal unitPriceVend5) {
			this.unitPriceVend5 = unitPriceVend5;
		}
		public String getVendor() {
			return vendor;
		}
		public void setVendor(String vendor) {
			this.vendor = vendor;
		}
		public RfqLine getRfqLine() {
			return this.rfqLine;
		}
		public void setRfqLine(RfqLine rfqLine) {
			this.rfqLine = rfqLine;
		}

		public String toString() {
				StringBuffer buffer = new StringBuffer();
				buffer.append("[WorkSheet:");
				buffer.append(" line: ");
				buffer.append(line);
				buffer.append(" quantity: ");
				buffer.append(quantity);
				buffer.append(" description: ");
				buffer.append(description);
				buffer.append(" itemNumber: ");
				buffer.append(itemNumber);
				buffer.append(" vendor: ");
				buffer.append(vendor);
				buffer.append(" unitPriceVend1: ");
				buffer.append(unitPriceVend1);
				buffer.append(" unitPriceVend2: ");
				buffer.append(unitPriceVend2);
				buffer.append(" unitPriceVend3: ");
				buffer.append(unitPriceVend3);
				buffer.append(" unitPriceVend4: ");
				buffer.append(unitPriceVend4);
				buffer.append(" unitPriceVend5: ");
				buffer.append(unitPriceVend5);
				buffer.append("]");
				return buffer.toString();
			}

		public String getUmCode() {
			return umCode;
		}

		public void setUmCode(String umCode) {
			this.umCode = umCode;
		}

		public BigDecimal getUmFactor() {
			return umFactor;
		}

		public void setUmFactor(BigDecimal umFactor) {
			this.umFactor = umFactor;
		}

		/**
		 * @return the bidCodeDescription1
		 */
		public String getBidCodeDescription1()
		{
			return bidCodeDescription1;
		}

		/**
		 * @param bidCodeDescription1 the bidCodeDescription1 to set
		 */
		public void setBidCodeDescription1(String bidCodeDescription1)
		{
			this.bidCodeDescription1 = bidCodeDescription1;
		}

		/**
		 * @return the bidCodeDescription2
		 */
		public String getBidCodeDescription2()
		{
			return bidCodeDescription2;
		}

		/**
		 * @param bidCodeDescription2 the bidCodeDescription2 to set
		 */
		public void setBidCodeDescription2(String bidCodeDescription2)
		{
			this.bidCodeDescription2 = bidCodeDescription2;
		}

		/**
		 * @return the bidCodeDescription3
		 */
		public String getBidCodeDescription3()
		{
			return bidCodeDescription3;
		}

		/**
		 * @param bidCodeDescription3 the bidCodeDescription3 to set
		 */
		public void setBidCodeDescription3(String bidCodeDescription3)
		{
			this.bidCodeDescription3 = bidCodeDescription3;
		}

		/**
		 * @return the bidCodeDescription4
		 */
		public String getBidCodeDescription4()
		{
			return bidCodeDescription4;
		}

		/**
		 * @param bidCodeDescription4 the bidCodeDescription4 to set
		 */
		public void setBidCodeDescription4(String bidCodeDescription4)
		{
			this.bidCodeDescription4 = bidCodeDescription4;
		}

		/**
		 * @return the bidCodeDescription5
		 */
		public String getBidCodeDescription5()
		{
			return bidCodeDescription5;
		}

		/**
		 * @param bidCodeDescription5 the bidCodeDescription5 to set
		 */
		public void setBidCodeDescription5(String bidCodeDescription5)
		{
			this.bidCodeDescription5 = bidCodeDescription5;
		}

}
