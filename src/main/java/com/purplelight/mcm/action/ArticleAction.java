package com.purplelight.mcm.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.purplelight.mcm.entity.Article;
import com.purplelight.mcm.entity.DictionaryItem;
import com.purplelight.mcm.entity.SystemUser;
import com.purplelight.mcm.service.IArticleService;
import com.purplelight.mcm.service.IDictionaryService;
import com.purplelight.mcm.util.McmConstant;
import com.purplelight.mcm.util.StringUtil;

public class ArticleAction extends BaseAction {
	private static final long serialVersionUID = -7962877311436757573L;

	private static final String ARTICLE_TYPE = "D000002";
	
	private List<DictionaryItem> articleTypes = new ArrayList<>();
	
	private List<Article> articles = new ArrayList<>();
	
	private String articleType;
	
	private Article article;
	
	@Resource
	private IDictionaryService dictService;
	
	@Resource
	private IArticleService articleService;
	
	public String execute() throws Exception{
		articleTypes = dictService.getDictItemsByDictNameCode(ARTICLE_TYPE);
		articleType = getRequest().getParameter("articleType");
		if (StringUtil.IsNullOrEmpty(articleType) && articleTypes != null && articleTypes.size() > 0){
			articleType = articleTypes.get(0).getDictItemCode();
		}
		if (!StringUtil.IsNullOrEmpty(articleType)){
			articles = articleService.getByArticleType(articleType);
		}
		
		return SUCCESS;
	}
	
	public String info() throws Exception{
		articleTypes = dictService.getDictItemsByDictNameCode(ARTICLE_TYPE);
		articleType = getRequest().getParameter("articleType");
		String articleId = getRequest().getParameter("id");
		if (!StringUtil.IsNullOrEmpty(articleId)){
			article = articleService.getById(Integer.parseInt(articleId));
		}
		
		return SUCCESS;
	}
	
	public String save() throws Exception{
		articleTypes = dictService.getDictItemsByDictNameCode(ARTICLE_TYPE);
		articleType = getRequest().getParameter("articleType");
		SystemUser loginedUser = (SystemUser)getSession().get(McmConstant.USER_SESSION);
		try{
			article.setArticleType(articleType);
			if (article.getId() == 0){
				articleService.addArticle(article, loginedUser);
			} else {
				articleService.updateArticle(article, loginedUser);
			}
			
			setMessageType(BaseAction.SUCCESS_MSG);
			setMessageFromResource("msg_save_success");
			return SUCCESS;
		} catch (Exception ex){
			setMessageType(BaseAction.ERROR_MSG);
			setMessage(ex.getMessage());
			return ERROR;
		}
	}

	public List<DictionaryItem> getArticleTypes() {
		return articleTypes;
	}

	public void setArticleTypes(List<DictionaryItem> articleTypes) {
		this.articleTypes = articleTypes;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public String getArticleType() {
		return articleType;
	}

	public void setArticleType(String articleType) {
		this.articleType = articleType;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}
	
}
