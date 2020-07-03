package com.liyuan.demo.form.product;

import com.liyuan.demo.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel(description = "商品表")
public class ProductQueryForm extends BaseQueryForm implements Serializable {

	@ApiModelProperty(value = "主键")
	private Integer id;

	@ApiModelProperty(value = "名称")
	private String name;

	@ApiModelProperty(value = "价格")
	private Integer price;

}