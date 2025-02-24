package com.gestionR.serviceImplementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionR.models.Notification;
import com.gestionR.repository.NotificationRepository;
import com.gestionR.service.NotificationService;
@Service
public class NotificationServiceImp  implements NotificationService{
	@Autowired
	NotificationRepository notRep;
	@Override
	public List<Notification> getNotification(String id) {
		List<Notification> listN = new ArrayList<Notification>();
		List<Notification> list = notRep.findAll();
		for(Notification not : list) {
			if(not.getIdDerstination().equals(id)) {
				listN.add(not);
			}
		}
		return listN;
	}

}
