/**
 * POI960Input.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package iseries.wsbeans.updateembo.xsd;

public class POI960Input  implements java.io.Serializable {
    private java.lang.String _PI_DBSKEY;

    private java.lang.String _PI_PANO;

    private java.lang.String _PI_PONUMB;

    private java.math.BigDecimal _PI_POSEQN;

    public POI960Input() {
    }

    public POI960Input(
           java.lang.String _PI_DBSKEY,
           java.lang.String _PI_PANO,
           java.lang.String _PI_PONUMB,
           java.math.BigDecimal _PI_POSEQN) {
           this._PI_DBSKEY = _PI_DBSKEY;
           this._PI_PANO = _PI_PANO;
           this._PI_PONUMB = _PI_PONUMB;
           this._PI_POSEQN = _PI_POSEQN;
    }


    /**
     * Gets the _PI_DBSKEY value for this POI960Input.
     * 
     * @return _PI_DBSKEY
     */
    public java.lang.String get_PI_DBSKEY() {
        return _PI_DBSKEY;
    }


    /**
     * Sets the _PI_DBSKEY value for this POI960Input.
     * 
     * @param _PI_DBSKEY
     */
    public void set_PI_DBSKEY(java.lang.String _PI_DBSKEY) {
        this._PI_DBSKEY = _PI_DBSKEY;
    }


    /**
     * Gets the _PI_PANO value for this POI960Input.
     * 
     * @return _PI_PANO
     */
    public java.lang.String get_PI_PANO() {
        return _PI_PANO;
    }


    /**
     * Sets the _PI_PANO value for this POI960Input.
     * 
     * @param _PI_PANO
     */
    public void set_PI_PANO(java.lang.String _PI_PANO) {
        this._PI_PANO = _PI_PANO;
    }


    /**
     * Gets the _PI_PONUMB value for this POI960Input.
     * 
     * @return _PI_PONUMB
     */
    public java.lang.String get_PI_PONUMB() {
        return _PI_PONUMB;
    }


    /**
     * Sets the _PI_PONUMB value for this POI960Input.
     * 
     * @param _PI_PONUMB
     */
    public void set_PI_PONUMB(java.lang.String _PI_PONUMB) {
        this._PI_PONUMB = _PI_PONUMB;
    }


    /**
     * Gets the _PI_POSEQN value for this POI960Input.
     * 
     * @return _PI_POSEQN
     */
    public java.math.BigDecimal get_PI_POSEQN() {
        return _PI_POSEQN;
    }


    /**
     * Sets the _PI_POSEQN value for this POI960Input.
     * 
     * @param _PI_POSEQN
     */
    public void set_PI_POSEQN(java.math.BigDecimal _PI_POSEQN) {
        this._PI_POSEQN = _PI_POSEQN;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof POI960Input)) return false;
        POI960Input other = (POI960Input) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this._PI_DBSKEY==null && other.get_PI_DBSKEY()==null) || 
             (this._PI_DBSKEY!=null &&
              this._PI_DBSKEY.equals(other.get_PI_DBSKEY()))) &&
            ((this._PI_PANO==null && other.get_PI_PANO()==null) || 
             (this._PI_PANO!=null &&
              this._PI_PANO.equals(other.get_PI_PANO()))) &&
            ((this._PI_PONUMB==null && other.get_PI_PONUMB()==null) || 
             (this._PI_PONUMB!=null &&
              this._PI_PONUMB.equals(other.get_PI_PONUMB()))) &&
            ((this._PI_POSEQN==null && other.get_PI_POSEQN()==null) || 
             (this._PI_POSEQN!=null &&
              this._PI_POSEQN.equals(other.get_PI_POSEQN())));
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
        if (get_PI_DBSKEY() != null) {
            _hashCode += get_PI_DBSKEY().hashCode();
        }
        if (get_PI_PANO() != null) {
            _hashCode += get_PI_PANO().hashCode();
        }
        if (get_PI_PONUMB() != null) {
            _hashCode += get_PI_PONUMB().hashCode();
        }
        if (get_PI_POSEQN() != null) {
            _hashCode += get_PI_POSEQN().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(POI960Input.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://updateembo.wsbeans.iseries/xsd", "POI960Input"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("_PI_DBSKEY");
        elemField.setXmlName(new javax.xml.namespace.QName("http://updateembo.wsbeans.iseries/xsd", "_PI_DBSKEY"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("_PI_PANO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://updateembo.wsbeans.iseries/xsd", "_PI_PANO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("_PI_PONUMB");
        elemField.setXmlName(new javax.xml.namespace.QName("http://updateembo.wsbeans.iseries/xsd", "_PI_PONUMB"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("_PI_POSEQN");
        elemField.setXmlName(new javax.xml.namespace.QName("http://updateembo.wsbeans.iseries/xsd", "_PI_POSEQN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
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
