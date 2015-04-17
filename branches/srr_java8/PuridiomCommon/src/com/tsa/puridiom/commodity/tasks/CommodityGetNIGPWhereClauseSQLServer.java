package com.tsa.puridiom.commodity.tasks;

public class CommodityGetNIGPWhereClauseSQLServer extends CommodityGetNIGPWhereClause
{
	protected StringBuffer getQueryString(StringBuffer keywordClause)
	{
		return new StringBuffer("select distinct (SUBSTRING(Commodity.commodity, 1, 3) + '00') as code from Commodity as Commodity where " + keywordClause.toString());
	}


}
