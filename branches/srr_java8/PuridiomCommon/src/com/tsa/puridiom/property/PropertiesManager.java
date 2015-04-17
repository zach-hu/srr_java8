/*
 * Created on Sep 30, 2003
 */
package com.tsa.puridiom.property;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.utility.BlackBox;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.Property;
import com.tsa.puridiom.entity.PropertyPK;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.utility.Utility;

/**
 * @author renzo
 */
public class PropertiesManager
{
	protected Map sections;
	protected List propertiesToAdd;
	protected List propertiesToUpdate;
	private static String organizationId;

	private static HashMap properties = new HashMap(); //stores PropertiesManager object for each oid

	public static PropertiesManager getInstance(String o) //o == organizationId
	{
		if (PropertiesManager.properties.get(o) == null)
		{
			PropertiesManager.properties.put(o, new PropertiesManager(o));
		}
		return (PropertiesManager) PropertiesManager.properties.get(o);
	}

	private PropertiesManager(String o)
	{
		this.propertiesToAdd = new ArrayList();
		this.propertiesToUpdate = new ArrayList();
		this.load(o);
	}
	
	public static boolean isValidOrganizationId(String organizationId) {
		boolean valid = false;
		
		if (PropertiesManager.properties.containsKey(organizationId)) {
			valid = true;
		}
		
		return valid;
	}

	public void load(String o)
	{
		try
		{
			if (!HiltonUtility.isEmpty(o)) 
			{
		    
				Map incomingRequest = new HashMap() ;
				incomingRequest.put("organizationId", o);

				PuridiomProcessLoader processLoader = new PuridiomProcessLoader() ;
				PuridiomProcess process = processLoader.loadProcess("property-retrieve-all.xml");

				process.executeProcess(incomingRequest);
				if (process.getStatus() == Status.SUCCEEDED)
				{
					this.sections = new HashMap();
					ArrayList qry = (ArrayList)incomingRequest.get("PropertyRetrieveAll");

					for (Iterator it = qry.iterator(); it.hasNext(); )
					{
						this.buildSection(it.next());
					}
					
					this.setOrganizationId(o);
				}
		    }
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private void buildSection(Object next)
	{
		Property prop = (Property) next ;
		PropertyPK pk = prop.getComp_id();

		Map propsTemp = null;
		String	section = pk.getSection();
		String	property = pk.getProperty();

		if (section != null)
		{
			section = section.toUpperCase();
		}
		if (property != null)
		{
			property = property.toUpperCase();
		}

		if(this.sections.containsKey(section))
		{
			propsTemp = (HashMap)this.sections.get(section);
		}
		else
		{
			propsTemp = new HashMap();
		}
		propsTemp.put(property, prop.getValue());
		this.sections.put(section, propsTemp);
	}

	public String getProperty(String section, String property, String def)
	{
		String value = def;
		if (this.sections != null) {
			Map sectionMap = (Map)this.sections.get(section.toUpperCase());
			if(sectionMap != null)
			{
				value = (String)sectionMap.get(property.toUpperCase());
				if(Utility.isEmpty(value))
				{
					value = def;
				}
			}
		}
		
		return value;
	}

	public Map getSection (String section)
	{
		Map sectionMap = (Map) this.sections.get(section.toUpperCase());
		if (sectionMap == null)
		{
			sectionMap =  new HashMap();
		}

		return sectionMap;
	}

	public void setProperty(String section, String property, String value)
	{
		Property propertyEntity = new Property();
		PropertyPK comp_id = new PropertyPK();

		if (section != null)
		{
		    section = section.toUpperCase();
		}
		if (property != null)
		{
		    property = property.toUpperCase();
		}

		comp_id.setSection(section);
		comp_id.setProperty(property);
		propertyEntity.setComp_id(comp_id);
		propertyEntity.setValue(value);

		Map sectionMap = (Map) this.sections.get(section);
		if (sectionMap != null && sectionMap.containsKey(property))
		{
			this.propertiesToUpdate.add(propertyEntity);
		}
		else
		{
			this.propertiesToAdd.add(propertyEntity);
		}
		this.buildSection(propertyEntity);
	}

	public boolean isModuleActive(String moduleId)
	{
		if (HiltonUtility.isEmpty(this.getOrganizationId())) {
			return false;
		}
		boolean active = false;
		String	flag = this.getProperty("MODULES", moduleId, null);

		if (flag != null && flag.equalsIgnoreCase("Y"))
		{
			if (moduleId.equals("REQUISITIONS") && this.getOrganizationId().startsWith("PX")) {
				//Retrieve count of REQUISITIONER group roles assigned.
				//	If none are assigned then the Requisitions should not be active.
				int requisitionerCount = 0;
				
				try {
					HashMap incomingRequest = new HashMap();
					incomingRequest.put("organizationId", this.getOrganizationId());
					incomingRequest.put("UserGroupRel_groupId", "REQUISITIONER");

					PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
		            PuridiomProcess process = processLoader.loadProcess("usergrouprel-retrieve-count-by-groupid.xml");
		            
		            process.executeProcess(incomingRequest);

					requisitionerCount = ((Long) incomingRequest.get("userGroupRelCount")).intValue();
				} catch (Exception e) {
					requisitionerCount = 0;
				}
				if (requisitionerCount > 0) {
					active = true;
				}
			} else {
				active = true;
			}
		}

		return active;
	}

    public Integer getLicensedUsers()
    {
        Integer licensedUsers = new Integer(0);
        String encLicUsers = this.getProperty("APPLICATION", "CUL", "0");
        String decLicUsers = "0";

        if (encLicUsers != null && !encLicUsers.equalsIgnoreCase("0"))
        {
            decLicUsers = BlackBox.getDecrypt(encLicUsers);

            int ics = decLicUsers.indexOf("CONCURRENT");
            decLicUsers = decLicUsers.substring(0, ics);
        }
        try {
            licensedUsers = new Integer(decLicUsers);
        } catch (Exception e) {
        }

        return licensedUsers;
    }
    public List getLanguagesAvailable()
    {
        String  languagesLoaded = this.getProperty("MISC","LANGUAGES","");
        List  languages = new ArrayList();

        if (languagesLoaded == null) { languagesLoaded = ""; }

        int index = languagesLoaded.indexOf(",");

        while (index >= 0) {
            languages.add(languagesLoaded.substring(0, index));
            languagesLoaded = languagesLoaded.substring(index + 1);
            index = languagesLoaded.indexOf(",");
        }
        if (!Utility.isEmpty(languagesLoaded)) {
            languages.add(languagesLoaded);
        }

        return languages;
    }

    public List getListFromDelimitedProperty(String section, String property, String defaultValue)
    {
        String  valuesLoaded = this.getProperty(section,property,defaultValue);
        List  list = new ArrayList();

        if (valuesLoaded == null) { valuesLoaded = ""; }

        int index = valuesLoaded.indexOf(",");

        while (index >= 0) {
            list.add(valuesLoaded.substring(0, index));
            valuesLoaded = valuesLoaded.substring(index + 1);
            index = valuesLoaded.indexOf(",");
        }
        if (!Utility.isEmpty(valuesLoaded)) {
            list.add(valuesLoaded);
        }

        return list;
    }

	public List getPropertiesToAdd()
	{
		return this.propertiesToAdd;
	}

	public void clearPropertiesToAdd()
	{
		this.propertiesToAdd.clear();
	}

	public List getPropertiesToUpdate()
	{
		return this.propertiesToUpdate;
	}

	public void clearPropertiesToUpdate()
	{
		this.propertiesToUpdate.clear();
	}

	public String toString()
	{
		StringBuffer sb = new StringBuffer();

		for (Iterator it = this.sections.keySet().iterator(); it.hasNext(); )
		{
			String section = (String)it.next();
			sb.append("Section : " + section + "\r\n");
			Map tempMap = (Map)this.sections.get(section);
			String key = "";
			for (Iterator itTemp = tempMap.keySet().iterator(); itTemp.hasNext(); )
			{
				key = itTemp.next().toString();
				sb.append(key + " = " + tempMap.get(key) + "\r\n");
			}
			sb.append("\r\n");
		}


		return sb.toString();
	}
	
	private String getOrganizationId() {
		return PropertiesManager.organizationId;
	}
	
	private void setOrganizationId(String o) {
		PropertiesManager.organizationId = o;
	}
	
	public static void removeOID(String o) {
		PropertiesManager propertiesManager = (PropertiesManager) properties.get(o);
		if (propertiesManager != null) {
			propertiesManager.clear();
			properties.remove(o);
		}
	}
	
	public void clear() {
		if (sections != null) {
			sections.clear();
		}
		
		if (propertiesToAdd != null) {
			propertiesToAdd.clear();
		}
		
		if (propertiesToUpdate != null) {
			propertiesToUpdate.clear();
		}
	}
}
