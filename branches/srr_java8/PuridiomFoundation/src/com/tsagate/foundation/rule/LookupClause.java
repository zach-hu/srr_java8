/*
 * Created on Nov 5, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsagate.foundation.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class LookupClause extends Clause
{
    private String sql = "";
    private Map Arguments = null;
    private String source = "";
    private String fromObject = "";
    public String name = "lookup";
    private boolean unique = true;
    private boolean elementSource = false;

    public LookupClause()
    {
        this.Arguments = new HashMap();
    }
    public List getArguments()
    {
        return new ArrayList(this.Arguments.keySet());
    }
    public String getArgColName(String key)
    {
        String argument[] = this.getArgument(key);
        return argument[1];
    }

    public String getArgSource(String key)
    {
        String argument[] = this.getArgument(key);
        return argument[0];
    }

    public String[] getArgument(String Key)
    {
        return (String[])this.Arguments.get(Key);
    }

    public void setArgument(String key, String table, String column)
    {
        String argus[] = {table, column};
        this.Arguments.put(key, argus);
    }
    /**
     * @return Returns the sql.
     */
    public String getSql()
    {
        return sql;
    }
    /**
     * @param sql The sql to set.
     */
    public void setSql(String sql)
    {
        this.sql = sql;
    }
    /**
     * @return Returns the source.
     */
    public String getSource()
    {
        return source;
    }
    /**
     * @param source The source to set.
     */
    public void setSource(String source)
    {
        this.source = source;
    }
    public String getFromObject()
    {
        return fromObject;
    }
    public void setFromObject(String fromObject)
    {
        this.fromObject = fromObject;
    }

    /**
     * @return the unique
     */
    public boolean isUnique()
    {
    	return unique;
    }
    /**
     * @param unique the unique to set
     */
    public void setUnique(boolean unique)
    {
    	this.unique = unique;
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

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("[LookupClause:");
        buffer.append(" sql: ");
        buffer.append(sql);
        buffer.append(" Arguments: ");
        buffer.append(Arguments);
        buffer.append(" source: ");
        buffer.append(source);
        buffer.append(" fromObject: ");
        buffer.append(fromObject);
        buffer.append("]");
        return buffer.toString();
    }
}
