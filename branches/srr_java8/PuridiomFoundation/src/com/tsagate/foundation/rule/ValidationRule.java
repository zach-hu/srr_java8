/*
 * Created on Dec 3, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsagate.foundation.rule;

import com.tsagate.properties.DictionaryManager;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ValidationRule
{
    private String alias = "";
    private String severity = "";
    private String message = "";
    private String messageLink = "";
    private String process = "";
    private String fieldName = "";
    private boolean result = false;
    private int order = 0;

    /**
     * @param oid
     * @param language
     * @return
     */
    public String getMessage(String oid, String language)
    {
    	return DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, this.message, this.message);
        //return message;
    }
    public String getMessage()
    {
        return message;
    }
    public void setMessage(String message)
    {
        this.message = message;
    }
    public int getOrder()
    {
        return order;
    }
    public void setOrder(int order)
    {
        this.order = order;
    }
    public boolean isResult()
    {
        return result;
    }
    public void setResult(boolean result)
    {
        this.result = result;
    }
    public String getSeverity()
    {
        return severity;
    }
    public void setSeverity(String severity)
    {
        this.severity = severity;
    }
    public String getMessageLink()
    {
        return messageLink;
    }
    public void setMessageLink(String messageLink)
    {
        this.messageLink = messageLink;
    }
    public String getProcess()
    {
        return process;
    }
    public void setProcess(String process)
    {
        this.process = process;
    }
    public String getFieldName()
    {
        return fieldName;
    }
    public void setFieldName(String fieldName)
    {
        this.fieldName = fieldName;
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("[ValidationRule:");
        buffer.append(" severity: ");
        buffer.append(severity);
        buffer.append(" message: ");
        buffer.append(message);
        buffer.append(" messageLink: ");
        buffer.append(messageLink);
        buffer.append(" process: ");
        buffer.append(process);
        buffer.append(" fieldName: ");
        buffer.append(fieldName);
        buffer.append(" result: ");
        buffer.append(result);
        buffer.append(" order: ");
        buffer.append(order);
        buffer.append("]");
        return buffer.toString();
    }
    public String getAlias()
    {
        return alias;
    }
    public void setAlias(String alias)
    {
        this.alias = alias;
    }
}
