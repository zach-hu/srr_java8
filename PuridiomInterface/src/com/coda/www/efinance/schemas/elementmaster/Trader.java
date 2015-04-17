/**
 * Trader.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.elementmaster;


/**
 * This element holds a
 *             trader.
 */
public class Trader  implements java.io.Serializable {
    private java.lang.Short timeStamp;

    /* The code for the company in
     *                         which the trader is being
     *                     maintained. */
    private java.lang.String cmpCode;

    /* The
     *                         code of the umbrella element within which
     * the
     *                         trader is held. */
    private java.lang.String elmCode;

    /* The
     *                         level of the umbrella element within which
     * the
     *                         trader is held. A number from 1 to 8
     *                     inclusive. */
    private short elmLevel;

    /* The
     *                         trader code. */
    private java.lang.String code;

    /* The
     *                         name for the trader. */
    private java.lang.String name;

    /* Holds
     *                         tax information for the specified
     *                     trader. */
    private com.coda.www.efinance.schemas.elementmaster.Tax tax;

    /* Holds address information for
     *                         the specified trader. */
    private com.coda.www.efinance.schemas.elementmaster.TraderAddress address;

    /* Holds information about the
     *                         specified trader's
     *                     bank. */
    private com.coda.www.efinance.schemas.elementmaster.TraderBank bank;

    public Trader() {
    }

    public Trader(
           java.lang.Short timeStamp,
           java.lang.String cmpCode,
           java.lang.String elmCode,
           short elmLevel,
           java.lang.String code,
           java.lang.String name,
           com.coda.www.efinance.schemas.elementmaster.Tax tax,
           com.coda.www.efinance.schemas.elementmaster.TraderAddress address,
           com.coda.www.efinance.schemas.elementmaster.TraderBank bank) {
           this.timeStamp = timeStamp;
           this.cmpCode = cmpCode;
           this.elmCode = elmCode;
           this.elmLevel = elmLevel;
           this.code = code;
           this.name = name;
           this.tax = tax;
           this.address = address;
           this.bank = bank;
    }


    /**
     * Gets the timeStamp value for this Trader.
     * 
     * @return timeStamp
     */
    public java.lang.Short getTimeStamp() {
        return timeStamp;
    }


    /**
     * Sets the timeStamp value for this Trader.
     * 
     * @param timeStamp
     */
    public void setTimeStamp(java.lang.Short timeStamp) {
        this.timeStamp = timeStamp;
    }


    /**
     * Gets the cmpCode value for this Trader.
     * 
     * @return cmpCode   * The code for the company in
     *                         which the trader is being
     *                     maintained.
     */
    public java.lang.String getCmpCode() {
        return cmpCode;
    }


    /**
     * Sets the cmpCode value for this Trader.
     * 
     * @param cmpCode   * The code for the company in
     *                         which the trader is being
     *                     maintained.
     */
    public void setCmpCode(java.lang.String cmpCode) {
        this.cmpCode = cmpCode;
    }


    /**
     * Gets the elmCode value for this Trader.
     * 
     * @return elmCode   * The
     *                         code of the umbrella element within which
     * the
     *                         trader is held.
     */
    public java.lang.String getElmCode() {
        return elmCode;
    }


    /**
     * Sets the elmCode value for this Trader.
     * 
     * @param elmCode   * The
     *                         code of the umbrella element within which
     * the
     *                         trader is held.
     */
    public void setElmCode(java.lang.String elmCode) {
        this.elmCode = elmCode;
    }


    /**
     * Gets the elmLevel value for this Trader.
     * 
     * @return elmLevel   * The
     *                         level of the umbrella element within which
     * the
     *                         trader is held. A number from 1 to 8
     *                     inclusive.
     */
    public short getElmLevel() {
        return elmLevel;
    }


    /**
     * Sets the elmLevel value for this Trader.
     * 
     * @param elmLevel   * The
     *                         level of the umbrella element within which
     * the
     *                         trader is held. A number from 1 to 8
     *                     inclusive.
     */
    public void setElmLevel(short elmLevel) {
        this.elmLevel = elmLevel;
    }


    /**
     * Gets the code value for this Trader.
     * 
     * @return code   * The
     *                         trader code.
     */
    public java.lang.String getCode() {
        return code;
    }


    /**
     * Sets the code value for this Trader.
     * 
     * @param code   * The
     *                         trader code.
     */
    public void setCode(java.lang.String code) {
        this.code = code;
    }


    /**
     * Gets the name value for this Trader.
     * 
     * @return name   * The
     *                         name for the trader.
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this Trader.
     * 
     * @param name   * The
     *                         name for the trader.
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the tax value for this Trader.
     * 
     * @return tax   * Holds
     *                         tax information for the specified
     *                     trader.
     */
    public com.coda.www.efinance.schemas.elementmaster.Tax getTax() {
        return tax;
    }


    /**
     * Sets the tax value for this Trader.
     * 
     * @param tax   * Holds
     *                         tax information for the specified
     *                     trader.
     */
    public void setTax(com.coda.www.efinance.schemas.elementmaster.Tax tax) {
        this.tax = tax;
    }


    /**
     * Gets the address value for this Trader.
     * 
     * @return address   * Holds address information for
     *                         the specified trader.
     */
    public com.coda.www.efinance.schemas.elementmaster.TraderAddress getAddress() {
        return address;
    }


    /**
     * Sets the address value for this Trader.
     * 
     * @param address   * Holds address information for
     *                         the specified trader.
     */
    public void setAddress(com.coda.www.efinance.schemas.elementmaster.TraderAddress address) {
        this.address = address;
    }


    /**
     * Gets the bank value for this Trader.
     * 
     * @return bank   * Holds information about the
     *                         specified trader's
     *                     bank.
     */
    public com.coda.www.efinance.schemas.elementmaster.TraderBank getBank() {
        return bank;
    }


    /**
     * Sets the bank value for this Trader.
     * 
     * @param bank   * Holds information about the
     *                         specified trader's
     *                     bank.
     */
    public void setBank(com.coda.www.efinance.schemas.elementmaster.TraderBank bank) {
        this.bank = bank;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Trader)) return false;
        Trader other = (Trader) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.timeStamp==null && other.getTimeStamp()==null) || 
             (this.timeStamp!=null &&
              this.timeStamp.equals(other.getTimeStamp()))) &&
            ((this.cmpCode==null && other.getCmpCode()==null) || 
             (this.cmpCode!=null &&
              this.cmpCode.equals(other.getCmpCode()))) &&
            ((this.elmCode==null && other.getElmCode()==null) || 
             (this.elmCode!=null &&
              this.elmCode.equals(other.getElmCode()))) &&
            this.elmLevel == other.getElmLevel() &&
            ((this.code==null && other.getCode()==null) || 
             (this.code!=null &&
              this.code.equals(other.getCode()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.tax==null && other.getTax()==null) || 
             (this.tax!=null &&
              this.tax.equals(other.getTax()))) &&
            ((this.address==null && other.getAddress()==null) || 
             (this.address!=null &&
              this.address.equals(other.getAddress()))) &&
            ((this.bank==null && other.getBank()==null) || 
             (this.bank!=null &&
              this.bank.equals(other.getBank())));
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
        if (getTimeStamp() != null) {
            _hashCode += getTimeStamp().hashCode();
        }
        if (getCmpCode() != null) {
            _hashCode += getCmpCode().hashCode();
        }
        if (getElmCode() != null) {
            _hashCode += getElmCode().hashCode();
        }
        _hashCode += getElmLevel();
        if (getCode() != null) {
            _hashCode += getCode().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getTax() != null) {
            _hashCode += getTax().hashCode();
        }
        if (getAddress() != null) {
            _hashCode += getAddress().hashCode();
        }
        if (getBank() != null) {
            _hashCode += getBank().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Trader.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Trader"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timeStamp");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "TimeStamp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cmpCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "CmpCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("elmCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "ElmCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("elmLevel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "ElmLevel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("code");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Code"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tax");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Tax"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Tax"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("address");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Address"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "TraderAddress"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bank");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Bank"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "TraderBank"));
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
