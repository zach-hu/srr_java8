package com.tsa.puridiom.autogen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.AutoGen;
import com.tsa.puridiom.organization.OrganizationManager;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;

/**
 * Creation date: August 27, 2010
 * @author: Kelli
 */
public class AutoGenManager {
	private static AutoGenManager INSTANCE = new AutoGenManager();
	private HashMap organizations = new HashMap();

	/**
	 * AutoGenManager constructor comment.
	 */
	private AutoGenManager() {
		super();
	}

	/**
	 * @return com.tsa.puridiom.autogen.AutoGenManager
	 */
	public static AutoGenManager getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new AutoGenManager();
		}
		return INSTANCE;
	}

	/**
	 * @return java.lang.String
	 * @param organizationId java.lang.String
	 * @param organizationId java.lang.String
	 * @exception java.lang.Exception The exception description.
	 */
	public List getActiveFiscalYears(String organizationId, String documentType) throws java.lang.Exception {
		List autoGenList = null;

		try {
			if (!HiltonUtility.isEmpty(organizationId)) {
				Map fiscalYears = new HashMap();
				if (this.organizations.containsKey(organizationId)) {
					fiscalYears = (Map) this.organizations.get(organizationId);
				}
				if (fiscalYears.containsKey(documentType)) {
					autoGenList = (List) fiscalYears.get(documentType);
				}
				if (autoGenList == null) {
					Map processParameters = new HashMap();
					processParameters.put("organizationId", organizationId);
					processParameters.put("AutoGen_documentType", documentType);

					PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
					PuridiomProcess process = processLoader.loadProcess("autogen-retrieve-by-documenttype.xml");

					process.executeProcess(processParameters);

					autoGenList = (List) processParameters.get("autoGenList");
					if (autoGenList != null) {
					    fiscalYears.put(documentType, autoGenList);
					}

					this.organizations.put(organizationId, fiscalYears);
				}
			}
			
			return autoGenList;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}
	}
	
    public static void refresh() {
        INSTANCE = new AutoGenManager();
    }

    public void refresh(String organizationId) {
    	// Remove organization from map to force retrieve of records
    	this.organizations.remove(organizationId);
    }
}
