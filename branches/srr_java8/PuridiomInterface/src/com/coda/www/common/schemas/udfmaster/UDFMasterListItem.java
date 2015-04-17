/**
 * UDFMasterListItem.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.common.schemas.udfmaster;


/**
 * This element contains details of a
 *                 UDF master list item.
 */
public class UDFMasterListItem  implements java.io.Serializable {
    private java.lang.String code;

    /* The
     *                         short name of the UDF
     *                     master. */
    private java.lang.String shortName;

    /* The field
     *                     type. */
    private java.lang.String fieldType;

    /* The
     *                         date the record was added. */
    private java.util.Calendar addDate;

    /* The date the record was
     *                     modified. */
    private java.util.Calendar modDate;

    /* The code of the user who
     *                         added or modified the
     *                     record. */
    private java.lang.String usrName;

    public UDFMasterListItem() {
    }

    public UDFMasterListItem(
           java.lang.String code,
           java.lang.String shortName,
           java.lang.String fieldType,
           java.util.Calendar addDate,
           java.util.Calendar modDate,
           java.lang.String usrName) {
           this.code = code;
           this.shortName = shortName;
           this.fieldType = fieldType;
           this.addDate = addDate;
           this.modDate = modDate;
           this.usrName = usrName;
    }


    /**
     * Gets the code value for this UDFMasterListItem.
     * 
     * @return code
     */
    public java.lang.String getCode() {
        return code;
    }


    /**
     * Sets the code value for this UDFMasterListItem.
     * 
     * @param code
     */
    public void setCode(java.lang.String code) {
        this.code = code;
    }


    /**
     * Gets the shortName value for this UDFMasterListItem.
     * 
     * @return shortName   * The
     *                         short name of the UDF
     *                     master.
     */
    public java.lang.String getShortName() {
        return shortName;
    }


    /**
     * Sets the shortName value for this UDFMasterListItem.
     * 
     * @param shortName   * The
     *                         short name of the UDF
     *                     master.
     */
    public void setShortName(java.lang.String shortName) {
        this.shortName = shortName;
    }


    /**
     * Gets the fieldType value for this UDFMasterListItem.
     * 
     * @return fieldType   * The field
     *                     type.
     */
    public java.lang.String getFieldType() {
        return fieldType;
    }


    /**
     * Sets the fieldType value for this UDFMasterListItem.
     * 
     * @param fieldType   * The field
     *                     type.
     */
    public void setFieldType(java.lang.String fieldType) {
        this.fieldType = fieldType;
    }


    /**
     * Gets the addDate value for this UDFMasterListItem.
     * 
     * @return addDate   * The
     *                         date the record was added.
     */
    public java.util.Calendar getAddDate() {
        return addDate;
    }


    /**
     * Sets the addDate value for this UDFMasterListItem.
     * 
     * @param addDate   * The
     *                         date the record was added.
     */
    public void setAddDate(java.util.Calendar addDate) {
        this.addDate = addDate;
    }


    /**
     * Gets the modDate value for this UDFMasterListItem.
     * 
     * @return modDate   * The date the record was
     *                     modified.
     */
    public java.util.Calendar getModDate() {
        return modDate;
    }


    /**
     * Sets the modDate value for this UDFMasterListItem.
     * 
     * @param modDate   * The date the record was
     *                     modified.
     */
    public void setModDate(java.util.Calendar modDate) {
        this.modDate = modDate;
    }


    /**
     * Gets the usrName value for this UDFMasterListItem.
     * 
     * @return usrName   * The code of the user who
     *                         added or modified the
     *                     record.
     */
    public java.lang.String getUsrName() {
        return usrName;
    }


    /**
     * Sets the usrName value for this UDFMasterListItem.
     * 
     * @param usrName   * The code of the user who
     *                         added or modified the
     *                     record.
     */
    public void setUsrName(java.lang.String usrName) {
        this.usrName = usrName;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UDFMasterListItem)) return false;
        UDFMasterListItem other = (UDFMasterListItem) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.code==null && other.getCode()==null) || 
             (this.code!=null &&
              this.code.equals(other.getCode()))) &&
            ((this.shortName==null && other.getShortName()==null) || 
             (this.shortName!=null &&
              this.shortName.equals(other.getShortName()))) &&
            ((this.fieldType==null && other.getFieldType()==null) || 
             (this.fieldType!=null &&
              this.fieldType.equals(other.getFieldType()))) &&
            ((this.addDate==null && other.getAddDate()==null) || 
             (this.addDate!=null &&
              this.addDate.equals(other.getAddDate()))) &&
            ((this.modDate==null && other.getModDate()==null) || 
             (this.modDate!=null &&
              this.modDate.equals(other.getModDate()))) &&
            ((this.usrName==null && other.getUsrName()==null) || 
             (this.usrName!=null &&
              this.usrName.equals(other.getUsrName())));
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
        if (getCode() != null) {
            _hashCode += getCode().hashCode();
        }
        if (getShortName() != null) {
            _hashCode += getShortName().hashCode();
        }
        if (getFieldType() != null) {
            _hashCode += getFieldType().hashCode();
        }
        if (getAddDate() != null) {
            _hashCode += getAddDate().hashCode();
        }
        if (getModDate() != null) {
            _hashCode += getModDate().hashCode();
        }
        if (getUsrName() != null) {
            _hashCode += getUsrName().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UDFMasterListItem.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/common/schemas/udfmaster", "UDFMasterListItem"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("code");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/common/schemas/udfmaster", "Code"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shortName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/common/schemas/udfmaster", "ShortName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fieldType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/common/schemas/udfmaster", "FieldType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("addDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/common/schemas/udfmaster", "AddDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("modDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/common/schemas/udfmaster", "ModDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("usrName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/common/schemas/udfmaster", "UsrName"));
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
