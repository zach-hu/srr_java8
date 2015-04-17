package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class PerformanceDetail implements Serializable {

	/** identifier field */
    private com.tsa.puridiom.entity.PerformanceDetailPK comp_id;

    /** nullable persistent field */
    private String evalName;

    /** nullable persistent field */
    private java.math.BigDecimal evalRating;

    /** nullable persistent field */
    private java.util.Date evalDate;

    /** nullable persistent field */
    private String evalNotes;

    /** nullable persistent field */
    private java.math.BigDecimal evalSequence;

    /** nullable persistent field */
    private String evalNotify;

    /** nullable persistent field */
    private java.math.BigDecimal evalWeight;

    /** nullable persistent field */
    private java.util.Date dateApproved;

    /** nullable persistent field */
    private String status;

    /** nullable persistent field */
    private String internalNotes;

    /** nullable persistent field */
    private String evaluator;

    /** nullable persistent field */
    private String assignedBy;

    /** nullable persistent field */
    private java.lang.String dateAssigned;

    /** nullable persistent field */
    private java.lang.String timeAssigned;

    private java.lang.String isNew;

	/** full constructor */
    public PerformanceDetail(com.tsa.puridiom.entity.PerformanceDetailPK comp_id, java.lang.String evalName, java.math.BigDecimal  evalRating, java.util.Date evalDate, java.lang.String evalNotes, java.math.BigDecimal  evalSequence, java.lang.String evalNotify, java.math.BigDecimal evalWeight, java.util.Date dateApproved, java.lang.String status, java.lang.String internalNotes, java.lang.String evaluator, java.lang.String assignedBy, java.lang.String dateAssigned, java.lang.String timeAssigned) {
        this.comp_id = comp_id;
        this.evalName = evalName;
        this.evalRating = evalRating;
        this.evalDate = evalDate;
        this.evalNotes = evalNotes;
        this.evalSequence = evalSequence;
        this.evalNotify = evalNotify;
        this.evalWeight = evalWeight;
        this.dateApproved = dateApproved;
        this.status = status;
        this.internalNotes = internalNotes;
        this.evaluator = evaluator;
        this.assignedBy = assignedBy;
        this.dateAssigned = dateAssigned;
    }

    /** default constructor */
    public PerformanceDetail() {
    }

    /** minimal constructor */
    public PerformanceDetail(com.tsa.puridiom.entity.PerformanceDetailPK comp_id) {
        this.comp_id = comp_id;
    }

    public java.lang.String getEvalName() {
		return (java.lang.String)HiltonUtility.ckNull(this.evalName).trim();
    }

    public void setEvalName(java.lang.String evalName) {
		if (!HiltonUtility.isEmpty(evalName) && evalName.length() > 15) {
			evalName = evalName.substring(0, 15);
		}
		this.evalName = evalName;
    }

    public java.math.BigDecimal  getEvalRating() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.evalRating);
    }
    public java.math.BigDecimal  getEvalRatingText() {
        return this.evalRating;
    }

    public void setEvalRating(java.math.BigDecimal  evalRating) {
        this.evalRating = evalRating;
    }

    public java.util.Date getEvalDate() {
        return this.evalDate;
    }

    public void setEvalDate(java.util.Date evalDate) {
        this.evalDate = evalDate;
    }

    public java.lang.String getEvalNotes() {
		return (java.lang.String)HiltonUtility.ckNull(this.evalNotes).trim();
    }

    public void setEvalNotes(java.lang.String evalNotes) {
		if (!HiltonUtility.isEmpty(evalNotes) && evalNotes.length() > 4000) {
			evalNotes = evalNotes.substring(0, 4000);
		}
		this.evalNotes = evalNotes;
    }

    public java.math.BigDecimal  getEvalSequence() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.evalSequence);
    }

    public void setEvalSequence(java.math.BigDecimal  evalSequence) {
        this.evalSequence = evalSequence;
    }

    public java.lang.String getEvalNotify() {
		return (java.lang.String)HiltonUtility.ckNull(this.evalNotify).trim();
    }

    public void setEvalNotify(java.lang.String evalNotify) {
		if (!HiltonUtility.isEmpty(evalNotify) && evalNotify.length() > 1) {
			evalNotify = evalNotify.substring(0, 1);
		}
		this.evalNotify = evalNotify;
    }

    public java.math.BigDecimal getEvalWeight() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.evalWeight);
    }

    public void setEvalWeight(java.math.BigDecimal evalWeight) {
        this.evalWeight = evalWeight;
    }

    public java.util.Date getDateApproved() {
        return this.dateApproved;
    }

    public void setDateApproved(java.util.Date dateApproved) {
        this.dateApproved = dateApproved;
    }

    public java.lang.String getStatus() {
		return (java.lang.String)HiltonUtility.ckNull(this.status).trim();
    }

    public void setStatus(java.lang.String status) {
		if (!HiltonUtility.isEmpty(status) && status.length() > 5) {
			status = status.substring(0, 5);
		}
		this.status = status;
    }

    public java.lang.String getInternalNotes() {
		return (java.lang.String)HiltonUtility.ckNull(this.internalNotes).trim();
    }

    public void setInternalNotes(java.lang.String internalNotes) {
		if (!HiltonUtility.isEmpty(internalNotes) && internalNotes.length() > 3000) {
			internalNotes = internalNotes.substring(0, 3000);
		}
		this.internalNotes = internalNotes;
    }

    public String toString() {
        return new ToStringBuilder(this)
        	.append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof PerformanceDetail) ) return false;
        PerformanceDetail castOther = (PerformanceDetail) other;
        return new EqualsBuilder()
        .append(this.getComp_id(), castOther.getComp_id())
        .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
        	.append(getComp_id())
            .toHashCode();
    }

	public com.tsa.puridiom.entity.PerformanceDetailPK getComp_id() {
		return comp_id;
	}

	public void setComp_id(com.tsa.puridiom.entity.PerformanceDetailPK comp_id) {
		this.comp_id = comp_id;
	}

	public String getEvaluator() {
		return (java.lang.String)HiltonUtility.ckNull(this.evaluator).trim();
	}

	public void setEvaluator(String evaluator) {
		if (!HiltonUtility.isEmpty(evaluator) && evaluator.length() > 255) {
			evaluator = evaluator.substring(0, 255);
		}
		this.evaluator = evaluator;
	}

	public String getAssignedBy() {
		return (java.lang.String)HiltonUtility.ckNull(this.assignedBy).trim();
	}

	public void setAssignedBy(String assignedBy) {
		if (!HiltonUtility.isEmpty(assignedBy) && assignedBy.length() > 25) {
			assignedBy = assignedBy.substring(0, 25);
		}
		this.assignedBy = assignedBy;
	}

	public java.lang.String getDateAssigned() {
		return (java.lang.String)HiltonUtility.ckNull(this.dateAssigned).trim();
	}

	public void setDateAssigned(java.lang.String dateAssigned) {
		if (!HiltonUtility.isEmpty(dateAssigned) && dateAssigned.length() > 255) {
			dateAssigned = dateAssigned.substring(0, 255);
		}
		this.dateAssigned = dateAssigned;
	}

	public java.lang.String getTimeAssigned() {
		return (java.lang.String)HiltonUtility.ckNull(this.timeAssigned).trim();
	}

	public void setTimeAssigned(java.lang.String timeAssigned) {
		if (!HiltonUtility.isEmpty(timeAssigned) && timeAssigned.length() > 255) {
			timeAssigned = timeAssigned.substring(0, 255);
		}
		this.timeAssigned = timeAssigned;
	}

	public java.lang.String getIsNew() {
		return (java.lang.String)HiltonUtility.ckNull(this.isNew).trim();
	}

	public void setIsNew(java.lang.String isNew) {
		this.isNew = isNew;
	}

}
