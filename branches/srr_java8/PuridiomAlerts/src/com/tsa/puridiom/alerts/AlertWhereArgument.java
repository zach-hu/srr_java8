package com.tsa.puridiom.alerts;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.hibernate.Hibernate;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Utility;

public class AlertWhereArgument
{
	private String organizationId;
	private Object type = "";
	private Object value = "";
	private int offset = 0;

	public int getOffset()
	{
		return offset;
	}
	public void setOffset(String offset)
	{
		if(!Utility.isEmpty(offset))
		{
			this.offset = Integer.parseInt(offset);
		}
	}
	public Object getType()
	{
		return type;
	}
	public void setType(String type)
	{
		if(!Utility.isEmpty(type))
		{
			if(type.equalsIgnoreCase("date"))
			{
				this.type = Hibernate.DATE;
			}
			else if(type.equalsIgnoreCase("number"))
			{
				this.type = Hibernate.BIG_DECIMAL;
			}
			else
			{
				this.type = Hibernate.STRING;
			}
		}
	}
	public Object getValue()
	{
		Object ret = null;
		if(!Utility.isObjectEmpty(this.value))
		{
			if(this.getType().equals(Hibernate.DATE))
			{
				if(value.equals("today"))
				{
					Calendar today = Calendar.getInstance();

					if(this.getOffset() != 0)
					{
						today.add(Calendar.DATE, getOffset());
					}
					ret = Dates.getDate(HiltonUtility.getFormattedDate(today.getTime(), this.getOrganizationId()));
				}
			}
		}
		return ret;
	}

	public static void main(String[] args)
	{
		System.err.println("start");
		AlertWhereArgument awa = new AlertWhereArgument();
		awa.setOffset("-10");
		awa.setType("date");
		awa.setValue("today");
		SimpleDateFormat QUEUE_DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd");

		System.out.println("values is: " + QUEUE_DATE_FORMAT.format(((Calendar)awa.getValue()).getTime()));

	}
	public void setValue(Object value)
	{
		this.value = value;
	}
	public String toString() {
			StringBuffer buffer = new StringBuffer();
			buffer.append("[AlertWhereArgument:");
			buffer.append(" type: ");
			buffer.append(type);
			buffer.append(" value: ");
			buffer.append(value);
			buffer.append(" offset: ");
			buffer.append(offset);
			buffer.append("]");
			return buffer.toString();
		}
	public String getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

}
