package com.purplelight.mcm.dao;

import java.util.List;

import com.purplelight.mcm.entity.Article;

public interface IArticleDao {
	public List<Article> getAll();
	public Article getById(int id);
	public List<Article> getByArticleType(String articleType);
	public void add(Article article);
	public void update(Article article) throws Exception;
	public void delete(Article article);
}
