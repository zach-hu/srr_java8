package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class AppRulesExtPK implements Serializable {

    /** identifier field */
    private java.math.BigDecimal ruleNumber;

    /** identifier field */
    private String ruleType;

    /** full constructor */
    public AppRulesExtPK(java.math.BigDecimal ruleNumber, String ruleType) {
        this.ruleNumber = ruleNumber;
        this.ruleType = ruleType;
    }

    /** default constructor */
    public AppRulesExtPK() {
    }

    public java.math.BigDecimal getRuleNumber() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.ruleNumber);
    }

    public void setRuleNumber(java.math.BigDecimal ruleNumber) {
        this.ruleNumber = ruleNumber;
    }

    public String getRuleType() {
		return HiltonUtility.ckNull(this.ruleType);
    }

    public void setRuleType(String ruleType) {
        this.ruleType = ruleType;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("ruleNumber", getRuleNumber())
            .append("ruleType", getRuleType())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof AppRulesExtPK) ) return false;
        AppRulesExtPK castOther = (AppRulesExtPK) other;
        return new EqualsBuilder()
            .append(this.getRuleNumber(), castOther.getRuleNumber())
            .append(this.getRuleType(), castOther.getRuleType())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getRuleNumber())
            .append(getRuleType())
            .toHashCode();
    }

}
