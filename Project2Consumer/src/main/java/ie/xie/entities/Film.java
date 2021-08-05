package ie.xie.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Film {
	private int filmId;
	private String filmName;
	private int releaseDate;
	private Director filmDirected;
}
