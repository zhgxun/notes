package github.banana.lmr;

import com.baidu.lmr.mapreduce.map.MapperBase;

public class StartMapper extends MapperBase<Object, Article, Object, Object> {
    @Override
    public void map(Object o, Article article) {
        System.out.println("Mapper Start...");
        if (article != null) {
            System.out.println("Article: " + article.toString());
        }
        System.out.println("一个 Mapper 执行完毕");
    }
}
