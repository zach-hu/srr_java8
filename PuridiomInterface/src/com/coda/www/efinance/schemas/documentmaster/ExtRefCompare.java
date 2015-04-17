/**
 * ExtRefCompare.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.documentmaster;


/**
 * This element holds information about
 *                 which external references the current value should
 * be
 *                 compared with when checking for
 *             duplicates.
 */
public class ExtRefCompare  implements java.io.Serializable {
    private boolean reference1;

    /* Specifies whether the value
     *                         of the current external reference should be
     * compared with that of external reference 2 when
     *                         checking for duplicates. */
    private boolean reference2;

    /* Specifies whether the value
     *                         of the current external reference should be
     * compared with that of external reference 3 when
     *                         checking for duplicates. */
    private boolean reference3;

    /* Specifies whether the value
     *                         of the current external reference should be
     * compared with that of external reference 4 when
     *                         checking for duplicates. */
    private boolean reference4;

    /* Specifies whether the value
     *                         of the current external reference should be
     * compared with that of external reference 5 when
     *                         checking for duplicates. */
    private boolean reference5;

    /* Specifies whether the value
     *                         of the current external reference should be
     * compared with that of external reference 6 when
     *                         checking for duplicates. */
    private boolean reference6;

    public ExtRefCompare() {
    }

    public ExtRefCompare(
           boolean reference1,
           boolean reference2,
           boolean reference3,
           boolean reference4,
           boolean reference5,
           boolean reference6) {
           this.reference1 = reference1;
           this.reference2 = reference2;
           this.reference3 = reference3;
           this.reference4 = reference4;
           this.reference5 = reference5;
           this.reference6 = reference6;
    }


    /**
     * Gets the reference1 value for this ExtRefCompare.
     * 
     * @return reference1
     */
    public boolean isReference1() {
        return reference1;
    }


    /**
     * Sets the reference1 value for this ExtRefCompare.
     * 
     * @param reference1
     */
    public void setReference1(boolean reference1) {
        this.reference1 = reference1;
    }


    /**
     * Gets the reference2 value for this ExtRefCompare.
     * 
     * @return reference2   * Specifies whether the value
     *                         of the current external reference should be
     * compared with that of external reference 2 when
     *                         checking for duplicates.
     */
    public boolean isReference2() {
        return reference2;
    }


    /**
     * Sets the reference2 value for this ExtRefCompare.
     * 
     * @param reference2   * Specifies whether the value
     *                         of the current external reference should be
     * compared with that of external reference 2 when
     *                         checking for duplicates.
     */
    public void setReference2(boolean reference2) {
        this.reference2 = reference2;
    }


    /**
     * Gets the reference3 value for this ExtRefCompare.
     * 
     * @return reference3   * Specifies whether the value
     *                         of the current external reference should be
     * compared with that of external reference 3 when
     *                         checking for duplicates.
     */
    public boolean isReference3() {
        return reference3;
    }


    /**
     * Sets the reference3 value for this ExtRefCompare.
     * 
     * @param reference3   * Specifies whether the value
     *                         of the current external reference should be
     * compared with that of external reference 3 when
     *                         checking for duplicates.
     */
    public void setReference3(boolean reference3) {
        this.reference3 = reference3;
    }


    /**
     * Gets the reference4 value for this ExtRefCompare.
     * 
     * @return reference4   * Specifies whether the value
     *                         of the current external reference should be
     * compared with that of external reference 4 when
     *                         checking for duplicates.
     */
    public boolean isReference4() {
        return reference4;
    }


    /**
     * Sets the reference4 value for this ExtRefCompare.
     * 
     * @param reference4   * Specifies whether the value
     *                         of the current external reference should be
     * compared with that of external reference 4 when
     *                         checking for duplicates.
     */
    public void setReference4(boolean reference4) {
        this.reference4 = reference4;
    }


    /**
     * Gets the reference5 value for this ExtRefCompare.
     * 
     * @return reference5   * Specifies whether the value
     *                         of the current external reference should be
     * compared with that of external reference 5 when
     *                         checking for duplicates.
     */
    public boolean isReference5() {
        return reference5;
    }


    /**
     * Sets the reference5 value for this ExtRefCompare.
     * 
     * @param reference5   * Specifies whether the value
     *                         of the current external reference should be
     * compared with that of external reference 5 when
     *                         checking for duplicates.
     */
    public void setReference5(boolean reference5) {
        this.reference5 = reference5;
    }


    /**
     * Gets the reference6 value for this ExtRefCompare.
     * 
     * @return reference6   * Specifies whether the value
     *                         of the current external reference should be
     * compared with that of external reference 6 when
     *                         checking for duplicates.
     */
    public boolean isReference6() {
        return reference6;
    }


    /**
     * Sets the reference6 value for this ExtRefCompare.
     * 
     * @param reference6   * Specifies whether the value
     *                         of the current external reference should be
     * compared with that of external reference 6 when
     *                         checking for duplicates.
     */
    public void setReference6(boolean reference6) {
        this.reference6 = reference6;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ExtRefCompare)) return false;
        ExtRefCompare other = (ExtRefCompare) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.reference1 == other.isReference1() &&
            this.reference2 == other.isReference2() &&
            this.reference3 == other.isReference3() &&
            this.reference4 == other.isReference4() &&
            this.reference5 == other.isReference5() &&
            this.reference6 == other.isReference6();
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
        _hashCode += (isReference1() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isReference2() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isReference3() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isReference4() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isReference5() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isReference6() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ExtRefCompare.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "ExtRefCompare"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reference1");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "Reference1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reference2");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "Reference2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reference3");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "Reference3"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reference4");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "Reference4"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reference5");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "Reference5"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reference6");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "Reference6"));
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
