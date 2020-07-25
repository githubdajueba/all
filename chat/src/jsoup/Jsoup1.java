package jsoup;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Jsoup1 {
    @Test
    public void test1() throws Exception {
        /*
         * 1.建立连接
         * 2.执行(发送请求, 接收响应)
         * 3.获取响应数据的协议体部分(html代码)
         */
        String html = Jsoup.connect("http://www.baidu.com").execute().body();
        System.out.println(html);
    }

    @Test
    public void test2() throws Exception {
        /*
         * html代码,会被解析,处理成一个树结构(DOM树)
         *
         *       /          根节点 Document
         *        |- <html>
         *            |- <head>
         *            |- <body>         元素节点  Element
         *               |- <a>
         *               |- <div>
         *               |- <div>
         *                  |- <div>
         *                  |- <div>
         *                     |- <a>
         *                     |- <a>
         *                     |- <a>
         *                     |- <a>
         *                        |- href       属性节点  Attribute
         *                        |- 文本                         文本节点  Text
         *                  |- <div>
         *               |- <div>
         *               |- <div>
         *               |- <div>
         */
        Document doc = Jsoup.connect("http://www.tedu.cn").get();//DOM树的根节点
        Elements list = doc.select("a");//用css选择器,选择所有的<a>元素
        for (Element e : list) {//遍历这些<a>元素
            String link = e.attr("href"); //取出href属性值<a href="xxxx">
            String text = e.text(); //取出a元素中的文本<a href="xxx">文本内容</a>
            System.out.println(text+" - "+link);
        }
    }


    @Test
    public void test3() throws Exception {
        String url = "https://item.jd.com/100005375193.html";

        String html = Jsoup.connect(url).execute().body();
        System.out.println(html);
    }

    @Test
    public void test4() throws Exception {
        String url = "https://item.jd.com/100005375193.html";

        String title = getTitle(url);
        System.out.println(title);
    }

    private String getTitle(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        Element e = doc.selectFirst("div.sku-name");
        String title = e.text();
        return title;
    }

    @Test
    public void test5() throws Exception {
        String url = "https://item.jd.com/100005375193.html";
        Document doc = Jsoup.connect(url).get();
        String p = doc.selectFirst("span.price").text();
        System.out.println("---"+p+"---");
    }

    @Test
    public void test6() throws Exception {
        String id = "100005375193";
        double price = getPrice(id);
        System.out.println(price);
    }

    private double getPrice(String id) throws Exception {
        String url = "https://p.3.cn/prices/mgets?skuIds="+id;
        String userAgent = "Mozilla/5.0 (Windows NT 5.1; zh-CN) AppleWebKit/535.12 (KHTML, like Gecko) Chrome/22.0.1229.79 Safari/535.12";

        String s =
                Jsoup.connect(url)
                        .ignoreContentType(true)//忽略内容类型,获取的数据不是html,让jsoup不要把数据当做html进行处理
                        .userAgent(userAgent)//欺骗服务器,我们是浏览器,而不是一段爬虫代码
                        .execute()
                        .body();
        //System.out.println(s);
        // Jackson API - 处理Json格式数据的开源API
        ObjectMapper om = new ObjectMapper();
        /*
         * [{"cbf":"0","id":"J_100005375193","m":"468.00","op":"168.00","p":"128.00"}]
         *
         * JsonNode 把Json数据,解析成一个树结构
         *
         *      /
         *       |- 数组
         *          |- 0
         *             |- 对象
         *                |- cbf
         *                |- id
         *                |- m
         *                |- p
         *          |- 1
         *          |- 2
         *          |- 3
         *          |- 4
         */
        JsonNode t = om.readTree(s);
        double price = t.get(0).get("p").asDouble();


        return price;
    }

    @Test
    public void test7() throws Exception {
        String desc = getDesc("100005375193");
        System.out.println(desc);
    }

    private String getDesc(String id) throws Exception {
        String url = "http://d.3.cn/desc/"+id;
        String userAgent = "Mozilla/5.0 (Windows NT 5.1; zh-CN) AppleWebKit/535.12 (KHTML, like Gecko) Chrome/22.0.1229.79 Safari/535.12";

        String s =
                Jsoup.connect(url)
                        .ignoreContentType(true)//忽略内容类型,获取的数据不是html,让jsoup不要把数据当做html进行处理
                        .userAgent(userAgent)//欺骗服务器,我们是浏览器,而不是一段爬虫代码
                        .execute()
                        .body();

        // showdesc({....}) ---> {....}
        s = s.substring(9, s.length()-1);

        ObjectMapper om = new ObjectMapper();
        JsonNode t = om.readTree(s);
        // {"date":"23452345",  "content":"商品详情"}
        String desc = t.get("content").asText();
        return desc;
    }


    @Test
    public void test8() throws Exception {
        //获取所有的分类地址
        List<String> list = getAllSort();
        for (String s : list) {
            System.out.println(s);
        }
        System.out.println(list.size());
    }

    private List<String> getAllSort() throws Exception {
        ArrayList<String> list = new ArrayList<String>();
        //这个地址包含所有分类的链接
        String url = "https://www.jd.com/allSort.aspx";
        Document doc = Jsoup.connect(url).get();
        Elements es = doc.select("dl.clearfix a");//取出所有链接
        for (Element e : es) {
            String link = e.attr("href");//遍历获取每一个链接的地址

            //只处理正常的分类, 忽略频道和一些专题页面
            if (link.startsWith("//list.jd.com")) {
                list.add("http:"+link);
            }
        }

        return list;
    }

    @Test
    public void test9() throws Exception {
        String url = "http://list.jd.com/list.html?cat=6233,6253,6263";
        int max = getMaxPage(url);
        System.out.println(max);
    }

    private int getMaxPage(String url) throws Exception {
        Document doc = Jsoup.connect(url).get();
        Element e = doc.selectFirst("#J_topPage i");
        String text = e.text();
        return Integer.parseInt(text);
    }

    @Test
    public void test10() throws Exception {
        String url = "http://list.jd.com/list.html?cat=6233,6253,6263";
        List<String> list = getAllPage(url);
        for (String s : list) {
            System.out.println(s);
        }
    }

    private List<String> getAllPage(String url) throws Exception {
        ArrayList<String> list = new ArrayList<String>();
        int max = getMaxPage(url);
        for (int i = 1; i <= max; i++) {
            list.add(url+"&page="+i);
        }
        return list;
    }

    @Test
    public void test11() throws Exception {
        String url =
                "https://list.jd.com/list.html?cat=6233,6253,6263&page=216";
        List<String> list = getItemList(url);
        for (String s : list) {
            System.out.println(s);
        }
    }

    private List<String> getItemList(String url) throws Exception {
        ArrayList<String> list = new ArrayList<String>();
        Document doc = Jsoup.connect(url).get();
        Elements es = doc.select("li.gl-item div.p-name a");
        for (Element e : es) {
            String s = e.attr("href");
            list.add("http:"+s);
        }
        return list;
    }

    @Test
    public void test12() throws Exception {
        //所有分类的地址
        List<String> list = getAllSort();
        for (String url : list) {
            //处理这个分类
            handleSort(url);
        }
    }

    private void handleSort(String url) throws Exception {
        //得到这个分类所有页面的地址
        List<String> list = getAllPage(url);
        for (String s : list) {
            //处理这一页
            handlePage(s);
        }
    }

    private void handlePage(String url) throws Exception {
        //这一页中所有的商品
        List<String> list = getItemList(url);
        for (String s : list) {
            try {
                //获取这个商品的标题,价格,详情
                String title = getTitle(s);
                //https://item.jd.com/10972906777.html --> 10972906777
                String id =
                        s.substring(s.lastIndexOf("/")+1, s.lastIndexOf("."));
                double price = getPrice(id);
                System.out.println(title+" - "+price);
            } catch (Exception e) {
            }
        }
    }
}
