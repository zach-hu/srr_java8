package com.tsa.puridiom.entity;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class UserStatistic implements Serializable {

    /** identifier field */
    private com.tsa.puridiom.entity.UserStatisticPK comp_id;

    /** nullable persistent field */
    private java.math.BigDecimal statCount;

    /** nullable persistent field */
    private java.math.BigDecimal statTotal;

    /** full constructor */
    public UserStatistic(com.tsa.puridiom.entity.UserStatisticPK comp_id, java.math.BigDecimal statCount, java.math.BigDecimal statTotal) {
        this.comp_id = comp_id;
        this.statCount = statCount;
        this.statTotal = statTotal;
    }

    /** default constructor */
    public UserStatistic() {
    }

    /** minimal constructor */
    public UserStatistic(com.tsa.puridiom.entity.UserStatisticPK comp_id) {
        this.comp_id = comp_id;
    }

    public com.tsa.puridiom.entity.UserStatisticPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(com.tsa.puridiom.entity.UserStatisticPK comp_id) {
        this.comp_id = comp_id;
    }

    public java.math.BigDecimal getStatCount() {
        return this.statCount;
    }

    public void setStatCount(java.math.BigDecimal statCount) {
        this.statCount = statCount;
    }

    public java.math.BigDecimal getStatTotal() {
        return this.statTotal;
    }

    public void setStatTotal(java.math.BigDecimal statTotal) {
        this.statTotal = statTotal;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof UserStatistic) ) return false;
        UserStatistic castOther = (UserStatistic) other;
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
