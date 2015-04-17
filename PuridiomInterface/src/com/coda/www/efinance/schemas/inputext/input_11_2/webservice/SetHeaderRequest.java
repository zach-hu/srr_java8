/**
 * SetHeaderRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.inputext.input_11_2.webservice;

public class SetHeaderRequest  implements java.io.Serializable {
    /* Contains the new document
     *                             header information. */
    private com.coda.www.efinance.schemas.transaction.Header newHeader;

    /* Contains the new
     *                             document-wide data consisting of due date,
     * value date and external references. */
    private com.coda.www.efinance.schemas.inputext.DocumentWideData newDocWideData;

    /* Contains details that are
     *                             used when the document is posted, or when
     * checking that the document is postable. */
    private com.coda.www.efinance.schemas.inputext.PostData postData;

    /* Contains the transaction details. */
    private com.coda.www.efinance.schemas.transaction.Transaction transaction;

    public SetHeaderRequest() {
    }

    public SetHeaderRequest(
           com.coda.www.efinance.schemas.transaction.Header newHeader,
           com.coda.www.efinance.schemas.inputext.DocumentWideData newDocWideData,
           com.coda.www.efinance.schemas.inputext.PostData postData,
           com.coda.www.efinance.schemas.transaction.Transaction transaction) {
           this.newHeader = newHeader;
           this.newDocWideData = newDocWideData;
           this.postData = postData;
           this.transaction = transaction;
    }


    /**
     * Gets the newHeader value for this SetHeaderRequest.
     * 
     * @return newHeader   * Contains the new document
     *                             header information.
     */
    public com.coda.www.efinance.schemas.transaction.Header getNewHeader() {
        return newHeader;
    }


    /**
     * Sets the newHeader value for this SetHeaderRequest.
     * 
     * @param newHeader   * Contains the new document
     *                             header information.
     */
    public void setNewHeader(com.coda.www.efinance.schemas.transaction.Header newHeader) {
        this.newHeader = newHeader;
    }


    /**
     * Gets the newDocWideData value for this SetHeaderRequest.
     * 
     * @return newDocWideData   * Contains the new
     *                             document-wide data consisting of due date,
     * value date and external references.
     */
    public com.coda.www.efinance.schemas.inputext.DocumentWideData getNewDocWideData() {
        return newDocWideData;
    }


    /**
     * Sets the newDocWideData value for this SetHeaderRequest.
     * 
     * @param newDocWideData   * Contains the new
     *                             document-wide data consisting of due date,
     * value date and external references.
     */
    public void setNewDocWideData(com.coda.www.efinance.schemas.inputext.DocumentWideData newDocWideData) {
        this.newDocWideData = newDocWideData;
    }


    /**
     * Gets the postData value for this SetHeaderRequest.
     * 
     * @return postData   * Contains details that are
     *                             used when the document is posted, or when
     * checking that the document is postable.
     */
    public com.coda.www.efinance.schemas.inputext.PostData getPostData() {
        return postData;
    }


    /**
     * Sets the postData value for this SetHeaderRequest.
     * 
     * @param postData   * Contains details that are
     *                             used when the document is posted, or when
     * checking that the document is postable.
     */
    public void setPostData(com.coda.www.efinance.schemas.inputext.PostData postData) {
        this.postData = postData;
    }


    /**
     * Gets the transaction value for this SetHeaderRequest.
     * 
     * @return transaction   * Contains the transaction details.
     */
    public com.coda.www.efinance.schemas.transaction.Transaction getTransaction() {
        return transaction;
    }


    /**
     * Sets the transaction value for this SetHeaderRequest.
     * 
     * @param transaction   * Contains the transaction details.
     */
    public void setTransaction(com.coda.www.efinance.schemas.transaction.Transaction transaction) {
        this.transaction = transaction;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SetHeaderRequest)) return false;
        SetHeaderRequest other = (SetHeaderRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.newHeader==null && other.getNewHeader()==null) || 
             (this.newHeader!=null &&
              this.newHeader.equals(other.getNewHeader()))) &&
            ((this.newDocWideData==null && other.getNewDocWideData()==null) || 
             (this.newDocWideData!=null &&
              this.newDocWideData.equals(other.getNewDocWideData()))) &&
            ((this.postData==null && other.getPostData()==null) || 
             (this.postData!=null &&
              this.postData.equals(other.getPostData()))) &&
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
        if (getNewHeader() != null) {
            _hashCode += getNewHeader().hashCode();
        }
        if (getNewDocWideData() != null) {
            _hashCode += getNewDocWideData().hashCode();
        }
        if (getPostData() != null) {
            _hashCode += getPostData().hashCode();
        }
        if (getTransaction() != null) {
            _hashCode += getTransaction().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SetHeaderRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">SetHeaderRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("newHeader");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "NewHeader"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Header"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("newDocWideData");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "NewDocWideData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "DocumentWideData"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("postData");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "PostData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "PostData"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transaction");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "Transaction"));
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
