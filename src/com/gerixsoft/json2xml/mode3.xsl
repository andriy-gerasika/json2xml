<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template priority="-9" mode="json2xml3" match="@*|node()">
		<xsl:copy>
			<xsl:apply-templates mode="json2xml3" select="@*|node()"/>
		</xsl:copy>
	</xsl:template>

	<xsl:template mode="json2xml3" match="object/symbol[.=','][preceding-sibling::*[1]/self::field and following-sibling::*[1]/self::field]"/>

	<xsl:template mode="json2xml3" match="array/symbol[.=','][preceding-sibling::*[1]/self::object and following-sibling::*[1]/self::object]"/>

</xsl:stylesheet>
