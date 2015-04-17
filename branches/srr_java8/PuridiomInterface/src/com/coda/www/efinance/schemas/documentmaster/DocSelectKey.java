/**
 * DocSelectKey.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.documentmaster;


/**
 * This is a document key used when
 *                 requesting a list of documents. It incorporates a
 * value
 *                 for the maximum number of items to return, the
 *                 document's short name, and flags to specify whether
 * the search is restricted to InterCompany and/or
 *                 cancelling documents only.
 */
public class DocSelectKey  implements java.io.Serializable {
    private int maxKeys;

    /* A
     *                         document master key that incorporates the
     *                         company within which the document master
     *                     exists. */
    private com.coda.www.efinance.schemas.common.Key key;

    /* The short name of a document
     *                     master. */
    private java.lang.String shortName;

    /* When TRUE Specifies that only
     *                         documents set for InterCompany use will be
     * listed. */
    private java.lang.Boolean onlyIntercompany;

    /* Specifies that only
     *                         cancelling documents will be
     *                     listed. */
    private java.lang.Boolean onlyCancelling;

    /* When
     *                         TRUE, only document masters to which the current
     * user has read/write access are returned. A
     *                         user's access to document masters is
     *                         controlled by the master security lists on
     * the
     *                         user's capability
     *                     master. */
    private java.lang.Boolean enableMasterSecurityFilter;

    /* If
     *                         set, reserved documents are not
     *                     included. */
    private java.lang.Boolean excludeReservedDocs;

    /* If
     *                         set, includes only reserved Sales Invoicing
     * documents. */
    private java.lang.Boolean onlySalesInvoiceReservedDocs;

    public DocSelectKey() {
    }

    public DocSelectKey(
           int maxKeys,
           com.coda.www.efinance.schemas.common.Key key,
           java.lang.String shortName,
           java.lang.Boolean onlyIntercompany,
           java.lang.Boolean onlyCancelling,
           java.lang.Boolean enableMasterSecurityFilter,
           java.lang.Boolean excludeReservedDocs,
           java.lang.Boolean onlySalesInvoiceReservedDocs) {
           this.maxKeys = maxKeys;
           this.key = key;
           this.shortName = shortName;
           this.onlyIntercompany = onlyIntercompany;
           this.onlyCancelling = onlyCancelling;
           this.enableMasterSecurityFilter = enableMasterSecurityFilter;
           this.excludeReservedDocs = excludeReservedDocs;
           this.onlySalesInvoiceReservedDocs = onlySalesInvoiceReservedDocs;
    }


    /**
     * Gets the maxKeys value for this DocSelectKey.
     * 
     * @return maxKeys
     */
    public int getMaxKeys() {
        return maxKeys;
    }


    /**
     * Sets the maxKeys value for this DocSelectKey.
     * 
     * @param maxKeys
     */
    public void setMaxKeys(int maxKeys) {
        this.maxKeys = maxKeys;
    }


    /**
     * Gets the key value for this DocSelectKey.
     * 
     * @return key   * A
     *                         document master key that incorporates the
     *                         company within which the document master
     *                     exists.
     */
    public com.coda.www.efinance.schemas.common.Key getKey() {
        return key;
    }


    /**
     * Sets the key value for this DocSelectKey.
     * 
     * @param key   * A
     *                         document master key that incorporates the
     *                         company within which the document master
     *                     exists.
     */
    public void setKey(com.coda.www.efinance.schemas.common.Key key) {
        this.key = key;
    }


    /**
     * Gets the shortName value for this DocSelectKey.
     * 
     * @return shortName   * The short name of a document
     *                     master.
     */
    public java.lang.String getShortName() {
        return shortName;
    }


    /**
     * Sets the shortName value for this DocSelectKey.
     * 
     * @param shortName   * The short name of a document
     *                     master.
     */
    public void setShortName(java.lang.String shortName) {
        this.shortName = shortName;
    }


    /**
     * Gets the onlyIntercompany value for this DocSelectKey.
     * 
     * @return onlyIntercompany   * When TRUE Specifies that only
     *                         documents set for InterCompany use will be
     * listed.
     */
    public java.lang.Boolean getOnlyIntercompany() {
        return onlyIntercompany;
    }


    /**
     * Sets the onlyIntercompany value for this DocSelectKey.
     * 
     * @param onlyIntercompany   * When TRUE Specifies that only
     *                         documents set for InterCompany use will be
     * listed.
     */
    public void setOnlyIntercompany(java.lang.Boolean onlyIntercompany) {
        this.onlyIntercompany = onlyIntercompany;
    }


    /**
     * Gets the onlyCancelling value for this DocSelectKey.
     * 
     * @return onlyCancelling   * Specifies that only
     *                         cancelling documents will be
     *                     listed.
     */
    public java.lang.Boolean getOnlyCancelling() {
        return onlyCancelling;
    }


    /**
     * Sets the onlyCancelling value for this DocSelectKey.
     * 
     * @param onlyCancelling   * Specifies that only
     *                         cancelling documents will be
     *                     listed.
     */
    public void setOnlyCancelling(java.lang.Boolean onlyCancelling) {
        this.onlyCancelling = onlyCancelling;
    }


    /**
     * Gets the enableMasterSecurityFilter value for this DocSelectKey.
     * 
     * @return enableMasterSecurityFilter   * When
     *                         TRUE, only document masters to which the current
     * user has read/write access are returned. A
     *                         user's access to document masters is
     *                         controlled by the master security lists on
     * the
     *                         user's capability
     *                     master.
     */
    public java.lang.Boolean getEnableMasterSecurityFilter() {
        return enableMasterSecurityFilter;
    }


    /**
     * Sets the enableMasterSecurityFilter value for this DocSelectKey.
     * 
     * @param enableMasterSecurityFilter   * When
     *                         TRUE, only document masters to which the current
     * user has read/write access are returned. A
     *                         user's access to document masters is
     *                         controlled by the master security lists on
     * the
     *                         user's capability
     *                     master.
     */
    public void setEnableMasterSecurityFilter(java.lang.Boolean enableMasterSecurityFilter) {
        this.enableMasterSecurityFilter = enableMasterSecurityFilter;
    }


    /**
     * Gets the excludeReservedDocs value for this DocSelectKey.
     * 
     * @return excludeReservedDocs   * If
     *                         set, reserved documents are not
     *                     included.
     */
    public java.lang.Boolean getExcludeReservedDocs() {
        return excludeReservedDocs;
    }


    /**
     * Sets the excludeReservedDocs value for this DocSelectKey.
     * 
     * @param excludeReservedDocs   * If
     *                         set, reserved documents are not
     *                     included.
     */
    public void setExcludeReservedDocs(java.lang.Boolean excludeReservedDocs) {
        this.excludeReservedDocs = excludeReservedDocs;
    }


    /**
     * Gets the onlySalesInvoiceReservedDocs value for this DocSelectKey.
     * 
     * @return onlySalesInvoiceReservedDocs   * If
     *                         set, includes only reserved Sales Invoicing
     * documents.
     */
    public java.lang.Boolean getOnlySalesInvoiceReservedDocs() {
        return onlySalesInvoiceReservedDocs;
    }


    /**
     * Sets the onlySalesInvoiceReservedDocs value for this DocSelectKey.
     * 
     * @param onlySalesInvoiceReservedDocs   * If
     *                         set, includes only reserved Sales Invoicing
     * documents.
     */
    public void setOnlySalesInvoiceReservedDocs(java.lang.Boolean onlySalesInvoiceReservedDocs) {
        this.onlySalesInvoiceReservedDocs = onlySalesInvoiceReservedDocs;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DocSelectKey)) return false;
        DocSelectKey other = (DocSelectKey) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.maxKeys == other.getMaxKeys() &&
            ((this.key==null && other.getKey()==null) || 
             (this.key!=null &&
              this.key.equals(other.getKey()))) &&
            ((this.shortName==null && other.getShortName()==null) || 
             (this.shortName!=null &&
              this.shortName.equals(other.getShortName()))) &&
            ((this.onlyIntercompany==null && other.getOnlyIntercompany()==null) || 
             (this.onlyIntercompany!=null &&
              this.onlyIntercompany.equals(other.getOnlyIntercompany()))) &&
            ((this.onlyCancelling==null && other.getOnlyCancelling()==null) || 
             (this.onlyCancelling!=null &&
              this.onlyCancelling.equals(other.getOnlyCancelling()))) &&
            ((this.enableMasterSecurityFilter==null && other.getEnableMasterSecurityFilter()==null) || 
             (this.enableMasterSecurityFilter!=null &&
              this.enableMasterSecurityFilter.equals(other.getEnableMasterSecurityFilter()))) &&
            ((this.excludeReservedDocs==null && other.getExcludeReservedDocs()==null) || 
             (this.excludeReservedDocs!=null &&
              this.excludeReservedDocs.equals(other.getExcludeReservedDocs()))) &&
            ((this.onlySalesInvoiceReservedDocs==null && other.getOnlySalesInvoiceReservedDocs()==null) || 
             (this.onlySalesInvoiceReservedDocs!=null &&
              this.onlySalesInvoiceReservedDocs.equals(other.getOnlySalesInvoiceReservedDocs())));
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
        _hashCode += getMaxKeys();
        if (getKey() != null) {
            _hashCode += getKey().hashCode();
        }
        if (getShortName() != null) {
            _hashCode += getShortName().hashCode();
        }
        if (getOnlyIntercompany() != null) {
            _hashCode += getOnlyIntercompany().hashCode();
        }
        if (getOnlyCancelling() != null) {
            _hashCode += getOnlyCancelling().hashCode();
        }
        if (getEnableMasterSecurityFilter() != null) {
            _hashCode += getEnableMasterSecurityFilter().hashCode();
        }
        if (getExcludeReservedDocs() != null) {
            _hashCode += getExcludeReservedDocs().hashCode();
        }
        if (getOnlySalesInvoiceReservedDocs() != null) {
            _hashCode += getOnlySalesInvoiceReservedDocs().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DocSelectKey.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "docSelectKey"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("maxKeys");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "MaxKeys"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("key");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "Key"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "Key"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shortName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "ShortName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("onlyIntercompany");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "OnlyIntercompany"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("onlyCancelling");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "OnlyCancelling"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("enableMasterSecurityFilter");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "EnableMasterSecurityFilter"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("excludeReservedDocs");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "ExcludeReservedDocs"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("onlySalesInvoiceReservedDocs");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "OnlySalesInvoiceReservedDocs"));
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
