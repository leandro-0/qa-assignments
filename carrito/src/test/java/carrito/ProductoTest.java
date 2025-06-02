package carrito;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductoTest {
    @Test
    @DisplayName("Test crear producto con nombre y precio")
    public void testCrearProducto() {
        Producto p = new Producto(1, "Laptop", 1500.00);
        assertEquals("Laptop", p.getNombre());
        assertEquals(1500.00, p.getPrecio());
    }

    @Test
    @DisplayName("Test crear producto con nombre nulo, precio negativo o id negativo")
    public void testCrearProductoConErrores() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Producto(1, null, 1500.00),
                "El nombre del producto no puede ser nulo"
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> new Producto(1, "Laptop", -1500.00),
                "El precio del producto no puede ser negativo"
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> new Producto(-1, "Laptop", 1500.00),
                "El ID del producto no puede ser negativo"
        );
    }

    @Test
    @DisplayName("Test comparar igualdad de productos por nombre y precio")
    public void testIgualdadProducto() {
        Producto p1 = new Producto(1, "Laptop", 1500.00);
        Producto p2 = new Producto(1, "Laptop", 1500.00);
        Producto p3 = new Producto(3, "Celular", 1000.00);

        assertEquals(p1, p2, "Los productos deben ser iguales");
        assertNotEquals(p1, p3, "Los productos deben ser diferentes");
    }
}