package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ComboTest {
	
	private Combo combo1;
	private ProductoMenu prod1;
	private ProductoMenu prod2;
	private ProductoMenu prod3;
	private ArrayList<ProductoMenu> itemsCombo1;
	
	@BeforeEach
	void setUp() throws Exception
	{
		itemsCombo1 = new ArrayList<ProductoMenu>();
		prod1 = new ProductoMenu("hamburguesa extra", 25000);
		prod2 = new ProductoMenu("chips de papa", 5000);
		prod3 = new ProductoMenu("gaseosa", 2500);
		
		itemsCombo1.add(prod1);
		itemsCombo1.add(prod2);
		itemsCombo1.add(prod3);
		
		combo1 = new Combo("hamburguesa full", 0.1, itemsCombo1);

	}
	
    @AfterEach
    void tearDown( ) throws Exception
    {
    }

    
    @Test
    void testGetNombre( )
    {
        assertEquals( "hamburguesa full", combo1.getNombre( ), "El nombre del producto no es el esperado." );
    }
 
    
	@Test
	void testGetPrecio() {
		
		assertEquals(29250, combo1.getPrecio(), "El precio no es el esperado");
	}
	
	
    @Test
    void testGenerarTextoFactura( )
    {
    	String textoFactura = "Combo " + "hamburguesa full" + "\n" + " Descuento: " + "0.1"+ "\n"+ "            "+ "29250" + "\n";
    		
    	assertEquals(textoFactura, combo1.generarTextoFactura(), "La factura no es la esperada.");
    
    }

}
