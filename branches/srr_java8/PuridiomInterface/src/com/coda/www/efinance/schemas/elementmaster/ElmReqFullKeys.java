/**
 * ElmReqFullKeys.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.elementmaster;


/**
 * This is an element
 *                 key used when requesting a list of elements, as it
 * incorporates a value for the maximum number of items to
 *                 return. It also incorporates the company within which
 * the element exists.
 */
public class ElmReqFullKeys  implements java.io.Serializable {
    private int maxKeys;

    /* An element
     *                     key. */
    private com.coda.www.efinance.schemas.elementmaster.ElmFullKey fullWildKey;

    public ElmReqFullKeys() {
    }

    public ElmReqFullKeys(
           int maxKeys,
           com.coda.www.efinance.schemas.elementmaster.ElmFullKey fullWildKey) {
           this.maxKeys = maxKeys;
           this.fullWildKey = fullWildKey;
    }


    /**
     * Gets the maxKeys value for this ElmReqFullKeys.
     * 
     * @return maxKeys
     */
    public int getMaxKeys() {
        return maxKeys;
    }


    /**
     * Sets the maxKeys value for this ElmReqFullKeys.
     * 
     * @param maxKeys
     */
    public void setMaxKeys(int maxKeys) {
        this.maxKeys = maxKeys;
    }


    /**
     * Gets the fullWildKey value for this ElmReqFullKeys.
     * 
     * @return fullWildKey   * An element
     *                     key.
     */
    public com.coda.www.efinance.schemas.elementmaster.ElmFullKey getFullWildKey() {
        return fullWildKey;
    }


    /**
     * Sets the fullWildKey value for this ElmReqFullKeys.
     * 
     * @param fullWildKey   * An element
     *                     key.
     */
    public void setFullWildKey(com.coda.www.efinance.schemas.elementmaster.ElmFullKey fullWildKey) {
        this.fullWildKey = fullWildKey;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ElmReqFullKeys)) return false;
        ElmReqFullKeys other = (ElmReqFullKeys) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.maxKeys == other.getMaxKeys() &&
            ((this.fullWildKey==null && other.getFullWildKey()==null) || 
             (this.fullWildKey!=null &&
              this.fullWildKey.equals(other.getFullWildKey())));
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
        if (getFullWildKey() != null) {
            _hashCode += getFullWildKey().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ElmReqFullKeys.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "elmReqFullKeys"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("maxKeys");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "MaxKeys"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fullWildKey");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "FullWildKey"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "elmFullKey"));
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
