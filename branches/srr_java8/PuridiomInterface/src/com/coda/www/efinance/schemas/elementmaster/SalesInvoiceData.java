/**
 * SalesInvoiceData.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.elementmaster;


/**
 * This element contains Sales Invoicing
 *             data.
 */
public class SalesInvoiceData  implements java.io.Serializable {
    private boolean customerStop;

    /* If set, indicates that this
     *                         customer uses self billing. */
    private boolean selfBillingCustomer;

    /* If set, indicates that you
     *                         want to consolidate the invoice based on account
     * code. */
    private boolean consolidatePostByAcc;

    /* The
     *                         charge master for this
     *                     customer. */
    private java.lang.String chargeCode;

    /* The
     *                         discount master for this
     *                     customer. */
    private java.lang.String discountCode;

    /* The language you want to use
     *                         when printing the invoice. */
    private java.lang.String localeLang;

    /* The
     *                         output device master to use to preview invoices
     * sent to this customer. */
    private java.lang.String previewOutputDevCode;

    /* The
     *                         print format master to use to preview invoices
     * sent to this customer. */
    private java.lang.String previewPrintFormatCode;

    /* The
     *                         output device master to use to print invoices
     * sent to this customer. */
    private java.lang.String invoiceOutputDevCode;

    /* The
     *                         print format master to use to print invoices
     * sent to this customer. */
    private java.lang.String invoicePrintFormatCode;

    /* The
     *                         output device master to use to print credit
     * notes sent to this customer. */
    private java.lang.String creditNoteOutputDevCode;

    /* The
     *                         print format master to use to print credit
     * notes
     *                         sent to this customer. */
    private java.lang.String creditNotePrintFormatCode;

    /* If set, indicates that
     *                         preview invoices should be sent to the Portal
     * for the customer to see. */
    private boolean previewSendToPortal;

    /* If set, indicates that
     *                         invoices should be sent to the Portal for
     * the
     *                         customer to see. */
    private boolean invoiceSendToPortal;

    /* If set, indicates that credit
     *                         notes should be sent to the Portal for the
     * customer to see. */
    private boolean creditNoteSendToPortal;

    /* This
     *                         element contains a list of user-defined fields
     * on a sales invoice header. */
    private com.coda.www.efinance.schemas.elementmaster.SalesInvoiceUDFRow[] salesInvoiceHeaderUDFList;

    /* This
     *                         element contains a list of user-defined fields
     * on a sales invoice line. */
    private com.coda.www.efinance.schemas.elementmaster.SalesInvoiceUDFRow[] salesInvoiceLineUDFList;

    public SalesInvoiceData() {
    }

    public SalesInvoiceData(
           boolean customerStop,
           boolean selfBillingCustomer,
           boolean consolidatePostByAcc,
           java.lang.String chargeCode,
           java.lang.String discountCode,
           java.lang.String localeLang,
           java.lang.String previewOutputDevCode,
           java.lang.String previewPrintFormatCode,
           java.lang.String invoiceOutputDevCode,
           java.lang.String invoicePrintFormatCode,
           java.lang.String creditNoteOutputDevCode,
           java.lang.String creditNotePrintFormatCode,
           boolean previewSendToPortal,
           boolean invoiceSendToPortal,
           boolean creditNoteSendToPortal,
           com.coda.www.efinance.schemas.elementmaster.SalesInvoiceUDFRow[] salesInvoiceHeaderUDFList,
           com.coda.www.efinance.schemas.elementmaster.SalesInvoiceUDFRow[] salesInvoiceLineUDFList) {
           this.customerStop = customerStop;
           this.selfBillingCustomer = selfBillingCustomer;
           this.consolidatePostByAcc = consolidatePostByAcc;
           this.chargeCode = chargeCode;
           this.discountCode = discountCode;
           this.localeLang = localeLang;
           this.previewOutputDevCode = previewOutputDevCode;
           this.previewPrintFormatCode = previewPrintFormatCode;
           this.invoiceOutputDevCode = invoiceOutputDevCode;
           this.invoicePrintFormatCode = invoicePrintFormatCode;
           this.creditNoteOutputDevCode = creditNoteOutputDevCode;
           this.creditNotePrintFormatCode = creditNotePrintFormatCode;
           this.previewSendToPortal = previewSendToPortal;
           this.invoiceSendToPortal = invoiceSendToPortal;
           this.creditNoteSendToPortal = creditNoteSendToPortal;
           this.salesInvoiceHeaderUDFList = salesInvoiceHeaderUDFList;
           this.salesInvoiceLineUDFList = salesInvoiceLineUDFList;
    }


    /**
     * Gets the customerStop value for this SalesInvoiceData.
     * 
     * @return customerStop
     */
    public boolean isCustomerStop() {
        return customerStop;
    }


    /**
     * Sets the customerStop value for this SalesInvoiceData.
     * 
     * @param customerStop
     */
    public void setCustomerStop(boolean customerStop) {
        this.customerStop = customerStop;
    }


    /**
     * Gets the selfBillingCustomer value for this SalesInvoiceData.
     * 
     * @return selfBillingCustomer   * If set, indicates that this
     *                         customer uses self billing.
     */
    public boolean isSelfBillingCustomer() {
        return selfBillingCustomer;
    }


    /**
     * Sets the selfBillingCustomer value for this SalesInvoiceData.
     * 
     * @param selfBillingCustomer   * If set, indicates that this
     *                         customer uses self billing.
     */
    public void setSelfBillingCustomer(boolean selfBillingCustomer) {
        this.selfBillingCustomer = selfBillingCustomer;
    }


    /**
     * Gets the consolidatePostByAcc value for this SalesInvoiceData.
     * 
     * @return consolidatePostByAcc   * If set, indicates that you
     *                         want to consolidate the invoice based on account
     * code.
     */
    public boolean isConsolidatePostByAcc() {
        return consolidatePostByAcc;
    }


    /**
     * Sets the consolidatePostByAcc value for this SalesInvoiceData.
     * 
     * @param consolidatePostByAcc   * If set, indicates that you
     *                         want to consolidate the invoice based on account
     * code.
     */
    public void setConsolidatePostByAcc(boolean consolidatePostByAcc) {
        this.consolidatePostByAcc = consolidatePostByAcc;
    }


    /**
     * Gets the chargeCode value for this SalesInvoiceData.
     * 
     * @return chargeCode   * The
     *                         charge master for this
     *                     customer.
     */
    public java.lang.String getChargeCode() {
        return chargeCode;
    }


    /**
     * Sets the chargeCode value for this SalesInvoiceData.
     * 
     * @param chargeCode   * The
     *                         charge master for this
     *                     customer.
     */
    public void setChargeCode(java.lang.String chargeCode) {
        this.chargeCode = chargeCode;
    }


    /**
     * Gets the discountCode value for this SalesInvoiceData.
     * 
     * @return discountCode   * The
     *                         discount master for this
     *                     customer.
     */
    public java.lang.String getDiscountCode() {
        return discountCode;
    }


    /**
     * Sets the discountCode value for this SalesInvoiceData.
     * 
     * @param discountCode   * The
     *                         discount master for this
     *                     customer.
     */
    public void setDiscountCode(java.lang.String discountCode) {
        this.discountCode = discountCode;
    }


    /**
     * Gets the localeLang value for this SalesInvoiceData.
     * 
     * @return localeLang   * The language you want to use
     *                         when printing the invoice.
     */
    public java.lang.String getLocaleLang() {
        return localeLang;
    }


    /**
     * Sets the localeLang value for this SalesInvoiceData.
     * 
     * @param localeLang   * The language you want to use
     *                         when printing the invoice.
     */
    public void setLocaleLang(java.lang.String localeLang) {
        this.localeLang = localeLang;
    }


    /**
     * Gets the previewOutputDevCode value for this SalesInvoiceData.
     * 
     * @return previewOutputDevCode   * The
     *                         output device master to use to preview invoices
     * sent to this customer.
     */
    public java.lang.String getPreviewOutputDevCode() {
        return previewOutputDevCode;
    }


    /**
     * Sets the previewOutputDevCode value for this SalesInvoiceData.
     * 
     * @param previewOutputDevCode   * The
     *                         output device master to use to preview invoices
     * sent to this customer.
     */
    public void setPreviewOutputDevCode(java.lang.String previewOutputDevCode) {
        this.previewOutputDevCode = previewOutputDevCode;
    }


    /**
     * Gets the previewPrintFormatCode value for this SalesInvoiceData.
     * 
     * @return previewPrintFormatCode   * The
     *                         print format master to use to preview invoices
     * sent to this customer.
     */
    public java.lang.String getPreviewPrintFormatCode() {
        return previewPrintFormatCode;
    }


    /**
     * Sets the previewPrintFormatCode value for this SalesInvoiceData.
     * 
     * @param previewPrintFormatCode   * The
     *                         print format master to use to preview invoices
     * sent to this customer.
     */
    public void setPreviewPrintFormatCode(java.lang.String previewPrintFormatCode) {
        this.previewPrintFormatCode = previewPrintFormatCode;
    }


    /**
     * Gets the invoiceOutputDevCode value for this SalesInvoiceData.
     * 
     * @return invoiceOutputDevCode   * The
     *                         output device master to use to print invoices
     * sent to this customer.
     */
    public java.lang.String getInvoiceOutputDevCode() {
        return invoiceOutputDevCode;
    }


    /**
     * Sets the invoiceOutputDevCode value for this SalesInvoiceData.
     * 
     * @param invoiceOutputDevCode   * The
     *                         output device master to use to print invoices
     * sent to this customer.
     */
    public void setInvoiceOutputDevCode(java.lang.String invoiceOutputDevCode) {
        this.invoiceOutputDevCode = invoiceOutputDevCode;
    }


    /**
     * Gets the invoicePrintFormatCode value for this SalesInvoiceData.
     * 
     * @return invoicePrintFormatCode   * The
     *                         print format master to use to print invoices
     * sent to this customer.
     */
    public java.lang.String getInvoicePrintFormatCode() {
        return invoicePrintFormatCode;
    }


    /**
     * Sets the invoicePrintFormatCode value for this SalesInvoiceData.
     * 
     * @param invoicePrintFormatCode   * The
     *                         print format master to use to print invoices
     * sent to this customer.
     */
    public void setInvoicePrintFormatCode(java.lang.String invoicePrintFormatCode) {
        this.invoicePrintFormatCode = invoicePrintFormatCode;
    }


    /**
     * Gets the creditNoteOutputDevCode value for this SalesInvoiceData.
     * 
     * @return creditNoteOutputDevCode   * The
     *                         output device master to use to print credit
     * notes sent to this customer.
     */
    public java.lang.String getCreditNoteOutputDevCode() {
        return creditNoteOutputDevCode;
    }


    /**
     * Sets the creditNoteOutputDevCode value for this SalesInvoiceData.
     * 
     * @param creditNoteOutputDevCode   * The
     *                         output device master to use to print credit
     * notes sent to this customer.
     */
    public void setCreditNoteOutputDevCode(java.lang.String creditNoteOutputDevCode) {
        this.creditNoteOutputDevCode = creditNoteOutputDevCode;
    }


    /**
     * Gets the creditNotePrintFormatCode value for this SalesInvoiceData.
     * 
     * @return creditNotePrintFormatCode   * The
     *                         print format master to use to print credit
     * notes
     *                         sent to this customer.
     */
    public java.lang.String getCreditNotePrintFormatCode() {
        return creditNotePrintFormatCode;
    }


    /**
     * Sets the creditNotePrintFormatCode value for this SalesInvoiceData.
     * 
     * @param creditNotePrintFormatCode   * The
     *                         print format master to use to print credit
     * notes
     *                         sent to this customer.
     */
    public void setCreditNotePrintFormatCode(java.lang.String creditNotePrintFormatCode) {
        this.creditNotePrintFormatCode = creditNotePrintFormatCode;
    }


    /**
     * Gets the previewSendToPortal value for this SalesInvoiceData.
     * 
     * @return previewSendToPortal   * If set, indicates that
     *                         preview invoices should be sent to the Portal
     * for the customer to see.
     */
    public boolean isPreviewSendToPortal() {
        return previewSendToPortal;
    }


    /**
     * Sets the previewSendToPortal value for this SalesInvoiceData.
     * 
     * @param previewSendToPortal   * If set, indicates that
     *                         preview invoices should be sent to the Portal
     * for the customer to see.
     */
    public void setPreviewSendToPortal(boolean previewSendToPortal) {
        this.previewSendToPortal = previewSendToPortal;
    }


    /**
     * Gets the invoiceSendToPortal value for this SalesInvoiceData.
     * 
     * @return invoiceSendToPortal   * If set, indicates that
     *                         invoices should be sent to the Portal for
     * the
     *                         customer to see.
     */
    public boolean isInvoiceSendToPortal() {
        return invoiceSendToPortal;
    }


    /**
     * Sets the invoiceSendToPortal value for this SalesInvoiceData.
     * 
     * @param invoiceSendToPortal   * If set, indicates that
     *                         invoices should be sent to the Portal for
     * the
     *                         customer to see.
     */
    public void setInvoiceSendToPortal(boolean invoiceSendToPortal) {
        this.invoiceSendToPortal = invoiceSendToPortal;
    }


    /**
     * Gets the creditNoteSendToPortal value for this SalesInvoiceData.
     * 
     * @return creditNoteSendToPortal   * If set, indicates that credit
     *                         notes should be sent to the Portal for the
     * customer to see.
     */
    public boolean isCreditNoteSendToPortal() {
        return creditNoteSendToPortal;
    }


    /**
     * Sets the creditNoteSendToPortal value for this SalesInvoiceData.
     * 
     * @param creditNoteSendToPortal   * If set, indicates that credit
     *                         notes should be sent to the Portal for the
     * customer to see.
     */
    public void setCreditNoteSendToPortal(boolean creditNoteSendToPortal) {
        this.creditNoteSendToPortal = creditNoteSendToPortal;
    }


    /**
     * Gets the salesInvoiceHeaderUDFList value for this SalesInvoiceData.
     * 
     * @return salesInvoiceHeaderUDFList   * This
     *                         element contains a list of user-defined fields
     * on a sales invoice header.
     */
    public com.coda.www.efinance.schemas.elementmaster.SalesInvoiceUDFRow[] getSalesInvoiceHeaderUDFList() {
        return salesInvoiceHeaderUDFList;
    }


    /**
     * Sets the salesInvoiceHeaderUDFList value for this SalesInvoiceData.
     * 
     * @param salesInvoiceHeaderUDFList   * This
     *                         element contains a list of user-defined fields
     * on a sales invoice header.
     */
    public void setSalesInvoiceHeaderUDFList(com.coda.www.efinance.schemas.elementmaster.SalesInvoiceUDFRow[] salesInvoiceHeaderUDFList) {
        this.salesInvoiceHeaderUDFList = salesInvoiceHeaderUDFList;
    }


    /**
     * Gets the salesInvoiceLineUDFList value for this SalesInvoiceData.
     * 
     * @return salesInvoiceLineUDFList   * This
     *                         element contains a list of user-defined fields
     * on a sales invoice line.
     */
    public com.coda.www.efinance.schemas.elementmaster.SalesInvoiceUDFRow[] getSalesInvoiceLineUDFList() {
        return salesInvoiceLineUDFList;
    }


    /**
     * Sets the salesInvoiceLineUDFList value for this SalesInvoiceData.
     * 
     * @param salesInvoiceLineUDFList   * This
     *                         element contains a list of user-defined fields
     * on a sales invoice line.
     */
    public void setSalesInvoiceLineUDFList(com.coda.www.efinance.schemas.elementmaster.SalesInvoiceUDFRow[] salesInvoiceLineUDFList) {
        this.salesInvoiceLineUDFList = salesInvoiceLineUDFList;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SalesInvoiceData)) return false;
        SalesInvoiceData other = (SalesInvoiceData) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.customerStop == other.isCustomerStop() &&
            this.selfBillingCustomer == other.isSelfBillingCustomer() &&
            this.consolidatePostByAcc == other.isConsolidatePostByAcc() &&
            ((this.chargeCode==null && other.getChargeCode()==null) || 
             (this.chargeCode!=null &&
              this.chargeCode.equals(other.getChargeCode()))) &&
            ((this.discountCode==null && other.getDiscountCode()==null) || 
             (this.discountCode!=null &&
              this.discountCode.equals(other.getDiscountCode()))) &&
            ((this.localeLang==null && other.getLocaleLang()==null) || 
             (this.localeLang!=null &&
              this.localeLang.equals(other.getLocaleLang()))) &&
            ((this.previewOutputDevCode==null && other.getPreviewOutputDevCode()==null) || 
             (this.previewOutputDevCode!=null &&
              this.previewOutputDevCode.equals(other.getPreviewOutputDevCode()))) &&
            ((this.previewPrintFormatCode==null && other.getPreviewPrintFormatCode()==null) || 
             (this.previewPrintFormatCode!=null &&
              this.previewPrintFormatCode.equals(other.getPreviewPrintFormatCode()))) &&
            ((this.invoiceOutputDevCode==null && other.getInvoiceOutputDevCode()==null) || 
             (this.invoiceOutputDevCode!=null &&
              this.invoiceOutputDevCode.equals(other.getInvoiceOutputDevCode()))) &&
            ((this.invoicePrintFormatCode==null && other.getInvoicePrintFormatCode()==null) || 
             (this.invoicePrintFormatCode!=null &&
              this.invoicePrintFormatCode.equals(other.getInvoicePrintFormatCode()))) &&
            ((this.creditNoteOutputDevCode==null && other.getCreditNoteOutputDevCode()==null) || 
             (this.creditNoteOutputDevCode!=null &&
              this.creditNoteOutputDevCode.equals(other.getCreditNoteOutputDevCode()))) &&
            ((this.creditNotePrintFormatCode==null && other.getCreditNotePrintFormatCode()==null) || 
             (this.creditNotePrintFormatCode!=null &&
              this.creditNotePrintFormatCode.equals(other.getCreditNotePrintFormatCode()))) &&
            this.previewSendToPortal == other.isPreviewSendToPortal() &&
            this.invoiceSendToPortal == other.isInvoiceSendToPortal() &&
            this.creditNoteSendToPortal == other.isCreditNoteSendToPortal() &&
            ((this.salesInvoiceHeaderUDFList==null && other.getSalesInvoiceHeaderUDFList()==null) || 
             (this.salesInvoiceHeaderUDFList!=null &&
              java.util.Arrays.equals(this.salesInvoiceHeaderUDFList, other.getSalesInvoiceHeaderUDFList()))) &&
            ((this.salesInvoiceLineUDFList==null && other.getSalesInvoiceLineUDFList()==null) || 
             (this.salesInvoiceLineUDFList!=null &&
              java.util.Arrays.equals(this.salesInvoiceLineUDFList, other.getSalesInvoiceLineUDFList())));
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
        _hashCode += (isCustomerStop() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isSelfBillingCustomer() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isConsolidatePostByAcc() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getChargeCode() != null) {
            _hashCode += getChargeCode().hashCode();
        }
        if (getDiscountCode() != null) {
            _hashCode += getDiscountCode().hashCode();
        }
        if (getLocaleLang() != null) {
            _hashCode += getLocaleLang().hashCode();
        }
        if (getPreviewOutputDevCode() != null) {
            _hashCode += getPreviewOutputDevCode().hashCode();
        }
        if (getPreviewPrintFormatCode() != null) {
            _hashCode += getPreviewPrintFormatCode().hashCode();
        }
        if (getInvoiceOutputDevCode() != null) {
            _hashCode += getInvoiceOutputDevCode().hashCode();
        }
        if (getInvoicePrintFormatCode() != null) {
            _hashCode += getInvoicePrintFormatCode().hashCode();
        }
        if (getCreditNoteOutputDevCode() != null) {
            _hashCode += getCreditNoteOutputDevCode().hashCode();
        }
        if (getCreditNotePrintFormatCode() != null) {
            _hashCode += getCreditNotePrintFormatCode().hashCode();
        }
        _hashCode += (isPreviewSendToPortal() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isInvoiceSendToPortal() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isCreditNoteSendToPortal() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getSalesInvoiceHeaderUDFList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSalesInvoiceHeaderUDFList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSalesInvoiceHeaderUDFList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getSalesInvoiceLineUDFList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSalesInvoiceLineUDFList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSalesInvoiceLineUDFList(), i);
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
        new org.apache.axis.description.TypeDesc(SalesInvoiceData.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "SalesInvoiceData"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customerStop");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "CustomerStop"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("selfBillingCustomer");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "SelfBillingCustomer"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("consolidatePostByAcc");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "ConsolidatePostByAcc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("chargeCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "ChargeCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("discountCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "DiscountCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("localeLang");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "LocaleLang"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("previewOutputDevCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "PreviewOutputDevCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("previewPrintFormatCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "PreviewPrintFormatCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("invoiceOutputDevCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "InvoiceOutputDevCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("invoicePrintFormatCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "InvoicePrintFormatCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("creditNoteOutputDevCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "CreditNoteOutputDevCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("creditNotePrintFormatCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "CreditNotePrintFormatCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("previewSendToPortal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "PreviewSendToPortal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("invoiceSendToPortal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "InvoiceSendToPortal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("creditNoteSendToPortal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "CreditNoteSendToPortal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("salesInvoiceHeaderUDFList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "SalesInvoiceHeaderUDFList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "SalesInvoiceUDFRow"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "SalesInvoiceUDFRow"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("salesInvoiceLineUDFList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "SalesInvoiceLineUDFList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "SalesInvoiceUDFRow"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "SalesInvoiceUDFRow"));
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
