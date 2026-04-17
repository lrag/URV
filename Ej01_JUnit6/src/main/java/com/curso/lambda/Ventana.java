package com.curso.lambda;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Ventana extends JFrame{

	public Ventana() throws HeadlessException {
		super();
		setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
        /*
		JButton boton = new JButton("Dale");		
		OyenteBotonDale oyente = new OyenteBotonDale();
		boton.addActionListener(oyente);
        */
        
        /*
		JButton boton = new JButton("Dale");		
		ActionListener oyente = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDalePulsado();
			}
		};
		boton.addActionListener(oyente);
		*/
		
		JButton boton = new JButton("Dale");	
		boton.addActionListener( e -> btnDalePulsado());        
        
        
		JPanel botonera = new JPanel();
		botonera.setLayout(new FlowLayout());		
		botonera.add(boton);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(botonera, BorderLayout.NORTH);
		
		this.setContentPane(panel);
				
	}


	private void btnDalePulsado() {
		System.out.println("Dale leña al mono que es de goma");
	}
		
	
}


class OyenteBotonDale implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {		
		System.out.println("DALE MÁS");	
	}	
	
}


