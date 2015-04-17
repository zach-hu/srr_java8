/**
 * AddressElement.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.elementmaster;


/**
 * Holds details of an
 *             address.
 */
public class AddressElement  implements java.io.Serializable {
    private java.lang.Boolean defaultAddress;

    /* The
     *                         tag for this address, a number or string which
     * can be used to identify the
     *                     address. */
    private java.lang.Short tag;

    /* A name
     *                         for the person or company associated with
     * this
     *                     address. */
    private java.lang.String name;

    /* An address
     *                     line. */
    private java.lang.String address1;

    /* An
     *                         address line. */
    private java.lang.String address2;

    /* An address
     *                     line. */
    private java.lang.String address3;

    /* An
     *                         address line. */
    private java.lang.String address4;

    /* An address
     *                     line. */
    private java.lang.String address5;

    /* An
     *                         address line. */
    private java.lang.String address6;

    /* The post code for this
     *                     address. */
    private java.lang.String postCode;

    /* The telephone number for this
     *                     address. */
    private java.lang.String tel;

    /* The fax number for this
     *                     address. */
    private java.lang.String fax;

    /* The code for the country
     *                         master for this address. */
    private java.lang.String country;

    /* The language used at this
     *                     address. */
    private java.lang.String language;

    /* The code for an entity master
     *                         of the address category
     *                     type. */
    private java.lang.String category;

    /* The
     *                         email address for this
     *                     address. */
    private java.lang.String EMail;

    public AddressElement() {
    }

    public AddressElement(
           java.lang.Boolean defaultAddress,
           java.lang.Short tag,
           java.lang.String name,
           java.lang.String address1,
           java.lang.String address2,
           java.lang.String address3,
           java.lang.String address4,
           java.lang.String address5,
           java.lang.String address6,
           java.lang.String postCode,
           java.lang.String tel,
           java.lang.String fax,
           java.lang.String country,
           java.lang.String language,
           java.lang.String category,
           java.lang.String EMail) {
           this.defaultAddress = defaultAddress;
           this.tag = tag;
           this.name = name;
           this.address1 = address1;
           this.address2 = address2;
           this.address3 = address3;
           this.address4 = address4;
           this.address5 = address5;
           this.address6 = address6;
           this.postCode = postCode;
           this.tel = tel;
           this.fax = fax;
           this.country = country;
           this.language = language;
           this.category = category;
           this.EMail = EMail;
    }


    /**
     * Gets the defaultAddress value for this AddressElement.
     * 
     * @return defaultAddress
     */
    public java.lang.Boolean getDefaultAddress() {
        return defaultAddress;
    }


    /**
     * Sets the defaultAddress value for this AddressElement.
     * 
     * @param defaultAddress
     */
    public void setDefaultAddress(java.lang.Boolean defaultAddress) {
        this.defaultAddress = defaultAddress;
    }


    /**
     * Gets the tag value for this AddressElement.
     * 
     * @return tag   * The
     *                         tag for this address, a number or string which
     * can be used to identify the
     *                     address.
     */
    public java.lang.Short getTag() {
        return tag;
    }


    /**
     * Sets the tag value for this AddressElement.
     * 
     * @param tag   * The
     *                         tag for this address, a number or string which
     * can be used to identify the
     *                     address.
     */
    public void setTag(java.lang.Short tag) {
        this.tag = tag;
    }


    /**
     * Gets the name value for this AddressElement.
     * 
     * @return name   * A name
     *                         for the person or company associated with
     * this
     *                     address.
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this AddressElement.
     * 
     * @param name   * A name
     *                         for the person or company associated with
     * this
     *                     address.
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the address1 value for this AddressElement.
     * 
     * @return address1   * An address
     *                     line.
     */
    public java.lang.String getAddress1() {
        return address1;
    }


    /**
     * Sets the address1 value for this AddressElement.
     * 
     * @param address1   * An address
     *                     line.
     */
    public void setAddress1(java.lang.String address1) {
        this.address1 = address1;
    }


    /**
     * Gets the address2 value for this AddressElement.
     * 
     * @return address2   * An
     *                         address line.
     */
    public java.lang.String getAddress2() {
        return address2;
    }


    /**
     * Sets the address2 value for this AddressElement.
     * 
     * @param address2   * An
     *                         address line.
     */
    public void setAddress2(java.lang.String address2) {
        this.address2 = address2;
    }


    /**
     * Gets the address3 value for this AddressElement.
     * 
     * @return address3   * An address
     *                     line.
     */
    public java.lang.String getAddress3() {
        return address3;
    }


    /**
     * Sets the address3 value for this AddressElement.
     * 
     * @param address3   * An address
     *                     line.
     */
    public void setAddress3(java.lang.String address3) {
        this.address3 = address3;
    }


    /**
     * Gets the address4 value for this AddressElement.
     * 
     * @return address4   * An
     *                         address line.
     */
    public java.lang.String getAddress4() {
        return address4;
    }


    /**
     * Sets the address4 value for this AddressElement.
     * 
     * @param address4   * An
     *                         address line.
     */
    public void setAddress4(java.lang.String address4) {
        this.address4 = address4;
    }


    /**
     * Gets the address5 value for this AddressElement.
     * 
     * @return address5   * An address
     *                     line.
     */
    public java.lang.String getAddress5() {
        return address5;
    }


    /**
     * Sets the address5 value for this AddressElement.
     * 
     * @param address5   * An address
     *                     line.
     */
    public void setAddress5(java.lang.String address5) {
        this.address5 = address5;
    }


    /**
     * Gets the address6 value for this AddressElement.
     * 
     * @return address6   * An
     *                         address line.
     */
    public java.lang.String getAddress6() {
        return address6;
    }


    /**
     * Sets the address6 value for this AddressElement.
     * 
     * @param address6   * An
     *                         address line.
     */
    public void setAddress6(java.lang.String address6) {
        this.address6 = address6;
    }


    /**
     * Gets the postCode value for this AddressElement.
     * 
     * @return postCode   * The post code for this
     *                     address.
     */
    public java.lang.String getPostCode() {
        return postCode;
    }


    /**
     * Sets the postCode value for this AddressElement.
     * 
     * @param postCode   * The post code for this
     *                     address.
     */
    public void setPostCode(java.lang.String postCode) {
        this.postCode = postCode;
    }


    /**
     * Gets the tel value for this AddressElement.
     * 
     * @return tel   * The telephone number for this
     *                     address.
     */
    public java.lang.String getTel() {
        return tel;
    }


    /**
     * Sets the tel value for this AddressElement.
     * 
     * @param tel   * The telephone number for this
     *                     address.
     */
    public void setTel(java.lang.String tel) {
        this.tel = tel;
    }


    /**
     * Gets the fax value for this AddressElement.
     * 
     * @return fax   * The fax number for this
     *                     address.
     */
    public java.lang.String getFax() {
        return fax;
    }


    /**
     * Sets the fax value for this AddressElement.
     * 
     * @param fax   * The fax number for this
     *                     address.
     */
    public void setFax(java.lang.String fax) {
        this.fax = fax;
    }


    /**
     * Gets the country value for this AddressElement.
     * 
     * @return country   * The code for the country
     *                         master for this address.
     */
    public java.lang.String getCountry() {
        return country;
    }


    /**
     * Sets the country value for this AddressElement.
     * 
     * @param country   * The code for the country
     *                         master for this address.
     */
    public void setCountry(java.lang.String country) {
        this.country = country;
    }


    /**
     * Gets the language value for this AddressElement.
     * 
     * @return language   * The language used at this
     *                     address.
     */
    public java.lang.String getLanguage() {
        return language;
    }


    /**
     * Sets the language value for this AddressElement.
     * 
     * @param language   * The language used at this
     *                     address.
     */
    public void setLanguage(java.lang.String language) {
        this.language = language;
    }


    /**
     * Gets the category value for this AddressElement.
     * 
     * @return category   * The code for an entity master
     *                         of the address category
     *                     type.
     */
    public java.lang.String getCategory() {
        return category;
    }


    /**
     * Sets the category value for this AddressElement.
     * 
     * @param category   * The code for an entity master
     *                         of the address category
     *                     type.
     */
    public void setCategory(java.lang.String category) {
        this.category = category;
    }


    /**
     * Gets the EMail value for this AddressElement.
     * 
     * @return EMail   * The
     *                         email address for this
     *                     address.
     */
    public java.lang.String getEMail() {
        return EMail;
    }


    /**
     * Sets the EMail value for this AddressElement.
     * 
     * @param EMail   * The
     *                         email address for this
     *                     address.
     */
    public void setEMail(java.lang.String EMail) {
        this.EMail = EMail;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AddressElement)) return false;
        AddressElement other = (AddressElement) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.defaultAddress==null && other.getDefaultAddress()==null) || 
             (this.defaultAddress!=null &&
              this.defaultAddress.equals(other.getDefaultAddress()))) &&
            ((this.tag==null && other.getTag()==null) || 
             (this.tag!=null &&
              this.tag.equals(other.getTag()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.address1==null && other.getAddress1()==null) || 
             (this.address1!=null &&
              this.address1.equals(other.getAddress1()))) &&
            ((this.address2==null && other.getAddress2()==null) || 
             (this.address2!=null &&
              this.address2.equals(other.getAddress2()))) &&
            ((this.address3==null && other.getAddress3()==null) || 
             (this.address3!=null &&
              this.address3.equals(other.getAddress3()))) &&
            ((this.address4==null && other.getAddress4()==null) || 
             (this.address4!=null &&
              this.address4.equals(other.getAddress4()))) &&
            ((this.address5==null && other.getAddress5()==null) || 
             (this.address5!=null &&
              this.address5.equals(other.getAddress5()))) &&
            ((this.address6==null && other.getAddress6()==null) || 
             (this.address6!=null &&
              this.address6.equals(other.getAddress6()))) &&
            ((this.postCode==null && other.getPostCode()==null) || 
             (this.postCode!=null &&
              this.postCode.equals(other.getPostCode()))) &&
            ((this.tel==null && other.getTel()==null) || 
             (this.tel!=null &&
              this.tel.equals(other.getTel()))) &&
            ((this.fax==null && other.getFax()==null) || 
             (this.fax!=null &&
              this.fax.equals(other.getFax()))) &&
            ((this.country==null && other.getCountry()==null) || 
             (this.country!=null &&
              this.country.equals(other.getCountry()))) &&
            ((this.language==null && other.getLanguage()==null) || 
             (this.language!=null &&
              this.language.equals(other.getLanguage()))) &&
            ((this.category==null && other.getCategory()==null) || 
             (this.category!=null &&
              this.category.equals(other.getCategory()))) &&
            ((this.EMail==null && other.getEMail()==null) || 
             (this.EMail!=null &&
              this.EMail.equals(other.getEMail())));
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
        if (getDefaultAddress() != null) {
            _hashCode += getDefaultAddress().hashCode();
        }
        if (getTag() != null) {
            _hashCode += getTag().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getAddress1() != null) {
            _hashCode += getAddress1().hashCode();
        }
        if (getAddress2() != null) {
            _hashCode += getAddress2().hashCode();
        }
        if (getAddress3() != null) {
            _hashCode += getAddress3().hashCode();
        }
        if (getAddress4() != null) {
            _hashCode += getAddress4().hashCode();
        }
        if (getAddress5() != null) {
            _hashCode += getAddress5().hashCode();
        }
        if (getAddress6() != null) {
            _hashCode += getAddress6().hashCode();
        }
        if (getPostCode() != null) {
            _hashCode += getPostCode().hashCode();
        }
        if (getTel() != null) {
            _hashCode += getTel().hashCode();
        }
        if (getFax() != null) {
            _hashCode += getFax().hashCode();
        }
        if (getCountry() != null) {
            _hashCode += getCountry().hashCode();
        }
        if (getLanguage() != null) {
            _hashCode += getLanguage().hashCode();
        }
        if (getCategory() != null) {
            _hashCode += getCategory().hashCode();
        }
        if (getEMail() != null) {
            _hashCode += getEMail().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AddressElement.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "AddressElement"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("defaultAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "DefaultAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tag");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Tag"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("address1");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Address1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("address2");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Address2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("address3");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Address3"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("address4");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Address4"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("address5");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Address5"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("address6");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Address6"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("postCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "PostCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Tel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fax");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Fax"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("country");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Country"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("language");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Language"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("category");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Category"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("EMail");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "EMail"));
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
