/**
 * SelectorServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.selector.selector_1_0.webservice;

public class SelectorServiceLocator extends org.apache.axis.client.Service implements com.coda.www.efinance.schemas.selector.selector_1_0.webservice.SelectorService {

    public SelectorServiceLocator() {
    }


    public SelectorServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SelectorServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SelectorServicePort
    private java.lang.String SelectorServicePort_address = "http://codaweb.int.thompsontractor.com/D43PRD/services/finance/selector/selector-1.0";

    public java.lang.String getSelectorServicePortAddress() {
        return SelectorServicePort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SelectorServicePortWSDDServiceName = "SelectorServicePort";

    public java.lang.String getSelectorServicePortWSDDServiceName() {
        return SelectorServicePortWSDDServiceName;
    }

    public void setSelectorServicePortWSDDServiceName(java.lang.String name) {
        SelectorServicePortWSDDServiceName = name;
    }

    public com.coda.www.efinance.schemas.selector.selector_1_0.webservice.SelectorServicePortTypes getSelectorServicePort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SelectorServicePort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSelectorServicePort(endpoint);
    }

    public com.coda.www.efinance.schemas.selector.selector_1_0.webservice.SelectorServicePortTypes getSelectorServicePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.coda.www.efinance.schemas.selector.selector_1_0.webservice.SelectorServiceSOAPBindingStub _stub = new com.coda.www.efinance.schemas.selector.selector_1_0.webservice.SelectorServiceSOAPBindingStub(portAddress, this);
            _stub.setPortName(getSelectorServicePortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSelectorServicePortEndpointAddress(java.lang.String address) {
        SelectorServicePort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.coda.www.efinance.schemas.selector.selector_1_0.webservice.SelectorServicePortTypes.class.isAssignableFrom(serviceEndpointInterface)) {
                com.coda.www.efinance.schemas.selector.selector_1_0.webservice.SelectorServiceSOAPBindingStub _stub = new com.coda.www.efinance.schemas.selector.selector_1_0.webservice.SelectorServiceSOAPBindingStub(new java.net.URL(SelectorServicePort_address), this);
                _stub.setPortName(getSelectorServicePortWSDDServiceName());
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
        if ("SelectorServicePort".equals(inputPortName)) {
            return getSelectorServicePort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/selector/selector-1.0/webservice", "SelectorService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/selector/selector-1.0/webservice", "SelectorServicePort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SelectorServicePort".equals(portName)) {
            setSelectorServicePortEndpointAddress(address);
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
