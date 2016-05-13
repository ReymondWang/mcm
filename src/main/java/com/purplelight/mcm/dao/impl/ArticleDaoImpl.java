package com.purplelight.mcm.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import com.purplelight.mcm.dao.IArticleDao;
import com.purplelight.mcm.entity.Article;

@SuppressWarnings("unchecked")
public class ArticleDaoImpl extends BaseDaoImpl<Article, Integer> implements IArticleDao {

	@Override
	public List<Article> getByArticleType(String articleType) {
		Query query = getSession().createQuery("select a from Article a where a.articleType = :articleType");
		query.setParameter("articleType", articleType);
		
		List<Article> list = (List<Article>)query.list();
		if (list != null && list.size() > 0){
			return list;
		}
		
		return new ArrayList<Article>();
	}
}
