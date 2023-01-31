package monprojet.dao;

import java.util.List;

import monprojet.dto.PopulationPays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import monprojet.entity.City;
import monprojet.entity.Country;

// This will be AUTO IMPLEMENTED by Spring 

public interface CountryRepository extends JpaRepository<Country, Integer> {

    /**
     * Calculer la pop totale d'un pays
     * @param countryID clef du pays
     * @return pop total par pays (somme des pop des villes de ce pays)
     */

    @Query(" SELECT SUM(c.population) " +
            " FROM City c "
            + " WHERE c.country.id = :countryID")
    public int populationparID(Integer countryID);

    @Query(value = "SELECT coun.name as nomPays, SUM(c.population) as popPays "
            + "FROM City c "
            + "JOIN Country coun ON coun.id = c.country_id "
            + "GROUP BY country_id" ,
            nativeQuery = true)
    public List<PopulationPays> listePopulationPays();

}
