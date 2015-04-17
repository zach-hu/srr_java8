/**
 * GetRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice;

public class GetRequest  implements java.io.Serializable {
    private com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.GetRequestGetOptions getOptions;

    /* Contains the key identifying
     *                             the element master you want to retrieve
     * from
     *                             the database. */
    private com.coda.www.efinance.schemas.elementmaster.ElmFullKey key;

    /* Indicates that the element
     *                             code specified in the Key for this Request,
     * may (or may not) be a mnemonic. */
    private java.lang.Boolean useMnemonic;

    /* Indicates whether details of
     *                             the last transaction posted to the specified
     * element(s) are retrieved. */
    private java.lang.Boolean includeLastTransaction;

    /* If set, checks for postings
     *                             to the specified element masters. */
    private java.lang.Boolean checkForPostings;

    public GetRequest() {
    }

    public GetRequest(
           com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.GetRequestGetOptions getOptions,
           com.coda.www.efinance.schemas.elementmaster.ElmFullKey key,
           java.lang.Boolean useMnemonic,
           java.lang.Boolean includeLastTransaction,
           java.lang.Boolean checkForPostings) {
           this.getOptions = getOptions;
           this.key = key;
           this.useMnemonic = useMnemonic;
           this.includeLastTransaction = includeLastTransaction;
           this.checkForPostings = checkForPostings;
    }


    /**
     * Gets the getOptions value for this GetRequest.
     * 
     * @return getOptions
     */
    public com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.GetRequestGetOptions getGetOptions() {
        return getOptions;
    }


    /**
     * Sets the getOptions value for this GetRequest.
     * 
     * @param getOptions
     */
    public void setGetOptions(com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.GetRequestGetOptions getOptions) {
        this.getOptions = getOptions;
    }


    /**
     * Gets the key value for this GetRequest.
     * 
     * @return key   * Contains the key identifying
     *                             the element master you want to retrieve
     * from
     *                             the database.
     */
    public com.coda.www.efinance.schemas.elementmaster.ElmFullKey getKey() {
        return key;
    }


    /**
     * Sets the key value for this GetRequest.
     * 
     * @param key   * Contains the key identifying
     *                             the element master you want to retrieve
     * from
     *                             the database.
     */
    public void setKey(com.coda.www.efinance.schemas.elementmaster.ElmFullKey key) {
        this.key = key;
    }


    /**
     * Gets the useMnemonic value for this GetRequest.
     * 
     * @return useMnemonic   * Indicates that the element
     *                             code specified in the Key for this Request,
     * may (or may not) be a mnemonic.
     */
    public java.lang.Boolean getUseMnemonic() {
        return useMnemonic;
    }


    /**
     * Sets the useMnemonic value for this GetRequest.
     * 
     * @param useMnemonic   * Indicates that the element
     *                             code specified in the Key for this Request,
     * may (or may not) be a mnemonic.
     */
    public void setUseMnemonic(java.lang.Boolean useMnemonic) {
        this.useMnemonic = useMnemonic;
    }


    /**
     * Gets the includeLastTransaction value for this GetRequest.
     * 
     * @return includeLastTransaction   * Indicates whether details of
     *                             the last transaction posted to the specified
     * element(s) are retrieved.
     */
    public java.lang.Boolean getIncludeLastTransaction() {
        return includeLastTransaction;
    }


    /**
     * Sets the includeLastTransaction value for this GetRequest.
     * 
     * @param includeLastTransaction   * Indicates whether details of
     *                             the last transaction posted to the specified
     * element(s) are retrieved.
     */
    public void setIncludeLastTransaction(java.lang.Boolean includeLastTransaction) {
        this.includeLastTransaction = includeLastTransaction;
    }


    /**
     * Gets the checkForPostings value for this GetRequest.
     * 
     * @return checkForPostings   * If set, checks for postings
     *                             to the specified element masters.
     */
    public java.lang.Boolean getCheckForPostings() {
        return checkForPostings;
    }


    /**
     * Sets the checkForPostings value for this GetRequest.
     * 
     * @param checkForPostings   * If set, checks for postings
     *                             to the specified element masters.
     */
    public void setCheckForPostings(java.lang.Boolean checkForPostings) {
        this.checkForPostings = checkForPostings;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetRequest)) return false;
        GetRequest other = (GetRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.getOptions==null && other.getGetOptions()==null) || 
             (this.getOptions!=null &&
              this.getOptions.equals(other.getGetOptions()))) &&
            ((this.key==null && other.getKey()==null) || 
             (this.key!=null &&
              this.key.equals(other.getKey()))) &&
            ((this.useMnemonic==null && other.getUseMnemonic()==null) || 
             (this.useMnemonic!=null &&
              this.useMnemonic.equals(other.getUseMnemonic()))) &&
            ((this.includeLastTransaction==null && other.getIncludeLastTransaction()==null) || 
             (this.includeLastTransaction!=null &&
              this.includeLastTransaction.equals(other.getIncludeLastTransaction()))) &&
            ((this.checkForPostings==null && other.getCheckForPostings()==null) || 
             (this.checkForPostings!=null &&
              this.checkForPostings.equals(other.getCheckForPostings())));
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
        if (getGetOptions() != null) {
            _hashCode += getGetOptions().hashCode();
        }
        if (getKey() != null) {
            _hashCode += getKey().hashCode();
        }
        if (getUseMnemonic() != null) {
            _hashCode += getUseMnemonic().hashCode();
        }
        if (getIncludeLastTransaction() != null) {
            _hashCode += getIncludeLastTransaction().hashCode();
        }
        if (getCheckForPostings() != null) {
            _hashCode += getCheckForPostings().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", ">GetRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getOptions");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", "GetOptions"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", ">>GetRequest>GetOptions"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("key");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", "Key"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "elmFullKey"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("useMnemonic");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", "UseMnemonic"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("includeLastTransaction");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", "IncludeLastTransaction"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("checkForPostings");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", "CheckForPostings"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
