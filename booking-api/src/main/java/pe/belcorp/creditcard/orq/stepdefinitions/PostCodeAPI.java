package pe.belcorp.creditcard.orq.stepdefinitions;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import net.serenitybdd.rest.SerenityRest;
import pe.belcorp.creditcard.orq.model.Booking;
import pe.belcorp.creditcard.orq.model.Bookingdates;
import pe.belcorp.creditcard.orq.steps.LocationResponse;

import static io.restassured.path.json.JsonPath.from;
import static net.serenitybdd.rest.SerenityRest.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static pe.belcorp.creditcard.orq.stepdefinitions.PostCodeStepDefinitions.*;


public class PostCodeAPI {

    public static String tokenBooking="";

    public void fetchLocationByPostCodeAndCountry() {
        SerenityRest.given()
                .body(LocationResponse.Name)
                .contentType("application/json");
        SerenityRest.when().post("https://restful-booker.herokuapp.com/auth");
    }

    public void fetchLocation() {
        restAssuredThat(response -> response.statusCode(200));
        System.out.println("el Token es = " + SerenityRest.lastResponse().jsonPath().getString("token"));
        String token= SerenityRest.lastResponse().jsonPath().getString("token");
        tokenBooking=token;
    }

    public void ObtengoID() {
        SerenityRest.given()
                .contentType("application/json");
        SerenityRest.when().get("https://restful-booker.herokuapp.com/booking");
    }

    public void ValidoID() {
        restAssuredThat(response -> response.statusCode(200));
        System.out.println("el ID es = " + SerenityRest.lastResponse().jsonPath().getString("bookingid"));

    }

    public void ObtengoPing() {
        SerenityRest.when().get("https://restful-booker.herokuapp.com/ping");
    }

    public void ValidoPing() {
        RestAssured.registerParser("Created", Parser.JSON);
        //   Assert.assertTrue( response -> response.statusCode(200));
        System.out.println(SerenityRest.lastResponse().htmlPath().getString("Created"));
    }

    public void ObtengoURl(String firstname, String lastname, String totalprice, String depositpaid, String checkin, String checkout, String additionalneeds) {
        PostCodeStepDefinitions pos = new PostCodeStepDefinitions();
        Bookingdates bookingdates1 = Bookingdates.builder()
                .checkin(checkin)
                .checkout(checkout)
                .build();

        Booking booking = Booking.builder()
                .firstname(firstname)
                .lastname(lastname)
                .totalprice(Integer.valueOf(totalprice))
                .depositpaid(depositpaid)
                .bookingdates(bookingdates1)
                .additionalneeds(additionalneeds)
                .build();

        SerenityRest.given()
                .contentType("application/json")
                .body(booking);
        System.out.println(booking);
        SerenityRest.when().post("https://restful-booker.herokuapp.com/booking");
        restAssuredThat(response -> response.statusCode(200));


    }

    public void actualizarRegistro(String firstname, String lastname, String totalprice, String depositpaid, String checkin, String checkout, String additionalneeds) {
        PostCodeStepDefinitions pos = new PostCodeStepDefinitions();
        Bookingdates bookingdates1 = Bookingdates.builder()
                .checkin(checkin)
                .checkout(checkout)
                .build();

        Booking booking = Booking.builder()
                .firstname(firstname)
                .lastname(lastname)
                .totalprice(Integer.valueOf(totalprice))
                .depositpaid(depositpaid)
                .bookingdates(bookingdates1)
                .additionalneeds(additionalneeds)
                .build();

        SerenityRest.given()
                .contentType("application/json")
                .header("Cookie","token="+tokenBooking)
                .body(booking);
        System.out.println(booking);
        SerenityRest.when().put("https://restful-booker.herokuapp.com/booking/"+""+bookingID);
        restAssuredThat(response -> response.statusCode(200));

    }

    public void modificarparcialRegistro(String firstname, String lastname) {
        PostCodeStepDefinitions pos = new PostCodeStepDefinitions();


        Booking booking = Booking.builder()
                .firstname(firstname)
                .lastname(lastname)
                .build();

        SerenityRest.given()
                .contentType("application/json")
                .header("Cookie","token="+tokenBooking)
                .body(booking);
        System.out.println(booking);
        SerenityRest.when().patch("https://restful-booker.herokuapp.com/booking/"+""+bookingID);
        restAssuredThat(response -> response.statusCode(200));

    }


    public void consultarRegistro(String idBokking) {
        PostCodeStepDefinitions get = new PostCodeStepDefinitions();

        SerenityRest.given();
        SerenityRest.when().get("https://restful-booker.herokuapp.com/booking/"+idBokking);
        restAssuredThat(response -> response.statusCode(200));
        String bodyResponse = lastResponse().getBody().asString();
       String nameBooking= from(bodyResponse).get("firstname").toString();

    }

    public void consultarRegistroParcial(String idBokking) {
        PostCodeStepDefinitions get = new PostCodeStepDefinitions();

        SerenityRest.given();
        SerenityRest.when().get("https://restful-booker.herokuapp.com/booking/"+idBokking);
        restAssuredThat(response -> response.statusCode(200));
        String bodyResponse = lastResponse().getBody().asString();
        String nameBooking= from(bodyResponse).get("firstname").toString();
        String lastnameBooking= from(bodyResponse).get("lastname").toString();
        assertThat(nameBooking, equalTo(nameUpdate));
        assertThat(lastnameBooking, equalTo(lastnameUpdate));
    }

    public void eliminarRegistro(String idBokking) {
        PostCodeStepDefinitions get = new PostCodeStepDefinitions();

        SerenityRest.given()
                .contentType("application/json")
                .header("Cookie","token="+tokenBooking);
        SerenityRest.when().delete("https://restful-booker.herokuapp.com/booking/"+idBokking);
        restAssuredThat(response -> response.statusCode(201));
        String bodyResponse = lastResponse().getBody().asString();
       // String nameBooking= from(bodyResponse).get("firstname").toString();


    }

    public void consultarRegistroEliminado(String idBokking) {
        PostCodeStepDefinitions get = new PostCodeStepDefinitions();

        SerenityRest.given();
        SerenityRest.when().get("https://restful-booker.herokuapp.com/booking/"+idBokking);
        restAssuredThat(response -> response.statusCode(404));
        String bodyResponse = lastResponse().getBody().asString();
        //String nameBooking= from(bodyResponse).get("firstname").toString();

    }


}




