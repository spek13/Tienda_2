/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiendita;

import java.sql.Connection;
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
        String SSQL ="SELECT * FROM `usuarios` WHERE 1";
       
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
    
}
