/**
 * GetAddressesRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice;

public class GetAddressesRequest  implements java.io.Serializable {
    /* Contains the key identifying
     *                             the element address you want to retrieve
     * from the database. */
    private com.coda.www.efinance.schemas.elementmaster.ElmFullKeyWithTemporaryElmFilter key;

    public GetAddressesRequest() {
    }

    public GetAddressesRequest(
           com.coda.www.efinance.schemas.elementmaster.ElmFullKeyWithTemporaryElmFilter key) {
           this.key = key;
    }


    /**
     * Gets the key value for this GetAddressesRequest.
     * 
     * @return key   * Contains the key identifying
     *                             the element address you want to retrieve
     * from the database.
     */
    public com.coda.www.efinance.schemas.elementmaster.ElmFullKeyWithTemporaryElmFilter getKey() {
        return key;
    }


    /**
     * Sets the key value for this GetAddressesRequest.
     * 
     * @param key   * Contains the key identifying
     *                             the element address you want to retrieve
     * from the database.
     */
    public void setKey(com.coda.www.efinance.schemas.elementmaster.ElmFullKeyWithTemporaryElmFilter key) {
        this.key = key;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetAddressesRequest)) return false;
        GetAddressesRequest other = (GetAddressesRequest) obj;
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
        new org.apache.axis.description.TypeDesc(GetAddressesRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", ">GetAddressesRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("key");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", "Key"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "elmFullKeyWithTemporaryElmFilter"));
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
