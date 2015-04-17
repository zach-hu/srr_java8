/**
 * HardenedDatesParams.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.inputext;


/**
 * Contains parameters which can be
 *                 specified when resolving soft dates.
 */
public class HardenedDatesParams  implements java.io.Serializable {
    private java.lang.String cmpCode;

    /* The code of the document
     *                         containing the soft date to be
     *                     resolved. */
    private java.lang.String docCode;

    /* The
     *                         code of the element containing the soft date
     * to
     *                         be resolved. */
    private java.lang.String elmCode;

    /* The
     *                         element level. */
    private short elmLevel;

    /* Set to TRUE to search on
     *                         element mnemonic if the ElmCode cannot be
     *                     found. */
    private java.lang.Boolean useMnemonic;

    /* The document
     *                     date. */
    private java.util.Calendar docDate;

    /* The date when the document
     *                         was input. */
    private java.util.Calendar inputDate;

    public HardenedDatesParams() {
    }

    public HardenedDatesParams(
           java.lang.String cmpCode,
           java.lang.String docCode,
           java.lang.String elmCode,
           short elmLevel,
           java.lang.Boolean useMnemonic,
           java.util.Calendar docDate,
           java.util.Calendar inputDate) {
           this.cmpCode = cmpCode;
           this.docCode = docCode;
           this.elmCode = elmCode;
           this.elmLevel = elmLevel;
           this.useMnemonic = useMnemonic;
           this.docDate = docDate;
           this.inputDate = inputDate;
    }


    /**
     * Gets the cmpCode value for this HardenedDatesParams.
     * 
     * @return cmpCode
     */
    public java.lang.String getCmpCode() {
        return cmpCode;
    }


    /**
     * Sets the cmpCode value for this HardenedDatesParams.
     * 
     * @param cmpCode
     */
    public void setCmpCode(java.lang.String cmpCode) {
        this.cmpCode = cmpCode;
    }


    /**
     * Gets the docCode value for this HardenedDatesParams.
     * 
     * @return docCode   * The code of the document
     *                         containing the soft date to be
     *                     resolved.
     */
    public java.lang.String getDocCode() {
        return docCode;
    }


    /**
     * Sets the docCode value for this HardenedDatesParams.
     * 
     * @param docCode   * The code of the document
     *                         containing the soft date to be
     *                     resolved.
     */
    public void setDocCode(java.lang.String docCode) {
        this.docCode = docCode;
    }


    /**
     * Gets the elmCode value for this HardenedDatesParams.
     * 
     * @return elmCode   * The
     *                         code of the element containing the soft date
     * to
     *                         be resolved.
     */
    public java.lang.String getElmCode() {
        return elmCode;
    }


    /**
     * Sets the elmCode value for this HardenedDatesParams.
     * 
     * @param elmCode   * The
     *                         code of the element containing the soft date
     * to
     *                         be resolved.
     */
    public void setElmCode(java.lang.String elmCode) {
        this.elmCode = elmCode;
    }


    /**
     * Gets the elmLevel value for this HardenedDatesParams.
     * 
     * @return elmLevel   * The
     *                         element level.
     */
    public short getElmLevel() {
        return elmLevel;
    }


    /**
     * Sets the elmLevel value for this HardenedDatesParams.
     * 
     * @param elmLevel   * The
     *                         element level.
     */
    public void setElmLevel(short elmLevel) {
        this.elmLevel = elmLevel;
    }


    /**
     * Gets the useMnemonic value for this HardenedDatesParams.
     * 
     * @return useMnemonic   * Set to TRUE to search on
     *                         element mnemonic if the ElmCode cannot be
     *                     found.
     */
    public java.lang.Boolean getUseMnemonic() {
        return useMnemonic;
    }


    /**
     * Sets the useMnemonic value for this HardenedDatesParams.
     * 
     * @param useMnemonic   * Set to TRUE to search on
     *                         element mnemonic if the ElmCode cannot be
     *                     found.
     */
    public void setUseMnemonic(java.lang.Boolean useMnemonic) {
        this.useMnemonic = useMnemonic;
    }


    /**
     * Gets the docDate value for this HardenedDatesParams.
     * 
     * @return docDate   * The document
     *                     date.
     */
    public java.util.Calendar getDocDate() {
        return docDate;
    }


    /**
     * Sets the docDate value for this HardenedDatesParams.
     * 
     * @param docDate   * The document
     *                     date.
     */
    public void setDocDate(java.util.Calendar docDate) {
        this.docDate = docDate;
    }


    /**
     * Gets the inputDate value for this HardenedDatesParams.
     * 
     * @return inputDate   * The date when the document
     *                         was input.
     */
    public java.util.Calendar getInputDate() {
        return inputDate;
    }


    /**
     * Sets the inputDate value for this HardenedDatesParams.
     * 
     * @param inputDate   * The date when the document
     *                         was input.
     */
    public void setInputDate(java.util.Calendar inputDate) {
        this.inputDate = inputDate;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof HardenedDatesParams)) return false;
        HardenedDatesParams other = (HardenedDatesParams) obj;
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
            ((this.elmCode==null && other.getElmCode()==null) || 
             (this.elmCode!=null &&
              this.elmCode.equals(other.getElmCode()))) &&
            this.elmLevel == other.getElmLevel() &&
            ((this.useMnemonic==null && other.getUseMnemonic()==null) || 
             (this.useMnemonic!=null &&
              this.useMnemonic.equals(other.getUseMnemonic()))) &&
            ((this.docDate==null && other.getDocDate()==null) || 
             (this.docDate!=null &&
              this.docDate.equals(other.getDocDate()))) &&
            ((this.inputDate==null && other.getInputDate()==null) || 
             (this.inputDate!=null &&
              this.inputDate.equals(other.getInputDate())));
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
        if (getElmCode() != null) {
            _hashCode += getElmCode().hashCode();
        }
        _hashCode += getElmLevel();
        if (getUseMnemonic() != null) {
            _hashCode += getUseMnemonic().hashCode();
        }
        if (getDocDate() != null) {
            _hashCode += getDocDate().hashCode();
        }
        if (getInputDate() != null) {
            _hashCode += getInputDate().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(HardenedDatesParams.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "HardenedDatesParams"));
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
        elemField.setFieldName("elmCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "ElmCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("elmLevel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "ElmLevel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("useMnemonic");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "UseMnemonic"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("docDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "DocDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inputDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "InputDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
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
