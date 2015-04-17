/**
 * ElementFilter.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.inputtemplate;


/**
 * Contains an element filter for one
 *                 element level in the account code on this document
 * line.
 */
public class ElementFilter  implements java.io.Serializable {
    private java.lang.String code;

    /* The element level to which
     *                         this filter applies. */
    private short level;

    /* Set to
     *                         TRUE to force the user to specify an element
     * in
     *                         the filtered list; when FALSE, the user may
     * specify any valid element regardless of whether
     *                         or not it is in the filtered
     *                     list. */
    private boolean limitToFilter;

    public ElementFilter() {
    }

    public ElementFilter(
           java.lang.String code,
           short level,
           boolean limitToFilter) {
           this.code = code;
           this.level = level;
           this.limitToFilter = limitToFilter;
    }


    /**
     * Gets the code value for this ElementFilter.
     * 
     * @return code
     */
    public java.lang.String getCode() {
        return code;
    }


    /**
     * Sets the code value for this ElementFilter.
     * 
     * @param code
     */
    public void setCode(java.lang.String code) {
        this.code = code;
    }


    /**
     * Gets the level value for this ElementFilter.
     * 
     * @return level   * The element level to which
     *                         this filter applies.
     */
    public short getLevel() {
        return level;
    }


    /**
     * Sets the level value for this ElementFilter.
     * 
     * @param level   * The element level to which
     *                         this filter applies.
     */
    public void setLevel(short level) {
        this.level = level;
    }


    /**
     * Gets the limitToFilter value for this ElementFilter.
     * 
     * @return limitToFilter   * Set to
     *                         TRUE to force the user to specify an element
     * in
     *                         the filtered list; when FALSE, the user may
     * specify any valid element regardless of whether
     *                         or not it is in the filtered
     *                     list.
     */
    public boolean isLimitToFilter() {
        return limitToFilter;
    }


    /**
     * Sets the limitToFilter value for this ElementFilter.
     * 
     * @param limitToFilter   * Set to
     *                         TRUE to force the user to specify an element
     * in
     *                         the filtered list; when FALSE, the user may
     * specify any valid element regardless of whether
     *                         or not it is in the filtered
     *                     list.
     */
    public void setLimitToFilter(boolean limitToFilter) {
        this.limitToFilter = limitToFilter;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ElementFilter)) return false;
        ElementFilter other = (ElementFilter) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.code==null && other.getCode()==null) || 
             (this.code!=null &&
              this.code.equals(other.getCode()))) &&
            this.level == other.getLevel() &&
            this.limitToFilter == other.isLimitToFilter();
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
        if (getCode() != null) {
            _hashCode += getCode().hashCode();
        }
        _hashCode += getLevel();
        _hashCode += (isLimitToFilter() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ElementFilter.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "ElementFilter"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("code");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "Code"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("level");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "Level"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("limitToFilter");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "LimitToFilter"));
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
