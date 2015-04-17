/**
 * GetResponsibleUsersRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.transaction.account_11_2.webservice;

public class GetResponsibleUsersRequest  implements java.io.Serializable {
    /* A company code. */
    private java.lang.String companyCode;

    /* A list of destination and
     *                             account combinations */
    private com.coda.www.efinance.schemas.transaction.DestAndAccount[] accounts;

    /* The scope of an element responsibilty. */
    private java.lang.String usage;

    /* A list of user codes. */
    private java.lang.String[] users;

    /* A currency code. */
    private java.lang.String currencyCode;

    /* A monetary value. */
    private java.math.BigDecimal value;

    public GetResponsibleUsersRequest() {
    }

    public GetResponsibleUsersRequest(
           java.lang.String companyCode,
           com.coda.www.efinance.schemas.transaction.DestAndAccount[] accounts,
           java.lang.String usage,
           java.lang.String[] users,
           java.lang.String currencyCode,
           java.math.BigDecimal value) {
           this.companyCode = companyCode;
           this.accounts = accounts;
           this.usage = usage;
           this.users = users;
           this.currencyCode = currencyCode;
           this.value = value;
    }


    /**
     * Gets the companyCode value for this GetResponsibleUsersRequest.
     * 
     * @return companyCode   * A company code.
     */
    public java.lang.String getCompanyCode() {
        return companyCode;
    }


    /**
     * Sets the companyCode value for this GetResponsibleUsersRequest.
     * 
     * @param companyCode   * A company code.
     */
    public void setCompanyCode(java.lang.String companyCode) {
        this.companyCode = companyCode;
    }


    /**
     * Gets the accounts value for this GetResponsibleUsersRequest.
     * 
     * @return accounts   * A list of destination and
     *                             account combinations
     */
    public com.coda.www.efinance.schemas.transaction.DestAndAccount[] getAccounts() {
        return accounts;
    }


    /**
     * Sets the accounts value for this GetResponsibleUsersRequest.
     * 
     * @param accounts   * A list of destination and
     *                             account combinations
     */
    public void setAccounts(com.coda.www.efinance.schemas.transaction.DestAndAccount[] accounts) {
        this.accounts = accounts;
    }


    /**
     * Gets the usage value for this GetResponsibleUsersRequest.
     * 
     * @return usage   * The scope of an element responsibilty.
     */
    public java.lang.String getUsage() {
        return usage;
    }


    /**
     * Sets the usage value for this GetResponsibleUsersRequest.
     * 
     * @param usage   * The scope of an element responsibilty.
     */
    public void setUsage(java.lang.String usage) {
        this.usage = usage;
    }


    /**
     * Gets the users value for this GetResponsibleUsersRequest.
     * 
     * @return users   * A list of user codes.
     */
    public java.lang.String[] getUsers() {
        return users;
    }


    /**
     * Sets the users value for this GetResponsibleUsersRequest.
     * 
     * @param users   * A list of user codes.
     */
    public void setUsers(java.lang.String[] users) {
        this.users = users;
    }


    /**
     * Gets the currencyCode value for this GetResponsibleUsersRequest.
     * 
     * @return currencyCode   * A currency code.
     */
    public java.lang.String getCurrencyCode() {
        return currencyCode;
    }


    /**
     * Sets the currencyCode value for this GetResponsibleUsersRequest.
     * 
     * @param currencyCode   * A currency code.
     */
    public void setCurrencyCode(java.lang.String currencyCode) {
        this.currencyCode = currencyCode;
    }


    /**
     * Gets the value value for this GetResponsibleUsersRequest.
     * 
     * @return value   * A monetary value.
     */
    public java.math.BigDecimal getValue() {
        return value;
    }


    /**
     * Sets the value value for this GetResponsibleUsersRequest.
     * 
     * @param value   * A monetary value.
     */
    public void setValue(java.math.BigDecimal value) {
        this.value = value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetResponsibleUsersRequest)) return false;
        GetResponsibleUsersRequest other = (GetResponsibleUsersRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.companyCode==null && other.getCompanyCode()==null) || 
             (this.companyCode!=null &&
              this.companyCode.equals(other.getCompanyCode()))) &&
            ((this.accounts==null && other.getAccounts()==null) || 
             (this.accounts!=null &&
              java.util.Arrays.equals(this.accounts, other.getAccounts()))) &&
            ((this.usage==null && other.getUsage()==null) || 
             (this.usage!=null &&
              this.usage.equals(other.getUsage()))) &&
            ((this.users==null && other.getUsers()==null) || 
             (this.users!=null &&
              java.util.Arrays.equals(this.users, other.getUsers()))) &&
            ((this.currencyCode==null && other.getCurrencyCode()==null) || 
             (this.currencyCode!=null &&
              this.currencyCode.equals(other.getCurrencyCode()))) &&
            ((this.value==null && other.getValue()==null) || 
             (this.value!=null &&
              this.value.equals(other.getValue())));
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
        if (getCompanyCode() != null) {
            _hashCode += getCompanyCode().hashCode();
        }
        if (getAccounts() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAccounts());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAccounts(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getUsage() != null) {
            _hashCode += getUsage().hashCode();
        }
        if (getUsers() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getUsers());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getUsers(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getCurrencyCode() != null) {
            _hashCode += getCurrencyCode().hashCode();
        }
        if (getValue() != null) {
            _hashCode += getValue().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetResponsibleUsersRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction/account-11.2/webservice", ">GetResponsibleUsersRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("companyCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction/account-11.2/webservice", "CompanyCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accounts");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction/account-11.2/webservice", "Accounts"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "DestAndAccount"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Account"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("usage");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction/account-11.2/webservice", "Usage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("users");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction/account-11.2/webservice", "Users"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeGeneralCode"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "UserCode"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("currencyCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction/account-11.2/webservice", "CurrencyCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("value");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction/account-11.2/webservice", "Value"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
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
