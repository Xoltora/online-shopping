package uz.pdp.g42accessoryserver.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.g42accessoryserver.entity.enums.DiscountType;
import uz.pdp.g42accessoryserver.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Discount extends AbsEntity {
    @ManyToOne
    private Trade trade;

    @Enumerated(EnumType.STRING)
    private DiscountType discountType;

    private double amount;
}
