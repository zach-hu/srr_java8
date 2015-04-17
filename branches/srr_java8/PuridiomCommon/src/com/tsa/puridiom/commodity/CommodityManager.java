package com.tsa.puridiom.commodity;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.tsa.puridiom.entity.Commodity;
import com.tsa.puridiom.organization.OrganizationManager;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;

/**
 * Creation date: March 4, 2005
 * @author: Kelli
 */
public class CommodityManager
{
	private static CommodityManager INSTANCE = new CommodityManager();
	private HashMap organizations = new HashMap();

	/**
	 * CommodityManager constructor comment.
	 */
	private CommodityManager()
	{
		super();
	}

	/**
	 * @return com.tsa.puridiom.commodity.CommodityManager
	 */
	public static CommodityManager getInstance()
	{
		if (INSTANCE == null)
		{
			INSTANCE = new CommodityManager();
		}
		return INSTANCE;
	}

	/**
	 * @return com.tsa.puridiom.entity.Commodity
	 * @param organizationId java.lang.String
	 * @param commodityId java.lang.String
	 * @exception java.lang.Exception The exception description.
	 */
	public String getCommodityDescription(String organizationId, String commodityCode) throws java.lang.Exception
	{
		String description = "";

		try
		{
		    Commodity commodity = null;
			if (!HiltonUtility.isEmpty(organizationId))
			{
				Map commodities = new HashMap();
				if (this.organizations.containsKey(organizationId))
				{
				    commodities = (Map) this.organizations.get(organizationId);
				}
				if (commodities.containsKey(commodityCode))
				{
				    commodity = (Commodity) commodities.get(commodityCode);
				}
				if (commodity == null)
				{
					HashMap processParameters = new HashMap();
					processParameters.put("organizationId", organizationId);
					processParameters.put("Commodity_commodity", commodityCode);

					PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
					PuridiomProcess process = processLoader.loadProcess("commodity-retrieve-by-id.xml");

					process.executeProcess(processParameters);

					commodity = (Commodity) processParameters.get("commodity");
					if (commodity != null)
					{
					    commodities.put(commodity.getCommodity(), commodity);
					}

					this.organizations.put(organizationId, commodities);
				}
			}

			if(commodity != null)
			{
				description = commodity.getDescription();
			}

			return HiltonUtility.ckNull(description);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}
	}

	public void setCommodityInCache(String organizationId, Commodity commodity) throws Exception
	{
		String	commodityCode = commodity.getCommodity();
		Map		commodities = new HashMap();

		if (HiltonUtility.isEmpty(commodityCode) || HiltonUtility.isEmpty(organizationId))
		{
			throw new Exception ("Commodity cannot be updated without a commodity code and an organization id!");
		}

		if (this.organizations.containsKey(organizationId))
		{
		    commodities = (Map) this.organizations.get(organizationId);
		}

		commodities.put(commodityCode, commodity);
		this.organizations.put(organizationId, commodities);
	}

	public void removeCommodityFromCache(String organizationId, String commodityCode) throws Exception
	{
		Map		commodities = new HashMap();

		if (!HiltonUtility.isEmpty(organizationId) && !HiltonUtility.isEmpty(commodityCode))
		{
			if (this.organizations.containsKey(organizationId))
			{
			    commodities = (Map) this.organizations.get(organizationId);
			    commodities.remove(commodityCode);
			}
		}

		this.organizations.put(organizationId, commodities);
	}

	/**
	 * @return icAccount java.lang.String
	 * @param organizationId java.lang.String
	 * @param commodityId java.lang.String
	 * @exception java.lang.Exception The exception description.
	 */
	public BigDecimal getCommodityIcAccount(String organizationId, String commodityCode) throws java.lang.Exception
	{
		BigDecimal icAccount = new BigDecimal(0);

		try
		{
		    Commodity commodity = null;
			if (!HiltonUtility.isEmpty(organizationId))
			{
				Map commodities = new HashMap();
				if (this.organizations.containsKey(organizationId))
				{
				    commodities = (Map) this.organizations.get(organizationId);
				}
				if (commodities.containsKey(commodityCode))
				{
				    commodity = (Commodity) commodities.get(commodityCode);
				}
				if (commodity == null)
				{
					HashMap processParameters = new HashMap();
					processParameters.put("organizationId", organizationId);
					processParameters.put("Commodity_commodity", commodityCode);

					PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
					PuridiomProcess process = processLoader.loadProcess("commodity-retrieve-by-id.xml");

					process.executeProcess(processParameters);

					commodity = (Commodity) processParameters.get("commodity");
					if (commodity != null)
					{
					    commodities.put(commodity.getCommodity(), commodity);
					}

					this.organizations.put(organizationId, commodities);
				}
			}

			if(commodity != null)
			{
				icAccount = commodity.getIcAccount();
			}

			return HiltonUtility.ckNull(icAccount);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}
	}

	public String getCommodityType(String organizationId, String commodityCode) throws java.lang.Exception
	{
		String commodityType = "";

		try
		{
		    Commodity commodity = null;
			if (!HiltonUtility.isEmpty(organizationId))
			{
				Map commodities = new HashMap();
				if (this.organizations.containsKey(organizationId))
				{
				    commodities = (Map) this.organizations.get(organizationId);
				}
				if (commodities.containsKey(commodityCode))
				{
				    commodity = (Commodity) commodities.get(commodityCode);
				}
				if (commodity == null)
				{
					HashMap processParameters = new HashMap();
					processParameters.put("organizationId", organizationId);
					processParameters.put("Commodity_commodity", commodityCode);

					PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
					PuridiomProcess process = processLoader.loadProcess("commodity-retrieve-by-id.xml");

					process.executeProcess(processParameters);

					commodity = (Commodity) processParameters.get("commodity");
					if (commodity != null)
					{
					    commodities.put(commodity.getCommodity(), commodity);
					}

					this.organizations.put(organizationId, commodities);
				}
			}

			if(commodity != null)
			{
				commodityType = commodity.getCommodityType();
			}

			return HiltonUtility.ckNull(commodityType);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}
	}

    /**
     * @return com.tsa.puridiom.entity.Commodity
     * @param organizationId java.lang.String
     * @param commodityCode java.lang.String
     * @exception java.lang.Exception The exception description.
     */
    public Commodity getCommodity(String organizationId, String commodityCode) throws java.lang.Exception
    {
        Commodity commodity = null;

        try
        {
            if (!HiltonUtility.isEmpty(organizationId))
            {
                Map commodities = new HashMap();
                if (this.organizations.containsKey(organizationId))
                {
                    commodities = (Map) this.organizations.get(organizationId);
                }
                if (commodities.containsKey(commodityCode))
                {
                    commodity = (Commodity) commodities.get(commodityCode);
                }
                if (commodity == null)
                {
                    HashMap processParameters = new HashMap();
                    processParameters.put("organizationId", organizationId);
                    processParameters.put("Commodity_commodity", commodityCode);

                    PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
                    PuridiomProcess process = processLoader.loadProcess("commodity-retrieve-by-id.xml");

                    process.executeProcess(processParameters);

                    commodity = (Commodity) processParameters.get("commodity");
                    if (commodity != null)
                    {
                        commodities.put(commodity.getCommodity(), commodity);
                    }
                    this.organizations.put(organizationId, commodities);
                }
            }

            return commodity;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw e;
        }
    }

	public Commodity getCommodityEntity(String organizationId, String commodityCode) {
		Commodity commodity = null;
		try {
			if (!HiltonUtility.isEmpty(organizationId))
			{
				Map commodities = new HashMap();
				if (this.organizations.containsKey(organizationId))
				{
				    commodities = (Map) this.organizations.get(organizationId);
				}
				if (commodities.containsKey(commodityCode))
				{
				    commodity = (Commodity) commodities.get(commodityCode);
				}
				if (commodity == null)
				{
					HashMap processParameters = new HashMap();
					processParameters.put("organizationId", organizationId);
					processParameters.put("Commodity_commodity", commodityCode);
	
					PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
					PuridiomProcess process = processLoader.loadProcess("commodity-retrieve-by-id.xml");
	
					process.executeProcess(processParameters);
	
					commodity = (Commodity) processParameters.get("commodity");
					if (commodity != null)
					{
					    commodities.put(commodity.getCommodity(), commodity);
					}
	
					this.organizations.put(organizationId, commodities);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return commodity;
	}
	
    public static void refresh() {
        INSTANCE = new CommodityManager();
    }
}
