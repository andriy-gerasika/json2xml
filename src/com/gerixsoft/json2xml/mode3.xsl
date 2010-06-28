<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:import href="identity.xsl"/>

	<xsl:template match="object/symbol[.=','][preceding-sibling::*[1]/self::field and following-sibling::*[1]/self::field]"/>

	<xsl:template match="array/symbol[.=','][preceding-sibling::*[1]/self::object and following-sibling::*[1]/self::object]"/>
	
</xsl:stylesheet>
