/*
 * Created on Nov 18, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.reports.datasource;

import java.io.IOException;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TiffDataSource implements JRDataSource
{
    private String imageName;
    private String organizationId = "puridiom";
    private TiffEntity tiff;
    private int index = -1;

    /**
     *
     */
    public TiffDataSource(String imageName)
    {
        this(imageName, "puridiom");
    }

    public TiffDataSource(String imageName, String _organizationId)
    {
        this.imageName = imageName;
        this.setOrganizationId(_organizationId);
        this.tiff = new TiffEntity(this.imageName);
    }

    /* (non-Javadoc)
     * @see net.sf.jasperreports.engine.JRDataSource#next()
     */
    public boolean next() throws JRException
    {
    	 index++;

         return (index < this.tiff.getPages());
    }

    /* (non-Javadoc)
     * @see net.sf.jasperreports.engine.JRDataSource#getFieldValue(net.sf.jasperreports.engine.JRField)
     */
    public Object getFieldValue(JRField field) throws JRException
    {
    	Object value = null;

        String fieldName = field.getName();
        if(fieldName.equalsIgnoreCase("image"))
        {
        	try
        	{
        		value = this.tiff.getImage(this.index);
			}
        	catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				value = null;
			}
        }
        else if(fieldName.equalsIgnoreCase("type"))
        {
        	int index = this.imageName.lastIndexOf(".");
        	value = this.imageName.toLowerCase().substring(index);
        }
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
