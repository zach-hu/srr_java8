/**
 * POI956Input.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package iseries.wsbeans.getworkordervalidation.xsd;

public class POI956Input  implements java.io.Serializable {
    private java.lang.String _PI_CUNO;

    private java.lang.String _PI_WONO;

    private java.lang.String _PI_WOOPNO;

    private java.lang.String _PI_WOSGNO;

    public POI956Input() {
    }

    public POI956Input(
           java.lang.String _PI_CUNO,
           java.lang.String _PI_WONO,
           java.lang.String _PI_WOOPNO,
           java.lang.String _PI_WOSGNO) {
           this._PI_CUNO = _PI_CUNO;
           this._PI_WONO = _PI_WONO;
           this._PI_WOOPNO = _PI_WOOPNO;
           this._PI_WOSGNO = _PI_WOSGNO;
    }


    /**
     * Gets the _PI_CUNO value for this POI956Input.
     * 
     * @return _PI_CUNO
     */
    public java.lang.String get_PI_CUNO() {
        return _PI_CUNO;
    }


    /**
     * Sets the _PI_CUNO value for this POI956Input.
     * 
     * @param _PI_CUNO
     */
    public void set_PI_CUNO(java.lang.String _PI_CUNO) {
        this._PI_CUNO = _PI_CUNO;
    }


    /**
     * Gets the _PI_WONO value for this POI956Input.
     * 
     * @return _PI_WONO
     */
    public java.lang.String get_PI_WONO() {
        return _PI_WONO;
    }


    /**
     * Sets the _PI_WONO value for this POI956Input.
     * 
     * @param _PI_WONO
     */
    public void set_PI_WONO(java.lang.String _PI_WONO) {
        this._PI_WONO = _PI_WONO;
    }


    /**
     * Gets the _PI_WOOPNO value for this POI956Input.
     * 
     * @return _PI_WOOPNO
     */
    public java.lang.String get_PI_WOOPNO() {
        return _PI_WOOPNO;
    }


    /**
     * Sets the _PI_WOOPNO value for this POI956Input.
     * 
     * @param _PI_WOOPNO
     */
    public void set_PI_WOOPNO(java.lang.String _PI_WOOPNO) {
        this._PI_WOOPNO = _PI_WOOPNO;
    }


    /**
     * Gets the _PI_WOSGNO value for this POI956Input.
     * 
     * @return _PI_WOSGNO
     */
    public java.lang.String get_PI_WOSGNO() {
        return _PI_WOSGNO;
    }


    /**
     * Sets the _PI_WOSGNO value for this POI956Input.
     * 
     * @param _PI_WOSGNO
     */
    public void set_PI_WOSGNO(java.lang.String _PI_WOSGNO) {
        this._PI_WOSGNO = _PI_WOSGNO;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof POI956Input)) return false;
        POI956Input other = (POI956Input) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this._PI_CUNO==null && other.get_PI_CUNO()==null) || 
             (this._PI_CUNO!=null &&
              this._PI_CUNO.equals(other.get_PI_CUNO()))) &&
            ((this._PI_WONO==null && other.get_PI_WONO()==null) || 
             (this._PI_WONO!=null &&
              this._PI_WONO.equals(other.get_PI_WONO()))) &&
            ((this._PI_WOOPNO==null && other.get_PI_WOOPNO()==null) || 
             (this._PI_WOOPNO!=null &&
              this._PI_WOOPNO.equals(other.get_PI_WOOPNO()))) &&
            ((this._PI_WOSGNO==null && other.get_PI_WOSGNO()==null) || 
             (this._PI_WOSGNO!=null &&
              this._PI_WOSGNO.equals(other.get_PI_WOSGNO())));
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
        if (get_PI_CUNO() != null) {
            _hashCode += get_PI_CUNO().hashCode();
        }
        if (get_PI_WONO() != null) {
            _hashCode += get_PI_WONO().hashCode();
        }
        if (get_PI_WOOPNO() != null) {
            _hashCode += get_PI_WOOPNO().hashCode();
        }
        if (get_PI_WOSGNO() != null) {
            _hashCode += get_PI_WOSGNO().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(POI956Input.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://getworkordervalidation.wsbeans.iseries/xsd", "POI956Input"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("_PI_CUNO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://getworkordervalidation.wsbeans.iseries/xsd", "_PI_CUNO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("_PI_WONO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://getworkordervalidation.wsbeans.iseries/xsd", "_PI_WONO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("_PI_WOOPNO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://getworkordervalidation.wsbeans.iseries/xsd", "_PI_WOOPNO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("_PI_WOSGNO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://getworkordervalidation.wsbeans.iseries/xsd", "_PI_WOSGNO"));
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
