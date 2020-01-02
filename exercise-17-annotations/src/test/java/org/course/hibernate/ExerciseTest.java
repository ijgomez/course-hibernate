package org.course.hibernate;

import java.util.Date;
import java.util.List;

import org.course.hibernate.beans.Blog;
import org.course.hibernate.beans.Entry;
import org.course.hibernate.factories.BlogServiceFactory;
import org.course.hibernate.services.BlogService;
import org.junit.Before;
import org.junit.Test;

public class ExerciseTest {
	
	private BlogService blogService;
	
	@Before
	public void beforeTest() throws Exception {
		blogService = BlogServiceFactory.getInstance();
	}
	
	@Test
	public void test() throws Exception {
		cargarBd();
        mostrarBlog();
	}

	private void cargarBd() {
		final int MAX_ENTRADAS = 5;
        Blog b = blogService.createBlog();
        b.setName("Blog uno");
        b.setDescription("Descripción uno");
        for (int i = 0; i < MAX_ENTRADAS; i++) {
            Entry e = new Entry("Entrada " + i, new Date());
            b.getEntries().add(e);
            e.setBlog(b);
        }
        blogService.updateBlog(b);
		
	}
	
	private void mostrarBlog() {
		List<Blog> blogs = blogService.getBlogs();
        for (Blog blog : blogs) {
            System.out.format("Título:%s. Descripción:%s%n", blog.getName(), blog.getDescription());
            List<Entry> entradas = blogService.getEntries(blog);
            if (entradas.size() == 0) {
                System.out.println("Este blog no tiene entradas");
            } else {
                for (Entry entradaBlog : entradas) {
                    System.out.format("Texto:%s. Fecha:%s%n", entradaBlog.getText(), entradaBlog.getDate());
                }
            }
        }
	}

	
}
