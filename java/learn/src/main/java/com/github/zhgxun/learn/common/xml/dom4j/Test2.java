package com.github.zhgxun.learn.common.xml.dom4j;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

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
            Object Object = json.get(nodeName);
            JSONArray array;
            if (Object instanceof JSONArray) {
                array = (JSONArray) Object;
            } else {
                array = new JSONArray();
                array.add(Object);
            }
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

        System.out.println(xml2Json("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<urlset>\n" +
                "  <url>\n" +
                "    <loc>http://detail.zol.com.cn/series/3/17922_1.html</loc>  \n" +
                "    <lastmod>2019-01-16</lastmod>  \n" +
                "    <changefreq>always</changefreq>  \n" +
                "    <priority>1.0</priority>\n" +
                "    <data> \n" +
                "      <display> \n" +
                "        <title>FURY 报价 参数 图片 评论 ZOL中关村在线</title>  \n" +
                "        <brand>金士顿</brand>  \n" +
                "        <brandOther>kingston</brandOther>  \n" +
                "        <serie>FURY</serie>  \n" +
                "        <serieOther>fury系列</serieOther>  \n" +
                "        <category>内存</category>  \n" +
                "        <imgSrc>http://2b.zol-img.com.cn/product/138_120x90/627/ce5aNu1nFSx3o.jpg</imgSrc>  \n" +
                "        <imgUrl>http://detail.zol.com.cn/series/3/17922_1.html</imgUrl>  \n" +
                "        <price>￥299-3199</price>  \n" +
                "        <param> \n" +
                "          <key>适用类型</key>  \n" +
                "          <value>台式机</value> \n" +
                "        </param>  \n" +
                "        <param> \n" +
                "          <key>内存容量</key>  \n" +
                "          <value>8GB</value> \n" +
                "        </param>  \n" +
                "        <param> \n" +
                "          <key>容量描述</key>  \n" +
                "          <value>单条(8GB)</value> \n" +
                "        </param>  \n" +
                "        <param> \n" +
                "          <key>内存类型</key>  \n" +
                "          <value>DDR3</value> \n" +
                "        </param>  \n" +
                "        <link> \n" +
                "          <text>购买</text>  \n" +
                "          <url>http://detail.zol.com.cn/386/385848/zplus.shtml</url> \n" +
                "        </link>  \n" +
                "        <link> \n" +
                "          <text>详细参数</text>  \n" +
                "          <url>http://detail.zol.com.cn/series/3/236/param_17922_0_1.html</url> \n" +
                "        </link>  \n" +
                "        <link> \n" +
                "          <text>点评</text>  \n" +
                "          <url>http://detail.zol.com.cn/series/3/236/review_17922.html</url> \n" +
                "        </link>  \n" +
                "        <link> \n" +
                "          <text>问答</text>  \n" +
                "          <url>http://ask.zol.com.cn/product_385848.html</url> \n" +
                "        </link>  \n" +
                "        <link> \n" +
                "          <text>二手</text>  \n" +
                "          <url>http://detail.zol.com.cn/series/3/236/ershou_17922.html</url> \n" +
                "        </link>  \n" +
                "        <link> \n" +
                "          <text>视频</text>  \n" +
                "          <url>http://detail.zol.com.cn/series/3/236/video_17922.html</url> \n" +
                "        </link>  \n" +
                "        <link> \n" +
                "          <text>配件</text>  \n" +
                "          <url>http://detail.zol.com.cn/series/3/236/fitting_17922.html</url> \n" +
                "        </link>  \n" +
                "        <hotModel> \n" +
                "          <head>型号</head>  \n" +
                "          <head>适用类型</head>  \n" +
                "          <head>价格</head>  \n" +
                "          <row> \n" +
                "            <column> \n" +
                "              <text>金士顿 骇客神条FURY 8GB DDR4 2400 HX424C15FB/8</text>  \n" +
                "              <url>http://detail.zol.com.cn/memory/index1115558.shtml</url> \n" +
                "            </column>  \n" +
                "            <column> \n" +
                "              <text>台式机</text> \n" +
                "            </column>  \n" +
                "            <column> \n" +
                "              <text>￥469</text> \n" +
                "            </column> \n" +
                "          </row>  \n" +
                "          <row> \n" +
                "            <column> \n" +
                "              <text>金士顿 骇客神条FURY 16GB DDR4 2400 HX424C15FB/16</text>  \n" +
                "              <url>http://detail.zol.com.cn/memory/index1149796.shtml</url> \n" +
                "            </column>  \n" +
                "            <column> \n" +
                "              <text>台式机</text> \n" +
                "            </column>  \n" +
                "            <column> \n" +
                "              <text>￥999</text> \n" +
                "            </column> \n" +
                "          </row>  \n" +
                "          <row> \n" +
                "            <column> \n" +
                "              <text>金士顿 骇客神条FURY 8GB DDR3 1866</text>  \n" +
                "              <url>http://detail.zol.com.cn/memory/index385848.shtml</url> \n" +
                "            </column>  \n" +
                "            <column> \n" +
                "              <text>台式机</text> \n" +
                "            </column>  \n" +
                "            <column> \n" +
                "              <text>￥499</text> \n" +
                "            </column> \n" +
                "          </row>  \n" +
                "          <more> \n" +
                "            <text>查看FURY全部产品</text>  \n" +
                "            <url>http://detail.zol.com.cn/series/3/17922_1.html</url> \n" +
                "          </more> \n" +
                "        </hotModel>  \n" +
                "        <publicPraise> \n" +
                "          <score>4.8</score>  \n" +
                "          <newScore>9.7</newScore>  \n" +
                "          <count>158</count>  \n" +
                "          <advantage> \n" +
                "            <text>性价比高</text>  \n" +
                "            <count>1</count> \n" +
                "          </advantage>  \n" +
                "          <disAdvantage> \n" +
                "            <text>暂无</text>  \n" +
                "            <count>0</count> \n" +
                "          </disAdvantage>  \n" +
                "          <good> \n" +
                "            <count>153</count>  \n" +
                "            <url>http://detail.zol.com.cn/386/385848/review.shtml#r3</url> \n" +
                "          </good>  \n" +
                "          <medium> \n" +
                "            <count>3</count>  \n" +
                "            <url>http://detail.zol.com.cn/386/385848/review.shtml#r4</url> \n" +
                "          </medium>  \n" +
                "          <bad> \n" +
                "            <count>2</count>  \n" +
                "            <url>http://detail.zol.com.cn/386/385848/review.shtml#r5</url> \n" +
                "          </bad>  \n" +
                "          <more> \n" +
                "            <text>查看FURY更多口碑评论</text>  \n" +
                "            <url>http://detail.zol.com.cn/386/385848/review.shtml</url> \n" +
                "          </more> \n" +
                "        </publicPraise>  \n" +
                "        <competingProducts> \n" +
                "          <row> \n" +
                "            <linkText>影驰GAMER 8GB DDR4 2400</linkText>  \n" +
                "            <linkUrl>http://detail.zol.com.cn/memory/index1147009.shtml</linkUrl>  \n" +
                "            <price>￥459</price>  \n" +
                "            <compareUrl>http://detail.zol.com.cn/pk/385848_1147009.shtml</compareUrl> \n" +
                "          </row>  \n" +
                "          <row> \n" +
                "            <linkText>金士顿8GB DDR3 1600 KVR1</linkText>  \n" +
                "            <linkUrl>http://detail.zol.com.cn/memory/index328918.shtml</linkUrl>  \n" +
                "            <price>￥439</price>  \n" +
                "            <compareUrl>http://detail.zol.com.cn/pk/328918_385848.shtml</compareUrl> \n" +
                "          </row>  \n" +
                "          <row> \n" +
                "            <linkText>影驰GAMER Ⅱ 8GB DDR4 24</linkText>  \n" +
                "            <linkUrl>http://detail.zol.com.cn/memory/index1207114.shtml</linkUrl>  \n" +
                "            <price>￥475</price>  \n" +
                "            <compareUrl>http://detail.zol.com.cn/pk/385848_1207114.shtml</compareUrl> \n" +
                "          </row>  \n" +
                "          <more> \n" +
                "            <text>查看FURY更多产品</text>  \n" +
                "            <url>http://detail.zol.com.cn/series/3/17922_1.html</url> \n" +
                "          </more> \n" +
                "        </competingProducts>  \n" +
                "        <showUrl>detail.zol.com.cn</showUrl>\n" +
                "      </display>\n" +
                "    </data>\n" +
                "  </url> \n" +
                "</urlset>"));
    }
}
