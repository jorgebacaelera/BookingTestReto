package pe.belcorp.creditcard.orq.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor

public class Booking {
    public String firstname;
    public String lastname;
    public Integer totalprice;
    public String depositpaid;
    public  Bookingdates bookingdates;
    public String additionalneeds;


}

