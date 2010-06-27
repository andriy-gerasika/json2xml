package com.gerixsoft.xslt;

import java.util.HashMap;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

import com.gerixsoft.utils.DelegatingContentHandler;

class XsltContentHandler extends DelegatingContentHandler {

	private String args;
	private String mode;

	XsltContentHandler(ContentHandler handler, String args) {
		super(handler);
		this.args = args;
		if (args != null) {
			Map<String, String> argz = new HashMap<String, String>();
			for (String arg : args.split("&")) {
				int j = arg.indexOf("=");
				argz.put(arg.substring(0, j), arg.substring(j + 1));
			}

			mode = argz.get("mode");
		}
	}

	public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
		if (uri.equals("http://www.w3.org/1999/XSL/Transform")) {
			if (localName.equals("template") && atts.getValue("", "match") != null || localName.equals("apply-templates")) {
				String mode = atts.getValue("", "mode");
				if (mode == null && this.mode != null) {
					AttributesImpl atts2 = new AttributesImpl(atts);
					atts2.addAttribute("", "mode", "mode", "CDATA", this.mode);
					atts = atts2;
				}
			} else if (localName.equals("include") || localName.equals("import")) {
				String href = atts.getValue("", "href");
				if (href != null && args != null) {
					if (href.contains("?")) {
						href = href + "&" + args;
					} else {
						href = href + "?" + args;
					}
					AttributesImpl atts2 = new AttributesImpl(atts);
					atts2.setAttribute(atts.getIndex("", "href"), "", "href", "href", "CDATA", href);
					atts = atts2;
				}
			}
		}
		super.startElement(uri, localName, qName, atts);
	}
}