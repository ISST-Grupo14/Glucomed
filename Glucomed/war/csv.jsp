<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ page import="java.io.*" %>
<%@ page import="javax.servlet.*" %>
<%@ page import="javax.servlet.http.*" %>


<%
  	/*
			@param: This code expects "csv_data" to contain the data to download as csv
			@param: In "file_name" its an optional param for naming the .csv file
		 */
		String csv_string = request.getParameter("csv_data");
		String file_name = request.getParameter("file_name");
		if(file_name.length() == 0) file_name = "data.csv";
		
		response.setContentType("application/csv");
		response.setHeader("content-disposition","filename="+file_name); // Filename
		
		PrintWriter outx = response.getWriter();
		outx.println(csv_string);
		outx.flush();
		outx.close();
%>