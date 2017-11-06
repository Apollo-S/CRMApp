package crmapp.app.controllers;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseController {

	static final String PARAM_ID = "id";
	static final String HEADER_JSON = "Accept=application/json";
	
	static final String REQUEST_MAPPING_EMPTY = "";
	static final String REQUEST_MAPPING_GET_ALL = "/";
	static final String REQUEST_MAPPING_BY_ID = "/{" + PARAM_ID + "}";
	static final String REQUEST_MAPPING_ADD = "/add/";
	static final String REQUEST_MAPPING_UPDATE = "/update/{id}";
	static final String REQUEST_MAPPING_DELETE = "/delete/{id}";

}
