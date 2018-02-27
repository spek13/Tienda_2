/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiendita;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Damián Javier Mejía y Arturo Nieva
 */
public class MetodoLogin {
     String barras;
    Pool metodospool = new Pool();
    
    public int Validar_Ingreso(){
        String usuario = Login.Usuariotxtfield.getText();
        String clave = String.valueOf(Login.Contraseña.getPassword());
        
        int resultado = 0;
        //Este es el error
        String SSQL ="SELECT * FROM `usuarios` WHERE usuario='"+usuario+"' && contraseña='"+clave+"'";
    
        
                Connection con = null; 
        
        try {
            con = metodospool.dataSource.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(SSQL);
            
            if(rs.next()){
                resultado = 1;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex,"Error de conexion", JOptionPane.ERROR_MESSAGE);
        } finally{
            try {
                
                con.close();
                
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex,"Error de desconexion", JOptionPane.ERROR_MESSAGE);
            }
        }
        return resultado;
        
    }

  

    
    public int Validar_Insert(String usuario,String clave,int tpo_user){ 

        String SSQL_INSERT = "INSERT INTO usuarios(usuario,contraseña,tipo_de_usuario) values(?,?,?)";
        
                Connection con = null; 
        
        try {
            con = metodospool.dataSource.getConnection();
            PreparedStatement st = con.prepareStatement(SSQL_INSERT);
            st.setString(1, usuario);
            st.setString(2, clave);
            st.setInt(3, tpo_user);
            int x =st.executeUpdate();
            if(x>0){
                JOptionPane.showMessageDialog(null,"Guardado " ,"Guardado con exito",JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex,"Error de conexion", JOptionPane.ERROR_MESSAGE);
        }
        return 0;
        
    }
     public int Insert_productos(String codigo,String nombre,double costo,int existencia){ 

        String SSQL_INSERT_products = "INSERT INTO `productos`(`codigo_de_barras`, `nombre`, `costo`, `existencia`) values(?,?,?,?)";
        
                Connection con = null; 
        
        try {
            con = metodospool.dataSource.getConnection();
            PreparedStatement st = con.prepareStatement(SSQL_INSERT_products);
            st.setString(1, codigo);
            st.setString(2, nombre);
            st.setDouble(3, costo);
            st.setInt(4, existencia);
            int x =st.executeUpdate();
            if(x>0){
                JOptionPane.showMessageDialog(null,"Guardado " ,"Guardado con exito",JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex,"Error de conexion", JOptionPane.ERROR_MESSAGE);
        }
        return 0;
        
    }
    
    public int valid_user(String user){
        Connection con = null;
        String mySql= "SELECT tipo_de_usuario FROM usuarios WHERE usuario= ?";
        int tipo=0;
        try {
            con = metodospool.dataSource.getConnection();
            PreparedStatement ps=con.prepareStatement(mySql);
            ps.setString(1,user);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                tipo=rs.getInt(1);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.INFORMATION_MESSAGE);
        }
        return tipo;
    }
public int Update_Insert_user(String usuario,String clave,int tpo_user){ //modificar usuario 

        String SSQL_UPDATE = "UPDATE usuarios SET contraseña, tipo_de_usuario value(?,?) WHERE  usuario= ?";

        
                Connection con = null; 
        
        try {
            con = metodospool.dataSource.getConnection();
            PreparedStatement st = con.prepareStatement(SSQL_UPDATE);
            st.setString(1, usuario);
            st.setString(2, clave);
            st.setInt(3, tpo_user);
            int x =st.executeUpdate();
            if(x>0){
                JOptionPane.showMessageDialog(null,"Modificado " ,"Modificado con exito",JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex,"Error de conexion", JOptionPane.ERROR_MESSAGE);
        }
        return 0;
        
    }
 public DefaultTableModel ADD_producto(String barra){
     String SSQL_UPDATE = "SELECT * FROM productos WHERE codigo_de_barras= ?";
                Connection con = null; 
        try {
            con = metodospool.dataSource.getConnection();
            PreparedStatement st = con.prepareStatement(SSQL_UPDATE);
            st.setString(1, barra);
            ResultSet rs = st.executeQuery();
            //int existen=s.getInt(3)
         
        
                  
           // if (a >= 1){
            
            Object []tabla = new Object[4];
            if(rs.next()){
                barras=rs.getString(1);
                tabla[0]=rs.getString(1);
                tabla[1]=rs.getString(2);
                tabla[2]=rs.getString(3);
                tabla[3]=rs.getString(4);
                sistema.q.addRow(tabla);
                
            }
        //} 
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex,"Error de conexion", JOptionPane.ERROR_MESSAGE);
        } finally{
            try {
                
                con.close();
                
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex,"Error de desconexion", JOptionPane.ERROR_MESSAGE);
            }
        }
        return sistema.q;
        
    }
 
 
 
public int modificarProductos(String id){ //modificar usuario 


        try{
         int existencia=0;
    	String SSQL_UPDATE = "UPDATE productos SET existencia= ? WHERE  id= ?";
        String mySql= "SELECT * FROM productos WHERE codigo_de_barras= ?";
             Connection con = null; 
            con = metodospool.dataSource.getConnection();
            PreparedStatement st = con.prepareStatement(SSQL_UPDATE);
            PreparedStatement sin = con.prepareStatement(mySql);
            sin.setString(1, id);
           
        if(existencia>0){  

        	existencia=existencia-1;
            st.setInt(1, existencia);
            st.setString(2, id);
            
         }

            int res = st.executeUpdate();
            if (res > 0){
                JOptionPane.showMessageDialog(null,"Resgistro Guardado con Exito","Exito",JOptionPane.INFORMATION_MESSAGE);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
        }
        return 0;
    }  

public void setCobro(String code, int cant) {
        int existencia=0;
        Connection con = null; 
        System.out.println(cant);
       
            try{
                PreparedStatement PS = metodospool.dataSource.getConnection().prepareStatement("SELECT existencia FROM productos WHERE codigo_de_barras= ?");
                PS.setString(1, code);
                ResultSet RS = PS.executeQuery();
                if (RS.next()){
                    existencia = RS.getInt(1);
                } 
            }catch(SQLException e){
                System.out.println("Error: "+ e.getMessage());
            }finally{
                 Connection PS = null;
                Connection RS = null;
            }
            if (existencia > 0){
               
            
            existencia=existencia-(cant);
            }
            if(cant==-1){
               
            
            existencia=existencia-(cant);
            }
            try{
               PreparedStatement PS = metodospool.dataSource.getConnection().prepareStatement("UPDATE productos SET existencia= ? WHERE codigo_de_barras= ?");
                PS.setInt(1,existencia);
                PS.setString(2, code);
                int y = PS.executeUpdate();

            }catch(SQLException e){
                JOptionPane.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
            }finally{
                Connection PS=null;
                Connection RS=null;
            }
        
        
        
    }
}



