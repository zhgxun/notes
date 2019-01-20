package com.github.zhgxun.learn.common.xml.sax.impl;

import com.github.zhgxun.learn.common.xml.SaxXml;
import com.github.zhgxun.learn.common.xml.sax.handler.ZolSaxHandler;
import org.xml.sax.SAXException;

import javax.xml.parsers.SAXParser;
import java.io.IOException;

public class ZolSaxImpl {

    public static void main(String[] args) {
        SAXParser parser = SaxXml.factory();
        try {
            parser.parse("/Users/zhgxun/Desktop/test.xml", new ZolSaxHandler());
        } catch (SAXException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
