package utilities;

import com.github.javafaker.Faker;

public class JavaFaker {

    Faker faker = new Faker();

    public String getFirstName(){
        return faker.name().firstName();
    }

    public String getLastName(){
        return faker.name().lastName();
    }

    public String  getPostalCode(){
        return faker.address().zipCode();
    }

}
