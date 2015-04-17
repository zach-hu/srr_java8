/*
 * Created on Nov 18, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.reports.datasource;

import java.lang.reflect.Method;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import com.tsagate.foundation.utility.Log;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class EntityDataSource implements JRDataSource
{
    private Object entity;
    private boolean hasNext = true;
    private String organizationId = "puridiom";

    /**
     *
     */
    public EntityDataSource(Object fromEntity)
    {
        this.entity = fromEntity;
    }

    public EntityDataSource(Object fromEntity, String _organizationId)
    {
        this.entity = fromEntity;
        this.setOrganizationId(_organizationId);
    }

    /* (non-Javadoc)
     * @see net.sf.jasperreports.engine.JRDataSource#next()
     */
    public boolean next() throws JRException
    {
        boolean nextItem = true;
        if(this.hasNext)
        {
            this.hasNext = false;
        }
        else
        {
            nextItem = false;
        }
        return nextItem;
    }

    /* (non-Javadoc)
     * @see net.sf.jasperreports.engine.JRDataSource#getFieldValue(net.sf.jasperreports.engine.JRField)
     */
    public Object getFieldValue(JRField field) throws JRException
    {
        Object value = null;
        if(this.entity == null)
        {
            return null;
        }
        Method fld = null;
        try
        {
            String sfield = field.getName();
            String fieldName = sfield.substring(sfield.indexOf("_") +1);
            String first = fieldName.substring(0, 1);
            first = first.toUpperCase();
            fieldName = first + fieldName.substring(1);
//System.out.println("asking for: " + field.getName());
			Class c= this.entity.getClass();
			fld = c.getMethod("get" + fieldName, null);

            //fld = this.entity.getClass().getMethod("get" + fieldName, null);

        }
        catch(NoSuchMethodException nsme)
        {
        	nsme.printStackTrace();
            return value = null;

        }
        try
        {
            value = fld.invoke(this.entity, null);
            Log.debug(this, "getFieldValue, value:" + fld.getName() + "\tvalue: " + value);
        }
        catch (Exception e)
        {
            value = null;
            e.printStackTrace();
        }
        //value = HiltonUtility.getFormattedObject(value, "", getOrganizationId());

        return value;
    }

	/**
	 * @return the organizationId
	 */
	public String getOrganizationId()
	{
		return organizationId;
	}

	/**
	 * @param organizationId the organizationId to set
	 */
	public void setOrganizationId(String organizationId)
	{
		this.organizationId = organizationId;
	}
}
