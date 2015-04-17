/**
 * TraderSelectFilter.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.elementmaster;


/**
 * A filter for selecting
 *             traders.
 */
public class TraderSelectFilter  implements java.io.Serializable {
    private java.util.Calendar lastUsed;

    /* Filter
     *                         for traders that were created on or before
     * this
     *                     date. */
    private java.util.Calendar created;

    /* Filter
     *                         for traders in this company. This can be
     *                     wildcarded. */
    private java.lang.String cmpCode;

    /* Filter for traders in this
     *                         umbrella element. This can be
     *                     wildcarded. */
    private java.lang.String elmCode;

    /* Filter
     *                         for traders in umbrella elements at this element
     * level. */
    private short elmLevel;

    /* Filter
     *                         for a trader with this trader code. This can
     * be
     *                     wildcarded. */
    private java.lang.String code;

    /* The maximum number of traders
     *                         to be returned from the
     *                     database. */
    private java.lang.Integer maxRows;

    public TraderSelectFilter() {
    }

    public TraderSelectFilter(
           java.util.Calendar lastUsed,
           java.util.Calendar created,
           java.lang.String cmpCode,
           java.lang.String elmCode,
           short elmLevel,
           java.lang.String code,
           java.lang.Integer maxRows) {
           this.lastUsed = lastUsed;
           this.created = created;
           this.cmpCode = cmpCode;
           this.elmCode = elmCode;
           this.elmLevel = elmLevel;
           this.code = code;
           this.maxRows = maxRows;
    }


    /**
     * Gets the lastUsed value for this TraderSelectFilter.
     * 
     * @return lastUsed
     */
    public java.util.Calendar getLastUsed() {
        return lastUsed;
    }


    /**
     * Sets the lastUsed value for this TraderSelectFilter.
     * 
     * @param lastUsed
     */
    public void setLastUsed(java.util.Calendar lastUsed) {
        this.lastUsed = lastUsed;
    }


    /**
     * Gets the created value for this TraderSelectFilter.
     * 
     * @return created   * Filter
     *                         for traders that were created on or before
     * this
     *                     date.
     */
    public java.util.Calendar getCreated() {
        return created;
    }


    /**
     * Sets the created value for this TraderSelectFilter.
     * 
     * @param created   * Filter
     *                         for traders that were created on or before
     * this
     *                     date.
     */
    public void setCreated(java.util.Calendar created) {
        this.created = created;
    }


    /**
     * Gets the cmpCode value for this TraderSelectFilter.
     * 
     * @return cmpCode   * Filter
     *                         for traders in this company. This can be
     *                     wildcarded.
     */
    public java.lang.String getCmpCode() {
        return cmpCode;
    }


    /**
     * Sets the cmpCode value for this TraderSelectFilter.
     * 
     * @param cmpCode   * Filter
     *                         for traders in this company. This can be
     *                     wildcarded.
     */
    public void setCmpCode(java.lang.String cmpCode) {
        this.cmpCode = cmpCode;
    }


    /**
     * Gets the elmCode value for this TraderSelectFilter.
     * 
     * @return elmCode   * Filter for traders in this
     *                         umbrella element. This can be
     *                     wildcarded.
     */
    public java.lang.String getElmCode() {
        return elmCode;
    }


    /**
     * Sets the elmCode value for this TraderSelectFilter.
     * 
     * @param elmCode   * Filter for traders in this
     *                         umbrella element. This can be
     *                     wildcarded.
     */
    public void setElmCode(java.lang.String elmCode) {
        this.elmCode = elmCode;
    }


    /**
     * Gets the elmLevel value for this TraderSelectFilter.
     * 
     * @return elmLevel   * Filter
     *                         for traders in umbrella elements at this element
     * level.
     */
    public short getElmLevel() {
        return elmLevel;
    }


    /**
     * Sets the elmLevel value for this TraderSelectFilter.
     * 
     * @param elmLevel   * Filter
     *                         for traders in umbrella elements at this element
     * level.
     */
    public void setElmLevel(short elmLevel) {
        this.elmLevel = elmLevel;
    }


    /**
     * Gets the code value for this TraderSelectFilter.
     * 
     * @return code   * Filter
     *                         for a trader with this trader code. This can
     * be
     *                     wildcarded.
     */
    public java.lang.String getCode() {
        return code;
    }


    /**
     * Sets the code value for this TraderSelectFilter.
     * 
     * @param code   * Filter
     *                         for a trader with this trader code. This can
     * be
     *                     wildcarded.
     */
    public void setCode(java.lang.String code) {
        this.code = code;
    }


    /**
     * Gets the maxRows value for this TraderSelectFilter.
     * 
     * @return maxRows   * The maximum number of traders
     *                         to be returned from the
     *                     database.
     */
    public java.lang.Integer getMaxRows() {
        return maxRows;
    }


    /**
     * Sets the maxRows value for this TraderSelectFilter.
     * 
     * @param maxRows   * The maximum number of traders
     *                         to be returned from the
     *                     database.
     */
    public void setMaxRows(java.lang.Integer maxRows) {
        this.maxRows = maxRows;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TraderSelectFilter)) return false;
        TraderSelectFilter other = (TraderSelectFilter) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.lastUsed==null && other.getLastUsed()==null) || 
             (this.lastUsed!=null &&
              this.lastUsed.equals(other.getLastUsed()))) &&
            ((this.created==null && other.getCreated()==null) || 
             (this.created!=null &&
              this.created.equals(other.getCreated()))) &&
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
            ((this.maxRows==null && other.getMaxRows()==null) || 
             (this.maxRows!=null &&
              this.maxRows.equals(other.getMaxRows())));
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
        if (getLastUsed() != null) {
            _hashCode += getLastUsed().hashCode();
        }
        if (getCreated() != null) {
            _hashCode += getCreated().hashCode();
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
        if (getMaxRows() != null) {
            _hashCode += getMaxRows().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TraderSelectFilter.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "TraderSelectFilter"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastUsed");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "LastUsed"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("created");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Created"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cmpCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "CmpCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("elmCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "ElmCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
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
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("maxRows");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "MaxRows"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
