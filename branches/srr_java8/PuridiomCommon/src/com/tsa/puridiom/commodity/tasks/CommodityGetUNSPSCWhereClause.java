/*
 * Created on Mar 10, 2005
 */
package com.tsa.puridiom.commodity.tasks;

import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;

import java.util.Map;

/**
 * @author kelli

	UNSPSC Commodity Code pattern: SSFFGGCCBB
	SS	-	Segment	(%00000000)
	FF	-	Family	(SS%000000)
	GG	-	Class / Group	(SSFF%0000)
	CC	-	Commodity	(SSFFGG%00)
	BB	-	Business Function	(SSFFGGCC%)
 */
public class CommodityGetUNSPSCWhereClause extends Task {
    
	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		
		Object obj = incomingRequest.get("Commodity_commodity");
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
	    
		StringBuffer sqlWhere = new StringBuffer();
		sqlWhere.append(" Commodity.status = '02'");
		
		String	retrieveAllCodes = Utility.ckNull((String) incomingRequest.get("retrieveAllCodes"));
		String	retrieveAllSegments = Utility.ckNull((String) incomingRequest.get("retrieveAllSegments"));
		String	retrieveAllFamilies = Utility.ckNull((String) incomingRequest.get("retrieveAllFamilies"));
		String	retrieveAllGroups = Utility.ckNull((String) incomingRequest.get("retrieveAllGroups"));
		String	retrieveAllCommodities = Utility.ckNull((String) incomingRequest.get("retrieveAllCommodities"));
		
		StringBuffer sqlWhereTemp = new StringBuffer();
		if (Utility.ckNull(retrieveAllCodes).equals("Y") || commodityCodes.length == 0) {
		    sqlWhereTemp.append("Commodity.commodity LIKE '%000000'");
		}
		
		for (int i=0; i < commodityCodes.length; i++) {
			String	commodityCode = commodityCodes[i];
			
			if (!Utility.isEmpty(commodityCode) || retrieveAllSegments.equals("Y") || retrieveAllFamilies.equals("Y") || retrieveAllGroups.equals("Y") || retrieveAllCommodities.equals("Y")) {
				String	segment = commodityCode.substring(0, 2);
				String	family = commodityCode.substring(2, 4);
				String	group = commodityCode.substring(4, 6);
				String	commodity = commodityCode.substring(6, 8);
				//String	function = commodityCode.substring(8, 9);
			    
			    if (!segment.equals("00") || retrieveAllSegments.equals("Y")) {
			        if (retrieveAllSegments.equals("Y")) {
			            segment = "";
			        }
		            sqlWhereTemp.append(" OR Commodity.commodity LIKE '" + segment + "%0000'");
				    
				    if (!family.equals("00") || retrieveAllFamilies.equals("Y")) {
				        if (retrieveAllFamilies.equals("Y")) {
				            family = "";
				        }
			            sqlWhereTemp.append(" OR Commodity.commodity LIKE '" + segment +family +  "%00'");
				        
					    if (!group.equals("00") || retrieveAllGroups.equals("Y")) {
					        if (retrieveAllGroups.equals("Y")) {
					            group = "";
					        }
					        sqlWhereTemp.append(" OR Commodity.commodity LIKE '" + segment +family + group + "%'");
					        
						    if (!commodity.equals("00") || retrieveAllCommodities.equals("Y")) {
						        if (retrieveAllCommodities.equals("Y")) {
						            commodity = "";
						        }
						        sqlWhereTemp.append(" OR Commodity.commodity = '" + segment +family + group + commodity + "'");
						        
						        //if (!function.equals("00")) {
							      //  sqlWhereTemp.append(" OR Commodity.commodity = '" + segment +family + group + commodity + function);
							    //}
						    }
					    }
				    }
			    }
			}
		}
		
		if (sqlWhereTemp.length() > 0) {
			if (sqlWhereTemp.toString().startsWith(" OR ")) {
			    sqlWhereTemp = new StringBuffer(sqlWhereTemp.substring(3));
			}
			sqlWhere.append(" AND (" + sqlWhereTemp.toString() + ") ");
		}
		return sqlWhere.toString();
	}
	
}
