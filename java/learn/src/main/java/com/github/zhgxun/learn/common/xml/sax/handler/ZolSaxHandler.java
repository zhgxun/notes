package com.github.zhgxun.learn.common.xml.sax.handler;

import lombok.extern.slf4j.Slf4j;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

@Slf4j
public class ZolSaxHandler extends DefaultHandler {

    // 存储当前解析的标签内容, 父标签时存在大量子标签, 需要定位到子标签获取内容
    private String content;
    private static final String URLSET = "urlset";
    // 内容块的开始和结束位置
    private static final String URL = "url";
    private static final String LOC = "loc";
    private static final String LASTMOD = "lastmod";
    private static final String CHANGE_FREQ = "changefreq";
    private static final String PRIORITY = "priority";

    /**
     * 遍历开始
     *
     * @throws SAXException
     */
    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
    }

    /**
     * 遍历结束
     *
     * @throws SAXException
     */
    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    /**
     * 开始标签, 比如 <url>
     *
     * @param uri        未知
     * @param localName  未知
     * @param qName      标签名, 不带开始结束标志
     * @param attributes 标签的属性信息
     * @throws SAXException SAXException
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        // 一个内容块的开始标签
        if (qName.equals(URL)) {

        } else if (qName.equals(LOC)) {

        }
    }

    /**
     * 结束标签, 比如 </url>
     *
     * @param uri       未知
     * @param localName 未知
     * @param qName     标签名
     * @throws SAXException SAXException
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        switch (qName) {
            case LOC:
                log.info("{} = {}", qName, content);
                break;
            case LASTMOD:
                log.info("{} = {}", qName, content);
                break;
            case CHANGE_FREQ:
                log.info("{} = {}", qName, content);
                break;
            case PRIORITY:
                log.info("{} = {}", qName, content);
                break;
        }
    }

    /**
     * 每个标签内容
     *
     * @param ch     标签内容字符数组
     * @param start  开始位置
     * @param length 内容长度
     * @throws SAXException SAXException
     */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        content = new String(ch, start, length).trim();
    }
}
