package com.zhihu.prestodb.tidb;

import static java.util.concurrent.CompletableFuture.completedFuture;

import com.facebook.presto.spi.ConnectorPageSink;
import com.facebook.presto.spi.Page;
import com.facebook.presto.spi.type.Type;
import com.google.common.collect.ImmutableList;
import io.airlift.slice.Slice;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class TiDBPageSink implements ConnectorPageSink {

  private final TiDBOutputTableHandle tiDBOutputTableHandle;

  public TiDBPageSink(TiDBOutputTableHandle tiDBOutputTableHandle) {
    this.tiDBOutputTableHandle = tiDBOutputTableHandle;
  }

  @Override
  public CompletableFuture<?> appendPage(Page page) {
    System.out.println("***********************page***********************");
    final List<String> columnNames = tiDBOutputTableHandle.getColumnNames();
    final List<Type> columnTypes = tiDBOutputTableHandle.getColumnTypes();
    System.out.println(columnNames);
    System.out.println(columnTypes);
    return NOT_BLOCKED;
  }

  @Override
  public CompletableFuture<Collection<Slice>> finish() {
    return completedFuture(ImmutableList.of());
  }

  @Override
  public void abort() {

  }
}
