package com.github.zhgxun.learn.common.xml;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * SAX工具
 */
public class SaxXml {

    private SaxXml() {
    }

    /**
     * 获取一个SAX解析器
     *
     * @return 解析器
     */
    public static SAXParser factory() {
        try {
            return SAXParserFactory.newInstance().newSAXParser();
        } catch (SAXException | ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }
}
