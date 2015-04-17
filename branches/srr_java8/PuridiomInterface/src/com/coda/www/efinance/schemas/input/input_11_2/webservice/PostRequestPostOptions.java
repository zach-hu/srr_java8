/**
 * PostRequestPostOptions.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.input.input_11_2.webservice;

public class PostRequestPostOptions  implements java.io.Serializable {
    private java.lang.String postto;  // attribute

    private boolean relaxtriangulation;  // attribute

    private boolean warningsareerrors;  // attribute

    private boolean stoponerror;  // attribute

    private boolean logerrorsonly;  // attribute

    private boolean reportallerrors;  // attribute

    public PostRequestPostOptions() {
    }

    public PostRequestPostOptions(
           java.lang.String postto,
           boolean relaxtriangulation,
           boolean warningsareerrors,
           boolean stoponerror,
           boolean logerrorsonly,
           boolean reportallerrors) {
           this.postto = postto;
           this.relaxtriangulation = relaxtriangulation;
           this.warningsareerrors = warningsareerrors;
           this.stoponerror = stoponerror;
           this.logerrorsonly = logerrorsonly;
           this.reportallerrors = reportallerrors;
    }


    /**
     * Gets the postto value for this PostRequestPostOptions.
     * 
     * @return postto
     */
    public java.lang.String getPostto() {
        return postto;
    }


    /**
     * Sets the postto value for this PostRequestPostOptions.
     * 
     * @param postto
     */
    public void setPostto(java.lang.String postto) {
        this.postto = postto;
    }


    /**
     * Gets the relaxtriangulation value for this PostRequestPostOptions.
     * 
     * @return relaxtriangulation
     */
    public boolean isRelaxtriangulation() {
        return relaxtriangulation;
    }


    /**
     * Sets the relaxtriangulation value for this PostRequestPostOptions.
     * 
     * @param relaxtriangulation
     */
    public void setRelaxtriangulation(boolean relaxtriangulation) {
        this.relaxtriangulation = relaxtriangulation;
    }


    /**
     * Gets the warningsareerrors value for this PostRequestPostOptions.
     * 
     * @return warningsareerrors
     */
    public boolean isWarningsareerrors() {
        return warningsareerrors;
    }


    /**
     * Sets the warningsareerrors value for this PostRequestPostOptions.
     * 
     * @param warningsareerrors
     */
    public void setWarningsareerrors(boolean warningsareerrors) {
        this.warningsareerrors = warningsareerrors;
    }


    /**
     * Gets the stoponerror value for this PostRequestPostOptions.
     * 
     * @return stoponerror
     */
    public boolean isStoponerror() {
        return stoponerror;
    }


    /**
     * Sets the stoponerror value for this PostRequestPostOptions.
     * 
     * @param stoponerror
     */
    public void setStoponerror(boolean stoponerror) {
        this.stoponerror = stoponerror;
    }


    /**
     * Gets the logerrorsonly value for this PostRequestPostOptions.
     * 
     * @return logerrorsonly
     */
    public boolean isLogerrorsonly() {
        return logerrorsonly;
    }


    /**
     * Sets the logerrorsonly value for this PostRequestPostOptions.
     * 
     * @param logerrorsonly
     */
    public void setLogerrorsonly(boolean logerrorsonly) {
        this.logerrorsonly = logerrorsonly;
    }


    /**
     * Gets the reportallerrors value for this PostRequestPostOptions.
     * 
     * @return reportallerrors
     */
    public boolean isReportallerrors() {
        return reportallerrors;
    }


    /**
     * Sets the reportallerrors value for this PostRequestPostOptions.
     * 
     * @param reportallerrors
     */
    public void setReportallerrors(boolean reportallerrors) {
        this.reportallerrors = reportallerrors;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PostRequestPostOptions)) return false;
        PostRequestPostOptions other = (PostRequestPostOptions) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.postto==null && other.getPostto()==null) || 
             (this.postto!=null &&
              this.postto.equals(other.getPostto()))) &&
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
        if (getPostto() != null) {
            _hashCode += getPostto().hashCode();
        }
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
        new org.apache.axis.description.TypeDesc(PostRequestPostOptions.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/input/input-11.2/webservice", ">>PostRequest>PostOptions"));
        org.apache.axis.description.AttributeDesc attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("postto");
        attrField.setXmlName(new javax.xml.namespace.QName("", "postto"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtDocDest"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
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
