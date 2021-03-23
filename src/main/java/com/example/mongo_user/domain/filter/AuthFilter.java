package com.example.mongo_user.domain.filter;

import com.example.mongo_user.domain.services.impl.CacheManager;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthFilter implements Filter {

  private ServletContext context;

  @Autowired
  CacheManager cacheManager;

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    this.context = filterConfig.getServletContext();
    System.out.println("fileterConfif.servletContext " + filterConfig.getServletContext());
  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    HttpServletResponse response = (HttpServletResponse) servletResponse;
    String url = request.getRequestURI();
    System.out.println("request.getRequestURI" + url );
    if ((url.startsWith("/api/users") || url.startsWith("/api/refresh") || url.startsWith("/api/user")) || url.startsWith("/api/question") || url.startsWith("/api/answer") && ((HttpServletRequest) servletRequest).getHeader("token")!= null ) {
      ((HttpServletResponse) servletResponse).setHeader("token",((HttpServletRequest) servletRequest).getHeader("token"));
      filterChain.doFilter(servletRequest, servletResponse);
    } else if (url.startsWith("/login") || url.startsWith("/user")) {
      filterChain.doFilter(servletRequest, servletResponse);
    } else {
      System.out.println("Bạn phải đăng nhập");
    }
  }

  @Override
  public void destroy() {

  }
}
