package com.synergy.quern.compat.jade.provider;

import static com.synergy.quern.Main.MODULE_ID;

import javax.annotation.Nullable;

import com.devdyna.cakesticklib.api.datagen.LangUtils.TipColors;
import com.devdyna.cakesticklib.api.utils.x;
import com.synergy.quern.Config;
import com.synergy.quern.init.builder.quern.QuernBE;
import com.synergy.quern.init.builder.quern.QuernBlock;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;
import snownee.jade.api.*;
import snownee.jade.api.config.IPluginConfig;

public class QuernSpeedProvider {

  public static final Identifier UUID = x.rl(MODULE_ID, "quern_speed");

  public static class ServerProvider implements StreamServerDataProvider<BlockAccessor, Double> {
    public static final ServerProvider INSTANCE = new ServerProvider();

    @Override
    public @Nullable Double streamData(BlockAccessor a) {
      return (a.getBlockEntity() instanceof QuernBE be) ? be.getSpeed() : null;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, Double> streamCodec() {
      return ByteBufCodecs.DOUBLE.cast();
    }

    @Override
    public Identifier getUid() {
      return QuernSpeedProvider.UUID;
    }
  }

  public static class ClientProvider implements IBlockComponentProvider {
    public static final ClientProvider INSTANCE = new ClientProvider();

    @Override
    public void appendTooltip(ITooltip t, BlockAccessor a, IPluginConfig c) {

      var v = ServerProvider.INSTANCE.decodeFromData(a);
      var speed = v.orElse(0d);

      var color = (speed >= Config.MAX_QUERN_SPEED.get() || speed <= 0)
          ? TipColors.Modifiers.NEUTRAL
          : (a.getBlockState().getValue(QuernBlock.ENABLED)
              ? TipColors.Modifiers.POSITIVE
              : TipColors.Modifiers.NEGATIVE);

      var formattedSpeed = ((int) (speed * 10.0)) / 10.0;

      t.add(Component.translatable(
          MODULE_ID + ".jade.quern.speed",
          color + "" + formattedSpeed));

    }

    @Override
    public Identifier getUid() {
      return QuernSpeedProvider.UUID;
    }

  }
}