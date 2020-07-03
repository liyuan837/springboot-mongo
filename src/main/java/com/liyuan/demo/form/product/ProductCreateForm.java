package com.liyuan.demo.form.product;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "商品表")
public class ProductCreateForm implements Serializable {

	@ApiModelProperty(value = "姓名")
	private String name;

	@ApiModelProperty(value = "价格")
	private Integer price;

}