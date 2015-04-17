/**
 * ItmReqKeys.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.inputtemplate;


/**
 * This is an input template key used
 *                 when requesting a list of input
 *             templates.
 */
public class ItmReqKeys  implements java.io.Serializable {
    private int maxKeys;

    /* Contains key information
     *                         about an input template master. The information
     * includes the company within which the input
     *                         template exists and the code for the linked
     * document master. */
    private com.coda.www.efinance.schemas.inputtemplate.ItmReqKey key;

    public ItmReqKeys() {
    }

    public ItmReqKeys(
           int maxKeys,
           com.coda.www.efinance.schemas.inputtemplate.ItmReqKey key) {
           this.maxKeys = maxKeys;
           this.key = key;
    }


    /**
     * Gets the maxKeys value for this ItmReqKeys.
     * 
     * @return maxKeys
     */
    public int getMaxKeys() {
        return maxKeys;
    }


    /**
     * Sets the maxKeys value for this ItmReqKeys.
     * 
     * @param maxKeys
     */
    public void setMaxKeys(int maxKeys) {
        this.maxKeys = maxKeys;
    }


    /**
     * Gets the key value for this ItmReqKeys.
     * 
     * @return key   * Contains key information
     *                         about an input template master. The information
     * includes the company within which the input
     *                         template exists and the code for the linked
     * document master.
     */
    public com.coda.www.efinance.schemas.inputtemplate.ItmReqKey getKey() {
        return key;
    }


    /**
     * Sets the key value for this ItmReqKeys.
     * 
     * @param key   * Contains key information
     *                         about an input template master. The information
     * includes the company within which the input
     *                         template exists and the code for the linked
     * document master.
     */
    public void setKey(com.coda.www.efinance.schemas.inputtemplate.ItmReqKey key) {
        this.key = key;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ItmReqKeys)) return false;
        ItmReqKeys other = (ItmReqKeys) obj;
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
        new org.apache.axis.description.TypeDesc(ItmReqKeys.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "itmReqKeys"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("maxKeys");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "MaxKeys"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("key");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "Key"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "itmReqKey"));
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
