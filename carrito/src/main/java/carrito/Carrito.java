package carrito;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Optional;

@Getter
public class Carrito {
    private ArrayList<ItemCarrito> items;

    public Carrito() {
        this.items = new ArrayList<>();
    }

    public double getTotal() {
        return items.stream()
                .mapToDouble(ItemCarrito::getSubtotal)
                .sum();
    }

    // Agrega un item al carrito. Si el item ya existe, suma la cantidad
    public void agregarItem(ItemCarrito item) {
        if (item == null) {
            throw new IllegalArgumentException("El item no puede ser nulo");
        }

        Optional<ItemCarrito> existingItem = items.stream()
                .filter(i -> i.equals(item))
                .findFirst();

        if (existingItem.isEmpty()) {
            items.add(item);
        } else {
            existingItem.get().setCantidad(existingItem.get().getCantidad() + item.getCantidad());
        }
    }

    public void eliminarItem(ItemCarrito item) {
        boolean result = items.remove(item);
        if (!result) {
            throw new IllegalArgumentException("El item no está en el carrito");
        }
    }

    public void cambiarCantidad(ItemCarrito item, int cantidad) {
        if (items.contains(item)) {
            item.setCantidad(cantidad);
        } else {
            throw new IllegalArgumentException("El item no está en el carrito");
        }
    }
}
