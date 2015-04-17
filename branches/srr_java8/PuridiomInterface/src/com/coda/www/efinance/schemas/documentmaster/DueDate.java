/**
 * DueDate.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.documentmaster;

public class DueDate  implements java.io.Serializable {
    /* The
     *                         offset rule for calculating the due
     *                     date. */
    private java.lang.String rule;

    /* Specifies whether the
     *                         calculated due date can be changed during
     *                     Input. */
    private java.lang.String modifiable;

    /* Specifies whether the
     *                         calculated due date applies to every line
     * on the
     *                     document. */
    private boolean documentWide;

    public DueDate() {
    }

    public DueDate(
           java.lang.String rule,
           java.lang.String modifiable,
           boolean documentWide) {
           this.rule = rule;
           this.modifiable = modifiable;
           this.documentWide = documentWide;
    }


    /**
     * Gets the rule value for this DueDate.
     * 
     * @return rule   * The
     *                         offset rule for calculating the due
     *                     date.
     */
    public java.lang.String getRule() {
        return rule;
    }


    /**
     * Sets the rule value for this DueDate.
     * 
     * @param rule   * The
     *                         offset rule for calculating the due
     *                     date.
     */
    public void setRule(java.lang.String rule) {
        this.rule = rule;
    }


    /**
     * Gets the modifiable value for this DueDate.
     * 
     * @return modifiable   * Specifies whether the
     *                         calculated due date can be changed during
     *                     Input.
     */
    public java.lang.String getModifiable() {
        return modifiable;
    }


    /**
     * Sets the modifiable value for this DueDate.
     * 
     * @param modifiable   * Specifies whether the
     *                         calculated due date can be changed during
     *                     Input.
     */
    public void setModifiable(java.lang.String modifiable) {
        this.modifiable = modifiable;
    }


    /**
     * Gets the documentWide value for this DueDate.
     * 
     * @return documentWide   * Specifies whether the
     *                         calculated due date applies to every line
     * on the
     *                     document.
     */
    public boolean isDocumentWide() {
        return documentWide;
    }


    /**
     * Sets the documentWide value for this DueDate.
     * 
     * @param documentWide   * Specifies whether the
     *                         calculated due date applies to every line
     * on the
     *                     document.
     */
    public void setDocumentWide(boolean documentWide) {
        this.documentWide = documentWide;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DueDate)) return false;
        DueDate other = (DueDate) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.rule==null && other.getRule()==null) || 
             (this.rule!=null &&
              this.rule.equals(other.getRule()))) &&
            ((this.modifiable==null && other.getModifiable()==null) || 
             (this.modifiable!=null &&
              this.modifiable.equals(other.getModifiable()))) &&
            this.documentWide == other.isDocumentWide();
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
        if (getRule() != null) {
            _hashCode += getRule().hashCode();
        }
        if (getModifiable() != null) {
            _hashCode += getModifiable().hashCode();
        }
        _hashCode += (isDocumentWide() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DueDate.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "DueDate"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rule");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "Rule"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("modifiable");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "Modifiable"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documentWide");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "DocumentWide"));
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
