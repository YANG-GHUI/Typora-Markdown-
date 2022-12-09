package com.ygh.admin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ygh.admin.commons.R;
import com.ygh.admin.commons.annotation.Log;
import com.ygh.admin.entity.Article;
import com.ygh.admin.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 文章 前端控制器
 * </p>
 *
 * @author YangGh
 * @since 2022-11-25
 */
@Api(tags = "文章管理接口")
@RestController
@CrossOrigin
@RequestMapping("/admin/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

//    @Cacheable(key = "'selectArticleByNew'", value = "article")
//    @ApiOperation("查询所有文章")
//    @GetMapping
//    public R getArticleByNew() {
//        QueryWrapper<Article> wrapper = new QueryWrapper<>();
//        wrapper.eq("status", 1);
//        wrapper.orderByAsc("sort").orderByDesc("gmt_create");
//        List<Article> articleList = articleService.list(wrapper);
//        return R.ok().data("list", articleList);
//    }
//
//    @Cacheable(key = "'selectArticleByLast'", value = "article")
//    @ApiOperation("查询所有文章（往期）")
//    @GetMapping("/getArticleByLast")
//    public R getArticleByLast() {
//        QueryWrapper<Article> wrapper = new QueryWrapper<>();
//        wrapper.eq("status", 1);
//        wrapper.orderByAsc("gmt_create");
//        List<Article> articleList = articleService.list(wrapper);
//        return R.ok().data("list", articleList);
//    }

    @ApiOperation("分页查询所有文章")
    @GetMapping("/pageArticle/{page}/{limit}")
    public R pageAllArticle(@ApiParam(name = "page", value = "页码")
                            @PathVariable long page,
                            @ApiParam(name = "limit", value = "每页显示记录数")
                            @PathVariable long limit) {
        Map<String, Object> map = articleService.pageArticle(page, limit);
        return R.ok().data(map);
    }

    @ApiOperation("根据id查询文章")
    @GetMapping("/getArticleById/{id}")
    public R getArticleById(@ApiParam(name = "id", value = "文章id")
                            @PathVariable("id") String id) {
        Article article = articleService.getById(id);
        return R.ok().data("article", article);
    }

    @Log("删除文章")
    @ApiOperation("根据id删除文章")
    @DeleteMapping("/{id}")
    public R removeArticle(@ApiParam(name = "id", value = "文章id")
                           @PathVariable("id") String id) {
        boolean flag = articleService.removeById(id);
        return flag ? R.ok() : R.error();
    }

    @Log("添加文章")
    @ApiOperation("添加文章")
    @PostMapping
    public R addArticle(@ApiParam(name = "article", value = "文章对象")
                        @RequestBody Article article) {
        boolean flag = articleService.save(article);
        return flag ? R.ok() : R.error();
    }

    @Log("修改文章")
    @ApiOperation("修改文章")
    @PutMapping("/updateArticle")
    public R updateArticle(@ApiParam(name = "article", value = "文章对象")
                           @RequestBody Article article) {
        boolean flag = articleService.updateById(article);
        return flag ? R.ok() : R.error();
    }
}

