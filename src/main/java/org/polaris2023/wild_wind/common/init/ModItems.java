package org.polaris2023.wild_wind.common.init;

import net.minecraft.core.component.DataComponents;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import org.polaris2023.annotation.language.I18n;
import org.polaris2023.wild_wind.common.item.BasicItem;
import org.polaris2023.wild_wind.common.item.BasicMobBucketItem;

import static org.polaris2023.wild_wind.common.init.ModInitializer.register;


public final class ModItems {
    @I18n(en_us = "Glow Powder", zh_cn = "萤光粉末", zh_tw = "螢光粉末")
    public static final DeferredItem<BasicItem> GLOW_POWDER = register("glow_powder", BasicItem::new);
    @I18n(en_us = "Living Tuber", zh_cn = "活根", zh_tw = "活根")
    public static final DeferredItem<BasicItem> LIVING_TUBER = register("living_tuber", BasicItem::stackTo16);
    @I18n(en_us = "Cooked Living Tuber", zh_cn = "熟活根", zh_tw = "熟活根")
    public static final DeferredItem<BasicItem> COOKED_LIVING_TUBER = register("cooked_living_tuber", BasicItem::stackTo16);

    @I18n(en_us = "Raw Trout", zh_cn = "生鳟鱼", zh_tw = "生鱒魚")
    public static final DeferredItem<BasicItem> RAW_TROUT = register("raw_trout", properties -> new BasicItem(properties,
            builder -> builder.nutrition(2).saturationModifier(0.2F)));
    @I18n(en_us = "Cooked Trout", zh_cn = "熟鳟鱼", zh_tw = "熟鱒魚")
    public static final DeferredItem<BasicItem> COOKED_TROUT = register("cooked_trout", properties -> new BasicItem(properties,
            builder -> builder.nutrition(6).saturationModifier(9.6F)));

    @I18n(en_us = "Wunderhorn", zh_cn = "魔号", zh_tw = "魔號")
    public static final DeferredItem<BasicItem> WUNDERHORN = register("wunderhorn", BasicItem::stackTo1);

    @I18n(en_us = "Apple Cake", zh_cn = "苹果派", zh_tw = "蘋果派")
    public static final DeferredItem<BasicItem> APPLE_CAKE = register("apple_cake", BasicItem::new);

    @I18n(en_us = "Berry Cake", zh_cn = "浆果派", zh_tw = "漿果派")
    public static final DeferredItem<BasicItem> BERRY_CAKE = register("berry_cake", BasicItem::new);

    @I18n(en_us = "Candy", zh_cn = "糖果", zh_tw = "糖果")
    public static final DeferredItem<BasicItem> CANDY = register("candy", BasicItem::stackTo16);

    @I18n(en_us = "Cheese", zh_tw = "乳酪", zh_cn = "奶酪")
    public static final DeferredItem<BasicItem> CHEESE = register("cheese", BasicItem::stackTo16);

    @I18n(en_us = "Cheese Pumpkin soup", zh_cn = "奶酪南瓜汤", zh_tw = "乳酪南瓜湯")
    public static final DeferredItem<BasicItem> CHEESE_PUMPKIN_SOUP = register("cheese_pumpkin_soup", BasicItem::stackTo1);

    @I18n(en_us = "Baked Beetroot", zh_cn = "烤甜菜根", zh_tw = "烤甜菜根")
    public static final DeferredItem<BasicItem> COOKED_BEETROOT = register("baked_beetroot", BasicItem::new);

    @I18n(en_us = "Baked carrot", zh_tw = "烤胡蘿蔔", zh_cn = "烤胡萝卜")
    public static final DeferredItem<BasicItem> COOKED_CARROT = register("baked_carrot", BasicItem::new);

    @I18n(en_us = "Cooked Egg", zh_cn = "煎蛋", zh_tw = "煎蛋")
    public static final DeferredItem<BasicItem> COOKED_EGG = register("cooked_egg", BasicItem::new);

    @I18n(en_us = "dough", zh_cn = "面团", zh_tw = "麵團")
    public static final DeferredItem<BasicItem> DOUGH = register("dough", BasicItem::new);

    @I18n(en_us = "Fish Chowder", zh_cn = "海鲜杂烩", zh_tw = "海鮮雜燴")
    public static final DeferredItem<BasicItem> FISH_CHOWDER = register("fish_chowder", BasicItem::stackTo1);

    @I18n(en_us = "Trout Bucket", zh_cn = "鳟鱼桶", zh_tw = "鱒魚桶")
    public static final DeferredItem<BasicMobBucketItem> TROUT_BUCKET = register("trout_bucket", () -> new BasicMobBucketItem(ModEntities.TROUT.get(), Fluids.WATER, SoundEvents.BUCKET_EMPTY_FISH, new Item.Properties().stacksTo(1).component(DataComponents.BUCKET_ENTITY_DATA, CustomData.EMPTY)));

    @I18n(en_us = "Russian Soup", zh_cn = "罗宋汤", zh_tw = "羅宋湯")
    public static final DeferredItem<BasicItem> RUSSIAN_SOUP = register("russian_soup", BasicItem::stackTo1);

    @I18n(en_us = "Pumpkin Slice", zh_cn = "南瓜片", zh_tw = "南瓜片")
    public static final DeferredItem<BasicItem> PUMPKIN_SLICE = register("pumpkin_slice", BasicItem::new);

    @I18n(en_us = "Vegetable Soup", zh_cn = "蔬菜浓汤", zh_tw = "蔬菜濃湯")
    public static final DeferredItem<BasicItem> VEGETABLE_SOUP = register("vegetable_soup", BasicItem::stackTo1);
    @I18n(en_us = "Flour", zh_cn = "面粉", zh_tw = "麵粉")
    public static final DeferredItem<BasicItem> FLOUR = register("flour", BasicItem::new);

    @I18n(en_us = "Spider Egg", zh_cn = "蜘蛛卵", zh_tw = "蜘蛛卵")
    public static final DeferredItem<BasicItem> SPIDER_EGG = register("spider_egg", BasicItem::stackTo1);

    @I18n(en_us = "Spider Mucosa", zh_cn = "蛛丝壁膜", zh_tw = "蛛絲壁膜")
    public static final DeferredItem<BasicItem> SPIDER_MUCOSA = register("spider_mucosa", BasicItem::new);

    @I18n(en_us = "Nether Mushroom Stew", zh_cn = "下界蘑菇煲", zh_tw = "下界蘑菇煲")
    public static final DeferredItem<BasicItem> NETHER_MUSHROOM_STEW = register("nether_mushroom_stew", BasicItem::stackTo1);

    @I18n(en_us = "Baked Mushroom", zh_cn = "烤蘑菇", zh_tw = "烤蘑菇")
    public static final DeferredItem<BasicItem> BAKED_MUSHROOM = register("baked_mushroom", BasicItem::new);

    @I18n(en_us = "Baked Seeds", zh_cn = "烤种子", zh_tw = "烤種子")
    public static final DeferredItem<BasicItem> BAKED_SEEDS = register("baked_seeds", BasicItem::new);

    @I18n(en_us = "Baked Berries", zh_cn = "烤浆果", zh_tw = "烤漿果")
    public static final DeferredItem<BasicItem> BAKED_BERRIES = register("baked_berries", BasicItem::new);

    @I18n(en_us = "Firefly Spawn Egg", zh_cn = "萤火虫刷怪蛋", zh_tw = "螢火蟲生怪蛋")
    public static final DeferredItem<DeferredSpawnEggItem> FIREFLY_SPAWN_EGG =
            register("firefly_spawn_egg",
                    ModEntities.FIREFLY,
                    0x0A233F,
                            0xAA8F74);


    @I18n(en_us = "Glare Spawn Egg", zh_cn = "怒目怪刷怪蛋", zh_tw = "怒目靈生怪蛋")
    public static final DeferredItem<DeferredSpawnEggItem> GLARE_SPAWN_EGG =
            register("glare_spawn_egg",
                    ModEntities.GLARE,
                    0x49601B,
                    0x10160A);
    @I18n(en_us = "Glare Spawn Egg", zh_cn = "鳟鱼刷怪蛋", zh_tw = "鱒魚生怪蛋")
    public static final DeferredItem<DeferredSpawnEggItem> TROUT_SPAWN_EGG =
            register("trout_spawn_egg",
                    ModEntities.TROUT,
                    0x8290a5,
                    0x6b9f93
                    );


}
