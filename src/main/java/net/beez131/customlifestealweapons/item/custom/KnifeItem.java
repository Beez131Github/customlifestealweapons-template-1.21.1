//package net.beez131.customlifestealweapons.item.custom;
//
//import net.minecraft.entity.LivingEntity;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.item.AxeItem;
//import net.minecraft.item.ItemStack;
//import net.minecraft.item.ToolMaterial;
//import net.minecraft.entity.EquipmentSlot;
//
//public class KnifeItem extends AxeItem {
//    public KnifeItem(ToolMaterial material, Settings settings) {
//        super(material, settings);
//    }
//
//    @Override
//    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
//        // Check if the item's durability is at 1 before damaging
//        if (stack.getDamage() >= stack.getMaxDamage() - 1) {
//            // Prevent breaking by not reducing durability past 1
//            stack.setDamage(stack.getMaxDamage() - 1);
//        } else {
//            // Apply normal damage reduction
//            stack.damage(1, attacker, e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
//        }
//        return true; // Indicate that the attack was successful
//    }
//}
