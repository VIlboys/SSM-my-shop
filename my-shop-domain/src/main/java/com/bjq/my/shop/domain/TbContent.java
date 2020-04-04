package com.bjq.my.shop.domain;

import com.bjq.my.shop.commons.persistence.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 内容管理
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class TbContent extends BaseEntity {

//    @NotNull(message = "父级不得为空")
//    private Long categoryId;

    @Length(min = 1,max = 20,message = "标题长度介于 1 - 20 个字符之间")
    private String title;

    @Length(min = 1,max = 20,message = "子标题长度介于 1 - 20 个字符之间")
    private String subTitle;

    @Length(min = 1,max = 50,message = "标题描述介于 1 - 50 个字符之间")
    private String titleDesc;

    private String url;
    private String pic;
    private String pic2;
    @Length(min = 1,message = "内容不得少于0")
    private String content;

    @NotNull(message = "父级不得为空")
    private TbContentCategory tbContentCategory;
}
