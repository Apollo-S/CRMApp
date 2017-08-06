package crmapp.app.controllers;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseController {

	static final String PARAM_ID = "id";
	static final String HEADER_JSON = "Accept=application/json";
	
	static final String VALUE_GET_ALL = "/";
	static final String VALUE_GET_BY_ID = "/{id}";
	static final String VALUE_ADD = "/add/";
	static final String VALUE_UPDATE = "/update/{id}";
	static final String VALUE_DELETE = "/delete/{id}";

}
