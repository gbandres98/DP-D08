
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.GPSCoordinates;

@Repository
public interface GPSCoordinatesRepository extends JpaRepository<GPSCoordinates, Integer> {

}
