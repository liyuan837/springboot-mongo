package com.liyuan.demo.domain.po.product;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @ClassName Product
 * @Description TODO
 * @Author liyuan
 * @Date 2020/6/10 21:11
 **/
@Data
@Document(collection = "product")
public class ProductPo {
    @Id
    private Long id;
    private String name;
    private Integer price;
}
