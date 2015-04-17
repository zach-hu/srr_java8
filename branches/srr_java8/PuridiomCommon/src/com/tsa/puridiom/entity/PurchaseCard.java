package com.tsa.puridiom.entity;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class PurchaseCard implements Serializable {

    /** identifier field */
    private String pcardCode;

    /** nullable persistent field */
    private String pcardNumber;

    /** nullable persistent field */
    private String pcardSecureCode;

    /** nullable persistent field */
    private String pcardName;

    /** nullable persistent field */
    private String pcardExpires;

    /** nullable persistent field */
    private String pcardHolder;

    /** nullable persistent field */
    private java.util.Date dateEntered;

    /** nullable persistent field */
    private java.util.Date dateExpires;

    /** nullable persistent field */
    private String status;

    /** nullable persistent field */
    private String owner;

    /** full constructor */
    public PurchaseCard(java.lang.String pcardCode, java.lang.String pcardNumber, java.lang.String pcardSecureCode, java.lang.String pcardName, java.lang.String pcardExpires, java.lang.String pcardpcardHolder, java.util.Date dateEntered, java.util.Date dateExpires, java.lang.String status, java.lang.String owner) {
        this.pcardCode = pcardCode;
        this.pcardNumber = pcardNumber;
        this.pcardSecureCode = pcardSecureCode;
        this.pcardName = pcardName;
        this.pcardExpires = pcardExpires;
        this.pcardHolder = pcardHolder;
        this.dateEntered = dateEntered;
        this.dateExpires = dateExpires;
        this.status = status;
        this.owner = owner;
    }

    /** default constructor */
    public PurchaseCard() {
    }

    /** minimal constructor */
    public PurchaseCard(java.lang.String pcardCode) {
        this.pcardCode = pcardCode;
    }

    public java.lang.String getPcardCode() {
        return this.pcardCode;
    }

    public void setPcardCode(java.lang.String pcardCode) {
        this.pcardCode = pcardCode;
    }

    public java.lang.String getPcardNumber() {
        return this.pcardNumber;
    }

    public void setPcardNumber(java.lang.String pcardNumber) {
        this.pcardNumber = pcardNumber;
    }

    public java.lang.String getPcardSecureCode() {
        return this.pcardSecureCode;
    }

    public void setPcardSecureCode(java.lang.String pcardSecureCode) {
        this.pcardSecureCode = pcardSecureCode;
    }

    public java.lang.String getPcardName() {
        return this.pcardName;
    }

    public void setPcardName(java.lang.String pcardName) {
        this.pcardName = pcardName;
    }

    public java.lang.String getPcardExpires() {
        return this.pcardExpires;
    }

    public void setPcardExpires(java.lang.String pcardExpires) {
        this.pcardExpires = pcardExpires;
    }

    public java.lang.String getPcardHolder() {
        return this.pcardHolder;
    }

    public void setPcardHolder(java.lang.String pcardHolder) {
        this.pcardHolder = pcardHolder;
    }

    public java.util.Date getDateEntered() {
        return this.dateEntered;
    }

    public void setDateEntered(java.util.Date dateEntered) {
        this.dateEntered = dateEntered;
    }

    public java.util.Date getDateExpires() {
        return this.dateExpires;
    }

    public void setDateExpires(java.util.Date dateExpires) {
        this.dateExpires = dateExpires;
    }

    public java.lang.String getStatus() {
        return this.status;
    }

    public void setStatus(java.lang.String status) {
        this.status = status;
    }

    public java.lang.String getOwner() {
        return this.owner;
    }

    public void setOwner(java.lang.String owner) {
        this.owner = owner;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("pcardCode", getPcardCode())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof PurchaseCard) ) return false;
        PurchaseCard castOther = (PurchaseCard) other;
        return new EqualsBuilder()
            .append(this.getPcardCode(), castOther.getPcardCode())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getPcardCode())
            .toHashCode();
    }

}
