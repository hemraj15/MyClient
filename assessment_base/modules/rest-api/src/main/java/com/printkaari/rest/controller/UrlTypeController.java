package com.printkaari.rest.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.printkaari.data.dao.entity.UrlType;
import com.printkaari.rest.model.ErrorResponse;
import com.printkaari.rest.service.CommonService;

@RestController
@RequestMapping("/url/types")
public class UrlTypeController {

	private Logger			LOGGER	= LoggerFactory.getLogger(UrlTypeController.class);

	@Autowired
	private CommonService	commonService;

	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public Object fetchAllUrlTypes(HttpServletResponse response) {
		Object data = null;
		try {
			List<UrlType> urlTypes = commonService.getActiveUrlTypesCriteria();
			data = urlTypes;

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			data = new ErrorResponse();
			((ErrorResponse) data).setMessage(e.getMessage());
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

		return data;

	}

}
