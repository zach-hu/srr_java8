/**
 * UmbrellaData.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.elementmaster;


/**
 * Holds details of values that may be
 *                 provided and values that must be provided for temporary
 * suppliers within this umbrella
 *             element.
 */
public class UmbrellaData  implements java.io.Serializable {
    private boolean allowTaxNumber;

    /* Indicates that a language
     *                         code can be provided for the temporary
     *                     supplier. */
    private boolean allowLanguageCode;

    /* Indicates that a country code
     *                         can be provided for the temporary
     *                     supplier. */
    private boolean allowCountryCode;

    /* Indicates that discount terms
     *                         must be provided if the document master allows
     * terms. */
    private boolean forceTerms;

    /* Indicates that the temporary
     *                         supplier's tax number must be provided.
     *                         When this is TRUE, the tax number must be
     *                         provided, even if AllowTaxNumber is set to
     * FALSE. */
    private boolean forceTaxNumber;

    /* Indicates that at least the
     *                         first line of the temporary supplier's
     *                         address must be provided. */
    private boolean forceAddress;

    /* Indicates that the temporary
     *                         supplier's federal tax ID or social
     *                         security number must be provided. When this
     * is
     *                         TRUE, you must also set Ten99 to
     *                     TRUE. */
    private boolean force1099Details;

    public UmbrellaData() {
    }

    public UmbrellaData(
           boolean allowTaxNumber,
           boolean allowLanguageCode,
           boolean allowCountryCode,
           boolean forceTerms,
           boolean forceTaxNumber,
           boolean forceAddress,
           boolean force1099Details) {
           this.allowTaxNumber = allowTaxNumber;
           this.allowLanguageCode = allowLanguageCode;
           this.allowCountryCode = allowCountryCode;
           this.forceTerms = forceTerms;
           this.forceTaxNumber = forceTaxNumber;
           this.forceAddress = forceAddress;
           this.force1099Details = force1099Details;
    }


    /**
     * Gets the allowTaxNumber value for this UmbrellaData.
     * 
     * @return allowTaxNumber
     */
    public boolean isAllowTaxNumber() {
        return allowTaxNumber;
    }


    /**
     * Sets the allowTaxNumber value for this UmbrellaData.
     * 
     * @param allowTaxNumber
     */
    public void setAllowTaxNumber(boolean allowTaxNumber) {
        this.allowTaxNumber = allowTaxNumber;
    }


    /**
     * Gets the allowLanguageCode value for this UmbrellaData.
     * 
     * @return allowLanguageCode   * Indicates that a language
     *                         code can be provided for the temporary
     *                     supplier.
     */
    public boolean isAllowLanguageCode() {
        return allowLanguageCode;
    }


    /**
     * Sets the allowLanguageCode value for this UmbrellaData.
     * 
     * @param allowLanguageCode   * Indicates that a language
     *                         code can be provided for the temporary
     *                     supplier.
     */
    public void setAllowLanguageCode(boolean allowLanguageCode) {
        this.allowLanguageCode = allowLanguageCode;
    }


    /**
     * Gets the allowCountryCode value for this UmbrellaData.
     * 
     * @return allowCountryCode   * Indicates that a country code
     *                         can be provided for the temporary
     *                     supplier.
     */
    public boolean isAllowCountryCode() {
        return allowCountryCode;
    }


    /**
     * Sets the allowCountryCode value for this UmbrellaData.
     * 
     * @param allowCountryCode   * Indicates that a country code
     *                         can be provided for the temporary
     *                     supplier.
     */
    public void setAllowCountryCode(boolean allowCountryCode) {
        this.allowCountryCode = allowCountryCode;
    }


    /**
     * Gets the forceTerms value for this UmbrellaData.
     * 
     * @return forceTerms   * Indicates that discount terms
     *                         must be provided if the document master allows
     * terms.
     */
    public boolean isForceTerms() {
        return forceTerms;
    }


    /**
     * Sets the forceTerms value for this UmbrellaData.
     * 
     * @param forceTerms   * Indicates that discount terms
     *                         must be provided if the document master allows
     * terms.
     */
    public void setForceTerms(boolean forceTerms) {
        this.forceTerms = forceTerms;
    }


    /**
     * Gets the forceTaxNumber value for this UmbrellaData.
     * 
     * @return forceTaxNumber   * Indicates that the temporary
     *                         supplier's tax number must be provided.
     *                         When this is TRUE, the tax number must be
     *                         provided, even if AllowTaxNumber is set to
     * FALSE.
     */
    public boolean isForceTaxNumber() {
        return forceTaxNumber;
    }


    /**
     * Sets the forceTaxNumber value for this UmbrellaData.
     * 
     * @param forceTaxNumber   * Indicates that the temporary
     *                         supplier's tax number must be provided.
     *                         When this is TRUE, the tax number must be
     *                         provided, even if AllowTaxNumber is set to
     * FALSE.
     */
    public void setForceTaxNumber(boolean forceTaxNumber) {
        this.forceTaxNumber = forceTaxNumber;
    }


    /**
     * Gets the forceAddress value for this UmbrellaData.
     * 
     * @return forceAddress   * Indicates that at least the
     *                         first line of the temporary supplier's
     *                         address must be provided.
     */
    public boolean isForceAddress() {
        return forceAddress;
    }


    /**
     * Sets the forceAddress value for this UmbrellaData.
     * 
     * @param forceAddress   * Indicates that at least the
     *                         first line of the temporary supplier's
     *                         address must be provided.
     */
    public void setForceAddress(boolean forceAddress) {
        this.forceAddress = forceAddress;
    }


    /**
     * Gets the force1099Details value for this UmbrellaData.
     * 
     * @return force1099Details   * Indicates that the temporary
     *                         supplier's federal tax ID or social
     *                         security number must be provided. When this
     * is
     *                         TRUE, you must also set Ten99 to
     *                     TRUE.
     */
    public boolean isForce1099Details() {
        return force1099Details;
    }


    /**
     * Sets the force1099Details value for this UmbrellaData.
     * 
     * @param force1099Details   * Indicates that the temporary
     *                         supplier's federal tax ID or social
     *                         security number must be provided. When this
     * is
     *                         TRUE, you must also set Ten99 to
     *                     TRUE.
     */
    public void setForce1099Details(boolean force1099Details) {
        this.force1099Details = force1099Details;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UmbrellaData)) return false;
        UmbrellaData other = (UmbrellaData) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.allowTaxNumber == other.isAllowTaxNumber() &&
            this.allowLanguageCode == other.isAllowLanguageCode() &&
            this.allowCountryCode == other.isAllowCountryCode() &&
            this.forceTerms == other.isForceTerms() &&
            this.forceTaxNumber == other.isForceTaxNumber() &&
            this.forceAddress == other.isForceAddress() &&
            this.force1099Details == other.isForce1099Details();
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
        _hashCode += (isAllowTaxNumber() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isAllowLanguageCode() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isAllowCountryCode() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isForceTerms() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isForceTaxNumber() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isForceAddress() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isForce1099Details() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UmbrellaData.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "UmbrellaData"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("allowTaxNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "AllowTaxNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("allowLanguageCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "AllowLanguageCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("allowCountryCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "AllowCountryCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("forceTerms");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "ForceTerms"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("forceTaxNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "ForceTaxNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("forceAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "ForceAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("force1099Details");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Force1099Details"));
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
