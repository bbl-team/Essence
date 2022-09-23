package com.benbenlaw.essence.events.loot;

import com.benbenlaw.essence.config.ConfigFile;
import com.google.gson.JsonObject;
import com.mojang.serialization.Codec;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import java.util.List;

public class SpawnerAdditionModifier {/* extends LootModifier {

    1
    private final Item addition;

    protected SpawnerAdditionModifier(LootItemCondition[] conditionsIn, Item addition) {
        super(conditionsIn);
        this.addition = addition;
    }

    @Nonnull
    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        // generatedLoot is the loot that would be dropped, if we wouldn't add or replace
        // anything!
        if(context.getRandom().nextDouble() > ConfigFile.spawnerShardChance.get()) {
            generatedLoot.add(new ItemStack(addition, ConfigFile.spawnerShardAmount.get()));
        }
        return generatedLoot;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return null;
    }

    public static class Serializer extends GlobalLootModifierProvider<SpawnerAdditionModifier> {

        public Serializer(DataGenerator gen, String modid) {
            super(gen, modid);
        }

        @Override
        public SpawnerAdditionModifier read(ResourceLocation location, JsonObject object, LootItemCondition[] conditionsIn) {
            Item addition = ForgeRegistries.ITEMS.getValue(
                    new ResourceLocation(GsonHelper.getAsString(object, "addition")));
            return new SpawnerAdditionModifier(conditionsIn, addition);
        }

        @Override
        public JsonObject write(SpawnerAdditionModifier instance) {
            JsonObject json = makeConditions(instance.conditions);
            json.addProperty("addition", ForgeRegistries.ITEMS.getKey(instance.addition).toString());
            return new JsonObject();
        }

        private JsonObject makeConditions(LootItemCondition[] conditions) {
        }

        @Override
        protected void start() {

        }
    }
    */
}
