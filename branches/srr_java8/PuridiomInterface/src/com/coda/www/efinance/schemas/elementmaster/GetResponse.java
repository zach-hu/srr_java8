/**
 * GetResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.elementmaster;

public class GetResponse  extends com.coda.www.efinance.schemas.common.Response  implements java.io.Serializable {
    /* The code for the
     *                                 company from which you attempted to
     * retrieve the specified element
     *                             master. */
    private java.lang.String cmpCode;

    /* The level of the
     *                                 element master that you attempted
     * to
     *                             retrieve. */
    private short level;

    /* The code for the
     *                                 element master that you attempted
     * to
     *                             retrieve. */
    private java.lang.String code;

    /* Contains the element
     *                                 master you have retrieved from the
     * database. */
    private com.coda.www.efinance.schemas.elementmaster.Element element;

    public GetResponse() {
    }

    public GetResponse(
           com.coda.www.efinance.schemas.common.TypeResponseStatus status,
           java.lang.String transactioncoordinator,
           com.coda.www.efinance.schemas.common.Reason reason,
           java.lang.String cmpCode,
           short level,
           java.lang.String code,
           com.coda.www.efinance.schemas.elementmaster.Element element) {
        super();
        this.cmpCode = cmpCode;
        this.level = level;
        this.code = code;
        this.element = element;
    }


    /**
     * Gets the cmpCode value for this GetResponse.
     *
     * @return cmpCode   * The code for the
     *                                 company from which you attempted to
     * retrieve the specified element
     *                             master.
     */
    public java.lang.String getCmpCode() {
        return cmpCode;
    }


    /**
     * Sets the cmpCode value for this GetResponse.
     *
     * @param cmpCode   * The code for the
     *                                 company from which you attempted to
     * retrieve the specified element
     *                             master.
     */
    public void setCmpCode(java.lang.String cmpCode) {
        this.cmpCode = cmpCode;
    }


    /**
     * Gets the level value for this GetResponse.
     *
     * @return level   * The level of the
     *                                 element master that you attempted
     * to
     *                             retrieve.
     */
    public short getLevel() {
        return level;
    }


    /**
     * Sets the level value for this GetResponse.
     *
     * @param level   * The level of the
     *                                 element master that you attempted
     * to
     *                             retrieve.
     */
    public void setLevel(short level) {
        this.level = level;
    }


    /**
     * Gets the code value for this GetResponse.
     *
     * @return code   * The code for the
     *                                 element master that you attempted
     * to
     *                             retrieve.
     */
    public java.lang.String getCode() {
        return code;
    }


    /**
     * Sets the code value for this GetResponse.
     *
     * @param code   * The code for the
     *                                 element master that you attempted
     * to
     *                             retrieve.
     */
    public void setCode(java.lang.String code) {
        this.code = code;
    }


    /**
     * Gets the element value for this GetResponse.
     *
     * @return element   * Contains the element
     *                                 master you have retrieved from the
     * database.
     */
    public com.coda.www.efinance.schemas.elementmaster.Element getElement() {
        return element;
    }


    /**
     * Sets the element value for this GetResponse.
     *
     * @param element   * Contains the element
     *                                 master you have retrieved from the
     * database.
     */
    public void setElement(com.coda.www.efinance.schemas.elementmaster.Element element) {
        this.element = element;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetResponse)) return false;
        GetResponse other = (GetResponse) obj;
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
            ((this.element==null && other.getElement()==null) ||
             (this.element!=null &&
              this.element.equals(other.getElement())));
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
        if (getElement() != null) {
            _hashCode += getElement().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "GetResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cmpCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "CmpCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("level");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Level"));
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
        elemField.setFieldName("element");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Element"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Element"));
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
