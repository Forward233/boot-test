package com.boot.demo.util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.util.Base64Util;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * @author: yhl
 * @DateTime: 2019/12/28 9:38
 * @Description:
 */
public class ArticleUtil {

    /**
     * 新闻动态
     */
    public static final String NEWS_ARTICLE_LIST = "news_article_list";
    /**
     * 最新活动
     */
    public static final String NEW_ACTIVITY_ARTICLE_LIST = "new_activity_article_list";

    /**
     * 中业公益
     */
    public static final String PUBLIC_GOOD_ARTICLE_LIST = "public_good_article_list";

    /**
     * 中业党建
     */
    public static final String PARTY_BUILDING_ARTICLE_LIST = "party_building_article_list";


    static List<String> list1;
    static List<String> list2;
    static List<String> list3;
    static List<String> list4;
    static List<String> AllList = Lists.newArrayList();

    static Map<String, Integer> map1 = Maps.newHashMap();
    static Map<String, Integer> map2 = Maps.newHashMap();
    static Map<String, Integer> map3 = Maps.newHashMap();
    static Map<String, Integer> map4 = Maps.newHashMap();

    static {


        String s1 = "1025,\n" +
                "243,\n" +
                "241,\n";
        list1 = Splitter.on(",").omitEmptyStrings().splitToList(s1.replace("\n", ""));
        int size1 = list1.size();
        for (String s : list1) {
            map1.put(s, size1 * 3);
            size1--;
        }

        String s2 = "101,\n" +
                "100";
        list2 = Splitter.on(",").omitEmptyStrings().splitToList(s2.replace("\n", ""));
        int size2 = list2.size();
        for (String s : list2) {
            map2.put(s, size2 * 3);
            size2--;
        }


        String s3 = "245,\n" +
                "244,\n" +
                "240,\n" +
                "239,\n" +
                "824,\n";
        list3 = Splitter.on(",").omitEmptyStrings().splitToList(s3.replace("\n", ""));
        int size3 = list3.size();
        for (String s : list3) {
            map3.put(s, size3 * 3);
            size3--;
        }

        String s4 = "943,\n" +
                "933,\n" +
                "932,\n" +
                "890,\n" +
                "826,\n" +
                "824,\n" +
                "816,\n" +
                "803,\n" +
                "630,\n" +
                "544,\n";
        list4 = Splitter.on(",").omitEmptyStrings().splitToList(s4.replace("\n", ""));
        int size4 = list4.size();
        for (String s : list4) {
            map4.put(s, size4 * 3);
            size4--;
        }

//        System.out.println(map1);
//        System.out.println(map2);
//        System.out.println(map3);
//        System.out.println(map4);
        AllList.addAll(list1);
        AllList.addAll(list2);
        AllList.addAll(list3);
        AllList.addAll(list4);
    }


    public static void base64Article1() throws IOException, ParseException {

        String teString = FileUtils.readFileToString(new File("D:/2.txt"), "UTF-8");
        final List<ArticleContentInfo> articleContentInfos = JSONObject.parseArray(teString, ArticleContentInfo.class);
        articleContentInfos.addAll(getArticle2());

        articleContentInfos.sort(Comparator.comparing(ArticleContentInfo::getId));
        for (ArticleContentInfo articleContentInfo : articleContentInfos) {
//            if(articleContentInfo.getId() == 50){
//                String houseString = FileUtils.readFileToString(new File("D:/3.txt"), "UTF-8");
//                articleContentInfo.setContent(Base64Util.encode(houseString));
//                articleContentInfo.setCreateTime(articleContentInfo.getCreateTime().replace("/", "-"));
//                System.out.println("set article_" + articleContentInfo.getId() + " " + JSON.toJSONString(JSON.toJSONString(articleContentInfo)) );
//            }
            if(AllList.contains(String.valueOf(articleContentInfo.getId())) || articleContentInfo.getId() == 50){
                articleContentInfo.setContent(Base64Util.encode(articleContentInfo.getContent()));
                articleContentInfo.setCreateTime(articleContentInfo.getCreateTime().replace("/", "-"));
                System.out.println("set article_" + articleContentInfo.getId() + " " + JSON.toJSONString(JSON.toJSONString(articleContentInfo)) );
            }
        }

        List<ArticleInfo> list1 = Lists.newArrayList();
        List<ArticleInfo> list2 = Lists.newArrayList();
        List<ArticleInfo> list3 = Lists.newArrayList();
        List<ArticleInfo> list4 = Lists.newArrayList();

        for (ArticleContentInfo articleContentInfo : articleContentInfos) {
            articleContentInfo.setContent(null);
            articleContentInfo.setCreateTime(articleContentInfo.getCreateTime().replace("/", "-"));
            if (map1.containsKey(String.valueOf(articleContentInfo.getId()))) {
                final String s = "ZADD news_article_list " + map1.get(String.valueOf(articleContentInfo.getId())) + " " + JSON.toJSONString(JSON.toJSONString(articleContentInfo));
                ArticleInfo articleInfo = new ArticleInfo();
                articleInfo.setScore(map1.get(String.valueOf(articleContentInfo.getId())));
                articleInfo.setContent(s );
                list1.add(articleInfo);
            }
            if (map2.containsKey(String.valueOf(articleContentInfo.getId()))) {
                final String s = "ZADD new_activity_article_list " + map2.get(String.valueOf(articleContentInfo.getId())) + " " + JSON.toJSONString(JSON.toJSONString(articleContentInfo));
                ArticleInfo articleInfo = new ArticleInfo();
                articleInfo.setScore(map2.get(String.valueOf(articleContentInfo.getId())));
                articleInfo.setContent(s );
                list2.add(articleInfo);
            }
            if (map3.containsKey(String.valueOf(articleContentInfo.getId()))) {
                final String s = "ZADD public_good_article_list " + map3.get(String.valueOf(articleContentInfo.getId())) + " " + JSON.toJSONString(JSON.toJSONString(articleContentInfo));
                ArticleInfo articleInfo = new ArticleInfo();
                articleInfo.setScore(map3.get(String.valueOf(articleContentInfo.getId())));
                articleInfo.setContent(s );
                list3.add(articleInfo);
            }
            if (map4.containsKey(String.valueOf(articleContentInfo.getId()))) {
                final String s = "ZADD party_building_article_list " + map4.get(String.valueOf(articleContentInfo.getId())) + " " + JSON.toJSONString(JSON.toJSONString(articleContentInfo));
                ArticleInfo articleInfo = new ArticleInfo();
                articleInfo.setScore(map4.get(String.valueOf(articleContentInfo.getId())));
                articleInfo.setContent(s );
                list4.add(articleInfo);
            }
        }

        list1.sort((o1, o2) -> o2.getScore() - o1.getScore());
        list2.sort((o1, o2) -> o2.getScore() - o1.getScore());
        list3.sort((o1, o2) -> o2.getScore() - o1.getScore());
        list4.sort((o1, o2) -> o2.getScore() - o1.getScore());
        System.out.println();
        for (ArticleInfo articleInfo : list1) {
            System.out.println(articleInfo.getContent());
        }
        System.out.println();
        for (ArticleInfo articleInfo : list2) {
            System.out.println(articleInfo.getContent());
        }
        System.out.println();
        for (ArticleInfo articleInfo : list3) {
            System.out.println(articleInfo.getContent());
        }
        System.out.println();
        for (ArticleInfo articleInfo : list4) {
            System.out.println(articleInfo.getContent());
        }

    }

    public static List<ArticleContentInfo> getArticle2() throws IOException {
        String teString = FileUtils.readFileToString(new File("D:/1.txt"), "UTF-8");
        final JSONObject jsonObject = JSON.parseObject(teString);
        final JSONObject data1 = (JSONObject) jsonObject.get("data");
        final String data2 = data1.getString("data");
        return JSONObject.parseArray(data2, ArticleContentInfo.class);
    }

    public static void main(String[] args) throws IOException, ParseException {
        base64Article1();
        String houseString = FileUtils.readFileToString(new File("D:/3.txt"), "UTF-8");
        System.out.println(houseString.replace("\n", ""));
    }
}
