/*
 * Created on September 14, 2006
 */
package com.tsa.puridiom.subcommodity.tasks;

import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;

import java.util.Map;
import java.util.StringTokenizer;

/**
 * @author kelli

	Commodity Code pattern setup as properties
 */
public class SubCommodityGetTreeViewWhereClause extends Task {
    
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
		
		StringBuffer sqlWhere = new StringBuffer();
		sqlWhere.append(" SubCommodity.status = '02'");
		
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
		                keywordClause.append(" OR UPPER(SubCommodity.description) LIKE '%" + keyword + "%'");
		            } else {
		                keywordClause.append(" UPPER(SubCommodity.description) LIKE '%" + keyword + "%'");
		        	}
		    	}
		    } else {
		        keywordClause.append(" UPPER(SubCommodity.description) LIKE '%" + keywords + "%'");
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
		
		StringBuffer sqlWhereTemp = new StringBuffer("SubCommodity.nigplevel = '1'");
		String	levelDefaultTmp = "00000000000000000000";
		
		for (int i=0; i < commodityCodes.length; i++) {
			String	commodityCode = commodityCodes[i];
			String	level[] = new String[categoryCount];
			String	defaultLevel[] = new String[categoryCount];
			String	levelClause[] = new String[categoryCount];
			
			if (!Utility.isEmpty(commodityCode) && commodityCode.length() == categoryEndPos[categoryCount - 1]) {
			    for (int ip=0; ip < categoryCount; ip++) {
			        level[ip] = commodityCode.substring(categoryStartPos[ip], categoryEndPos[ip]);
			        
			        levelClause[ip] = " OR (SubCommodity.commodity like '";
			        for (int ipx = 0; ipx < ip; ipx++) {
			            levelClause[ip] = levelClause[ip] + level[ipx];
			        }
			        levelClause[ip] = levelClause[ip] + "%' and SubCommodity.nigplevel = '" + (ip + 1) + "')";
			        
			        defaultLevel[ip] = level[ip].substring(0, (categoryEndPos[ip] - categoryStartPos[ip]));
			    }
			    
			    for (int ipx = categoryCount - 1; ipx > 0; ipx--) {
		            String	tmp = "";
		            String	def = "";
		            
	                tmp = level[ipx] + tmp;
	                def = defaultLevel[ipx] + def;
		            
		            if (level[ipx].equals(defaultLevel[ipx])) {
		                for (int ipc = ipx; ipc > 0; ipc--) {
			                if (sqlWhereTemp.indexOf(levelClause[ipc]) < 0) {
				                sqlWhereTemp.append(levelClause[ipc]);
				            }
		                }
		                break;
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
		if (keywordClause.length() > 0) {
			if (keywordClause.toString().startsWith(" OR ")) {
			    keywordClause = new StringBuffer(keywordClause.substring(3));
			}
			sqlWhere.append(" AND (" + keywordClause.toString() + ") ");
		}
		return sqlWhere.toString();
	}
	
}
