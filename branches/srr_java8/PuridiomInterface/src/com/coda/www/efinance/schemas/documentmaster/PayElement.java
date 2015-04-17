/**
 * PayElement.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.documentmaster;

public class PayElement  implements java.io.Serializable {
    /* Specifies whether the element
     *                         bank used for the payment must be entered
     * during
     *                     Input. */
    private boolean bankCodeRequired;

    /* Specifies whether the account
     *                         number and trading address of the element
     * bank
     *                         used for the payment must be entered during
     * Input. */
    private boolean addressRequired;

    public PayElement() {
    }

    public PayElement(
           boolean bankCodeRequired,
           boolean addressRequired) {
           this.bankCodeRequired = bankCodeRequired;
           this.addressRequired = addressRequired;
    }


    /**
     * Gets the bankCodeRequired value for this PayElement.
     * 
     * @return bankCodeRequired   * Specifies whether the element
     *                         bank used for the payment must be entered
     * during
     *                     Input.
     */
    public boolean isBankCodeRequired() {
        return bankCodeRequired;
    }


    /**
     * Sets the bankCodeRequired value for this PayElement.
     * 
     * @param bankCodeRequired   * Specifies whether the element
     *                         bank used for the payment must be entered
     * during
     *                     Input.
     */
    public void setBankCodeRequired(boolean bankCodeRequired) {
        this.bankCodeRequired = bankCodeRequired;
    }


    /**
     * Gets the addressRequired value for this PayElement.
     * 
     * @return addressRequired   * Specifies whether the account
     *                         number and trading address of the element
     * bank
     *                         used for the payment must be entered during
     * Input.
     */
    public boolean isAddressRequired() {
        return addressRequired;
    }


    /**
     * Sets the addressRequired value for this PayElement.
     * 
     * @param addressRequired   * Specifies whether the account
     *                         number and trading address of the element
     * bank
     *                         used for the payment must be entered during
     * Input.
     */
    public void setAddressRequired(boolean addressRequired) {
        this.addressRequired = addressRequired;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PayElement)) return false;
        PayElement other = (PayElement) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.bankCodeRequired == other.isBankCodeRequired() &&
            this.addressRequired == other.isAddressRequired();
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
        _hashCode += (isBankCodeRequired() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isAddressRequired() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PayElement.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "PayElement"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bankCodeRequired");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "BankCodeRequired"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("addressRequired");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "AddressRequired"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
