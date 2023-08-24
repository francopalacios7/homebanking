package com.MindHub.HomeBanking.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Producto implements Comparable<Producto> {
    private String nombre;
    private String atributo;
    private double precio;

    public Producto(String nombre, String atributo, double precio) {
        this.nombre = nombre;
        this.atributo = atributo;
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Nombre: " + " " + nombre + " " + " /// " + " " + atributo + " " + " /// Precio: $" + " " + precio;
    }

    @Override
    public int compareTo(Producto otro) {
        return Double.compare(otro.precio, this.precio);
    }

    public String getNombre() {
        return nombre;
    }
}

public class Supermercado {
    public static void main(String[] args) {
        List<Producto> productos = cargarProductos();

        imprimirProductos(productos);
        imprimirProductoMasCaro(productos);
        imprimirProductoMasBarato(productos);
    }

    public static List<Producto> cargarProductos() {
        List<Producto> productos = new ArrayList<>();

        productos.add(new Producto("Coca-Cola Zero", "Litros: 1.5", 20));
        productos.add(new Producto("Coca-Cola", "Litros: 1.5", 18));
        productos.add(new Producto("Shampoo Sedal", "Contenido: 500ml", 19));
        productos.add(new Producto("Frutillas", "Unidad de venta: kilo", 64));

        return productos;
    }

    public static void imprimirProductos(List<Producto> productos) {
        for (Producto producto : productos) {
            System.out.println(producto);
        }
        System.out.println("=============================");
    }

    public static void imprimirProductoMasCaro(List<Producto> productos) {
        Producto masCaro = Collections.max(productos);
        System.out.println("Producto más caro: " + masCaro.getNombre());
    }

    public static void imprimirProductoMasBarato(List<Producto> productos) {
        Producto masBarato = Collections.min(productos);
        System.out.println("Producto más barato: " + masBarato.getNombre());
    }
}





