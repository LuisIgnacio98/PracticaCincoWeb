package edu.pucmm.practica2.Controladora;

import edu.pucmm.practica2.encapsulaciones.Carrito;
import edu.pucmm.practica2.encapsulaciones.Producto;
import edu.pucmm.practica2.Controladora.ProductoControladora;
import edu.pucmm.practica2.encapsulaciones.Usuario;
import edu.pucmm.practica2.encapsulaciones.VentaProducto;
import edu.pucmm.practica2.util.BaseControlador;
import io.javalin.Javalin;
import static io.javalin.apibuilder.ApiBuilder.*;

import java.util.*;

public class CrudControladora  extends  BaseControlador{

    public CrudControladora(Javalin app){ super(app);}

    @Override
    public void aplicarRutas() {

        app.routes(() -> {
            path("/", () ->{

                get("/" , ctx -> {
                   ctx.redirect("/listar");
                });

                get("/listar", ctx -> {
                    ArrayList<Producto> productos = ProductoControladora.getInstance().getProductos();
                    ArrayList<Producto> productosCarrito = CarritoControladora.getInstance().getProductos();
                    Map<String, Object> datos = new HashMap<>();
                    datos.put("productos", productos);
                    datos.put("cantidad", productosCarrito.size());
                    ctx.render("/freemarker/listaProducto.ftl", datos);
                });

                get("/adminProduct", ctx -> {
                    ArrayList<Producto> productos = ProductoControladora.getInstance().getProductos();
                    ArrayList<Producto> productosCarrito = CarritoControladora.getInstance().getProductos();
                    Map<String, Object> datos = new HashMap<>();
                    datos.put("productos", productos);
                    datos.put("cantidad" , productosCarrito.size());
                    ctx.render("/freemarker/adminProducto.ftl", datos);
                });

                get("/registrarProducto" , ctx -> {
                    ArrayList<Producto> productosCarrito = CarritoControladora.getInstance().getProductos();
                    Map<String, Object> datos = new HashMap<>();

                    datos.put("cantidad" , productosCarrito.size());
                    ctx.render("/freemarker/registrarProducto.ftl", datos);
                });

                get("/eliminar/:index", ctx -> {
                    int index = ctx.pathParam("index", Integer.class).get();
                    ProductoControladora.getInstance().getProductos().remove(index);
                    ctx.redirect("/adminProduct");
                });

                get("/eliminar-carrito/:index", ctx -> {
                    int index = ctx.pathParam("index", Integer.class).get();
                    CarritoControladora.getInstance().getProductos().remove(index);
                    ctx.redirect("/carrito");
                });

                get("/actualizar/:index/" , ctx -> {
                    int index = ctx.pathParam("index", Integer.class).get();
                    ArrayList<Producto> productosCarrito = CarritoControladora.getInstance().getProductos();
                    Map<String,Object> attributes = new HashMap<>();
                    Producto producto = ProductoControladora.getInstance().getProductos().get(index);
                    attributes.put("producto", producto);
                    attributes.put("index", index);
                    attributes.put("cantidad", index);
                    ctx.render("/freemarker/actualizarProducto.ftl", attributes);
                });

                get("/carrito", ctx -> {
                    ArrayList<Producto> productosCarrito = CarritoControladora.getInstance().getProductos();
                    Map<String, Object> datos = new HashMap<>();
                    datos.put("productos", productosCarrito);
                    datos.put("cantidad" , productosCarrito.size());
                    ctx.render("/freemarker/Carrito.ftl", datos);
                });

                get("/agregar/:index/", ctx -> {
                    int index = ctx.pathParam("index", Integer.class).get();
                    Producto producto = ProductoControladora.getInstance().getProductos().get(index);
                    CarritoControladora.getInstance().getProductos().add(producto);
                    ctx.redirect("/listar");

                });

                post("/registrar", ctx -> {
                    String nombre = ctx.formParam("nombre");
                    Double precio = ctx.formParam("precio" , Double.class).get();

                    Producto producto = new Producto(nombre, precio);
                    ProductoControladora.getInstance().getProductos().add(producto);
                    ctx.redirect("/adminProduct");
                });

                post("/actualizarProducto/:index/", ctx -> {
                    int index = ctx.pathParam("index", Integer.class).get();
                    String nombre = ctx.formParam("nombre");
                    Double precio = ctx.formParam("precio" , Double.class).get();

                    Producto producto = ProductoControladora.getInstance().getProductos().get(index);

                    producto.setNombre(nombre);
                    producto.setPrecio(precio);

                    ProductoControladora.getInstance().getProductos().set(index, producto);
                    ctx.redirect("/adminProduct");
                });

                get("/compra", ctx -> {
                    ArrayList<Producto> productosCarrito = CarritoControladora.getInstance().getProductos();
                    Map<String, Object> datos = new HashMap<>();
                    Date fecha = new Date();
                    VentaProducto venta = new VentaProducto(fecha.toString(), productosCarrito);
                    VentaProductoControladora.getInstance().setProductos(productosCarrito);
                    VentaProductoControladora.getInstance().getVentas().add(venta);
                    ctx.redirect("carrito");

                });

                get("/ventas", ctx -> {
                   ArrayList<VentaProducto> ventas = VentaProductoControladora.getInstance().getVentas();
                    ArrayList<Producto> productosVenta = VentaProductoControladora.getInstance().getProductos();
                    ArrayList<Producto> productosCarrito = CarritoControladora.getInstance().getProductos();
                    Map<String, Object> datos = new HashMap<>();
                    datos.put("ventas", ventas);
                    datos.put("cantidad", productosCarrito.size());
                    datos.put("productos", productosVenta);
                    ctx.render("/freemarker/VentaRealizadas.ftl", datos);
                });


            });
        });
    }
}
