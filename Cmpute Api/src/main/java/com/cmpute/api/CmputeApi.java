package com.cmpute.api;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;

import static com.jayway.restassured.RestAssured.given;

/*
 * Created by Kuldeep Singh, Email : kspuwal@gmail.com, Mob : +91 8553815417, on 8/16/2017.
 * LinkedIn : https://www.linkedin.com/in/kuldeep-singh-a8951337/
*/


public class CmputeApi {

    String myJson;
    Response response;
        //API End point Url.
        static String apiEndPointUrl = "https://jsonplaceholder.typicode.com/";
        static int responseCode;
        String getMethod1 = "GET";
        static String postsAction = "posts";
        static String commentsAction ="comments";
        static String postMethod = "posts";

        //Common method : getResponse(String apiUrl)
        public static Response getResponse(String apiUrl){
            Response res = RestAssured.get().andReturn();
            System.out.println("\n--------------  Response of " +apiUrl+ "     ------------------\n");
            System.out.println(res.asString());
            return res;
        }

        //Get Response Result based on Action Type and ID
        public int getResults(String actionType, int id){
            String apiUrl = apiEndPointUrl+actionType+"/"+id;
            RestAssured.baseURI = apiUrl;
            responseCode=0;
            System.out.println("**********Response for "+ actionType +" Action for ID :"+id+" ************\n");
            responseCode = getResponse(apiUrl).getStatusCode();
            return responseCode;
        }

        //Get Response Result based on Action Type ( posts OR comments )

        public int getResults(String actionType){
            String apiUrl = apiEndPointUrl+actionType;
            RestAssured.baseURI = apiUrl;
            responseCode=0;
            System.out.println("**********Response for "+ actionType +"Action for All ID's ************\n");
            responseCode = getResponse(apiUrl).getStatusCode();
            return responseCode;
        }

        public int getCommentsBasedOnPostId_1(int postId){
            System.out.println("This Method is use for getting all the Comments details for API : /"+postsAction+"/"+postId+"/"+commentsAction);
            String apiUrl = apiEndPointUrl+postsAction+"/"+postId+"/"+commentsAction;
            RestAssured.baseURI = apiUrl;
            responseCode=0;
            System.out.println("****************** All Comments details of Post ID : "+postId+" ******************\n");
            responseCode = getResponse(apiUrl).getStatusCode();
            return responseCode;
        }

        //Get result based on PostID where api = apiEndPointUrl/comments?posts=ID

        public int getCommentsBasedOnPostId_2(int postId){
            System.out.println("This Method is use for getting all the Comments details for API : /"+commentsAction+"?"+postsAction+"=");
            String apiUrl = apiEndPointUrl+commentsAction+"?"+postsAction+"="+postId;
            RestAssured.baseURI = apiUrl;
            responseCode=0;
            responseCode = getResponse(apiUrl).getStatusCode();
            return responseCode;
        }

        //Get result based on UserID where api = apiEndPointUrl/posts/UserId
        public int getPostsAndAlbumBasedOnUserId(String actionType,int userId){
            System.out.println("This Method is use for getting all the "+actionType+" details for API : /"+actionType+"?"+userId);
            String apiUrl = apiEndPointUrl+actionType+"?"+userId;
            RestAssured.baseURI = apiUrl;
            responseCode=0;
            responseCode = getResponse(apiUrl).getStatusCode();
            return responseCode;
        }

    //We pass "" empty String in case if we don't want to pass any String value for Title and Body
    //Right now userId is mandatory to pass otherwise our test case will be failed
    // POST adds a random id to the object sent So, we are not passing id

        public String setPostData(String title, String body, int userIdIn){
            if(title !="" && body != "") {
                myJson = "{\"title\":\"" + title + "\",\"body\":\"" + body + "\",\"userId\":" + userIdIn + "}";
            }else if(title != ""){
                myJson = "{\"title\":\"" + title + "\",\"userId\":" + userIdIn + "}";
            }
            else if (body != ""){
                myJson = "{\"body\":\"" + body + "\",\"userId\":" + userIdIn + "}";
            }
        return myJson;
    }


		public int postRequest(String title, String body, int userId){
           myJson=setPostData(title, body, userId);
           Response response = given()
                        .contentType("application/json")
                        .body(myJson) //Passing the Data in Json format
                        .when()
                        .post();
            String getBody = response.getBody().toString();
            System.out.print("Json Body :\n"+myJson+"\n");
            System.out.print("Response Body : "+getBody+"\n");
            return response.getStatusCode();
		}


         public String setPutData(int id,String title, String body, int userId){
            if(title !="" && body != "") {
                 myJson = "{\"id\":"+id+",\"title\":\"" + title + "\",\"body\":\"" + body + "\",\"userId\":" + userId + "}";
             }else if(title != ""){
                 myJson = "{\"id\":"+id+",\"title\":\"" + title + "\",\"userId\":" + userId + "}";
             }else if (body != ""){
                myJson = "{\"id\":"+id+",\"body\":\"" + body + "\",\"userId\":" + userId + "}";
             }
        return myJson;
        }
        public int putRequest(int id,String title, String body, int userId){
            myJson=setPutData(id,title, body, userId);
            Response response = given()
                .contentType("application/json")
                .body(myJson) //Passing the Data in Json format
                .when()
                .put();
            String getBody = response.getBody().toString();
            System.out.print("Json Body :\n"+myJson+"\n");
            System.out.print("Response Body : "+getBody+"\n");
            return response.getStatusCode();
    }

}
