/*
 * Created on Nov 26, 2003
 */
package com.tsagate.foundation.rule;

import java.util.ArrayList;
import java.util.List;

import com.tsagate.foundation.utility.Dates;

/**
 * @author Kelli
 */
public class ClauseElement 
{
	private String type = "";
	private String name = "";
	private String source = "";
	private String object = "";
	private String ruleName = null;
	private Object value = null;
	private String methodFromObject = "";
	private List arguments = new ArrayList();
	private boolean elementSource = false;
	private boolean matchAll = true;
	
	public void addArgument(Object arg)
	{
		this.arguments.add(arg);
	}
	public List getArguments()
	{
		return this.arguments;
	}
	public String getSource() 
	{
		return source;
	}
	public void setSource(String source) 
	{
		this.source = source; 
	}
	public String getType() 
	{
		return type;
	}
	public void setType(String type) 
	{
		this.type = type; 
	}
	public String getName() 
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name = name; 
	}
	public String getObject() 
	{
		return object;
	}
	public void setObject(String object) 
	{
		this.object = object; 
	}
	
	/**
	 * @return the ruleName
	 */
	public String getRuleName()
	{
		return ruleName;
	}
	
	/**
	 * @param ruleName the ruleName to set
	 */
	public void setRuleName(String ruleName)
	{
		this.ruleName = ruleName;
	}
	
	public Object getValue()
	{
		Object object = null;
		
		if (value != null)
		{
			if (this.getType().equalsIgnoreCase("DateCompare"))
			{
				object = Dates.getDate(String.valueOf(value));
			}
            else if (this.getType().equalsIgnoreCase("DateTimeCompare"))
            {
                object = Dates.getDateTime(String.valueOf(value));
            }
			else if (this.getType().equalsIgnoreCase("DoubleCompare") || this.getType().equalsIgnoreCase("NumberCompare"))
			{
				object = new Double(String.valueOf(value));
			}
			else if (this.getType().equalsIgnoreCase("IntegerCompare"))
			{
				object = new Integer(String.valueOf(value));
			}
			else if (this.getType().equalsIgnoreCase("BooleanCompare"))
			{
				object = new Boolean(String.valueOf(value));
			}
			else
			{//default case a string
				object = String.valueOf(value);
			}
		}
		return object;
	}
	public void setValue(Object value) 
	{
		this.value = value; 
	}
	
    /**
	 * @return the elementSource
	 */
	public boolean isElementSource()
	{
		return elementSource;
	}
	
	/**
	 * @param elementSource the elementSource to set
	 */
	public void setElementSource(boolean elementSource)
	{
		this.elementSource = elementSource;
	}
	
	/* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("type: " + this.type);
        sb.append(" source: " + this.source);
        sb.append(" object: " + this.object);
        sb.append(" name: " + this.name);
        sb.append(" value: " + this.value);
        sb.append(" args: " + this.arguments.toString());
        return sb.toString();
    }
}
