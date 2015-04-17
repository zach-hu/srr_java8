/**
 * IntrayFilter.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.inputext;


/**
 * Contains criteria
 *                 for selecting documents on the
 *             Intray.
 */
public class IntrayFilter  implements java.io.Serializable {
    private java.lang.String cmpCode;

    /* The
     *                         maximum number of documents to
     *                     return. */
    private java.lang.Integer limit;

    /* The document master
     *                     code. */
    private java.lang.String code;

    /* The
     *                         document number of the single document you
     * want
     *                         to select, or the first document number in
     * a
     *                         range if you want to select multiple
     *                     documents. */
    private java.lang.String numberFrom;

    /* The last document number in
     *                         the range if you want to select multiple
     *                         documents. This can be blank if you are
     *                         selecting a single document. */
    private java.lang.String numberTo;

    /* The document input date of
     *                         the single document you want to select, or
     * the
     *                         start document input date in a range if you
     * want
     *                         to select multiple
     *                     documents. */
    private java.util.Calendar dateFrom;

    /* The end document input date
     *                         in the range if you want to select multiple
     * documents. This can be blank if you are
     *                         selecting a single document. */
    private java.util.Calendar dateTo;

    /* The year/period of the single
     *                         document you want to select, or the start
     *                         year/period in a range if you want to select
     * multiple documents. */
    private java.lang.String periodFrom;

    /* The end year/period in the
     *                         range if you want to select multiple documents.
     * This can be blank if you are selecting a single
     *                     document. */
    private java.lang.String periodTo;

    /* The
     *                         user who created the
     *                     document. */
    private java.lang.String originalUser;

    /* The
     *                         user who last modified the document and posted
     * it back to the Intray. */
    private java.lang.String lastModBy;

    /* The initials of the
     *                         authorising user. */
    private java.lang.String authInitials;

    /* The external reference (1-6)
     *                         that you want to search for the string specified
     * in ExtRef. */
    private java.lang.Short extRefPos;

    /* The document-wide external
     *                         reference of the document you want to
     *                     select. */
    private java.lang.String extRef;

    /* The document account
     *                     code. */
    private java.lang.String accountCode;

    /* The
     *                         current workflow authorisation status of the
     * document. */
    private java.lang.String authorisationStatus;

    public IntrayFilter() {
    }

    public IntrayFilter(
           java.lang.String cmpCode,
           java.lang.Integer limit,
           java.lang.String code,
           java.lang.String numberFrom,
           java.lang.String numberTo,
           java.util.Calendar dateFrom,
           java.util.Calendar dateTo,
           java.lang.String periodFrom,
           java.lang.String periodTo,
           java.lang.String originalUser,
           java.lang.String lastModBy,
           java.lang.String authInitials,
           java.lang.Short extRefPos,
           java.lang.String extRef,
           java.lang.String accountCode,
           java.lang.String authorisationStatus) {
           this.cmpCode = cmpCode;
           this.limit = limit;
           this.code = code;
           this.numberFrom = numberFrom;
           this.numberTo = numberTo;
           this.dateFrom = dateFrom;
           this.dateTo = dateTo;
           this.periodFrom = periodFrom;
           this.periodTo = periodTo;
           this.originalUser = originalUser;
           this.lastModBy = lastModBy;
           this.authInitials = authInitials;
           this.extRefPos = extRefPos;
           this.extRef = extRef;
           this.accountCode = accountCode;
           this.authorisationStatus = authorisationStatus;
    }


    /**
     * Gets the cmpCode value for this IntrayFilter.
     * 
     * @return cmpCode
     */
    public java.lang.String getCmpCode() {
        return cmpCode;
    }


    /**
     * Sets the cmpCode value for this IntrayFilter.
     * 
     * @param cmpCode
     */
    public void setCmpCode(java.lang.String cmpCode) {
        this.cmpCode = cmpCode;
    }


    /**
     * Gets the limit value for this IntrayFilter.
     * 
     * @return limit   * The
     *                         maximum number of documents to
     *                     return.
     */
    public java.lang.Integer getLimit() {
        return limit;
    }


    /**
     * Sets the limit value for this IntrayFilter.
     * 
     * @param limit   * The
     *                         maximum number of documents to
     *                     return.
     */
    public void setLimit(java.lang.Integer limit) {
        this.limit = limit;
    }


    /**
     * Gets the code value for this IntrayFilter.
     * 
     * @return code   * The document master
     *                     code.
     */
    public java.lang.String getCode() {
        return code;
    }


    /**
     * Sets the code value for this IntrayFilter.
     * 
     * @param code   * The document master
     *                     code.
     */
    public void setCode(java.lang.String code) {
        this.code = code;
    }


    /**
     * Gets the numberFrom value for this IntrayFilter.
     * 
     * @return numberFrom   * The
     *                         document number of the single document you
     * want
     *                         to select, or the first document number in
     * a
     *                         range if you want to select multiple
     *                     documents.
     */
    public java.lang.String getNumberFrom() {
        return numberFrom;
    }


    /**
     * Sets the numberFrom value for this IntrayFilter.
     * 
     * @param numberFrom   * The
     *                         document number of the single document you
     * want
     *                         to select, or the first document number in
     * a
     *                         range if you want to select multiple
     *                     documents.
     */
    public void setNumberFrom(java.lang.String numberFrom) {
        this.numberFrom = numberFrom;
    }


    /**
     * Gets the numberTo value for this IntrayFilter.
     * 
     * @return numberTo   * The last document number in
     *                         the range if you want to select multiple
     *                         documents. This can be blank if you are
     *                         selecting a single document.
     */
    public java.lang.String getNumberTo() {
        return numberTo;
    }


    /**
     * Sets the numberTo value for this IntrayFilter.
     * 
     * @param numberTo   * The last document number in
     *                         the range if you want to select multiple
     *                         documents. This can be blank if you are
     *                         selecting a single document.
     */
    public void setNumberTo(java.lang.String numberTo) {
        this.numberTo = numberTo;
    }


    /**
     * Gets the dateFrom value for this IntrayFilter.
     * 
     * @return dateFrom   * The document input date of
     *                         the single document you want to select, or
     * the
     *                         start document input date in a range if you
     * want
     *                         to select multiple
     *                     documents.
     */
    public java.util.Calendar getDateFrom() {
        return dateFrom;
    }


    /**
     * Sets the dateFrom value for this IntrayFilter.
     * 
     * @param dateFrom   * The document input date of
     *                         the single document you want to select, or
     * the
     *                         start document input date in a range if you
     * want
     *                         to select multiple
     *                     documents.
     */
    public void setDateFrom(java.util.Calendar dateFrom) {
        this.dateFrom = dateFrom;
    }


    /**
     * Gets the dateTo value for this IntrayFilter.
     * 
     * @return dateTo   * The end document input date
     *                         in the range if you want to select multiple
     * documents. This can be blank if you are
     *                         selecting a single document.
     */
    public java.util.Calendar getDateTo() {
        return dateTo;
    }


    /**
     * Sets the dateTo value for this IntrayFilter.
     * 
     * @param dateTo   * The end document input date
     *                         in the range if you want to select multiple
     * documents. This can be blank if you are
     *                         selecting a single document.
     */
    public void setDateTo(java.util.Calendar dateTo) {
        this.dateTo = dateTo;
    }


    /**
     * Gets the periodFrom value for this IntrayFilter.
     * 
     * @return periodFrom   * The year/period of the single
     *                         document you want to select, or the start
     *                         year/period in a range if you want to select
     * multiple documents.
     */
    public java.lang.String getPeriodFrom() {
        return periodFrom;
    }


    /**
     * Sets the periodFrom value for this IntrayFilter.
     * 
     * @param periodFrom   * The year/period of the single
     *                         document you want to select, or the start
     *                         year/period in a range if you want to select
     * multiple documents.
     */
    public void setPeriodFrom(java.lang.String periodFrom) {
        this.periodFrom = periodFrom;
    }


    /**
     * Gets the periodTo value for this IntrayFilter.
     * 
     * @return periodTo   * The end year/period in the
     *                         range if you want to select multiple documents.
     * This can be blank if you are selecting a single
     *                     document.
     */
    public java.lang.String getPeriodTo() {
        return periodTo;
    }


    /**
     * Sets the periodTo value for this IntrayFilter.
     * 
     * @param periodTo   * The end year/period in the
     *                         range if you want to select multiple documents.
     * This can be blank if you are selecting a single
     *                     document.
     */
    public void setPeriodTo(java.lang.String periodTo) {
        this.periodTo = periodTo;
    }


    /**
     * Gets the originalUser value for this IntrayFilter.
     * 
     * @return originalUser   * The
     *                         user who created the
     *                     document.
     */
    public java.lang.String getOriginalUser() {
        return originalUser;
    }


    /**
     * Sets the originalUser value for this IntrayFilter.
     * 
     * @param originalUser   * The
     *                         user who created the
     *                     document.
     */
    public void setOriginalUser(java.lang.String originalUser) {
        this.originalUser = originalUser;
    }


    /**
     * Gets the lastModBy value for this IntrayFilter.
     * 
     * @return lastModBy   * The
     *                         user who last modified the document and posted
     * it back to the Intray.
     */
    public java.lang.String getLastModBy() {
        return lastModBy;
    }


    /**
     * Sets the lastModBy value for this IntrayFilter.
     * 
     * @param lastModBy   * The
     *                         user who last modified the document and posted
     * it back to the Intray.
     */
    public void setLastModBy(java.lang.String lastModBy) {
        this.lastModBy = lastModBy;
    }


    /**
     * Gets the authInitials value for this IntrayFilter.
     * 
     * @return authInitials   * The initials of the
     *                         authorising user.
     */
    public java.lang.String getAuthInitials() {
        return authInitials;
    }


    /**
     * Sets the authInitials value for this IntrayFilter.
     * 
     * @param authInitials   * The initials of the
     *                         authorising user.
     */
    public void setAuthInitials(java.lang.String authInitials) {
        this.authInitials = authInitials;
    }


    /**
     * Gets the extRefPos value for this IntrayFilter.
     * 
     * @return extRefPos   * The external reference (1-6)
     *                         that you want to search for the string specified
     * in ExtRef.
     */
    public java.lang.Short getExtRefPos() {
        return extRefPos;
    }


    /**
     * Sets the extRefPos value for this IntrayFilter.
     * 
     * @param extRefPos   * The external reference (1-6)
     *                         that you want to search for the string specified
     * in ExtRef.
     */
    public void setExtRefPos(java.lang.Short extRefPos) {
        this.extRefPos = extRefPos;
    }


    /**
     * Gets the extRef value for this IntrayFilter.
     * 
     * @return extRef   * The document-wide external
     *                         reference of the document you want to
     *                     select.
     */
    public java.lang.String getExtRef() {
        return extRef;
    }


    /**
     * Sets the extRef value for this IntrayFilter.
     * 
     * @param extRef   * The document-wide external
     *                         reference of the document you want to
     *                     select.
     */
    public void setExtRef(java.lang.String extRef) {
        this.extRef = extRef;
    }


    /**
     * Gets the accountCode value for this IntrayFilter.
     * 
     * @return accountCode   * The document account
     *                     code.
     */
    public java.lang.String getAccountCode() {
        return accountCode;
    }


    /**
     * Sets the accountCode value for this IntrayFilter.
     * 
     * @param accountCode   * The document account
     *                     code.
     */
    public void setAccountCode(java.lang.String accountCode) {
        this.accountCode = accountCode;
    }


    /**
     * Gets the authorisationStatus value for this IntrayFilter.
     * 
     * @return authorisationStatus   * The
     *                         current workflow authorisation status of the
     * document.
     */
    public java.lang.String getAuthorisationStatus() {
        return authorisationStatus;
    }


    /**
     * Sets the authorisationStatus value for this IntrayFilter.
     * 
     * @param authorisationStatus   * The
     *                         current workflow authorisation status of the
     * document.
     */
    public void setAuthorisationStatus(java.lang.String authorisationStatus) {
        this.authorisationStatus = authorisationStatus;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof IntrayFilter)) return false;
        IntrayFilter other = (IntrayFilter) obj;
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
            ((this.limit==null && other.getLimit()==null) || 
             (this.limit!=null &&
              this.limit.equals(other.getLimit()))) &&
            ((this.code==null && other.getCode()==null) || 
             (this.code!=null &&
              this.code.equals(other.getCode()))) &&
            ((this.numberFrom==null && other.getNumberFrom()==null) || 
             (this.numberFrom!=null &&
              this.numberFrom.equals(other.getNumberFrom()))) &&
            ((this.numberTo==null && other.getNumberTo()==null) || 
             (this.numberTo!=null &&
              this.numberTo.equals(other.getNumberTo()))) &&
            ((this.dateFrom==null && other.getDateFrom()==null) || 
             (this.dateFrom!=null &&
              this.dateFrom.equals(other.getDateFrom()))) &&
            ((this.dateTo==null && other.getDateTo()==null) || 
             (this.dateTo!=null &&
              this.dateTo.equals(other.getDateTo()))) &&
            ((this.periodFrom==null && other.getPeriodFrom()==null) || 
             (this.periodFrom!=null &&
              this.periodFrom.equals(other.getPeriodFrom()))) &&
            ((this.periodTo==null && other.getPeriodTo()==null) || 
             (this.periodTo!=null &&
              this.periodTo.equals(other.getPeriodTo()))) &&
            ((this.originalUser==null && other.getOriginalUser()==null) || 
             (this.originalUser!=null &&
              this.originalUser.equals(other.getOriginalUser()))) &&
            ((this.lastModBy==null && other.getLastModBy()==null) || 
             (this.lastModBy!=null &&
              this.lastModBy.equals(other.getLastModBy()))) &&
            ((this.authInitials==null && other.getAuthInitials()==null) || 
             (this.authInitials!=null &&
              this.authInitials.equals(other.getAuthInitials()))) &&
            ((this.extRefPos==null && other.getExtRefPos()==null) || 
             (this.extRefPos!=null &&
              this.extRefPos.equals(other.getExtRefPos()))) &&
            ((this.extRef==null && other.getExtRef()==null) || 
             (this.extRef!=null &&
              this.extRef.equals(other.getExtRef()))) &&
            ((this.accountCode==null && other.getAccountCode()==null) || 
             (this.accountCode!=null &&
              this.accountCode.equals(other.getAccountCode()))) &&
            ((this.authorisationStatus==null && other.getAuthorisationStatus()==null) || 
             (this.authorisationStatus!=null &&
              this.authorisationStatus.equals(other.getAuthorisationStatus())));
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
        if (getLimit() != null) {
            _hashCode += getLimit().hashCode();
        }
        if (getCode() != null) {
            _hashCode += getCode().hashCode();
        }
        if (getNumberFrom() != null) {
            _hashCode += getNumberFrom().hashCode();
        }
        if (getNumberTo() != null) {
            _hashCode += getNumberTo().hashCode();
        }
        if (getDateFrom() != null) {
            _hashCode += getDateFrom().hashCode();
        }
        if (getDateTo() != null) {
            _hashCode += getDateTo().hashCode();
        }
        if (getPeriodFrom() != null) {
            _hashCode += getPeriodFrom().hashCode();
        }
        if (getPeriodTo() != null) {
            _hashCode += getPeriodTo().hashCode();
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
        if (getExtRefPos() != null) {
            _hashCode += getExtRefPos().hashCode();
        }
        if (getExtRef() != null) {
            _hashCode += getExtRef().hashCode();
        }
        if (getAccountCode() != null) {
            _hashCode += getAccountCode().hashCode();
        }
        if (getAuthorisationStatus() != null) {
            _hashCode += getAuthorisationStatus().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(IntrayFilter.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "IntrayFilter"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cmpCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "CmpCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("limit");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "Limit"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("code");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "Code"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numberFrom");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "NumberFrom"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numberTo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "NumberTo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dateFrom");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "DateFrom"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dateTo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "DateTo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("periodFrom");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "PeriodFrom"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseYearPeriod"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("periodTo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "PeriodTo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseYearPeriod"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("originalUser");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "OriginalUser"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastModBy");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "LastModBy"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("authInitials");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "AuthInitials"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("extRefPos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "ExtRefPos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("extRef");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "ExtRef"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "AccountCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("authorisationStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "AuthorisationStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum"));
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
