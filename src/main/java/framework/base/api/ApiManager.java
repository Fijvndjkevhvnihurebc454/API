package framework.base.api;

import framework.entity.Category;
import framework.entity.TagsItem;
import framework.entity.request.PetsCreationUpdateRequest;
import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.List;

import static framework.base.BaseTest.getBaseUrl;

public class ApiManager {
    private RequestSpecification clientRequestSpecification = null;

    public ApiManager() {
        createRequestSpecification(getBaseUrl(), "all");
    }

    private RequestSpecification createRequestSpecification(String baseUri, String logLevel) {

        switch(logLevel) {
            case "all":
                clientRequestSpecification =  RestAssured.given().
                        log().all()
                        .baseUri(baseUri)
                        .config(RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation()))
                        .expect().log().all().request();
                break;
            case "headers":
                clientRequestSpecification =  RestAssured.given().
                        log().all()
                        .baseUri(baseUri)
                        .config(RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation()))
                        .expect().log().headers().request();
                break;
            case "body":
                clientRequestSpecification =  RestAssured.given().
                        log().all()
                        .baseUri(baseUri)
                        .config(RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation()))
                        .expect().log().body().request();
                break;
            default:
                clientRequestSpecification =  RestAssured.given().
                        log().all()
                        .baseUri(baseUri)
                        .config(RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation()))
                        .expect().request();
        }

        return clientRequestSpecification;
    }

    public RequestSpecification getClientRequestSpecification() {
        return clientRequestSpecification;
    }

    public Response createPet(String petId, String petName, String petStatus) {
        return getClientRequestSpecification()
                .body(PetsCreationUpdateRequest.builder()
                        .photoUrls(List.of("test url"))
                        .name(petName)
                        .id(Integer.parseInt(petId))
                        .category(Category.builder().id(Integer.parseInt(petId)).name(petName).build())
                        .tags(List.of(TagsItem.builder().id(Integer.parseInt(petId)).name(petName).build()))
                        .status(petStatus)
                        .build())
                .with()
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .post("/v2/pet");
    }

    public Response updatePet(String petId, String petName, String petStatus) {
        return getClientRequestSpecification()
                .body(PetsCreationUpdateRequest.builder()
                        .photoUrls(List.of("test url"))
                        .name(petName)
                        .id(Integer.parseInt(petId))
                        .category(Category.builder().id(Integer.parseInt(petId)).name(petName).build())
                        .tags(List.of(TagsItem.builder().id(Integer.parseInt(petId)).name(petName).build()))
                        .status(petStatus)
                        .build())
                .with()
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .put("/v2/pet");
    }

    public Response getPet(String petId) {
        return getClientRequestSpecification()
                .with()
                .header("Content-Type", "application/json")
                .get("/v2/pet/" + petId);
    }

    public Response deletePet(String petId) {
        return getClientRequestSpecification()
                .with()
                .header("Content-Type", "application/json")
                .delete("/v2/pet/" + petId);
    }
}
