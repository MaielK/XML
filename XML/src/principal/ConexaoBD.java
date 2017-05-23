/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author labin
 */
public class ConexaoBD {
    
    private Connection conexao;

    private final static String url = "jdbc:postgresql://localhost/";
    private final static String baseDados = "xml";
    private final static String usuario = "postgres"; //usuário
    private final static String senha = "postgres";//senha

    public void abreConexao() throws ClassNotFoundException, SQLException {
        if (conexao == null) {
            Class.forName("org.postgresql.Driver");
            conexao = DriverManager.getConnection(url + baseDados, usuario, senha);
        }
    }

    public void fechaConexao() throws SQLException {
        conexao.close();
        conexao = null;
    }
    
    
    public void insereCidade(Cidade cidade) throws SQLException{
        conexao.setAutoCommit(false);
        String sql = "insert into cidade (codigo, nome, uf) values (?, ?, ?)";
        PreparedStatement ps = conexao.prepareStatement(sql);
        ps.setInt(1, cidade.getCodigo());
        ps.setString(2, cidade.getNome());
        ps.setString(3, cidade.getUf());
        ps.execute();
        ps.close();
        conexao.commit();
     }
    
    public void insereCliente(Cliente cliente) throws SQLException{
        conexao.setAutoCommit(false);
        String sql = "insert into cliente (matricula, nome, data, codigo_cidade) values (?, ?, ?, ?)";
        PreparedStatement ps = conexao.prepareStatement(sql);
        ps.setInt(1, cliente.getMatricula());
        ps.setString(2, cliente.getNome());
        ps.setString(3, cliente.getData());
        ps.setInt(4, cliente.getCodigo_cidade());
        ps.execute();
        ps.close();
        conexao.commit();
     }
}
