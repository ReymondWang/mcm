package com.purplelight.mcm.dao;

import java.util.List;

import com.purplelight.mcm.entity.Article;

public interface IArticleDao extends IBaseDao<Article, Integer> {
	public List<Article> getByArticleType(String articleType);
}
