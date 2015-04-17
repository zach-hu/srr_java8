package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class DocComment implements Serializable {

    /** identifier field */
    private com.tsa.puridiom.entity.DocCommentPK comp_id;

    /** persistent field */
    private String referenceType;

    /** nullable persistent field */
    private String commentId;

    /** nullable persistent field */
    private String commentTitle;

    /** nullable persistent field */
    private java.math.BigDecimal icText;

    /** nullable persistent field */
    private String commentPrint;

    /** nullable persistent field */
    private String commentPlace;

    /** nullable persistent field */
    private String commentSource;

    /** nullable persistent field */
    private String commentBold;

    /** nullable persistent field */
    private String commentPublic;

    /** nullable persistent field */
    private String commentWebpost;

    private DocText docText;

    /** full constructor */
    public DocComment(com.tsa.puridiom.entity.DocCommentPK comp_id, java.lang.String referenceType, java.lang.String commentId, java.lang.String commentTitle, java.math.BigDecimal icText, java.lang.String commentPrint, java.lang.String commentPlace, java.lang.String commentSource, java.lang.String commentBold, java.lang.String commentPublic, java.lang.String commentWebpost) {
        this.comp_id = comp_id;
        this.referenceType = referenceType;
        this.commentId = commentId;
        this.commentTitle = commentTitle;
        this.icText = icText;
        this.commentPrint = commentPrint;
        this.commentPlace = commentPlace;
        this.commentSource = commentSource;
        this.commentBold = commentBold;
        this.commentPublic = commentPublic;
        this.commentWebpost = commentWebpost;
    }

    /** default constructor */
    public DocComment() {
    }

    /** minimal constructor */
    public DocComment(com.tsa.puridiom.entity.DocCommentPK comp_id, java.lang.String referenceType) {
        this.comp_id = comp_id;
        this.referenceType = referenceType;
    }

    public com.tsa.puridiom.entity.DocCommentPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(com.tsa.puridiom.entity.DocCommentPK comp_id) {
        this.comp_id = comp_id;
    }

    public java.lang.String getReferenceType() {
		return (java.lang.String)HiltonUtility.ckNull(this.referenceType).trim();
    }

    public void setReferenceType(java.lang.String referenceType) {
		if (!HiltonUtility.isEmpty(referenceType) && referenceType.length() > 3) {
			referenceType = referenceType.substring(0, 3);
		}
		this.referenceType = referenceType;
    }

    public java.lang.String getCommentId() {
		return (java.lang.String)HiltonUtility.ckNull(this.commentId).trim();
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

    public java.math.BigDecimal getIcText() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icText);
    }

    public void setIcText(java.math.BigDecimal icText) {
        this.icText = icText;
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

    public java.lang.String getCommentPlace() {
		return (java.lang.String)HiltonUtility.ckNull(this.commentPlace).trim();
    }

    public void setCommentPlace(java.lang.String commentPlace) {
		if (!HiltonUtility.isEmpty(commentPlace) && commentPlace.length() > 1) {
			commentPlace = commentPlace.substring(0, 1);
		}
		this.commentPlace = commentPlace;
    }

    public java.lang.String getCommentSource() {
		return (java.lang.String)HiltonUtility.ckNull(this.commentSource).trim();
    }

    public void setCommentSource(java.lang.String commentSource) {
		if (!HiltonUtility.isEmpty(commentSource) && commentSource.length() > 3) {
			commentSource = commentSource.substring(0, 3);
		}
		this.commentSource = commentSource;
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

    public java.lang.String getCommentWebpost() {
		return (java.lang.String)HiltonUtility.ckNull(this.commentWebpost).trim();
    }

    public void setCommentWebpost(java.lang.String commentWebpost) {
		if (!HiltonUtility.isEmpty(commentWebpost) && commentWebpost.length() > 1) {
			commentWebpost = commentWebpost.substring(0, 1);
		}
		this.commentWebpost = commentWebpost;
    }

	public DocText getDocText() {
		return this.docText;
	}

    public String getCommentText() {
		return (java.lang.String)HiltonUtility.ckNull(this.docText.getStdText()).trim();
    }

	public void setDocText(DocText docText) {
		this.docText = docText;
	}

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof DocComment) ) return false;
        DocComment castOther = (DocComment) other;
        return new EqualsBuilder()
            .append(this.getComp_id(), castOther.getComp_id())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getComp_id())
            .toHashCode();
    }

}
