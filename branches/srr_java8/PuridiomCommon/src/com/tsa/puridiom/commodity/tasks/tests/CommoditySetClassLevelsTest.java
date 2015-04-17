package com.tsa.puridiom.commodity.tasks.tests;

import com.tsa.puridiom.commodity.tasks.*;
import java.util.*;

public class CommoditySetClassLevelsTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			CommoditySetClassLevels test = new CommoditySetClassLevels();
			Map incomingRequest = new HashMap();
		
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "SYSADM");
			//String	commodities[] = {"20100000000","20132000000","20132560000","20132564321"};
			//incomingRequest.put("Commodity_commodity", commodities);
			String	commodity = "20132564321";
			incomingRequest.put("Commodity_commodity", commodity);
		
			test.executeTask(incomingRequest);
			
			Object		nigpLevelObj = incomingRequest.get("Commodity_nigpLevel");
			
			if (nigpLevelObj instanceof String[]) {
				String	nigpLevels[] = (String[]) nigpLevelObj;
				String	levels1[] = (String[]) incomingRequest.get("Commodity_level1");
				String	levels2[] = (String[]) incomingRequest.get("Commodity_level2");
				String	levels3[] = (String[]) incomingRequest.get("Commodity_level3");
				String commodityCodes[] = (String[]) incomingRequest.get("Commodity_commodity");
				
				for (int i = 0; i < nigpLevels.length; i++) {
					System.out.println("Commodity_nigplevel[" + i +  "] = " + nigpLevels[i]);
					System.out.println("Commodity_level1[" + i + "] = " + levels1[i]);
					System.out.println("Commodity_level2[" + i + "] = " + levels2[i]);
					System.out.println("Commodity_level3[" + i + "] = " + levels3[i]);
					System.out.println("Commodity Code  [" + i + "] = " + commodityCodes[i]);
					System.out.println("");
				}
			} else  if (nigpLevelObj instanceof String) {
			    System.out.println("Commodity_nigplevel = " + (String) incomingRequest.get("Commodity_nigpLevel"));
			    System.out.println("Commodity_level1 = " + (String) incomingRequest.get("Commodity_level1"));
			    System.out.println("Commodity_level2 = " + (String) incomingRequest.get("Commodity_level2"));
			    System.out.println("Commodity_level3 = " + (String) incomingRequest.get("Commodity_level3"));
			    System.out.println("Commodity Code   = " + (String) incomingRequest.get("Commodity_commodity"));
			}

			System.out.println("CommoditySetClassLevelsTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}