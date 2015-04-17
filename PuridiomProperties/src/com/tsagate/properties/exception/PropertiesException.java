/*
 * Created on Jun 10, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsagate.properties.exception;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PropertiesException extends Exception
{
    private String propsFileName = "";

    public String buildMsg()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("Properties File [");
        sb.append(this.propsFileName);
        sb.append("does not Exist!");
        sb.append("\r\n");

        return sb.toString();
    }
    /**
     *
     */
    public PropertiesException()
    {
        this("Properties File doesn't Exist!");
    }

    public PropertiesException(String _propsFileName)
    {
        this.propsFileName = _propsFileName;
    }

    /* (non-Javadoc)
     * @see java.lang.Throwable#getMessage()
     */
    public String getMessage()
    {
        return this.buildMsg();
    }
}
