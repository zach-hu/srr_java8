/**
 * PostData.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.inputext;


/**
 * Contains details that are used when
 *                 the document is posted, or when checking that the
 *                 document is postable.
 */
public class PostData  implements java.io.Serializable {
    private java.lang.String template;

    /* This
     *                         element contains details that apply to all
     * lines
     *                         in the document. */
    private com.coda.www.efinance.schemas.inputext.DocumentWideData documentWideData;

    /* Contains details that can be
     *                         specified when posting reversing documents,
     * or
     *                         when checking that reversing documents are
     * postable. */
    private com.coda.www.efinance.schemas.inputext.ReversingPostData reversingDetails;

    /* Contains details that can be
     *                         specified when posting recurring documents,
     * or
     *                         when checking that recurring documents are
     * postable. */
    private com.coda.www.efinance.schemas.inputext.RecurringPostData recurringDetails;

    /* An optional control total
     *                         entered in Input. */
    private java.math.BigDecimal controlTotal;

    /* The
     *                         workflow process definition
     *                     code. */
    private java.lang.String workflowCode;

    /* The
     *                         position code within the position
     *                     hierarchy. */
    private java.lang.String positionCode;

    /* Specifies the CODA
     *                         application to which this document is
     *                     reserved. */
    private java.lang.Short applicationId;

    public PostData() {
    }

    public PostData(
           java.lang.String template,
           com.coda.www.efinance.schemas.inputext.DocumentWideData documentWideData,
           com.coda.www.efinance.schemas.inputext.ReversingPostData reversingDetails,
           com.coda.www.efinance.schemas.inputext.RecurringPostData recurringDetails,
           java.math.BigDecimal controlTotal,
           java.lang.String workflowCode,
           java.lang.String positionCode,
           java.lang.Short applicationId) {
           this.template = template;
           this.documentWideData = documentWideData;
           this.reversingDetails = reversingDetails;
           this.recurringDetails = recurringDetails;
           this.controlTotal = controlTotal;
           this.workflowCode = workflowCode;
           this.positionCode = positionCode;
           this.applicationId = applicationId;
    }


    /**
     * Gets the template value for this PostData.
     * 
     * @return template
     */
    public java.lang.String getTemplate() {
        return template;
    }


    /**
     * Sets the template value for this PostData.
     * 
     * @param template
     */
    public void setTemplate(java.lang.String template) {
        this.template = template;
    }


    /**
     * Gets the documentWideData value for this PostData.
     * 
     * @return documentWideData   * This
     *                         element contains details that apply to all
     * lines
     *                         in the document.
     */
    public com.coda.www.efinance.schemas.inputext.DocumentWideData getDocumentWideData() {
        return documentWideData;
    }


    /**
     * Sets the documentWideData value for this PostData.
     * 
     * @param documentWideData   * This
     *                         element contains details that apply to all
     * lines
     *                         in the document.
     */
    public void setDocumentWideData(com.coda.www.efinance.schemas.inputext.DocumentWideData documentWideData) {
        this.documentWideData = documentWideData;
    }


    /**
     * Gets the reversingDetails value for this PostData.
     * 
     * @return reversingDetails   * Contains details that can be
     *                         specified when posting reversing documents,
     * or
     *                         when checking that reversing documents are
     * postable.
     */
    public com.coda.www.efinance.schemas.inputext.ReversingPostData getReversingDetails() {
        return reversingDetails;
    }


    /**
     * Sets the reversingDetails value for this PostData.
     * 
     * @param reversingDetails   * Contains details that can be
     *                         specified when posting reversing documents,
     * or
     *                         when checking that reversing documents are
     * postable.
     */
    public void setReversingDetails(com.coda.www.efinance.schemas.inputext.ReversingPostData reversingDetails) {
        this.reversingDetails = reversingDetails;
    }


    /**
     * Gets the recurringDetails value for this PostData.
     * 
     * @return recurringDetails   * Contains details that can be
     *                         specified when posting recurring documents,
     * or
     *                         when checking that recurring documents are
     * postable.
     */
    public com.coda.www.efinance.schemas.inputext.RecurringPostData getRecurringDetails() {
        return recurringDetails;
    }


    /**
     * Sets the recurringDetails value for this PostData.
     * 
     * @param recurringDetails   * Contains details that can be
     *                         specified when posting recurring documents,
     * or
     *                         when checking that recurring documents are
     * postable.
     */
    public void setRecurringDetails(com.coda.www.efinance.schemas.inputext.RecurringPostData recurringDetails) {
        this.recurringDetails = recurringDetails;
    }


    /**
     * Gets the controlTotal value for this PostData.
     * 
     * @return controlTotal   * An optional control total
     *                         entered in Input.
     */
    public java.math.BigDecimal getControlTotal() {
        return controlTotal;
    }


    /**
     * Sets the controlTotal value for this PostData.
     * 
     * @param controlTotal   * An optional control total
     *                         entered in Input.
     */
    public void setControlTotal(java.math.BigDecimal controlTotal) {
        this.controlTotal = controlTotal;
    }


    /**
     * Gets the workflowCode value for this PostData.
     * 
     * @return workflowCode   * The
     *                         workflow process definition
     *                     code.
     */
    public java.lang.String getWorkflowCode() {
        return workflowCode;
    }


    /**
     * Sets the workflowCode value for this PostData.
     * 
     * @param workflowCode   * The
     *                         workflow process definition
     *                     code.
     */
    public void setWorkflowCode(java.lang.String workflowCode) {
        this.workflowCode = workflowCode;
    }


    /**
     * Gets the positionCode value for this PostData.
     * 
     * @return positionCode   * The
     *                         position code within the position
     *                     hierarchy.
     */
    public java.lang.String getPositionCode() {
        return positionCode;
    }


    /**
     * Sets the positionCode value for this PostData.
     * 
     * @param positionCode   * The
     *                         position code within the position
     *                     hierarchy.
     */
    public void setPositionCode(java.lang.String positionCode) {
        this.positionCode = positionCode;
    }


    /**
     * Gets the applicationId value for this PostData.
     * 
     * @return applicationId   * Specifies the CODA
     *                         application to which this document is
     *                     reserved.
     */
    public java.lang.Short getApplicationId() {
        return applicationId;
    }


    /**
     * Sets the applicationId value for this PostData.
     * 
     * @param applicationId   * Specifies the CODA
     *                         application to which this document is
     *                     reserved.
     */
    public void setApplicationId(java.lang.Short applicationId) {
        this.applicationId = applicationId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PostData)) return false;
        PostData other = (PostData) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.template==null && other.getTemplate()==null) || 
             (this.template!=null &&
              this.template.equals(other.getTemplate()))) &&
            ((this.documentWideData==null && other.getDocumentWideData()==null) || 
             (this.documentWideData!=null &&
              this.documentWideData.equals(other.getDocumentWideData()))) &&
            ((this.reversingDetails==null && other.getReversingDetails()==null) || 
             (this.reversingDetails!=null &&
              this.reversingDetails.equals(other.getReversingDetails()))) &&
            ((this.recurringDetails==null && other.getRecurringDetails()==null) || 
             (this.recurringDetails!=null &&
              this.recurringDetails.equals(other.getRecurringDetails()))) &&
            ((this.controlTotal==null && other.getControlTotal()==null) || 
             (this.controlTotal!=null &&
              this.controlTotal.equals(other.getControlTotal()))) &&
            ((this.workflowCode==null && other.getWorkflowCode()==null) || 
             (this.workflowCode!=null &&
              this.workflowCode.equals(other.getWorkflowCode()))) &&
            ((this.positionCode==null && other.getPositionCode()==null) || 
             (this.positionCode!=null &&
              this.positionCode.equals(other.getPositionCode()))) &&
            ((this.applicationId==null && other.getApplicationId()==null) || 
             (this.applicationId!=null &&
              this.applicationId.equals(other.getApplicationId())));
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
        if (getTemplate() != null) {
            _hashCode += getTemplate().hashCode();
        }
        if (getDocumentWideData() != null) {
            _hashCode += getDocumentWideData().hashCode();
        }
        if (getReversingDetails() != null) {
            _hashCode += getReversingDetails().hashCode();
        }
        if (getRecurringDetails() != null) {
            _hashCode += getRecurringDetails().hashCode();
        }
        if (getControlTotal() != null) {
            _hashCode += getControlTotal().hashCode();
        }
        if (getWorkflowCode() != null) {
            _hashCode += getWorkflowCode().hashCode();
        }
        if (getPositionCode() != null) {
            _hashCode += getPositionCode().hashCode();
        }
        if (getApplicationId() != null) {
            _hashCode += getApplicationId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PostData.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "PostData"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("template");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "Template"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documentWideData");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "DocumentWideData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "DocumentWideData"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reversingDetails");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "ReversingDetails"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "ReversingPostData"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recurringDetails");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "RecurringDetails"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "RecurringPostData"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("controlTotal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "ControlTotal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("workflowCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "WorkflowCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("positionCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "PositionCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("applicationId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "ApplicationId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
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
