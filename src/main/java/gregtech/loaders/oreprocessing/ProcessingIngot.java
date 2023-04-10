package gregtech.loaders.oreprocessing;

import static gregtech.api.util.GT_Recipe.GT_Recipe_Map.sBenderRecipes;
import static gregtech.api.util.GT_Utility.calculateRecipeEU;

import net.minecraft.item.ItemStack;

import gregtech.api.GregTech_API;
import gregtech.api.enums.*;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_OreDictUnificator;
import gregtech.api.util.GT_RecipeRegistrator;
import gregtech.api.util.GT_Utility;
import gregtech.common.GT_Proxy;

public class ProcessingIngot implements gregtech.api.interfaces.IOreRecipeRegistrator {

    public ProcessingIngot() {
        OrePrefixes.ingot.add(this);
        OrePrefixes.ingotDouble.add(this);
        OrePrefixes.ingotTriple.add(this);
        OrePrefixes.ingotQuadruple.add(this);
        OrePrefixes.ingotQuintuple.add(this);
        OrePrefixes.ingotHot.add(this);
    }

    @Override
    public void registerOre(OrePrefixes aPrefix, Materials aMaterial, String aOreDictName, String aModName,
        ItemStack aStack) {
        boolean aNoSmashing = aMaterial.contains(SubTag.NO_SMASHING);
        boolean aNoSmelting = aMaterial.contains(SubTag.NO_SMELTING);
        long aMaterialMass = aMaterial.getMass();
        boolean aSpecialRecipeReq = aMaterial.mUnificatable && (aMaterial.mMaterialInto == aMaterial)
            && !aMaterial.contains(SubTag.NO_SMASHING);

        switch (aPrefix) {
            case ingot:
                // Fuel recipe
                if (aMaterial.mFuelPower > 0) {
                    GT_Values.RA
                        .addFuel(GT_Utility.copyAmount(1L, aStack), null, aMaterial.mFuelPower, aMaterial.mFuelType);
                }
                if (aMaterial.mStandardMoltenFluid != null
                    && !(aMaterial == Materials.AnnealedCopper || aMaterial == Materials.WroughtIron)) {
                    // Fluid solidifier recipes
                    {
                        GT_Values.RA.addFluidSolidifierRecipe(
                            ItemList.Shape_Mold_Ingot.get(0L),
                            aMaterial.getMolten(144L),
                            GT_OreDictUnificator.get(OrePrefixes.ingot, aMaterial, 1L),
                            32,
                            calculateRecipeEU(aMaterial, 8));
                    }

                }
            // Reverse recipes
            {
                GT_RecipeRegistrator.registerReverseFluidSmelting(aStack, aMaterial, aPrefix.mMaterialAmount, null);
                GT_RecipeRegistrator
                    .registerReverseMacerating(aStack, aMaterial, aPrefix.mMaterialAmount, null, null, null, false);
                if (aMaterial.mSmeltInto.mArcSmeltInto != aMaterial) {
                    GT_RecipeRegistrator.registerReverseArcSmelting(
                        GT_Utility.copyAmount(1L, aStack),
                        aMaterial,
                        aPrefix.mMaterialAmount,
                        null,
                        null,
                        null);
                }
            }

                ItemStack tStack = GT_OreDictUnificator.get(OrePrefixes.dust, aMaterial.mMacerateInto, 1L);
                if ((tStack != null) && ((aMaterial.mBlastFurnaceRequired) || aNoSmelting)) {
                    GT_ModHandler.removeFurnaceSmelting(tStack);
                }

                if (aMaterial.mUnificatable && (aMaterial.mMaterialInto == aMaterial)
                    && !aMaterial.contains(SubTag.NO_WORKING)
                    && !aMaterial.contains(SubTag.SMELTING_TO_GEM)
                    && aMaterial.contains(SubTag.MORTAR_GRINDABLE)
                    && GregTech_API.sRecipeFile.get(ConfigCategories.Tools.mortar, aMaterial.mName, true)) {
                    GT_ModHandler.addShapelessCraftingRecipe(
                        GT_OreDictUnificator.get(OrePrefixes.dust, aMaterial, 1L),
                        GT_Proxy.tBits,
                        new Object[] { ToolDictNames.craftingToolMortar, OrePrefixes.ingot.get(aMaterial) });
                }

                if (!aNoSmashing) {
                    // Forge hammer recipes
                    if (aMaterial.getProcessingMaterialTierEU() < TierEU.IV) {
                        GT_Values.RA.addForgeHammerRecipe(
                            GT_Utility.copyAmount(3L, aStack),
                            GT_OreDictUnificator.get(OrePrefixes.plate, aMaterial, 2L),
                            (int) Math.max(aMaterialMass, 1L),
                            calculateRecipeEU(aMaterial, 16));
                    }

                    // Bender recipes
                    {
                        GT_Values.RA.stdBuilder()
                            .itemInputs(GT_Utility.copyAmount(1L, aStack))
                            .itemOutputs(GT_OreDictUnificator.get(OrePrefixes.plate, aMaterial, 1L))
                            .noFluidInputs()
                            .noFluidOutputs()
                            .duration(Math.max(aMaterialMass, 1L))
                            .eut(calculateRecipeEU(aMaterial, 24))
                            .addTo(sBenderRecipes);

                        GT_Values.RA.stdBuilder()
                            .itemInputs(GT_Utility.copyAmount(2L, aStack))
                            .itemOutputs(GT_OreDictUnificator.get(OrePrefixes.plateDouble, aMaterial, 1L))
                            .noFluidInputs()
                            .noFluidOutputs()
                            .duration(Math.max(aMaterialMass * 2L, 1L))
                            .eut(calculateRecipeEU(aMaterial, 96))
                            .addTo(sBenderRecipes);

                        GT_Values.RA.stdBuilder()
                            .itemInputs(GT_Utility.copyAmount(3L, aStack))
                            .itemOutputs(GT_OreDictUnificator.get(OrePrefixes.plateTriple, aMaterial, 1L))
                            .noFluidInputs()
                            .noFluidOutputs()
                            .duration(Math.max(aMaterialMass * 3L, 1L))
                            .eut(calculateRecipeEU(aMaterial, 96))
                            .addTo(sBenderRecipes);

                        GT_Values.RA.stdBuilder()
                            .itemInputs(GT_Utility.copyAmount(4L, aStack))
                            .itemOutputs(GT_OreDictUnificator.get(OrePrefixes.plateQuadruple, aMaterial, 1L))
                            .noFluidInputs()
                            .noFluidOutputs()
                            .duration(Math.max(aMaterialMass * 4L, 1L))
                            .eut(calculateRecipeEU(aMaterial, 96))
                            .addTo(sBenderRecipes);

                        GT_Values.RA.stdBuilder()
                            .itemInputs(GT_Utility.copyAmount(5L, aStack))
                            .itemOutputs(GT_OreDictUnificator.get(OrePrefixes.plateQuintuple, aMaterial, 1L))
                            .noFluidInputs()
                            .noFluidOutputs()
                            .duration(Math.max(aMaterialMass * 5L, 1L))
                            .eut(calculateRecipeEU(aMaterial, 96))
                            .addTo(sBenderRecipes);

                        GT_Values.RA.stdBuilder()
                            .itemInputs(GT_Utility.copyAmount(9L, aStack))
                            .itemOutputs(GT_OreDictUnificator.get(OrePrefixes.plateDense, aMaterial, 1L))
                            .noFluidInputs()
                            .noFluidOutputs()
                            .duration(Math.max(aMaterialMass * 9L, 1L))
                            .eut(calculateRecipeEU(aMaterial, 96))
                            .addTo(sBenderRecipes);

                        GT_Values.RA.stdBuilder()
                            .itemInputs(GT_Utility.copyAmount(1L, aStack), GT_Utility.getIntegratedCircuit(10))
                            .itemOutputs(GT_OreDictUnificator.get(OrePrefixes.foil, aMaterial, 4L))
                            .noFluidInputs()
                            .noFluidOutputs()
                            .duration(Math.max(aMaterialMass * 2L, 1L))
                            .eut(calculateRecipeEU(aMaterial, 24))
                            .addTo(sBenderRecipes);
                    }
                }
                break;

            case ingotDouble:
                if (!aNoSmashing) {
                    // bender recipes
                    {
                        GT_Values.RA.stdBuilder()
                            .itemInputs(GT_Utility.copyAmount(1L, aStack))
                            .itemOutputs(GT_OreDictUnificator.get(OrePrefixes.plateDouble, aMaterial, 1L))
                            .noFluidInputs()
                            .noFluidOutputs()
                            .duration(Math.max(aMaterialMass, 1L))
                            .eut(calculateRecipeEU(aMaterial, 96))
                            .addTo(sBenderRecipes);

                        GT_Values.RA.stdBuilder()
                            .itemInputs(GT_Utility.copyAmount(2L, aStack))
                            .itemOutputs(GT_OreDictUnificator.get(OrePrefixes.plateQuadruple, aMaterial, 1L))
                            .noFluidInputs()
                            .noFluidOutputs()
                            .duration(Math.max(aMaterialMass * 2L, 1L))
                            .eut(calculateRecipeEU(aMaterial, 96))
                            .addTo(sBenderRecipes);
                    }

                    // Enable crafting with hammer if tier is < IV.
                    if (aMaterial.getProcessingMaterialTierEU() < TierEU.IV && aSpecialRecipeReq
                        && GregTech_API.sRecipeFile
                            .get(ConfigCategories.Tools.hammermultiingot, aMaterial.toString(), true)) {
                        GT_ModHandler.addCraftingRecipe(
                            GT_OreDictUnificator.get(OrePrefixes.ingotDouble, aMaterial, 1L),
                            GT_Proxy.tBits,
                            new Object[] { "I", "I", "h", 'I', OrePrefixes.ingot.get(aMaterial) });
                    }
                }
                break;

            case ingotTriple:
                if (!aNoSmashing) {
                    // Bender recipes
                    {
                        GT_Values.RA.stdBuilder()
                            .itemInputs(GT_Utility.copyAmount(1L, aStack))
                            .itemOutputs(GT_OreDictUnificator.get(OrePrefixes.plateTriple, aMaterial, 1L))
                            .noFluidInputs()
                            .noFluidOutputs()
                            .duration(Math.max(aMaterialMass, 1L))
                            .eut(calculateRecipeEU(aMaterial, 96))
                            .addTo(sBenderRecipes);

                        GT_Values.RA.stdBuilder()
                            .itemInputs(GT_Utility.copyAmount(3L, aStack))
                            .itemOutputs(GT_OreDictUnificator.get(OrePrefixes.plateDense, aMaterial, 1L))
                            .noFluidInputs()
                            .noFluidOutputs()
                            .duration(Math.max(aMaterialMass * 3L, 1L))
                            .eut(calculateRecipeEU(aMaterial, 96))
                            .addTo(sBenderRecipes);
                    }

                    if (aMaterial.getProcessingMaterialTierEU() < TierEU.IV && aSpecialRecipeReq
                        && GregTech_API.sRecipeFile
                            .get(ConfigCategories.Tools.hammermultiingot, aMaterial.toString(), true)) {
                        GT_ModHandler.addCraftingRecipe(
                            GT_OreDictUnificator.get(OrePrefixes.ingotTriple, aMaterial, 1L),
                            GT_Proxy.tBits,
                            new Object[] { "I", "B", "h", 'I', OrePrefixes.ingotDouble.get(aMaterial), 'B',
                                OrePrefixes.ingot.get(aMaterial) });
                    }
                }
                break;

            case ingotQuadruple:
                if (!aNoSmashing) {
                    // Bender recipes
                    {
                        GT_Values.RA.stdBuilder()
                            .itemInputs(GT_Utility.copyAmount(1L, aStack))
                            .itemOutputs(GT_OreDictUnificator.get(OrePrefixes.plateQuadruple, aMaterial, 1L))
                            .noFluidInputs()
                            .noFluidOutputs()
                            .duration(Math.max(aMaterialMass, 1L))
                            .eut(calculateRecipeEU(aMaterial, 96))
                            .addTo(sBenderRecipes);
                    }

                    // If tier < IV add manual crafting.
                    if (aMaterial.getProcessingMaterialTierEU() < TierEU.IV && aSpecialRecipeReq
                        && GregTech_API.sRecipeFile
                            .get(ConfigCategories.Tools.hammermultiingot, aMaterial.toString(), true)) {
                        GT_ModHandler.addCraftingRecipe(
                            GT_OreDictUnificator.get(OrePrefixes.ingotQuadruple, aMaterial, 1L),
                            GT_Proxy.tBits,
                            new Object[] { "I", "B", "h", 'I', OrePrefixes.ingotTriple.get(aMaterial), 'B',
                                OrePrefixes.ingot.get(aMaterial) });
                    }
                }
                break;

            case ingotQuintuple:
                if (!aNoSmashing) {
                    // Bender recipes
                    {
                        GT_Values.RA.stdBuilder()
                            .itemInputs(GT_Utility.copyAmount(1L, aStack))
                            .itemOutputs(GT_OreDictUnificator.get(OrePrefixes.plateQuintuple, aMaterial, 1L))
                            .noFluidInputs()
                            .noFluidOutputs()
                            .duration(Math.max(aMaterialMass, 1L))
                            .eut(calculateRecipeEU(aMaterial, 96))
                            .addTo(sBenderRecipes);
                    }

                    // Crafting recipes
                    if (aMaterial.getProcessingMaterialTierEU() < TierEU.IV && aSpecialRecipeReq
                        && GregTech_API.sRecipeFile
                            .get(ConfigCategories.Tools.hammermultiingot, aMaterial.toString(), true)) {
                        GT_ModHandler.addCraftingRecipe(
                            GT_OreDictUnificator.get(OrePrefixes.ingotQuintuple, aMaterial, 1L),
                            GT_Proxy.tBits,
                            new Object[] { "I", "B", "h", 'I', OrePrefixes.ingotQuadruple.get(aMaterial), 'B',
                                OrePrefixes.ingot.get(aMaterial) });
                    }
                }
                break;

            case ingotHot:
                if (aMaterial.mAutoGenerateVacuumFreezerRecipes) {
                    // Vacuum freezer recipes
                    {
                        GT_Values.RA.addVacuumFreezerRecipe(
                            GT_Utility.copyAmount(1L, aStack),
                            GT_OreDictUnificator.get(OrePrefixes.ingot, aMaterial, 1L),
                            (int) Math.max(aMaterialMass * 3L, 1L));
                    }
                }
                break;

            default:
                break;
        }
    }
}
