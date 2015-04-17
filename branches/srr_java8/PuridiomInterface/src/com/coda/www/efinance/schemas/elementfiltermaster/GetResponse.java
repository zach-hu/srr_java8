/**
 * GetResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.elementfiltermaster;

public class GetResponse  extends com.coda.www.efinance.schemas.common.Response  implements java.io.Serializable {
    /* The code for the
     *                                 company from which you attempted to
     * retrieve the specified element filter
     *                             master. */
    private java.lang.String cmpCode;

    /* The element level to
     *                                 which the element filter master
     *                             applies. */
    private short level;

    /* The code for the
     *                                 element master that you attempted
     * to
     *                             retrieve. */
    private java.lang.String code;

    /* Contains the element
     *                                 filter master you have retrieved from
     * the database. */
    private com.coda.www.efinance.schemas.elementfiltermaster.ElementFilterMaster elementFilterMaster;

    public GetResponse() {
    }

    public GetResponse(
           com.coda.www.efinance.schemas.common.TypeResponseStatus status,
           java.lang.String transactioncoordinator,
           com.coda.www.efinance.schemas.common.Reason reason,
           java.lang.String cmpCode,
           short level,
           java.lang.String code,
           com.coda.www.efinance.schemas.elementfiltermaster.ElementFilterMaster elementFilterMaster) {
        super();
        this.cmpCode = cmpCode;
        this.level = level;
        this.code = code;
        this.elementFilterMaster = elementFilterMaster;
    }


    /**
     * Gets the cmpCode value for this GetResponse.
     *
     * @return cmpCode   * The code for the
     *                                 company from which you attempted to
     * retrieve the specified element filter
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
     * retrieve the specified element filter
     *                             master.
     */
    public void setCmpCode(java.lang.String cmpCode) {
        this.cmpCode = cmpCode;
    }


    /**
     * Gets the level value for this GetResponse.
     *
     * @return level   * The element level to
     *                                 which the element filter master
     *                             applies.
     */
    public short getLevel() {
        return level;
    }


    /**
     * Sets the level value for this GetResponse.
     *
     * @param level   * The element level to
     *                                 which the element filter master
     *                             applies.
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
     * Gets the elementFilterMaster value for this GetResponse.
     *
     * @return elementFilterMaster   * Contains the element
     *                                 filter master you have retrieved from
     * the database.
     */
    public com.coda.www.efinance.schemas.elementfiltermaster.ElementFilterMaster getElementFilterMaster() {
        return elementFilterMaster;
    }


    /**
     * Sets the elementFilterMaster value for this GetResponse.
     *
     * @param elementFilterMaster   * Contains the element
     *                                 filter master you have retrieved from
     * the database.
     */
    public void setElementFilterMaster(com.coda.www.efinance.schemas.elementfiltermaster.ElementFilterMaster elementFilterMaster) {
        this.elementFilterMaster = elementFilterMaster;
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
            ((this.elementFilterMaster==null && other.getElementFilterMaster()==null) ||
             (this.elementFilterMaster!=null &&
              this.elementFilterMaster.equals(other.getElementFilterMaster())));
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
        if (getElementFilterMaster() != null) {
            _hashCode += getElementFilterMaster().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementfiltermaster", "GetResponse"));
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
        elemField.setFieldName("elementFilterMaster");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementfiltermaster", "ElementFilterMaster"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementfiltermaster", "ElementFilterMaster"));
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
