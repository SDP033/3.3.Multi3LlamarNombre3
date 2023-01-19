package llamarNombre;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class Llamada {

	public static void main(String[] args) throws IOException, InterruptedException {

		File directorio = new File("C:\\Users\\sergi\\eclipse-workspace\\3.3.Multi3CrearNombre3\\bin");
		ProcessBuilder pb = new ProcessBuilder("java", "crearNombre3.Nombre");

		pb.directory(directorio);

		String cadena = "";

		Integer i = 0;
		for (i = 0; i <= 9999; i++) {
			System.out.println("Escribe un número: ");
			// entrada de una cadena
			Scanner sc = new Scanner(System.in);
			String r = sc.nextLine();
			cadena = cadena + r + "\n";
			if (r.equals("*")) {
				break;
			}
//		        else {
//		        	try {				  
//						Float j = Float.valueOf(r);
//						cadena = cadena + r+"\n";  
//					  } catch (Exception e) {
//						  System.out.println("No has escrito un numero");
//						  System.exit(-1);}
//		        }	
		}

		// se ejecuta el proceso
		Process p = pb.start();

		cadena = cadena + "\n";
		OutputStream os = p.getOutputStream();
		os.write(cadena.getBytes());
		os.flush(); // vacía el buffer de salida

		// COMPROBACION DE ERROR - 0 bien - 1 mal
		int exitVal = -1;
		try {
			exitVal = p.waitFor();
			System.out.println("Valor de Salida: " + exitVal);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if (exitVal == 0) {
			try {

				InputStream is = p.getInputStream();
				// mostramos en pantalla caracter a caracter
				int c;
				while ((c = is.read()) != -1)
					System.out.print((char) c);
				// is.close();
				System.exit(0);

			} catch (Exception e) {
				System.exit(-1);
				e.printStackTrace();
			}
		}
	}
}
