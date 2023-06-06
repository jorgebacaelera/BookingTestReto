package pe.belcorp.creditcard.orq.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Bookingdates {
    public String checkin;
    public String checkout;
}
