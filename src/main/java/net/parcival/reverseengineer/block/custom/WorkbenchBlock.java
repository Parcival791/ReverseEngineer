package net.parcival.reverseengineer.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.parcival.reverseengineer.ReverseEngineer;
import net.parcival.reverseengineer.block.entity.ModBlockEntities;
import net.parcival.reverseengineer.block.entity.WorkbenchBlockEntity;
import org.jetbrains.annotations.Nullable;

public class WorkbenchBlock extends BaseEntityBlock {
    public WorkbenchBlock(Properties pProperties) {
        super(pProperties);
        ReverseEngineer.LOGGER.info("new Block instance");
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new WorkbenchBlockEntity(pPos, pState);
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pMovedByPiston) {
        if (pState.getBlock() != pNewState.getBlock()) {
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if (blockEntity instanceof WorkbenchBlockEntity) {
                //((WorkbenchBlockEntity) blockEntity).drops(); //TODO
            }
        }
        super.onRemove(pState, pLevel, pPos, pNewState, pMovedByPiston);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        ReverseEngineer.LOGGER.info("use registered");
        if (pLevel.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            BlockEntity blockentity = pLevel.getBlockEntity(pPos);
            ServerPlayer serverPlayer = (ServerPlayer) pPlayer;
            ReverseEngineer.LOGGER.info(serverPlayer.toString());
            if (blockentity instanceof WorkbenchBlockEntity) {
                ReverseEngineer.LOGGER.info("tried opening menu");
                serverPlayer.openMenu((WorkbenchBlockEntity) blockentity, buf -> buf.writeBlockPos(pPos));
                ReverseEngineer.LOGGER.info("after opening menu");
            }
            return InteractionResult.CONSUME;
        }
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        if (pLevel.isClientSide) {return null;}
        return createTickerHelper(pBlockEntityType, ModBlockEntities.WORKBENCH_BE.get(),
                ((pLevel1, pPos, pState1, pBlockEntity) -> pBlockEntity.tick(pLevel1,pPos,pState1)));
    }
}
