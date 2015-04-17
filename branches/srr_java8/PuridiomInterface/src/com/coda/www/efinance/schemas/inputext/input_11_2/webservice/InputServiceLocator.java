/**
 * InputServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.inputext.input_11_2.webservice;

public class InputServiceLocator extends org.apache.axis.client.Service implements com.coda.www.efinance.schemas.inputext.input_11_2.webservice.InputService {

    public InputServiceLocator() {
    }


    public InputServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public InputServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for InputServicePort
    private java.lang.String InputServicePort_address = "http://codaweb.int.thompsontractor.com/D43PRD/services/finance/inputext/input-11.2";

    public java.lang.String getInputServicePortAddress() {
        return InputServicePort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String InputServicePortWSDDServiceName = "InputServicePort";

    public java.lang.String getInputServicePortWSDDServiceName() {
        return InputServicePortWSDDServiceName;
    }

    public void setInputServicePortWSDDServiceName(java.lang.String name) {
        InputServicePortWSDDServiceName = name;
    }

    public com.coda.www.efinance.schemas.inputext.input_11_2.webservice.InputServicePortTypes getInputServicePort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(InputServicePort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getInputServicePort(endpoint);
    }

    public com.coda.www.efinance.schemas.inputext.input_11_2.webservice.InputServicePortTypes getInputServicePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.coda.www.efinance.schemas.inputext.input_11_2.webservice.InputServiceSOAPBindingStub _stub = new com.coda.www.efinance.schemas.inputext.input_11_2.webservice.InputServiceSOAPBindingStub(portAddress, this);
            _stub.setPortName(getInputServicePortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setInputServicePortEndpointAddress(java.lang.String address) {
        InputServicePort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.coda.www.efinance.schemas.inputext.input_11_2.webservice.InputServicePortTypes.class.isAssignableFrom(serviceEndpointInterface)) {
                com.coda.www.efinance.schemas.inputext.input_11_2.webservice.InputServiceSOAPBindingStub _stub = new com.coda.www.efinance.schemas.inputext.input_11_2.webservice.InputServiceSOAPBindingStub(new java.net.URL(InputServicePort_address), this);
                _stub.setPortName(getInputServicePortWSDDServiceName());
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
        if ("InputServicePort".equals(inputPortName)) {
            return getInputServicePort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "InputService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "InputServicePort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("InputServicePort".equals(portName)) {
            setInputServicePortEndpointAddress(address);
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
