package pl.bezskrajny.blachy.blachy;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDto {

    private Long id;

    private String rodzajMaterialu;
    private int dlugosc;
    private String szerokoscXwysokoscXgrubosc;
    private int ilosc;

    public ProductDto(String rodzajMaterialu, int dlugosc, String szerokoscXwysokoscXgrubosc, int ilosc) {
        this.rodzajMaterialu = rodzajMaterialu;
        this.dlugosc = dlugosc;
        this.szerokoscXwysokoscXgrubosc =szerokoscXwysokoscXgrubosc;
        this.ilosc = ilosc;
    }
}
