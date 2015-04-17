/**
 * ElementFilterMaster.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.elementfiltermaster;


/**
 * This element holds an element filter
 *             master.
 */
public class ElementFilterMaster  implements java.io.Serializable {
    private java.lang.Short timeStamp;

    /* The code for the company in
     *                         which the element filter master is being
     *                     maintained. */
    private java.lang.String cmpCode;

    /* The
     *                         element level to which the element filter
     * master
     *                     applies. */
    private short level;

    /* The
     *                         code of the element filter
     *                     master. */
    private java.lang.String code;

    /* The
     *                         full name for the element filter
     *                     master. */
    private java.lang.String name;

    /* The
     *                         abbreviated name for the element filter
     *                     master. */
    private java.lang.String shortName;

    /* This element holds the list
     *                         of criteria used by the element filter
     *                     master. */
    private com.coda.www.efinance.schemas.elementfiltermaster.ElementFilterItem[] elementFilterList;

    public ElementFilterMaster() {
    }

    public ElementFilterMaster(
           java.lang.Short timeStamp,
           java.lang.String cmpCode,
           short level,
           java.lang.String code,
           java.lang.String name,
           java.lang.String shortName,
           com.coda.www.efinance.schemas.elementfiltermaster.ElementFilterItem[] elementFilterList) {
           this.timeStamp = timeStamp;
           this.cmpCode = cmpCode;
           this.level = level;
           this.code = code;
           this.name = name;
           this.shortName = shortName;
           this.elementFilterList = elementFilterList;
    }


    /**
     * Gets the timeStamp value for this ElementFilterMaster.
     * 
     * @return timeStamp
     */
    public java.lang.Short getTimeStamp() {
        return timeStamp;
    }


    /**
     * Sets the timeStamp value for this ElementFilterMaster.
     * 
     * @param timeStamp
     */
    public void setTimeStamp(java.lang.Short timeStamp) {
        this.timeStamp = timeStamp;
    }


    /**
     * Gets the cmpCode value for this ElementFilterMaster.
     * 
     * @return cmpCode   * The code for the company in
     *                         which the element filter master is being
     *                     maintained.
     */
    public java.lang.String getCmpCode() {
        return cmpCode;
    }


    /**
     * Sets the cmpCode value for this ElementFilterMaster.
     * 
     * @param cmpCode   * The code for the company in
     *                         which the element filter master is being
     *                     maintained.
     */
    public void setCmpCode(java.lang.String cmpCode) {
        this.cmpCode = cmpCode;
    }


    /**
     * Gets the level value for this ElementFilterMaster.
     * 
     * @return level   * The
     *                         element level to which the element filter
     * master
     *                     applies.
     */
    public short getLevel() {
        return level;
    }


    /**
     * Sets the level value for this ElementFilterMaster.
     * 
     * @param level   * The
     *                         element level to which the element filter
     * master
     *                     applies.
     */
    public void setLevel(short level) {
        this.level = level;
    }


    /**
     * Gets the code value for this ElementFilterMaster.
     * 
     * @return code   * The
     *                         code of the element filter
     *                     master.
     */
    public java.lang.String getCode() {
        return code;
    }


    /**
     * Sets the code value for this ElementFilterMaster.
     * 
     * @param code   * The
     *                         code of the element filter
     *                     master.
     */
    public void setCode(java.lang.String code) {
        this.code = code;
    }


    /**
     * Gets the name value for this ElementFilterMaster.
     * 
     * @return name   * The
     *                         full name for the element filter
     *                     master.
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this ElementFilterMaster.
     * 
     * @param name   * The
     *                         full name for the element filter
     *                     master.
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the shortName value for this ElementFilterMaster.
     * 
     * @return shortName   * The
     *                         abbreviated name for the element filter
     *                     master.
     */
    public java.lang.String getShortName() {
        return shortName;
    }


    /**
     * Sets the shortName value for this ElementFilterMaster.
     * 
     * @param shortName   * The
     *                         abbreviated name for the element filter
     *                     master.
     */
    public void setShortName(java.lang.String shortName) {
        this.shortName = shortName;
    }


    /**
     * Gets the elementFilterList value for this ElementFilterMaster.
     * 
     * @return elementFilterList   * This element holds the list
     *                         of criteria used by the element filter
     *                     master.
     */
    public com.coda.www.efinance.schemas.elementfiltermaster.ElementFilterItem[] getElementFilterList() {
        return elementFilterList;
    }


    /**
     * Sets the elementFilterList value for this ElementFilterMaster.
     * 
     * @param elementFilterList   * This element holds the list
     *                         of criteria used by the element filter
     *                     master.
     */
    public void setElementFilterList(com.coda.www.efinance.schemas.elementfiltermaster.ElementFilterItem[] elementFilterList) {
        this.elementFilterList = elementFilterList;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ElementFilterMaster)) return false;
        ElementFilterMaster other = (ElementFilterMaster) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.timeStamp==null && other.getTimeStamp()==null) || 
             (this.timeStamp!=null &&
              this.timeStamp.equals(other.getTimeStamp()))) &&
            ((this.cmpCode==null && other.getCmpCode()==null) || 
             (this.cmpCode!=null &&
              this.cmpCode.equals(other.getCmpCode()))) &&
            this.level == other.getLevel() &&
            ((this.code==null && other.getCode()==null) || 
             (this.code!=null &&
              this.code.equals(other.getCode()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.shortName==null && other.getShortName()==null) || 
             (this.shortName!=null &&
              this.shortName.equals(other.getShortName()))) &&
            ((this.elementFilterList==null && other.getElementFilterList()==null) || 
             (this.elementFilterList!=null &&
              java.util.Arrays.equals(this.elementFilterList, other.getElementFilterList())));
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
        if (getTimeStamp() != null) {
            _hashCode += getTimeStamp().hashCode();
        }
        if (getCmpCode() != null) {
            _hashCode += getCmpCode().hashCode();
        }
        _hashCode += getLevel();
        if (getCode() != null) {
            _hashCode += getCode().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getShortName() != null) {
            _hashCode += getShortName().hashCode();
        }
        if (getElementFilterList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getElementFilterList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getElementFilterList(), i);
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
        new org.apache.axis.description.TypeDesc(ElementFilterMaster.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementfiltermaster", "ElementFilterMaster"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timeStamp");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementfiltermaster", "TimeStamp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
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
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementfiltermaster", "Name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shortName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementfiltermaster", "ShortName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("elementFilterList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementfiltermaster", "ElementFilterList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementfiltermaster", "ElementFilterItem"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementfiltermaster", "ElementFilterItem"));
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
