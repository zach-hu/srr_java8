package com.tsa.puridiom.browse.tasks;

import com.tsa.puridiom.browse.BrowseFilter;
import com.tsa.puridiom.browse.BrowseGroupFilter;
import com.tsa.puridiom.browse.BrowseObject;
import com.tsa.puridiom.browse.BrowseValidationUtility;
import com.tsa.puridiom.commodity.tasks.CommodityGetUNSPSCWhereClause;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.*;

import java.util.*;

import org.owasp.esapi.Encoder;
import org.owasp.esapi.codecs.OracleCodec;
import org.owasp.esapi.reference.DefaultEncoder;

public class GroupFilterSelectionRetrieve extends Task
{
	Encoder encoder = DefaultEncoder.getInstance();
	
	public Object  executeTask (Object object) throws Exception
	{
		List result;
		try {
			Map incomingRequest = (Map)object;
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String	o = (String) incomingRequest.get("organizationId");
            String userId = (String) incomingRequest.get("userId");
            String userDateFormat = (String) incomingRequest.get("userDateFormat");
			BrowseObject b = (BrowseObject) incomingRequest.get("browseObject");
			List	groupFilters = b.getGroupFilters();
			List	groupFilterOptions = new ArrayList();
			List commodities = new ArrayList();
			//boolean unspscCommodities = PropertiesManager.getInstance(o).getProperty("MISC", "CommodityType", "").equalsIgnoreCase("UNSPSC");
			boolean unspscCommodities = false;
			boolean	commoditySet = false;

            PropertiesManager propertiesManager = PropertiesManager.getInstance(o);

            if (Utility.isEmpty(userDateFormat)) {
                userDateFormat = propertiesManager.getProperty("MISC", "DateFormat", "MM-dd-yyyy");
            }

			for (int ix=0; ix < groupFilters.size(); ix++) {
				BrowseGroupFilter gf = (BrowseGroupFilter) groupFilters.get(ix);
				StringBuffer	query = new StringBuffer();
				StringBuffer	queryFilter = new StringBuffer();

				if (!Utility.isEmpty(gf.getType()) && gf.getType().equals("CostRange")) {
					incomingRequest.put("CostRange_itemType", incomingRequest.get("as_item_type"));
				}

				List dateArguments = new ArrayList();

				if (Utility.isEmpty(gf.getType()) || !gf.getType().equals("Keywords")) {
					query.append(gf.getSqlSelect() + " from " + gf.getSqlFrom() + " where 1 = 1");

					if (Utility.ckNull(gf.getSqlWhere()).length() > 0) {
						query.append(" and ( " + gf.getSqlWhere() + ")");
					}
					if (Utility.ckNull(b.getSqlWhere()).length() > 0) {
					    String	sqlWhere = b.getSqlWhere();
		                sqlWhere = sqlWhere.replaceAll(":as_userid", "'" + userId + "'");
		                if (sqlWhere.indexOf(":as_today") > 0) {
		                    dateArguments.add(Dates.getDate(Dates.today(userDateFormat, (String) incomingRequest.get("userTimeZone"))));
		                }
		                sqlWhere = sqlWhere.replaceAll(":as_today", "?");

		                query.append(" and ( " + sqlWhere + ")");
					}

					List filters = b.getBrowseFilters();
					if (filters != null) {
						for (int i=0; i < filters.size(); i++) {
							BrowseFilter filter = (BrowseFilter) filters.get(i);
							String	key = filter.getColumnName();
							
							if (!b.validateColumn(key)) {
		                    	//skip this filter
		                    	continue;
		                    }
						
							String	value = encoder.encodeForSQL(new OracleCodec(), filter.getValue());
							
							String	operator = filter.getOperator();
							String	logicalOperator = filter.getLogicalOperator();
							
							if (!BrowseValidationUtility.permissibleOperators.contains(operator)) {
		                    	operator = "=";
		                    }
		                    
		                    if (!BrowseValidationUtility.permissibleLogicalOperators.contains(logicalOperator)) {
		                    	logicalOperator = "AND";
		                    }
							
							String	type = filter.getType();

							if (!Utility.isEmpty(value)) {
								if (Utility.isEmpty(operator)) {
									operator = "=";
								}
								if (Utility.isEmpty(logicalOperator)) {
									logicalOperator = "AND";
								}
								if (Utility.isEmpty(type)) {
									type = "STRING";
								}

								if (queryFilter.length() > 0) {
									queryFilter.append(" " + logicalOperator +  " ");
								}

								if (type.equalsIgnoreCase("DATE")) {
									dateArguments.add(Dates.getDate(userDateFormat, value));
									queryFilter.append(" " + key + " " + operator + " ?");
								}
								else if (operator.equalsIgnoreCase("LIKE")){
									queryFilter.append(" UPPER(" + key + ") " + operator + " '" + value + "'");
								}
								else {
									queryFilter.append(" " + key + " " + operator + " '" + value + "'");
								}

								if (key.indexOf(".commodity") > 0) {
								    if (!Utility.isEmpty(value)) {
								        commoditySet = true;
								        if (value.indexOf("%") > 0) {
								            String commodity = "00000000";
								            for (int iv = 0; iv < value.length(); iv++) {
								                char temp = value.charAt(iv);
								                if (temp != '%') {
								                    if (iv == 0) {
								                        commodity = temp + commodity.substring(iv + 1);
								                    } else if (commodity.length() >= (iv + 1)) {
								                        commodity = commodity.substring(0, iv) + temp + commodity.substring(iv + 1);
								                    } else {
								                        commodity = commodity.substring(0, iv) + temp;
								                    }
								                }
								            }
								            if (!commodities.contains(commodity)) {
								                commodities.add(commodity);
								            }
								        }
								    }
								}
							}
						}
					}

					if (queryFilter.length() > 0) {
						query.append("AND (" + queryFilter + " )");
					}

					if (gf.getType().equalsIgnoreCase("Commodity") && unspscCommodities) {
					    String originalQueryString = query.toString();
					    List	commodityList = null;
					    CommodityGetUNSPSCWhereClause commodityWhere = new CommodityGetUNSPSCWhereClause();

					    int attempts = 0;
					    while ((commodityList == null || commodityList.size() == 0) && attempts <= 3) {
					        query = new StringBuffer(originalQueryString);

						    String	commodityArray[] = new String[commodities.size()];
							commodities.toArray(commodityArray);
							incomingRequest.put("Commodity_commodity", commodityArray);
							incomingRequest.put("retrieveAllCodes", "N");

							if (attempts > 0) {
						        incomingRequest.put("retrieveAllFamilies", "Y");
						        if (attempts > 1) {
						            incomingRequest.put("retrieveAllGroups", "Y");
						            if (attempts > 2) {
						                incomingRequest.put("retrieveAllCommodities", "Y");
						            }
						        }
							}

							String	unspscWhere = (String) commodityWhere.executeTask(incomingRequest);
							if (!Utility.isEmpty(unspscWhere)) {
							    query.append(" AND (" + unspscWhere + ")");
							}

							if (!Utility.isEmpty(gf.getSqlGroupBy())) {
								query.append(" group by " + gf.getSqlGroupBy());
							}
							if (!Utility.isEmpty(gf.getSqlOrderBy())) {
								query.append(" order by " + gf.getSqlOrderBy());
							}

							Log.debug(this, "group filter query: " + query.toString());

							Object arguments[] = new Object[dateArguments.size()];
							for (int i=0; i < dateArguments.size(); i ++) {
								arguments[i] = dateArguments.get(i);
							}
							commodityList = dbs.query(query.toString(), arguments) ;

							attempts++;
					    }

						gf.setSelectionValues(commodityList);
					} else {
						if (!Utility.isEmpty(gf.getSqlGroupBy())) {
							query.append(" group by " + gf.getSqlGroupBy());
						}
						if (!Utility.isEmpty(gf.getSqlOrderBy())) {
							query.append(" order by " + gf.getSqlOrderBy());
						}

						Log.debug(this, "group filter query: " + query.toString());

						Object arguments[] = new Object[dateArguments.size()];
						for (int i=0; i < dateArguments.size(); i ++) {
							arguments[i] = dateArguments.get(i);
						}
						List list = dbs.query(query.toString(), arguments) ;

						gf.setSelectionValues(list);
					}
				}
			}

			result = groupFilters;

			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e) {
		    e.printStackTrace();
			throw e;
		}
		return result ;
	}
}