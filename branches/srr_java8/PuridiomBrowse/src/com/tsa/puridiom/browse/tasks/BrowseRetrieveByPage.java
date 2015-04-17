package com.tsa.puridiom.browse.tasks;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.owasp.esapi.Encoder;
import org.owasp.esapi.codecs.OracleCodec;
import org.owasp.esapi.reference.DefaultEncoder;

import com.tsa.puridiom.browse.BrowseColumn;
import com.tsa.puridiom.browse.BrowseFilter;
import com.tsa.puridiom.browse.BrowseObject;
import com.tsa.puridiom.browse.BrowseValidationUtility;
import com.tsa.puridiom.browse.ReportDates;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.database.SearchResult;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;

public class BrowseRetrieveByPage extends Task
{
	Encoder encoder = DefaultEncoder.getInstance();

    public Object executeTask(Object object) throws Exception
    {
        StringBuffer query = new StringBuffer();
        Object result;
        try
        {
            Map incomingRequest = (Map) object;
            DBSession dbs = (DBSession) incomingRequest.get("dbsession");
            String organizationId = (String) incomingRequest.get("organizationId");
            String userId = (String) incomingRequest.get("userId");
            String userTimeZone = (String) incomingRequest.get("userTimeZone");
            String userDateFormat = (String) incomingRequest.get("userDateFormat");
            String vendorId = (String) incomingRequest.get("vendorId");
            BrowseObject b = (BrowseObject) incomingRequest.get("browseObject");
            String execute = (String)incomingRequest.get("execute");
            PropertiesManager propertiesManager = PropertiesManager.getInstance(organizationId);

            if (Utility.isEmpty(userDateFormat)) {
                userDateFormat = propertiesManager.getProperty("MISC", "DateFormat", "MM-dd-yyyy");
            }

            if(execute == null){		execute = "Y";	}
            StringBuffer queryCount = new StringBuffer();
            StringBuffer queryFilter = new StringBuffer();
            StringBuffer orderBy = new StringBuffer();
            String sqlFrom = b.getSqlFrom();
            String sqlWhere = b.getSqlWhere();
            String sqlSelect = b.getSqlSelect();
            String sqlGroupBy = b.getSqlGroupBy();
            String sqlOrderBy = b.getSqlOrderBy();
            String sortColumn = "";
//        	String dbproduct = dbs.getSqlConnection().getMetaData().getDatabaseProductName() ;

        	if (propertiesManager.getProperty("PO OPTIONS", "RESTRICTCONTRACTACCESS", "N").equalsIgnoreCase("Y"))
        	{
        		if (UserManager.getInstance().getUserRole(organizationId, userId).getAccessRights("POCT") < 1 && sqlFrom.indexOf("PoHeader") >= 0) {
            		sqlWhere = sqlWhere + " and ( PoHeader.poType <> 'CT' ) ";
            	}
        	}

            query.append(sqlSelect);
            query.append(" from " + sqlFrom + " where 1 = 1");

            if(sqlSelect.indexOf("DISTINCT") != -1){
            	BrowseColumn browseColumns[] = b.getBrowseColumns();

            	for(int i=0; i < browseColumns.length ; i++ )
            	{
            		BrowseColumn browseColumn = browseColumns[i];
            		if(browseColumn.isDistinct()){
            			queryCount.append("Select count(DISTINCT ");
            			String columnName = browseColumn.getColumnName();
                        queryCount.append(columnName.replace("_", "."));
                        queryCount.append(")");
            			break;
            		}
            	}
            }else{
            	queryCount.append("Select count(*) ");
            }

            queryCount.append(" from ");
            queryCount.append(sqlFrom + " where 1 = 1");

            List dateArguments = new ArrayList();
            List dateArgumentsCount = new ArrayList();
            List filters = b.getBrowseFilters();
            int openParenthesis = 0;

            if (Utility.ckNull(sqlWhere).length() > 0)
            {
                sqlWhere = sqlWhere.replaceAll(":as_userid", "'" + userId + "'");
                sqlWhere = sqlWhere.replaceAll(":as_vendorid", "'" + vendorId + "'");

                if (execute.equalsIgnoreCase("Y"))
				{
					int index;
					while ((index = sqlWhere.indexOf(BrowseDateRanges.AS_DATE)) > 0)
					{
						index += BrowseDateRanges.AS_DATE.length();
						dateArguments.add(Dates.getDate(sqlWhere.substring(index, index + userDateFormat.length())));
						dateArgumentsCount.add(Dates.getDate(sqlWhere.substring(index, index + userDateFormat.length())));
						sqlWhere = sqlWhere.replaceFirst(BrowseDateRanges.AS_DATE + "\\d{4}\\-\\d{2}\\-\\d{2}", "?");
					}

					while (sqlWhere.indexOf(":as_today") > 0)
					{
						sqlWhere = sqlWhere.replaceFirst(":as_today", "?");
						dateArguments.add(Dates.getDate(Dates.today(userDateFormat, userTimeZone)));
						dateArgumentsCount.add(Dates.getDate(Dates.today(userDateFormat, userTimeZone)));
					}

					while (sqlWhere.indexOf(":as_baseReportDate") > 0)
					{
						sqlWhere = sqlWhere.replaceFirst(":as_baseReportDate", "?");
						dateArguments.add(BrowseRetrieve.baseReportDate());
						dateArgumentsCount.add(BrowseRetrieve.baseReportDate());
					}

					if (sqlWhere.indexOf(":as_fiscalstart") > 0)
					{
						String fyBegin = propertiesManager.getProperty("Misc", "fybegin", "1");
						Date fyStartDate = Dates.getFiscalYearStartDate(Integer.parseInt(fyBegin), userTimeZone);

						while (sqlWhere.indexOf(":as_fiscal") > 0)
						{
							sqlWhere = sqlWhere.replaceFirst(":as_fiscalstart", "?");
							dateArguments.add(fyStartDate);
							dateArgumentsCount.add(fyStartDate);
						}
					}

					if (sqlWhere.indexOf(":as_fiscalyear") > 0)
					{
						String fiscalYear = HiltonUtility.getFiscalYear(organizationId, userTimeZone);

						while (sqlWhere.indexOf(":as_fiscalyear") > 0)
						{
							sqlWhere = sqlWhere.replaceFirst(":as_fiscalyear", "?");
							dateArguments.add(fiscalYear);
							dateArgumentsCount.add(fiscalYear);
						}
					}
				}

	            int istart = sqlWhere.indexOf("@");
				while (istart > 0) {
				    int	iend = sqlWhere.indexOf("#");
				    String	variable = sqlWhere.substring(istart + 1, iend);
				    String	replaceValue = HiltonUtility.ckNull((String) incomingRequest.get(variable));

				    if (b.requireAttributeValues() && Utility.isEmpty(replaceValue)) {
				        b.setAttributeSet(false);
				        return new ArrayList();
				    } else {
				        replaceValue = replaceValue.trim();
				        replaceValue = "'" + replaceValue + "'";
				    }

				    String original = "@" + variable + "#";

				    sqlWhere = sqlWhere.replaceAll(original, replaceValue);

				    istart = sqlWhere.indexOf("@");
				}

				queryCount.append(" and ( " + sqlWhere + ")");
				query.append(" and ( " + sqlWhere + ")");

            }

            if (filters != null)
            {
                boolean orOperatorSet = false;

                for (int i = 0; i < filters.size(); i++)
                {
                    BrowseFilter filter = (BrowseFilter) filters.get(i);
                    String key = filter.getColumnName();
                    
                    if (!b.validateColumn(key)) {
                    	//skip this filter
                    	continue;
                    }
                    
                    String value = encoder.encodeForSQL(new OracleCodec(), filter.getValue());
                    String operator = filter.getOperator();
                    String logicalOperator = filter.getLogicalOperator();
                    String sort = filter.getSort();
                    String type = filter.getType();

                    if (!BrowseValidationUtility.permissibleOperators.contains(operator)) {
                    	operator = "=";
                    }
                    
                    if (!BrowseValidationUtility.permissibleLogicalOperators.contains(logicalOperator)) {
                    	logicalOperator = "AND";
                    }
                    
                    if (!BrowseValidationUtility.permissibleSortOperators.contains(sort)) {
                    	sort = "N";
                    }

                    if (!Utility.isEmpty(value))
                    {
                        if (Utility.isEmpty(operator))
                        {
                            operator = "=";
                        }
                        if (Utility.isEmpty(type))
                        {
                            type = "STRING";
                        }

                        /**** Wildcards ****/
                        // If value is surrounded by ' then assume select will be done with the exact value otherwise
                        // assume select will be used with %
    					if (!Utility.isEmpty(value) && ( type.equalsIgnoreCase("STRING") || type.equalsIgnoreCase("USER-ID") || type.equalsIgnoreCase("VSBA") )&& operator.equalsIgnoreCase("=")) {
    						if (value.startsWith("'") && value.endsWith("'")) {
    							while (value.startsWith("'") && value.endsWith("'")) {
	    							value = value.substring(1);
	    							value = value.substring(0, value.length()-1);
    							}
    						}
    						else {
    							operator = "LIKE";
    						}
    					}
    					/** End Wildcards **/

                        if (queryFilter.length() > 0)
                        {
                            boolean logicalOperatorSet = false;

                            if ((i + 1) < filters.size())
                            {
                                BrowseFilter nextFilter = (BrowseFilter) filters.get(i + 1);
                                String nextLogicalOper = nextFilter.getLogicalOperator();
                                if (!Utility.isEmpty(nextLogicalOper) && nextLogicalOper.equalsIgnoreCase("OR"))
                                {
                                    orOperatorSet = true;
                                    queryFilter.append(" " + logicalOperator + " (");
                                    openParenthesis++;
                                    logicalOperatorSet = true;
                                }
                            }
                            if (!logicalOperatorSet)
                            {
                                if (openParenthesis > 0 && !orOperatorSet)
                                {
                                    openParenthesis--;
                                    queryFilter.append(") " + logicalOperator + " ");
                                }
                                else if (openParenthesis > 0 && orOperatorSet && !logicalOperator.equalsIgnoreCase("OR"))
                                {
                                    openParenthesis--;
                                    queryFilter.append(") " + logicalOperator + " ");
                                }
                                else
                                {
                                    queryFilter.append(" " + logicalOperator + " ");
                                }
                            }
                        }
                        else if ((i + 1) < filters.size())
                        {
                            BrowseFilter nextFilter = (BrowseFilter) filters.get(i + 1);
                            String nextLogicalOper = nextFilter.getLogicalOperator();
                            if (!Utility.isEmpty(nextLogicalOper) && nextLogicalOper.equalsIgnoreCase("OR"))
                            {
                                orOperatorSet = true;
                                queryFilter.append(" (");
                                openParenthesis++;
                            }
                        }

                        if (type.equalsIgnoreCase("DATE"))
						{
							if (!value.startsWith(":"))
							{
								if (execute.equalsIgnoreCase("Y"))
								{
									dateArguments.add(Dates.getDate(value, userDateFormat));
									dateArgumentsCount.add(Dates.getDate(value, userDateFormat));

									if (operator.equals("=")){
										dateArguments.add(Dates.add(Dates.getDate(value, userDateFormat), 1));
										dateArgumentsCount.add(Dates.add(Dates.getDate(value, userDateFormat), 1));
										queryFilter.append(" " + key + " >= ? and " + key + " < ? ");
									}
									else
										queryFilter.append(" " + key + " " + operator + " ?");
								} else
								{
									queryFilter.append(" " + key + " " + operator + " " + BrowseDateRanges.AS_DATE + value);
								}
							} else
							{
								if (":today".startsWith(value) && "=".equals(operator))
									queryFilter.append(" " + key + " " + ">=" + " " + value);
								else
									queryFilter.append(" " + key + " " + operator + " " + value);
							}
						}
                        else if (operator.equalsIgnoreCase("LIKE"))
                        {
                            if (value.indexOf('%') < 0)
                            {
                            	queryFilter.append(" UPPER(" + key + ") " + operator + " '%" + value.toUpperCase() + "%'");
                            	/* queryFilter.append("( ");
                                queryFilter.append(" UPPER(" + key + ") " + operator + " '%" + value.toUpperCase() + "%'");
                                queryFilter.append(" OR");
                                queryFilter.append(" UPPER(" + key + ") " + operator + " '" + value.toUpperCase() + "%'");
                                queryFilter.append(" OR");
                                queryFilter.append(" UPPER(" + key + ") " + operator + " '%" + value.toUpperCase() + "'");
                                queryFilter.append(" OR");
                                queryFilter.append(" UPPER(" + key + ") = '" + value.toUpperCase() + "'");
                                queryFilter.append(" )");*/
                            }
                            else
                            {
                                queryFilter.append(" UPPER(" + key + ") " + operator + " '" + value.toUpperCase() + "'");
                            }
                        }
                        else if (type.toUpperCase().indexOf("DECIMAL") >= 0)
                        {
                        	if (operator.equalsIgnoreCase("isnull")) queryFilter.append(" " + key + " is null");
                        	else queryFilter.append(" " + key + " " + operator + " " + value.toUpperCase() + "");
                        }
                        else
                        {
                        	if (operator.equalsIgnoreCase("isnull")) queryFilter.append(" " + key + " is null");
                        	else queryFilter.append(" UPPER(" + key + ") " + operator + " '" + value.toUpperCase() + "'");
                        }
                    }
                    if (!Utility.isEmpty(sort) && !sort.equalsIgnoreCase("N"))
                    {
                        if (orderBy.length() > 0)
                        {
                        	// cannot have two orderBy's with the same key
                        	if (!orderBy.toString().contains(key)) {
                        		orderBy.append(", " + key + " " + sort);
                        	}
                        }
                        else
                        {
                            sortColumn = key;
                            orderBy.append(key + " " + sort);
                        }
                    }
                }
                int ip = 0;
                while (ip < openParenthesis)
                {
                    queryFilter.append(")");
                    openParenthesis--;
                }

                if (!Utility.isEmpty(queryFilter.toString())) {
                	StringBuffer queryFilterTemp = new StringBuffer();
                	queryFilterTemp.append(" 2 = 2 and ( ");
                	queryFilterTemp.append(queryFilter);
                	queryFilterTemp.append(" )");
                	queryFilter = queryFilterTemp;
                }
            }

            String invBrowse = (String) incomingRequest.get("invbrowse");
            if (invBrowse == null)
            {
                invBrowse = "false";
            }
            //invbrowse stuff(state, product,, inv_catalog)
            if (invBrowse.equalsIgnoreCase("true"))
            {
                query.append(" " + invBrowseFilter(incomingRequest));
                queryCount.append(" " + invBrowseFilter(incomingRequest));
            }

            if (queryFilter.length() > 0)
            {
                query.append(" AND (" + queryFilter + " )");
                queryCount.append(" AND (" + queryFilter + " )");
                b.setQueryFilter(queryFilter.toString());
            }

            if(!HiltonUtility.isEmpty(sqlGroupBy))
            {
                query.append(" group by ");
                query.append(sqlGroupBy);
            }

            if (orderBy.length() > 0)
            {
                query.append(" order by " + orderBy);
            }
            else
            {
            	if (!Utility.isEmpty(sqlOrderBy))
            	{
            		query.append(" order by " + sqlOrderBy);
            	}
            }

            Log.debug(this, incomingRequest.get("userId") + " - query: " + query.toString());

            if(execute.equalsIgnoreCase("Y"))
            {
            	long start = System.currentTimeMillis();
                Object types = null;

                String finalQuery = ReportDates.dateWhereClauseDecoder(query.toString(), dateArguments, organizationId, userTimeZone);
                String finalCountQuery = ReportDates.dateWhereClauseDecoder(queryCount.toString(), dateArgumentsCount, organizationId, userTimeZone);
                Object arguments[] = new Object[dateArguments.size()];
                for (int i = 0; i < dateArguments.size(); i++)
                {
                	arguments[i] = dateArguments.get(i);
                }
	            //List list = dbs.query(query.toString(), arguments, types, b.getMaxRows());
                //List list = dbs.query(finalQuery, arguments, types, b.getMaxRows());

                ///////////////////////////////////////////////////////////
                //If b.getPageSize() = -1 all registers will be obtained
                ///////////////////////////////////////////////////////////
                SearchResult searchResult = dbs.query(finalQuery, finalCountQuery, arguments, types, b.getCurrentPage(), b.getPageSize());
                incomingRequest.put("browseSearchResult", searchResult);
                List list = searchResult.getList();

	            long end = System.currentTimeMillis();
	            Log.debug(this, "It took " + ((end - start)/1000) +" seconds to execute the query");
	            Log.debug(this, incomingRequest.get("userId") + " - dbs.query COMPLETE.");

	            result = list;
	            //result = searchResult;
	            Log.debug(this, incomingRequest.get("userId") + " - values for BrowseObjects have been populated.");
            }
            else
            {
            	int whereIndex = query.indexOf("where");
            	int orderByIndex = query.indexOf(" order by");

//            	int dateIndex = query.indexOf("?");
//            	int i = 0;
//            	while (dateIndex > 0)
//            	{
//            		query.replace(dateIndex, dateIndex + 1, "'" + new SimpleDateFormat("dd-MMM-yyyy").format(dateArguments.get(i)) + "'");
//            		i++;
//            		dateIndex = query.indexOf("?", dateIndex + 1);
//				}

            	result = query.toString().substring(whereIndex);
            	if (orderByIndex > whereIndex) {
            		incomingRequest.put("sqlWhereForCount", query.toString().substring(whereIndex, orderByIndex));
            	}
            }
            incomingRequest.put("sortedColumn", sortColumn);

            this.setStatus(dbs.getStatus());
        }
        catch (Exception e)
        {
            Log.error(this.getName(), query.toString());
            this.setStatus(Status.FAILED);
            throw e;
            //throw new TsaException(this.getName(), e);
        }
        return result;
    }

    /**
     * @param incomingRequest
     */
    private String invBrowseFilter(Map incomingRequest)
    {
        String state = (String) incomingRequest.get("as_inv_state");
        String productId = (String) incomingRequest.get("as_inv_product");
        String catalogId = (String) incomingRequest.get("as_inv_catalogId");

        StringBuffer querySB = new StringBuffer();
        querySB.setLength(0);

        if (state != null && state.length() > 0)
        {
            querySB.append(" AND ");
            querySB
                    .append("(EXISTS (from InvFormState as state WHERE state.id.itemNumber = InvItem.id and "
                            + "state.id.itemNumber = InvLocation.id.itemNumber AND "
                            + "(state.id.stateId = '"
                            + state
                            + "' or state.id.stateId = '*')))");
        }
        if (productId != null && productId.length() > 0)
        {
            querySB.append(" AND ");
            querySB
                    .append("EXISTS ( from InvFormProduct as product where product.id.itemNumber = InvItem.id AND "
                            + "product.id.itemNumber = InvLocation.id.itemNumber AND "
                            + "(product.id.productId = '"
                            + productId
                            + "' OR product.id.productId = '*'))");
        }

        if (catalogId != null && catalogId.length() > 0)
        {
            querySB.append(" AND ");
            querySB
                    .append("(EXISTS( from InvFormCatalog as catalog where catalog.id.invCatId = '"
                            + catalogId
                            + "' AND "
                            + "catalog.id.itemNumber = invItem.id.itemNumber ))");
        }
        return querySB.toString();
    }

    public static Date baseReportDate()
	{
		Calendar calendar = Calendar.getInstance();
		calendar.set(2006, Calendar.JANUARY, 1);
		return calendar.getTime();
	}

}

