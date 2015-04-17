/**
 * POI961Result.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package iseries.wsbeans.updatestockorder.xsd;

public class POI961Result  implements java.io.Serializable {
    private java.lang.String _PO_ERRORCODE;

    private java.lang.String _PO_ERRORDESC;

    public POI961Result() {
    }

    public POI961Result(
           java.lang.String _PO_ERRORCODE,
           java.lang.String _PO_ERRORDESC) {
           this._PO_ERRORCODE = _PO_ERRORCODE;
           this._PO_ERRORDESC = _PO_ERRORDESC;
    }


    /**
     * Gets the _PO_ERRORCODE value for this POI961Result.
     * 
     * @return _PO_ERRORCODE
     */
    public java.lang.String get_PO_ERRORCODE() {
        return _PO_ERRORCODE;
    }


    /**
     * Sets the _PO_ERRORCODE value for this POI961Result.
     * 
     * @param _PO_ERRORCODE
     */
    public void set_PO_ERRORCODE(java.lang.String _PO_ERRORCODE) {
        this._PO_ERRORCODE = _PO_ERRORCODE;
    }


    /**
     * Gets the _PO_ERRORDESC value for this POI961Result.
     * 
     * @return _PO_ERRORDESC
     */
    public java.lang.String get_PO_ERRORDESC() {
        return _PO_ERRORDESC;
    }


    /**
     * Sets the _PO_ERRORDESC value for this POI961Result.
     * 
     * @param _PO_ERRORDESC
     */
    public void set_PO_ERRORDESC(java.lang.String _PO_ERRORDESC) {
        this._PO_ERRORDESC = _PO_ERRORDESC;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof POI961Result)) return false;
        POI961Result other = (POI961Result) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this._PO_ERRORCODE==null && other.get_PO_ERRORCODE()==null) || 
             (this._PO_ERRORCODE!=null &&
              this._PO_ERRORCODE.equals(other.get_PO_ERRORCODE()))) &&
            ((this._PO_ERRORDESC==null && other.get_PO_ERRORDESC()==null) || 
             (this._PO_ERRORDESC!=null &&
              this._PO_ERRORDESC.equals(other.get_PO_ERRORDESC())));
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
        if (get_PO_ERRORCODE() != null) {
            _hashCode += get_PO_ERRORCODE().hashCode();
        }
        if (get_PO_ERRORDESC() != null) {
            _hashCode += get_PO_ERRORDESC().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(POI961Result.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://updatestockorder.wsbeans.iseries/xsd", "POI961Result"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("_PO_ERRORCODE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://updatestockorder.wsbeans.iseries/xsd", "_PO_ERRORCODE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("_PO_ERRORDESC");
        elemField.setXmlName(new javax.xml.namespace.QName("http://updatestockorder.wsbeans.iseries/xsd", "_PO_ERRORDESC"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
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
