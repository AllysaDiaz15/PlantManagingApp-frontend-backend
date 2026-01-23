    package com.example.plant_app.repository;

    import com.example.plant_app.Entity.Plant;
    import org.springframework.data.repository.CrudRepository;
    import java.util.List;

    public interface PlantRepository extends CrudRepository <Plant, Long>{
        List <Plant> findByUserUserId(Long userId);
        // Whats happening here is that this is public and everyone can see this, interface meaning its a template (no user input yet) <plant> is the plant entity and List
        // with <plant> is calling all the list of plant
    }
