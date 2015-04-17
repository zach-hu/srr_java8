/**
 * DocTax.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.documentmaster;


/**
 * This element holds tax
 *             settings.
 */
public class DocTax  implements java.io.Serializable {
    private java.lang.String method;

    /* Specifies that this document
     *                         type must be selected for European Sales List
     * reports. */
    private boolean ESL;

    /* Specifies that this document
     *                         type must be selected for Intrastat reports
     * (The
     *                         Statutory Supplementary
     *                     Declaration). */
    private boolean intrastat;

    /* Specifies that this document
     *                         type must be selected for 1099
     *                     reports. */
    private boolean ten99;

    /* The maximum number of tax
     *                         codes allowed on each document
     *                     line. */
    private short numberOfTaxCodes;

    public DocTax() {
    }

    public DocTax(
           java.lang.String method,
           boolean ESL,
           boolean intrastat,
           boolean ten99,
           short numberOfTaxCodes) {
           this.method = method;
           this.ESL = ESL;
           this.intrastat = intrastat;
           this.ten99 = ten99;
           this.numberOfTaxCodes = numberOfTaxCodes;
    }


    /**
     * Gets the method value for this DocTax.
     * 
     * @return method
     */
    public java.lang.String getMethod() {
        return method;
    }


    /**
     * Sets the method value for this DocTax.
     * 
     * @param method
     */
    public void setMethod(java.lang.String method) {
        this.method = method;
    }


    /**
     * Gets the ESL value for this DocTax.
     * 
     * @return ESL   * Specifies that this document
     *                         type must be selected for European Sales List
     * reports.
     */
    public boolean isESL() {
        return ESL;
    }


    /**
     * Sets the ESL value for this DocTax.
     * 
     * @param ESL   * Specifies that this document
     *                         type must be selected for European Sales List
     * reports.
     */
    public void setESL(boolean ESL) {
        this.ESL = ESL;
    }


    /**
     * Gets the intrastat value for this DocTax.
     * 
     * @return intrastat   * Specifies that this document
     *                         type must be selected for Intrastat reports
     * (The
     *                         Statutory Supplementary
     *                     Declaration).
     */
    public boolean isIntrastat() {
        return intrastat;
    }


    /**
     * Sets the intrastat value for this DocTax.
     * 
     * @param intrastat   * Specifies that this document
     *                         type must be selected for Intrastat reports
     * (The
     *                         Statutory Supplementary
     *                     Declaration).
     */
    public void setIntrastat(boolean intrastat) {
        this.intrastat = intrastat;
    }


    /**
     * Gets the ten99 value for this DocTax.
     * 
     * @return ten99   * Specifies that this document
     *                         type must be selected for 1099
     *                     reports.
     */
    public boolean isTen99() {
        return ten99;
    }


    /**
     * Sets the ten99 value for this DocTax.
     * 
     * @param ten99   * Specifies that this document
     *                         type must be selected for 1099
     *                     reports.
     */
    public void setTen99(boolean ten99) {
        this.ten99 = ten99;
    }


    /**
     * Gets the numberOfTaxCodes value for this DocTax.
     * 
     * @return numberOfTaxCodes   * The maximum number of tax
     *                         codes allowed on each document
     *                     line.
     */
    public short getNumberOfTaxCodes() {
        return numberOfTaxCodes;
    }


    /**
     * Sets the numberOfTaxCodes value for this DocTax.
     * 
     * @param numberOfTaxCodes   * The maximum number of tax
     *                         codes allowed on each document
     *                     line.
     */
    public void setNumberOfTaxCodes(short numberOfTaxCodes) {
        this.numberOfTaxCodes = numberOfTaxCodes;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DocTax)) return false;
        DocTax other = (DocTax) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.method==null && other.getMethod()==null) || 
             (this.method!=null &&
              this.method.equals(other.getMethod()))) &&
            this.ESL == other.isESL() &&
            this.intrastat == other.isIntrastat() &&
            this.ten99 == other.isTen99() &&
            this.numberOfTaxCodes == other.getNumberOfTaxCodes();
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
        if (getMethod() != null) {
            _hashCode += getMethod().hashCode();
        }
        _hashCode += (isESL() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isIntrastat() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isTen99() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += getNumberOfTaxCodes();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DocTax.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "DocTax"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("method");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "Method"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ESL");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "ESL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("intrastat");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "Intrastat"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ten99");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "Ten99"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numberOfTaxCodes");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "NumberOfTaxCodes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
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
