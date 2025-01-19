package net.nuclearteam.createnuclear.content.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.nuclearteam.createnuclear.CNEffects;
import net.nuclearteam.createnuclear.CNTags;
import net.nuclearteam.createnuclear.content.equipment.armor.AntiRadiationArmorItem;

public class RadiationEffect extends MobEffect {
    public RadiationEffect() {
        super(MobEffectCategory.HARMFUL, 15453236);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }

    @Override
    public void applyEffectTick(LivingEntity livingEntity, int amplifier) {
        //Si le joueur porte l'armure anti_radiation_suit alors il ne prend pas de dégâts
        livingEntity.getArmorSlots().forEach(e -> {
            //à rajouter quand l'armure sera faite
            if (livingEntity.hasEffect( CNEffects.RADIATION.get()) && AntiRadiationArmorItem.Armor.isArmored2(e)) {
                livingEntity.hurt(livingEntity.damageSources().magic(), 0.0F);
            }
            if (livingEntity.getType().is(CNTags.CNEntityTags.IRRADIATED_IMMUNE.tag)) {
                livingEntity.removeEffect(this);
            }
            else {
                livingEntity.hurt(livingEntity.damageSources().magic(), 1 << amplifier);
            }
        });
    }
}
