package com.curso.modelo.negocio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.curso.modelo.entidad.DetallePedido;
import com.curso.modelo.entidad.Pedido;
import com.curso.modelo.persistencia.PedidoDao;

@Service
@Transactional(propagation=Propagation.REQUIRED) 
public class GestorPedidos {

	@Autowired private PedidoDao pedidoDao;
	@Autowired private GestorBancos gestorBancos;
	@Autowired private GestorAlmacen gestorAlmacen;
	@Autowired private GestorTransportes gestorTransportes;
	@Autowired private GestorOfertas gestorOfertas;
		
	public void setPedidoDao(PedidoDao pedidoDao) {
		this.pedidoDao = pedidoDao;
	}

	public void setGestorBancos(GestorBancos gestorBancos) {
		this.gestorBancos = gestorBancos;
	}

	public void setGestorAlmacen(GestorAlmacen gestorAlmacen) {
		this.gestorAlmacen = gestorAlmacen;
	}

	public void setGestorTransportes(GestorTransportes gestorTransportes) {
		this.gestorTransportes = gestorTransportes;
	}

	public void setGestorOfertas(GestorOfertas gestorOfertas) {
		this.gestorOfertas = gestorOfertas;
	}

	public void insertar(Pedido pedido){
		//Validación
		pedidoDao.insertar(pedido);
	}
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor= { Exception.class } )
	//En esta aplicacion solo se aceptan pedidos que ya está insertados
	//en la base de datos. Por eso solo recibimos el id
	public Pedido aceptar(Integer idPedido) throws Exception{
	
		Pedido pedido = pedidoDao.buscar(idPedido);

		//Ciertas validaciones		
		if(pedido.getDetalles().size()==0) {
			throw new Exception("El pedido no tiene detalles!");
		}
	
		Integer numeroTC = pedido.getCliente().getNumeroTC();				
		gestorBancos.comprobarTC(numeroTC);
				
		for(DetallePedido dp : pedido.getDetalles()) {
			gestorAlmacen.comprobarExistencias(dp.getProducto(), dp.getCantidad());
			gestorAlmacen.reducirExistencias(dp.getProducto(), dp.getCantidad());
		}
		
		String camion = gestorTransportes.obtenerCamion(true);
		pedido.setCamion(camion);		
		
		try {
			String perritoPiloto = gestorOfertas.obtenerPerritoPiloto(true);
			pedido.setRegalo(perritoPiloto);
		} catch (Exception e) {
			System.out.println("GESTOR_PEDIDOS: "+e.getMessage());
			//Procesar el drama de que no haya perrito piloto
		}
		
		//EMITIR UNA FACTURA
		//gestorFacturas.bla bla blá...
		
		pedido.setEstado("ACEPTADO");
		pedidoDao.modificar(pedido);
	
		//System.out.println(TransactionAspectSupport.currentTransactionStatus().isRollbackOnly());
		//System.out.println("FIN");
		
		return pedido;
	}
	
	public Pedido buscar(Integer id){
		return pedidoDao.buscar(id);
	}
	
}








