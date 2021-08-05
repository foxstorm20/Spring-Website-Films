package ie.xie;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Component
public class ThymeleafBeans {
	public String removeLangFromUrl() {
		return ServletUriComponentsBuilder.fromCurrentRequest().replaceQueryParam("lang").toUriString();
	}
}
