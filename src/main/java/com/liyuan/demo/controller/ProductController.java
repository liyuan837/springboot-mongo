package com.liyuan.demo.controller;

import com.liyuan.demo.domain.exception.MongoSpringBootException;
import com.liyuan.demo.domain.po.product.ProductPo;
import com.liyuan.demo.domain.response.ResponseEntity;
import com.liyuan.demo.form.product.ProductCreateForm;
import com.liyuan.demo.form.product.ProductQueryForm;
import com.liyuan.demo.util.CopyUtil;
import com.liyuan.demo.vo.product.ProductVo;
import com.liyuan.demo.vo.user.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/product")
@Api(value = "/product", description = "商品")
public class ProductController extends BaseController {

	@Autowired
	private MongoTemplate mongoTemplate;

	@ApiOperation(value = "查询商品",notes = "查询商品",httpMethod = "GET")
	@GetMapping(value = "/query")
	public ResponseEntity<UserVo> query(@ApiParam(value = "主键", required = true)@RequestParam Integer id) throws MongoSpringBootException {
		ProductPo po = mongoTemplate.findById(id, ProductPo.class);
		ProductVo vo = CopyUtil.transfer(po, ProductVo.class);
		return getSuccessResult(vo);
	}

	@ApiOperation(value = "查询商品数量",notes = "查询商品数量",httpMethod = "POST")
	@PostMapping(value = "/queryCount")
	public ResponseEntity<Integer> queryCount(@RequestBody@Valid ProductQueryForm form) throws MongoSpringBootException {
		Criteria criteria = Criteria.where("name").is(form.getName());
		Query query = new Query(criteria);
		long count = mongoTemplate.count(query,ProductPo.class);
		return getSuccessResult(count);
	}

//	@ApiOperation(value = "查询平台用户表列表",notes = "查询平台用户表列表",httpMethod = "POST")
//	@PostMapping(value = "/queryList")
//	public ResponseEntity<PageListResponse<UserVo>> queryList(@RequestBody@Valid UserQueryForm form) throws MongoSpringBootException {
//		UserCondition condition = CopyUtil.transfer(form, UserCondition.class);
//		condition.setPageNum(0);
//		condition.setPageSize(Integer.MAX_VALUE);
//		List<UserPo> poList = userService.queryList(condition);
//		List<UserVo> voList = CopyUtil.transfer(poList, UserVo.class);
//		return getSuccessResult(voList);
//	}

//	@ApiOperation(value = "查询平台用户表列表(带分页)",notes = "查询平台用户表列表(带分页)",httpMethod = "POST")
//	@PostMapping(value = "/queryPageList")
//	public ResponseEntity<PageListResponse<UserVo>> queryPageList(@RequestBody@Valid UserQueryForm form) throws MongoSpringBootException {
//		UserCondition condition = CopyUtil.transfer(form, UserCondition.class);
//		List<UserVo> voList = new ArrayList<>();
//		int count = userService.queryCount(condition);
//		if (count > 0) {
//			List<UserPo> poList = userService.queryList(condition);
//			voList = CopyUtil.transfer(poList, UserVo.class);
//		}
//		return getSuccessResult(getPageListResponse(condition.getPageNum(),condition.getPageSize(),count,voList));
//	}

	@ApiOperation(value = "新增商品",notes = "新增商品",httpMethod = "POST")
	@PostMapping(value = "/add")
	public ResponseEntity<UserVo> add(@RequestBody@Valid ProductCreateForm form) throws MongoSpringBootException {
		ProductPo po = CopyUtil.transfer(form, ProductPo.class);
		mongoTemplate.save(po);
		UserVo vo = CopyUtil.transfer(po, UserVo.class);
		return getSuccessResult(vo);
	}

//	@ApiOperation(value = "修改平台用户表",notes = "修改平台用户表",httpMethod = "POST")
//	@PostMapping(value = "/update")
//	public ResponseEntity update(@RequestBody@Valid UserUpdateForm form) throws MongoSpringBootException {
//		Date optTime = new Date();
//		UserPo po = CopyUtil.transfer(form, UserPo.class);
//		userService.update(po);
//		return getSuccessResult();
//	}
//
//	@ApiOperation(value = "（单个）删除平台用户表",notes = "删除平台用户表",httpMethod = "POST")
//	@PostMapping(value = "/delete")
//	public ResponseEntity delete(@RequestBody@Valid UserDeleteForm form) throws MongoSpringBootException {
//		userService.delete(form.getId());
//		return getSuccessResult();
//	}

}