package carrito;

import lombok.Getter;

@Getter
public class Producto {
    private int id;
    private String nombre;
    private double precio;

    public Producto(int id, String nombre, double precio) {
        if (id < 0) {
            throw new IllegalArgumentException("El ID del producto no puede ser negativo");
        }
        if (nombre == null) {
            throw new IllegalArgumentException("El nombre del producto no puede ser nulo");
        }
        if (precio < 0) {
            throw new IllegalArgumentException("El precio del producto no puede ser negativo");
        }

        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Producto)) return false;
        Producto other = (Producto) o;
        return other.id == id;
    }
}
