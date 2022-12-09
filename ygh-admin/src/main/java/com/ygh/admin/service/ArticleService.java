package com.ygh.admin.service;

import com.ygh.admin.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 文章 服务类
 * </p>
 *
 * @author YangGh
 * @since 2022-11-25
 */
public interface ArticleService extends IService<Article> {
    Map<String, Object> pageArticle(long page, long limit);
}
