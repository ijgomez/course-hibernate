package org.course.hibernate.utils;

public class HibernateHook extends Thread {

	@Override
	public void run() {
		HibernateUtil.shutdown();
	}
}
