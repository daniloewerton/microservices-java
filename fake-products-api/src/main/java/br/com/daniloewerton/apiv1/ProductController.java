package br.com.daniloewerton.apiv1;

import br.com.daniloewerton.apiv1.dto.ProductRequest;
import br.com.daniloewerton.apiv1.dto.ProductResponse;
import br.com.daniloewerton.business.FakeApiService;
import br.com.daniloewerton.business.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Tag(name = "fake-products-api")
public class ProductController {

    private final FakeApiService fakeApiService;
    private final ProductService productService;

    @Operation(summary = "Realiza uma busca por todos os produtos e salva no banco", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar busca")
    })
    @PostMapping("/api")
    public ResponseEntity<List<ProductResponse>> getProducts() {
        return ResponseEntity.ok(fakeApiService.getAndSaveProducts());
    }

    @Operation(summary = "Salva novos produtos", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto salvo com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao salvar produto")
    })
    @PostMapping
    public ResponseEntity<ProductResponse> saveProducts(@RequestBody ProductRequest request) {
        return ResponseEntity.ok(productService.saveProductDTO(request));
    }

    @Operation(summary = "Atualiza produtos existentes", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao atualizar produto")
    })
    @PutMapping
    public ResponseEntity<ProductResponse> updateProducts(@RequestParam("id") String id, @RequestBody ProductResponse response) {
        return ResponseEntity.ok(productService.updateProduct(id, response));
    }

    @Operation(summary = "Deleta produtos existentes", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto removido com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao remover produto")
    })
    @DeleteMapping
    public ResponseEntity<Void> deleteProducts(@RequestParam("title") String title) {
        productService.delete(title);
        return ResponseEntity.accepted().build();
    }

    @Operation(summary = "Busca por todos os produtos cadastrados", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar busca")
    })
    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @Operation(summary = "Busca produtos por nome", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar busca")
    })
    @GetMapping("/{name}")
    public ResponseEntity<ProductResponse> getProductByName(@PathVariable("name") String title) {
        return ResponseEntity.ok(productService.getProductByName(title));
    }
}