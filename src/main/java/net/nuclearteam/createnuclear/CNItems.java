package net.nuclearteam.createnuclear;

import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.world.item.Item;

public class CNItems {

    public static final ItemEntry<Item>
        YELLOWCAKE = CreateNuclear.REGISTRATE
            .item("yellowcake", Item::new)
            .register()
    ;

    public static void register() {}
}
