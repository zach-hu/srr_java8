/**
 * FetchRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice;

public class FetchRequest  implements java.io.Serializable {
    private com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.FetchRequestFetchOptions fetchOptions;

    /* Contains the key identifying
     *                             the element information you want to retrieve
     * from the database. */
    private com.coda.www.efinance.schemas.elementmaster.FetchKey key;

    public FetchRequest() {
    }

    public FetchRequest(
           com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.FetchRequestFetchOptions fetchOptions,
           com.coda.www.efinance.schemas.elementmaster.FetchKey key) {
           this.fetchOptions = fetchOptions;
           this.key = key;
    }


    /**
     * Gets the fetchOptions value for this FetchRequest.
     * 
     * @return fetchOptions
     */
    public com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.FetchRequestFetchOptions getFetchOptions() {
        return fetchOptions;
    }


    /**
     * Sets the fetchOptions value for this FetchRequest.
     * 
     * @param fetchOptions
     */
    public void setFetchOptions(com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.FetchRequestFetchOptions fetchOptions) {
        this.fetchOptions = fetchOptions;
    }


    /**
     * Gets the key value for this FetchRequest.
     * 
     * @return key   * Contains the key identifying
     *                             the element information you want to retrieve
     * from the database.
     */
    public com.coda.www.efinance.schemas.elementmaster.FetchKey getKey() {
        return key;
    }


    /**
     * Sets the key value for this FetchRequest.
     * 
     * @param key   * Contains the key identifying
     *                             the element information you want to retrieve
     * from the database.
     */
    public void setKey(com.coda.www.efinance.schemas.elementmaster.FetchKey key) {
        this.key = key;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof FetchRequest)) return false;
        FetchRequest other = (FetchRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.fetchOptions==null && other.getFetchOptions()==null) || 
             (this.fetchOptions!=null &&
              this.fetchOptions.equals(other.getFetchOptions()))) &&
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
        if (getFetchOptions() != null) {
            _hashCode += getFetchOptions().hashCode();
        }
        if (getKey() != null) {
            _hashCode += getKey().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(FetchRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", ">FetchRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fetchOptions");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", "FetchOptions"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", ">>FetchRequest>FetchOptions"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("key");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", "Key"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "FetchKey"));
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
