package com.curso.modelo.negocio;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.curso.modelo.entidad.Cliente;
import com.curso.modelo.entidad.DetallePedido;
import com.curso.modelo.entidad.Pedido;
import com.curso.modelo.entidad.Producto;
import com.curso.modelo.negocio.excepcion.DatosBancariosEx;
import com.curso.modelo.negocio.excepcion.ExistenciasEx;
import com.curso.modelo.persistencia.PedidoDao;

@ExtendWith(MockitoExtension.class)
public class GestorPedidosTest_Final {
	
	//MOCKS. Ya tenemos dummies aqui:
	@Mock private PedidoDao pedidoDao;
	@Mock private GestorBancos gestorBancos; 
	@Mock private GestorAlmacen gestorAlmacen; 
	@Mock private GestorTransportes gestorTransportes; 
	@Mock private GestorOfertas gestorOfertas; 	

	//Este será el objeto real que vamos a probar
	@InjectMocks
	private GestorPedidos gestorPedidos = new GestorPedidos();
	
	//Este pedido se usará para las diferentes pruebas:
	private Pedido pedido;
	private Producto p3; 	
	
	//Método que inicializa el pedido
	private void crearPedido() {		
		Producto p1 = new Producto(null, "P1", "F1", 25d, 1000);
		Producto p2 = new Producto(null, "P2", "F2", 50d, 1000);
		p3 = new Producto(null, "P5", "F5", 125d, 1000);

		Cliente c1 = new Cliente(2, "Mongomery Burns", "Su mansión", "123", 9999);
		
		pedido = new Pedido(1, "PED-0", LocalDate.now(), "PENDIENTE", c1, null);
		List<DetallePedido> detalles2 = new ArrayList<>();
		detalles2.add(new DetallePedido(3, pedido, p1, 25d, 25));
		detalles2.add(new DetallePedido(4, pedido, p2, 75d, 25));
		detalles2.add(new DetallePedido(5, pedido, p3, 125d, 25));
		pedido.setDetalles(detalles2);		
	}	
	
	@BeforeEach
	public void inicializar() {
		crearPedido();
	}	
		
	@Test
	@DisplayName("GestorPedidos.aceptar funciona cuando el pedido es correcto")
	public void aceptarPedido() throws Exception {
		
		//DADOS
		//Premisa: 
		//El pedido cuyo id es 1:
		//-tiene un cliente con los datos bancarios correctos
		//-tiene unos detalles cuyas cantidades se pueden satisfacer en el almacén
		//Y
		//-hay camiones disponibles
		//-hay perritos pilotos para parar un tren
		Integer idPedido = 1;  
		
		//Y estos test doubles

		//PedidoDao: Stub
		Mockito
			.when(pedidoDao.buscar(any(Integer.class)))
			.thenReturn(pedido);
		
		//GestorBancos: Dummie		
		//GestorAlmacen: Dummie
		
		//GestorTransportes: Stub
		Mockito
			.when(gestorTransportes.obtenerCamion(Mockito.any(Boolean.class)))
			.thenReturn("MOOOC MOOOOOOC!!!!!");

		//GestorOfertas: Stub
		Mockito
			.when(gestorOfertas.obtenerPerritoPiloto(any(Boolean.class)))
			.thenReturn("Perrito piloto");
		
		//CUANDO
		Pedido pedidoAceptado = gestorPedidos.aceptar(idPedido);
		
		//ENTONCES
		assertAll(
				() -> assertNotNull(pedidoAceptado.getCamion(),"El pedido no tiene camión"),
				() -> assertNotNull(pedidoAceptado.getRegalo(),"El pedido no tiene regalo"),
				//() -> Assertions.assertNotNull(pedidoAceptado.getFactura(),"El pedido no tiene factura"),
				() -> assertEquals("ACEPTADO", pedidoAceptado.getEstado(),"El pedido no tiene estado 'ACEPTADO'")
			);			
	}
	
	//@Test
	@DisplayName("GestorPedidos.aceptar lanza datosBancarios exception cuando hay un problema con los datos bancarios del cliente")
	public void aceptarPedidoDatosBancariosMal() throws Exception {
		
		//DADOS
		//Premisa: 
		//El pedido cuyo id es 1:
		//-tiene un cliente con los datos bancarios MAL
		//-tiene unos detalles cuyas cantidades se pueden satisfacer en el almacén
		Integer idPedido = 1;  
		
		//Y estos test doubles
		//PedidoDao: Stub
		Mockito
			.when(pedidoDao.buscar(Mockito.any(Integer.class)))
			.thenReturn(pedido);		
		
		//GestorBancos: Stub
		Mockito
			.doThrow(new DatosBancariosEx("Datos bancarios incorrectos"))
			.when(gestorBancos)
			.comprobarTC(Mockito.any(Integer.class));
		
		//GestorAlmacen: Dummie (tambien se podría quedar a nulo)
		//GestorTransportes: Dummie (tambien se podría quedar a nulo)
		//GestorOfertas: Dummie (tambien se podría quedar a nulo)
		
		//CUANDO + ENTONCES:		
		Exception e = assertThrows(
				DatosBancariosEx.class, 
				() -> gestorPedidos.aceptar(idPedido), 
				"No se la lanzado DatosBancariosEx"
			);
		assertEquals("Datos bancarios incorrectos", e.getMessage());	
	}
	
	//@Test
	@DisplayName("GestorPedidos.aceptar lanza ExistenciasException cuando no hay existencias de un producto...")
	public void aceptarPedidoExistenciasInsuficientes() throws ExistenciasEx {
		
		//DADOS
		//Premisa: 
		//El pedido cuyo id es 1:
		//-tiene un cliente con los datos bancarios BIEN
		//-tiene unos detalles cuyas cantidades NO se pueden satisfacer en el almacén
		Integer idPedido = 1;
		
		//Test doubles
		
		//PedidoDao: stub
		Mockito
			.when(pedidoDao.buscar(idPedido))
			.thenReturn(pedido);
		
		//GestorBancos: dummie

		//GestorAlmacen: stub
		Mockito
			.doThrow(new ExistenciasEx())
			.when(gestorAlmacen)
			//.comprobarExistencias(Mockito.eq(p3), Mockito.any(Integer.class));
			.comprobarExistencias(Mockito.any(Producto.class), Mockito.any(Integer.class));
		
		//GestorTransportes: dummie
		//GestorOfertas: dummie
		
		//ENTONCES
		Assertions.assertThrows(
				ExistenciasEx.class, 
				()->gestorPedidos.aceptar(idPedido),
				"No ha lanzado ExistenciasEx"
			);		
	}	
		
	//@Test
	@DisplayName("GestorPedidos.aceptar invoca correctamente a sus dependencias")
	//ESTO NO ES UN TEST UNITARIO
	public void pruebaMocks() throws Exception {
		
		//Dados
		//Premisa: con este pedido todo está OK
		Integer idPedido = 1;
		
		//PedidoDao: stub
		Mockito
			.when(pedidoDao.buscar(idPedido))
			.thenReturn(pedido);
		
		//GestorBancos: dummie
		//GestorAlmacen: dummie
		
		//GestorTransportes: stub
		Mockito
			.when(gestorTransportes.obtenerCamion(true))
			.thenReturn("MOOOOC MOOOOOOOOC");
		
		//GestorOfertras: stub
		Mockito
			.when(gestorOfertas.obtenerPerritoPiloto(true))
			.thenReturn("Perrito Piloto");		
		
		//Cuando
		gestorPedidos.aceptar(idPedido);
		
		//Entonces
		Mockito.verify(gestorBancos).comprobarTC(Mockito.any()); //Exactamente una vez
		Mockito.verify(gestorAlmacen, times(3)).comprobarExistencias(any(Producto.class), any(Integer.class));
		Mockito.verify(gestorAlmacen, times(3)).reducirExistencias(any(Producto.class), any(Integer.class));
		Mockito.verify(gestorTransportes).obtenerCamion(true); //Una y solo una vez
		Mockito.verify(gestorOfertas, times(1)).obtenerPerritoPiloto(true); //Así escribimos más		
	}		
	
}













