package org.course.hibernate.interceptors;

import java.io.Serializable;
import java.util.Iterator;

import org.hibernate.CallbackException;
import org.hibernate.EntityMode;
import org.hibernate.Interceptor;
import org.hibernate.Transaction;
import org.hibernate.type.Type;

public class NoHacerloAsiInterceptor implements Interceptor {

	@Override
	public boolean onLoad(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) throws CallbackException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

	@Override
    public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) throws CallbackException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

	@Override
    public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) throws CallbackException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

	@Override
    public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) throws CallbackException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

	@Override
    public void onCollectionRecreate(Object collection, Serializable key) throws CallbackException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

	@Override
    public void onCollectionRemove(Object collection, Serializable key) throws CallbackException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

	@Override
    public void onCollectionUpdate(Object collection, Serializable key) throws CallbackException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

	@SuppressWarnings("rawtypes")
	@Override
    public void preFlush(Iterator entities) throws CallbackException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

	@SuppressWarnings("rawtypes")
	@Override
    public void postFlush(Iterator entities) throws CallbackException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

	@Override
    public Boolean isTransient(Object entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

	@Override
    public int[] findDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

	@Override
    public Object instantiate(String entityName, EntityMode entityMode, Serializable id) throws CallbackException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

	@Override
    public String getEntityName(Object object) throws CallbackException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

	@Override
    public Object getEntity(String entityName, Serializable id) throws CallbackException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

	@Override
    public void afterTransactionBegin(Transaction tx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

	@Override
    public void beforeTransactionCompletion(Transaction tx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

	@Override
    public void afterTransactionCompletion(Transaction tx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

	@Override
    public String onPrepareStatement(String sql) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
