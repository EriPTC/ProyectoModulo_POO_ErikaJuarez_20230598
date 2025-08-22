package ErikaJuarez_2030598.ProyectoModulo_ErikaJuarez_2030598;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ProyectoModuloErikaJuarez2030598Application.class);
	}

}
