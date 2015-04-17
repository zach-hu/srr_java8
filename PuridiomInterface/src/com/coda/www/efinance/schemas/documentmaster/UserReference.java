/**
 * UserReference.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.documentmaster;


/**
 * This element holds information about
 *                 user reference 1.
 */
public class UserReference  implements java.io.Serializable {
    private boolean required;

    /* Specifies whether the user
     *                         can change the user reference during
     *                     Input. */
    private boolean modifiable;

    /* The
     *                         default text for the user
     *                     reference. */
    private java.lang.String _default;

    /* Specifies whether the type of
     *                         the user reference is literal text or a
     *                         vocabulary item. */
    private java.lang.String type;

    /* The
     *                         default vocabulary item for the user
     *                     reference. */
    private short vocab;

    public UserReference() {
    }

    public UserReference(
           boolean required,
           boolean modifiable,
           java.lang.String _default,
           java.lang.String type,
           short vocab) {
           this.required = required;
           this.modifiable = modifiable;
           this._default = _default;
           this.type = type;
           this.vocab = vocab;
    }


    /**
     * Gets the required value for this UserReference.
     * 
     * @return required
     */
    public boolean isRequired() {
        return required;
    }


    /**
     * Sets the required value for this UserReference.
     * 
     * @param required
     */
    public void setRequired(boolean required) {
        this.required = required;
    }


    /**
     * Gets the modifiable value for this UserReference.
     * 
     * @return modifiable   * Specifies whether the user
     *                         can change the user reference during
     *                     Input.
     */
    public boolean isModifiable() {
        return modifiable;
    }


    /**
     * Sets the modifiable value for this UserReference.
     * 
     * @param modifiable   * Specifies whether the user
     *                         can change the user reference during
     *                     Input.
     */
    public void setModifiable(boolean modifiable) {
        this.modifiable = modifiable;
    }


    /**
     * Gets the _default value for this UserReference.
     * 
     * @return _default   * The
     *                         default text for the user
     *                     reference.
     */
    public java.lang.String get_default() {
        return _default;
    }


    /**
     * Sets the _default value for this UserReference.
     * 
     * @param _default   * The
     *                         default text for the user
     *                     reference.
     */
    public void set_default(java.lang.String _default) {
        this._default = _default;
    }


    /**
     * Gets the type value for this UserReference.
     * 
     * @return type   * Specifies whether the type of
     *                         the user reference is literal text or a
     *                         vocabulary item.
     */
    public java.lang.String getType() {
        return type;
    }


    /**
     * Sets the type value for this UserReference.
     * 
     * @param type   * Specifies whether the type of
     *                         the user reference is literal text or a
     *                         vocabulary item.
     */
    public void setType(java.lang.String type) {
        this.type = type;
    }


    /**
     * Gets the vocab value for this UserReference.
     * 
     * @return vocab   * The
     *                         default vocabulary item for the user
     *                     reference.
     */
    public short getVocab() {
        return vocab;
    }


    /**
     * Sets the vocab value for this UserReference.
     * 
     * @param vocab   * The
     *                         default vocabulary item for the user
     *                     reference.
     */
    public void setVocab(short vocab) {
        this.vocab = vocab;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UserReference)) return false;
        UserReference other = (UserReference) obj;
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
              this._default.equals(other.get_default()))) &&
            ((this.type==null && other.getType()==null) || 
             (this.type!=null &&
              this.type.equals(other.getType()))) &&
            this.vocab == other.getVocab();
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
        if (getType() != null) {
            _hashCode += getType().hashCode();
        }
        _hashCode += getVocab();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UserReference.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "UserReference"));
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("type");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "Type"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("vocab");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "Vocab"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
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
