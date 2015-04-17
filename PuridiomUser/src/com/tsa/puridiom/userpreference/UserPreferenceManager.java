package com.tsa.puridiom.userpreference;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.UserPreference;
import com.tsa.puridiom.entity.UserPreferencePK;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.utility.Utility;

/**
 * Creation date: January 18, 2005
 * @author: Kathleen
 */
public class UserPreferenceManager
{
	private static UserPreferenceManager INSTANCE = new UserPreferenceManager();
	private HashMap organizations = new HashMap();

	/**
	 * UserPreferenceAdminManager constructor comment.
	 */
	private UserPreferenceManager()
	{
		super();
	}

	/**
	 * @return com.tsa.puridiom.user.UserPreferenceManager
	 */
	public static UserPreferenceManager getInstance()
	{
		if (INSTANCE == null)
		{
			INSTANCE = new UserPreferenceManager();
		}
		return INSTANCE;
	}

	/**
	 * @return com.tsa.puridiom.user.UserPreference
	 * @param organizationId java.lang.String
	 * @param userId java.lang.String
	 * @exception java.lang.Exception The exception description.
	 */
	public String getUserPreference(String organizationId, String userId, String property, String defaultvalue) throws java.lang.Exception
	{
		UserPreference userPreference = null;
		String value = null;

		try
		{
			if (!Utility.isEmpty(userId) && !Utility.isEmpty(organizationId) && !Utility.isEmpty(property))
			{
				if(property.compareToIgnoreCase("COLOR") == 0 || property.compareToIgnoreCase("NOTES") == 0){
					
					Map users = new HashMap();
					Map userPrefs = new HashMap();
	
	                property = property.toUpperCase();
	
					if (this.organizations.containsKey(organizationId))
					{
						users = (Map) this.organizations.get(organizationId);
	                    if (users.containsKey(userId))
	                    {
	                        userPrefs = (Map) users.get(userId);
	                    }
					}
					if (userPrefs.containsKey(property))
					{
						value = (String) userPrefs.get(property);
					} else {
						userPrefs.put(property, defaultvalue);
						users.put(userId, userPrefs);
						this.organizations.put(organizationId, users);
					}
					
				} else {
					
					HashMap processParameters = new HashMap();
					processParameters.put("organizationId", organizationId);
					processParameters.put("UserPreference_userId", userId);
					processParameters.put("UserPreference_property", property);

					PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
					PuridiomProcess process = processLoader.loadProcess("userpreference-retrieve-by-id.xml");

					process.executeProcess(processParameters);

					userPreference = (UserPreference)processParameters.get("userPreference");
					if (userPreference != null)
					{
						value = userPreference.getValue();
					}
					
				}
			}

            if (HiltonUtility.isEmpty(value))
            {
                value = defaultvalue;
            }
			return value;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}
	}

	public void setUserPreferenceInCache(String organizationId, UserPreference userPreference) throws Exception
	{
		UserPreferencePK pk = userPreference.getComp_id();
		String	userId = pk.getUserId();
		Map		users = new HashMap();
		Map		userPrefs = new HashMap();

		if (userId == null || userId.trim().length() == 0 || organizationId == null || organizationId.trim().length() == 0)
		{
			throw new Exception ("User Preference cannot be updated without a user id and an organization id!");
		}

		if (this.organizations.containsKey(organizationId))
		{
			users = (Map) this.organizations.get(organizationId);
		}
		if (users.containsKey(userId))
		{
			userPrefs = (Map) users.get(userId);
		}

		UserPreferencePK userPreferencePK = userPreference.getComp_id();
		String prop = userPreferencePK.getProperty();
		String key = userPreference.getValue();
		userPrefs.put(prop, key);

		users.put(userId, userPrefs);
		this.organizations.put(organizationId, users);
	}

	public static String getUserLogo(String organizationId, String imgPath, String imgFileName) {
	    String	img = "";
	    String  separator = "/" ;    // File.separator ;

	    if (imgPath.lastIndexOf(separator) < imgPath.length() - 1) {
	        imgPath = imgPath + separator;
	    }

	    if (Utility.isEmpty(organizationId)) {
	        img = imgPath + imgFileName;
	    } else {
	    	if (! PropertiesManager.getInstance(organizationId).getProperty("MISC", "UseOrgIdPath", "N").equalsIgnoreCase("Y")) {
                img = imgPath + imgFileName;
	    	} else {
		        img = imgPath + organizationId.toLowerCase() + separator + imgFileName;
		        try {
		            URL u = new URL(img);
		            u.openStream();
		        } catch (Exception e) {
	                img = imgPath + imgFileName;
		        }
	    	}
	    }

	    return img;
	}
}
