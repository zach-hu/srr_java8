/**
 * FilterResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.elementmaster;

public class FilterResponse  extends com.coda.www.efinance.schemas.common.Response  implements java.io.Serializable {
    /* This element contains
     *                                 key information identifying the element
     * filter master sent in the request
     *                                 corresponding to this
     *                             response. */
    private com.coda.www.efinance.schemas.elementfiltermaster.ElementFilterKey key;

    /* The maximum number of
     *                                 element masters to be
     *                             returned. */
    private java.lang.Integer maxKeys;

    /* If TRUE, specifies
     *                                 that names have been
     *                             returned. */
    private java.lang.Boolean returnNames;

    /* Contains the key
     *                                 information for the listed element
     * masters. */
    private com.coda.www.efinance.schemas.elementmaster.ElmKeyDataElementNamed[] keys;

    public FilterResponse() {
    }

    public FilterResponse(
           com.coda.www.efinance.schemas.common.TypeResponseStatus status,
           java.lang.String transactioncoordinator,
           com.coda.www.efinance.schemas.common.Reason reason,
           com.coda.www.efinance.schemas.elementfiltermaster.ElementFilterKey key,
           java.lang.Integer maxKeys,
           java.lang.Boolean returnNames,
           com.coda.www.efinance.schemas.elementmaster.ElmKeyDataElementNamed[] keys) {
        super();
        this.key = key;
        this.maxKeys = maxKeys;
        this.returnNames = returnNames;
        this.keys = keys;
    }


    /**
     * Gets the key value for this FilterResponse.
     *
     * @return key   * This element contains
     *                                 key information identifying the element
     * filter master sent in the request
     *                                 corresponding to this
     *                             response.
     */
    public com.coda.www.efinance.schemas.elementfiltermaster.ElementFilterKey getKey() {
        return key;
    }


    /**
     * Sets the key value for this FilterResponse.
     *
     * @param key   * This element contains
     *                                 key information identifying the element
     * filter master sent in the request
     *                                 corresponding to this
     *                             response.
     */
    public void setKey(com.coda.www.efinance.schemas.elementfiltermaster.ElementFilterKey key) {
        this.key = key;
    }


    /**
     * Gets the maxKeys value for this FilterResponse.
     *
     * @return maxKeys   * The maximum number of
     *                                 element masters to be
     *                             returned.
     */
    public java.lang.Integer getMaxKeys() {
        return maxKeys;
    }


    /**
     * Sets the maxKeys value for this FilterResponse.
     *
     * @param maxKeys   * The maximum number of
     *                                 element masters to be
     *                             returned.
     */
    public void setMaxKeys(java.lang.Integer maxKeys) {
        this.maxKeys = maxKeys;
    }


    /**
     * Gets the returnNames value for this FilterResponse.
     *
     * @return returnNames   * If TRUE, specifies
     *                                 that names have been
     *                             returned.
     */
    public java.lang.Boolean getReturnNames() {
        return returnNames;
    }


    /**
     * Sets the returnNames value for this FilterResponse.
     *
     * @param returnNames   * If TRUE, specifies
     *                                 that names have been
     *                             returned.
     */
    public void setReturnNames(java.lang.Boolean returnNames) {
        this.returnNames = returnNames;
    }


    /**
     * Gets the keys value for this FilterResponse.
     *
     * @return keys   * Contains the key
     *                                 information for the listed element
     * masters.
     */
    public com.coda.www.efinance.schemas.elementmaster.ElmKeyDataElementNamed[] getKeys() {
        return keys;
    }


    /**
     * Sets the keys value for this FilterResponse.
     *
     * @param keys   * Contains the key
     *                                 information for the listed element
     * masters.
     */
    public void setKeys(com.coda.www.efinance.schemas.elementmaster.ElmKeyDataElementNamed[] keys) {
        this.keys = keys;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof FilterResponse)) return false;
        FilterResponse other = (FilterResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) &&
            ((this.key==null && other.getKey()==null) ||
             (this.key!=null &&
              this.key.equals(other.getKey()))) &&
            ((this.maxKeys==null && other.getMaxKeys()==null) ||
             (this.maxKeys!=null &&
              this.maxKeys.equals(other.getMaxKeys()))) &&
            ((this.returnNames==null && other.getReturnNames()==null) ||
             (this.returnNames!=null &&
              this.returnNames.equals(other.getReturnNames()))) &&
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
        int _hashCode = super.hashCode();
        if (getKey() != null) {
            _hashCode += getKey().hashCode();
        }
        if (getMaxKeys() != null) {
            _hashCode += getMaxKeys().hashCode();
        }
        if (getReturnNames() != null) {
            _hashCode += getReturnNames().hashCode();
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
        new org.apache.axis.description.TypeDesc(FilterResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "FilterResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("key");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Key"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementfiltermaster", "ElementFilterKey"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("maxKeys");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "MaxKeys"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("returnNames");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "ReturnNames"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("keys");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Keys"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "elmKeyDataElementNamed"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Key"));
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
