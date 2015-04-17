package com.tsa.puridiom.browse.tasks;

import com.tsa.puridiom.browse.BrowseFilter;
import com.tsa.puridiom.browse.BrowseObject;
import com.tsa.puridiom.browse.BrowseUtility;
import com.tsa.puridiom.browse.BrowseValidationUtility;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.*;

import java.util.*;

import org.owasp.esapi.Encoder;
import org.owasp.esapi.codecs.OracleCodec;
import org.owasp.esapi.reference.DefaultEncoder;

public class BrowseRetrieveNormal extends Task
{
	Encoder encoder = DefaultEncoder.getInstance();
	
	public Object executeTask(Object object) throws Exception
    {
        List result;
        try
        {
            Map incomingRequest = (Map) object;
            DBSession dbs = (DBSession) incomingRequest.get("dbsession");
            String o = (String) incomingRequest.get("organizationId");
            String userId = (String) incomingRequest.get("userId");
            String userDateFormat = (String) incomingRequest.get("userDateFormat");
            BrowseObject b = (BrowseObject) incomingRequest.get("browseObject");
            StringBuffer query = new StringBuffer();
            StringBuffer queryFilter = new StringBuffer();
            StringBuffer orderBy = new StringBuffer();
            String sqlWhere = b.getSqlWhere();
            String sqlSelect = b.getSqlSelect();
            String sortColumn = "";
            PropertiesManager propertiesManager = PropertiesManager.getInstance(o);

            if (Utility.isEmpty(userDateFormat)) {
                userDateFormat = propertiesManager.getProperty("MISC", "DateFormat", "MM-dd-yyyy");
            }

            //query.append(sqlSelect);
            query.append(" from " + b.getSqlFrom() + " where 1 = 1");

            if (Utility.ckNull(sqlWhere).length() > 0)
            {
                sqlWhere = sqlWhere.replaceAll(":as_userid", "'" + userId + "'");
                query.append(" and ( " + sqlWhere + ")");
            }

            List dateArguments = new ArrayList();
            List filters = b.getBrowseFilters();
            int openParenthesis = 0;

            if (filters != null)
            {
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
                    
                    if (!BrowseValidationUtility.permissibleOperators.contains(operator)) {
                    	operator = "=";
                    }
                    
                    if (!BrowseValidationUtility.permissibleLogicalOperators.contains(logicalOperator)) {
                    	logicalOperator = "AND";
                    }
                    
                    String sort = filter.getSort();
                    
                    if (!BrowseValidationUtility.permissibleSortOperators.contains(sort)) {
                    	sort = "N";
                    }
                    
                    String type = filter.getType();

                    if (!Utility.isEmpty(value))
                    {
                        if (Utility.isEmpty(operator))
                        {
                            operator = "=";
                        }
                        if (Utility.isEmpty(logicalOperator))
                        {
                            logicalOperator = "AND";
                        }
                        if (Utility.isEmpty(type))
                        {
                            type = "STRING";
                        }

                        if (queryFilter.length() > 0)
                        {
                            boolean logicalOperatorSet = false;

                            if ((i + 1) < filters.size())
                            {
                                BrowseFilter nextFilter = (BrowseFilter) filters.get(i + 1);
                                String nextLogicalOper = nextFilter.getLogicalOperator();
                                if (!Utility.isEmpty(nextLogicalOper) && nextLogicalOper.equalsIgnoreCase("OR"))
                                {
                                    queryFilter.append(" " + logicalOperator + " (");
                                    openParenthesis++;
                                    logicalOperatorSet = true;
                                }
                            }
                            if (!logicalOperatorSet)
                            {
                                if (openParenthesis > 0)
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

                        if (type.equalsIgnoreCase("DATE"))
                        {
                            dateArguments.add(Dates.getDate(value, userDateFormat));
                            queryFilter.append(" " + key + " " + operator
                                    + " ?");
                        }
                        else if (operator.equalsIgnoreCase("LIKE"))
                        {
                            queryFilter.append(" UPPER(" + key + ") "
                                    + operator + " '" + value + "'");
                        }
                        else
                        {
                            queryFilter.append(" " + key + " " + operator
                                    + " '" + value + "'");
                        }
                    }
                    if (!Utility.isEmpty(sort) && !sort.equalsIgnoreCase("N"))
                    {
                        if (orderBy.length() > 0)
                        {
                            orderBy.append(", " + key + " " + sort);
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
            }

            if (queryFilter.length() > 0)
            {
                query.append(" AND (" + queryFilter + " )");
            }
            if (orderBy.length() > 0)
            {
                query.append(" order by " + orderBy);
            }
            //System.out.println(query);
            Log.debug(this, incomingRequest.get("userId") + " - query: " + query.toString());

            Object arguments[] = new Object[dateArguments.size()];
            for (int i = 0; i < dateArguments.size(); i++)
            {
                arguments[i] = dateArguments.get(i);
            }
            long start = System.currentTimeMillis();

            List list = dbs.query(query.toString(), arguments);
            long end = System.currentTimeMillis();
            Log.debug(this, "It took " + ((end - start)/1000) +" seconds to execute the query");
            Log.debug(this, incomingRequest.get("userId") + " - dbs.query COMPLETE.");

            String debug = (String) incomingRequest.get("debug");

            if (debug == null)
            {
                debug = "false";
            }
            if (debug.equalsIgnoreCase("true"))
            {
                result = BrowseUtility.populateValues(list, b, o, userId);
            }
            else
            {
                result = list;
            }

            Log.debug(this, incomingRequest.get("userId") + " - values for BrowseObjects have been populated.");

            incomingRequest.put("sortedColumn", sortColumn);

            this.setStatus(dbs.getStatus());
        }
        catch (Exception e)
        {
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
}