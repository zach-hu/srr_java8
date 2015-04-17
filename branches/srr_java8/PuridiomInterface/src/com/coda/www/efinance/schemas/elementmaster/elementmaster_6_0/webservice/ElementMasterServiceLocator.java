/**
 * ElementMasterServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice;

public class ElementMasterServiceLocator extends org.apache.axis.client.Service implements com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.ElementMasterService {

    public ElementMasterServiceLocator() {
    }


    public ElementMasterServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ElementMasterServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ElementMasterServicePort
    private java.lang.String ElementMasterServicePort_address = "http://codaweb.int.thompsontractor.com/D43PRD/services/finance/elementmaster/elementmaster-6.0";

    public java.lang.String getElementMasterServicePortAddress() {
        return ElementMasterServicePort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ElementMasterServicePortWSDDServiceName = "ElementMasterServicePort";

    public java.lang.String getElementMasterServicePortWSDDServiceName() {
        return ElementMasterServicePortWSDDServiceName;
    }

    public void setElementMasterServicePortWSDDServiceName(java.lang.String name) {
        ElementMasterServicePortWSDDServiceName = name;
    }

    public com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.ElementMasterServicePortTypes getElementMasterServicePort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ElementMasterServicePort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getElementMasterServicePort(endpoint);
    }

    public com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.ElementMasterServicePortTypes getElementMasterServicePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.ElementMasterServiceSOAPBindingStub _stub = new com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.ElementMasterServiceSOAPBindingStub(portAddress, this);
            _stub.setPortName(getElementMasterServicePortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setElementMasterServicePortEndpointAddress(java.lang.String address) {
        ElementMasterServicePort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.ElementMasterServicePortTypes.class.isAssignableFrom(serviceEndpointInterface)) {
                com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.ElementMasterServiceSOAPBindingStub _stub = new com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.ElementMasterServiceSOAPBindingStub(new java.net.URL(ElementMasterServicePort_address), this);
                _stub.setPortName(getElementMasterServicePortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("ElementMasterServicePort".equals(inputPortName)) {
            return getElementMasterServicePort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", "ElementMasterService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", "ElementMasterServicePort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ElementMasterServicePort".equals(portName)) {
            setElementMasterServicePortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
