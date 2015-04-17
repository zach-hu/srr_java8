package com.tsa.puridiom.jasperreports;

import java.math.BigDecimal;
import java.util.Date;

import com.tsa.puridiom.entity.Address;
import com.tsagate.foundation.utility.Utility;

public class ShipToLineReport
{
	private Address addy;
	public String shipToCode;
	public BigDecimal quantity;
	public String attention;
	public Date requiredDate;

	public ShipToLineReport(Address shipToAddress)
	{
		this.addy = shipToAddress;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
	public String getShipToCode() {
		return shipToCode;
	}
	public void setShipToCode(String shipToCode) {
		this.shipToCode = shipToCode;
	}

	public String getCityStatePostal()
    {
        StringBuffer sb = new StringBuffer();
        if(!Utility.isEmpty(this.getCity()) )
        {
            sb.append(this.getCity());
        }
        if(!Utility.isEmpty(this.getState()) )
        {
            if(sb.length() > 0)
            {
                sb.append(", ");
            }
            sb.append(this.getState());
        }
        if(!Utility.isEmpty(this.getPostalCode()) )
        {
            if(sb.length() > 0)
            {
                sb.append(" ");
            }
            sb.append(this.getPostalCode());
        }
        return sb.toString();
    }

	public String getAddressLine1() {
		if(this.getAddy() == null) {		return "";		}
		return this.getAddy().getAddressLine1();
	}

	public String getAddressLine2() {
		if(this.getAddy() == null) {		return "";		}
		return this.getAddy().getAddressLine2();
	}

	public String getAddressLine3() {
		if(this.getAddy() == null) {		return "";		}
		return this.getAddy().getAddressLine3();
	}

	public String getAddressLine4() {
		if(this.getAddy() == null) {		return "";		}
		return this.getAddy().getAddressLine4();
	}

	public Address getAddy() {
		return addy;
	}

	public void setAddy(Address addy) {
		this.addy = addy;
	}

	public String getCity() {
		if(this.getAddy() == null) {		return "";		}
		return this.getAddy().getCity();
	}

	public String getCountry() {
		if(this.getAddy() == null) {		return "";		}
		return this.getAddy().getCountry();
	}

	public String getPostalCode() {
		if(this.getAddy() == null) {		return "";		}
		return this.getAddy().getPostalCode();
	}

	public String getState() {
		if(this.getAddy() == null) {		return "";		}
		return this.getAddy().getState();
	}

	public String getAttention() {
		return attention;
	}

	public void setAttention(String attention) {
		this.attention = attention;
	}

	public String getShipTo()
	{
		StringBuffer sb = new StringBuffer();
		sb.setLength(0);
		sb = this.appendAddressLine(sb, this.getAddressLine1());
		sb = this.appendAddressLine(sb, this.getAddressLine2());
		sb = this.appendAddressLine(sb, this.getAddressLine3());
		sb = this.appendAddressLine(sb, this.getCityStatePostal());
		if(!this.getCountry().equalsIgnoreCase("USA"))
		{
			sb = this.appendAddressLine(sb, this.getCountry());
		}

		return sb.toString();
	}

	public StringBuffer appendAddressLine(StringBuffer sb, String lineToAppend)
	{
		if(lineToAppend.length() > 0)
		{
			if(sb.length() > 0)
			{
				sb.append("\n");
			}
			sb.append(lineToAppend);
		}

		return sb;
	}

	public Date getRequiredDate() {
		return requiredDate;
	}

	public void setRequiredDate(Date requiredDate) {
		this.requiredDate = requiredDate;
	}
}
