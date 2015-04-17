/**
 * ElmFullKeyWithTemporaryElmFilter.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.elementmaster;


/**
 * This is an element key that
 *                 incorporates temporary supplier
 *             identifiers.
 */
public class ElmFullKeyWithTemporaryElmFilter  implements java.io.Serializable {
    private com.coda.www.efinance.schemas.elementmaster.ElmFullKey fullKey;

    /* Specifies whether to use the
     *                         temporary supplier filter. */
    private boolean usingFilter;

    /* The
     *                         temporary supplier filter. */
    private com.coda.www.efinance.schemas.elementmaster.ElmTemporaryElmFilter temporaryElmFilter;

    public ElmFullKeyWithTemporaryElmFilter() {
    }

    public ElmFullKeyWithTemporaryElmFilter(
           com.coda.www.efinance.schemas.elementmaster.ElmFullKey fullKey,
           boolean usingFilter,
           com.coda.www.efinance.schemas.elementmaster.ElmTemporaryElmFilter temporaryElmFilter) {
           this.fullKey = fullKey;
           this.usingFilter = usingFilter;
           this.temporaryElmFilter = temporaryElmFilter;
    }


    /**
     * Gets the fullKey value for this ElmFullKeyWithTemporaryElmFilter.
     * 
     * @return fullKey
     */
    public com.coda.www.efinance.schemas.elementmaster.ElmFullKey getFullKey() {
        return fullKey;
    }


    /**
     * Sets the fullKey value for this ElmFullKeyWithTemporaryElmFilter.
     * 
     * @param fullKey
     */
    public void setFullKey(com.coda.www.efinance.schemas.elementmaster.ElmFullKey fullKey) {
        this.fullKey = fullKey;
    }


    /**
     * Gets the usingFilter value for this ElmFullKeyWithTemporaryElmFilter.
     * 
     * @return usingFilter   * Specifies whether to use the
     *                         temporary supplier filter.
     */
    public boolean isUsingFilter() {
        return usingFilter;
    }


    /**
     * Sets the usingFilter value for this ElmFullKeyWithTemporaryElmFilter.
     * 
     * @param usingFilter   * Specifies whether to use the
     *                         temporary supplier filter.
     */
    public void setUsingFilter(boolean usingFilter) {
        this.usingFilter = usingFilter;
    }


    /**
     * Gets the temporaryElmFilter value for this ElmFullKeyWithTemporaryElmFilter.
     * 
     * @return temporaryElmFilter   * The
     *                         temporary supplier filter.
     */
    public com.coda.www.efinance.schemas.elementmaster.ElmTemporaryElmFilter getTemporaryElmFilter() {
        return temporaryElmFilter;
    }


    /**
     * Sets the temporaryElmFilter value for this ElmFullKeyWithTemporaryElmFilter.
     * 
     * @param temporaryElmFilter   * The
     *                         temporary supplier filter.
     */
    public void setTemporaryElmFilter(com.coda.www.efinance.schemas.elementmaster.ElmTemporaryElmFilter temporaryElmFilter) {
        this.temporaryElmFilter = temporaryElmFilter;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ElmFullKeyWithTemporaryElmFilter)) return false;
        ElmFullKeyWithTemporaryElmFilter other = (ElmFullKeyWithTemporaryElmFilter) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.fullKey==null && other.getFullKey()==null) || 
             (this.fullKey!=null &&
              this.fullKey.equals(other.getFullKey()))) &&
            this.usingFilter == other.isUsingFilter() &&
            ((this.temporaryElmFilter==null && other.getTemporaryElmFilter()==null) || 
             (this.temporaryElmFilter!=null &&
              this.temporaryElmFilter.equals(other.getTemporaryElmFilter())));
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
        if (getFullKey() != null) {
            _hashCode += getFullKey().hashCode();
        }
        _hashCode += (isUsingFilter() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getTemporaryElmFilter() != null) {
            _hashCode += getTemporaryElmFilter().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ElmFullKeyWithTemporaryElmFilter.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "elmFullKeyWithTemporaryElmFilter"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fullKey");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "FullKey"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "elmFullKey"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("usingFilter");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "UsingFilter"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("temporaryElmFilter");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "TemporaryElmFilter"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "elmTemporaryElmFilter"));
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
