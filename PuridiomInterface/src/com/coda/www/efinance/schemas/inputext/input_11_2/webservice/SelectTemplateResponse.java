/**
 * SelectTemplateResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.inputext.input_11_2.webservice;

public class SelectTemplateResponse  implements java.io.Serializable {
    /* Contains the input template
     *                             master you specified in the request
     *                             corresponding to this response. */
    private com.coda.www.efinance.schemas.inputtemplate.InputTemplate inputTemplate;

    /* Contains the document master
     *                             linked to the specified input template
     * master. If the input template master is
     *                             linked to multiple document masters, the
     * document masters are returned in
     *                             DocCodeList. You should repeat the
     *                             SelectTemplateRequest, supplying the code
     * of
     *                             the document master you wish to use. */
    private com.coda.www.efinance.schemas.documentmaster.DocumentMaster documentMaster;

    /* Contains the default
     *                             transaction details as determined by the
     * input template master. */
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

    /* When the specified input
     *                             template master is linked to multiple
     *                             document masters, this element lists the
     * codes of the linked document masters. If
     *                             this list is returned, you should repeat
     * the
     *                             SelectTemplateRequest, supplying the code
     * of
     *                             the document master you wish to use. */
    private java.lang.String[] docCodeList;

    public SelectTemplateResponse() {
    }

    public SelectTemplateResponse(
           com.coda.www.efinance.schemas.inputtemplate.InputTemplate inputTemplate,
           com.coda.www.efinance.schemas.documentmaster.DocumentMaster documentMaster,
           com.coda.www.efinance.schemas.transaction.Transaction transaction,
           com.coda.www.efinance.schemas.transaction.Line addedLine,
           com.coda.www.efinance.schemas.inputext.PostData postData,
           java.lang.String[] docCodeList) {
           this.inputTemplate = inputTemplate;
           this.documentMaster = documentMaster;
           this.transaction = transaction;
           this.addedLine = addedLine;
           this.postData = postData;
           this.docCodeList = docCodeList;
    }


    /**
     * Gets the inputTemplate value for this SelectTemplateResponse.
     * 
     * @return inputTemplate   * Contains the input template
     *                             master you specified in the request
     *                             corresponding to this response.
     */
    public com.coda.www.efinance.schemas.inputtemplate.InputTemplate getInputTemplate() {
        return inputTemplate;
    }


    /**
     * Sets the inputTemplate value for this SelectTemplateResponse.
     * 
     * @param inputTemplate   * Contains the input template
     *                             master you specified in the request
     *                             corresponding to this response.
     */
    public void setInputTemplate(com.coda.www.efinance.schemas.inputtemplate.InputTemplate inputTemplate) {
        this.inputTemplate = inputTemplate;
    }


    /**
     * Gets the documentMaster value for this SelectTemplateResponse.
     * 
     * @return documentMaster   * Contains the document master
     *                             linked to the specified input template
     * master. If the input template master is
     *                             linked to multiple document masters, the
     * document masters are returned in
     *                             DocCodeList. You should repeat the
     *                             SelectTemplateRequest, supplying the code
     * of
     *                             the document master you wish to use.
     */
    public com.coda.www.efinance.schemas.documentmaster.DocumentMaster getDocumentMaster() {
        return documentMaster;
    }


    /**
     * Sets the documentMaster value for this SelectTemplateResponse.
     * 
     * @param documentMaster   * Contains the document master
     *                             linked to the specified input template
     * master. If the input template master is
     *                             linked to multiple document masters, the
     * document masters are returned in
     *                             DocCodeList. You should repeat the
     *                             SelectTemplateRequest, supplying the code
     * of
     *                             the document master you wish to use.
     */
    public void setDocumentMaster(com.coda.www.efinance.schemas.documentmaster.DocumentMaster documentMaster) {
        this.documentMaster = documentMaster;
    }


    /**
     * Gets the transaction value for this SelectTemplateResponse.
     * 
     * @return transaction   * Contains the default
     *                             transaction details as determined by the
     * input template master.
     */
    public com.coda.www.efinance.schemas.transaction.Transaction getTransaction() {
        return transaction;
    }


    /**
     * Sets the transaction value for this SelectTemplateResponse.
     * 
     * @param transaction   * Contains the default
     *                             transaction details as determined by the
     * input template master.
     */
    public void setTransaction(com.coda.www.efinance.schemas.transaction.Transaction transaction) {
        this.transaction = transaction;
    }


    /**
     * Gets the addedLine value for this SelectTemplateResponse.
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
     * Sets the addedLine value for this SelectTemplateResponse.
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
     * Gets the postData value for this SelectTemplateResponse.
     * 
     * @return postData   * Contains details that are
     *                             used when the document is posted, or when
     * checking that the document is postable.
     */
    public com.coda.www.efinance.schemas.inputext.PostData getPostData() {
        return postData;
    }


    /**
     * Sets the postData value for this SelectTemplateResponse.
     * 
     * @param postData   * Contains details that are
     *                             used when the document is posted, or when
     * checking that the document is postable.
     */
    public void setPostData(com.coda.www.efinance.schemas.inputext.PostData postData) {
        this.postData = postData;
    }


    /**
     * Gets the docCodeList value for this SelectTemplateResponse.
     * 
     * @return docCodeList   * When the specified input
     *                             template master is linked to multiple
     *                             document masters, this element lists the
     * codes of the linked document masters. If
     *                             this list is returned, you should repeat
     * the
     *                             SelectTemplateRequest, supplying the code
     * of
     *                             the document master you wish to use.
     */
    public java.lang.String[] getDocCodeList() {
        return docCodeList;
    }


    /**
     * Sets the docCodeList value for this SelectTemplateResponse.
     * 
     * @param docCodeList   * When the specified input
     *                             template master is linked to multiple
     *                             document masters, this element lists the
     * codes of the linked document masters. If
     *                             this list is returned, you should repeat
     * the
     *                             SelectTemplateRequest, supplying the code
     * of
     *                             the document master you wish to use.
     */
    public void setDocCodeList(java.lang.String[] docCodeList) {
        this.docCodeList = docCodeList;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SelectTemplateResponse)) return false;
        SelectTemplateResponse other = (SelectTemplateResponse) obj;
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
              this.postData.equals(other.getPostData()))) &&
            ((this.docCodeList==null && other.getDocCodeList()==null) || 
             (this.docCodeList!=null &&
              java.util.Arrays.equals(this.docCodeList, other.getDocCodeList())));
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
        if (getDocCodeList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDocCodeList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDocCodeList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SelectTemplateResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">SelectTemplateResponse"));
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("docCodeList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "DocCodeList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeGeneralCode"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "DocCode"));
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
