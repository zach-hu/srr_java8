/**
 * TestResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.elementfiltermaster;

public class TestResponse  extends com.coda.www.efinance.schemas.common.Response  implements java.io.Serializable {
    /* The code for the
     *                                 company in which the element filter
     * master is
     *                             maintained. */
    private java.lang.String cmpCode;

    /* The element level to
     *                                 which the element filter master
     *                             applies. */
    private short level;

    /* The code for the
     *                                 element filter master that you have
     * attempted to test. */
    private java.lang.String code;

    /* The maximum number of
     *                                 elements to be
     *                             returned. */
    private java.lang.Integer maxKeys;

    /* Contains extended key
     *                                 information for the listed
     *                             element. */
    private com.coda.www.efinance.schemas.elementmaster.ElmKeyDataElement[] keys;

    public TestResponse() {
    }

    public TestResponse(
           com.coda.www.efinance.schemas.common.TypeResponseStatus status,
           java.lang.String transactioncoordinator,
           com.coda.www.efinance.schemas.common.Reason reason,
           java.lang.String cmpCode,
           short level,
           java.lang.String code,
           java.lang.Integer maxKeys,
           com.coda.www.efinance.schemas.elementmaster.ElmKeyDataElement[] keys) {
        super();
        this.cmpCode = cmpCode;
        this.level = level;
        this.code = code;
        this.maxKeys = maxKeys;
        this.keys = keys;
    }


    /**
     * Gets the cmpCode value for this TestResponse.
     *
     * @return cmpCode   * The code for the
     *                                 company in which the element filter
     * master is
     *                             maintained.
     */
    public java.lang.String getCmpCode() {
        return cmpCode;
    }


    /**
     * Sets the cmpCode value for this TestResponse.
     *
     * @param cmpCode   * The code for the
     *                                 company in which the element filter
     * master is
     *                             maintained.
     */
    public void setCmpCode(java.lang.String cmpCode) {
        this.cmpCode = cmpCode;
    }


    /**
     * Gets the level value for this TestResponse.
     *
     * @return level   * The element level to
     *                                 which the element filter master
     *                             applies.
     */
    public short getLevel() {
        return level;
    }


    /**
     * Sets the level value for this TestResponse.
     *
     * @param level   * The element level to
     *                                 which the element filter master
     *                             applies.
     */
    public void setLevel(short level) {
        this.level = level;
    }


    /**
     * Gets the code value for this TestResponse.
     *
     * @return code   * The code for the
     *                                 element filter master that you have
     * attempted to test.
     */
    public java.lang.String getCode() {
        return code;
    }


    /**
     * Sets the code value for this TestResponse.
     *
     * @param code   * The code for the
     *                                 element filter master that you have
     * attempted to test.
     */
    public void setCode(java.lang.String code) {
        this.code = code;
    }


    /**
     * Gets the maxKeys value for this TestResponse.
     *
     * @return maxKeys   * The maximum number of
     *                                 elements to be
     *                             returned.
     */
    public java.lang.Integer getMaxKeys() {
        return maxKeys;
    }


    /**
     * Sets the maxKeys value for this TestResponse.
     *
     * @param maxKeys   * The maximum number of
     *                                 elements to be
     *                             returned.
     */
    public void setMaxKeys(java.lang.Integer maxKeys) {
        this.maxKeys = maxKeys;
    }


    /**
     * Gets the keys value for this TestResponse.
     *
     * @return keys   * Contains extended key
     *                                 information for the listed
     *                             element.
     */
    public com.coda.www.efinance.schemas.elementmaster.ElmKeyDataElement[] getKeys() {
        return keys;
    }


    /**
     * Sets the keys value for this TestResponse.
     *
     * @param keys   * Contains extended key
     *                                 information for the listed
     *                             element.
     */
    public void setKeys(com.coda.www.efinance.schemas.elementmaster.ElmKeyDataElement[] keys) {
        this.keys = keys;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TestResponse)) return false;
        TestResponse other = (TestResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) &&
            ((this.cmpCode==null && other.getCmpCode()==null) ||
             (this.cmpCode!=null &&
              this.cmpCode.equals(other.getCmpCode()))) &&
            this.level == other.getLevel() &&
            ((this.code==null && other.getCode()==null) ||
             (this.code!=null &&
              this.code.equals(other.getCode()))) &&
            ((this.maxKeys==null && other.getMaxKeys()==null) ||
             (this.maxKeys!=null &&
              this.maxKeys.equals(other.getMaxKeys()))) &&
            ((this.keys==null && other.getKeys()==null) ||
             (this.keys!=null &&
              java.util.Arrays.equals(this.keys, other.getKeys())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        if (getCmpCode() != null) {
            _hashCode += getCmpCode().hashCode();
        }
        _hashCode += getLevel();
        if (getCode() != null) {
            _hashCode += getCode().hashCode();
        }
        if (getMaxKeys() != null) {
            _hashCode += getMaxKeys().hashCode();
        }
        if (getKeys() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getKeys());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getKeys(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TestResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementfiltermaster", "TestResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cmpCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementfiltermaster", "CmpCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("level");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementfiltermaster", "Level"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("code");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementfiltermaster", "Code"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("maxKeys");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementfiltermaster", "MaxKeys"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("keys");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementfiltermaster", "Keys"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "elmKeyDataElement"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Key"));
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
