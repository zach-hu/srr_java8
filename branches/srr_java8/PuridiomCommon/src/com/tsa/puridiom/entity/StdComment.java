package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class StdComment implements Serializable {

    /** identifier field */
    private String commentId;

    /** nullable persistent field */
    private String commentTitle;

    /** nullable persistent field */
    private String commentPrint;

    /** nullable persistent field */
    private java.math.BigDecimal icText;

    /** nullable persistent field */
    private java.util.Date dateEntered;

    /** nullable persistent field */
    private java.util.Date dateExpires;

    /** nullable persistent field */
    private String owner;

    /** nullable persistent field */
    private String status;

    /** nullable persistent field */
    private String commentBold;

    /** nullable persistent field */
    private String commentPublic;

    /** full constructor */
    public StdComment(java.lang.String commentId, java.lang.String commentTitle, java.lang.String commentPrint, java.math.BigDecimal icText, java.util.Date dateEntered, java.util.Date dateExpires, java.lang.String owner, java.lang.String status, java.lang.String commentBold, java.lang.String commentPublic) {
        this.commentId = commentId;
        this.commentTitle = commentTitle;
        this.commentPrint = commentPrint;
        this.icText = icText;
        this.dateEntered = dateEntered;
        this.dateExpires = dateExpires;
        this.owner = owner;
        this.status = status;
        this.commentBold = commentBold;
        this.commentPublic = commentPublic;
    }

    /** default constructor */
    public StdComment() {
    }

    /** minimal constructor */
    public StdComment(java.lang.String commentId) {
        this.commentId = commentId;
    }

    public java.lang.String getCommentId() {
		return (java.lang.String)HiltonUtility.ckNull(this.commentId);
    }

    public void setCommentId(java.lang.String commentId) {
		if (!HiltonUtility.isEmpty(commentId) && commentId.length() > 15) {
			commentId = commentId.substring(0, 15);
		}
		this.commentId = commentId;
    }

    public java.lang.String getCommentTitle() {
		return (java.lang.String)HiltonUtility.ckNull(this.commentTitle).trim();
    }

    public void setCommentTitle(java.lang.String commentTitle) {
		if (!HiltonUtility.isEmpty(commentTitle) && commentTitle.length() > 60) {
			commentTitle = commentTitle.substring(0, 60);
		}
		this.commentTitle = commentTitle;
    }

    public java.lang.String getCommentPrint() {
		return (java.lang.String)HiltonUtility.ckNull(this.commentPrint).trim();
    }

    public void setCommentPrint(java.lang.String commentPrint) {
		if (!HiltonUtility.isEmpty(commentPrint) && commentPrint.length() > 1) {
			commentPrint = commentPrint.substring(0, 1);
		}
		this.commentPrint = commentPrint;
    }

    public java.math.BigDecimal getIcText() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icText);
    }

    public void setIcText(java.math.BigDecimal icText) {
        this.icText = icText;
    }

    public java.util.Date getDateEntered() {
		return this.dateEntered;
//		return HiltonUtility.ckNull(this.dateEntered);
//		return (java.sql.Date)HiltonUtility.ckNull(this.dateEntered);
    }

    public void setDateEntered(java.util.Date dateEntered) {
        this.dateEntered = dateEntered;
    }

    public java.util.Date getDateExpires() {
		return this.dateExpires;
//		return HiltonUtility.ckNull(this.dateExpires);
//		return (java.sql.Date)HiltonUtility.ckNull(this.dateExpires);
    }

    public void setDateExpires(java.util.Date dateExpires) {
        this.dateExpires = dateExpires;
    }

    public java.lang.String getOwner() {
		return (java.lang.String)HiltonUtility.ckNull(this.owner).trim();
    }

    public void setOwner(java.lang.String owner) {
		if (!HiltonUtility.isEmpty(owner) && owner.length() > 15) {
			owner = owner.substring(0, 15);
		}
		this.owner = owner;
    }

    public java.lang.String getStatus() {
		return (java.lang.String)HiltonUtility.ckNull(this.status).trim();
    }

    public void setStatus(java.lang.String status) {
		if (!HiltonUtility.isEmpty(status) && status.length() > 4) {
			status = status.substring(0, 4);
		}
		this.status = status;
    }

    public java.lang.String getCommentBold() {
		return (java.lang.String)HiltonUtility.ckNull(this.commentBold).trim();
    }

    public void setCommentBold(java.lang.String commentBold) {
		if (!HiltonUtility.isEmpty(commentBold) && commentBold.length() > 1) {
			commentBold = commentBold.substring(0, 1);
		}
		this.commentBold = commentBold;
    }

    public java.lang.String getCommentPublic() {
		return (java.lang.String)HiltonUtility.ckNull(this.commentPublic).trim();
    }

    public void setCommentPublic(java.lang.String commentPublic) {
		if (!HiltonUtility.isEmpty(commentPublic) && commentPublic.length() > 1) {
			commentPublic = commentPublic.substring(0, 1);
		}
		this.commentPublic = commentPublic;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("commentId", getCommentId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof StdComment) ) return false;
        StdComment castOther = (StdComment) other;
        return new EqualsBuilder()
            .append(this.getCommentId(), castOther.getCommentId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getCommentId())
            .toHashCode();
    }

}
