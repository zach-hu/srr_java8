package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class InvFormPart implements Serializable {

    /** identifier field */
    private com.tsa.puridiom.entity.InvFormPartPK comp_id;

    /** nullable persistent field */
    private String measureBy;

    /** nullable persistent field */
    private java.math.BigDecimal overallWidth;

    /** nullable persistent field */
    private java.math.BigDecimal overallLen;

    /** nullable persistent field */
    private java.math.BigDecimal detachWidth;

    /** nullable persistent field */
    private java.math.BigDecimal detachLen;

    /** nullable persistent field */
    private String sizeUdf;

    /** nullable persistent field */
    private java.math.BigDecimal printSides;

    /** nullable persistent field */
    private java.math.BigDecimal printCopies;

    /** nullable persistent field */
    private String marginWords;

    /** nullable persistent field */
    private String addchgdel;

    /** nullable persistent field */
    private String blockout;

    /** nullable persistent field */
    private String copiesUdf;

    /** nullable persistent field */
    private String paperColor;

    /** nullable persistent field */
    private java.math.BigDecimal paperWeight;

    /** nullable persistent field */
    private String paperGrade;

    /** nullable persistent field */
    private String inkFront;

    /** nullable persistent field */
    private String inkBack;

    /** nullable persistent field */
    private String paperUdf;

    /** nullable persistent field */
    private String inkUdf;

    /** nullable persistent field */
    private java.math.BigDecimal carbonWeight;

    /** nullable persistent field */
    private String carbonGrade;

    /** nullable persistent field */
    private String carbonColor;

    /** nullable persistent field */
    private java.math.BigDecimal carbonSize;

    /** nullable persistent field */
    private String carbonSpot;

    /** nullable persistent field */
    private String carbonStrip;

    /** nullable persistent field */
    private String carbonUdf;

    /** nullable persistent field */
    private java.math.BigDecimal punchHoles;

    /** nullable persistent field */
    private java.math.BigDecimal punchSize;

    /** nullable persistent field */
    private java.math.BigDecimal punchCenter;

    /** nullable persistent field */
    private String punchPos;

    /** nullable persistent field */
    private java.math.BigDecimal mperfLeft;

    /** nullable persistent field */
    private java.math.BigDecimal mperfRight;

    /** nullable persistent field */
    private java.math.BigDecimal operfLeft;

    /** nullable persistent field */
    private java.math.BigDecimal operfRight;

    /** nullable persistent field */
    private String perfType;

    /** nullable persistent field */
    private String turnType;

    /** full constructor */
    public InvFormPart(com.tsa.puridiom.entity.InvFormPartPK comp_id, java.lang.String measureBy, java.math.BigDecimal overallWidth, java.math.BigDecimal overallLen, java.math.BigDecimal detachWidth, java.math.BigDecimal detachLen, java.lang.String sizeUdf, java.math.BigDecimal printSides, java.math.BigDecimal printCopies, java.lang.String marginWords, java.lang.String addchgdel, java.lang.String blockout, java.lang.String copiesUdf, java.lang.String paperColor, java.math.BigDecimal paperWeight, java.lang.String paperGrade, java.lang.String inkFront, java.lang.String inkBack, java.lang.String paperUdf, java.lang.String inkUdf, java.math.BigDecimal carbonWeight, java.lang.String carbonGrade, java.lang.String carbonColor, java.math.BigDecimal carbonSize, java.lang.String carbonSpot, java.lang.String carbonStrip, java.lang.String carbonUdf, java.math.BigDecimal punchHoles, java.math.BigDecimal punchSize, java.math.BigDecimal punchCenter, java.lang.String punchPos, java.math.BigDecimal mperfLeft, java.math.BigDecimal mperfRight, java.math.BigDecimal operfLeft, java.math.BigDecimal operfRight, java.lang.String perfType, java.lang.String turnType) {
        this.comp_id = comp_id;
        this.measureBy = measureBy;
        this.overallWidth = overallWidth;
        this.overallLen = overallLen;
        this.detachWidth = detachWidth;
        this.detachLen = detachLen;
        this.sizeUdf = sizeUdf;
        this.printSides = printSides;
        this.printCopies = printCopies;
        this.marginWords = marginWords;
        this.addchgdel = addchgdel;
        this.blockout = blockout;
        this.copiesUdf = copiesUdf;
        this.paperColor = paperColor;
        this.paperWeight = paperWeight;
        this.paperGrade = paperGrade;
        this.inkFront = inkFront;
        this.inkBack = inkBack;
        this.paperUdf = paperUdf;
        this.inkUdf = inkUdf;
        this.carbonWeight = carbonWeight;
        this.carbonGrade = carbonGrade;
        this.carbonColor = carbonColor;
        this.carbonSize = carbonSize;
        this.carbonSpot = carbonSpot;
        this.carbonStrip = carbonStrip;
        this.carbonUdf = carbonUdf;
        this.punchHoles = punchHoles;
        this.punchSize = punchSize;
        this.punchCenter = punchCenter;
        this.punchPos = punchPos;
        this.mperfLeft = mperfLeft;
        this.mperfRight = mperfRight;
        this.operfLeft = operfLeft;
        this.operfRight = operfRight;
        this.perfType = perfType;
        this.turnType = turnType;
    }

    /** default constructor */
    public InvFormPart() {
    }

    /** minimal constructor */
    public InvFormPart(com.tsa.puridiom.entity.InvFormPartPK comp_id) {
        this.comp_id = comp_id;
    }

    public com.tsa.puridiom.entity.InvFormPartPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(com.tsa.puridiom.entity.InvFormPartPK comp_id) {
        this.comp_id = comp_id;
    }

    public java.lang.String getMeasureBy() {
		return (java.lang.String)HiltonUtility.ckNull(this.measureBy).trim();
    }

    public void setMeasureBy(java.lang.String measureBy) {
		if (!HiltonUtility.isEmpty(measureBy) && measureBy.length() > 2) {
			measureBy = measureBy.substring(0, 2);
		}
		this.measureBy = measureBy;
    }

    public java.math.BigDecimal getOverallWidth() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.overallWidth);
    }

    public void setOverallWidth(java.math.BigDecimal overallWidth) {
        this.overallWidth = overallWidth;
    }

    public java.math.BigDecimal getOverallLen() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.overallLen);
    }

    public void setOverallLen(java.math.BigDecimal overallLen) {
        this.overallLen = overallLen;
    }

    public java.math.BigDecimal getDetachWidth() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.detachWidth);
    }

    public void setDetachWidth(java.math.BigDecimal detachWidth) {
        this.detachWidth = detachWidth;
    }

    public java.math.BigDecimal getDetachLen() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.detachLen);
    }

    public void setDetachLen(java.math.BigDecimal detachLen) {
        this.detachLen = detachLen;
    }

    public java.lang.String getSizeUdf() {
		return (java.lang.String)HiltonUtility.ckNull(this.sizeUdf).trim();
    }

    public void setSizeUdf(java.lang.String sizeUdf) {
		if (!HiltonUtility.isEmpty(sizeUdf) && sizeUdf.length() > 20) {
			sizeUdf = sizeUdf.substring(0, 20);
		}
		this.sizeUdf = sizeUdf;
    }

    public java.math.BigDecimal getPrintSides() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.printSides);
    }

    public void setPrintSides(java.math.BigDecimal printSides) {
        this.printSides = printSides;
    }

    public java.math.BigDecimal getPrintCopies() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.printCopies);
    }

    public void setPrintCopies(java.math.BigDecimal printCopies) {
        this.printCopies = printCopies;
    }

    public java.lang.String getMarginWords() {
		return (java.lang.String)HiltonUtility.ckNull(this.marginWords).trim();
    }

    public void setMarginWords(java.lang.String marginWords) {
		if (!HiltonUtility.isEmpty(marginWords) && marginWords.length() > 40) {
			marginWords = marginWords.substring(0, 40);
		}
		this.marginWords = marginWords;
    }

    public java.lang.String getAddchgdel() {
		return (java.lang.String)HiltonUtility.ckNull(this.addchgdel).trim();
    }

    public void setAddchgdel(java.lang.String addchgdel) {
		if (!HiltonUtility.isEmpty(addchgdel) && addchgdel.length() > 3) {
			addchgdel = addchgdel.substring(0, 3);
		}
		this.addchgdel = addchgdel;
    }

    public java.lang.String getBlockout() {
		return (java.lang.String)HiltonUtility.ckNull(this.blockout).trim();
    }

    public void setBlockout(java.lang.String blockout) {
		if (!HiltonUtility.isEmpty(blockout) && blockout.length() > 15) {
			blockout = blockout.substring(0, 15);
		}
		this.blockout = blockout;
    }

    public java.lang.String getCopiesUdf() {
		return (java.lang.String)HiltonUtility.ckNull(this.copiesUdf).trim();
    }

    public void setCopiesUdf(java.lang.String copiesUdf) {
		if (!HiltonUtility.isEmpty(copiesUdf) && copiesUdf.length() > 20) {
			copiesUdf = copiesUdf.substring(0, 20);
		}
		this.copiesUdf = copiesUdf;
    }

    public java.lang.String getPaperColor() {
		return (java.lang.String)HiltonUtility.ckNull(this.paperColor).trim();
    }

    public void setPaperColor(java.lang.String paperColor) {
		if (!HiltonUtility.isEmpty(paperColor) && paperColor.length() > 15) {
			paperColor = paperColor.substring(0, 15);
		}
		this.paperColor = paperColor;
    }

    public java.math.BigDecimal getPaperWeight() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.paperWeight);
    }

    public void setPaperWeight(java.math.BigDecimal paperWeight) {
        this.paperWeight = paperWeight;
    }

    public java.lang.String getPaperGrade() {
		return (java.lang.String)HiltonUtility.ckNull(this.paperGrade).trim();
    }

    public void setPaperGrade(java.lang.String paperGrade) {
		if (!HiltonUtility.isEmpty(paperGrade) && paperGrade.length() > 15) {
			paperGrade = paperGrade.substring(0, 15);
		}
		this.paperGrade = paperGrade;
    }

    public java.lang.String getInkFront() {
		return (java.lang.String)HiltonUtility.ckNull(this.inkFront).trim();
    }

    public void setInkFront(java.lang.String inkFront) {
		if (!HiltonUtility.isEmpty(inkFront) && inkFront.length() > 15) {
			inkFront = inkFront.substring(0, 15);
		}
		this.inkFront = inkFront;
    }

    public java.lang.String getInkBack() {
		return (java.lang.String)HiltonUtility.ckNull(this.inkBack).trim();
    }

    public void setInkBack(java.lang.String inkBack) {
		if (!HiltonUtility.isEmpty(inkBack) && inkBack.length() > 15) {
			inkBack = inkBack.substring(0, 15);
		}
		this.inkBack = inkBack;
    }

    public java.lang.String getPaperUdf() {
		return (java.lang.String)HiltonUtility.ckNull(this.paperUdf).trim();
    }

    public void setPaperUdf(java.lang.String paperUdf) {
		if (!HiltonUtility.isEmpty(paperUdf) && paperUdf.length() > 20) {
			paperUdf = paperUdf.substring(0, 20);
		}
		this.paperUdf = paperUdf;
    }

    public java.lang.String getInkUdf() {
		return (java.lang.String)HiltonUtility.ckNull(this.inkUdf).trim();
    }

    public void setInkUdf(java.lang.String inkUdf) {
		if (!HiltonUtility.isEmpty(inkUdf) && inkUdf.length() > 20) {
			inkUdf = inkUdf.substring(0, 20);
		}
		this.inkUdf = inkUdf;
    }

    public java.math.BigDecimal getCarbonWeight() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.carbonWeight);
    }

    public void setCarbonWeight(java.math.BigDecimal carbonWeight) {
        this.carbonWeight = carbonWeight;
    }

    public java.lang.String getCarbonGrade() {
		return (java.lang.String)HiltonUtility.ckNull(this.carbonGrade).trim();
    }

    public void setCarbonGrade(java.lang.String carbonGrade) {
	if (!HiltonUtility.isEmpty(carbonGrade) && carbonGrade.length() > 15) {
		carbonGrade = carbonGrade.substring(0, 15);
	}
	this.carbonGrade = carbonGrade;
    }

    public java.lang.String getCarbonColor() {
		return (java.lang.String)HiltonUtility.ckNull(this.carbonColor).trim();
    }

    public void setCarbonColor(java.lang.String carbonColor) {
		if (!HiltonUtility.isEmpty(carbonColor) && carbonColor.length() > 15) {
			carbonColor = carbonColor.substring(0, 15);
		}
		this.carbonColor = carbonColor;
    }

    public java.math.BigDecimal getCarbonSize() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.carbonSize);
    }

    public void setCarbonSize(java.math.BigDecimal carbonSize) {
        this.carbonSize = carbonSize;
    }

    public java.lang.String getCarbonSpot() {
		return (java.lang.String)HiltonUtility.ckNull(this.carbonSpot).trim();
    }

    public void setCarbonSpot(java.lang.String carbonSpot) {
		if (!HiltonUtility.isEmpty(carbonSpot) && carbonSpot.length() > 20) {
			carbonSpot = carbonSpot.substring(0, 20);
		}
		this.carbonSpot = carbonSpot;
    }

    public java.lang.String getCarbonStrip() {
		return (java.lang.String)HiltonUtility.ckNull(this.carbonStrip).trim();
    }

    public void setCarbonStrip(java.lang.String carbonStrip) {
		if (!HiltonUtility.isEmpty(carbonStrip) && carbonStrip.length() > 20) {
			carbonStrip = carbonStrip.substring(0, 20);
		}
		this.carbonStrip = carbonStrip;
    }

    public java.lang.String getCarbonUdf() {
		return (java.lang.String)HiltonUtility.ckNull(this.carbonUdf).trim();
    }

    public void setCarbonUdf(java.lang.String carbonUdf) {
		if (!HiltonUtility.isEmpty(carbonUdf) && carbonUdf.length() > 20) {
			carbonUdf = carbonUdf.substring(0, 20);
		}
		this.carbonUdf = carbonUdf;
    }

    public java.math.BigDecimal getPunchHoles() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.punchHoles);
    }

    public void setPunchHoles(java.math.BigDecimal punchHoles) {
        this.punchHoles = punchHoles;
    }

    public java.math.BigDecimal getPunchSize() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.punchSize);
    }

    public void setPunchSize(java.math.BigDecimal punchSize) {
        this.punchSize = punchSize;
    }

    public java.math.BigDecimal getPunchCenter() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.punchCenter);
    }

    public void setPunchCenter(java.math.BigDecimal punchCenter) {
        this.punchCenter = punchCenter;
    }

    public java.lang.String getPunchPos() {
		return (java.lang.String)HiltonUtility.ckNull(this.punchPos).trim();
    }

    public void setPunchPos(java.lang.String punchPos) {
		if (!HiltonUtility.isEmpty(punchPos) && punchPos.length() > 20) {
			punchPos = punchPos.substring(0, 20);
		}
		this.punchPos = punchPos;
    }

    public java.math.BigDecimal getMperfLeft() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.mperfLeft);
    }

    public void setMperfLeft(java.math.BigDecimal mperfLeft) {
        this.mperfLeft = mperfLeft;
    }

    public java.math.BigDecimal getMperfRight() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.mperfRight);
    }

    public void setMperfRight(java.math.BigDecimal mperfRight) {
        this.mperfRight = mperfRight;
    }

    public java.math.BigDecimal getOperfLeft() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.operfLeft);
    }

    public void setOperfLeft(java.math.BigDecimal operfLeft) {
        this.operfLeft = operfLeft;
    }

    public java.math.BigDecimal getOperfRight() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.operfRight);
    }

    public void setOperfRight(java.math.BigDecimal operfRight) {
        this.operfRight = operfRight;
    }

    public java.lang.String getPerfType() {
		return (java.lang.String)HiltonUtility.ckNull(this.perfType).trim();
    }

    public void setPerfType(java.lang.String perfType) {
		if (!HiltonUtility.isEmpty(perfType) && perfType.length() > 15) {
			perfType = perfType.substring(0, 15);
		}
		this.perfType = perfType;
    }

    public java.lang.String getTurnType() {
		return (java.lang.String)HiltonUtility.ckNull(this.turnType).trim();
    }

    public void setTurnType(java.lang.String turnType) {
		if (!HiltonUtility.isEmpty(turnType) && turnType.length() > 20) {
			turnType = turnType.substring(0, 20);
		}
		this.turnType = turnType;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof InvFormPart) ) return false;
        InvFormPart castOther = (InvFormPart) other;
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
