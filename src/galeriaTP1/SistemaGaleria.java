package galeriaTP1;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class SistemaGaleria {
	private static ArrayList<RegistroLocal> registro = new ArrayList<RegistroLocal>();
	private static String archivo = "galeriaTP1.txt";
	
	private void iniciarRegistro() {
		String line = null;
		
		try {
			FileReader file = new FileReader(archivo);
			
			BufferedReader buffer = new BufferedReader(file);
			
			while((line = buffer.readLine()) != null ) {
				String data[] = line.split("\\t+");
				
				for( int i = 0; i < data.length; i++ )
					data[i] = data[i].trim();
				
				RegistroLocal reg = new RegistroLocal(data);
				registro.add(reg);
				
				
			}
			
			buffer.close();
		}
		catch(FileNotFoundException ex) {
	        System.out.println("No se pudo abrir el archivo '" + archivo + "'");                
	    }
	    catch(IOException ex) {
	        System.out.println("Error leyendo el archivo '" + archivo + "'");
	    }
	}
	
	public int totalFacturado() {
		return registro.stream().map(reg -> reg.getFacturacion()).reduce(0, (fact1, fact2) -> fact1 + fact2);
	}
	
	private void agregarLocal(String[] local) {
		if( buscarRegistroPorLocal(Integer.parseInt(local[2])) == null ) {
			RegistroLocal nuevoRegistro = new RegistroLocal(local);
			registro.add(nuevoRegistro);
			actualizarRegistro();
		}
	}
	
	private void actualizarRegistro() {
		try {
			FileWriter file = new FileWriter(archivo, false);
			
			BufferedWriter buffer = new BufferedWriter(file);
			
			for( RegistroLocal reg : registro ) {
				System.out.println("Subiendo local: " + reg.getLocal());
				buffer.write(reg.toString() + "\n");
			}
			
			buffer.close();
		}
		catch(FileNotFoundException ex) {
	        System.out.println("No se pudo abrir el archivo '" + archivo + "'");                
	    }
	    catch(IOException ex) {
	        System.out.println("Error escribiendo el archivo '" + archivo + "'");
	    }
	}
	
	private RegistroLocal buscarRegistroPorNombre(String nombre) {
		return registro.stream().filter(local -> local.getNombre().equals(nombre)).findAny().orElse(null);
	}
	
	private RegistroLocal buscarRegistroPorApellido(String apellido) {
		return registro.stream().filter(local -> local.getApellido().equals(apellido)).findAny().orElse(null);
	}
	
	private RegistroLocal buscarRegistroPorLocal(int numLocal) {
		return registro.stream().filter(local -> local.getLocal() == numLocal).findAny().orElse(null);
	}
	
	public static void main(String [] args) {
		SistemaGaleria sys = new SistemaGaleria();
		
		sys.iniciarRegistro();
		
		System.out.println(sys.totalFacturado());		
		
		sys.agregarLocal(new String[]{"ROBIN LELEU", "MARÍA ELENA", "1007", "MANUALIDADES EN ALPACA Y CUERO-RESINA", "0"});
		
		RegistroLocal reg = sys.buscarRegistroPorApellido("MANSEBO");
		if(reg != null) {
			reg.setApellido("MANCEBO");
		}
		
		sys.actualizarRegistro();
	}
}
