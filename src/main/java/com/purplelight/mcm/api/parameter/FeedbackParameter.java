package com.purplelight.mcm.api.parameter;

import com.purplelight.mcm.entity.Feedback;

public class FeedbackParameter extends Parameter {
	private Feedback feedback;

	public Feedback getFeedback() {
		return feedback;
	}

	public void setFeedback(Feedback feedback) {
		this.feedback = feedback;
	}
}
