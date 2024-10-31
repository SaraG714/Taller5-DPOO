package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.Ingrediente;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;


public class ProductoAjustadoTest {
	
	private ProductoMenu prodMenu1;
	private Ingrediente ing1;
	private Ingrediente ing2;
	private Ingrediente ing3;
	
	private ProductoAjustado prodAju1;
	
	@BeforeEach
	void setUp() throws Exception
	{
		prodMenu1 = new ProductoMenu("hamburguesa de pollo", 23000);
		ing1 = new Ingrediente("salsa rosada", 500);
		ing2 = new Ingrediente("tocineta", 2000);
		ing3 = new Ingrediente("cebolla caramelizada", 1000);
		
		prodAju1 = new ProductoAjustado(prodMenu1);
		
		prodAju1.addAgregado(ing2);
		prodAju1.addAgregado(ing3);
		prodAju1.addEliminado(ing1);
	}
	
    @AfterEach
    void tearDown( ) throws Exception
    {
    }

    @Test
    void testGetNombre( )
    {
        assertEquals( "hamburguesa de pollo", prodAju1.getNombre( ), "El nombre del producto no es el esperado." );
    }

    @Test
    void testGetPrecio( )
    {
        assertEquals( 26000, prodAju1.getPrecio( ), "El precio del producto no es el esperado." );
    }
    
    

    @Test
    void testGenerarTextoFactura( )
    {
    	String textoFactura = "hamburguesa de pollo" + "    +" + "tocineta" + "                " + "2000"+ 
    "    +" + "cebolla caramelizada" + "                " + "1000"+ "    -" + "salsa rosada"+ "            " + "26000"+ "\n";
    	
    	assertEquals(textoFactura, prodAju1.generarTextoFactura(), "La factura no es la esperada.");
    
    }
    
    
	

	
}
