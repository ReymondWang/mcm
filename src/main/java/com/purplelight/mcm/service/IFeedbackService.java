package com.purplelight.mcm.service;

import java.util.List;

import com.purplelight.mcm.entity.Feedback;
import com.purplelight.mcm.query.PageInfo;

public interface IFeedbackService {
	public List<Feedback> getAll();
	public PageInfo<Feedback> query(String content, String startDate, String endDate, int pageNo) throws Exception;
	public Feedback getById(int id);
	public void addFeedback(Feedback feedback);
}
