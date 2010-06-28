<?xml version="1.0" encoding="utf-8"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:include href="mode0.xsl"/>
	<xsl:include href="mode1.xsl"/>
	<xsl:include href="mode2.xsl"/>
	<xsl:include href="mode3.xsl"/>

	<xsl:template match="/">
		<xsl:variable name="mode0">
			<xsl:apply-templates mode="json2xml0" select="."/>
		</xsl:variable>
		<xsl:variable name="mode1">
			<xsl:apply-templates mode="json2xml1" select="$mode0"/>
		</xsl:variable>
		<xsl:variable name="mode2">
			<xsl:apply-templates mode="json2xml2" select="$mode1"/>
		</xsl:variable>
		<xsl:variable name="mode3">
			<xsl:apply-templates mode="json2xml3" select="$mode2"/>
		</xsl:variable>
		<xsl:copy-of select="$mode3"/>
	</xsl:template>

</xsl:stylesheet>