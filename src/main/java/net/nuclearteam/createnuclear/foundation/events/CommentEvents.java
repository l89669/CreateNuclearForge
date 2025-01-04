package net.nuclearteam.createnuclear.foundation.events;

import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.event.TickEvent.ServerTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.nuclearteam.createnuclear.CNFluids;
import net.nuclearteam.createnuclear.CreateNuclear;

@Mod.EventBusSubscriber
public class CommentEvents {
    @SubscribeEvent
    public static void onServerTick(ServerTickEvent event) {
        if (event.phase == Phase.START) return;
    }
}
