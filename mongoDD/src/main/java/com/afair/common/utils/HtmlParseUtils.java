package com.afair.common.utils;

import com.afair.pojo.entity.JdGoods;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author WangBing
 * @date 2020/12/25 16:02
 */
public class HtmlParseUtils {
    public static List<JdGoods> JdParse(String keyword, Integer page) throws Exception {
        //京东采用的是奇数方式存储页号
        page = 2 * page - 1;
        String url = "https://search.jd.com/Search?keyword=" + keyword + "&page=" + page;
        //js的界面对象
        Document document = Jsoup.parse(new URL(url), 30000);
        Element goodsElement = document.getElementById("J_goodsList");
        Elements goodLi = goodsElement.getElementsByTag("li");
        ArrayList<JdGoods> jdGoodsList = new ArrayList<>();
        goodLi.forEach(info -> {
            String img = info.getElementsByTag("img").get(0).attr("data-lazy-img");
            String price = info.getElementsByClass("p-price").get(0).text();
            String name = info.getElementsByClass("p-name").get(0).text();
            JdGoods jdGoods = new JdGoods();
            jdGoods.setName(name);
            jdGoods.setImg(img);
            jdGoods.setPrice(price);
            jdGoodsList.add(jdGoods);
        });
        return jdGoodsList;
    }
}
