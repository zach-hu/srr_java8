/**
 * ProtectedElements.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.documentmaster;


/**
 * This elements specifies whether the
 *                 elements in the account code are protected during
 *             Input.
 */
public class ProtectedElements  implements java.io.Serializable {
    private boolean protectElement1;

    /* Specifies that this element
     *                         is protected in Input. */
    private boolean protectElement2;

    /* Specifies that this element
     *                         is protected in Input. */
    private boolean protectElement3;

    /* Specifies that this element
     *                         is protected in Input. */
    private boolean protectElement4;

    /* Specifies that this element
     *                         is protected in Input. */
    private boolean protectElement5;

    /* Specifies that this element
     *                         is protected in Input. */
    private boolean protectElement6;

    /* Specifies that this element
     *                         is protected in Input. */
    private boolean protectElement7;

    /* Specifies that this element
     *                         is protected in Input. */
    private boolean protectElement8;

    public ProtectedElements() {
    }

    public ProtectedElements(
           boolean protectElement1,
           boolean protectElement2,
           boolean protectElement3,
           boolean protectElement4,
           boolean protectElement5,
           boolean protectElement6,
           boolean protectElement7,
           boolean protectElement8) {
           this.protectElement1 = protectElement1;
           this.protectElement2 = protectElement2;
           this.protectElement3 = protectElement3;
           this.protectElement4 = protectElement4;
           this.protectElement5 = protectElement5;
           this.protectElement6 = protectElement6;
           this.protectElement7 = protectElement7;
           this.protectElement8 = protectElement8;
    }


    /**
     * Gets the protectElement1 value for this ProtectedElements.
     * 
     * @return protectElement1
     */
    public boolean isProtectElement1() {
        return protectElement1;
    }


    /**
     * Sets the protectElement1 value for this ProtectedElements.
     * 
     * @param protectElement1
     */
    public void setProtectElement1(boolean protectElement1) {
        this.protectElement1 = protectElement1;
    }


    /**
     * Gets the protectElement2 value for this ProtectedElements.
     * 
     * @return protectElement2   * Specifies that this element
     *                         is protected in Input.
     */
    public boolean isProtectElement2() {
        return protectElement2;
    }


    /**
     * Sets the protectElement2 value for this ProtectedElements.
     * 
     * @param protectElement2   * Specifies that this element
     *                         is protected in Input.
     */
    public void setProtectElement2(boolean protectElement2) {
        this.protectElement2 = protectElement2;
    }


    /**
     * Gets the protectElement3 value for this ProtectedElements.
     * 
     * @return protectElement3   * Specifies that this element
     *                         is protected in Input.
     */
    public boolean isProtectElement3() {
        return protectElement3;
    }


    /**
     * Sets the protectElement3 value for this ProtectedElements.
     * 
     * @param protectElement3   * Specifies that this element
     *                         is protected in Input.
     */
    public void setProtectElement3(boolean protectElement3) {
        this.protectElement3 = protectElement3;
    }


    /**
     * Gets the protectElement4 value for this ProtectedElements.
     * 
     * @return protectElement4   * Specifies that this element
     *                         is protected in Input.
     */
    public boolean isProtectElement4() {
        return protectElement4;
    }


    /**
     * Sets the protectElement4 value for this ProtectedElements.
     * 
     * @param protectElement4   * Specifies that this element
     *                         is protected in Input.
     */
    public void setProtectElement4(boolean protectElement4) {
        this.protectElement4 = protectElement4;
    }


    /**
     * Gets the protectElement5 value for this ProtectedElements.
     * 
     * @return protectElement5   * Specifies that this element
     *                         is protected in Input.
     */
    public boolean isProtectElement5() {
        return protectElement5;
    }


    /**
     * Sets the protectElement5 value for this ProtectedElements.
     * 
     * @param protectElement5   * Specifies that this element
     *                         is protected in Input.
     */
    public void setProtectElement5(boolean protectElement5) {
        this.protectElement5 = protectElement5;
    }


    /**
     * Gets the protectElement6 value for this ProtectedElements.
     * 
     * @return protectElement6   * Specifies that this element
     *                         is protected in Input.
     */
    public boolean isProtectElement6() {
        return protectElement6;
    }


    /**
     * Sets the protectElement6 value for this ProtectedElements.
     * 
     * @param protectElement6   * Specifies that this element
     *                         is protected in Input.
     */
    public void setProtectElement6(boolean protectElement6) {
        this.protectElement6 = protectElement6;
    }


    /**
     * Gets the protectElement7 value for this ProtectedElements.
     * 
     * @return protectElement7   * Specifies that this element
     *                         is protected in Input.
     */
    public boolean isProtectElement7() {
        return protectElement7;
    }


    /**
     * Sets the protectElement7 value for this ProtectedElements.
     * 
     * @param protectElement7   * Specifies that this element
     *                         is protected in Input.
     */
    public void setProtectElement7(boolean protectElement7) {
        this.protectElement7 = protectElement7;
    }


    /**
     * Gets the protectElement8 value for this ProtectedElements.
     * 
     * @return protectElement8   * Specifies that this element
     *                         is protected in Input.
     */
    public boolean isProtectElement8() {
        return protectElement8;
    }


    /**
     * Sets the protectElement8 value for this ProtectedElements.
     * 
     * @param protectElement8   * Specifies that this element
     *                         is protected in Input.
     */
    public void setProtectElement8(boolean protectElement8) {
        this.protectElement8 = protectElement8;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ProtectedElements)) return false;
        ProtectedElements other = (ProtectedElements) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.protectElement1 == other.isProtectElement1() &&
            this.protectElement2 == other.isProtectElement2() &&
            this.protectElement3 == other.isProtectElement3() &&
            this.protectElement4 == other.isProtectElement4() &&
            this.protectElement5 == other.isProtectElement5() &&
            this.protectElement6 == other.isProtectElement6() &&
            this.protectElement7 == other.isProtectElement7() &&
            this.protectElement8 == other.isProtectElement8();
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
        _hashCode += (isProtectElement1() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isProtectElement2() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isProtectElement3() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isProtectElement4() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isProtectElement5() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isProtectElement6() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isProtectElement7() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isProtectElement8() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ProtectedElements.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "ProtectedElements"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("protectElement1");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "ProtectElement1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("protectElement2");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "ProtectElement2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("protectElement3");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "ProtectElement3"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("protectElement4");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "ProtectElement4"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("protectElement5");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "ProtectElement5"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("protectElement6");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "ProtectElement6"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("protectElement7");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "ProtectElement7"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("protectElement8");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "ProtectElement8"));
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
