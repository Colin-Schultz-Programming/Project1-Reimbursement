package com.reimbursement.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class RDBConnection {
	//Database statics
		private final static String RDS_HOSTNAME = "jdbc:postgresql://revaturedb.cdzcuikxvam1.us-east-2.rds.amazonaws.com:5432/Project1";
		//private final static String RDS_PORT = "5432";
		//private final static String RDS_DB_NAME = "Project1";
		private final static String RDS_USERNAME = "projects";
		private final static String RDS_PASSWORD = "revature";
		/*
		public static String getRdsHostname() {
			return RDS_HOSTNAME;
		}
		public static String getRdsPort() {
			return RDS_PORT;
		}
		public static String getRdsDbName() {
			return RDS_DB_NAME;
		}
		public static String getRdsUsername() {
			return RDS_USERNAME;
		}
		public static String getRdsPassword() {
			return RDS_PASSWORD;
		}
		*/
		public static Connection CreateRDBConnection(Logger logger) {
		    if (/*System.getenv("RDS_HOSTNAME") */ RDS_HOSTNAME != null) {
		      try {
		    	  
		      Class.forName("org.postgresql.Driver");
		      /*
		      String dbName = RDS_DB_NAME; //System.getenv("RDS_DB_NAME");
		      String userName = RDS_USERNAME; //System.getenv("RDS_USERNAME");
		      String password = RDS_PASSWORD; //System.getenv("RDS_PASSWORD");
		      String hostname = RDS_HOSTNAME;//System.getenv("RDS_HOSTNAME");
		      String port = RDS_PORT;//System.getenv("RDS_PORT");
		      String jdbcUrl = "jdbc:postgresql://" + hostname + ":" + port + "/" + dbName + "?user=" + userName + "&password=" + password;
		      */
		      logger.trace("Getting remote connection with connection string from environment variables.");
		      //Connection con = DriverManager.getConnection(jdbcUrl);
		      logger.debug("Attempting to connect to remote database");
		      Connection con = DriverManager.getConnection(RDS_HOSTNAME, RDS_USERNAME, RDS_PASSWORD);
		      logger.debug("Remote connection successful.");
		      return con;
		    }
		    catch (ClassNotFoundException e) { logger.warn(e.toString());}
		    catch (SQLException e) { logger.warn(e.toString());}
		    }
		    
		    return null;
		  }

}
