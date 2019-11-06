package com.hoony.msa.zuul.system.config;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

@Configuration
public class AuthConfiguration extends ZuulFilter {

    private Logger logger = LoggerFactory.getLogger(AuthConfiguration.class);

    @Override
    public String filterType() {
        logger.debug("======================================");
        logger.debug("filterType");
        return "pre";
    }

    @Override
    public int filterOrder() {
        logger.debug("======================================");
        logger.debug("filterOrder");
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        logger.debug("======================================");
        logger.debug("shouldFilter");
        RequestContext ctx = RequestContext.getCurrentContext();
        if (ctx.getRequest().getRequestURI().contains("login")) {
            logger.info("Login Request :: Skip Auth");
            return false;
        }
        if (ctx.getRequest().getRequestURI().contains("msa//v1/member") && "POST".equals(ctx.getRequest().getMethod())) {
            logger.info("Member creation request :: Skip Auth");
            return false;
        }

        if (ctx.getRequest().getRequestURI().endsWith("api-docs")) {
            logger.info("API Docs :: Skip Auth");
            return false;
        }
        logger.debug("Processing Auth filter");
        return true;
    }

    @Override
    public Object run() {
        logger.debug("======================================");
        logger.debug("run");
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        logger.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
        String token = request.getHeader("Authorization");
//        TokenResult result = tokenClient.getMemberByToken(token).getResult();
//
//        if (result == null || !result.isValid()) {
//            logger.info("Authorization fail " + result);
//            authorizeFail(ctx);
//        } else {
//            ctx.addZuulRequestHeader("userId", result.getUserId());
//        }
        return null;
    }

    private static void authorizeFail(RequestContext ctx) {
        ctx.setSendZuulResponse(false);
        ctx.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        ctx.getResponse().setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString());
        ctx.getResponse().setCharacterEncoding(StandardCharsets.UTF_8.toString());
        ctx.setResponseBody("{errorCode: 401, errorMessage:\"Unauthorized\"}");
    }
}
