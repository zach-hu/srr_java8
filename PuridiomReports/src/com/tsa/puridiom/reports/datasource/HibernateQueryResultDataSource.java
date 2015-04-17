/*
 * Created on Nov 17, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.reports.datasource;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

import com.tsa.puridiom.entity.InspectionHeader;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.utility.Log;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JRRewindableDataSource;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class HibernateQueryResultDataSource implements JRRewindableDataSource
{
      private Iterator iterator;
      private Object currentValue;
      private List list;
      private String organizationId;

      public HibernateQueryResultDataSource(List _list)
      {
        this.iterator = _list.iterator();
        this.list = _list;
      }

    public boolean next() throws JRException
    {
            currentValue = iterator.hasNext() ? iterator.next() : null;
            return (currentValue != null);
    }

    public int getSize()
    {
        if (this.list != null)
        {
            return this.list.size();
        }
        else
        {
            return 0;
        }
    }

    public HibernateQueryResultDataSource getDS(String type)
    {
        HibernateQueryResultDataSource ds = null;
        if(type.equalsIgnoreCase("accounts"))
        {
            RequisitionLine reqLine = (RequisitionLine)this.currentValue;
            if(reqLine != null)
            {
                ds = new HibernateQueryResultDataSource(reqLine.getAccountList());
            }
        }
        else if(type.equals("shipto"))
        {
        	if (this.currentValue instanceof PoLine)
        	{
				PoLine poLine = (PoLine) this.currentValue;
				if(poLine != null)
				{
					ds = new HibernateQueryResultDataSource(poLine.getShipToList());
				}
			}
        }
        else if(type.equals("inspection"))
        {
        	RequisitionLine reqLine = (RequisitionLine)this.currentValue;
        	 if(reqLine != null)
             {
                 ds = new HibernateQueryResultDataSource(reqLine.getInspectionList());
             }
        }
        else if(type.equals("inspectionline"))
        {
        	InspectionHeader inspHeader = (InspectionHeader)this.currentValue;
        	if(inspHeader != null)
        	{
        		ds = new HibernateQueryResultDataSource(inspHeader.getInspectionLineList());
        	}
        }
        return ds;
    }

	  /* (non-Javadoc)
	   * @see net.sf.jasperreports.engine.JRDataSource#getFieldValue(net.sf.jasperreports.engine.JRField)
	   */
	  public Object getFieldValue(JRField field) throws JRException
	  {
	        Object value = null;
	        try
	        {
	            String sfield = field.getName();
	            String fieldName = sfield.substring(sfield.indexOf("_") + 1);
	            String first = fieldName.substring(0, 1);
	            first = first.toUpperCase();
	            fieldName = first + fieldName.substring(1);

	            Method fld = currentValue.getClass().getMethod("get" + fieldName, null);
	            Log.debug(this, "getFieldValue, field: " + fld.getName());
	            value = fld.invoke(currentValue,null);
	            Log.debug(this, "getFieldValue, value:-" + value + "-");
	        }
	        catch (IllegalAccessException iae)
	        {
	            value = null;
	            iae.printStackTrace();
	        }
	        catch(InvocationTargetException ite)
	        {
	            value = null;
	            ite.printStackTrace();
	        }
	        catch(NoSuchMethodException nsme)
	        {
	            value = null;
	            nsme.printStackTrace();
	        }
	        return value;
	      }
	    /**
	     * @return Returns the organizationId.
	     */
	    public String getOrganizationId()
	    {
	        return organizationId;
	    }
	    /**
	     * @param organizationId The organizationId to set.
	     */
	    public void setOrganizationId(String organizationId)
	    {
	        this.organizationId = organizationId;
	    }

		public List getList()
		{
			return list;
		}

		@Override
		public void moveFirst() throws JRException {
			// TODO Auto-generated method stub

		}
    }
