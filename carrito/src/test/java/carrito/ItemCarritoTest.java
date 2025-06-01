package carrito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ItemCarritoTest {
    private Producto producto;

    @BeforeEach
    public void setUp() {
        producto = new Producto("Laptop", 1500.00);
    }

    @Test
    @DisplayName("Test crear item de carrito con producto y cantidad")
    public void testCrearItemCarrito() {
        ItemCarrito item = new ItemCarrito(producto, 2);

        assertEquals(2, item.getCantidad());
        assertEquals(3000.00, item.getSubtotal(), 0.01);
    }

    @Test
    @DisplayName("Test crear item de carrito con producto nulo o cantidad no positiva")
    public void testCrearItemCarritoConErrores() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new ItemCarrito(null, 2),
                "El producto no puede ser nulo"
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> new ItemCarrito(producto, 0),
                "La cantidad debe ser mayor que cero"
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> new ItemCarrito(producto, -1),
                "La cantidad debe ser mayor que cero"
        );
    }

    @Test
    @DisplayName("Test obtener subtotal de item de carrito")
    public void testCambiarCantidadItemCarrito() {
        ItemCarrito item = new ItemCarrito(producto, 2);
        item.setCantidad(4);

        assertEquals(4, item.getCantidad());
        assertEquals(6000.00, item.getSubtotal(), 0.01);
    }

    @Test
    @DisplayName("Test comparar igualdad de items de carrito")
    public void testIgualdadItemCarrito() {
        ItemCarrito item1 = new ItemCarrito(producto, 2);
        ItemCarrito item2 = new ItemCarrito(producto, 2);
        ItemCarrito item3 = new ItemCarrito(new Producto("Celular", 1000.00), 1);
        ItemCarrito item4 = new ItemCarrito(producto, 3);

        assertEquals(item1, item2, "Los items deben ser iguales");
        assertEquals(item1.getSubtotal(), item2.getSubtotal(), "Los subtotales deben ser iguales");
        assertNotEquals(item1, item3, "Los items deben ser diferentes");
        assertEquals(item1, item4, "Los items deben ser iguales porque son el mismo producto");
    }
}
