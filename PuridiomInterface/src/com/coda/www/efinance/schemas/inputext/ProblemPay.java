/**
 * ProblemPay.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.inputext;


/**
 * Contains payment information that
 *                 needs to be specified on the problem document line.
 * This
 *                 information can be completed, then re-submitted with
 * the
 *                 original Post or CheckPost request.
 */
public class ProblemPay  implements java.io.Serializable {
    private java.lang.String matchableElementCode;

    /* The short name of the
     *                         matchable element on this document
     *                     line. */
    private java.lang.String matchableElementShortName;

    /* The code of the media master
     *                         used for the payment or collection of this
     * document line value. */
    private com.coda.www.efinance.schemas.inputext.PayValue mediaCode;

    /* The
     *                         code of the bank master used for the payment
     * or
     *                         collection of this document line
     *                     value. */
    private com.coda.www.efinance.schemas.inputext.PayValue bankCode;

    /* The bank of the matchable
     *                         element on this document
     *                     line. */
    private com.coda.www.efinance.schemas.inputext.PayValue elmBank;

    /* The address of the matchable
     *                         element on this document
     *                     line. */
    private com.coda.www.efinance.schemas.inputext.PayValue elmAddr;

    /* User
     *                         reference 1 on this document
     *                     line. */
    private com.coda.www.efinance.schemas.inputext.ProblemUserReference userRef1;

    /* User
     *                         reference 2 on this document
     *                     line. */
    private com.coda.www.efinance.schemas.inputext.ProblemUserReference userRef2;

    /* User
     *                         reference 3 on this document
     *                     line. */
    private com.coda.www.efinance.schemas.inputext.ProblemUserReference userRef3;

    /* Indicates whether the
     *                         document line is summarised (for payment or
     * collection) with other document lines. When
     *                         FALSE, the document line is not
     *                     summarised. */
    private boolean disableSummaries;

    public ProblemPay() {
    }

    public ProblemPay(
           java.lang.String matchableElementCode,
           java.lang.String matchableElementShortName,
           com.coda.www.efinance.schemas.inputext.PayValue mediaCode,
           com.coda.www.efinance.schemas.inputext.PayValue bankCode,
           com.coda.www.efinance.schemas.inputext.PayValue elmBank,
           com.coda.www.efinance.schemas.inputext.PayValue elmAddr,
           com.coda.www.efinance.schemas.inputext.ProblemUserReference userRef1,
           com.coda.www.efinance.schemas.inputext.ProblemUserReference userRef2,
           com.coda.www.efinance.schemas.inputext.ProblemUserReference userRef3,
           boolean disableSummaries) {
           this.matchableElementCode = matchableElementCode;
           this.matchableElementShortName = matchableElementShortName;
           this.mediaCode = mediaCode;
           this.bankCode = bankCode;
           this.elmBank = elmBank;
           this.elmAddr = elmAddr;
           this.userRef1 = userRef1;
           this.userRef2 = userRef2;
           this.userRef3 = userRef3;
           this.disableSummaries = disableSummaries;
    }


    /**
     * Gets the matchableElementCode value for this ProblemPay.
     * 
     * @return matchableElementCode
     */
    public java.lang.String getMatchableElementCode() {
        return matchableElementCode;
    }


    /**
     * Sets the matchableElementCode value for this ProblemPay.
     * 
     * @param matchableElementCode
     */
    public void setMatchableElementCode(java.lang.String matchableElementCode) {
        this.matchableElementCode = matchableElementCode;
    }


    /**
     * Gets the matchableElementShortName value for this ProblemPay.
     * 
     * @return matchableElementShortName   * The short name of the
     *                         matchable element on this document
     *                     line.
     */
    public java.lang.String getMatchableElementShortName() {
        return matchableElementShortName;
    }


    /**
     * Sets the matchableElementShortName value for this ProblemPay.
     * 
     * @param matchableElementShortName   * The short name of the
     *                         matchable element on this document
     *                     line.
     */
    public void setMatchableElementShortName(java.lang.String matchableElementShortName) {
        this.matchableElementShortName = matchableElementShortName;
    }


    /**
     * Gets the mediaCode value for this ProblemPay.
     * 
     * @return mediaCode   * The code of the media master
     *                         used for the payment or collection of this
     * document line value.
     */
    public com.coda.www.efinance.schemas.inputext.PayValue getMediaCode() {
        return mediaCode;
    }


    /**
     * Sets the mediaCode value for this ProblemPay.
     * 
     * @param mediaCode   * The code of the media master
     *                         used for the payment or collection of this
     * document line value.
     */
    public void setMediaCode(com.coda.www.efinance.schemas.inputext.PayValue mediaCode) {
        this.mediaCode = mediaCode;
    }


    /**
     * Gets the bankCode value for this ProblemPay.
     * 
     * @return bankCode   * The
     *                         code of the bank master used for the payment
     * or
     *                         collection of this document line
     *                     value.
     */
    public com.coda.www.efinance.schemas.inputext.PayValue getBankCode() {
        return bankCode;
    }


    /**
     * Sets the bankCode value for this ProblemPay.
     * 
     * @param bankCode   * The
     *                         code of the bank master used for the payment
     * or
     *                         collection of this document line
     *                     value.
     */
    public void setBankCode(com.coda.www.efinance.schemas.inputext.PayValue bankCode) {
        this.bankCode = bankCode;
    }


    /**
     * Gets the elmBank value for this ProblemPay.
     * 
     * @return elmBank   * The bank of the matchable
     *                         element on this document
     *                     line.
     */
    public com.coda.www.efinance.schemas.inputext.PayValue getElmBank() {
        return elmBank;
    }


    /**
     * Sets the elmBank value for this ProblemPay.
     * 
     * @param elmBank   * The bank of the matchable
     *                         element on this document
     *                     line.
     */
    public void setElmBank(com.coda.www.efinance.schemas.inputext.PayValue elmBank) {
        this.elmBank = elmBank;
    }


    /**
     * Gets the elmAddr value for this ProblemPay.
     * 
     * @return elmAddr   * The address of the matchable
     *                         element on this document
     *                     line.
     */
    public com.coda.www.efinance.schemas.inputext.PayValue getElmAddr() {
        return elmAddr;
    }


    /**
     * Sets the elmAddr value for this ProblemPay.
     * 
     * @param elmAddr   * The address of the matchable
     *                         element on this document
     *                     line.
     */
    public void setElmAddr(com.coda.www.efinance.schemas.inputext.PayValue elmAddr) {
        this.elmAddr = elmAddr;
    }


    /**
     * Gets the userRef1 value for this ProblemPay.
     * 
     * @return userRef1   * User
     *                         reference 1 on this document
     *                     line.
     */
    public com.coda.www.efinance.schemas.inputext.ProblemUserReference getUserRef1() {
        return userRef1;
    }


    /**
     * Sets the userRef1 value for this ProblemPay.
     * 
     * @param userRef1   * User
     *                         reference 1 on this document
     *                     line.
     */
    public void setUserRef1(com.coda.www.efinance.schemas.inputext.ProblemUserReference userRef1) {
        this.userRef1 = userRef1;
    }


    /**
     * Gets the userRef2 value for this ProblemPay.
     * 
     * @return userRef2   * User
     *                         reference 2 on this document
     *                     line.
     */
    public com.coda.www.efinance.schemas.inputext.ProblemUserReference getUserRef2() {
        return userRef2;
    }


    /**
     * Sets the userRef2 value for this ProblemPay.
     * 
     * @param userRef2   * User
     *                         reference 2 on this document
     *                     line.
     */
    public void setUserRef2(com.coda.www.efinance.schemas.inputext.ProblemUserReference userRef2) {
        this.userRef2 = userRef2;
    }


    /**
     * Gets the userRef3 value for this ProblemPay.
     * 
     * @return userRef3   * User
     *                         reference 3 on this document
     *                     line.
     */
    public com.coda.www.efinance.schemas.inputext.ProblemUserReference getUserRef3() {
        return userRef3;
    }


    /**
     * Sets the userRef3 value for this ProblemPay.
     * 
     * @param userRef3   * User
     *                         reference 3 on this document
     *                     line.
     */
    public void setUserRef3(com.coda.www.efinance.schemas.inputext.ProblemUserReference userRef3) {
        this.userRef3 = userRef3;
    }


    /**
     * Gets the disableSummaries value for this ProblemPay.
     * 
     * @return disableSummaries   * Indicates whether the
     *                         document line is summarised (for payment or
     * collection) with other document lines. When
     *                         FALSE, the document line is not
     *                     summarised.
     */
    public boolean isDisableSummaries() {
        return disableSummaries;
    }


    /**
     * Sets the disableSummaries value for this ProblemPay.
     * 
     * @param disableSummaries   * Indicates whether the
     *                         document line is summarised (for payment or
     * collection) with other document lines. When
     *                         FALSE, the document line is not
     *                     summarised.
     */
    public void setDisableSummaries(boolean disableSummaries) {
        this.disableSummaries = disableSummaries;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ProblemPay)) return false;
        ProblemPay other = (ProblemPay) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.matchableElementCode==null && other.getMatchableElementCode()==null) || 
             (this.matchableElementCode!=null &&
              this.matchableElementCode.equals(other.getMatchableElementCode()))) &&
            ((this.matchableElementShortName==null && other.getMatchableElementShortName()==null) || 
             (this.matchableElementShortName!=null &&
              this.matchableElementShortName.equals(other.getMatchableElementShortName()))) &&
            ((this.mediaCode==null && other.getMediaCode()==null) || 
             (this.mediaCode!=null &&
              this.mediaCode.equals(other.getMediaCode()))) &&
            ((this.bankCode==null && other.getBankCode()==null) || 
             (this.bankCode!=null &&
              this.bankCode.equals(other.getBankCode()))) &&
            ((this.elmBank==null && other.getElmBank()==null) || 
             (this.elmBank!=null &&
              this.elmBank.equals(other.getElmBank()))) &&
            ((this.elmAddr==null && other.getElmAddr()==null) || 
             (this.elmAddr!=null &&
              this.elmAddr.equals(other.getElmAddr()))) &&
            ((this.userRef1==null && other.getUserRef1()==null) || 
             (this.userRef1!=null &&
              this.userRef1.equals(other.getUserRef1()))) &&
            ((this.userRef2==null && other.getUserRef2()==null) || 
             (this.userRef2!=null &&
              this.userRef2.equals(other.getUserRef2()))) &&
            ((this.userRef3==null && other.getUserRef3()==null) || 
             (this.userRef3!=null &&
              this.userRef3.equals(other.getUserRef3()))) &&
            this.disableSummaries == other.isDisableSummaries();
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
        if (getMatchableElementCode() != null) {
            _hashCode += getMatchableElementCode().hashCode();
        }
        if (getMatchableElementShortName() != null) {
            _hashCode += getMatchableElementShortName().hashCode();
        }
        if (getMediaCode() != null) {
            _hashCode += getMediaCode().hashCode();
        }
        if (getBankCode() != null) {
            _hashCode += getBankCode().hashCode();
        }
        if (getElmBank() != null) {
            _hashCode += getElmBank().hashCode();
        }
        if (getElmAddr() != null) {
            _hashCode += getElmAddr().hashCode();
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
        _hashCode += (isDisableSummaries() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ProblemPay.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "ProblemPay"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("matchableElementCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "MatchableElementCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("matchableElementShortName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "MatchableElementShortName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mediaCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "MediaCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "PayValue"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bankCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "BankCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "PayValue"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("elmBank");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "ElmBank"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "PayValue"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("elmAddr");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "ElmAddr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "PayValue"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userRef1");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "UserRef1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "ProblemUserReference"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userRef2");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "UserRef2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "ProblemUserReference"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userRef3");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "UserRef3"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "ProblemUserReference"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("disableSummaries");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "DisableSummaries"));
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
