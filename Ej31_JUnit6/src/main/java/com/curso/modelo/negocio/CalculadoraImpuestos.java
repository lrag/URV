package com.curso.modelo.negocio;


public class CalculadoraImpuestos {

	//@Override
	public double calcularImpuestosSobreIngreso(double ingreso) throws InvalidIngresoException{
		if (ingreso <= 0){
			throw new InvalidIngresoException("El ingreso ha de ser superior a 0");
		} else if (ingreso > 0 && ingreso < 8000){
			return 0.0;
		} else if(ingreso >= 8000 && ingreso < 15000){
			return ingreso * 0.08;
		} else if(ingreso >= 15000 && ingreso < 20000){
			return ingreso * 0.10;
		} else if(ingreso >= 20000 && ingreso < 30000){
			return ingreso * 0.15;
		} else {
			//if(ingreso > 30000)
			return ingreso * 0.195;
		}
	}

}
