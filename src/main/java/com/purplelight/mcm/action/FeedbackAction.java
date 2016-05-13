package com.purplelight.mcm.action;

import javax.annotation.Resource;

import com.purplelight.mcm.entity.Feedback;
import com.purplelight.mcm.query.PageInfo;
import com.purplelight.mcm.service.IFeedbackService;
import com.purplelight.mcm.util.StringUtil;

public class FeedbackAction extends BaseAction {
	private static final long serialVersionUID = -7512747753376977821L;

	private Feedback feedback;
	
	private PageInfo<Feedback> pageInfo;
	
	private String content;
	
	private String startDate;
	
	private String endDate;
	
	private int currentPageNo = 1;
	
	@Resource
	private IFeedbackService feedbackService;
	
	@Override
	public String execute() throws Exception{
		pageInfo = feedbackService.query(content, startDate, endDate, currentPageNo);
		
		return SUCCESS;
	}
	
	public String show() throws Exception{
		String id = getRequest().getParameter("id");
		if (!StringUtil.IsNullOrEmpty(id)){
			feedback = feedbackService.getById(Integer.parseInt(id));
		}
		
		return SUCCESS;
	}

	public Feedback getFeedback() {
		return feedback;
	}

	public void setFeedback(Feedback feedback) {
		this.feedback = feedback;
	}

	public PageInfo<Feedback> getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo<Feedback> pageInfo) {
		this.pageInfo = pageInfo;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getCurrentPageNo() {
		return currentPageNo;
	}

	public void setCurrentPageNo(int currentPageNo) {
		this.currentPageNo = currentPageNo;
	}
}
