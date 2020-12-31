package com.techbow.notification.gateway;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

public class GatewayErrorFilter extends ZuulFilter {

    private static final Logger LOG = Logger.getLogger(GatewayPostFilter.class.getName());

    @Override
    public String filterType() {
        return "error";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        LOG.info("Response - [Method: " + request.getMethod() + ", URL: " + request.getRequestURL().toString() + "]");
        return null;
    }
}
