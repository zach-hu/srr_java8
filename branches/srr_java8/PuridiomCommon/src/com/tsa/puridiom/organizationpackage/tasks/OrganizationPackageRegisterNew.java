/*
 * Created on October 13, 2009
 */
package com.tsa.puridiom.organizationpackage.tasks;

import com.tsa.puridiom.common.documents.GeneralStatus;

import com.tsa.puridiom.entity.Organization;
import com.tsa.puridiom.entity.OrganizationPackage;
import com.tsa.puridiom.entity.PackagePricing;
import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.organization.OrganizationManager;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author Kelli
 */
public class OrganizationPackageRegisterNew extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		boolean closeDBS = false;
		
		try {
			if (dbs == null || !dbs.getConfigId().equalsIgnoreCase("host")) {
				//	Always use host database configuration for this table
				dbs = new DBSession("host") ;
				dbs.startTransaction();
				closeDBS = true;
			}

			String organizationId = (String) incomingRequest.get("organizationId");
			String icPackageStr = (String) incomingRequest.get("icPackage");
			BigDecimal icPackage = new BigDecimal(0);
			try {
				icPackage = new BigDecimal(icPackageStr);
			} catch (Exception e1) {
				throw new Exception("Invalid Package Selection.  Registration cannot be completed.");
			}

			Organization organization = (Organization)incomingRequest.get("organization");
			PackagePricing packagePricing = (PackagePricing)incomingRequest.get("packagePricing");
			UserProfile userProfile = (UserProfile)incomingRequest.get("userProfile");
			
			if (organization == null) {
				organization = OrganizationManager.getInstance().getOrganization(organizationId);
			}
			
			/*********************************************
			 * Create new OrganizationPackage record
			 *********************************************/
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();

			OrganizationPackage organizationPackage = new OrganizationPackage();
			organizationPackage.setOrganizationId(organizationId);
			organizationPackage.setIcPackage(icPackage);
			organizationPackage.setIcOrgPackage(new BigDecimal(ukg.getUniqueKey().toString()));
			organizationPackage.setBuyerCount(packagePricing.getBuyerCount());
			organizationPackage.setDatePurchased(Dates.getCurrentDate(""));
			organizationPackage.setPurchasedBy(userProfile.getDisplayName());
			organizationPackage.setQuantity(new BigDecimal(1));
			organizationPackage.setRequisitionerCount(packagePricing.getRequisitionerCount());
			organizationPackage.setStatus(GeneralStatus.STATUS_ON_HOLD);
			organizationPackage.setTotal(packagePricing.getPackagePrice());
			organizationPackage.setUnitPrice(packagePricing.getPackagePrice());
			organizationPackage.setPackageType(packagePricing.getPackageType());
			organizationPackage.setDateActive(Dates.getCurrentDate(""));
			if (organization != null) {
				organizationPackage.setDateExpires(organization.getDateExpires());
			} else {
				organizationPackage.setDateExpires(Dates.getCurrentDate(""));
			}
			
			dbs.add(organizationPackage);
			if (closeDBS) {
				dbs.commit();
			}

			result = organizationPackage;
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		} finally {
			if (dbs != null && closeDBS) {
				dbs.close();
			}
		}

		return result;
	}
}
