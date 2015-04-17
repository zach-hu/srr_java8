/*
 * Created on March 15, 2005
 */
package com.tsa.puridiom.catalogsecurity.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.UserProfile;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.util.*;
/**
 * @author Richard
 */
public class CatalogRetrieveActiveByCatalogSecurity extends Task
{
	/* (non-Javadoc)
	 * @see com.tsagate.common.processengine.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;

		UserProfile user = (UserProfile) incomingRequest.get("userProfile");
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		List result = null;
		try
		{
			String userId = (String) user.getUserId();
			String userDepartment = (String) user.getDepartment();
			String userNameUdf1 = (String) user.getNameUdf1();
			String userNameUdf2 = (String) user.getNameUdf2();
			String userNameUdf3 = (String) user.getNameUdf3();

			String queryString = "Select distinct Catalog from Catalog Catalog, CatalogSecurity CatalogSecurity " +
								 "where Catalog.catalogId = CatalogSecurity.catalogId and ( Catalog.status = '02' or (Catalog.status = '01' and Catalog.dateExpires >= ?)) ";

			String 	filters = " AND ( ";

			if(!HiltonUtility.isEmpty(userId)){
				filters+= " ( CatalogSecurity.accessType = 'UI'  AND ( " + buildWildcardFilters(userId) + ") )";
			}
			else
			{
				filters+= " ( CatalogSecurity.accessType = 'UI'  AND CatalogSecurity.accessId = '*' ) ";
			}

			if(!HiltonUtility.isEmpty(userDepartment)){
				filters+= " OR ( CatalogSecurity.accessType = 'UD'  AND ( " + buildWildcardFilters(userDepartment) + ") )";
			}
			else
			{
				filters+= " OR ( CatalogSecurity.accessType = 'UD'  AND CatalogSecurity.accessId = '*' ) ";
			}

			if(!HiltonUtility.isEmpty(userNameUdf1)){
				filters+= " OR ( CatalogSecurity.accessType = 'UU1'  AND ( " + buildWildcardFilters(userNameUdf1) + ") )";
			}
			else
			{
				filters+= " OR ( CatalogSecurity.accessType = 'UU1'  AND CatalogSecurity.accessId = '*' ) ";
			}

			if(!HiltonUtility.isEmpty(userNameUdf2)){
				filters+= " OR ( CatalogSecurity.accessType = 'UU2'  AND ( " + buildWildcardFilters(userNameUdf2) + ") )";
			}
			else
			{
				filters+= " OR ( CatalogSecurity.accessType = 'UU2'  AND CatalogSecurity.accessId = '*' ) ";
			}

			if(!HiltonUtility.isEmpty(userNameUdf3)){
				filters+= " OR ( CatalogSecurity.accessType = 'UU3'  AND ( " + buildWildcardFilters(userNameUdf3) + ") )";
			}
			else
			{
				filters+= " OR ( CatalogSecurity.accessType = 'UU3'  AND CatalogSecurity.accessId = '*' ) ";
			}
			filters+=" )";
			queryString += filters + " order by Catalog.catalogId";

			result = dbs.query(queryString, new Object[] { Dates.getDate(Dates.today("", (String) incomingRequest.get("userTimeZone"))) }, new Type[] { Hibernate.DATE }) ;

			Log.debug(this, "filters: \r\n" + queryString);

			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{	Log.debug(this, "Error on the class CatalogRetrieveActiveByCatalogSecurity: \r\n" + e.getMessage());
			e.printStackTrace();
			throw e;
		}

		return result ;
	}

	String buildWildcardFilters(String filterValue) {
		String filters = " CatalogSecurity.accessId = '*'";
		for (int i = 1; i <= filterValue.length(); i++) {
			filters += " OR CatalogSecurity.accessId = '" + filterValue.substring(0, i);
			if (i < filterValue.length()) {
				filters += "*'";
			}
			else{
				filters += "' ";
			}
		}
		return filters;
	   }

}
