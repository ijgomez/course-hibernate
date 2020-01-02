package org.course.hibernate.services;

import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.course.hibernate.beans.Blog;
import org.course.hibernate.beans.Entry;
import org.course.hibernate.exceptions.BlogException;

public class BlogServiceImpl implements BlogService {

	private ResourceBundle bundle = ResourceBundle.getBundle("message_es");
	
    private EntityManagerFactory emf;
    
    public BlogServiceImpl() {
    	try {
            emf = Persistence.createEntityManagerFactory("CourseAppPU");
        } catch (Exception e) {
            throw new BlogException(bundle.getString("NO_SERVICIO_BLOG"), e);
        }
	}
    
	@SuppressWarnings("unchecked")
	@Override
	public List<Blog> getBlogs() throws BlogException {
		List<Blog> lista = null;
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            lista = em.createQuery("select b from Blog b").getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            throw new BlogException(bundle.getString("NO_LISTA_BLOGS"), e);
        } finally {
            em.close();
        }
        return lista;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Entry> getEntries(Blog blog) throws BlogException {
		List<Entry> lista = null;
        if (blog == null || blog.getId() == null) {
            throw new BlogException(bundle.getString("BLOG_NULO"));
        } else {
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            try {
                lista = em.createQuery("select b.entries from Blog b where b.id = :id").setParameter("id", blog.getId()).getResultList();
                em.getTransaction().commit();
            } catch (Exception e) {
                e.printStackTrace();
                em.getTransaction().rollback();
                throw new BlogException(bundle.getString("NO_ENTRADAS"), e);
            } finally {
                em.close();
            }
        }
        return lista;
	}

	@Override
	public Blog createBlog() throws BlogException {
		Blog blog = new Blog();
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(blog);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            throw new BlogException(bundle.getString("NO_CREAR_BLOG"), e);
        } finally {
            em.close();
        }
        return blog.getId() == null ? null : blog;
	}

	@Override
	public Blog findBlog(Long id) throws BlogException {
		if (id == null) {
            throw new BlogException(bundle.getString("IDENTIFICADOR_NULO"));
        }
        Blog blog = null;
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            blog = em.find(Blog.class, id);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            throw new BlogException(bundle.getString("NO_SE_ENCUENTRA_BLOG") + blog.getId(), e);
        } finally {
            em.close();
        }
        return blog;
	}

	@Override
	public Blog deleteBlog(Long id) throws BlogException {
		if (id == null) {
            throw new BlogException(bundle.getString("IDENTIFICADOR_NULO"));
        }
        Blog blog = null;
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            blog = em.find(Blog.class, id);
            em.remove(blog);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            throw new BlogException(bundle.getString("NO_BORRAR_BLOG") + blog.getId(), e);
        } finally {
            em.close();
        }
        return blog;
	}

	@Override
	public void updateBlog(Blog blog) throws BlogException {
		if (blog == null || blog.getId() == null) {
            throw new BlogException(bundle.getString("NO_ACTUALIZAR_BLOG"));
        }
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.merge(blog);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            throw new BlogException(bundle.getString("NO_ACTUALIZAR_BLOG") + blog.getId(), e);
        } finally {
            em.close();
        }
	}

	@Override
	public void updateBlog(Blog blog, Entry entry) throws BlogException {
		if (blog == null || blog.getId() == null) {
            throw new BlogException(bundle.getString("NO_ACTUALIZAR_BLOG"));
        }
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            Blog b = em.merge(blog);
            b.getEntries().add(entry);
            entry.setBlog(b);
            em.merge(b);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            throw new BlogException(bundle.getString("NO_ACTUALIZAR_BLOG") + blog.getId(), e);
        } finally {
            em.close();
        }
	}

	@Override
	public void deleteEntry(Long id) throws BlogException {
		if (id == null) {
            throw new BlogException(bundle.getString("IDENTIFICADOR_NULO"));
        }
        Entry entry = null;
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
        	entry = em.find(Entry.class, id);
            Blog blog = entry.getBlog();
            blog.getEntries().remove(entry);
            entry.setBlog(null);
            em.remove(entry);
            em.merge(blog);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            throw new BlogException(bundle.getString("NO_BORRAR_BLOG") + entry.getId(), e);
        } finally {
            em.close();
        }
	}

}
