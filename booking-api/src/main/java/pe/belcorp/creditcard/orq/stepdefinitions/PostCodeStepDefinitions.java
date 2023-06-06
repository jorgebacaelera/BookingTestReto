package pe.belcorp.creditcard.orq.stepdefinitions;


import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;
import cucumber.api.java.es.Y;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;

import static io.restassured.path.json.JsonPath.from;
import static net.serenitybdd.rest.SerenityRest.*;


public class PostCodeStepDefinitions {

    public static String firstname;
    public static String lastname;
    public static String Date1;
    public static String Date2;
    public static String totalprice;
    public static String depositpaid;
    public static String additionalneeds;
    public static String bookingID="";
    public static String nameUpdate="";
    public static String lastnameUpdate="";




    @Steps
    PostCodeAPI postCodeAPI;

    @Dado("ingreso usuario y clave")
    public void ingreso_usuario_admin_y_clave_password123() {
        postCodeAPI.fetchLocationByPostCodeAndCountry();
    }

    @Entonces("valido token")
    public void validoToken() {
        postCodeAPI.fetchLocation();

    }

    @Dado("obtengo id")
    public void obtengoId() {
        postCodeAPI.ObtengoID();
    }

    @Entonces("valido numero de id")
    public void validoNumeroDeId() {
        postCodeAPI.ValidoID();
    }

    @Dado("obtengo Ping")
    public void obtengoPing() {
        postCodeAPI.ObtengoPing();
    }

    @Entonces("valido numero de Ping")
    public void validoNumeroDePing() {
        postCodeAPI.ValidoPing();
    }

    @Dado("que ingreso una solicitud de booking")
    public void queIngresoUnaSolicitudDeBooking() {

        postCodeAPI.fetchLocationByPostCodeAndCountry();
    }


    @Cuando("completo el campo firstname (.*)")
    public void completoElCampoNombreNOMBRE(String nombre) {
        firstname = nombre;

    }

    @Y("completo el campo Apellido (.*)")
    public void completoElCampoApellidoAPELLIDO(String apellido) {
        lastname = apellido;
    }

    @Y("completo el campo Precio (.*)")
    public void completoElCampoPrecioPRECIO(String precio) {
        totalprice = precio;
    }

    @Y("completo el campo Depósito (.*)")
    public void completoElCampoDepósitoDEPOSITOP(String Depositpaid) {
        depositpaid = Depositpaid;
    }

    @Y("completo el campo Fecha reserva (.*) fecha de pago (.*)")
    public void completoElCampoFechaReservaFECHARFechaDePagoFECHAP(String chekin, String checkout) {
        Date1 = chekin;
        Date2 = checkout;
    }

    @Y("completo el campo adicionales (.*)")
    public void completoElCampoAdicionalesADICIONALES(String Additionalneeds) {
        additionalneeds = Additionalneeds;
    }

    @Y("se envia petición")
    public void seEnviaSolicitud() {
        PostCodeAPI postCodeAPI1 = new PostCodeAPI();
        postCodeAPI1.ObtengoURl(this.firstname,
                this.lastname,
                this.totalprice,
                this.depositpaid,
                this.Date1,
                this.Date2,
                this.additionalneeds);
        String bodyResponse = lastResponse().getBody().asString();
        String id = from(bodyResponse).get("bookingid").toString();
        //String url="https://restful-booker.herokuapp.com/booking/"+id;
        bookingID=id;
    }


    @Entonces("verificamos que el registro exista")
    public void verificarRegistro() {
        postCodeAPI.consultarRegistro(bookingID);

    }
    @Dado("que tengo un registro de reserva en Booking")
    public void registraReserva(){

            PostCodeAPI postCodeAPI1 = new PostCodeAPI();
            postCodeAPI1.ObtengoURl("Jorge","Baca","500","true","2022-01-01","2023-01-01","COMIDA");
            String bodyResponse = lastResponse().getBody().asString();
            String id = from(bodyResponse).get("bookingid").toString();
            bookingID=id;

    }



    @Cuando("actualizo el registro")
    public void actualizarRegistro(){
        postCodeAPI.fetchLocationByPostCodeAndCountry();
        PostCodeAPI postCodeAPI1 = new PostCodeAPI();
        postCodeAPI1.actualizarRegistro("Jorge","Baca","500","true","2022-01-01","2023-01-01","COMIDA");

    }

    @Cuando("modifico el registro parcialmente")
    public void modificarRegistroParcial(){
        postCodeAPI.fetchLocationByPostCodeAndCountry();
        PostCodeAPI postCodeAPI1 = new PostCodeAPI();
        String nombre="Jorge";
        String apellido="Baca";
        nameUpdate=nombre;
        lastnameUpdate=apellido;
        postCodeAPI1.modificarparcialRegistro(nameUpdate,lastnameUpdate);

    }

    @Cuando("elimino el registro")
    public void eliminarRegistro(){
        postCodeAPI.fetchLocationByPostCodeAndCountry();
        PostCodeAPI postCodeAPI1 = new PostCodeAPI();
        postCodeAPI1.eliminarRegistro(bookingID);

    }

    @Entonces("verificamos que el registro se haya actualizado")
    public void verificarRegistroActualizado(){
        postCodeAPI.consultarRegistro(bookingID);

    }

    @Entonces("verificamos que el registro no exista")
    public void verificarRegistroEliminado(){
        postCodeAPI.consultarRegistroEliminado(bookingID);

    }

    @Entonces("verificamos que el registro se haya modificado parcialmente")
    public void verificarRegistroModificado(){
        postCodeAPI.consultarRegistroParcial(bookingID);

    }

}
