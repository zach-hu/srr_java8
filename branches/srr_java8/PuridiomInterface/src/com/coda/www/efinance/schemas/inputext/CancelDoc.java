/**
 * CancelDoc.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.inputext;


/**
 * Contains parameters which can be
 *                 specified when cancelling a single document or multiple
 * documents.
 */
public class CancelDoc  implements java.io.Serializable {
    private java.lang.String cmpCode;

    /* The code of the document
     *                         master set up to produce cancelling
     *                     documents. */
    private java.lang.String cancellingDocCode;

    /* The
     *                         code of the document master used to input
     * the
     *                         document(s) you wish to
     *                     cancel. */
    private java.lang.String docCode;

    /* The
     *                         document number of the single document you
     * want
     *                         to cancel, or the first document number in
     * a
     *                         range if you want to cancel multiple
     *                     documents. */
    private java.lang.String docNumberFrom;

    /* The last document number in
     *                         the range if you want to cancel multiple
     *                         documents. This can be blank if you are
     *                         cancelling a single
     *                     document. */
    private java.lang.String docNumberTo;

    /* When
     *                         cancelling multiple documents, this contains
     * the
     *                         list of documents to be cancelled. The selected
     * documents must fall within the range specified
     *                         by DocNumberFrom and DocNumberTo. The document
     * numbers must be listed in ascending order and
     *                         must not be duplicated. */
    private java.lang.String[] selectedDocs;

    /* The date to be used as the
     *                         document date for the cancelling documents.
     * If
     *                         this is left blank, the original document
     * date
     *                         is used. */
    private java.util.Calendar docDate;

    /* The year and period to which
     *                         the cancelling documents are posted. If this
     * is
     *                         left blank, the original document year/period
     * is
     *                     used. */
    private java.lang.String period;

    /* When true, any documents
     *                         using InterCompany (that is, containing accounts
     * in more than one company) will be cancelled if
     *                         selected by the cancellation criteria. When
     * false, no InterCompany documents will be
     *                         cancelled. If this element is omitted, the
     * cancellation request will abort with a reason
     *                         message in CancelDocResults/InterCompanyWarning
     * if any InterCompany documents are
     *                     found. */
    private java.lang.Boolean includeInterCompanyDocs;

    /* An optional log title. All
     *                         attempts to cancel the specified documents
     * are
     *                         stored under this log title. */
    private java.lang.String logTitle;

    /* The
     *                         workflow process definition
     *                     code. */
    private java.lang.String workflowCode;

    /* The
     *                         position code within the position
     *                     hierarchy. */
    private java.lang.String positionCode;

    public CancelDoc() {
    }

    public CancelDoc(
           java.lang.String cmpCode,
           java.lang.String cancellingDocCode,
           java.lang.String docCode,
           java.lang.String docNumberFrom,
           java.lang.String docNumberTo,
           java.lang.String[] selectedDocs,
           java.util.Calendar docDate,
           java.lang.String period,
           java.lang.Boolean includeInterCompanyDocs,
           java.lang.String logTitle,
           java.lang.String workflowCode,
           java.lang.String positionCode) {
           this.cmpCode = cmpCode;
           this.cancellingDocCode = cancellingDocCode;
           this.docCode = docCode;
           this.docNumberFrom = docNumberFrom;
           this.docNumberTo = docNumberTo;
           this.selectedDocs = selectedDocs;
           this.docDate = docDate;
           this.period = period;
           this.includeInterCompanyDocs = includeInterCompanyDocs;
           this.logTitle = logTitle;
           this.workflowCode = workflowCode;
           this.positionCode = positionCode;
    }


    /**
     * Gets the cmpCode value for this CancelDoc.
     * 
     * @return cmpCode
     */
    public java.lang.String getCmpCode() {
        return cmpCode;
    }


    /**
     * Sets the cmpCode value for this CancelDoc.
     * 
     * @param cmpCode
     */
    public void setCmpCode(java.lang.String cmpCode) {
        this.cmpCode = cmpCode;
    }


    /**
     * Gets the cancellingDocCode value for this CancelDoc.
     * 
     * @return cancellingDocCode   * The code of the document
     *                         master set up to produce cancelling
     *                     documents.
     */
    public java.lang.String getCancellingDocCode() {
        return cancellingDocCode;
    }


    /**
     * Sets the cancellingDocCode value for this CancelDoc.
     * 
     * @param cancellingDocCode   * The code of the document
     *                         master set up to produce cancelling
     *                     documents.
     */
    public void setCancellingDocCode(java.lang.String cancellingDocCode) {
        this.cancellingDocCode = cancellingDocCode;
    }


    /**
     * Gets the docCode value for this CancelDoc.
     * 
     * @return docCode   * The
     *                         code of the document master used to input
     * the
     *                         document(s) you wish to
     *                     cancel.
     */
    public java.lang.String getDocCode() {
        return docCode;
    }


    /**
     * Sets the docCode value for this CancelDoc.
     * 
     * @param docCode   * The
     *                         code of the document master used to input
     * the
     *                         document(s) you wish to
     *                     cancel.
     */
    public void setDocCode(java.lang.String docCode) {
        this.docCode = docCode;
    }


    /**
     * Gets the docNumberFrom value for this CancelDoc.
     * 
     * @return docNumberFrom   * The
     *                         document number of the single document you
     * want
     *                         to cancel, or the first document number in
     * a
     *                         range if you want to cancel multiple
     *                     documents.
     */
    public java.lang.String getDocNumberFrom() {
        return docNumberFrom;
    }


    /**
     * Sets the docNumberFrom value for this CancelDoc.
     * 
     * @param docNumberFrom   * The
     *                         document number of the single document you
     * want
     *                         to cancel, or the first document number in
     * a
     *                         range if you want to cancel multiple
     *                     documents.
     */
    public void setDocNumberFrom(java.lang.String docNumberFrom) {
        this.docNumberFrom = docNumberFrom;
    }


    /**
     * Gets the docNumberTo value for this CancelDoc.
     * 
     * @return docNumberTo   * The last document number in
     *                         the range if you want to cancel multiple
     *                         documents. This can be blank if you are
     *                         cancelling a single
     *                     document.
     */
    public java.lang.String getDocNumberTo() {
        return docNumberTo;
    }


    /**
     * Sets the docNumberTo value for this CancelDoc.
     * 
     * @param docNumberTo   * The last document number in
     *                         the range if you want to cancel multiple
     *                         documents. This can be blank if you are
     *                         cancelling a single
     *                     document.
     */
    public void setDocNumberTo(java.lang.String docNumberTo) {
        this.docNumberTo = docNumberTo;
    }


    /**
     * Gets the selectedDocs value for this CancelDoc.
     * 
     * @return selectedDocs   * When
     *                         cancelling multiple documents, this contains
     * the
     *                         list of documents to be cancelled. The selected
     * documents must fall within the range specified
     *                         by DocNumberFrom and DocNumberTo. The document
     * numbers must be listed in ascending order and
     *                         must not be duplicated.
     */
    public java.lang.String[] getSelectedDocs() {
        return selectedDocs;
    }


    /**
     * Sets the selectedDocs value for this CancelDoc.
     * 
     * @param selectedDocs   * When
     *                         cancelling multiple documents, this contains
     * the
     *                         list of documents to be cancelled. The selected
     * documents must fall within the range specified
     *                         by DocNumberFrom and DocNumberTo. The document
     * numbers must be listed in ascending order and
     *                         must not be duplicated.
     */
    public void setSelectedDocs(java.lang.String[] selectedDocs) {
        this.selectedDocs = selectedDocs;
    }


    /**
     * Gets the docDate value for this CancelDoc.
     * 
     * @return docDate   * The date to be used as the
     *                         document date for the cancelling documents.
     * If
     *                         this is left blank, the original document
     * date
     *                         is used.
     */
    public java.util.Calendar getDocDate() {
        return docDate;
    }


    /**
     * Sets the docDate value for this CancelDoc.
     * 
     * @param docDate   * The date to be used as the
     *                         document date for the cancelling documents.
     * If
     *                         this is left blank, the original document
     * date
     *                         is used.
     */
    public void setDocDate(java.util.Calendar docDate) {
        this.docDate = docDate;
    }


    /**
     * Gets the period value for this CancelDoc.
     * 
     * @return period   * The year and period to which
     *                         the cancelling documents are posted. If this
     * is
     *                         left blank, the original document year/period
     * is
     *                     used.
     */
    public java.lang.String getPeriod() {
        return period;
    }


    /**
     * Sets the period value for this CancelDoc.
     * 
     * @param period   * The year and period to which
     *                         the cancelling documents are posted. If this
     * is
     *                         left blank, the original document year/period
     * is
     *                     used.
     */
    public void setPeriod(java.lang.String period) {
        this.period = period;
    }


    /**
     * Gets the includeInterCompanyDocs value for this CancelDoc.
     * 
     * @return includeInterCompanyDocs   * When true, any documents
     *                         using InterCompany (that is, containing accounts
     * in more than one company) will be cancelled if
     *                         selected by the cancellation criteria. When
     * false, no InterCompany documents will be
     *                         cancelled. If this element is omitted, the
     * cancellation request will abort with a reason
     *                         message in CancelDocResults/InterCompanyWarning
     * if any InterCompany documents are
     *                     found.
     */
    public java.lang.Boolean getIncludeInterCompanyDocs() {
        return includeInterCompanyDocs;
    }


    /**
     * Sets the includeInterCompanyDocs value for this CancelDoc.
     * 
     * @param includeInterCompanyDocs   * When true, any documents
     *                         using InterCompany (that is, containing accounts
     * in more than one company) will be cancelled if
     *                         selected by the cancellation criteria. When
     * false, no InterCompany documents will be
     *                         cancelled. If this element is omitted, the
     * cancellation request will abort with a reason
     *                         message in CancelDocResults/InterCompanyWarning
     * if any InterCompany documents are
     *                     found.
     */
    public void setIncludeInterCompanyDocs(java.lang.Boolean includeInterCompanyDocs) {
        this.includeInterCompanyDocs = includeInterCompanyDocs;
    }


    /**
     * Gets the logTitle value for this CancelDoc.
     * 
     * @return logTitle   * An optional log title. All
     *                         attempts to cancel the specified documents
     * are
     *                         stored under this log title.
     */
    public java.lang.String getLogTitle() {
        return logTitle;
    }


    /**
     * Sets the logTitle value for this CancelDoc.
     * 
     * @param logTitle   * An optional log title. All
     *                         attempts to cancel the specified documents
     * are
     *                         stored under this log title.
     */
    public void setLogTitle(java.lang.String logTitle) {
        this.logTitle = logTitle;
    }


    /**
     * Gets the workflowCode value for this CancelDoc.
     * 
     * @return workflowCode   * The
     *                         workflow process definition
     *                     code.
     */
    public java.lang.String getWorkflowCode() {
        return workflowCode;
    }


    /**
     * Sets the workflowCode value for this CancelDoc.
     * 
     * @param workflowCode   * The
     *                         workflow process definition
     *                     code.
     */
    public void setWorkflowCode(java.lang.String workflowCode) {
        this.workflowCode = workflowCode;
    }


    /**
     * Gets the positionCode value for this CancelDoc.
     * 
     * @return positionCode   * The
     *                         position code within the position
     *                     hierarchy.
     */
    public java.lang.String getPositionCode() {
        return positionCode;
    }


    /**
     * Sets the positionCode value for this CancelDoc.
     * 
     * @param positionCode   * The
     *                         position code within the position
     *                     hierarchy.
     */
    public void setPositionCode(java.lang.String positionCode) {
        this.positionCode = positionCode;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CancelDoc)) return false;
        CancelDoc other = (CancelDoc) obj;
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
            ((this.cancellingDocCode==null && other.getCancellingDocCode()==null) || 
             (this.cancellingDocCode!=null &&
              this.cancellingDocCode.equals(other.getCancellingDocCode()))) &&
            ((this.docCode==null && other.getDocCode()==null) || 
             (this.docCode!=null &&
              this.docCode.equals(other.getDocCode()))) &&
            ((this.docNumberFrom==null && other.getDocNumberFrom()==null) || 
             (this.docNumberFrom!=null &&
              this.docNumberFrom.equals(other.getDocNumberFrom()))) &&
            ((this.docNumberTo==null && other.getDocNumberTo()==null) || 
             (this.docNumberTo!=null &&
              this.docNumberTo.equals(other.getDocNumberTo()))) &&
            ((this.selectedDocs==null && other.getSelectedDocs()==null) || 
             (this.selectedDocs!=null &&
              java.util.Arrays.equals(this.selectedDocs, other.getSelectedDocs()))) &&
            ((this.docDate==null && other.getDocDate()==null) || 
             (this.docDate!=null &&
              this.docDate.equals(other.getDocDate()))) &&
            ((this.period==null && other.getPeriod()==null) || 
             (this.period!=null &&
              this.period.equals(other.getPeriod()))) &&
            ((this.includeInterCompanyDocs==null && other.getIncludeInterCompanyDocs()==null) || 
             (this.includeInterCompanyDocs!=null &&
              this.includeInterCompanyDocs.equals(other.getIncludeInterCompanyDocs()))) &&
            ((this.logTitle==null && other.getLogTitle()==null) || 
             (this.logTitle!=null &&
              this.logTitle.equals(other.getLogTitle()))) &&
            ((this.workflowCode==null && other.getWorkflowCode()==null) || 
             (this.workflowCode!=null &&
              this.workflowCode.equals(other.getWorkflowCode()))) &&
            ((this.positionCode==null && other.getPositionCode()==null) || 
             (this.positionCode!=null &&
              this.positionCode.equals(other.getPositionCode())));
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
        if (getCancellingDocCode() != null) {
            _hashCode += getCancellingDocCode().hashCode();
        }
        if (getDocCode() != null) {
            _hashCode += getDocCode().hashCode();
        }
        if (getDocNumberFrom() != null) {
            _hashCode += getDocNumberFrom().hashCode();
        }
        if (getDocNumberTo() != null) {
            _hashCode += getDocNumberTo().hashCode();
        }
        if (getSelectedDocs() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSelectedDocs());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSelectedDocs(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getDocDate() != null) {
            _hashCode += getDocDate().hashCode();
        }
        if (getPeriod() != null) {
            _hashCode += getPeriod().hashCode();
        }
        if (getIncludeInterCompanyDocs() != null) {
            _hashCode += getIncludeInterCompanyDocs().hashCode();
        }
        if (getLogTitle() != null) {
            _hashCode += getLogTitle().hashCode();
        }
        if (getWorkflowCode() != null) {
            _hashCode += getWorkflowCode().hashCode();
        }
        if (getPositionCode() != null) {
            _hashCode += getPositionCode().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CancelDoc.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "CancelDoc"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cmpCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "CmpCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cancellingDocCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "CancellingDocCode"));
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
        elemField.setFieldName("docNumberFrom");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "DocNumberFrom"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("docNumberTo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "DocNumberTo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("selectedDocs");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "SelectedDocs"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeDocNum"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "DocNumber"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("docDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "DocDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("period");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "Period"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseYearPeriod"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("includeInterCompanyDocs");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "IncludeInterCompanyDocs"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("logTitle");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "LogTitle"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
