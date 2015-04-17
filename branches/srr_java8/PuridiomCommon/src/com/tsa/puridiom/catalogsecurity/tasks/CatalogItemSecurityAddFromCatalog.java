/*
 * Created on Aug 19, 2003
 */
package com.tsa.puridiom.catalogsecurity.tasks;

import java.util.*;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.CatalogSecurity;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;
import com.tsa.puridiom.property.PropertiesManager;
/**
 * @author Administrator
 */
public class CatalogItemSecurityAddFromCatalog extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
		Object result = null;

		try {
			String organizationId = (String) incomingRequest.get("organizationId");
			String userDateFormat = (String) incomingRequest.get("userDateFormat");
			PropertiesManager propertiesManager = PropertiesManager.getInstance(organizationId) ;
			CatalogSecurity catalogSecurity = (CatalogSecurity) incomingRequest.get("catalogSecurity");
			String isCatalog = HiltonUtility.ckNull((String) incomingRequest.get("isCatalog"));
			String catalogId = (String) catalogSecurity.getCatalogId();
			String accesType = (String) catalogSecurity.getAccessType();
			String accessId = (String)catalogSecurity.getAccessId();

			if (HiltonUtility.isEmpty(userDateFormat)) {
				userDateFormat = propertiesManager.getProperty("MISC","DateFormat","MM-dd-yyyy");
			}

			String today = Dates.today(userDateFormat, (String) incomingRequest.get("userTimeZone")) ;

			if( isCatalog.equalsIgnoreCase("V"))
			{
				DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;

				String queryString = "from CatalogSecurity as cs " +
									  "where cs.catalogId = ? and cs.itemNumber != '0' "	+
									         "and cs.accessType = ? ";

				List resultList = dbs.query(queryString, new Object[] {catalogId, accesType} , new Type[] { Hibernate.STRING, Hibernate.STRING}) ;

				if (resultList != null && resultList.size() > 0)
				{
					for (int i=0; i < resultList.size(); i++)
					{
						CatalogSecurity catalogItemSecurity = (CatalogSecurity) resultList.get(i);

						if( !accessId.equalsIgnoreCase(catalogItemSecurity.getAccessId()) )
						{
							PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
							PuridiomProcess process = processLoader.loadProcess("catalogsecurity-add.xml");

							Map updateParameters = new HashMap();

							updateParameters.put("organizationId", organizationId);
							updateParameters.put("dbsession", incomingRequest.get("dbsession"));

							updateParameters.put("CatalogSecurity_catalogId", (String)catalogSecurity.getCatalogId());
							updateParameters.put("CatalogSecurity_itemNumber", (String)catalogItemSecurity.getItemNumber());
							updateParameters.put("CatalogSecurity_accessType", (String)catalogSecurity.getAccessType());
							updateParameters.put("CatalogSecurity_accessId", (String)catalogSecurity.getAccessId());
							updateParameters.put("CatalogSecurity_owner", (String)catalogSecurity.getOwner());
							updateParameters.put("CatalogSecurity_dateEntered", today);
							updateParameters.put("CatalogSecurity_dateChanged", today);
							updateParameters.put("CatalogSecurity_lastChangedBy", (String)catalogSecurity.getCatalogId());

							process.executeProcess(updateParameters);

							catalogSecurity.setAccessId(accessId);

							dbs.add(catalogSecurity);
							if(dbs.getStatus() != Status.SUCCEEDED)
							{
								throw new TsaException("error ocurred at update catalog security from  for " + catalogSecurity.getItemNumber() );

							}
						}
					}
				}

			}

			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			Log.error(this, "An Error occurred at CatalogItemSecurityAddFromCatalog" + e);
			e.printStackTrace();
			throw e;

		}
		return result;
	}
}
