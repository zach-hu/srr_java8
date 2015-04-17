/*
 * Created on Mar 10, 2005
 */
package com.tsa.puridiom.commodity.tasks;

import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;

import java.util.Map;

/**
 * @author kelli

	11 Digit NIGP Commodity Code pattern: 111-22-33-4444
	Level 1	(xxx00000000)
	Level 2 	(111xx000000)
	Level 3	(11122xx0000)
	Level 4	(1112233xxxx)
 */
public class CommodityGet11DigitNIGPWhereClause extends Task {
    
	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;

		StringBuffer sqlWhere = new StringBuffer();
		sqlWhere.append(" Commodity.status = '02'");
		
		StringBuffer keywordClause = new StringBuffer();
		String	keywords = (String) incomingRequest.get("commodityKeywords");
		if (!Utility.isEmpty(keywords)) {
		    keywords = keywords.trim().toUpperCase();

		    keywords = keywords.replaceAll("%","");
		    
		    if (keywords.indexOf(" ") > 0) {
		        // multiple keywords
		        while(keywords.length() > 0) {
		            String	keyword = "";
		            int ind = keywords.indexOf(" ");

		            if (ind < 0) {
		                keyword = keywords.trim();
		                keywords = "";
		            } else {
		                keyword = keywords.substring(0, ind);
		                keywords = keywords.substring(keywords.indexOf(" ") + 1);
		            }

		            if (keywordClause.length() > 0) {
		                keywordClause.append(" OR UPPER(Commodity.description) LIKE '%" + keyword + "%'");
		            } else {
		                keywordClause.append(" UPPER(Commodity.description) LIKE '%" + keyword + "%'");
		        	}
		    	}
		    } else {
		        keywordClause.append(" UPPER(Commodity.description) LIKE '%" + keywords + "%'");
		    }
		}
		
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
		
		String	retrieveAllCodes = Utility.ckNull((String) incomingRequest.get("retrieveAllCodes"));
		
		StringBuffer sqlWhereTemp = new StringBuffer("Commodity.nigplevel = '1'");
		
		for (int i=0; i < commodityCodes.length; i++) {
			String	commodityCode = commodityCodes[i];
			
			if (!Utility.isEmpty(commodityCode) && commodityCode.length() == 11) {
				String	level1 = commodityCode.substring(0, 3);
				String	level2 = commodityCode.substring(3, 5);
				String	level3 = commodityCode.substring(5, 7);
				String	level4 = commodityCode.substring(7, 11);
				String	level2Clause = " OR (Commodity.commodity like '" + level1 + "%' and Commodity.nigplevel = '2')";
				String	level3Clause = " OR (Commodity.commodity like '" + level1 + level2 + "%' and Commodity.nigplevel = '3')";
			    String	level4Clause = " OR (Commodity.commodity like '" + level1 + level2 + level3 + "%' and Commodity.nigplevel = '4')";
				boolean retrieveLevel2Codes = false;
				boolean retrieveLevel3Codes = false;
				boolean retrieveLevel4Codes = false;
			    
				if ((level2 + level3 + level4).equals("00000000")) {
				    retrieveLevel2Codes = true;
				} else if ((level3 + level4).equals("000000")) {
				    retrieveLevel2Codes = true;
				    retrieveLevel3Codes = true;
				} else {
				    retrieveLevel2Codes = true;
				    retrieveLevel3Codes = true;
				    retrieveLevel4Codes = true;
				}
				
				if (retrieveLevel2Codes && sqlWhereTemp.indexOf(level2Clause) < 0) {
				        sqlWhereTemp.append(level2Clause);
				}
				if (retrieveLevel3Codes && sqlWhereTemp.indexOf(level3Clause) < 0) {
				    sqlWhereTemp.append(level3Clause);
				}
				if (retrieveLevel4Codes && sqlWhereTemp.indexOf(level4Clause) < 0) {
				    sqlWhereTemp.append(level4Clause);
				}
			}
		}
		
		if (sqlWhereTemp.length() > 0) {
			if (sqlWhereTemp.toString().startsWith(" OR ")) {
			    sqlWhereTemp = new StringBuffer(sqlWhereTemp.substring(3));
			}
			sqlWhere.append(" AND (" + sqlWhereTemp.toString() + ") ");
		}
		if (keywordClause.length() > 0) {
			if (keywordClause.toString().startsWith(" OR ")) {
			    keywordClause = new StringBuffer(keywordClause.substring(3));
			}
			sqlWhere.append(" AND (" + keywordClause.toString() + ") ");
		}
		return sqlWhere.toString();
	}
	
}
