/**
 * ElmQuantities.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.transaction;


/**
 * This element holds the quantity
 *                 information for the element at level
 *             1.
 */
public class ElmQuantities  implements java.io.Serializable {
    private java.math.BigDecimal quantity1;

    /* The
     *                         amount for quantity 2. */
    private java.math.BigDecimal quantity2;

    /* The amount for quantity
     *                     3. */
    private java.math.BigDecimal quantity3;

    /* The
     *                         amount for quantity 4. */
    private java.math.BigDecimal quantity4;

    public ElmQuantities() {
    }

    public ElmQuantities(
           java.math.BigDecimal quantity1,
           java.math.BigDecimal quantity2,
           java.math.BigDecimal quantity3,
           java.math.BigDecimal quantity4) {
           this.quantity1 = quantity1;
           this.quantity2 = quantity2;
           this.quantity3 = quantity3;
           this.quantity4 = quantity4;
    }


    /**
     * Gets the quantity1 value for this ElmQuantities.
     * 
     * @return quantity1
     */
    public java.math.BigDecimal getQuantity1() {
        return quantity1;
    }


    /**
     * Sets the quantity1 value for this ElmQuantities.
     * 
     * @param quantity1
     */
    public void setQuantity1(java.math.BigDecimal quantity1) {
        this.quantity1 = quantity1;
    }


    /**
     * Gets the quantity2 value for this ElmQuantities.
     * 
     * @return quantity2   * The
     *                         amount for quantity 2.
     */
    public java.math.BigDecimal getQuantity2() {
        return quantity2;
    }


    /**
     * Sets the quantity2 value for this ElmQuantities.
     * 
     * @param quantity2   * The
     *                         amount for quantity 2.
     */
    public void setQuantity2(java.math.BigDecimal quantity2) {
        this.quantity2 = quantity2;
    }


    /**
     * Gets the quantity3 value for this ElmQuantities.
     * 
     * @return quantity3   * The amount for quantity
     *                     3.
     */
    public java.math.BigDecimal getQuantity3() {
        return quantity3;
    }


    /**
     * Sets the quantity3 value for this ElmQuantities.
     * 
     * @param quantity3   * The amount for quantity
     *                     3.
     */
    public void setQuantity3(java.math.BigDecimal quantity3) {
        this.quantity3 = quantity3;
    }


    /**
     * Gets the quantity4 value for this ElmQuantities.
     * 
     * @return quantity4   * The
     *                         amount for quantity 4.
     */
    public java.math.BigDecimal getQuantity4() {
        return quantity4;
    }


    /**
     * Sets the quantity4 value for this ElmQuantities.
     * 
     * @param quantity4   * The
     *                         amount for quantity 4.
     */
    public void setQuantity4(java.math.BigDecimal quantity4) {
        this.quantity4 = quantity4;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ElmQuantities)) return false;
        ElmQuantities other = (ElmQuantities) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.quantity1==null && other.getQuantity1()==null) || 
             (this.quantity1!=null &&
              this.quantity1.equals(other.getQuantity1()))) &&
            ((this.quantity2==null && other.getQuantity2()==null) || 
             (this.quantity2!=null &&
              this.quantity2.equals(other.getQuantity2()))) &&
            ((this.quantity3==null && other.getQuantity3()==null) || 
             (this.quantity3!=null &&
              this.quantity3.equals(other.getQuantity3()))) &&
            ((this.quantity4==null && other.getQuantity4()==null) || 
             (this.quantity4!=null &&
              this.quantity4.equals(other.getQuantity4())));
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
        if (getQuantity1() != null) {
            _hashCode += getQuantity1().hashCode();
        }
        if (getQuantity2() != null) {
            _hashCode += getQuantity2().hashCode();
        }
        if (getQuantity3() != null) {
            _hashCode += getQuantity3().hashCode();
        }
        if (getQuantity4() != null) {
            _hashCode += getQuantity4().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ElmQuantities.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "ElmQuantities"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("quantity1");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Quantity1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("quantity2");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Quantity2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("quantity3");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Quantity3"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("quantity4");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Quantity4"));
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
