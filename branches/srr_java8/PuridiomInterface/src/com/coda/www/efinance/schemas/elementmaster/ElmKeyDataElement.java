/**
 * ElmKeyDataElement.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.elementmaster;


/**
 * Holds the extended key information
 *                 for one element.
 */
public class ElmKeyDataElement  implements java.io.Serializable {
    private java.lang.String code;

    /* The
     *                         element's short name. */
    private java.lang.String shortName;

    /* A
     *                         specification of whether the element represents
     * a customer or supplier. */
    private boolean customerSupplier;

    /* Specifies whether this
     *                         element is a customer only. */
    private boolean isCustomer;

    /* Specifies whether this
     *                         element is a supplier only. */
    private boolean isSupplier;

    public ElmKeyDataElement() {
    }

    public ElmKeyDataElement(
           java.lang.String code,
           java.lang.String shortName,
           boolean customerSupplier,
           boolean isCustomer,
           boolean isSupplier) {
           this.code = code;
           this.shortName = shortName;
           this.customerSupplier = customerSupplier;
           this.isCustomer = isCustomer;
           this.isSupplier = isSupplier;
    }


    /**
     * Gets the code value for this ElmKeyDataElement.
     * 
     * @return code
     */
    public java.lang.String getCode() {
        return code;
    }


    /**
     * Sets the code value for this ElmKeyDataElement.
     * 
     * @param code
     */
    public void setCode(java.lang.String code) {
        this.code = code;
    }


    /**
     * Gets the shortName value for this ElmKeyDataElement.
     * 
     * @return shortName   * The
     *                         element's short name.
     */
    public java.lang.String getShortName() {
        return shortName;
    }


    /**
     * Sets the shortName value for this ElmKeyDataElement.
     * 
     * @param shortName   * The
     *                         element's short name.
     */
    public void setShortName(java.lang.String shortName) {
        this.shortName = shortName;
    }


    /**
     * Gets the customerSupplier value for this ElmKeyDataElement.
     * 
     * @return customerSupplier   * A
     *                         specification of whether the element represents
     * a customer or supplier.
     */
    public boolean isCustomerSupplier() {
        return customerSupplier;
    }


    /**
     * Sets the customerSupplier value for this ElmKeyDataElement.
     * 
     * @param customerSupplier   * A
     *                         specification of whether the element represents
     * a customer or supplier.
     */
    public void setCustomerSupplier(boolean customerSupplier) {
        this.customerSupplier = customerSupplier;
    }


    /**
     * Gets the isCustomer value for this ElmKeyDataElement.
     * 
     * @return isCustomer   * Specifies whether this
     *                         element is a customer only.
     */
    public boolean isIsCustomer() {
        return isCustomer;
    }


    /**
     * Sets the isCustomer value for this ElmKeyDataElement.
     * 
     * @param isCustomer   * Specifies whether this
     *                         element is a customer only.
     */
    public void setIsCustomer(boolean isCustomer) {
        this.isCustomer = isCustomer;
    }


    /**
     * Gets the isSupplier value for this ElmKeyDataElement.
     * 
     * @return isSupplier   * Specifies whether this
     *                         element is a supplier only.
     */
    public boolean isIsSupplier() {
        return isSupplier;
    }


    /**
     * Sets the isSupplier value for this ElmKeyDataElement.
     * 
     * @param isSupplier   * Specifies whether this
     *                         element is a supplier only.
     */
    public void setIsSupplier(boolean isSupplier) {
        this.isSupplier = isSupplier;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ElmKeyDataElement)) return false;
        ElmKeyDataElement other = (ElmKeyDataElement) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.code==null && other.getCode()==null) || 
             (this.code!=null &&
              this.code.equals(other.getCode()))) &&
            ((this.shortName==null && other.getShortName()==null) || 
             (this.shortName!=null &&
              this.shortName.equals(other.getShortName()))) &&
            this.customerSupplier == other.isCustomerSupplier() &&
            this.isCustomer == other.isIsCustomer() &&
            this.isSupplier == other.isIsSupplier();
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
        if (getCode() != null) {
            _hashCode += getCode().hashCode();
        }
        if (getShortName() != null) {
            _hashCode += getShortName().hashCode();
        }
        _hashCode += (isCustomerSupplier() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isIsCustomer() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isIsSupplier() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ElmKeyDataElement.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "elmKeyDataElement"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("code");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Code"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shortName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "ShortName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customerSupplier");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "CustomerSupplier"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isCustomer");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "IsCustomer"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isSupplier");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "IsSupplier"));
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
