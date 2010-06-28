<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template match="json">
		<xsl:copy>
			<xsl:apply-templates select="@*"/>
			<xsl:analyze-string select="." regex="//(.*?)\n|/\*(.*?)\*/|('|&quot;)(.*?)\3|(-?\d+(\.\d+)?)" flags="s">
				<xsl:matching-substring>
					<xsl:choose>
						<!-- single line comment -->
						<xsl:when test="regex-group(1)">
							<xsl:comment>
								<xsl:value-of select="regex-group(1)"/>
							</xsl:comment>
							<xsl:text>&#10;</xsl:text>
						</xsl:when>
						<!-- multi line comment -->
						<xsl:when test="regex-group(2)">
							<xsl:comment>
								<xsl:value-of select="regex-group(2)"/>
							</xsl:comment>
						</xsl:when>
						<!-- string -->
						<xsl:when test="regex-group(3)">
							<string>
								<xsl:value-of select="regex-group(4)"/>
							</string>
						</xsl:when>
						<!-- number -->
						<xsl:when test="regex-group(5)">
							<number>
								<xsl:value-of select="regex-group(5)"/>
							</number>
						</xsl:when>
						<xsl:otherwise>
							<xsl:message terminate="yes" select="'internal error'"/>
						</xsl:otherwise>
					</xsl:choose>
				</xsl:matching-substring>
				<xsl:non-matching-substring>
					<xsl:variable name="symbol-regex" select="'[:,\{\}\[\]]'"/>
					<xsl:analyze-string select="." regex="{$symbol-regex}">
						<xsl:matching-substring>
							<symbol>
								<xsl:value-of select="."/>
							</symbol>
						</xsl:matching-substring>
						<xsl:non-matching-substring>
							<xsl:if test="normalize-space()!=''">
								<xsl:message select="concat('unknown token: ', .)"/>
							</xsl:if>
						</xsl:non-matching-substring>
					</xsl:analyze-string>
				</xsl:non-matching-substring>
			</xsl:analyze-string>
		</xsl:copy>
	</xsl:template>

</xsl:stylesheet>
