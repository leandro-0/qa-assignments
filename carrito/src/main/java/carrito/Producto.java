package carrito;

import lombok.Getter;

@Getter
public class Producto {
    private String nombre;
    private double precio;

    public Producto(String nombre, double precio) {
        if (nombre == null) {
            throw new IllegalArgumentException("El nombre del producto no puede ser nulo");
        }
        if (precio < 0) {
            throw new IllegalArgumentException("El precio del producto no puede ser negativo");
        }

        this.nombre = nombre;
        this.precio = precio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Producto)) return false;
        Producto other = (Producto) o;
        return Double.compare(other.precio, precio) == 0 && nombre.equals(other.nombre);
    }
}
