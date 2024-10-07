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
    private static final String APPLICATION_JSON = "application/json";
    private static final String APPLICATION_CONTENT_TYPE = "Content-Type";
    private RequestSpecification clientRequestSpecification = null;

    public ApiManager(String logLevel) {
        createRequestSpecification(getBaseUrl(), logLevel);
    }

    private RequestSpecification createRequestSpecification(String baseUri, String logLevel) {

        switch(logLevel) {
            case "all":
                this.clientRequestSpecification =  RestAssured.given().
                        log().all()
                        .baseUri(baseUri)
                        .config(RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation()))
                        .expect().log().all().request();
                break;
            case "headers":
                this.clientRequestSpecification =  RestAssured.given().
                        log().all()
                        .baseUri(baseUri)
                        .config(RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation()))
                        .expect().log().headers().request();
                break;
            case "body":
                this.clientRequestSpecification =  RestAssured.given().
                        log().all()
                        .baseUri(baseUri)
                        .config(RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation()))
                        .expect().log().body().request();
                break;
            default:
                this.clientRequestSpecification =  RestAssured.given().
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
                .header("accept", APPLICATION_JSON)
                .header(APPLICATION_CONTENT_TYPE, APPLICATION_JSON)
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
                .header("accept", APPLICATION_JSON)
                .header(APPLICATION_CONTENT_TYPE, APPLICATION_JSON)
                .put("/v2/pet");
    }

    public Response getPet(String petId) {
        return getClientRequestSpecification()
                .with()
                .header(APPLICATION_CONTENT_TYPE, APPLICATION_JSON)
                .get("/v2/pet/" + petId);
    }

    public Response deletePet(String petId) {
        return getClientRequestSpecification()
                .with()
                .header(APPLICATION_CONTENT_TYPE, APPLICATION_JSON)
                .delete("/v2/pet/" + petId);
    }
}
