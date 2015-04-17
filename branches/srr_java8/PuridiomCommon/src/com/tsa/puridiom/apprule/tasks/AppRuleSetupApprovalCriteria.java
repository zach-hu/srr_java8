/*
 * Created on Dec 20, 2005
 */
package com.tsa.puridiom.apprule.tasks;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;

/**
 * @author kelli
 */
public class AppRuleSetupApprovalCriteria extends Task {

	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
        StringBuffer whereClause = new StringBuffer("");

        try {
            String      appUdfColumn[] = (String[]) incomingRequest.get("appUdfColumn") ;
            List dataList = (List) incomingRequest.get("dataList") ;

            int dataCount = dataList.size() ;
            /* Start Rule Loop */

            for (int cx = 0; cx < dataCount; cx++) {
                List rowList = (List) dataList.get(cx) ;
                int cont = 0;
                if (cx > 0) {
                    whereClause.append(" OR ");
                }
                whereClause.append(" (");
                for (int ux = 0; ux < appUdfColumn.length; ux++)  {
                	if(appUdfColumn[ux] != null){
	                    for (int bx = 0; bx < rowList.size(); bx++) {
	                        Hashtable htColumn = (Hashtable) rowList.get(bx) ;
	
	                        String  ruleDataName = (String) htColumn.get("name") ;
	                        if (! ruleDataName.equalsIgnoreCase(appUdfColumn[ux])) {
	                            continue;
	                        }
	
	                        String dataValue = (String) htColumn.get("value") ;
	                        if (dataValue == null) dataValue = "";
	                        dataValue = dataValue.trim() ;
	                        String fldName = "appRule.id.udf" + String.valueOf(ux + 1) + "Code";
	                        cont++; 
	                        if (ux > 0 && cont > 1) {
	                            whereClause.append(" AND ");
	                        }
	                        whereClause.append("(" + this.getAppRuleWhereClause(fldName, dataValue) + ")");
	                    }  // Next bx
                	}
                }   // Next ux
                whereClause.append(") ");
            }   // Next cx
            Log.debug(this, "AppRuleWhereClause: " + whereClause.toString());
        } catch (Exception e) {

        }
		return whereClause.toString();
	}

    private String getAppRuleWhereClause(String fldName, String dataValue) {
        StringBuffer sqlWhere = new StringBuffer();

       sqlWhere.append(fldName + " = '*'");

       if (!Utility.isEmpty(dataValue)) {
           sqlWhere.append(" OR " + fldName + " = '" + dataValue + "'");
       }

       for (int i=1; i < dataValue.length(); i++) {
           sqlWhere.append(" OR " + fldName + " = '%" + dataValue.substring(i) + "'");
           sqlWhere.append(" OR " + fldName + " = '" + dataValue.substring(0,(dataValue.length() - i)) + "%'");
       }

        return sqlWhere.toString();
    }
}
