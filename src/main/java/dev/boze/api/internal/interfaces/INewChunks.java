package dev.boze.api.internal.interfaces;

import dev.boze.api.utility.NewChunksHelper;

public interface INewChunks {

    NewChunksHelper.ChunkAge getAge(int chunkX, int chunkZ);

    NewChunksHelper.ChunkDetail getDetail(int chunkX, int chunkZ);

    void forEachMarked(int centerChunkX, int centerChunkZ, int radius, NewChunksHelper.MarkedChunkConsumer consumer);
}
