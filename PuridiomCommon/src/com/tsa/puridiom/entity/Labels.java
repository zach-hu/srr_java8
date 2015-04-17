/**
 *
 */
package com.tsa.puridiom.entity;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.utility.Utility;

/**
 * @author kelli
 */

public class Labels implements Serializable {
	/** identifier field */
    private java.math.BigDecimal icLabel;
    private String labelCode;
    private String moduleAccess;
    private String module;
    private String moduleType;
    private String language;
    private String labelValue;
    private String abbreviation;
    private String hoverHelp;
    private String visible;
    private String required;
    private String validation;
    private String validationSeverity;
    private String validationMessage;
    private String browseName;
    private String udf1Code;
    private String udf2Code;
    private String udf3Code;
    private String udf4Code;
    private String udf5Code;
    private String status;
    private String owner;
    private String lastChangeBy;
    private java.util.Date lastChangeDate;
    private String allowEdit;
    private String validationLink;
    private String fieldname;
    private String readOnly;
    private String allowLink;
    private String fieldLength;
    private String fieldType;
    
	/** full constructor */
    public Labels(java.math.BigDecimal icLabel, String labelCode, String accessModule, String module, String moduleType, String language, String labelValue, String abbreviation, String hoverHelp, String visible, String validation, String required, String validationSeverity, String validationMessage,
    		String browseName, String udf1Code, String udf2Code, String udf3Code, String udf4Code, String udf5Code, String status, String owner, String lastChangeBy, java.util.Date lastChangeDate, String allowEdit, String validationLink, String fieldname, String readOnly, String allowLink){
    	this.icLabel = icLabel;
    	this.labelCode = labelCode;
    	this.moduleAccess = accessModule;
    	this.module = module;
    	this.moduleType = moduleType;
    	this.language = language;
    	this.labelValue = labelValue;
    	this.abbreviation = abbreviation;
    	this.hoverHelp = hoverHelp;
    	this.visible = visible;
    	this.required =  required;
    	this.validation = validation;
    	this.validationSeverity = validationSeverity;
    	this.validationMessage = validationMessage;
    	this.browseName = browseName;
    	this.udf1Code = udf1Code;
    	this.udf2Code = udf2Code;
    	this.udf3Code = udf3Code;
    	this.udf4Code = udf4Code;
    	this.udf5Code = udf5Code;
    	this.status = status;
    	this.owner = owner;
    	this.lastChangeBy = lastChangeBy;
    	this.lastChangeDate = lastChangeDate;
    	this.allowEdit = allowEdit;
    	this.validationLink = validationLink;
    	this.fieldname = fieldname;
    	this.readOnly = readOnly;
    	this.allowLink = allowLink;
 }

    /** default constructor */
    public Labels() {
    }

    /** minimal constructor */
    public Labels(java.math.BigDecimal icLabel) {
        this.icLabel = icLabel;
    }

	public java.math.BigDecimal getIcLabel() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icLabel);
	}

	public void setIcLabel(java.math.BigDecimal icLabel) {
		this.icLabel = icLabel;
	}

	public String getLabelCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.labelCode).trim();
	}

	public void setLabelCode(String labelCode) {
        if (!HiltonUtility.isEmpty(labelCode) && labelCode.length() > 60) {
        	labelCode = labelCode.substring(0, 60);
        }
		this.labelCode = labelCode;
	}

	public String getModuleAccess() {
		return (java.lang.String)HiltonUtility.ckNull(this.moduleAccess).trim();
	}

	public void setModuleAccess(String moduleAccess) {
        if (!HiltonUtility.isEmpty(moduleAccess) && moduleAccess.length() > 30) {
        	moduleAccess = moduleAccess.substring(0, 30);
        }
		this.moduleAccess = moduleAccess;
	}

	public String getModule() {
		return (java.lang.String)HiltonUtility.ckNull(this.module).trim();
	}

	public void setModule(String module) {
        if (!HiltonUtility.isEmpty(module) && module.length() > 15) {
        	module = module.substring(0, 15);
        }
		this.module = module;
	}

	public String getModuleType() {
		return (java.lang.String)HiltonUtility.ckNull(this.moduleType).trim().toLowerCase();
	}

	public void setModuleType(String moduleType) {
        if (!HiltonUtility.isEmpty(moduleType) && moduleType.length() > 5) {
        	moduleType = moduleType.substring(0, 5);
        }
		this.moduleType = moduleType;
	}

	public String getLanguage() {
		return (java.lang.String)HiltonUtility.ckNull(this.language).trim();
	}

	public void setLanguage(String language) {
        if (!HiltonUtility.isEmpty(language) && language.length() > 15) {
            language = language.substring(0, 15);
        }
        this.language = language;
	}

	public String getLabelValue() {
		return (java.lang.String)HiltonUtility.ckNull(this.labelValue).trim();
	}

	public void setLabelValue(String labelValue) {
        if (!HiltonUtility.isEmpty(labelValue) && labelValue.length() > 2000) {
        	labelValue = labelValue.substring(0, 2000);
        }
		this.labelValue = labelValue;
	}

	public String getAbbreviation() {
		return (java.lang.String)HiltonUtility.ckNull(this.abbreviation).trim();
	}

	public void setAbbreviation(String abbreviation) {
        if (!HiltonUtility.isEmpty(abbreviation) && abbreviation.length() > 60) {
        	abbreviation = abbreviation.substring(0, 60);
        }
		this.abbreviation = abbreviation;
	}

	public String getAllowEdit() {
		return (java.lang.String)HiltonUtility.ckNull(this.allowEdit).trim();
	}

	public void setAllowEdit(String allowEdit) {
        if (!HiltonUtility.isEmpty(allowEdit) && allowEdit.length() > 1) {
        	allowEdit = allowEdit.substring(0, 1);
        }
		this.allowEdit = allowEdit;
	}

	public String getHoverHelp() {
		return (java.lang.String)HiltonUtility.ckNull(this.hoverHelp).trim();
	}

	public void setHoverHelp(String hoverHelp) {
        if (!HiltonUtility.isEmpty(hoverHelp) && hoverHelp.length() > 255) {
        	hoverHelp = hoverHelp.substring(0, 255);
        }
		this.hoverHelp = hoverHelp;
	}

	public String getBrowseName() {
		return (java.lang.String)HiltonUtility.ckNull(this.browseName).trim();
	}

	public void setBrowseName(String browseName) {
        if (!HiltonUtility.isEmpty(browseName) && browseName.length() > 60) {
        	browseName = browseName.substring(0, 60);
        }
		this.browseName = browseName;
	}

	public String getUdf1Code() {
		return (java.lang.String)HiltonUtility.ckNull(this.udf1Code).trim();
	}

	public void setUdf1Code(String udf1Code) {
        if (!HiltonUtility.isEmpty(udf1Code) && udf1Code.length() > 60) {
        	udf1Code = udf1Code.substring(0, 60);
        }
		this.udf1Code = udf1Code;
	}

	public String getUdf2Code() {
		return (java.lang.String)HiltonUtility.ckNull(this.udf2Code).trim();
	}

	public void setUdf2Code(String udf2Code) {
        if (!HiltonUtility.isEmpty(udf2Code) && udf2Code.length() > 60) {
        	udf2Code = udf2Code.substring(0, 60);
        }
		this.udf2Code = udf2Code;
	}

	public String getUdf3Code() {
		return (java.lang.String)HiltonUtility.ckNull(this.udf3Code).trim();
	}

	public void setUdf3Code(String udf3Code) {
        if (!HiltonUtility.isEmpty(udf3Code) && udf3Code.length() > 60) {
        	udf3Code = udf3Code.substring(0, 60);
        }
		this.udf3Code = udf3Code;
	}

	public String getUdf4Code() {
		return (java.lang.String)HiltonUtility.ckNull(this.udf4Code).trim();
	}

	public void setUdf4Code(String udf4Code) {
        if (!HiltonUtility.isEmpty(udf4Code) && udf4Code.length() > 60) {
        	udf4Code = udf4Code.substring(0, 60);
        }
		this.udf4Code = udf4Code;
	}

	public String getUdf5Code() {
		return (java.lang.String)HiltonUtility.ckNull(this.udf5Code).trim();
	}

	public void setUdf5Code(String udf5Code) {
        if (!HiltonUtility.isEmpty(udf5Code) && udf5Code.length() > 60) {
        	udf5Code = udf5Code.substring(0, 60);
        }
		this.udf5Code = udf5Code;
	}

	public String getVisible() {
		return (java.lang.String)HiltonUtility.ckNull(this.visible).trim();
	}

	public void setVisible(String visible) {
        if (!HiltonUtility.isEmpty(visible) && visible.length() > 1) {
        	visible = visible.substring(0, 1);
        }
		this.visible = visible;
	}

	public String getRequired() {
		return (java.lang.String)HiltonUtility.ckNull(this.required).trim();
	}

	public void setRequired(String required) {
        if (!HiltonUtility.isEmpty(required) && required.length() > 1) {
        	required = required.substring(0, 1);
        }
		this.required = required;
	}

	public String getValidation() {
		return (java.lang.String)HiltonUtility.ckNull(this.validation).trim();
	}

	public void setValidation(String validation) {
        if (!HiltonUtility.isEmpty(validation) && validation.length() > 1) {
        	validation = validation.substring(0, 1);
        }
		this.validation = validation;
	}

	public String getValidationSeverity() {
		return (java.lang.String)HiltonUtility.ckNull(this.validationSeverity).trim();
	}

	public void setValidationSeverity(String validationSeverity) {
        if (!HiltonUtility.isEmpty(validationSeverity) && validationSeverity.length() > 1) {
        	validationSeverity = validationSeverity.substring(0, 1);
        }
		this.validationSeverity = validationSeverity;
	}

	public String getValidationMessage() {
		return (java.lang.String)HiltonUtility.ckNull(this.validationMessage).trim();
	}

	public void setValidationMessage(String validationMessage) {
        if (!HiltonUtility.isEmpty(validationMessage) && validationMessage.length() > 255) {
        	validationMessage = validationMessage.substring(0, 255);
        }
		this.validationMessage = validationMessage;
	}

	public String getStatus() {
		return (java.lang.String)HiltonUtility.ckNull(this.status).trim();
	}

	public void setStatus(String status) {
        if (!HiltonUtility.isEmpty(status) && status.length() > 4) {
        	status = status.substring(0, 4);
        }
		this.status = status;
	}

	public String getOwner() {
		return (java.lang.String)HiltonUtility.ckNull(this.owner).trim();
	}

	public void setOwner(String owner) {
        if (!HiltonUtility.isEmpty(owner) && owner.length() > 15) {
        	owner = owner.substring(0, 15);
        }
		this.owner = owner;
	}

	public String getLastChangeBy() {
		return (java.lang.String)HiltonUtility.ckNull(this.lastChangeBy).trim();
	}

	public void setLastChangeBy(String lastChangeBy) {
		if (!HiltonUtility.isEmpty(lastChangeBy) && lastChangeBy.length() > 15) {
			lastChangeBy = lastChangeBy.substring(0, 15);
		}
		this.lastChangeBy = lastChangeBy;
	}

	public java.util.Date getLastChangeDate() {
		return lastChangeDate;
	}

	public void setLastChangeDate(java.util.Date lastChangeDate) {
		this.lastChangeDate = lastChangeDate;
	}

	public String getValidationLink() {
		return (java.lang.String)Utility.ckNull(this.validationLink).trim();
	}

	public void setValidationLink(String validationLink) {
		if (!HiltonUtility.isEmpty(validationLink) && validationLink.length() > 255) {
			validationLink = validationLink.substring(0, 255);
		}
		this.validationLink = validationLink;
	}

	public String getFieldname() {
		return (java.lang.String)HiltonUtility.ckNull(this.fieldname).trim();
	}

	public void setFieldname(String fieldname) {
		if (!HiltonUtility.isEmpty(fieldname) && fieldname.length() > 60) {
			fieldname = fieldname.substring(0, 60);
		}
		this.fieldname = fieldname;
	}

	public String getReadOnly() {
		return (java.lang.String)HiltonUtility.ckNull(this.readOnly).trim();
	}

	public void setReadOnly(String readOnly) {
        if (!HiltonUtility.isEmpty(readOnly) && readOnly.length() > 1) {
        	readOnly = readOnly.substring(0, 1);
        }
		this.readOnly = readOnly;
	}

	public String getAllowLink() {
		return (java.lang.String)HiltonUtility.ckNull(this.allowLink).trim();
	}

	public void setAllowLink(String allowLink) {
        if (!HiltonUtility.isEmpty(allowLink) && allowLink.length() > 1) {
        	allowLink = allowLink.substring(0, 1);
        }
		this.allowLink = allowLink;
	}

    public String getFieldLength() {
		return fieldLength;
	}

	public void setFieldLength(String fieldLength) {
		this.fieldLength = fieldLength;
	}

	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	public String toString() {
        return new ToStringBuilder(this)
            .append("icLabel", this.getIcLabel())
            .append(";code", this.getLabelCode())
            .append(";value", this.getLabelValue())
            .append(";moduleAccess", this.getModuleAccess())
            .append(";module", this.getModule())
            .append(";moduleType", this.getModuleType())
            .append(";language", this.getLanguage())
            .toString();
    }
	public boolean equals(Object other) {
        if ( !(other instanceof Labels) ) return false;
        Labels castOther = (Labels) other;
        return new EqualsBuilder()
            .append(this.getIcLabel(), castOther.getIcLabel())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIcLabel())
            .toHashCode();
    }
}
