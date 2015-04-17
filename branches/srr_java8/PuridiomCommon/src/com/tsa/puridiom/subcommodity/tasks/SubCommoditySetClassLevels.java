/*
 * Created on October 24, 2006
 */
package com.tsa.puridiom.subcommodity.tasks;

import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;

import java.util.Map;
import java.util.StringTokenizer;

/**
 * @author kelli

	SubCommodity Code pattern setup as properties
 */
public class SubCommoditySetClassLevels extends Task {
    
	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		
		String organizationId = (String) incomingRequest.get("organizationId");
		PropertiesManager propertiesManager = PropertiesManager.getInstance(organizationId);
		int categoryCount = (new Integer(propertiesManager.getProperty("MISC", "SubCommoditySections", "0"))).intValue();
		int categoryStartPos[] = new int[categoryCount];
		int categoryEndPos[] = new int[categoryCount];
		
		for (int cnt = 0; cnt < categoryCount; cnt++ ) {
		    String posTemp = propertiesManager.getProperty("MISC","SubCommodityLevel" + Integer.toString(cnt + 1) + "Pos","") ;
		
			// Remove Quotes
			posTemp = posTemp.replaceAll("\"","");
			
			if (!Utility.isEmpty(posTemp)) {
			    //Pick out the parameters
			    StringTokenizer st = new StringTokenizer(posTemp,",");
		        for (int ix = 1;st.hasMoreElements();ix++) {
		        	categoryStartPos[cnt] = Integer.valueOf(st.nextToken()).intValue();
		        	categoryEndPos[cnt] = Integer.valueOf(st.nextToken()).intValue();
		        	if (ix == 2) { break ; }
		        }
		    } else {
		    	categoryStartPos[cnt] = 0;
		    	categoryEndPos[cnt] = 0;
		    }
		}
		
		Object obj = incomingRequest.get("SubCommodity_commodity");
		String	commodityCodes[] = null;
		
		if (obj instanceof String) {
		    commodityCodes = new String[1];
		    commodityCodes[0] = (String) obj;
		} else if (obj instanceof String[]) {
		    commodityCodes = (String[]) obj;
		}
		if (commodityCodes == null || commodityCodes.length == 0) {
		    commodityCodes = new String[1];
		    commodityCodes[0] = "";
		}

		int commodityCount = commodityCodes.length;
		
		String	levelDefaultTmp = "00000000000000000000";
		String	nigpLevels[] = new String[commodityCount];
		String	commodityLevels[][] = new String[4][commodityCount];
		
		for (int i=0; i < commodityCount; i++) {
			String	commodityCode = commodityCodes[i];
			int nigpLevel = 0;
			
			if (!Utility.isEmpty(commodityCode)) {
			    for (int ic = 0; ic < categoryCount; ic++) {
			        if (commodityCode.length() >= categoryEndPos[ic]) {
				        String level = commodityCode.substring(0, categoryEndPos[ic]);
				        String	levelTmp = level.substring(0, categoryStartPos[ic]) + levelDefaultTmp.substring(categoryStartPos[ic], categoryEndPos[ic]);
				        
				        commodityLevels[ic][i] = level;
				        if (!level.equals(levelTmp)) {
				            nigpLevel ++;
				        }
				    }
			    }

   			    nigpLevels[i] = String.valueOf(nigpLevel);
			}
		}
		if (commodityCodes.length == 1) {
			incomingRequest.put("SubCommodity_nigplevel", nigpLevels[0]);
			incomingRequest.put("SubCommodity_level1", commodityLevels[0][0]);
			incomingRequest.put("SubCommodity_level2", commodityLevels[1][0]);
			incomingRequest.put("SubCommodity_level3", commodityLevels[2][0]);
		} else {
			incomingRequest.put("SubCommodity_nigplevel", nigpLevels);
			incomingRequest.put("SubCommodity_level1", commodityLevels[0]);
			incomingRequest.put("SubCommodity_level2", commodityLevels[1]);
			incomingRequest.put("SubCommodity_level3", commodityLevels[2]);
		}
		
		this.setStatus(Status.SUCCEEDED);
		
		return null;
	}
	
}
