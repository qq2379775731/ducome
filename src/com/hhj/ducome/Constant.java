package com.hhj.ducome;

public final class Constant {
	
	final static int ROLLBOOK_SEND 			= 0x00000001;
	final static int ROLLBOOK_SEND_NEW 		= 0x00000002;
	final static int ROLLBOOK_BACK_OK 		= 0x00000011;
	final static int ROLLBOOK_BACK_CANCEL 	= 0x00000012;
	final static int ROLLBOOK_BACK_DELETE 	= 0x00000013;
	final static int ROLLBOOK_BACK_RETURN	= 0x00000014;
	final static int STUDENT_SEND 			= 0x00000101;
	final static int STUDENT_SEND_NEW 		= 0x00000102;
	final static int STUDENT_BACK_OK 		= 0x00000111;
	final static int STUDENT_BACK_CANCEL 	= 0x00000112;
	final static int STUDENT_BACK_DELETE 	= 0x00000113;
	
	final static String ROLLBOOKNO			= "rollbookno";
	final static String POSITION			= "position";
	final static String CLASS_NAME			= "classname";
	final static String STUDENT_NUMBER 		= "studentnumber";
	final static String ROLL_TIME 			= "rolltime";
	final static String STUDENT_NAME 		= "studentname";
	final static String STUDENT_NO 			= "studentno";
	final static String NO_COME_TIME 		= "nocometime";
	final static String DATASAVE_OBJECT		= "datasave_object";
	
	final static String ROLLBOOK_SAVE_PATH	= "RollBooks.data";
	final static String STURENT_SAVE_PATH	= "Student/";
	final static String CONFIG_SAVE_PATH    = "config.ini";
}
