package com.liyuan.demo.form.product;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel(description = "商品表")
public class ProductUpdateForm implements Serializable {

	@ApiModelProperty(value = "主键", required = true)
	@NotNull(message = "主键不能为空")
	private Integer id;

	@ApiModelProperty(value = "名称")
	private String name;

	@ApiModelProperty(value = "价格")
	private Integer price;

}