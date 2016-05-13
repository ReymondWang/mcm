package com.purplelight.mcm.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import com.purplelight.mcm.dao.IFeedbackDao;
import com.purplelight.mcm.entity.Feedback;
import com.purplelight.mcm.query.ConditionItem;
import com.purplelight.mcm.query.PageInfo;
import com.purplelight.mcm.query.SqlCondition;
import com.purplelight.mcm.query.SqlJoin;
import com.purplelight.mcm.query.Strategy;
import com.purplelight.mcm.service.IFeedbackService;
import com.purplelight.mcm.util.McmConstant;
import com.purplelight.mcm.util.StringUtil;

public class FeedbackServiceImpl implements IFeedbackService {

	@Resource
	private IFeedbackDao feedbackDao;
	
	@Override
	public List<Feedback> getAll() {
		return feedbackDao.getAll();
	}

	@Override
	public PageInfo<Feedback> query(String content, String startDate, String endDate, int pageNo) throws Exception {
		int pageSize = McmConstant.PAGE_SIZE;
		int startPos = (pageNo - 1) * pageSize;
		
		Strategy strategy = new Strategy();
		strategy.setTable("Feedback");
		strategy.setAlias("f");
		if (!StringUtil.IsNullOrEmpty(content)){
			strategy.add(new ConditionItem("content", content, SqlJoin.AND, SqlCondition.LIKE));
		}
		if (!StringUtil.IsNullOrEmpty(startDate)){
//			strategy.add(new ConditionItem("inputTime", ConvertUtil.ConvertToTimestamp(startDate), SqlJoin.AND, SqlCondition.BIGGER_OR_EQUALS));
		}
		if (!StringUtil.IsNullOrEmpty(endDate)){
			strategy.add(new ConditionItem("inputTime", endDate, SqlJoin.AND, SqlCondition.LESS_OR_EQUALS));
		}
		
		return feedbackDao.find(strategy, startPos, pageNo, pageSize);
	}

	@Override
	public Feedback getById(int id) {
		return feedbackDao.getById(id);
	}

	@Override
	public void addFeedback(Feedback feedback) {
		feedbackDao.save(feedback);
	}
}
