package com.liyuan.demo.vo.product;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "商品表")
public class ProductVo implements Serializable {

	@ApiModelProperty(value = "主键", required = true)
	private Long id;

	@ApiModelProperty(value = "名称")
	private String name;

	@ApiModelProperty(value = "价格")
	private Integer price;

}