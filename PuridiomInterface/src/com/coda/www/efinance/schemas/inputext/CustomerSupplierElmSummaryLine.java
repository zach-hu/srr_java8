/**
 * CustomerSupplierElmSummaryLine.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.inputext;


/**
 * This element contains summary
 *                 information about the customer/supplier element on
 * the
 *                 specified document line.
 */
public class CustomerSupplierElmSummaryLine  implements java.io.Serializable {
    private int lineNumber;

    /* The code of the
     *                         customer/supplier element. */
    private java.lang.String elmCode;

    /* The
     *                         element level of the customer/supplier
     *                     element. */
    private short elmLevel;

    /* The customer/supplier
     *                         element's short name. */
    private java.lang.String shortName;

    /* The
     *                         value (in document currency) of this document
     * line. */
    private java.math.BigDecimal docValue;

    /* The sense of this line
     *                         (debit, credit or none). */
    private java.lang.String lineSense;

    public CustomerSupplierElmSummaryLine() {
    }

    public CustomerSupplierElmSummaryLine(
           int lineNumber,
           java.lang.String elmCode,
           short elmLevel,
           java.lang.String shortName,
           java.math.BigDecimal docValue,
           java.lang.String lineSense) {
           this.lineNumber = lineNumber;
           this.elmCode = elmCode;
           this.elmLevel = elmLevel;
           this.shortName = shortName;
           this.docValue = docValue;
           this.lineSense = lineSense;
    }


    /**
     * Gets the lineNumber value for this CustomerSupplierElmSummaryLine.
     * 
     * @return lineNumber
     */
    public int getLineNumber() {
        return lineNumber;
    }


    /**
     * Sets the lineNumber value for this CustomerSupplierElmSummaryLine.
     * 
     * @param lineNumber
     */
    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }


    /**
     * Gets the elmCode value for this CustomerSupplierElmSummaryLine.
     * 
     * @return elmCode   * The code of the
     *                         customer/supplier element.
     */
    public java.lang.String getElmCode() {
        return elmCode;
    }


    /**
     * Sets the elmCode value for this CustomerSupplierElmSummaryLine.
     * 
     * @param elmCode   * The code of the
     *                         customer/supplier element.
     */
    public void setElmCode(java.lang.String elmCode) {
        this.elmCode = elmCode;
    }


    /**
     * Gets the elmLevel value for this CustomerSupplierElmSummaryLine.
     * 
     * @return elmLevel   * The
     *                         element level of the customer/supplier
     *                     element.
     */
    public short getElmLevel() {
        return elmLevel;
    }


    /**
     * Sets the elmLevel value for this CustomerSupplierElmSummaryLine.
     * 
     * @param elmLevel   * The
     *                         element level of the customer/supplier
     *                     element.
     */
    public void setElmLevel(short elmLevel) {
        this.elmLevel = elmLevel;
    }


    /**
     * Gets the shortName value for this CustomerSupplierElmSummaryLine.
     * 
     * @return shortName   * The customer/supplier
     *                         element's short name.
     */
    public java.lang.String getShortName() {
        return shortName;
    }


    /**
     * Sets the shortName value for this CustomerSupplierElmSummaryLine.
     * 
     * @param shortName   * The customer/supplier
     *                         element's short name.
     */
    public void setShortName(java.lang.String shortName) {
        this.shortName = shortName;
    }


    /**
     * Gets the docValue value for this CustomerSupplierElmSummaryLine.
     * 
     * @return docValue   * The
     *                         value (in document currency) of this document
     * line.
     */
    public java.math.BigDecimal getDocValue() {
        return docValue;
    }


    /**
     * Sets the docValue value for this CustomerSupplierElmSummaryLine.
     * 
     * @param docValue   * The
     *                         value (in document currency) of this document
     * line.
     */
    public void setDocValue(java.math.BigDecimal docValue) {
        this.docValue = docValue;
    }


    /**
     * Gets the lineSense value for this CustomerSupplierElmSummaryLine.
     * 
     * @return lineSense   * The sense of this line
     *                         (debit, credit or none).
     */
    public java.lang.String getLineSense() {
        return lineSense;
    }


    /**
     * Sets the lineSense value for this CustomerSupplierElmSummaryLine.
     * 
     * @param lineSense   * The sense of this line
     *                         (debit, credit or none).
     */
    public void setLineSense(java.lang.String lineSense) {
        this.lineSense = lineSense;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CustomerSupplierElmSummaryLine)) return false;
        CustomerSupplierElmSummaryLine other = (CustomerSupplierElmSummaryLine) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.lineNumber == other.getLineNumber() &&
            ((this.elmCode==null && other.getElmCode()==null) || 
             (this.elmCode!=null &&
              this.elmCode.equals(other.getElmCode()))) &&
            this.elmLevel == other.getElmLevel() &&
            ((this.shortName==null && other.getShortName()==null) || 
             (this.shortName!=null &&
              this.shortName.equals(other.getShortName()))) &&
            ((this.docValue==null && other.getDocValue()==null) || 
             (this.docValue!=null &&
              this.docValue.equals(other.getDocValue()))) &&
            ((this.lineSense==null && other.getLineSense()==null) || 
             (this.lineSense!=null &&
              this.lineSense.equals(other.getLineSense())));
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
        _hashCode += getLineNumber();
        if (getElmCode() != null) {
            _hashCode += getElmCode().hashCode();
        }
        _hashCode += getElmLevel();
        if (getShortName() != null) {
            _hashCode += getShortName().hashCode();
        }
        if (getDocValue() != null) {
            _hashCode += getDocValue().hashCode();
        }
        if (getLineSense() != null) {
            _hashCode += getLineSense().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CustomerSupplierElmSummaryLine.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "CustomerSupplierElmSummaryLine"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lineNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "LineNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("elmCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "ElmCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("elmLevel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "ElmLevel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shortName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "ShortName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("docValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "DocValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lineSense");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "LineSense"));
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
