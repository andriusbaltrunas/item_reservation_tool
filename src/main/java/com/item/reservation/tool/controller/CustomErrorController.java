package com.item.reservation.tool.controller;

import org.apache.log4j.Logger;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {

    private static final Logger log = Logger.getLogger(CustomErrorController.class);

    private static final String ERROR_PAGE_VIEW = "errorPage";
    private static final String ERROR_PAGE_PATH = "/error";
    private static final String PAGE_NOT_FOUND_PATH = "/404";
    private static final String TEMPORARY_PROBLEM_VIEW = "temporaryProblems";
    private static final String PAGE_NOT_FOUND_VIEW = "pageNotFound";
    private static final String ACCESS_DENIED_VIEW = "accessDenied";

    private static final String ERROR_CODE = "javax.servlet.error.status_code";
    private static final String ERROR_MESSAGE = "javax.servlet.error.exception";

    @RequestMapping(value = {ERROR_PAGE_PATH, PAGE_NOT_FOUND_PATH}, method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView defaultErrorPage(HttpServletRequest servletRequest, Exception e) {

        String view = ERROR_PAGE_VIEW;
        int statusCode = getErrorCode(servletRequest);

        switch (statusCode) {
            case 400:
                view = PAGE_NOT_FOUND_VIEW;
                break;
            case 401:
                view = ACCESS_DENIED_VIEW;
                break;
            case 404:
                view = PAGE_NOT_FOUND_VIEW;
                break;
            case 500:
                view = TEMPORARY_PROBLEM_VIEW;
                break;
            default:
                break;
        }
        log.error("Get error in controller error status " +statusCode+" error message:"+ getErrorMessage(servletRequest, statusCode));

        ModelAndView modelAndView = new ModelAndView(view);
        return modelAndView;
    }

    private int getErrorCode(HttpServletRequest httpServletRequest) {
        int statusCode = (int) httpServletRequest.getAttribute(ERROR_CODE);
        return statusCode;
    }

    private String getErrorMessage(HttpServletRequest request, int errorCode) {
        String message = HttpStatus.valueOf(errorCode).getReasonPhrase();
        Throwable throwable = (Throwable) request.getAttribute(ERROR_MESSAGE);
        if (throwable != null) {
            message = throwable.getMessage();
        }
        return message;
    }

    @Override
    public String getErrorPath() {
        return ERROR_PAGE_PATH;
    }
}