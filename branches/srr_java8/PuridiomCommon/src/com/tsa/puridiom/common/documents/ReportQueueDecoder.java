package com.tsa.puridiom.common.documents;

import com.tsagate.foundation.utility.Utility;
import com.tsagate.properties.DictionaryManager;

public class ReportQueueDecoder
{
	public static final String WHERE_FILTER_CLAUSE_START = " 2 = 2 and ( ";

	public static final String WHERE_FILTER_CLAUSE_END = " ) )";

	public static final String REGEX_WHITE_SPACES = "\\s+";

	public static final String REGEX_LOGICAL_OPERATOR = "(AND|OR)";

	public static final String REGEX_UPPER_FUNCTION_START = "(.*)UPPER\\(";

	public static final String REGEX_UPPER_FUNCTION_END = "\\)";

	public static final String REGEX_ANY_CHARACTER = "(.+)";

	public static final String REGEX_PARENTHESIS_LEFT = "\\(+";

	public static final String REGEX_PARENTHESIS_RIGHT = "\\)+";

	public static final String UPPER_FUNCTION = "UPPER(";

	String whereClauseCoded = "";

	String whereClauseDecoded = "";

	public void setWhereClauseCoded(String whereClause)
	{
		this.whereClauseCoded = whereClause;
	}

	public static String decode(String whereClause, String organizationId)
	{
		String[] conditions;
		String[] expressions;
		String[] components;
		String workString = "";
		String property = "";
		String operator = "";
		String value = "";

		if (whereClause.indexOf(WHERE_FILTER_CLAUSE_START) >= 0)
		{
			whereClause = whereClause.trim().substring(whereClause.indexOf(WHERE_FILTER_CLAUSE_START) + WHERE_FILTER_CLAUSE_START.length(), whereClause.lastIndexOf(WHERE_FILTER_CLAUSE_END)).trim();

			conditions = whereClause.split(REGEX_WHITE_SPACES + REGEX_LOGICAL_OPERATOR + REGEX_WHITE_SPACES);

			for (int i = 0; i < conditions.length; i++)
			{
				workString = conditions[i].trim();

				if (workString.matches(REGEX_UPPER_FUNCTION_START + REGEX_ANY_CHARACTER))
				{
					workString = workString.substring(workString.indexOf(UPPER_FUNCTION) + UPPER_FUNCTION.length()).replaceFirst(REGEX_UPPER_FUNCTION_END, "");
				}

				if (workString.matches(REGEX_PARENTHESIS_LEFT + REGEX_ANY_CHARACTER))
				{
					workString = workString.replaceFirst(REGEX_PARENTHESIS_LEFT, "").trim();
				} else if (workString.matches(REGEX_ANY_CHARACTER + REGEX_PARENTHESIS_RIGHT))
				{
					workString = workString.replaceFirst(REGEX_PARENTHESIS_RIGHT, "").trim();
				}

				expressions = workString.trim().split(REGEX_WHITE_SPACES);

				property = expressions[0].trim();
				operator = expressions[1].trim();
				value = "";

				for (int j = 2; j < expressions.length; j++)
				{
					value += " " + expressions[j];

				}

				value = value.trim();

				components = property.split("\\.");

				whereClause = whereClause.replaceFirst("(UPPER\\()?" + property + "\\)?", getLabelProperty(components[0], components[components.length - 1], organizationId));

				whereClause = whereClause.replaceFirst(value, getValueProperty(components[components.length - 1], operator, value, organizationId));
			}
		}

		whereClause = replaceLogicalOperators(whereClause);

		return whereClause;
	}

	private static String replaceLogicalOperators(String whereClause)
	{
		whereClause = whereClause.replaceAll(REGEX_WHITE_SPACES + "AND" + REGEX_WHITE_SPACES, " and \n");
		whereClause = whereClause.replaceAll(REGEX_WHITE_SPACES + "OR" + REGEX_WHITE_SPACES, " or \n");

		return whereClause;
	}

	private static String getLabelProperty(String entity, String property, String oid)
	{
		String label = "";

		if (Utility.isEmpty(DictionaryManager.getLabel(oid, property, "")))
		{
			if (entity.indexOf("Req") == 0)
			{
				label = "req-";
			} else if (entity.indexOf("Rfq") == 0)
			{
				label = "rfq-";
			} else if (entity.indexOf("Po") == 0)
			{
				label = "po-";
			} else if (entity.indexOf("Ivc") == 0)
			{
				label = "ivc-";
			}

			label += property;
		} else
		{
			label = property;
		}

		return DictionaryManager.getLabel(oid, label, label);
	}

	private static String getValueProperty(String property, String operator, String value, String organizationId)
	{
		boolean hasSingleQuotes = false;

		if (value.startsWith("'"))
		{
			value = value.substring(1, value.length() - 1);
			hasSingleQuotes = true;
		}

//		if (operator.equals("LIKE"))
//		{
////			value = value.substring(1, value.length() - 1);
//			value = value.replaceAll("%", "");
//		}

		if (property.toLowerCase().indexOf("type") >= 0)
		{
			if (property.toLowerCase().indexOf("req") >= 0)
			{
				value = RequisitionType.toString(value, organizationId);
			} else if (property.toLowerCase().indexOf("rfq") >= 0)
			{
				value = RfqType.toString(value, organizationId);
			} else if (property.toLowerCase().indexOf("rec") >= 0)
			{
				value = ReceiptType.toString(value, organizationId);
			} else if (property.toLowerCase().indexOf("po") >= 0)
			{
				value = OrderType.toString(value, organizationId);
			} else if (property.toLowerCase().indexOf("inv") >= 0)
			{
				value = InvoiceType.toString(value, organizationId);
			}

		} else if (property.toLowerCase().indexOf("status") >= 0)
		{
			value = DocumentStatus.toString(value, organizationId);

		} else if (property.toLowerCase().indexOf("date") >= 0)
		{
			value = DateRanges.toString(value, organizationId);
		}

		if (hasSingleQuotes)
		{
			value = "'" + value + "'";
		}

		return value;
	}

}