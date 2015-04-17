package com.tsa.puridiom.entity.sungard;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class KeyId implements Serializable {

    /** identifier field */
    private com.tsa.puridiom.entity.sungard.KeyIdPK comp_id;
    
    /** persistent field */
    private java.math.BigDecimal keyRangeHigh;

    /** persistent field */
    private java.math.BigDecimal lastCheckedTo;

    /** full constructor */
    public KeyId(com.tsa.puridiom.entity.sungard.KeyIdPK comp_id, java.math.BigDecimal keyRangeHigh, java.math.BigDecimal lastCheckedTo) {
        this.comp_id = comp_id;
        this.keyRangeHigh = keyRangeHigh;
        this.lastCheckedTo = lastCheckedTo;
    }

    /** default constructor */
    public KeyId() {
    }
    
    /** minimal constructor */
    public KeyId(com.tsa.puridiom.entity.sungard.KeyIdPK comp_id) {
        this.comp_id = comp_id;
    }

    public com.tsa.puridiom.entity.sungard.KeyIdPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(com.tsa.puridiom.entity.sungard.KeyIdPK comp_id) {
        this.comp_id = comp_id;
    }

    public java.math.BigDecimal getKeyRangeHigh() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.keyRangeHigh);
    }

    public void setKeyRangeHigh(java.math.BigDecimal keyRangeHigh) {
        this.keyRangeHigh = keyRangeHigh;
    }

    public java.math.BigDecimal getLastCheckedTo() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.lastCheckedTo);
    }

    public void setLastCheckedTo(java.math.BigDecimal lastCheckedTo) {
        this.lastCheckedTo = lastCheckedTo;
    }

    public String toString() {
        return new ToStringBuilder(this)
        .append("comp_id", getComp_id())
        .toString();
    }

}
