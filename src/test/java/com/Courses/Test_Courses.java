package com.Courses;

import files.Payload;
import io.restassured.path.json.JsonPath;

public class Test_Courses {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JsonPath js = new JsonPath(Payload.courses());
		
		// scenario - "Print No of courses returned by API"
		
		int No_of_courses=	js.getInt("courses.size()");
		System.out.println(No_of_courses);
		
		
				

	}

}
