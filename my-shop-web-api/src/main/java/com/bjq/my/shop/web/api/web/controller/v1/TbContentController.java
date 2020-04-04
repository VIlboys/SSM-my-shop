package com.bjq.my.shop.web.api.web.controller.v1;

import com.bjq.my.shop.commons.dto.BaseResult;
import com.bjq.my.shop.domain.TbContent;
import com.bjq.my.shop.web.api.service.TbContentService;
import com.bjq.my.shop.web.api.web.dto.TbContentDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController //返回的是json数据
@RequestMapping(value = "${api.path.v1}/contents")
public class TbContentController {


    @Autowired
    private TbContentService tbContentService;

    @ModelAttribute
    public TbContent getTbContent(Long id){

        TbContent tbContent = null;

        if(id == null){
            tbContent = new TbContent();
        }
        return tbContent;
    }

    /**
     * 幻灯片接口
     * @return
     */
    @RequestMapping(value = "ppt",method = RequestMethod.GET)
    public BaseResult findPPT(){

        List<TbContentDTO> tbContentDTOS = null;
        List<TbContent> tbContents = tbContentService.selectByCategoryId(89L);

        if(tbContents != null && tbContents.size() > 0){
            tbContentDTOS = new ArrayList<>();
            for (TbContent tbContent : tbContents) {
                TbContentDTO dto = new TbContentDTO();
                BeanUtils.copyProperties(tbContent,dto);
                tbContentDTOS.add(dto);
            }
        }

        return BaseResult.success("成功",tbContentDTOS);
    }

}
