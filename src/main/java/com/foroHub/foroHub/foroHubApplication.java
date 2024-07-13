package com.foroHub.foroHub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;

@SpringBootApplication
public class foroHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(foroHubApplication.class, args);
	}

	@Bean
	public CommandLineRunner run() {
		return args -> {
			Scanner scanner = new Scanner(System.in);
			while (true) {
				System.out.println("Seleccione una opción:");
				System.out.println("1. Listar todos los tópicos");
				System.out.println("2. Crear un nuevo tópico");
				System.out.println("3. Eliminar un tópico existente");
				System.out.println("4. Salir");

				int option = scanner.nextInt();
				scanner.nextLine(); // Consume newline

				switch (option) {
					case 1:
						// Lógica para listar todos los tópicos
						break;
					case 2:
						// Lógica para crear un nuevo tópico
						break;
					case 3:
						// Lógica para eliminar un tópico existente
						break;
					case 4:
						System.exit(0);
						break;
					default:
						System.out.println("Opción no válida");
						break;
				}
			}
		};
	}
}

