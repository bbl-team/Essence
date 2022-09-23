package com.benbenlaw.essence.damage;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;

public class ModDamageSources {

    public static final DamageSource SHOCKED = new DamageSource("shocked").bypassArmor().bypassMagic();

}