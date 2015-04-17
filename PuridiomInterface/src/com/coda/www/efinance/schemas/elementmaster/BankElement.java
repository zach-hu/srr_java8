/**
 * BankElement.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.elementmaster;


/**
 * Holds details of one of the
 *                 element's banks.
 */
public class BankElement  implements java.io.Serializable {
    private boolean defaultBank;

    /* The
     *                         tag for this bank, a number or string which
     * can
     *                         be used to identify the
     *                     bank. */
    private java.lang.Short tag;

    /* The bank account
     *                     number. */
    private java.lang.String accountNumber;

    /* The
     *                         bank's sort code. */
    private java.lang.String sortCode;

    /* An account name for the
     *                     bank. */
    private java.lang.String accountName;

    /* The bank account reference,
     *                         which is used when making payments or
     *                         collections by electronic
     *                     transfer. */
    private java.lang.String accountRef;

    /* A name
     *                         for the person or company at this bank
     *                     address. */
    private java.lang.String bankName;

    /* An
     *                         address line. */
    private java.lang.String address1;

    /* An address
     *                     line. */
    private java.lang.String address2;

    /* An
     *                         address line. */
    private java.lang.String address3;

    /* An address
     *                     line. */
    private java.lang.String address4;

    /* An
     *                         address line. */
    private java.lang.String address5;

    /* An address
     *                     line. */
    private java.lang.String address6;

    /* The
     *                         postal code for this bank
     *                     address. */
    private java.lang.String postCode;

    /* The code for a country master
     *                         for this bank address. */
    private java.lang.String country;

    /* The bank's SWIFT
     *                     address. */
    private java.lang.String SWIFT;

    /* A name for the person or
     *                         company at this intermediary bank
     *                     address. */
    private java.lang.String intBankName;

    /* An address
     *                     line. */
    private java.lang.String intAddress1;

    /* An
     *                         address line. */
    private java.lang.String intAddress2;

    /* An address
     *                     line. */
    private java.lang.String intAddress3;

    /* An
     *                         address line. */
    private java.lang.String intAddress4;

    /* An address
     *                     line. */
    private java.lang.String intAddress5;

    /* An
     *                         address line. */
    private java.lang.String intAddress6;

    /* The postal code for this
     *                         intermediary bank address. */
    private java.lang.String intPostCode;

    /* The
     *                         code for a country master for this intermediary
     * bank address. */
    private java.lang.String intCountry;

    /* The International Bank
     *                         Account Number (IBAN) of the bank
     *                     account. */
    private java.lang.String IBAN;

    public BankElement() {
    }

    public BankElement(
           boolean defaultBank,
           java.lang.Short tag,
           java.lang.String accountNumber,
           java.lang.String sortCode,
           java.lang.String accountName,
           java.lang.String accountRef,
           java.lang.String bankName,
           java.lang.String address1,
           java.lang.String address2,
           java.lang.String address3,
           java.lang.String address4,
           java.lang.String address5,
           java.lang.String address6,
           java.lang.String postCode,
           java.lang.String country,
           java.lang.String SWIFT,
           java.lang.String intBankName,
           java.lang.String intAddress1,
           java.lang.String intAddress2,
           java.lang.String intAddress3,
           java.lang.String intAddress4,
           java.lang.String intAddress5,
           java.lang.String intAddress6,
           java.lang.String intPostCode,
           java.lang.String intCountry,
           java.lang.String IBAN) {
           this.defaultBank = defaultBank;
           this.tag = tag;
           this.accountNumber = accountNumber;
           this.sortCode = sortCode;
           this.accountName = accountName;
           this.accountRef = accountRef;
           this.bankName = bankName;
           this.address1 = address1;
           this.address2 = address2;
           this.address3 = address3;
           this.address4 = address4;
           this.address5 = address5;
           this.address6 = address6;
           this.postCode = postCode;
           this.country = country;
           this.SWIFT = SWIFT;
           this.intBankName = intBankName;
           this.intAddress1 = intAddress1;
           this.intAddress2 = intAddress2;
           this.intAddress3 = intAddress3;
           this.intAddress4 = intAddress4;
           this.intAddress5 = intAddress5;
           this.intAddress6 = intAddress6;
           this.intPostCode = intPostCode;
           this.intCountry = intCountry;
           this.IBAN = IBAN;
    }


    /**
     * Gets the defaultBank value for this BankElement.
     * 
     * @return defaultBank
     */
    public boolean isDefaultBank() {
        return defaultBank;
    }


    /**
     * Sets the defaultBank value for this BankElement.
     * 
     * @param defaultBank
     */
    public void setDefaultBank(boolean defaultBank) {
        this.defaultBank = defaultBank;
    }


    /**
     * Gets the tag value for this BankElement.
     * 
     * @return tag   * The
     *                         tag for this bank, a number or string which
     * can
     *                         be used to identify the
     *                     bank.
     */
    public java.lang.Short getTag() {
        return tag;
    }


    /**
     * Sets the tag value for this BankElement.
     * 
     * @param tag   * The
     *                         tag for this bank, a number or string which
     * can
     *                         be used to identify the
     *                     bank.
     */
    public void setTag(java.lang.Short tag) {
        this.tag = tag;
    }


    /**
     * Gets the accountNumber value for this BankElement.
     * 
     * @return accountNumber   * The bank account
     *                     number.
     */
    public java.lang.String getAccountNumber() {
        return accountNumber;
    }


    /**
     * Sets the accountNumber value for this BankElement.
     * 
     * @param accountNumber   * The bank account
     *                     number.
     */
    public void setAccountNumber(java.lang.String accountNumber) {
        this.accountNumber = accountNumber;
    }


    /**
     * Gets the sortCode value for this BankElement.
     * 
     * @return sortCode   * The
     *                         bank's sort code.
     */
    public java.lang.String getSortCode() {
        return sortCode;
    }


    /**
     * Sets the sortCode value for this BankElement.
     * 
     * @param sortCode   * The
     *                         bank's sort code.
     */
    public void setSortCode(java.lang.String sortCode) {
        this.sortCode = sortCode;
    }


    /**
     * Gets the accountName value for this BankElement.
     * 
     * @return accountName   * An account name for the
     *                     bank.
     */
    public java.lang.String getAccountName() {
        return accountName;
    }


    /**
     * Sets the accountName value for this BankElement.
     * 
     * @param accountName   * An account name for the
     *                     bank.
     */
    public void setAccountName(java.lang.String accountName) {
        this.accountName = accountName;
    }


    /**
     * Gets the accountRef value for this BankElement.
     * 
     * @return accountRef   * The bank account reference,
     *                         which is used when making payments or
     *                         collections by electronic
     *                     transfer.
     */
    public java.lang.String getAccountRef() {
        return accountRef;
    }


    /**
     * Sets the accountRef value for this BankElement.
     * 
     * @param accountRef   * The bank account reference,
     *                         which is used when making payments or
     *                         collections by electronic
     *                     transfer.
     */
    public void setAccountRef(java.lang.String accountRef) {
        this.accountRef = accountRef;
    }


    /**
     * Gets the bankName value for this BankElement.
     * 
     * @return bankName   * A name
     *                         for the person or company at this bank
     *                     address.
     */
    public java.lang.String getBankName() {
        return bankName;
    }


    /**
     * Sets the bankName value for this BankElement.
     * 
     * @param bankName   * A name
     *                         for the person or company at this bank
     *                     address.
     */
    public void setBankName(java.lang.String bankName) {
        this.bankName = bankName;
    }


    /**
     * Gets the address1 value for this BankElement.
     * 
     * @return address1   * An
     *                         address line.
     */
    public java.lang.String getAddress1() {
        return address1;
    }


    /**
     * Sets the address1 value for this BankElement.
     * 
     * @param address1   * An
     *                         address line.
     */
    public void setAddress1(java.lang.String address1) {
        this.address1 = address1;
    }


    /**
     * Gets the address2 value for this BankElement.
     * 
     * @return address2   * An address
     *                     line.
     */
    public java.lang.String getAddress2() {
        return address2;
    }


    /**
     * Sets the address2 value for this BankElement.
     * 
     * @param address2   * An address
     *                     line.
     */
    public void setAddress2(java.lang.String address2) {
        this.address2 = address2;
    }


    /**
     * Gets the address3 value for this BankElement.
     * 
     * @return address3   * An
     *                         address line.
     */
    public java.lang.String getAddress3() {
        return address3;
    }


    /**
     * Sets the address3 value for this BankElement.
     * 
     * @param address3   * An
     *                         address line.
     */
    public void setAddress3(java.lang.String address3) {
        this.address3 = address3;
    }


    /**
     * Gets the address4 value for this BankElement.
     * 
     * @return address4   * An address
     *                     line.
     */
    public java.lang.String getAddress4() {
        return address4;
    }


    /**
     * Sets the address4 value for this BankElement.
     * 
     * @param address4   * An address
     *                     line.
     */
    public void setAddress4(java.lang.String address4) {
        this.address4 = address4;
    }


    /**
     * Gets the address5 value for this BankElement.
     * 
     * @return address5   * An
     *                         address line.
     */
    public java.lang.String getAddress5() {
        return address5;
    }


    /**
     * Sets the address5 value for this BankElement.
     * 
     * @param address5   * An
     *                         address line.
     */
    public void setAddress5(java.lang.String address5) {
        this.address5 = address5;
    }


    /**
     * Gets the address6 value for this BankElement.
     * 
     * @return address6   * An address
     *                     line.
     */
    public java.lang.String getAddress6() {
        return address6;
    }


    /**
     * Sets the address6 value for this BankElement.
     * 
     * @param address6   * An address
     *                     line.
     */
    public void setAddress6(java.lang.String address6) {
        this.address6 = address6;
    }


    /**
     * Gets the postCode value for this BankElement.
     * 
     * @return postCode   * The
     *                         postal code for this bank
     *                     address.
     */
    public java.lang.String getPostCode() {
        return postCode;
    }


    /**
     * Sets the postCode value for this BankElement.
     * 
     * @param postCode   * The
     *                         postal code for this bank
     *                     address.
     */
    public void setPostCode(java.lang.String postCode) {
        this.postCode = postCode;
    }


    /**
     * Gets the country value for this BankElement.
     * 
     * @return country   * The code for a country master
     *                         for this bank address.
     */
    public java.lang.String getCountry() {
        return country;
    }


    /**
     * Sets the country value for this BankElement.
     * 
     * @param country   * The code for a country master
     *                         for this bank address.
     */
    public void setCountry(java.lang.String country) {
        this.country = country;
    }


    /**
     * Gets the SWIFT value for this BankElement.
     * 
     * @return SWIFT   * The bank's SWIFT
     *                     address.
     */
    public java.lang.String getSWIFT() {
        return SWIFT;
    }


    /**
     * Sets the SWIFT value for this BankElement.
     * 
     * @param SWIFT   * The bank's SWIFT
     *                     address.
     */
    public void setSWIFT(java.lang.String SWIFT) {
        this.SWIFT = SWIFT;
    }


    /**
     * Gets the intBankName value for this BankElement.
     * 
     * @return intBankName   * A name for the person or
     *                         company at this intermediary bank
     *                     address.
     */
    public java.lang.String getIntBankName() {
        return intBankName;
    }


    /**
     * Sets the intBankName value for this BankElement.
     * 
     * @param intBankName   * A name for the person or
     *                         company at this intermediary bank
     *                     address.
     */
    public void setIntBankName(java.lang.String intBankName) {
        this.intBankName = intBankName;
    }


    /**
     * Gets the intAddress1 value for this BankElement.
     * 
     * @return intAddress1   * An address
     *                     line.
     */
    public java.lang.String getIntAddress1() {
        return intAddress1;
    }


    /**
     * Sets the intAddress1 value for this BankElement.
     * 
     * @param intAddress1   * An address
     *                     line.
     */
    public void setIntAddress1(java.lang.String intAddress1) {
        this.intAddress1 = intAddress1;
    }


    /**
     * Gets the intAddress2 value for this BankElement.
     * 
     * @return intAddress2   * An
     *                         address line.
     */
    public java.lang.String getIntAddress2() {
        return intAddress2;
    }


    /**
     * Sets the intAddress2 value for this BankElement.
     * 
     * @param intAddress2   * An
     *                         address line.
     */
    public void setIntAddress2(java.lang.String intAddress2) {
        this.intAddress2 = intAddress2;
    }


    /**
     * Gets the intAddress3 value for this BankElement.
     * 
     * @return intAddress3   * An address
     *                     line.
     */
    public java.lang.String getIntAddress3() {
        return intAddress3;
    }


    /**
     * Sets the intAddress3 value for this BankElement.
     * 
     * @param intAddress3   * An address
     *                     line.
     */
    public void setIntAddress3(java.lang.String intAddress3) {
        this.intAddress3 = intAddress3;
    }


    /**
     * Gets the intAddress4 value for this BankElement.
     * 
     * @return intAddress4   * An
     *                         address line.
     */
    public java.lang.String getIntAddress4() {
        return intAddress4;
    }


    /**
     * Sets the intAddress4 value for this BankElement.
     * 
     * @param intAddress4   * An
     *                         address line.
     */
    public void setIntAddress4(java.lang.String intAddress4) {
        this.intAddress4 = intAddress4;
    }


    /**
     * Gets the intAddress5 value for this BankElement.
     * 
     * @return intAddress5   * An address
     *                     line.
     */
    public java.lang.String getIntAddress5() {
        return intAddress5;
    }


    /**
     * Sets the intAddress5 value for this BankElement.
     * 
     * @param intAddress5   * An address
     *                     line.
     */
    public void setIntAddress5(java.lang.String intAddress5) {
        this.intAddress5 = intAddress5;
    }


    /**
     * Gets the intAddress6 value for this BankElement.
     * 
     * @return intAddress6   * An
     *                         address line.
     */
    public java.lang.String getIntAddress6() {
        return intAddress6;
    }


    /**
     * Sets the intAddress6 value for this BankElement.
     * 
     * @param intAddress6   * An
     *                         address line.
     */
    public void setIntAddress6(java.lang.String intAddress6) {
        this.intAddress6 = intAddress6;
    }


    /**
     * Gets the intPostCode value for this BankElement.
     * 
     * @return intPostCode   * The postal code for this
     *                         intermediary bank address.
     */
    public java.lang.String getIntPostCode() {
        return intPostCode;
    }


    /**
     * Sets the intPostCode value for this BankElement.
     * 
     * @param intPostCode   * The postal code for this
     *                         intermediary bank address.
     */
    public void setIntPostCode(java.lang.String intPostCode) {
        this.intPostCode = intPostCode;
    }


    /**
     * Gets the intCountry value for this BankElement.
     * 
     * @return intCountry   * The
     *                         code for a country master for this intermediary
     * bank address.
     */
    public java.lang.String getIntCountry() {
        return intCountry;
    }


    /**
     * Sets the intCountry value for this BankElement.
     * 
     * @param intCountry   * The
     *                         code for a country master for this intermediary
     * bank address.
     */
    public void setIntCountry(java.lang.String intCountry) {
        this.intCountry = intCountry;
    }


    /**
     * Gets the IBAN value for this BankElement.
     * 
     * @return IBAN   * The International Bank
     *                         Account Number (IBAN) of the bank
     *                     account.
     */
    public java.lang.String getIBAN() {
        return IBAN;
    }


    /**
     * Sets the IBAN value for this BankElement.
     * 
     * @param IBAN   * The International Bank
     *                         Account Number (IBAN) of the bank
     *                     account.
     */
    public void setIBAN(java.lang.String IBAN) {
        this.IBAN = IBAN;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BankElement)) return false;
        BankElement other = (BankElement) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.defaultBank == other.isDefaultBank() &&
            ((this.tag==null && other.getTag()==null) || 
             (this.tag!=null &&
              this.tag.equals(other.getTag()))) &&
            ((this.accountNumber==null && other.getAccountNumber()==null) || 
             (this.accountNumber!=null &&
              this.accountNumber.equals(other.getAccountNumber()))) &&
            ((this.sortCode==null && other.getSortCode()==null) || 
             (this.sortCode!=null &&
              this.sortCode.equals(other.getSortCode()))) &&
            ((this.accountName==null && other.getAccountName()==null) || 
             (this.accountName!=null &&
              this.accountName.equals(other.getAccountName()))) &&
            ((this.accountRef==null && other.getAccountRef()==null) || 
             (this.accountRef!=null &&
              this.accountRef.equals(other.getAccountRef()))) &&
            ((this.bankName==null && other.getBankName()==null) || 
             (this.bankName!=null &&
              this.bankName.equals(other.getBankName()))) &&
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
            ((this.country==null && other.getCountry()==null) || 
             (this.country!=null &&
              this.country.equals(other.getCountry()))) &&
            ((this.SWIFT==null && other.getSWIFT()==null) || 
             (this.SWIFT!=null &&
              this.SWIFT.equals(other.getSWIFT()))) &&
            ((this.intBankName==null && other.getIntBankName()==null) || 
             (this.intBankName!=null &&
              this.intBankName.equals(other.getIntBankName()))) &&
            ((this.intAddress1==null && other.getIntAddress1()==null) || 
             (this.intAddress1!=null &&
              this.intAddress1.equals(other.getIntAddress1()))) &&
            ((this.intAddress2==null && other.getIntAddress2()==null) || 
             (this.intAddress2!=null &&
              this.intAddress2.equals(other.getIntAddress2()))) &&
            ((this.intAddress3==null && other.getIntAddress3()==null) || 
             (this.intAddress3!=null &&
              this.intAddress3.equals(other.getIntAddress3()))) &&
            ((this.intAddress4==null && other.getIntAddress4()==null) || 
             (this.intAddress4!=null &&
              this.intAddress4.equals(other.getIntAddress4()))) &&
            ((this.intAddress5==null && other.getIntAddress5()==null) || 
             (this.intAddress5!=null &&
              this.intAddress5.equals(other.getIntAddress5()))) &&
            ((this.intAddress6==null && other.getIntAddress6()==null) || 
             (this.intAddress6!=null &&
              this.intAddress6.equals(other.getIntAddress6()))) &&
            ((this.intPostCode==null && other.getIntPostCode()==null) || 
             (this.intPostCode!=null &&
              this.intPostCode.equals(other.getIntPostCode()))) &&
            ((this.intCountry==null && other.getIntCountry()==null) || 
             (this.intCountry!=null &&
              this.intCountry.equals(other.getIntCountry()))) &&
            ((this.IBAN==null && other.getIBAN()==null) || 
             (this.IBAN!=null &&
              this.IBAN.equals(other.getIBAN())));
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
        _hashCode += (isDefaultBank() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getTag() != null) {
            _hashCode += getTag().hashCode();
        }
        if (getAccountNumber() != null) {
            _hashCode += getAccountNumber().hashCode();
        }
        if (getSortCode() != null) {
            _hashCode += getSortCode().hashCode();
        }
        if (getAccountName() != null) {
            _hashCode += getAccountName().hashCode();
        }
        if (getAccountRef() != null) {
            _hashCode += getAccountRef().hashCode();
        }
        if (getBankName() != null) {
            _hashCode += getBankName().hashCode();
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
        if (getCountry() != null) {
            _hashCode += getCountry().hashCode();
        }
        if (getSWIFT() != null) {
            _hashCode += getSWIFT().hashCode();
        }
        if (getIntBankName() != null) {
            _hashCode += getIntBankName().hashCode();
        }
        if (getIntAddress1() != null) {
            _hashCode += getIntAddress1().hashCode();
        }
        if (getIntAddress2() != null) {
            _hashCode += getIntAddress2().hashCode();
        }
        if (getIntAddress3() != null) {
            _hashCode += getIntAddress3().hashCode();
        }
        if (getIntAddress4() != null) {
            _hashCode += getIntAddress4().hashCode();
        }
        if (getIntAddress5() != null) {
            _hashCode += getIntAddress5().hashCode();
        }
        if (getIntAddress6() != null) {
            _hashCode += getIntAddress6().hashCode();
        }
        if (getIntPostCode() != null) {
            _hashCode += getIntPostCode().hashCode();
        }
        if (getIntCountry() != null) {
            _hashCode += getIntCountry().hashCode();
        }
        if (getIBAN() != null) {
            _hashCode += getIBAN().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BankElement.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "BankElement"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("defaultBank");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "DefaultBank"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
        elemField.setFieldName("accountNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "AccountNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sortCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "SortCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "AccountName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountRef");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "AccountRef"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bankName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "BankName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("address1");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Address1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
        elemField.setFieldName("country");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Country"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SWIFT");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "SWIFT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("intBankName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "IntBankName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("intAddress1");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "IntAddress1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("intAddress2");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "IntAddress2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("intAddress3");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "IntAddress3"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("intAddress4");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "IntAddress4"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("intAddress5");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "IntAddress5"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("intAddress6");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "IntAddress6"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("intPostCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "IntPostCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("intCountry");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "IntCountry"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IBAN");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "IBAN"));
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
