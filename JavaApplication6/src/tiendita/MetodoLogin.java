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

/**
 *
 * @author Damián Javier Mejía y Arturo Nieva
 */
public class MetodoLogin {
    
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

        String SSQL_INSERT_products = "INSERT INTO `productos`(`codigo de barras`, `nombre`, `costo`, `existencia`) values(?,?,?,?)";
        
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
    
}




