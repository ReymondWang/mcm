package com.purplelight.mcm.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;

import com.purplelight.mcm.dao.IArticleDao;
import com.purplelight.mcm.entity.Article;

public class ArticleDaoImpl extends BaseDaoImpl implements IArticleDao {

	@Override
	public List<Article> getAll() {
		@SuppressWarnings("unchecked")
		List<Article> retList = (List<Article>)getSession().createQuery("select a from Article a").list();
		if (retList != null && retList.size() > 0){
			return retList;
		}
		
		return new ArrayList<Article>();
	}

	@Override
	public Article getById(int id) {
		Query query = getSession().createQuery("select a from Article a where a.id = :id");
		query.setParameter("id", id);
		
		@SuppressWarnings("unchecked")
		Iterator<Article> it = (Iterator<Article>)query.iterate();
		if (it.hasNext()){
			return it.next();
		}
		
		return null;
	}

	@Override
	public List<Article> getByArticleType(String articleType) {
		Query query = getSession().createQuery("select a from Article a where a.articleType = :articleType");
		query.setParameter("articleType", articleType);
		
		@SuppressWarnings("unchecked")
		List<Article> list = (List<Article>)query.list();
		if (list != null && list.size() > 0){
			return list;
		}
		
		return new ArrayList<Article>();
	}

	@Override
	public void add(Article article) {
		getSession().save(article);
	}

	@Override
	public void update(Article article) {
		getSession().update(article);
	}

	@Override
	public void delete(Article article) {
		getSession().delete(article);
	}

}
