package com.smarthome.message.resp;

import java.util.List;

/**
 * 文本消息
 * Created by zhengcong on 14-4-19.
 */
public class NewsMessage extends BaseMessage {
    //图文消息个数，限制为10条以内
    private int ArticleCount;
    //多条图文消息信息，默认第一条item为大图
    private List<Article> Articles;

    public int getArticleCount() {
        return ArticleCount;
    }

    public void setArticleCount(int articleCount) {
        this.ArticleCount = articleCount;
    }

    public List<Article> getArticles() {
        return Articles;
    }

    public void setArticles(List<Article> articles) {
        this.Articles = articles;
    }
}
