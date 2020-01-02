package org.course.hibernate.interceptors;

import java.io.Serializable;

import org.course.hibernate.annotations.Auditable;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

public class MyEmptyInterceptor extends EmptyInterceptor {

	private static final long serialVersionUID = -7063302406930811472L;

	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		System.out.println("Se va a crear un objeto persistente instancia de " + entity.getClass().getSimpleName());
        Auditable auditable = entity.getClass().getAnnotation(Auditable.class);
        if (auditable != null) {
            if (auditable.notifyToAdministrator()) {
                System.out.println("Esta operación se le comunicará al administrador por correo electrónico");
            }
        }
        return false;
	}
}
