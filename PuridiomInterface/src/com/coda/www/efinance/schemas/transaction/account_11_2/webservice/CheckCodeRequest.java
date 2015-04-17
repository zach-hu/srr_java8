/**
 * CheckCodeRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.transaction.account_11_2.webservice;

public class CheckCodeRequest  implements java.io.Serializable {
    /* This element holds details of
     *                             the account code you want to validate
     * and
     *                             the type of validation you want to perform. */
    private com.coda.www.efinance.schemas.transaction.ChkAccCodeData chkCodeData;

    public CheckCodeRequest() {
    }

    public CheckCodeRequest(
           com.coda.www.efinance.schemas.transaction.ChkAccCodeData chkCodeData) {
           this.chkCodeData = chkCodeData;
    }


    /**
     * Gets the chkCodeData value for this CheckCodeRequest.
     * 
     * @return chkCodeData   * This element holds details of
     *                             the account code you want to validate
     * and
     *                             the type of validation you want to perform.
     */
    public com.coda.www.efinance.schemas.transaction.ChkAccCodeData getChkCodeData() {
        return chkCodeData;
    }


    /**
     * Sets the chkCodeData value for this CheckCodeRequest.
     * 
     * @param chkCodeData   * This element holds details of
     *                             the account code you want to validate
     * and
     *                             the type of validation you want to perform.
     */
    public void setChkCodeData(com.coda.www.efinance.schemas.transaction.ChkAccCodeData chkCodeData) {
        this.chkCodeData = chkCodeData;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CheckCodeRequest)) return false;
        CheckCodeRequest other = (CheckCodeRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.chkCodeData==null && other.getChkCodeData()==null) || 
             (this.chkCodeData!=null &&
              this.chkCodeData.equals(other.getChkCodeData())));
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
        if (getChkCodeData() != null) {
            _hashCode += getChkCodeData().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CheckCodeRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction/account-11.2/webservice", ">CheckCodeRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("chkCodeData");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction/account-11.2/webservice", "ChkCodeData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "ChkAccCodeData"));
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
