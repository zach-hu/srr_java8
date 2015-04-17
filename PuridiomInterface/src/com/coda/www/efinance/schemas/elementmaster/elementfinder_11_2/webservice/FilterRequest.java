/**
 * FilterRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.elementmaster.elementfinder_11_2.webservice;

public class FilterRequest  implements java.io.Serializable {
    /* This element contains key
     *                             information for specifying the element
     * filter master to use. */
    private com.coda.www.efinance.schemas.elementfiltermaster.ElementFilterKey key;

    /* The maximum number of element
     *                             masters to be returned. */
    private java.lang.Integer maxKeys;

    /* If TRUE, specifies that names
     *                             should be returned. */
    private java.lang.Boolean returnNames;

    public FilterRequest() {
    }

    public FilterRequest(
           com.coda.www.efinance.schemas.elementfiltermaster.ElementFilterKey key,
           java.lang.Integer maxKeys,
           java.lang.Boolean returnNames) {
           this.key = key;
           this.maxKeys = maxKeys;
           this.returnNames = returnNames;
    }


    /**
     * Gets the key value for this FilterRequest.
     * 
     * @return key   * This element contains key
     *                             information for specifying the element
     * filter master to use.
     */
    public com.coda.www.efinance.schemas.elementfiltermaster.ElementFilterKey getKey() {
        return key;
    }


    /**
     * Sets the key value for this FilterRequest.
     * 
     * @param key   * This element contains key
     *                             information for specifying the element
     * filter master to use.
     */
    public void setKey(com.coda.www.efinance.schemas.elementfiltermaster.ElementFilterKey key) {
        this.key = key;
    }


    /**
     * Gets the maxKeys value for this FilterRequest.
     * 
     * @return maxKeys   * The maximum number of element
     *                             masters to be returned.
     */
    public java.lang.Integer getMaxKeys() {
        return maxKeys;
    }


    /**
     * Sets the maxKeys value for this FilterRequest.
     * 
     * @param maxKeys   * The maximum number of element
     *                             masters to be returned.
     */
    public void setMaxKeys(java.lang.Integer maxKeys) {
        this.maxKeys = maxKeys;
    }


    /**
     * Gets the returnNames value for this FilterRequest.
     * 
     * @return returnNames   * If TRUE, specifies that names
     *                             should be returned.
     */
    public java.lang.Boolean getReturnNames() {
        return returnNames;
    }


    /**
     * Sets the returnNames value for this FilterRequest.
     * 
     * @param returnNames   * If TRUE, specifies that names
     *                             should be returned.
     */
    public void setReturnNames(java.lang.Boolean returnNames) {
        this.returnNames = returnNames;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof FilterRequest)) return false;
        FilterRequest other = (FilterRequest) obj;
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
            ((this.maxKeys==null && other.getMaxKeys()==null) || 
             (this.maxKeys!=null &&
              this.maxKeys.equals(other.getMaxKeys()))) &&
            ((this.returnNames==null && other.getReturnNames()==null) || 
             (this.returnNames!=null &&
              this.returnNames.equals(other.getReturnNames())));
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
        if (getMaxKeys() != null) {
            _hashCode += getMaxKeys().hashCode();
        }
        if (getReturnNames() != null) {
            _hashCode += getReturnNames().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(FilterRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementfinder-11.2/webservice", ">FilterRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("key");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementfinder-11.2/webservice", "Key"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementfiltermaster", "ElementFilterKey"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("maxKeys");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementfinder-11.2/webservice", "MaxKeys"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("returnNames");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementfinder-11.2/webservice", "ReturnNames"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
