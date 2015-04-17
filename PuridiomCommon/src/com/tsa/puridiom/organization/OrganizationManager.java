/*
 * Created on Aug 24, 2004
 */
package com.tsa.puridiom.organization;

import com.tsa.puridiom.common.documents.GeneralStatus;
import com.tsa.puridiom.common.utility.BlackBox;
import com.tsa.puridiom.entity.Organization;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author kelli
 */
public class OrganizationManager {

    private static OrganizationManager INSTANCE = new OrganizationManager();
    private HashMap organizations = new HashMap();

	private OrganizationManager() {
		super();
		PuridiomProcess process = null;
		try {
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader(null);
			process = processLoader.loadProcess("organization-retrieve-all.xml");
			Map requestMap = new HashMap();
			// Set 'host' as organizationId so host.cfg.xml is used for the database connection
			requestMap.put("organizationId", "host");
			process.executeProcess(requestMap);

			List organizationList = (List) requestMap.get("organizationList");
			if (organizationList != null) {
			    for (int i = 0; i < organizationList.size(); i++) {
			        Organization organization = (Organization) organizationList.get(i);
			        String	id = organization.getOrganizationId();
			        if (!Utility.isEmpty(id)) {
			            organizations.put(id.toUpperCase(), organization);
			            
			            try {
				            //Reload labels.properties file
				        	PuridiomProcessLoader labelProcessLoader = new PuridiomProcessLoader(id);
							PuridiomProcess labelProcess = labelProcessLoader.loadProcess("labels-refresh.xml");
							HashMap requestParams = new HashMap();
							requestParams.put("organizationId", id);
							labelProcess.executeProcess(requestParams);
			            } catch (Exception e1) {
			            	Log.error(this, "Labels were not loaded for " + id);
			            	Log.error(this, e1.getMessage());
			            }
			        }
			    }
			}
		}
		catch (Exception e) {
			//throw e;
		}
	}

	/**
	 * @return com.tsa.puridiom.organization.OrganizationManager
	 */
	public static OrganizationManager getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new OrganizationManager();
		}
		return INSTANCE;
	}

	public boolean isOrganizationValid(String oid) {
	    boolean valid = false;

	    if (organizations != null && !Utility.isEmpty(oid) && this.organizations.containsKey(oid.toUpperCase())) {
	        Organization organization = (Organization) this.organizations.get(oid);
		    if (organization != null) {
		    	if (organization.getStatus().equals(GeneralStatus.STATUS_TEMPORARY) || organization.getStatus().equals(GeneralStatus.STATUS_PERMANENT)) {
			        Date dateExpires = organization.getDateExpires();
			        if (dateExpires != null && Dates.getDate(Dates.today("", "")).compareTo(dateExpires) <= 0) {
			            valid = true;
			        }
		    	}
		    }
	    }
	    return valid;
	}

	public boolean isOrganizationOnHold(String oid) {
	    boolean onHold = false;

	    if (organizations != null && !Utility.isEmpty(oid) && this.organizations.containsKey(oid.toUpperCase())) {
	        Organization organization = (Organization) this.organizations.get(oid);
		    if (organization != null && organization.getStatus().equals("04")) {
		    	onHold = true;
		    }
	    }
	    return onHold;
	}

	public Organization getOrganization(String oid) {
		Organization organization = null;

	    if (organizations != null && !Utility.isEmpty(oid)) {
		    organization = (Organization) this.organizations.get(oid);
	    }
	    if (organization == null) {
	    	organization = new Organization();
	    }
	    return organization;
	}

	public String getOrganizationName(String oid) {
	    String	organizationName = "";

	    if (organizations != null && !Utility.isEmpty(oid)) {
		    Organization organization = (Organization) this.organizations.get(oid);
		    if (organization != null) {
		        organizationName = organization.getOrganizationName();
		    }
	    }
	    return Utility.ckNull(organizationName);
	}

	public List getOrganizationList() {
	    List list = new ArrayList();
	    if (this.organizations != null) {
	    	Map organizationMap = new TreeMap(organizations);
	        list.addAll(organizationMap.values());
	    }
	    return list;
	}


	public List getOrganizationList(boolean validOnly) {
	    List list = new ArrayList();

	    if (validOnly) {
		    if (this.organizations != null) {
		        Map organizationMap = new TreeMap(organizations);
		    	Iterator iterator = organizationMap.keySet().iterator();
		    	while (iterator.hasNext()) {
		    	    String oid = (String) iterator.next();
		    	    if (this.isOrganizationValid(oid)) {
		    	        list.add(organizationMap.get(oid));
		    	    }
		    	}
		    }
		} else {
	        list = this.getOrganizationList();
	    }
	    return list;
	}
    /**
     * @param organizationId String
     * @exception java.lang.Exception
     */
    public void removeOrganizationFromCache(String organizationId) throws Exception
    {
        try
        {
            if (!Utility.isEmpty(organizationId))
            {
                organizationId = organizationId.trim().toUpperCase();
               	this.organizations.remove(organizationId);
            }
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    /**
     * @param organization Organization
     * @exception java.lang.Exception
     */
    public void setOrganizationInCache(Organization organization) throws Exception
    {
        try
        {
            if (organization == null) throw new Exception ("Organization was null.");

            String	organizationId = organization.getOrganizationId();

            if (!Utility.isEmpty(organizationId))
            {
                this.organizations.put(organizationId, organization);
            }
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    public static void refresh() {
	    INSTANCE = new OrganizationManager();
	}
    
    public int getLicensedBuyers(String oid)
    {
        int licensedBuyers = 0;
        PuridiomProcess process = null;
		try {
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader(null);
			process = processLoader.loadProcess("organizationpackage-retrieve-buyer-count.xml");
			Map requestMap = new HashMap();
			// Set 'host' as organizationId so host.cfg.xml is used for the database connection
			requestMap.put("organizationId", "host");
			requestMap.put("OrganizationPackage_organizationId", oid);
			process.executeProcess(requestMap);

			BigDecimal buyerCount = (BigDecimal) requestMap.get("buyerCount");
			if (buyerCount != null) {
			    licensedBuyers = buyerCount.intValue();
			}
		}
		catch (Exception e) {
			//throw e;
		}
		return licensedBuyers;
    }
    
    public int getLicensedRequisitioners(String oid)
    {
        int licensedRequisitioners = 0;
        PuridiomProcess process = null;
		try {
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader(null);
			process = processLoader.loadProcess("organizationpackage-retrieve-requisitioner-count.xml");
			Map requestMap = new HashMap();
			// Set 'host' as organizationId so host.cfg.xml is used for the database connection
			requestMap.put("organizationId", "host");
			requestMap.put("OrganizationPackage_organizationId", oid);
			process.executeProcess(requestMap);

			BigDecimal requisitionerCount = (BigDecimal) requestMap.get("requisitionerCount");
			if (requisitionerCount != null) {
				licensedRequisitioners = requisitionerCount.intValue();
			}
		}
		catch (Exception e) {
			//throw e;
		}
		return licensedRequisitioners;
    }
}
