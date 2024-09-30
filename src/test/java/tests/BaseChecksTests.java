package tests;

import data.PetsData;
import framework.base.BaseTest;
import framework.base.api.ApiManager;
import framework.entity.Category;
import framework.entity.TagsItem;
import framework.entity.request.PetsCreationUpdateRequest;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;


import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BaseChecksTests extends BaseTest {


    @Test(dataProvider = "Pets Creation data", dataProviderClass = PetsData.class)
    public void checkPetCreationDeletionTest(String petId, String petName, String petStatus) {
        ApiManager apiManager = new ApiManager();
        Response petCreationResponse = apiManager.createPet(petId, petName, petStatus);

        assertThat(petCreationResponse.getStatusCode()).as("Status code for creation is not correct").isEqualTo(200);
        assertThat(JsonPath.from(petCreationResponse.asString()).getString("id")).as("Pets ID for creation is not correct").isEqualTo(petId);
        assertThat(JsonPath.from(petCreationResponse.asString()).getString("name")).as("Pets Name for creation is not correct").isEqualTo(petName);
        assertThat(JsonPath.from(petCreationResponse.asString()).getString("status")).as("Pets Status for creation is not correct").isEqualTo(petStatus);

        Response petGetResponse = apiManager.getPet(petId);

        assertThat(JsonPath.from(petGetResponse.asString()).getString("id")).as("Pets ID is not correct").isEqualTo(petId);
        assertThat(JsonPath.from(petGetResponse.asString()).getString("name")).as("Pets Name is not correct").isEqualTo(petName);
        assertThat(JsonPath.from(petGetResponse.asString()).getString("status")).as("Pets Status is not correct").isEqualTo(petStatus);

        Response petDeletionResponse = apiManager.deletePet(petId);

        assertThat(petDeletionResponse.getStatusCode()).as("Status code for deletion is not correct").isEqualTo(200);

        Response response = apiManager.getPet(petId);

        assertThat(response.getStatusCode()).as("Status code for getting deleted pet is not correct").isEqualTo(404);
    }

    @Test(dataProvider = "Pets Creation data", dataProviderClass = PetsData.class)
    public void checkPetUpdateTest(String petId, String petName, String petStatus) {
        ApiManager apiManager = new ApiManager();
        Response petCreationResponse = apiManager.createPet(petId, petName, petStatus);

        assertThat(petCreationResponse.getStatusCode()).as("Status code for creation is not correct").isEqualTo(200);
        assertThat(JsonPath.from(petCreationResponse.asString()).getString("id")).as("Pets ID for creation is not correct").isEqualTo(petId);
        assertThat(JsonPath.from(petCreationResponse.asString()).getString("name")).as("Pets Name for creation is not correct").isEqualTo(petName);
        assertThat(JsonPath.from(petCreationResponse.asString()).getString("status")).as("Pets Status for creation is not correct").isEqualTo(petStatus);

        int newPetId = ThreadLocalRandom.current().nextInt(1, 50 + 1);
        String newPetName = RandomStringUtils.randomAlphabetic(6);
        String newPetStatus = "sold";

        Response petUpdateResponse = apiManager.updatePet(String.valueOf(newPetId), newPetName, newPetStatus);

        assertThat(petUpdateResponse.getStatusCode()).as("Status code for update is not correct").isEqualTo(200);
        assertThat(JsonPath.from(petUpdateResponse.asString()).getString("id")).as("Pets ID for update is not correct").isEqualTo(String.valueOf(newPetId));
        assertThat(JsonPath.from(petUpdateResponse.asString()).getString("name")).as("Pets Name for update is not correct").isEqualTo(newPetName);
        assertThat(JsonPath.from(petUpdateResponse.asString()).getString("status")).as("Pets Status for update is not correct").isEqualTo(newPetStatus);

        Response petDeletionResponse = apiManager.deletePet(String.valueOf(newPetId));

        assertThat(petDeletionResponse.getStatusCode()).as("Status code for deletion is not correct").isEqualTo(200);

        Response response = apiManager.getPet(String.valueOf(newPetId));

        assertThat(response.getStatusCode()).as("Status code for getting deleted pet is not correct").isEqualTo(404);
    }
}
