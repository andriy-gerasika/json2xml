<?xml version="1.0" encoding="utf-8"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:output method="xml" omit-xml-declaration="no" indent="yes" encoding="utf-8"/>

	<xsl:include href="mode0.xsl"/>
	<xsl:include href="mode1.xsl"/>
	<xsl:include href="mode2.xsl"/>
	<xsl:include href="mode3.xsl"/>

	<xsl:template match="/">
		<xsl:variable name="mode0">
			<xsl:apply-templates mode="mode0" select="."/>
		</xsl:variable>
		<xsl:variable name="mode1">
			<xsl:apply-templates mode="mode1" select="$mode0"/>
		</xsl:variable>
		<xsl:variable name="mode2">
			<xsl:apply-templates mode="mode2" select="$mode1"/>
		</xsl:variable>
		<xsl:variable name="mode3">
			<xsl:apply-templates mode="mode3" select="$mode2"/>
		</xsl:variable>

		<xsl:copy-of select="$mode3"/>
	</xsl:template>

</xsl:stylesheet>