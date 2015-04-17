package com.tsa.puridiom.catalogsecurity.tasks;

import java.util.List;
import java.util.Map;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsa.puridiom.common.utility.HiltonUtility;

public class LookupPunchoutCatalogSecurity extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		List resultList = null;
		Object result = "F";
		try {
			Log.debug(this, ">>>>>>>>>>>>>>> Begin --> LookupPunchoutCatalogSecurity" );
			Map incomingRequest = (Map)object;
			String orgId = (String) incomingRequest.get("organizationId");
			DBSession dbs = new DBSession(orgId);

			String	catalogId = (String) incomingRequest.get("catalogId") ;
			String	userId = (String) incomingRequest.get("userId") ;
			String	userDepartment = (String) incomingRequest.get("userDepartment") ;
			String	userNameUdf1 = (String) incomingRequest.get("userNameUdf1") ;
			String	userNameUdf2 = (String) incomingRequest.get("userNameUdf2") ;
			String	userNameUdf3 = (String) incomingRequest.get("userNameUdf3") ;

			Log.debug(this, catalogId + " userId > " + userId + " userDepartment> " + userDepartment +  " userNameUdf1> " + userNameUdf1 + "userNameUdf2> " + userNameUdf2 + "userNameUdf3> " + userNameUdf3 );

			String queryString =
			"Select Catalog.catalogId " +
			"from Catalog Catalog, CatalogSecurity CatalogSecurity " +
			"where ( Catalog.catalogId = CatalogSecurity.catalogId ) AND Catalog.externalCatalog='Y'  AND ( Catalog.catalogId = '" + catalogId + "' )";

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
			queryString += filters;
			Log.debug(this, "filters: \r\n" + queryString);

			resultList = dbs.query(queryString);
			if (resultList != null && resultList.size() > 0)
			{
				result = "Y";
				Log.debug(this, "return records: \r\n" + resultList.size());
			}

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e) {
			Log.debug(this, "Error on LookupPunchoutCatalogSecurity: \r\n" + e.getMessage());
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