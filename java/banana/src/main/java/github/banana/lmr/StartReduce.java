package github.banana.lmr;

import com.baidu.lmr.mapreduce.reduce.ReducerBase;

public class StartReduce extends ReducerBase<String, Article> {
    @Override
    public void reduce(String s, Iterable<Article> iterable) {
        System.out.println("Reduce Start...");
        System.out.println("s: " + s);
        for (Article article : iterable) {
            System.out.println("article: " + article.toString());
        }
    }
}
