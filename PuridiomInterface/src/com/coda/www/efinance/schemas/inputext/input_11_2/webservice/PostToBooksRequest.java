/**
 * PostToBooksRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.inputext.input_11_2.webservice;

public class PostToBooksRequest  implements java.io.Serializable {
    private com.coda.www.efinance.schemas.inputext.input_11_2.webservice.PostToBooksRequestPostToBooksOptions postToBooksOptions;

    /* Contains the document
     *                             you want to post. */
    private com.coda.www.efinance.schemas.transaction.Transaction transaction;

    /* Contains details that are
     *                             used when the document is posted, or when
     * checking that the document is postable. */
    private com.coda.www.efinance.schemas.inputext.PostData postData;

    /* Contains a value selected
     *                             from the database using the selector defined
     * on the input template and is in the currency
     *                             defined on the input template. Other fields
     * may be derived from the SelectedValue which
     *                             is why it should be supplied. */
    private java.math.BigDecimal selectedValue;

    public PostToBooksRequest() {
    }

    public PostToBooksRequest(
           com.coda.www.efinance.schemas.inputext.input_11_2.webservice.PostToBooksRequestPostToBooksOptions postToBooksOptions,
           com.coda.www.efinance.schemas.transaction.Transaction transaction,
           com.coda.www.efinance.schemas.inputext.PostData postData,
           java.math.BigDecimal selectedValue) {
           this.postToBooksOptions = postToBooksOptions;
           this.transaction = transaction;
           this.postData = postData;
           this.selectedValue = selectedValue;
    }


    /**
     * Gets the postToBooksOptions value for this PostToBooksRequest.
     * 
     * @return postToBooksOptions
     */
    public com.coda.www.efinance.schemas.inputext.input_11_2.webservice.PostToBooksRequestPostToBooksOptions getPostToBooksOptions() {
        return postToBooksOptions;
    }


    /**
     * Sets the postToBooksOptions value for this PostToBooksRequest.
     * 
     * @param postToBooksOptions
     */
    public void setPostToBooksOptions(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.PostToBooksRequestPostToBooksOptions postToBooksOptions) {
        this.postToBooksOptions = postToBooksOptions;
    }


    /**
     * Gets the transaction value for this PostToBooksRequest.
     * 
     * @return transaction   * Contains the document
     *                             you want to post.
     */
    public com.coda.www.efinance.schemas.transaction.Transaction getTransaction() {
        return transaction;
    }


    /**
     * Sets the transaction value for this PostToBooksRequest.
     * 
     * @param transaction   * Contains the document
     *                             you want to post.
     */
    public void setTransaction(com.coda.www.efinance.schemas.transaction.Transaction transaction) {
        this.transaction = transaction;
    }


    /**
     * Gets the postData value for this PostToBooksRequest.
     * 
     * @return postData   * Contains details that are
     *                             used when the document is posted, or when
     * checking that the document is postable.
     */
    public com.coda.www.efinance.schemas.inputext.PostData getPostData() {
        return postData;
    }


    /**
     * Sets the postData value for this PostToBooksRequest.
     * 
     * @param postData   * Contains details that are
     *                             used when the document is posted, or when
     * checking that the document is postable.
     */
    public void setPostData(com.coda.www.efinance.schemas.inputext.PostData postData) {
        this.postData = postData;
    }


    /**
     * Gets the selectedValue value for this PostToBooksRequest.
     * 
     * @return selectedValue   * Contains a value selected
     *                             from the database using the selector defined
     * on the input template and is in the currency
     *                             defined on the input template. Other fields
     * may be derived from the SelectedValue which
     *                             is why it should be supplied.
     */
    public java.math.BigDecimal getSelectedValue() {
        return selectedValue;
    }


    /**
     * Sets the selectedValue value for this PostToBooksRequest.
     * 
     * @param selectedValue   * Contains a value selected
     *                             from the database using the selector defined
     * on the input template and is in the currency
     *                             defined on the input template. Other fields
     * may be derived from the SelectedValue which
     *                             is why it should be supplied.
     */
    public void setSelectedValue(java.math.BigDecimal selectedValue) {
        this.selectedValue = selectedValue;
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
              this.transaction.equals(other.getTransaction()))) &&
            ((this.postData==null && other.getPostData()==null) || 
             (this.postData!=null &&
              this.postData.equals(other.getPostData()))) &&
            ((this.selectedValue==null && other.getSelectedValue()==null) || 
             (this.selectedValue!=null &&
              this.selectedValue.equals(other.getSelectedValue())));
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
        if (getPostData() != null) {
            _hashCode += getPostData().hashCode();
        }
        if (getSelectedValue() != null) {
            _hashCode += getSelectedValue().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PostToBooksRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">PostToBooksRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("postToBooksOptions");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "PostToBooksOptions"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">>PostToBooksRequest>PostToBooksOptions"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transaction");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "Transaction"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Transaction"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("postData");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "PostData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "PostData"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("selectedValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "SelectedValue"));
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
