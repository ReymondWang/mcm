package com.purplelight.mcm.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="article")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Article implements Serializable {
	private static final long serialVersionUID = 1056293286675585399L;

	private int id;
	
	/**
	 * 文章编号
	 */
	private String articleCode;
	
	/**
	 * 文章名称
	 */
	private String articleName;
	
	/**
	 * 文章类型
	 */
	private String articleType;
	
	/**
	 * 文章内容
	 */
	private String articleContent;
	
	/**
	 * 创建者
	 */
	private int inputUser;
	
	/**
	 * 创建时间
	 */
	private Timestamp inputTime;
	
	/**
	 * 更新者
	 */
	private int updateUser;
	
	/**
	 * 更新时间
	 */
	private Timestamp upateTime;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id", unique=true)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="article_code", length=50)
	public String getArticleCode() {
		return articleCode;
	}

	public void setArticleCode(String articleCode) {
		this.articleCode = articleCode;
	}
	
	@Column(name="article_name", length=100)
	public String getArticleName() {
		return articleName;
	}

	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}

	@Column(name="article_type", length=50)
	public String getArticleType() {
		return articleType;
	}

	public void setArticleType(String articleType) {
		this.articleType = articleType;
	}

	@Column(name="article_content", length=Integer.MAX_VALUE)
	public String getArticleContent() {
		return articleContent;
	}

	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}

	@Column(name="input_user")
	public int getInputUser() {
		return inputUser;
	}

	public void setInputUser(int inputUser) {
		this.inputUser = inputUser;
	}

	@Column(name="input_time")
	public Timestamp getInputTime() {
		return inputTime;
	}

	public void setInputTime(Timestamp inputTime) {
		this.inputTime = inputTime;
	}

	@Column(name="update_user")
	public int getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(int updateUser) {
		this.updateUser = updateUser;
	}

	@Column(name="update_time")
	public Timestamp getUpateTime() {
		return upateTime;
	}

	public void setUpateTime(Timestamp upateTime) {
		this.upateTime = upateTime;
	}
}
