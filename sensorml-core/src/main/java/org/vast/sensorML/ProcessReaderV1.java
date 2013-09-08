/***************************** BEGIN LICENSE BLOCK ***************************

 The contents of this file are subject to the Mozilla Public License Version
 1.1 (the "License"); you may not use this file except in compliance with
 the License. You may obtain a copy of the License at
 http://www.mozilla.org/MPL/MPL-1.1.html
 
 Software distributed under the License is distributed on an "AS IS" basis,
 WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 for the specific language governing rights and limitations under the License.
 
 The Original Code is the "SensorML DataProcessing Engine".
 
 The Initial Developer of the Original Code is the VAST team at the
 
 Contributor(s): 
    Alexandre Robin <robin@nsstc.uah.edu>
 
******************************* END LICENSE BLOCK ***************************/

package org.vast.sensorML;

import org.w3c.dom.*;
import org.vast.process.*;
import org.vast.cdm.common.DataComponent;
import org.vast.xml.DOMHelper;
import org.vast.xml.XMLReaderException;
import org.vast.sensorML.metadata.Metadata;
import org.vast.sweCommon.SWECommonUtils;


/**
 * <p><b>Title:</b><br/>
 * Process Reader v1.0
 * </p>
 *
 * <p><b>Description:</b><br/>
 * Reader for Process, ProcessModel, ProcessChain, ProcessList
 * Uses Reflection to create particular ProcessModel for plug'n play capability
 * </p>
 *
 * <p>Copyright (c) 2005</p>
 * @author Alexandre Robin
 * @version 1.0
 */
public class ProcessReaderV1 extends AbstractSMLReader implements ProcessReader
{
    protected static final String dataSeparator = "/"; 
    protected MetadataReaderV1 metadataReader;
    protected ProcessLoader processLoader;
    protected SWECommonUtils utils;
    protected boolean readMetadata = false;
    protected boolean createExecutableProcess = true;
        
    
    /**
     * Constructs a ProcessReader using the specified DOMReader
     * @param dom
     */
    public ProcessReaderV1()
    {
        utils = new SWECommonUtils();
        processLoader = new ProcessLoader();
    }
    
    
    /**
     * Reads any Process (ProcessModel, ProcessChain or derived versions)
     * @param processDefElement
     * @return
     * @throws SMLException
     */
    public DataProcess read(DOMHelper dom, Element processElement) throws XMLReaderException
    {
        DataProcess dataProcess;

        // read process core info (model or chain)
        if (processElement.getLocalName().equals("DataSource"))
            dataProcess = readDataSource(dom, processElement);
        if (dom.existElement(processElement, "components"))
            dataProcess = readProcessChain(dom, processElement);
        else
            dataProcess = readProcessModel(dom, processElement);

        // get name from parent property or element
        if (processElement.getParentNode().getNodeType() == Node.ELEMENT_NODE)
            dataProcess.setName(dom.getAttributeValue((Element)processElement.getParentNode(), "@name"));
        else
            dataProcess.setName(processElement.getLocalName());
            
        return dataProcess;
    }
    
    
    /**
     * Parses all metadata content and add it to the given DataProcess object
     * @param processElement
     * @param process
     */
    protected void parseMetadata(DOMHelper dom, Element processElement, DataProcess dataProcess) throws XMLReaderException
    {
        // read metadata if needed
        if (readMetadata)
        {
            if (metadataReader == null)
                metadataReader = new MetadataReaderV1();
            
            Metadata metadata = metadataReader.readMetadata(dom, processElement);
            dataProcess.setProperty(DataProcess.METADATA, metadata);
        }
    }
    
    
    /**
     * Creates a DataProcess object corresponding to given method URN
     * @param processURN
     * @return
     * @throws SMLException
     */
    protected DataProcess readProcessModel(DOMHelper dom, Element processModelElement) throws XMLReaderException
    {
        // get process urn
        String uri = dom.getAttributeValue(processModelElement, "method/href");
        
        // load process
        DataProcess newProcess;
        try
        {
            if (createExecutableProcess)
                newProcess = processLoader.loadProcess(uri);
            else
                newProcess = new Dummy_Process();
        }
        catch (Exception e)
        {
            throw new XMLReaderException("No implementation found for process method " + uri);
        }
        
        // read metadata
        parseMetadata(dom, processModelElement, newProcess);
        
        // read output/output/parameter structures
        readProcessIO(dom, processModelElement, newProcess);
        
        // set process type (=method uri)
        newProcess.setType(uri);
        
        return newProcess;
    }
    
    
    /**
     * Constructs a Data Source Process
     * @param dataSourceElement
     * @return
     * @throws SMLException
     */
    protected DataProcess readDataSource(DOMHelper dom, Element dataSourceElement) throws XMLReaderException
    {
        // TODO parse DataSource structure
        DataProcess newProcess = new Dummy_Process();
        
        // read metadata
        parseMetadata(dom, dataSourceElement, newProcess);
        
        try
        {
            // read dataDefinition
            Element dataDefElt = dom.getElement(dataSourceElement, "dataDefinition");
            DataComponent dataDef = utils.readComponentProperty(dom, dataDefElt);
            newProcess.addOutput(dataDef.getName(), dataDef);            
        }
        catch (XMLReaderException e)
        {
            throw new XMLReaderException("Error while parsing DataSource", e);
        }
        
        // TODO case of observation reference...
        
        return newProcess;
    }
    
    
    /**
     * Constructs a ProcessChain from XML structure
     * @param processChainElement Element the XML element to read from
     * @throws SMLException
     * @return ProcessChain
     */
    protected ProcessChain readProcessChain(DOMHelper dom, Element processChainElement) throws XMLReaderException
    {
        return readProcessChain(dom, processChainElement, null);
    }
    
    
    /**
     * Fills up process chain information using XML element content
     * @param processChainElement the XML element to read from
     * @param processChain the process chain to fill up
     * @return ProcessChain
     * @throws SMLException
     */
    protected ProcessChain readProcessChain(DOMHelper dom, Element processChainElement, ProcessChain processChain) throws XMLReaderException
    {
        NodeList processList, connectionList;
        
        // get process list
        processList = dom.getElements(processChainElement, "components/ComponentList/component/*");
        int memberCount = processList.getLength();
        
        // construct the process chain if it was not given
        if (processChain == null)
            processChain = new ProcessChain(memberCount);
        
        // read output/output/parameter structures
        readProcessIO(dom, processChainElement, processChain);
        
        // parse and add each process
        for (int i = 0; i < processList.getLength(); i++)
        {
            Element processElement = (Element)processList.item(i);
            if (processElement != null)
            {
                DataProcess childProcess = read(dom, processElement);
                processChain.addProcess(childProcess.getName(), childProcess);
            }
        }
        
        // get connection list
        connectionList = dom.getElements(processChainElement, "connections/ConnectionList/connection/*");
        int connectionNumber = connectionList.getLength();

        // parse and create each DataQueue between processes
        for (int i = 0; i < connectionNumber; i++)
        {
            Element connectionElement = (Element)connectionList.item(i);
            String linkType = connectionElement.getLocalName();
            
            DataQueue dataQueue = null;
            
            if (linkType.equals("Link"))
            {
            	// create new DataQueue
                dataQueue = new DataQueue();
                
                // parse source
                String source = dom.getAttributeValue(connectionElement, "source/ref");
                connectSignal(processChain, dataQueue, source);
                
                // parse destination
                String dest = dom.getAttributeValue(connectionElement, "destination/ref");
                connectSignal(processChain, dataQueue, dest);
                            
                // check source/destination coherency
                //dataQueue.check();
            }
            else if (linkType.equals("ArrayLink"))
            {
            	this.createMuxProcess(dom, connectionElement);
            }
            else
            	throw new XMLReaderException("Unkown link type: " + linkType);
            
            // also add the connection to the main list
            processChain.getInternalConnections().add(dataQueue);
        }
        
        // read metadata
        parseMetadata(dom, processChainElement, processChain);
        
        return processChain;
    }
    
    
    /**
     * TODO Creates a multiplexing process implied by an ArrayLink connection
     * @param arrayLinkElement
     */
    public void createMuxProcess(DOMHelper dom, Element arrayLinkElement)
    {
    	
    }
    
    
    /**
     * Connect a dataQueue to desired process input/output/param
     * @param processChain
     * @param dataQueue
     * @param linkString
     * @throws SMLException
     */
    public void connectSignal(ProcessChain processChain, DataQueue dataQueue, String linkString) throws XMLReaderException
    {
        boolean internalConnection = false;
        DataProcess selectedProcess;        
        
        // parse link path
        int sep1 = linkString.indexOf(dataSeparator, 1);
        String processName = linkString.substring(0, sep1);
        
        int sep2 = linkString.indexOf(dataSeparator, sep1 + 1);
        String portType = linkString.substring(sep1+1, sep2);
        
        String portName = linkString.substring(sep2 + 1);
        //String [] names = portName.split(dataSeparator);
                
        // special case if 'this'
        if (processName.equalsIgnoreCase("this"))
        {
            // make internal connections if 'this'
            internalConnection = true;
            selectedProcess = processChain;
        }
        else
        {
            // find desired process in the chain
            selectedProcess = processChain.getProcess(processName);            
        }        
        
        if (selectedProcess == null)
        {
            throw new XMLReaderException("Process " + processName + " does't exist in ProcessChain");
        }       
        
        // connect connection to input, output or parameter port
        if (portType.equals("inputs"))
        {
            try
            {
                if (internalConnection)
                    processChain.connectInternalInput(portName, dataQueue);
                else
                    selectedProcess.connectInput(portName, dataQueue);
            }
            catch (ProcessException e)
            {
                throw new XMLReaderException("No input named " + portName + " in process " + processName);
            }
        }
        else if (portType.equals("outputs"))
        {
            try
            {
                if (internalConnection)
                    processChain.connectInternalOutput(portName, dataQueue);
                else
                    selectedProcess.connectOutput(portName, dataQueue);
            }
            catch (ProcessException e)
            {
                throw new XMLReaderException("No output named " + portName + " in process " + processName);
            }
        }
        else if (portType.equals("parameters"))
        {
            try
            {
                if (internalConnection)
                    processChain.connectInternalParam(portName, dataQueue);
                else
                    selectedProcess.connectParameter(portName, dataQueue);
            }
            catch (ProcessException e)
            {
                throw new XMLReaderException("No parameter named " + portName + " in process " + processName);
            }
        }
        
        // make sure connection is ok
        try
        {
            dataQueue.check();
        }
        catch (ProcessException e)
        {
            String srcName = dataQueue.getSourceProcess().getName();
            String destName = dataQueue.getDestinationProcess().getName();
            throw new XMLReaderException("Connection on " + linkString + " cannot be made between " + srcName + " and " + destName, e);
        }
    }


    /**
     * Reads all input/output/parameter structure from the process element
     * and try to assign them to the given DataProcess
     * @param processElement
     * @param process
     * @throws SMLException
     */
    public void readProcessIO(DOMHelper dom, Element processElement, DataProcess process) throws XMLReaderException
    {
        try
		{
			Element dataElement;
			NodeList signalList;
			int signalCount;
			
			// read inputs
			signalList = dom.getElements(processElement, "inputs/InputList/*");
			signalCount = signalList.getLength();
			for (int i=0; i<signalCount; i++)
			{
			    dataElement = (Element)signalList.item(i);
			    DataComponent inputData = utils.readComponentProperty(dom, dataElement);
			    process.addInput(inputData.getName(), inputData);
			}
			
			// read outputs
			signalList = dom.getElements(processElement, "outputs/OutputList/*");
			signalCount = signalList.getLength();
			for (int i=0; i<signalCount; i++)
			{
			    dataElement = (Element)signalList.item(i);
			    DataComponent outputData = utils.readComponentProperty(dom, dataElement);
			    process.addOutput(outputData.getName(), outputData);
			}	        
			
			// read parameters
			signalList = dom.getElements(processElement, "parameters/ParameterList/*");
			signalCount = signalList.getLength();
			for (int i=0; i<signalCount; i++)
			{
			    dataElement = (Element)signalList.item(i);
			    DataComponent paramData = utils.readComponentProperty(dom, dataElement);
			    process.addParameter(paramData.getName(), paramData);
			}
		}
		catch (Exception e)
		{
			throw new XMLReaderException("Error while parsing process I/O", e);
		}
    }


    public boolean getReadMetadata()
    {
        return readMetadata;
    }


    public void setReadMetadata(boolean readMetadata)
    {
        this.readMetadata = readMetadata;
    }


    public boolean isCreateExecutableProcess()
    {
        return createExecutableProcess;
    }


    public void setCreateExecutableProcess(boolean createExecutableProcess)
    {
        this.createExecutableProcess = createExecutableProcess;
    }
}