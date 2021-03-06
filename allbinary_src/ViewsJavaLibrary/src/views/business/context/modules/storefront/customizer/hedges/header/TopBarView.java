/*
* AllBinary Open License Version 1
* Copyright (c) 2011 AllBinary
* 
* By agreeing to this license you and any business entity you represent are
* legally bound to the AllBinary Open License Version 1 legal agreement.
* 
* You may obtain the AllBinary Open License Version 1 legal agreement from
* AllBinary or the root directory of AllBinary's AllBinary Platform repository.
* 
* Created By: Travis Berthelot
* 
*/
package views.business.context.modules.storefront.customizer.hedges.header;

import abcs.logic.communication.log.LogFactory;
import org.w3c.dom.Node;
import org.w3c.dom.Document;

import abcs.logic.communication.log.LogUtil;

import allbinary.data.tree.dom.DomNodeInterface;

import allbinary.logic.visual.transform.info.TransformInfoInterface;

import allbinary.logic.visual.transform.template.customizer.hedges.heading.HeadingValidation;

import views.business.context.modules.storefront.HttpStoreComponentView;

public class TopBarView 
    extends HttpStoreComponentView
    implements DomNodeInterface
{
    protected HeadingValidation heading;

    public TopBarView(TransformInfoInterface transformInfoInterface)
        throws Exception
    {
        super(transformInfoInterface);
    }

    public void addDomNodeInterfaces()
    {
        this.addDomNodeInterface((DomNodeInterface) this);
    }

    public Node toXmlNode(Document document) throws Exception
    {
        try
        {
            return this.heading.toXmlNode(document);
        } catch (Exception e)
        {
            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                abcs.logic.communication.log.config.type.LogConfigType.XSLLOGGINGERROR))
            {
                LogUtil.put(LogFactory.getInstance("Command Failed", this, "toXmlNode", e));
            }
            throw e;
        }
    }

    public String view() throws Exception
    {
        try
        {
            this.addDomNodeInterfaces();
            return super.view();
        } catch (Exception e)
        {
            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.TAGHELPERERROR))
            {
                String error = "Failed to view TopBar";
                LogUtil.put(LogFactory.getInstance(error, this, "view()", e));
            }
            throw e;
        }
    }
}
