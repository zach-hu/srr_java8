/**
 * PayData.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.documentmaster;


/**
 * This element holds details of the
 *                 media used for the payment.
 */
public class PayData  implements java.io.Serializable {
    private boolean required;

    /* Specifies whether the user
     *                         can change the media code or company bank
     * tag
     *                         during Input. */
    private boolean modifiable;

    /* The default value for the
     *                         media code or company bank tag used for the
     * payment. */
    private java.lang.String _default;

    public PayData() {
    }

    public PayData(
           boolean required,
           boolean modifiable,
           java.lang.String _default) {
           this.required = required;
           this.modifiable = modifiable;
           this._default = _default;
    }


    /**
     * Gets the required value for this PayData.
     * 
     * @return required
     */
    public boolean isRequired() {
        return required;
    }


    /**
     * Sets the required value for this PayData.
     * 
     * @param required
     */
    public void setRequired(boolean required) {
        this.required = required;
    }


    /**
     * Gets the modifiable value for this PayData.
     * 
     * @return modifiable   * Specifies whether the user
     *                         can change the media code or company bank
     * tag
     *                         during Input.
     */
    public boolean isModifiable() {
        return modifiable;
    }


    /**
     * Sets the modifiable value for this PayData.
     * 
     * @param modifiable   * Specifies whether the user
     *                         can change the media code or company bank
     * tag
     *                         during Input.
     */
    public void setModifiable(boolean modifiable) {
        this.modifiable = modifiable;
    }


    /**
     * Gets the _default value for this PayData.
     * 
     * @return _default   * The default value for the
     *                         media code or company bank tag used for the
     * payment.
     */
    public java.lang.String get_default() {
        return _default;
    }


    /**
     * Sets the _default value for this PayData.
     * 
     * @param _default   * The default value for the
     *                         media code or company bank tag used for the
     * payment.
     */
    public void set_default(java.lang.String _default) {
        this._default = _default;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PayData)) return false;
        PayData other = (PayData) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.required == other.isRequired() &&
            this.modifiable == other.isModifiable() &&
            ((this._default==null && other.get_default()==null) || 
             (this._default!=null &&
              this._default.equals(other.get_default())));
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
        _hashCode += (isRequired() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isModifiable() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (get_default() != null) {
            _hashCode += get_default().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PayData.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "PayData"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("required");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "Required"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("modifiable");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "Modifiable"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("_default");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "Default"));
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
