/**
 * UDFMaster.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.common.schemas.udfmaster;


/**
 * This element contains details of a
 *                 user-defined field (UDF) master.
 */
public class UDFMaster  implements java.io.Serializable {
    private java.lang.String code;

    /* The name of the UDF
     *                     master. */
    private java.lang.String name;

    /* The
     *                         short name of the UDF
     *                     master. */
    private java.lang.String shortName;

    /* The
     *                         field type. */
    private java.lang.String fieldType;

    /* The
     *                         code of an element filter
     *                     master. */
    private java.lang.String elementFilter;

    /* The
     *                         element level from which element codes are
     * selected. */
    private java.lang.Short elementLevel;

    /* If
     *                         set, specifies that possible values for the
     * field are predefined. */
    private java.lang.Boolean definedValues;

    /* If
     *                         set, specifies that the user is restricted
     * to
     *                         choosing a value from the list of defined
     *                     values. */
    private java.lang.Boolean restrictToList;

    /* If
     *                         set, a value must be specified for the
     *                     field. */
    private java.lang.Boolean mandatory;

    /* The
     *                         code of a user extension
     *                     master. */
    private java.lang.String userExtension;

    /* Specifies how the elements
     *                         available for selection are
     *                     presented. */
    private java.lang.String listingMethod;

    /* This element contains a list
     *                         of UDF values. */
    private com.coda.www.common.schemas.udfmaster.UDFDefinedValue[] udfValueList;

    /* The
     *                         database time stamp. */
    private short timeStamp;

    /* The date the record was
     *                     added. */
    private java.util.Calendar addDate;

    /* The
     *                         date the record was
     *                     modified. */
    private java.util.Calendar modDate;

    /* The code of the user who
     *                         added or modified the
     *                     record. */
    private java.lang.String usrName;

    public UDFMaster() {
    }

    public UDFMaster(
           java.lang.String code,
           java.lang.String name,
           java.lang.String shortName,
           java.lang.String fieldType,
           java.lang.String elementFilter,
           java.lang.Short elementLevel,
           java.lang.Boolean definedValues,
           java.lang.Boolean restrictToList,
           java.lang.Boolean mandatory,
           java.lang.String userExtension,
           java.lang.String listingMethod,
           com.coda.www.common.schemas.udfmaster.UDFDefinedValue[] udfValueList,
           short timeStamp,
           java.util.Calendar addDate,
           java.util.Calendar modDate,
           java.lang.String usrName) {
           this.code = code;
           this.name = name;
           this.shortName = shortName;
           this.fieldType = fieldType;
           this.elementFilter = elementFilter;
           this.elementLevel = elementLevel;
           this.definedValues = definedValues;
           this.restrictToList = restrictToList;
           this.mandatory = mandatory;
           this.userExtension = userExtension;
           this.listingMethod = listingMethod;
           this.udfValueList = udfValueList;
           this.timeStamp = timeStamp;
           this.addDate = addDate;
           this.modDate = modDate;
           this.usrName = usrName;
    }


    /**
     * Gets the code value for this UDFMaster.
     * 
     * @return code
     */
    public java.lang.String getCode() {
        return code;
    }


    /**
     * Sets the code value for this UDFMaster.
     * 
     * @param code
     */
    public void setCode(java.lang.String code) {
        this.code = code;
    }


    /**
     * Gets the name value for this UDFMaster.
     * 
     * @return name   * The name of the UDF
     *                     master.
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this UDFMaster.
     * 
     * @param name   * The name of the UDF
     *                     master.
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the shortName value for this UDFMaster.
     * 
     * @return shortName   * The
     *                         short name of the UDF
     *                     master.
     */
    public java.lang.String getShortName() {
        return shortName;
    }


    /**
     * Sets the shortName value for this UDFMaster.
     * 
     * @param shortName   * The
     *                         short name of the UDF
     *                     master.
     */
    public void setShortName(java.lang.String shortName) {
        this.shortName = shortName;
    }


    /**
     * Gets the fieldType value for this UDFMaster.
     * 
     * @return fieldType   * The
     *                         field type.
     */
    public java.lang.String getFieldType() {
        return fieldType;
    }


    /**
     * Sets the fieldType value for this UDFMaster.
     * 
     * @param fieldType   * The
     *                         field type.
     */
    public void setFieldType(java.lang.String fieldType) {
        this.fieldType = fieldType;
    }


    /**
     * Gets the elementFilter value for this UDFMaster.
     * 
     * @return elementFilter   * The
     *                         code of an element filter
     *                     master.
     */
    public java.lang.String getElementFilter() {
        return elementFilter;
    }


    /**
     * Sets the elementFilter value for this UDFMaster.
     * 
     * @param elementFilter   * The
     *                         code of an element filter
     *                     master.
     */
    public void setElementFilter(java.lang.String elementFilter) {
        this.elementFilter = elementFilter;
    }


    /**
     * Gets the elementLevel value for this UDFMaster.
     * 
     * @return elementLevel   * The
     *                         element level from which element codes are
     * selected.
     */
    public java.lang.Short getElementLevel() {
        return elementLevel;
    }


    /**
     * Sets the elementLevel value for this UDFMaster.
     * 
     * @param elementLevel   * The
     *                         element level from which element codes are
     * selected.
     */
    public void setElementLevel(java.lang.Short elementLevel) {
        this.elementLevel = elementLevel;
    }


    /**
     * Gets the definedValues value for this UDFMaster.
     * 
     * @return definedValues   * If
     *                         set, specifies that possible values for the
     * field are predefined.
     */
    public java.lang.Boolean getDefinedValues() {
        return definedValues;
    }


    /**
     * Sets the definedValues value for this UDFMaster.
     * 
     * @param definedValues   * If
     *                         set, specifies that possible values for the
     * field are predefined.
     */
    public void setDefinedValues(java.lang.Boolean definedValues) {
        this.definedValues = definedValues;
    }


    /**
     * Gets the restrictToList value for this UDFMaster.
     * 
     * @return restrictToList   * If
     *                         set, specifies that the user is restricted
     * to
     *                         choosing a value from the list of defined
     *                     values.
     */
    public java.lang.Boolean getRestrictToList() {
        return restrictToList;
    }


    /**
     * Sets the restrictToList value for this UDFMaster.
     * 
     * @param restrictToList   * If
     *                         set, specifies that the user is restricted
     * to
     *                         choosing a value from the list of defined
     *                     values.
     */
    public void setRestrictToList(java.lang.Boolean restrictToList) {
        this.restrictToList = restrictToList;
    }


    /**
     * Gets the mandatory value for this UDFMaster.
     * 
     * @return mandatory   * If
     *                         set, a value must be specified for the
     *                     field.
     */
    public java.lang.Boolean getMandatory() {
        return mandatory;
    }


    /**
     * Sets the mandatory value for this UDFMaster.
     * 
     * @param mandatory   * If
     *                         set, a value must be specified for the
     *                     field.
     */
    public void setMandatory(java.lang.Boolean mandatory) {
        this.mandatory = mandatory;
    }


    /**
     * Gets the userExtension value for this UDFMaster.
     * 
     * @return userExtension   * The
     *                         code of a user extension
     *                     master.
     */
    public java.lang.String getUserExtension() {
        return userExtension;
    }


    /**
     * Sets the userExtension value for this UDFMaster.
     * 
     * @param userExtension   * The
     *                         code of a user extension
     *                     master.
     */
    public void setUserExtension(java.lang.String userExtension) {
        this.userExtension = userExtension;
    }


    /**
     * Gets the listingMethod value for this UDFMaster.
     * 
     * @return listingMethod   * Specifies how the elements
     *                         available for selection are
     *                     presented.
     */
    public java.lang.String getListingMethod() {
        return listingMethod;
    }


    /**
     * Sets the listingMethod value for this UDFMaster.
     * 
     * @param listingMethod   * Specifies how the elements
     *                         available for selection are
     *                     presented.
     */
    public void setListingMethod(java.lang.String listingMethod) {
        this.listingMethod = listingMethod;
    }


    /**
     * Gets the udfValueList value for this UDFMaster.
     * 
     * @return udfValueList   * This element contains a list
     *                         of UDF values.
     */
    public com.coda.www.common.schemas.udfmaster.UDFDefinedValue[] getUdfValueList() {
        return udfValueList;
    }


    /**
     * Sets the udfValueList value for this UDFMaster.
     * 
     * @param udfValueList   * This element contains a list
     *                         of UDF values.
     */
    public void setUdfValueList(com.coda.www.common.schemas.udfmaster.UDFDefinedValue[] udfValueList) {
        this.udfValueList = udfValueList;
    }


    /**
     * Gets the timeStamp value for this UDFMaster.
     * 
     * @return timeStamp   * The
     *                         database time stamp.
     */
    public short getTimeStamp() {
        return timeStamp;
    }


    /**
     * Sets the timeStamp value for this UDFMaster.
     * 
     * @param timeStamp   * The
     *                         database time stamp.
     */
    public void setTimeStamp(short timeStamp) {
        this.timeStamp = timeStamp;
    }


    /**
     * Gets the addDate value for this UDFMaster.
     * 
     * @return addDate   * The date the record was
     *                     added.
     */
    public java.util.Calendar getAddDate() {
        return addDate;
    }


    /**
     * Sets the addDate value for this UDFMaster.
     * 
     * @param addDate   * The date the record was
     *                     added.
     */
    public void setAddDate(java.util.Calendar addDate) {
        this.addDate = addDate;
    }


    /**
     * Gets the modDate value for this UDFMaster.
     * 
     * @return modDate   * The
     *                         date the record was
     *                     modified.
     */
    public java.util.Calendar getModDate() {
        return modDate;
    }


    /**
     * Sets the modDate value for this UDFMaster.
     * 
     * @param modDate   * The
     *                         date the record was
     *                     modified.
     */
    public void setModDate(java.util.Calendar modDate) {
        this.modDate = modDate;
    }


    /**
     * Gets the usrName value for this UDFMaster.
     * 
     * @return usrName   * The code of the user who
     *                         added or modified the
     *                     record.
     */
    public java.lang.String getUsrName() {
        return usrName;
    }


    /**
     * Sets the usrName value for this UDFMaster.
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
        if (!(obj instanceof UDFMaster)) return false;
        UDFMaster other = (UDFMaster) obj;
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
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.shortName==null && other.getShortName()==null) || 
             (this.shortName!=null &&
              this.shortName.equals(other.getShortName()))) &&
            ((this.fieldType==null && other.getFieldType()==null) || 
             (this.fieldType!=null &&
              this.fieldType.equals(other.getFieldType()))) &&
            ((this.elementFilter==null && other.getElementFilter()==null) || 
             (this.elementFilter!=null &&
              this.elementFilter.equals(other.getElementFilter()))) &&
            ((this.elementLevel==null && other.getElementLevel()==null) || 
             (this.elementLevel!=null &&
              this.elementLevel.equals(other.getElementLevel()))) &&
            ((this.definedValues==null && other.getDefinedValues()==null) || 
             (this.definedValues!=null &&
              this.definedValues.equals(other.getDefinedValues()))) &&
            ((this.restrictToList==null && other.getRestrictToList()==null) || 
             (this.restrictToList!=null &&
              this.restrictToList.equals(other.getRestrictToList()))) &&
            ((this.mandatory==null && other.getMandatory()==null) || 
             (this.mandatory!=null &&
              this.mandatory.equals(other.getMandatory()))) &&
            ((this.userExtension==null && other.getUserExtension()==null) || 
             (this.userExtension!=null &&
              this.userExtension.equals(other.getUserExtension()))) &&
            ((this.listingMethod==null && other.getListingMethod()==null) || 
             (this.listingMethod!=null &&
              this.listingMethod.equals(other.getListingMethod()))) &&
            ((this.udfValueList==null && other.getUdfValueList()==null) || 
             (this.udfValueList!=null &&
              java.util.Arrays.equals(this.udfValueList, other.getUdfValueList()))) &&
            this.timeStamp == other.getTimeStamp() &&
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
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getShortName() != null) {
            _hashCode += getShortName().hashCode();
        }
        if (getFieldType() != null) {
            _hashCode += getFieldType().hashCode();
        }
        if (getElementFilter() != null) {
            _hashCode += getElementFilter().hashCode();
        }
        if (getElementLevel() != null) {
            _hashCode += getElementLevel().hashCode();
        }
        if (getDefinedValues() != null) {
            _hashCode += getDefinedValues().hashCode();
        }
        if (getRestrictToList() != null) {
            _hashCode += getRestrictToList().hashCode();
        }
        if (getMandatory() != null) {
            _hashCode += getMandatory().hashCode();
        }
        if (getUserExtension() != null) {
            _hashCode += getUserExtension().hashCode();
        }
        if (getListingMethod() != null) {
            _hashCode += getListingMethod().hashCode();
        }
        if (getUdfValueList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getUdfValueList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getUdfValueList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        _hashCode += getTimeStamp();
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
        new org.apache.axis.description.TypeDesc(UDFMaster.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/common/schemas/udfmaster", "UDFMaster"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("code");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/common/schemas/udfmaster", "Code"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/common/schemas/udfmaster", "Name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("elementFilter");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/common/schemas/udfmaster", "ElementFilter"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("elementLevel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/common/schemas/udfmaster", "ElementLevel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("definedValues");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/common/schemas/udfmaster", "DefinedValues"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("restrictToList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/common/schemas/udfmaster", "RestrictToList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mandatory");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/common/schemas/udfmaster", "Mandatory"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userExtension");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/common/schemas/udfmaster", "UserExtension"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listingMethod");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/common/schemas/udfmaster", "ListingMethod"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("udfValueList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/common/schemas/udfmaster", "UdfValueList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/common/schemas/udfmaster", "UDFDefinedValue"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.coda.com/common/schemas/udfmaster", "UDFDefinedValue"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timeStamp");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/common/schemas/udfmaster", "TimeStamp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
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
