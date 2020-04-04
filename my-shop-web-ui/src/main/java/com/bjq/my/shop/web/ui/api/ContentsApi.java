package com.bjq.my.shop.web.ui.api;

import com.bjq.my.shop.commons.utils.HttpClientUtils;
import com.bjq.my.shop.commons.utils.MapperUtils;
import com.bjq.my.shop.web.ui.dto.TbContent;

import java.util.List;

/**
 * 内容管理接口
 */
public class ContentsApi {

    public static List<TbContent> ppt(){
        List<TbContent> tbContents = null;
        String result = HttpClientUtils.doGet(API.API_CONTENTS_PPT);

        try {
            tbContents = MapperUtils.json2listByTree(result,"data",TbContent.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tbContents;

    }

}
