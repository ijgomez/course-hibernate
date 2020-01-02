package org.course.hibernate.listeners;

import org.hibernate.HibernateException;
import org.hibernate.event.SaveOrUpdateEvent;
import org.hibernate.event.SaveOrUpdateEventListener;

public class MySaveOrUpdateEventListener implements SaveOrUpdateEventListener {

	private static final long serialVersionUID = -4258441832745642807L;

	@Override
	public void onSaveOrUpdate(SaveOrUpdateEvent event) throws HibernateException {
		System.out.format("onSaveOrUpdate invocado sobre un objeto instancia de %s%n",event.getEntityName());
	}

}
