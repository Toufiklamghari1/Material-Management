package com.gestionR.service;

import java.util.List;

import com.gestionR.models.Notification;

public interface NotificationService {
	public List<Notification> getNotification(String id);
}
