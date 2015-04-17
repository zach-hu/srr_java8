/**
 * ElementFilterItem.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.elementfiltermaster;


/**
 * A row in the list
 *                 of criteria used by the element filter
 *             master.
 */
public class ElementFilterItem  implements java.io.Serializable {
    private java.lang.String logical;

    /* The
     *                         number of opening brackets to insert at this
     * point (for grouping
     *                     purposes). */
    private java.lang.String LBracket;

    /* A
     *                         number identifying an element level or an
     *                         element group level. */
    private java.lang.String vocab;

    /* A
     *                         number identifying the relational operator
     * that
     *                         specifies the kind of comparison made between
     * element codes matching the Vocab item, and the
     *                         supplied Value. */
    private java.lang.String function;

    /* A
     *                         pattern value that is compared to element
     * codes
     *                         matching the Vocab item. You can use wildcards
     * only with the LIKE and NOTLIKE
     *                     functions. */
    private java.lang.String value;

    /* The
     *                         number of closing brackets to insert at this
     * point (for grouping
     *                     purposes). */
    private java.lang.String TBracket;

    public ElementFilterItem() {
    }

    public ElementFilterItem(
           java.lang.String logical,
           java.lang.String LBracket,
           java.lang.String vocab,
           java.lang.String function,
           java.lang.String value,
           java.lang.String TBracket) {
           this.logical = logical;
           this.LBracket = LBracket;
           this.vocab = vocab;
           this.function = function;
           this.value = value;
           this.TBracket = TBracket;
    }


    /**
     * Gets the logical value for this ElementFilterItem.
     * 
     * @return logical
     */
    public java.lang.String getLogical() {
        return logical;
    }


    /**
     * Sets the logical value for this ElementFilterItem.
     * 
     * @param logical
     */
    public void setLogical(java.lang.String logical) {
        this.logical = logical;
    }


    /**
     * Gets the LBracket value for this ElementFilterItem.
     * 
     * @return LBracket   * The
     *                         number of opening brackets to insert at this
     * point (for grouping
     *                     purposes).
     */
    public java.lang.String getLBracket() {
        return LBracket;
    }


    /**
     * Sets the LBracket value for this ElementFilterItem.
     * 
     * @param LBracket   * The
     *                         number of opening brackets to insert at this
     * point (for grouping
     *                     purposes).
     */
    public void setLBracket(java.lang.String LBracket) {
        this.LBracket = LBracket;
    }


    /**
     * Gets the vocab value for this ElementFilterItem.
     * 
     * @return vocab   * A
     *                         number identifying an element level or an
     *                         element group level.
     */
    public java.lang.String getVocab() {
        return vocab;
    }


    /**
     * Sets the vocab value for this ElementFilterItem.
     * 
     * @param vocab   * A
     *                         number identifying an element level or an
     *                         element group level.
     */
    public void setVocab(java.lang.String vocab) {
        this.vocab = vocab;
    }


    /**
     * Gets the function value for this ElementFilterItem.
     * 
     * @return function   * A
     *                         number identifying the relational operator
     * that
     *                         specifies the kind of comparison made between
     * element codes matching the Vocab item, and the
     *                         supplied Value.
     */
    public java.lang.String getFunction() {
        return function;
    }


    /**
     * Sets the function value for this ElementFilterItem.
     * 
     * @param function   * A
     *                         number identifying the relational operator
     * that
     *                         specifies the kind of comparison made between
     * element codes matching the Vocab item, and the
     *                         supplied Value.
     */
    public void setFunction(java.lang.String function) {
        this.function = function;
    }


    /**
     * Gets the value value for this ElementFilterItem.
     * 
     * @return value   * A
     *                         pattern value that is compared to element
     * codes
     *                         matching the Vocab item. You can use wildcards
     * only with the LIKE and NOTLIKE
     *                     functions.
     */
    public java.lang.String getValue() {
        return value;
    }


    /**
     * Sets the value value for this ElementFilterItem.
     * 
     * @param value   * A
     *                         pattern value that is compared to element
     * codes
     *                         matching the Vocab item. You can use wildcards
     * only with the LIKE and NOTLIKE
     *                     functions.
     */
    public void setValue(java.lang.String value) {
        this.value = value;
    }


    /**
     * Gets the TBracket value for this ElementFilterItem.
     * 
     * @return TBracket   * The
     *                         number of closing brackets to insert at this
     * point (for grouping
     *                     purposes).
     */
    public java.lang.String getTBracket() {
        return TBracket;
    }


    /**
     * Sets the TBracket value for this ElementFilterItem.
     * 
     * @param TBracket   * The
     *                         number of closing brackets to insert at this
     * point (for grouping
     *                     purposes).
     */
    public void setTBracket(java.lang.String TBracket) {
        this.TBracket = TBracket;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ElementFilterItem)) return false;
        ElementFilterItem other = (ElementFilterItem) obj;
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
            ((this.LBracket==null && other.getLBracket()==null) || 
             (this.LBracket!=null &&
              this.LBracket.equals(other.getLBracket()))) &&
            ((this.vocab==null && other.getVocab()==null) || 
             (this.vocab!=null &&
              this.vocab.equals(other.getVocab()))) &&
            ((this.function==null && other.getFunction()==null) || 
             (this.function!=null &&
              this.function.equals(other.getFunction()))) &&
            ((this.value==null && other.getValue()==null) || 
             (this.value!=null &&
              this.value.equals(other.getValue()))) &&
            ((this.TBracket==null && other.getTBracket()==null) || 
             (this.TBracket!=null &&
              this.TBracket.equals(other.getTBracket())));
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
        if (getLBracket() != null) {
            _hashCode += getLBracket().hashCode();
        }
        if (getVocab() != null) {
            _hashCode += getVocab().hashCode();
        }
        if (getFunction() != null) {
            _hashCode += getFunction().hashCode();
        }
        if (getValue() != null) {
            _hashCode += getValue().hashCode();
        }
        if (getTBracket() != null) {
            _hashCode += getTBracket().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ElementFilterItem.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementfiltermaster", "ElementFilterItem"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("logical");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementfiltermaster", "Logical"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("LBracket");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementfiltermaster", "LBracket"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("vocab");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementfiltermaster", "Vocab"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("function");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementfiltermaster", "Function"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("value");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementfiltermaster", "Value"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TBracket");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementfiltermaster", "TBracket"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum"));
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
