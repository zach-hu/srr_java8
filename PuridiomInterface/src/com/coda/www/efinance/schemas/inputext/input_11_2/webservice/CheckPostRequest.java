/**
 * CheckPostRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.inputext.input_11_2.webservice;

public class CheckPostRequest  implements java.io.Serializable {
    private com.coda.www.efinance.schemas.inputext.input_11_2.webservice.CheckPostRequestCheckPostOptions checkPostOptions;

    /* Contains the transaction details. */
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

    public CheckPostRequest() {
    }

    public CheckPostRequest(
           com.coda.www.efinance.schemas.inputext.input_11_2.webservice.CheckPostRequestCheckPostOptions checkPostOptions,
           com.coda.www.efinance.schemas.transaction.Transaction transaction,
           com.coda.www.efinance.schemas.inputext.PostData postData,
           java.math.BigDecimal selectedValue) {
           this.checkPostOptions = checkPostOptions;
           this.transaction = transaction;
           this.postData = postData;
           this.selectedValue = selectedValue;
    }


    /**
     * Gets the checkPostOptions value for this CheckPostRequest.
     * 
     * @return checkPostOptions
     */
    public com.coda.www.efinance.schemas.inputext.input_11_2.webservice.CheckPostRequestCheckPostOptions getCheckPostOptions() {
        return checkPostOptions;
    }


    /**
     * Sets the checkPostOptions value for this CheckPostRequest.
     * 
     * @param checkPostOptions
     */
    public void setCheckPostOptions(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.CheckPostRequestCheckPostOptions checkPostOptions) {
        this.checkPostOptions = checkPostOptions;
    }


    /**
     * Gets the transaction value for this CheckPostRequest.
     * 
     * @return transaction   * Contains the transaction details.
     */
    public com.coda.www.efinance.schemas.transaction.Transaction getTransaction() {
        return transaction;
    }


    /**
     * Sets the transaction value for this CheckPostRequest.
     * 
     * @param transaction   * Contains the transaction details.
     */
    public void setTransaction(com.coda.www.efinance.schemas.transaction.Transaction transaction) {
        this.transaction = transaction;
    }


    /**
     * Gets the postData value for this CheckPostRequest.
     * 
     * @return postData   * Contains details that are
     *                             used when the document is posted, or when
     * checking that the document is postable.
     */
    public com.coda.www.efinance.schemas.inputext.PostData getPostData() {
        return postData;
    }


    /**
     * Sets the postData value for this CheckPostRequest.
     * 
     * @param postData   * Contains details that are
     *                             used when the document is posted, or when
     * checking that the document is postable.
     */
    public void setPostData(com.coda.www.efinance.schemas.inputext.PostData postData) {
        this.postData = postData;
    }


    /**
     * Gets the selectedValue value for this CheckPostRequest.
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
     * Sets the selectedValue value for this CheckPostRequest.
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
        if (!(obj instanceof CheckPostRequest)) return false;
        CheckPostRequest other = (CheckPostRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.checkPostOptions==null && other.getCheckPostOptions()==null) || 
             (this.checkPostOptions!=null &&
              this.checkPostOptions.equals(other.getCheckPostOptions()))) &&
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
        if (getCheckPostOptions() != null) {
            _hashCode += getCheckPostOptions().hashCode();
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
        new org.apache.axis.description.TypeDesc(CheckPostRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">CheckPostRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("checkPostOptions");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "CheckPostOptions"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">>CheckPostRequest>CheckPostOptions"));
        elemField.setNillable(false);
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
