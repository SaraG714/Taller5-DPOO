package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.excepciones.HamburguesaException;
import uniandes.dpoo.hamburguesas.excepciones.IngredienteRepetidoException;
import uniandes.dpoo.hamburguesas.excepciones.NoHayPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.excepciones.ProductoFaltanteException;
import uniandes.dpoo.hamburguesas.excepciones.ProductoRepetidoException;
import uniandes.dpoo.hamburguesas.excepciones.YaHayUnPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.Ingrediente;
import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;
import uniandes.dpoo.hamburguesas.mundo.Restaurante;


public class RestauranteTest {
	
	private Pedido pedido1;
	private Pedido pedido2;

	
    private ArrayList<Ingrediente> ingredientesEsperados;
    private Ingrediente ing1; 
    private Ingrediente ing2;
    private Ingrediente ing3; 
    private Ingrediente ing4;
    
    private ArrayList<ProductoMenu> menuBaseEsperado;
    private ProductoMenu prodMenu1;
    private ProductoMenu prodMenu2;
    private ProductoMenu prodMenu3;
    private ProductoMenu prodMenu4;
    
    private ArrayList<Combo> menuCombosEsperado;
    private Combo combo1;
    private Combo combo2;
    
    private Restaurante restaurante;

    @BeforeEach
    void setUp( ) throws Exception
    {	
        restaurante = new Restaurante();
    }

    @AfterEach
    void tearDown( ) throws Exception
    {
    }
    
    @Test
    void testCargarInformacion() throws IOException, NumberFormatException, HamburguesaException {

    	//Se inicializan los ingredientes esperados
    	ingredientesEsperados = new ArrayList<Ingrediente>();
		ing1 = new Ingrediente("lechuga", 1000);
		ing2 = new Ingrediente("tomate", 1000);
		ing3 = new Ingrediente("cebolla", 1000);
		ing4 = new Ingrediente("queso mozzarella", 2500);	
		ingredientesEsperados.add(ing1);
		ingredientesEsperados.add(ing2);
		ingredientesEsperados.add(ing3);
		ingredientesEsperados.add(ing4);

		//Se inicializa el menu base esperado
		menuBaseEsperado = new ArrayList<ProductoMenu>();
		prodMenu1 = new ProductoMenu("corral", 14000);
		prodMenu2 = new ProductoMenu("corral queso", 16000);
		prodMenu3 = new ProductoMenu("papas medianas", 5500);
		prodMenu4 = new ProductoMenu("gaseosa", 5000);
		menuBaseEsperado.add(prodMenu1);
		menuBaseEsperado.add(prodMenu2);
		menuBaseEsperado.add(prodMenu3);
		menuBaseEsperado.add(prodMenu4);
		
		//Se crea el combo1
		menuCombosEsperado = new ArrayList<Combo>();
		ArrayList<ProductoMenu> itemsCombo1 = new ArrayList<ProductoMenu>();
		itemsCombo1.add(prodMenu1);
		itemsCombo1.add(prodMenu3);
		itemsCombo1.add(prodMenu4);
		combo1 = new Combo("combo corral", 0.1, itemsCombo1);
		
		//Se crea el combo2
		ArrayList<ProductoMenu> itemsCombo2 = new ArrayList<ProductoMenu>();
		itemsCombo2.add(prodMenu2);
		itemsCombo2.add(prodMenu3);
		itemsCombo2.add(prodMenu4);
		combo2 = new Combo("combo corral queso", 0.1, itemsCombo2);		
		
		//Añadir ambos combos al menu combos esperado
		menuCombosEsperado.add(combo1);
		menuCombosEsperado.add(combo2);
    	
		File fileCombos = new File("tests/datosPrueba/combosTest");
		File fileIngs = new File("tests/datosPrueba/ingredientesTest");
		File fileMenu = new File("tests/datosPrueba/menuTest");
		
		restaurante.cargarInformacionRestaurante(fileIngs, fileMenu, fileCombos);
		 
		ArrayList<Ingrediente> ingredientesCargados = restaurante.getIngredientes();
		
		ArrayList<ProductoMenu> menuBaseCargado = restaurante.getMenuBase();
		
		ArrayList<Combo> menuCombosCargado = restaurante.getMenuCombos();
		
		assertEquals(4, ingredientesCargados.size(), "No se cargaron el la cantidad de ingredientes esperados.");
		assertEquals(4, menuBaseCargado.size(), "No se cargó la cantidad de elementos esperados en el menu base.");
		assertEquals(2, menuCombosCargado.size(), "No se cargó la cantidad de elementos esperados en el menu combos.");
		
		for (int i = 0; i < ingredientesEsperados.size(); i++) {
            assertEquals((ingredientesEsperados.get(i)).getNombre(), (ingredientesCargados.get(i)).getNombre(),
            		"El nombre del ingrediente en el índice " + i + " no es el esperado.");
            assertEquals((ingredientesEsperados.get(i)).getCostoAdicional(), (ingredientesCargados.get(i)).getCostoAdicional(), 
            		"El costo adicional del ingrediente en el índice"+i+"no es el esperado.");
		}
		
		for (int i = 0; i < menuBaseEsperado.size(); i++) {
            assertEquals((menuBaseEsperado.get(i)).getNombre(), (menuBaseCargado.get(i)).getNombre(), 
            		"El nombre del producto menu en el índice " + i + " no es el esperado.");
            assertEquals((menuBaseEsperado.get(i)).getPrecio(), (menuBaseCargado.get(i)).getPrecio(),
            		"El precio del producto menu en el índice"+i+"no es el esperado.");
		}
		
		for (int i = 0; i < menuCombosEsperado.size(); i++) {
            assertEquals((menuCombosEsperado.get(i)).getNombre(), (menuCombosCargado.get(i)).getNombre(), 
            		"El nombre del combo en el índice " + i + " no es el esperado.");
            assertEquals((menuCombosEsperado.get(i)).getPrecio(), (menuCombosCargado.get(i)).getPrecio(), 
            		"El precio del combo en el índice"+i+"no es el esperado.");
		}
		
    }
    
    @Test
    void testIngRep() {
    	
		File fileCombos = new File("tests/datosPrueba/combosTest");
		File fileIngsRep = new File("tests/datosPrueba/ingredientesRepTest");		
		File fileMenu = new File("tests/datosPrueba/menuTest");
		
		assertThrows(IngredienteRepetidoException.class, () -> {restaurante.cargarInformacionRestaurante(fileIngsRep, fileMenu, fileCombos);});
    	
    }
    
    @Test
    void testMenuRep() {
    	
		File fileCombos = new File("tests/datosPrueba/combosTest");
		File fileIngs = new File("tests/datosPrueba/ingredientesTest");
		File fileMenuRep = new File("tests/datosPrueba/menuRepTest");
		
		assertThrows(ProductoRepetidoException.class, () -> {restaurante.cargarInformacionRestaurante(fileIngs, fileMenuRep, fileCombos);});
    	
    }
    
    @Test
    void testRepCombos() {
    	
		File fileCombosErr = new File("tests/datosPrueba/combosErrorTest");
		File fileIngs = new File("tests/datosPrueba/ingredientesTest");
		File fileMenu = new File("tests/datosPrueba/menuTest");
		
		assertThrows(ProductoRepetidoException.class, () -> {restaurante.cargarInformacionRestaurante(fileIngs, fileMenu, fileCombosErr);});

    }
    
    @Test
    public void testFaltanteCombos() throws IOException {

    	
		File fileIngs = new File("tests/datosPrueba/ingredientesTest");
		File fileMenu = new File("tests/datosPrueba/menuTest");
    	
        File fileFaltCombo = File.createTempFile("testCombo", ".txt");
        FileWriter writer = new FileWriter(fileFaltCombo);
        writer.write("Combo Familiar;10;chips\n");
        writer.close();

        assertThrows(ProductoFaltanteException.class, () -> {restaurante.cargarInformacionRestaurante(fileIngs, fileMenu, fileFaltCombo);});

        fileFaltCombo.delete();
    }
    
	
	@Test
	void testIniciarPedido() throws YaHayUnPedidoEnCursoException{
		
		pedido1 = new Pedido("Amanda Castillo", "Calle 103 #56");
		restaurante.iniciarPedido("Amanda Castillo", "Calle 103 #56");
		
		Pedido pedidoObtenido = restaurante.getPedidoEnCurso();
		
		assertEquals(pedido1.getNombreCliente(), pedidoObtenido.getNombreCliente(), "El cliente del pedido en curso no es el esperado");
		
	}

	@Test
	void testIniciarPedidoException() throws YaHayUnPedidoEnCursoException{
		
		pedido1 = new Pedido("Amanda Castillo", "Calle 103 #56");
		pedido2 = new Pedido("Olivia Rodríguez", "Avenida 13 #98");
		restaurante.iniciarPedido("Amanda Castillo", "Calle 103 #56");
				
        assertThrows(YaHayUnPedidoEnCursoException.class, () -> {restaurante.iniciarPedido("Olivia Rodríguez", "Avenida 13 #98"); });
	}
	
	@Test 
	void testCerrarYGuardarPedido() throws YaHayUnPedidoEnCursoException, NoHayPedidoEnCursoException, IOException {
		pedido1 = new Pedido("Amanda Castillo", "Calle 103 #56");
		
		assertThrows(NoHayPedidoEnCursoException.class, () -> { restaurante.cerrarYGuardarPedido(); });
		
		restaurante.iniciarPedido("Amanda Castillo", "Calle 103 #56");
		restaurante.cerrarYGuardarPedido();
		

    	String textoFactura = "";
    

    	File tempFile = null;
    	tempFile = File.createTempFile("facturaTest", ".txt");


    	String contenidoObtenido = Files.readString(tempFile.toPath());

    	assertEquals(textoFactura, contenidoObtenido, "El texto del archivo no coincide con lo esperado.");

    	//No se guarda la factura, por ende, contenidoObtenido es un string vacío. Esto muestra un error en Restaurante. 
    	//Iván dijo que solo debíamos detectar los errores, no necesariamente corregirlos.
		
	}
	
	
	@Test
	void testGetPedidos() throws YaHayUnPedidoEnCursoException, NoHayPedidoEnCursoException, IOException {
		pedido1 = new Pedido("Amanda Castillo", "Calle 103 #56");
		pedido2 = new Pedido("Olivia Rodríguez", "Avenida 13 #98");
		restaurante.iniciarPedido("Amanda Castillo", "Calle 103 #56");
		restaurante.cerrarYGuardarPedido();
		restaurante.iniciarPedido("Olivia Rodríguez", "Avenida 13 #98");
		restaurante.cerrarYGuardarPedido();
		
		assertEquals(2, (restaurante.getPedidos().size()), "La cantidad de pedidos en el historial no es la esperada.");
	}
	
	
}