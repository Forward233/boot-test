package com.boot.demo.util;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * @author: yhl
 * @DateTime: 2019/12/28 10:33
 * @Description:
 */
@Data
public class ArticleInfo {

    private int score;
    private String content;

    public static void main(String[] args) {
        String qstr = "https://office.whfat.com/UserAdminWeb/user/list?ajax=1&pageNo=1&pageSize=10";
        if (StringUtils.isNotEmpty(qstr)) {
            int k = qstr.indexOf("ajax=");
            if (k >= 0) {
                int k1 = qstr.indexOf("&", k + 1);
                if (k1 < 0)
                    k1 = qstr.length();
                if (k1 >= (k + 5)) {
                    System.out.println("----");
                }
            }
        }
    }

}
