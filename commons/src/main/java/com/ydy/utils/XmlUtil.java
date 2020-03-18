package com.ydy.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import javax.xml.bind.JAXB;

public class XmlUtil {

	public static <T> T xmlToObject(String xml, Class<T> clazz) {
		return JAXB.unmarshal(new ByteArrayInputStream(xml.getBytes()), clazz);
	}

	public static String objectToXml(Object obj) {
		OutputStream out = new ByteArrayOutputStream();
		JAXB.marshal(obj, out);
		return out.toString();
	}
}
