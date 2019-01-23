package com.github.zhgxun.learn.common.xml.dom4j;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.File;
import java.util.List;

public class Test2 {

    public static JSONObject xml2Json(String xml) throws DocumentException {
        JSONObject jsonObject = new JSONObject();
        Document document = DocumentHelper.parseText(xml);
        Element root = document.getRootElement();
        iterateNodes(root, jsonObject);
        return jsonObject;
    }

    /**
     * 递归组合子节点
     *
     * @param node 当前节点
     * @param json 组合后的json对象
     */
    private static void iterateNodes(Element node, JSONObject json) {
        // 获取当前元素的名称
        String nodeName = node.getName();
        // 判断已遍历的JSON中是否已经有了该元素的名称
        // 该处本不想用 fastjson 库, 但是看还只要 fastjson 库 JSONObject 支持查找当前对象中是否包含某元素
        // 该元素在同级下有多个
        if (json.containsKey(nodeName)) {
            Object object = json.get(nodeName);

            // 既然是相同的元素, 存储为 JsonArray
            // 保存已有元素
            JSONArray array;
            if (object instanceof JSONArray) {
                array = (JSONArray) object;
            } else {
                array = new JSONArray();
                array.add(object);
            }
            // 添加新元素
            // 获取该元素下所有子元素
            List<Element> listElement = node.elements();
            if (listElement.isEmpty()) {
                // 该元素无子元素, 获取元素的值
                String nodeValue = node.getTextTrim();
                array.add(nodeValue);
                json.put(nodeName, array);
                return;
            }
            // 有子元素, 遍历所有子元素
            JSONObject newJson = new JSONObject();
            for (Element e : listElement) {
                iterateNodes(e, newJson);
            }
            array.add(newJson);
            json.put(nodeName, array);
            return;
        }

        // 该元素同级下第一次遍历, 获取该元素下所有子元素
        List<Element> listElement = node.elements();
        // 子元素为空, 最小维度节点
        if (listElement.isEmpty()) {
            String nodeValue = node.getTextTrim();
            json.put(nodeName, nodeValue);
            return;
        }
        // 有子节点, 新建一个 JSONObject 来存储该节点下子节点的值
        JSONObject object = new JSONObject();
        for (Element e : listElement) {
            iterateNodes(e, object);
        }
        json.put(nodeName, object);
    }

    public static void main(String[] args) throws Exception {
        String content = FileUtils.readFileToString(new File("/Users/zhgxun/Desktop/test.xml"), "UTF-8");
        System.out.println(xml2Json(content));
    }
}
