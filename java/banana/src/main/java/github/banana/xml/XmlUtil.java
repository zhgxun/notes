package github.banana.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class XmlUtil {

    public static void main(String[] args) {
        // 得到DOM解析器的工厂实例
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = null;
        try {
            // 从工厂实例获取DOM解析器
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Document doc = null;
        try {
            // 解析xml文档输入流, 接口会根据参数匹配到地址, 可以是文件等参数
            doc = db.parse("/Users/baidu/Desktop/22.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (doc != null) {
            printNode(doc);
        }
    }

    private static void printNode(Node n) {
        // 节点类型
        switch (n.getNodeType()) {
            // 根节点
            case Node.DOCUMENT_NODE:
                System.out.println("Document: " + n.getNodeName());
                break;
            // 元素节点
            case Node.ELEMENT_NODE:
                System.out.println("Element: " + n.getNodeName());
                break;
            // 文本节点
            case Node.TEXT_NODE:
                System.out.println("Text: " + n.getNodeName() + " = " + n.getNodeValue());
                break;
            // 属性节点
            case Node.ATTRIBUTE_NODE:
                System.out.println("Attr: " + n.getNodeName() + " = " + n.getNodeValue());
                break;
            // CDATA描述
            case Node.CDATA_SECTION_NODE:
                System.out.println("CDATA: " + n.getNodeName() + " = " + n.getNodeValue());
                break;
            // 评论内容
            case Node.COMMENT_NODE:
                System.out.println("Comment: " + n.getNodeName() + " = " + n.getNodeValue());
                break;
            // 其它节点
            default:
                System.out.println("NodeType: " + n.getNodeType() + ", NodeName: " + n.getNodeName());
        }
        // 递归解析
        for (Node child = n.getFirstChild(); child != null; child = child.getNextSibling()) {
            // 只要存在子节点, 继续执行深度解析
            printNode(child);
        }
    }
}
