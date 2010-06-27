package com.gerixsoft.xslt;

import org.xml.sax.ContentHandler;
import org.xml.sax.XMLReader;

import com.gerixsoft.utils.DelegatingXMLReader;

class XsltXMLReader extends DelegatingXMLReader {
	String args;

	XsltXMLReader(XMLReader reader, String args) {
		super(reader);
		this.args = args;
	}

	public void setContentHandler(ContentHandler handler) {
		super.setContentHandler(new XsltContentHandler(handler, args));
	}
}