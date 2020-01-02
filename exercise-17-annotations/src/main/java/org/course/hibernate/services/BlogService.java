package org.course.hibernate.services;

import java.util.List;

import org.course.hibernate.beans.Blog;
import org.course.hibernate.beans.Entry;
import org.course.hibernate.exceptions.BlogException;

public interface BlogService {

	List<Blog> getBlogs() throws BlogException;

    List<Entry> getEntries(final Blog blog) throws BlogException;

    Blog createBlog() throws BlogException;

    Blog findBlog(final Long id) throws BlogException;

    Blog deleteBlog(final Long id) throws BlogException;

    void updateBlog(Blog blog) throws BlogException;

    void updateBlog(Blog blog, Entry entry) throws BlogException;

    void deleteEntry(Long id) throws BlogException;
    
}
