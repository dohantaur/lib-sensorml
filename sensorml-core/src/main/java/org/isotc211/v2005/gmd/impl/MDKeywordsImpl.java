package org.isotc211.v2005.gmd.impl;

import java.util.ArrayList;
import java.util.List;
import net.opengis.OgcProperty;
import net.opengis.OgcPropertyImpl;
import org.isotc211.v2005.gco.CodeListValue;
import org.isotc211.v2005.gco.impl.AbstractObjectImpl;
import org.isotc211.v2005.gmd.CICitation;
import org.isotc211.v2005.gmd.MDKeywords;


/**
 * POJO class for XML type MD_Keywords_Type(@http://www.isotc211.org/2005/gmd).
 *
 * This is a complex type.
 */
public class MDKeywordsImpl extends AbstractObjectImpl implements MDKeywords
{
    static final long serialVersionUID = 1L;
    protected List<String> keywordList = new ArrayList<String>();
    protected CodeListValue type;
    protected OgcProperty<CICitation> thesaurusName;
    
    
    public MDKeywordsImpl()
    {
    }
    
    
    /**
     * Gets the list of keyword properties
     */
    @Override
    public List<String> getKeywordList()
    {
        return keywordList;
    }
    
    
    /**
     * Returns number of keyword properties
     */
    @Override
    public int getNumKeywords()
    {
        if (keywordList == null)
            return 0;
        return keywordList.size();
    }
    
    
    /**
     * Adds a new keyword property
     */
    @Override
    public void addKeyword(String keyword)
    {
        this.keywordList.add(keyword);
    }
    
    
    /**
     * Gets the type property
     */
    @Override
    public CodeListValue getType()
    {
        return type;
    }
    
    
    /**
     * Checks if type is set
     */
    @Override
    public boolean isSetType()
    {
        return (type != null);
    }
    
    
    /**
     * Sets the type property
     */
    @Override
    public void setType(CodeListValue type)
    {
        this.type = type;
    }
    
    
    /**
     * Gets the thesaurusName property
     */
    @Override
    public CICitation getThesaurusName()
    {
        return thesaurusName.getValue();
    }
    
    
    /**
     * Gets extra info (name, xlink, etc.) carried by the thesaurusName property
     */
    @Override
    public OgcProperty<CICitation> getThesaurusNameProperty()
    {
        if (thesaurusName == null)
            thesaurusName = new OgcPropertyImpl<CICitation>();
        return thesaurusName;
    }
    
    
    /**
     * Checks if thesaurusName is set
     */
    @Override
    public boolean isSetThesaurusName()
    {
        return (thesaurusName != null && thesaurusName.getValue() != null);
    }
    
    
    /**
     * Sets the thesaurusName property
     */
    @Override
    public void setThesaurusName(CICitation thesaurusName)
    {
        if (this.thesaurusName == null)
            this.thesaurusName = new OgcPropertyImpl<CICitation>();
        this.thesaurusName.setValue(thesaurusName);
    }
}
