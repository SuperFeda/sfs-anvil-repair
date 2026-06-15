package net.superfeda.sfs_anvil_repair.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AnvilBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import net.superfeda.sfs_anvil_repair.SFsAnvilRepairConfig;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;


@Mixin(BlockBehaviour.class)
public class AnvilBlockMixin {

    @Inject(
            method = "useItemOn(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/InteractionHand;Lnet/minecraft/world/phys/BlockHitResult;)Lnet/minecraft/world/ItemInteractionResult;",
            at = @At("HEAD"),
            cancellable = true
    )
    protected void useItemOnAnvilBlock(
            ItemStack stack,
            BlockState state,
            Level level,
            BlockPos pos,
            Player player,
            InteractionHand hand,
            BlockHitResult hitResult,
            CallbackInfoReturnable<ItemInteractionResult> cir
    ) {
        Block anvilBlock = state.getBlock();
        if (anvilBlock != Blocks.CHIPPED_ANVIL && anvilBlock != Blocks.DAMAGED_ANVIL) {
            cancelWithResult(ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION, cir);
            return;
        }

        ResourceLocation repairItemId = ResourceLocation.tryParse(SFsAnvilRepairConfig.REPAIR_ITEM.get());
        if (repairItemId == null) {
            cancelWithResult(ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION, cir);
            return;
        }

        Optional<Holder.Reference<Item>> repairItem = BuiltInRegistries.ITEM.getHolder(repairItemId);
        if (repairItem.isEmpty()) {
            cancelWithResult(ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION, cir);
            return;
        }

        if (repairItem.get().value() != stack.getItem()) {
            cancelWithResult(ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION, cir);
            return;
        }

        final int usageCost = SFsAnvilRepairConfig.USAGE_COST.get();
        if (usageCost > 1 && stack.getCount() < usageCost) {
            player.displayClientMessage(
                    Component.translatable("clientMessage.sfs_anvil_repair.lessUsageCost", usageCost),
                    true
            );
            cancelWithResult(ItemInteractionResult.FAIL, cir);
            return;
        }

        Block newBlock = null;
        if (anvilBlock == Blocks.DAMAGED_ANVIL) {
            newBlock = Blocks.CHIPPED_ANVIL;
        } else if (anvilBlock == Blocks.CHIPPED_ANVIL) {
            newBlock = Blocks.ANVIL;
        }

        if (newBlock != null) {
            BlockState newBlockState = newBlock.defaultBlockState().setValue(AnvilBlock.FACING, state.getValue(AnvilBlock.FACING));

            level.setBlock(pos, newBlockState, 1);

            if (!player.isCreative()) {
                stack.setCount(stack.getCount() - usageCost);
            }

            level.playSound(null, pos, SoundEvents.IRON_GOLEM_REPAIR, player.getSoundSource(), 1.0F, 1.0F);

            cancelWithResult(ItemInteractionResult.SUCCESS, cir);
            return;
        }

        cancelWithResult(ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION, cir);
        return;
    }

    @Unique
    private static void cancelWithResult(ItemInteractionResult result, CallbackInfoReturnable<ItemInteractionResult> cir) {
        cir.setReturnValue(result);
        cir.cancel();
    }
}
