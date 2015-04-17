/**
 * SalesInvoiceUDFRow.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.elementmaster;


/**
 * This element contains details of a
 *                 user-defined field.
 */
public class SalesInvoiceUDFRow  implements java.io.Serializable {
    private java.lang.String UDFText;

    /* A
     *                         user-defined number field. */
    private java.lang.String UDFNumber;

    /* A user-defined date
     *                     field. */
    private java.lang.String UDFDate;

    /* A
     *                         user-defined element field. */
    private java.lang.String UDFElm;

    public SalesInvoiceUDFRow() {
    }

    public SalesInvoiceUDFRow(
           java.lang.String UDFText,
           java.lang.String UDFNumber,
           java.lang.String UDFDate,
           java.lang.String UDFElm) {
           this.UDFText = UDFText;
           this.UDFNumber = UDFNumber;
           this.UDFDate = UDFDate;
           this.UDFElm = UDFElm;
    }


    /**
     * Gets the UDFText value for this SalesInvoiceUDFRow.
     * 
     * @return UDFText
     */
    public java.lang.String getUDFText() {
        return UDFText;
    }


    /**
     * Sets the UDFText value for this SalesInvoiceUDFRow.
     * 
     * @param UDFText
     */
    public void setUDFText(java.lang.String UDFText) {
        this.UDFText = UDFText;
    }


    /**
     * Gets the UDFNumber value for this SalesInvoiceUDFRow.
     * 
     * @return UDFNumber   * A
     *                         user-defined number field.
     */
    public java.lang.String getUDFNumber() {
        return UDFNumber;
    }


    /**
     * Sets the UDFNumber value for this SalesInvoiceUDFRow.
     * 
     * @param UDFNumber   * A
     *                         user-defined number field.
     */
    public void setUDFNumber(java.lang.String UDFNumber) {
        this.UDFNumber = UDFNumber;
    }


    /**
     * Gets the UDFDate value for this SalesInvoiceUDFRow.
     * 
     * @return UDFDate   * A user-defined date
     *                     field.
     */
    public java.lang.String getUDFDate() {
        return UDFDate;
    }


    /**
     * Sets the UDFDate value for this SalesInvoiceUDFRow.
     * 
     * @param UDFDate   * A user-defined date
     *                     field.
     */
    public void setUDFDate(java.lang.String UDFDate) {
        this.UDFDate = UDFDate;
    }


    /**
     * Gets the UDFElm value for this SalesInvoiceUDFRow.
     * 
     * @return UDFElm   * A
     *                         user-defined element field.
     */
    public java.lang.String getUDFElm() {
        return UDFElm;
    }


    /**
     * Sets the UDFElm value for this SalesInvoiceUDFRow.
     * 
     * @param UDFElm   * A
     *                         user-defined element field.
     */
    public void setUDFElm(java.lang.String UDFElm) {
        this.UDFElm = UDFElm;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SalesInvoiceUDFRow)) return false;
        SalesInvoiceUDFRow other = (SalesInvoiceUDFRow) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.UDFText==null && other.getUDFText()==null) || 
             (this.UDFText!=null &&
              this.UDFText.equals(other.getUDFText()))) &&
            ((this.UDFNumber==null && other.getUDFNumber()==null) || 
             (this.UDFNumber!=null &&
              this.UDFNumber.equals(other.getUDFNumber()))) &&
            ((this.UDFDate==null && other.getUDFDate()==null) || 
             (this.UDFDate!=null &&
              this.UDFDate.equals(other.getUDFDate()))) &&
            ((this.UDFElm==null && other.getUDFElm()==null) || 
             (this.UDFElm!=null &&
              this.UDFElm.equals(other.getUDFElm())));
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
        if (getUDFText() != null) {
            _hashCode += getUDFText().hashCode();
        }
        if (getUDFNumber() != null) {
            _hashCode += getUDFNumber().hashCode();
        }
        if (getUDFDate() != null) {
            _hashCode += getUDFDate().hashCode();
        }
        if (getUDFElm() != null) {
            _hashCode += getUDFElm().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SalesInvoiceUDFRow.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "SalesInvoiceUDFRow"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UDFText");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "UDFText"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UDFNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "UDFNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UDFDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "UDFDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UDFElm");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "UDFElm"));
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
