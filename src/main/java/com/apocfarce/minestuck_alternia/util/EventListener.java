package com.apocfarce.minestuck_alternia.util;

import com.apocfarce.minestuck_alternia.network.AlterniaPacketHandler;
import com.apocfarce.minestuck_alternia.network.ShouldDoSelectionPacket;
import com.apocfarce.minestuck_alternia.world.AlterniaDimensions;
import com.apocfarce.minestuck_alternia.world.gen.feature.AlterniaFeatures;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.entity.player.AdvancementEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.server.FMLServerStoppingEvent;
import net.minecraftforge.fml.network.PacketDistributor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.Set;

public class EventListener {
	private static final Logger LOGGER = LogManager.getLogger();
	
	private static final Set<ServerPlayerEntity> playerInSelection = new HashSet<>();
	

	@SubscribeEvent
	public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
		ServerPlayerEntity player = (ServerPlayerEntity) event.getPlayer();
		if(PlayerDataHelper.hasNotSelectedDimension(player)) {
			if(mightBeFirstTimeLogin(player)) {
				playerInSelection.add(player);
				player.canUpdate(false);
				AlterniaPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new ShouldDoSelectionPacket());
			} else PlayerDataHelper.setSelectedDimension(player, false);
		}else {
			player.canUpdate(true);
		}
	}
	
	@SubscribeEvent
	public static void onServerStopped(FMLServerStoppingEvent event) {
		playerInSelection.clear();
	}
	
/*	@SubscribeEvent
	public static void onAdvancement(AdvancementEvent event) {
		if(event.getAdvancement().getCriteria().values().contains(CriteriaTriggers.LOCATION)) {
            //if(criteriaType=="minecraft:enter_block") {
                //Players should not be in selection if they're actually interacting with the game
                // (it'd be better with a different hook that catches more cases,
                // but this should catch all practical ways of abusing dimension selection)
                playerInSelection.remove(event.getPlayer());
            //}
        }	
	}*/
	
	public static void handleDimensionSelection(ServerPlayerEntity player, boolean choseAlternia, BloodColor color) {
		if(playerInSelection.contains(player)) {
			playerInSelection.remove(player);
			PlayerDataHelper.setSelectedDimension(player, choseAlternia);
			if(choseAlternia) {
				PlayerDataHelper.setBloodColor(player, color);
				
				ServerWorld alterniaWorld = DimensionManager.getWorld(player.server, AlterniaDimensions.getDimensionType(), true, true);
				
				if(alterniaWorld != null) {
					BlockPos spawnPos = AlterniaFeatures.HIVE.get().tryFindHiveToOccupy(alterniaWorld, color);
					if(spawnPos != null) {
						player.setSpawnPoint(spawnPos, true, false, AlterniaDimensions.getDimensionType());
					} else {
						LOGGER.warn("Unable to find hive for {} blood color. Player is teleported to spawn.", color.getBloodName());
						alterniaWorld.getChunk(alterniaWorld.getSpawnPoint());    //Make sure that the chunk is created and exists before calling world.getHeight()
						spawnPos = alterniaWorld.getHeight(Heightmap.Type.MOTION_BLOCKING, alterniaWorld.getSpawnPoint());
					}
					
					PortalUtil.teleportEntity(player, alterniaWorld, spawnPos.getX() + 0.5, spawnPos.getY(), spawnPos.getZ() + 0.5, 0, 0);
				} else LOGGER.error("Couldn't teleport player to alternia. Got null world from the DimensionManager!");
			}
		}
		player.canUpdate(true);
	}
	
	private static boolean mightBeFirstTimeLogin(ServerPlayerEntity player) {
		//Used to attempt to tell the difference between a player who logged in to the world for the first time,
		// and a player who had logged in to the world before, but before adding this mod/before this update
		// It's not a surefire way, but it should catch all important situations, by assuming that if a player haven't gotten any
		// advancements, then they have joined for the first time.
		return player.server.getAdvancementManager().getAllAdvancements().stream().noneMatch(advancement ->
				player.getAdvancements().getProgress(advancement).isDone());
	}
}
