package com.tsa.puridiom.graphs;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.utility.Dates;

public class GeneralSqlParams {

	private String userId;
	private Date today;
	private String fiscalYear;
	private String as_vendorid;
	private String as_fiscalstart;
	private String as_fiscalend;
	private String sqlQuery;
	private List values =  new ArrayList();
	private List types;
	private List userFilter = new ArrayList();

	public void replaceSqlQuery()
	{
		//String select = "Select * from requisition_header where owner = :userId and requiredDate = :today";


		int paramIndex = this.getSqlQuery().indexOf(":");
		List params = new ArrayList();
		setValues(new ArrayList());
		setTypes(new ArrayList());
		while (paramIndex > 0)
		{
			String paramName = this.getSqlQuery().substring(paramIndex, this.getSqlQuery().indexOf(" ", paramIndex));
			params.add(paramName);
			paramIndex = this.getSqlQuery().indexOf(":", paramIndex + 1);
		}
		List userFilterList = this.getUserFilter();
		if(userFilterList.size() > 0)
		{
			String value = (String)userFilterList.get(1);
			if(!value.equalsIgnoreCase(":none"))
			{
				String tmpSql = this.getSqlQuery();
				String newSql = "2 = 2 and " + userFilterList.get(0) + " >= " + userFilterList.get(1);
				tmpSql = tmpSql.replaceAll("2 = 2", newSql);
				this.setSqlQuery(tmpSql);
				params.add(userFilterList.get(1));
			}
		}

		for(int i = 0; i < params.size(); i++)
		{
			String param = (String)params.get(i);
			if(param.equalsIgnoreCase(":as_userId"))
			{
				getValues().add(this.getUserId());
				getTypes().add(Hibernate.STRING);
			}
			else if (param.equalsIgnoreCase(":as_today"))
			{
				getValues().add(this.getToday());
				getTypes().add(Hibernate.DATE);
			}
			else if (param.equalsIgnoreCase(":as_fiscalYear"))
			{
				getValues().add(this.getFiscalYear());
				getTypes().add(Hibernate.STRING);
			}
			else if (param.equalsIgnoreCase(":thisyear"))
			{
				getValues().add(this.getThisYear());
				getTypes().add(Hibernate.DATE);
			}
			else if (param.equalsIgnoreCase(":thismonth"))
			{
				getValues().add(this.getThisMonth());
				getTypes().add(Hibernate.DATE);
			}
			else if (param.equalsIgnoreCase(":thisquarter"))
			{
				getValues().add(this.getThisQuarter());
				getTypes().add(Hibernate.DATE);
			}
			else if (param.equalsIgnoreCase(":thisweek"))
			{
				getValues().add(this.getThisWeek());
				getTypes().add(Hibernate.DATE);
			}
		}
		this.setSqlQuery( this.getSqlQuery().replaceAll(":as_userId", "?") );
		this.setSqlQuery( this.getSqlQuery().replaceAll(":as_today", "?") );
		this.setSqlQuery( this.getSqlQuery().replaceAll(":as_fiscalYear", "?"));
		this.setSqlQuery( this.getSqlQuery().replaceAll(":thisyear", "?"));
		this.setSqlQuery( this.getSqlQuery().replaceAll(":thismonth", "?"));
		this.setSqlQuery( this.getSqlQuery().replaceAll(":thisquarter", "?"));
		this.setSqlQuery( this.getSqlQuery().replaceAll(":thisweek", "?"));
	}

	public Date getThisYear()
	{
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, Calendar.JANUARY);
		calendar.set(Calendar.DATE, 1);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.AM_PM, Calendar.AM);
		return calendar.getTime();
	}

	public Date getThisMonth()
	{
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, 1);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.AM_PM, Calendar.AM);

		return calendar.getTime();
	}

	public Date getThisWeek()
	{
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_WEEK , calendar.getMinimum(Calendar.DAY_OF_WEEK));
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.AM_PM, Calendar.AM);
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		//System.out.println(sdf.format(calendar.getTime()));

		return calendar.getTime();
	}

	public Date getThisQuarter()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	    Calendar calendar = Calendar.getInstance();
	    calendar.add(Calendar.MONTH, (calendar.get(Calendar.MONTH)%3) - 3);
	    calendar.set(Calendar.DAY_OF_MONTH, 1);
	    calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.AM_PM, Calendar.AM);
	    //System.out.println(sdf.format(cal.getTime()));

		return calendar.getTime();
	}

	public static void main(String[] args) {
		System.out.println("start");
		GeneralSqlParams test = new GeneralSqlParams();
		System.out.println(test.getThisQuarter());
	}

	/**
	 * @param userId
	 * @param organizationId
	 */
	public void createDefaultParams(String userId, String organizationId, String userTimeZone)
	{
		this.setUserId(userId);
		this.setFiscalYear( HiltonUtility.getFiscalYear(organizationId, userTimeZone) );
		this.setToday(Dates.getDate(Dates.today("", userTimeZone)));
	}

	public String getAs_fiscalstart()
	{
		return as_fiscalstart;
	}
	public void setAs_fiscalstart(String as_fiscalstart) {
		this.as_fiscalstart = as_fiscalstart;
	}
	public String getAs_vendorid() {
		return as_vendorid;
	}
	public void setAs_vendorid(String as_vendorid) {
		this.as_vendorid = as_vendorid;
	}
	public String getFiscalYear() {
		return fiscalYear;
	}
	public void setFiscalYear(String fiscalYear) {
		this.fiscalYear = fiscalYear;
	}
	public void setAs_fiscalend(String as_fiscalend) {
		this.as_fiscalend = as_fiscalend;
	}
	public String getAs_fiscalend() {
		return as_fiscalend;
	}

	public void setSqlQuery(String sqlQuery) {
		this.sqlQuery = sqlQuery;
	}

	public String getSqlQuery() {
		return sqlQuery;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserId() {
		return userId;
	}
	public void setToday(Date today) {
		this.today = today;
	}
	public Date getToday() {
		return today;
	}
	public void setValues(List values) {
		this.values = values;
	}
	public List getValues() {
		return values;
	}
	public void setTypes(List types) {
		this.types = types;
	}
	public List getTypes() {
		return types;
	}

	public List getUserFilter() {
		return userFilter;
	}

	public void setUserFilter(List userFilter) {
		this.userFilter = userFilter;
	}

}
