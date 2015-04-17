/**
 * SalesInvoiceUDFRowEx.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.elementmaster;

public class SalesInvoiceUDFRowEx  extends com.coda.www.efinance.schemas.elementmaster.SalesInvoiceUDFRow  implements java.io.Serializable {
    private java.lang.String rowLabel;

    public SalesInvoiceUDFRowEx() {
    }

    public SalesInvoiceUDFRowEx(
           java.lang.String UDFText,
           java.lang.String UDFNumber,
           java.lang.String UDFDate,
           java.lang.String UDFElm,
           java.lang.String rowLabel) {
        super(
            UDFText,
            UDFNumber,
            UDFDate,
            UDFElm);
        this.rowLabel = rowLabel;
    }


    /**
     * Gets the rowLabel value for this SalesInvoiceUDFRowEx.
     * 
     * @return rowLabel
     */
    public java.lang.String getRowLabel() {
        return rowLabel;
    }


    /**
     * Sets the rowLabel value for this SalesInvoiceUDFRowEx.
     * 
     * @param rowLabel
     */
    public void setRowLabel(java.lang.String rowLabel) {
        this.rowLabel = rowLabel;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SalesInvoiceUDFRowEx)) return false;
        SalesInvoiceUDFRowEx other = (SalesInvoiceUDFRowEx) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.rowLabel==null && other.getRowLabel()==null) || 
             (this.rowLabel!=null &&
              this.rowLabel.equals(other.getRowLabel())));
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
        if (getRowLabel() != null) {
            _hashCode += getRowLabel().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SalesInvoiceUDFRowEx.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "SalesInvoiceUDFRowEx"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rowLabel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "RowLabel"));
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
