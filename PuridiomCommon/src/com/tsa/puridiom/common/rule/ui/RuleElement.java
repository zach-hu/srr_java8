/*
 * Created on Jun 1, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.common.rule.ui;

import java.util.ArrayList;
import java.util.List;

import org.jdom.Element;

import com.tsagate.foundation.utility.Log;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RuleElement
{

    private Element rule = null;
    private String fileName = "";
    private String alias = "";
    private String msg = "";
    private String severity = "";
    private String status = "";
    private int order = 0;
    private boolean enabled = false;

    private String getText(String child)
    {
        Log.debug(this, "getting text for: " + child);
        String text = "";

        Element tempEl = this.rule.getChild(child);
        if(tempEl != null)
        {
            text = tempEl.getTextTrim();
        }

        return text;
    }

    public Element getXmlRule()
    {
        this.rule = new Element("rule");
        List children = new ArrayList();

        Element _filename = new Element("filename");
        _filename.setText(this.getFileName());
        children.add(_filename);

        Element _alias = new Element("alias");
        _alias.setText(this.getAlias());
        children.add(_alias);

        Element _msg = new Element("msg");
        _msg.setText(this.getMsg());
        children.add(_msg);

        Element _severity = new Element("severity");
        _severity.setText(this.getSeverity());
        children.add(_severity);

        Element _order = new Element("order");
        _order.setText(String.valueOf(this.getOrder()));
        children.add(_order);

        Element _enabled = new Element("enabled");
        if(this.isEnabled())
        {
            _enabled.setText("Y");
        }
        else
        {
            _enabled.setText("N");
        }
        children.add(_enabled);
        this.rule.setChildren(children);

        return this.rule;
    }
    private void processRule()
    {

        this.setFileName(this.getText("filename"));
        this.setAlias(this.getText("alias"));
        this.setMsg(this.getText("msg"));
        this.setSeverity(this.getText("severity"));
        this.setOrder(this.getText("order"));
        this.setEnabled(this.getText("enabled"));
    }

    public RuleElement()
    {
        this.rule = null;
    }
    public RuleElement(Element ruleEl)
    {
        this.rule = ruleEl;
        this.processRule();
    }
    public String getAlias()
    {
        return alias;
    }
    public void setAlias(String alias)
    {
        this.alias = alias;
    }
    public boolean isEnabled()
    {
        return enabled;
    }
    public void setEnabled(String enabled)
    {
        if(enabled == null)
        {
            enabled = "N";
        }
        this.enabled = enabled.equalsIgnoreCase("Y");
    }
    public String getFileName()
    {
        return fileName;
    }
    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }
    public String getMsg()
    {
        return msg;
    }
    public void setMsg(String msg)
    {
        this.msg = msg;
    }
    public int getOrder()
    {
        return order;
    }
    public void setOrder(String order)
    {
        this.order = Integer.parseInt(order);
    }
    public String getSeverity()
    {
        return severity;
    }
    public void setSeverity(String severity)
    {
        this.severity = severity;
    }
    public String getStatus()
    {
        return status;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }
public String toString() {
    StringBuffer buffer = new StringBuffer();
    buffer.append("[RuleElement:");
    buffer.append(" rule: ");
    buffer.append(rule);
    buffer.append(" fileName: ");
    buffer.append(fileName);
    buffer.append(" alias: ");
    buffer.append(alias);
    buffer.append(" msg: ");
    buffer.append(msg);
    buffer.append(" severity: ");
    buffer.append(severity);
    buffer.append(" status: ");
    buffer.append(status);
    buffer.append(" order: ");
    buffer.append(order);
    buffer.append(" enabled: ");
    buffer.append(enabled);
    buffer.append("]");
    return buffer.toString();
}

    /**
     * Returns <code>true</code> if this <code>RuleElement</code> is the same as the o argument.
     *
     * @return <code>true</code> if this <code>RuleElement</code> is the same as the o argument.
     */
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (o.getClass() != getClass()) {
            return false;
        }
        RuleElement castedObj = (RuleElement) o;
        return (this.alias == null ? castedObj.getAlias() == null : this.alias.equals(castedObj.getAlias()));
    }
}
