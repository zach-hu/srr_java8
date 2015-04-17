/**
 * ElmReviewItem.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.inputext;


/**
 * This element contains the total value
 *                 (in document, home and dual currencies) for a balancing
 * element in the document.
 */
public class ElmReviewItem  implements java.io.Serializable {
    private java.lang.String balElmCode;

    /* The total value for this
     *                         balancing element in document
     *                     currency. */
    private java.math.BigDecimal docValue;

    /* The
     *                         total value for this balancing element in
     * home
     *                     currency. */
    private java.math.BigDecimal homeValue;

    /* The total value for this
     *                         balancing element in dual
     *                     currency. */
    private java.math.BigDecimal dualValue;

    public ElmReviewItem() {
    }

    public ElmReviewItem(
           java.lang.String balElmCode,
           java.math.BigDecimal docValue,
           java.math.BigDecimal homeValue,
           java.math.BigDecimal dualValue) {
           this.balElmCode = balElmCode;
           this.docValue = docValue;
           this.homeValue = homeValue;
           this.dualValue = dualValue;
    }


    /**
     * Gets the balElmCode value for this ElmReviewItem.
     * 
     * @return balElmCode
     */
    public java.lang.String getBalElmCode() {
        return balElmCode;
    }


    /**
     * Sets the balElmCode value for this ElmReviewItem.
     * 
     * @param balElmCode
     */
    public void setBalElmCode(java.lang.String balElmCode) {
        this.balElmCode = balElmCode;
    }


    /**
     * Gets the docValue value for this ElmReviewItem.
     * 
     * @return docValue   * The total value for this
     *                         balancing element in document
     *                     currency.
     */
    public java.math.BigDecimal getDocValue() {
        return docValue;
    }


    /**
     * Sets the docValue value for this ElmReviewItem.
     * 
     * @param docValue   * The total value for this
     *                         balancing element in document
     *                     currency.
     */
    public void setDocValue(java.math.BigDecimal docValue) {
        this.docValue = docValue;
    }


    /**
     * Gets the homeValue value for this ElmReviewItem.
     * 
     * @return homeValue   * The
     *                         total value for this balancing element in
     * home
     *                     currency.
     */
    public java.math.BigDecimal getHomeValue() {
        return homeValue;
    }


    /**
     * Sets the homeValue value for this ElmReviewItem.
     * 
     * @param homeValue   * The
     *                         total value for this balancing element in
     * home
     *                     currency.
     */
    public void setHomeValue(java.math.BigDecimal homeValue) {
        this.homeValue = homeValue;
    }


    /**
     * Gets the dualValue value for this ElmReviewItem.
     * 
     * @return dualValue   * The total value for this
     *                         balancing element in dual
     *                     currency.
     */
    public java.math.BigDecimal getDualValue() {
        return dualValue;
    }


    /**
     * Sets the dualValue value for this ElmReviewItem.
     * 
     * @param dualValue   * The total value for this
     *                         balancing element in dual
     *                     currency.
     */
    public void setDualValue(java.math.BigDecimal dualValue) {
        this.dualValue = dualValue;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ElmReviewItem)) return false;
        ElmReviewItem other = (ElmReviewItem) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.balElmCode==null && other.getBalElmCode()==null) || 
             (this.balElmCode!=null &&
              this.balElmCode.equals(other.getBalElmCode()))) &&
            ((this.docValue==null && other.getDocValue()==null) || 
             (this.docValue!=null &&
              this.docValue.equals(other.getDocValue()))) &&
            ((this.homeValue==null && other.getHomeValue()==null) || 
             (this.homeValue!=null &&
              this.homeValue.equals(other.getHomeValue()))) &&
            ((this.dualValue==null && other.getDualValue()==null) || 
             (this.dualValue!=null &&
              this.dualValue.equals(other.getDualValue())));
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
        if (getBalElmCode() != null) {
            _hashCode += getBalElmCode().hashCode();
        }
        if (getDocValue() != null) {
            _hashCode += getDocValue().hashCode();
        }
        if (getHomeValue() != null) {
            _hashCode += getHomeValue().hashCode();
        }
        if (getDualValue() != null) {
            _hashCode += getDualValue().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ElmReviewItem.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "ElmReviewItem"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("balElmCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "BalElmCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("docValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "DocValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("homeValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "HomeValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dualValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "DualValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
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
