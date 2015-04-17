/**
 * PostToIntrayRequestPostToIntrayOptions.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.input.input_11_2.webservice;

public class PostToIntrayRequestPostToIntrayOptions  implements java.io.Serializable {
    private boolean relaxtriangulation;  // attribute

    private boolean warningsareerrors;  // attribute

    private boolean stoponerror;  // attribute

    private boolean logerrorsonly;  // attribute

    private boolean reportallerrors;  // attribute

    public PostToIntrayRequestPostToIntrayOptions() {
    }

    public PostToIntrayRequestPostToIntrayOptions(
           boolean relaxtriangulation,
           boolean warningsareerrors,
           boolean stoponerror,
           boolean logerrorsonly,
           boolean reportallerrors) {
           this.relaxtriangulation = relaxtriangulation;
           this.warningsareerrors = warningsareerrors;
           this.stoponerror = stoponerror;
           this.logerrorsonly = logerrorsonly;
           this.reportallerrors = reportallerrors;
    }


    /**
     * Gets the relaxtriangulation value for this PostToIntrayRequestPostToIntrayOptions.
     * 
     * @return relaxtriangulation
     */
    public boolean isRelaxtriangulation() {
        return relaxtriangulation;
    }


    /**
     * Sets the relaxtriangulation value for this PostToIntrayRequestPostToIntrayOptions.
     * 
     * @param relaxtriangulation
     */
    public void setRelaxtriangulation(boolean relaxtriangulation) {
        this.relaxtriangulation = relaxtriangulation;
    }


    /**
     * Gets the warningsareerrors value for this PostToIntrayRequestPostToIntrayOptions.
     * 
     * @return warningsareerrors
     */
    public boolean isWarningsareerrors() {
        return warningsareerrors;
    }


    /**
     * Sets the warningsareerrors value for this PostToIntrayRequestPostToIntrayOptions.
     * 
     * @param warningsareerrors
     */
    public void setWarningsareerrors(boolean warningsareerrors) {
        this.warningsareerrors = warningsareerrors;
    }


    /**
     * Gets the stoponerror value for this PostToIntrayRequestPostToIntrayOptions.
     * 
     * @return stoponerror
     */
    public boolean isStoponerror() {
        return stoponerror;
    }


    /**
     * Sets the stoponerror value for this PostToIntrayRequestPostToIntrayOptions.
     * 
     * @param stoponerror
     */
    public void setStoponerror(boolean stoponerror) {
        this.stoponerror = stoponerror;
    }


    /**
     * Gets the logerrorsonly value for this PostToIntrayRequestPostToIntrayOptions.
     * 
     * @return logerrorsonly
     */
    public boolean isLogerrorsonly() {
        return logerrorsonly;
    }


    /**
     * Sets the logerrorsonly value for this PostToIntrayRequestPostToIntrayOptions.
     * 
     * @param logerrorsonly
     */
    public void setLogerrorsonly(boolean logerrorsonly) {
        this.logerrorsonly = logerrorsonly;
    }


    /**
     * Gets the reportallerrors value for this PostToIntrayRequestPostToIntrayOptions.
     * 
     * @return reportallerrors
     */
    public boolean isReportallerrors() {
        return reportallerrors;
    }


    /**
     * Sets the reportallerrors value for this PostToIntrayRequestPostToIntrayOptions.
     * 
     * @param reportallerrors
     */
    public void setReportallerrors(boolean reportallerrors) {
        this.reportallerrors = reportallerrors;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PostToIntrayRequestPostToIntrayOptions)) return false;
        PostToIntrayRequestPostToIntrayOptions other = (PostToIntrayRequestPostToIntrayOptions) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.relaxtriangulation == other.isRelaxtriangulation() &&
            this.warningsareerrors == other.isWarningsareerrors() &&
            this.stoponerror == other.isStoponerror() &&
            this.logerrorsonly == other.isLogerrorsonly() &&
            this.reportallerrors == other.isReportallerrors();
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
        _hashCode += (isRelaxtriangulation() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isWarningsareerrors() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isStoponerror() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isLogerrorsonly() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isReportallerrors() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PostToIntrayRequestPostToIntrayOptions.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/input/input-11.2/webservice", ">>PostToIntrayRequest>PostToIntrayOptions"));
        org.apache.axis.description.AttributeDesc attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("relaxtriangulation");
        attrField.setXmlName(new javax.xml.namespace.QName("", "relaxtriangulation"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("warningsareerrors");
        attrField.setXmlName(new javax.xml.namespace.QName("", "warningsareerrors"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("stoponerror");
        attrField.setXmlName(new javax.xml.namespace.QName("", "stoponerror"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("logerrorsonly");
        attrField.setXmlName(new javax.xml.namespace.QName("", "logerrorsonly"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("reportallerrors");
        attrField.setXmlName(new javax.xml.namespace.QName("", "reportallerrors"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        typeDesc.addFieldDesc(attrField);
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
