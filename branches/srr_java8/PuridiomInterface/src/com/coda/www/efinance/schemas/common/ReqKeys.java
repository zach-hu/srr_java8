/**
 * ReqKeys.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.common;


/**
 * This element is a generic key for
 *                 requesting a series of data items from the server.
 * It
 *                 consists of a company code, a code and the maximum
 * number of items to be returned.
 */
public class ReqKeys  implements java.io.Serializable {
    private int maxKeys;

    /* The
     *                         generic key. It consists of a company code
     * and a
     *                     code. */
    private com.coda.www.efinance.schemas.common.Key key;

    public ReqKeys() {
    }

    public ReqKeys(
           int maxKeys,
           com.coda.www.efinance.schemas.common.Key key) {
           this.maxKeys = maxKeys;
           this.key = key;
    }


    /**
     * Gets the maxKeys value for this ReqKeys.
     * 
     * @return maxKeys
     */
    public int getMaxKeys() {
        return maxKeys;
    }


    /**
     * Sets the maxKeys value for this ReqKeys.
     * 
     * @param maxKeys
     */
    public void setMaxKeys(int maxKeys) {
        this.maxKeys = maxKeys;
    }


    /**
     * Gets the key value for this ReqKeys.
     * 
     * @return key   * The
     *                         generic key. It consists of a company code
     * and a
     *                     code.
     */
    public com.coda.www.efinance.schemas.common.Key getKey() {
        return key;
    }


    /**
     * Sets the key value for this ReqKeys.
     * 
     * @param key   * The
     *                         generic key. It consists of a company code
     * and a
     *                     code.
     */
    public void setKey(com.coda.www.efinance.schemas.common.Key key) {
        this.key = key;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ReqKeys)) return false;
        ReqKeys other = (ReqKeys) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.maxKeys == other.getMaxKeys() &&
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
        _hashCode += getMaxKeys();
        if (getKey() != null) {
            _hashCode += getKey().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ReqKeys.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "ReqKeys"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("maxKeys");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "MaxKeys"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("key");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "Key"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "Key"));
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
