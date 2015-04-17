/**
 * GetCopyDocParamsRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.inputext.input_11_2.webservice;

public class GetCopyDocParamsRequest  implements java.io.Serializable {
    /* Contains a key uniquely
     *                             identifying a document within the Financials
     * database. */
    private com.coda.www.efinance.schemas.transaction.TxnKey key;

    public GetCopyDocParamsRequest() {
    }

    public GetCopyDocParamsRequest(
           com.coda.www.efinance.schemas.transaction.TxnKey key) {
           this.key = key;
    }


    /**
     * Gets the key value for this GetCopyDocParamsRequest.
     * 
     * @return key   * Contains a key uniquely
     *                             identifying a document within the Financials
     * database.
     */
    public com.coda.www.efinance.schemas.transaction.TxnKey getKey() {
        return key;
    }


    /**
     * Sets the key value for this GetCopyDocParamsRequest.
     * 
     * @param key   * Contains a key uniquely
     *                             identifying a document within the Financials
     * database.
     */
    public void setKey(com.coda.www.efinance.schemas.transaction.TxnKey key) {
        this.key = key;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetCopyDocParamsRequest)) return false;
        GetCopyDocParamsRequest other = (GetCopyDocParamsRequest) obj;
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
        if (getKey() != null) {
            _hashCode += getKey().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetCopyDocParamsRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">GetCopyDocParamsRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
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
