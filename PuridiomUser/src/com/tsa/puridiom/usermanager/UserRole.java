package com.tsa.puridiom.usermanager;

import java.util.*;

/**
 * Creation date: June 2004
 * @author: Kelli Knisely
 */
public class UserRole
{
	private String roleId = "";
	private String organizationId = "";
	private String description = "";
	private Map accessProperties = null;
	private int idleMinutes = 0;
	
	/**
	 * UserRole constructor comment.
	 */
	public UserRole()
	{
		super();
		
		if (this.accessProperties == null)
		{
			this.accessProperties = new HashMap();
		}
	}
	/**
	 * @return String
	 */
	public String getRoleId()
	{
		return this.roleId;
	}
	/**
	 * @return String
	 */
	public String getOrganizationId()
	{
		return this.organizationId;
	}
	/**
	 * @return String
	 */
	public String getDescription()
	{
		return this.description;
	}	
	/**
	 * @return java.util.Map
	 */
	public java.util.Map getAccessProperties()
	{
		return this.accessProperties;
	}
	/**
	 * 99 - Supervisor Access Rights
	 *  3  - Create Access
	 *  2 - Maintain Access
	 *  1 - View Access
	 *  0 - No Access
	 * @param accessType String
	 * @return int 
	 */
	public int getAccessRights(String accessType)
	{
		int access = 0;
		
		if (this.accessProperties.containsKey(accessType))
		{
			String accessString = (String) accessProperties.get(accessType);
			if (accessString != null && accessString.equals("S"))
			{
				access = 99;
			}
			else if (accessString != null && accessString.equals("C"))
			{
				access = 3;
			}
			else if (accessString != null && accessString.equals("M"))
			{
				access = 2;
			}
			else if (accessString != null && accessString.equals("V"))
			{
				access = 1;
			}
		}
		
		return access;
	}
	/**
	 * 99 - Supervisor Access Rights
	 *  3  - Create Access
	 *  2 - Maintain Access
	 *  1 - View Access
	 *  0 - No Access
	 * @param accessType String
	 * @return int 
	 */
	public void setAccessRights(String accessType, int access)
	{
		String accessString = "";
		
		if (access == 99)
		{
			accessString = "S";
		}
		else if (access == 3)
		{
			accessString = "M";
		}
		else if (access == 2)
		{
			accessString = "C";
		}
		else if (access == 1)
		{
			accessString = "V";
		}
		
		this.accessProperties.put(accessType, accessString);
	}
	
	/**
	 *  S - Supervisor Access Rights
	 *  C  - Create Access
	 *  M - Maintain Access
	 *  V - View Access
	 *  - - No Access
	 * @param accessType String
	 * @return int 
	 */
	public void setAccessRights(String accessType, String accessLevel)
	{
		this.accessProperties.put(accessType, accessLevel);
	}
	/**
	 * @param newRoleId String
	 */
	public void setRoleId(String newRoleId)
	{
		this.roleId = newRoleId;
	}
	/**
	 * @param newOrganizationId String
	 */
	public void setOrganizationId(String newOrganizationId)
	{
		this.organizationId = newOrganizationId;
	}
	/**
	 * @param newDescription String
	 */
	public void setDescription(String newDescription)
	{
		this.description = newDescription;
	}
	/**
	 * @param newAccessProperties java.util.Map
	 */
	public void setAccessProperties(java.util.Map newAccessProperties)
	{
		this.accessProperties = newAccessProperties;
	}
	
    public int getIdleMinutes()
    {
        return idleMinutes;
    }

    public void setIdleMinutes(int idleMinutes)
    {
        this.idleMinutes = idleMinutes;
    }
    
	/**
	 * Returns a String that represents the value of this object.
	 * @return a string representation of the receiver
	 */
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("[ClassName=com.tsa.puridiom.usermanager.UserRole, ");
		sb.append("roleID=" + this.getRoleId());
		sb.append(", description=" + this.getDescription());
		
		if (this.getAccessProperties() != null)
		{
			if (this.getAccessProperties().keySet() != null)
			{
				Iterator iterator = this.getAccessProperties().keySet().iterator();
				String	key = "";
				String	value = "";
		
				sb.append(", Properties: ");
		
				while (iterator.hasNext())
				{
					key = (String) iterator.next();
					value = (String) this.getAccessProperties().get(key);
			
					sb.append(" ( " + key + "=" + value + " )");
				}				
			}
		}

		sb.append("]");
		return sb.toString();
	}
}
