/**
 * Reason.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.common;


/**
 * This element holds an explanation of
 *                 why the operation failed.
 */
public class Reason  implements java.io.Serializable {
    private com.coda.www.efinance.schemas.common.ReasonText[] text;

    /* Identifies the element that
     *                         has caused the problem. */
    private java.lang.String[] path;

    /* Provides more information on
     *                         the cause of the problem when appropriate.
     * For
     *                         example, when the problem element contains
     * an
     *                         account code, this field specifies the element
     * level. */
    private java.lang.String[] hint;

    public Reason() {
    }

    public Reason(
           com.coda.www.efinance.schemas.common.ReasonText[] text,
           java.lang.String[] path,
           java.lang.String[] hint) {
           this.text = text;
           this.path = path;
           this.hint = hint;
    }


    /**
     * Gets the text value for this Reason.
     * 
     * @return text
     */
    public com.coda.www.efinance.schemas.common.ReasonText[] getText() {
        return text;
    }


    /**
     * Sets the text value for this Reason.
     * 
     * @param text
     */
    public void setText(com.coda.www.efinance.schemas.common.ReasonText[] text) {
        this.text = text;
    }

    public com.coda.www.efinance.schemas.common.ReasonText getText(int i) {
        return this.text[i];
    }

    public void setText(int i, com.coda.www.efinance.schemas.common.ReasonText _value) {
        this.text[i] = _value;
    }


    /**
     * Gets the path value for this Reason.
     * 
     * @return path   * Identifies the element that
     *                         has caused the problem.
     */
    public java.lang.String[] getPath() {
        return path;
    }


    /**
     * Sets the path value for this Reason.
     * 
     * @param path   * Identifies the element that
     *                         has caused the problem.
     */
    public void setPath(java.lang.String[] path) {
        this.path = path;
    }

    public java.lang.String getPath(int i) {
        return this.path[i];
    }

    public void setPath(int i, java.lang.String _value) {
        this.path[i] = _value;
    }


    /**
     * Gets the hint value for this Reason.
     * 
     * @return hint   * Provides more information on
     *                         the cause of the problem when appropriate.
     * For
     *                         example, when the problem element contains
     * an
     *                         account code, this field specifies the element
     * level.
     */
    public java.lang.String[] getHint() {
        return hint;
    }


    /**
     * Sets the hint value for this Reason.
     * 
     * @param hint   * Provides more information on
     *                         the cause of the problem when appropriate.
     * For
     *                         example, when the problem element contains
     * an
     *                         account code, this field specifies the element
     * level.
     */
    public void setHint(java.lang.String[] hint) {
        this.hint = hint;
    }

    public java.lang.String getHint(int i) {
        return this.hint[i];
    }

    public void setHint(int i, java.lang.String _value) {
        this.hint[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Reason)) return false;
        Reason other = (Reason) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.text==null && other.getText()==null) || 
             (this.text!=null &&
              java.util.Arrays.equals(this.text, other.getText()))) &&
            ((this.path==null && other.getPath()==null) || 
             (this.path!=null &&
              java.util.Arrays.equals(this.path, other.getPath()))) &&
            ((this.hint==null && other.getHint()==null) || 
             (this.hint!=null &&
              java.util.Arrays.equals(this.hint, other.getHint())));
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
        if (getText() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getText());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getText(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getPath() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPath());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPath(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getHint() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getHint());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getHint(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Reason.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "Reason"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("text");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "Text"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "ReasonText"));
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("path");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "Path"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hint");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "Hint"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
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
