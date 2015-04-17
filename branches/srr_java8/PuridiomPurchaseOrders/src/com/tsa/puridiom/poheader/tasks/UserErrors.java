/**
 * 
 * Created on Feb 13, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.poheader.tasks.UserErrors.java
 * 
 */
package com.tsa.puridiom.poheader.tasks;

import java.util.ArrayList;
import java.util.List;

public class UserErrors
{
	public List errorsList = null;

	public UserErrors()
	{
		this.errorsList = new ArrayList();
	}

	public void addError(String text, int severity)
	{
		TSAError error = new TSAError();
		error.setErrorText(text);
		error.setSeverity(severity);
		this.errorsList.add(error);
	}
	
	public List getErrors()
	{
		return this.errorsList;
	}
	
	public String getErrorText(int order)
	{
	    TSAError errorT = (TSAError)this.errorsList.get(order);
	    return errorT.getErrorText();
	}
	
	public int getErrorSeverity(int order)
	{
	    TSAError errorT = (TSAError)this.errorsList.get(order);
	    return errorT.getSeverity();
	}
	
	public int size()
    {
        if(this.errorsList != null)
        {
            return this.errorsList.size();
        }
        else
        {
            return 0;
        }
    }

	private class TSAError
	{
		public String errorText = "";
		public int severity  = 0;
		/**
		 * @return Returns the errorText.
		 */
		public String getErrorText()
		{
			return errorText;
		}

		/**
		 * @param errorText The errorText to set.
		 */
		public void setErrorText(String errorText)
		{
			this.errorText = errorText;
		}

		/**
		 * @return Returns the severity.
		 */
		public int getSeverity()
		{
			return severity;
		}

		/**
		 * @param severity The severity to set.
		 */
		public void setSeverity(int severity)
		{
			this.severity = severity;
		}

	}
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("[UserErrors:");
        buffer.append(" errorsList: ");
        buffer.append(errorsList);
        buffer.append("]");
        return buffer.toString();
    }
}
