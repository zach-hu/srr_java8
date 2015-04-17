/**
 * IntrayDeleteRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.inputext.input_11_2.webservice;

public class IntrayDeleteRequest  implements java.io.Serializable {
    /* Contains the reason why the
     *                             document on the Intray is being deleted. */
    private com.coda.www.efinance.schemas.inputext.IntrayDeleteInfo intrayDeleteInfo;

    /* Contains key information
     *                             identifying which document(s) on the Intray
     * will be deleted. */
    private com.coda.www.efinance.schemas.transaction.TxnKey[] keys;

    public IntrayDeleteRequest() {
    }

    public IntrayDeleteRequest(
           com.coda.www.efinance.schemas.inputext.IntrayDeleteInfo intrayDeleteInfo,
           com.coda.www.efinance.schemas.transaction.TxnKey[] keys) {
           this.intrayDeleteInfo = intrayDeleteInfo;
           this.keys = keys;
    }


    /**
     * Gets the intrayDeleteInfo value for this IntrayDeleteRequest.
     * 
     * @return intrayDeleteInfo   * Contains the reason why the
     *                             document on the Intray is being deleted.
     */
    public com.coda.www.efinance.schemas.inputext.IntrayDeleteInfo getIntrayDeleteInfo() {
        return intrayDeleteInfo;
    }


    /**
     * Sets the intrayDeleteInfo value for this IntrayDeleteRequest.
     * 
     * @param intrayDeleteInfo   * Contains the reason why the
     *                             document on the Intray is being deleted.
     */
    public void setIntrayDeleteInfo(com.coda.www.efinance.schemas.inputext.IntrayDeleteInfo intrayDeleteInfo) {
        this.intrayDeleteInfo = intrayDeleteInfo;
    }


    /**
     * Gets the keys value for this IntrayDeleteRequest.
     * 
     * @return keys   * Contains key information
     *                             identifying which document(s) on the Intray
     * will be deleted.
     */
    public com.coda.www.efinance.schemas.transaction.TxnKey[] getKeys() {
        return keys;
    }


    /**
     * Sets the keys value for this IntrayDeleteRequest.
     * 
     * @param keys   * Contains key information
     *                             identifying which document(s) on the Intray
     * will be deleted.
     */
    public void setKeys(com.coda.www.efinance.schemas.transaction.TxnKey[] keys) {
        this.keys = keys;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof IntrayDeleteRequest)) return false;
        IntrayDeleteRequest other = (IntrayDeleteRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.intrayDeleteInfo==null && other.getIntrayDeleteInfo()==null) || 
             (this.intrayDeleteInfo!=null &&
              this.intrayDeleteInfo.equals(other.getIntrayDeleteInfo()))) &&
            ((this.keys==null && other.getKeys()==null) || 
             (this.keys!=null &&
              java.util.Arrays.equals(this.keys, other.getKeys())));
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
        if (getIntrayDeleteInfo() != null) {
            _hashCode += getIntrayDeleteInfo().hashCode();
        }
        if (getKeys() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getKeys());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getKeys(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(IntrayDeleteRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">IntrayDeleteRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("intrayDeleteInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "IntrayDeleteInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "IntrayDeleteInfo"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("keys");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "Keys"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "txnKey"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "Key"));
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
