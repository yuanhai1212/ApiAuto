package department;


import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.given;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Demo_01_base {
    private static final Logger logger=  LoggerFactory.getLogger(Demo_01_base.class);
    static String accessToke;
    static String departmentID;



    @BeforeAll
    public  static void getAccessToken(){
        accessToke=given().log().all()
                .when()
                .param("corpid","ww7a47a90c451e713a")
                .param("corpsecret","Qi1Ofi1RTIs1p0tRi0pEGMFqd3yL7utSe9OV6DqQVUU")
                .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                .then().log().all().extract().response().path("access_token");

    }
    
    @DisplayName("创建部门")
    @Test
    @Order(1)
    public  void createDepartment(){
        logger.info(accessToke);
        String Body="{\n" +
                "   \"name\": \"广州研发中心\",\n" +
                "   \"name_en\": \"RDGZ\",\n" +
                "   \"parentid\": 1,\n" +
                "   \"order\": 1,\n" +
                "   \"id\": 2\n" +
                "}\n" +
                "\n";
        Response response=given().log().all()
                .contentType("application/json")
                .queryParam("access_token",accessToke)
                .body(Body)
                .when()
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/create")
                .then().log().all().extract().response();
        departmentID=response.path("parentid");

    }

    @DisplayName("修改部门")
    @Test
    @Order(2)
    public  void updateDepartment(){
        String Body="{\n" +
                "   \"id\": 2,\n" +
                "   \"name\": \"广州研发中心1\",\n" +
                "   \"name_en\": \"RDGZ\",\n" +
                "   \"parentid\": "+departmentID+",\n" +
                "   \"order\": 1\n" +
                "}\n" +
                "\n";
        Response response=given().log().all()
                .contentType("application/json")
                .queryParam("access_token",accessToke)
                .body(Body)
                .when()
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/update")
                .then().log().all().extract().response();
        departmentID=response.path("parentid");

    }


    @DisplayName("查询部门")
    @Test
    @Order(3)
    public  void searchDepartment(){
        Response response=given().log().all()
                .contentType("application/json")
                .param("access_token",accessToke)
                .param("id","2")
                .when()
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/list")
                .then().log().all().extract().response();

    }

    @DisplayName("删除部门")
    @Test
    @Order(4)
    public  void DeleteDepartment(){
        Response response=given().log().all()
                .contentType("application/json")
                .param("access_token",accessToke)
                .param("id","2")
                .when()
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/delete")
                .then().log().all().extract().response();

    }
}
