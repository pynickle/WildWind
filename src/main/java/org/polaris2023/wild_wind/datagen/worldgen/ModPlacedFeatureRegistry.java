package org.polaris2023.wild_wind.datagen.worldgen;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import org.polaris2023.wild_wind.util.Helpers;

import java.util.List;

public class ModPlacedFeatureRegistry {
	public static final ResourceKey<PlacedFeature> BRITTLE_ICE = create("brittle_ice");
	public static final ResourceKey<PlacedFeature> DISK_BRITTLE_ICE = create("disk_brittle_ice");

	//Ore
	public static final ResourceKey<PlacedFeature> ORE_SALT = create("ore_salt");
	public static final ResourceKey<PlacedFeature> ORE_SALT_BURIED = create("ore_salt_buried");

	//Quicksand
	public static final ResourceKey<PlacedFeature> QUICKSAND = create("quicksand");
	public static final ResourceKey<PlacedFeature> RED_QUICKSAND = create("red_quicksand");

	public static void bootstrap(BootstrapContext<PlacedFeature> context) {
		HolderGetter<ConfiguredFeature<?, ?>> configuredFeaturesLookup = context.lookup(Registries.CONFIGURED_FEATURE);
		PlacementUtils.register(
				context, BRITTLE_ICE, configuredFeaturesLookup.getOrThrow(ModConfiguredFeatureRegistry.BRITTLE_ICE),
				orePlacement(CountPlacement.of(2), HeightRangePlacement.uniform(VerticalAnchor.absolute(60), VerticalAnchor.absolute(67)))
		);
		PlacementUtils.register(
				context, DISK_BRITTLE_ICE, configuredFeaturesLookup.getOrThrow(ModConfiguredFeatureRegistry.DISK_BRITTLE_ICE),
				orePlacement(CountPlacement.of(6), HeightmapPlacement.onHeightmap(Heightmap.Types.OCEAN_FLOOR_WG))
		);
		PlacementUtils.register(
				context, ORE_SALT, configuredFeaturesLookup.getOrThrow(ModConfiguredFeatureRegistry.ORE_SALT),
				orePlacement(CountPlacement.of(2), HeightRangePlacement.triangle(VerticalAnchor.absolute(-32), VerticalAnchor.absolute(32)))
		);
		PlacementUtils.register(
				context, ORE_SALT_BURIED, configuredFeaturesLookup.getOrThrow(ModConfiguredFeatureRegistry.ORE_SALT_BURIED),
				orePlacement(CountPlacement.of(2), HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(64)))
		);
		PlacementUtils.register(
				context, QUICKSAND, configuredFeaturesLookup.getOrThrow(ModConfiguredFeatureRegistry.QUICKSAND),
				RarityFilter.onAverageOnceEvery(40),
				InSquarePlacement.spread(),
				PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
				BiomeFilter.biome()
		);
		PlacementUtils.register(
				context, RED_QUICKSAND, configuredFeaturesLookup.getOrThrow(ModConfiguredFeatureRegistry.RED_QUICKSAND),
				RarityFilter.onAverageOnceEvery(40),
				InSquarePlacement.spread(),
				PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
				BiomeFilter.biome()
		);
	}

	private static List<PlacementModifier> orePlacement(PlacementModifier countPlacement, PlacementModifier heightRange) {
		return List.of(countPlacement, InSquarePlacement.spread(), heightRange, BiomeFilter.biome());
	}

	private static ResourceKey<PlacedFeature> create(String name) {
		return ResourceKey.create(Registries.PLACED_FEATURE, Helpers.location(name));
	}
}
