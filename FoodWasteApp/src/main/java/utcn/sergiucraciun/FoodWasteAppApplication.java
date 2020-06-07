package utcn.sergiucraciun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("FoodWasteApp")
public class FoodWasteAppApplication {

	public static void main(String[] args) {

		SpringApplication.run(FoodWasteAppApplication.class, args);
	}

}
