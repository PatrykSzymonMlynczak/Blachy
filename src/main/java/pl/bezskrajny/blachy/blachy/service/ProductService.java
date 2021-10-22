package pl.bezskrajny.blachy.blachy.service;

import org.springframework.stereotype.Service;
import pl.bezskrajny.blachy.blachy.ProductDto;
import pl.bezskrajny.blachy.blachy.entity.ProductEntity;
import pl.bezskrajny.blachy.blachy.helper.Parameters;
import pl.bezskrajny.blachy.blachy.helper.StringParser;
import pl.bezskrajny.blachy.blachy.repository.ProductRepo;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepo productRepo;
    private final StringParser stringParser;

    public ProductService(ProductRepo productRepo, StringParser stringParser) {
        this.productRepo = productRepo;
        this.stringParser = stringParser;
    }

    public ProductEntity saveProduct(String rodzajMaterialu, int dlugosc, int szerokosc, int wysokosc, int grubosc, int ilosc) {
        ProductEntity productEntity = new ProductEntity(rodzajMaterialu, dlugosc, szerokosc, wysokosc, grubosc, ilosc);

        return productRepo.save(productEntity);
    }

    public ProductEntity parseAndSaveProduct(String rodzajMaterialu, int dlugosc, String szerokoscXwysokoscXgrubosc, int ilosc) {
        Parameters parameters = stringParser.parseStringValue(szerokoscXwysokoscXgrubosc);
        ProductEntity productEntity = new ProductEntity(
                rodzajMaterialu,
                dlugosc,
                parameters.getSzerokosc(),
                parameters.getWysokosc(),
                parameters.getGrubosc(),
                ilosc);
        return productRepo.save(productEntity);
    }

    public List<ProductDto> getAllProducts() {
        List<ProductDto> dtoList = new ArrayList();
        productRepo.findAll().forEach(productEntity -> {
            dtoList.add(new ProductDto(
                    productEntity.getId(),
                    productEntity.getRodzajMaterialu(),
                    productEntity.getDlugosc(),
                    ""+productEntity.getSzerokosc()+'x'+productEntity.getWysokosc()+'x'+productEntity.getGrubosc(),
                    productEntity.getIlosc()
            ));
        });
        return dtoList;
    }

    public void parseAndUpdateProduct(Long id, Long ilosc) {
        productRepo.updateProduct(id, ilosc);

    }

    public float productCheckout(String rodzajMaterialu, int dlugosc, String szerokoscXwysokoscXgrubosc) {
        Parameters parameters = stringParser.parseStringValue(szerokoscXwysokoscXgrubosc);

        return productRepo.productCheckout(
                rodzajMaterialu,
                dlugosc,
                parameters.getSzerokosc(),
                parameters.getWysokosc(),
                parameters.getGrubosc());
    }

    public void deleteById(Long id) {
        productRepo.deleteById(id);
    }
}
