package pl.bezskrajny.blachy.blachy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String rodzajMaterialu;
    private int dlugosc;
    private int szerokosc;
    private int wysokosc;
    private int grubosc;
    private int ilosc;

    public ProductEntity(String rodzajMaterialu, int dlugosc, int szerokosc, int wysokosc, int grubosc, int ilosc) {
        this.rodzajMaterialu = rodzajMaterialu;
        this.dlugosc = dlugosc;
        this.szerokosc = szerokosc;
        this.wysokosc = wysokosc;
        this.grubosc = grubosc;
        this.ilosc = ilosc;
    }

/*    public ProductEntity(String rodzajMaterialu, int dlugosc, String szerokoscXwysokoscXgrubosc, int ilosc) {
        this.rodzajMaterialu = rodzajMaterialu;
        this.dlugosc = dlugosc;
        this.szerokosc = szerokosc;
        this.wysokosc = wysokosc;
        this.grubosc = grubosc;
        this.ilosc = ilosc;
    }*/
}