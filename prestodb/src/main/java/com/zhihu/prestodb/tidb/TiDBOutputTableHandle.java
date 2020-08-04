package com.zhihu.prestodb.tidb;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

import com.facebook.presto.spi.ConnectorInsertTableHandle;
import com.facebook.presto.spi.ConnectorOutputTableHandle;
import com.facebook.presto.spi.type.Type;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableList;
import java.util.List;
import java.util.Objects;
import javax.annotation.Nullable;

public class TiDBOutputTableHandle implements ConnectorOutputTableHandle,
    ConnectorInsertTableHandle {

  private final String connectorId;
  private final String schemaName;
  private final String tableName;
  private final List<String> columnNames;
  private final List<Type> columnTypes;

  @JsonCreator
  public TiDBOutputTableHandle(
      @JsonProperty("connectorId") String connectorId,
      @JsonProperty("schemaName") @Nullable String schemaName,
      @JsonProperty("tableName") String tableName,
      @JsonProperty("columnNames") List<String> columnNames,
      @JsonProperty("columnTypes") List<Type> columnTypes) {
    this.connectorId = requireNonNull(connectorId, "connectorId is null");
    this.schemaName = schemaName;
    this.tableName = requireNonNull(tableName, "tableName is null");

    requireNonNull(columnNames, "columnNames is null");
    requireNonNull(columnTypes, "columnTypes is null");
    checkArgument(columnNames.size() == columnTypes.size(),
        "columnNames and columnTypes sizes don't match");
    this.columnNames = ImmutableList.copyOf(columnNames);
    this.columnTypes = ImmutableList.copyOf(columnTypes);
  }

  @JsonProperty
  public String getConnectorId() {
    return connectorId;
  }

  @JsonProperty
  @Nullable
  public String getSchemaName() {
    return schemaName;
  }

  @JsonProperty
  public String getTableName() {
    return tableName;
  }

  @JsonProperty
  public List<String> getColumnNames() {
    return columnNames;
  }

  @JsonProperty
  public List<Type> getColumnTypes() {
    return columnTypes;
  }


  @Override
  public String toString() {
    return format("jdbc:%s.%s", schemaName, tableName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        connectorId,
        schemaName,
        tableName,
        columnNames,
        columnTypes);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    TiDBOutputTableHandle other = (TiDBOutputTableHandle) obj;
    return Objects.equals(this.connectorId, other.connectorId)
        && Objects.equals(this.schemaName, other.schemaName)
        && Objects.equals(this.tableName, other.tableName)
        && Objects.equals(this.columnNames, other.columnNames)
        && Objects.equals(this.columnTypes, other.columnTypes);
  }


}
