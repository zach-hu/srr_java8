/**
 * ExtensionRef.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.common;


/**
 * This element holds details of an
 *             extension.
 */
public class ExtensionRef  implements java.io.Serializable {
    private java.lang.String code;

    /* The configuration
     *                         identification number, a system generated
     * number
     *                         that identifies the configuration
     *                     details. */
    private java.lang.Integer configurationID;

    public ExtensionRef() {
    }

    public ExtensionRef(
           java.lang.String code,
           java.lang.Integer configurationID) {
           this.code = code;
           this.configurationID = configurationID;
    }


    /**
     * Gets the code value for this ExtensionRef.
     * 
     * @return code
     */
    public java.lang.String getCode() {
        return code;
    }


    /**
     * Sets the code value for this ExtensionRef.
     * 
     * @param code
     */
    public void setCode(java.lang.String code) {
        this.code = code;
    }


    /**
     * Gets the configurationID value for this ExtensionRef.
     * 
     * @return configurationID   * The configuration
     *                         identification number, a system generated
     * number
     *                         that identifies the configuration
     *                     details.
     */
    public java.lang.Integer getConfigurationID() {
        return configurationID;
    }


    /**
     * Sets the configurationID value for this ExtensionRef.
     * 
     * @param configurationID   * The configuration
     *                         identification number, a system generated
     * number
     *                         that identifies the configuration
     *                     details.
     */
    public void setConfigurationID(java.lang.Integer configurationID) {
        this.configurationID = configurationID;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ExtensionRef)) return false;
        ExtensionRef other = (ExtensionRef) obj;
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
            ((this.configurationID==null && other.getConfigurationID()==null) || 
             (this.configurationID!=null &&
              this.configurationID.equals(other.getConfigurationID())));
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
        if (getConfigurationID() != null) {
            _hashCode += getConfigurationID().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ExtensionRef.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "ExtensionRef"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("code");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "Code"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("configurationID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "ConfigurationID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
