package com.gmail.ivanytskyy.vitaliy.dao;
/*
 * Task #2/2015/11/29 (pet web project #1)
 * DAOException class
 * @version 1.01 2015.11.29
 * @author Vitaliy Ivanytskyy
 */
public class DAOException extends Exception{
	private static final long serialVersionUID = 1L;
	public DAOException(){
		super();
	}
	public DAOException(String message){
		super(message);
	}
	public DAOException(String message, Throwable cause){
		super(message, cause);
	}
}