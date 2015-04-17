/**
 * GenerateTaxWithoutTemplateRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.inputext.input_11_2.webservice;

public class GenerateTaxWithoutTemplateRequest  implements java.io.Serializable {
    /* Contains the transaction details. */
    private com.coda.www.efinance.schemas.transaction.Transaction transaction;

    /* Contains details that are
     *                             used when the document is posted, or when
     * checking that the document is postable. */
    private com.coda.www.efinance.schemas.inputext.PostData postData;

    public GenerateTaxWithoutTemplateRequest() {
    }

    public GenerateTaxWithoutTemplateRequest(
           com.coda.www.efinance.schemas.transaction.Transaction transaction,
           com.coda.www.efinance.schemas.inputext.PostData postData) {
           this.transaction = transaction;
           this.postData = postData;
    }


    /**
     * Gets the transaction value for this GenerateTaxWithoutTemplateRequest.
     * 
     * @return transaction   * Contains the transaction details.
     */
    public com.coda.www.efinance.schemas.transaction.Transaction getTransaction() {
        return transaction;
    }


    /**
     * Sets the transaction value for this GenerateTaxWithoutTemplateRequest.
     * 
     * @param transaction   * Contains the transaction details.
     */
    public void setTransaction(com.coda.www.efinance.schemas.transaction.Transaction transaction) {
        this.transaction = transaction;
    }


    /**
     * Gets the postData value for this GenerateTaxWithoutTemplateRequest.
     * 
     * @return postData   * Contains details that are
     *                             used when the document is posted, or when
     * checking that the document is postable.
     */
    public com.coda.www.efinance.schemas.inputext.PostData getPostData() {
        return postData;
    }


    /**
     * Sets the postData value for this GenerateTaxWithoutTemplateRequest.
     * 
     * @param postData   * Contains details that are
     *                             used when the document is posted, or when
     * checking that the document is postable.
     */
    public void setPostData(com.coda.www.efinance.schemas.inputext.PostData postData) {
        this.postData = postData;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GenerateTaxWithoutTemplateRequest)) return false;
        GenerateTaxWithoutTemplateRequest other = (GenerateTaxWithoutTemplateRequest) obj;
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
              this.transaction.equals(other.getTransaction()))) &&
            ((this.postData==null && other.getPostData()==null) || 
             (this.postData!=null &&
              this.postData.equals(other.getPostData())));
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
        if (getPostData() != null) {
            _hashCode += getPostData().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GenerateTaxWithoutTemplateRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">GenerateTaxWithoutTemplateRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transaction");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "Transaction"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Transaction"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("postData");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "PostData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "PostData"));
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
