package com.curso.soap.cliente;

import java.util.List;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import com.curso.webservice.clientes.AltaClienteRequest;
import com.curso.webservice.clientes.AltaClienteResponse;
import com.curso.webservice.clientes.BajaClienteRequest;
import com.curso.webservice.clientes.BajaClienteResponse;
import com.curso.webservice.clientes.BuscarPorIdRequest;
import com.curso.webservice.clientes.BuscarPorIdResponse;
import com.curso.webservice.clientes.ClienteDTO;
import com.curso.webservice.clientes.ListarClientesRequest;
import com.curso.webservice.clientes.ListarClientesResponse;
import com.curso.webservice.clientes.ModificarClienteRequest;
import com.curso.webservice.clientes.ModificarClienteResponse;

//
//La clase es nuestra, pero la registramos en ClienteSoapConfig.java
//
public class ClienteSoapClient extends WebServiceGatewaySupport {

	//Este método acoplaría al resto de la aplicación con que esto es un servicio web SOAP.
	/*
	public AltaClienteResponse altaCliente_(AltaClienteRequest request) {
		 return ((AltaClienteResponse) getWebServiceTemplate()
	                .marshalSendAndReceive(request));
	}
	*/
	
	public String altaCliente(ClienteDTO cliente) {
        AltaClienteRequest request = new AltaClienteRequest();
        request.setCliente(cliente);
        return ((AltaClienteResponse) getWebServiceTemplate()
                .marshalSendAndReceive(request)).getResultado();
    }

    public String modificarCliente(ClienteDTO cliente) {
        ModificarClienteRequest request = new ModificarClienteRequest();
        request.setCliente(cliente);
        return ((ModificarClienteResponse) getWebServiceTemplate()
                .marshalSendAndReceive(request)).getResultado();
    }

    public Boolean bajaCliente(int id) {
        BajaClienteRequest request = new BajaClienteRequest();
        request.setId(id);
        return ((BajaClienteResponse) getWebServiceTemplate()
                .marshalSendAndReceive(request)).isConfirmacion();
    }

    public ClienteDTO obtenerCliente(int id) {
        BuscarPorIdRequest request = new BuscarPorIdRequest();
        request.setId(id);
        return ((BuscarPorIdResponse) getWebServiceTemplate()
                .marshalSendAndReceive(request)).getCliente();
    }

    public List<ClienteDTO> listarClientes() {
    	//La petición está vacía, pero el objeto debe instanciarse
        ListarClientesRequest request = new ListarClientesRequest();
        return ((ListarClientesResponse) getWebServiceTemplate()
                .marshalSendAndReceive(request)).getClientes();
    }
    
}