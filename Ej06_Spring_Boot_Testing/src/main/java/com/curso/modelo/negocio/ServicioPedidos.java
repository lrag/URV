package com.curso.modelo.negocio;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.curso.cfg.Configuracion;
import com.curso.modelo.entidad.Cliente;
import com.curso.modelo.entidad.DetallePedido;
import com.curso.modelo.entidad.Pedido;
import com.curso.modelo.entidad.Producto;
import com.curso.modelo.persistencia.PedidoRepositorio;
import com.curso.modelo.proxy.ClientesRestProxy;
import com.curso.modelo.proxy.ProductosRestProxy;

@Service
@Transactional
public class ServicioPedidos {

	@Autowired private PedidoRepositorio pedidoRepo;	
	@Autowired private ClientesRestProxy clientesProxy;
	@Autowired private ProductosRestProxy productosRestProxy;
	@Autowired private ServicioOfertas servicioOfertas;
	
	public ServicioPedidos() {
		super();
		System.out.println("CREANDO SERVICIO_PEDIDOS");
	}

	public Pedido altaPedido(Pedido pedido) {

		System.out.println("===============================");
		System.out.println("Alta pedido");
		
		//Buscamos al cliente a partir de su login
		String login = pedido.getCliente().getLogin();			
		System.out.println("Login del cliente:"+login);
		Cliente cliente = clientesProxy.buscar(login);
		pedido.setCliente(cliente);
		
		//Comprobamos los datos bancarios
		//...

		//Calculamos el total
		Double total = 0d;
		for(DetallePedido dp : pedido.getDetalles()) {
			String codigoProducto = dp.getProducto().getCodigo();
			Producto producto = productosRestProxy.buscar(codigoProducto);
			dp.setProducto(producto);
			dp.setPrecio(producto.getPrecio());
			dp.setPedido(pedido);
			total += producto.getPrecio()*dp.getCantidad();
		}
		pedido.setTotal(total);
		
		//Le asignamos un codigo al pedido
		pedido.setCodigo("PED-"+Math.round(System.currentTimeMillis()/1000));
		//Lo ponemos en estado 'ACEPTADO'
		pedido.setEstado("ACEPTADO");
		
		//OFERTAS
		servicioOfertas.calcularOferta(pedido);
		
		//FACTURAS...COBROS...ENVÍO...
		
		//Y lo insertamos
		pedidoRepo.save(pedido);
		
		return pedido;
	}

	public void modificar(Pedido pedido) {
		// TODO Auto-generated method stub		
	}

	public void borrar(String codigo) {
		// TODO Auto-generated method stub		
	}	
	
}









