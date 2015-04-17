/**
 * GenerateBalancingLinesResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.inputext.input_11_2.webservice;

public class GenerateBalancingLinesResponse  implements java.io.Serializable {
    /* Contains the transaction
     *                             details, including the balancing lines. */
    private com.coda.www.efinance.schemas.transaction.Transaction transaction;

    public GenerateBalancingLinesResponse() {
    }

    public GenerateBalancingLinesResponse(
           com.coda.www.efinance.schemas.transaction.Transaction transaction) {
           this.transaction = transaction;
    }


    /**
     * Gets the transaction value for this GenerateBalancingLinesResponse.
     * 
     * @return transaction   * Contains the transaction
     *                             details, including the balancing lines.
     */
    public com.coda.www.efinance.schemas.transaction.Transaction getTransaction() {
        return transaction;
    }


    /**
     * Sets the transaction value for this GenerateBalancingLinesResponse.
     * 
     * @param transaction   * Contains the transaction
     *                             details, including the balancing lines.
     */
    public void setTransaction(com.coda.www.efinance.schemas.transaction.Transaction transaction) {
        this.transaction = transaction;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GenerateBalancingLinesResponse)) return false;
        GenerateBalancingLinesResponse other = (GenerateBalancingLinesResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.transaction==null && other.getTransaction()==null) || 
             (this.transaction!=null &&
              this.transaction.equals(other.getTransaction())));
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
        if (getTransaction() != null) {
            _hashCode += getTransaction().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GenerateBalancingLinesResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">GenerateBalancingLinesResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transaction");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "Transaction"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Transaction"));
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
