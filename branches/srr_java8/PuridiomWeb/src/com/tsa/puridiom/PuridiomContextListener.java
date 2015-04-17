package com.tsa.puridiom;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.hibernate.encryptor.HibernatePBEEncryptorRegistry;

import com.tsa.puridiom.entity.Organization;
import com.tsa.puridiom.organization.OrganizationManager;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.database.DBConfiguration;
import com.tsagate.foundation.utility.Log;

/**
 * Class that allows to know when servlet context is initialized or destroyed,
 * closed SessionFactory when context is destroyed.
 *
 * @author Alexander
 *
 */
public class PuridiomContextListener implements ServletContextListener {

	/**
	 *
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		Log.info(this, "Puridiom - Context Listener  - Initialized");

		StandardPBEStringEncryptor strongEncryptor = new StandardPBEStringEncryptor();
		strongEncryptor.setPassword("tsapuridiom");

		HibernatePBEEncryptorRegistry registry =
			HibernatePBEEncryptorRegistry.getInstance();
		registry.registerPBEStringEncryptor("hibernateEncryptor", strongEncryptor);

		List organizationList = OrganizationManager.getInstance().getOrganizationList(true);
		for (int i = 0; i < organizationList.size(); i++) {
			Organization organization = (Organization) organizationList.get(i);
			String organizationId = organization.getOrganizationId();
			Log.info(this, "Loading properties for the OID: " + organizationId);
			PropertiesManager.getInstance(organizationId);
		}
	}

	/**
	 *
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		Log.info(this, "Puridiom - Context Destroyed");

		RequestIdManager.getInstance().close();

		List organizationList = OrganizationManager.getInstance().getOrganizationList(true);
		for (int i = 0; i < organizationList.size(); i++) {
			Organization organization = (Organization) organizationList.get(i);
			String oid = organization.getOrganizationId();

			Log.info(this, "Cleaning properties of the OID: " + oid);
			if (oid != null && oid.length() > 0) {
				DBConfiguration dbConfiguration = DBConfiguration.getInstance(oid);
				if (dbConfiguration != null) {
					dbConfiguration.closeSessionFactory();
					Log.info(this, "Puridiom - closed session factory");
				}
			}

			PropertiesManager propertiesManager = PropertiesManager.getInstance(oid.toUpperCase());
			propertiesManager.clear();
			PropertiesManager.removeOID(oid);
		}
	}
}
