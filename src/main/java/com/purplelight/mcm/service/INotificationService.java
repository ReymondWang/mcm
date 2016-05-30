package com.purplelight.mcm.service;

import com.purplelight.mcm.api.result.NotificationCntResult;

public interface INotificationService {
	public NotificationCntResult getNotificationCnt(int userId, int appId);
}
