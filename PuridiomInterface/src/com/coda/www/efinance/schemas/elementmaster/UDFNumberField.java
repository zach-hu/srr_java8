/**
 * UDFNumberField.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.elementmaster;


/**
 * The details of a user-defined number
 *             field.
 */
public class UDFNumberField  implements java.io.Serializable {
    private java.math.BigDecimal number;

    /* The UDF
     *                     code. */
    private java.lang.String code;

    public UDFNumberField() {
    }

    public UDFNumberField(
           java.math.BigDecimal number,
           java.lang.String code) {
           this.number = number;
           this.code = code;
    }


    /**
     * Gets the number value for this UDFNumberField.
     * 
     * @return number
     */
    public java.math.BigDecimal getNumber() {
        return number;
    }


    /**
     * Sets the number value for this UDFNumberField.
     * 
     * @param number
     */
    public void setNumber(java.math.BigDecimal number) {
        this.number = number;
    }


    /**
     * Gets the code value for this UDFNumberField.
     * 
     * @return code   * The UDF
     *                     code.
     */
    public java.lang.String getCode() {
        return code;
    }


    /**
     * Sets the code value for this UDFNumberField.
     * 
     * @param code   * The UDF
     *                     code.
     */
    public void setCode(java.lang.String code) {
        this.code = code;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UDFNumberField)) return false;
        UDFNumberField other = (UDFNumberField) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.number==null && other.getNumber()==null) || 
             (this.number!=null &&
              this.number.equals(other.getNumber()))) &&
            ((this.code==null && other.getCode()==null) || 
             (this.code!=null &&
              this.code.equals(other.getCode())));
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
        if (getNumber() != null) {
            _hashCode += getNumber().hashCode();
        }
        if (getCode() != null) {
            _hashCode += getCode().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UDFNumberField.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "UDFNumberField"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("number");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Number"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("code");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Code"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
