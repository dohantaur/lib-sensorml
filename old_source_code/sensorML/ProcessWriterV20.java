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
 University of Alabama in Huntsville (UAH). <http://vast.uah.edu>
 Portions created by the Initial Developer are Copyright (C) 2007
 the Initial Developer. All Rights Reserved.

 Please Contact Alexandre Robin <alex.robin@sensiasoftware.com> or
 Mike Botts <mike.botts@uah.edu> for more information.
 
 Contributor(s): 
    Alexandre Robin <alex.robin@sensiasoftware.com>
 
******************************* END LICENSE BLOCK ***************************/

package org.vast.sensorML;

import java.text.NumberFormat;
import org.vast.cdm.common.DataComponent;
import org.vast.ogc.OGCRegistry;
import org.vast.ogc.gml.GMLFeatureWriter;
import org.vast.ogc.gml.GMLTimeWriter;
import org.vast.ogc.gml.GMLUtils;
import org.vast.sensorML.metadata.Metadata;
import org.vast.sensorML.metadata.MetadataWriterV20;
import org.vast.sweCommon.SWECommonUtils;
import org.vast.sweCommon.SweComponentWriterV20;
import org.vast.xml.DOMHelper;
import org.vast.xml.IXMLWriterDOM;
import org.vast.xml.XMLWriterException;
import org.w3c.dom.Element;


/**
 * <p>
 * Process Writer for SensorML version 2.0
 * </p>
 *
 * <p>Copyright (c) 2014</p>
 * @author Alexandre Robin
 * @since Feb 6, 2014
 */
public class ProcessWriterV20 implements IXMLWriterDOM<SMLProcess>
{
    private static String GML_VERSION = "3.2";
    protected GMLTimeWriter timeWriter;
    protected GMLFeatureWriter featureWriter;
    protected MetadataWriterV20 metadataWriter;
    protected SweComponentWriterV20 sweWriter;
    protected int currentId;
    protected NumberFormat idFormatter;
    
    
    public ProcessWriterV20()
    {
        timeWriter = new GMLTimeWriter(GML_VERSION);
        featureWriter = new GMLFeatureWriter();
        featureWriter.setGmlVersion(GML_VERSION);
        metadataWriter = new MetadataWriterV20();
        sweWriter = new SweComponentWriterV20();
        
        currentId = 1;
        idFormatter = NumberFormat.getNumberInstance();
        idFormatter.setMinimumIntegerDigits(3);
        idFormatter.setGroupingUsed(false);
    }
    
    
    public Element write(DOMHelper dom, SMLProcess smlProcess) throws XMLWriterException
    {
        Element procElt;
        dom.addUserPrefix("sml", OGCRegistry.getNamespaceURI(SMLUtils.SENSORML, "2.0"));
        
        if (smlProcess instanceof SMLProcessChain)
            procElt = dom.createElement("sml:AggregateProcess");
        else
            procElt = dom.createElement("sml:SimpleProcess");
        
        write(dom, procElt, smlProcess);        
        return procElt;
    }
    
    
    protected void write(DOMHelper dom, Element procElt, SMLProcess smlProcess) throws XMLWriterException
    {
        dom.addUserPrefix("swe", OGCRegistry.getNamespaceURI(SWECommonUtils.SWE, "2.0"));
        dom.addUserPrefix("gml", OGCRegistry.getNamespaceURI(GMLUtils.GML, "3.2"));
        dom.addUserPrefix("xlink", OGCRegistry.getNamespaceURI(OGCRegistry.XLINK));
        
        if (smlProcess.getDescription() != null)
            dom.setElementValue(procElt, "gml:description", smlProcess.getDescription());
        
        dom.setElementValue(procElt, "gml:identifier", smlProcess.getIdentifier());
        
        if (smlProcess.getName() != null)
            dom.setElementValue(procElt, "gml:name", smlProcess.getName());
        
        // metadata
        Metadata metadata = smlProcess.getMetadata();
        if (metadata != null)
            metadataWriter.writeMetadata(dom, procElt, metadata);
        
        // IOs
        writeIOList(dom, procElt, "sml:inputs/sml:InputList", "sml:input", smlProcess.getInputList());
        writeIOList(dom, procElt, "sml:outputs/sml:OutputList", "sml:output", smlProcess.getOutputList());
        writeIOList(dom, procElt, "sml:parameters/sml:ParameterList", "sml:parameter", smlProcess.getParameterList());
    }
    
    
    protected void writeIOList(DOMHelper dom, Element parentElt, String listPath, String itemName, DataComponent ioList) throws XMLWriterException
    {
        if (ioList == null)
            return;
        
        int numComponents = ioList.getComponentCount();
        if (ioList != null &&  numComponents > 0)
        {
            Element ioListElt = dom.addElement(parentElt, listPath);            
            for (int i = 0; i < numComponents; i++)
            {
                Element propElt = sweWriter.addComponentProperty(dom, ioListElt, "+"+itemName, ioList.getComponent(i), true);
                propElt.setAttribute("name", ioList.getComponent(i).getName());
            }
        }
    }
}
