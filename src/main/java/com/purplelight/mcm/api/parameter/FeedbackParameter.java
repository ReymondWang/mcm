package com.purplelight.mcm.api.parameter;

import com.purplelight.mcm.api.bean.FeedbackInfo;

public class FeedbackParameter extends Parameter {
	private FeedbackInfo feedback;

	public FeedbackInfo getFeedback() {
		return feedback;
	}

	public void setFeedback(FeedbackInfo feedback) {
		this.feedback = feedback;
	}
}
