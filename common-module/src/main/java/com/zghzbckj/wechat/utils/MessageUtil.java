package com.zghzbckj.wechat.utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.zghzbckj.messageapi.wechat.model.MusicMessage;
import com.zghzbckj.messageapi.wechat.model.NewsMessage;
import com.zghzbckj.messageapi.wechat.model.TextMessage;
import com.zghzbckj.messageapi.wechat.utils.response.Article;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>方法: 消息工具类 </p>
 * <ul>
 * <li> @param null TODO</li>
 * <li>@return   </li>
 * <li>@author JackZhou </li>
 * <li>@date 2018/2/10 16:34  </li>
 * </ul>
 */
public class MessageUtil {
    /*返回消息类型：文本*/
    public static final String RESP_MESSAGE_TYPE_TEXT = "text";
    /*返回消息类型：音乐*/
    public static final String RESP_MESSAGE_TYPE_MUSIC = "music";
    /*返回消息类型：图文*/
    public static final String RESP_MESSAGE_TYPE_NEWS = "news";
    /*请求消息类型：文本*/
    public static final String REQ_MESSAGE_TYPE_TEXT = "text";
    /*请求消息类型：图片*/
    public static final String REQ_MESSAGE_TYPE_IMAGE = "image";
    /*请求消息类型：链接*/
    public static final String REQ_MESSAGE_TYPE_LINK = "link";
    /*请求消息类型：地理位置*/
    public static final String REQ_MESSAGE_TYPE_LOCATION = "location";
    /*请求消息类型：音频*/
    public static final String REQ_MESSAGE_TYPE_VOICE = "voice";
    /*请求消息类型：推送*/
    public static final String REQ_MESSAGE_TYPE_EVENT = "event";
    /*事件类型：subscribe(订阅)*/
    public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";
    /*事件类型：unsubscribe(取消订阅)*/
    public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";
    /*事件类型：CLICK(自定义菜单点击事件)*/
    public static final String EVENT_TYPE_CLICK = "CLICK";
    /*扫码推事件的事件推送*/
    public static final String SCANCODE_PUSH = "scancode_push";
    /*扫码推事件且弹出“消息接收中”提示框的事件推送*/
    public static final String SCANCODE_WAITMSG = "scancode_waitmsg";
    /*弹出系统拍照发图的事件推送*/
    public static final String PIC_SYSPHOTO = "pic_sysphoto";
    /*pic_photo_or_album：弹出拍照或者相册发图的事件推送*/
    public static final String PIC_PHOTO_OR_ALBUM = "pic_photo_or_album";
    /*pic_weixin：弹出微信相册发图器的事件推送*/
    public static final String PIC_WEIXIN = "pic_weixin";
    /*location_select：弹出地理位置选择器的事件推送*/
    public static final String LOCATION_SELECT = "location_select";
    /*用户已关注时的事件推送*/
    public static final String EVENT_TYPE_SCAN = "SCAN";

    /**
     * 解析微信发来的请求（XML）
     *
     * @param request
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static Map<String, String> parseXml(HttpServletRequest request) throws Exception {
        // 将解析结果存储在HashMap中
        Map<String, String> map = new HashMap<String, String>();
        // 从request中取得输入流
        InputStream inputStream = request.getInputStream();
        // 读取输入流
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        // 得到xml根元素
        Element root = document.getRootElement();
        // 得到根元素的所有子节点
        List<Element> elementList = root.elements();
        // 遍历所有子节点
        for (Element e : elementList) {
            map.put(e.getName(), e.getText());
        }
        // 释放资源
        inputStream.close();
        inputStream = null;
        return map;
    }

    /**
     * 文本消息对象转换成xml
     *
     * @param textMessage 文本消息对象
     * @return xml
     */
    public static String textMessageToXml(TextMessage textMessage) {
        xstream.alias("xml", textMessage.getClass());
        return xstream.toXML(textMessage);
    }

    /**
     * 音乐消息对象转换成xml
     *
     * @param musicMessage 音乐消息对象
     * @return xml
     */
    public static String musicMessageToXml(MusicMessage musicMessage) {
        xstream.alias("xml", musicMessage.getClass());
        return xstream.toXML(musicMessage);
    }

    /**
     * 图文消息对象转换成xml
     *
     * @param newsMessage 图文消息对象
     * @return xml
     */
    public static String newsMessageToXml(NewsMessage newsMessage) {
        xstream.alias("xml", newsMessage.getClass());
        xstream.alias("item", new Article().getClass());
        return xstream.toXML(newsMessage);
    }

    /**
     * 扩展xstream，使其支持CDATA块
     *
     * @date 2013-05-19
     */
    private static XStream xstream = new XStream(new XppDriver() {
        @Override
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {
                // 对所有xml节点的转换都增加CDATA标记
                boolean cdata = true;

                @Override
                public void startNode(String name, Class clazz) {
                    super.startNode(name, clazz);
                }

                @Override
                protected void writeText(QuickWriter writer, String text) {
                    if (cdata) {
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    } else {
                        writer.write(text);
                    }
                }
            };
        }
    });

    /**
     * 判断是否是QQ表情
     *
     * @param content
     * @return
     */
    public static boolean isQqFace(String content) {
        boolean result = false;

        // 判断QQ表情的正则表达式
        String qqfaceRegex = "/::\\)|/::~|/::B|/::\\||/:8-\\)|/::<|/::$|/::X|/::Z|/::'\\(|/::-\\||/::@|/::P|/::D|/::O|/::\\(|/::\\+|/:--b|/::Q|/::T|/:,@P|/:,@-D|/::d|/:,@o|/::g|/:\\|-\\)|/::!|/::L|/::>|/::,@|/:,@f|/::-S|/:\\?|/:,@x|/:,@@|/::8|/:,@!|/:!!!|/:xx|/:bye|/:wipe|/:dig|/:handclap|/:&-\\(|/:B-\\)|/:<@|/:@>|/::-O|/:>-\\||/:P-\\(|/::'\\||/:X-\\)|/::\\*|/:@x|/:8\\*|/:pd|/:<W>|/:beer|/:basketb|/:oo|/:coffee|/:eat|/:pig|/:rose|/:fade|/:showlove|/:heart|/:break|/:cake|/:li|/:bome|/:kn|/:footb|/:ladybug|/:shit|/:moon|/:sun|/:gift|/:hug|/:strong|/:weak|/:share|/:v|/:@\\)|/:jj|/:@@|/:bad|/:lvu|/:no|/:ok|/:love|/:<L>|/:jump|/:shake|/:<O>|/:circle|/:kotow|/:turn|/:skip|/:oY|/:#-0|/:hiphot|/:kiss|/:<&|/:&>";
        Pattern p = Pattern.compile(qqfaceRegex);
        Matcher m = p.matcher(content);
        if (m.matches()) {
            result = true;
        }
        return result;
    }

    /**
     * 将long类型的时间转换成标准格式（yyyy-MM-dd HH:mm:ss）
     *
     * @param longTime
     * @return
     */
    public static String formatTime(long longTime) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date(longTime));
    }


    /**
     * 演示Java中常用的获取long类型时间的两种方式
     */
    public static void main(String[] args) {
//        long longTime = 1373206143378L;
//        String stdFormatTime = formatTime(longTime);
//        // 输出格式：2013-07-07 22:09:03
//        System.out.println(stdFormatTime);
        //文本消息
//        Map<String, String> map = new HashMap<String, String>();
//        map.put("FromUserName", "0987655432");
//        map.put("ToUserName", "appid");
//        TextMessage txt = TextMessage.instance(map, 0, "HelloWorld");
//        System.out.println(MessageUtil.textMessageToXml(txt));

        NewsMessage newsMessage = new NewsMessage();
        newsMessage.setArticleCount(1);
        newsMessage.setMsgType("news");
        newsMessage.setFromUserName("098765543d2");
        newsMessage.setToUserName("appId");
        List<Article> articleList = new ArrayList<Article>(1);
        Article article = new Article();
        article.setDescription("测试图文");
        article.setTitle("欢迎关注");
        article.setPicUrl("http://www.baidu.com/1.jpg");
        article.setUrl("http://test.test.docm");
        articleList.add(article);
        newsMessage.setArticles(articleList);

        System.out.println(MessageUtil.newsMessageToXml(newsMessage));


    }


}
