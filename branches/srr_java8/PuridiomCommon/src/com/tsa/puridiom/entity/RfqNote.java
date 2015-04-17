package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class RfqNote implements Serializable {

    /** identifier field */
    private com.tsa.puridiom.entity.RfqNotePK comp_id;

    /** nullable persistent field */
    private String notesText;

    /** full constructor */
    public RfqNote(com.tsa.puridiom.entity.RfqNotePK comp_id, java.lang.String notesText) {
        this.comp_id = comp_id;
        this.notesText = notesText;
    }

    /** default constructor */
    public RfqNote() {
    }

    /** minimal constructor */
    public RfqNote(com.tsa.puridiom.entity.RfqNotePK comp_id) {
        this.comp_id = comp_id;
    }

    public com.tsa.puridiom.entity.RfqNotePK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(com.tsa.puridiom.entity.RfqNotePK comp_id) {
        this.comp_id = comp_id;
    }

    public java.lang.String getNotesText() {
		return (java.lang.String)HiltonUtility.ckNull(this.notesText).trim();
    }

    public void setNotesText(java.lang.String notesText) {
		if (!HiltonUtility.isEmpty(notesText) && notesText.length() > 4000) {
			notesText = notesText.substring(0, 4000);
		}
		this.notesText = notesText;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof RfqNote) ) return false;
        RfqNote castOther = (RfqNote) other;
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
