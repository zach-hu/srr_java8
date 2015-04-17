package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class News implements Serializable {

    /** identifier field */
    private java.math.BigDecimal icNews;

    /** persistent field */
    private java.math.BigDecimal sequence;

    /** nullable persistent field */
    private String newsText;

    /** nullable persistent field */
    private String newsFont;

    /** nullable persistent field */
    private String newsLink;

    /** nullable persistent field */
    private String newsImage;

    /** nullable persistent field */
    private String newsAltTag;

    /** full constructor */
    public News(java.math.BigDecimal icNews, java.math.BigDecimal sequence, java.lang.String newsText, java.lang.String newsFont, java.lang.String newsLink, java.lang.String newsImage, java.lang.String newsAltTag) {
        this.icNews = icNews;
        this.sequence = sequence;
        this.newsText = newsText;
        this.newsFont = newsFont;
        this.newsLink = newsLink;
        this.newsImage = newsImage;
        this.newsAltTag = newsAltTag;
    }

    /** default constructor */
    public News() {
    }

    /** minimal constructor */
    public News(java.math.BigDecimal icNews) {
    	this.icNews = icNews;
    }

    public java.math.BigDecimal getIcNews() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icNews);
    }

    public void setIcNews(java.math.BigDecimal icNews) {
        this.icNews = icNews;
    }

    public java.math.BigDecimal getSequence() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.sequence);
    }

    public void setSequence(java.math.BigDecimal sequence) {
        this.sequence = sequence;
    }

    public java.lang.String getNewsText() {
		return (java.lang.String)HiltonUtility.ckNull(this.newsText).trim();
    }

    public void setNewsText(java.lang.String newsText) {
		if (!HiltonUtility.isEmpty(newsText) && newsText.length() > 2000) {
			newsText = newsText.substring(0, 2000);
		}
		this.newsText = newsText;
    }

    public java.lang.String getNewsFont() {
		return (java.lang.String)HiltonUtility.ckNull(this.newsFont).trim();
    }

    public void setNewsFont(java.lang.String newsFont) {
		if (!HiltonUtility.isEmpty(newsFont) && newsFont.length() > 40) {
			newsFont = newsFont.substring(0, 40);
		}
		this.newsFont = newsFont;
    }

    public java.lang.String getNewsLink() {
		return (java.lang.String)HiltonUtility.ckNull(this.newsLink).trim();
    }

    public void setNewsLink(java.lang.String newsLink) {
		if (!HiltonUtility.isEmpty(newsLink) && newsLink.length() > 255) {
			newsLink = newsLink.substring(0, 255);
		}
		this.newsLink = newsLink;
    }

    public java.lang.String getNewsImage() {
		return (java.lang.String)HiltonUtility.ckNull(this.newsImage).trim();
    }

    public void setNewsImage(java.lang.String newsImage) {
		if (!HiltonUtility.isEmpty(newsImage) && newsImage.length() > 40) {
			newsImage = newsImage.substring(0, 40);
		}
		this.newsImage = newsImage;
    }

    public java.lang.String getNewsAltTag() {
		return (java.lang.String)HiltonUtility.ckNull(this.newsAltTag).trim();
    }

    public void setNewsAltTag(java.lang.String newsAltTag) {
		if (!HiltonUtility.isEmpty(newsAltTag) && newsAltTag.length() > 255) {
			newsAltTag = newsAltTag.substring(0, 255);
		}
		this.newsAltTag = newsAltTag;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("icNews", getIcNews())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof News) ) return false;
        News castOther = (News) other;
        return new EqualsBuilder()
            .append(this.getIcNews(), castOther.getIcNews())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIcNews())
            .toHashCode();
    }

}
