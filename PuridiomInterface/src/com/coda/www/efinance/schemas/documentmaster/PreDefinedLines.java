/**
 * PreDefinedLines.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.documentmaster;


/**
 * This element holds pre-defined line
 *             settings.
 */
public class PreDefinedLines  implements java.io.Serializable {
    private boolean enabled;

    /* The
     *                         maximum number of lines the document may
     *                     contain. */
    private short maximum;

    /* Specifies whether users can
     *                         add new lines to a document during Input.
     * If
     *                         not, only the pre-defined lines are allowed
     * on
     *                         the document. */
    private boolean allowAdditionalLines;

    public PreDefinedLines() {
    }

    public PreDefinedLines(
           boolean enabled,
           short maximum,
           boolean allowAdditionalLines) {
           this.enabled = enabled;
           this.maximum = maximum;
           this.allowAdditionalLines = allowAdditionalLines;
    }


    /**
     * Gets the enabled value for this PreDefinedLines.
     * 
     * @return enabled
     */
    public boolean isEnabled() {
        return enabled;
    }


    /**
     * Sets the enabled value for this PreDefinedLines.
     * 
     * @param enabled
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


    /**
     * Gets the maximum value for this PreDefinedLines.
     * 
     * @return maximum   * The
     *                         maximum number of lines the document may
     *                     contain.
     */
    public short getMaximum() {
        return maximum;
    }


    /**
     * Sets the maximum value for this PreDefinedLines.
     * 
     * @param maximum   * The
     *                         maximum number of lines the document may
     *                     contain.
     */
    public void setMaximum(short maximum) {
        this.maximum = maximum;
    }


    /**
     * Gets the allowAdditionalLines value for this PreDefinedLines.
     * 
     * @return allowAdditionalLines   * Specifies whether users can
     *                         add new lines to a document during Input.
     * If
     *                         not, only the pre-defined lines are allowed
     * on
     *                         the document.
     */
    public boolean isAllowAdditionalLines() {
        return allowAdditionalLines;
    }


    /**
     * Sets the allowAdditionalLines value for this PreDefinedLines.
     * 
     * @param allowAdditionalLines   * Specifies whether users can
     *                         add new lines to a document during Input.
     * If
     *                         not, only the pre-defined lines are allowed
     * on
     *                         the document.
     */
    public void setAllowAdditionalLines(boolean allowAdditionalLines) {
        this.allowAdditionalLines = allowAdditionalLines;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PreDefinedLines)) return false;
        PreDefinedLines other = (PreDefinedLines) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.enabled == other.isEnabled() &&
            this.maximum == other.getMaximum() &&
            this.allowAdditionalLines == other.isAllowAdditionalLines();
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
        _hashCode += (isEnabled() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += getMaximum();
        _hashCode += (isAllowAdditionalLines() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PreDefinedLines.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "PreDefinedLines"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("enabled");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "Enabled"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("maximum");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "Maximum"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("allowAdditionalLines");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "AllowAdditionalLines"));
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
