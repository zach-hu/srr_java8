/**
 * CopyDocRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.inputext.input_11_2.webservice;

public class CopyDocRequest  implements java.io.Serializable {
    /* Contains a key uniquely
     *                             identifying a document within the Financials
     * database. */
    private com.coda.www.efinance.schemas.transaction.TxnKey key;

    /* The document date for the new document. */
    private java.util.Calendar newDate;

    /* The year/period to which the
     *                             new document should be posted. */
    private java.lang.String newPeriod;

    /* The code of the input
     *                             template master to associate with the
     * new document. */
    private java.lang.String inputTemplateCode;

    public CopyDocRequest() {
    }

    public CopyDocRequest(
           com.coda.www.efinance.schemas.transaction.TxnKey key,
           java.util.Calendar newDate,
           java.lang.String newPeriod,
           java.lang.String inputTemplateCode) {
           this.key = key;
           this.newDate = newDate;
           this.newPeriod = newPeriod;
           this.inputTemplateCode = inputTemplateCode;
    }


    /**
     * Gets the key value for this CopyDocRequest.
     * 
     * @return key   * Contains a key uniquely
     *                             identifying a document within the Financials
     * database.
     */
    public com.coda.www.efinance.schemas.transaction.TxnKey getKey() {
        return key;
    }


    /**
     * Sets the key value for this CopyDocRequest.
     * 
     * @param key   * Contains a key uniquely
     *                             identifying a document within the Financials
     * database.
     */
    public void setKey(com.coda.www.efinance.schemas.transaction.TxnKey key) {
        this.key = key;
    }


    /**
     * Gets the newDate value for this CopyDocRequest.
     * 
     * @return newDate   * The document date for the new document.
     */
    public java.util.Calendar getNewDate() {
        return newDate;
    }


    /**
     * Sets the newDate value for this CopyDocRequest.
     * 
     * @param newDate   * The document date for the new document.
     */
    public void setNewDate(java.util.Calendar newDate) {
        this.newDate = newDate;
    }


    /**
     * Gets the newPeriod value for this CopyDocRequest.
     * 
     * @return newPeriod   * The year/period to which the
     *                             new document should be posted.
     */
    public java.lang.String getNewPeriod() {
        return newPeriod;
    }


    /**
     * Sets the newPeriod value for this CopyDocRequest.
     * 
     * @param newPeriod   * The year/period to which the
     *                             new document should be posted.
     */
    public void setNewPeriod(java.lang.String newPeriod) {
        this.newPeriod = newPeriod;
    }


    /**
     * Gets the inputTemplateCode value for this CopyDocRequest.
     * 
     * @return inputTemplateCode   * The code of the input
     *                             template master to associate with the
     * new document.
     */
    public java.lang.String getInputTemplateCode() {
        return inputTemplateCode;
    }


    /**
     * Sets the inputTemplateCode value for this CopyDocRequest.
     * 
     * @param inputTemplateCode   * The code of the input
     *                             template master to associate with the
     * new document.
     */
    public void setInputTemplateCode(java.lang.String inputTemplateCode) {
        this.inputTemplateCode = inputTemplateCode;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CopyDocRequest)) return false;
        CopyDocRequest other = (CopyDocRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.key==null && other.getKey()==null) || 
             (this.key!=null &&
              this.key.equals(other.getKey()))) &&
            ((this.newDate==null && other.getNewDate()==null) || 
             (this.newDate!=null &&
              this.newDate.equals(other.getNewDate()))) &&
            ((this.newPeriod==null && other.getNewPeriod()==null) || 
             (this.newPeriod!=null &&
              this.newPeriod.equals(other.getNewPeriod()))) &&
            ((this.inputTemplateCode==null && other.getInputTemplateCode()==null) || 
             (this.inputTemplateCode!=null &&
              this.inputTemplateCode.equals(other.getInputTemplateCode())));
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
        if (getKey() != null) {
            _hashCode += getKey().hashCode();
        }
        if (getNewDate() != null) {
            _hashCode += getNewDate().hashCode();
        }
        if (getNewPeriod() != null) {
            _hashCode += getNewPeriod().hashCode();
        }
        if (getInputTemplateCode() != null) {
            _hashCode += getInputTemplateCode().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CopyDocRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">CopyDocRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("key");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "Key"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "txnKey"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("newDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "NewDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("newPeriod");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "NewPeriod"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseYearPeriod"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inputTemplateCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "InputTemplateCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
