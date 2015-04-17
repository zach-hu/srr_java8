package com.tsa.puridiom.rfq.worksheet;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.tsa.puridiom.common.documents.ChecklistEntryResponseType;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.ResponseValue;
import com.tsa.puridiom.entity.RfqQuestion;
import com.tsa.puridiom.entity.VendorResponse;

public class WorkSheetQuestion
{
	private BigDecimal sequence;

	private String questionText;

	private String responseType;

	private RfqQuestion rfqQuestion;

	private BigDecimal maxPoints;

	private String response1;

	private String response2;

	private String response3;

	private String response4;

	private String response5;

	private BigDecimal score1;

	private BigDecimal score2;

	private BigDecimal score3;

	private BigDecimal score4;

	private BigDecimal score5;

	public WorkSheetQuestion(RfqQuestion rfqQuestion)
	{
		this.setSequence(rfqQuestion.getSequence());
		this.setQuestionText(rfqQuestion.getQuestionText());
		this.setResponseType(rfqQuestion.getResponseType());
		this.setMaxPoints(rfqQuestion.getMaxPoints());
		this.setRfqQuestion(rfqQuestion);
	}

	public WorkSheetQuestion(RfqQuestion rfqQuestion, List vendorResponses, List responseValues)
	{
		this(rfqQuestion);

		if (vendorResponses != null)
		{
			for (int i = 0; i < vendorResponses.size(); i++)
			{
				this.setVendorResponses(i, (VendorResponse) vendorResponses.get(i), (ResponseValue) responseValues.get(i));
			}
		}
	}

	private void setVendorResponses(int vendIndex, VendorResponse vendorResponse, ResponseValue responseValue)
	{
		String response = "";
		String responseText = "";
		BigDecimal score = vendorResponse.getScore();

		if (!HiltonUtility.isEmpty(responseValue.getResponseText()))
		{
			responseText = " - " + responseValue.getResponseText();
		}

		if (this.getResponseType().equals(ChecklistEntryResponseType.YES_NO_AND_OPEN_TEXT))
		{
			response = HiltonUtility.ckNull(vendorResponse.getTextResponse()) + " " + HiltonUtility.ckNull(vendorResponse.getResponse());

		} else if (this.getResponseType().equals(ChecklistEntryResponseType.YES_NA) || this.getResponseType().equals(ChecklistEntryResponseType.MULTIPLE_CHOICE))
		{
			response = vendorResponse.getResponse();

		} else
		{
			response = vendorResponse.getTextResponse();
		}

		response += responseText;

		switch (vendIndex)
		{
			case 0:
				this.setResponse1(response);
				this.setScore1(score);
				break;
			case 1:
				this.setResponse2(response);
				this.setScore2(score);
				break;
			case 2:
				this.setResponse3(response);
				this.setScore3(score);
				break;
			case 3:
				this.setResponse4(response);
				this.setScore4(score);
				break;
			case 4:
				this.setResponse5(response);
				this.setScore5(score);
				break;
			default:
				break;
		}
	}

	public boolean equals(Object other)
	{
		if (!(other instanceof WorkSheetQuestion))
			return false;
		WorkSheetQuestion castOther = (WorkSheetQuestion) other;
		return new EqualsBuilder().append(this.getSequence(), castOther.getSequence()).isEquals();
	}

	public int hashCode()
	{
		return new HashCodeBuilder().append(this.getSequence()).toHashCode();
	}

	public String toString()
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("[WorkSheetQuestion:");
		buffer.append(sequence);
		buffer.append(" question: ");
		buffer.append(questionText);
		buffer.append(" response type: ");
		buffer.append(responseType);
		buffer.append(" max points: ");
		buffer.append(maxPoints);
		buffer.append(" response1: ");
		buffer.append(response1);
		buffer.append(" score1: ");
		buffer.append(score1);
		buffer.append(" response2: ");
		buffer.append(response2);
		buffer.append(" score2: ");
		buffer.append(score2);
		buffer.append(" response3: ");
		buffer.append(response3);
		buffer.append(" score3: ");
		buffer.append(score3);
		buffer.append(" response4: ");
		buffer.append(response4);
		buffer.append(" score4: ");
		buffer.append(score4);
		buffer.append("]");
		return buffer.toString();
	}

	/**
	 * @return the maxPoints
	 */
	public BigDecimal getMaxPoints()
	{
		return maxPoints;
	}

	/**
	 * @param maxPoints
	 *            the maxPoints to set
	 */
	public void setMaxPoints(BigDecimal maxPoints)
	{
		this.maxPoints = maxPoints;
	}

	/**
	 * @return the questionText
	 */
	public String getQuestionText()
	{
		return questionText;
	}

	/**
	 * @param questionText
	 *            the questionText to set
	 */
	public void setQuestionText(String questionText)
	{
		this.questionText = questionText;
	}

	/**
	 * @return the response1
	 */
	public String getResponse1()
	{
		return response1;
	}

	/**
	 * @param response1
	 *            the response1 to set
	 */
	public void setResponse1(String response1)
	{
		this.response1 = response1;
	}

	/**
	 * @return the response2
	 */
	public String getResponse2()
	{
		return response2;
	}

	/**
	 * @param response2
	 *            the response2 to set
	 */
	public void setResponse2(String response2)
	{
		this.response2 = response2;
	}

	/**
	 * @return the response3
	 */
	public String getResponse3()
	{
		return response3;
	}

	/**
	 * @param response3
	 *            the response3 to set
	 */
	public void setResponse3(String response3)
	{
		this.response3 = response3;
	}

	/**
	 * @return the response4
	 */
	public String getResponse4()
	{
		return response4;
	}

	/**
	 * @param response4
	 *            the response4 to set
	 */
	public void setResponse4(String response4)
	{
		this.response4 = response4;
	}

	/**
	 * @return the response5
	 */
	public String getResponse5()
	{
		return response5;
	}

	/**
	 * @param response5
	 *            the response5 to set
	 */
	public void setResponse5(String response5)
	{
		this.response5 = response5;
	}

	/**
	 * @return the responseType
	 */
	public String getResponseType()
	{
		return responseType;
	}

	/**
	 * @param responseType
	 *            the responseType to set
	 */
	public void setResponseType(String responseType)
	{
		this.responseType = responseType;
	}

	/**
	 * @return the rfqQuestion
	 */
	public RfqQuestion getRfqQuestion()
	{
		return rfqQuestion;
	}

	/**
	 * @param rfqQuestion
	 *            the rfqQuestion to set
	 */
	public void setRfqQuestion(RfqQuestion rfqQuestion)
	{
		this.rfqQuestion = rfqQuestion;
	}

	/**
	 * @return the score1
	 */
	public BigDecimal getScore1()
	{
		return score1;
	}

	/**
	 * @param score1
	 *            the score1 to set
	 */
	public void setScore1(BigDecimal score1)
	{
		this.score1 = score1;
	}

	/**
	 * @return the score2
	 */
	public BigDecimal getScore2()
	{
		return score2;
	}

	/**
	 * @param score2
	 *            the score2 to set
	 */
	public void setScore2(BigDecimal score2)
	{
		this.score2 = score2;
	}

	/**
	 * @return the score3
	 */
	public BigDecimal getScore3()
	{
		return score3;
	}

	/**
	 * @param score3
	 *            the score3 to set
	 */
	public void setScore3(BigDecimal score3)
	{
		this.score3 = score3;
	}

	/**
	 * @return the score4
	 */
	public BigDecimal getScore4()
	{
		return score4;
	}

	/**
	 * @param score4
	 *            the score4 to set
	 */
	public void setScore4(BigDecimal score4)
	{
		this.score4 = score4;
	}

	/**
	 * @return the score5
	 */
	public BigDecimal getScore5()
	{
		return score5;
	}

	/**
	 * @param score5
	 *            the score5 to set
	 */
	public void setScore5(BigDecimal score5)
	{
		this.score5 = score5;
	}

	/**
	 * @return the sequence
	 */
	public BigDecimal getSequence()
	{
		return sequence;
	}

	/**
	 * @param sequence
	 *            the sequence to set
	 */
	public void setSequence(BigDecimal sequence)
	{
		this.sequence = sequence;
	}

}
