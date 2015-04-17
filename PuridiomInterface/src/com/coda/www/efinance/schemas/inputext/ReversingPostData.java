/**
 * ReversingPostData.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.inputext;


/**
 * Contains details that can be
 *                 specified when posting reversing documents, or when
 * checking that reversing documents are
 *             postable.
 */
public class ReversingPostData  extends com.coda.www.efinance.schemas.inputext.SpecialDocPostData  implements java.io.Serializable {
    private java.util.Calendar docDate;

    /* The year and period
     *                                 to which the reversing entry will
     * be
     *                                 posted. This defaults to the period
     * following that of the original document.
     *                                 If the period on the original document
     * is the last posting period of the year,
     *                                 the reversing entry will be posted
     * to
     *                                 the first posting period in the new
     * year. */
    private java.lang.String period;

    /* When TRUE, lines on
     *                                 the reversing document are automatically
     * matched against lines on the original
     *                             document. */
    private boolean match;

    public ReversingPostData() {
    }

    public ReversingPostData(
           java.lang.String rateRule,
           java.util.Calendar docDate,
           java.lang.String period,
           boolean match) {
        super(
            rateRule);
        this.docDate = docDate;
        this.period = period;
        this.match = match;
    }


    /**
     * Gets the docDate value for this ReversingPostData.
     * 
     * @return docDate
     */
    public java.util.Calendar getDocDate() {
        return docDate;
    }


    /**
     * Sets the docDate value for this ReversingPostData.
     * 
     * @param docDate
     */
    public void setDocDate(java.util.Calendar docDate) {
        this.docDate = docDate;
    }


    /**
     * Gets the period value for this ReversingPostData.
     * 
     * @return period   * The year and period
     *                                 to which the reversing entry will
     * be
     *                                 posted. This defaults to the period
     * following that of the original document.
     *                                 If the period on the original document
     * is the last posting period of the year,
     *                                 the reversing entry will be posted
     * to
     *                                 the first posting period in the new
     * year.
     */
    public java.lang.String getPeriod() {
        return period;
    }


    /**
     * Sets the period value for this ReversingPostData.
     * 
     * @param period   * The year and period
     *                                 to which the reversing entry will
     * be
     *                                 posted. This defaults to the period
     * following that of the original document.
     *                                 If the period on the original document
     * is the last posting period of the year,
     *                                 the reversing entry will be posted
     * to
     *                                 the first posting period in the new
     * year.
     */
    public void setPeriod(java.lang.String period) {
        this.period = period;
    }


    /**
     * Gets the match value for this ReversingPostData.
     * 
     * @return match   * When TRUE, lines on
     *                                 the reversing document are automatically
     * matched against lines on the original
     *                             document.
     */
    public boolean isMatch() {
        return match;
    }


    /**
     * Sets the match value for this ReversingPostData.
     * 
     * @param match   * When TRUE, lines on
     *                                 the reversing document are automatically
     * matched against lines on the original
     *                             document.
     */
    public void setMatch(boolean match) {
        this.match = match;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ReversingPostData)) return false;
        ReversingPostData other = (ReversingPostData) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.docDate==null && other.getDocDate()==null) || 
             (this.docDate!=null &&
              this.docDate.equals(other.getDocDate()))) &&
            ((this.period==null && other.getPeriod()==null) || 
             (this.period!=null &&
              this.period.equals(other.getPeriod()))) &&
            this.match == other.isMatch();
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
        if (getDocDate() != null) {
            _hashCode += getDocDate().hashCode();
        }
        if (getPeriod() != null) {
            _hashCode += getPeriod().hashCode();
        }
        _hashCode += (isMatch() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ReversingPostData.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "ReversingPostData"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("docDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "DocDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("period");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "Period"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseYearPeriod"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("match");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "Match"));
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
