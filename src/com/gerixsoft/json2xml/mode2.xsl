<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template priority="-9" mode="mode2" match="@*|node()">
		<xsl:copy>
			<xsl:apply-templates mode="mode2" select="@*|node()"/>
		</xsl:copy>
	</xsl:template>

	<xsl:template mode="mode2"
		match="string[following-sibling::*[1]/self::symbol[.=':']	and following-sibling::*[2]/(self::string|self::number|self::object|self::array)]"/>

	<xsl:template mode="mode2"
		match="symbol[.=':'][preceding-sibling::*[1]/self::string and following-sibling::*[1]/(self::string|self::number|self::object|self::array)]">
		<field name="{preceding-sibling::*[1]}">
			<xsl:for-each select="following-sibling::*[1]">
				<xsl:copy>
					<xsl:apply-templates mode="mode2" select="@*|node()"/>
				</xsl:copy>
			</xsl:for-each>
		</field>
	</xsl:template>

	<xsl:template mode="mode2"
		match="*[self::string|self::number|self::object|self::array][preceding-sibling::*[2]/self::string and preceding-sibling::*[1]/self::symbol[.=':']]"/>

</xsl:stylesheet>
