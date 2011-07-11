<?xml version="1.0" encoding="UTF-8" ?>

<!--
AllBinary Open License Version 1
Copyright (c) 2011 AllBinary

By agreeing to this license you and any business entity you represent are
legally bound to the AllBinary Open License Version 1 legal agreement.

You may obtain the AllBinary Open License Version 1 legal agreement from
AllBinary or the root directory of AllBinary's AllBinary Platform repository.

Created By: Travis Berthelot
-->

<xsl:stylesheet version="1.0" 
   xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
   xmlns:jsp="http://java.sun.com/JSP/Page" 
   xmlns:jutil="/WEB-INF/jutil.tld"
   xmlns:admin="/WEB-INF/admin.tld"   
   xmlns:ecommerce="/WEB-INF/ecommerce.tld"   
   xmlns:generic="/WEB-INF/generic.tld"
   xmlns:payment="/WEB-INF/payment.tld"
   xmlns:transform="/WEB-INF/transform.tld"
   xmlns:transformInfoObjectConfig="/WEB-INF/transformInfoObjectConfig.tld" >

   <xsl:import href="/generic/imports/jsp/directive/include/include.xsl" />

   <xsl:template name="menu" >

<jsp:scriptlet>
/*
 *Copyright (c) 2002-2004 AllBinary.
 *License information: http://www.weblisket.com/license.html
 */
</jsp:scriptlet>

<div ID="rightsidebar" class="rightsidebar" >

<xsl:call-template name="jspDirectiveIncludeWindow" >
   <xsl:with-param name="fileName" >
      miniBasket
   </xsl:with-param>
</xsl:call-template>
</div>
<p/>

<div ID="rightsidebar" class="rightsidebar" >
<xsl:call-template name="jspDirectiveIncludeWindow" >
   <xsl:with-param name="fileName" >
      liveHelp
   </xsl:with-param>
</xsl:call-template>
</div>
<p/>

<ecommerce:authentication roles="%= roles %" command="%= GLOBALS.PROCESSBODYIFNOTAUTHENTICATED %">

<div ID="rightsidebar" class="rightsidebar" >
<xsl:call-template name="jspDirectiveIncludeWindow" >
   <xsl:with-param name="fileName" >
      microRegistration
   </xsl:with-param>
</xsl:call-template>
</div>
<p/>
</ecommerce:authentication>

<div ID="rightsidebar" class="rightsidebar" >

</div>


<div ID="rightsidebar" class="rightsidebar" >

<xsl:call-template name="jspDirectiveIncludeWindow" >
   <xsl:with-param name="fileName" >
      partnersList
   </xsl:with-param>
</xsl:call-template>

</div>

    </xsl:template>

</xsl:stylesheet> 
