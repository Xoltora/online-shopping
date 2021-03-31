package uz.pdp.g42accessoryserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.g42accessoryserver.entity.Shop;
import uz.pdp.g42accessoryserver.entity.Warehouse;

public interface WarehouseRepository extends JpaRepository<Warehouse    ,Integer> {
}