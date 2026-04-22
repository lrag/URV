package com.curso.endpoint;
import java.util.HashMap;
import java.util.Map;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

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
//http://localhost:8080/ws/clientes.wsdl
//

/*
 
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                  xmlns:cli="http://curso.com/webservice/clientes">
   <soapenv:Header/>
   <soapenv:Body>
      <cli:altaClienteRequest>
         <cli:cliente>
            <cli:id>1</cli:id>
            <cli:nombre>Ringo Starr</cli:nombre>
            <cli:direccion>C/Su calle</cli:direccion>
            <cli:telefono>123456789</cli:telefono>
         </cli:cliente>
      </cli:altaClienteRequest>
   </soapenv:Body>
</soapenv:Envelope>

<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                  xmlns:cli="http://curso.com/webservice/clientes">
   <soapenv:Header/>
   <soapenv:Body>
      <cli:listarClientesRequest>
      </cli:listarClientesRequest>
   </soapenv:Body>
</soapenv:Envelope> 
 
*/


@Endpoint
public class ClienteEndpoint {
    private static final String NAMESPACE_URI = "http://curso.com/webservice/clientes";

    private static final Map<Integer, ClienteDTO> clientesDb = new HashMap<>();

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "altaClienteRequest")
    @ResponsePayload
    public AltaClienteResponse altaCliente(@RequestPayload AltaClienteRequest request) {
        
    	ClienteDTO nuevo = request.getCliente();
        clientesDb.put(nuevo.getId(), nuevo);
        
        AltaClienteResponse response = new AltaClienteResponse();
        response.setResultado("Cliente " + nuevo.getNombre() + " creado con éxito.");
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "modificarClienteRequest")
    @ResponsePayload
    public ModificarClienteResponse modificarCliente(@RequestPayload ModificarClienteRequest request) {
        ModificarClienteResponse response = new ModificarClienteResponse();
        ClienteDTO c = request.getCliente();
        if (clientesDb.containsKey(c.getId())) {
            clientesDb.put(c.getId(), c);
            response.setResultado("Cliente actualizado.");
        } else {
            response.setResultado("Error: Cliente no encontrado.");
        }
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "bajaClienteRequest")
    @ResponsePayload
    public BajaClienteResponse bajaCliente(@RequestPayload BajaClienteRequest request) {
        ClienteDTO eliminado = clientesDb.remove(request.getId());
        BajaClienteResponse response = new BajaClienteResponse();
        response.setConfirmacion(eliminado != null);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "buscarPorIdRequest")
    @ResponsePayload
    public BuscarPorIdResponse buscarPorId(@RequestPayload BuscarPorIdRequest request) {
        BuscarPorIdResponse response = new BuscarPorIdResponse();
        response.setCliente(clientesDb.get(request.getId()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "listarClientesRequest")
    @ResponsePayload
    public ListarClientesResponse listarClientes(@RequestPayload ListarClientesRequest request) {
        ListarClientesResponse response = new ListarClientesResponse();
        response.getClientes().addAll(clientesDb.values());
        return response;
    }
    
}