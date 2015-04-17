/**
 * AddTraderResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.elementmaster;

public class AddTraderResponse  extends com.coda.www.efinance.schemas.common.Response  implements java.io.Serializable {
    /* The code for the
     *                                 company in which you attempted to
     * create
     *                                 the trader. */
    private java.lang.String cmpCode;

    /* The code for the
     *                                 umbrella element within which you
     *                                 attempted to create the
     *                             trader. */
    private java.lang.String elmCode;

    /* The level of the
     *                                 umbrella element within which you
     *                                 attempted to create the
     *                             trader. */
    private short elmLevel;

    /* The code for the
     *                                 trader which you attempted to
     *                             create. */
    private java.lang.String code;

    /* The TimeStamp value
     *                                 for this trader in the
     *                             database. */
    private short timeStamp;

    public AddTraderResponse() {
    }

    public AddTraderResponse(
           com.coda.www.efinance.schemas.common.TypeResponseStatus status,
           java.lang.String transactioncoordinator,
           com.coda.www.efinance.schemas.common.Reason reason,
           java.lang.String cmpCode,
           java.lang.String elmCode,
           short elmLevel,
           java.lang.String code,
           short timeStamp) {
        super();
        this.cmpCode = cmpCode;
        this.elmCode = elmCode;
        this.elmLevel = elmLevel;
        this.code = code;
        this.timeStamp = timeStamp;
    }


    /**
     * Gets the cmpCode value for this AddTraderResponse.
     *
     * @return cmpCode   * The code for the
     *                                 company in which you attempted to
     * create
     *                                 the trader.
     */
    public java.lang.String getCmpCode() {
        return cmpCode;
    }


    /**
     * Sets the cmpCode value for this AddTraderResponse.
     *
     * @param cmpCode   * The code for the
     *                                 company in which you attempted to
     * create
     *                                 the trader.
     */
    public void setCmpCode(java.lang.String cmpCode) {
        this.cmpCode = cmpCode;
    }


    /**
     * Gets the elmCode value for this AddTraderResponse.
     *
     * @return elmCode   * The code for the
     *                                 umbrella element within which you
     *                                 attempted to create the
     *                             trader.
     */
    public java.lang.String getElmCode() {
        return elmCode;
    }


    /**
     * Sets the elmCode value for this AddTraderResponse.
     *
     * @param elmCode   * The code for the
     *                                 umbrella element within which you
     *                                 attempted to create the
     *                             trader.
     */
    public void setElmCode(java.lang.String elmCode) {
        this.elmCode = elmCode;
    }


    /**
     * Gets the elmLevel value for this AddTraderResponse.
     *
     * @return elmLevel   * The level of the
     *                                 umbrella element within which you
     *                                 attempted to create the
     *                             trader.
     */
    public short getElmLevel() {
        return elmLevel;
    }


    /**
     * Sets the elmLevel value for this AddTraderResponse.
     *
     * @param elmLevel   * The level of the
     *                                 umbrella element within which you
     *                                 attempted to create the
     *                             trader.
     */
    public void setElmLevel(short elmLevel) {
        this.elmLevel = elmLevel;
    }


    /**
     * Gets the code value for this AddTraderResponse.
     *
     * @return code   * The code for the
     *                                 trader which you attempted to
     *                             create.
     */
    public java.lang.String getCode() {
        return code;
    }


    /**
     * Sets the code value for this AddTraderResponse.
     *
     * @param code   * The code for the
     *                                 trader which you attempted to
     *                             create.
     */
    public void setCode(java.lang.String code) {
        this.code = code;
    }


    /**
     * Gets the timeStamp value for this AddTraderResponse.
     *
     * @return timeStamp   * The TimeStamp value
     *                                 for this trader in the
     *                             database.
     */
    public short getTimeStamp() {
        return timeStamp;
    }


    /**
     * Sets the timeStamp value for this AddTraderResponse.
     *
     * @param timeStamp   * The TimeStamp value
     *                                 for this trader in the
     *                             database.
     */
    public void setTimeStamp(short timeStamp) {
        this.timeStamp = timeStamp;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AddTraderResponse)) return false;
        AddTraderResponse other = (AddTraderResponse) obj;
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
            ((this.elmCode==null && other.getElmCode()==null) ||
             (this.elmCode!=null &&
              this.elmCode.equals(other.getElmCode()))) &&
            this.elmLevel == other.getElmLevel() &&
            ((this.code==null && other.getCode()==null) ||
             (this.code!=null &&
              this.code.equals(other.getCode()))) &&
            this.timeStamp == other.getTimeStamp();
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
        if (getElmCode() != null) {
            _hashCode += getElmCode().hashCode();
        }
        _hashCode += getElmLevel();
        if (getCode() != null) {
            _hashCode += getCode().hashCode();
        }
        _hashCode += getTimeStamp();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AddTraderResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "AddTraderResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cmpCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "CmpCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("elmCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "ElmCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timeStamp");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "TimeStamp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
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
