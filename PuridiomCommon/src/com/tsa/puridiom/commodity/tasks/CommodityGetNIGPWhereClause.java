/*
 * Created on Dec 20, 2005
 */
package com.tsa.puridiom.commodity.tasks;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;

/**
 * @author kelli

	NIGP Commodity Code pattern: CCCPP
	CCC	-	Category	(%00)
	PP	-	Product	(CCC%)
 */
public class CommodityGetNIGPWhereClause extends Task {

	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;

		StringBuffer sqlWhere = new StringBuffer();
		String	keywords = null;

		if (incomingRequest.get("Commodity_description") instanceof String) {
			keywords = (String) incomingRequest.get("Commodity_description");
		}



		Object obj = incomingRequest.get("Commodity_commodity");
		String	commodityCodes[] = null;
		if (obj instanceof String) {
			commodityCodes = new String[1];
			commodityCodes[0] = (String) obj;
		} else if (obj instanceof String[]) {
			commodityCodes = (String[]) obj;
		}
		if (commodityCodes == null || commodityCodes.length == 0) {
			commodityCodes = new String[1];
			commodityCodes[0] = "";
		}

		sqlWhere.append(" Commodity.status = '02'");
		System.out.println("WHERE = " + sqlWhere.toString());

		String	retrieveAllCodes = Utility.ckNull((String) incomingRequest.get("retrieveAllCodes"));
		String	retrieveAllCategories = Utility.ckNull((String) incomingRequest.get("retrieveAllSegments"));
		String	retrieveAllProducts = Utility.ckNull((String) incomingRequest.get("retrieveAllFamilies"));

		StringBuffer sqlWhereTemp = new StringBuffer();
		if (Utility.ckNull(retrieveAllCodes).equals("Y") || commodityCodes.length == 0) {
			sqlWhereTemp.append("Commodity.commodity LIKE '%00'");
		}

		for (int i=0; i < commodityCodes.length; i++) {
			String	commodityCode = commodityCodes[i];

			if ((!Utility.isEmpty(commodityCode) || retrieveAllCategories.equals("Y") || retrieveAllProducts.equals("Y")) && commodityCode.length() >= 5) {
				String	category = commodityCode.substring(0, 3);
				String	product = commodityCode.substring(3, 5);

				if ((!category.equals("000") || retrieveAllCategories.equals("Y"))) {
					if (retrieveAllCategories.equals("Y")) {
						category = "";
					}
					sqlWhereTemp.append(" OR Commodity.commodity LIKE '" + category + "%'");

					if (!product.equals("00") || retrieveAllProducts.equals("Y")) {
						if (retrieveAllProducts.equals("Y")) {
							product = "";
						}
						sqlWhereTemp.append(" OR Commodity.commodity LIKE '" + category + product + "'");
					}
				}
			}
		}

		if (sqlWhereTemp.length() > 0) {
			if (sqlWhereTemp.toString().startsWith(" OR ")) {
				sqlWhereTemp = new StringBuffer(sqlWhereTemp.substring(3));
			} else if (sqlWhereTemp.toString().startsWith(" AND ")) {
				sqlWhereTemp = new StringBuffer(sqlWhereTemp.substring(4));
			}
			sqlWhere.append(" AND (" + sqlWhereTemp.toString() + ") ");
		}




		return sqlWhere.toString();
	}

	protected StringBuffer getCategoryWhereClause(Connection connection, StringBuffer queryString)
	{
		Statement statement = null;
		ResultSet rs = null;
		StringBuffer categoryWhereClause = new StringBuffer();
		try
		{
			statement = connection.createStatement();
			rs = statement.executeQuery(queryString.toString());
			while (rs.next())
			{
				String code = rs.getString("code");

				categoryWhereClause.append(" OR Commodity.commodity = '" + code +"'");
			}
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try
			{
				rs.close();
				statement.close();
			}
			catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return categoryWhereClause;
	}

	protected StringBuffer getQueryString(StringBuffer keywordClause)
	{
		//		String queryString = "select distinct (SUBSTRING(Commodity.commodity, 1, 3) + '00') as code from Commodity as Commodity where " + keywordClause.toString();
		/*
		 *     Oracle
		 */
		//return new StringBuffer("select distinct (SUBSTR(Commodity.commodity, 1, 3) || '00') as code from Commodity where " + keywordClause.toString());
		return new StringBuffer("select distinct (SUBSTRING(Commodity.commodity, 1 ,3) + '00') as code from Commodity as Commodity where " + keywordClause.toString());
	}

}
