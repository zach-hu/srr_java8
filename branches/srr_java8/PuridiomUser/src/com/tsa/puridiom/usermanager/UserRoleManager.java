package com.tsa.puridiom.usermanager;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.usermanager.UserRole;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;
import java.util.*;

/**
 * Creation date:June 2004
 * @author: Kelli Knisely
 */
public class UserRoleManager
{
	private static UserRoleManager INSTANCE = new UserRoleManager();
	private HashMap organizations = new HashMap();

	/**
	 * UserRoleAdminManager constructor comment.
	 */
	private UserRoleManager()
	{
		super();
	}
	/**
	 * @return com.tsa.puridiom.user.UserRoleManager
	 */
	public static UserRoleManager getInstance()
	{
		if (INSTANCE == null)
		{
			INSTANCE = new UserRoleManager();
		}
		return INSTANCE;
	}
	/**
	 * @return com.puridiom.hitlon.user.UserRole
	 * @param roleId java.lang.String
	 * @param organizationId java.lang.String
	 * @exception java.lang.Exception The exception description.
	 */
	public UserRole getUserRole(String roleId, String organizationId) throws java.lang.Exception
	{
		UserRole userRole = null;
		
		try
		{
			if (roleId == null || roleId.trim().length() == 0 || organizationId == null || organizationId.trim().length() == 0)
			{
				userRole = new UserRole();
			}
			else
			{
				Map userRoles = new HashMap();
				
				if (this.organizations.containsKey(organizationId))
				{
					userRoles = (Map) this.organizations.get(organizationId);
				}
				if (userRoles.containsKey(roleId)) 
				{
					userRole = (UserRole) userRoles.get(roleId);
				}
				else
				{
					HashMap processParameters = new HashMap();
					processParameters.put("organizationId", organizationId);
					processParameters.put("SecurityProfile_groupId", roleId);
					
					PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
					PuridiomProcess process = processLoader.loadProcess("securityprofile-retrieve-by.xml");
					
					process.executeProcess(processParameters);
					
					List list = (List) processParameters.get("securityProfileList");

					userRole = this.getUserRoleFromSecurityProfileList(organizationId, roleId, list);
					
					if (userRole != null)
					{
						userRoles.put(roleId, userRole);
					}
					this.organizations.put(organizationId, userRoles);
				}
			}
		}
		catch (Exception e)
		{
			//system.out.println("EXCEPTION ERROR in getUserRole: " + e.getMessage());
			e.printStackTrace();
			throw e;
		}
		finally
		{
			return userRole;
		}
	}
	/**
	 * @return com.tsagate.common.service.security.UserRole
	 * @param roleId java.lang.String
	 * @param organizationId java.lang.String
	 * @exception java.lang.Exception The exception description.
	 */
	public UserRole getUserRole(List roleIdList, String organizationId) throws java.lang.Exception
	{
		UserRole userRole = null;
	
		try
		{
			if (roleIdList == null || roleIdList.size() == 0 || Utility.isEmpty(organizationId))
			{
				userRole = new UserRole();
			}
			else
			{
				List		userRoleList = new ArrayList();
				String	roleId = "";
				String	key = "";
				int	idleMinutes = 0;

				for (int i = 0; i < roleIdList.size(); i++)
				{
					if (roleIdList.get(i) != null)
					{
						roleId = (String) roleIdList.get(i);
				
						UserRole userRoleTemp = getUserRole(roleId, organizationId);
						userRoleList.add(userRoleTemp);
						
						SecurityGroup securityGroup = GroupManager.getInstance().getSecurityGroup(organizationId, roleId);
						try
						{
						    int tempIdleMinutes = Integer.valueOf(securityGroup.getIdlemin()).intValue(); 
						    if (tempIdleMinutes > idleMinutes)
						    {
						        idleMinutes = tempIdleMinutes;
						    }
						}
						catch (Exception e)
						{
						}
					}
				}
				
				userRole = this.populateUserRoleFromList(userRoleList);
				userRole.setIdleMinutes(idleMinutes);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}
		finally
		{
			return userRole;
		}
	}
	/**
	 * @return com.tsagate.common.service.security.UserRole
	 * @param user com.tsagate.common.service.security.UserRole
	 * @exception java.lang.Exception The exception description.
	 */
	public UserRole populateUserRoleFromList(List roleList) throws Exception
	{
	    Map accessProperties = new HashMap();
	    UserRole userRole = new UserRole();
	    
	    try
		{
			if (roleList != null)
			{
				for (int i = 0; i < roleList.size(); i++)
				{
				    UserRole tempRole = (UserRole) roleList.get(i);
				    Map tempAccessProperties = tempRole.getAccessProperties();
					
					if (i == 0)
					{
					    accessProperties.putAll(tempAccessProperties);
					}
					else
					{
					    Iterator iterator = tempAccessProperties.keySet().iterator();
					
						while (iterator.hasNext())
						{
						    String key = (String) iterator.next();
							int	tempAccess = tempRole.getAccessRights(key);
							
							if (accessProperties.containsKey(key))
							{
								int	currentAccess =  UserRoleManager.getIntAccess((String) accessProperties.get(key));
								
								if (tempAccess > currentAccess)
								{
								    accessProperties.put(key, UserRoleManager.getAccessString(tempAccess));
								}
							}
							else
							{
							    accessProperties.put(key, UserRoleManager.getAccessString(tempAccess));
							}
						}							
					}
				}
				userRole.setAccessProperties(accessProperties);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}
		finally
		{
			return userRole;
		}
	}
	public void setUserRoleInCache(UserRole userRole) throws Exception
	{
		String	roleId = userRole.getRoleId();
		String	organizationId = userRole.getOrganizationId();
		Map		userRoles = new HashMap();
		
		if (roleId == null || roleId.trim().length() == 0 || organizationId == null || organizationId.trim().length() == 0)
		{
			throw new Exception ("User Role cannot be updated without a role id and an organization id!");
		}
		
		if (this.organizations.containsKey(organizationId)) 
		{
			userRoles = (Map) this.organizations.get(organizationId);
		}
		
		userRoles.put(roleId, userRole);
		this.organizations.put(organizationId, userRoles);
	}
	
	public void removeUserRoleFromCache(String organizationId, String roleId) throws Exception
	{
		Map		userRoles = new HashMap();
		
		if (this.organizations.containsKey(organizationId)) 
		{
			userRoles = (Map) this.organizations.get(organizationId);
		}
		
		userRoles.remove(roleId);
		this.organizations.put(organizationId, userRoles);
	}
	
	public int getAccess(String roleId, String organizationId, String accessType) throws Exception
	{
		int access = 0;
		
		try
		{
			UserRole userRole = this.getUserRole(roleId, organizationId);
			 
			if (userRole == null)
			{
				throw new Exception("UserRole could not be found.");
			}
			
			access = userRole.getAccessRights(accessType);
		}
		catch (Exception e)
		{
			//system.out.println("ERROR: " + e.getMessage());
		}
			
		return access;
	}
	
	public UserRole getUserRoleFromSecurityProfileList(String organizationId, String roleId, List list)
	{
		Map accessProperties = new HashMap();
		UserRole userRole = new UserRole();
		
		if (list != null && list.size() > 0)
		{
			for (int i=0; i < list.size(); i++)
			{
				SecurityProfile securityProfile = (SecurityProfile) list.get(i);
				accessProperties.put(securityProfile.getComp_id().getProfile(), securityProfile.getFlags());
			}
			userRole.setOrganizationId(organizationId);
			userRole.setRoleId(roleId);
			userRole.setDescription("");
			userRole.setAccessProperties(accessProperties);
		}
		
		return userRole;
	}
	
	public static int getIntAccess(String accessString) {
		int	access = 0;
		
		try
		{
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
		catch (Exception e)
		{
		}
			
		return access;
	}
	
	public static String getAccessString(int access) {
	    String	accessString = "";
	    
	    try
	    {
	        if (access == 99)
	        {
	            accessString = "S";
	        }
			else if (access  == 3)
			{
				accessString = "C";
			}
			else if (access == 2)
			{
				accessString = "M";
			}
			else if (access == 1)
			{
				accessString = "V";
			}
	    }
	    catch (Exception e)
	    {
	    }
	    
	    return accessString;
	}
	
	/**
	 * Returns a String that represents the value of this object.
	 * @return a string representation of the receiver
	 */
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("[ClassName=com.tsa.puridiom.usermanager.UserRoleManager]");
		return sb.toString();
	}
}
