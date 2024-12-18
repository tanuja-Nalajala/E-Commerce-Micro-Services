package com.tanuja;

import com.tanuja.Model.Inventory;
import com.tanuja.Repo.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;

@SpringBootApplication

public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository){
		return args->{
			Inventory inventory=new Inventory();
			inventory.setSkuCode("Iphone-13");
			inventory.setQuantity(100);

			Inventory inventory1=new Inventory();
			inventory1.setSkuCode("Iphone-14");
			inventory1.setQuantity(200);

			Inventory inventory2=new Inventory();
			inventory2.setSkuCode("Iphone-15");
			inventory2.setQuantity(100);

			Inventory inventory3=new Inventory();
			inventory3.setSkuCode("Iphone-16");
			inventory3.setQuantity(100);

			Inventory inventory4=new Inventory();
			inventory4.setSkuCode("Ipad");
			inventory4.setQuantity(0);

			inventoryRepository.save(inventory);
			inventoryRepository.save(inventory1);
			inventoryRepository.save(inventory2);
			inventoryRepository.save(inventory3);
			inventoryRepository.save(inventory4);

		};
	}
}
