/**
 * IntrayItem.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.inputext;


/**
 * Contains a document on the Intray
 *                 that has been retrieved.
 */
public class IntrayItem  implements java.io.Serializable {
    private java.lang.String cmpCode;

    /* The document master
     *                     code. */
    private java.lang.String code;

    /* The document
     *                     number. */
    private java.lang.String number;

    /* The
     *                         document date. */
    private java.util.Calendar date;

    /* The
     *                         year and period to which the document is to
     * be
     *                     posted. */
    private java.lang.String period;

    /* The code of the user who
     *                         created the document. */
    private java.lang.String originalUser;

    /* The code of the user who last
     *                         modified the document. */
    private java.lang.String lastModBy;

    /* The authorising
     *                     initials. */
    private java.lang.String authInitials;

    /* The
     *                         date when the document was last
     *                     modified. */
    private java.util.Calendar lastModDate;

    /* The
     *                         value of the first line on the
     *                     document. */
    private java.math.BigDecimal firstDetailValue;

    /* The account code of the first
     *                         line on the document. */
    private java.lang.String firstAccountCode;

    /* Indicates whether the
     *                         document is an InterCompany
     *                     document. */
    private boolean isInterCompany;

    /* This element will be set to
     *                         TRUE if the document has been matched to a
     * purchasing ordering document, and is therefore
     *                         not deletable. */
    private boolean isPODNotDeletable;

    /* The
     *                         current workflow authorisation status of the
     * document. */
    private java.lang.String authorisationStatus;

    /* Specifies whether current
     *                         exchange rates can be applied to this document
     * when posting. */
    private boolean canApplyCurrentRate;

    /* The
     *                         code of the input template master used when
     * inputting the document. */
    private java.lang.String template;

    /* Indicates that this document
     *                         requires workflow authorisation before posting
     * to the Books. */
    private boolean requiresBooksWorkflow;

    /* The
     *                         workflow process definition code for use when
     * posting to the Books. */
    private java.lang.String booksWorkflowCode;

    /* Indicates whether the
     *                         workflow process definition code is
     *                     protected. */
    private java.lang.Boolean booksWorkflowProtected;

    /* The
     *                         position code within the position
     *                     hierarchy. */
    private java.lang.String positionHierarchyCode;

    public IntrayItem() {
    }

    public IntrayItem(
           java.lang.String cmpCode,
           java.lang.String code,
           java.lang.String number,
           java.util.Calendar date,
           java.lang.String period,
           java.lang.String originalUser,
           java.lang.String lastModBy,
           java.lang.String authInitials,
           java.util.Calendar lastModDate,
           java.math.BigDecimal firstDetailValue,
           java.lang.String firstAccountCode,
           boolean isInterCompany,
           boolean isPODNotDeletable,
           java.lang.String authorisationStatus,
           boolean canApplyCurrentRate,
           java.lang.String template,
           boolean requiresBooksWorkflow,
           java.lang.String booksWorkflowCode,
           java.lang.Boolean booksWorkflowProtected,
           java.lang.String positionHierarchyCode) {
           this.cmpCode = cmpCode;
           this.code = code;
           this.number = number;
           this.date = date;
           this.period = period;
           this.originalUser = originalUser;
           this.lastModBy = lastModBy;
           this.authInitials = authInitials;
           this.lastModDate = lastModDate;
           this.firstDetailValue = firstDetailValue;
           this.firstAccountCode = firstAccountCode;
           this.isInterCompany = isInterCompany;
           this.isPODNotDeletable = isPODNotDeletable;
           this.authorisationStatus = authorisationStatus;
           this.canApplyCurrentRate = canApplyCurrentRate;
           this.template = template;
           this.requiresBooksWorkflow = requiresBooksWorkflow;
           this.booksWorkflowCode = booksWorkflowCode;
           this.booksWorkflowProtected = booksWorkflowProtected;
           this.positionHierarchyCode = positionHierarchyCode;
    }


    /**
     * Gets the cmpCode value for this IntrayItem.
     * 
     * @return cmpCode
     */
    public java.lang.String getCmpCode() {
        return cmpCode;
    }


    /**
     * Sets the cmpCode value for this IntrayItem.
     * 
     * @param cmpCode
     */
    public void setCmpCode(java.lang.String cmpCode) {
        this.cmpCode = cmpCode;
    }


    /**
     * Gets the code value for this IntrayItem.
     * 
     * @return code   * The document master
     *                     code.
     */
    public java.lang.String getCode() {
        return code;
    }


    /**
     * Sets the code value for this IntrayItem.
     * 
     * @param code   * The document master
     *                     code.
     */
    public void setCode(java.lang.String code) {
        this.code = code;
    }


    /**
     * Gets the number value for this IntrayItem.
     * 
     * @return number   * The document
     *                     number.
     */
    public java.lang.String getNumber() {
        return number;
    }


    /**
     * Sets the number value for this IntrayItem.
     * 
     * @param number   * The document
     *                     number.
     */
    public void setNumber(java.lang.String number) {
        this.number = number;
    }


    /**
     * Gets the date value for this IntrayItem.
     * 
     * @return date   * The
     *                         document date.
     */
    public java.util.Calendar getDate() {
        return date;
    }


    /**
     * Sets the date value for this IntrayItem.
     * 
     * @param date   * The
     *                         document date.
     */
    public void setDate(java.util.Calendar date) {
        this.date = date;
    }


    /**
     * Gets the period value for this IntrayItem.
     * 
     * @return period   * The
     *                         year and period to which the document is to
     * be
     *                     posted.
     */
    public java.lang.String getPeriod() {
        return period;
    }


    /**
     * Sets the period value for this IntrayItem.
     * 
     * @param period   * The
     *                         year and period to which the document is to
     * be
     *                     posted.
     */
    public void setPeriod(java.lang.String period) {
        this.period = period;
    }


    /**
     * Gets the originalUser value for this IntrayItem.
     * 
     * @return originalUser   * The code of the user who
     *                         created the document.
     */
    public java.lang.String getOriginalUser() {
        return originalUser;
    }


    /**
     * Sets the originalUser value for this IntrayItem.
     * 
     * @param originalUser   * The code of the user who
     *                         created the document.
     */
    public void setOriginalUser(java.lang.String originalUser) {
        this.originalUser = originalUser;
    }


    /**
     * Gets the lastModBy value for this IntrayItem.
     * 
     * @return lastModBy   * The code of the user who last
     *                         modified the document.
     */
    public java.lang.String getLastModBy() {
        return lastModBy;
    }


    /**
     * Sets the lastModBy value for this IntrayItem.
     * 
     * @param lastModBy   * The code of the user who last
     *                         modified the document.
     */
    public void setLastModBy(java.lang.String lastModBy) {
        this.lastModBy = lastModBy;
    }


    /**
     * Gets the authInitials value for this IntrayItem.
     * 
     * @return authInitials   * The authorising
     *                     initials.
     */
    public java.lang.String getAuthInitials() {
        return authInitials;
    }


    /**
     * Sets the authInitials value for this IntrayItem.
     * 
     * @param authInitials   * The authorising
     *                     initials.
     */
    public void setAuthInitials(java.lang.String authInitials) {
        this.authInitials = authInitials;
    }


    /**
     * Gets the lastModDate value for this IntrayItem.
     * 
     * @return lastModDate   * The
     *                         date when the document was last
     *                     modified.
     */
    public java.util.Calendar getLastModDate() {
        return lastModDate;
    }


    /**
     * Sets the lastModDate value for this IntrayItem.
     * 
     * @param lastModDate   * The
     *                         date when the document was last
     *                     modified.
     */
    public void setLastModDate(java.util.Calendar lastModDate) {
        this.lastModDate = lastModDate;
    }


    /**
     * Gets the firstDetailValue value for this IntrayItem.
     * 
     * @return firstDetailValue   * The
     *                         value of the first line on the
     *                     document.
     */
    public java.math.BigDecimal getFirstDetailValue() {
        return firstDetailValue;
    }


    /**
     * Sets the firstDetailValue value for this IntrayItem.
     * 
     * @param firstDetailValue   * The
     *                         value of the first line on the
     *                     document.
     */
    public void setFirstDetailValue(java.math.BigDecimal firstDetailValue) {
        this.firstDetailValue = firstDetailValue;
    }


    /**
     * Gets the firstAccountCode value for this IntrayItem.
     * 
     * @return firstAccountCode   * The account code of the first
     *                         line on the document.
     */
    public java.lang.String getFirstAccountCode() {
        return firstAccountCode;
    }


    /**
     * Sets the firstAccountCode value for this IntrayItem.
     * 
     * @param firstAccountCode   * The account code of the first
     *                         line on the document.
     */
    public void setFirstAccountCode(java.lang.String firstAccountCode) {
        this.firstAccountCode = firstAccountCode;
    }


    /**
     * Gets the isInterCompany value for this IntrayItem.
     * 
     * @return isInterCompany   * Indicates whether the
     *                         document is an InterCompany
     *                     document.
     */
    public boolean isIsInterCompany() {
        return isInterCompany;
    }


    /**
     * Sets the isInterCompany value for this IntrayItem.
     * 
     * @param isInterCompany   * Indicates whether the
     *                         document is an InterCompany
     *                     document.
     */
    public void setIsInterCompany(boolean isInterCompany) {
        this.isInterCompany = isInterCompany;
    }


    /**
     * Gets the isPODNotDeletable value for this IntrayItem.
     * 
     * @return isPODNotDeletable   * This element will be set to
     *                         TRUE if the document has been matched to a
     * purchasing ordering document, and is therefore
     *                         not deletable.
     */
    public boolean isIsPODNotDeletable() {
        return isPODNotDeletable;
    }


    /**
     * Sets the isPODNotDeletable value for this IntrayItem.
     * 
     * @param isPODNotDeletable   * This element will be set to
     *                         TRUE if the document has been matched to a
     * purchasing ordering document, and is therefore
     *                         not deletable.
     */
    public void setIsPODNotDeletable(boolean isPODNotDeletable) {
        this.isPODNotDeletable = isPODNotDeletable;
    }


    /**
     * Gets the authorisationStatus value for this IntrayItem.
     * 
     * @return authorisationStatus   * The
     *                         current workflow authorisation status of the
     * document.
     */
    public java.lang.String getAuthorisationStatus() {
        return authorisationStatus;
    }


    /**
     * Sets the authorisationStatus value for this IntrayItem.
     * 
     * @param authorisationStatus   * The
     *                         current workflow authorisation status of the
     * document.
     */
    public void setAuthorisationStatus(java.lang.String authorisationStatus) {
        this.authorisationStatus = authorisationStatus;
    }


    /**
     * Gets the canApplyCurrentRate value for this IntrayItem.
     * 
     * @return canApplyCurrentRate   * Specifies whether current
     *                         exchange rates can be applied to this document
     * when posting.
     */
    public boolean isCanApplyCurrentRate() {
        return canApplyCurrentRate;
    }


    /**
     * Sets the canApplyCurrentRate value for this IntrayItem.
     * 
     * @param canApplyCurrentRate   * Specifies whether current
     *                         exchange rates can be applied to this document
     * when posting.
     */
    public void setCanApplyCurrentRate(boolean canApplyCurrentRate) {
        this.canApplyCurrentRate = canApplyCurrentRate;
    }


    /**
     * Gets the template value for this IntrayItem.
     * 
     * @return template   * The
     *                         code of the input template master used when
     * inputting the document.
     */
    public java.lang.String getTemplate() {
        return template;
    }


    /**
     * Sets the template value for this IntrayItem.
     * 
     * @param template   * The
     *                         code of the input template master used when
     * inputting the document.
     */
    public void setTemplate(java.lang.String template) {
        this.template = template;
    }


    /**
     * Gets the requiresBooksWorkflow value for this IntrayItem.
     * 
     * @return requiresBooksWorkflow   * Indicates that this document
     *                         requires workflow authorisation before posting
     * to the Books.
     */
    public boolean isRequiresBooksWorkflow() {
        return requiresBooksWorkflow;
    }


    /**
     * Sets the requiresBooksWorkflow value for this IntrayItem.
     * 
     * @param requiresBooksWorkflow   * Indicates that this document
     *                         requires workflow authorisation before posting
     * to the Books.
     */
    public void setRequiresBooksWorkflow(boolean requiresBooksWorkflow) {
        this.requiresBooksWorkflow = requiresBooksWorkflow;
    }


    /**
     * Gets the booksWorkflowCode value for this IntrayItem.
     * 
     * @return booksWorkflowCode   * The
     *                         workflow process definition code for use when
     * posting to the Books.
     */
    public java.lang.String getBooksWorkflowCode() {
        return booksWorkflowCode;
    }


    /**
     * Sets the booksWorkflowCode value for this IntrayItem.
     * 
     * @param booksWorkflowCode   * The
     *                         workflow process definition code for use when
     * posting to the Books.
     */
    public void setBooksWorkflowCode(java.lang.String booksWorkflowCode) {
        this.booksWorkflowCode = booksWorkflowCode;
    }


    /**
     * Gets the booksWorkflowProtected value for this IntrayItem.
     * 
     * @return booksWorkflowProtected   * Indicates whether the
     *                         workflow process definition code is
     *                     protected.
     */
    public java.lang.Boolean getBooksWorkflowProtected() {
        return booksWorkflowProtected;
    }


    /**
     * Sets the booksWorkflowProtected value for this IntrayItem.
     * 
     * @param booksWorkflowProtected   * Indicates whether the
     *                         workflow process definition code is
     *                     protected.
     */
    public void setBooksWorkflowProtected(java.lang.Boolean booksWorkflowProtected) {
        this.booksWorkflowProtected = booksWorkflowProtected;
    }


    /**
     * Gets the positionHierarchyCode value for this IntrayItem.
     * 
     * @return positionHierarchyCode   * The
     *                         position code within the position
     *                     hierarchy.
     */
    public java.lang.String getPositionHierarchyCode() {
        return positionHierarchyCode;
    }


    /**
     * Sets the positionHierarchyCode value for this IntrayItem.
     * 
     * @param positionHierarchyCode   * The
     *                         position code within the position
     *                     hierarchy.
     */
    public void setPositionHierarchyCode(java.lang.String positionHierarchyCode) {
        this.positionHierarchyCode = positionHierarchyCode;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof IntrayItem)) return false;
        IntrayItem other = (IntrayItem) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.cmpCode==null && other.getCmpCode()==null) || 
             (this.cmpCode!=null &&
              this.cmpCode.equals(other.getCmpCode()))) &&
            ((this.code==null && other.getCode()==null) || 
             (this.code!=null &&
              this.code.equals(other.getCode()))) &&
            ((this.number==null && other.getNumber()==null) || 
             (this.number!=null &&
              this.number.equals(other.getNumber()))) &&
            ((this.date==null && other.getDate()==null) || 
             (this.date!=null &&
              this.date.equals(other.getDate()))) &&
            ((this.period==null && other.getPeriod()==null) || 
             (this.period!=null &&
              this.period.equals(other.getPeriod()))) &&
            ((this.originalUser==null && other.getOriginalUser()==null) || 
             (this.originalUser!=null &&
              this.originalUser.equals(other.getOriginalUser()))) &&
            ((this.lastModBy==null && other.getLastModBy()==null) || 
             (this.lastModBy!=null &&
              this.lastModBy.equals(other.getLastModBy()))) &&
            ((this.authInitials==null && other.getAuthInitials()==null) || 
             (this.authInitials!=null &&
              this.authInitials.equals(other.getAuthInitials()))) &&
            ((this.lastModDate==null && other.getLastModDate()==null) || 
             (this.lastModDate!=null &&
              this.lastModDate.equals(other.getLastModDate()))) &&
            ((this.firstDetailValue==null && other.getFirstDetailValue()==null) || 
             (this.firstDetailValue!=null &&
              this.firstDetailValue.equals(other.getFirstDetailValue()))) &&
            ((this.firstAccountCode==null && other.getFirstAccountCode()==null) || 
             (this.firstAccountCode!=null &&
              this.firstAccountCode.equals(other.getFirstAccountCode()))) &&
            this.isInterCompany == other.isIsInterCompany() &&
            this.isPODNotDeletable == other.isIsPODNotDeletable() &&
            ((this.authorisationStatus==null && other.getAuthorisationStatus()==null) || 
             (this.authorisationStatus!=null &&
              this.authorisationStatus.equals(other.getAuthorisationStatus()))) &&
            this.canApplyCurrentRate == other.isCanApplyCurrentRate() &&
            ((this.template==null && other.getTemplate()==null) || 
             (this.template!=null &&
              this.template.equals(other.getTemplate()))) &&
            this.requiresBooksWorkflow == other.isRequiresBooksWorkflow() &&
            ((this.booksWorkflowCode==null && other.getBooksWorkflowCode()==null) || 
             (this.booksWorkflowCode!=null &&
              this.booksWorkflowCode.equals(other.getBooksWorkflowCode()))) &&
            ((this.booksWorkflowProtected==null && other.getBooksWorkflowProtected()==null) || 
             (this.booksWorkflowProtected!=null &&
              this.booksWorkflowProtected.equals(other.getBooksWorkflowProtected()))) &&
            ((this.positionHierarchyCode==null && other.getPositionHierarchyCode()==null) || 
             (this.positionHierarchyCode!=null &&
              this.positionHierarchyCode.equals(other.getPositionHierarchyCode())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getCmpCode() != null) {
            _hashCode += getCmpCode().hashCode();
        }
        if (getCode() != null) {
            _hashCode += getCode().hashCode();
        }
        if (getNumber() != null) {
            _hashCode += getNumber().hashCode();
        }
        if (getDate() != null) {
            _hashCode += getDate().hashCode();
        }
        if (getPeriod() != null) {
            _hashCode += getPeriod().hashCode();
        }
        if (getOriginalUser() != null) {
            _hashCode += getOriginalUser().hashCode();
        }
        if (getLastModBy() != null) {
            _hashCode += getLastModBy().hashCode();
        }
        if (getAuthInitials() != null) {
            _hashCode += getAuthInitials().hashCode();
        }
        if (getLastModDate() != null) {
            _hashCode += getLastModDate().hashCode();
        }
        if (getFirstDetailValue() != null) {
            _hashCode += getFirstDetailValue().hashCode();
        }
        if (getFirstAccountCode() != null) {
            _hashCode += getFirstAccountCode().hashCode();
        }
        _hashCode += (isIsInterCompany() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isIsPODNotDeletable() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getAuthorisationStatus() != null) {
            _hashCode += getAuthorisationStatus().hashCode();
        }
        _hashCode += (isCanApplyCurrentRate() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getTemplate() != null) {
            _hashCode += getTemplate().hashCode();
        }
        _hashCode += (isRequiresBooksWorkflow() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getBooksWorkflowCode() != null) {
            _hashCode += getBooksWorkflowCode().hashCode();
        }
        if (getBooksWorkflowProtected() != null) {
            _hashCode += getBooksWorkflowProtected().hashCode();
        }
        if (getPositionHierarchyCode() != null) {
            _hashCode += getPositionHierarchyCode().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(IntrayItem.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "IntrayItem"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cmpCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "CmpCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("code");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "Code"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("number");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "Number"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("date");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "Date"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("period");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "Period"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseYearPeriod"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("originalUser");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "OriginalUser"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastModBy");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "LastModBy"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("authInitials");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "AuthInitials"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastModDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "LastModDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("firstDetailValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "FirstDetailValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("firstAccountCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "FirstAccountCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isInterCompany");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "IsInterCompany"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isPODNotDeletable");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "IsPODNotDeletable"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("authorisationStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "AuthorisationStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("canApplyCurrentRate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "CanApplyCurrentRate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("template");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "Template"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requiresBooksWorkflow");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "RequiresBooksWorkflow"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("booksWorkflowCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "BooksWorkflowCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("booksWorkflowProtected");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "BooksWorkflowProtected"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("positionHierarchyCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "PositionHierarchyCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
