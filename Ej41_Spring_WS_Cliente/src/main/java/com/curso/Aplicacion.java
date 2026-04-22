package com.curso;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.curso.configuracion.ClienteSoapConfig;
import com.curso.soap.cliente.ClienteSoapClient;
import com.curso.webservice.clientes.ClienteDTO;

@SpringBootApplication
public class Aplicacion implements CommandLineRunner {

    private final ClienteSoapConfig clienteSoapConfig;
	
	@Autowired
	private ClienteSoapClient clienteSoap;

    Aplicacion(ClienteSoapConfig clienteSoapConfig) {
        this.clienteSoapConfig = clienteSoapConfig;
    }
	
	public static void main(String[] args) {
		SpringApplication.run(Aplicacion.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		ClienteDTO c1 = new ClienteDTO();
		c1.setId(1);
		c1.setNombre("N1");
		c1.setDireccion("D1");
		c1.setTelefono("T1");
		ClienteDTO c2 = new ClienteDTO();
		c2.setId(2);
		c2.setNombre("N2");
		c2.setDireccion("D2");
		c2.setTelefono("T2");
		ClienteDTO c3 = new ClienteDTO();
		c3.setId(3);
		c3.setNombre("N3");
		c3.setDireccion("D3");
		c3.setTelefono("T3");
		
		clienteSoap.altaCliente(c1);
		clienteSoap.altaCliente(c2);
		clienteSoap.altaCliente(c3);
		
		List<ClienteDTO> clientes = clienteSoap.listarClientes();
		clientes.forEach( c -> System.out.println(c.getNombre()) );
		
	}

}
