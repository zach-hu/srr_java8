package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class InvLocation implements Serializable {

    /** identifier field */
    private com.tsa.puridiom.entity.InvLocationPK comp_id;

    /** nullable persistent field */
    private java.math.BigDecimal qtyOnHand;

    /** nullable persistent field */
    private java.math.BigDecimal qtyOnOrder;

    /** nullable persistent field */
    private java.math.BigDecimal minOnHand;

    /** nullable persistent field */
    private java.math.BigDecimal maxOnHand;

    /** nullable persistent field */
    private java.math.BigDecimal qtyEoq;

    /** nullable persistent field */
    private java.math.BigDecimal qtyEsq;

    /** nullable persistent field */
    private java.math.BigDecimal averageCost;

    /** nullable persistent field */
    private String udf1Code;

    /** nullable persistent field */
    private String udf2Code;

    /** nullable persistent field */
    private String udf3Code;

    /** nullable persistent field */
    private String udf4Code;

    /** nullable persistent field */
    private String udf5Code;

    /** nullable persistent field */
    private java.math.BigDecimal qtyAlloc;

    /** nullable persistent field */
    private java.math.BigDecimal icInvAccount;

    /** nullable persistent field */
    private java.math.BigDecimal icInvHeader;

    /** nullable persistent field */
    private java.math.BigDecimal qtyRequested;

    /** nullable persistent field */
    private String autoReplenish;

    /** nullable persistent field */
    private java.math.BigDecimal physActual;

    /** nullable persistent field */
    private java.math.BigDecimal physOriginal;

    /** nullable persistent field */
    private String primeLocation;

    /** nullable persistent field */
    private java.math.BigDecimal physAlloc;

    /** nullable persistent field */
    private java.math.BigDecimal originalAlloc;

    /** nullable persistent field */
    private java.math.BigDecimal qtyPendOrder;

    /** nullable persistent field */
    private java.math.BigDecimal duomQtyOnHand;

    /** nullable persistent field */
    private java.math.BigDecimal duomQtyAlloc;

    /** nullable persistent field */
    private java.math.BigDecimal duomPhysActual;

    /** nullable persistent field */
    private java.math.BigDecimal duomPhysOriginal;


    /** full constructor */
    public InvLocation(com.tsa.puridiom.entity.InvLocationPK comp_id, java.math.BigDecimal qtyOnHand, java.math.BigDecimal qtyOnOrder, java.math.BigDecimal minOnHand, java.math.BigDecimal maxOnHand, java.math.BigDecimal qtyEoq, java.math.BigDecimal qtyEsq, java.math.BigDecimal averageCost, java.lang.String udf1Code, java.lang.String udf2Code, java.lang.String udf3Code, java.lang.String udf4Code, java.lang.String udf5Code, java.math.BigDecimal qtyAlloc, java.math.BigDecimal icInvAccount, java.math.BigDecimal icInvHeader, java.math.BigDecimal qtyRequested, java.lang.String autoReplenish, java.math.BigDecimal physActual, java.math.BigDecimal physOriginal, java.lang.String primeLocation, java.math.BigDecimal physAlloc, java.math.BigDecimal originalAlloc, java.math.BigDecimal qtyPendOrder) {
        this.comp_id = comp_id;
        this.qtyOnHand = qtyOnHand;
        this.qtyOnOrder = qtyOnOrder;
        this.minOnHand = minOnHand;
        this.maxOnHand = maxOnHand;
        this.qtyEoq = qtyEoq;
        this.qtyEsq = qtyEsq;
        this.averageCost = averageCost;
        this.udf1Code = udf1Code;
        this.udf2Code = udf2Code;
        this.udf3Code = udf3Code;
        this.udf4Code = udf4Code;
        this.udf5Code = udf5Code;
        this.qtyAlloc = qtyAlloc;
        this.icInvAccount = icInvAccount;
        this.icInvHeader = icInvHeader;
        this.qtyRequested = qtyRequested;
        this.autoReplenish = autoReplenish;
        this.physActual = physActual;
        this.physOriginal = physOriginal;
        this.primeLocation = primeLocation;
        this.physAlloc = physAlloc;
        this.originalAlloc = originalAlloc;
        this.qtyPendOrder = qtyPendOrder;
    }

    public InvLocation(InvLocation oldLocation)
	{
        this.qtyOnHand = oldLocation.getQtyOnHand();
        this.qtyOnOrder = oldLocation.getQtyOnOrder();
        this.minOnHand = oldLocation.getMinOnHand();
        this.maxOnHand = oldLocation.getMaxOnHand();
        this.qtyEoq = oldLocation.getQtyEoq();
        this.qtyEsq = oldLocation.getQtyEsq();
        this.averageCost = oldLocation.getAverageCost();
        this.udf1Code = oldLocation.getUdf1Code();
        this.udf2Code = oldLocation.getUdf2Code();
        this.udf3Code = oldLocation.getUdf3Code();
        this.udf4Code = oldLocation.getUdf4Code();
        this.udf5Code = oldLocation.getUdf5Code();
        this.qtyAlloc = oldLocation.getQtyAlloc();
        this.icInvAccount = oldLocation.getIcInvAccount();
        this.icInvHeader = oldLocation.getIcInvHeader();
        this.qtyRequested = oldLocation.getQtyRequested();
        this.autoReplenish = oldLocation.getAutoReplenish();
        this.physActual = oldLocation.getPhysActual();
        this.physOriginal = oldLocation.getPhysOriginal();
        this.primeLocation = oldLocation.getPrimeLocation();
        this.physAlloc = oldLocation.getPhysAlloc();
        this.originalAlloc = oldLocation.getOriginalAlloc();
        this.qtyPendOrder = oldLocation.getQtyPendOrder();
    }

    /** default constructor */
    public InvLocation() {
    }

    /** minimal constructor */
    public InvLocation(com.tsa.puridiom.entity.InvLocationPK comp_id) {
        this.comp_id = comp_id;
    }

    public com.tsa.puridiom.entity.InvLocationPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(com.tsa.puridiom.entity.InvLocationPK comp_id) {
        this.comp_id = comp_id;
    }

    public java.math.BigDecimal getQtyOnHand() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.qtyOnHand);
    }

    public void setQtyOnHand(java.math.BigDecimal qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    public java.math.BigDecimal getQtyOnOrder() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.qtyOnOrder);
    }

    public void setQtyOnOrder(java.math.BigDecimal qtyOnOrder) {
        this.qtyOnOrder = qtyOnOrder;
    }

    public java.math.BigDecimal getMinOnHand() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.minOnHand);
    }

    public void setMinOnHand(java.math.BigDecimal minOnHand) {
        this.minOnHand = minOnHand;
    }

    public java.math.BigDecimal getMaxOnHand() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.maxOnHand);
    }

    public void setMaxOnHand(java.math.BigDecimal maxOnHand) {
        this.maxOnHand = maxOnHand;
    }

    public java.math.BigDecimal getQtyEoq() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.qtyEoq);
    }

    public void setQtyEoq(java.math.BigDecimal qtyEoq) {
        this.qtyEoq = qtyEoq;
    }

    public java.math.BigDecimal getQtyEsq() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.qtyEsq);
    }

    public void setQtyEsq(java.math.BigDecimal qtyEsq) {
        this.qtyEsq = qtyEsq;
    }

    public java.math.BigDecimal getAverageCost() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.averageCost);
    }

    public void setAverageCost(java.math.BigDecimal averageCost) {
        this.averageCost = averageCost;
    }

    public java.lang.String getUdf1Code() {
		return (java.lang.String)HiltonUtility.ckNull(this.udf1Code).trim();
    }

    public void setUdf1Code(java.lang.String udf1Code) {
		if (!HiltonUtility.isEmpty(udf1Code) && udf1Code.length() > 15) {
			udf1Code = udf1Code.substring(0, 15);
		}
		this.udf1Code = udf1Code;
    }

    public java.lang.String getUdf2Code() {
		return (java.lang.String)HiltonUtility.ckNull(this.udf2Code).trim();
    }

    public void setUdf2Code(java.lang.String udf2Code) {
		if (!HiltonUtility.isEmpty(udf2Code) && udf2Code.length() > 15) {
			udf2Code = udf2Code.substring(0, 15);
		}
		this.udf2Code = udf2Code;
    }

    public java.lang.String getUdf3Code() {
		return (java.lang.String)HiltonUtility.ckNull(this.udf3Code).trim();
    }

    public void setUdf3Code(java.lang.String udf3Code) {
		if (!HiltonUtility.isEmpty(udf3Code) && udf3Code.length() > 15) {
			udf3Code = udf3Code.substring(0, 15);
		}
		this.udf3Code = udf3Code;
    }

    public java.lang.String getUdf4Code() {
		return (java.lang.String)HiltonUtility.ckNull(this.udf4Code).trim();
    }

    public void setUdf4Code(java.lang.String udf4Code) {
		if (!HiltonUtility.isEmpty(udf4Code) && udf4Code.length() > 15) {
			udf4Code = udf4Code.substring(0, 15);
		}
		this.udf4Code = udf4Code;
    }

    public java.lang.String getUdf5Code() {
		return (java.lang.String)HiltonUtility.ckNull(this.udf5Code).trim();
    }

    public void setUdf5Code(java.lang.String udf5Code) {
		if (!HiltonUtility.isEmpty(udf5Code) && udf5Code.length() > 15) {
			udf5Code = udf5Code.substring(0, 15);
		}
		this.udf5Code = udf5Code;
    }

    public java.math.BigDecimal getQtyAlloc() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.qtyAlloc);
    }

    public void setQtyAlloc(java.math.BigDecimal qtyAlloc) {
        this.qtyAlloc = qtyAlloc;
    }

    public java.math.BigDecimal getIcInvAccount() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icInvAccount);
    }

    public void setIcInvAccount(java.math.BigDecimal icInvAccount) {
        this.icInvAccount = icInvAccount;
    }

    public java.math.BigDecimal getIcInvHeader() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icInvHeader);
    }

    public void setIcInvHeader(java.math.BigDecimal icInvHeader) {
        this.icInvHeader = icInvHeader;
    }

    public java.math.BigDecimal getQtyRequested() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.qtyRequested);
    }

    public void setQtyRequested(java.math.BigDecimal qtyRequested) {
        this.qtyRequested = qtyRequested;
    }

    public java.lang.String getAutoReplenish() {
		return (java.lang.String)HiltonUtility.ckNull(this.autoReplenish).trim();
    }

    public void setAutoReplenish(java.lang.String autoReplenish) {
		if (!HiltonUtility.isEmpty(autoReplenish) && autoReplenish.length() > 1) {
			autoReplenish = autoReplenish.substring(0, 1);
		}
		this.autoReplenish = autoReplenish;
    }

    public java.math.BigDecimal getPhysActual() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.physActual);
    }

    public void setPhysActual(java.math.BigDecimal physActual) {
        this.physActual = physActual;
    }

    public java.math.BigDecimal getPhysOriginal() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.physOriginal);
    }

    public void setPhysOriginal(java.math.BigDecimal physOriginal) {
        this.physOriginal = physOriginal;
    }

    public java.lang.String getPrimeLocation() {
		return (java.lang.String)HiltonUtility.ckNull(this.primeLocation).trim();
    }

    public void setPrimeLocation(java.lang.String primeLocation) {
		if (!HiltonUtility.isEmpty(primeLocation) && primeLocation.length() > 1) {
			primeLocation = primeLocation.substring(0, 1);
		}
		this.primeLocation = primeLocation;
    }

    public java.math.BigDecimal getPhysAlloc() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.physAlloc);
    }

    public void setPhysAlloc(java.math.BigDecimal physAlloc) {
        this.physAlloc = physAlloc;
    }

    public java.math.BigDecimal getOriginalAlloc() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.originalAlloc);
    }

    public void setOriginalAlloc(java.math.BigDecimal originalAlloc) {
        this.originalAlloc = originalAlloc;
    }

    public java.math.BigDecimal getQtyPendOrder() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.qtyPendOrder);
    }

    public void setQtyPendOrder(java.math.BigDecimal qtyPendOrder) {
        this.qtyPendOrder = qtyPendOrder;
    }

    public java.math.BigDecimal getDuomQtyOnHand() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.duomQtyOnHand);
    }

    public void setDuomQtyOnHand(java.math.BigDecimal duomQtyOnHand) {
        this.duomQtyOnHand = duomQtyOnHand;
    }

    public java.math.BigDecimal getDuomQtyAlloc() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.duomQtyAlloc);
    }

    public void setDuomQtyAlloc(java.math.BigDecimal duomQtyAlloc) {
        this.duomQtyAlloc = duomQtyAlloc;
    }

    public java.math.BigDecimal getDuomPhysActual() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.duomPhysActual);
    }

    public void setDuomPhysActual(java.math.BigDecimal duomPhysActual) {
        this.duomPhysActual = duomPhysActual;
    }

    public java.math.BigDecimal getDuomPhysOriginal() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.duomPhysOriginal);
    }

    public void setDuomPhysOriginal(java.math.BigDecimal duomPhysOriginal) {
        this.duomPhysOriginal = duomPhysOriginal;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof InvLocation) ) return false;
        InvLocation castOther = (InvLocation) other;
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
