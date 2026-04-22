package com.curso.modelo.negocio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.curso.modelo.entidad.DetallePedido;
import com.curso.modelo.entidad.Pedido;
import com.curso.modelo.negocio.excepcion.PerritoPilotoException;
import com.curso.modelo.persistencia.PedidoDao;

@Service
@Transactional
public class ServicioPedidos {

	@Autowired private PedidoDao pedidoDao;
	@Autowired private ServicioBancos servicioBancos;
	@Autowired private ServicioAlmacen servicioAlmacen;
	@Autowired private ServicioTransportes servicioTransportes;
	@Autowired private ServicioOfertas servicioOfertas;
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void insertar(Pedido pedido) {
		//LN
		pedidoDao.insertar(pedido);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, 
			       rollbackFor = { Exception.class }, //Esto incluye DatosBancariosException, ExistenciasException y PerritoPilotoException
			       noRollbackFor = { PerritoPilotoException.class } )
	public void aceptar(Integer idPedido) throws Exception {
		
		Pedido pedido = pedidoDao.buscar(idPedido);
		
		servicioBancos.comprobarTC(pedido.getCliente().getNumeroTC());

		for(DetallePedido dp: pedido.getDetalles()) {
			servicioAlmacen.comprobarExistencias(dp.getProducto(), dp.getCantidad());
			servicioAlmacen.reducirExistencias(dp.getProducto(), dp.getCantidad());
		}
		
		String camion = servicioTransportes.obtenerCamion(true);
		pedido.setCamion(camion);
				
		try {
			String regalo = servicioOfertas.obtenerPerritoPiloto(false);
			pedido.setRegalo(regalo);
		} catch(PerritoPilotoException e) {
			System.out.println("Excepción capturada:"+e.getMessage());
			pedido.setRegalo("Regalo pendiente");
		}
		
		//gestorFacturas.emitirFactura(pedido);
		
		pedido.setEstado("ACEPTADO");
		pedidoDao.modificar(pedido);
		
		System.out.println("TX chunga:"+TransactionAspectSupport.currentTransactionStatus().isRollbackOnly());
		
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public Pedido buscar(Integer id) {
		return pedidoDao.buscar(id);
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Pedido> listar() {
		return pedidoDao.listar();
	}

	public void modificar(Pedido pedido) {
		pedidoDao.modificar(pedido);
	}

	public void borrar(Integer idPedido) {
		Pedido p = new Pedido();
		p.setId(idPedido);
		pedidoDao.borrar(p);
	}
	
	
}


/*
class TXAdvice implements MethodInterceptor {
	@Override
	public Object invoke(MethodInvocation mi) throws Throwable {

		mi.getMethod().getAnnotations();
		//txmanager.mira a ver que este hilo quiere nosequé
		
		Object retorno = null;
		
		try {
			retorno = mi.proceed();
			//if set rollback only
			//->ROLLBACK
			//
			//COMMIT
		} catch (excepciones_de_rollback e) {
			//ROLLBACK
			throw e;
		}catch (otras_excepciones e) {
			//COMMIT
			throw e;
		}
		
		return retorno;
	}
}
*/

























