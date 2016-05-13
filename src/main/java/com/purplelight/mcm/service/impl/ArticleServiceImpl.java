package com.purplelight.mcm.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import com.purplelight.mcm.dao.IArticleDao;
import com.purplelight.mcm.entity.Article;
import com.purplelight.mcm.entity.SystemUser;
import com.purplelight.mcm.service.IArticleService;
import com.purplelight.mcm.util.UpdateUtil;

public class ArticleServiceImpl implements IArticleService {

	@Resource
	private IArticleDao articleDao;
	
	@Override
	public List<Article> getByArticleType(String articleType) {
		return articleDao.getByArticleType(articleType);
	}

	@Override
	public Article getById(int id) {
		return articleDao.getById(id);
	}

	@Override
	public void addArticle(Article article, SystemUser loginedUser) {
		article.setInputUser(loginedUser.getId());
		article.setInputTime(new Timestamp(System.currentTimeMillis()));
		article.setUpdateUser(loginedUser.getId());
		article.setUpateTime(new Timestamp(System.currentTimeMillis()));
		
		articleDao.save(article);
	}

	@Override
	public void updateArticle(Article article, SystemUser loginedUser) throws Exception {
		Article orgArt = getById(article.getId());
		orgArt = UpdateUtil.copyNotNullOrEmptyValue(orgArt, article);
		orgArt.setUpdateUser(loginedUser.getId());
		orgArt.setUpateTime(new Timestamp(System.currentTimeMillis()));
		
		articleDao.update(orgArt);
	}

	@Override
	public void deleteArticle(Article article) {
		articleDao.delete(article);
	}

}
