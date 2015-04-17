/*
 * Created on Aug 24, 2004
 */
package com.tsagate.foundation.rule;

import java.util.Map;

/**
 * @author Kelli
 */
public class ArgumentElement 
{
	private String name = "";
	private String source = "";
	private String value = null;
	
	public String getSource() 
	{
		return source;
	}
	public void setSource(String source) 
	{
		this.source = source; 
	}
	public String getName() 
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name = name; 
	}
	public String getValue() 
	{
		return this.value;
	}
	public void setValue(String value) 
	{
		this.value = value; 
	}
	public Object getValue(Map map)
	{
		Object argValue = null;
		
		try
		{
			String source = this.getSource();
			
			if (source.equalsIgnoreCase("incomingRequest"))
			{
			    argValue = map.get(this.getName());
			}
			else
			{
			    argValue = this.getValue();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return argValue;
	}
	
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("[ArgumentElement:");
        buffer.append(" name: ");
        buffer.append(name);
        buffer.append(" source: ");
        buffer.append(source);
        buffer.append(" value: ");
        buffer.append(value);
        buffer.append("]");
        return buffer.toString();
    }
}
