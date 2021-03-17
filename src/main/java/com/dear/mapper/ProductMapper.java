package com.dear.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dear.domain.Product;
import com.dear.domain.ProductVO;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductMapper extends BaseMapper<Product> {

    @Select("select id, title, content, img_url imgUrl " +
            "from product where del = 1 order by sort desc, create_time desc")
    List<ProductVO> getProductListByDel();

}