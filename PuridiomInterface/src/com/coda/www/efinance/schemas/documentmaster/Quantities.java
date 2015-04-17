/**
 * Quantities.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.documentmaster;

public class Quantities  implements java.io.Serializable {
    /* Specifies whether quantities
     *                         are reversed on credit
     *                     lines. */
    private boolean reverseOnCredit;

    /* Specifies whether quantities
     *                         are reversed on debit lines. */
    private boolean reverseOnDebit;

    public Quantities() {
    }

    public Quantities(
           boolean reverseOnCredit,
           boolean reverseOnDebit) {
           this.reverseOnCredit = reverseOnCredit;
           this.reverseOnDebit = reverseOnDebit;
    }


    /**
     * Gets the reverseOnCredit value for this Quantities.
     * 
     * @return reverseOnCredit   * Specifies whether quantities
     *                         are reversed on credit
     *                     lines.
     */
    public boolean isReverseOnCredit() {
        return reverseOnCredit;
    }


    /**
     * Sets the reverseOnCredit value for this Quantities.
     * 
     * @param reverseOnCredit   * Specifies whether quantities
     *                         are reversed on credit
     *                     lines.
     */
    public void setReverseOnCredit(boolean reverseOnCredit) {
        this.reverseOnCredit = reverseOnCredit;
    }


    /**
     * Gets the reverseOnDebit value for this Quantities.
     * 
     * @return reverseOnDebit   * Specifies whether quantities
     *                         are reversed on debit lines.
     */
    public boolean isReverseOnDebit() {
        return reverseOnDebit;
    }


    /**
     * Sets the reverseOnDebit value for this Quantities.
     * 
     * @param reverseOnDebit   * Specifies whether quantities
     *                         are reversed on debit lines.
     */
    public void setReverseOnDebit(boolean reverseOnDebit) {
        this.reverseOnDebit = reverseOnDebit;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Quantities)) return false;
        Quantities other = (Quantities) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.reverseOnCredit == other.isReverseOnCredit() &&
            this.reverseOnDebit == other.isReverseOnDebit();
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
        _hashCode += (isReverseOnCredit() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isReverseOnDebit() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Quantities.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "Quantities"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reverseOnCredit");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "ReverseOnCredit"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reverseOnDebit");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "ReverseOnDebit"));
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
