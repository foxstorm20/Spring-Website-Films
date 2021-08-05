package ie.niall.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Household {
	private int houseId;
	private String Eircode;
	private String address;
}
