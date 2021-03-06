package net.opengis.sensorml.v20.impl;

import org.vast.data.AbstractSWEImpl;
import net.opengis.gml.v32.Reference;
import net.opengis.sensorml.v20.Term;


/**
 * POJO class for XML type TermType(@http://www.opengis.net/sensorml/2.0).
 *
 * This is a complex type.
 */
public class TermImpl extends AbstractSWEImpl implements Term
{
    static final long serialVersionUID = 1L;
    protected String label = "";
    protected Reference codeSpace;
    protected String value = "";
    protected String definition;
    
    
    public TermImpl()
    {
    }
    
    
    /**
     * Gets the label property
     */
    @Override
    public String getLabel()
    {
        return label;
    }
    
    
    /**
     * Sets the label property
     */
    @Override
    public void setLabel(String label)
    {
        this.label = label;
    }
    
    
    /**
     * Gets the codeSpace property
     */
    @Override
    public Reference getCodeSpace()
    {
        return codeSpace;
    }
    
    
    /**
     * Checks if codeSpace is set
     */
    @Override
    public boolean isSetCodeSpace()
    {
        return (codeSpace != null);
    }
    
    
    /**
     * Sets the codeSpace property
     */
    @Override
    public void setCodeSpace(Reference codeSpace)
    {
        this.codeSpace = codeSpace;
    }
    
    
    /**
     * Gets the value property
     */
    @Override
    public String getValue()
    {
        return value;
    }
    
    
    /**
     * Sets the value property
     */
    @Override
    public void setValue(String value)
    {
        this.value = value;
    }
    
    
    /**
     * Gets the definition property
     */
    @Override
    public String getDefinition()
    {
        return definition;
    }
    
    
    /**
     * Checks if definition is set
     */
    @Override
    public boolean isSetDefinition()
    {
        return (definition != null);
    }
    
    
    /**
     * Sets the definition property
     */
    @Override
    public void setDefinition(String definition)
    {
        this.definition = definition;
    }
}
