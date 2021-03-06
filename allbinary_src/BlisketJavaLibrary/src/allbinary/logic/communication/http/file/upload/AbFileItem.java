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
package allbinary.logic.communication.http.file.upload;

import abcs.logic.basic.NotImplemented;
import abcs.logic.basic.string.CommonStrings;
import abcs.logic.basic.string.StringUtil;
import abcs.logic.basic.string.StringValidationUtil;
import abcs.logic.communication.log.ForcedLogUtil;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemUtil;

/**
 *
 * @author user
 */
public class AbFileItem
    implements FileItem
{
    private final String name;
    private String fieldName;
    private final byte[] byteArray;

    public AbFileItem(String name, String fieldName, byte[] byteArray)
    {
        this.name = name;
        this.fieldName = fieldName;
        this.byteArray = byteArray;
    }

    public InputStream getInputStream() throws IOException
    {
        throw new IOException(NotImplemented.NAME);
    }

    public String getContentType()
    {
        return null;
    }

    public String getName()
    {
        return this.name;
    }

    public boolean isInMemory()
    {
        return true;
    }

    public long getSize()
    {
        return this.byteArray.length;
    }

    public byte[] get()
    {
        return this.byteArray;
    }

    public String getString(String encoding) throws UnsupportedEncodingException
    {
        return new String(this.byteArray, encoding);
    }

    public String getString()
    {
        try
        {
            return FileItemUtil.getString(byteArray);
        }
        catch(Exception e)
        {
            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.HTTPERROR))
            {
                LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, "getString", e));
            }
            return StringUtil.getInstance().EMPTY_STRING;
        }
    }

    public void write(File file) throws Exception
    {
        throw new Exception(NotImplemented.NAME);
    }

    public void delete()
    {
        ForcedLogUtil.log(NotImplemented.NAME, "delete()");
    }

    public String getFieldName()
    {
        return this.fieldName;
    }

    public void setFieldName(String name)
    {
        this.fieldName = name;
    }

    public boolean isFormField()
    {
        if(StringValidationUtil.getInstance().isEmpty(this.fieldName))
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public void setFormField(boolean state)
    {

    }

    public OutputStream getOutputStream()
        throws IOException
    {
        throw new IOException(NotImplemented.NAME);
    }
}
