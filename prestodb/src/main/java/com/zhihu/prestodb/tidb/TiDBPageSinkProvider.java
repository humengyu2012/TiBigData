package com.zhihu.prestodb.tidb;

import com.facebook.presto.spi.ConnectorInsertTableHandle;
import com.facebook.presto.spi.ConnectorOutputTableHandle;
import com.facebook.presto.spi.ConnectorPageSink;
import com.facebook.presto.spi.ConnectorSession;
import com.facebook.presto.spi.PageSinkProperties;
import com.facebook.presto.spi.connector.ConnectorPageSinkProvider;
import com.facebook.presto.spi.connector.ConnectorTransactionHandle;

public class TiDBPageSinkProvider implements ConnectorPageSinkProvider {

  @Override
  public ConnectorPageSink createPageSink(ConnectorTransactionHandle transactionHandle,
      ConnectorSession session, ConnectorOutputTableHandle outputTableHandle,
      PageSinkProperties pageSinkProperties) {
    return new TiDBPageSink((TiDBOutputTableHandle) outputTableHandle);
  }

  @Override
  public ConnectorPageSink createPageSink(ConnectorTransactionHandle transactionHandle,
      ConnectorSession session, ConnectorInsertTableHandle insertTableHandle,
      PageSinkProperties pageSinkProperties) {
    return new TiDBPageSink((TiDBOutputTableHandle) insertTableHandle);
  }
}
