import java.io.*;

// Clase GestorDeArchivo
class GestorDeArchivo {
    // Método para guardar contenido en un archivo
    public void guardar(String nombreArchivo, String contenido) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo));
        writer.write(contenido);
        writer.close();
    }

    // Método para leer contenido de un archivo línea por línea
    public String leer(String nombreArchivo) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo));
        StringBuilder contenido = new StringBuilder();
        String linea;

        while ((linea = reader.readLine()) != null) {
            contenido.append(linea).append("\n");
        }
        reader.close();
        return contenido.toString();
    }
}

// Clase de excepción personalizada
class ArchivoVacioException extends Exception {
    public ArchivoVacioException(String mensaje) {
        super(mensaje);
    }
}

// Clase ValidadorArchivo
class ValidadorArchivo {
    public void verificarNoVacio(String nombreArchivo) throws IOException, ArchivoVacioException {
        BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo));
        if (reader.readLine() == null) {
            reader.close();
            throw new ArchivoVacioException("El archivo está vacío: " + nombreArchivo);
        }
        reader.close();
    }
}

// Clase Principal
public class Main {
    public static void main(String[] args) {
        GestorDeArchivo gestor = new GestorDeArchivo();
        ValidadorArchivo validador = new ValidadorArchivo();
        String nombreArchivo = "ejemplo.txt";

        try {
            // Guardar contenido en el archivo
            gestor.guardar(nombreArchivo, "Hola, este es un contenido de prueba.\nSegunda línea.");

            // Leer contenido del archivo
            String contenido = gestor.leer(nombreArchivo);
            System.out.println("Contenido del archivo:\n" + contenido);

            // Verificar si el archivo está vacío
            validador.verificarNoVacio(nombreArchivo);
            System.out.println("El archivo no está vacío.");

        } catch (ArchivoVacioException ave) {
            System.out.println("Error: " + ave.getMessage());
        } catch (IOException ioe) {
            System.out.println("Error de entrada/salida: " + ioe.getMessage());
        }
    }
}
