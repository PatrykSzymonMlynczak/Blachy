package pl.bezskrajny.blachy.blachy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.bezskrajny.blachy.blachy.entity.ProductEntity;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<ProductEntity, Long> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE product_entity SET ilosc = :ilosc " +
            "WHERE szerokosc = :szerokosc " +
            "and wysokosc = :wysokosc " +
            "and grubosc = :grubosc " +
            "and dlugosc = :dlugosc " +
            "and rodzaj_materialu = :rodzajMaterialu", nativeQuery = true)
    void updateProduct(
            @Param("rodzajMaterialu") String rodzajMaterialu,
            @Param("dlugosc") int dlugosc,
            @Param("szerokosc") int szerokosc,
            @Param("wysokosc") int wysokosc,
            @Param("grubosc") int grubosc,
            @Param("ilosc") int ilosc);


    @Query(value = "SELECT ilosc FROM product_entity " +
            "WHERE szerokosc = :szerokosc " +
            "and wysokosc = :wysokosc " +
            "and grubosc = :grubosc " +
            "and dlugosc = :dlugosc " +
            "and rodzaj_materialu = :rodzajMaterialu", nativeQuery = true)
    float productCheckout(@Param("rodzajMaterialu") String rodzajMaterialu,
                                        @Param("dlugosc") int dlugosc,
                                        @Param("szerokosc") int szerokosc,
                                        @Param("wysokosc") int wysokosc,
                                        @Param("grubosc") int grubosc);
}

// SELECT ilosc FROM product_entity WHERE szerokosc = 10 and wysokosc = 50 and and grubosc = 100 and dlugosc = 6000 and rodzaj_materialu = 'hardox';