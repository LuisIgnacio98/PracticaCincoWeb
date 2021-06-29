package edu.pucmm.practica2.encapsulaciones;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class VentaProducto {
    private static final AtomicInteger count = new AtomicInteger(0);
    private Integer id;
    private String fechaCompra;
    private ArrayList<Producto> productos;

    public VentaProducto(String fechaCompra, ArrayList<Producto> productos){
        this.id = count.incrementAndGet();
        this.fechaCompra = fechaCompra;
        this.productos = productos;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(String fechaCompra) {
        this.fechaCompra = fechaCompra;
    }
}
