/**
 * UDFDefinedValue.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.common.schemas.udfmaster;


/**
 * This element contains details of a
 *                 UDF value.
 */
public class UDFDefinedValue  implements java.io.Serializable {
    private int sequenceNumber;

    /* The field
     *                     value. */
    private java.lang.String udfValue;

    /* The display name for the
     *                     UDF. */
    private java.lang.String elementDisplayName;

    public UDFDefinedValue() {
    }

    public UDFDefinedValue(
           int sequenceNumber,
           java.lang.String udfValue,
           java.lang.String elementDisplayName) {
           this.sequenceNumber = sequenceNumber;
           this.udfValue = udfValue;
           this.elementDisplayName = elementDisplayName;
    }


    /**
     * Gets the sequenceNumber value for this UDFDefinedValue.
     * 
     * @return sequenceNumber
     */
    public int getSequenceNumber() {
        return sequenceNumber;
    }


    /**
     * Sets the sequenceNumber value for this UDFDefinedValue.
     * 
     * @param sequenceNumber
     */
    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }


    /**
     * Gets the udfValue value for this UDFDefinedValue.
     * 
     * @return udfValue   * The field
     *                     value.
     */
    public java.lang.String getUdfValue() {
        return udfValue;
    }


    /**
     * Sets the udfValue value for this UDFDefinedValue.
     * 
     * @param udfValue   * The field
     *                     value.
     */
    public void setUdfValue(java.lang.String udfValue) {
        this.udfValue = udfValue;
    }


    /**
     * Gets the elementDisplayName value for this UDFDefinedValue.
     * 
     * @return elementDisplayName   * The display name for the
     *                     UDF.
     */
    public java.lang.String getElementDisplayName() {
        return elementDisplayName;
    }


    /**
     * Sets the elementDisplayName value for this UDFDefinedValue.
     * 
     * @param elementDisplayName   * The display name for the
     *                     UDF.
     */
    public void setElementDisplayName(java.lang.String elementDisplayName) {
        this.elementDisplayName = elementDisplayName;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UDFDefinedValue)) return false;
        UDFDefinedValue other = (UDFDefinedValue) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.sequenceNumber == other.getSequenceNumber() &&
            ((this.udfValue==null && other.getUdfValue()==null) || 
             (this.udfValue!=null &&
              this.udfValue.equals(other.getUdfValue()))) &&
            ((this.elementDisplayName==null && other.getElementDisplayName()==null) || 
             (this.elementDisplayName!=null &&
              this.elementDisplayName.equals(other.getElementDisplayName())));
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
        _hashCode += getSequenceNumber();
        if (getUdfValue() != null) {
            _hashCode += getUdfValue().hashCode();
        }
        if (getElementDisplayName() != null) {
            _hashCode += getElementDisplayName().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UDFDefinedValue.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/common/schemas/udfmaster", "UDFDefinedValue"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sequenceNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/common/schemas/udfmaster", "SequenceNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("udfValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/common/schemas/udfmaster", "UdfValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("elementDisplayName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/common/schemas/udfmaster", "ElementDisplayName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
