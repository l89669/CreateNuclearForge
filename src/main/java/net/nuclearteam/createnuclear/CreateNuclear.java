package net.nuclearteam.createnuclear;


import com.mojang.logging.LogUtils;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.item.ItemDescription;
import com.simibubi.create.foundation.item.KineticStats;
import com.simibubi.create.foundation.item.TooltipHelper;
import com.simibubi.create.foundation.item.TooltipModifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.nuclearteam.createnuclear.infrastructure.data.CreateNuclearDatagen;
import org.slf4j.Logger;

@Mod(CreateNuclear.MOD_ID)
public class CreateNuclear {
    public static final String MOD_ID = "createnuclear";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(MOD_ID)
           .defaultCreativeTab((ResourceKey<CreativeModeTab>) null);

    static {
        REGISTRATE.setTooltipModifierFactory(item -> {
            return new ItemDescription.Modifier(item, TooltipHelper.Palette.STANDARD_CREATE)
                    .andThen(TooltipModifier.mapNull(KineticStats.create(item)));
        });
    }

    public CreateNuclear() {
        onInitialize();
    }

    public static void onInitialize() {
        ModLoadingContext modLoadingContext = ModLoadingContext.get();

        IEventBus modEventBus = FMLJavaModLoadingContext.get()
                .getModEventBus();
        IEventBus forgeEventBus = MinecraftForge.EVENT_BUS;

        REGISTRATE.registerEventListeners(modEventBus);

        CNTags.init();
        CNBlocks.register();
        CNItems.register();
        CNPackets.registerPackets();
        CNMenus.register();

        modEventBus.addListener(CreateNuclear::init);
        modEventBus.addListener(EventPriority.LOWEST, CreateNuclearDatagen::gatherData);


        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> CreateNuclearClient.onCtorClient(modEventBus, forgeEventBus));
    }

    public static void init(final FMLCommonSetupEvent event) {

    }


    public static ResourceLocation asResource(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

}
