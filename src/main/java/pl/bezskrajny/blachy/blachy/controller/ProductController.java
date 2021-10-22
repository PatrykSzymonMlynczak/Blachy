package pl.bezskrajny.blachy.blachy.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import pl.bezskrajny.blachy.blachy.ProductDto;
import pl.bezskrajny.blachy.blachy.entity.ProductEntity;
import pl.bezskrajny.blachy.blachy.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @ApiOperation(value = "Pobierz wszystkie produkty")
    @GetMapping
    public List<ProductDto> getAllProducts(){
        return productService.getAllProducts();
    }

    @ApiOperation(value = "Dodaj nowy produkt")
    @PostMapping("/{rodzajMaterialu}/{dlugosc}/{szerokosc}/{wysokosc}/{grubosc}/{ilosc}")
    public ProductEntity addProductString(@PathVariable String rodzajMaterialu,
                                          @PathVariable int dlugosc,
                                          @PathVariable int szerokosc,
                                          @PathVariable int wysokosc,
                                          @PathVariable int grubosc,
                                          @PathVariable int ilosc){
        return productService.saveProduct(rodzajMaterialu,dlugosc,szerokosc, wysokosc, grubosc, ilosc);
    }

    @ApiOperation(value = "Dodaj nowy produkt")
    @PostMapping("/{rodzajMaterialu}/{dlugosc}/{szerokoscXwysokoscXgrubosc}/{ilosc}")
    public ProductDto addProductString(@PathVariable String rodzajMaterialu,
                                          @PathVariable int dlugosc,
                                          @PathVariable String szerokoscXwysokoscXgrubosc,
                                          //updating above name demands change StringParser constructor
                                          @PathVariable int ilosc){

        ProductEntity productEntity =  productService.parseAndSaveProduct(rodzajMaterialu,dlugosc,szerokoscXwysokoscXgrubosc,ilosc);

        return new ProductDto(
                productEntity.getId(),
                productEntity.getRodzajMaterialu(),
                productEntity.getDlugosc(),
                ""+productEntity.getSzerokosc()+'x'+productEntity.getWysokosc()+'x'+productEntity.getGrubosc(),
                productEntity.getIlosc()
        );
    }

    @ApiOperation(value = "Aktualizuj ilosc")
    @PutMapping("/{id}/{ilosc}")
    public void updateProduct(@PathVariable Long id, @PathVariable Long ilosc){
        productService.parseAndUpdateProduct(id ,ilosc);
    }

    @ApiOperation(value = "Sprawdz czy istnieje")
    @GetMapping("/ilosc/{rodzajMaterialu}/{dlugosc}/{szerokoscXwysokoscXgrubosc}")
    public float productCheckout(@PathVariable String rodzajMaterialu,
                                               @PathVariable int dlugosc,
                                               @PathVariable String szerokoscXwysokoscXgrubosc){
                                               //updating above name demands change StringParser constructor
        return productService.productCheckout(rodzajMaterialu,dlugosc,szerokoscXwysokoscXgrubosc);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        productService.deleteById(id);
    }
}
