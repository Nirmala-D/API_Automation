package com.Courses;

import org.testng.Assert;

import files.Payload;
import io.restassured.path.json.JsonPath;

public class Test_Courses {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JsonPath js = new JsonPath(Payload.courses());
		
		// scenario - "Print No of courses returned by API"
		int count=	js.getInt("courses.size()");
		System.out.println(count);
		
		
		// scenario - "Print Purchase Amount"
		int Purchase_Amount =js.getInt("dashboard.purchaseAmount");
		System.out.println(Purchase_Amount);
		
		//scenario - "Print Title of the first course"
		String first_Course_title = js.get("courses[0].title");
		System.out.println(first_Course_title);
		
		
		//scenario - "Print All course titles and their respective Prices"
		for (int i=0; i<count; i++)
		{
			String coursesTitles =js.get("courses["+i+"].title");
			System.out.println(coursesTitles);
			System.out.println(js.get("courses["+i+"].price").toString());
			}
		
		//scenario - "Print no of copies sold by RPA Course"
		System.out.println("no of copies sold by RPA Course");
		for (int i=0;i<count; i++) {
			String courseTitle =js.get("courses["+i+"].title");
		if(courseTitle.equalsIgnoreCase("RPA")) {
			int copies=js.getInt("courses["+i+"].copies");
			System.out.println(copies);
			break;
		}
		
		//scenario - "Verify if Sum of all Course prices matches with Purchase Amount"
		int sum =0;
		for(int i1=0; i1<count;i1++) {
			int price =js.getInt("courses["+i1+"].price");
			int copies =js.getInt("courses["+i1+"].copies");
			int amount = price * copies;
			System.out.println(amount);
			sum = sum + amount;
			
		}
		System.out.println(sum);
		int purchaseAmount =js.getInt("dashboard.purchaseAmount");
		Assert.assertEquals(sum, purchaseAmount);

		
			
			
			
		}

	}

}
