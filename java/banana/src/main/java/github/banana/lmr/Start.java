package github.banana.lmr;

import com.baidu.lmr.mapreduce.Instance;
import com.baidu.lmr.mapreduce.common.InputEntry;
import com.baidu.lmr.mapreduce.common.InputEntryUtils;

import java.util.ArrayList;
import java.util.List;

public class Start {

    public static void main(String[] args) {
        List<Article> articles = new ArrayList<>();

        // 构造数据
        for (int i = 0; i < 100; i++) {
            Article article = new Article();
            article.setId(i);
            article.setTitle("标题: " + i);
            article.setContent("内容: " + i);
            articles.add(article);
        }

        // 构造输入列表
        List<InputEntry<Object, Article>> inputEntryList = InputEntryUtils.convertList(articles);
        Instance instance = new Instance();
        instance.setMaxMapperNumber(1);
        instance.setMapperClass(StartMapper.class);
//        instance.setMaxReducerNumber(2);
//        instance.setReducerClass(StartReduce.class);
        instance.start(inputEntryList);
    }
}
