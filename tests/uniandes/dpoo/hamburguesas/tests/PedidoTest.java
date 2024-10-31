package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;


public class PedidoTest {
	
	private ProductoMenu prod1;
	private ProductoMenu prod2;
	
	private Pedido pedido1;
	private Pedido pedido2;
	private Pedido pedido3;
	
	@BeforeEach
	void setUp() throws Exception
	{
		prod1 = new ProductoMenu("papas fritas", 11000 );
		prod2 = new ProductoMenu("hamburguesa pequeña", 20000);
		
		pedido1 = new Pedido("Amanda Castillo", "Calle 103 #56");
		pedido2 = new Pedido("Olivia Rodríguez", "Avenida 13 #98");
		pedido3 = new Pedido("Enrique Ruiz", "Calle 30 #25");

		pedido2.agregarProducto(prod1);
		pedido2.agregarProducto(prod2);
	}
	
    @AfterEach
    void tearDown( ) throws Exception
    {
    }
    
    @Test
    void testGetIdPedido() {
    	
    	int idInicial = pedido1.getIdPedido();
    	assertEquals(idInicial + 1, pedido2.getIdPedido(), "No se generó el ID correcto");
    	assertEquals(idInicial + 2, pedido3.getIdPedido(), "No se generó el ID correcto");
    }
    
    @Test
    void testGetNombreCliente() {
    	
    	assertEquals("Amanda Castillo", pedido1.getNombreCliente(), "El nombre del cliente no es el esperado.");
    	assertEquals("Olivia Rodríguez", pedido2.getNombreCliente(), "El nombre del cliente no es el esperado.");
    	assertEquals("Enrique Ruiz", pedido3.getNombreCliente(), "El nombre del cliente no es el esperado.");
    }

    @Test 
    void testGetPrecioTotalPedido (){
    	
    	assertEquals(36890, pedido2.getPrecioTotalPedido(), "El precio total no es el esperado.");
    }
    
    @Test
    void testGenerarTextoFactura( )
    {
    	String textoFactura = "Cliente: " + "Olivia Rodríguez" + "\n" + "Dirección: " + "Avenida 13 #98"+ "\n"+
    "----------------\n"+ "papas fritas" + "\n" + "            " + "11000" + "\n"+
    			"hamburguesa pequeña" + "\n" + "            " + "20000" + "\n"+ "----------------\n"+
    "Precio Neto:  " + "31000"+ "\n"+ "IVA:          " + "5890"+ "\n"+ "Precio Total: " + "36890"+ "\n";
    
    	assertEquals(textoFactura, pedido2.generarTextoFactura(), "La factura no es la esperada.");
 
    }

    @Test
    void testGuardarFactura() throws FileNotFoundException, IOException{

    	String textoFactura = "Cliente: " + "Olivia Rodríguez" + "\n" + "Dirección: " + "Avenida 13 #98"+ "\n"+
    "----------------\n"+ "papas fritas" + "\n" + "            " + "11000" + "\n"+
    			"hamburguesa pequeña" + "\n" + "            " + "20000" + "\n"+ "----------------\n"+
    "Precio Neto:  " + "31000"+ "\n"+ "IVA:          " + "5890"+ "\n"+ "Precio Total: " + "36890"+ "\n";
    

    	File tempFile = null;
    	tempFile = File.createTempFile("facturaTest", ".txt");

    	pedido2.guardarFactura(tempFile);

    	String contenidoObtenido = Files.readString(tempFile.toPath());

    	assertEquals(textoFactura, contenidoObtenido, "El textp del archivo no coincide con lo esperado.");

    }
    
    @Test
    void testGuardarFacturaException () throws IOException {
    	File archivoInvalido = File.createTempFile("/ruta/no/valida/factura", ".txt");
    	archivoInvalido.setReadOnly();
    	archivoInvalido.deleteOnExit();
    	
        assertThrows(FileNotFoundException.class, () -> {pedido2.guardarFactura(archivoInvalido); });
    }
    


}
