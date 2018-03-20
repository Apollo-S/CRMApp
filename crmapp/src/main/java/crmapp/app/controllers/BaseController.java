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

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
	
	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
	
	public static final String NUMBER_SIGNS = "###";
	public static final String LOG_ENTER_METHOD = ANSI_BLUE + NUMBER_SIGNS + " Enter ";
	public static final String LOG_OUT_OF_METHOD = ANSI_YELLOW + NUMBER_SIGNS + " Out of ";
	public static final String LOG_TEXT = ANSI_GREEN + NUMBER_SIGNS + " ";
	public static final String LOG_ERROR = ANSI_RED + NUMBER_SIGNS + " ";
	public static final String LOG_CLOSE = " " + NUMBER_SIGNS + ANSI_RESET;
	
}
