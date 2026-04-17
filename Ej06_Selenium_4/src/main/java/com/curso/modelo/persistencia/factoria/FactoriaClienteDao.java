package com.curso.modelo.persistencia.factoria;

import com.curso.modelo.persistencia.ClienteDao;
import com.curso.modelo.persistencia.ClienteDaoArrayList;

public class FactoriaClienteDao {

	public static ClienteDao getClienteDao(){
		return new ClienteDaoArrayList();
	}
	
}
