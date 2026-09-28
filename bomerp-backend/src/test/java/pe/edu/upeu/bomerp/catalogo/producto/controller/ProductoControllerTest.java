package pe.edu.upeu.bomerp.catalogo.producto.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import pe.edu.upeu.bomerp.catalogo.categoria.dto.CategoriaResumen;
import pe.edu.upeu.bomerp.catalogo.producto.dto.ProductoRequest;
import pe.edu.upeu.bomerp.catalogo.producto.dto.ProductoResponse;
import pe.edu.upeu.bomerp.catalogo.producto.service.ProductoService;
import pe.edu.upeu.bomerp.exception.ResourceNotFoundException;
import tools.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductoController.class)
class ProductoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ProductoService productoService;

    @Test
    void listar_respondeOkConLosProductosDelService() throws Exception {
        when(productoService.listar()).thenReturn(List.of(
                ProductoResponse.builder().id(1L).nombre("Teclado mecánico").precio(new BigDecimal("180.50")).stock(25).build()
        ));

        mockMvc.perform(get("/api/v1/productos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Teclado mecánico"));
    }

    @Test
    void crear_conDatosValidos_respondeCreated() throws Exception {
        ProductoRequest request = new ProductoRequest();
        request.setNombre("Teclado mecánico");
        request.setPrecio(new BigDecimal("180.50"));
        request.setStock(25);
        request.setCategoriaId(1L);

        when(productoService.crear(any())).thenReturn(
                ProductoResponse.builder().id(1L).nombre("Teclado mecánico").precio(new BigDecimal("180.50")).stock(25)
                        .categoria(new CategoriaResumen(1L, "Electrodomesticos")).build()
        );

        mockMvc.perform(post("/api/v1/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.categoria.nombre").value("Electrodomesticos"));
    }

    @Test
    void crear_conNombreVacio_respondeBadRequestSinLlegarAlService() throws Exception {
        ProductoRequest request = new ProductoRequest();
        request.setNombre("");
        request.setPrecio(new BigDecimal("10"));
        request.setStock(1);
        request.setCategoriaId(1L);

        mockMvc.perform(post("/api/v1/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void crear_conCategoriaIdNulo_respondeBadRequestSinLlegarAlService() throws Exception {
        ProductoRequest request = new ProductoRequest();
        request.setNombre("Teclado mecánico");
        request.setPrecio(new BigDecimal("180.50"));
        request.setStock(25);

        mockMvc.perform(post("/api/v1/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void obtener_conIdInexistente_respondeNotFound() throws Exception {
        when(productoService.obtener(999L)).thenThrow(new ResourceNotFoundException("Producto no encontrado: 999"));

        mockMvc.perform(get("/api/v1/productos/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void listar_conCategoriaIdExistente_respondeOkFiltrado() throws Exception {
        when(productoService.listarPorCategoria(1L)).thenReturn(List.of(
                ProductoResponse.builder().id(10L).nombre("Teclado mecánico").precio(new BigDecimal("180.50")).stock(25).build()
        ));

        mockMvc.perform(get("/api/v1/productos").param("categoriaId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(10));
    }

    @Test
    void listar_conCategoriaIdInexistente_respondeNotFound() throws Exception {
        when(productoService.listarPorCategoria(999L)).thenThrow(new ResourceNotFoundException("Categoria no encontrada: 999"));

        mockMvc.perform(get("/api/v1/productos").param("categoriaId", "999"))
                .andExpect(status().isNotFound());
    }
}
