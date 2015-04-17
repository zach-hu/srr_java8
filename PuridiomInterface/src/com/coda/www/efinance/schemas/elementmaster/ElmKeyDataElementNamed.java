/**
 * ElmKeyDataElementNamed.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.elementmaster;


/**
 * The key to an
 *             element.
 */
public class ElmKeyDataElementNamed  implements java.io.Serializable {
    private java.lang.String code;

    /* The element
     *                     name. */
    private java.lang.String name;

    /* The
     *                         element short name. */
    private java.lang.String shortName;

    /* If
     *                         TRUE, specifies that this element is a customer
     * and a supplier. */
    private boolean customerSupplier;

    /* If
     *                         TRUE, specifies that this element is a
     *                     customer. */
    private boolean isCustomer;

    /* If
     *                         TRUE, specifies that this element is a
     *                     supplier. */
    private boolean isSupplier;

    public ElmKeyDataElementNamed() {
    }

    public ElmKeyDataElementNamed(
           java.lang.String code,
           java.lang.String name,
           java.lang.String shortName,
           boolean customerSupplier,
           boolean isCustomer,
           boolean isSupplier) {
           this.code = code;
           this.name = name;
           this.shortName = shortName;
           this.customerSupplier = customerSupplier;
           this.isCustomer = isCustomer;
           this.isSupplier = isSupplier;
    }


    /**
     * Gets the code value for this ElmKeyDataElementNamed.
     * 
     * @return code
     */
    public java.lang.String getCode() {
        return code;
    }


    /**
     * Sets the code value for this ElmKeyDataElementNamed.
     * 
     * @param code
     */
    public void setCode(java.lang.String code) {
        this.code = code;
    }


    /**
     * Gets the name value for this ElmKeyDataElementNamed.
     * 
     * @return name   * The element
     *                     name.
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this ElmKeyDataElementNamed.
     * 
     * @param name   * The element
     *                     name.
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the shortName value for this ElmKeyDataElementNamed.
     * 
     * @return shortName   * The
     *                         element short name.
     */
    public java.lang.String getShortName() {
        return shortName;
    }


    /**
     * Sets the shortName value for this ElmKeyDataElementNamed.
     * 
     * @param shortName   * The
     *                         element short name.
     */
    public void setShortName(java.lang.String shortName) {
        this.shortName = shortName;
    }


    /**
     * Gets the customerSupplier value for this ElmKeyDataElementNamed.
     * 
     * @return customerSupplier   * If
     *                         TRUE, specifies that this element is a customer
     * and a supplier.
     */
    public boolean isCustomerSupplier() {
        return customerSupplier;
    }


    /**
     * Sets the customerSupplier value for this ElmKeyDataElementNamed.
     * 
     * @param customerSupplier   * If
     *                         TRUE, specifies that this element is a customer
     * and a supplier.
     */
    public void setCustomerSupplier(boolean customerSupplier) {
        this.customerSupplier = customerSupplier;
    }


    /**
     * Gets the isCustomer value for this ElmKeyDataElementNamed.
     * 
     * @return isCustomer   * If
     *                         TRUE, specifies that this element is a
     *                     customer.
     */
    public boolean isIsCustomer() {
        return isCustomer;
    }


    /**
     * Sets the isCustomer value for this ElmKeyDataElementNamed.
     * 
     * @param isCustomer   * If
     *                         TRUE, specifies that this element is a
     *                     customer.
     */
    public void setIsCustomer(boolean isCustomer) {
        this.isCustomer = isCustomer;
    }


    /**
     * Gets the isSupplier value for this ElmKeyDataElementNamed.
     * 
     * @return isSupplier   * If
     *                         TRUE, specifies that this element is a
     *                     supplier.
     */
    public boolean isIsSupplier() {
        return isSupplier;
    }


    /**
     * Sets the isSupplier value for this ElmKeyDataElementNamed.
     * 
     * @param isSupplier   * If
     *                         TRUE, specifies that this element is a
     *                     supplier.
     */
    public void setIsSupplier(boolean isSupplier) {
        this.isSupplier = isSupplier;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ElmKeyDataElementNamed)) return false;
        ElmKeyDataElementNamed other = (ElmKeyDataElementNamed) obj;
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
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
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
        if (getName() != null) {
            _hashCode += getName().hashCode();
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
        new org.apache.axis.description.TypeDesc(ElmKeyDataElementNamed.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "elmKeyDataElementNamed"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("code");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Code"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
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
