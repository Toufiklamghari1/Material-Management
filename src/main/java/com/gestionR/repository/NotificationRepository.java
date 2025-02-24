package com.gestionR.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gestionR.models.Notification;

@Repository
public interface NotificationRepository extends MongoRepository<Notification,String>{
//	public List<Notification> findByIdDerstination(String id);
}
