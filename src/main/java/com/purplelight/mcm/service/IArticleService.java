package com.purplelight.mcm.service;

import java.util.List;

import com.purplelight.mcm.entity.Article;
import com.purplelight.mcm.entity.SystemUser;

public interface IArticleService {
	public List<Article> getByArticleType(String articleType);
	public Article getById(int id);
	public void addArticle(Article article, SystemUser loginedUser);
	public void updateArticle(Article article, SystemUser loginedUser) throws Exception;
	public void deleteArticle(Article article);
}
