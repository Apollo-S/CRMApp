package crmapp.app.controllers;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseController {

	static final String PARAM_ID = "id";
	static final String HEADER_JSON = "Accept=application/json";
	
	static final String GET_VALUE = "/";
	static final String ADD_VALUE = "/add/";
	static final String UPDATE_VALUE = "/update/{id}";
	static final String DELETE_VALUE = "/delete/{id}";

}
