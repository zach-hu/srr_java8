package com.tsa.puridiom.apppool;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class AppPoolManager
{
    private static AppPoolManager INSTANCE = new AppPoolManager();
    private HashMap organizations = new HashMap();

    private AppPoolManager()
    {
        super();
    }

    public static AppPoolManager getInstance()
    {
        if (INSTANCE == null)
        {
            INSTANCE = new AppPoolManager();
        }
        return INSTANCE;
    }

    public AppPool getAppPool(String organizationId, String poolid) throws java.lang.Exception
    {
    	AppPool appPool = null;

        try
        {
            if (Utility.isEmpty(poolid) || Utility.isEmpty(organizationId))
            {
                return appPool;
            }
            else
            {
                organizationId = organizationId.toUpperCase();

                Map appPools = new HashMap();

                if (this.organizations.containsKey(organizationId))
                {
                	appPools = (Map) this.organizations.get(organizationId);
                }

                if (appPools.containsKey(poolid))
                {
                	appPool = (AppPool) appPools.get(poolid);
                }
                else
                {
                    HashMap processParameters = new HashMap();
                    processParameters.put("organizationId", organizationId);
                    processParameters.put("AppPool_poolid", poolid);

                    PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
                    PuridiomProcess process = processLoader.loadProcess("app-pool-retrieve-by-id.xml");

                    process.executeProcess(processParameters);

                    appPool = (AppPool) processParameters.get("appPool");
                    if (appPool != null) {
                    	appPools.put(poolid, appPool);
                    }

                    this.organizations.put(organizationId, appPools);
                }
            }
        }
        catch (Exception e)
        {
            throw e;
        }
        finally
        {
            return appPool;
        }
    }

    public void setAppPoolInCache(String organizationId, AppPool appPool) throws Exception
    {
        try
        {
        	if (appPool != null)
        	{
        		String poolid = appPool.getPoolid();
        		Map	appPools = new HashMap();

        		if (HiltonUtility.isEmpty(poolid) || HiltonUtility.isEmpty(organizationId))
        		{
        			throw new Exception ("appPool cannot be updated without a poolid and an organization id!");
        		}

        		if (this.organizations.containsKey(organizationId))
        		{
        			appPools = (Map) this.organizations.get(organizationId);
        		}

        		appPools.put(poolid, appPool);
        		this.organizations.put(organizationId, appPools);
        	}
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    public void removeAppPoolFromCache(String organizationId, String poolid) throws Exception
	{
		Map	appPools = new HashMap();

		if (!HiltonUtility.isEmpty(organizationId) && !HiltonUtility.isEmpty(poolid))
		{
			if (this.organizations.containsKey(organizationId))
			{
				appPools = (Map) this.organizations.get(organizationId);
			}
			appPools.remove(poolid);
			this.organizations.put(organizationId, appPools);
		}
	}
}
