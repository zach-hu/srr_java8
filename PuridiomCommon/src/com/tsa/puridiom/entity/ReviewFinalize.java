package com.tsa.puridiom.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.tsa.puridiom.common.utility.HiltonUtility;

/** @author nestor */
public class ReviewFinalize implements Serializable
{
	/** identifier field */
	private java.math.BigDecimal icHeader;

	private java.math.BigDecimal icReview;

	/** nullable persistent field */
	private String step;

	/** nullable persistent field */
	private String owner;

	/** nullable persistent field */
	private String revisedBy;

	/** nullable persistent field */
	private String completed;

	private BigDecimal dateCompleted;

	private String notes;

	/** full constructor */
	public ReviewFinalize(java.math.BigDecimal icReview, java.math.BigDecimal icHeader, java.lang.String step, java.lang.String owner, java.lang.String revisedBy, String completed, BigDecimal dateCompleted, String notes)
	{
		this.icReview = icReview;
		this.icHeader = icHeader;
		this.step = step;
		this.owner = owner;
		this.revisedBy = revisedBy;
		this.completed = completed;
		this.notes = notes;
		this.dateCompleted = dateCompleted;
	}

	/** default constructor */
	public ReviewFinalize()
	{
	}

	/** minimal constructor */
	public ReviewFinalize(java.math.BigDecimal icReview)
	{
		this.icReview = icReview;
	}

	public boolean isReviewCompleted()
	{
		return this.getCompleted().equalsIgnoreCase("Y") ? true : false;
	}

	public String getCompleted()
	{
		return (java.lang.String)HiltonUtility.ckNull(this.completed).trim();
	}

	public void setCompleted(String completed)
	{
		this.completed = completed;
	}

	public java.math.BigDecimal getIcHeader()
	{
		//return icHeader;
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icHeader);
	}

	public void setIcHeader(java.math.BigDecimal icHeader)
	{
		this.icHeader = icHeader;
	}

	public String getOwner()
	{
		return (java.lang.String)HiltonUtility.ckNull(this.owner).trim();
	}

	public void setOwner(String owner)
	{
		this.owner = owner;
	}

	public String getRevisedBy()
	{
		return (java.lang.String)HiltonUtility.ckNull(this.revisedBy).trim();
	}

	public void setRevisedBy(String revisedBy)
	{
		this.revisedBy = revisedBy;
	}

	public String getStep()
	{
		return (java.lang.String)HiltonUtility.ckNull(this.step).trim();
	}

	public void setStep(String step)
	{
		this.step = step;
	}

	public BigDecimal getDateCompleted() {
		return dateCompleted;
	}

	public Date getDateCompletedAsDate() {
		Date date = new Date();
		if(this.getDateCompleted() != null)
		{
			date.setTime(this.getDateCompleted().longValue());
			return date;
		}

		return null;
	}

	public void setDateCompleted(BigDecimal dateCompleted) {
		this.dateCompleted = dateCompleted;
	}

	public void setDateCompleted(Date dateCompleted) {
		if(dateCompleted == null)
		{
			this.dateCompleted = null;
		}
		else {
			this.dateCompleted = new BigDecimal(String.valueOf(dateCompleted.getTime()));
		}

	}

	public String getNotes() {
		return (java.lang.String)HiltonUtility.ckNull(this.notes).trim();
	}

	public void setNotes(String notes) {
		if (!HiltonUtility.isEmpty(notes) && notes.length() > 2000) {
			notes = notes.substring(0, 2000);
		}
		this.notes = notes;
	}

	public java.math.BigDecimal getIcReview() {
		return icReview;
	}

	public void setIcReview(java.math.BigDecimal icReview) {
		this.icReview = icReview;
	}

}
