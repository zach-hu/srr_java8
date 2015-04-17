/**
 * RuleElement.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.elementmaster;


/**
 * Holds details of an element
 *             rule.
 */
public class RuleElement  implements java.io.Serializable {
    private java.lang.String logical;

    /* A count of the number of
     *                         opening brackets on the
     *                     line. */
    private short openBrackets;

    /* A value signifying which
     *                         vocabulary item is present on the
     *                     line. */
    private java.lang.String vocabulary;

    /* A value signifying which
     *                         function is present on the
     *                     line. */
    private java.lang.String function;

    /* The
     *                         criterion value with which the vocabulary
     * item
     *                         is compared. */
    private java.lang.String value;

    /* A count of the number of
     *                         closing brackets on the
     *                     line. */
    private short closeBrackets;

    public RuleElement() {
    }

    public RuleElement(
           java.lang.String logical,
           short openBrackets,
           java.lang.String vocabulary,
           java.lang.String function,
           java.lang.String value,
           short closeBrackets) {
           this.logical = logical;
           this.openBrackets = openBrackets;
           this.vocabulary = vocabulary;
           this.function = function;
           this.value = value;
           this.closeBrackets = closeBrackets;
    }


    /**
     * Gets the logical value for this RuleElement.
     * 
     * @return logical
     */
    public java.lang.String getLogical() {
        return logical;
    }


    /**
     * Sets the logical value for this RuleElement.
     * 
     * @param logical
     */
    public void setLogical(java.lang.String logical) {
        this.logical = logical;
    }


    /**
     * Gets the openBrackets value for this RuleElement.
     * 
     * @return openBrackets   * A count of the number of
     *                         opening brackets on the
     *                     line.
     */
    public short getOpenBrackets() {
        return openBrackets;
    }


    /**
     * Sets the openBrackets value for this RuleElement.
     * 
     * @param openBrackets   * A count of the number of
     *                         opening brackets on the
     *                     line.
     */
    public void setOpenBrackets(short openBrackets) {
        this.openBrackets = openBrackets;
    }


    /**
     * Gets the vocabulary value for this RuleElement.
     * 
     * @return vocabulary   * A value signifying which
     *                         vocabulary item is present on the
     *                     line.
     */
    public java.lang.String getVocabulary() {
        return vocabulary;
    }


    /**
     * Sets the vocabulary value for this RuleElement.
     * 
     * @param vocabulary   * A value signifying which
     *                         vocabulary item is present on the
     *                     line.
     */
    public void setVocabulary(java.lang.String vocabulary) {
        this.vocabulary = vocabulary;
    }


    /**
     * Gets the function value for this RuleElement.
     * 
     * @return function   * A value signifying which
     *                         function is present on the
     *                     line.
     */
    public java.lang.String getFunction() {
        return function;
    }


    /**
     * Sets the function value for this RuleElement.
     * 
     * @param function   * A value signifying which
     *                         function is present on the
     *                     line.
     */
    public void setFunction(java.lang.String function) {
        this.function = function;
    }


    /**
     * Gets the value value for this RuleElement.
     * 
     * @return value   * The
     *                         criterion value with which the vocabulary
     * item
     *                         is compared.
     */
    public java.lang.String getValue() {
        return value;
    }


    /**
     * Sets the value value for this RuleElement.
     * 
     * @param value   * The
     *                         criterion value with which the vocabulary
     * item
     *                         is compared.
     */
    public void setValue(java.lang.String value) {
        this.value = value;
    }


    /**
     * Gets the closeBrackets value for this RuleElement.
     * 
     * @return closeBrackets   * A count of the number of
     *                         closing brackets on the
     *                     line.
     */
    public short getCloseBrackets() {
        return closeBrackets;
    }


    /**
     * Sets the closeBrackets value for this RuleElement.
     * 
     * @param closeBrackets   * A count of the number of
     *                         closing brackets on the
     *                     line.
     */
    public void setCloseBrackets(short closeBrackets) {
        this.closeBrackets = closeBrackets;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RuleElement)) return false;
        RuleElement other = (RuleElement) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.logical==null && other.getLogical()==null) || 
             (this.logical!=null &&
              this.logical.equals(other.getLogical()))) &&
            this.openBrackets == other.getOpenBrackets() &&
            ((this.vocabulary==null && other.getVocabulary()==null) || 
             (this.vocabulary!=null &&
              this.vocabulary.equals(other.getVocabulary()))) &&
            ((this.function==null && other.getFunction()==null) || 
             (this.function!=null &&
              this.function.equals(other.getFunction()))) &&
            ((this.value==null && other.getValue()==null) || 
             (this.value!=null &&
              this.value.equals(other.getValue()))) &&
            this.closeBrackets == other.getCloseBrackets();
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
        if (getLogical() != null) {
            _hashCode += getLogical().hashCode();
        }
        _hashCode += getOpenBrackets();
        if (getVocabulary() != null) {
            _hashCode += getVocabulary().hashCode();
        }
        if (getFunction() != null) {
            _hashCode += getFunction().hashCode();
        }
        if (getValue() != null) {
            _hashCode += getValue().hashCode();
        }
        _hashCode += getCloseBrackets();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RuleElement.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "RuleElement"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("logical");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Logical"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("openBrackets");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "OpenBrackets"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("vocabulary");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Vocabulary"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("function");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Function"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("value");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Value"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("closeBrackets");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "CloseBrackets"));
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
