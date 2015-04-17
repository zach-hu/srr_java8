/**
 * SelectTemplateRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.inputext.input_11_2.webservice;

public class SelectTemplateRequest  implements java.io.Serializable {
    /* This element is a key
     *                             consisting of a company code and an input
     * template master code. */
    private com.coda.www.efinance.schemas.common.Key key;

    /* The code of the document
     *                             master you wish to use. This is only
     *                             relevant if the specified input template
     * master is linked to more than one document master. */
    private java.lang.String docCode;

    public SelectTemplateRequest() {
    }

    public SelectTemplateRequest(
           com.coda.www.efinance.schemas.common.Key key,
           java.lang.String docCode) {
           this.key = key;
           this.docCode = docCode;
    }


    /**
     * Gets the key value for this SelectTemplateRequest.
     * 
     * @return key   * This element is a key
     *                             consisting of a company code and an input
     * template master code.
     */
    public com.coda.www.efinance.schemas.common.Key getKey() {
        return key;
    }


    /**
     * Sets the key value for this SelectTemplateRequest.
     * 
     * @param key   * This element is a key
     *                             consisting of a company code and an input
     * template master code.
     */
    public void setKey(com.coda.www.efinance.schemas.common.Key key) {
        this.key = key;
    }


    /**
     * Gets the docCode value for this SelectTemplateRequest.
     * 
     * @return docCode   * The code of the document
     *                             master you wish to use. This is only
     *                             relevant if the specified input template
     * master is linked to more than one document master.
     */
    public java.lang.String getDocCode() {
        return docCode;
    }


    /**
     * Sets the docCode value for this SelectTemplateRequest.
     * 
     * @param docCode   * The code of the document
     *                             master you wish to use. This is only
     *                             relevant if the specified input template
     * master is linked to more than one document master.
     */
    public void setDocCode(java.lang.String docCode) {
        this.docCode = docCode;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SelectTemplateRequest)) return false;
        SelectTemplateRequest other = (SelectTemplateRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.key==null && other.getKey()==null) || 
             (this.key!=null &&
              this.key.equals(other.getKey()))) &&
            ((this.docCode==null && other.getDocCode()==null) || 
             (this.docCode!=null &&
              this.docCode.equals(other.getDocCode())));
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
        if (getKey() != null) {
            _hashCode += getKey().hashCode();
        }
        if (getDocCode() != null) {
            _hashCode += getDocCode().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SelectTemplateRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">SelectTemplateRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("key");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "Key"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "Key"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("docCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "DocCode"));
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
