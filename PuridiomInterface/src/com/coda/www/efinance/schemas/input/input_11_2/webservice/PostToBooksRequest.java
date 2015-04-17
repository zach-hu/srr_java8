/**
 * PostToBooksRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.input.input_11_2.webservice;

public class PostToBooksRequest  implements java.io.Serializable {
    private com.coda.www.efinance.schemas.input.input_11_2.webservice.PostToBooksRequestPostToBooksOptions postToBooksOptions;

    /* Contains the document you
     *                             want to post. */
    private com.coda.www.efinance.schemas.transaction.Transaction transaction;

    public PostToBooksRequest() {
    }

    public PostToBooksRequest(
           com.coda.www.efinance.schemas.input.input_11_2.webservice.PostToBooksRequestPostToBooksOptions postToBooksOptions,
           com.coda.www.efinance.schemas.transaction.Transaction transaction) {
           this.postToBooksOptions = postToBooksOptions;
           this.transaction = transaction;
    }


    /**
     * Gets the postToBooksOptions value for this PostToBooksRequest.
     * 
     * @return postToBooksOptions
     */
    public com.coda.www.efinance.schemas.input.input_11_2.webservice.PostToBooksRequestPostToBooksOptions getPostToBooksOptions() {
        return postToBooksOptions;
    }


    /**
     * Sets the postToBooksOptions value for this PostToBooksRequest.
     * 
     * @param postToBooksOptions
     */
    public void setPostToBooksOptions(com.coda.www.efinance.schemas.input.input_11_2.webservice.PostToBooksRequestPostToBooksOptions postToBooksOptions) {
        this.postToBooksOptions = postToBooksOptions;
    }


    /**
     * Gets the transaction value for this PostToBooksRequest.
     * 
     * @return transaction   * Contains the document you
     *                             want to post.
     */
    public com.coda.www.efinance.schemas.transaction.Transaction getTransaction() {
        return transaction;
    }


    /**
     * Sets the transaction value for this PostToBooksRequest.
     * 
     * @param transaction   * Contains the document you
     *                             want to post.
     */
    public void setTransaction(com.coda.www.efinance.schemas.transaction.Transaction transaction) {
        this.transaction = transaction;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PostToBooksRequest)) return false;
        PostToBooksRequest other = (PostToBooksRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.postToBooksOptions==null && other.getPostToBooksOptions()==null) || 
             (this.postToBooksOptions!=null &&
              this.postToBooksOptions.equals(other.getPostToBooksOptions()))) &&
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
        if (getPostToBooksOptions() != null) {
            _hashCode += getPostToBooksOptions().hashCode();
        }
        if (getTransaction() != null) {
            _hashCode += getTransaction().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PostToBooksRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/input/input-11.2/webservice", ">PostToBooksRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("postToBooksOptions");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/input/input-11.2/webservice", "PostToBooksOptions"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/input/input-11.2/webservice", ">>PostToBooksRequest>PostToBooksOptions"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transaction");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/input/input-11.2/webservice", "Transaction"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Transaction"));
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
