/**
 * ExtraPayData.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.inputext;


/**
 * Contains payment information on the
 *                 problem document line. This information is not returned
 * if the PostRequest or CheckPostRequest has
 *                 ignoresurplusdata set to TRUE.
 */
public class ExtraPayData  implements java.io.Serializable {
    private java.lang.String mediaCode;

    /* The code of the bank master
     *                         used for the payment or collection of this
     * document line value. */
    private java.lang.String bankCode;

    /* The tag identifier for the
     *                         element bank for the customer/supplier element
     * on this document line. */
    private java.lang.Short elmBankTag;

    /* The tag identifier for the
     *                         element address for the customer/supplier
     *                         element on this document
     *                     line. */
    private java.lang.Short elmAddrTag;

    /* User
     *                         reference 1 on this document
     *                     line. */
    private java.lang.String userRef1;

    /* User
     *                         reference 2 on this document
     *                     line. */
    private java.lang.String userRef2;

    /* User
     *                         reference 3 on this document
     *                     line. */
    private java.lang.String userRef3;

    public ExtraPayData() {
    }

    public ExtraPayData(
           java.lang.String mediaCode,
           java.lang.String bankCode,
           java.lang.Short elmBankTag,
           java.lang.Short elmAddrTag,
           java.lang.String userRef1,
           java.lang.String userRef2,
           java.lang.String userRef3) {
           this.mediaCode = mediaCode;
           this.bankCode = bankCode;
           this.elmBankTag = elmBankTag;
           this.elmAddrTag = elmAddrTag;
           this.userRef1 = userRef1;
           this.userRef2 = userRef2;
           this.userRef3 = userRef3;
    }


    /**
     * Gets the mediaCode value for this ExtraPayData.
     * 
     * @return mediaCode
     */
    public java.lang.String getMediaCode() {
        return mediaCode;
    }


    /**
     * Sets the mediaCode value for this ExtraPayData.
     * 
     * @param mediaCode
     */
    public void setMediaCode(java.lang.String mediaCode) {
        this.mediaCode = mediaCode;
    }


    /**
     * Gets the bankCode value for this ExtraPayData.
     * 
     * @return bankCode   * The code of the bank master
     *                         used for the payment or collection of this
     * document line value.
     */
    public java.lang.String getBankCode() {
        return bankCode;
    }


    /**
     * Sets the bankCode value for this ExtraPayData.
     * 
     * @param bankCode   * The code of the bank master
     *                         used for the payment or collection of this
     * document line value.
     */
    public void setBankCode(java.lang.String bankCode) {
        this.bankCode = bankCode;
    }


    /**
     * Gets the elmBankTag value for this ExtraPayData.
     * 
     * @return elmBankTag   * The tag identifier for the
     *                         element bank for the customer/supplier element
     * on this document line.
     */
    public java.lang.Short getElmBankTag() {
        return elmBankTag;
    }


    /**
     * Sets the elmBankTag value for this ExtraPayData.
     * 
     * @param elmBankTag   * The tag identifier for the
     *                         element bank for the customer/supplier element
     * on this document line.
     */
    public void setElmBankTag(java.lang.Short elmBankTag) {
        this.elmBankTag = elmBankTag;
    }


    /**
     * Gets the elmAddrTag value for this ExtraPayData.
     * 
     * @return elmAddrTag   * The tag identifier for the
     *                         element address for the customer/supplier
     *                         element on this document
     *                     line.
     */
    public java.lang.Short getElmAddrTag() {
        return elmAddrTag;
    }


    /**
     * Sets the elmAddrTag value for this ExtraPayData.
     * 
     * @param elmAddrTag   * The tag identifier for the
     *                         element address for the customer/supplier
     *                         element on this document
     *                     line.
     */
    public void setElmAddrTag(java.lang.Short elmAddrTag) {
        this.elmAddrTag = elmAddrTag;
    }


    /**
     * Gets the userRef1 value for this ExtraPayData.
     * 
     * @return userRef1   * User
     *                         reference 1 on this document
     *                     line.
     */
    public java.lang.String getUserRef1() {
        return userRef1;
    }


    /**
     * Sets the userRef1 value for this ExtraPayData.
     * 
     * @param userRef1   * User
     *                         reference 1 on this document
     *                     line.
     */
    public void setUserRef1(java.lang.String userRef1) {
        this.userRef1 = userRef1;
    }


    /**
     * Gets the userRef2 value for this ExtraPayData.
     * 
     * @return userRef2   * User
     *                         reference 2 on this document
     *                     line.
     */
    public java.lang.String getUserRef2() {
        return userRef2;
    }


    /**
     * Sets the userRef2 value for this ExtraPayData.
     * 
     * @param userRef2   * User
     *                         reference 2 on this document
     *                     line.
     */
    public void setUserRef2(java.lang.String userRef2) {
        this.userRef2 = userRef2;
    }


    /**
     * Gets the userRef3 value for this ExtraPayData.
     * 
     * @return userRef3   * User
     *                         reference 3 on this document
     *                     line.
     */
    public java.lang.String getUserRef3() {
        return userRef3;
    }


    /**
     * Sets the userRef3 value for this ExtraPayData.
     * 
     * @param userRef3   * User
     *                         reference 3 on this document
     *                     line.
     */
    public void setUserRef3(java.lang.String userRef3) {
        this.userRef3 = userRef3;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ExtraPayData)) return false;
        ExtraPayData other = (ExtraPayData) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.mediaCode==null && other.getMediaCode()==null) || 
             (this.mediaCode!=null &&
              this.mediaCode.equals(other.getMediaCode()))) &&
            ((this.bankCode==null && other.getBankCode()==null) || 
             (this.bankCode!=null &&
              this.bankCode.equals(other.getBankCode()))) &&
            ((this.elmBankTag==null && other.getElmBankTag()==null) || 
             (this.elmBankTag!=null &&
              this.elmBankTag.equals(other.getElmBankTag()))) &&
            ((this.elmAddrTag==null && other.getElmAddrTag()==null) || 
             (this.elmAddrTag!=null &&
              this.elmAddrTag.equals(other.getElmAddrTag()))) &&
            ((this.userRef1==null && other.getUserRef1()==null) || 
             (this.userRef1!=null &&
              this.userRef1.equals(other.getUserRef1()))) &&
            ((this.userRef2==null && other.getUserRef2()==null) || 
             (this.userRef2!=null &&
              this.userRef2.equals(other.getUserRef2()))) &&
            ((this.userRef3==null && other.getUserRef3()==null) || 
             (this.userRef3!=null &&
              this.userRef3.equals(other.getUserRef3())));
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
        if (getMediaCode() != null) {
            _hashCode += getMediaCode().hashCode();
        }
        if (getBankCode() != null) {
            _hashCode += getBankCode().hashCode();
        }
        if (getElmBankTag() != null) {
            _hashCode += getElmBankTag().hashCode();
        }
        if (getElmAddrTag() != null) {
            _hashCode += getElmAddrTag().hashCode();
        }
        if (getUserRef1() != null) {
            _hashCode += getUserRef1().hashCode();
        }
        if (getUserRef2() != null) {
            _hashCode += getUserRef2().hashCode();
        }
        if (getUserRef3() != null) {
            _hashCode += getUserRef3().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ExtraPayData.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "ExtraPayData"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mediaCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "MediaCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bankCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "BankCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("elmBankTag");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "ElmBankTag"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("elmAddrTag");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "ElmAddrTag"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userRef1");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "UserRef1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userRef2");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "UserRef2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userRef3");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "UserRef3"));
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
