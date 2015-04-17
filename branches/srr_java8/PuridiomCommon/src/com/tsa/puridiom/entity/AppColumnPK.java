package com.tsa.puridiom.entity;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class AppColumnPK implements Serializable {

    /** identifier field */
    private String formtype;

    /** identifier field */
    private String colsection;

    /** identifier field */
    private String colname;

    /** full constructor */
    public AppColumnPK(java.lang.String formtype, java.lang.String colsection, java.lang.String colname) {
        this.formtype = formtype;
        this.colsection = colsection;
        this.colname = colname;
    }

    /** default constructor */
    public AppColumnPK() {
    }

    public java.lang.String getFormtype() {
        return this.formtype;
    }

    public void setFormtype(java.lang.String formtype) {
        this.formtype = formtype;
    }

    public java.lang.String getColsection() {
        return this.colsection;
    }

    public void setColsection(java.lang.String colsection) {
        this.colsection = colsection;
    }

    public java.lang.String getColname() {
        return this.colname;
    }

    public void setColname(java.lang.String colname) {
        this.colname = colname;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("formtype", getFormtype())
            .append("colsection", getColsection())
            .append("colname", getColname())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof AppColumnPK) ) return false;
        AppColumnPK castOther = (AppColumnPK) other;
        return new EqualsBuilder()
            .append(this.getFormtype(), castOther.getFormtype())
            .append(this.getColsection(), castOther.getColsection())
            .append(this.getColname(), castOther.getColname())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getFormtype())
            .append(getColsection())
            .append(getColname())
            .toHashCode();
    }

}
