/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Db_Conexion {
    /*Se declara Variables con Mayuscula por la homeclatura ya que son variables final*/
    private static Connection CN;
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String USER = "Damian";
    private static final String PASSWORD = "";
    private static final String URL = "jdbc:mysql://SPEK:3306/";

    public Db_Conexion() {
        CN = null;
        try{
            Class.forName(DRIVER);
            CN = DriverManager.getConnection(URL,USER,PASSWORD);
            if(CN!=null){
                //JOptionPane.showMessageDialog(null,"Conexión Establecida Con Éxito...", "Conexión Establecida", JOptionPane.INFORMATION_MESSAGE);
            }
        }catch (ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage(), "Conexión Denegada", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /*metodo para retornar la conexión*/
    public Connection getConnection(){
       return CN; 
    }
    
    /*Metodo para desconectar*/
    public void close(){
        try{
            CN.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage(), "Conexión Interrumpida", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    
    
}