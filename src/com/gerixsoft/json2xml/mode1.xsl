<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:my="urn.mynamespace">

	<xsl:template mode="mode1" match="json">
		<xsl:copy>
			<xsl:copy-of select="@*"/>
			<xsl:apply-templates mode="mode1" select="node()[1]"/>
		</xsl:copy>
	</xsl:template>

	<xsl:template mode="mode1" match="node()" priority="-9">
		<xsl:copy-of select="."/>
		<xsl:apply-templates mode="mode1" select="following-sibling::node()[1]"/>
	</xsl:template>

	<xsl:template mode="mode1" match="symbol[.=('}',']')]"/>

	<xsl:function name="my:level">
		<xsl:param name="context" as="element()"/>
		<xsl:value-of select="$context/(count(preceding-sibling::symbol[.=('{','[')])-count(preceding-sibling::symbol[.=('}',']')]))"/>
	</xsl:function>

	<xsl:template mode="mode1" match="symbol[.=('{','[')]">
		<xsl:element name="{if (.='{') then 'object' else 'array'}">
			<xsl:apply-templates mode="mode1" select="following-sibling::node()[1]"/>
		</xsl:element>
		<xsl:apply-templates mode="mode1"
			select="following-sibling::symbol[.=('}',']') and my:level(.)=my:level(current())+1][1]
				/following-sibling::node()[1]"/>
	</xsl:template>

</xsl:stylesheet>