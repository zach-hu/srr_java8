/**
 * IntrayLoadRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.inputext.input_11_2.webservice;

public class IntrayLoadRequest  implements java.io.Serializable {
    /* The code of the input
     *                             template master used to input the document
     * that you want to load. */
    private java.lang.String templateCode;

    /* A key identifying the
     *                             document on the Intray that will be loaded. */
    private com.coda.www.efinance.schemas.transaction.TxnKey key;

    public IntrayLoadRequest() {
    }

    public IntrayLoadRequest(
           java.lang.String templateCode,
           com.coda.www.efinance.schemas.transaction.TxnKey key) {
           this.templateCode = templateCode;
           this.key = key;
    }


    /**
     * Gets the templateCode value for this IntrayLoadRequest.
     * 
     * @return templateCode   * The code of the input
     *                             template master used to input the document
     * that you want to load.
     */
    public java.lang.String getTemplateCode() {
        return templateCode;
    }


    /**
     * Sets the templateCode value for this IntrayLoadRequest.
     * 
     * @param templateCode   * The code of the input
     *                             template master used to input the document
     * that you want to load.
     */
    public void setTemplateCode(java.lang.String templateCode) {
        this.templateCode = templateCode;
    }


    /**
     * Gets the key value for this IntrayLoadRequest.
     * 
     * @return key   * A key identifying the
     *                             document on the Intray that will be loaded.
     */
    public com.coda.www.efinance.schemas.transaction.TxnKey getKey() {
        return key;
    }


    /**
     * Sets the key value for this IntrayLoadRequest.
     * 
     * @param key   * A key identifying the
     *                             document on the Intray that will be loaded.
     */
    public void setKey(com.coda.www.efinance.schemas.transaction.TxnKey key) {
        this.key = key;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof IntrayLoadRequest)) return false;
        IntrayLoadRequest other = (IntrayLoadRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.templateCode==null && other.getTemplateCode()==null) || 
             (this.templateCode!=null &&
              this.templateCode.equals(other.getTemplateCode()))) &&
            ((this.key==null && other.getKey()==null) || 
             (this.key!=null &&
              this.key.equals(other.getKey())));
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
        if (getTemplateCode() != null) {
            _hashCode += getTemplateCode().hashCode();
        }
        if (getKey() != null) {
            _hashCode += getKey().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(IntrayLoadRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">IntrayLoadRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("templateCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "TemplateCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("key");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "Key"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "txnKey"));
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
