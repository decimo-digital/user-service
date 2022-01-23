package it.decimo.user_service.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import it.decimo.user_service.dto.Location;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.geo.Point;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "merchant")
public class Merchant {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "location")
    @JsonAlias(value = "location")
    private Point storeLocation;

    @Column(name = "enabled")
    private boolean isEnabled;
    @Column(name = "store_name")
    @JsonAlias(value = "store_name")
    private String storeName;
    @JsonAlias(value = "owner")
    @Column(name = "owner")
    private Integer owner;
    @Column(name = "free_seats")
    @JsonAlias(value = "free_seats")
    private Integer freeSeats;
    @Column(name = "description")
    private String description;
    @Transient
    @JsonAlias(value = "occupancy_rate")
    private float occupancyRate;
    @Column(name = "total_seats")
    @JsonAlias(value = "total_seats")
    private Integer totalSeats;

    @Column(name = "cuisine_type")
    private String cuisineType;


    @JsonIgnore
    public Point getPoint() {
        return storeLocation;
    }

    @JsonAnyGetter
    public Map<String, Double> getStoreLocation() {
        if (storeLocation == null) {
            return null;
        }
        return new HashMap<String, Double>() {
            {
                put("lat", storeLocation.getX());
                put("lng", storeLocation.getY());
            }
        };
    }

    @JsonAnySetter
    public void setStoreLocation(Location location) {
        if (location != null) {
            this.storeLocation = new Point(location.getX(), location.getY());
        }
    }

}
