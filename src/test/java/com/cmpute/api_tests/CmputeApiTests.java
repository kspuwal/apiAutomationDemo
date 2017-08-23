package com.cmpute.api_tests;

import com.compute.api.ComputeApi;
import com.jayway.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/*
 * Created by Kuldeep Singh, Email : kspuwal@gmail.com, Mob : +91 8553815417, on 8/16/2017.
 * LinkedIn : https://www.linkedin.com/in/kuldeep-singh-a8951337/
*/


public class CmputeApiTests {
        ComputeApi ca = new ComputeApi();
        int actualResCode;


    //This Data Provider will pass the Test Description , Action Type and Expected Status Code
    //End point gets the details of different actionType : https://jsonplaceholder.typicode.com/actionType

    @DataProvider
    public Object[][] getActionTypeData() {
        return new Object[][]{
                {"\t 001 : Verify that GET Request for /posts returns all the posts details ","posts",200},
                {"\t 002 : Verify that GET Request for /comments returns all the comments details ","comments",200},
                {"\t 003 : Verify that GET Request for /albums returns all the comments details ","albums",200},
                {"\t 004 : Verify that GET Request for /photos returns all the comments details ","photos",200},
                {"\t 005 : Verify that GET Request for /todos returns all the comments details ","todos",200},
                {"\t 006 : Verify that GET Request for /users returns all the comments details ","users",200},
                {"\t 007 : Verify that GET Request for a wrong end point should not returns anythings, " +
                        "\n It should be fail with 404 code ","comment",404}
        };
    }
	@Test(dataProvider ="getActionTypeData" )
	public void getPostsResultsTest1(String testDesc,String actionType, int expectedResCode){
        System.out.println("\n "+testDesc);
		actualResCode = ca.getResults(actionType);
		System.out.println("Actual Status Code : "+actualResCode);
        System.out.println("Expected Status Code : "+expectedResCode);
		Assert.assertEquals(actualResCode, expectedResCode, "Test Failed : Response Code not matching ");
        System.out.println("-------------------   Test Case Passed    --------------------\n\n");
	}



    //This Data Provider will pass the Test Description , Action Type , Expected Status Code and the ID's
    //End point gets the details of different actionType using id : https://jsonplaceholder.typicode.com/actionType/id
    @DataProvider
    public Object[][] getActionTypeAndIdData() {
        return new Object[][]{
                {"\t 008: Verify that GET Request for /posts returns all the posts details ", "posts",200,1},
                {"\t 009: Verify that GET Request for /comments returns all the comments details ", "comments",200,5},
                {"\t 010: Verify that GET Request for /albums returns all the comments details ","albums",200,2},
                {"\t 011: Verify that GET Request for /photos returns all the comments details ","photos",200,3},
                {"\t 012: Verify that GET Request for /todos returns all the comments details ","todos",200,10},
                {"\t 013: Verify that GET Request for /users returns all the comments details ","users",200,9},
                {"\t 014: Verify that GET Request for a wrong end point should not returns anythings, " +
                        "\n It should be fail with 404 code ","comment",404,1}
        };
    }

   @Test(dataProvider = "getActionTypeAndIdData")
	public void getResultsBasedOnActionAndIdTest2(String testDesc,String actionType, int expectedResCode, int id){
       System.out.println("\n "+testDesc);
       actualResCode = ca.getResults(actionType,id);
       System.out.println("Actual Status Code of "+actionType+ " Action based on ID :"+id+" = "+actualResCode);
       System.out.println("Expected Status Code : "+expectedResCode);
       Assert.assertEquals(actualResCode, expectedResCode, "Test Failed : Response Code not matching ");
       System.out.println("-------------------   Test Case Passed    --------------------\n\n");

	}



    //This data Provider will be used for below 15 and 16 test cases because both Tests using same data but diff endpoints
    // This will pass the Test Description ,Expected Status Code and ID's

    @DataProvider
    public Object[][] getPostIdData() {
        return new Object[][]{
                {"a : Verify that GET Request returns the comments details based on PostsId, where posts id = ",200,25},
                {"b : Verify that GET Request returns the comments details based on PostsId, where posts id = ",200,45}
        };
    }
    //This End point gets the comments details based on PostID : https://jsonplaceholder.typicode.com/posts/postId/comments
    @Test(dataProvider = "getPostIdData")
        public void getCommentsBasedOnPostId_1Test3(String testDesc,int expectedResCode, int postId){
            System.out.println("\n \t15."+testDesc+postId);
            actualResCode = ca.getCommentsBasedOnPostId_1(postId);
            System.out.println("Actual Status Code : "+actualResCode);
            System.out.println("Expected Status Code : "+expectedResCode);
            Assert.assertEquals(actualResCode, expectedResCode, "Test Failed : Response Code not matching ");
            System.out.println("-------------------   Test Case Passed    --------------------\n\n");
        }

    // End point gets the comments details based on PostID : https://jsonplaceholder.typicode.com/comments?posts=postID
    @Test(dataProvider = "getPostIdData")
    public void getCommentsBasedOnPostId_2Test4(String testDesc,int expectedResCode, int postId){
        System.out.println("\n \t16"+testDesc+postId);
        actualResCode = ca.getCommentsBasedOnPostId_2(postId);
        System.out.println("Actual Status Code : "+actualResCode);
        System.out.println("Expected Status Code : "+expectedResCode);
        Assert.assertEquals(actualResCode, expectedResCode, "Test Failed : Response Code not matching ");
        System.out.println("-------------------   Test Case Passed    --------------------\n\n");
    }



    // This will pass the Test Description ,ActionType : posts / albums,ID and Expected Status Code
    @DataProvider
    public Object[][] getActionTypeUserIdData() {
        return new Object[][]{
                {"017: Verify that GET Request returns the Posts details based on UserId, where User id = ","posts",50,200},
                {"018: Verify that GET Request returns the Albums details based on UserId, where User id = ","albums",10,200}
        };
    }
    //This End point gets the posts details based on User ID  :https://jsonplaceholder.typicode.com/posts?userId
    //his End point gets the albums details based on User ID  :https://jsonplaceholder.typicode.com/albums?userId
    @Test(dataProvider = "getActionTypeUserIdData")
    public void getDetailsBasedOnUserIdTest5(String testDesc,String actionType,int userId,int expectedResCode){
        System.out.println("\t"+testDesc+userId);
        actualResCode = ca.getPostsAndAlbumBasedOnUserId(actionType,userId);
        System.out.println("Actual Status Code : "+actualResCode);
        System.out.println("Expected Status Code : "+expectedResCode);
        Assert.assertEquals(actualResCode, expectedResCode, "Test Failed : Response Code not matching ");
        System.out.println("-------------------   Test Case Passed    --------------------\n\n");
    }



    //This Data Provider will pass the Test Case Description, Title, Body and UserId to postDataTest(....)
    @DataProvider
    public Object[][] getPostData() {
        return new Object[][]{
                {"a : Verify that POST Request successful and returns response code : 201 \n " +
                        "when Title,Body and UserId passed","Title Input 1","Body Input 1",5,201},
                {"b : Verify that POST Request successful and returns response code : 201 \n" +
                        "when Empty Title,Body and UserId passed","", "Body Input 2 with Empty Title", 6, 201},
                {"c : Verify that POST Request successful and returns response code : 201 \n" +
                        "when Title,Empty Body and UserId passed","Tile Input 3 with Empty Body", "", 7, 201},

                };
        }
    //POST Request with different combination of input parameters
    @Test(dataProvider = "getPostData")
        public void postDataTest6(String testDesc,String title, String body, int userId,int expectedResCode){
            System.out.println("\n \t19."+testDesc);
            RestAssured.baseURI = "https://jsonplaceholder.typicode.com/posts/";
            actualResCode = ca.postRequest(title,body,userId);
            System.out.println("Actual Status Code : "+actualResCode);
            System.out.println("Expected Status Code : "+expectedResCode);
            Assert.assertEquals(actualResCode, expectedResCode, "Test Failed : Response Code not matching ");
            System.out.println("-------------------   Test Case Passed    --------------------\n\n");

        }

    //This Data Provider will pass the Test Case Description, Id, Title, Body and UserId to the putDataTest(....)
    @DataProvider
    public Object[][] getPutData() {
        return new Object[][]{
                {"a : Verify that PUT Request successful and returns response code : 201 \n " +
                        "when Title,Body and UserId passed",1,"Title Input 1","Body Input 1",5,200},
                {"b : Verify that PUT Request successful and returns response code : 201 \n" +
                        "when Empty Title,Body and UserId passed",2,"", "Body Input 2 with Empty Title", 6, 200},
                {"c : Verify that PUT Request successful and returns response code : 201 \n" +
                        "when Title,Empty Body and UserId passed",1,"Tile Input 3 with Empty Body", "", 7, 200},

        };
    }
    //PUT Request with different combination of input parameters
    @Test(dataProvider = "getPutData")
    public void putDataTest7(String testDesc,int id,String title, String body, int userId,int expectedResCode){
        System.out.println("\n \t20."+testDesc);
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/posts/"+id;
        actualResCode = ca.putRequest(id,title,body,userId);
        System.out.println("Actual Status Code : "+actualResCode);
        System.out.println("Expected Status Code : "+expectedResCode);
        Assert.assertEquals(actualResCode, expectedResCode, "Test Failed : Response Code not matching ");
        System.out.println("-------------------   Test Case Passed    --------------------\n\n");

    }
}
