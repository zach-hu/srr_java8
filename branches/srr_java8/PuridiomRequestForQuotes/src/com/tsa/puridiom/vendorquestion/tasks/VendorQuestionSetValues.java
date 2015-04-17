package com.tsa.puridiom.vendorquestion.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class VendorQuestionSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			VendorQuestion vendorQuestion = (VendorQuestion) incomingRequest.get("vendorQuestion");
			if (vendorQuestion == null)
			{
				vendorQuestion = new VendorQuestion();
			}

			if (incomingRequest.containsKey("VendorQuestion_icVendorQuestion"))
			{
				String icVendorQuestionString = (String) incomingRequest.get("VendorQuestion_icVendorQuestion");
				if (Utility.isEmpty(icVendorQuestionString))
				{
					icVendorQuestionString = "0";
				}
				BigDecimal icVendorQuestion = new BigDecimal ( icVendorQuestionString );
				vendorQuestion.setIcVendorQuestion(icVendorQuestion);
			}
			if (incomingRequest.containsKey("VendorQuestion_icRfqHeader"))
			{
				String icRfqHeaderString = (String) incomingRequest.get("VendorQuestion_icRfqHeader");
				if (Utility.isEmpty(icRfqHeaderString))
				{
					icRfqHeaderString = "0";
				}
				BigDecimal icRfqHeader = new BigDecimal ( icRfqHeaderString );
				vendorQuestion.setIcRfqHeader(icRfqHeader);
			}
			if (incomingRequest.containsKey("VendorQuestion_vendorId"))
			{
				String vendorId = (String ) incomingRequest.get("VendorQuestion_vendorId");
				vendorQuestion.setVendorId(vendorId);
			}
			if (incomingRequest.containsKey("VendorQuestion_questionTitle"))
			{
				String questionTitle = (String ) incomingRequest.get("VendorQuestion_questionTitle");
				vendorQuestion.setQuestionTitle(questionTitle);
			}
			if (incomingRequest.containsKey("VendorQuestion_questionText"))
			{
				String questionText = (String ) incomingRequest.get("VendorQuestion_questionText");
				vendorQuestion.setQuestionText(questionText);
			}
			if (incomingRequest.containsKey("VendorQuestion_datePosted"))
			{
				String datePostedString = (String) incomingRequest.get("VendorQuestion_datePosted");
				Date datePosted = Dates.getDate(datePostedString);
				vendorQuestion.setDatePosted(datePosted);
			}
			if (incomingRequest.containsKey("VendorQuestion_timePosted"))
			{
				String timePosted = (String ) incomingRequest.get("VendorQuestion_timePosted");
				vendorQuestion.setTimePosted(timePosted);
			}
			if (incomingRequest.containsKey("VendorQuestion_responseText"))
			{
				String responseText = (String ) incomingRequest.get("VendorQuestion_responseText");
				if (!Utility.isEmpty(responseText) && !incomingRequest.containsKey("VendorQuestion_responseDate")) {
                    // Supplier Portal time zone is always based on the system default server time zone
				    vendorQuestion.setResponseDate(Dates.getDate(Dates.today("yyyy-MM-dd", "")));
				    vendorQuestion.setResponseTime(Dates.today("hh:mm:ss", ""));
				}
				vendorQuestion.setResponseText(responseText);
			}
			if (incomingRequest.containsKey("VendorQuestion_responseDate"))
			{
				String responseDateString = (String) incomingRequest.get("VendorQuestion_responseDate");
				Date responseDate = Dates.getDate(responseDateString);
				vendorQuestion.setResponseDate(responseDate);
			}
			if (incomingRequest.containsKey("VendorQuestion_responseTime"))
			{
				String responseTime = (String ) incomingRequest.get("VendorQuestion_responseTime");
				vendorQuestion.setResponseTime(responseTime);
			}

			result = vendorQuestion;
			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}
