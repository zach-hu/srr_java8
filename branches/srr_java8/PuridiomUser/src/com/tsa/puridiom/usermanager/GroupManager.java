package com.tsa.puridiom.usermanager;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;
import java.util.*;

/**
 * Creation date:June 2004
 * @author: Kelli Knisely
 */
public class GroupManager
{
	private static GroupManager INSTANCE = new GroupManager();
	private HashMap organizations = new HashMap();

	/**
	 * GroupManager constructor comment.
	 */
	private GroupManager()
	{
		super();
	}
	/**
	 * @return com.tsa.puridiom.user.UserRoleManager
	 */
	public static GroupManager getInstance()
	{
		if (INSTANCE == null)
		{
			INSTANCE = new GroupManager();
		}
		return INSTANCE;
	}
	/**
	 * @return com.puridiom.hitlon.entity.SecurityGroup
	 * @param groupId java.lang.String
	 * @param organizationId java.lang.String
	 * @exception java.lang.Exception The exception description.
	 */
	public SecurityGroup getSecurityGroup(String organizationId, String groupId) throws java.lang.Exception
	{
		//system.out.println("GroupManager.getSecurityGroup BEGIN " + Calendar.getInstance().getTime().toString());
		//system.out.println("GroupManager.getSecurityGroup groupId: " + groupId + "; organizationId: " + organizationId);
		SecurityGroup securityGroup = null;
		
		try
		{
			if (Utility.isEmpty(groupId) || Utility.isEmpty(organizationId))
			{
				securityGroup = new SecurityGroup();
			}
			else
			{
				Map groups = new HashMap();
				
				if (this.organizations.containsKey(organizationId))
				{
					groups = (Map) this.organizations.get(organizationId);
				}
				if (groups.containsKey(groupId)) 
				{
					securityGroup = (SecurityGroup) groups.get(groupId);
				}
				else
				{
					HashMap processParameters = new HashMap();
					processParameters.put("organizationId", organizationId);
					
					PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
					PuridiomProcess process = processLoader.loadProcess("securitygroup-retrieve-all.xml");
					
					process.executeProcess(processParameters);
					
					List list = (List) processParameters.get("securityGroupList");

					if (list != null)
					{
						for (int i=0; i < list.size(); i++)
						{
							SecurityGroup group = (SecurityGroup) list.get(i);
							if (group != null)
							{
								String	gid = group.getGroupId();
								if (groupId.equalsIgnoreCase(gid))
								{
									securityGroup = group;
								}
								groups.put(gid, group);
							}
						}
					}
					
					this.organizations.put(organizationId, groups);
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
			return securityGroup;
		}
	}
	
	/**
	 * @return String
	 * @param groupId java.lang.String
	 * @param organizationId java.lang.String
	 * @exception java.lang.Exception The exception description.
	 */
	public String getSecurityGroupDescription(String organizationId, String groupId) throws java.lang.Exception
	{
		//system.out.println("GroupManager.getSecurityGroupDescription BEGIN " + Calendar.getInstance().getTime().toString());
		//system.out.println("GroupManager.getSecurityGroupDescription groupId: " + groupId + "; organizationId: " + organizationId);
		String description = "";
		
		try
		{
			SecurityGroup securityGroup = null;
			if (Utility.isEmpty(groupId) || Utility.isEmpty(organizationId))
			{
				securityGroup = new SecurityGroup();
			}
			else
			{
				Map groups = new HashMap();
				
				if (this.organizations.containsKey(organizationId))
				{
					groups = (Map) this.organizations.get(organizationId);
				}
				if (groups.containsKey(groupId)) 
				{
					securityGroup = (SecurityGroup) groups.get(groupId);
				}
				else
				{
					HashMap processParameters = new HashMap();
					processParameters.put("organizationId", organizationId);
					
					PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
					PuridiomProcess process = processLoader.loadProcess("securitygroup-retrieve-all.xml");
					
					process.executeProcess(processParameters);
					
					List list = (List) processParameters.get("securityGroupList");

					if (list != null)
					{
						for (int i=0; i < list.size(); i++)
						{
							SecurityGroup group = (SecurityGroup) list.get(i);
							if (group != null)
							{
								String	gid = group.getGroupId();
								if (groupId.equalsIgnoreCase(gid))
								{
									securityGroup = group;
								}
								groups.put(gid, group);
							}
						}
					}
					
					this.organizations.put(organizationId, groups);
				}
			}
			if (securityGroup != null) {
				description = securityGroup.getGroupDescription();
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
			return description;
		}
	}

	public void setSecurityGroupInCache(String organizationId, SecurityGroup securityGroup) throws Exception
	{
		String	groupId = securityGroup.getGroupId();
		Map		groups = new HashMap();
		
		if (Utility.isEmpty(groupId) || Utility.isEmpty(organizationId))
		{
			throw new Exception ("SecurityGroup cannot be cached without a group id and an organization id!");
		}
		
		if (this.organizations.containsKey(organizationId)) 
		{
			groups = (Map) this.organizations.get(organizationId);
		}
		
		groups.put(groupId, securityGroup);
		this.organizations.put(organizationId, groups);
	}
	
	/**
	 * Returns a String that represents the value of this object.
	 * @return a string representation of the receiver
	 */
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("[ClassName=com.tsa.puridiom.usermanager.GroupManager]");
		return sb.toString();
	}
}
