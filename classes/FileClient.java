/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jesusra
 */
import java.io.*; 
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class FileClient{
    
    public static String fileName = " ";
    
    public static int menu(){
        Scanner reader = new Scanner(System.in);
        int opcion;
        
        System.out.println("1.- Ingresar nombre de archivo");
        System.out.println("2.- Descargar archivo");
        System.out.println("3.- Contar líneas");
        System.out.println("4.- Contar vocales");
        System.out.println("5.- Leer contenido");
        System.out.println("6.- Respaldar archivo");
        System.out.println("7.- Copiar archivo");
        System.out.println("8.- Renombrar archivo");
        System.out.println("9.- Eliminar archivo");
        System.out.println("10.- Terminar ejecución");
        System.out.println("Ingrese su opción");
        opcion = reader.nextInt();
        
        return opcion;
    }
    
   public static void main(String argv[]) {
      if(argv.length < 1) {
        System.out.println("Usage: java FileClient machineName");
        System.exit(0);
      }
      try {
         //System.setProperty("java.rmi.server.hostname","187.235.110.238  ");
         //Registry registry = LocateRegistry.getRegistry(argv[1]);
         Scanner reader = new Scanner(System.in);
         int opcion;
         String line;
         
         String name = "rmi://" + argv[0] + "/FileServer";
         FileInterface fi = (FileInterface) Naming.lookup(name);

        do{
            opcion = menu();
            
            switch(opcion){
                case 1: 
                    System.out.println("Ingrese el nombre del archivo con su extensión.");
                    fileName = reader.nextLine();
                    fi.setFileName(fileName);
                break;

                case 2:
                    // Descarga archivo
                    System.out.println("Ingrese el nombre del archivo que desea descargar.");
                    line = reader.nextLine();
                    byte[] filedata = fi.downloadFile(line);
                    System.out.println("Ingrese el nombre que desea asignar al archivo.");
                    line = reader.nextLine();
                    BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(line));
                    output.write(filedata,0,filedata.length);
                    output.flush();
                    output.close();
                    System.out.println("Archivo descargado satisfactoriamente.");
                    // Fin descarga archivo
                break;

                case 3:
                    if( fi.countLines() != -1 ){
                        System.out.println( fi.getFileName() + " tiene "+ fi.countLines() +" lineas");
                    }else{
                        System.out.println("No se han podido contar las líneas del archivo " + fi.getFileName() + ", asegurate de que exista en el directorio específicado.");
                    }
                break;

                case 4:
                    System.out.println("Ingrese el texto al qué desea contar vocales");
                    line = reader.nextLine();
                    int numVocales = fi.numeroDeVocales(line);
                    System.out.println( line + " tiene " + numVocales + " vocales");
                break;

                case 5:
                    //System.out.println("Contenido: ");
                    if( fi.print() != null ){
                        String fileContent[] = fi.print();
                        for(int i = 0; i < fileContent.length; i++){
                            System.out.println(fileContent[i]);
                        }
                    }else{
                        System.out.println("No se pudo encontrar el archivo " + fi.getFileName() +", asegurate de que exista en el directorio específicado.");
                    }
                break;

                case 6:
                    System.out.println("Ingrese el nombre para el respaldo");
                    line = reader.nextLine();
                    if (fi.backup(line) ){
                        System.out.println("Respaldo realizado correctamente");
                    }else{
                        System.out.println("Operación fallida, autodestrucción iniciada");
                    }
                break;

                case 7:
                    System.out.println("Ingrese el nombre deseado para la copia del archivo");
                    line = reader.nextLine();
                    if ( fi.copy(line) ){
                        System.out.println("Se ha copiado el archivo satisfactoriamente");
                    }else{
                        System.out.println("No se ha podido crear la copia del archivo " + fi.getFileName() + ", asegurate de que exista en el directorio específicado.");
                    }
                break;

                case 8:
                    System.out.println("Ingrese el nuevo nombre");
                    line = reader.nextLine();
                    if(fi.rename(line)){
                        System.out.println("Archivo renombrado satisfactoriamente!");
                    }else{
                        System.out.println("No se pudeo renombrar el archivo " + fi.getFileName() + ", asegurate de que exista en el directorio específicado.");
                    }
                break;
                
                case 9:
                    if( fi.delete() ){
                        System.out.println("Archivo eliminado.");
                    }else{
                        System.out.println("No se pudo eliminar el archivo "+ fi.getFileName() +", asegurate de que exista en el directorio específicado.");
                    }
                break;
                
                case 10:
                    System.out.println("Hasta la vista, baby!");
                break;
                
                default:
                    System.out.println("Ingrese una opción del menú");
                break;
            }
            
            if(opcion != 10){
                System.out.println("");
                System.out.println("Presione enter para continuar...");
                line = reader.nextLine();
            }
            
        }while( opcion != 10 );
        
        
      } catch(Exception e) {
         System.err.println("FileServer exception: "+ e.getMessage());
         e.printStackTrace();
      }
   }
}