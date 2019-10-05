package com.assignment.shoppingApp.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySources(value = {@PropertySource("classpath:messages.properties"),
		@PropertySource("classpath:application.properties") })
public class MyCorsFilter implements Filter {

	private static final Logger LOGGER = Logger.getLogger(MyCorsFilter.class);

	@Autowired
	private Environment environment;

	public MyCorsFilter() {
		super();
	}

	public final void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain)
			throws IOException, ServletException {
		final HttpServletRequest request = (HttpServletRequest) req;
		String origin = request.getHeader("Origin");

		LOGGER.info("Request origin URL: " + origin);
		/**
		 * URL originUrl = null; try { originUrl = new URL(origin); } catch
		 * (MalformedURLException ex) { }
		 */

		final HttpServletResponse response = (HttpServletResponse) res;
		// Allow codegrip.tech
		// Or app.codegrip.tech
		// Or api.myDomain.com

		// hostAllowedPattern
		/**
		 * Pattern hostAllowedPattern = Pattern.compile("(.+\\.)*myDomain\\.com",
		 * Pattern.CASE_INSENSITIVE);
		 */

		// Allow host?
		/**
		 * if (hostAllowedPattern.matcher(originUrl.getHost()).matches()) {
		 * response.addHeader("Access-Control-Allow-Origin", origin);
		 * 
		 * } else { // Throw 403 status OR send default allow
		 * response.addHeader("Access-Control-Allow-Origin", "https://my_domain.com"); }
		 */
		
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers",
				"X-Requested-With, Authorization, Origin, Content-Type, Version");
		response.setHeader("Access-Control-Expose-Headers", "X-Requested-With, Authorization, Origin, Content-Type");

		if (!request.getMethod().equals("OPTIONS")) {
			chain.doFilter(req, res);
		} else {
			// do not continue with filter chain for options requests
		}
	}

	public void destroy() {
		// method data.
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		// method data.
	}
}