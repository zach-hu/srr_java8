/**
 * LastTransactions.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.elementmaster;


/**
 * Holds details of the last
 *                 transactions posted to this element.
 */
public class LastTransactions  implements java.io.Serializable {
    private com.coda.www.efinance.schemas.elementmaster.LastTransaction invoice;

    /* Holds
     *                         details of the last payment transaction posted
     * to this element. */
    private com.coda.www.efinance.schemas.elementmaster.LastTransaction payment;

    public LastTransactions() {
    }

    public LastTransactions(
           com.coda.www.efinance.schemas.elementmaster.LastTransaction invoice,
           com.coda.www.efinance.schemas.elementmaster.LastTransaction payment) {
           this.invoice = invoice;
           this.payment = payment;
    }


    /**
     * Gets the invoice value for this LastTransactions.
     * 
     * @return invoice
     */
    public com.coda.www.efinance.schemas.elementmaster.LastTransaction getInvoice() {
        return invoice;
    }


    /**
     * Sets the invoice value for this LastTransactions.
     * 
     * @param invoice
     */
    public void setInvoice(com.coda.www.efinance.schemas.elementmaster.LastTransaction invoice) {
        this.invoice = invoice;
    }


    /**
     * Gets the payment value for this LastTransactions.
     * 
     * @return payment   * Holds
     *                         details of the last payment transaction posted
     * to this element.
     */
    public com.coda.www.efinance.schemas.elementmaster.LastTransaction getPayment() {
        return payment;
    }


    /**
     * Sets the payment value for this LastTransactions.
     * 
     * @param payment   * Holds
     *                         details of the last payment transaction posted
     * to this element.
     */
    public void setPayment(com.coda.www.efinance.schemas.elementmaster.LastTransaction payment) {
        this.payment = payment;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LastTransactions)) return false;
        LastTransactions other = (LastTransactions) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.invoice==null && other.getInvoice()==null) || 
             (this.invoice!=null &&
              this.invoice.equals(other.getInvoice()))) &&
            ((this.payment==null && other.getPayment()==null) || 
             (this.payment!=null &&
              this.payment.equals(other.getPayment())));
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
        if (getInvoice() != null) {
            _hashCode += getInvoice().hashCode();
        }
        if (getPayment() != null) {
            _hashCode += getPayment().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LastTransactions.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "LastTransactions"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("invoice");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Invoice"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "LastTransaction"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("payment");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Payment"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "LastTransaction"));
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
