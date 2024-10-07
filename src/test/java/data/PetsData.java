package data;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.DataProvider;

import java.util.concurrent.ThreadLocalRandom;

public class PetsData {

    @DataProvider(name = "Pets Creation data")
    public Object[][] petCreationData(){
        int firstPetId = ThreadLocalRandom.current().nextInt(1, 50 + 1);
        String firstPetName = RandomStringUtils.randomAlphabetic(6);
        String firstPetStatus = "available";

        return new Object[][] { {String.valueOf(firstPetId), firstPetName, firstPetStatus} };
    }

}
