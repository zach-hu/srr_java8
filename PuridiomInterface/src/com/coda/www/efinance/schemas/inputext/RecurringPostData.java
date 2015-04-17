/**
 * RecurringPostData.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.inputext;


/**
 * Contains details that can be
 *                 specified when posting recurring documents, or when
 * checking that recurring documents are
 *             postable.
 */
public class RecurringPostData  extends com.coda.www.efinance.schemas.inputext.SpecialDocPostData  implements java.io.Serializable {
    private int numberOfDocs;

    /* The time lapse
     *                                 between the document date of each
     *                                 document. This determines when each
     * recurring document will be posted. Here
     *                                 you specify a soft date consisting
     * of
     *                                 four characters with the initial
     *                                 character representing days, months
     * or
     *                                 periods. For example, D007 indicates
     * 7
     *                                 days between postings, M002 indicates
     * 2
     *                                 months, P003 indicates 3
     *                             periods. */
    private java.lang.String phasing;

    /* The date rule to use
     *                                 for determining the document date
     * of the
     *                                 recurring documents. The rule you
     * can
     *                                 specify depends on what you have set
     * for
     *                             Phasing. */
    private java.lang.String dateRule;

    /* Determines the value
     *                                 of the recurring documents. The value
     * of
     *                                 the original document can be divided
     * equally between the recurring documents
     *                                 (divided), or each recurring document
     * can have the same value as the original
     *                                 document (equal). If you set this
     * to
     *                                 null, the default is applied which
     * is
     *                             equal. */
    private java.lang.String amountRule;

    /* A valid soft date
     *                                 determining the due date. This defaults
     * to the value from the document master
     *                                 (if one exists) or the value from
     * the
     *                                 element master. It is read-only if
     * the
     *                                 document master has DueDate/DocumentWide
     * set to TRUE, or if DueDate/Modifiable is
     *                                 set to not
     *                             modifiable. */
    private java.lang.String docWideDueTerms;

    /* A valid soft date
     *                                 determining the value date. This
     *                                 defaults to the value from the document
     * master (if one exists) or is blank. It
     *                                 is read-only if the document master
     * has
     *                                 ValueDate/DocumentWide set to TRUE,
     * or
     *                                 if ValueDate/Modifiable is set to
     * not
     *                             modifiable. */
    private java.lang.String docWideValueTerms;

    public RecurringPostData() {
    }

    public RecurringPostData(
           java.lang.String rateRule,
           int numberOfDocs,
           java.lang.String phasing,
           java.lang.String dateRule,
           java.lang.String amountRule,
           java.lang.String docWideDueTerms,
           java.lang.String docWideValueTerms) {
        super(
            rateRule);
        this.numberOfDocs = numberOfDocs;
        this.phasing = phasing;
        this.dateRule = dateRule;
        this.amountRule = amountRule;
        this.docWideDueTerms = docWideDueTerms;
        this.docWideValueTerms = docWideValueTerms;
    }


    /**
     * Gets the numberOfDocs value for this RecurringPostData.
     * 
     * @return numberOfDocs
     */
    public int getNumberOfDocs() {
        return numberOfDocs;
    }


    /**
     * Sets the numberOfDocs value for this RecurringPostData.
     * 
     * @param numberOfDocs
     */
    public void setNumberOfDocs(int numberOfDocs) {
        this.numberOfDocs = numberOfDocs;
    }


    /**
     * Gets the phasing value for this RecurringPostData.
     * 
     * @return phasing   * The time lapse
     *                                 between the document date of each
     *                                 document. This determines when each
     * recurring document will be posted. Here
     *                                 you specify a soft date consisting
     * of
     *                                 four characters with the initial
     *                                 character representing days, months
     * or
     *                                 periods. For example, D007 indicates
     * 7
     *                                 days between postings, M002 indicates
     * 2
     *                                 months, P003 indicates 3
     *                             periods.
     */
    public java.lang.String getPhasing() {
        return phasing;
    }


    /**
     * Sets the phasing value for this RecurringPostData.
     * 
     * @param phasing   * The time lapse
     *                                 between the document date of each
     *                                 document. This determines when each
     * recurring document will be posted. Here
     *                                 you specify a soft date consisting
     * of
     *                                 four characters with the initial
     *                                 character representing days, months
     * or
     *                                 periods. For example, D007 indicates
     * 7
     *                                 days between postings, M002 indicates
     * 2
     *                                 months, P003 indicates 3
     *                             periods.
     */
    public void setPhasing(java.lang.String phasing) {
        this.phasing = phasing;
    }


    /**
     * Gets the dateRule value for this RecurringPostData.
     * 
     * @return dateRule   * The date rule to use
     *                                 for determining the document date
     * of the
     *                                 recurring documents. The rule you
     * can
     *                                 specify depends on what you have set
     * for
     *                             Phasing.
     */
    public java.lang.String getDateRule() {
        return dateRule;
    }


    /**
     * Sets the dateRule value for this RecurringPostData.
     * 
     * @param dateRule   * The date rule to use
     *                                 for determining the document date
     * of the
     *                                 recurring documents. The rule you
     * can
     *                                 specify depends on what you have set
     * for
     *                             Phasing.
     */
    public void setDateRule(java.lang.String dateRule) {
        this.dateRule = dateRule;
    }


    /**
     * Gets the amountRule value for this RecurringPostData.
     * 
     * @return amountRule   * Determines the value
     *                                 of the recurring documents. The value
     * of
     *                                 the original document can be divided
     * equally between the recurring documents
     *                                 (divided), or each recurring document
     * can have the same value as the original
     *                                 document (equal). If you set this
     * to
     *                                 null, the default is applied which
     * is
     *                             equal.
     */
    public java.lang.String getAmountRule() {
        return amountRule;
    }


    /**
     * Sets the amountRule value for this RecurringPostData.
     * 
     * @param amountRule   * Determines the value
     *                                 of the recurring documents. The value
     * of
     *                                 the original document can be divided
     * equally between the recurring documents
     *                                 (divided), or each recurring document
     * can have the same value as the original
     *                                 document (equal). If you set this
     * to
     *                                 null, the default is applied which
     * is
     *                             equal.
     */
    public void setAmountRule(java.lang.String amountRule) {
        this.amountRule = amountRule;
    }


    /**
     * Gets the docWideDueTerms value for this RecurringPostData.
     * 
     * @return docWideDueTerms   * A valid soft date
     *                                 determining the due date. This defaults
     * to the value from the document master
     *                                 (if one exists) or the value from
     * the
     *                                 element master. It is read-only if
     * the
     *                                 document master has DueDate/DocumentWide
     * set to TRUE, or if DueDate/Modifiable is
     *                                 set to not
     *                             modifiable.
     */
    public java.lang.String getDocWideDueTerms() {
        return docWideDueTerms;
    }


    /**
     * Sets the docWideDueTerms value for this RecurringPostData.
     * 
     * @param docWideDueTerms   * A valid soft date
     *                                 determining the due date. This defaults
     * to the value from the document master
     *                                 (if one exists) or the value from
     * the
     *                                 element master. It is read-only if
     * the
     *                                 document master has DueDate/DocumentWide
     * set to TRUE, or if DueDate/Modifiable is
     *                                 set to not
     *                             modifiable.
     */
    public void setDocWideDueTerms(java.lang.String docWideDueTerms) {
        this.docWideDueTerms = docWideDueTerms;
    }


    /**
     * Gets the docWideValueTerms value for this RecurringPostData.
     * 
     * @return docWideValueTerms   * A valid soft date
     *                                 determining the value date. This
     *                                 defaults to the value from the document
     * master (if one exists) or is blank. It
     *                                 is read-only if the document master
     * has
     *                                 ValueDate/DocumentWide set to TRUE,
     * or
     *                                 if ValueDate/Modifiable is set to
     * not
     *                             modifiable.
     */
    public java.lang.String getDocWideValueTerms() {
        return docWideValueTerms;
    }


    /**
     * Sets the docWideValueTerms value for this RecurringPostData.
     * 
     * @param docWideValueTerms   * A valid soft date
     *                                 determining the value date. This
     *                                 defaults to the value from the document
     * master (if one exists) or is blank. It
     *                                 is read-only if the document master
     * has
     *                                 ValueDate/DocumentWide set to TRUE,
     * or
     *                                 if ValueDate/Modifiable is set to
     * not
     *                             modifiable.
     */
    public void setDocWideValueTerms(java.lang.String docWideValueTerms) {
        this.docWideValueTerms = docWideValueTerms;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RecurringPostData)) return false;
        RecurringPostData other = (RecurringPostData) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            this.numberOfDocs == other.getNumberOfDocs() &&
            ((this.phasing==null && other.getPhasing()==null) || 
             (this.phasing!=null &&
              this.phasing.equals(other.getPhasing()))) &&
            ((this.dateRule==null && other.getDateRule()==null) || 
             (this.dateRule!=null &&
              this.dateRule.equals(other.getDateRule()))) &&
            ((this.amountRule==null && other.getAmountRule()==null) || 
             (this.amountRule!=null &&
              this.amountRule.equals(other.getAmountRule()))) &&
            ((this.docWideDueTerms==null && other.getDocWideDueTerms()==null) || 
             (this.docWideDueTerms!=null &&
              this.docWideDueTerms.equals(other.getDocWideDueTerms()))) &&
            ((this.docWideValueTerms==null && other.getDocWideValueTerms()==null) || 
             (this.docWideValueTerms!=null &&
              this.docWideValueTerms.equals(other.getDocWideValueTerms())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        _hashCode += getNumberOfDocs();
        if (getPhasing() != null) {
            _hashCode += getPhasing().hashCode();
        }
        if (getDateRule() != null) {
            _hashCode += getDateRule().hashCode();
        }
        if (getAmountRule() != null) {
            _hashCode += getAmountRule().hashCode();
        }
        if (getDocWideDueTerms() != null) {
            _hashCode += getDocWideDueTerms().hashCode();
        }
        if (getDocWideValueTerms() != null) {
            _hashCode += getDocWideValueTerms().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RecurringPostData.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "RecurringPostData"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numberOfDocs");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "NumberOfDocs"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("phasing");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "Phasing"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dateRule");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "DateRule"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("amountRule");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "AmountRule"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("docWideDueTerms");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "DocWideDueTerms"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("docWideValueTerms");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "DocWideValueTerms"));
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
