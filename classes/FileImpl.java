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
import java.rmi.server.UnicastRemoteObject;

public class FileImpl extends UnicastRemoteObject implements FileInterface {

   private String fileName;
   private File file;
   
   public FileImpl(String name) throws RemoteException{
      super();
      fileName = name;
      file = new File(name);
   }
   
   @Override
   public void setFileName(String name){
       file = new File(name);
       fileName = name;
   }
   
   @Override
   public String getFileName(){
       return file.getName();
   }

   @Override
   public byte[] downloadFile(String fileName){
      try {
         File file = new File(fileName);
         byte buffer[] = new byte[(int)file.length()];
         BufferedInputStream input = new BufferedInputStream(new FileInputStream(fileName));
         input.read(buffer,0,buffer.length);
         input.close();
         return(buffer);
      } catch(IOException e){
         System.out.println("FileImpl: "+e.getMessage());
         return(null);
      }
   }
   
   @Override
   public int countLines() throws RemoteException{
       try{
            FileReader fileReader = new FileReader(fileName);
            BufferedReader reader = new BufferedReader(fileReader);
            
            String line;
            int numLines = 0;
            while( (line = reader.readLine()) != null ){
                numLines++;
            }
            reader.close();
            fileReader.close();
            
            return numLines;
            
       }catch(IOException e){
           System.out.println(e);
           return -1;
       }
   }
   
    @Override
    public String[] reader(){
       try{
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);
            String line;
            int numLines = countLines();
            int i = 0;
            String data[] = new String[numLines];
            
            while( (line = reader.readLine() ) != null &&  i < numLines ){
                data[i++] = line;
            }
            reader.close();
            fileReader.close();
            
            return (data);
            
       }catch(IOException e){
           System.out.println(e);
           return null;
       }
    }
   
    //Devuelve la cantidad de vocales de la frase
   @Override
    public int numeroDeVocales(String frase) throws RemoteException {
        int res = 0;
        String fraseMin = frase.toLowerCase();

        for (int i = 0; i < fraseMin.length(); ++i) {
            switch(fraseMin.charAt(i)) {
                case 'a': case 'á':
                case 'e': case 'é':
                case 'i': case 'í':
                case 'o': case 'ó':
                case 'u': case 'ú':
                res++;
                break;
                default:
                // se ignoran las demás letras
            }
        }
        return res;
    }
    
    @Override
    public void write(OutputStream output) {
        try{

            String content[] = reader();
            
            for (String line : content) {
                output.write(line.length());
            }

        }catch(IOException e){
            System.out.println(e);
        }
        
    }
    
   @Override
    public String[] print(){
        try{
            
            return (reader());
            
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }
    
    @Override
    public boolean backup(String backup){
        try{
            FileWriter file = new FileWriter(backup);
            PrintWriter writer = new PrintWriter(file);
            String fileContent[] = reader();
            
            for (String line : fileContent) {
                writer.write(line);
                writer.write("\n");
            }
            
            writer.close();
            file.close();
            return true;
            
        }catch(IOException e){
            System.out.println(e);
            return false;
        }
    }
    
    @Override
    public boolean copy(String newFile){
        return backup(newFile);
    }
    
   @Override
    public boolean rename(String name){
        //File currentName = new File(fileName);
        
        if( file.getParent() != null ){
            name = file.getParent() + "/" + name;
        }
        File newName = new File(name);
        
        return file.renameTo(newName);
    }
    
    @Override
    public boolean delete(){
        //File file = new File(fileName);
        return file.delete();
    }
}