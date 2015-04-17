/**
 * ElementFinderNumberRange.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.elementmaster;


/**
 * This element holds
 *                 parameters for you to specify the number range to
 *             search.
 */
public class ElementFinderNumberRange  implements java.io.Serializable {
    private short begin;

    /* The
     *                         maximum number of records (element masters)
     * to
     *                     retrieve. */
    private short extent;

    public ElementFinderNumberRange() {
    }

    public ElementFinderNumberRange(
           short begin,
           short extent) {
           this.begin = begin;
           this.extent = extent;
    }


    /**
     * Gets the begin value for this ElementFinderNumberRange.
     * 
     * @return begin
     */
    public short getBegin() {
        return begin;
    }


    /**
     * Sets the begin value for this ElementFinderNumberRange.
     * 
     * @param begin
     */
    public void setBegin(short begin) {
        this.begin = begin;
    }


    /**
     * Gets the extent value for this ElementFinderNumberRange.
     * 
     * @return extent   * The
     *                         maximum number of records (element masters)
     * to
     *                     retrieve.
     */
    public short getExtent() {
        return extent;
    }


    /**
     * Sets the extent value for this ElementFinderNumberRange.
     * 
     * @param extent   * The
     *                         maximum number of records (element masters)
     * to
     *                     retrieve.
     */
    public void setExtent(short extent) {
        this.extent = extent;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ElementFinderNumberRange)) return false;
        ElementFinderNumberRange other = (ElementFinderNumberRange) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.begin == other.getBegin() &&
            this.extent == other.getExtent();
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
        _hashCode += getBegin();
        _hashCode += getExtent();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ElementFinderNumberRange.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "ElementFinderNumberRange"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("begin");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Begin"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("extent");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Extent"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
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
