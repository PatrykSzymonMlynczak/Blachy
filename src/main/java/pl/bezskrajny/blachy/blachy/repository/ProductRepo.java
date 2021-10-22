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
            "WHERE id = :id", nativeQuery = true)
    void updateProduct(@Param("id") Long id, @Param("ilosc") Long ilosc);

    @Transactional
    @Modifying


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


    @Query(value = "UPDATE product_entity SET ilosc = :ilosc " +
            "WHERE szerokosc = :szerokosc " +
            "and wysokosc = :wysokosc " +
            "and grubosc = :grubosc " +
            "and dlugosc = :dlugosc " +
            "and rodzaj_materialu = :rodzajMaterialu", nativeQuery = true)
    void updateProductByValues(
            @Param("rodzajMaterialu") String rodzajMaterialu,
            @Param("dlugosc") int dlugosc,
            @Param("szerokosc") int szerokosc,
            @Param("wysokosc") int wysokosc,
            @Param("grubosc") int grubosc,
            @Param("ilosc") int ilosc);


}