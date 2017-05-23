/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import java.io.*;
import java.util.*;
import org.jdom2.*;
import org.jdom2.filter.ElementFilter;
import org.jdom2.input.*;
import org.jdom2.output.*;

/**
 *
 * @author labin
 */
public class XML {

    public static void main(String[] args) {

        ConexaoBD conexao = new ConexaoBD();
        Cliente cliente = new Cliente();
        Cidade cidade = new Cidade();
        File arquivo_xml = new File("C:\\Users\\labin\\Downloads\\clientes.xml");
       
        if (arquivo_xml.exists()) {
            SAXBuilder sb = new SAXBuilder();
            Document d;

            try {
                
                d = sb.build(arquivo_xml);
                Element cidades = d.getRootElement();
                List cid = cidades.getChildren();
                Iterator i = cid.iterator();
                conexao.abreConexao();
                
                while (i.hasNext()) {
                    Element element = (Element) i.next();
                    
                   cidade.setCodigo(Integer.parseInt(element.getAttributeValue("codigo")));
                   cidade.setNome(element.getAttributeValue("nome"));
                   cidade.setUf(element.getAttributeValue("uf"));
                   
                   conexao.insereCidade(cidade);
                   
                    List clientes = element.getChildren();
                    Iterator cli = clientes.iterator();
                                        
                    while (cli.hasNext()) {
                        Element client = (Element) cli.next();

                        cliente.setMatricula(Integer.parseInt(client.getChildText("matricula")));
                        cliente.setNome(client.getChildText("nome"));
                        cliente.setData(client.getChildText("nascimento"));
                        cliente.setCodigo_cidade(Integer.parseInt(element.getAttributeValue("codigo")));
                        
                        conexao.insereCliente(cliente);
                    }
                }

            } catch (Exception e) {
               

            }

        }
    }

  
}
