package com.tsagate.foundation.database;

import java.io.File;
import java.util.HashMap;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.tsagate.foundation.exceptions.DatabaseConnectionException;
import com.tsagate.foundation.utility.Log;
import com.tsagate.properties.DictionaryManager;

/**
 * @author JEFF
 */
public class DBConfiguration {

	private static HashMap instances = new HashMap() ;
	private SessionFactory sessionFactory = null;
	private String confName = "puridiom";
	private AuditInterceptor auditInterceptor = new AuditInterceptor();

	private DBConfiguration() {
	}
	public static DBConfiguration getInstance(String  dbcfg)
	{
		DBConfiguration instance = (DBConfiguration) instances.get(dbcfg) ;
		if (instance == null)
		{
			instance = new DBConfiguration();
			instance.setConfName(dbcfg) ;
			instances.put(dbcfg, instance) ;
		}
		return instance;
	}

	public Session getSession() throws Exception
	{
		Session session = null;
		String	path = DictionaryManager.getInstance("host", this.confName).getProperty("default-database-location", "/com/tsa/puridiom/entity/hbm/");

		try
		{
			if (this.sessionFactory == null)
			{
			    File cfgFile = new File(path + this.confName.toLowerCase() + ".cfg.xml");
			    if (!cfgFile.exists())
			    {
			        Log.error(this, "The database configuration file: " + cfgFile.getAbsolutePath() + " does not exist.");
			    }
			    this.sessionFactory = new Configuration().configure(cfgFile).buildSessionFactory();
			}
			auditInterceptor = new AuditInterceptor();
			auditInterceptor.setOrganizationId(this.confName);
			auditInterceptor.setUserId("test");
			String auditMe = DictionaryManager.getInstance("host", this.confName).getProperty("auditTrail", "N");
			if(auditMe.equalsIgnoreCase("Y"))
			{
				session = this.sessionFactory.openSession(auditInterceptor);
			}
			else
			{
				session = this.sessionFactory.openSession();
			}
		}
		catch (HibernateException e)
		{
			Log.error(this, e);
		    throw new DatabaseConnectionException("A database connection could not be established.");
		}
		catch (Throwable ex)
		{
	         // We have to catch Throwable, otherwise we will miss
	         // NoClassDefFoundError and other subclasses of Error
	         //system.out.println("Missing jar files");
	         throw new ExceptionInInitializerError(ex);
	      }

		return session;
	}

	/**
	 * Returns the confName.
	 * @return String
	 */
	public String getConfName() {
		return confName;
	}

	/**
	 * Sets the confName.
	 * @param confName The confName to set
	 */
	public void setConfName(String confName) {
		this.confName = confName;
	}
	public AuditInterceptor getAuditInterceptor() {
		return auditInterceptor;
	}
	
	public void closeSessionFactory() {
		if (this.sessionFactory != null) {
			sessionFactory.close();
		}
	}
	
}
