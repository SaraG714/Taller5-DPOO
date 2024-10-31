package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;


public class ProductoMenuTest {

	private ProductoMenu prodMenu1;
	
    @BeforeEach
    void setUp( ) throws Exception
    {
        prodMenu1 = new ProductoMenu( "papas fritas", 11000 );
    }
    
    
    @AfterEach
    void tearDown( ) throws Exception
    {
    }

    @Test
    void testGetNombre( )
    {
        assertEquals( "papas fritas", prodMenu1.getNombre( ), "El nombre del producto no es el esperado." );
    }

    @Test
    void testGetPrecio( )
    {
        assertEquals( 11000, prodMenu1.getPrecio( ), "El precio del producto no es el esperado." );
    }

    @Test
    void testGenerarTextoFactura( )
    {
    	String textoFactura = "papas fritas" + "\n" + "            " + "11000" + "\n";
    	assertEquals(textoFactura, prodMenu1.generarTextoFactura(), "El texto de la factura no es el esperado.");
    }
    
    
}
