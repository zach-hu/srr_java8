/**
 * AllCmpTraderFilter.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.elementmaster;


/**
 * A filter for selecting all trader
 *                 codes in the specified company regardless of which
 * umbrella element the traders belong
 *             to.
 */
public class AllCmpTraderFilter  implements java.io.Serializable {
    private java.lang.String cmpCode;

    /* The
     *                         trader code to search for in the specified
     * company. This can be
     *                     wildcarded. */
    private java.lang.String code;

    /* The
     *                         maximum number of trader codes to be returned
     * from the database. */
    private java.math.BigInteger maxCodes;

    public AllCmpTraderFilter() {
    }

    public AllCmpTraderFilter(
           java.lang.String cmpCode,
           java.lang.String code,
           java.math.BigInteger maxCodes) {
           this.cmpCode = cmpCode;
           this.code = code;
           this.maxCodes = maxCodes;
    }


    /**
     * Gets the cmpCode value for this AllCmpTraderFilter.
     * 
     * @return cmpCode
     */
    public java.lang.String getCmpCode() {
        return cmpCode;
    }


    /**
     * Sets the cmpCode value for this AllCmpTraderFilter.
     * 
     * @param cmpCode
     */
    public void setCmpCode(java.lang.String cmpCode) {
        this.cmpCode = cmpCode;
    }


    /**
     * Gets the code value for this AllCmpTraderFilter.
     * 
     * @return code   * The
     *                         trader code to search for in the specified
     * company. This can be
     *                     wildcarded.
     */
    public java.lang.String getCode() {
        return code;
    }


    /**
     * Sets the code value for this AllCmpTraderFilter.
     * 
     * @param code   * The
     *                         trader code to search for in the specified
     * company. This can be
     *                     wildcarded.
     */
    public void setCode(java.lang.String code) {
        this.code = code;
    }


    /**
     * Gets the maxCodes value for this AllCmpTraderFilter.
     * 
     * @return maxCodes   * The
     *                         maximum number of trader codes to be returned
     * from the database.
     */
    public java.math.BigInteger getMaxCodes() {
        return maxCodes;
    }


    /**
     * Sets the maxCodes value for this AllCmpTraderFilter.
     * 
     * @param maxCodes   * The
     *                         maximum number of trader codes to be returned
     * from the database.
     */
    public void setMaxCodes(java.math.BigInteger maxCodes) {
        this.maxCodes = maxCodes;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AllCmpTraderFilter)) return false;
        AllCmpTraderFilter other = (AllCmpTraderFilter) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.cmpCode==null && other.getCmpCode()==null) || 
             (this.cmpCode!=null &&
              this.cmpCode.equals(other.getCmpCode()))) &&
            ((this.code==null && other.getCode()==null) || 
             (this.code!=null &&
              this.code.equals(other.getCode()))) &&
            ((this.maxCodes==null && other.getMaxCodes()==null) || 
             (this.maxCodes!=null &&
              this.maxCodes.equals(other.getMaxCodes())));
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
        if (getCmpCode() != null) {
            _hashCode += getCmpCode().hashCode();
        }
        if (getCode() != null) {
            _hashCode += getCode().hashCode();
        }
        if (getMaxCodes() != null) {
            _hashCode += getMaxCodes().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AllCmpTraderFilter.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "AllCmpTraderFilter"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cmpCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "CmpCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
        elemField.setFieldName("maxCodes");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "MaxCodes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
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
