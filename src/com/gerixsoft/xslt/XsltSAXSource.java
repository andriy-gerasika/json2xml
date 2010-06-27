package com.gerixsoft.xslt;

import java.io.InputStream;

import javax.xml.transform.sax.SAXSource;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.XMLReaderFactory;

public class XsltSAXSource extends SAXSource {

	public XsltSAXSource(InputStream stream, String args) throws SAXException {
		super(new XsltXMLReader(XMLReaderFactory.createXMLReader(), args), new InputSource(stream));
	}

	public XsltSAXSource(InputSource source, String args) throws SAXException {
		super(new XsltXMLReader(XMLReaderFactory.createXMLReader(), args), source);
	}
}