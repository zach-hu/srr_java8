package com.tsa.puridiom.browse.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.browse.BrowseGroupFilter;
import com.tsa.puridiom.browse.BrowseObject;
import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsa.puridiom.common.utility.HiltonUtility;

public class GenerateBrowseFilterFromCatalogSecurity extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Object result;

		try {
			Log.debug(this, ">>>>>>>>>>>>>>> Begin - GenerateBrowseFilterFromCatalogSecurity" );
			Map incomingRequest = (Map)object;
			String	organizationId = (String) incomingRequest.get("organizationId") ;
			BrowseObject b = (BrowseObject) incomingRequest.get("browseObject");
			String sqlFrom = b.getSqlFrom();
			b.setSqlFrom(sqlFrom + ", CatalogSecurity as CatalogSecurity");
			String sqlWhere = b.getSqlWhere() + " and (CatalogSecurity.id.catalogId = Catalog.catalogId) ";
			List groupFilters = b.getGroupFilters();
			for (int ix=0; ix < groupFilters.size(); ix++) {
				BrowseGroupFilter gf = (BrowseGroupFilter) groupFilters.get(ix);
				String fromGroupFilter = gf.getSqlFrom();
				gf.setSqlFrom(fromGroupFilter + ", CatalogSecurity as CatalogSecurity");
			}
			
			String	userId = (String) incomingRequest.get("userId") ;
			UserProfile user = UserManager.getInstance().getUser(organizationId, userId);

			String userDepartment = user.getDepartment();
			String userNameUdf1 = user.getNameUdf1();
			String userNameUdf2 = user.getNameUdf2();
			String userNameUdf3 = user.getNameUdf3();

			Log.debug(this, " userId> " + userId + " userDepartment>" + userDepartment +  "userNameUdf1> " + userNameUdf1 + " userNameUdf2>" + userNameUdf2 + " userNameUdf3>" + userNameUdf3 );

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

			filters += " AND CatalogSecurity.id.catalogId = Catalog.catalogId";
			
			b.setSqlWhere(sqlWhere + filters);
			Log.debug(this, "sqlWhere: \r\n" + sqlWhere + filters);
			result = b;
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e) {
			Log.debug(this, e.getMessage());
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