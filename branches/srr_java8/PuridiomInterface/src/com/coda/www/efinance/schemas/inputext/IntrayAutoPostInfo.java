/**
 * IntrayAutoPostInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.inputext;


/**
 * Contains information used for posting
 *                 documents on the Intray, to the
 *             Books.
 */
public class IntrayAutoPostInfo  implements java.io.Serializable {
    private java.lang.String logTitle;

    /* Set to
     *                         TRUE to post the document to a new year/period
     * which you specify in Period. When FALSE, the
     *                         document is posted to the year/period specified
     * when the document was posted to the
     *                     Intray. */
    private boolean rePeriod;

    /* When
     *                         RePeriod is TRUE, specify the year/period
     * that
     *                         the document will be posted
     *                     to. */
    private java.lang.String period;

    /* When TRUE, any InterCompany
     *                         documents on the Intray will be included for
     * posting to the Books. When FALSE or omitted, any
     *                         InterCompany documents on the Intray cannot
     * be
     *                         included for posting; this is recorded in
     * the
     *                     log. */
    private java.lang.Boolean includeInterCompanyDocs;

    public IntrayAutoPostInfo() {
    }

    public IntrayAutoPostInfo(
           java.lang.String logTitle,
           boolean rePeriod,
           java.lang.String period,
           java.lang.Boolean includeInterCompanyDocs) {
           this.logTitle = logTitle;
           this.rePeriod = rePeriod;
           this.period = period;
           this.includeInterCompanyDocs = includeInterCompanyDocs;
    }


    /**
     * Gets the logTitle value for this IntrayAutoPostInfo.
     * 
     * @return logTitle
     */
    public java.lang.String getLogTitle() {
        return logTitle;
    }


    /**
     * Sets the logTitle value for this IntrayAutoPostInfo.
     * 
     * @param logTitle
     */
    public void setLogTitle(java.lang.String logTitle) {
        this.logTitle = logTitle;
    }


    /**
     * Gets the rePeriod value for this IntrayAutoPostInfo.
     * 
     * @return rePeriod   * Set to
     *                         TRUE to post the document to a new year/period
     * which you specify in Period. When FALSE, the
     *                         document is posted to the year/period specified
     * when the document was posted to the
     *                     Intray.
     */
    public boolean isRePeriod() {
        return rePeriod;
    }


    /**
     * Sets the rePeriod value for this IntrayAutoPostInfo.
     * 
     * @param rePeriod   * Set to
     *                         TRUE to post the document to a new year/period
     * which you specify in Period. When FALSE, the
     *                         document is posted to the year/period specified
     * when the document was posted to the
     *                     Intray.
     */
    public void setRePeriod(boolean rePeriod) {
        this.rePeriod = rePeriod;
    }


    /**
     * Gets the period value for this IntrayAutoPostInfo.
     * 
     * @return period   * When
     *                         RePeriod is TRUE, specify the year/period
     * that
     *                         the document will be posted
     *                     to.
     */
    public java.lang.String getPeriod() {
        return period;
    }


    /**
     * Sets the period value for this IntrayAutoPostInfo.
     * 
     * @param period   * When
     *                         RePeriod is TRUE, specify the year/period
     * that
     *                         the document will be posted
     *                     to.
     */
    public void setPeriod(java.lang.String period) {
        this.period = period;
    }


    /**
     * Gets the includeInterCompanyDocs value for this IntrayAutoPostInfo.
     * 
     * @return includeInterCompanyDocs   * When TRUE, any InterCompany
     *                         documents on the Intray will be included for
     * posting to the Books. When FALSE or omitted, any
     *                         InterCompany documents on the Intray cannot
     * be
     *                         included for posting; this is recorded in
     * the
     *                     log.
     */
    public java.lang.Boolean getIncludeInterCompanyDocs() {
        return includeInterCompanyDocs;
    }


    /**
     * Sets the includeInterCompanyDocs value for this IntrayAutoPostInfo.
     * 
     * @param includeInterCompanyDocs   * When TRUE, any InterCompany
     *                         documents on the Intray will be included for
     * posting to the Books. When FALSE or omitted, any
     *                         InterCompany documents on the Intray cannot
     * be
     *                         included for posting; this is recorded in
     * the
     *                     log.
     */
    public void setIncludeInterCompanyDocs(java.lang.Boolean includeInterCompanyDocs) {
        this.includeInterCompanyDocs = includeInterCompanyDocs;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof IntrayAutoPostInfo)) return false;
        IntrayAutoPostInfo other = (IntrayAutoPostInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.logTitle==null && other.getLogTitle()==null) || 
             (this.logTitle!=null &&
              this.logTitle.equals(other.getLogTitle()))) &&
            this.rePeriod == other.isRePeriod() &&
            ((this.period==null && other.getPeriod()==null) || 
             (this.period!=null &&
              this.period.equals(other.getPeriod()))) &&
            ((this.includeInterCompanyDocs==null && other.getIncludeInterCompanyDocs()==null) || 
             (this.includeInterCompanyDocs!=null &&
              this.includeInterCompanyDocs.equals(other.getIncludeInterCompanyDocs())));
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
        if (getLogTitle() != null) {
            _hashCode += getLogTitle().hashCode();
        }
        _hashCode += (isRePeriod() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getPeriod() != null) {
            _hashCode += getPeriod().hashCode();
        }
        if (getIncludeInterCompanyDocs() != null) {
            _hashCode += getIncludeInterCompanyDocs().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(IntrayAutoPostInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "IntrayAutoPostInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("logTitle");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "LogTitle"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rePeriod");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "RePeriod"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("period");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "Period"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseYearPeriod"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("includeInterCompanyDocs");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "IncludeInterCompanyDocs"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
