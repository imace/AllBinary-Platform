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
package views;

import java.util.Iterator;
import java.util.Vector;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import abcs.data.tree.dom.DomNodeHelper;
import abcs.data.tree.dom.document.DomDocumentHelper;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.data.tree.dom.DomNodeInterface;
import allbinary.logic.visual.transform.BasicTransformer;
import allbinary.logic.visual.transform.TransformInterface;
import allbinary.logic.visual.transform.data.TransformDocumentInterface;
import allbinary.logic.visual.transform.data.TransformHttpRequestDocumentFactory;
import allbinary.logic.visual.transform.info.TransformInfoHttpComposite;
import allbinary.logic.visual.transform.info.TransformInfoInterface;

public class HttpComponentView extends TransformInfoHttpComposite
    implements TransformInterface
{
    private Vector domNodeInterfaceVector;
    private TransformDocumentInterface transformDocumentInterface;

    public HttpComponentView(TransformInfoInterface transformInfoInterface)
        throws Exception
    {
        super(transformInfoInterface);

        if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
        {
            LogUtil.put(LogFactory.getInstance("View Name: " + transformInfoInterface.getName(), this, "ComponentView()"));
        }

        this.domNodeInterfaceVector = new Vector();

        this.setTransformDocumentInterface(
            TransformHttpRequestDocumentFactory.getInstance(
            this.getPageContext(), this.getWeblisketSession()));
    }

    public int NO_TYPE = 0;
    
    public int getTypeId()
    {
 	   return NO_TYPE;
    }

    public TransformDocumentInterface getTransformDocumentInterface()
    {
        return transformDocumentInterface;
    }

    public void setTransformDocumentInterface(TransformDocumentInterface transformDocumentInterface)
    {
        this.transformDocumentInterface = transformDocumentInterface;
    }

    public void addDomNodeInterface(DomNodeInterface domNodeInterface)
    {
        this.domNodeInterfaceVector.add(domNodeInterface);
    }

    public Document toXmlDoc() throws Exception
    {
        try
        {
            Iterator iter = this.domNodeInterfaceVector.iterator();
            while (iter.hasNext())
            {
                DomNodeInterface domNodeInterface = (DomNodeInterface) iter.next();
                this.transformDocumentInterface.getBaseNode().appendChild(
                    domNodeInterface.toXmlNode(this.transformDocumentInterface.getDoc()));
            }
            return this.getTransformDocumentInterface().getDoc();
        } catch (Exception e)
        {
            String error = "Failed to get Doc";
            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEWERROR))
            {
                LogUtil.put(LogFactory.getInstance(error, this, "toXmlDoc()", e));
            }
            throw e;
        }
    }

    public Document getDoc() throws Exception
    {
        Document document = this.getTransformInfoInterface().getDataDocument();

        Node node = DomNodeHelper.getFirstChildElement(document);

        if (node != null)
        {
            Node dataNode = this.getTransformDocumentInterface().getDoc().importNode(
                node, true);

            if (dataNode != null)
            {
                this.getTransformDocumentInterface().getBaseNode().appendChild(dataNode);
            }
        }

        return this.getTransformDocumentInterface().getDoc();
    }

    public String view() throws Exception
    {
        try
        {

            this.toXmlDoc();
            String success = DomDocumentHelper.toString(this.getDoc());

            String result = new BasicTransformer(
                this.getTransformInfoInterface()).translate(success);

            return result;
        } catch (Exception e)
        {
            String error = "Failed to Component view";
            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.TAGHELPERERROR))
            {
                LogUtil.put(LogFactory.getInstance(error, this, "view()", e));
            }
            throw e;
        }
    }
}
