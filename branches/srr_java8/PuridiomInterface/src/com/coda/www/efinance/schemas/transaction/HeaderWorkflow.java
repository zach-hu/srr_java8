/**
 * HeaderWorkflow.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.transaction;


/**
 * This element holds
 *                 details of the workflow used by this
 *             document.
 */
public class HeaderWorkflow  implements java.io.Serializable {
    private java.lang.String workflowType;

    /* The
     *                         current status of the
     *                     workflow. */
    private java.lang.String headerStatus;

    /* The
     *                         code of the document
     *                     approver. */
    private java.lang.String documentApprover;

    public HeaderWorkflow() {
    }

    public HeaderWorkflow(
           java.lang.String workflowType,
           java.lang.String headerStatus,
           java.lang.String documentApprover) {
           this.workflowType = workflowType;
           this.headerStatus = headerStatus;
           this.documentApprover = documentApprover;
    }


    /**
     * Gets the workflowType value for this HeaderWorkflow.
     * 
     * @return workflowType
     */
    public java.lang.String getWorkflowType() {
        return workflowType;
    }


    /**
     * Sets the workflowType value for this HeaderWorkflow.
     * 
     * @param workflowType
     */
    public void setWorkflowType(java.lang.String workflowType) {
        this.workflowType = workflowType;
    }


    /**
     * Gets the headerStatus value for this HeaderWorkflow.
     * 
     * @return headerStatus   * The
     *                         current status of the
     *                     workflow.
     */
    public java.lang.String getHeaderStatus() {
        return headerStatus;
    }


    /**
     * Sets the headerStatus value for this HeaderWorkflow.
     * 
     * @param headerStatus   * The
     *                         current status of the
     *                     workflow.
     */
    public void setHeaderStatus(java.lang.String headerStatus) {
        this.headerStatus = headerStatus;
    }


    /**
     * Gets the documentApprover value for this HeaderWorkflow.
     * 
     * @return documentApprover   * The
     *                         code of the document
     *                     approver.
     */
    public java.lang.String getDocumentApprover() {
        return documentApprover;
    }


    /**
     * Sets the documentApprover value for this HeaderWorkflow.
     * 
     * @param documentApprover   * The
     *                         code of the document
     *                     approver.
     */
    public void setDocumentApprover(java.lang.String documentApprover) {
        this.documentApprover = documentApprover;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof HeaderWorkflow)) return false;
        HeaderWorkflow other = (HeaderWorkflow) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.workflowType==null && other.getWorkflowType()==null) || 
             (this.workflowType!=null &&
              this.workflowType.equals(other.getWorkflowType()))) &&
            ((this.headerStatus==null && other.getHeaderStatus()==null) || 
             (this.headerStatus!=null &&
              this.headerStatus.equals(other.getHeaderStatus()))) &&
            ((this.documentApprover==null && other.getDocumentApprover()==null) || 
             (this.documentApprover!=null &&
              this.documentApprover.equals(other.getDocumentApprover())));
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
        if (getWorkflowType() != null) {
            _hashCode += getWorkflowType().hashCode();
        }
        if (getHeaderStatus() != null) {
            _hashCode += getHeaderStatus().hashCode();
        }
        if (getDocumentApprover() != null) {
            _hashCode += getDocumentApprover().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(HeaderWorkflow.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "HeaderWorkflow"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("workflowType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "WorkflowType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("headerStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "HeaderStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documentApprover");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "DocumentApprover"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
