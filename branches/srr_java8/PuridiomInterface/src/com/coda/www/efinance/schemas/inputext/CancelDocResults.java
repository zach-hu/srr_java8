/**
 * CancelDocResults.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.inputext;


/**
 * Contains the results of a request to
 *                 cancel one or more documents.
 */
public class CancelDocResults  implements java.io.Serializable {
    private java.lang.String cmpCode;

    /* The document master code of
     *                         the documents that were
     *                     cancelled. */
    private java.lang.String docCode;

    /* The number of document
     *                         numbers sent in the request if SelectedDocs
     * is
     *                         present, or 1 if the request was to cancel
     * a
     *                         single document. */
    private java.lang.Integer numberOfDocsSelected;

    /* The
     *                         number of documents found. */
    private int numberOfDocsFound;

    /* The number of documents found
     *                         that have not already been
     *                     cancelled. */
    private int numberOfUncancelledDocsFound;

    /* The number of documents
     *                         actually cancelled. */
    private int numberOfDocsCancelled;

    /* Contains a reason message if
     *                         any InterCompany documents were found but
     *                         IncludeInterCompanyDocs was not supplied in
     * the
     *                     request. */
    private com.coda.www.efinance.schemas.inputext.CancelDocInterCompanyWarning interCompanyWarning;

    /* Indicates that the workflow
     *                         process has started. */
    private boolean workflowStarted;

    public CancelDocResults() {
    }

    public CancelDocResults(
           java.lang.String cmpCode,
           java.lang.String docCode,
           java.lang.Integer numberOfDocsSelected,
           int numberOfDocsFound,
           int numberOfUncancelledDocsFound,
           int numberOfDocsCancelled,
           com.coda.www.efinance.schemas.inputext.CancelDocInterCompanyWarning interCompanyWarning,
           boolean workflowStarted) {
           this.cmpCode = cmpCode;
           this.docCode = docCode;
           this.numberOfDocsSelected = numberOfDocsSelected;
           this.numberOfDocsFound = numberOfDocsFound;
           this.numberOfUncancelledDocsFound = numberOfUncancelledDocsFound;
           this.numberOfDocsCancelled = numberOfDocsCancelled;
           this.interCompanyWarning = interCompanyWarning;
           this.workflowStarted = workflowStarted;
    }


    /**
     * Gets the cmpCode value for this CancelDocResults.
     * 
     * @return cmpCode
     */
    public java.lang.String getCmpCode() {
        return cmpCode;
    }


    /**
     * Sets the cmpCode value for this CancelDocResults.
     * 
     * @param cmpCode
     */
    public void setCmpCode(java.lang.String cmpCode) {
        this.cmpCode = cmpCode;
    }


    /**
     * Gets the docCode value for this CancelDocResults.
     * 
     * @return docCode   * The document master code of
     *                         the documents that were
     *                     cancelled.
     */
    public java.lang.String getDocCode() {
        return docCode;
    }


    /**
     * Sets the docCode value for this CancelDocResults.
     * 
     * @param docCode   * The document master code of
     *                         the documents that were
     *                     cancelled.
     */
    public void setDocCode(java.lang.String docCode) {
        this.docCode = docCode;
    }


    /**
     * Gets the numberOfDocsSelected value for this CancelDocResults.
     * 
     * @return numberOfDocsSelected   * The number of document
     *                         numbers sent in the request if SelectedDocs
     * is
     *                         present, or 1 if the request was to cancel
     * a
     *                         single document.
     */
    public java.lang.Integer getNumberOfDocsSelected() {
        return numberOfDocsSelected;
    }


    /**
     * Sets the numberOfDocsSelected value for this CancelDocResults.
     * 
     * @param numberOfDocsSelected   * The number of document
     *                         numbers sent in the request if SelectedDocs
     * is
     *                         present, or 1 if the request was to cancel
     * a
     *                         single document.
     */
    public void setNumberOfDocsSelected(java.lang.Integer numberOfDocsSelected) {
        this.numberOfDocsSelected = numberOfDocsSelected;
    }


    /**
     * Gets the numberOfDocsFound value for this CancelDocResults.
     * 
     * @return numberOfDocsFound   * The
     *                         number of documents found.
     */
    public int getNumberOfDocsFound() {
        return numberOfDocsFound;
    }


    /**
     * Sets the numberOfDocsFound value for this CancelDocResults.
     * 
     * @param numberOfDocsFound   * The
     *                         number of documents found.
     */
    public void setNumberOfDocsFound(int numberOfDocsFound) {
        this.numberOfDocsFound = numberOfDocsFound;
    }


    /**
     * Gets the numberOfUncancelledDocsFound value for this CancelDocResults.
     * 
     * @return numberOfUncancelledDocsFound   * The number of documents found
     *                         that have not already been
     *                     cancelled.
     */
    public int getNumberOfUncancelledDocsFound() {
        return numberOfUncancelledDocsFound;
    }


    /**
     * Sets the numberOfUncancelledDocsFound value for this CancelDocResults.
     * 
     * @param numberOfUncancelledDocsFound   * The number of documents found
     *                         that have not already been
     *                     cancelled.
     */
    public void setNumberOfUncancelledDocsFound(int numberOfUncancelledDocsFound) {
        this.numberOfUncancelledDocsFound = numberOfUncancelledDocsFound;
    }


    /**
     * Gets the numberOfDocsCancelled value for this CancelDocResults.
     * 
     * @return numberOfDocsCancelled   * The number of documents
     *                         actually cancelled.
     */
    public int getNumberOfDocsCancelled() {
        return numberOfDocsCancelled;
    }


    /**
     * Sets the numberOfDocsCancelled value for this CancelDocResults.
     * 
     * @param numberOfDocsCancelled   * The number of documents
     *                         actually cancelled.
     */
    public void setNumberOfDocsCancelled(int numberOfDocsCancelled) {
        this.numberOfDocsCancelled = numberOfDocsCancelled;
    }


    /**
     * Gets the interCompanyWarning value for this CancelDocResults.
     * 
     * @return interCompanyWarning   * Contains a reason message if
     *                         any InterCompany documents were found but
     *                         IncludeInterCompanyDocs was not supplied in
     * the
     *                     request.
     */
    public com.coda.www.efinance.schemas.inputext.CancelDocInterCompanyWarning getInterCompanyWarning() {
        return interCompanyWarning;
    }


    /**
     * Sets the interCompanyWarning value for this CancelDocResults.
     * 
     * @param interCompanyWarning   * Contains a reason message if
     *                         any InterCompany documents were found but
     *                         IncludeInterCompanyDocs was not supplied in
     * the
     *                     request.
     */
    public void setInterCompanyWarning(com.coda.www.efinance.schemas.inputext.CancelDocInterCompanyWarning interCompanyWarning) {
        this.interCompanyWarning = interCompanyWarning;
    }


    /**
     * Gets the workflowStarted value for this CancelDocResults.
     * 
     * @return workflowStarted   * Indicates that the workflow
     *                         process has started.
     */
    public boolean isWorkflowStarted() {
        return workflowStarted;
    }


    /**
     * Sets the workflowStarted value for this CancelDocResults.
     * 
     * @param workflowStarted   * Indicates that the workflow
     *                         process has started.
     */
    public void setWorkflowStarted(boolean workflowStarted) {
        this.workflowStarted = workflowStarted;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CancelDocResults)) return false;
        CancelDocResults other = (CancelDocResults) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.cmpCode==null && other.getCmpCode()==null) || 
             (this.cmpCode!=null &&
              this.cmpCode.equals(other.getCmpCode()))) &&
            ((this.docCode==null && other.getDocCode()==null) || 
             (this.docCode!=null &&
              this.docCode.equals(other.getDocCode()))) &&
            ((this.numberOfDocsSelected==null && other.getNumberOfDocsSelected()==null) || 
             (this.numberOfDocsSelected!=null &&
              this.numberOfDocsSelected.equals(other.getNumberOfDocsSelected()))) &&
            this.numberOfDocsFound == other.getNumberOfDocsFound() &&
            this.numberOfUncancelledDocsFound == other.getNumberOfUncancelledDocsFound() &&
            this.numberOfDocsCancelled == other.getNumberOfDocsCancelled() &&
            ((this.interCompanyWarning==null && other.getInterCompanyWarning()==null) || 
             (this.interCompanyWarning!=null &&
              this.interCompanyWarning.equals(other.getInterCompanyWarning()))) &&
            this.workflowStarted == other.isWorkflowStarted();
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
        if (getCmpCode() != null) {
            _hashCode += getCmpCode().hashCode();
        }
        if (getDocCode() != null) {
            _hashCode += getDocCode().hashCode();
        }
        if (getNumberOfDocsSelected() != null) {
            _hashCode += getNumberOfDocsSelected().hashCode();
        }
        _hashCode += getNumberOfDocsFound();
        _hashCode += getNumberOfUncancelledDocsFound();
        _hashCode += getNumberOfDocsCancelled();
        if (getInterCompanyWarning() != null) {
            _hashCode += getInterCompanyWarning().hashCode();
        }
        _hashCode += (isWorkflowStarted() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CancelDocResults.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "CancelDocResults"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cmpCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "CmpCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("docCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "DocCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numberOfDocsSelected");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "NumberOfDocsSelected"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numberOfDocsFound");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "NumberOfDocsFound"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numberOfUncancelledDocsFound");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "NumberOfUncancelledDocsFound"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numberOfDocsCancelled");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "NumberOfDocsCancelled"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("interCompanyWarning");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "InterCompanyWarning"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "CancelDocInterCompanyWarning"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("workflowStarted");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "WorkflowStarted"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
