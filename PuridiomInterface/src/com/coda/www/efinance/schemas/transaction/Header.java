/**
 * Header.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.transaction;


/**
 * This element holds the document
 *             header.
 */
public class Header  implements java.io.Serializable {
    private com.coda.www.efinance.schemas.transaction.TxnKey key;

    /* The
     *                         TimeStamp value for this document in the
     *                         database. This TimeStamp provides protection
     * when two or more users attempt to simultaneously
     *                         edit the same document from the Intray. For
     * the
     *                         post of a new document, this should be set
     * to 0
     *                     (zero). */
    private short timeStamp;

    /* The date the document was
     *                         entered into the user's
     *                     system. */
    private java.util.Calendar inputDate;

    /* The time the document was
     *                         entered into the user's
     *                     system. */
    private java.lang.String inputTime;

    /* The year and period to which
     *                         the document is to be posted. This is a hard
     * year/period. */
    private java.lang.String period;

    /* The document
     *                     currency. */
    private java.lang.String curCode;

    /* The document
     *                     date. */
    private java.util.Calendar date;

    /* The
     *                         authorising initials. */
    private java.lang.String authoriser;

    /* The document's
     *                     description. */
    private java.lang.String description;

    /* The
     *                         company code for the original document
     *                     reference. */
    private java.lang.String originalCompany;

    /* The
     *                         document code for the original document
     *                     reference. */
    private java.lang.String originalCode;

    /* The
     *                         document number for the original document
     *                     reference. */
    private java.lang.String originalNumber;

    /* The code of the user
     *                         inputting this document. */
    private java.lang.String userCode;

    /* The code of the
     *                         customer/supplier element if there is one
     *                         customer/supplier on the document. If there
     * is
     *                         no customer/supplier element, or more than
     * one,
     *                         this element is blank. */
    private java.lang.String crossReference;

    /* This element holds details of
     *                         the workflow used by this
     *                     document. */
    private com.coda.www.efinance.schemas.transaction.HeaderWorkflow workflow;

    /* The URL of an attached image
     *                     file. */
    private java.lang.String attachFile;

    /* An
     *                     attachment. */
    private com.coda.www.common.schemas.attachment.Attachment attachment;

    /* Purchase invoice match
     *                     reference. */
    private java.lang.Integer purchaseInvoiceMatchReference;

    /* The destination of postings
     *                         when the document is used in
     *                     Input. */
    private java.lang.String location;

    /* The
     *                         sales invoicing status. */
    private java.lang.String salesInvoiceStatus;

    public Header() {
    }

    public Header(
           com.coda.www.efinance.schemas.transaction.TxnKey key,
           short timeStamp,
           java.util.Calendar inputDate,
           java.lang.String inputTime,
           java.lang.String period,
           java.lang.String curCode,
           java.util.Calendar date,
           java.lang.String authoriser,
           java.lang.String description,
           java.lang.String originalCompany,
           java.lang.String originalCode,
           java.lang.String originalNumber,
           java.lang.String userCode,
           java.lang.String crossReference,
           com.coda.www.efinance.schemas.transaction.HeaderWorkflow workflow,
           java.lang.String attachFile,
           com.coda.www.common.schemas.attachment.Attachment attachment,
           java.lang.Integer purchaseInvoiceMatchReference,
           java.lang.String location,
           java.lang.String salesInvoiceStatus) {
           this.key = key;
           this.timeStamp = timeStamp;
           this.inputDate = inputDate;
           this.inputTime = inputTime;
           this.period = period;
           this.curCode = curCode;
           this.date = date;
           this.authoriser = authoriser;
           this.description = description;
           this.originalCompany = originalCompany;
           this.originalCode = originalCode;
           this.originalNumber = originalNumber;
           this.userCode = userCode;
           this.crossReference = crossReference;
           this.workflow = workflow;
           this.attachFile = attachFile;
           this.attachment = attachment;
           this.purchaseInvoiceMatchReference = purchaseInvoiceMatchReference;
           this.location = location;
           this.salesInvoiceStatus = salesInvoiceStatus;
    }


    /**
     * Gets the key value for this Header.
     * 
     * @return key
     */
    public com.coda.www.efinance.schemas.transaction.TxnKey getKey() {
        return key;
    }


    /**
     * Sets the key value for this Header.
     * 
     * @param key
     */
    public void setKey(com.coda.www.efinance.schemas.transaction.TxnKey key) {
        this.key = key;
    }


    /**
     * Gets the timeStamp value for this Header.
     * 
     * @return timeStamp   * The
     *                         TimeStamp value for this document in the
     *                         database. This TimeStamp provides protection
     * when two or more users attempt to simultaneously
     *                         edit the same document from the Intray. For
     * the
     *                         post of a new document, this should be set
     * to 0
     *                     (zero).
     */
    public short getTimeStamp() {
        return timeStamp;
    }


    /**
     * Sets the timeStamp value for this Header.
     * 
     * @param timeStamp   * The
     *                         TimeStamp value for this document in the
     *                         database. This TimeStamp provides protection
     * when two or more users attempt to simultaneously
     *                         edit the same document from the Intray. For
     * the
     *                         post of a new document, this should be set
     * to 0
     *                     (zero).
     */
    public void setTimeStamp(short timeStamp) {
        this.timeStamp = timeStamp;
    }


    /**
     * Gets the inputDate value for this Header.
     * 
     * @return inputDate   * The date the document was
     *                         entered into the user's
     *                     system.
     */
    public java.util.Calendar getInputDate() {
        return inputDate;
    }


    /**
     * Sets the inputDate value for this Header.
     * 
     * @param inputDate   * The date the document was
     *                         entered into the user's
     *                     system.
     */
    public void setInputDate(java.util.Calendar inputDate) {
        this.inputDate = inputDate;
    }


    /**
     * Gets the inputTime value for this Header.
     * 
     * @return inputTime   * The time the document was
     *                         entered into the user's
     *                     system.
     */
    public java.lang.String getInputTime() {
        return inputTime;
    }


    /**
     * Sets the inputTime value for this Header.
     * 
     * @param inputTime   * The time the document was
     *                         entered into the user's
     *                     system.
     */
    public void setInputTime(java.lang.String inputTime) {
        this.inputTime = inputTime;
    }


    /**
     * Gets the period value for this Header.
     * 
     * @return period   * The year and period to which
     *                         the document is to be posted. This is a hard
     * year/period.
     */
    public java.lang.String getPeriod() {
        return period;
    }


    /**
     * Sets the period value for this Header.
     * 
     * @param period   * The year and period to which
     *                         the document is to be posted. This is a hard
     * year/period.
     */
    public void setPeriod(java.lang.String period) {
        this.period = period;
    }


    /**
     * Gets the curCode value for this Header.
     * 
     * @return curCode   * The document
     *                     currency.
     */
    public java.lang.String getCurCode() {
        return curCode;
    }


    /**
     * Sets the curCode value for this Header.
     * 
     * @param curCode   * The document
     *                     currency.
     */
    public void setCurCode(java.lang.String curCode) {
        this.curCode = curCode;
    }


    /**
     * Gets the date value for this Header.
     * 
     * @return date   * The document
     *                     date.
     */
    public java.util.Calendar getDate() {
        return date;
    }


    /**
     * Sets the date value for this Header.
     * 
     * @param date   * The document
     *                     date.
     */
    public void setDate(java.util.Calendar date) {
        this.date = date;
    }


    /**
     * Gets the authoriser value for this Header.
     * 
     * @return authoriser   * The
     *                         authorising initials.
     */
    public java.lang.String getAuthoriser() {
        return authoriser;
    }


    /**
     * Sets the authoriser value for this Header.
     * 
     * @param authoriser   * The
     *                         authorising initials.
     */
    public void setAuthoriser(java.lang.String authoriser) {
        this.authoriser = authoriser;
    }


    /**
     * Gets the description value for this Header.
     * 
     * @return description   * The document's
     *                     description.
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this Header.
     * 
     * @param description   * The document's
     *                     description.
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }


    /**
     * Gets the originalCompany value for this Header.
     * 
     * @return originalCompany   * The
     *                         company code for the original document
     *                     reference.
     */
    public java.lang.String getOriginalCompany() {
        return originalCompany;
    }


    /**
     * Sets the originalCompany value for this Header.
     * 
     * @param originalCompany   * The
     *                         company code for the original document
     *                     reference.
     */
    public void setOriginalCompany(java.lang.String originalCompany) {
        this.originalCompany = originalCompany;
    }


    /**
     * Gets the originalCode value for this Header.
     * 
     * @return originalCode   * The
     *                         document code for the original document
     *                     reference.
     */
    public java.lang.String getOriginalCode() {
        return originalCode;
    }


    /**
     * Sets the originalCode value for this Header.
     * 
     * @param originalCode   * The
     *                         document code for the original document
     *                     reference.
     */
    public void setOriginalCode(java.lang.String originalCode) {
        this.originalCode = originalCode;
    }


    /**
     * Gets the originalNumber value for this Header.
     * 
     * @return originalNumber   * The
     *                         document number for the original document
     *                     reference.
     */
    public java.lang.String getOriginalNumber() {
        return originalNumber;
    }


    /**
     * Sets the originalNumber value for this Header.
     * 
     * @param originalNumber   * The
     *                         document number for the original document
     *                     reference.
     */
    public void setOriginalNumber(java.lang.String originalNumber) {
        this.originalNumber = originalNumber;
    }


    /**
     * Gets the userCode value for this Header.
     * 
     * @return userCode   * The code of the user
     *                         inputting this document.
     */
    public java.lang.String getUserCode() {
        return userCode;
    }


    /**
     * Sets the userCode value for this Header.
     * 
     * @param userCode   * The code of the user
     *                         inputting this document.
     */
    public void setUserCode(java.lang.String userCode) {
        this.userCode = userCode;
    }


    /**
     * Gets the crossReference value for this Header.
     * 
     * @return crossReference   * The code of the
     *                         customer/supplier element if there is one
     *                         customer/supplier on the document. If there
     * is
     *                         no customer/supplier element, or more than
     * one,
     *                         this element is blank.
     */
    public java.lang.String getCrossReference() {
        return crossReference;
    }


    /**
     * Sets the crossReference value for this Header.
     * 
     * @param crossReference   * The code of the
     *                         customer/supplier element if there is one
     *                         customer/supplier on the document. If there
     * is
     *                         no customer/supplier element, or more than
     * one,
     *                         this element is blank.
     */
    public void setCrossReference(java.lang.String crossReference) {
        this.crossReference = crossReference;
    }


    /**
     * Gets the workflow value for this Header.
     * 
     * @return workflow   * This element holds details of
     *                         the workflow used by this
     *                     document.
     */
    public com.coda.www.efinance.schemas.transaction.HeaderWorkflow getWorkflow() {
        return workflow;
    }


    /**
     * Sets the workflow value for this Header.
     * 
     * @param workflow   * This element holds details of
     *                         the workflow used by this
     *                     document.
     */
    public void setWorkflow(com.coda.www.efinance.schemas.transaction.HeaderWorkflow workflow) {
        this.workflow = workflow;
    }


    /**
     * Gets the attachFile value for this Header.
     * 
     * @return attachFile   * The URL of an attached image
     *                     file.
     */
    public java.lang.String getAttachFile() {
        return attachFile;
    }


    /**
     * Sets the attachFile value for this Header.
     * 
     * @param attachFile   * The URL of an attached image
     *                     file.
     */
    public void setAttachFile(java.lang.String attachFile) {
        this.attachFile = attachFile;
    }


    /**
     * Gets the attachment value for this Header.
     * 
     * @return attachment   * An
     *                     attachment.
     */
    public com.coda.www.common.schemas.attachment.Attachment getAttachment() {
        return attachment;
    }


    /**
     * Sets the attachment value for this Header.
     * 
     * @param attachment   * An
     *                     attachment.
     */
    public void setAttachment(com.coda.www.common.schemas.attachment.Attachment attachment) {
        this.attachment = attachment;
    }


    /**
     * Gets the purchaseInvoiceMatchReference value for this Header.
     * 
     * @return purchaseInvoiceMatchReference   * Purchase invoice match
     *                     reference.
     */
    public java.lang.Integer getPurchaseInvoiceMatchReference() {
        return purchaseInvoiceMatchReference;
    }


    /**
     * Sets the purchaseInvoiceMatchReference value for this Header.
     * 
     * @param purchaseInvoiceMatchReference   * Purchase invoice match
     *                     reference.
     */
    public void setPurchaseInvoiceMatchReference(java.lang.Integer purchaseInvoiceMatchReference) {
        this.purchaseInvoiceMatchReference = purchaseInvoiceMatchReference;
    }


    /**
     * Gets the location value for this Header.
     * 
     * @return location   * The destination of postings
     *                         when the document is used in
     *                     Input.
     */
    public java.lang.String getLocation() {
        return location;
    }


    /**
     * Sets the location value for this Header.
     * 
     * @param location   * The destination of postings
     *                         when the document is used in
     *                     Input.
     */
    public void setLocation(java.lang.String location) {
        this.location = location;
    }


    /**
     * Gets the salesInvoiceStatus value for this Header.
     * 
     * @return salesInvoiceStatus   * The
     *                         sales invoicing status.
     */
    public java.lang.String getSalesInvoiceStatus() {
        return salesInvoiceStatus;
    }


    /**
     * Sets the salesInvoiceStatus value for this Header.
     * 
     * @param salesInvoiceStatus   * The
     *                         sales invoicing status.
     */
    public void setSalesInvoiceStatus(java.lang.String salesInvoiceStatus) {
        this.salesInvoiceStatus = salesInvoiceStatus;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Header)) return false;
        Header other = (Header) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.key==null && other.getKey()==null) || 
             (this.key!=null &&
              this.key.equals(other.getKey()))) &&
            this.timeStamp == other.getTimeStamp() &&
            ((this.inputDate==null && other.getInputDate()==null) || 
             (this.inputDate!=null &&
              this.inputDate.equals(other.getInputDate()))) &&
            ((this.inputTime==null && other.getInputTime()==null) || 
             (this.inputTime!=null &&
              this.inputTime.equals(other.getInputTime()))) &&
            ((this.period==null && other.getPeriod()==null) || 
             (this.period!=null &&
              this.period.equals(other.getPeriod()))) &&
            ((this.curCode==null && other.getCurCode()==null) || 
             (this.curCode!=null &&
              this.curCode.equals(other.getCurCode()))) &&
            ((this.date==null && other.getDate()==null) || 
             (this.date!=null &&
              this.date.equals(other.getDate()))) &&
            ((this.authoriser==null && other.getAuthoriser()==null) || 
             (this.authoriser!=null &&
              this.authoriser.equals(other.getAuthoriser()))) &&
            ((this.description==null && other.getDescription()==null) || 
             (this.description!=null &&
              this.description.equals(other.getDescription()))) &&
            ((this.originalCompany==null && other.getOriginalCompany()==null) || 
             (this.originalCompany!=null &&
              this.originalCompany.equals(other.getOriginalCompany()))) &&
            ((this.originalCode==null && other.getOriginalCode()==null) || 
             (this.originalCode!=null &&
              this.originalCode.equals(other.getOriginalCode()))) &&
            ((this.originalNumber==null && other.getOriginalNumber()==null) || 
             (this.originalNumber!=null &&
              this.originalNumber.equals(other.getOriginalNumber()))) &&
            ((this.userCode==null && other.getUserCode()==null) || 
             (this.userCode!=null &&
              this.userCode.equals(other.getUserCode()))) &&
            ((this.crossReference==null && other.getCrossReference()==null) || 
             (this.crossReference!=null &&
              this.crossReference.equals(other.getCrossReference()))) &&
            ((this.workflow==null && other.getWorkflow()==null) || 
             (this.workflow!=null &&
              this.workflow.equals(other.getWorkflow()))) &&
            ((this.attachFile==null && other.getAttachFile()==null) || 
             (this.attachFile!=null &&
              this.attachFile.equals(other.getAttachFile()))) &&
            ((this.attachment==null && other.getAttachment()==null) || 
             (this.attachment!=null &&
              this.attachment.equals(other.getAttachment()))) &&
            ((this.purchaseInvoiceMatchReference==null && other.getPurchaseInvoiceMatchReference()==null) || 
             (this.purchaseInvoiceMatchReference!=null &&
              this.purchaseInvoiceMatchReference.equals(other.getPurchaseInvoiceMatchReference()))) &&
            ((this.location==null && other.getLocation()==null) || 
             (this.location!=null &&
              this.location.equals(other.getLocation()))) &&
            ((this.salesInvoiceStatus==null && other.getSalesInvoiceStatus()==null) || 
             (this.salesInvoiceStatus!=null &&
              this.salesInvoiceStatus.equals(other.getSalesInvoiceStatus())));
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
        if (getKey() != null) {
            _hashCode += getKey().hashCode();
        }
        _hashCode += getTimeStamp();
        if (getInputDate() != null) {
            _hashCode += getInputDate().hashCode();
        }
        if (getInputTime() != null) {
            _hashCode += getInputTime().hashCode();
        }
        if (getPeriod() != null) {
            _hashCode += getPeriod().hashCode();
        }
        if (getCurCode() != null) {
            _hashCode += getCurCode().hashCode();
        }
        if (getDate() != null) {
            _hashCode += getDate().hashCode();
        }
        if (getAuthoriser() != null) {
            _hashCode += getAuthoriser().hashCode();
        }
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        if (getOriginalCompany() != null) {
            _hashCode += getOriginalCompany().hashCode();
        }
        if (getOriginalCode() != null) {
            _hashCode += getOriginalCode().hashCode();
        }
        if (getOriginalNumber() != null) {
            _hashCode += getOriginalNumber().hashCode();
        }
        if (getUserCode() != null) {
            _hashCode += getUserCode().hashCode();
        }
        if (getCrossReference() != null) {
            _hashCode += getCrossReference().hashCode();
        }
        if (getWorkflow() != null) {
            _hashCode += getWorkflow().hashCode();
        }
        if (getAttachFile() != null) {
            _hashCode += getAttachFile().hashCode();
        }
        if (getAttachment() != null) {
            _hashCode += getAttachment().hashCode();
        }
        if (getPurchaseInvoiceMatchReference() != null) {
            _hashCode += getPurchaseInvoiceMatchReference().hashCode();
        }
        if (getLocation() != null) {
            _hashCode += getLocation().hashCode();
        }
        if (getSalesInvoiceStatus() != null) {
            _hashCode += getSalesInvoiceStatus().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Header.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Header"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("key");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Key"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "txnKey"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timeStamp");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "TimeStamp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inputDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "InputDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inputTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "InputTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("period");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Period"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseYearPeriod"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("curCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "CurCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("date");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Date"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("authoriser");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Authoriser"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("description");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Description"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("originalCompany");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "OriginalCompany"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("originalCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "OriginalCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("originalNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "OriginalNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "UserCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("crossReference");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "CrossReference"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("workflow");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Workflow"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "HeaderWorkflow"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("attachFile");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "AttachFile"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("attachment");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Attachment"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/common/schemas/attachment", "Attachment"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("purchaseInvoiceMatchReference");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "PurchaseInvoiceMatchReference"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("location");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Location"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("salesInvoiceStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "SalesInvoiceStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum"));
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
