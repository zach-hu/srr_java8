/**
 * AddRequestAddOptions.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice;

public class AddRequestAddOptions  implements java.io.Serializable {
    private java.lang.String templatecmpcode;  // attribute

    private java.lang.String templatecode;  // attribute

    private boolean inhibitinitialisationfromtemplate;  // attribute

    private boolean autogeneratefromtemplate;  // attribute

    public AddRequestAddOptions() {
    }

    public AddRequestAddOptions(
           java.lang.String templatecmpcode,
           java.lang.String templatecode,
           boolean inhibitinitialisationfromtemplate,
           boolean autogeneratefromtemplate) {
           this.templatecmpcode = templatecmpcode;
           this.templatecode = templatecode;
           this.inhibitinitialisationfromtemplate = inhibitinitialisationfromtemplate;
           this.autogeneratefromtemplate = autogeneratefromtemplate;
    }


    /**
     * Gets the templatecmpcode value for this AddRequestAddOptions.
     * 
     * @return templatecmpcode
     */
    public java.lang.String getTemplatecmpcode() {
        return templatecmpcode;
    }


    /**
     * Sets the templatecmpcode value for this AddRequestAddOptions.
     * 
     * @param templatecmpcode
     */
    public void setTemplatecmpcode(java.lang.String templatecmpcode) {
        this.templatecmpcode = templatecmpcode;
    }


    /**
     * Gets the templatecode value for this AddRequestAddOptions.
     * 
     * @return templatecode
     */
    public java.lang.String getTemplatecode() {
        return templatecode;
    }


    /**
     * Sets the templatecode value for this AddRequestAddOptions.
     * 
     * @param templatecode
     */
    public void setTemplatecode(java.lang.String templatecode) {
        this.templatecode = templatecode;
    }


    /**
     * Gets the inhibitinitialisationfromtemplate value for this AddRequestAddOptions.
     * 
     * @return inhibitinitialisationfromtemplate
     */
    public boolean isInhibitinitialisationfromtemplate() {
        return inhibitinitialisationfromtemplate;
    }


    /**
     * Sets the inhibitinitialisationfromtemplate value for this AddRequestAddOptions.
     * 
     * @param inhibitinitialisationfromtemplate
     */
    public void setInhibitinitialisationfromtemplate(boolean inhibitinitialisationfromtemplate) {
        this.inhibitinitialisationfromtemplate = inhibitinitialisationfromtemplate;
    }


    /**
     * Gets the autogeneratefromtemplate value for this AddRequestAddOptions.
     * 
     * @return autogeneratefromtemplate
     */
    public boolean isAutogeneratefromtemplate() {
        return autogeneratefromtemplate;
    }


    /**
     * Sets the autogeneratefromtemplate value for this AddRequestAddOptions.
     * 
     * @param autogeneratefromtemplate
     */
    public void setAutogeneratefromtemplate(boolean autogeneratefromtemplate) {
        this.autogeneratefromtemplate = autogeneratefromtemplate;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AddRequestAddOptions)) return false;
        AddRequestAddOptions other = (AddRequestAddOptions) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.templatecmpcode==null && other.getTemplatecmpcode()==null) || 
             (this.templatecmpcode!=null &&
              this.templatecmpcode.equals(other.getTemplatecmpcode()))) &&
            ((this.templatecode==null && other.getTemplatecode()==null) || 
             (this.templatecode!=null &&
              this.templatecode.equals(other.getTemplatecode()))) &&
            this.inhibitinitialisationfromtemplate == other.isInhibitinitialisationfromtemplate() &&
            this.autogeneratefromtemplate == other.isAutogeneratefromtemplate();
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
        if (getTemplatecmpcode() != null) {
            _hashCode += getTemplatecmpcode().hashCode();
        }
        if (getTemplatecode() != null) {
            _hashCode += getTemplatecode().hashCode();
        }
        _hashCode += (isInhibitinitialisationfromtemplate() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isAutogeneratefromtemplate() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AddRequestAddOptions.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", ">>AddRequest>AddOptions"));
        org.apache.axis.description.AttributeDesc attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("templatecmpcode");
        attrField.setXmlName(new javax.xml.namespace.QName("", "templatecmpcode"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeGeneralCode"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("templatecode");
        attrField.setXmlName(new javax.xml.namespace.QName("", "templatecode"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeGeneralCode"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("inhibitinitialisationfromtemplate");
        attrField.setXmlName(new javax.xml.namespace.QName("", "inhibitinitialisationfromtemplate"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("autogeneratefromtemplate");
        attrField.setXmlName(new javax.xml.namespace.QName("", "autogeneratefromtemplate"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        typeDesc.addFieldDesc(attrField);
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
