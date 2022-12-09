package com.ygh.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ygh.admin.entity.Article;
import com.ygh.admin.mapper.ArticleMapper;
import com.ygh.admin.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 文章 服务实现类
 * </p>
 *
 * @author YangGh
 * @since 2022-11-25
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    //分页查询文章
    @Override
    public Map<String, Object> pageArticle(long page, long limit) {
        Page<Article> pageArticle = new Page<>(page, limit);
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("gmt_create");
        baseMapper.selectPage(pageArticle, wrapper);
        long total = pageArticle.getTotal();
        List<Article> records = pageArticle.getRecords();
        long size = pageArticle.getSize();
        long pages = pageArticle.getPages();
        long current = pageArticle.getCurrent();
        boolean hasNext = pageArticle.hasNext();//是否有下一页
        boolean hasPrevious = pageArticle.hasPrevious();//是否有下一页
        HashMap<String, Object> map = new HashMap<>();
        map.put("size", size);
        map.put("pages", pages);
        map.put("current", current);
        map.put("total", total);
        map.put("rows", records);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);
        return map;
    }
}
