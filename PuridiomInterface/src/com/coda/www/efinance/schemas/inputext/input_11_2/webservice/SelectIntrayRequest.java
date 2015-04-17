/**
 * SelectIntrayRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.inputext.input_11_2.webservice;

public class SelectIntrayRequest  implements java.io.Serializable {
    /* Contains criteria for
     *                             selecting documents on the Intray. */
    private com.coda.www.efinance.schemas.inputext.IntrayFilter intrayFilter;

    public SelectIntrayRequest() {
    }

    public SelectIntrayRequest(
           com.coda.www.efinance.schemas.inputext.IntrayFilter intrayFilter) {
           this.intrayFilter = intrayFilter;
    }


    /**
     * Gets the intrayFilter value for this SelectIntrayRequest.
     * 
     * @return intrayFilter   * Contains criteria for
     *                             selecting documents on the Intray.
     */
    public com.coda.www.efinance.schemas.inputext.IntrayFilter getIntrayFilter() {
        return intrayFilter;
    }


    /**
     * Sets the intrayFilter value for this SelectIntrayRequest.
     * 
     * @param intrayFilter   * Contains criteria for
     *                             selecting documents on the Intray.
     */
    public void setIntrayFilter(com.coda.www.efinance.schemas.inputext.IntrayFilter intrayFilter) {
        this.intrayFilter = intrayFilter;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SelectIntrayRequest)) return false;
        SelectIntrayRequest other = (SelectIntrayRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.intrayFilter==null && other.getIntrayFilter()==null) || 
             (this.intrayFilter!=null &&
              this.intrayFilter.equals(other.getIntrayFilter())));
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
        if (getIntrayFilter() != null) {
            _hashCode += getIntrayFilter().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SelectIntrayRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">SelectIntrayRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("intrayFilter");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "IntrayFilter"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "IntrayFilter"));
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
