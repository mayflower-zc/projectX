package com.smarthome.util;

import com.smarthome.message.resp.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by zhengcong on 14-4-19.
 */
public class MessageUtilTest {
    //文本消息测试例子
    private String text_message_xml = "<xml>\n" +
            "  <ToUserName><![CDATA[toUser]]></ToUserName>\n" +
            "  <FromUserName><![CDATA[fromUser]]></FromUserName>\n" +
            "  <CreateTime>12345678</CreateTime>\n" +
            "  <MsgType><![CDATA[text]]></MsgType>\n" +
            "  <FuncFlag>0</FuncFlag>\n" +
            "  <Content><![CDATA[content]]></Content>\n" +
            "</xml>";

    //音乐消息测试例子
    private String music_message_xml =
            "<xml>\n" +
                    "  <ToUserName><![CDATA[toUser]]></ToUserName>\n" +
                    "  <FromUserName><![CDATA[fromUser]]></FromUserName>\n" +
                    "  <CreateTime>12345678</CreateTime>\n" +
                    "  <MsgType><![CDATA[music]]></MsgType>\n" +
                    "  <FuncFlag>0</FuncFlag>\n" +
                    "  <Music>\n" +
                    "    <Title><![CDATA[TITLE]]></Title>\n" +
                    "    <Description><![CDATA[DESCRIPTION]]></Description>\n" +
                    "    <MusicUrl><![CDATA[MUSIC_Url]]></MusicUrl>\n" +
                    "    <HQMusicUrl><![CDATA[HQ_MUSIC_Url]]></HQMusicUrl>\n" +
                    "  </Music>\n" +
                    "</xml>";
    private String news_message_xml =
            "<xml>\n" +
                    "  <ToUserName><![CDATA[toUser]]></ToUserName>\n" +
                    "  <FromUserName><![CDATA[fromUser]]></FromUserName>\n" +
                    "  <CreateTime>12345678</CreateTime>\n" +
                    "  <MsgType><![CDATA[news]]></MsgType>\n" +
                    "  <FuncFlag>1</FuncFlag>\n" +
                    "  <ArticleCount>2</ArticleCount>\n" +
                    "  <Articles>\n" +
                    "    <item>\n" +
                    "      <Title><![CDATA[title1]]></Title>\n" +
                    "      <Description><![CDATA[description1]]></Description>\n" +
                    "      <PicUrl><![CDATA[picurl]]></PicUrl>\n" +
                    "      <Url><![CDATA[url]]></Url>\n" +
                    "    </item>\n" +
                    "    <item>\n" +
                    "      <Title><![CDATA[title]]></Title>\n" +
                    "      <Description><![CDATA[description]]></Description>\n" +
                    "      <PicUrl><![CDATA[picurl]]></PicUrl>\n" +
                    "      <Url><![CDATA[url]]></Url>\n" +
                    "    </item>\n" +
                    "  </Articles>\n" +
                    "</xml>";

    @Test
    public void testTextMessageToXml() throws Exception {
        TextMessage textMessage = new TextMessage();
        textMessage.setFromUserName("fromUser");
        textMessage.setToUserName("toUser");
        textMessage.setCreateTime(12345678l);
        textMessage.setMsgType("text");
        textMessage.setContent("content");
        assertEquals(text_message_xml, MessageUtil.textMessageToXml(textMessage));
    }

    @Test
    public void testMusicMessageToXml() throws Exception {
        MusicMessage musicMessage = new MusicMessage();
        musicMessage.setToUserName("toUser");
        musicMessage.setFromUserName("fromUser");
        musicMessage.setCreateTime(12345678l);
        musicMessage.setMsgType("music");
        Music music = new Music("TITLE", "DESCRIPTION", "MUSIC_Url", "HQ_MUSIC_Url");
        musicMessage.setMusic(music);
        assertEquals(music_message_xml, MessageUtil.musicMessageToXml(musicMessage));
    }

    @Test
    public void testNewsMessageToXml() throws Exception {
        Article article1 = new Article("title1", "description1", "picurl", "url");
        Article article2 = new Article("title", "description", "picurl", "url");
        NewsMessage newsMessage = new NewsMessage();
        newsMessage.setToUserName("toUser");
        newsMessage.setFromUserName("fromUser");
        newsMessage.setCreateTime(12345678l);
        newsMessage.setMsgType("news");
        newsMessage.setArticleCount(2);
        List<Article> articleList = new ArrayList<Article>();
        articleList.add(article1);
        articleList.add(article2);
        newsMessage.setArticles(articleList);
        newsMessage.setFuncFlag(1);
        assertEquals(news_message_xml, MessageUtil.newsMessageToXml(newsMessage));
    }
}
