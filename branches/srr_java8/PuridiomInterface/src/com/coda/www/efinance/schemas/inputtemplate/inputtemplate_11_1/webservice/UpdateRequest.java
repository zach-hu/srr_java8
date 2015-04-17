/**
 * UpdateRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.inputtemplate.inputtemplate_11_1.webservice;

public class UpdateRequest  implements java.io.Serializable {
    private java.lang.String[] companies;

    /* Contains the information for
     *                             the input template master you want to
     * update. */
    private com.coda.www.efinance.schemas.inputtemplate.InputTemplate inputTemplate;

    public UpdateRequest() {
    }

    public UpdateRequest(
           java.lang.String[] companies,
           com.coda.www.efinance.schemas.inputtemplate.InputTemplate inputTemplate) {
           this.companies = companies;
           this.inputTemplate = inputTemplate;
    }


    /**
     * Gets the companies value for this UpdateRequest.
     * 
     * @return companies
     */
    public java.lang.String[] getCompanies() {
        return companies;
    }


    /**
     * Sets the companies value for this UpdateRequest.
     * 
     * @param companies
     */
    public void setCompanies(java.lang.String[] companies) {
        this.companies = companies;
    }


    /**
     * Gets the inputTemplate value for this UpdateRequest.
     * 
     * @return inputTemplate   * Contains the information for
     *                             the input template master you want to
     * update.
     */
    public com.coda.www.efinance.schemas.inputtemplate.InputTemplate getInputTemplate() {
        return inputTemplate;
    }


    /**
     * Sets the inputTemplate value for this UpdateRequest.
     * 
     * @param inputTemplate   * Contains the information for
     *                             the input template master you want to
     * update.
     */
    public void setInputTemplate(com.coda.www.efinance.schemas.inputtemplate.InputTemplate inputTemplate) {
        this.inputTemplate = inputTemplate;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UpdateRequest)) return false;
        UpdateRequest other = (UpdateRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.companies==null && other.getCompanies()==null) || 
             (this.companies!=null &&
              java.util.Arrays.equals(this.companies, other.getCompanies()))) &&
            ((this.inputTemplate==null && other.getInputTemplate()==null) || 
             (this.inputTemplate!=null &&
              this.inputTemplate.equals(other.getInputTemplate())));
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
        if (getCompanies() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCompanies());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCompanies(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getInputTemplate() != null) {
            _hashCode += getInputTemplate().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UpdateRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate/inputtemplate-11.1/webservice", ">UpdateRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("companies");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate/inputtemplate-11.1/webservice", "Companies"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeGeneralCode"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "Company"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inputTemplate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate/inputtemplate-11.1/webservice", "InputTemplate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "InputTemplate"));
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
