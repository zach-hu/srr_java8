/**
 * AnalysisLines.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.documentmaster;

public class AnalysisLines  implements java.io.Serializable {
    /* The
     *                         default sense of analysis lines on this type
     * of
     *                     document. */
    private java.lang.String defaultSense;

    /* The
     *                         presenter master that determines how the
     *                         information in the analysis lines is displayed
     * during Input. */
    private java.lang.String presenter;

    public AnalysisLines() {
    }

    public AnalysisLines(
           java.lang.String defaultSense,
           java.lang.String presenter) {
           this.defaultSense = defaultSense;
           this.presenter = presenter;
    }


    /**
     * Gets the defaultSense value for this AnalysisLines.
     * 
     * @return defaultSense   * The
     *                         default sense of analysis lines on this type
     * of
     *                     document.
     */
    public java.lang.String getDefaultSense() {
        return defaultSense;
    }


    /**
     * Sets the defaultSense value for this AnalysisLines.
     * 
     * @param defaultSense   * The
     *                         default sense of analysis lines on this type
     * of
     *                     document.
     */
    public void setDefaultSense(java.lang.String defaultSense) {
        this.defaultSense = defaultSense;
    }


    /**
     * Gets the presenter value for this AnalysisLines.
     * 
     * @return presenter   * The
     *                         presenter master that determines how the
     *                         information in the analysis lines is displayed
     * during Input.
     */
    public java.lang.String getPresenter() {
        return presenter;
    }


    /**
     * Sets the presenter value for this AnalysisLines.
     * 
     * @param presenter   * The
     *                         presenter master that determines how the
     *                         information in the analysis lines is displayed
     * during Input.
     */
    public void setPresenter(java.lang.String presenter) {
        this.presenter = presenter;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AnalysisLines)) return false;
        AnalysisLines other = (AnalysisLines) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.defaultSense==null && other.getDefaultSense()==null) || 
             (this.defaultSense!=null &&
              this.defaultSense.equals(other.getDefaultSense()))) &&
            ((this.presenter==null && other.getPresenter()==null) || 
             (this.presenter!=null &&
              this.presenter.equals(other.getPresenter())));
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
        if (getDefaultSense() != null) {
            _hashCode += getDefaultSense().hashCode();
        }
        if (getPresenter() != null) {
            _hashCode += getPresenter().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AnalysisLines.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "AnalysisLines"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("defaultSense");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "DefaultSense"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("presenter");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "Presenter"));
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
