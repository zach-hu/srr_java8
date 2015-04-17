package com.tsa.puridiom.reportqueue.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.DateRanges;
import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.documents.InvoiceType;
import com.tsa.puridiom.common.documents.OrderType;
import com.tsa.puridiom.common.documents.ReceiptType;
import com.tsa.puridiom.common.documents.RequisitionType;
import com.tsa.puridiom.common.documents.RfqType;
import com.tsa.puridiom.entity.ReportQueue;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class ReportQueueWhereClauseDecod extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		final String WHERE_FILTER_CLAUSE_START = " 2 = 2 and ( ";
		final String WHERE_FILTER_CLAUSE_END = " ) )";
		final String REGEX_WHITE_SPACES = "\\s+";
		final String REGEX_LOGICAL_OPERATOR = "(AND|OR)";
		final String REGEX_UPPER_FUNCTION_START = "(.*)UPPER\\(";
		final String REGEX_UPPER_FUNCTION_END = "\\)";
		final String REGEX_ANY_CHARACTER = "(.+)";
		final String REGEX_PARENTHESIS_LEFT = "\\(+";
		final String REGEX_PARENTHESIS_RIGHT = "\\)+";
		final String UPPER_FUNCTION = "UPPER(";

		Map incomingRequest = (Map) object;
		List statementList = new ArrayList();

		String[] conditions;
		String[] expressions;
		String workString = "";
		String logicalOperator = "";

		try
		{
			ReportQueue reportQueue = (ReportQueue) incomingRequest.get("reportQueue");
			String organizationId = (String) incomingRequest.get("organizationId");

			String whereClause = reportQueue.getWhereClause();
			List statement = new ArrayList();

			if (whereClause == null)
			{
				whereClause = "";
			}

			if (whereClause.indexOf(WHERE_FILTER_CLAUSE_START) >= 0)
			{

				whereClause = whereClause.trim().substring(whereClause.indexOf(WHERE_FILTER_CLAUSE_START) + WHERE_FILTER_CLAUSE_START.length(), whereClause.lastIndexOf(WHERE_FILTER_CLAUSE_END))
						.trim();

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

					expressions = this.getExpression(workString.trim().split(REGEX_WHITE_SPACES), organizationId);

					if (i < (conditions.length - 1))
					{
						whereClause = whereClause.substring(conditions[i].trim().length()).trim();

						logicalOperator = whereClause.substring(0, whereClause.indexOf(" ")).trim();

						whereClause = whereClause.substring(logicalOperator.length()).trim();

					} else
					{
						logicalOperator = "";
					}

					statement = new ArrayList();
					statement.add(expressions[0]);
					statement.add(expressions[1]);
					statement.add(expressions[2]);
					statement.add(expressions[3]);
					statement.add(logicalOperator);

					statementList.add(statement);
				}
			}

			this.setStatus(Status.SUCCEEDED);
		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return statementList;
	}

	private String[] getExpression(String[] expression, String organizationId)
	{
		String property = expression[0].trim();
		String operator = expression[1].trim();
		String value = "";
		String label = "";
		String[] components = property.split("\\.");

		for (int j = 2; j < expression.length; j++)
		{
			value += " " + expression[j];
		}

		value = value.trim();

		property = property.replaceFirst("\\.", "_");

		if (operator.equals("LIKE") && value.matches("'%(.)+%'"))
		{
			operator = "=";
			value = value.substring(2, value.length() - 2);
		} else if ((!operator.equals("LIKE")) && value.startsWith("'") && value.endsWith("'"))
		{
			if (!operator.equals("="))
			{
				value = value.substring(1, value.length() - 1);
			} else if ((components[1].toLowerCase().indexOf("type") >= 0) || (components[1].toLowerCase().indexOf("status") >= 0))
			{
				value = value.substring(1, value.length() - 1);

				if (components[1].toLowerCase().indexOf("req") >= 0)
				{
					label = RequisitionType.toString(value, organizationId);
				} else if (components[1].toLowerCase().indexOf("rfq") >= 0)
				{
					label = RfqType.toString(value, organizationId);
				} else if (components[1].toLowerCase().indexOf("rec") >= 0)
				{
					label = ReceiptType.toString(value, organizationId);
				} else if (components[1].toLowerCase().indexOf("po") >= 0)
				{
					label = OrderType.toString(value, organizationId);
				} else if (components[1].toLowerCase().indexOf("inv") >= 0)
				{
					label = InvoiceType.toString(value, organizationId);
				} else if (components[1].toLowerCase().indexOf("status") >= 0)
				{
					label = DocumentStatus.toString(value, organizationId);
				}

				label = (value.equals(label)) ? "" : label;
			}

		} else if (value.startsWith(":"))
		{
			if (value.startsWith(DateRanges.AS_DATE)) {
				value = DateRanges.toString(value, organizationId);
			} else {
				label = DateRanges.toString(value, organizationId);
			}
		}

		return new String[] { property, operator, value, label };
	}

}