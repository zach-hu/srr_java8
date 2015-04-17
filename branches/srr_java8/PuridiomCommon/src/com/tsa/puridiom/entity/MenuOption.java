package com.tsa.puridiom.entity;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class MenuOption implements Serializable {

    /** identifier field */
    private com.tsa.puridiom.entity.MenuOptionPK comp_id;

    /** nullable persistent field */
    private String title;

    /** nullable persistent field */
    private String type;

    /** nullable persistent field */
    private java.math.BigDecimal userAccess;

    /** nullable persistent field */
    private String xml;

    /** nullable persistent field */
    private java.math.BigDecimal graphHeight;

    /** nullable persistent field */
    private java.math.BigDecimal graphWidth;

    /** full constructor */
    public MenuOption(com.tsa.puridiom.entity.MenuOptionPK comp_id, java.lang.String title, java.lang.String type, java.math.BigDecimal userAccess, java.lang.String xml, java.math.BigDecimal graphHeight, java.math.BigDecimal graphWidth) {
        this.comp_id = comp_id;
        this.title = title;
        this.type = type;
        this.userAccess = userAccess;
        this.xml = xml;
        this.graphHeight = graphHeight;
        this.graphWidth = graphWidth;
    }

    /** default constructor */
    public MenuOption() {
    }

    /** minimal constructor */
    public MenuOption(com.tsa.puridiom.entity.MenuOptionPK comp_id) {
        this.comp_id = comp_id;
    }

    public com.tsa.puridiom.entity.MenuOptionPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(com.tsa.puridiom.entity.MenuOptionPK comp_id) {
        this.comp_id = comp_id;
    }

    public java.lang.String getTitle() {
        return this.title;
    }

    public void setTitle(java.lang.String title) {
        this.title = title;
    }

    public java.lang.String getType() {
        return this.type;
    }

    public void setType(java.lang.String type) {
        this.type = type;
    }

    public java.math.BigDecimal getUserAccess() {
        return this.userAccess;
    }

    public void setUserAccess(java.math.BigDecimal userAccess) {
        this.userAccess = userAccess;
    }

    public java.lang.String getXml() {
        return this.xml;
    }

    public void setXml(java.lang.String xml) {
        this.xml = xml;
    }

    public java.math.BigDecimal getGraphHeight() {
        return this.graphHeight;
    }

    public void setGraphHeight(java.math.BigDecimal graphHeight) {
        this.graphHeight = graphHeight;
    }

    public java.math.BigDecimal getGraphWidth() {
        return this.graphWidth;
    }

    public void setGraphWidth(java.math.BigDecimal graphWidth) {
        this.graphWidth = graphWidth;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof MenuOption) ) return false;
        MenuOption castOther = (MenuOption) other;
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
