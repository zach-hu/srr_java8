/**
 * ChkAccCodeData.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.transaction;


/**
 * This element holds details of the
 *                 account code you want to validate and the type of
 *                 validation you want to perform.
 */
public class ChkAccCodeData  implements java.io.Serializable {
    private java.lang.String cmpCode;

    /* The
     *                         InterCompany destination code prefix to the
     * account code. */
    private java.lang.String destination;

    /* The
     *                         account code to be
     *                     validated. */
    private java.lang.String accountCode;

    /* Specifies whether the account
     *                         code supplied is complete or just an initial
     * fragment. */
    private java.lang.Boolean codeComplete;

    /* The period for which you want
     *                         the account code validated. */
    private java.lang.String period;

    /* Specifies the type of
     *                         validation you want
     *                     performed. */
    private java.lang.String checkingType;

    public ChkAccCodeData() {
    }

    public ChkAccCodeData(
           java.lang.String cmpCode,
           java.lang.String destination,
           java.lang.String accountCode,
           java.lang.Boolean codeComplete,
           java.lang.String period,
           java.lang.String checkingType) {
           this.cmpCode = cmpCode;
           this.destination = destination;
           this.accountCode = accountCode;
           this.codeComplete = codeComplete;
           this.period = period;
           this.checkingType = checkingType;
    }


    /**
     * Gets the cmpCode value for this ChkAccCodeData.
     * 
     * @return cmpCode
     */
    public java.lang.String getCmpCode() {
        return cmpCode;
    }


    /**
     * Sets the cmpCode value for this ChkAccCodeData.
     * 
     * @param cmpCode
     */
    public void setCmpCode(java.lang.String cmpCode) {
        this.cmpCode = cmpCode;
    }


    /**
     * Gets the destination value for this ChkAccCodeData.
     * 
     * @return destination   * The
     *                         InterCompany destination code prefix to the
     * account code.
     */
    public java.lang.String getDestination() {
        return destination;
    }


    /**
     * Sets the destination value for this ChkAccCodeData.
     * 
     * @param destination   * The
     *                         InterCompany destination code prefix to the
     * account code.
     */
    public void setDestination(java.lang.String destination) {
        this.destination = destination;
    }


    /**
     * Gets the accountCode value for this ChkAccCodeData.
     * 
     * @return accountCode   * The
     *                         account code to be
     *                     validated.
     */
    public java.lang.String getAccountCode() {
        return accountCode;
    }


    /**
     * Sets the accountCode value for this ChkAccCodeData.
     * 
     * @param accountCode   * The
     *                         account code to be
     *                     validated.
     */
    public void setAccountCode(java.lang.String accountCode) {
        this.accountCode = accountCode;
    }


    /**
     * Gets the codeComplete value for this ChkAccCodeData.
     * 
     * @return codeComplete   * Specifies whether the account
     *                         code supplied is complete or just an initial
     * fragment.
     */
    public java.lang.Boolean getCodeComplete() {
        return codeComplete;
    }


    /**
     * Sets the codeComplete value for this ChkAccCodeData.
     * 
     * @param codeComplete   * Specifies whether the account
     *                         code supplied is complete or just an initial
     * fragment.
     */
    public void setCodeComplete(java.lang.Boolean codeComplete) {
        this.codeComplete = codeComplete;
    }


    /**
     * Gets the period value for this ChkAccCodeData.
     * 
     * @return period   * The period for which you want
     *                         the account code validated.
     */
    public java.lang.String getPeriod() {
        return period;
    }


    /**
     * Sets the period value for this ChkAccCodeData.
     * 
     * @param period   * The period for which you want
     *                         the account code validated.
     */
    public void setPeriod(java.lang.String period) {
        this.period = period;
    }


    /**
     * Gets the checkingType value for this ChkAccCodeData.
     * 
     * @return checkingType   * Specifies the type of
     *                         validation you want
     *                     performed.
     */
    public java.lang.String getCheckingType() {
        return checkingType;
    }


    /**
     * Sets the checkingType value for this ChkAccCodeData.
     * 
     * @param checkingType   * Specifies the type of
     *                         validation you want
     *                     performed.
     */
    public void setCheckingType(java.lang.String checkingType) {
        this.checkingType = checkingType;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ChkAccCodeData)) return false;
        ChkAccCodeData other = (ChkAccCodeData) obj;
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
            ((this.destination==null && other.getDestination()==null) || 
             (this.destination!=null &&
              this.destination.equals(other.getDestination()))) &&
            ((this.accountCode==null && other.getAccountCode()==null) || 
             (this.accountCode!=null &&
              this.accountCode.equals(other.getAccountCode()))) &&
            ((this.codeComplete==null && other.getCodeComplete()==null) || 
             (this.codeComplete!=null &&
              this.codeComplete.equals(other.getCodeComplete()))) &&
            ((this.period==null && other.getPeriod()==null) || 
             (this.period!=null &&
              this.period.equals(other.getPeriod()))) &&
            ((this.checkingType==null && other.getCheckingType()==null) || 
             (this.checkingType!=null &&
              this.checkingType.equals(other.getCheckingType())));
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
        if (getDestination() != null) {
            _hashCode += getDestination().hashCode();
        }
        if (getAccountCode() != null) {
            _hashCode += getAccountCode().hashCode();
        }
        if (getCodeComplete() != null) {
            _hashCode += getCodeComplete().hashCode();
        }
        if (getPeriod() != null) {
            _hashCode += getPeriod().hashCode();
        }
        if (getCheckingType() != null) {
            _hashCode += getCheckingType().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ChkAccCodeData.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "ChkAccCodeData"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cmpCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "CmpCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("destination");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Destination"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "AccountCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codeComplete");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "CodeComplete"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("period");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Period"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseYearPeriod"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("checkingType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "CheckingType"));
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
