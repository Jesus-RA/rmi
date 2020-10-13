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
import java.rmi.server.UnicastRemoteObject;
import java.lang.Object;
import java.lang.SecurityManager;
import java.security.Permission;

public class FileServer {
   public static void main(String argv[]) {
      Object context = null;
      Permission permission = new Permission("AllPermission") {
          @Override
          public boolean implies(Permission permission) {
              throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
          }

          @Override
          public boolean equals(Object obj) {
              throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
          }

          @Override
          public int hashCode() {
              throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
          }

          @Override
          public String getActions() {
              throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
          }
      };
      SecurityManager security = System.getSecurityManager();
      
      if(security != null) {
            context = security.getSecurityContext();
            security.checkPermission(permission, context);
      }
      try {

         Naming.rebind("rmi://localhost:9996/FileServer", new FileImpl("policy.txt"));
         
         System.out.println("Server is ready");

      } catch(Exception e) {
         System.out.println("FileServer: "+e.getMessage());
         e.printStackTrace();
      }
   }
}