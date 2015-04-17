/**
 * Pay.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.documentmaster;

public class Pay  implements java.io.Serializable {
    /* Specifies whether users can
     *                         enter payment details during
     *                     Input. */
    private boolean enable;

    /* This element holds details of
     *                         the media used for the
     *                     payment. */
    private com.coda.www.efinance.schemas.documentmaster.PayData media;

    /* This
     *                         element holds details of the company bank
     * used
     *                         for the payment. */
    private com.coda.www.efinance.schemas.documentmaster.PayData companyBank;

    private com.coda.www.efinance.schemas.documentmaster.PayElement element;

    public Pay() {
    }

    public Pay(
           boolean enable,
           com.coda.www.efinance.schemas.documentmaster.PayData media,
           com.coda.www.efinance.schemas.documentmaster.PayData companyBank,
           com.coda.www.efinance.schemas.documentmaster.PayElement element) {
           this.enable = enable;
           this.media = media;
           this.companyBank = companyBank;
           this.element = element;
    }


    /**
     * Gets the enable value for this Pay.
     * 
     * @return enable   * Specifies whether users can
     *                         enter payment details during
     *                     Input.
     */
    public boolean isEnable() {
        return enable;
    }


    /**
     * Sets the enable value for this Pay.
     * 
     * @param enable   * Specifies whether users can
     *                         enter payment details during
     *                     Input.
     */
    public void setEnable(boolean enable) {
        this.enable = enable;
    }


    /**
     * Gets the media value for this Pay.
     * 
     * @return media   * This element holds details of
     *                         the media used for the
     *                     payment.
     */
    public com.coda.www.efinance.schemas.documentmaster.PayData getMedia() {
        return media;
    }


    /**
     * Sets the media value for this Pay.
     * 
     * @param media   * This element holds details of
     *                         the media used for the
     *                     payment.
     */
    public void setMedia(com.coda.www.efinance.schemas.documentmaster.PayData media) {
        this.media = media;
    }


    /**
     * Gets the companyBank value for this Pay.
     * 
     * @return companyBank   * This
     *                         element holds details of the company bank
     * used
     *                         for the payment.
     */
    public com.coda.www.efinance.schemas.documentmaster.PayData getCompanyBank() {
        return companyBank;
    }


    /**
     * Sets the companyBank value for this Pay.
     * 
     * @param companyBank   * This
     *                         element holds details of the company bank
     * used
     *                         for the payment.
     */
    public void setCompanyBank(com.coda.www.efinance.schemas.documentmaster.PayData companyBank) {
        this.companyBank = companyBank;
    }


    /**
     * Gets the element value for this Pay.
     * 
     * @return element
     */
    public com.coda.www.efinance.schemas.documentmaster.PayElement getElement() {
        return element;
    }


    /**
     * Sets the element value for this Pay.
     * 
     * @param element
     */
    public void setElement(com.coda.www.efinance.schemas.documentmaster.PayElement element) {
        this.element = element;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Pay)) return false;
        Pay other = (Pay) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.enable == other.isEnable() &&
            ((this.media==null && other.getMedia()==null) || 
             (this.media!=null &&
              this.media.equals(other.getMedia()))) &&
            ((this.companyBank==null && other.getCompanyBank()==null) || 
             (this.companyBank!=null &&
              this.companyBank.equals(other.getCompanyBank()))) &&
            ((this.element==null && other.getElement()==null) || 
             (this.element!=null &&
              this.element.equals(other.getElement())));
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
        _hashCode += (isEnable() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getMedia() != null) {
            _hashCode += getMedia().hashCode();
        }
        if (getCompanyBank() != null) {
            _hashCode += getCompanyBank().hashCode();
        }
        if (getElement() != null) {
            _hashCode += getElement().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Pay.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "Pay"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("enable");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "Enable"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("media");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "Media"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "PayData"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("companyBank");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "CompanyBank"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "PayData"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("element");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "Element"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "PayElement"));
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
