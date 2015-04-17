/**
 *
 */
package com.tsa.puridiom.browse;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.TimeZone;

import com.tsa.puridiom.browse.tasks.BrowseDateRanges;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.utility.Dates;

/**
 * @author Johnny Zapana
 */
public class ReportDates
{
	public static String dateWhereClauseDecoder(String sqlWhere, List dateArguments, String organizationId, String timeZone) throws Exception
	{
		final String WHERE_FILTER_CLAUSE_START = "where";
		final String WHERE_FILTER_CLAUSE_END = ")";
		final String REGEX_WHITE_SPACES = "\\s+";
		final String REGEX_LOGICAL_OPERATOR = "(and|or|AND|OR)";
		final String REGEX_ANY_CHARACTER = "(.+)";
		final String REGEX_PARENTHESIS_LEFT = "\\(+";
		final String REGEX_PARENTHESIS_RIGHT = "\\)+";
		final String REGEX_COMPARATOR_OPERATOR = "(=|<|>|<=|>=|<>)";
		final String DATE_FORMAT = PropertiesManager.getInstance(organizationId).getProperty("MISC", "DateFormat", "MM-dd-yyyy");

		String whereClause = new String(sqlWhere);
		Pattern pattern = Pattern.compile(REGEX_COMPARATOR_OPERATOR + REGEX_WHITE_SPACES + ":");
		Matcher matcher = pattern.matcher(whereClause);
		String[] conditions;
		String[] expressions;
		String workString = "";
		String property = "";
		String operator = "";
		String value = "";
		char rangeDateType;

		if (matcher.find() && whereClause.indexOf(WHERE_FILTER_CLAUSE_START) >= 0)
		{
			whereClause = whereClause.trim().substring(whereClause.indexOf(WHERE_FILTER_CLAUSE_START) + WHERE_FILTER_CLAUSE_START.length(), whereClause.lastIndexOf(WHERE_FILTER_CLAUSE_END) + 1)
					.trim();

			conditions = whereClause.split(REGEX_WHITE_SPACES + REGEX_LOGICAL_OPERATOR + REGEX_WHITE_SPACES);

			for (int i = 0; i < conditions.length; i++)
			{
				workString = conditions[i].trim();

				if (workString.matches(REGEX_ANY_CHARACTER + REGEX_COMPARATOR_OPERATOR + REGEX_WHITE_SPACES + ":" + REGEX_ANY_CHARACTER))
				{
					if (workString.matches(REGEX_PARENTHESIS_LEFT + REGEX_ANY_CHARACTER))
					{
						workString = workString.replaceAll(REGEX_PARENTHESIS_LEFT, "").trim();
					}

					if (workString.matches(REGEX_ANY_CHARACTER + REGEX_PARENTHESIS_RIGHT))
					{
						workString = workString.replaceAll(REGEX_PARENTHESIS_RIGHT, "").trim();
					}

					expressions = workString.trim().split(REGEX_WHITE_SPACES);

					property = expressions[0].trim();
					operator = expressions[1].trim();
					value = expressions[2].trim();

					rangeDateType = 'N';

					if (value.startsWith(BrowseDateRanges.AS_DATE))
					{
						sqlWhere = sqlWhere.replaceFirst(value, "?");
						dateArguments.add(new Date((new SimpleDateFormat(DATE_FORMAT).parse(value.substring(BrowseDateRanges.AS_DATE.length()))).getTime()));

					} else if (value.indexOf(BrowseDateRanges.AS_FISCALSTART) > -1)
					{
						String fyBegin = PropertiesManager.getInstance(organizationId).getProperty("Misc", "fybegin", "1");
						sqlWhere = sqlWhere.replaceFirst(BrowseDateRanges.AS_FISCALSTART, "?");
						dateArguments.add(new Date(Dates.getFiscalYearStartDate(Integer.parseInt(fyBegin), timeZone).getTime()));

					} else if (value.indexOf(BrowseDateRanges.AS_BASEREPORTDATE) > -1)
					{
						Calendar calendar = Calendar.getInstance();
						calendar.set(Calendar.YEAR, 2006);
						calendar.set(Calendar.DAY_OF_YEAR, 1);

						sqlWhere = sqlWhere.replaceFirst(BrowseDateRanges.AS_BASEREPORTDATE, "?");
						dateArguments.add(new Date(calendar.getTime().getTime()));

					} else if (value.equals(BrowseDateRanges.AS_TODAY) || value.equals(BrowseDateRanges.TODAY))
					{
						sqlWhere = sqlWhere.replaceFirst(value, "?");
						dateArguments.add(Dates.getDate(Dates.today("", timeZone)));

					} else if (value.equals(BrowseDateRanges.THISWEEK) || value.equals(BrowseDateRanges.LASTWEEK))
					{
						rangeDateType = 'W';
					} else if (value.equals(BrowseDateRanges.THISMONTH) || value.equals(BrowseDateRanges.LASTMONTH))
					{
						rangeDateType = 'M';
					} else if (value.equals(BrowseDateRanges.THISQUARTER) || value.equals(BrowseDateRanges.LASTQUARTER))
					{
						rangeDateType = 'Q';
					} else if (value.equals(BrowseDateRanges.THISYEAR) || value.equals(BrowseDateRanges.LASTYEAR))
					{
						rangeDateType = 'Y';
					}

					if (rangeDateType != 'N')
					{
						sqlWhere = sqlWhere.replaceFirst(operator + REGEX_WHITE_SPACES + value, ReportDates.getDateRangeExpression(operator, value, rangeDateType, property, dateArguments, timeZone));
					}
				}
			}
		}

		return sqlWhere;
	}

	private static String getDateRangeExpression(String operator, String value, char rangeType, String property, List dateArguments, String timeZone)
	{
		String expression = "";
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(timeZone));
		Calendar calendarTwo = Calendar.getInstance(TimeZone.getTimeZone(timeZone));
		String[] operators;

		if (value.indexOf("last") == 1)
		{
			ReportDates.setLastDateRange(calendar, rangeType);
			ReportDates.setLastDateRange(calendarTwo, rangeType);
		}

		if (operator.equals("<") || operator.equals(">="))
		{
			ReportDates.setFirstDayRangeType(calendar, rangeType);
			expression = operator + " ? ";
			dateArguments.add(new Date(calendar.getTime().getTime()));
		} else if (operator.equals("<=") || operator.equals(">"))
		{
			ReportDates.setLastDayRangeType(calendar, rangeType);
			expression = operator + " ? ";
			dateArguments.add(new Date(calendar.getTime().getTime()));
		} else
		{
			if (operator.equals("="))
			{
				operator = ">=:<=";
			} else if (operator.equals("<>"))
			{
				operator = "<:>";
			}

			operators = operator.split(":");

			ReportDates.setFirstDayRangeType(calendar, rangeType);
			ReportDates.setLastDayRangeType(calendarTwo, rangeType);

			expression = operators[0] + " ? AND " + property + " " + operators[1] + " ? ";
			dateArguments.add(new Date(calendar.getTime().getTime()));
			dateArguments.add(new Date(calendarTwo.getTime().getTime()));
		}

		return " " + expression;
	}

	private static void setFirstDayRangeType(Calendar calendar, char rangeType)
	{
		switch (rangeType)
		{
			case 'W':
				calendar.add(Calendar.DAY_OF_WEEK, (calendar.get(Calendar.DAY_OF_WEEK) > 1) ? (-calendar.get(Calendar.DAY_OF_WEEK) + 2) : (calendar.get(Calendar.DAY_OF_WEEK) - Calendar.DAY_OF_WEEK));
				break;
			case 'M':
				calendar.set(Calendar.DAY_OF_MONTH, 1);
				break;
			case 'Q':
				calendar.set(Calendar.MONTH, ((((int) Math.ceil((calendar.get(Calendar.MONTH) + 1) / 3F)) * 3) - 2) - 1);
				calendar.set(Calendar.DAY_OF_MONTH, 1);
				break;
			case 'Y':
				calendar.set(Calendar.DAY_OF_YEAR, 1);
				break;
		}
	}

	private static void setLastDayRangeType(Calendar calendar, char rangeType)
	{
		switch (rangeType)
		{
			case 'W':
				calendar.add(Calendar.DAY_OF_WEEK, (calendar.get(Calendar.DAY_OF_WEEK) > 1) ? (Calendar.DAY_OF_WEEK - calendar.get(Calendar.DAY_OF_WEEK) + 1) : 0);
				break;
			case 'M':
				calendar.set(Calendar.DAY_OF_MONTH, 1);
				calendar.add(Calendar.MONTH, 1);
				calendar.add(Calendar.DATE, -1);
				break;
			case 'Q':
				calendar.set(Calendar.MONTH, ((int) Math.ceil((calendar.get(Calendar.MONTH) + 1) / 3F)) * 3);
				calendar.set(Calendar.DAY_OF_MONTH, 1);
				calendar.add(Calendar.DATE, -1);
				break;
			case 'Y':
				calendar.set(Calendar.DATE, 31);
				calendar.set(Calendar.MONTH, 11);
				break;
		}
	}

	private static void setLastDateRange(Calendar calendar, char rangeType)
	{
		switch (rangeType)
		{
			case 'W':
				calendar.add(Calendar.DAY_OF_WEEK, -Calendar.DAY_OF_WEEK);
				break;
			case 'M':
				calendar.add(Calendar.MONTH, -1);
				break;
			case 'Q':
				calendar.add(Calendar.MONTH, -3);
				break;
			case 'Y':
				calendar.add(Calendar.YEAR, -1);
				break;
		}
	}
}
