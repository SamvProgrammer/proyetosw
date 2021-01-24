/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventas.basedatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author samv
 */
public class conexionBase {

    private Connection conexion;

    public conexionBase(String host, String basedatos, String puerto, String username, String password) {
        try {
            try {
            //Método de conectar a la base de datos

                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(conexionBase.class.getName()).log(Level.SEVERE, null, ex);
            }

            String url = String.format("jdbc:postgresql://%1$s:%2$s/%3$s", host, puerto, basedatos);
            
            System.out.println(url);

            conexion = DriverManager.getConnection(url, username, password);
            
            
            JOptionPane.showMessageDialog(null, "Se conecto correctamente a la base de datos");
            
        } catch (SQLException ex) {
            Logger.getLogger(conexionBase.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    public Object consulta(String query,boolean tipoQuery){
    
        
        //tipoquery == true es una sentencia de insert, update, o delete etc... 
        
      
        
        
         Statement sentencia;
         
         Object resultado = null;
         
        try {
            
            
            
            sentencia = conexion.createStatement();
           
            
            if(tipoQuery){
               resultado = sentencia.executeUpdate(query);
            }else{
               ResultSet datos =   sentencia.executeQuery(query);
               
               List<Map<String,Object>> rrquery = new ArrayList<Map<String,Object>>();
               
               ResultSetMetaData rsmd = datos.getMetaData();
               
               for(;datos.next();){
                 
                   
                   Map<String,Object> row = new HashMap<String,Object>();
                
                   for(int x = 1; x <= rsmd.getColumnCount(); x++){
                       row.put(rsmd.getColumnLabel(x), datos.getObject(rsmd.getColumnLabel(x)) );
                   }
                   
                   rrquery.add(row);
                   
               }
               
               
               datos.close();
               resultado = rrquery;
            }
            
            sentencia.close();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(conexionBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return resultado;
    }

}
