/**
 * CopyDocResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.inputext.input_11_2.webservice;

public class CopyDocResponse  implements java.io.Serializable {
    /* The code of the input
     *                             template master associated with the new
     * document. */
    private com.coda.www.efinance.schemas.inputtemplate.InputTemplate inputTemplate;

    /* The document master of the
     *                             new document. */
    private com.coda.www.efinance.schemas.documentmaster.DocumentMaster documentMaster;

    /* Contains the new
     *                             document's transaction details. */
    private com.coda.www.efinance.schemas.transaction.Transaction transaction;

    /* Contains the default
     *                             additional line values as determined by
     * the
     *                             input template master. */
    private com.coda.www.efinance.schemas.transaction.Line addedLine;

    /* Contains details that are
     *                             used when the document is posted, or when
     * checking that the document is postable. */
    private com.coda.www.efinance.schemas.inputext.PostData postData;

    public CopyDocResponse() {
    }

    public CopyDocResponse(
           com.coda.www.efinance.schemas.inputtemplate.InputTemplate inputTemplate,
           com.coda.www.efinance.schemas.documentmaster.DocumentMaster documentMaster,
           com.coda.www.efinance.schemas.transaction.Transaction transaction,
           com.coda.www.efinance.schemas.transaction.Line addedLine,
           com.coda.www.efinance.schemas.inputext.PostData postData) {
           this.inputTemplate = inputTemplate;
           this.documentMaster = documentMaster;
           this.transaction = transaction;
           this.addedLine = addedLine;
           this.postData = postData;
    }


    /**
     * Gets the inputTemplate value for this CopyDocResponse.
     * 
     * @return inputTemplate   * The code of the input
     *                             template master associated with the new
     * document.
     */
    public com.coda.www.efinance.schemas.inputtemplate.InputTemplate getInputTemplate() {
        return inputTemplate;
    }


    /**
     * Sets the inputTemplate value for this CopyDocResponse.
     * 
     * @param inputTemplate   * The code of the input
     *                             template master associated with the new
     * document.
     */
    public void setInputTemplate(com.coda.www.efinance.schemas.inputtemplate.InputTemplate inputTemplate) {
        this.inputTemplate = inputTemplate;
    }


    /**
     * Gets the documentMaster value for this CopyDocResponse.
     * 
     * @return documentMaster   * The document master of the
     *                             new document.
     */
    public com.coda.www.efinance.schemas.documentmaster.DocumentMaster getDocumentMaster() {
        return documentMaster;
    }


    /**
     * Sets the documentMaster value for this CopyDocResponse.
     * 
     * @param documentMaster   * The document master of the
     *                             new document.
     */
    public void setDocumentMaster(com.coda.www.efinance.schemas.documentmaster.DocumentMaster documentMaster) {
        this.documentMaster = documentMaster;
    }


    /**
     * Gets the transaction value for this CopyDocResponse.
     * 
     * @return transaction   * Contains the new
     *                             document's transaction details.
     */
    public com.coda.www.efinance.schemas.transaction.Transaction getTransaction() {
        return transaction;
    }


    /**
     * Sets the transaction value for this CopyDocResponse.
     * 
     * @param transaction   * Contains the new
     *                             document's transaction details.
     */
    public void setTransaction(com.coda.www.efinance.schemas.transaction.Transaction transaction) {
        this.transaction = transaction;
    }


    /**
     * Gets the addedLine value for this CopyDocResponse.
     * 
     * @return addedLine   * Contains the default
     *                             additional line values as determined by
     * the
     *                             input template master.
     */
    public com.coda.www.efinance.schemas.transaction.Line getAddedLine() {
        return addedLine;
    }


    /**
     * Sets the addedLine value for this CopyDocResponse.
     * 
     * @param addedLine   * Contains the default
     *                             additional line values as determined by
     * the
     *                             input template master.
     */
    public void setAddedLine(com.coda.www.efinance.schemas.transaction.Line addedLine) {
        this.addedLine = addedLine;
    }


    /**
     * Gets the postData value for this CopyDocResponse.
     * 
     * @return postData   * Contains details that are
     *                             used when the document is posted, or when
     * checking that the document is postable.
     */
    public com.coda.www.efinance.schemas.inputext.PostData getPostData() {
        return postData;
    }


    /**
     * Sets the postData value for this CopyDocResponse.
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
        if (!(obj instanceof CopyDocResponse)) return false;
        CopyDocResponse other = (CopyDocResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.inputTemplate==null && other.getInputTemplate()==null) || 
             (this.inputTemplate!=null &&
              this.inputTemplate.equals(other.getInputTemplate()))) &&
            ((this.documentMaster==null && other.getDocumentMaster()==null) || 
             (this.documentMaster!=null &&
              this.documentMaster.equals(other.getDocumentMaster()))) &&
            ((this.transaction==null && other.getTransaction()==null) || 
             (this.transaction!=null &&
              this.transaction.equals(other.getTransaction()))) &&
            ((this.addedLine==null && other.getAddedLine()==null) || 
             (this.addedLine!=null &&
              this.addedLine.equals(other.getAddedLine()))) &&
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
        if (getInputTemplate() != null) {
            _hashCode += getInputTemplate().hashCode();
        }
        if (getDocumentMaster() != null) {
            _hashCode += getDocumentMaster().hashCode();
        }
        if (getTransaction() != null) {
            _hashCode += getTransaction().hashCode();
        }
        if (getAddedLine() != null) {
            _hashCode += getAddedLine().hashCode();
        }
        if (getPostData() != null) {
            _hashCode += getPostData().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CopyDocResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">CopyDocResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inputTemplate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "InputTemplate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "InputTemplate"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documentMaster");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "DocumentMaster"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "DocumentMaster"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transaction");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "Transaction"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Transaction"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("addedLine");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "AddedLine"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Line"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("postData");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "PostData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "PostData"));
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
