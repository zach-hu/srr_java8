/**
 * GetDocumentResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.transaction;

public class GetDocumentResponse  extends com.coda.www.efinance.schemas.common.Response  implements java.io.Serializable {
    /* This element contains
     *                                 a key uniquely identifying the document
     * you have retrieved. */
    private com.coda.www.efinance.schemas.transaction.TxnKey key;

    /* This element holds
     *                                 the financial document you have
     *                             retrieved. */
    private com.coda.www.efinance.schemas.transaction.Transaction transaction;

    public GetDocumentResponse() {
    }

    public GetDocumentResponse(
           com.coda.www.efinance.schemas.common.TypeResponseStatus status,
           java.lang.String transactioncoordinator,
           com.coda.www.efinance.schemas.common.Reason reason,
           com.coda.www.efinance.schemas.transaction.TxnKey key,
           com.coda.www.efinance.schemas.transaction.Transaction transaction) {
        super();
        this.key = key;
        this.transaction = transaction;
    }


    /**
     * Gets the key value for this GetDocumentResponse.
     *
     * @return key   * This element contains
     *                                 a key uniquely identifying the document
     * you have retrieved.
     */
    public com.coda.www.efinance.schemas.transaction.TxnKey getKey() {
        return key;
    }


    /**
     * Sets the key value for this GetDocumentResponse.
     *
     * @param key   * This element contains
     *                                 a key uniquely identifying the document
     * you have retrieved.
     */
    public void setKey(com.coda.www.efinance.schemas.transaction.TxnKey key) {
        this.key = key;
    }


    /**
     * Gets the transaction value for this GetDocumentResponse.
     *
     * @return transaction   * This element holds
     *                                 the financial document you have
     *                             retrieved.
     */
    public com.coda.www.efinance.schemas.transaction.Transaction getTransaction() {
        return transaction;
    }


    /**
     * Sets the transaction value for this GetDocumentResponse.
     *
     * @param transaction   * This element holds
     *                                 the financial document you have
     *                             retrieved.
     */
    public void setTransaction(com.coda.www.efinance.schemas.transaction.Transaction transaction) {
        this.transaction = transaction;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetDocumentResponse)) return false;
        GetDocumentResponse other = (GetDocumentResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) &&
            ((this.key==null && other.getKey()==null) ||
             (this.key!=null &&
              this.key.equals(other.getKey()))) &&
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
        int _hashCode = super.hashCode();
        if (getKey() != null) {
            _hashCode += getKey().hashCode();
        }
        if (getTransaction() != null) {
            _hashCode += getTransaction().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetDocumentResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "GetDocumentResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("key");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Key"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "txnKey"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transaction");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Transaction"));
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
