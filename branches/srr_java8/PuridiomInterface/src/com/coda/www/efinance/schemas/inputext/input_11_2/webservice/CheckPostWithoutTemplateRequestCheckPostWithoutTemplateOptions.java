/**
 * CheckPostWithoutTemplateRequestCheckPostWithoutTemplateOptions.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.inputext.input_11_2.webservice;

public class CheckPostWithoutTemplateRequestCheckPostWithoutTemplateOptions  implements java.io.Serializable {
    private java.lang.String postto;  // attribute

    private boolean ignoresurplusdata;  // attribute

    public CheckPostWithoutTemplateRequestCheckPostWithoutTemplateOptions() {
    }

    public CheckPostWithoutTemplateRequestCheckPostWithoutTemplateOptions(
           java.lang.String postto,
           boolean ignoresurplusdata) {
           this.postto = postto;
           this.ignoresurplusdata = ignoresurplusdata;
    }


    /**
     * Gets the postto value for this CheckPostWithoutTemplateRequestCheckPostWithoutTemplateOptions.
     * 
     * @return postto
     */
    public java.lang.String getPostto() {
        return postto;
    }


    /**
     * Sets the postto value for this CheckPostWithoutTemplateRequestCheckPostWithoutTemplateOptions.
     * 
     * @param postto
     */
    public void setPostto(java.lang.String postto) {
        this.postto = postto;
    }


    /**
     * Gets the ignoresurplusdata value for this CheckPostWithoutTemplateRequestCheckPostWithoutTemplateOptions.
     * 
     * @return ignoresurplusdata
     */
    public boolean isIgnoresurplusdata() {
        return ignoresurplusdata;
    }


    /**
     * Sets the ignoresurplusdata value for this CheckPostWithoutTemplateRequestCheckPostWithoutTemplateOptions.
     * 
     * @param ignoresurplusdata
     */
    public void setIgnoresurplusdata(boolean ignoresurplusdata) {
        this.ignoresurplusdata = ignoresurplusdata;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CheckPostWithoutTemplateRequestCheckPostWithoutTemplateOptions)) return false;
        CheckPostWithoutTemplateRequestCheckPostWithoutTemplateOptions other = (CheckPostWithoutTemplateRequestCheckPostWithoutTemplateOptions) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.postto==null && other.getPostto()==null) || 
             (this.postto!=null &&
              this.postto.equals(other.getPostto()))) &&
            this.ignoresurplusdata == other.isIgnoresurplusdata();
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
        if (getPostto() != null) {
            _hashCode += getPostto().hashCode();
        }
        _hashCode += (isIgnoresurplusdata() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CheckPostWithoutTemplateRequestCheckPostWithoutTemplateOptions.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">>CheckPostWithoutTemplateRequest>CheckPostWithoutTemplateOptions"));
        org.apache.axis.description.AttributeDesc attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("postto");
        attrField.setXmlName(new javax.xml.namespace.QName("", "postto"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtDocPost"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("ignoresurplusdata");
        attrField.setXmlName(new javax.xml.namespace.QName("", "ignoresurplusdata"));
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
