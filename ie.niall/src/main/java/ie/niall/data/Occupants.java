package ie.niall.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Occupants {
	private int occupantId;
	private String Name;
	private int Age;
	private String Occupation;
	private String eircode;
}
