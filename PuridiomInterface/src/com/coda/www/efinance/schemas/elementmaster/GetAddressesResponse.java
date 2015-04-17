/**
 * GetAddressesResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.elementmaster;

public class GetAddressesResponse  extends com.coda.www.efinance.schemas.common.Response  implements java.io.Serializable {
    /* Contains the key
     *                                 identifying the element address you
     * attempted to retrieve from the
     *                             database. */
    private com.coda.www.efinance.schemas.elementmaster.ElmFullKeyWithTemporaryElmFilter key;

    /* Contains the element
     *                                 address information you have retrieved
     * from the database. */
    private com.coda.www.efinance.schemas.elementmaster.AddressElement[] addresses;

    public GetAddressesResponse() {
    }

    public GetAddressesResponse(
           com.coda.www.efinance.schemas.common.TypeResponseStatus status,
           java.lang.String transactioncoordinator,
           com.coda.www.efinance.schemas.common.Reason reason,
           com.coda.www.efinance.schemas.elementmaster.ElmFullKeyWithTemporaryElmFilter key,
           com.coda.www.efinance.schemas.elementmaster.AddressElement[] addresses) {
        super();
        this.key = key;
        this.addresses = addresses;
    }


    /**
     * Gets the key value for this GetAddressesResponse.
     *
     * @return key   * Contains the key
     *                                 identifying the element address you
     * attempted to retrieve from the
     *                             database.
     */
    public com.coda.www.efinance.schemas.elementmaster.ElmFullKeyWithTemporaryElmFilter getKey() {
        return key;
    }


    /**
     * Sets the key value for this GetAddressesResponse.
     *
     * @param key   * Contains the key
     *                                 identifying the element address you
     * attempted to retrieve from the
     *                             database.
     */
    public void setKey(com.coda.www.efinance.schemas.elementmaster.ElmFullKeyWithTemporaryElmFilter key) {
        this.key = key;
    }


    /**
     * Gets the addresses value for this GetAddressesResponse.
     *
     * @return addresses   * Contains the element
     *                                 address information you have retrieved
     * from the database.
     */
    public com.coda.www.efinance.schemas.elementmaster.AddressElement[] getAddresses() {
        return addresses;
    }


    /**
     * Sets the addresses value for this GetAddressesResponse.
     *
     * @param addresses   * Contains the element
     *                                 address information you have retrieved
     * from the database.
     */
    public void setAddresses(com.coda.www.efinance.schemas.elementmaster.AddressElement[] addresses) {
        this.addresses = addresses;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetAddressesResponse)) return false;
        GetAddressesResponse other = (GetAddressesResponse) obj;
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
            ((this.addresses==null && other.getAddresses()==null) ||
             (this.addresses!=null &&
              java.util.Arrays.equals(this.addresses, other.getAddresses())));
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
        if (getAddresses() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAddresses());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAddresses(), i);
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
        new org.apache.axis.description.TypeDesc(GetAddressesResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "GetAddressesResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("key");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Key"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "elmFullKeyWithTemporaryElmFilter"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("addresses");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Addresses"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "AddressElement"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Address"));
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
