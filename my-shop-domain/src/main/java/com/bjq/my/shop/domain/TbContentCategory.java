package com.bjq.my.shop.domain;

import com.bjq.my.shop.commons.persistence.BaseEntity;
import com.bjq.my.shop.commons.persistence.BaseTreeEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

/**
 * 分类管理
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class TbContentCategory extends BaseTreeEntity {

    //private Long parentId;

    @Length(min = 1,max = 10,message = "分类名称必须在 1~10 位之间")
    private String name;
    private Integer status;
    @NotNull(message = "排序不能为空")
    private Integer sortOrder;
    private Boolean isParent;

    private TbContentCategory parent;
}
