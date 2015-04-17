package com.tsa.puridiom.browse.tasks;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.tsa.puridiom.browse.BrowseFilter;
import com.tsa.puridiom.browse.BrowseObject;
import com.tsa.puridiom.browse.BrowseValidationUtility;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;

public class GenerateBrowseFilter extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Object result;
		
		try {
			Map incomingRequest = (Map)object;
			String organizationId = (String)incomingRequest.get("organizationId"); 
			String isActivityCatalogSecurity = (String)PropertiesManager.getInstance(organizationId).getProperty("CATALOG SECURITY DEFAULTS", "CATALOGSECURITYACTIVE", "N");
			BrowseObject b = (BrowseObject) incomingRequest.get("browseObject");
			Object filterColObject = incomingRequest.get("colname");
			Map columnTypes = b.getColumnTypes();

			String fromPage = Utility.ckNull((String) incomingRequest.get("fromPage"));

			if (filterColObject instanceof String[]) {
				String filterColumns[] = (String[]) filterColObject;
				String filterTextValues[] = (String[]) incomingRequest.get("filter_txt");
				String sortValues[] = (String[]) incomingRequest.get("sort");
				String operators[] = (String[]) incomingRequest.get("operator");
				String logicalOperators[] = (String[]) incomingRequest.get("logicalOperator");
				String originalFilters[] = (String[]) incomingRequest.get("originalFilter");
				String previousLogicalOperator = "AND";
				
				if (fromPage.indexOf("main_menu") > 0)
				{
					previousLogicalOperator = "OR";
				}

				if( isActivityCatalogSecurity.equalsIgnoreCase("Y") )
				{
					previousLogicalOperator = "AND";
				}
				
				for (int i=0; i < filterColumns.length; i++) {
					String	filterColumn = filterColumns[i];
					String	filterValue = HiltonUtility.decodeHtml(filterTextValues[i]);
					String	sort = sortValues[i];

					if (!Utility.isEmpty(filterValue) || (!Utility.isEmpty(sort) && !sort.equalsIgnoreCase("N"))) {
						BrowseFilter filter = null;
						String	type = "STRING";

						if (columnTypes.containsKey(filterColumn)){
							type = (String) columnTypes.get(filterColumn);
						}

						filterColumn = filterColumn.replaceAll("_",".");

						if (!Utility.isEmpty(filterValue) || (!Utility.isEmpty(sort) && !sort.equalsIgnoreCase("N")) ) {
							if(filterColumn.equalsIgnoreCase("CatalogItem.description") && logicalOperators[i].equalsIgnoreCase("OR"))
							{
								previousLogicalOperator = logicalOperators[i];
							}
							filter = this.setupBrowseFilter(filterColumn, filterValue, operators[i], previousLogicalOperator, sort, originalFilters[i], type);
							previousLogicalOperator = logicalOperators[i];
							
							b.addBrowseFilter(filter);
						}
					}
				}
			}
			else {
				String filterColumn = (String) filterColObject;
				String filterValue = (String) incomingRequest.get("filter_txt");
				String sort = (String) incomingRequest.get("sort");

				if (!Utility.isEmpty(filterValue) || (!Utility.isEmpty(sort) && !sort.equalsIgnoreCase("N"))) {
					BrowseFilter filter = null;
					String operator = (String) incomingRequest.get("operator");
					String logicalOperator = (String) incomingRequest.get("logicalOperator");
					String originalFilter = (String) incomingRequest.get("originalFilter");
					String type = "STRING";

					if (columnTypes.containsKey(filterColumn)){
						type = (String) columnTypes.get(filterColumn);
					}

					filterColumn = filterColumn.replaceAll("_",".");

					filter = this.setupBrowseFilter(filterColumn, filterValue, operator, logicalOperator, sort, originalFilter, type);

					b.addBrowseFilter(filter);
				}
			}
			result = b;
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e) {
			throw e;
		}
		return result ;
	}
	private BrowseFilter setupBrowseFilter(String filterKey, String filterValue, String operator,
			String logicalOperator, String sort, String original, String type) {
		BrowseFilter filter = new BrowseFilter();
		if (Utility.isEmpty(operator) || !BrowseValidationUtility.permissibleOperators.contains(operator)) {
			operator = "=";
		}
		if (Utility.isEmpty(logicalOperator) || !BrowseValidationUtility.permissibleLogicalOperators.contains(logicalOperator)) {
			logicalOperator = "AND";
		}
		if (Utility.isEmpty(sort) || !BrowseValidationUtility.permissibleSortOperators.contains(sort)) {
			sort = "N";
		}
		if (Utility.isEmpty(original)) {
			original = "N";
		}
		filter.setColumnName(filterKey);
		filter.setValue(filterValue.trim());
		filter.setOperator(operator);
		filter.setLogicalOperator(logicalOperator);
		filter.setSort(sort);
		filter.setOriginalFilter(original.equalsIgnoreCase("Y"));
		filter.setType(type);

		return filter;
	}
}