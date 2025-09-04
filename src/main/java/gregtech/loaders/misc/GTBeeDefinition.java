package gregtech.loaders.misc;

import static forestry.api.apiculture.EnumBeeChromosome.CAVE_DWELLING;
import static forestry.api.apiculture.EnumBeeChromosome.EFFECT;
import static forestry.api.apiculture.EnumBeeChromosome.FERTILITY;
import static forestry.api.apiculture.EnumBeeChromosome.FLOWERING;
import static forestry.api.apiculture.EnumBeeChromosome.FLOWER_PROVIDER;
import static forestry.api.apiculture.EnumBeeChromosome.HUMIDITY_TOLERANCE;
import static forestry.api.apiculture.EnumBeeChromosome.LIFESPAN;
import static forestry.api.apiculture.EnumBeeChromosome.NOCTURNAL;
import static forestry.api.apiculture.EnumBeeChromosome.SPECIES;
import static forestry.api.apiculture.EnumBeeChromosome.SPEED;
import static forestry.api.apiculture.EnumBeeChromosome.TEMPERATURE_TOLERANCE;
import static forestry.api.apiculture.EnumBeeChromosome.TERRITORY;
import static forestry.api.apiculture.EnumBeeChromosome.TOLERANT_FLYER;
import static forestry.api.core.EnumHumidity.ARID;
import static forestry.api.core.EnumHumidity.DAMP;
import static forestry.api.core.EnumTemperature.COLD;
import static forestry.api.core.EnumTemperature.HELLISH;
import static forestry.api.core.EnumTemperature.HOT;
import static forestry.api.core.EnumTemperature.ICY;
import static forestry.api.core.EnumTemperature.NORMAL;
import static forestry.api.core.EnumTemperature.WARM;
import static forestry.core.genetics.alleles.EnumAllele.Fertility;
import static forestry.core.genetics.alleles.EnumAllele.Flowering;
import static forestry.core.genetics.alleles.EnumAllele.Flowers;
import static forestry.core.genetics.alleles.EnumAllele.Lifespan;
import static forestry.core.genetics.alleles.EnumAllele.Speed;
import static forestry.core.genetics.alleles.EnumAllele.Territory;
import static forestry.core.genetics.alleles.EnumAllele.Tolerance;
import static gregtech.api.enums.Mods.AdvancedSolarPanel;
import static gregtech.api.enums.Mods.AppliedEnergistics2;
import static gregtech.api.enums.Mods.Avaritia;
import static gregtech.api.enums.Mods.AvaritiaAddons;
import static gregtech.api.enums.Mods.BiomesOPlenty;
import static gregtech.api.enums.Mods.Botania;
import static gregtech.api.enums.Mods.CropsPlusPlus;
import static gregtech.api.enums.Mods.EnderStorage;
import static gregtech.api.enums.Mods.ExtraBees;
import static gregtech.api.enums.Mods.ExtraCells2;
import static gregtech.api.enums.Mods.ExtraUtilities;
import static gregtech.api.enums.Mods.Forestry;
import static gregtech.api.enums.Mods.GalacticraftCore;
import static gregtech.api.enums.Mods.GalacticraftMars;
import static gregtech.api.enums.Mods.GalaxySpace;
import static gregtech.api.enums.Mods.HardcoreEnderExpansion;
import static gregtech.api.enums.Mods.IndustrialCraft2;
import static gregtech.api.enums.Mods.MagicBees;
import static gregtech.api.enums.Mods.NewHorizonsCoreMod;
import static gregtech.api.enums.Mods.PamsHarvestCraft;
import static gregtech.api.enums.Mods.TaintedMagic;
import static gregtech.api.enums.Mods.Thaumcraft;
import static gregtech.api.enums.Mods.ThaumicBases;
import static gregtech.api.enums.Mods.TinkerConstruct;
import static gregtech.loaders.misc.GTBeeDefinitionReference.EXTRABEES;
import static gregtech.loaders.misc.GTBeeDefinitionReference.FORESTRY;
import static gregtech.loaders.misc.GTBeeDefinitionReference.GENDUSTRY;
import static gregtech.loaders.misc.GTBeeDefinitionReference.GREGTECH;
import static gregtech.loaders.misc.GTBeeDefinitionReference.MAGICBEES;

import java.awt.Color;
import java.util.Arrays;
import java.util.Locale;
import java.util.function.Consumer;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import org.apache.commons.lang3.text.WordUtils;

import cpw.mods.fml.common.registry.GameRegistry;
import forestry.api.apiculture.BeeManager;
import forestry.api.apiculture.EnumBeeType;
import forestry.api.apiculture.IAlleleBeeEffect;
import forestry.api.apiculture.IAlleleBeeSpecies;
import forestry.api.apiculture.IBee;
import forestry.api.apiculture.IBeeGenome;
import forestry.api.apiculture.IBeeMutationCustom;
import forestry.api.core.EnumHumidity;
import forestry.api.core.EnumTemperature;
import forestry.api.genetics.AlleleManager;
import forestry.api.genetics.IAllele;
import forestry.api.genetics.IAlleleFlowers;
import forestry.api.genetics.IMutationCustom;
import forestry.apiculture.genetics.Bee;
import forestry.apiculture.genetics.IBeeDefinition;
import forestry.apiculture.genetics.alleles.AlleleEffect;
import forestry.core.genetics.alleles.AlleleHelper;
import gregtech.GTMod;
import gregtech.api.GregTechAPI;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.MaterialsKevlar;
import gregtech.api.enums.MetaTileEntityIDs;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GTLanguageManager;
import gregtech.api.util.GTModHandler;
import gregtech.api.util.GTOreDictUnificator;
import gregtech.api.util.JubilanceMegaApiary;
import gregtech.common.bees.GTAlleleBeeSpecies;
import gregtech.common.bees.GTBeeMutation;
import gregtech.common.items.CombType;
import gregtech.common.items.DropType;
import gregtech.common.items.PropolisType;
import gregtech.loaders.misc.bees.GTAlleleEffect;
import gregtech.loaders.misc.bees.GTFlowers;
import gtnhlanth.common.register.WerkstoffMaterialPool;

/**
 * Bride Class for Lambdas
 */
class GTBeeDefinitionReference {

    protected static final byte FORESTRY = 0;
    protected static final byte EXTRABEES = 1;
    protected static final byte GENDUSTRY = 2;
    protected static final byte MAGICBEES = 3;
    protected static final byte GREGTECH = 4;

    private GTBeeDefinitionReference() {}
}

public enum GTBeeDefinition implements IBeeDefinition {

    // organic
    CLAY(GTBranchDefinition.ORGANIC, "Clay", true, new Color(0xC8C8DA), new Color(0x0000FF), beeSpecies -> {
        beeSpecies.addProduct(GTModHandler.getModItem(Forestry.ID, "beeCombs", 1, 0), 0.30f);
        beeSpecies.addProduct(new ItemStack(Items.clay_ball, 1), 0.15f);
        beeSpecies.addSpecialty(GTModHandler.getModItem(BiomesOPlenty.ID, "mudball", 1, 0), 0.05f);
        beeSpecies.setHumidity(DAMP);
        beeSpecies.setTemperature(EnumTemperature.NORMAL);
    }, template -> {
        AlleleHelper.instance.set(template, FLOWERING, Flowering.SLOWER);
        AlleleHelper.instance.set(template, HUMIDITY_TOLERANCE, Tolerance.NONE);
        AlleleHelper.instance.set(template, FLOWER_PROVIDER, Flowers.VANILLA);
    }, dis -> {
        IBeeMutationCustom tMutation = dis
            .registerMutation(getSpecies(FORESTRY, "Industrious"), getSpecies(FORESTRY, "Diligent"), 10);
        tMutation.requireResource(Blocks.clay, 0); // blockStainedHardenedClay
    }),
    SLIMEBALL(GTBranchDefinition.ORGANIC, "SlimeBall", true, new Color(0x4E9E55), new Color(0x00FF15), beeSpecies -> {
        beeSpecies.addProduct(GTModHandler.getModItem(Forestry.ID, "beeCombs", 1, 15), 0.30f);
        beeSpecies.addProduct(new ItemStack(Items.slime_ball, 1), 0.15f);
        beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.STICKY), 0.05f);
        beeSpecies.setHumidity(DAMP);
        beeSpecies.setTemperature(EnumTemperature.NORMAL);
        if (TinkerConstruct.isModLoaded()) {
            beeSpecies.addProduct(GTModHandler.getModItem(TinkerConstruct.ID, "strangeFood", 1, 0), 0.10f);
            beeSpecies.addSpecialty(GTModHandler.getModItem(TinkerConstruct.ID, "slime.gel", 1, 2), 0.02f);
        }
    }, template -> {
        AlleleHelper.instance.set(template, FLOWER_PROVIDER, Flowers.MUSHROOMS);
        AlleleHelper.instance.set(template, FLOWERING, Flowering.SLOWER);
        AlleleHelper.instance.set(template, TEMPERATURE_TOLERANCE, Tolerance.BOTH_1);
        AlleleHelper.instance.set(template, HUMIDITY_TOLERANCE, Tolerance.BOTH_1);
        AlleleHelper.instance.set(template, FLOWER_PROVIDER, getFlowers(EXTRABEES, "water"));
    }, dis -> {
        IBeeMutationCustom tMutation = dis.registerMutation(getSpecies(FORESTRY, "Marshy"), CLAY, 7);
        if (TinkerConstruct.isModLoaded())
            tMutation.requireResource(GameRegistry.findBlock(TinkerConstruct.ID, "slime.gel"), 1);
    }),
    PEAT(GTBranchDefinition.ORGANIC, "Peat", true, new Color(0x906237), new Color(0x58300B), beeSpecies -> {
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.LIGNIE), 0.30f);
        beeSpecies.addProduct(GTModHandler.getModItem(Forestry.ID, "beeCombs", 1, 0), 0.15f);
        beeSpecies.addSpecialty(GTModHandler.getModItem(Forestry.ID, "peat", 1, 0), 0.15f);
        beeSpecies.addSpecialty(GTModHandler.getModItem(Forestry.ID, "mulch", 1, 0), 0.05f);
        beeSpecies.setHumidity(EnumHumidity.NORMAL);
        beeSpecies.setTemperature(EnumTemperature.NORMAL);
    }, template -> {
        AlleleHelper.instance.set(template, SPEED, Speed.SLOWER);
        AlleleHelper.instance.set(template, LIFESPAN, Lifespan.SHORTER);
        AlleleHelper.instance.set(template, FLOWER_PROVIDER, Flowers.WHEAT);
        AlleleHelper.instance.set(template, FLOWERING, Flowering.FASTER);
        AlleleHelper.instance.set(template, HUMIDITY_TOLERANCE, Tolerance.NONE);
    }, dis -> dis.registerMutation(getSpecies(FORESTRY, "Rural"), CLAY, 10)),
    STICKYRESIN(GTBranchDefinition.ORGANIC, "StickyResin", true, new Color(0x2E8F5B), new Color(0xDCC289),
        beeSpecies -> {
            beeSpecies.addProduct(GTModHandler.getModItem(Forestry.ID, "beeCombs", 1, 0), 0.30f);
            beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.STICKY), 0.15f);
            beeSpecies.addSpecialty(ItemList.IC2_Resin.get(1), 0.15f);
            beeSpecies.setHumidity(EnumHumidity.NORMAL);
            beeSpecies.setTemperature(EnumTemperature.NORMAL);
        }, template -> {
            AlleleHelper.instance.set(template, FLOWERING, Flowering.SLOWER);
            AlleleHelper.instance.set(template, HUMIDITY_TOLERANCE, Tolerance.NONE);
        }, dis -> {
            IBeeMutationCustom tMutation = dis.registerMutation(SLIMEBALL, PEAT, 15);
            tMutation.requireResource("logRubber");
        }),
    COAL(GTBranchDefinition.ORGANIC, "Coal", true, new Color(0x666666), new Color(0x525252), beeSpecies -> {
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.LIGNIE), 0.30f);
        beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.COAL), 0.15f);
        beeSpecies.setHumidity(EnumHumidity.NORMAL);
        beeSpecies.setTemperature(EnumTemperature.NORMAL);
    }, template -> {
        AlleleHelper.instance.set(template, FLOWER_PROVIDER, Flowers.CACTI);
        AlleleHelper.instance.set(template, SPEED, Speed.SLOWEST);
        AlleleHelper.instance.set(template, LIFESPAN, Lifespan.LONGER);
        AlleleHelper.instance.set(template, TEMPERATURE_TOLERANCE, Tolerance.DOWN_2);
        AlleleHelper.instance.set(template, HUMIDITY_TOLERANCE, Tolerance.DOWN_1);
        AlleleHelper.instance.set(template, NOCTURNAL, true);
        AlleleHelper.instance.set(template, EFFECT, AlleleEffect.effectCreeper);
    }, dis -> {
        IBeeMutationCustom tMutation = dis.registerMutation(getSpecies(FORESTRY, "Industrious"), PEAT, 9);
        tMutation.requireResource("blockCoal");
    }),
    OIL(GTBranchDefinition.ORGANIC, "Oil", true, new Color(0x4C4C4C), new Color(0x333333), beeSpecies -> {
        beeSpecies.addProduct(GTModHandler.getModItem(Forestry.ID, "beeCombs", 1, 0), 0.30f);
        beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.OIL), 0.75f);
        beeSpecies.setHumidity(DAMP);
        beeSpecies.setTemperature(EnumTemperature.NORMAL);
        beeSpecies.setHasEffect();
    }, template -> {
        AlleleHelper.instance.set(template, FLOWERING, Flowering.SLOWER);
        AlleleHelper.instance.set(template, NOCTURNAL, true);
        AlleleHelper.instance.set(template, LIFESPAN, Lifespan.NORMAL);
        AlleleHelper.instance.set(template, SPEED, Speed.SLOWER);
        AlleleHelper.instance.set(template, FLOWER_PROVIDER, getFlowers(EXTRABEES, "water"));
        AlleleHelper.instance.set(template, TEMPERATURE_TOLERANCE, Tolerance.NONE);
        AlleleHelper.instance.set(template, HUMIDITY_TOLERANCE, Tolerance.NONE);
    }, dis -> dis.registerMutation(COAL, STICKYRESIN, 4)),
    SANDWICH(GTBranchDefinition.ORGANIC, "Sandwich", true, new Color(0x32CD32), new Color(0xDAA520), beeSpecies -> {
        beeSpecies.addProduct(GTModHandler.getModItem(ExtraBees.ID, "honeyComb", 1, 9), 0.15f);
        beeSpecies.addSpecialty(ItemList.Food_Sliced_Cucumber.get(1), 0.05f);
        beeSpecies.addSpecialty(ItemList.Food_Sliced_Onion.get(1), 0.05f);
        beeSpecies.addSpecialty(ItemList.Food_Sliced_Tomato.get(1), 0.05f);
        beeSpecies.addSpecialty(ItemList.Food_Sliced_Cheese.get(1), 0.05f);
        beeSpecies.addSpecialty(new ItemStack(Items.cooked_porkchop, 1, 0), 0.05f);
        beeSpecies.addSpecialty(new ItemStack(Items.cooked_beef, 1, 0), 0.05f);
        beeSpecies.setHumidity(EnumHumidity.NORMAL);
        beeSpecies.setTemperature(EnumTemperature.NORMAL);
    }, template -> {
        AlleleHelper.instance.set(template, SPEED, Speed.SLOW);
        AlleleHelper.instance.set(template, HUMIDITY_TOLERANCE, Tolerance.BOTH_2);
        AlleleHelper.instance.set(template, EFFECT, AlleleEffect.effectFertile);
        AlleleHelper.instance.set(template, TERRITORY, Territory.LARGE);
        AlleleHelper.instance.set(template, LIFESPAN, Lifespan.SHORTER);
        AlleleHelper.instance.set(template, FLOWER_PROVIDER, Flowers.WHEAT);
        AlleleHelper.instance.set(template, FLOWERING, Flowering.FASTER);
    }, dis -> dis.registerMutation(getSpecies(FORESTRY, "Agrarian"), getSpecies(MAGICBEES, "TCBatty"), 10)),
    ASH(GTBranchDefinition.ORGANIC, "Ash", true, new Color(0x1e1a18), new Color(0xc6c6c6), beeSpecies -> {
        beeSpecies.addProduct(GTModHandler.getModItem(ExtraBees.ID, "honeyComb", 1, 9), 0.15f);
        beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.ASH), 0.15f);
        beeSpecies.setHumidity(ARID);
        beeSpecies.setTemperature(HOT);
    }, template -> {
        AlleleHelper.instance.set(template, SPEED, Speed.NORMAL);
        AlleleHelper.instance.set(template, TERRITORY, Territory.LARGE);
        AlleleHelper.instance.set(template, LIFESPAN, Lifespan.SHORTER);
        AlleleHelper.instance.set(template, FLOWER_PROVIDER, Flowers.WHEAT);
        AlleleHelper.instance.set(template, FLOWERING, Flowering.FASTER);
    }, dis -> {
        IBeeMutationCustom tMutation = dis.registerMutation(COAL, CLAY, 10);
        tMutation.restrictTemperature(HELLISH);
    }),
    APATITE(GTBranchDefinition.ORGANIC, "Apatite", true, new Color(0xc1c1f6), new Color(0x676784), beeSpecies -> {
        beeSpecies.addProduct(GTModHandler.getModItem(ExtraBees.ID, "honeyComb", 1, 9), 0.15f);
        beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.APATITE), 0.15f);
        beeSpecies.setHumidity(EnumHumidity.NORMAL);
        beeSpecies.setTemperature(WARM);
    }, template -> {
        AlleleHelper.instance.set(template, SPEED, Speed.FASTEST);
        AlleleHelper.instance.set(template, LIFESPAN, Lifespan.LONGER);
        AlleleHelper.instance.set(template, FLOWER_PROVIDER, Flowers.WHEAT);
        AlleleHelper.instance.set(template, FLOWERING, Flowering.FASTER);
        AlleleHelper.instance.set(template, FLOWER_PROVIDER, getFlowers(EXTRABEES, "rock"));
    }, dis -> {
        IBeeMutationCustom tMutation = dis.registerMutation(ASH, COAL, 10);
        tMutation.requireResource("blockApatite");
    }),
    FERTILIZER(GTBranchDefinition.ORGANIC, "Fertilizer", true, new Color(0x7fcef5), new Color(0x654525), beeSpecies -> {
        beeSpecies.addProduct(GTModHandler.getModItem(ExtraBees.ID, "honeyComb", 1, 9), 0.15f);
        beeSpecies.addSpecialty(GTOreDictUnificator.get(OrePrefixes.dustTiny, Materials.Ash, 1), 0.2f);
        beeSpecies.addSpecialty(GTOreDictUnificator.get(OrePrefixes.dustTiny, Materials.DarkAsh, 1), 0.2f);
        beeSpecies.addSpecialty(ItemList.FR_Fertilizer.get(1), 0.3f);
        beeSpecies.addSpecialty(ItemList.IC2_Fertilizer.get(1), 0.3f);
        beeSpecies.setHumidity(DAMP);
        beeSpecies.setTemperature(WARM);
    }, template -> {
        AlleleHelper.instance.set(template, SPEED, Speed.FASTEST);
        AlleleHelper.instance.set(template, LIFESPAN, Lifespan.LONGER);
        AlleleHelper.instance.set(template, FLOWER_PROVIDER, Flowers.WHEAT);
        AlleleHelper.instance.set(template, FLOWERING, Flowering.FASTER);
    }, dis -> dis.registerMutation(ASH, APATITE, 8)),
    // Phosphorus bee, Humidity: normal, Temperature: Hot, Parents: Apatite & Ash, Mutationrate: 12%, Combrate: 55%
    PHOSPHORUS(GTBranchDefinition.ORGANIC, "Phosphorus", false, new Color(0xFFC826), new Color(0xC1C1F6),
        beeSpecies -> {
            beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.PHOSPHORUS), 0.35f);
            beeSpecies.setHumidity(EnumHumidity.NORMAL);
            beeSpecies.setTemperature(HOT);
            beeSpecies.setNocturnal();
            beeSpecies.setHasEffect();
        }, template -> AlleleHelper.instance.set(template, LIFESPAN, Lifespan.SHORTEST), dis -> {
            IBeeMutationCustom tMutation = dis.registerMutation(APATITE, ASH, 12);
            tMutation.restrictTemperature(HOT);
            GregTechAPI.sGTCompleteLoad.add(() -> tMutation.requireResource(GregTechAPI.sBlockGem2, 8));
        }),
    // Tea bee, Humidity: normal, Parents: Ash and Fertilizer, Mutationrate: 10%, combrate: 10%
    TEA(GTBranchDefinition.ORGANIC, "Tea", false, new Color(0x65D13A), new Color(0x9a9679), beeSpecies -> {
        beeSpecies.addProduct(
            GTModHandler.getModItem(PamsHarvestCraft.ID, "tealeafItem", 1, ItemList.Crop_Drop_TeaLeaf.get(1)),
            0.10f);
        beeSpecies.setHumidity(EnumHumidity.NORMAL);
        beeSpecies.setTemperature(NORMAL);
        beeSpecies.setHasEffect();
    }, template -> AlleleHelper.instance.set(template, LIFESPAN, Lifespan.SHORTEST),
        dis -> dis.registerMutation(FERTILIZER, ASH, 10)),
    // Mica bee, Humidity: normal, Parents: Silicon & PEAT, Mutationrate: 15%, Combrate: 25%
    MICA(GTBranchDefinition.ORGANIC, "Mica", false, new Color(0xFFC826), new Color(0xC1C1F6), beeSpecies -> {
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.MICA), 0.25f);
        beeSpecies.setHumidity(EnumHumidity.NORMAL);
        beeSpecies.setTemperature(HOT);
        beeSpecies.setNocturnal();
        beeSpecies.setHasEffect();
    }, template -> AlleleHelper.instance.set(template, LIFESPAN, Lifespan.SHORTEST), dis -> {
        IBeeMutationCustom tMutation = dis.registerMutation(PEAT, getSpecies(MAGICBEES, "Silicon"), 15);
        GregTechAPI.sGTCompleteLoad.add(() -> tMutation.requireResource(GregTechAPI.sBlockCasings5, 0));
    }),

    // gems
    REDSTONE(GTBranchDefinition.GEM, "Redstone", true, new Color(0x7D0F0F), new Color(0xD11919), beeSpecies -> {
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.STONE), 0.30f);
        beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.REDSTONE), 0.15f);
        beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.RAREEARTH), 0.15f);
        beeSpecies.setHumidity(EnumHumidity.NORMAL);
        beeSpecies.setTemperature(EnumTemperature.NORMAL);
    }, template -> AlleleHelper.instance.set(template, SPEED, Speed.SLOWER), dis -> {
        IBeeMutationCustom tMutation = dis
            .registerMutation(getSpecies(FORESTRY, "Industrious"), getSpecies(FORESTRY, "Demonic"), 10);
        tMutation.requireResource("blockRedstone");
    }),
    LAPIS(GTBranchDefinition.GEM, "Lapis", true, new Color(0x1947D1), new Color(0x476CDA), beeSpecies -> {
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.STONE), 0.30f);
        beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.LAPIS), 0.15f);
        beeSpecies.setHumidity(EnumHumidity.NORMAL);
        beeSpecies.setTemperature(EnumTemperature.NORMAL);
    }, template -> AlleleHelper.instance.set(template, SPEED, Speed.SLOWER), dis -> {
        IBeeMutationCustom tMutation = dis
            .registerMutation(getSpecies(FORESTRY, "Demonic"), getSpecies(FORESTRY, "Imperial"), 10);
        tMutation.requireResource("blockLapis");
    }),
    CERTUS(GTBranchDefinition.GEM, "CertusQuartz", true, new Color(0x57CFFB), new Color(0xBBEEFF), beeSpecies -> {
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.STONE), 0.30f);
        beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.CERTUS), 0.15f);
        beeSpecies.setHumidity(EnumHumidity.NORMAL);
        beeSpecies.setTemperature(EnumTemperature.NORMAL);
    }, template -> AlleleHelper.instance.set(template, SPEED, Speed.SLOWER), dis -> {
        IBeeMutationCustom tMutation = dis.registerMutation(getSpecies(FORESTRY, "Hermitic"), LAPIS, 10);
        tMutation.requireResource(GameRegistry.findBlock(AppliedEnergistics2.ID, "tile.BlockQuartz"), 0);
    }),
    FLUIX(GTBranchDefinition.GEM, "FluixDust", true, new Color(0xA375FF), new Color(0xB591FF), beeSpecies -> {
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.STONE), 0.30f);
        beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.FLUIX), 0.15f);
        beeSpecies.setHumidity(EnumHumidity.NORMAL);
        beeSpecies.setTemperature(EnumTemperature.NORMAL);
    }, template -> AlleleHelper.instance.set(template, SPEED, Speed.SLOWER), dis -> {
        IBeeMutationCustom tMutation = dis.registerMutation(REDSTONE, LAPIS, 7);
        tMutation.requireResource(GameRegistry.findBlock(AppliedEnergistics2.ID, "tile.BlockFluix"), 0);
    }),
    DIAMOND(GTBranchDefinition.GEM, "Diamond", false, new Color(0xCCFFFF), new Color(0xA3CCCC), beeSpecies -> {
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.STONE), 0.30f);
        beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.DIAMOND), 0.15f);
        beeSpecies.setHumidity(EnumHumidity.NORMAL);
        beeSpecies.setTemperature(HOT);
        beeSpecies.setHasEffect();
    }, template -> AlleleHelper.instance.set(template, SPEED, Speed.SLOWER), dis -> {
        IBeeMutationCustom tMutation = dis.registerMutation(CERTUS, COAL, 3);
        tMutation.requireResource("blockDiamond");
    }),
    RUBY(GTBranchDefinition.GEM, "Ruby", false, new Color(0xE6005C), new Color(0xCC0052), beeSpecies -> {
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.STONE), 0.30f);
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.RUBY), 0.15f);
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.REDGARNET), 0.05f);
        beeSpecies.setHumidity(DAMP);
        beeSpecies.setTemperature(HOT);
    }, template -> AlleleHelper.instance.set(template, SPEED, Speed.SLOWER), dis -> {
        IBeeMutationCustom tMutation = dis.registerMutation(REDSTONE, DIAMOND, 5);
        tMutation.requireResource("blockRuby");
    }),
    SAPPHIRE(GTBranchDefinition.GEM, "Sapphire", true, new Color(0x0033CC), new Color(0x00248F), beeSpecies -> {
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.STONE), 0.30f);
        beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.SAPPHIRE), 0.15f);
        beeSpecies.setHumidity(EnumHumidity.NORMAL);
        beeSpecies.setTemperature(EnumTemperature.NORMAL);
    }, template -> AlleleHelper.instance.set(template, SPEED, Speed.SLOWER), dis -> {
        IBeeMutationCustom tMutation = dis.registerMutation(CERTUS, LAPIS, 5);
        tMutation.requireResource(GregTechAPI.sBlockGem2, 12);
    }),
    OLIVINE(GTBranchDefinition.GEM, "Olivine", true, new Color(0x248F24), new Color(0xCCFFCC), beeSpecies -> {
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.STONE), 0.30f);
        beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.OLIVINE), 0.15f);
        beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.MAGNESIUM), 0.05f);
        beeSpecies.setHumidity(EnumHumidity.NORMAL);
        beeSpecies.setTemperature(EnumTemperature.NORMAL);
    }, template -> AlleleHelper.instance.set(template, SPEED, Speed.SLOWER),
        dis -> dis.registerMutation(CERTUS, getSpecies(FORESTRY, "Ended"), 5)),
    EMERALD(GTBranchDefinition.GEM, "Emerald", false, new Color(0x248F24), new Color(0x2EB82E), beeSpecies -> {
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.STONE), 0.30f);
        beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.EMERALD), 0.15f);
        beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.ALUMINIUM), 0.05f);
        beeSpecies.setHumidity(EnumHumidity.NORMAL);
        beeSpecies.setTemperature(COLD);
        beeSpecies.setHasEffect();
    }, template -> AlleleHelper.instance.set(template, SPEED, Speed.SLOWER), dis -> {
        IBeeMutationCustom tMutation = dis.registerMutation(OLIVINE, DIAMOND, 4);
        tMutation.requireResource("blockEmerald");
    }),
    REDGARNET(GTBranchDefinition.GEM, "RedGarnet", false, new Color(0xBD4C4C), new Color(0xECCECE), beeSpecies -> {
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.STONE), 0.30f);
        beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.REDGARNET), 0.15f);
        beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.PYROPE), 0.05f);
        beeSpecies.setHumidity(DAMP);
        beeSpecies.setTemperature(WARM);
        beeSpecies.setHasEffect();
    }, template -> {
        AlleleHelper.instance.set(template, SPEED, Speed.FAST);
        AlleleHelper.instance.set(template, LIFESPAN, Lifespan.SHORTEST);
    }, dis -> {
        IBeeMutationCustom tMutation = dis.registerMutation(DIAMOND, RUBY, 4);
        tMutation.requireResource("blockGarnetRed");
    }),
    YELLOWGARNET(GTBranchDefinition.GEM, "YellowGarnet", false, new Color(0xA3A341), new Color(0xEDEDCE),
        beeSpecies -> {
            beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.STONE), 0.30f);
            beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.YELLOWGARNET), 0.15f);
            beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.GROSSULAR), 0.05f);
            beeSpecies.setHumidity(DAMP);
            beeSpecies.setTemperature(WARM);
            beeSpecies.setHasEffect();
        }, template -> {
            AlleleHelper.instance.set(template, SPEED, Speed.FAST);
            AlleleHelper.instance.set(template, LIFESPAN, Lifespan.SHORTEST);
        }, dis -> {
            IBeeMutationCustom tMutation = dis.registerMutation(EMERALD, REDGARNET, 3);
            tMutation.requireResource("blockGarnetYellow");
        }),
    FIRESTONE(GTBranchDefinition.GEM, "Firestone", false, new Color(0xC00000), new Color(0xFF0000), beeSpecies -> {
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.STONE), 0.30f);
        beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.FIRESTONE), 0.20f);
        beeSpecies.setHumidity(DAMP);
        beeSpecies.setTemperature(WARM);
        beeSpecies.setHasEffect();
    }, template -> {
        AlleleHelper.instance.set(template, SPEED, Speed.FAST);
        AlleleHelper.instance.set(template, LIFESPAN, Lifespan.SHORTEST);
        AlleleHelper.instance.set(template, NOCTURNAL, true);
    }, dis -> {
        IBeeMutationCustom tMutation = dis.registerMutation(REDSTONE, RUBY, 4);
        tMutation.requireResource("blockFirestone");
    }),
    PRISMATIC(GTBranchDefinition.GEM, "Prismatic", false, new Color(0x117777), new Color(0xcfe4e4), beeSpecies -> {
        beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.PRISMATIC), 0.1f);
        beeSpecies.setHumidity(DAMP);
        beeSpecies.setTemperature(COLD);
        beeSpecies.setHasEffect();
    }, template -> {
        AlleleHelper.instance.set(template, SPEED, Speed.NORMAL);
        AlleleHelper.instance.set(template, LIFESPAN, Lifespan.NORMAL);
        AlleleHelper.instance.set(template, TOLERANT_FLYER, true);
        AlleleHelper.instance.set(template, TEMPERATURE_TOLERANCE, Tolerance.BOTH_1);
        AlleleHelper.instance.set(template, EFFECT, getEffect(MAGICBEES, "SlowSpeed"));
        AlleleHelper.instance.set(template, FLOWER_PROVIDER, Flowers.END);
        AlleleHelper.instance.set(template, FLOWERING, Flowering.SLOWEST);
    }, dis -> {
        IBeeMutationCustom tMutation = dis.registerMutation(CERTUS, getSpecies(EXTRABEES, "ocean"), 10);
        tMutation.restrictHumidity(DAMP);
        if (Botania.isModLoaded()) {
            tMutation.requireResource(
                Block.getBlockFromItem(
                    GTModHandler.getModItem(Botania.ID, "prismarine", 1)
                        .getItem()),
                0);
        } else {
            tMutation.requireResource("blockPrismarine");
        }
    }),
    // Metal Line
    COPPER(GTBranchDefinition.METAL, "Copper", true, new Color(0xFF6600), new Color(0xE65C00), beeSpecies -> {
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.SLAG), 0.30f);
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.COPPER), 0.15f);
        beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.GOLD), 0.05f);
        beeSpecies.setHumidity(EnumHumidity.NORMAL);
        beeSpecies.setTemperature(EnumTemperature.NORMAL);
    }, template -> AlleleHelper.instance.set(template, SPEED, Speed.SLOWER), dis -> {
        IBeeMutationCustom tMutation = dis.registerMutation(getSpecies(FORESTRY, "Majestic"), CLAY, 13);
        tMutation.requireResource("blockCopper");
    }),
    TIN(GTBranchDefinition.METAL, "Tin", true, new Color(0xD4D4D4), new Color(0xDDDDDD), beeSpecies -> {
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.SLAG), 0.30f);
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.TIN), 0.15f);
        beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.ZINC), 0.05f);
        beeSpecies.setHumidity(EnumHumidity.NORMAL);
        beeSpecies.setTemperature(EnumTemperature.NORMAL);
    }, template -> AlleleHelper.instance.set(template, SPEED, Speed.SLOWER), dis -> {
        IBeeMutationCustom tMutation = dis.registerMutation(CLAY, getSpecies(FORESTRY, "Diligent"), 13);
        tMutation.requireResource("blockTin");
    }),
    LEAD(GTBranchDefinition.METAL, "Lead", true, new Color(0x666699), new Color(0xA3A3CC), beeSpecies -> {
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.SLAG), 0.30f);
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.LEAD), 0.15f);
        beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.SULFUR), 0.05f);
        beeSpecies.setHumidity(DAMP);
        beeSpecies.setTemperature(WARM);
    }, template -> AlleleHelper.instance.set(template, SPEED, Speed.SLOWER), dis -> {
        IBeeMutationCustom tMutation = dis.registerMutation(COAL, COPPER, 13);
        tMutation.requireResource("blockLead");
    }),
    IRON(GTBranchDefinition.METAL, "Iron", true, new Color(0xDA9147), new Color(0xDE9C59), beeSpecies -> {
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.SLAG), 0.30f);
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.IRON), 0.15f);
        beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.TIN), 0.05f);
        beeSpecies.setHumidity(EnumHumidity.NORMAL);
        beeSpecies.setTemperature(EnumTemperature.NORMAL);
    }, template -> AlleleHelper.instance.set(template, SPEED, Speed.SLOWER), dis -> {
        IBeeMutationCustom tMutation = dis.registerMutation(TIN, COPPER, 13);
        tMutation.requireResource("blockIron");
    }),
    STEEL(GTBranchDefinition.METAL, "Steel", true, new Color(0x808080), new Color(0x999999), beeSpecies -> {
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.SLAG), 0.30f);
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.STEEL), 0.15f);
        beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.IRON), 0.05f);
        beeSpecies.setHumidity(EnumHumidity.NORMAL);
        beeSpecies.setTemperature(WARM);
    }, template -> AlleleHelper.instance.set(template, SPEED, Speed.SLOWER), dis -> {
        IBeeMutationCustom tMutation = dis.registerMutation(IRON, COAL, 10);
        tMutation.requireResource(GregTechAPI.sBlockMetal6, 13);
    }),
    NICKEL(GTBranchDefinition.METAL, "Nickel", true, new Color(0x8585AD), new Color(0x8585AD), beeSpecies -> {
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.SLAG), 0.30f);
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.NICKEL), 0.15f);
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.PLATINUM), 0.02f);
        beeSpecies.setHumidity(EnumHumidity.NORMAL);
        beeSpecies.setTemperature(EnumTemperature.NORMAL);
    }, template -> AlleleHelper.instance.set(template, SPEED, Speed.SLOWER), dis -> {
        IBeeMutationCustom tMutation = dis.registerMutation(IRON, COPPER, 13);
        tMutation.requireResource("blockNickel");
    }),
    ZINC(GTBranchDefinition.METAL, "Zinc", true, new Color(0xF0DEF0), new Color(0xF2E1F2), beeSpecies -> {
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.SLAG), 0.30f);
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.ZINC), 0.15f);
        beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.GALLIUM), 0.05f);
        beeSpecies.setHumidity(EnumHumidity.NORMAL);
        beeSpecies.setTemperature(EnumTemperature.NORMAL);
    }, template -> AlleleHelper.instance.set(template, SPEED, Speed.SLOWER), dis -> {
        IBeeMutationCustom tMutation = dis.registerMutation(IRON, TIN, 13);
        tMutation.requireResource("blockZinc");
    }),
    SILVER(GTBranchDefinition.METAL, "Silver", true, new Color(0xC2C2D6), new Color(0xCECEDE), beeSpecies -> {
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.SLAG), 0.30f);
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.SILVER), 0.15f);
        beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.SULFUR), 0.05f);
        beeSpecies.setHumidity(EnumHumidity.NORMAL);
        beeSpecies.setTemperature(COLD);
    }, template -> AlleleHelper.instance.set(template, SPEED, Speed.SLOWER), dis -> {
        IBeeMutationCustom tMutation = dis.registerMutation(LEAD, TIN, 10);
        tMutation.requireResource("blockSilver");
    }),
    CRYOLITE(GTBranchDefinition.METAL, "Cryolite", true, new Color(0xBFEFFF), new Color(0x73B9D0), beeSpecies -> {
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.SLAG), 0.30f);
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.CRYOLITE), 0.15f);
        beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.SILVER), 0.05f);
        beeSpecies.setHumidity(EnumHumidity.NORMAL);
        beeSpecies.setTemperature(WARM);
    }, template -> AlleleHelper.instance.set(template, SPEED, Speed.FASTEST), dis -> {
        IBeeMutationCustom tMutation = dis.registerMutation(LEAD, SILVER, 9);
        tMutation.requireResource("blockCryolite");
    }),
    GOLD(GTBranchDefinition.METAL, "Gold", true, new Color(0xEBC633), new Color(0xEDCC47), beeSpecies -> {
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.SLAG), 0.30f);
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.GOLD), 0.15f);
        beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.NICKEL), 0.05f);
        beeSpecies.setHumidity(EnumHumidity.NORMAL);
        beeSpecies.setTemperature(WARM);
    }, template -> AlleleHelper.instance.set(template, SPEED, Speed.SLOWER), dis -> {
        IBeeMutationCustom tMutation = dis.registerMutation(LEAD, COPPER, 13);
        tMutation.requireResource("blockGold");
        tMutation.restrictTemperature(HOT);
    }),
    ARSENIC(GTBranchDefinition.METAL, "Arsenic", true, new Color(0x736C52), new Color(0x292412), beeSpecies -> {
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.SLAG), 0.30f);
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.ARSENIC), 0.15f);
        beeSpecies.setHumidity(EnumHumidity.NORMAL);
        beeSpecies.setTemperature(WARM);
    }, template -> AlleleHelper.instance.set(template, SPEED, Speed.SLOWER), dis -> {
        IBeeMutationCustom tMutation = dis.registerMutation(ZINC, SILVER, 10);
        tMutation.requireResource("blockArsenic");
    }),

    // Rare Metals
    ALUMINIUM(GTBranchDefinition.RAREMETAL, "Aluminium", true, new Color(0xB8B8FF), new Color(0xD6D6FF), beeSpecies -> {
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.SLAG), 0.30f);
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.ALUMINIUM), 0.15f);
        beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.BAUXITE), 0.05f);
        beeSpecies.setHumidity(ARID);
        beeSpecies.setTemperature(HOT);
    }, template -> AlleleHelper.instance.set(template, SPEED, Speed.SLOWER), dis -> {
        IBeeMutationCustom tMutation = dis.registerMutation(NICKEL, ZINC, 9);
        tMutation.requireResource("blockAluminium");
    }),
    TITANIUM(GTBranchDefinition.RAREMETAL, "Titanium", true, new Color(0xCC99FF), new Color(0xDBB8FF), beeSpecies -> {
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.SLAG), 0.30f);
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.TITANIUM), 0.15f);
        beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.ALMANDINE), 0.05f);
        beeSpecies.setHumidity(ARID);
        beeSpecies.setTemperature(HOT);
    }, template -> AlleleHelper.instance.set(template, SPEED, Speed.SLOWER), dis -> {
        IBeeMutationCustom tMutation = dis.registerMutation(REDSTONE, ALUMINIUM, 5);
        tMutation.requireResource(GregTechAPI.sBlockMetal7, 9);
    }),
    GLOWSTONE(GTBranchDefinition.RAREMETAL, "Glowstone", false, new Color(0xE5CA2A), new Color(0xFFBC5E),
        beeSpecies -> {
            beeSpecies.addSpecialty(Materials.Glowstone.getDust(1), 0.20f);
            beeSpecies.setHumidity(EnumHumidity.NORMAL);
            beeSpecies.setNocturnal();
            beeSpecies.setHasEffect();
        }, template -> AlleleHelper.instance.set(template, LIFESPAN, Lifespan.NORMAL),
        dis -> dis.registerMutation(REDSTONE, GOLD, 10)),
    SUNNARIUM(GTBranchDefinition.RAREMETAL, "Sunnarium", false, new Color(0xFFBC5E), new Color(0xE5CA2A),
        beeSpecies -> {
            beeSpecies.addProduct(Materials.Glowstone.getDust(1), 0.30f);
            beeSpecies.addSpecialty(Materials.Sunnarium.getDust(1), 0.05f);
            beeSpecies.setHumidity(EnumHumidity.NORMAL);
            beeSpecies.setNocturnal();
            beeSpecies.setHasEffect();
        }, template -> AlleleHelper.instance.set(template, LIFESPAN, Lifespan.LONGEST), dis -> {
            IBeeMutationCustom tMutation = dis.registerMutation(GLOWSTONE, GOLD, 5);
            GregTechAPI.sGTCompleteLoad.add(() -> tMutation.requireResource(GregTechAPI.sBlockCasings1, 15));
        }),
    CHROME(GTBranchDefinition.RAREMETAL, "Chrome", true, new Color(0xEBA1EB), new Color(0xF2C3F2), beeSpecies -> {
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.SLAG), 0.30f);
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.CHROME), 0.15f);
        beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.MAGNESIUM), 0.05f);
        beeSpecies.setHumidity(ARID);
        beeSpecies.setTemperature(HOT);
    }, template -> AlleleHelper.instance.set(template, SPEED, Speed.SLOWER), dis -> {
        IBeeMutationCustom tMutation = dis.registerMutation(TITANIUM, RUBY, 5);
        tMutation.requireResource(GregTechAPI.sBlockMetal2, 3);
    }),
    MANGANESE(GTBranchDefinition.RAREMETAL, "Manganese", true, new Color(0xD5D5D5), new Color(0xAAAAAA), beeSpecies -> {
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.SLAG), 0.30f);
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.MANGANESE), 0.15f);
        beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.IRON), 0.05f);
        beeSpecies.setHumidity(ARID);
        beeSpecies.setTemperature(HOT);
    }, template -> AlleleHelper.instance.set(template, SPEED, Speed.SLOWER), dis -> {
        IBeeMutationCustom tMutation = dis.registerMutation(TITANIUM, ALUMINIUM, 5);
        tMutation.requireResource(GregTechAPI.sBlockMetal4, 6);
    }),
    TUNGSTEN(GTBranchDefinition.RAREMETAL, "Tungsten", false, new Color(0x5C5C8A), new Color(0x7D7DA1), beeSpecies -> {
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.SLAG), 0.30f);
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.TUNGSTEN), 0.15f);
        beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.MOLYBDENUM), 0.05f);
        beeSpecies.setHumidity(ARID);
        beeSpecies.setTemperature(HOT);
    }, template -> AlleleHelper.instance.set(template, SPEED, Speed.SLOWER), dis -> {
        IBeeMutationCustom tMutation = dis.registerMutation(getSpecies(FORESTRY, "Heroic"), MANGANESE, 5);
        tMutation.requireResource(GregTechAPI.sBlockMetal7, 11);
    }),
    PLATINUM(GTBranchDefinition.RAREMETAL, "Platinum", false, new Color(0xE6E6E6), new Color(0xFFFFCC), beeSpecies -> {
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.SLAG), 0.30f);
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.PLATINUM), 0.30f);
        beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.IRIDIUM), 0.02f);
        beeSpecies.setHumidity(ARID);
        beeSpecies.setTemperature(HOT);
    }, template -> AlleleHelper.instance.set(template, SPEED, Speed.SLOWER), dis -> {
        IBeeMutationCustom tMutation = dis.registerMutation(DIAMOND, CHROME, 5);
        tMutation.requireResource("blockPlatinum");
    }),
    IRIDIUM(GTBranchDefinition.RAREMETAL, "Iridium", false, new Color(0xDADADA), new Color(0xD1D1E0), beeSpecies -> {
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.SLAG), 0.30f);
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.IRIDIUM), 0.30f);
        beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.OSMIUM), 0.05f);
        beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.PALLADIUM), 0.30f);
        beeSpecies.setHumidity(ARID);
        beeSpecies.setTemperature(HELLISH);
        beeSpecies.setHasEffect();
    }, template -> AlleleHelper.instance.set(template, SPEED, Speed.SLOWER), dis -> {
        IBeeMutationCustom tMutation = dis.registerMutation(TUNGSTEN, PLATINUM, 5);
        tMutation.requireResource(GregTechAPI.sBlockMetal3, 12);
    }),
    OSMIUM(GTBranchDefinition.RAREMETAL, "Osmium", false, new Color(0x2B2BDA), new Color(0x8B8B8B), beeSpecies -> {
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.SLAG), 0.30f);
        beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.OSMIUM), 0.30f);
        beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.IRIDIUM), 0.05f);
        beeSpecies.setHumidity(ARID);
        beeSpecies.setTemperature(COLD);
        beeSpecies.setHasEffect();
    }, template -> AlleleHelper.instance.set(template, SPEED, Speed.SLOWER), dis -> {
        IBeeMutationCustom tMutation = dis.registerMutation(TUNGSTEN, PLATINUM, 5);
        tMutation.requireResource(GregTechAPI.sBlockMetal5, 9);
    }),
    SALTY(GTBranchDefinition.RAREMETAL, "Salt", true, new Color(0xF0C8C8), new Color(0xFAFAFA), beeSpecies -> {
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.SLAG), 0.30f);
        beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.SALT), 0.35f);
        beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.LITHIUM), 0.05f);
        beeSpecies.addSpecialty(GTOreDictUnificator.get(OrePrefixes.dust, Materials.Borax, 1L), 0.1f);
        beeSpecies.setHumidity(ARID);
        beeSpecies.setTemperature(WARM);
    }, template -> AlleleHelper.instance.set(template, SPEED, Speed.SLOWER), dis -> {
        IBeeMutationCustom tMutation = dis.registerMutation(CLAY, ALUMINIUM, 5);
        tMutation.requireResource("blockSalt");
    }),
    LITHIUM(GTBranchDefinition.RAREMETAL, "Lithium", false, new Color(0xF0328C), new Color(0xE1DCFF), beeSpecies -> {
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.SLAG), 0.30f);
        beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.LITHIUM), 0.15f);
        beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.SALT), 0.05f);
        beeSpecies.setHumidity(EnumHumidity.NORMAL);
        beeSpecies.setTemperature(COLD);
    }, template -> AlleleHelper.instance.set(template, SPEED, Speed.SLOWER), dis -> {
        IBeeMutationCustom tMutation = dis.registerMutation(SALTY, ALUMINIUM, 5);
        tMutation.requireResource("frameGtLithium");
    }),
    ELECTROTINE(GTBranchDefinition.RAREMETAL, "Electrotine", false, new Color(0x1E90FF), new Color(0x3CB4C8),
        beeSpecies -> {
            beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.SLAG), 0.30f);
            beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.ELECTROTINE), 0.15f);
            beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.REDSTONE), 0.05f);
            beeSpecies.setHumidity(EnumHumidity.NORMAL);
            beeSpecies.setTemperature(HOT);
        }, template -> AlleleHelper.instance.set(template, SPEED, Speed.SLOWER), dis -> {
            IBeeMutationCustom tMutation = dis.registerMutation(REDSTONE, GOLD, 5);
            tMutation.requireResource("blockElectrotine");
        }),
    // Sulfur bee, Humidity: normal, Temperature: Hot, Parents: PEAT & Ash, Mutationrate: 15%, Combrate: 80%
    SULFUR(GTBranchDefinition.RAREMETAL, "Sulfur", false, new Color(0x6F6F01), new Color(0x8B8B8B), beeSpecies -> {
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.SULFUR), 0.70f);
        beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.PYRITE), 0.15f);
        beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.FIRESTONE), 0.05f);
        beeSpecies.setHumidity(EnumHumidity.NORMAL);
        beeSpecies.setTemperature(HOT);
    }, template -> AlleleHelper.instance.set(template, SPEED, Speed.NORMAL),
        dis -> dis.registerMutation(ASH, PEAT, 15)),

    INDIUM(GTBranchDefinition.RAREMETAL, "Indium", false, new Color(0xFFA9FF), new Color(0x8F5D99), beeSpecies -> {
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.INDIUM), 0.05f);
        beeSpecies.setHumidity(EnumHumidity.NORMAL);
        beeSpecies.setTemperature(HOT);
    }, template -> AlleleHelper.instance.set(template, SPEED, Speed.SLOWEST), dis -> {
        IBeeMutationCustom tMutation = dis.registerMutation(LEAD, OSMIUM, 1);
        tMutation.requireResource("blockIndium");
        tMutation.addMutationCondition(new GTBees.DimensionMutationCondition(39, "Venus")); // Venus Dim
        // Earlier mutation for geting ichorium
        tMutation = dis.registerMutation(SILVER, OSMIUM, 1);
        tMutation.requireResource("blockArcanite");
        tMutation.addMutationCondition(new GTBees.DimensionMutationCondition(60, "Bedrock")); // Thaumic Tinkerer
        // Bedrock Dim
    }),
    Netherite(GTBranchDefinition.RAREMETAL, "Netherite", false, new Color(0x31291a), new Color(0xada9aa),
        beeSpecies -> {
            beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.NETHERITE), 0.1f);
            beeSpecies.setHumidity(ARID);
            beeSpecies.setTemperature((HELLISH));
            beeSpecies.setHasEffect();
        }, template -> {
            AlleleHelper.instance.set(template, SPEED, Speed.SLOWEST);
            AlleleHelper.instance.set(template, LIFESPAN, Lifespan.NORMAL);
            AlleleHelper.instance.set(template, EFFECT, AlleleEffect.effectCreeper);
            AlleleHelper.instance.set(template, TEMPERATURE_TOLERANCE, Tolerance.NONE);
            AlleleHelper.instance.set(template, NOCTURNAL, true);
            AlleleHelper.instance.set(template, CAVE_DWELLING, true);
            AlleleHelper.instance.set(template, FLOWER_PROVIDER, Flowers.NETHER);
            AlleleHelper.instance.set(template, FLOWERING, Flowering.SLOWEST);
        }, dis -> {
            IBeeMutationCustom tMutation = dis.registerMutation(getSpecies(FORESTRY, "Demonic"), DIAMOND, 3);
            tMutation.restrictTemperature(HELLISH);
            tMutation.requireResource(GregTechAPI.sBlockMetal9, 12);
        }),

    // IC2
    COOLANT(GTBranchDefinition.IC2, "Coolant", false, new Color(0x144F5A), new Color(0x2494A2), beeSpecies -> {
        beeSpecies.addProduct(GTModHandler.getModItem(Forestry.ID, "beeCombs", 1, 4), 0.30f);
        beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.COOLANT), 0.15f);
        beeSpecies.setHumidity(ARID);
        beeSpecies.setTemperature(COLD);
        beeSpecies.setHasEffect();
    }, template -> {
        AlleleHelper.instance.set(template, SPEED, Speed.SLOW);
        AlleleHelper.instance.set(template, LIFESPAN, Lifespan.SHORT);
        AlleleHelper.instance.set(template, TEMPERATURE_TOLERANCE, Tolerance.UP_1);
        AlleleHelper.instance.set(template, HUMIDITY_TOLERANCE, Tolerance.BOTH_1);
        AlleleHelper.instance.set(template, FLOWER_PROVIDER, Flowers.SNOW);
        AlleleHelper.instance.set(template, EFFECT, AlleleEffect.effectGlacial);
    }, dis -> {
        IBeeMutationCustom tMutation = dis
            .registerMutation(getSpecies(FORESTRY, "Icy"), getSpecies(FORESTRY, "Glacial"), 10);
        tMutation.requireResource(
            Block.getBlockFromItem(
                GTModHandler.getModItem(IndustrialCraft2.ID, "fluidCoolant", 1)
                    .getItem()),
            0);
        tMutation.restrictTemperature(ICY);
    }),
    ENERGY(GTBranchDefinition.IC2, "Energy", false, new Color(0xC11F1F), new Color(0xEBB9B9), beeSpecies -> {
        beeSpecies.addProduct(GTModHandler.getModItem(ExtraBees.ID, "honeyComb", 1, 12), 0.30f);
        beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.ENERGY), 0.15f);
        beeSpecies.setHumidity(EnumHumidity.NORMAL);
        beeSpecies.setTemperature(WARM);
        beeSpecies.setHasEffect();
    }, template -> {
        AlleleHelper.instance.set(template, SPEED, Speed.SLOWER);
        AlleleHelper.instance.set(template, LIFESPAN, Lifespan.LONGER);
        AlleleHelper.instance.set(template, EFFECT, AlleleEffect.effectIgnition);
        AlleleHelper.instance.set(template, TEMPERATURE_TOLERANCE, Tolerance.DOWN_2);
        AlleleHelper.instance.set(template, NOCTURNAL, true);
        AlleleHelper.instance.set(template, FLOWER_PROVIDER, Flowers.NETHER);
        AlleleHelper.instance.set(template, FLOWERING, Flowering.AVERAGE);
    }, dis -> {
        IBeeMutationCustom tMutation = dis
            .registerMutation(getSpecies(FORESTRY, "Demonic"), getSpecies(EXTRABEES, "volcanic"), 10);
        tMutation.requireResource(
            Block.getBlockFromItem(
                GTModHandler.getModItem(IndustrialCraft2.ID, "fluidHotCoolant", 1)
                    .getItem()),
            0);
        tMutation.addMutationCondition(new GTBees.BiomeIDMutationCondition(128, "Boneyard Biome")); // Boneyard Biome
    }),
    LAPOTRON(GTBranchDefinition.IC2, "Lapotron", false, new Color(0x6478FF), new Color(0x1414FF), beeSpecies -> {
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.LAPIS), 0.20f);
        beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.ENERGY), 0.15f);
        beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.LAPOTRON), 0.10f);
        beeSpecies.setHumidity(DAMP);
        beeSpecies.setTemperature(ICY);
        beeSpecies.setHasEffect();
    }, template -> {
        AlleleHelper.instance.set(template, SPEED, Speed.SLOWER);
        AlleleHelper.instance.set(template, LIFESPAN, Lifespan.LONGER);
        AlleleHelper.instance.set(template, EFFECT, AlleleEffect.effectIgnition);
        AlleleHelper.instance.set(template, TEMPERATURE_TOLERANCE, Tolerance.UP_1);
        AlleleHelper.instance.set(template, NOCTURNAL, true);
        AlleleHelper.instance.set(template, FLOWER_PROVIDER, Flowers.SNOW);
        AlleleHelper.instance.set(template, FLOWERING, Flowering.AVERAGE);
    }, dis -> {
        IBeeMutationCustom tMutation = dis.registerMutation(LAPIS, ENERGY, 6);
        tMutation.requireResource("blockLapis");
        tMutation.restrictTemperature(ICY);
        tMutation.addMutationCondition(new GTBees.DimensionMutationCondition(28, "Moon")); // moon dim
    }),
    PYROTHEUM(GTBranchDefinition.IC2, "Pyrotheum", false, new Color(0xffebc4), new Color(0xe36400), beeSpecies -> {
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.ENERGY), 0.20f);
        beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.PYROTHEUM), 0.15f);
        beeSpecies.setHumidity(ARID);
        beeSpecies.setTemperature(HELLISH);
        beeSpecies.setHasEffect();
    }, template -> {
        AlleleHelper.instance.set(template, SPEED, Speed.FASTEST);
        AlleleHelper.instance.set(template, LIFESPAN, Lifespan.SHORTEST);
        AlleleHelper.instance.set(template, EFFECT, AlleleEffect.effectIgnition);
        AlleleHelper.instance.set(template, TEMPERATURE_TOLERANCE, Tolerance.NONE);
        AlleleHelper.instance.set(template, NOCTURNAL, true);
        AlleleHelper.instance.set(template, FLOWER_PROVIDER, Flowers.NETHER);
        AlleleHelper.instance.set(template, FLOWERING, Flowering.AVERAGE);
    }, dis -> {
        IBeeMutationCustom tMutation = dis.registerMutation(REDSTONE, ENERGY, 4);
        tMutation.restrictTemperature(HELLISH);
    }),
    CRYOTHEUM(GTBranchDefinition.IC2, "Cryotheum", false, new Color(0x2660ff), new Color(0x5af7ff), beeSpecies -> {
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.BLIZZ), 0.15f);
        beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.CRYOTHEUM), 0.20f);
        beeSpecies.setHumidity(ARID);
        beeSpecies.setTemperature(ICY);
        beeSpecies.setHasEffect();
    }, template -> {
        AlleleHelper.instance.set(template, SPEED, Speed.SLOWEST);
        AlleleHelper.instance.set(template, LIFESPAN, Lifespan.LONGEST);
        AlleleHelper.instance.set(template, EFFECT, AlleleEffect.effectSnowing);
        AlleleHelper.instance.set(template, TEMPERATURE_TOLERANCE, Tolerance.NONE);
        AlleleHelper.instance.set(template, NOCTURNAL, true);
        AlleleHelper.instance.set(template, FLOWER_PROVIDER, Flowers.SNOW);
        AlleleHelper.instance.set(template, FLOWERING, Flowering.AVERAGE);
    }, dis -> {
        IBeeMutationCustom tMutation = dis.registerMutation(REDSTONE, COOLANT, 4);
        tMutation.restrictTemperature(ICY);
    }),
    Explosive(GTBranchDefinition.IC2, "explosive", false, new Color(0x7E270F), new Color(0x747474), beeSpecies -> {
        beeSpecies.addProduct(GTModHandler.getIC2Item("industrialTnt", 1L), 0.2f);
        beeSpecies.setHumidity(ARID);
        beeSpecies.setTemperature(HELLISH);
        beeSpecies.setHasEffect();
    }, template -> {
        AlleleHelper.instance.set(template, SPEED, Speed.SLOWEST);
        AlleleHelper.instance.set(template, LIFESPAN, Lifespan.LONGEST);
        AlleleHelper.instance.set(template, EFFECT, AlleleEffect.effectSnowing);
        AlleleHelper.instance.set(template, TEMPERATURE_TOLERANCE, Tolerance.NONE);
        AlleleHelper.instance.set(template, NOCTURNAL, true);
        AlleleHelper.instance.set(template, FLOWER_PROVIDER, Flowers.SNOW);
        AlleleHelper.instance.set(template, FLOWERING, Flowering.AVERAGE);
    }, dis -> {
        IBeeMutationCustom tMutation = dis.registerMutation(FIRESTONE, COAL, 4);
        tMutation.requireResource(GameRegistry.findBlock(IndustrialCraft2.ID, "blockITNT"), 0);
    }),
    // Alloy
    REDALLOY(GTBranchDefinition.GTALLOY, "RedAlloy", false, new Color(0xE60000), new Color(0xB80000), beeSpecies -> {
        beeSpecies.addProduct(GTModHandler.getModItem(Forestry.ID, "beeCombs", 1, 7), 0.30f);
        beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.REDALLOY), 0.15f);
        beeSpecies.setHumidity(EnumHumidity.NORMAL);
        beeSpecies.setTemperature(EnumTemperature.NORMAL);
    }, template -> {
        AlleleHelper.instance.set(template, SPEED, Speed.SLOWER);
        AlleleHelper.instance.set(template, LIFESPAN, Lifespan.SHORTER);
    }, dis -> {
        IBeeMutationCustom tMutation = dis.registerMutation(COPPER, REDSTONE, 10);
        tMutation.requireResource("blockRedAlloy");
    }),
    REDSTONEALLOY(GTBranchDefinition.GTALLOY, "RedStoneAlloy", false, new Color(0xA50808), new Color(0xE80000),
        beeSpecies -> {
            beeSpecies.addProduct(GTModHandler.getModItem(Forestry.ID, "beeCombs", 1, 7), 0.30f);
            beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.REDSTONEALLOY), 0.15f);
            beeSpecies.setHumidity(EnumHumidity.NORMAL);
            beeSpecies.setTemperature(EnumTemperature.NORMAL);
        }, template -> {
            AlleleHelper.instance.set(template, SPEED, Speed.SLOWER);
            AlleleHelper.instance.set(template, LIFESPAN, Lifespan.SHORTER);
        }, dis -> {
            IBeeMutationCustom tMutation = dis.registerMutation(REDSTONE, REDALLOY, 8);
            tMutation.requireResource("blockRedstoneAlloy");
        }),
    CONDUCTIVEIRON(GTBranchDefinition.GTALLOY, "ConductiveIron", false, new Color(0xCEADA3), new Color(0x817671),
        beeSpecies -> {
            beeSpecies.addProduct(GTModHandler.getModItem(Forestry.ID, "beeCombs", 1, 7), 0.30f);
            beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.CONDUCTIVEIRON), 0.15f);
            beeSpecies.setHumidity(DAMP);
            beeSpecies.setTemperature(WARM);
            beeSpecies.setHasEffect();
        }, template -> {
            AlleleHelper.instance.set(template, SPEED, Speed.FAST);
            AlleleHelper.instance.set(template, LIFESPAN, Lifespan.SHORTEST);
        }, dis -> {
            IBeeMutationCustom tMutation = dis.registerMutation(REDSTONEALLOY, IRON, 8);
            tMutation.requireResource("blockConductiveIron");
        }),
    ENERGETICALLOY(GTBranchDefinition.GTALLOY, "EnergeticAlloy", false, new Color(0xFF9933), new Color(0xFFAD5C),
        beeSpecies -> {
            beeSpecies.addProduct(GTModHandler.getModItem(Forestry.ID, "beeCombs", 1, 7), 0.30f);
            beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.ENERGETICALLOY), 0.15f);
            beeSpecies.setHumidity(DAMP);
            beeSpecies.setTemperature(EnumTemperature.NORMAL);
        }, template -> {
            AlleleHelper.instance.set(template, SPEED, Speed.FAST);
            AlleleHelper.instance.set(template, LIFESPAN, Lifespan.SHORTEST);
        }, dis -> {
            IBeeMutationCustom tMutation = dis.registerMutation(REDSTONEALLOY, getSpecies(FORESTRY, "Demonic"), 9);
            tMutation.requireResource("blockEnergeticAlloy");
        }),
    VIBRANTALLOY(GTBranchDefinition.GTALLOY, "VibrantAlloy", false, new Color(0x86A12D), new Color(0xC4F2AE),
        beeSpecies -> {
            beeSpecies.addProduct(GTModHandler.getModItem(Forestry.ID, "beeCombs", 1, 7), 0.30f);
            beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.VIBRANTALLOY), 0.15f);
            beeSpecies.setHumidity(DAMP);
            beeSpecies.setTemperature(EnumTemperature.NORMAL);
            beeSpecies.setHasEffect();
        }, template -> {
            AlleleHelper.instance.set(template, SPEED, Speed.SLOWER);
            AlleleHelper.instance.set(template, LIFESPAN, Lifespan.SHORTEST);
            AlleleHelper.instance.set(template, FLOWERING, Flowering.FAST);
        }, dis -> {
            IBeeMutationCustom tMutation = dis.registerMutation(ENERGETICALLOY, getSpecies(FORESTRY, "Phantasmal"), 6);
            tMutation.requireResource("blockVibrantAlloy");
            tMutation.restrictTemperature(HOT, HELLISH);
        }),
    ELECTRICALSTEEL(GTBranchDefinition.GTALLOY, "ElectricalSteel", false, new Color(0x787878), new Color(0xD8D8D8),
        beeSpecies -> {
            beeSpecies.addProduct(GTModHandler.getModItem(Forestry.ID, "beeCombs", 1, 7), 0.30f);
            beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.ELECTRICALSTEEL), 0.15f);
            beeSpecies.setHumidity(DAMP);
            beeSpecies.setTemperature(EnumTemperature.NORMAL);
        }, template -> {
            AlleleHelper.instance.set(template, SPEED, Speed.SLOWER);
            AlleleHelper.instance.set(template, LIFESPAN, Lifespan.SHORTER);
        }, dis -> {
            IBeeMutationCustom tMutation = dis.registerMutation(STEEL, getSpecies(FORESTRY, "Demonic"), 9);
            tMutation.requireResource("blockElectricalSteel");
        }),
    DARKSTEEL(GTBranchDefinition.GTALLOY, "DarkSteel", false, new Color(0x252525), new Color(0x443B44), beeSpecies -> {
        beeSpecies.addProduct(GTModHandler.getModItem(Forestry.ID, "beeCombs", 1, 7), 0.30f);
        beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.DARKSTEEL), 0.15f);
        beeSpecies.setHumidity(EnumHumidity.NORMAL);
        beeSpecies.setTemperature(COLD);
    }, template -> {
        AlleleHelper.instance.set(template, SPEED, Speed.FAST);
        AlleleHelper.instance.set(template, LIFESPAN, Lifespan.SHORTEST);
    }, dis -> {
        IBeeMutationCustom tMutation = dis.registerMutation(ELECTRICALSTEEL, getSpecies(FORESTRY, "Demonic"), 7);
        tMutation.requireResource("blockDarkSteel");
    }),
    PULSATINGIRON(GTBranchDefinition.GTALLOY, "PulsatingIron", false, new Color(0x6DD284), new Color(0x006600),
        beeSpecies -> {
            beeSpecies.addProduct(GTModHandler.getModItem(Forestry.ID, "beeCombs", 1, 7), 0.30f);
            beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.PULSATINGIRON), 0.15f);
            beeSpecies.setHumidity(DAMP);
            beeSpecies.setTemperature(EnumTemperature.NORMAL);
        }, template -> {
            AlleleHelper.instance.set(template, SPEED, Speed.FAST);
            AlleleHelper.instance.set(template, LIFESPAN, Lifespan.SHORTEST);
        }, dis -> {
            IBeeMutationCustom tMutation = dis.registerMutation(REDALLOY, getSpecies(FORESTRY, "Ended"), 9);
            tMutation.requireResource("blockPulsatingIron");
        }),
    STAINLESSSTEEL(GTBranchDefinition.GTALLOY, "StainlessSteel", false, new Color(0xC8C8DC), new Color(0x778899),
        beeSpecies -> {
            beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.SLAG), 0.30f);
            beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.STEEL), 0.10f);
            beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.STAINLESSSTEEL), 0.15f);
            beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.CHROME), 0.05f);
            beeSpecies.setHumidity(EnumHumidity.NORMAL);
            beeSpecies.setTemperature(HOT);
        }, template -> {
            AlleleHelper.instance.set(template, SPEED, Speed.FAST);
            AlleleHelper.instance.set(template, LIFESPAN, Lifespan.SHORTEST);
            AlleleHelper.instance.set(template, EFFECT, AlleleEffect.effectIgnition);
        }, dis -> {
            IBeeMutationCustom tMutation = dis.registerMutation(CHROME, STEEL, 9);
            tMutation.requireResource("blockStainlessSteel");
        }),
    ENDERIUM(GTBranchDefinition.GTALLOY, "Enderium", false, new Color(0x599087), new Color(0x2E8B57), beeSpecies -> {
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.SLAG), 0.30f);
        beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.ENDERIUM), 0.15f);
        beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.CHROME), 0.05f);
        beeSpecies.setHumidity(EnumHumidity.NORMAL);
        beeSpecies.setTemperature(HOT);
    }, template -> {
        AlleleHelper.instance.set(template, SPEED, GTBees.speedBlinding);
        AlleleHelper.instance.set(template, EFFECT, getEffect(EXTRABEES, "teleport"));
        AlleleHelper.instance.set(template, LIFESPAN, Lifespan.SHORTEST);
    }, dis -> {
        IBeeMutationCustom tMutation = dis.registerMutation(PLATINUM, getSpecies(FORESTRY, "Phantasmal"), 3);
        tMutation.requireResource("blockEnderium");
    }),
    BEDROCKIUM(GTBranchDefinition.GTALLOY, "Bedrockium", false, new Color(0x0C0C0C), new Color(0xC6C6C6),
        beeSpecies -> {
            beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.SLAG), 0.20f);
            beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.BEDROCKIUM), 0.55f);
            beeSpecies.setHumidity(EnumHumidity.NORMAL);
            beeSpecies.setTemperature(HOT);
        }, template -> {
            AlleleHelper.instance.set(template, SPEED, Speed.SLOW);
            AlleleHelper.instance.set(template, EFFECT, getEffect(EXTRABEES, "gravity"));
            AlleleHelper.instance.set(template, LIFESPAN, Lifespan.SHORTEST);
        }, dis -> {
            IBeeMutationCustom tMutation = dis.registerMutation(Explosive, DIAMOND, 2);
            if (ExtraUtilities.isModLoaded())
                tMutation.requireResource(GameRegistry.findBlock(ExtraUtilities.ID, "block_bedrockium"), 0);
        }),

    // thaumic
    THAUMIUMDUST(GTBranchDefinition.THAUMIC, "ThaumiumDust", true, new Color(0x7A007A), new Color(0x5C005C),
        beeSpecies -> {
            beeSpecies.addProduct(GTModHandler.getModItem(Forestry.ID, "beeCombs", 1, 3), 0.30f);
            beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.THAUMIUMDUST), 0.20f);
            beeSpecies.setHumidity(EnumHumidity.NORMAL);
            beeSpecies.setTemperature(EnumTemperature.NORMAL);
        }, template -> {
            AlleleHelper.instance.set(template, SPEED, Speed.SLOWEST);
            AlleleHelper.instance.set(template, LIFESPAN, Lifespan.LONGER);
            AlleleHelper.instance.set(template, TEMPERATURE_TOLERANCE, Tolerance.BOTH_2);
            AlleleHelper.instance.set(template, EFFECT, AlleleEffect.effectExploration);
            AlleleHelper.instance.set(template, HUMIDITY_TOLERANCE, Tolerance.UP_1);
            AlleleHelper.instance.set(template, FLOWER_PROVIDER, Flowers.JUNGLE);
        }, dis -> {
            IBeeMutationCustom tMutation = dis
                .registerMutation(getSpecies(MAGICBEES, "TCFire"), getSpecies(FORESTRY, "Edenic"), 10);
            tMutation.requireResource("blockThaumium");
            tMutation.addMutationCondition(new GTBees.BiomeIDMutationCondition(192, "Magical Forest")); // magical
                                                                                                        // forest
        }),
    THAUMIUMSHARD(GTBranchDefinition.THAUMIC, "ThaumiumShard", true, new Color(0x9966FF), new Color(0xAD85FF),
        beeSpecies -> {
            beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.THAUMIUMDUST), 0.30f);
            beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.THAUMIUMSHARD), 0.20f);
            beeSpecies.setHumidity(EnumHumidity.NORMAL);
            beeSpecies.setTemperature(EnumTemperature.NORMAL);
            beeSpecies.setHasEffect();
        }, template -> {
            AlleleHelper.instance.set(template, SPEED, Speed.SLOWER);
            AlleleHelper.instance.set(template, LIFESPAN, Lifespan.SHORT);
            AlleleHelper.instance.set(template, TEMPERATURE_TOLERANCE, Tolerance.UP_1);
            AlleleHelper.instance.set(template, HUMIDITY_TOLERANCE, Tolerance.BOTH_1);
            AlleleHelper.instance.set(template, FLOWER_PROVIDER, Flowers.SNOW);
            AlleleHelper.instance.set(template, EFFECT, AlleleEffect.effectGlacial);
        }, dis -> {
            IBeeMutationCustom tMutation = dis.registerMutation(THAUMIUMDUST, getSpecies(MAGICBEES, "TCWater"), 10);
            tMutation.addMutationCondition(new GTBees.BiomeIDMutationCondition(192, "Magical Forest")); // magical
                                                                                                        // forest
        }),
    AMBER(GTBranchDefinition.THAUMIC, "Amber", true, new Color(0xEE7700), new Color(0x774B15), beeSpecies -> {
        beeSpecies.addProduct(GTModHandler.getModItem(Forestry.ID, "beeCombs", 1, 3), 0.30f);
        beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.AMBER), 0.20f);
        beeSpecies.setHumidity(EnumHumidity.NORMAL);
        beeSpecies.setTemperature(EnumTemperature.NORMAL);
        beeSpecies.setHasEffect();
    }, template -> {
        AlleleHelper.instance.set(template, SPEED, Speed.SLOWER);
        AlleleHelper.instance.set(template, TEMPERATURE_TOLERANCE, Tolerance.NONE);
        AlleleHelper.instance.set(template, HUMIDITY_TOLERANCE, Tolerance.NONE);
    }, dis -> {
        IBeeMutationCustom tMutation = dis.registerMutation(THAUMIUMDUST, STICKYRESIN, 10);
        tMutation.requireResource("blockAmber");
    }),
    QUICKSILVER(GTBranchDefinition.THAUMIC, "Quicksilver", true, new Color(0x7A007A), new Color(0x5C005C),
        beeSpecies -> {
            beeSpecies.addProduct(GTModHandler.getModItem(Forestry.ID, "beeCombs", 1, 3), 0.30f);
            beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.QUICKSILVER), 0.20f);
            beeSpecies.setHumidity(EnumHumidity.NORMAL);
            beeSpecies.setTemperature(EnumTemperature.NORMAL);
            beeSpecies.setHasEffect();
        }, template -> {
            AlleleHelper.instance.set(template, TEMPERATURE_TOLERANCE, Tolerance.UP_1);
            AlleleHelper.instance.set(template, HUMIDITY_TOLERANCE, Tolerance.UP_1);
            AlleleHelper.instance.set(template, FLOWER_PROVIDER, Flowers.JUNGLE);
            AlleleHelper.instance.set(template, EFFECT, AlleleEffect.effectMiasmic);
        }, dis -> dis.registerMutation(THAUMIUMDUST, SILVER, 10)),
    SALISMUNDUS(GTBranchDefinition.THAUMIC, "SalisMundus", true, new Color(0xF7ADDE), new Color(0x592582),
        beeSpecies -> {
            beeSpecies.addProduct(GTModHandler.getModItem(Forestry.ID, "beeCombs", 1, 3), 0.30f);
            beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.SALISMUNDUS), 0.20f);
            beeSpecies.setHumidity(EnumHumidity.NORMAL);
            beeSpecies.setTemperature(EnumTemperature.NORMAL);
            beeSpecies.setHasEffect();
        }, template -> {
            AlleleHelper.instance.set(template, TEMPERATURE_TOLERANCE, Tolerance.UP_1);
            AlleleHelper.instance.set(template, HUMIDITY_TOLERANCE, Tolerance.UP_1);
            AlleleHelper.instance.set(template, FLOWER_PROVIDER, Flowers.JUNGLE);
            AlleleHelper.instance.set(template, EFFECT, AlleleEffect.effectMiasmic);
            AlleleHelper.instance.set(template, LIFESPAN, Lifespan.SHORT);
            AlleleHelper.instance.set(template, SPEED, Speed.SLOWER);
        }, dis -> {
            IBeeMutationCustom tMutation = dis.registerMutation(THAUMIUMDUST, THAUMIUMSHARD, 8);
            tMutation.addMutationCondition(new GTBees.BiomeIDMutationCondition(192, "Magical Forest")); // magical
                                                                                                        // forest
        }),
    TAINTED(GTBranchDefinition.THAUMIC, "Tainted", true, new Color(0x904BB8), new Color(0xE800FF), beeSpecies -> {
        beeSpecies.addProduct(GTModHandler.getModItem(Forestry.ID, "beeCombs", 1, 3), 0.30f);
        beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.TAINTED), 0.20f);
        beeSpecies.setHumidity(EnumHumidity.NORMAL);
        beeSpecies.setTemperature(EnumTemperature.NORMAL);
        beeSpecies.setHasEffect();
    }, template -> {
        AlleleHelper.instance.set(template, NOCTURNAL, true);
        AlleleHelper.instance.set(template, CAVE_DWELLING, true);
        AlleleHelper.instance.set(template, TOLERANT_FLYER, true);
        AlleleHelper.instance.set(template, FERTILITY, Fertility.LOW);
        AlleleHelper.instance.set(template, TEMPERATURE_TOLERANCE, Tolerance.BOTH_1);
        AlleleHelper.instance.set(template, HUMIDITY_TOLERANCE, Tolerance.BOTH_1);
        AlleleHelper.instance.set(template, LIFESPAN, Lifespan.SHORT);
        AlleleHelper.instance.set(template, FLOWER_PROVIDER, getFlowers(EXTRABEES, "rock"));
    }, dis -> {
        IBeeMutationCustom tMutation = dis.registerMutation(THAUMIUMDUST, THAUMIUMSHARD, 7);
        tMutation.addMutationCondition(new GTBees.BiomeIDMutationCondition(193, "Tainted Land")); // Tainted Land
    }),
    ASTRALSILVER(GTBranchDefinition.THAUMIC, "AstralSilver", true, new Color(0xAFEEEE), new Color(0xE6E6FF),
        beeSpecies -> {
            beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.SILVER), 0.20f);
            beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.ASTRALSILVER), 0.125f);
            beeSpecies.setHumidity(EnumHumidity.NORMAL);
            beeSpecies.setTemperature(EnumTemperature.NORMAL);
        }, template -> {
            AlleleHelper.instance.set(template, NOCTURNAL, true);
            AlleleHelper.instance.set(template, CAVE_DWELLING, true);
            AlleleHelper.instance.set(template, TOLERANT_FLYER, true);
            AlleleHelper.instance.set(template, FERTILITY, Fertility.LOW);
            AlleleHelper.instance.set(template, TEMPERATURE_TOLERANCE, Tolerance.BOTH_1);
            AlleleHelper.instance.set(template, HUMIDITY_TOLERANCE, Tolerance.BOTH_1);
            AlleleHelper.instance.set(template, LIFESPAN, Lifespan.SHORT);
            AlleleHelper.instance.set(template, FLOWER_PROVIDER, getFlowers(EXTRABEES, "rock"));
        }, dis -> {
            IBeeMutationCustom tMutation = dis.registerMutation(SILVER, IRON, 3);
            tMutation.requireResource(GregTechAPI.sBlockMetal1, 6);
        }),
    THAUMINITE(GTBranchDefinition.THAUMIC, "Thauminite", true, new Color(0x2E2D79), new Color(0x7581E0), beeSpecies -> {
        beeSpecies.addProduct(GTModHandler.getModItem(MagicBees.ID, "comb", 1, 19), 0.20f);
        beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.THAUMINITE), 0.125f);
        beeSpecies.setHumidity(EnumHumidity.NORMAL);
        beeSpecies.setTemperature(EnumTemperature.NORMAL);
        beeSpecies.setHasEffect();
    }, template -> {
        AlleleHelper.instance.set(template, SPEED, Speed.SLOWER);
        AlleleHelper.instance.set(template, LIFESPAN, Lifespan.SHORT);
        AlleleHelper.instance.set(template, FLOWERING, Flowering.SLOW);
        AlleleHelper.instance.set(template, NOCTURNAL, true);
    }, dis -> {
        IBeeMutationCustom tMutation = dis.registerMutation(getSpecies(MAGICBEES, "TCOrder"), THAUMIUMDUST, 8);
        if (ThaumicBases.isModLoaded())
            tMutation.requireResource(GameRegistry.findBlock(ThaumicBases.ID, "thauminiteBlock"), 0);
    }),
    SHADOWMETAL(GTBranchDefinition.THAUMIC, "ShadowMetal", true, new Color(0x100322), new Color(0x100342),
        beeSpecies -> {
            beeSpecies.addProduct(GTModHandler.getModItem(MagicBees.ID, "comb", 1, 20), 0.20f);
            beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.SHADOWMETAL), 0.125f);
            beeSpecies.setHumidity(EnumHumidity.NORMAL);
            beeSpecies.setTemperature(EnumTemperature.NORMAL);
            beeSpecies.setHasEffect();
        }, template -> {
            AlleleHelper.instance.set(template, SPEED, Speed.SLOWER);
            AlleleHelper.instance.set(template, LIFESPAN, Lifespan.SHORT);
            AlleleHelper.instance.set(template, FLOWERING, Flowering.SLOW);
            AlleleHelper.instance.set(template, TEMPERATURE_TOLERANCE, Tolerance.NONE);
            AlleleHelper.instance.set(template, HUMIDITY_TOLERANCE, Tolerance.NONE);
            AlleleHelper.instance.set(template, NOCTURNAL, true);
        }, dis -> {
            IBeeMutationCustom tMutation = dis
                .registerMutation(getSpecies(MAGICBEES, "TCChaos"), getSpecies(MAGICBEES, "TCVoid"), 6);
            if (TaintedMagic.isModLoaded()) {
                tMutation.requireResource("blockShadow");
            }
        }),
    DIVIDED(GTBranchDefinition.THAUMIC, "Unstable", true, new Color(0xF0F0F0), new Color(0xDCDCDC), beeSpecies -> {
        beeSpecies.addProduct(GTModHandler.getModItem(ExtraBees.ID, "honeyComb", 1, 61), 0.20f);
        beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.DIVIDED), 0.125f);
        beeSpecies.setHumidity(EnumHumidity.NORMAL);
        beeSpecies.setTemperature(EnumTemperature.NORMAL);
        beeSpecies.setHasEffect();
    }, template -> {
        AlleleHelper.instance.set(template, SPEED, Speed.SLOWER);
        AlleleHelper.instance.set(template, LIFESPAN, Lifespan.SHORT);
        AlleleHelper.instance.set(template, FLOWERING, Flowering.SLOW);
        AlleleHelper.instance.set(template, NOCTURNAL, true);
    }, dis -> {
        IBeeMutationCustom tMutation = dis.registerMutation(DIAMOND, IRON, 3);
        if (ExtraUtilities.isModLoaded())
            tMutation.requireResource(GameRegistry.findBlock(ExtraUtilities.ID, "decorativeBlock1"), 5);
    }),
    CAELESTIS(GTBranchDefinition.THAUMIC, "Caelestis", true, new Color(0xF0F0F0), new Color(0xDCDCDC), beeSpecies -> {
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.CAELESTISRED), 0.60f);
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.CAELESTISBLUE), 0.60f);
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.CAELESTISGREEN), 0.60f);
        beeSpecies.setHumidity(EnumHumidity.NORMAL);
        beeSpecies.setTemperature(EnumTemperature.NORMAL);
        beeSpecies.setHasEffect();
    }, template -> {
        AlleleHelper.instance.set(template, SPEED, Speed.SLOWER);
        AlleleHelper.instance.set(template, LIFESPAN, Lifespan.SHORT);
        AlleleHelper.instance.set(template, FLOWERING, Flowering.SLOW);
        AlleleHelper.instance.set(template, NOCTURNAL, true);
    }, dis -> dis.registerMutation(DIAMOND, DIVIDED, 10)),
    SPARKELING(GTBranchDefinition.THAUMIC, "NetherStar", true, new Color(0x7A007A), new Color(0xFFFFFF), beeSpecies -> {
        beeSpecies.addProduct(GTModHandler.getModItem(MagicBees.ID, "miscResources", 1, 3), 0.20f);
        beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.SPARKLING), 0.125f);
        beeSpecies.setHumidity(EnumHumidity.NORMAL);
        beeSpecies.setTemperature(EnumTemperature.NORMAL);
    }, template -> {
        AlleleHelper.instance.set(template, TEMPERATURE_TOLERANCE, Tolerance.DOWN_2);
        AlleleHelper.instance.set(template, NOCTURNAL, true);
        AlleleHelper.instance.set(template, CAVE_DWELLING, true);
        AlleleHelper.instance.set(template, FLOWER_PROVIDER, Flowers.NETHER);
        AlleleHelper.instance.set(template, LIFESPAN, Lifespan.SHORT);
        AlleleHelper.instance.set(template, EFFECT, AlleleEffect.effectAggressive);
        AlleleHelper.instance.set(template, FLOWERING, Flowering.AVERAGE);
    }, dis -> {
        IBeeMutationCustom tMutation = dis
            .registerMutation(getSpecies(MAGICBEES, "Withering"), getSpecies(MAGICBEES, "Draconic"), 1);
        tMutation.requireResource(GregTechAPI.sBlockGem3, 3);
        tMutation.addMutationCondition(new GTBees.BiomeIDMutationCondition(9, "END Biome")); // sky end biome
    }),

    ESSENTIA(GTBranchDefinition.THAUMIC, "Essentia", true, new Color(0x7A007A), new Color(0xFFFFFF), beeSpecies -> {
        beeSpecies.addProduct(GTModHandler.getModItem(MagicBees.ID, "miscResources", 1, 3), 0.20f);
        beeSpecies.setHumidity(EnumHumidity.NORMAL);
        beeSpecies.setTemperature(EnumTemperature.NORMAL);
    }, template -> {
        AlleleHelper.instance.set(template, TEMPERATURE_TOLERANCE, Tolerance.DOWN_2);
        AlleleHelper.instance.set(template, CAVE_DWELLING, true);
        AlleleHelper.instance.set(template, FLOWER_PROVIDER, Flowers.VANILLA);
        AlleleHelper.instance.set(template, EFFECT, AlleleEffect.effectReanimation);
    }, dis -> {
        IBeeMutationCustom tMutation = dis.registerMutation(SHADOWMETAL, SPARKELING, 5);
        if (Thaumcraft.isModLoaded())
            tMutation.requireResource(GameRegistry.findBlock(Thaumcraft.ID, "blockCrystal"), 6);
    }),

    DRAKE(GTBranchDefinition.THAUMIC, "Drake", true, new Color(0x100322), new Color(0x7A007A), beeSpecies -> {
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.DRACONIC), 0.30f);
        beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.AWAKENEDDRACONIUM), 0.20f);
        beeSpecies.setHumidity(DAMP);
        beeSpecies.setTemperature(HELLISH);
        beeSpecies.setJubilanceProvider(JubilanceMegaApiary.instance);
    }, template -> {
        AlleleHelper.instance.set(template, TEMPERATURE_TOLERANCE, Tolerance.DOWN_3);
        AlleleHelper.instance.set(template, CAVE_DWELLING, false);
        AlleleHelper.instance.set(template, FLOWER_PROVIDER, Flowers.END);
        AlleleHelper.instance.set(template, EFFECT, AlleleEffect.effectDrunkard);
    }, dis -> {
        IBeeMutationCustom tMutation = dis.registerMutation(ESSENTIA, THAUMINITE, 5);
        GregTechAPI.sGTCompleteLoad.add(() -> tMutation.requireResource(GregTechAPI.sBlockCasings5, 8));
    }),

    // radioctive
    URANIUM(GTBranchDefinition.RADIOACTIVE, "Uranium", true, new Color(0x19AF19), new Color(0x169E16), beeSpecies -> {
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.SLAG), 0.30f);
        beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.URANIUM), 0.15f);
        beeSpecies.setHumidity(EnumHumidity.NORMAL);
        beeSpecies.setTemperature(COLD);
        beeSpecies.setNocturnal();
    }, template -> {
        AlleleHelper.instance.set(template, SPEED, Speed.SLOWEST);
        AlleleHelper.instance.set(template, LIFESPAN, Lifespan.LONGEST);
    }, dis -> {
        IBeeMutationCustom tMutation = dis.registerMutation(getSpecies(FORESTRY, "Avenging"), PLATINUM, 3);
        tMutation.requireResource(GregTechAPI.sBlockMetal7, 14);
    }),
    PLUTONIUM(GTBranchDefinition.RADIOACTIVE, "Plutonium", true, new Color(0x570000), new Color(0x240000),
        beeSpecies -> {
            beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.SLAG), 0.30f);
            beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.LEAD), 0.15f);
            beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.PLUTONIUM), 0.15f);
            beeSpecies.setHumidity(EnumHumidity.NORMAL);
            beeSpecies.setTemperature(ICY);
            beeSpecies.setNocturnal();
        }, template -> {
            AlleleHelper.instance.set(template, SPEED, Speed.SLOWEST);
            AlleleHelper.instance.set(template, LIFESPAN, Lifespan.LONGEST);
        }, dis -> {
            IBeeMutationCustom tMutation = dis.registerMutation(URANIUM, EMERALD, 3);
            tMutation.requireResource(GregTechAPI.sBlockMetal5, 13);
        }),
    NAQUADAH(GTBranchDefinition.RADIOACTIVE, "Naquadah", false, new Color(0x003300), new Color(0x002400),
        beeSpecies -> {
            beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.SLAG), 0.30f);
            beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.NAQUADAH), 0.15f);
            beeSpecies.setHumidity(ARID);
            beeSpecies.setTemperature(ICY);
            beeSpecies.setNocturnal();
            beeSpecies.setHasEffect();
        }, template -> {
            AlleleHelper.instance.set(template, SPEED, Speed.SLOWEST);
            AlleleHelper.instance.set(template, LIFESPAN, Lifespan.LONGEST);
        }, dis -> {
            IBeeMutationCustom tMutation = dis.registerMutation(PLUTONIUM, IRIDIUM, 3);
            tMutation.requireResource(GregTechAPI.sBlockMetal4, 12);
        }),
    NAQUADRIA(GTBranchDefinition.RADIOACTIVE, "Naquadria", false, new Color(0x000000), new Color(0x002400),
        beeSpecies -> {
            beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.SLAG), 0.30f);
            beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.NAQUADAH), 0.20f);
            beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.NAQUADRIA), 0.15f);
            beeSpecies.setHumidity(ARID);
            beeSpecies.setTemperature(ICY);
            beeSpecies.setNocturnal();
            beeSpecies.setHasEffect();
        }, template -> {
            AlleleHelper.instance.set(template, SPEED, Speed.SLOWEST);
            AlleleHelper.instance.set(template, LIFESPAN, Lifespan.LONGEST);
        }, dis -> {
            IBeeMutationCustom tMutation = dis.registerMutation(PLUTONIUM, IRIDIUM, 8, 10);
            tMutation.requireResource(GregTechAPI.sBlockMetal4, 15);
        }),
    DOB(GTBranchDefinition.RADIOACTIVE, "DOB", false, new Color(0x003300), new Color(0x002400), beeSpecies -> {
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.DOB), 0.75f);
        beeSpecies.setHumidity(EnumHumidity.NORMAL);
        beeSpecies.setTemperature(EnumTemperature.NORMAL);
        beeSpecies.setNocturnal();
        beeSpecies.setHasEffect();
    }, template -> {
        AlleleHelper.instance.set(template, SPEED, Speed.FAST);
        AlleleHelper.instance.set(template, LIFESPAN, Lifespan.SHORTEST);
    }, dis -> {
        IBeeMutationCustom tMutation = dis.registerMutation(NAQUADAH, THAUMIUMSHARD, 2);
        if (AdvancedSolarPanel.isModLoaded())
            tMutation.requireResource(GregTechAPI.sBlockMachines, MetaTileEntityIDs.SOLAR_PANEL_HV.ID);
        tMutation.addMutationCondition(new GTBees.BiomeIDMutationCondition(9, "END Biome")); // sky end biome
    }),
    THORIUM(GTBranchDefinition.RADIOACTIVE, "Thorium", false, new Color(0x005000), new Color(0x001E00), beeSpecies -> {
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.THORIUM), 0.75f);
        beeSpecies.setHumidity(EnumHumidity.NORMAL);
        beeSpecies.setTemperature(COLD);
        beeSpecies.setNocturnal();
    }, template -> {
        AlleleHelper.instance.set(template, SPEED, Speed.SLOWEST);
        AlleleHelper.instance.set(template, LIFESPAN, Lifespan.LONGEST);
    }, dis -> {
        IMutationCustom tMutation = dis.registerMutation(COAL, URANIUM, 3)
            .setIsSecret();
        tMutation.requireResource(GregTechAPI.sBlockMetal7, 5);
    }),
    LUTETIUM(GTBranchDefinition.RADIOACTIVE, "Lutetium", false, new Color(0xE6FFE6), new Color(0xFFFFFF),
        beeSpecies -> {
            beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.LUTETIUM), 0.15f);
            beeSpecies.setHumidity(EnumHumidity.NORMAL);
            beeSpecies.setTemperature(EnumTemperature.NORMAL);
            beeSpecies.setNocturnal();
            beeSpecies.setHasEffect();
        }, template -> {
            AlleleHelper.instance.set(template, SPEED, Speed.SLOWEST);
            AlleleHelper.instance.set(template, LIFESPAN, Lifespan.LONGEST);
        }, dis -> {
            IMutationCustom tMutation = dis.registerMutation(THORIUM, getSpecies(EXTRABEES, "rotten"), 1)
                .setIsSecret();
            tMutation.requireResource(GregTechAPI.sBlockMetal4, 3);
        }),
    AMERICIUM(GTBranchDefinition.RADIOACTIVE, "Americium", false, new Color(0xE6E6FF), new Color(0xC8C8C8),
        beeSpecies -> {
            beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.AMERICIUM), 0.075f);
            beeSpecies.setHumidity(EnumHumidity.NORMAL);
            beeSpecies.setTemperature(EnumTemperature.NORMAL);
            beeSpecies.setNocturnal();
            beeSpecies.setHasEffect();
            // Makes it only work in the Mega Apiary NOTE: COMB MUST BE SPECIALITY COMB
            beeSpecies.setJubilanceProvider(JubilanceMegaApiary.instance);
        }, template -> {
            AlleleHelper.instance.set(template, SPEED, Speed.SLOWEST);
            AlleleHelper.instance.set(template, LIFESPAN, Lifespan.LONGEST);
        }, dis -> {
            IMutationCustom tMutation = dis.registerMutation(LUTETIUM, CHROME, 5, 4)
                .setIsSecret();
            tMutation.requireResource(GregTechAPI.sBlockMetal1, 2);
        }),
    NEUTRONIUM(GTBranchDefinition.RADIOACTIVE, "Neutronium", false, new Color(0xFFF0F0), new Color(0xFAFAFA),
        beeSpecies -> {
            beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.NEUTRONIUM), 0.02f);
            beeSpecies.setHumidity(DAMP);
            beeSpecies.setTemperature(HELLISH);
            beeSpecies.setHasEffect();
        }, template -> {
            AlleleHelper.instance.set(template, SPEED, Speed.SLOWEST);
            AlleleHelper.instance.set(template, LIFESPAN, Lifespan.LONGEST);
            AlleleHelper.instance.set(template, NOCTURNAL, true);
        }, dis -> {
            IMutationCustom tMutation = dis.registerMutation(NAQUADRIA, AMERICIUM, 2, 2)
                .setIsSecret();
            tMutation.requireResource(GregTechAPI.sBlockMetal5, 2);
        }),
    // HEE
    ENDDUST(GTBranchDefinition.HEE, "End Dust", true, new Color(0xCC00FA), new Color(0x003A7D), beeSpecies -> {
        beeSpecies.addProduct(GTModHandler.getModItem(Forestry.ID, "beeCombs", 1, 8), 0.30f);
        beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.ENDDUST), 0.10f);
        beeSpecies.setHumidity(ARID);
        beeSpecies.setTemperature(EnumTemperature.NORMAL);
        beeSpecies.setHasEffect();
    }, template -> AlleleHelper.instance.set(template, LIFESPAN, Lifespan.NORMAL), dis -> {
        IBeeMutationCustom tMutation = dis.registerMutation(getSpecies(FORESTRY, "Ended"), STAINLESSSTEEL, 8);

        tMutation.restrictHumidity(ARID);
        if (HardcoreEnderExpansion.isModLoaded())
            tMutation.requireResource(GameRegistry.findBlock(HardcoreEnderExpansion.ID, "end_powder_ore"), 0);
        tMutation.addMutationCondition(new GTBees.DimensionMutationCondition(1, "End")); // End Dim
    }),
    ENDIUM(GTBranchDefinition.HEE, "Endium", true, new Color(0xa0ffff), new Color(0x2F5A6C), beeSpecies -> {
        beeSpecies.addProduct(GTModHandler.getModItem(Forestry.ID, "beeCombs", 1, 8), 0.30f);
        beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.ENDIUM), 0.10f);
        beeSpecies.addSpecialty(GTBees.propolis.getStackForType(PropolisType.Endium), 0.15f);
        beeSpecies.addSpecialty(GTBees.drop.getStackForType(DropType.ENDERGOO), 0.10f);
        beeSpecies.setHumidity(ARID);
        beeSpecies.setTemperature(EnumTemperature.NORMAL);
        beeSpecies.setHasEffect();
    }, template -> AlleleHelper.instance.set(template, LIFESPAN, Lifespan.NORMAL), dis -> {
        IBeeMutationCustom tMutation = dis.registerMutation(getSpecies(FORESTRY, "Ended"), THAUMIUMDUST, 8);
        tMutation.restrictHumidity(ARID);
        if (HardcoreEnderExpansion.isModLoaded()) tMutation.requireResource("blockHeeEndium");
        tMutation.addMutationCondition(new GTBees.DimensionMutationCondition(1, "End")); // End Dim
    }),
    STARDUST(GTBranchDefinition.HEE, "Star Dust", true, new Color(0xffff00), new Color(0xDCBE13), beeSpecies -> {
        beeSpecies.addProduct(GTModHandler.getModItem(Forestry.ID, "beeCombs", 1, 8), 0.30f);
        beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.STARDUST), 0.10f);
        beeSpecies.setHumidity(ARID);
        beeSpecies.setTemperature(EnumTemperature.NORMAL);
        beeSpecies.setHasEffect();
    }, template -> {
        AlleleHelper.instance.set(template, SPEED, GTBees.speedBlinding);
        AlleleHelper.instance.set(template, LIFESPAN, Lifespan.SHORTEST);
        AlleleHelper.instance.set(template, FLOWERING, Flowering.SLOWER);
    }, dis -> {
        IBeeMutationCustom tMutation = dis.registerMutation(getSpecies(FORESTRY, "Ended"), ZINC, 8);
        tMutation.restrictHumidity(ARID);
        if (HardcoreEnderExpansion.isModLoaded())
            tMutation.requireResource(GameRegistry.findBlock(HardcoreEnderExpansion.ID, "stardust_ore"), 0);
        tMutation.addMutationCondition(new GTBees.DimensionMutationCondition(1, "End")); // End Dim
    }),
    ECTOPLASMA(GTBranchDefinition.HEE, "Ectoplasma", true, new Color(0xDCB0E5), new Color(0x381C40), beeSpecies -> {
        beeSpecies.addProduct(GTModHandler.getModItem(Forestry.ID, "beeCombs", 1, 8), 0.30f);
        beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.ECTOPLASMA), 0.10f);
        beeSpecies.setHumidity(ARID);
        beeSpecies.setTemperature(EnumTemperature.NORMAL);
        beeSpecies.setHasEffect();
    }, template -> {
        AlleleHelper.instance.set(template, LIFESPAN, Lifespan.SHORT);
        AlleleHelper.instance.set(template, TEMPERATURE_TOLERANCE, Tolerance.BOTH_1);
        AlleleHelper.instance.set(template, HUMIDITY_TOLERANCE, Tolerance.BOTH_1);
    }, dis -> {
        IBeeMutationCustom tMutation = dis.registerMutation(getSpecies(FORESTRY, "Ended"), ENDDUST, 5);
        tMutation.restrictHumidity(ARID);
        if (HardcoreEnderExpansion.isModLoaded())
            tMutation.requireResource(GameRegistry.findBlock(HardcoreEnderExpansion.ID, "spooky_log"), 0);
        tMutation.addMutationCondition(new GTBees.DimensionMutationCondition(1, "End")); // End Dim
    }),
    ARCANESHARDS(GTBranchDefinition.HEE, "Arcane Shards", true, new Color(0x9010AD), new Color(0x333D82),
        beeSpecies -> {
            beeSpecies.addProduct(GTModHandler.getModItem(Forestry.ID, "beeCombs", 1, 8), 0.30f);
            beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.ARCANESHARD), 0.10f);
            beeSpecies.setHumidity(ARID);
            beeSpecies.setTemperature(EnumTemperature.NORMAL);
            beeSpecies.setHasEffect();
        }, template -> {
            AlleleHelper.instance.set(template, LIFESPAN, Lifespan.LONG);
            AlleleHelper.instance.set(template, TEMPERATURE_TOLERANCE, Tolerance.BOTH_1);
            AlleleHelper.instance.set(template, HUMIDITY_TOLERANCE, Tolerance.BOTH_1);
        }, dis -> {
            IBeeMutationCustom tMutation = dis.registerMutation(THAUMIUMSHARD, ENDDUST, 5);
            tMutation.restrictHumidity(ARID);
            if (HardcoreEnderExpansion.isModLoaded())
                tMutation.requireResource(GameRegistry.findBlock(HardcoreEnderExpansion.ID, "laboratory_floor"), 0);
            tMutation.addMutationCondition(new GTBees.DimensionMutationCondition(1, "End")); // End Dim
        }),
    DRAGONESSENCE(GTBranchDefinition.HEE, "Dragonessence", true, new Color(0xFFA12B), new Color(0x911ECE),
        beeSpecies -> {
            beeSpecies.addProduct(GTModHandler.getModItem(Forestry.ID, "beeCombs", 1, 8), 0.30f);
            beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.DRAGONESSENCE), 0.10f);
            beeSpecies.setHumidity(ARID);
            beeSpecies.setTemperature(EnumTemperature.NORMAL);
            beeSpecies.setHasEffect();
        }, template -> {
            AlleleHelper.instance.set(template, LIFESPAN, Lifespan.LONGER);
            AlleleHelper.instance.set(template, EFFECT, AlleleEffect.effectBeatific);
            AlleleHelper.instance.set(template, TEMPERATURE_TOLERANCE, Tolerance.BOTH_3);
            AlleleHelper.instance.set(template, HUMIDITY_TOLERANCE, Tolerance.BOTH_3);
        }, dis -> {
            IBeeMutationCustom tMutation = dis.registerMutation(ECTOPLASMA, ARCANESHARDS, 4);
            tMutation.restrictHumidity(ARID);
            if (HardcoreEnderExpansion.isModLoaded())
                tMutation.requireResource(GameRegistry.findBlock(HardcoreEnderExpansion.ID, "essence_altar"), 0);
            tMutation.addMutationCondition(new GTBees.DimensionMutationCondition(1, "End")); // End Dim
        }),
    FIREESSENCE(GTBranchDefinition.HEE, "Fireessence", true, new Color(0xD41238), new Color(0xFFA157), beeSpecies -> {
        beeSpecies.addProduct(GTModHandler.getModItem(Forestry.ID, "beeCombs", 1, 8), 0.30f);
        beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.FIREESSENSE), 0.10f);
        beeSpecies.setHumidity(ARID);
        beeSpecies.setTemperature(HELLISH);
        beeSpecies.setHasEffect();
    }, template -> {
        AlleleHelper.instance.set(template, LIFESPAN, Lifespan.ELONGATED);
        AlleleHelper.instance.set(template, EFFECT, AlleleEffect.effectIgnition);
        AlleleHelper.instance.set(template, TEMPERATURE_TOLERANCE, Tolerance.UP_3);
        AlleleHelper.instance.set(template, HUMIDITY_TOLERANCE, Tolerance.DOWN_3);
    }, dis -> {
        IBeeMutationCustom tMutation = dis.registerMutation(FIRESTONE, ARCANESHARDS, 4);
        tMutation.restrictHumidity(ARID);
        if (HardcoreEnderExpansion.isModLoaded())
            tMutation.requireResource(GameRegistry.findBlock(HardcoreEnderExpansion.ID, "essence_altar"), 2);
        tMutation.addMutationCondition(new GTBees.DimensionMutationCondition(1, "End")); // End Dim
    }),
    ENDERMANHEAD(GTBranchDefinition.HEE, "EndermanHead", true, new Color(0x161616), new Color(0x6200e7), beeSpecies -> {
        beeSpecies.addProduct(GTModHandler.getModItem(Forestry.ID, "beeCombs", 1, 8), 0.30f);
        beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.ENDERMAN), 0.10f);
        beeSpecies.setHumidity(ARID);
        beeSpecies.setTemperature(EnumTemperature.NORMAL);
        beeSpecies.setHasEffect();
    }, template -> {
        AlleleHelper.instance.set(template, LIFESPAN, Lifespan.LONGER);
        AlleleHelper.instance.set(template, EFFECT, getEffect(EXTRABEES, "teleport"));
        AlleleHelper.instance.set(template, TEMPERATURE_TOLERANCE, Tolerance.UP_1);
        AlleleHelper.instance.set(template, HUMIDITY_TOLERANCE, Tolerance.UP_1);
    }, dis -> {
        IBeeMutationCustom tMutation = dis.registerMutation(ENDERIUM, STARDUST, 4);
        tMutation.restrictHumidity(ARID);
        if (HardcoreEnderExpansion.isModLoaded())
            tMutation.requireResource(GameRegistry.findBlock(HardcoreEnderExpansion.ID, "ender_goo"), 0);
        tMutation.addMutationCondition(new GTBees.DimensionMutationCondition(1, "End")); // End Dim
    }),
    SILVERFISH(GTBranchDefinition.HEE, "Silverfisch", true, new Color(0xEE053D), new Color(0x000000), beeSpecies -> {
        beeSpecies.addProduct(GTModHandler.getModItem(Forestry.ID, "beeCombs", 1, 8), 0.30f);
        beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.SILVERFISH), 0.10f);
        beeSpecies.setHumidity(ARID);
        beeSpecies.setTemperature(EnumTemperature.NORMAL);
        beeSpecies.setHasEffect();
    }, template -> {
        AlleleHelper.instance.set(template, LIFESPAN, Lifespan.LONGER);
        AlleleHelper.instance.set(template, EFFECT, getEffect(MAGICBEES, "SlowSpeed"));
        AlleleHelper.instance.set(template, TEMPERATURE_TOLERANCE, Tolerance.DOWN_1);
        AlleleHelper.instance.set(template, HUMIDITY_TOLERANCE, Tolerance.DOWN_1);
    }, dis -> {
        IBeeMutationCustom tMutation = dis.registerMutation(ECTOPLASMA, STARDUST, 5);
        tMutation.restrictHumidity(ARID);
        if (HardcoreEnderExpansion.isModLoaded())
            tMutation.requireResource(GameRegistry.findBlock(HardcoreEnderExpansion.ID, "ender_goo"), 0);
        tMutation.addMutationCondition(new GTBees.DimensionMutationCondition(1, "End")); // End Dim
    }),
    RUNE(GTBranchDefinition.HEE, "Rune", true, new Color(0xE31010), new Color(0x0104D9), beeSpecies -> {
        beeSpecies.addProduct(GTModHandler.getModItem(Forestry.ID, "beeCombs", 1, 8), 0.30f);
        beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.RUNEI), 0.025f);
        beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.RUNEII), 0.0125f);
        beeSpecies.setHumidity(ARID);
        beeSpecies.setTemperature(HELLISH);
        beeSpecies.setHasEffect();
    }, template -> {
        AlleleHelper.instance.set(template, LIFESPAN, GTBees.superLife);
        AlleleHelper.instance.set(template, EFFECT, getEffect(MAGICBEES, "SlowSpeed"));
        AlleleHelper.instance.set(template, TEMPERATURE_TOLERANCE, Tolerance.NONE);
        AlleleHelper.instance.set(template, HUMIDITY_TOLERANCE, Tolerance.NONE);
    }, dis -> {
        IMutationCustom tMutation = dis.registerMutation(DRAGONESSENCE, STARDUST, 2)
            .setIsSecret();
        tMutation.restrictHumidity(ARID);
        if (EnderStorage.isModLoaded())
            tMutation.requireResource(GameRegistry.findBlock(EnderStorage.ID, "enderChest"), 0);
        tMutation.addMutationCondition(new GTBees.DimensionMutationCondition(1, "End")); // End Dim
    }),
    // Walrus Bee, 100% Combchance, Parents: Catty and Watery
    WALRUS(GTBranchDefinition.PLANET, "Walrus", true, new Color(0xD6D580), new Color(0xB5CFC9), beeSpecies -> {
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.WALRUS), 1.00f);
        beeSpecies.setHumidity(DAMP);
        beeSpecies.setTemperature(EnumTemperature.NORMAL);
        beeSpecies.setHasEffect();
    }, template -> {
        AlleleHelper.instance.set(template, SPEED, Speed.FASTEST);
        AlleleHelper.instance.set(template, LIFESPAN, Lifespan.LONGER);
        AlleleHelper.instance.set(template, TEMPERATURE_TOLERANCE, Tolerance.BOTH_1);
        AlleleHelper.instance.set(template, HUMIDITY_TOLERANCE, Tolerance.BOTH_1);
        AlleleHelper.instance.set(template, NOCTURNAL, true);
    }, dis -> {
        IBeeMutationCustom tMutation = dis
            .registerMutation(getSpecies(MAGICBEES, "Watery"), getSpecies(MAGICBEES, "Catty"), 45, 2);
        if (ExtraCells2.isModLoaded()) {
            tMutation.requireResource(GameRegistry.findBlock(ExtraCells2.ID, "walrus"), 0);
        }
    }),
    MACHINIST(GTBranchDefinition.ORGANIC, "Machinist", true, new Color(85, 37, 130), new Color(253, 185, 39),
        beeSpecies -> {
            beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.MACHINIST), 0.2f);
            beeSpecies.setHumidity(EnumHumidity.NORMAL);
            beeSpecies.setTemperature(NORMAL);
            beeSpecies.setHasEffect();
        }, template -> {
            AlleleHelper.instance.set(template, SPEED, Speed.FASTEST);
            AlleleHelper.instance.set(template, LIFESPAN, Lifespan.LONGEST);
            AlleleHelper.instance.set(template, TEMPERATURE_TOLERANCE, Tolerance.BOTH_1);
            AlleleHelper.instance.set(template, HUMIDITY_TOLERANCE, Tolerance.BOTH_1);
            AlleleHelper.instance.set(template, NOCTURNAL, true);
            AlleleHelper.instance.set(template, FLOWER_PROVIDER, GTFlowers.FLAMING);
            AlleleHelper.instance.set(template, FERTILITY, Fertility.MAXIMUM);
            AlleleHelper.instance.set(template, EFFECT, getEffect(GREGTECH, "MachineBoost"));
        }, dis -> {
            IBeeMutationCustom tMutation = dis
                .registerMutation(getSpecies(FORESTRY, "Industrious"), getSpecies(FORESTRY, "Imperial"), 1);
            tMutation.addMutationCondition(new GTBees.ActiveGTMachineMutationCondition());
        }),

    // Infinity Line
    COSMICNEUTRONIUM(GTBranchDefinition.PLANET, "CosmicNeutronium", false, new Color(0x484848), new Color(0x323232),
        beeSpecies -> {
            beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.COSMICNEUTRONIUM), 0.375f);
            beeSpecies.setHumidity(DAMP);
            beeSpecies.setTemperature(ICY);
            beeSpecies.setNocturnal();
            beeSpecies.setHasEffect();
            // Makes it only work in the Mega Apiary NOTE: COMB MUST BE SPECIALITY COMB
            beeSpecies.setJubilanceProvider(JubilanceMegaApiary.instance);
        }, template -> AlleleHelper.instance.set(template, LIFESPAN, Lifespan.SHORTEST), dis -> {
            IBeeMutationCustom tMutation = dis.registerMutation(NEUTRONIUM, BEDROCKIUM, 7, 10);
            if (Avaritia.isModLoaded())
                tMutation.requireResource(GameRegistry.findBlock(Avaritia.ID, "Resource_Block"), 0);
        }),
    INFINITYCATALYST(GTBranchDefinition.PLANET, "InfinityCatalyst", false, new Color(0xFFFFFF), new Color(0xFFFFFF),
        beeSpecies -> {
            beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.INFINITYCATALYST), 0.015f);
            beeSpecies.setHumidity(DAMP);
            beeSpecies.setTemperature(HELLISH);
            beeSpecies.setNocturnal();
            beeSpecies.setHasEffect();
            // Makes it only work in the Mega Apiary NOTE: COMB MUST BE SPECIALITY COMB
            beeSpecies.setJubilanceProvider(JubilanceMegaApiary.instance);
        }, template -> {
            AlleleHelper.instance.set(template, LIFESPAN, Lifespan.SHORTEST);
            AlleleHelper.instance.set(template, EFFECT, getEffect(EXTRABEES, "blindness"));
        }, dis -> {
            IMutationCustom tMutation = dis.registerMutation(DOB, COSMICNEUTRONIUM, 3, 10)
                .setIsSecret();
            if (Avaritia.isModLoaded()) {
                tMutation.requireResource(GameRegistry.findBlock(Avaritia.ID, "Resource_Block"), 1);
            }
        }),
    INFINITY(GTBranchDefinition.PLANET, "Infinity", false, new Color(0xFFFFFF), new Color(0xFFFFFF), beeSpecies -> {
        beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.INFINITY), 0.015f);
        beeSpecies.setHumidity(EnumHumidity.NORMAL);
        beeSpecies.setTemperature(ICY);
        beeSpecies.setNocturnal();
        beeSpecies.setHasEffect();
        // Makes it only work in the Mega Apiary NOTE: COMB MUST BE SPECIALITY COMB
        beeSpecies.setJubilanceProvider(JubilanceMegaApiary.instance);
    }, template -> AlleleHelper.instance.set(template, LIFESPAN, Lifespan.SHORTEST), dis -> {
        IBeeMutationCustom tMutation = dis.registerMutation(INFINITYCATALYST, COSMICNEUTRONIUM, 1, 10);
        if (AvaritiaAddons.isModLoaded()) {
            tMutation.requireResource(GameRegistry.findBlock(AvaritiaAddons.ID, "InfinityChest"), 0);
        }
    }),
    KEVLAR(GTBranchDefinition.IC2, "Kevlar", false, new Color(0x2d542f), new Color(0xa2baa3), beeSpecies -> {
        beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.KEVLAR), 0.075f);
        beeSpecies.addSpecialty(MaterialsKevlar.Kevlar.getNuggets(1), 0.01f);
        beeSpecies.setHumidity(DAMP);
        beeSpecies.setTemperature(COLD);
        beeSpecies.setHasEffect();
        beeSpecies.setJubilanceProvider(JubilanceMegaApiary.instance);
    }, template -> {
        AlleleHelper.instance.set(template, SPEED, Speed.SLOWEST);
        AlleleHelper.instance.set(template, LIFESPAN, Lifespan.LONGEST);
        AlleleHelper.instance.set(template, EFFECT, AlleleEffect.effectSnowing);
        AlleleHelper.instance.set(template, TEMPERATURE_TOLERANCE, Tolerance.NONE);
        AlleleHelper.instance.set(template, NOCTURNAL, true);
        AlleleHelper.instance.set(template, FLOWER_PROVIDER, Flowers.SNOW);
        AlleleHelper.instance.set(template, FLOWERING, Flowering.AVERAGE);
    }, dis -> {
        IBeeMutationCustom tMutation = dis.registerMutation(OIL, INFINITYCATALYST, 4);
        // UHV Replicator (UU-Matter)
        GregTechAPI.sGTCompleteLoad.add(() -> tMutation.requireResource(GregTechAPI.sBlockMachines, 11003));
    }),

    // Noble Gas Line
    // Helium bee, Humidity: normal, Temperature: Icy, Parents: Space & Mars, Mutationrate: 10%, Combrate: 50%
    HELIUM(GTBranchDefinition.NOBLEGAS, "Helium", false, new Color(0xFFA9FF), new Color(0xC8B8B4), beeSpecies -> {
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.HELIUM), 0.35f);
        beeSpecies.setHumidity(EnumHumidity.NORMAL);
        beeSpecies.setTemperature(ICY);
        beeSpecies.setNocturnal();
        beeSpecies.setHasEffect();
    }, template -> AlleleHelper.instance.set(template, LIFESPAN, Lifespan.SHORTEST), dis -> {
        IBeeMutationCustom tMutation = dis.registerMutation(getSpecies(MAGICBEES, "Watery"), ENDDUST, 10);
        tMutation.restrictTemperature(ICY);
    }),
    // Oxygen bee, Humidity: normal, Temperature: Icy, Parents: Space & Callisto, Mutationrate: 15%, Combrate: 50%
    OXYGEN(GTBranchDefinition.NOBLEGAS, "Oxygen", false, new Color(0xFFFFFF), new Color(0x8F8FFF), beeSpecies -> {
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.OXYGEN), 0.45f);
        beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.HYDROGEN), 0.20f);
        beeSpecies.setHumidity(EnumHumidity.NORMAL);
        beeSpecies.setTemperature(ICY);
        beeSpecies.setNocturnal();
        beeSpecies.setHasEffect();
    }, template -> AlleleHelper.instance.set(template, LIFESPAN, Lifespan.SHORTEST), dis -> {
        IBeeMutationCustom tMutation = dis.registerMutation(HELIUM, DRAGONESSENCE, 15);
        tMutation.restrictTemperature(ICY);
    }),
    // Hydrogen bee, Humidity: normal, Temperature: Icy, Parents: Oxygen & Watery, Mutationrate: 15%, Combrate: 50%
    HYDROGEN(GTBranchDefinition.NOBLEGAS, "Oxygen", false, new Color(0xFFFFFF), new Color(0xFF1493), beeSpecies -> {
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.HYDROGEN), 0.45f);
        beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.NITROGEN), 0.20f);
        beeSpecies.setHumidity(EnumHumidity.NORMAL);
        beeSpecies.setTemperature(ICY);
        beeSpecies.setNocturnal();
        beeSpecies.setHasEffect();
    }, template -> AlleleHelper.instance.set(template, LIFESPAN, Lifespan.SHORTEST), dis -> {
        IBeeMutationCustom tMutation = dis.registerMutation(OXYGEN, getSpecies(MAGICBEES, "Watery"), 15);
        tMutation.restrictTemperature(ICY);
    }),
    // Nitrogen bee, Humidity: normal, Temperature: Icy, Parents: Oxygen & Hydrogen, Mutationrate: 15%, Combrate: 50%
    NITROGEN(GTBranchDefinition.NOBLEGAS, "Nitrogen", false, new Color(0xFFC832), new Color(0xA52A2A), beeSpecies -> {
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.NITROGEN), 0.45f);
        beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.FLUORINE), 0.20f);
        beeSpecies.setHumidity(EnumHumidity.NORMAL);
        beeSpecies.setTemperature(ICY);
        beeSpecies.setNocturnal();
        beeSpecies.setHasEffect();
    }, template -> AlleleHelper.instance.set(template, LIFESPAN, Lifespan.SHORTEST), dis -> {
        IBeeMutationCustom tMutation = dis.registerMutation(OXYGEN, HYDROGEN, 15);
        tMutation.restrictTemperature(ICY);
    }),
    // Fluorine bee, Humidity: normal, Temperature: Icy, Parents: Nitrogen & Hydrogen, Mutationrate: 15%, Combrate: 50%
    FLUORINE(GTBranchDefinition.NOBLEGAS, "Fluorine", false, new Color(0x86AFF0), new Color(0xFF6D00), beeSpecies -> {
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.FLUORINE), 0.45f);
        beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.OXYGEN), 0.20f);
        beeSpecies.setHumidity(EnumHumidity.NORMAL);
        beeSpecies.setTemperature(ICY);
        beeSpecies.setNocturnal();
        beeSpecies.setHasEffect();
    }, template -> AlleleHelper.instance.set(template, LIFESPAN, Lifespan.SHORTEST), dis -> {
        IBeeMutationCustom tMutation = dis.registerMutation(NITROGEN, HYDROGEN, 15);
        tMutation.restrictTemperature(ICY);
    }),
    // Europium line, needed after fluorine definition
    RAREEARTH(GTBranchDefinition.RAREMETAL, "RareEarth", false, new Color(0x555643), new Color(0x343428),
        beeSpecies -> {
            beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.RAREEARTH), 0.20f);
            beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.REFINEDRAREEARTH), 0.05f);
            beeSpecies.setHumidity(EnumHumidity.NORMAL);
            beeSpecies.setTemperature(NORMAL);
            // Makes it only work in the Mega Apiary NOTE: COMB MUST BE SPECIALITY COMB
            beeSpecies.setJubilanceProvider(JubilanceMegaApiary.instance);
        }, template -> AlleleHelper.instance.set(template, SPEED, Speed.SLOWEST),
        dis -> dis.registerMutation(FLUORINE, REDSTONE, 10)),

    NEODYMIUM(GTBranchDefinition.RAREMETAL, "Neodymium", false, new Color(0x555555), new Color(0x4F4F4F),
        beeSpecies -> {
            beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.RAREEARTH), 0.15f);
            beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.NEODYMIUM), 0.15f);
            beeSpecies.setHumidity(EnumHumidity.NORMAL);
            beeSpecies.setTemperature(HOT);
        }, template -> {
            AlleleHelper.instance.set(template, SPEED, Speed.SLOWEST);
            AlleleHelper.instance.set(template, LIFESPAN, Lifespan.LONGEST);
        }, dis -> {
            IBeeMutationCustom tMutation = dis.registerMutation(RAREEARTH, IRON, 10);
            tMutation.requireResource(GregTechAPI.sBlockMetal5, 0);
        }),
    EUROPIUM(GTBranchDefinition.RAREMETAL, "Europium", false, new Color(0xDAA0E2), new Color(0xAB7EB1), beeSpecies -> {
        beeSpecies.addProduct(WerkstoffMaterialPool.EuropiumIIIOxide.get(OrePrefixes.dust, 1), 0.10F);
        beeSpecies.addSpecialty(GTBees.combs.getStackForType(CombType.EUROPIUM), 0.075f);
        beeSpecies.setHumidity(EnumHumidity.NORMAL);
        beeSpecies.setTemperature(HOT);
        beeSpecies.setNocturnal();
        // Makes it only work in the Mega Apiary NOTE: COMB MUST BE SPECIALITY COMB
        beeSpecies.setJubilanceProvider(JubilanceMegaApiary.instance);
    }, template -> {
        AlleleHelper.instance.set(template, SPEED, Speed.SLOWEST);
        AlleleHelper.instance.set(template, LIFESPAN, Lifespan.LONGEST);
    }, dis -> {
        IBeeMutationCustom tMutation = dis.registerMutation(NEODYMIUM, HYDROGEN, 5, 4);
        tMutation.requireResource(GregTechAPI.sBlockMetal3, 3);
    }),
    // infused Shards line
    AIR(GTBranchDefinition.INFUSEDSHARD, "Air", false, new Color(0xFFFF7E), new Color(0x60602F), beeSpecies -> {
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.INFUSEDAER), 0.30f);
        beeSpecies.setHumidity(DAMP);
        beeSpecies.setHasEffect();
    }, template -> AlleleHelper.instance.set(template, LIFESPAN, Lifespan.SHORTEST), dis -> {
        IBeeMutationCustom tMutation = dis
            .registerMutation(getSpecies(MAGICBEES, "Supernatural"), getSpecies(MAGICBEES, "Windy"), 15);
        tMutation.restrictTemperature(HOT);
        if (Thaumcraft.isModLoaded())
            tMutation.requireResource(GameRegistry.findBlock(Thaumcraft.ID, "blockCrystal"), 0);
    }),
    FIRE(GTBranchDefinition.INFUSEDSHARD, "Fire", false, new Color(0xED3801), new Color(0x3B0E00), beeSpecies -> {
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.INFUSEDIGNIS), 0.30f);
        beeSpecies.setHumidity(ARID);
        beeSpecies.setHasEffect();
    }, template -> AlleleHelper.instance.set(template, LIFESPAN, Lifespan.SHORTEST), dis -> {
        IBeeMutationCustom tMutation = dis.registerMutation(getSpecies(MAGICBEES, "Supernatural"), AIR, 15);
        tMutation.restrictTemperature(HELLISH);
        if (Thaumcraft.isModLoaded())
            tMutation.requireResource(GameRegistry.findBlock(Thaumcraft.ID, "blockCrystal"), 1);
    }),
    WATER(GTBranchDefinition.INFUSEDSHARD, "Water", false, new Color(0x0090FF), new Color(0x002542), beeSpecies -> {
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.INFUSEDAQUA), 0.30f);
        beeSpecies.setHumidity(EnumHumidity.NORMAL);
        beeSpecies.setHasEffect();
    }, template -> AlleleHelper.instance.set(template, LIFESPAN, Lifespan.SHORTEST), dis -> {
        IBeeMutationCustom tMutation = dis.registerMutation(FIRE, AIR, 15);
        tMutation.restrictTemperature(ICY);
        if (Thaumcraft.isModLoaded())
            tMutation.requireResource(GameRegistry.findBlock(Thaumcraft.ID, "blockCrystal"), 2);
    }),
    EARTH(GTBranchDefinition.INFUSEDSHARD, "Earth", false, new Color(0x008600), new Color(0x003300), beeSpecies -> {
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.INFUSEDTERRA), 0.30f);
        beeSpecies.setHumidity(EnumHumidity.NORMAL);
        beeSpecies.setHasEffect();
    }, template -> AlleleHelper.instance.set(template, LIFESPAN, Lifespan.SHORTEST), dis -> {
        IBeeMutationCustom tMutation = dis.registerMutation(WATER, FIRE, 15);
        tMutation.restrictTemperature(WARM);
        if (Thaumcraft.isModLoaded())
            tMutation.requireResource(GameRegistry.findBlock(Thaumcraft.ID, "blockCrystal"), 3);
    }),
    ORDER(GTBranchDefinition.INFUSEDSHARD, "Order", false, new Color(0x8A97B0), new Color(0x5C5F62), beeSpecies -> {
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.INFUSEDORDO), 0.30f);
        beeSpecies.setHumidity(EnumHumidity.NORMAL);
        beeSpecies.setHasEffect();
    }, template -> AlleleHelper.instance.set(template, LIFESPAN, Lifespan.SHORTEST), dis -> {
        IBeeMutationCustom tMutation = dis.registerMutation(EARTH, FIRE, 15);
        tMutation.restrictTemperature(ICY);
        if (Thaumcraft.isModLoaded())
            tMutation.requireResource(GameRegistry.findBlock(Thaumcraft.ID, "blockCrystal"), 4);
    }),
    CHAOS(GTBranchDefinition.INFUSEDSHARD, "Chaos", false, new Color(0x2E2E41), new Color(0x232129), beeSpecies -> {
        beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.INFUSEDPERDITIO), 0.30f);
        beeSpecies.setHumidity(EnumHumidity.NORMAL);
        beeSpecies.setHasEffect();
    }, template -> AlleleHelper.instance.set(template, LIFESPAN, Lifespan.SHORTEST), dis -> {
        IBeeMutationCustom tMutation = dis.registerMutation(ORDER, FIRE, 15);
        tMutation.restrictTemperature(ICY);
        if (Thaumcraft.isModLoaded())
            tMutation.requireResource(GameRegistry.findBlock(Thaumcraft.ID, "blockCrystal"), 5);
    }),
    NETHERSHARD(GTBranchDefinition.INFUSEDSHARD, "NetherShard", false, new Color(0xBE0135), new Color(0x350211),
        beeSpecies -> {
            beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.NETHERSHARD), 0.30f);
            beeSpecies.setHumidity(ARID);
            beeSpecies.setTemperature(HOT);
            beeSpecies.setHasEffect();
        }, template -> AlleleHelper.instance.set(template, LIFESPAN, Lifespan.SHORTEST), dis -> {
            IBeeMutationCustom tMutation = dis.registerMutation(CHAOS, FIRE, 15);
            GregTechAPI.sGTCompleteLoad.add(() -> tMutation.requireResource(GregTechAPI.sBlockGem3, 3));
        }),
    ENDSHARD(GTBranchDefinition.INFUSEDSHARD, "EnderShard", false, new Color(0x2E2E41), new Color(0x232129),
        beeSpecies -> {
            beeSpecies.addProduct(GTBees.combs.getStackForType(CombType.ENDERSHARD), 0.30f);
            beeSpecies.setHumidity(EnumHumidity.NORMAL);
            beeSpecies.setNocturnal();
            beeSpecies.setHasEffect();
        }, template -> AlleleHelper.instance.set(template, LIFESPAN, Lifespan.SHORTEST), dis -> {
            IBeeMutationCustom tMutation = dis.registerMutation(NETHERSHARD, ENDDUST, 15);
            tMutation.restrictTemperature(ICY);
            GregTechAPI.sGTCompleteLoad.add(() -> tMutation.requireResource(GregTechAPI.sBlockGem1, 7));
        }),
    // Endgame bees
    JAEGERMEISTER(GTBranchDefinition.ENDGAME, "JaegerMeister", false, new Color(0x05AD18), new Color(0xE7DAC3),
        beeSpecies -> {
            beeSpecies.addProduct(GTModHandler.getModItem(CropsPlusPlus.ID, "BppPotions", 1L, 8), 0.01f);
            beeSpecies.setHumidity(EnumHumidity.NORMAL);
            beeSpecies.setNocturnal();
            beeSpecies.setHasEffect();
        }, template -> AlleleHelper.instance.set(template, LIFESPAN, Lifespan.SHORTEST), dis -> {
            IBeeMutationCustom tMutation = dis.registerMutation(INFINITYCATALYST, NAQUADRIA, 5);
            tMutation.requireResource(GregTechAPI.sBlockMachines, 4684);
            tMutation.addMutationCondition(new GTBees.DimensionMutationCondition(100, "Deep Dark")); // Deep Dark
                                                                                                     // dim
        });

    private final GTBranchDefinition branch;
    private final GTAlleleBeeSpecies species;
    private final Consumer<GTAlleleBeeSpecies> mSpeciesProperties;
    private final Consumer<IAllele[]> mAlleles;
    private final Consumer<GTBeeDefinition> mMutations;
    private IAllele[] template;
    private IBeeGenome genome;

    GTBeeDefinition(GTBranchDefinition branch, String binomial, boolean dominant, Color primary, Color secondary,
        Consumer<GTAlleleBeeSpecies> aSpeciesProperties, Consumer<IAllele[]> aAlleles,
        Consumer<GTBeeDefinition> aMutations) {
        this.mAlleles = aAlleles;
        this.mMutations = aMutations;
        this.mSpeciesProperties = aSpeciesProperties;
        String lowercaseName = this.toString()
            .toLowerCase(Locale.ENGLISH);
        String species = WordUtils.capitalize(lowercaseName);

        String uid = "gregtech5.bee.species" + species;
        String description = "for.description." + lowercaseName;
        String name = "for.bees.species." + lowercaseName;
        GTLanguageManager.addStringLocalization("for.bees.species." + lowercaseName, species);

        String authority = GTLanguageManager.getTranslation("for.bees.authority." + lowercaseName);
        if (authority.equals("for.bees.authority." + lowercaseName)) {
            authority = "GTNH";
        }
        this.branch = branch;
        this.species = new GTAlleleBeeSpecies(
            uid,
            dominant,
            name,
            authority,
            description,
            branch.getBranch(),
            binomial,
            primary,
            secondary);
    }

    public static void initBees() {
        for (GTBeeDefinition bee : values()) {
            bee.init();
        }
        for (GTBeeDefinition bee : values()) {
            bee.registerMutations();
        }
    }

    static IAlleleBeeEffect getEffect(byte modid, String name) {
        String s = switch (modid) {
            case EXTRABEES -> "extrabees.effect." + name;
            case GENDUSTRY -> "gendustry.effect." + name;
            case MAGICBEES -> "magicbees.effect" + name;
            case GREGTECH -> "gregtech5.effect" + name;
            default -> "forestry.effect" + name;
        };
        IAlleleBeeEffect allele = (IAlleleBeeEffect) AlleleManager.alleleRegistry.getAllele(s);
        if (allele == null) {
            GTMod.GT_FML_LOGGER.warn("Attempted to get unknown bee effect: " + s);
            allele = GTAlleleEffect.FORESTRY_BASE_EFFECT;
        }
        return allele;
    }

    static IAlleleFlowers getFlowers(byte modid, String name) {
        String s = switch (modid) {
            case EXTRABEES -> "extrabees.flower." + name;
            case GENDUSTRY -> "gendustry.flower." + name;
            case MAGICBEES -> "magicbees.flower" + name;
            case GREGTECH -> "gregtech5.flower" + name;
            default -> "forestry.flowers" + name;
        };
        IAlleleFlowers allele = (IAlleleFlowers) AlleleManager.alleleRegistry.getAllele(s);
        if (allele == null) {
            GTMod.GT_FML_LOGGER.warn("Attempted to get unknown bee flower: " + s);
        }
        return allele;
    }

    private static IAlleleBeeSpecies getSpecies(byte modid, String name) {
        String s = switch (modid) {
            case EXTRABEES -> "extrabees.species." + name;
            case GENDUSTRY -> "gendustry.bee." + name;
            case MAGICBEES -> "magicbees.species" + name;
            case GREGTECH -> "gregtech5.species" + name;
            default -> "forestry.species" + name;
        };
        IAlleleBeeSpecies ret = (IAlleleBeeSpecies) AlleleManager.alleleRegistry.getAllele(s);
        if (ret == null) {
            GTMod.GT_FML_LOGGER.warn("Attempted to get unknown bee species: " + s);
            ret = NAQUADRIA.species;
        }

        return ret;
    }

    private void setSpeciesProperties(GTAlleleBeeSpecies beeSpecies) {
        this.mSpeciesProperties.accept(beeSpecies);
    }

    private void setAlleles(IAllele[] template) {
        this.mAlleles.accept(template);
    }

    private void registerMutations() {
        this.mMutations.accept(this);
    }

    private void init() {
        setSpeciesProperties(species);

        template = branch.getTemplate();
        AlleleHelper.instance.set(template, SPECIES, species);
        setAlleles(template);

        genome = BeeManager.beeRoot.templateAsGenome(template);

        BeeManager.beeRoot.registerTemplate(template);
    }

    private IBeeMutationCustom registerMutation(IAlleleBeeSpecies parent1, IAlleleBeeSpecies parent2, int chance) {
        return registerMutation(parent1, parent2, chance, 1f);
    }

    private IBeeMutationCustom registerMutation(GTBeeDefinition parent1, IAlleleBeeSpecies parent2, int chance) {
        return registerMutation(parent1, parent2, chance, 1f);
    }

    private IBeeMutationCustom registerMutation(IAlleleBeeSpecies parent1, GTBeeDefinition parent2, int chance) {
        return registerMutation(parent1, parent2, chance, 1f);
    }

    private IBeeMutationCustom registerMutation(GTBeeDefinition parent1, GTBeeDefinition parent2, int chance) {
        return registerMutation(parent1, parent2, chance, 1f);
    }

    /**
     * Diese neue Funtion erlaubt Mutationsraten unter 1%. Setze dazu die Mutationsrate als Bruch mit chance /
     * chanceDivider This new function allows Mutation percentages under 1%. Set them as a fraction with chance /
     * chanceDivider
     */
    private IBeeMutationCustom registerMutation(IAlleleBeeSpecies parent1, IAlleleBeeSpecies parent2, int chance,
        float chanceDivider) {
        return new GTBeeMutation(parent1, parent2, this.getTemplate(), chance, chanceDivider);
    }

    private IBeeMutationCustom registerMutation(GTBeeDefinition parent1, IAlleleBeeSpecies parent2, int chance,
        float chanceDivider) {
        return registerMutation(parent1.species, parent2, chance, chanceDivider);
    }

    private IBeeMutationCustom registerMutation(IAlleleBeeSpecies parent1, GTBeeDefinition parent2, int chance,
        float chanceDivider) {
        return registerMutation(parent1, parent2.species, chance, chanceDivider);
    }

    private IBeeMutationCustom registerMutation(GTBeeDefinition parent1, GTBeeDefinition parent2, int chance,
        float chanceDivider) {
        return registerMutation(parent1.species, parent2, chance, chanceDivider);
    }

    @Override
    public final IAllele[] getTemplate() {
        return Arrays.copyOf(template, template.length);
    }

    @Override
    public final IBeeGenome getGenome() {
        return genome;
    }

    @Override
    public final IBee getIndividual() {
        return new Bee(genome);
    }

    @Override
    public final ItemStack getMemberStack(EnumBeeType beeType) {
        return BeeManager.beeRoot.getMemberStack(getIndividual(), beeType.ordinal());
    }

    public GTAlleleBeeSpecies getSpecies() {
        return species;
    }
}
