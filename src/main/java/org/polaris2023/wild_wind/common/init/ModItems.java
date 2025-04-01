package org.polaris2023.wild_wind.common.init;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.entity.BannerPatternLayers;
import net.neoforged.neoforge.registries.DeferredItem;
import org.polaris2023.annotation.language.I18n;
import org.polaris2023.annotation.modelgen.item.BasicBlockLocatedItem;
import org.polaris2023.annotation.modelgen.item.BasicItem;
import org.polaris2023.annotation.modelgen.item.ParentItem;
import org.polaris2023.wild_wind.common.block.item.PresentBlockItem;
import org.polaris2023.wild_wind.common.block.item.TrappedPresentBlockItem;
import org.polaris2023.wild_wind.common.item.*;
import org.polaris2023.wild_wind.common.item.food.CheeseItem;
import org.polaris2023.wild_wind.common.item.food.NetherMushroomStewItem;
import org.polaris2023.wild_wind.common.item.modified.ModBannerItem;

import static org.polaris2023.wild_wind.util.interfaces.registry.ItemRegistry.*;

public class ModItems {
    @BasicItem
    @I18n(en_us = "Living Tuber", zh_cn = "活根", zh_tw = "活根")
    public static final DeferredItem<LivingTuberItem> LIVING_TUBER =
            register("living_tuber", properties -> new LivingTuberItem(properties
                    .stacksTo(16)
                    .component(ModComponents.VEGETABLE_VALUE, 1F)
                    .component(ModComponents.MEAT_VALUE, 1F)
                    .component(ModComponents.MONSTER_VALUE, 1F)
                    .food(ModFoods.LIVING_TUBER.get())));

    @I18n(en_us = "Magic Wand Tool", zh_cn = "魔棒工具", zh_tw = "魔棒工具")
    @ParentItem(parent = "minecraft:item/stick")
    public static final DeferredItem<MagicWandToolItem> MAGIC_WAND_TOOL_ITEM =
            register("magic_wand_tool", p -> new MagicWandToolItem(p.stacksTo(1)));

    @BasicItem
    @I18n(en_us = "Glow Powder", zh_cn = "萤光粉末", zh_tw = "螢光粉末")
    public static final DeferredItem<GlowPowderItem> GLOW_POWDER =
            register("glow_powder", p -> new GlowPowderItem(p.stacksTo(64)));

    @BasicItem
    @I18n(en_us = "Ash Dust", zh_cn = "灰烬粉末", zh_tw = "灰烬粉末")
    public static final DeferredItem<AshDustItem> ASH_DUST =
            register("ash_dust", p -> new AshDustItem(p.stacksTo(64)));

    @BasicItem(used = false)// don't run datagen by this
    @I18n(en_us = "Magic Flute", zh_cn = "魔笛", zh_tw = "魔笛")
    public static final DeferredItem<MagicFluteItem> MAGIC_FLUTE =
            register("magic_flute", MagicFluteItem::stackTo1);

    @BasicItem
    @I18n(en_us = "Cheese", zh_cn = "奶酪", zh_tw = "起司")
    public static final DeferredItem<CheeseItem> CHEESE =
            register("cheese", p -> new CheeseItem(p.stacksTo(16).food(ModFoods.CHEESE.get())));
    @BasicItem
    @I18n(en_us = "Russian Soup", zh_cn = "罗宋汤", zh_tw = "羅宋湯")
    public static final DeferredItem<Item> RUSSIAN_SOUP =
            simpleItem("russian_soup", p -> p.stacksTo(1));
    @BasicItem
    @I18n(en_us = "Vegetable Soup", zh_cn = "蔬菜浓汤", zh_tw = "蔬菜濃湯")
    public static final DeferredItem<Item> VEGETABLE_SOUP =
            simpleItem("vegetable_soup", p -> p.stacksTo(1));

    @BasicItem
    @I18n(en_us = "Nether Mushroom Stew", zh_cn = "下界蘑菇煲", zh_tw = "下界蘑菇煲")
    public static final DeferredItem<NetherMushroomStewItem> NETHER_MUSHROOM_STEW =
            register("nether_mushroom_stew", properties -> 
                    new NetherMushroomStewItem(properties.stacksTo(1), ModFoods.NETHER_MUSHROOM_STEW));
    @BasicItem
    public static final DeferredItem<BlockItem> GLOW_MUCUS_ITEM = register("glow_mucus", ModBlocks.GLOW_MUCUS);

    public static final DeferredItem<BlockItem> FIREFLY_JAR_ITEM = register("firefly_jar", ModBlocks.FIREFLY_JAR);

    @BasicItem
    public static final DeferredItem<BlockItem> GLAREFLOWER_ITEM = register("glareflower", ModBlocks.GLAREFLOWER);

    @BasicItem
    public static final DeferredItem<BlockItem> GLAREFLOWER_SEEDS_ITEM = register("glareflower_seeds", ModBlocks.GLAREFLOWER_SEEDS, ModFoods.GLAREFLOWER_SEEDS);

    public static final DeferredItem<BlockItem> COBWEB_COVER_ITEM = register("cobweb_cover", ModBlocks.COBWEB_COVER);

    @BasicItem
    public static final DeferredItem<BlockItem> REEDS_ITEM = register("reeds", ModBlocks.REEDS);

    @BasicItem
    public static final DeferredItem<BlockItem> CATTAILS_ITEM = register("cattails", ModBlocks.CATTAILS);

    public static final DeferredItem<PresentBlockItem> PRESENT_ITEM = register("present", p -> new PresentBlockItem(ModBlocks.PRESENT.get(), p));

    public static final DeferredItem<TrappedPresentBlockItem> TRAPPED_PRESENT_ITEM = register("trapped_present", p -> new TrappedPresentBlockItem(ModBlocks.TRAPPED_PRESENT.get(), p));

    public static final DeferredItem<BlockItem> BED_ITEM = register("bed", ModBlocks.BED);

    public static final DeferredItem<ModBannerItem> BANNER_ITEM = register("banner", p -> new ModBannerItem(ModBlocks.BANNER.get(), ModBlocks.WALL_BANNER.get(), p.stacksTo(16).component(DataComponents.BANNER_PATTERNS, BannerPatternLayers.EMPTY)));

    public static final DeferredItem<BlockItem> SILT_ITEM = register("silt", ModBlocks.SILT);

    @BasicBlockLocatedItem
    public static final DeferredItem<BlockItem> TINY_CACTUS_ITEM = register("tiny_cactus", ModBlocks.TINY_CACTUS);

    public static final DeferredItem<BlockItem> QUICKSAND_ITEM = register("quicksand", ModBlocks.QUICKSAND);

    public static final DeferredItem<BlockItem> RED_QUICKSAND_ITEM = register("red_quicksand", ModBlocks.RED_QUICKSAND);

    public static final DeferredItem<BlockItem> COOKING_POT_ITEM = register("cooking_pot", ModBlocks.COOKING_POT);

    public static final DeferredItem<BlockItem> SCULK_JAW_ITEM = register("sculk_jaw", ModBlocks.SCULK_JAW);
    @BasicItem
    public static final DeferredItem<BlockItem> DUCKWEED_ITEM = register("duckweed", ModBlocks.DUCKWEED);

    public static final DeferredItem<BlockItem> BRITTLE_ICE_ITEM = register("brittle_ice", ModBlocks.BRITTLE_ICE);
    public static final DeferredItem<BlockItem> ASH_BLOCK_ITEM = register("ash_block", ModBlocks.ASH_BLOCK);
    @ParentItem(parent = "wild_wind:block/ash_1")
    public static final DeferredItem<BlockItem> ASH_ITEM = register("ash", ModBlocks.ASH);
    public static final DeferredItem<BlockItem> WOOL_ITEM = register("wool", ModBlocks.WOOL);
    public static final DeferredItem<BlockItem> CARPET_ITEM = register("carpet", ModBlocks.CARPET);
    public static final DeferredItem<BlockItem> CONCRETE_ITEM = register("concrete", ModBlocks.CONCRETE);

}
