package com.tsa.puridiom.invoice.approvals.tasks;

import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;

import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Map;
import java.util.StringTokenizer;

public class ApprovalSetOptions extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;

		String 	appUdfName[] = new String[10];
		String	appUdfColumn[] = new String[10];
		String appUdfSection[] = new String[10];
		String	appUdfType[] = new String[10];

		PropertiesManager propertiesManager = PropertiesManager.getInstance((String)incomingRequest.get("organizationId")) ;

		try
		{
            String organizationId = (String) incomingRequest.get("organizationId");
            String userId = (String) incomingRequest.get("userId");
            String approvalFormType = Utility.ckNull((String) incomingRequest.get("approvalFormType"));
            String approvalSection = "INV APPROVALS";
            //if (!approvalFormType.isEmpty()) {
            //    approvalSection = approvalFormType + " APPROVALS";
            //}
            String defineRulesBy = propertiesManager.getProperty(approvalSection, "DEFINERULESBY", "");
            String defineRulesByValue = "";
            String columnLabel = "";
            String columnSection = "";
            String columnName = "";
            String columnType = "";

            // Remove Quotes
            defineRulesBy = defineRulesBy.replaceAll("\"","");

            if (! Utility.isEmpty(defineRulesBy)) {
                // Obtain criteria and value for multiple rule definitions

                //Pick out the parameters
                StringTokenizer st = new StringTokenizer(defineRulesBy,",");
                for (int ix = 1;st.hasMoreElements();ix++) {
                    columnLabel = Utility.ckNull(st.nextToken()).trim();
                    columnSection = Utility.ckNull(st.nextToken()).trim();
                    columnName = Utility.ckNull(st.nextToken()).trim();
                    columnType = Utility.ckNull(st.nextToken()).trim();
                    if (ix == 4) { break ; }
                }

                if (columnSection.equalsIgnoreCase("header") ) {
                    // Header
                    RequisitionHeader requisitionHeader = (RequisitionHeader) incomingRequest.get("requisitionHeader");
                    Class cls = requisitionHeader.getClass() ;
                    Method mth = cls.getMethod("get" + columnName, null);
                    defineRulesByValue = (String) mth.invoke(requisitionHeader, null);
                } else  if (columnSection.equalsIgnoreCase("user")) {
                    UserProfile userProfile = UserManager.getInstance().getUser(organizationId, userId);
                    Class cls = userProfile.getClass() ;
                    Method mth = cls.getMethod("get" + columnName, null);
                    defineRulesByValue = (String) mth.invoke(userProfile, null);
                }
            }

            defineRulesByValue = Utility.ckNull(defineRulesByValue);

            if (!Utility.isEmpty(defineRulesByValue)) {
                defineRulesByValue = defineRulesByValue.trim() + "-";
            }

	        for (int cnt = 0; cnt < 10; cnt++ ) {
	    		String appTemp = propertiesManager.getProperty(approvalSection, defineRulesByValue + "AppUdf" + Integer.toString(cnt + 1),"") ;

                if (cnt == 0 && Utility.isEmpty(appTemp)) {
                    // No Rules Defined for this criteria - default rule definition should be used
                    appTemp = propertiesManager.getProperty(approvalSection, "AppUdf" + Integer.toString(cnt + 1),"") ;
                    defineRulesByValue = "";
                }

	    		// Remove Quotes
	    		appTemp = appTemp.replaceAll("\"","");

	    		if (! Utility.isEmpty(appTemp)) {
	                //Pick out the parameters
	                StringTokenizer st = new StringTokenizer(appTemp,",");
	                for (int ix = 1;st.hasMoreElements();ix++) {
	                	appUdfName[cnt] = st.nextToken() ;
	                	appUdfSection[cnt] = st.nextToken() ;
	                	appUdfColumn[cnt] = st.nextToken() ;
	                	appUdfType[cnt] = st.nextToken();
	                	if (ix == 4) { break ; }
	                }
	            } else {
                	appUdfName[cnt] = null ;
                	appUdfSection[cnt] = null;
                	appUdfColumn[cnt] = null ;
                	appUdfType[cnt] = null ;
	            }
	        }
			this.status = Status.SUCCEEDED;

            if (!Utility.isEmpty(defineRulesByValue)) {
                incomingRequest.put("appRulesDefinedBy", "[" + columnLabel + " = " + defineRulesByValue + "]");
            } else {
                //Standard Rule Definitions
                incomingRequest.put("appRulesDefinedBy", "");
            }
			incomingRequest.put("appUdfName",appUdfName) ;
			incomingRequest.put("appUdfColumn",appUdfColumn) ;
			incomingRequest.put("appUdfSection",appUdfSection) ;
			incomingRequest.put("appUdfType",appUdfType) ;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return null ;
	}
}