/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ventas.basedatos.conexionBase;
import ventas.configuraciones.globales;
import ventas.ventanas.login;

/**
 *
 * @author samv
 */
public class principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //Parametros de inicialización
        
        String host = "localhost";
        String puerto = "5432";
        String baseDatos = "ventas";
        String userName = "postgres";
        String password = "12345";
        
        
        globales.db = new conexionBase(host, baseDatos, puerto, userName, password);
        
        new login().setVisible(true);
        
        
//        List<Map<String,Object>> milista = new ArrayList<Map<String,Object>>();
//        
//        Map<String,Object> obj1 = new HashMap<String,Object>();
//        
//       
//        obj1.put("Nombre","Santiago");
//        obj1.put("Apellido","Mariscal");
//        obj1.put("Edad",27);
//        
//        
//        milista.add(obj1);
//        
//        
//         Map<String,Object> obj2 = new HashMap<String,Object>();
//        
//       
//        obj2.put("Nombre","Orionx");
//        obj2.put("Apellido","temple");
//        obj2.put("Edad",18);
//        milista.add(obj2);
//        
//           Map<String,Object> obj3 = new HashMap<String,Object>();
//        
//       
//        obj3.put("Nombre","Paulo");
//        obj3.put("Apellido","Apa");
//        obj3.put("Edad",22);
//        milista.add(obj3);
//        
//        
//        
//        for(Map<String,Object> item : milista){
//           String nombre = String.valueOf(item.get("Nombre"));
//           String Apellido = String.valueOf(item.get("Apellido"));
//           int edad = Integer.parseInt(String.valueOf(item.get("Edad")));
//           
//            System.out.println(nombre+" "+Apellido+" "+edad);
//        }
//        
        
    }
    
}
