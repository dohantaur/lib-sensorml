package org.isotc211.v2005.gco.impl;

import org.isotc211.v2005.gco.CodeListValue;


/**
 * POJO class for XML type CodeListValue_Type(@http://www.isotc211.org/2005/gco).
 *
 */
public class CodeListValueImpl implements CodeListValue
{
    static final long serialVersionUID = 1L;
    protected String codeList = "";
    protected String codeListValue = "";
    protected String codeSpace;
    protected String value;
    
    
    public CodeListValueImpl()
    {
    }
    
    
    public CodeListValueImpl(String value)
    {
        this.value = value;
    }
    
    
    /**
     * Gets the codeList property
     */
    @Override
    public String getCodeList()
    {
        return codeList;
    }
    
    
    /**
     * Sets the codeList property
     */
    @Override
    public void setCodeList(String codeList)
    {
        this.codeList = codeList;
    }
    
    
    /**
     * Gets the codeListValue property
     */
    @Override
    public String getCodeListValue()
    {
        return codeListValue;
    }
    
    
    /**
     * Sets the codeListValue property
     */
    @Override
    public void setCodeListValue(String codeListValue)
    {
        this.codeListValue = codeListValue;
    }
    
    
    /**
     * Gets the codeSpace property
     */
    @Override
    public String getCodeSpace()
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
    public void setCodeSpace(String codeSpace)
    {
        this.codeSpace = codeSpace;
    }
    
    
    /**
     * Gets the inline value
     */
    @Override
    public String getValue()
    {
        return value;
    }
    
    
    /**
     * Sets the inline value
     */
    @Override
    public void setValue(String value)
    {
        this.value = value;
    }
}
