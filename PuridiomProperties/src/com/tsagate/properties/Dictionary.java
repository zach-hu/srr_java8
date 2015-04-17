/*
 * Created on Aug 4, 2004
 *
 * @author  * renzo
 * project: TsaProperties
 * package com.tsagate.properties;.ResourceLoader.java
 *
 */
package com.tsagate.properties;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class Dictionary
{
    public ResourceBundle bundle = null;
    private String bundleName = "properties.host";
    private String organizationId = "";

    public Dictionary()
    {
        this.setName("properties.host");
        this.setBundle(bundleName, null);
    }
    public Dictionary(String bundleName)
    {
        this.setBundle(bundleName, null);
    }

    public Dictionary(String bundle, String customer)
    {
        //Locale local = new Locale("en", "US", customer);
        if(customer == null || customer.length() < 1)
        {
            this.setBundle(bundle, null);
        }
        else
        {
	        Locale local = new Locale(customer);
	        this.setBundle(bundle, local);
        }
     }

    private void setBundle(String name, Locale customer)
    {
        if(name == null)
        {
            System.out.println("No bundle specified, using host!");
            name = this.bundleName;
        }
        try
        {
            if(name.indexOf("properties") < 0)
            {
                name = "properties." + name;
            }
	        if(customer == null)
	        {
	            this.bundle = ResourceBundle.getBundle(name);
	        }
	        else
	        {
	            this.bundle = ResourceBundle.getBundle(name, customer);
	        }
        }
        catch (Exception e)
        {
            System.out.println("Error obtaining properties from: " + name + " for customer: " + customer);
            e.printStackTrace();
        }
    }

    public String getProperty(String property, String defaultProp)
    {
        String propertyValue = "";
        try
        {
            propertyValue = this.bundle.getString(property);
        }
        catch (Exception e)
        {
            //System.out.println("Property: " + property.toUpperCase() + " from: " + this.bundleName + " was not found!");
            propertyValue = defaultProp;
        }
        return propertyValue;
    }
    public String getLabelProperty(String property, String defaultProp)
    {
    	return "";
    }

    protected String getLabelProperty(String property)
    {
    	return "";
    }

    public String getProperty(String property)
    {
       return this.getProperty(property, "");
    }
    
    public String getEncryptedProperty(String property, String defaultValue)
    {
    	String value =  this.getProperty(property, defaultValue);
    	
    	if (value.substring(0, 4).equalsIgnoreCase("enc(") && value.substring(value.length() - 1).equals(")")) {
    		String ciphertext = value.substring(4, value.length() - 1);
    		
    		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
    		encryptor.setPassword("tsapuridiom");
    		
    		value = encryptor.decrypt(ciphertext);
    	}
    	
    	return value;
    }

    public Map getPropertyMap()
	{
		HashMap propertyMap = new HashMap();
		Enumeration _enum = this.bundle.getKeys();

		while(_enum != null && _enum.hasMoreElements())
		{
			String key = (String) _enum.nextElement();
			String value = this.bundle.getString(key);

			propertyMap.put(key, value);
		}
		return propertyMap;
	}

    /**
     * @return Returns the bundleName.
     */
    public String getName()
    {
        return bundleName;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        String ret = this.getName();
        ret += this.getPropertyMap();
        return ret;
    }
    /**
     * @param bundleName The bundleName to set.
     */
    protected void setName(String bundleName)
    {
        this.bundleName = bundleName;
    }

    public boolean isVisible(String label)
    {
        // TODO Auto-generated method stub
        return true;
    }

    public boolean isReadOnly(String label)
    {
        // TODO Auto-generated method stub
        return true;
    }

    public boolean isRequired(String label)
    {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean isLink(String label)
    {
        // TODO Auto-generated method stub
        return true;
    }

    public String findIt()
    {
        return "";
    }
	public String getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

}
