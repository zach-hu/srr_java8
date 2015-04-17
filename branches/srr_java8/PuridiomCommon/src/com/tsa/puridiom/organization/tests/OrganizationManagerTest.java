package com.tsa.puridiom.organization.tests;

import com.tsa.puridiom.organization.OrganizationManager;

public class OrganizationManagerTest {
    
	public static void  main (String[] args) throws Exception {
		try {
			boolean validOrganization = false;
			String	organizationId = "PURIDIOM";
			
			validOrganization = OrganizationManager.getInstance().isOrganizationValid(organizationId);
			System.out.println("Organization: " + organizationId + " should be valid: " + validOrganization);
			
			organizationId = "TSA";
			validOrganization = OrganizationManager.getInstance().isOrganizationValid(organizationId);
			System.out.println("Organization: " + organizationId + " should be valid: " + validOrganization);
			
			organizationId = "XYZ";
			validOrganization = OrganizationManager.getInstance().isOrganizationValid(organizationId);
			System.out.println("Organization: " + organizationId + " should be invalid: " + validOrganization);
		
			organizationId = "";
			validOrganization = OrganizationManager.getInstance().isOrganizationValid(organizationId);
			System.out.println("Organization: " + organizationId + " should be invalid: " + validOrganization);
			
			organizationId = null;
			validOrganization = OrganizationManager.getInstance().isOrganizationValid(organizationId);
			System.out.println("Organization: " + organizationId + " should be invalid: " + validOrganization);
			
			System.out.println("OrganizationManagerTest COMPLETE");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}