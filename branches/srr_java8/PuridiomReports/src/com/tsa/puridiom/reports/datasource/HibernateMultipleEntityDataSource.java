/*
 * Created on Jan 02, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.reports.datasource;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.tsagate.foundation.utility.Log;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 * @author nestor
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class HibernateMultipleEntityDataSource implements JRDataSource
{
      private Iterator iterator;
      private Object currentValue[];
      private List list;
      private String organizationId;

      public HibernateMultipleEntityDataSource(List _list)
      {
    	  if(_list != null)
    	  {
    		  this.iterator = _list.iterator();
    		  this.list = _list;
    	  }
    	  else
    	  {
    		  this.list = new ArrayList();
    		  this.iterator = this.list.iterator();
    	  }
      }

    public boolean next() throws JRException
    {
    	if(this.list != null)
  	  {
            currentValue = (Object[])( iterator.hasNext() ? iterator.next() : null );
            return (currentValue != null);
  	  }
    	else
    	{
    			return false;
    	}

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

	 public Object getFieldValue(JRField field) throws JRException
	  {
	        Object value = null;
	        try
	        {
	            String sfield = field.getName();
	            String fieldName = sfield.substring(sfield.indexOf("_") + 1);
	            String className = sfield.substring(0, sfield.indexOf("_"));
	            String first = fieldName.substring(0, 1);
	            first = first.toUpperCase();
	            fieldName = first + fieldName.substring(1);

	            for ( int i=0 ; i<currentValue.length ; i++)
	            {
		            Class c = currentValue[i].getClass();

		            if ( className.equals(c.getName().substring(c.getName().lastIndexOf(".")+1)) )
		            {
		            	Method fld = c.getMethod("get" + fieldName, null);
			            Log.debug(this, "getFieldValue, field: " + fld.getName());
			            value = fld.invoke(currentValue[i],null);
			            Log.debug(this, "getFieldValue, value:-" + value + "-");
			            return value;
		            }
	            }
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
    }
