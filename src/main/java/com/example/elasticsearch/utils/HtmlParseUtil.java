package com.example.elasticsearch.utils;

import com.example.elasticsearch.domain.Content;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HtmlParseUtil {
    public static void main(String[] args) throws Exception {
        new HtmlParseUtil().
                parseJD("nginx").
                forEach(System.out::println);
    }

    public List<Content> parseJD(String keywords) throws IOException {
        String url = "https://search.jd.com/Search?keyword=" + keywords + "&enc=utf-8";

        Document document = Jsoup.connect(url)
                .header("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36")
                .get();
        //解析网页。(Jsoup返回Document就是浏览器Document对象)
        //Document document = Jsoup.parse(new URL(url), 30000);
        //所有你在js中可以使用的方法，这里都能用！
        Element element = document.getElementById("J_goodsList");
        System.out.println(document.toString());
        // 获取所有的li元素
        Elements  li= element.getElementsByTag("li");
        //Elements表示一个由li组成的数组
        //获取元素中的内容,这里的el就是 每一个li标签了

        ArrayList<Content> goodsList = new ArrayList<>();

        for (Element el : li) {
            Content content = new Content(
                    el.getElementsByClass("p-name").eq(0).text(),
                    el.getElementsByClass("p-price").eq(0).text(),
                    el.getElementsByTag("img").eq(0).attr("data-lazy-img")
            );
            goodsList.add(content);
        }
        return goodsList;
    }
}