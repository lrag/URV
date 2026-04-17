import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.curso.cfg.Configuracion;
import com.curso.modelo.entidad.DetallePedido;
import com.curso.modelo.entidad.Pedido;
import com.curso.modelo.negocio.GestorPedidos;

public class Pruebas {

	public static void main(String[] args) {
		
		AbstractApplicationContext appCtx = new AnnotationConfigApplicationContext(Configuracion.class);
		
		
		GestorPedidos gp = appCtx.getBean(GestorPedidos.class);
		try {
			gp.aceptar(3);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		EntityManagerFactory emf = appCtx.getBean(EntityManagerFactory.class);
		
		EntityManager em = emf.createEntityManager();
		System.out.println("=================================================");
		Pedido pBis = em.find(Pedido.class, 1);
		System.out.println(pBis.getCodigo());
		System.out.println(pBis.getCliente().getNombre());
		System.out.println(pBis.getEstado());
		for(DetallePedido dp : pBis.getDetalles()) {
			System.out.println(dp.getProducto().getNombre());
		}
		em.close();
		
		appCtx.close();		
		
	}
	
}
