package com.curso.modelo.negocio;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import com.curso.modelo.entidad.Cliente;
import com.curso.modelo.entidad.Comercial;
import com.curso.modelo.entidad.Sucursal;
import com.curso.modelo.negocio.excepcion.DireccionException;
import com.curso.modelo.negocio.excepcion.SucursalException;
import com.curso.modelo.persistencia.ClienteDao;
import com.curso.modelo.util.EmisorCorreosElectronicos;


public class ServicioClientesTest_Final {
	
	@Test
	@DisplayName("GestorClientes.altaCliente: Un cliente con datos correctos se insertará correctamente")
	public void altaClienteDatosCorrectos() throws Exception {
	
		//Dados
		Cliente cliente = new Cliente(null,"Bender Bending Rodriguez","NNY","555666"); //Premisa: estos datos SON CORRECTOS
		ServicioClientes gestorClientes = new ServicioClientes(); //Este es el objeto real que vamos a probar		
		
		//Y estos test doubles

		//Dummie:
		ServicioDirecciones gestorDirecciones = Mockito.mock(ServicioDirecciones.class); 
		
		//Stub:
		ServicioSucursales gestorSucursales = Mockito.mock(ServicioSucursales.class); //Inicialmente es un dummie
		Mockito
			.when(gestorSucursales.encontrarSucursalCercana(Mockito.any(String.class)))
			.thenReturn(new Sucursal(1,"Sucursal 1","C/Tocotó"));

		//Stub:
		ServicioComerciales gestorComerciales = Mockito.mock(ServicioComerciales.class);
		List<Comercial> comerciales = new ArrayList<Comercial>();
		comerciales.add(new Comercial(1,"EMP-1","Comercial1"));
		comerciales.add(new Comercial(2,"EMP-2","Comercial2"));	
		Mockito
			//.lenient()
			.when(gestorComerciales.encontrarComerciales())
			.thenReturn(comerciales);		
		
		//Stub:		
		ClienteDao clienteDao = Mockito.mock(ClienteDao.class); 
		Mockito
			.when(clienteDao.insertar(Mockito.any(Cliente.class)))
			.thenAnswer(invocation -> {
					Cliente cAux = (Cliente) invocation.getArgument(0);
					cAux.setId(1);
					return cAux;
				});
		
		//Dummie:
		EmisorCorreosElectronicos emisorCorreos = Mockito.mock(EmisorCorreosElectronicos.class); 
				
		//Le proporcionamos los test doubles a gestorClientes
		gestorClientes.setGestorDirecciones(gestorDirecciones);
		gestorClientes.setGestorSucursales(gestorSucursales);		
		gestorClientes.setGestorComerciales(gestorComerciales);
		gestorClientes.setClienteDao(clienteDao);
		gestorClientes.setEmisorCorreos(emisorCorreos);
		
		//CUANDO:
		Cliente clienteInsertado = gestorClientes.altaCliente(cliente);		
		
		//ENTONCES:
		System.out.println(clienteInsertado);
		assertAll( () -> assertEquals(2, clienteInsertado.getComerciales().size(),"El cliente no tiene comerciales!"),
				   () -> assertNotNull(clienteInsertado.getSucursal(),"El cliente no tiene sucursal!"),
				   () -> assertNotEquals(clienteInsertado.getSucursal().getNombre() ,"Sucursal virtual", "Al cliente no se le debe asignar la sucursal virtual"),
				   () -> assertSame(clienteInsertado, cliente),
				   () -> assertNotNull(clienteInsertado.getId(),"El cliente no tiene id!"));	
	}	
	
	@Test
	@DisplayName("GestorClientes.altaCliente: a cliente cuya direccion est� alejada de cualquier sucursal se le asignar� la sucursal virtual")
	public void altaClienteConDireccionAlejadaDeCualquierSucursal() throws Exception {
		
		//Dados
		Cliente cliente = new Cliente(null,"Bender Bending Rodriguez","El quinto pino","555666"); //Premisa: esta direcci�n no est� cerca de ninguna sucursal
		ServicioClientes gestorClientes = new ServicioClientes(); //Este es el objeto real que vamos a probar		
		
		//Y estos test doubles
		
		//Dummie:
		ServicioDirecciones gestorDirecciones = Mockito.mock(ServicioDirecciones.class); 
		
		//Dummie:
		ServicioSucursales gestorSucursales = Mockito.mock(ServicioSucursales.class); //Inicialmente es un dummie

		//Stub:
		ServicioComerciales gestorComerciales = Mockito.mock(ServicioComerciales.class);
		List<Comercial> comerciales = new ArrayList<Comercial>();
		comerciales.add(new Comercial(1,"EMP-1","Comercial1"));
		comerciales.add(new Comercial(2,"EMP-2","Comercial2"));	
		Mockito
			//.lenient()
			.when(gestorComerciales.encontrarComerciales())
			.thenReturn(comerciales);		
		
		//Stub:		
		ClienteDao clienteDao = Mockito.mock(ClienteDao.class); //Mockito tambien hace test doubles a partir de interfaces
		Mockito
			.when(clienteDao.insertar(Mockito.any(Cliente.class)))
			.thenAnswer(invocation -> {
					Cliente cAux = (Cliente) invocation.getArgument(0);
					cAux.setId(1);
					return cAux;
				});
		
		//Dummie:
		EmisorCorreosElectronicos emisorCorreos = Mockito.mock(EmisorCorreosElectronicos.class); 
		
		//Le proporcionamos los test doubles a gestorClientes
		gestorClientes.setGestorDirecciones(gestorDirecciones);
		gestorClientes.setGestorSucursales(gestorSucursales);		
		gestorClientes.setGestorComerciales(gestorComerciales);
		gestorClientes.setClienteDao(clienteDao);		
		gestorClientes.setEmisorCorreos(emisorCorreos);
		
		//CUANDO:
		Cliente clienteInsertado = gestorClientes.altaCliente(cliente);		
		
		//ENTONCES:
		System.out.println(clienteInsertado);
		assertAll( 
			() -> assertEquals(2, clienteInsertado.getComerciales().size(),"El cliente no tiene comerciales!"),
			() -> assertNotNull(clienteInsertado.getSucursal(),"El cliente no tiene sucursal!"),
			() -> assertEquals(clienteInsertado.getSucursal().getNombre(),"Sucursal virtual", "Al cliente no se le ha asignado la sucursal virtual"),
			() -> assertNotNull(clienteInsertado.getId(),"El cliente no tiene id!")
		);
				
	}
	
	@Test
	@DisplayName("GestorClientes.altaCliente: un cliente con la direccion falsa lanzar� una DireccionException")
	public void altaClienteTestDireccionFalsa() throws Exception {
		
		//Dados
		Cliente cliente = new Cliente(null,"Bender Bending Rodriguez","C/Falsa, 123","555666"); //Premisa: esta direccion es falsa
		ServicioClientes gestorClientes = new ServicioClientes(); //Este es el objeto real que vamos a probar		

		//Stub:
		ServicioDirecciones gestorDirecciones = Mockito.mock(ServicioDirecciones.class); 
		Mockito
			.doThrow(new DireccionException("Esta direccion es falsa"))
			.when(gestorDirecciones)
			.comprobarDireccion(Mockito.any(String.class));
			
		//Dummie:
		ServicioSucursales gestorSucursales = Mockito.mock(ServicioSucursales.class); 

		//Dummie:
		ServicioComerciales gestorComerciales = Mockito.mock(ServicioComerciales.class);
		
		//Dummie:
		ClienteDao clienteDao = Mockito.mock(ClienteDao.class); 
		
		//Dummie:
		EmisorCorreosElectronicos emisorCorreos = Mockito.mock(EmisorCorreosElectronicos.class); 
		
		//Le proporcionamos los test doubles a gestorClientes
		gestorClientes.setGestorDirecciones(gestorDirecciones);
		gestorClientes.setGestorSucursales(gestorSucursales);		
		gestorClientes.setGestorComerciales(gestorComerciales);
		gestorClientes.setClienteDao(clienteDao);		
		gestorClientes.setEmisorCorreos(emisorCorreos);		
		
		//CUANDO - ENTONCES:
		Exception e = assertThrows(DireccionException.class, () -> gestorClientes.altaCliente(cliente));
		assertEquals(e.getMessage(), "Esta direccion es falsa");
	
	}
	

	@Test
	@DisplayName("Comprobamos que gestorClientes.altaCliente realiza las llamadas correctas a sus dependecias")
	public void pruebaMOCKS() throws Exception {
						
		//Dados:
		List<Cliente> clientes = new ArrayList<>();
		clientes.add(new Cliente(null,"N1","D1","T1")); //Este es correcto
		//clientes.add(new Cliente(null,"N2",null,"T2")); //Este no
		//clientes.add(new Cliente(null,"N3","D3","T3")); //Este si
		//clientes.add(new Cliente(null,"N4","C/Falsa, 123","T4")); //Y este no
		//clientes.add(new Cliente(null,"N5","D5","T5")); //Y este si
						
		//Este es el objeto real que vamos a probar
		ServicioClientes gestorClientes = new ServicioClientes();
		
		// dir = null                -> Dirección nula exception
		// dir = C/Falsa, 123        -> Dirección falsa exception
		// dir = cualquier otra cosa -> nada
		
		//TestDoubles;
		ServicioDirecciones gestorDirecciones = Mockito.mock(ServicioDirecciones.class);		
		Mockito
			//.lenient()
			.doThrow(new DireccionException("Dirección nula"))
			.when(gestorDirecciones)
			.comprobarDireccion(null);
		Mockito
			//.lenient()
			.doThrow(new DireccionException("La dirección es falsa"))
			.when(gestorDirecciones)
			.comprobarDireccion("C/Falsa, 123");
		
		ServicioSucursales gestorSucursales = Mockito.mock(ServicioSucursales.class);	
		Mockito
			.when(gestorSucursales.encontrarSucursalCercana(Mockito.any(String.class)))
			.thenReturn(new Sucursal(1,"Sucursal 1","C/Tocotó"));
		
		ServicioComerciales gestorComerciales = mock(ServicioComerciales.class);
		List<Comercial> comerciales = new ArrayList<Comercial>();
		comerciales.add(new Comercial(1,"EMP-1","Comercial1"));
		comerciales.add(new Comercial(2,"EMP-2","Comercial2"));	
		Mockito
			.when(gestorComerciales.encontrarComerciales())
			.thenReturn(comerciales);
		
		ClienteDao clienteDao = Mockito.mock(ClienteDao.class);
		Mockito
			.when(clienteDao.insertar(Mockito.any(Cliente.class)))
			.thenAnswer( 
			  invocation -> {
				System.out.println("llamada a clienteDao.insertar");
				Cliente cli = invocation.getArgument(0, Cliente.class);
				cli.setId(42);
				return cli;
			});
		
		//Dummie:
		EmisorCorreosElectronicos emisorCorreos = Mockito.mock(EmisorCorreosElectronicos.class); 
		
		gestorClientes.setClienteDao(clienteDao);
		gestorClientes.setGestorComerciales(gestorComerciales);
		gestorClientes.setGestorDirecciones(gestorDirecciones);
		gestorClientes.setGestorSucursales(gestorSucursales);
		gestorClientes.setEmisorCorreos(emisorCorreos);
		
		//Cuando:
		gestorClientes.altaClientes(clientes);

		//Entonces:
		
		//Los test doubles de Mockito guardan como estado las llamadas que han 
		//recibido, el número y el orden de las mismas
		//
		//Si usamos esta característica de mockito en un test este deja de ser un test unitario como tal
		//y se convierte en una especie de test de integración
		//
		//Y también se convierte en una especie de test de caja blanca
		//
		//En un test de este tipo no tienen cabida asertos como los de los test unitarios
		//			
		
		//Verificamos que gestorClientes ha llamado a los métodos adecuados de sus dependecias
		//Esto es posible porque los mocks recuerdan las llamadas recibidas
				
		/*
		Mockito.verify(clienteDao, Mockito.times(3)).insertar(Mockito.any(Cliente.class));
		Mockito.verify(gestorDirecciones, Mockito.times(5)).comprobarDireccion(Mockito.any());			
		Mockito.verify(gestorComerciales, Mockito.times(3)).encontrarComerciales();
		Mockito.verify(gestorSucursales, Mockito.times(1)).encontrarSucursalCercana("D5"); 
		//Cuando queremos verificar que se ha llamado una única vez podemos escribirlo así
		Mockito.verify(gestorSucursales).encontrarSucursalCercana("D5"); //Verifica que se ha llamado UNA vez
		*/
		
		
		//Podemos verificar el orden de las llamadas
		//Este ejemplo no funciona si en la lista de clientes tenemos más de uno
		//El orden en el que se añaden los doubles da igual
		InOrder ordered = Mockito.inOrder(gestorDirecciones, gestorSucursales, clienteDao, gestorComerciales, emisorCorreos); 
		
		ordered.verify(gestorDirecciones).comprobarDireccion(Mockito.any(String.class));
	    ordered.verify(gestorSucursales).encontrarSucursalCercana(Mockito.any(String.class));
	    ordered.verify(gestorComerciales).encontrarComerciales();
	    ordered.verify(clienteDao).insertar(Mockito.any(Cliente.class));
	    ordered.verify(emisorCorreos).enviarCorreo(Mockito.any(String.class),Mockito.any(String.class));
	
	}		
	
	
}









