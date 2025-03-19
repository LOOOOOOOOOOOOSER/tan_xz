package world.tan_xz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author 谭轩钊
 * version 1.0
 */

@Document(collection = "QueryLogs")
public class QueryLogs {
    /**
     * 主键ID
     */

    @Id
    @Field("_id")
    private String Id;

    /**
     * 用户问题
     */
    private String query;

    /**
     * 回复
     */
    private String response;

    /**
     * 响应时间
     */
    @Field("response_time_ms")
    private Double responseTimeMs;

    /**
     * 提问时间
     */
    private String timestamp;

    /**
     * token消耗
     */
    @Field("tokens_used")
    private Integer tokensUsed;

    @TableField(exist = false)
    private Double cost;
    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Double getResponseTimeMs() {
        return responseTimeMs;
    }

    public void setResponseTimeMs(Double responseTimeMs) {
        this.responseTimeMs = responseTimeMs;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getTokensUsed() {
        return tokensUsed;
    }

    public void setTokensUsed(Integer tokensUsed) {
        this.tokensUsed = tokensUsed;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public QueryLogs() {
    }
    public QueryLogs(String query, String response, Double responseTimeMs, String timestamp, Integer tokensUsed) {
        this.query = query;
        this.response = response;
        this.responseTimeMs = responseTimeMs;
        this.timestamp = timestamp;
        this.tokensUsed = tokensUsed;
    }
}
