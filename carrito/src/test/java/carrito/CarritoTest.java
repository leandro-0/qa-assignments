package carrito;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CarritoTest {
    private Carrito carrito;
    private Producto producto1;
    private Producto producto2;

    @BeforeEach
    public void setUp() {
        carrito = new Carrito();
        producto1 = new Producto("Laptop", 1500.00);
        producto2 = new Producto("Celular", 1000.00);
    }

    @Test
    @DisplayName("Test agregar productos al carrito")
    public void testAgregarProducto() {
        carrito.agregarItem(new ItemCarrito(producto1, 2));
        carrito.agregarItem(new ItemCarrito(producto2, 1));

        assertEquals(4000.00, carrito.getTotal(), 0.01);
        assertEquals(2, carrito.getItems().size());
        assertEquals(2, carrito.getItems().get(0).getCantidad());
        assertEquals(1, carrito.getItems().get(1).getCantidad());
    }

    @Test
    @DisplayName("Agregar producto existente")
    public void testAgregarProductoExistente() {
        carrito.agregarItem(new ItemCarrito(producto1, 2));
        carrito.agregarItem(new ItemCarrito(producto1, 3));

        assertEquals(7500.00, carrito.getTotal(), 0.01);
        assertEquals(1, carrito.getItems().size());
        assertEquals(5, carrito.getItems().get(0).getCantidad(), "Cantidad debe ser la suma de las cantidades agregadas");
    }

    @Test
    @DisplayName("Test eliminar producto del carrito")
    public void testEliminarProducto() {
        ItemCarrito item1 = new ItemCarrito(producto1, 2);
        ItemCarrito item2 = new ItemCarrito(producto2, 1);

        carrito.agregarItem(item1);
        carrito.agregarItem(item2);

        carrito.eliminarItem(item1);
        assertEquals(1000.00, carrito.getTotal(), 0.01);

        assertEquals(1, carrito.getItems().size(), "Debe quedar solo un item en el carrito");
        assertEquals(item2, carrito.getItems().get(0), "El item restante debe ser el celular");
    }

    @Test
    @DisplayName("Test cambiar cantidad de producto en el carrito")
    public void testCambiarCantidadProducto() {
        ItemCarrito item = new ItemCarrito(producto1, 2);
        carrito.agregarItem(item);

        carrito.cambiarCantidad(item, 5);
        assertEquals(7500.00, carrito.getTotal(), 0.01);
        assertEquals(5, item.getCantidad(), "La cantidad debe actualizarse correctamente");
    }

    @Test
    @DisplayName("Total de la compra")
    public void testTotalCompra() {
        carrito.agregarItem(new ItemCarrito(producto1, 2));
        carrito.agregarItem(new ItemCarrito(producto2, 1));

        assertEquals(4000.00, carrito.getTotal(), 0.01, "El total debe ser la suma de los subtotales de los items");
    }

    @Test
    @DisplayName("Test agregar item nulo al carrito")
    public void testAgregarItemNulo() {
        assertThrows(
            IllegalArgumentException.class,
            () -> carrito.agregarItem(null),
            "El item no puede ser nulo"
        );
    }

    @Test
    @DisplayName("Test eliminar item no existente del carrito")
    public void testEliminarItemNoExistente() {
        ItemCarrito item = new ItemCarrito(producto1, 2);
        assertThrows(
            IllegalArgumentException.class,
            () -> carrito.eliminarItem(item),
            "El item no está en el carrito"
        );
    }

    @Test
    @DisplayName("Test cambiar cantidad de item no existente en el carrito")
    public void testCambiarCantidadItemNoExistente() {
        ItemCarrito item = new ItemCarrito(producto1, 2);
        assertThrows(
            IllegalArgumentException.class,
            () -> carrito.cambiarCantidad(item, 3),
            "El item no está en el carrito"
        );
    }
}
